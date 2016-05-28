package com.oncedoing.bikeshop.manage_bike.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.oncedoing.bikeshop.BikeShopApp;
import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.manage_bike.adapter.BikeListAdapter;
import com.oncedoing.bikeshop.manage_bike.adapter.FiltersChosenAdapter;
import com.oncedoing.bikeshop.manage_bike.datalayer.GetBikeListUsercase;
import com.oncedoing.bikeshop.manage_bike.inject.BikeListModule;
import com.oncedoing.bikeshop.manage_bike.inject.DaggerBikeListComponent;
import com.oncedoing.bikeshop.manage_bike.mvp.BikeListPresenter;
import com.oncedoing.bikeshop.manage_bike.mvp.IBikeListView;
import com.oncedoing.bikeshop.model.BikeInfoEntity;
import com.oncedoing.bikeshop.widget.CleanEditText;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.kkwang.commonlib.base.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/3/15.
 */
public class BikeListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,RecyclerArrayAdapter.OnLoadMoreListener,IBikeListView {

    public static final String TAG = "BikeListFragment";


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

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_bike_list;
    }


    @Override
    protected void initViewsAndEvents(View view) {
        initDependencyInjector();
        initPresenter();

        rvBikelist.setLayoutManager(new LinearLayoutManager(mContext));
        rvBikelist.setAdapterWithProgress(bikeListAdapter = new BikeListAdapter(mContext));
        bikeListAdapter.setMore(R.layout.view_more, this);
        bikeListAdapter.setNoMore(R.layout.view_nomore);
        bikeListAdapter.setError(R.layout.view_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bikeListAdapter.resumeMore();
            }
        });

        rvBikelist.setRefreshListener(this);
        presenter.refreshBikeList(true);
        initSearchEvent();

        //初始化筛选条
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvFilterChosen.setLayoutManager(llm);
        filtersChosenAdapter = new FiltersChosenAdapter(mContext);
        rvFilterChosen.setAdapter(filtersChosenAdapter);
        filtersChosenAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position,View view) {
                filtersChosenAdapter.remove(position);
                //启动新的过滤搜索
            }
        });
    }

    private void initPresenter() {
        presenter.attachView(this);
    }

    private void initDependencyInjector() {
        BikeShopApp app = (BikeShopApp) mContext.getApplication();
        DaggerBikeListComponent.builder()
                .applicationComponent(app.component())
                .bikeListModule(new BikeListModule(mContext))
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
            presenter.addTopCategory(top);
        }
    }

    public void removeTopCategory(String top){
        filtersChosenAdapter.remove(top);
        presenter.removeTopCategory(top);

    }

    public void setNumLimit(boolean isNumLimit){
        presenter.setNumLimit(isNumLimit);
    }

    public static BikeListFragment newInstance() {

        BikeListFragment fragment = new BikeListFragment();

        return fragment;
    }

}
