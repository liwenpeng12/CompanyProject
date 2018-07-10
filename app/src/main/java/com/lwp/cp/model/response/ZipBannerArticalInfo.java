package com.lwp.cp.model.response;

import com.lwp.cp.base.BaseBean;

/**
 * liwenpeng
 * 2018/7/10 17:42
 * CompanyProject
 * Descrobe:
 */
public class ZipBannerArticalInfo {
    private BaseBean<HomeResponseBean> homeResponseBean;
    private BannerResponse bannerResponse;

    public ZipBannerArticalInfo(BaseBean<HomeResponseBean> homeResponseBean, BannerResponse bannerResponse) {
        this.homeResponseBean = homeResponseBean;
        this.bannerResponse = bannerResponse;
    }

    public BaseBean<HomeResponseBean> getHomeResponseBean() {
        return homeResponseBean;
    }

    public void setHomeResponseBean(BaseBean<HomeResponseBean> homeResponseBean) {
        this.homeResponseBean = homeResponseBean;
    }

    public BannerResponse getBannerResponse() {
        return bannerResponse;
    }

    public void setBannerResponse(BannerResponse bannerResponse) {
        this.bannerResponse = bannerResponse;
    }
}
