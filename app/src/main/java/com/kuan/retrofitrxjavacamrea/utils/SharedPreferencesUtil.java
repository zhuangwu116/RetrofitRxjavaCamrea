package com.kuan.retrofitrxjavacamrea.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by zhuangwu on 17-5-6.
 */

public class SharedPreferencesUtil {
    private static final String FILE_NAME="network_setting";
    public static final String NETWORK_IP="ip";
    public static final String NETWORK_PORT="port";
    public static void saveData(Context context,String key,String data){
        SharedPreferences sharedPreferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key,data);
        editor.apply();
    }
    public static String getData(Context context,String key,String defValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,defValue);
    }
}
