package com.oncedoing.bikeshop.utils;

import com.oncedoing.bikeshop.config.AppConstants;

/**
 * Created by Administrator on 2016/3/15.
 */
public class UriHelper {

    public static int calculateTotalPages(int totalNumber) {
        if (totalNumber > 0) {
            return totalNumber % AppConstants.SINGLE_PAGE_COUNT != 0 ? (totalNumber / AppConstants.SINGLE_PAGE_COUNT + 1) : totalNumber / AppConstants.SINGLE_PAGE_COUNT;
        } else {
            return 0;
        }
    }
}
