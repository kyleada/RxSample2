package com.oncedoing.bikeshop.domain;

import rx.Observable;

/**
 * @author huxian99
 */
public interface Usecase<T> {

    Observable<T> execute();
}
