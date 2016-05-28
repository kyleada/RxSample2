package com.oncedoing.bikeshop.manage_bike.mvp;


import com.oncedoing.bikeshop.model.BikeInfoEntity;
import com.oncedoing.bikeshop.mvp.IView;

import java.util.List;

/**
 * Created by Administrator on 2016/3/15.
 */
public interface IBikeListView extends IView {

    void refreshList(List<BikeInfoEntity> booinfoList);

    void addList(List<BikeInfoEntity> booinfoList);

    void showLoading();

    void hideLoading();

    void showLoadError();

    void showEmpty();

    void hideEmpty();

    void showSearchError();

    void showSearchEmpty();

    void showNoMore();

    void showFiltersChosen(boolean isShow);
}
