package com.oncedoing.bikeshop;

import android.app.Application;

import com.oncedoing.bikeshop.db.DBManager;
import com.oncedoing.bikeshop.inject.component.ApplicationComponent;
import com.oncedoing.bikeshop.inject.component.DaggerApplicationComponent;
import com.oncedoing.bikeshop.inject.module.ApplicationModule;

import im.fir.sdk.FIR;
import me.kkwang.commonlib.CommonLibUtils;

/**
 * Created by Administrator on 2016/3/14.
 */
public class BikeShopApp extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

		FIR.init(this);

//        LeakCanary.install(this);

        setupGraph();
        //初始化公共库
        CommonLibUtils.init(this);
        //初始化数据库
        DBManager.init(this);
    }

    private void setupGraph() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        //component.inject(this);
    }

    public ApplicationComponent component() {
        return component;
    }
}
