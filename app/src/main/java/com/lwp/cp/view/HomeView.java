package com.lwp.cp.view;

import com.lwp.cp.base.BaseBean;
import com.lwp.cp.model.response.BannerResponse;
import com.lwp.cp.model.response.HomeResponseBean;
import com.trello.rxlifecycle2.LifecycleProvider;

/**
 * liwenpeng
 * 2018/7/10 20:45
 * PlayAndroid
 * Descrobe:
 */
public interface HomeView {
    void onGetting(int type);
    void onGetFailed(int type);
    void onGetBannerSuccess(BannerResponse responseList);
    void onGetArticalSuccess(BaseBean<HomeResponseBean> responseList);
    LifecycleProvider getActivityLifeCycle();
}
