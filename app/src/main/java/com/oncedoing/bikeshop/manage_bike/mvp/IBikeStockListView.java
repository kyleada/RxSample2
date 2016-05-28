package com.oncedoing.bikeshop.manage_bike.mvp;


import com.oncedoing.bikeshop.model.BikeStockEntity;
import com.oncedoing.bikeshop.mvp.IView;

import java.util.List;

/**
 * Created by Administrator on 2016/3/15.
 */
public interface IBikeStockListView extends IView {

    void refreshList(List<BikeStockEntity> bikeList);

    void addList(List<BikeStockEntity> bikeList);

    void showLoading();

    void hideLoading();

    void showLoadError();

    void showEmpty();

    void hideEmpty();

    void showSearchError();

    void showSearchEmpty();

    void showNoMore();

}
