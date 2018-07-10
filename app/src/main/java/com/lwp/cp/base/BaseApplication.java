package com.lwp.cp.base;

import android.app.Application;
import android.content.Context;

import com.lwp.cp.util.Utils;

import cn.jpush.android.api.JPushInterface;

/**
 * liwenpeng
 * 2018/6/30 14:37
 * CompanyProject
 * Descrobe:application基类
 */
public class BaseApplication extends Application {

    private static boolean isDebugMode = true;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        mContext = getApplicationContext();
    }

    public static boolean isIsDebugMode() {
        return isDebugMode;
    }
    public static Context getmContext(){
        return mContext;
    }


}
