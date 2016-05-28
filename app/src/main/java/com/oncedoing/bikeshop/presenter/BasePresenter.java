package com.oncedoing.bikeshop.presenter;


import com.oncedoing.bikeshop.view.IView;

/**
 * @author huxian99
 */
public interface BasePresenter {

    void onCreate();

    void attachView(IView view);

}
