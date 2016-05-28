package com.oncedoing.bikeshop.network;

import com.oncedoing.bikeshop.config.AppConstants;
import com.oncedoing.bikeshop.model.BikeListFeed;
import com.oncedoing.bikeshop.model.BikeStockListFeed;
import com.oncedoing.bikeshop.model.CustomerListFeed;

import com.oncedoing.bikeshop.model.StockOutFeed;
import javax.inject.Inject;

import me.kkwang.commonlib.net.OkHttpManager;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RestDataSource implements Repository {

    private ApiService apiService;


    @Inject public RestDataSource() {

        OkHttpClient okHttpClient = OkHttpManager.getInstance()
                                                 .getOkHttpClient();
        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient)
                                                  .baseUrl(
                                                          AppConstants.BASE_URL)
                                                  .addConverterFactory(
                                                          GsonConverterFactory.create())
                                                  .addCallAdapterFactory(
                                                          RxJavaCallAdapterFactory
                                                                  .create())
                                                  .build();
        apiService = retrofit.create(ApiService.class);
    }


    @Override
    public Observable<BikeListFeed> getBikeList (int pageSize,
                                                int pageNum,
                                                String sortString,
                                                String product_name,
                                                String product_brand,
                                                String product_category,
                                                int moreexistnum,
                                                int lessexistnum,
                                                boolean num_limit, String topCategoryList){

        return apiService.getBikeList(pageSize, pageNum, sortString,product_name,
                product_brand, product_category, moreexistnum, lessexistnum,
                num_limit,topCategoryList);
    }

    @Override
    public Observable<CustomerListFeed> getCustomerList(String uri, int pageSize, int pageNum, String searchKey, String sortString, String tenantId, int lessConsumptionAmount, int moreConsumptionAmount, boolean activityCompleted) {
        return apiService.getCustomerList(uri,  pageSize,  pageNum,  searchKey,  sortString,  tenantId,  lessConsumptionAmount,  moreConsumptionAmount,  activityCompleted);
    }

    @Override public Observable<Void> login(String username, String password) {
        return apiService.login(username, password);
    }

    @Override
    public Observable<BikeStockListFeed> getStockInBikeList(int pageSize, int pageNum, String product_name, String sortString, String parent_code, long fromTime, long endTime) {
        return apiService.getStockInBikeList(pageSize,pageNum,product_name,sortString,parent_code,fromTime,endTime);
    }


    @Override
    public Observable<StockOutFeed> getStockoutFeed(int pageSize, int pageNum, boolean isByStockinPrice, String sortString, String parent_code, long fromTime, long endTime) {
        return apiService.getStockOutFeed(pageSize,pageNum,isByStockinPrice,
                sortString,parent_code,fromTime,endTime);
    }

    public static <T> Observable.Transformer<T, T> applySchedulers() {

        return new Observable.Transformer<T, T>() {
            @Override public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                                  .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
