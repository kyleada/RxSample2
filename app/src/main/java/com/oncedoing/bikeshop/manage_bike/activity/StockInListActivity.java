package com.oncedoing.bikeshop.manage_bike.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.oncedoing.bikeshop.BikeShopApp;
import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.kbase.KBaseSwipeToolbarActivity;
import com.oncedoing.bikeshop.manage_bike.adapter.StockInListAdapter;
import com.oncedoing.bikeshop.manage_bike.inject.BikeListModule;
import com.oncedoing.bikeshop.manage_bike.inject.DaggerBikeListComponent;
import com.oncedoing.bikeshop.manage_bike.mvp.StockInListPresenter;
import com.oncedoing.bikeshop.manage_bike.mvp.IStockInListView;
import com.oncedoing.bikeshop.model.StockInEntity;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import me.kkwang.commonlib.widget.recyclerview.DividerItemDecoration;

public class StockInListActivity extends KBaseSwipeToolbarActivity implements
        IStockInListView, SwipeRefreshLayout.OnRefreshListener,RecyclerArrayAdapter.OnLoadMoreListener {

    @Inject StockInListPresenter bikeInListPresenter;
    @Bind(R.id.rv)
    EasyRecyclerView rv;

    StockInListAdapter bikeInListAdapter;
    AppCompatSpinner appCompatSpinner;

    String keywords;
    int category;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_stock_in_list;
    }

    @Override
    public String getToolbarTitle() {
        return "入库列表";
    }

    @Override
    protected boolean isActionBarNeedBackEnable() {
        return true;
    }

    @Override
    protected void initInject() {

        DaggerBikeListComponent
                .builder()
                .applicationComponent(((BikeShopApp)getApplication()).component())
                .bikeListModule(new BikeListModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initPresenter() {
        bikeInListPresenter.attachView(this);
    }


    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        appCompatSpinner = (AppCompatSpinner) findViewById(R.id.spinner_timein);
        ArrayAdapter mAdapter = ArrayAdapter.createFromResource
                (getSupportActionBar().getThemedContext(), R.array
                        .items_filter_period, R.layout.spinner_drop_title);
        mAdapter.setDropDownViewResource(R.layout.simple_spinner_item);
        appCompatSpinner.setAdapter(mAdapter);
        appCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category = i;
                bikeInListPresenter.refresh(keywords, category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        bikeInListAdapter = new StockInListAdapter(this);
        bikeInListAdapter.setMore(R.layout.view_more, this);
        bikeInListAdapter.setNoMore(R.layout.view_nomore);
        bikeInListAdapter.setError(R.layout.view_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bikeInListAdapter.resumeMore();
            }
        });

        rv.setAdapterWithProgress(bikeInListAdapter);
        rv.setRefreshListener(this);

        //appCompatSpinner的初始化会触发
        //bikeInListPresenter.refreshList("", StockInListPresenter.FilterType.ALL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bike_in_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bikeInListPresenter.onDestroy();
    }

    @Override
    public void onRefresh() {
        bikeInListPresenter.refresh(keywords,category);
    }

    @Override
    public void onLoadMore() {
        bikeInListPresenter.loadMore(keywords,category);
    }

    @Override
    public void refreshList(List<StockInEntity> bikeList) {
        bikeInListAdapter.clear();
        bikeInListAdapter.addAll(bikeList);
    }

    @Override
    public void addList(List<StockInEntity> booinfoList) {
        bikeInListAdapter.addAll(booinfoList);
    }

    @Override
    public void showLoading() {
        rv.showProgress();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoadError() {
rv.showError();
    }

    @Override
    public void showLoadMoreError() {
        bikeInListAdapter.pauseMore();
    }

    @Override
    public void showEmpty() {
rv.showEmpty();
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
        bikeInListAdapter.stopMore();
        showToast("没有更多了");
    }


}
