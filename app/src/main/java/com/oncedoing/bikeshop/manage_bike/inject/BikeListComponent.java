package com.oncedoing.bikeshop.manage_bike.inject;

import com.oncedoing.bikeshop.inject.ActivityScope;
import com.oncedoing.bikeshop.inject.component.ApplicationComponent;
import com.oncedoing.bikeshop.manage_bike.activity.StockInListActivity;
import com.oncedoing.bikeshop.manage_bike.activity.ProductListActivity;
import com.oncedoing.bikeshop.manage_bike.activity.StockOutListActivity;
import com.oncedoing.bikeshop.manage_bike.fragment.BikeListFragment;
import com.oncedoing.bikeshop.manage_bike.fragment.BikeListFragment2;

import dagger.Component;

/**
 * Created by Administrator on 2016/3/15.
 */

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {BikeListModule.class})
public interface BikeListComponent {

    void inject(BikeListFragment fragment);
    void inject(BikeListFragment2 fragment);
    void inject(ProductListActivity bikeListActivity);
    void inject(StockInListActivity bikeListActivity);
    void inject(StockOutListActivity stockOutListActivity);

}
