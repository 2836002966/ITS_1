package com.example.administrator.its.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2018/11/4.
 */

public class CacheUntil {
    public static void putString(Context context,String name,String value){
        SharedPreferences sharedPreferences=context.getSharedPreferences("cache", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(name,value);
        editor.commit();
    }
    public static void putBoolean(Context context,String name,Boolean value){
        SharedPreferences sharedPreferences=context.getSharedPreferences("cache",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putBoolean(name,value);
        editor.commit();
    }
    public static String getString(Context context, String name, String value){
        SharedPreferences sharedPreferences=context.getSharedPreferences("cache", Context.MODE_PRIVATE);
        return sharedPreferences.getString(name,value);
    }
    public static Boolean getBoolean(Context context,String key,Boolean value){
        SharedPreferences sharedPreferences=context.getSharedPreferences("cache",Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,value);
    }
}
