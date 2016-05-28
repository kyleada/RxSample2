package com.oncedoing.bikeshop.config;

/**
 * Created by Administrator on 2016/3/14.
 */
public interface AppConstants {

    String BASE_URL = "https://www.oncedoing.com/";

    int SINGLE_PAGE_COUNT = 10;

    int DEFULAT_MAX_CUSTOMER_COUNT = 2000;//这里是为了一次性获取全部的客户数，假定最多2000，设定2000为单页个数，是个Bug

    String QUERY_CUSTOMER_LIST_URI = "/tenantcustomers/tenant/{tenantId}";


    //请求码

    int REQUEST_CODE_ADD_CUSTOMER_INFO = 1000;




    //部分Intent Key值

    String CUSTOMER_INFO_KEY = "customer_info";
    String COMMODITY_INFO_KEY = "commodity_info";
    String STOCK_OUT_INFO_INFO_KEY = "commodity_info";


	//Third Party

	String FIR_API_TOKEN = "2c641c31769eb4fb8a9d2f199244d079";
}
