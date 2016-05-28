package com.oncedoing.bikeshop.network;

import com.oncedoing.bikeshop.model.BikeListFeed;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


public interface OpenApiService {

    /* param sortString:
     * create_time.desc
     */

    @GET("product/openList.do")
    Observable<BikeListFeed> getBikeList(@Query("pageSize") int pageSize, @Query("pageSize") int pageNum, String sortString);

    @FormUrlEncoded
    @POST("j_spring_security_check")
    Observable<Void> login(@Field("j_username") String username, @Field("j_password") String password);



}
