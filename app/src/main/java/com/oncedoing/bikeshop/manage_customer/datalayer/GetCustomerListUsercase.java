package com.oncedoing.bikeshop.manage_customer.datalayer;

import com.oncedoing.bikeshop.config.AppConstants;
import com.oncedoing.bikeshop.domain.Usecase;
import com.oncedoing.bikeshop.model.CustomerListFeed;
import com.oncedoing.bikeshop.network.Repository;

import me.kkwang.commonlib.rx.RxUtils;
import rx.Observable;

/**
 * Created by Administrator on 2016/3/18.
 */
public class GetCustomerListUsercase implements Usecase<CustomerListFeed> {


    private Repository repository;

    public GetCustomerListUsercase(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<CustomerListFeed> execute() {
        return null;
    }

    public Observable<CustomerListFeed> getCustomerListByPageNum(int pageNum) {
        return repository.getCustomerList(
                AppConstants.QUERY_CUSTOMER_LIST_URI,
                AppConstants.SINGLE_PAGE_COUNT,
                pageNum,
                "",
                "create_time.desc",
                "oncebikeadmin",
                -1,
                -1,
                false)
                .compose(RxUtils.<CustomerListFeed>applyIOMainSchedulers());
    }

    public Observable<CustomerListFeed> getAllCustomerList() {
        return repository.getCustomerList(
                AppConstants.QUERY_CUSTOMER_LIST_URI,
                AppConstants.DEFULAT_MAX_CUSTOMER_COUNT,
                1,
                "",
                "create_time.desc",
                "oncebikeadmin",
                -1,
                -1,
                false);
    }

    public Observable<CustomerListFeed> getBookListBySearchKeyAndPageNum(String searchKey,
                                                                         int pageNum) {
        return repository.getCustomerList(
                AppConstants.QUERY_CUSTOMER_LIST_URI,
                AppConstants.SINGLE_PAGE_COUNT,
                pageNum,
                searchKey,
                "create_time.desc",
                "oncebikeadmin", //此处不应该这样
                -1,
                -1,
                false)
                .compose(RxUtils.<CustomerListFeed>applyIOMainSchedulers());
    }

    public Observable<CustomerListFeed> search(String productName) {
        return getBookListBySearchKeyAndPageNum(productName, 1);
    }
}
