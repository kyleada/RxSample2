package com.oncedoing.bikeshop.inject.module;


import com.oncedoing.bikeshop.BikeShopApp;
import com.oncedoing.bikeshop.network.Repository;
import com.oncedoing.bikeshop.network.RestDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    private BikeShopApp app;

    public ApplicationModule(BikeShopApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    BikeShopApp provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    Repository provideDataRepository (RestDataSource restDataSource) {
        return restDataSource;
    }


}
