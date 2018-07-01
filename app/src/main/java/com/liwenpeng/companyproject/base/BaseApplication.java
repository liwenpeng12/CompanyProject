package com.liwenpeng.companyproject.base;

import android.app.Application;

import com.liwenpeng.companyproject.util.Utils;

/**
 * liwenpeng
 * 2018/6/30 14:37
 * CompanyProject
 * Descrobe:application基类
 */
public class BaseApplication extends Application {

    private static boolean isDebugMode = true;
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }

    public static boolean isIsDebugMode() {
        return isDebugMode;
    }
}
