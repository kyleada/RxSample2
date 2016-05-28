package com.oncedoing.bikeshop.network;

import com.oncedoing.bikeshop.model.BikeListFeed;
import com.oncedoing.bikeshop.model.BikeStockListFeed;
import com.oncedoing.bikeshop.model.CustomerListFeed;

import com.oncedoing.bikeshop.model.StockOutFeed;
import rx.Observable;


public interface Repository {

    Observable<BikeListFeed> getBikeList(int pageSize,
                                         int pageNum,
                                         String sortString,
                                         String product_name,
                                         String product_brand,
                                         String product_category,
                                         int moreexistnum,
                                         int lessexistnum,
                                         boolean num_limit,
                                         String topCategoryList);


    Observable<CustomerListFeed> getCustomerList( String uri,
                                                int pageSize,
                                                int pageNum,
                                                String searchKey,
                                                String sortString,
                                                String tenantId,
                                                int lessConsumptionAmount,
                                                int moreConsumptionAmount,
                                                boolean activityCompleted);



    Observable<Void> login(String username,
                           String password);

    Observable<BikeStockListFeed> getStockInBikeList(int pageSize,
                                                     int pageNum,
                                                     String product_name,
                                                     String sortString,
                                                     String parent_code,
                                                     long fromTime,
                                                     long endTime);

    Observable<StockOutFeed> getStockoutFeed(
            int pageSize,
            int pageNum,
            boolean isByStockinPrice,
            String sortString,//stockout_time.desc
            String parent_code, //""
            long fromTime,//:-1
            long endTime//:-1
    );


}
