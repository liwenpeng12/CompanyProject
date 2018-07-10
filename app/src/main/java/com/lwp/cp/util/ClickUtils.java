package com.lwp.cp.util;

/**
 * liwenpeng
 * 2018/6/30 18:02
 * CompanyProject
 * Descrobe:防止 测试/用户 多次点击
 */
public class ClickUtils {

    private static final int MIN_DELAY_TIME= 1000;  // 两次点击间隔不能少于1000ms
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }
}
