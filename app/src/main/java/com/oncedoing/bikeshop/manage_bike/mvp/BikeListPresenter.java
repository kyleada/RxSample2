package com.oncedoing.bikeshop.manage_bike.mvp;

import android.util.Log;

import com.oncedoing.bikeshop.manage_bike.datalayer.GetBikeListUsercase;
import com.oncedoing.bikeshop.model.BikeInfoEntity;
import com.oncedoing.bikeshop.model.BikeListFeed;
import com.oncedoing.bikeshop.mvp.IBasePresenter;
import com.oncedoing.bikeshop.mvp.IView;
import com.oncedoing.bikeshop.utils.UriHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import me.kkwang.commonlib.utils.StringUtils;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/3/15.
 */
public class BikeListPresenter implements IBasePresenter {

    private static final String TAG = "BikeListPresenter";
    boolean isCanLoadMore;
    int currentPage = 1;
    int totalCount;
    int totalPageCount;
    String searchKeyWords = "";
    String topCategoryList;
    Set<String> topCategorySet = new HashSet<>();
    boolean numLimit;
    List<BikeInfoEntity> bikeInfoEntityList;

    private IBikeListView bikeInfoListView;
    private GetBikeListUsercase getBikeInfoListUsercase;

    @Inject
    public BikeListPresenter(GetBikeListUsercase getBookInfoListUsercase) {
        this.getBikeInfoListUsercase = getBookInfoListUsercase;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(IView view) {
        bikeInfoListView = (IBikeListView) view;
    }

    @Override
    public void onDestroy() {
        bikeInfoEntityList = null;
        bikeInfoListView = null;

    }

    /*
     * 这里的参数isFirst是为了第一次如果加载失败，那么要在content区域显示加载失败
     * 如果不是第一次，也就是content已经有显示内容了，那不就在增加显示加载失败，保留原内容
     */

    public void refreshBikeList(final boolean isFirst) {

        currentPage = 1;

		getBikeInfoListUsercase.getBookListByFilterAndPageNum(searchKeyWords,numLimit,topCategoryList,currentPage)
				.subscribe(new Subscriber<BikeListFeed>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if(isFirst){
                    bikeInfoListView.hideLoading();
                    bikeInfoListView.showLoadError();
                }else{
                    bikeInfoListView.hideLoading();
                }
            }

            @Override
            public void onNext(BikeListFeed bikeListFeed) {

                bikeInfoListView.hideLoading();
                bikeInfoEntityList = bikeListFeed.getRows();
                bikeInfoListView.refreshList(bikeInfoEntityList);
                totalCount = bikeListFeed.getTotal();
                totalPageCount = UriHelper.calculateTotalPages(totalCount);
                if(bikeInfoEntityList != null && currentPage < totalPageCount){
                    isCanLoadMore = true;
                }else {
                    isCanLoadMore = false;
                }

            }
        });
    }

    public boolean isCanLoadMore() {
        return isCanLoadMore;
    }

    public void setCanLoadMore(boolean isCanLoadMore){
        this.isCanLoadMore = isCanLoadMore;
    }


    public void loadMoreBikeList(){
        loadMoreBikeList(searchKeyWords);
    }

    public void loadMoreBikeList(String productname){
         if(isCanLoadMore){
             currentPage ++;
             Log.d(TAG, "loadMoreBikeList: currentPage :" + currentPage);
             getBikeInfoListUsercase.getBookListByFilterAndPageNum(productname,numLimit,topCategoryList,currentPage).subscribe(new Subscriber<BikeListFeed>() {
                 @Override
                 public void onCompleted() {

                 }

                 @Override
                 public void onError(Throwable e) {

                 }

                 @Override
                 public void onNext(BikeListFeed bikeListFeed) {
                     bikeInfoEntityList = bikeListFeed.getRows();
                     bikeInfoListView.addList(bikeInfoEntityList);
                     totalCount = bikeListFeed.getTotal();
                     totalPageCount = UriHelper.calculateTotalPages(totalCount);
                     if(bikeInfoEntityList != null && currentPage < totalPageCount){
                         isCanLoadMore = true;
                     }else {
                         isCanLoadMore = false;
                     }
                 }
             });
         }else{
             bikeInfoListView.hideLoading();
             bikeInfoListView.showNoMore();
         }
    }

//    public void searchByKeywords(EditText editText){
//
//        RxTextView.textChanges(editText)
//                .subscribeOn(AndroidSchedulers.mainThread())
//                //delay 500ms
//                //debounce and throttle will use different thread after
//                .throttleWithTimeout(100, TimeUnit.MILLISECONDS,
//                        AndroidSchedulers.mainThread())
//                .distinct()
//                .filter(new Func1<CharSequence, Boolean>() {
//                    @UiThread
//                    @Override public Boolean call(CharSequence charSequence) {
//                        //void unnecessary request
//                        return charSequence.length() > 1;
//                    }
//                })
//                .map(new Func1<CharSequence, String>() {
//                    @UiThread @Override public String call(CharSequence charSequence) {
//                        //fit network api doc require
//
//                        return charSequence.toString();
//                    }
//                })
//                .doOnNext(new Action1<CharSequence>() {
//                    @UiThread @Override public void call(CharSequence charSequence) {
//                        //progressBar.setVisibility(View.VISIBLE);
//                        //arrayList.clear();
//                        // adapter.notifyDataSetChanged();
//                        searchKeyWords = charSequence.toString();
//                    }
//                })
//                .observeOn(Schedulers.io())
//                .switchMap(new Func1<String, Observable<BikeListFeed>>() {
//                    @WorkerThread
//                    @Override public Observable<BikeListFeed> call(String s) {
//                        return getBikeInfoListUsercase.searchByKeywords(s);
//                    }
//                })
//                .retry(new Func2<Integer, Throwable, Boolean>() {
//                    //fix InterruptedIOException bugs on Retrofit
//                    // when stop old ic_search
//                    @WorkerThread @Override public Boolean call(Integer integer, Throwable throwable) {
//                        return throwable instanceof InterruptedIOException;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<BikeListFeed>() {
//                    @Override public void onCompleted() {
//
//                    }
//
//                    @Override public void onError(Throwable e) {
//                        //FIR.sendCrashManually(e);
//                        //progressBar.setVisibility(View.INVISIBLE);
//                        e.printStackTrace();
//                        bikeInfoListView.showSearchError();
//                    }
//
//                    @Override public void onNext(BikeListFeed bikeListFeed) {
//
//                        bikeInfoEntityList = bikeListFeed.getRows();
//                        if(bikeInfoEntityList != null){
//                            totalCount = bikeListFeed.getTotal();
//                            if(totalCount < 1){
//                                bikeInfoListView.showSearchEmpty();
//                            }else{
//                                bikeInfoListView.refreshList(bikeInfoEntityList);
//                                totalPageCount = UriHelper.calculateTotalPages(totalCount);
//                                if(bikeInfoEntityList != null && bikeInfoEntityList.size() <= AppConstants.SINGLE_PAGE_COUNT){
//                                    isCanLoadMore = false;
//                                }else {
//                                    isCanLoadMore = true;
//                                }
//                            }
//                        }else{
//                            bikeInfoListView.showSearchEmpty();
//                        }
//                    }
//                });
//    }

    public void search(String keywords, String topCategoryList,boolean numLimit){

        this.searchKeyWords = keywords;
        this.topCategoryList = topCategoryList;
        this.numLimit = numLimit;

		currentPage = 1;
        getBikeInfoListUsercase.getBookListByFilterAndPageNum(keywords,numLimit,topCategoryList,currentPage)
                .subscribe(new Subscriber<BikeListFeed>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BikeListFeed bikeListFeed) {
                        bikeInfoEntityList = bikeListFeed.getRows();
                        bikeInfoListView.refreshList(bikeInfoEntityList);
                        totalCount = bikeListFeed.getTotal();
                        totalPageCount = UriHelper.calculateTotalPages(totalCount);
                        if(bikeInfoEntityList != null && currentPage < totalPageCount){
                            isCanLoadMore = true;
                        }else {
                            isCanLoadMore = false;
                        }
                    }
                });

    }

    public void search(){

        this.search(this.searchKeyWords,this.topCategoryList,this.numLimit);
    }

    public boolean containsTopCategory(String topCategory){
        return topCategorySet.contains(topCategory);
    }
    public void addTopCategory(String top){
        topCategorySet.add(top);
        bikeInfoListView.showFiltersChosen(true);
        topCategoryList = StringUtils.set2String(topCategorySet);
    }

    public void removeTopCategory(String top){
        topCategorySet.remove(top);
        if(topCategorySet.size() == 0){
            bikeInfoListView.showFiltersChosen(false);
        }
        topCategoryList = StringUtils.set2String(topCategorySet);

    }

    public void setNumLimit(boolean isNumLimit){
        this.numLimit = isNumLimit;
    }

	public void setSearchKeyWords(String keyWords){
		this.searchKeyWords = keyWords;
	}


}
