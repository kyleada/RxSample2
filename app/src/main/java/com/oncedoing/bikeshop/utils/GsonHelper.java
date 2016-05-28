package com.oncedoing.bikeshop.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Administrator on 2016/3/26.
 */
public class GsonHelper {

    private static volatile Gson gson;

    public static Gson getGson(){
        if(gson == null){
            synchronized (GsonHelper.class){
                if (gson == null){
                    gson = new GsonBuilder().serializeNulls().create();
                }
            }
        }
        return gson;
    }
}
