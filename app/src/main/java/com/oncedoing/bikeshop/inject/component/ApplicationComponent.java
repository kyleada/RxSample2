package com.oncedoing.bikeshop.inject.component;


import com.oncedoing.bikeshop.BikeShopApp;
import com.oncedoing.bikeshop.auth.ui.LoginActivity;
import com.oncedoing.bikeshop.inject.module.ApplicationModule;
import com.oncedoing.bikeshop.network.Repository;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(
        modules = {
                ApplicationModule.class
        }
)
public interface ApplicationComponent {

    void inject(BikeShopApp app);

    void inject(LoginActivity loginActivity);

    BikeShopApp application();

    Repository repository();

}
