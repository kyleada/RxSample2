package com.oncedoing.bikeshop.model;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 *@author xiaobo.cui 2014年11月26日 上午10:40:15
 *
 */
@Table("sort_token")
public class SortToken {
	@PrimaryKey(AssignType.AUTO_INCREMENT)
	int _id;

	public String simpleSpell = "";//简拼
	public String wholeSpell = "";//全拼
	public String chName = "";//中文全名
}
