package com.oncedoing.bikeshop.mvp;

import android.util.Log;

import com.oncedoing.bikeshop.utils.UriHelper;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/3/18.
 */
public class BaseListPresenter<U extends IBaseGetListUasecase, V extends IBaseListView, L extends ListFeed> implements IBasePresenter {


    private static final String TAG = "BaseListPresenter";

    U baseListUsercase;
    V baseListView;
    L listFeed;

    boolean isCanLoadMore;
    int currentPage = 1;
    int totalCount;
    int totalPageCount;


    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(IView view) {

    }

    @Override
    public void onDestroy() {
        baseListView = null;
    }

    /*
     * 这里的参数isFirst是为了第一次如果加载失败，那么要在content区域显示加载失败
     * 如果不是第一次，也就是content已经有显示内容了，那不就在增加显示加载失败，保留原内容
     */

    public void refreshBikeList(final boolean isFirst) {

        currentPage = 1;

        baseListUsercase.getList().subscribe(new Subscriber<ListFeed>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if(isFirst){
                    baseListView.hideLoading();
                    baseListView.showLoadError();
                }else{
                    baseListView.hideLoading();
                }
            }

            @Override
            public void onNext(ListFeed feedResult) {

                baseListView.hideLoading();
                List entityList = feedResult.getEntityList();
                baseListView.refreshList(entityList);
                totalCount = feedResult.getTotalCount();
                totalPageCount = UriHelper.calculateTotalPages(totalCount);
                if(entityList != null && currentPage < totalPageCount){
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
        if(isCanLoadMore){
            currentPage ++;
            Log.d(TAG, "loadMoreBikeList: currentPage :" + currentPage);
            baseListUsercase.getList().subscribe(new Subscriber<ListFeed>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(ListFeed listFeed) {
                    baseListView.hideLoading();
                    List entityList = listFeed.getEntityList();
                    baseListView.addList(entityList);
                    totalCount = listFeed.getTotalCount();
                    totalPageCount = UriHelper.calculateTotalPages(totalCount);
                    if(entityList != null && currentPage < totalPageCount){
                        isCanLoadMore = true;
                    }else {
                        isCanLoadMore = false;
                    }
                }
            });
        }else{
            baseListView.hideLoading();
            baseListView.showNoMore();
        }
    }
}
