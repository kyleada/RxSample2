package com.oncedoing.bikeshop.manage_customer.presenter;

import android.util.Log;

import com.oncedoing.bikeshop.manage_customer.datalayer.CustomerStorage;
import com.oncedoing.bikeshop.manage_customer.datalayer.GetCustomerListUsercase;
import com.oncedoing.bikeshop.manage_customer.utils.CharacterParser;
import com.oncedoing.bikeshop.manage_customer.utils.PinyinComparator;
import com.oncedoing.bikeshop.manage_customer.view.ICustomerListView;
import com.oncedoing.bikeshop.model.CustomerEntity;
import com.oncedoing.bikeshop.model.CustomerListFeed;
import com.oncedoing.bikeshop.model.CustomerSortEntity;
import com.oncedoing.bikeshop.model.SortModel;
import com.oncedoing.bikeshop.model.SortToken;
import com.oncedoing.bikeshop.mvp.IBasePresenter;
import com.oncedoing.bikeshop.mvp.IView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2016/3/15.
 */
public class CustomerListPresenter implements IBasePresenter {

    private static final String TAG = "CustomerListPresenter";

    private ICustomerListView customerListView;
    private GetCustomerListUsercase getCustomerListUsercase;

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    /**
     * 名字转拼音,取首字母
     *
     * @param name
     * @return
     */
    private String getSortLetter(String name) {
        String letter = "#";
        if (name == null) {
            return letter;
        }
        //汉字转换成拼音
        String pinyin = characterParser.getSelling(name);
        String sortString = pinyin.substring(0, 1).toUpperCase(Locale.CHINESE);

        // 正则表达式，判断首字母是否是英文字母
        if (sortString.matches("[A-Z]")) {
            letter = sortString.toUpperCase(Locale.CHINESE);
        }
        return letter;
    }

    /**
     * 取sort_key的首字母
     *
     * @param sortKey
     * @return
     */
    private String getSortLetterBySortKey(String sortKey) {
        if (sortKey == null || "".equals(sortKey.trim())) {
            return null;
        }
        String letter = "#";
        //汉字转换成拼音
        String sortString = sortKey.trim().substring(0, 1).toUpperCase(Locale.CHINESE);
        // 正则表达式，判断首字母是否是英文字母
        if (sortString.matches("[A-Z]")) {
            letter = sortString.toUpperCase(Locale.CHINESE);
        }
        return letter;
    }

    String chReg = "[\\u4E00-\\u9FA5]+";//中文字符串匹配

    //String chReg="[^\\u4E00-\\u9FA5]";//除中文外的字符匹配

    /**
     * 解析sort_key,封装简拼,全拼
     *
     * @param sortKey
     * @return
     */
    public SortToken parseSortKey(String sortKey) {
        SortToken token = new SortToken();
        if (sortKey != null && sortKey.length() > 0) {
            //其中包含的中文字符
            String[] enStrs = sortKey.replace(" ", "").split(chReg);
            for (int i = 0, length = enStrs.length; i < length; i++) {
                if (enStrs[i].length() > 0) {
                    //拼接简拼
                    token.simpleSpell += enStrs[i].charAt(0);
                    token.wholeSpell += enStrs[i];
                }
            }
        }
        return token;
    }

    @Inject
    public CustomerListPresenter(GetCustomerListUsercase getCustomerInfoListUsercase) {
        this.getCustomerListUsercase = getCustomerInfoListUsercase;
    }

    @Override
    public void onCreate() {

        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
    }

    @Override
    public void attachView(IView view) {
        customerListView = (ICustomerListView) view;
    }

    @Override
    public void onDestroy() {
        customerListView = null;
    }

    /*
     * 这里的参数isFirst是为了第一次如果加载失败，那么要在content区域显示加载失败
     * 如果不是第一次，也就是content已经有显示内容了，那不就在增加显示加载失败，保留原内容
     */

    public void refreshList(final boolean isFirst) {

        getCustomerListUsercase.getAllCustomerList()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
//                .doOnNext(new Action1<CustomerListFeed>() {
//                    @Override
//                    public void call(final CustomerListFeed customerListFeed) {
//                        Schedulers.io().createWorker().schedule(new Action0() {
//                            @Override
//                            public void call() {
//                                try {
//                                    Log.e(TAG, "call: " + Thread.currentThread().getName());
//                                    CustomerStorage.insertAll(customerListFeed.getResult());
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
//
//                    }
//                })
                .map(new Func1<CustomerListFeed, List<CustomerSortEntity>>() {
                    @Override
                    public List<CustomerSortEntity> call(CustomerListFeed customerListFeed) {

                        Log.e(TAG, "call: " + Thread.currentThread().getName());
                        List<CustomerSortEntity> listSortModel = new ArrayList<>();
                        List<CustomerEntity> listCustomerEntity = customerListFeed.getResult();
                        for (CustomerEntity customer : listCustomerEntity) {

                            String name = customer.getCustomer_name();
                            String sortKey = characterParser.getSelling(name);
                            SortModel sortModel = new SortModel(name, customer.getCustomer_phone(), sortKey);
                            //优先使用系统sortkey取,取不到再使用工具取
                            String sortLetters = getSortLetterBySortKey(sortKey);
                            if (sortLetters == null) {
                                sortLetters = getSortLetter(name);
                            }
                            sortModel.sortLetters = sortLetters;
                            sortModel.sortToken = parseSortKey(sortKey);
                            CustomerSortEntity customerSortEntity = new CustomerSortEntity(sortModel, customer);
                            listSortModel.add(customerSortEntity);
                        }
                        Collections.sort(listSortModel, pinyinComparator);

                        //存储在本地，暂没有看到合适的操作符
//                        Schedulers.io().createWorker().schedule(new Action0() {
//                            @Override
//                            public void call() {
//                                try {
//                                    Log.e(TAG, "call: " + Thread.currentThread().getName());
                        CustomerStorage.deleteAll();
                        CustomerStorage.insertAll(listSortModel);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });


                        //

                        return listSortModel;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CustomerSortEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isFirst) {
                            customerListView.hideLoading();
                            customerListView.showLoadError();
                        } else {
                            customerListView.hideLoading();
                        }
                    }

                    @Override
                    public void onNext(List<CustomerSortEntity> listFeed) {

                        customerListView.refreshList(listFeed);
                    }
                });

    }

    public void readLocalFirst() {

        Observable.concat(CustomerStorage.getAllCustomerWarpByObservable(), readServerObservable())
                .first(new Func1<List<CustomerSortEntity>, Boolean>() {
                    @Override
                    public Boolean call(List<CustomerSortEntity> customerSortEntities) {
                        return customerSortEntities != null && customerSortEntities.size() > 0;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CustomerSortEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        customerListView.hideLoading();
                        customerListView.showLoadError();
                    }

                    @Override
                    public void onNext(List<CustomerSortEntity> listFeed) {

                        customerListView.refreshList(listFeed);
                    }
                });

    }

    private Observable<List<CustomerSortEntity>> readServerObservable() {

        return getCustomerListUsercase.getAllCustomerList()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
//                .doOnNext(new Action1<CustomerListFeed>() {
//                    @Override
//                    public void call(final CustomerListFeed customerListFeed) {
//                        Schedulers.io().createWorker().schedule(new Action0() {
//                            @Override
//                            public void call() {
//                                try {
//                                    Log.e(TAG, "call: " + Thread.currentThread().getName());
//                                    CustomerStorage.insertAll(customerListFeed.getResult());
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
//
//                    }
//                })
                .map(new Func1<CustomerListFeed, List<CustomerSortEntity>>() {
                    @Override
                    public List<CustomerSortEntity> call(CustomerListFeed customerListFeed) {

                        Log.e(TAG, "call: " + Thread.currentThread().getName());
                        List<CustomerSortEntity> listSortModel = new ArrayList<>();
                        List<CustomerEntity> listCustomerEntity = customerListFeed.getResult();
                        for (CustomerEntity customer : listCustomerEntity) {

                            String name = customer.getCustomer_name();
                            String sortKey = characterParser.getSelling(name);
                            SortModel sortModel = new SortModel(name, customer.getCustomer_phone(), sortKey);
                            //优先使用系统sortkey取,取不到再使用工具取
                            String sortLetters = getSortLetterBySortKey(sortKey);
                            if (sortLetters == null) {
                                sortLetters = getSortLetter(name);
                            }
                            sortModel.sortLetters = sortLetters;
                            sortModel.sortToken = parseSortKey(sortKey);
                            CustomerSortEntity customerSortEntity = new CustomerSortEntity(sortModel, customer);
                            listSortModel.add(customerSortEntity);
                        }
                        Collections.sort(listSortModel, pinyinComparator);

                        //存储在本地，暂没有看到合适的操作符
//                        Schedulers.io().createWorker().schedule(new Action0() {
//                            @Override
//                            public void call() {
//                                try {
//                                    Log.e(TAG, "call: " + Thread.currentThread().getName());
                        CustomerStorage.insertAll(listSortModel);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });


                        //

                        return listSortModel;
                    }
                });
    }


}
