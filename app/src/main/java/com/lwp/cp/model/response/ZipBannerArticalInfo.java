package com.lwp.cp.model.response;

import com.lwp.cp.base.BaseBean;

/**
 * liwenpeng
 * 2018/7/10 17:42
 * CompanyProject
 * Descrobe:
 */
public class ZipBannerArticalInfo {
    private HomeResponseBean homeResponseBean;
    private BannerResponse bannerResponse;

    public ZipBannerArticalInfo(HomeResponseBean homeResponseBean, BannerResponse bannerResponse) {
        this.homeResponseBean = homeResponseBean;
        this.bannerResponse = bannerResponse;
    }

    public HomeResponseBean getHomeResponseBean() {
        return homeResponseBean;
    }

    public void setHomeResponseBean(HomeResponseBean homeResponseBean) {
        this.homeResponseBean = homeResponseBean;
    }

    public BannerResponse getBannerResponse() {
        return bannerResponse;
    }

    public void setBannerResponse(BannerResponse bannerResponse) {
        this.bannerResponse = bannerResponse;
    }
}
