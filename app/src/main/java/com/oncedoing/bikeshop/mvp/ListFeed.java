package com.oncedoing.bikeshop.mvp;

import java.util.List;

/**
 * Created by Administrator on 2016/3/19.
 */
public abstract class ListFeed<T> {

    int totalCount;
    List<T> entityList;

    public abstract List<T> getEntityList();
    public int getTotalCount(){
        return totalCount;
    }
}
