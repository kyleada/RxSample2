package com.oncedoing.bikeshop.manage_bike.inject;

import android.content.Context;

import com.oncedoing.bikeshop.inject.ActivityScope;
import com.oncedoing.bikeshop.manage_bike.datalayer.GetBikeListUsercase;
import com.oncedoing.bikeshop.network.Repository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/3/15.
 */

@Module
public class BikeListModule {

    private Context context;


    public BikeListModule(Context context) {
        this.context = context;
    }

    @Provides
    @ActivityScope
    GetBikeListUsercase provideGetBikeInfoListUsercase (Repository repository) {
        return new GetBikeListUsercase(repository);
    }

    @Provides
    @ActivityScope
    Context provideContext() {
        return context;
    }
}
