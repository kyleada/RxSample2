package com.oncedoing.bikeshop.mvp;


/**
 * @author huxian99
 */
public interface IBasePresenter {

    void onCreate();

    void attachView(IView view);

    void onDestroy();

}
