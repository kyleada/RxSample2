package com.oncedoing.bikeshop.manage_bike.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.oncedoing.bikeshop.BikeShopApp;
import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.kbase.KBaseSwipeToolbarActivity;
import com.oncedoing.bikeshop.manage_bike.adapter.StockOutListAdapter;
import com.oncedoing.bikeshop.manage_bike.inject.BikeListModule;
import com.oncedoing.bikeshop.manage_bike.inject.DaggerBikeListComponent;
import com.oncedoing.bikeshop.manage_bike.mvp.IStockOutListView;
import com.oncedoing.bikeshop.manage_bike.mvp.StockOutListPresenter;
import com.oncedoing.bikeshop.model.StockOutEntity;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.kkwang.commonlib.widget.recyclerview.DividerItemDecoration;

public class StockOutListActivity extends KBaseSwipeToolbarActivity implements
        IStockOutListView,
        SwipeRefreshLayout.OnRefreshListener,
        RecyclerArrayAdapter.OnLoadMoreListener {

    final int LAYOUT = R.layout.activity_stock_out_list;
    final int TITLE = R.string.stockout_list;

    @Inject StockOutListPresenter mStockOutListPresenter;

    @Bind(R.id.spinner_filter) AppCompatSpinner mSpinnerFilter;
    @Bind(R.id.rv) EasyRecyclerView mRv;

    StockOutListAdapter mStockOutListAdapter;


    @Override public void refreshList(List list) {
        mStockOutListAdapter.clear();
        mStockOutListAdapter.addAll(list);
    }


    @Override public void addList(List list) {
        mStockOutListAdapter.addAll(list);
    }


    @Override public void showLoading() {
        mRv.showProgress();
    }


    @Override public void hideLoading() {

    }


    @Override public void showLoadError() {
        mRv.showError();
    }


    @Override public void showEmpty() {
        mRv.showEmpty();
    }


    @Override public void hideEmpty() {
        mRv.showRecycler();
    }


    @Override public void showSearchError() {

    }


    @Override public void showSearchEmpty() {

    }


    @Override public void showNoMore() {
        mStockOutListAdapter.stopMore();
    }


    @Override protected int getLayoutId() {
        return LAYOUT;
    }


    @Override public String getToolbarTitle() {
        return getString(TITLE);
    }


    @Override protected boolean isActionBarNeedBackEnable() {
        return true;
    }


    @Override protected void initInject() {
        DaggerBikeListComponent.builder()
                               .applicationComponent(
                                       ((BikeShopApp) getApplication()).component())
                               .bikeListModule(new BikeListModule(this))
                               .build()
                               .inject(this);
    }


    @Override protected void afterCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        mSpinnerFilter = (AppCompatSpinner) findViewById(R.id.spinner_filter);
        ArrayAdapter mAdapter = ArrayAdapter.createFromResource
                (getSupportActionBar().getThemedContext(), R.array
                        .items_filter_period, R.layout.spinner_drop_title);
        mAdapter.setDropDownViewResource(R.layout.simple_spinner_item);
        mSpinnerFilter.setAdapter(mAdapter);
        mSpinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        mStockOutListAdapter = new StockOutListAdapter(this);
        mStockOutListAdapter.setMore(R.layout.view_more, this);
        mStockOutListAdapter.setNoMore(R.layout.view_nomore);
        mStockOutListAdapter.setError(R.layout.view_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStockOutListAdapter.resumeMore();
            }
        });
        mStockOutListAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                StockOutEntity stockOutEntity = mStockOutListAdapter.getItem(position);
                StockOutDetailActivity.start(mActivity,stockOutEntity);

            }
        });
        mRv.setAdapterWithProgress(mStockOutListAdapter);
        mRv.setRefreshListener(this);

        mStockOutListPresenter.refresh();
    }


    @Override protected void initPresenter() {
        mStockOutListPresenter.attachView(this);
    }


    @Override public void onLoadMore() {
        mStockOutListPresenter.loadMore();
    }


    @Override public void onRefresh() {
        mRv.showProgress();
        mStockOutListPresenter.refresh();
    }
}
