package com.oncedoing.bikeshop.manage_bike.mvp;


import com.oncedoing.bikeshop.model.StockInEntity;
import com.oncedoing.bikeshop.mvp.IView;

import java.util.List;

/**
 * Created by Administrator on 2016/3/15.
 */
public interface IStockInListView extends IView {

    void refreshList(List<StockInEntity> bikeList);

    void addList(List<StockInEntity> bikeList);

    void showLoading();

    void hideLoading();

    void showLoadError();

    void showLoadMoreError();

    void showEmpty();

    void hideEmpty();

    void showSearchError();

    void showSearchEmpty();

    void showNoMore();

}
