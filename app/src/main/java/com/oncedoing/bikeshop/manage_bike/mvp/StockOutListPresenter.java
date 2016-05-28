package com.oncedoing.bikeshop.manage_bike.mvp;

import android.os.Handler;

import com.oncedoing.bikeshop.manage_bike.datalayer.GetBikeListUsercase;
import com.oncedoing.bikeshop.model.StockOutEntity;
import com.oncedoing.bikeshop.model.StockOutFeed;
import com.oncedoing.bikeshop.mvp.IBaseListPresenter;
import com.oncedoing.bikeshop.mvp.IView;
import com.oncedoing.bikeshop.utils.UriHelper;

import java.util.List;

import javax.inject.Inject;

import me.kkwang.commonlib.rx.RxUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by kw on 2016/3/25.12:58.
 */
public class StockOutListPresenter implements IBaseListPresenter {

	int currentPage = 1;
	boolean isCanLoadMore;
	IStockOutListView mListView;
	GetBikeListUsercase mGetBikeListUsercase;


	@Inject
	public StockOutListPresenter(GetBikeListUsercase getBikeListUsercase) {
		mGetBikeListUsercase = getBikeListUsercase;
	}


	@Override
	public void onCreate() {

	}


	@Override
	public void attachView(IView view) {
		mListView = (IStockOutListView) view;
		;
	}


	@Override
	public void onDestroy() {
		mListView = null;
	}


	private void calculateNoMore(int totalCount) {

		int totalPageCount = UriHelper.calculateTotalPages(totalCount);
		if (currentPage < totalPageCount) {
			isCanLoadMore = true;
		} else {
			isCanLoadMore = false;
			mListView.showNoMore();
		}
	}


	@Override
	public void refresh() {
		currentPage = 1;
		mGetBikeListUsercase.getStockOutFeed(currentPage)
				.compose(RxUtils.applyIOMainSchedulers())
				.subscribe();
	}


	@Override
	public void loadMore() {
		if (isCanLoadMore) {
			currentPage++;
			mGetBikeListUsercase.getStockOutFeed(currentPage)
					.subscribeOn(Schedulers.io())
                                .filter(new Func1<StockOutFeed, Boolean>() {
                                    @Override
                                    public Boolean call(StockOutFeed stockOutFeed) {
                                        return stockOutFeed != null;
                                    }
                                })
//					.compose(RxUtils.applyIOMainSchedulers())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(new Subscriber<StockOutFeed>() {
						@Override
						public void onCompleted() {

						}

						@Override
						public void onError(Throwable e) {
							mListView.showLoadError();
						}

						@Override
						public void onNext(StockOutFeed stockOutFeed) {
							int total = stockOutFeed.getTotal();
							List<StockOutEntity> list = stockOutFeed
									.getRows();
							mListView.addList(list);
							calculateNoMore(total);

						}
					});
		} else {
			mListView.showNoMore();
			new Handler();
		}
	}
}
