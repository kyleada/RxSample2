package com.oncedoing.bikeshop.mvp;

/**
 * Created by kw on 2016/3/25.13:15.
 */
public interface IBaseListPresenter {

    void refresh();

    void loadMore();

    void onCreate();

    void attachView(IView view);

    void onDestroy();


}
