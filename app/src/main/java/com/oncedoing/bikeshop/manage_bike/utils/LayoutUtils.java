package com.oncedoing.bikeshop.manage_bike.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by Administrator on 2016/3/26.
 */
public class LayoutUtils {

    public static LinearLayoutManager getVerticalLinearLayoutManager(Context context){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return  linearLayoutManager;

    }
}
