package com.oncedoing.bikeshop.network;

import com.oncedoing.bikeshop.model.BikeListFeed;
import com.oncedoing.bikeshop.model.BikeStockListFeed;
import com.oncedoing.bikeshop.model.CustomerListFeed;
import com.oncedoing.bikeshop.model.StockOutFeed;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiService {

    /* param sortString:
     * create_time.desc
     */

    @GET("product/openList.do")
    Observable<BikeListFeed> getBikeList(@Query("pageSize") int pageSize,
                                         @Query("pageNum") int pageNum,
                                         @Query("sortString") String
                                                 sortString,
                                         @Query("product_name")
                                         String product_name,
                                         @Query("product_brand")
                                         String product_brand,
                                         @Query("product_category")
                                         String product_category,
                                         @Query("moreexistnum")
                                         int moreexistnum,
                                         @Query("lessexistnum")
                                         int lessexistnum,
                                         @Query("num_limit")
                                         boolean num_limit,
                                         @Query("top_category_list")
                                         String topCategoryList);




    @FormUrlEncoded
    @POST("j_spring_security_check")
    Observable<Void> login(@Field("j_username") String username, @Field("j_password") String password);


    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: RetrofitBean-Sample-App",
            "name:ljd"
    })
    @GET("service_get.do")
    Observable<CustomerListFeed> getCustomerList(@Query("uri") String uri,
                                               @Query("pageSize") int pageSize,
                                               @Query("pageNum") int pageNum,
                                               @Query("searchKey") String searchKey,
                                               @Query("sortString") String sortString,
                                               @Query("tenantId") String tenantId,
                                               @Query("lessConsumptionAmount") int lessConsumptionAmount,
                                               @Query("moreConsumptionAmount") int moreConsumptionAmount,
                                               @Query("activityCompleted") boolean activityCompleted
                                               );

    @GET("stockin/openList.do")
    Observable<BikeStockListFeed> getStockInBikeList(@Query("pageSize") int pageSize,
                                                     @Query("pageNum") int pageNum,
                                                     @Query("product_name") String product_name,
                                                     @Query("sortString") String sortString,
                                                     @Query("parent_code") String parent_code,
                                                     @Query("fromTime") long fromTime,
                                                     @Query("endTime") long endTime);

    @GET("stockout/openList.do")
    Observable<StockOutFeed> getStockOutFeed(
            @Query("pageSize")
            int pageSize,
            @Query("pageNum")
            int pageNum,
            @Query("isByStockinPrice")
            boolean isByStockinPrice,
            @Query("sortString")
            String sortString,//stockout_time.desc
            @Query("parent_code")
            String parent_code, //""
            @Query("fromTime")
            long fromTime,//:-1
            @Query("endTime")
            long endTime//:-1
    );



}
