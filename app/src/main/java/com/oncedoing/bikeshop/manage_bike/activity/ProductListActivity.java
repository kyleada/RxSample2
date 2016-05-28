package com.oncedoing.bikeshop.manage_bike.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.oncedoing.bikeshop.BikeShopApp;
import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.config.AppConstants;
import com.oncedoing.bikeshop.manage_bike.adapter.BikeListAdapter;
import com.oncedoing.bikeshop.manage_bike.adapter.FiltersChosenAdapter;
import com.oncedoing.bikeshop.manage_bike.datalayer.GetBikeListUsercase;
import com.oncedoing.bikeshop.manage_bike.inject.BikeListModule;
import com.oncedoing.bikeshop.manage_bike.inject.DaggerBikeListComponent;
import com.oncedoing.bikeshop.manage_bike.mvp.BikeListPresenter;
import com.oncedoing.bikeshop.manage_bike.mvp.IBikeListView;
import com.oncedoing.bikeshop.model.BikeInfoEntity;
import com.oncedoing.bikeshop.widget.CleanEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.kkwang.commonlib.base.BaseToolBarActivity;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ProductListActivity extends BaseToolBarActivity implements SwipeRefreshLayout.OnRefreshListener,RecyclerArrayAdapter.OnLoadMoreListener,IBikeListView {

    private String state;

    public  interface NAV_STATE{

        String NAVIGATION_KEY = "navigation_key";
        String CHOOSE_BIKE = "choose_bike";
    }

    final int REQUEST_EDIT_PRODUCT_CODE = 3000;

    private boolean isToggleShow = false;
    @Bind(R.id.iv_toggle_show)
    ImageView ivToggleShow;
    @OnClick(R.id.iv_toggle_show)
    void onToggleShow(){
        if(isToggleShow){
            isToggleShow = false;
            vTemp.setVisibility(View.GONE);
        }else{
            isToggleShow = true;
            vTemp.setVisibility(View.VISIBLE);
        }
    }

    @Bind(R.id.ll_temp)
    View vTemp;

    @Bind(R.id.et_search)
    CleanEditText etSearch;

    @Bind(R.id.rv_bikelist)
    EasyRecyclerView rvBikelist;
    @Bind(R.id.rv_filter_chosen)
    RecyclerView rvFilterChosen;


    @Inject
    GetBikeListUsercase getBikeInfoListUsercase;

    @Inject
    BikeListPresenter presenter;

    //搜索框需要监听输入为空时恢复到包含所有的列表
    //但只应该在第一次请求完毕显示后，反生过一次有关键词的搜索后
    private boolean hasSearchedMoreOne = false;

    private BikeListAdapter bikeListAdapter;
    private FiltersChosenAdapter filtersChosenAdapter;
    private ArrayMap<String, String> topCategoryNameAndIdMap = new ArrayMap<>();

    @Override
    protected boolean isActionBarNeedBackEnable() {
        return true;
    }

    @Override
    protected int getAppBarLayoutId() {
        return R.id.app_bar_layout;
    }

    @Override
    protected int getToolbarLayoutId() {
        return R.id.toolbar;
    }

    @Override protected int getLayoutId() {
        return R.layout.activity_bike_list;
    }


    @Override public String getToolbarTitle() {
        return "商品列表";
    }


    @Override protected void afterCreate(Bundle savedInstanceState) {

        ButterKnife.bind(this);

        initDependencyInjector();
        initPresenter();

        Intent intent = getIntent();
        state = intent.getStringExtra(NAV_STATE.NAVIGATION_KEY);

        if(TextUtils.equals(state, NAV_STATE.CHOOSE_BIKE)){
            bikeListAdapter = new BikeListAdapter(this,true);
        }else{
            bikeListAdapter = new BikeListAdapter(this,false);
        }

//        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)mAppBar.getLayoutParams();
//        TypedValue typedValue = new TypedValue();
//        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true)) {
//            int actionBarHeight = TypedValue.complexToDimensionPixelSize(typedValue.data,getResources().getDisplayMetrics());
//            lp.topMargin = getResources().getDimensionPixelSize(R.dimen.status_bar_height) + actionBarHeight + 20;
//
//        }
//        typedValue = null;
//        mAppBar.setLayoutParams();
//        mAppBar.setLayoutParams(lp);
        rvBikelist.setLayoutManager(new LinearLayoutManager(this));
        rvBikelist.setAdapterWithProgress(bikeListAdapter);
        bikeListAdapter.setMore(R.layout.view_more, this);
        bikeListAdapter.setNoMore(R.layout.view_nomore);
        bikeListAdapter.setError(R.layout.view_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bikeListAdapter.resumeMore();
            }
        });
        bikeListAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                if(TextUtils.equals(state, NAV_STATE.CHOOSE_BIKE)){
                    BikeListAdapter.BikeInfoWithCbViewHolder viewHolder = (BikeListAdapter.BikeInfoWithCbViewHolder)view.getTag();
                    viewHolder.cb.performClick();
                    //bikeListAdapter.toggleChecked(position);

                }else{
                    ProductEditActivity.startForEdit(ProductListActivity.this, bikeListAdapter.getItem(position),REQUEST_EDIT_PRODUCT_CODE);
                }
            }
        });
        rvBikelist.setRefreshListener(this);
        presenter.refreshBikeList(true);
        initSearchEvent();

        //初始化筛选条
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvFilterChosen.setLayoutManager(llm);
        filtersChosenAdapter = new FiltersChosenAdapter(this);
        rvFilterChosen.setAdapter(filtersChosenAdapter);
        filtersChosenAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                removeTopCategory(filtersChosenAdapter.getItem(position));
                //filtersChosenAdapter.remove(filtersChosenAdapter.getItem(position));
                //启动新的过滤搜索
                search();
            }
        });

        //初始化topCategory里数字和显示文字的对应关系
        String[] topCats = getResources().getStringArray(R.array.product_top_category);
        for (int i = 100; i<105;i++){
            topCategoryNameAndIdMap.put(topCats[i%100],String.valueOf(i + 1));
        }

    }

    MenuItem miNext;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bike_list_menu, menu);
        miNext = menu.findItem(R.id.action_next);
        if(TextUtils.equals(state, NAV_STATE.CHOOSE_BIKE)){
            miNext.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_next) {

            ArrayList<BikeInfoEntity> list = bikeListAdapter.getSelectedList();
            if(list.size() == 0){
                showToast("请至少选择一件商品");
            }else{
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra(AppConstants.COMMODITY_INFO_KEY, list);
                setResult(RESULT_OK, intent);
                onBackPressed();
            }
        }else if(id != R.id.action_filter){
            boolean isCheck = item.isChecked();
            if(!isCheck){
                item.setChecked(true);
                addTopCategory(item.getTitle().toString());
            }else {
                item.setChecked(false);
                removeTopCategory(item.getTitle().toString());
            }
            search();
        }

        return super.onOptionsItemSelected(item);
    }



    private void initPresenter() {
        presenter.attachView(this);
    }

    private void initDependencyInjector() {
        BikeShopApp app = (BikeShopApp) this.getApplication();
        DaggerBikeListComponent.builder()
                .applicationComponent(app.component())
                .bikeListModule(new BikeListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onRefresh() {

        presenter.refreshBikeList(false);
    }

    @Override
    public void onLoadMore() {

        presenter.loadMoreBikeList();
    }


    @Override
    public void refreshList(List<BikeInfoEntity> booinfoList) {
        bikeListAdapter.clear();
        bikeListAdapter.addAll(booinfoList);
    }

    @Override
    public void addList(List<BikeInfoEntity> booinfoList) {
        bikeListAdapter.addAll(booinfoList);
    }

    @Override
    public void showLoading() {

        rvBikelist.showProgress();
    }

    @Override
    public void hideLoading() {
        rvBikelist.showRecycler();
    }

    @Override
    public void showLoadError() {
        rvBikelist.showError();
    }

    @Override
    public void showEmpty() {

        rvBikelist.showEmpty();
    }

    @Override
    public void hideEmpty() {
        rvBikelist.showRecycler();
    }


    @Override public void showNoMore() {
        bikeListAdapter.stopMore();
        showToast("没有更多了");
    }


    @Override
    public void showSearchError() {
        showToast("搜索异常，请重试");
    }

    @Override
    public void showSearchEmpty() {
        showToast("没有符合条件的搜索结果，请换个关键词");
    }

    private void initSearchEvent(){

        RxTextView.textChanges(etSearch)
                .subscribeOn(AndroidSchedulers.mainThread())
                //delay 500ms
                //debounce and throttle will use different thread after
                .throttleWithTimeout(150, TimeUnit.MILLISECONDS,
                        AndroidSchedulers.mainThread())
                .distinct()
                .filter(new Func1<CharSequence, Boolean>() {
                    @UiThread
                    @Override public Boolean call(CharSequence charSequence) {
                        //void unnecessary request
                        return charSequence.length() > 1 || (hasSearchedMoreOne && (charSequence.length() == 0));
                    }
                })
                .map(new Func1<CharSequence, String>() {
                    @UiThread @Override public String call(CharSequence charSequence) {
                        //fit network api doc require
                        return charSequence.toString();
                    }
                })
                .doOnNext(new Action1<CharSequence>() {
                    @UiThread @Override public void call(CharSequence charSequence) {
                        hasSearchedMoreOne = true;
						presenter.setSearchKeyWords(charSequence.toString());
                    }
                })

                .observeOn(Schedulers.io())
//                  .switchMap(new Func1<String, Observable<BikeListFeed>>() {
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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        presenter.search();
                    }
                });
    }

    public void search(){
        presenter.search();
    }

    @Override
    public void showFiltersChosen(boolean isShow) {
        if(isShow){
            rvFilterChosen.setVisibility(View.VISIBLE);
        }else{
            rvFilterChosen.setVisibility(View.GONE);
        }

    }

    public void addTopCategory(String top){
        if(!presenter.containsTopCategory(top)){
            filtersChosenAdapter.add(top);
            presenter.addTopCategory(topCategoryNameAndIdMap.get(top));
        }
    }

    public void removeTopCategory(String top){
        filtersChosenAdapter.remove(top);
        presenter.removeTopCategory(topCategoryNameAndIdMap.get(top));

    }

    public void setNumLimit(boolean isNumLimit){
        presenter.setNumLimit(isNumLimit);
    }


    public static void startForResult(Activity context, String state, int request_code){
        Intent intent = new Intent(context, ProductListActivity.class);
        intent.putExtra(NAV_STATE.NAVIGATION_KEY,state);
        context.startActivityForResult(intent,request_code);
    }

    private Intent wrapSelectedBike(){

        ArrayList<BikeInfoEntity> list = new ArrayList<>();
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(AppConstants.COMMODITY_INFO_KEY,list);
        return intent;
    }

}
