package com.oncedoing.bikeshop.manage_bike.utils;

import com.google.gson.reflect.TypeToken;
import com.oncedoing.bikeshop.model.ProductGeneralEntity;
import com.oncedoing.bikeshop.utils.GsonHelper;

import java.util.List;

/**
 * Created by kw on 2016/3/25.15:00.
 */
public class ManageUtils {

    public static String convertMoney(int money){
        return "￥" + (float)(Math.round(money*100))/10000;
    }

    public static List<ProductGeneralEntity> parseGeneral(String generalString){
        return GsonHelper.getGson().fromJson(generalString, new
                TypeToken<List<ProductGeneralEntity>>(){}.getType());
    }
}
