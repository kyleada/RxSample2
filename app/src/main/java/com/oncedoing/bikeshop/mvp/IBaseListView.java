package com.oncedoing.bikeshop.mvp;

import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
public interface IBaseListView<T> extends IView {

    void refreshList(List<T> list);

    void addList(List<T> list);

    void showLoading();

    void hideLoading();

    void showLoadError();

    void showEmpty();

    void hideEmpty();

    void showSearchError();

    void showSearchEmpty();

    void showNoMore();
}
