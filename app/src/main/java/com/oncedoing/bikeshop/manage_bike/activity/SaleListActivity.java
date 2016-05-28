package com.oncedoing.bikeshop.manage_bike.activity;

import android.os.Bundle;

import com.oncedoing.bikeshop.R;

import me.kkwang.commonlib.base.BaseToolBarActivity;

public class SaleListActivity extends BaseToolBarActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sale_list;
    }

    @Override
    public String getToolbarTitle() {
        return "销售列表";
    }

    @Override
    protected boolean isActionBarNeedBackEnable() {
        return true;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

    }
}
