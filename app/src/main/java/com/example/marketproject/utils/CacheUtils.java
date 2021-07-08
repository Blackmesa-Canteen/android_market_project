package com.example.marketproject.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 缓存工具类
 */
public class CacheUtils {

    /**
     * 得到本地保存的String类型的数据
     * @param mContext
     * @param key
     * @return
     */
    public static String getString(Context mContext, String key) {
        SharedPreferences sp = mContext.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    /**
     * 保存String类型数据
     * @param mContext 上下文
     * @param key
     * @param value 保存的值
     */
    public static void saveString(Context mContext, String key, String value) {
        SharedPreferences sp = mContext.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }
}
