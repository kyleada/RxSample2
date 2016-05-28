package com.oncedoing.bikeshop.mvp;

import rx.Observable;

/**
 * Created by Administrator on 2016/3/18.
 */
public interface IBaseGetListUasecase<L extends ListFeed> {

    Observable<L> getList();

}
