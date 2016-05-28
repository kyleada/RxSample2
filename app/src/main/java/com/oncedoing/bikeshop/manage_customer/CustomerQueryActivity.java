package com.oncedoing.bikeshop.manage_customer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.oncedoing.bikeshop.BikeShopApp;
import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.config.AppConstants;
import com.oncedoing.bikeshop.manage_customer.adapter.CustomerListAdapter;
import com.oncedoing.bikeshop.manage_customer.component.DaggerCustomerListComponent;
import com.oncedoing.bikeshop.manage_customer.module.CustomerListModule;
import com.oncedoing.bikeshop.manage_customer.presenter.CustomerListPresenter;
import com.oncedoing.bikeshop.manage_customer.utils.CharacterParser;
import com.oncedoing.bikeshop.manage_customer.utils.PinyinComparator;
import com.oncedoing.bikeshop.manage_customer.view.ICustomerListView;
import com.oncedoing.bikeshop.model.CustomerEntity;
import com.oncedoing.bikeshop.model.CustomerSortEntity;
import com.oncedoing.bikeshop.model.SortModel;
import com.oncedoing.bikeshop.widget.CleanEditText;
import com.oncedoing.bikeshop.widget.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.kkwang.commonlib.base.BaseToolBarActivity;



public class CustomerQueryActivity extends BaseToolBarActivity implements ICustomerListView, android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener {


    private static final String TAG = "CustomerQueryActivity";
    final int REQUEST_EDIT_CUSTOMER_CODE = 3000;

    public static interface NAV_STATE {

        String NAVIGATION_KEY = "navigation_key";
        String GET_ONE_CUSTOMER = "get_one_customer";
    }

    private static boolean hasReadNetOnce;

    private String state;

    private TextWatcher textWatcher;

    @Inject
    CustomerListPresenter presenter;

    @Bind(R.id.et_search)
    CleanEditText etSearch;
    @Bind(R.id.rv_contacts)
    EasyRecyclerView rvContacts;
    @Bind(R.id.tv_show_letter)
    TextView tvShowLetter;
    @Bind(R.id.sidebar)
    SideBar sideBar;

    //第一次出现本页面，从网络读取并缓存到本地，以后都是只从本地读取，除非本地读的是空
    boolean isNeedChosenOne;

    private List<CustomerSortEntity> mAllContactsList;
    CustomerListAdapter adapter;


    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;


    private void initPresenter() {
        presenter.onCreate();
        presenter.attachView(this);
    }

    private void initDependencyInjector() {
        BikeShopApp app = (BikeShopApp) getApplication();
        DaggerCustomerListComponent.builder()
                .applicationComponent(app.component())
                .customerListModule(new CustomerListModule(this))
                .build()
                .inject(this);
    }

    private void initListener() {


        textWatcher = new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable e) {

                String content = etSearch.getText().toString();

                if (content.length() > 0) {
                    ArrayList<CustomerSortEntity> fileterList = (ArrayList<CustomerSortEntity>) search(content);
                    adapter.clear();
                    adapter.addAll(fileterList);
                } else {
                    adapter.clear();
                    adapter.addAll(mAllContactsList);

                }
                rvContacts.scrollToPosition(0);

            }

        };


       etSearch.addTextChangedListener(textWatcher);

        //设置右侧[A-Z]快速导航栏触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    rvContacts.scrollToPosition(position);
                }
            }
        });

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {

                CustomerEntity customerEntity = adapter.getItem(position).getCustomerEntity();


                if(TextUtils.equals(state, NAV_STATE.GET_ONE_CUSTOMER)){ //从新增销售选择客户进入,回传客户数据
//                    String name = adapter.getItem(position).getSortModel().name;

//                    customerEntity.setCustomer_name(name);
                    Intent intent = new Intent();
                    intent.putExtra(AppConstants.CUSTOMER_INFO_KEY, customerEntity);
                    setResult(RESULT_OK,intent);
                    finish();
                }else{ //从首页直接进入本页的，点击是要编辑客户信息
                    CustomerEditActivity.startForResult(CustomerQueryActivity.this, customerEntity, CustomerEditActivity.NAV_STATE.EDIT_CUSTOMER, REQUEST_EDIT_CUSTOMER_CODE);
                }
//                ContactsSortAdapter.ViewHolder viewHolder = (ContactsSortAdapter.ViewHolder) view.getTag();
//                viewHolder.cbChecked.performClick();
//                adapter.toggleChecked(position);
            }
        });

    }

    private void initView() {
        sideBar.setTextView(tvShowLetter);
        characterParser = CharacterParser.getInstance();
        mAllContactsList = new ArrayList<>();
        pinyinComparator = new PinyinComparator();
        Collections.sort(mAllContactsList, pinyinComparator);// 根据a-z进行排序源数据

        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        rvContacts.setAdapterWithProgress(adapter = new CustomerListAdapter(this, mAllContactsList));

        adapter.setError(R.layout.view_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.resumeMore();
            }
        });

        rvContacts.setRefreshListener(this);

        if(!hasReadNetOnce){
            presenter.refreshList(true);
        }else{
            presenter.readLocalFirst();
        }

    }

    @Override
    public void onRefresh() {
        presenter.refreshList(false);
    }


    /**
     * 模糊查询
     * @param str
     * @return
     */
    private List<CustomerSortEntity> search(String str) {

        List<CustomerSortEntity> filterList = new ArrayList<CustomerSortEntity>();// 过滤后的list
        //if (str.matches("^([0-9]|[/+])*$")) {// 正则表达式 匹配号码
        if (str.matches("^([0-9]|[/+]).*")) {// 正则表达式 匹配以数字或者加号开头的字符串(包括了带空格及-分割的号码)
            String simpleStr = str.replaceAll("\\-|\\s", "");
            for (CustomerSortEntity contact : mAllContactsList) {
                SortModel sortModel = contact.getSortModel();
                if (sortModel.number != null && sortModel.name != null) {
                    if (sortModel.simpleNumber.contains(simpleStr) || sortModel.name.contains(str)) {
                        if (!filterList.contains(contact)) {
                            filterList.add(contact);
                        }
                    }
                }
            }
        }else {
            for (CustomerSortEntity contact : mAllContactsList) {
                SortModel sortModel = contact.getSortModel();
                if (sortModel.number != null && sortModel.name != null) {
                    //姓名全匹配,姓名首字母简拼匹配,姓名全字母匹配
                    if (sortModel.name.toLowerCase(Locale.CHINESE).contains(str.toLowerCase(Locale.CHINESE))
                            || sortModel.sortKey.toLowerCase(Locale.CHINESE).replace(" ", "").contains(str.toLowerCase(Locale.CHINESE))
                            || sortModel.sortToken.simpleSpell.toLowerCase(Locale.CHINESE).contains(str.toLowerCase(Locale.CHINESE))
                            || sortModel.sortToken.wholeSpell.toLowerCase(Locale.CHINESE).contains(str.toLowerCase(Locale.CHINESE))) {
                        if (!filterList.contains(contact)) {
                            filterList.add(contact);
                        }
                    }
                }
            }
        }
        return filterList;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_list;
    }


    @Override
    protected void afterCreate(Bundle savedInstanceState) {

        ButterKnife.bind(this);
        initDependencyInjector();
        initPresenter();
        initView();
        initListener();

        Intent intent = getIntent();
        //表示从新建销售页面进入，要选择一个客户
        if(intent != null){
            state = intent.getStringExtra(NAV_STATE.NAVIGATION_KEY);
            if(TextUtils.equals(state, NAV_STATE.GET_ONE_CUSTOMER)){
                setTitle("选择客户");
            }
        }
    }


    @Override
    protected boolean isActionBarNeedBackEnable() {
        return true;
    }


    @Override
    public String getToolbarTitle() {
        return "客户查询";
    }


    @Override
    public void refreshList(List<CustomerSortEntity> list) {
        hasReadNetOnce = true;
        mAllContactsList = list;
        adapter.clear();
        adapter.addAll(list);

    }

    @Override
    public void addList(List<CustomerSortEntity> list) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoadError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void hideEmpty() {

    }

    @Override
    public void showSearchError() {

    }

    @Override
    public void showSearchEmpty() {

    }

    @Override
    public void showNoMore() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        etSearch.removeTextChangedListener(textWatcher);
        ButterKnife.unbind(this);
    }

    public static void startForResult(Activity context, String state, int request_code){
        Intent intent = new Intent(context, CustomerQueryActivity.class);
        intent.putExtra(NAV_STATE.NAVIGATION_KEY,state);
        context.startActivityForResult(intent,request_code);

    }
}
