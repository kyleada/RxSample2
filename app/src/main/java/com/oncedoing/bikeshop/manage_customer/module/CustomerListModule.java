package com.oncedoing.bikeshop.manage_customer.module;

import android.content.Context;

import com.oncedoing.bikeshop.manage_customer.datalayer.GetCustomerListUsercase;
import com.oncedoing.bikeshop.inject.ActivityScope;
import com.oncedoing.bikeshop.network.Repository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/3/15.
 */

@Module
public class CustomerListModule {

    private Context context;


    public CustomerListModule(Context context) {
        this.context = context;
    }

    @Provides
    @ActivityScope
    GetCustomerListUsercase provideGetCustomerListUsercase (Repository repository) {
        return new GetCustomerListUsercase(repository);
    }

    @Provides
    @ActivityScope
    Context provideContext() {
        return context;
    }
}
