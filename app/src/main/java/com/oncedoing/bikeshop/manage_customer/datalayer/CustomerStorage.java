package com.oncedoing.bikeshop.manage_customer.datalayer;

import com.oncedoing.bikeshop.db.DBManager;
import com.oncedoing.bikeshop.model.CustomerSortEntity;

import java.util.List;
import java.util.concurrent.Callable;

import me.kkwang.commonlib.rx.RxUtils;
import me.kkwang.commonlib.utils.Logger;
import rx.Observable;

/**
 * Created by Administrator on 2016/3/19.
 */
public class CustomerStorage {

    public static void deleteAll(){
        DBManager.deleteAll();
    }

    public static void insertAll(List<CustomerSortEntity> list){
        DBManager.insertAll(list);
    }


    public static List<CustomerSortEntity> getAllCustomerReal(){
        return DBManager.getQueryAll(CustomerSortEntity.class);
    }

    // callable 对象可以作为线程体执行
    public static Callable<List<CustomerSortEntity>> getAllCustomerWrapper() {
        return new Callable<List<CustomerSortEntity>>() {
            @Override
            public List<CustomerSortEntity> call() throws Exception {
                List<CustomerSortEntity> results = getAllCustomerReal();
                Logger.i(" results.size() "+results.size());
                return results;
            }
        };
    }

    public static Observable<List<CustomerSortEntity>> getAllCustomerWarpByObservable() {
        return RxUtils.makeObservable(getAllCustomerWrapper());
    }
}
