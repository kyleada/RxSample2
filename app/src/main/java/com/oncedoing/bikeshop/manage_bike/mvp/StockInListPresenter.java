package com.oncedoing.bikeshop.manage_bike.mvp;

import com.oncedoing.bikeshop.manage_bike.datalayer.GetBikeListUsercase;
import com.oncedoing.bikeshop.model.StockInEntity;
import com.oncedoing.bikeshop.model.BikeStockListFeed;
import com.oncedoing.bikeshop.mvp.IBasePresenter;
import com.oncedoing.bikeshop.mvp.IView;
import com.oncedoing.bikeshop.utils.UriHelper;
import java.util.List;
import javax.inject.Inject;
import me.kkwang.commonlib.rx.RxUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/3/22.
 */
public class StockInListPresenter implements IBasePresenter {


    public interface FilterType {

        int ALL = 0;
        int TODAY = 1;
        int WEEK = 2;
        int MONTH = 3;
    }

    private static final String TAG = "StockInListPresenter";


    private int currentPage;
    private boolean isCanLoadMore;
    private List<StockInEntity> bikeInfoEntityList;
    private IStockInListView bikeListView;
    private GetBikeListUsercase getBikeListUsercase;


    @Inject
    public StockInListPresenter(GetBikeListUsercase getBikeListUsercase) {
        this.getBikeListUsercase = getBikeListUsercase;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(IView view) {
        bikeListView = (IStockInListView) view;
    }

    @Override
    public void onDestroy() {

        bikeInfoEntityList = null;
        bikeListView = null;
    }

    private Observable<BikeStockListFeed> getRequestObservable(int currentPage, String keywords, int filterType){

        Observable<BikeStockListFeed> source = Observable.empty();
        currentPage = 1;
        switch (filterType) {
            case FilterType.ALL:
                source = getBikeListUsercase.getStockInBikeList(currentPage);
                break;
            case FilterType.TODAY:
                source = getBikeListUsercase.getStockInBikeListToday(currentPage);
                break;
            case FilterType.WEEK:
                source = getBikeListUsercase.getStockInBikeListWeek(currentPage);
                break;
            case FilterType.MONTH:
                source = getBikeListUsercase.getStockInBikeListMonth(currentPage);
                break;
        }
        return source.compose(RxUtils.<BikeStockListFeed>applyIOMainSchedulers())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<BikeStockListFeed>() {
							  @Override
							  public void call(BikeStockListFeed bikeStockListFeed) {
								  bikeListView.showLoading();
							  }
						  }

				)
                .filter(new Func1<BikeStockListFeed, Boolean>() {
					@Override
					public Boolean call(BikeStockListFeed bikeStockListFeed) {
						return bikeStockListFeed != null;
					}
				});
    }

    private void calculateNoMore(BikeStockListFeed listFeed){

        int totalCount = listFeed.getTotal();
        int totalPageCount = UriHelper.calculateTotalPages(totalCount);
        if(bikeInfoEntityList != null && currentPage < totalPageCount){
            isCanLoadMore = true;
        }else {
            isCanLoadMore = false;
        }
    }

    public void refreshList(String keywords, int filterType) {

        currentPage = 1;
        getRequestObservable(1,keywords,filterType).subscribe(new Subscriber<BikeStockListFeed>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        bikeListView.showLoadError();
                    }

                    @Override
                    public void onNext(BikeStockListFeed listFeed) {
                        bikeListView.hideLoading();
                        bikeInfoEntityList = listFeed.getRows();
                        if (listFeed.getTotal() == 0) {
                            bikeListView.showEmpty();
                        } else {
                            bikeListView.refreshList(bikeInfoEntityList);
                        }
                        calculateNoMore(listFeed);
                    }
                });
    }


    public void loadMore(String keywords, int filterType){
        if(isCanLoadMore){
            currentPage++;

            getRequestObservable(1,keywords,filterType).subscribe(new Subscriber<BikeStockListFeed>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
//                    bikeListView.showLo();
                }

                @Override
                public void onNext(BikeStockListFeed listFeed) {
                    bikeListView.hideLoading();
                    bikeInfoEntityList = listFeed.getRows();
                    bikeListView.addList(bikeInfoEntityList);
                    calculateNoMore(listFeed);

                }
            });

        }else{
            bikeListView.showNoMore();
        }
    }

    public void refresh(String keywords,int category) {

        switch (category) {
            case 0:
                refreshList(keywords, StockInListPresenter.FilterType.ALL);
                break;
            case 1:
                refreshList(keywords, StockInListPresenter.FilterType.TODAY);
                break;
            case 2:
                refreshList(keywords, StockInListPresenter.FilterType.WEEK);
                break;
            case 3:
                refreshList(keywords, StockInListPresenter.FilterType.MONTH);
                break;
        }
    }

}
