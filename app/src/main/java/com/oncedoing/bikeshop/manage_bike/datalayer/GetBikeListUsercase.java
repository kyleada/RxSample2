package com.oncedoing.bikeshop.manage_bike.datalayer;

import com.oncedoing.bikeshop.config.AppConstants;
import com.oncedoing.bikeshop.domain.Usecase;
import com.oncedoing.bikeshop.model.BikeListFeed;
import com.oncedoing.bikeshop.model.BikeStockListFeed;
import com.oncedoing.bikeshop.model.StockOutFeed;
import com.oncedoing.bikeshop.network.Repository;

import java.util.Date;

import me.kkwang.commonlib.utils.TimeUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/3/15.
 */
public class GetBikeListUsercase implements Usecase<BikeListFeed> {



    private Repository repository;

    public GetBikeListUsercase(Repository repository){
        this.repository = repository;
    }

    @Override
    public Observable<BikeListFeed> execute() {
        return null;
    }

    public Observable<BikeListFeed> getBookListByPageNum(int pageNum){
            return repository.getBikeList(
                    AppConstants.SINGLE_PAGE_COUNT,
                    pageNum,
                    "create_time.desc",
                    "",
                    "-1",
                    "-1",
                    -1,
                    -1,
                    false,
                    "")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BikeListFeed> getBookListByFilterAndPageNum(String productName,boolean num_limit,
                                                                  String topCategoryList,
                                                                  int pageNum){
        return repository.getBikeList(
                AppConstants.SINGLE_PAGE_COUNT,
                pageNum,
                "create_time.desc",
                productName,
                "-1",
                "-1",
                -1,
                -1,
                num_limit,
                topCategoryList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BikeStockListFeed> getStockInBikeList(int pageNum) {
        return repository.getStockInBikeList(AppConstants.SINGLE_PAGE_COUNT,pageNum,"","stockin_time.desc","",-1,-1);
    }

    public Observable<BikeStockListFeed> getStockInBikeListToday(int pageNum) {
        long from = TimeUtils.getTimesmorning().getTime();
        long to = new Date().getTime();
        return repository.getStockInBikeList(AppConstants.SINGLE_PAGE_COUNT,pageNum,"","stockin_time.desc","",from,to);
    }

    public Observable<BikeStockListFeed> getStockInBikeListWeek(int pageNum) {
        long from = TimeUtils.getTimesWeekmorning().getTime();
        long to = new Date().getTime();
        return repository.getStockInBikeList(AppConstants.SINGLE_PAGE_COUNT,pageNum,"","stockin_time.desc","",from,to);
    }

    public Observable<BikeStockListFeed> getStockInBikeListMonth(int pageNum) {
        long from = TimeUtils.getTimesMonthmorning().getTime();
        long to = new Date().getTime();
        return repository.getStockInBikeList(AppConstants.SINGLE_PAGE_COUNT,pageNum,"","stockin_time.desc","",from,to);
    }

    public Observable<StockOutFeed> getStockOutFeed(int pageNum) {
        return repository.getStockoutFeed(
                AppConstants.SINGLE_PAGE_COUNT,
                pageNum,
                false,
                "stockout_time.desc",
                "",
                -1,
                -1);
    }
}
