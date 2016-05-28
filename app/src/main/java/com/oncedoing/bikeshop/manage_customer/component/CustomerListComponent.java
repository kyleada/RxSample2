package com.oncedoing.bikeshop.manage_customer.component;

import com.oncedoing.bikeshop.manage_customer.CustomerQueryActivity;
import com.oncedoing.bikeshop.manage_customer.module.CustomerListModule;
import com.oncedoing.bikeshop.inject.ActivityScope;
import com.oncedoing.bikeshop.inject.component.ApplicationComponent;

import dagger.Component;

/**
 * Created by Administrator on 2016/3/15.
 */

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {CustomerListModule.class})
public interface CustomerListComponent {

    void inject(CustomerQueryActivity customerQueryActivity);

}
