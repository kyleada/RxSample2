package com.oncedoing.bikeshop.manage_customer;

import android.content.Intent;

import com.oncedoing.bikeshop.config.AppConstants;
import com.oncedoing.bikeshop.model.CustomerEntity;

/**
 * Created by Administrator on 2016/3/20.
 */
public class CustomerHelper {

    public static Intent wrapCustomerIntent(CustomerEntity customerEntity){
        Intent intent = new Intent();
        intent.putExtra(AppConstants.CUSTOMER_INFO_KEY, customerEntity);
        return intent;
    }
}
