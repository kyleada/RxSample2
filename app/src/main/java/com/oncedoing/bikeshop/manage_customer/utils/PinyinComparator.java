package com.oncedoing.bikeshop.manage_customer.utils;

import com.oncedoing.bikeshop.model.CustomerSortEntity;

import java.util.Comparator;

/**
 * 
 * @author xiaanming
 *
 */
public class PinyinComparator implements Comparator<CustomerSortEntity> {

	public int compare(CustomerSortEntity o1, CustomerSortEntity o2) {
		if (o1.getSortModel().sortLetters.equals("@") || o2.getSortModel().sortLetters.equals("#")) {
			return 1;
		} else if (o1.getSortModel().sortLetters.equals("#") || o1.getSortModel().sortLetters.equals("@")) {
			return -1;
		} else {
			return o1.getSortModel().sortLetters.compareTo(o2.getSortModel().sortLetters);
		}
	}

}
