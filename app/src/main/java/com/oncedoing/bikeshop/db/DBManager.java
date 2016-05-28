package com.oncedoing.bikeshop.db;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.oncedoing.bikeshop.BuildConfig;
import com.oncedoing.bikeshop.model.CustomerSortEntity;

import java.util.List;

import me.kkwang.commonlib.db.LiteOrmDBUtil;

/**
 * Created by Administrator on 2016/3/19.
 */
public class DBManager {

    private static final String DB_NAME = "oncedoing.db";
    public static Context sContext;
    public static LiteOrm sDb;


    public static void init(Context context){

        sContext = context;
        sDb = LiteOrm.newCascadeInstance(context, DB_NAME);
        if (BuildConfig.DEBUG) {
            sDb.setDebugged(true);
        }

        LiteOrmDBUtil.init(sDb);
    }

    /**
     * 插入所有记录
     * @param list
     */
    public static <T> void insertAll(List<T> list){
        LiteOrmDBUtil.insertAll(list);
    }
    /**
     * 查询所有
     * @param cla
     * @return
     */
    public static <T> List<T> getQueryAll(Class<T> cla){
        return LiteOrmDBUtil.getQueryAll(cla);
    }

    public static void deleteAll(){
        LiteOrmDBUtil.deleteAll(CustomerSortEntity.class);
    }


}
