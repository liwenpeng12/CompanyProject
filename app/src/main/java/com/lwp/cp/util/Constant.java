package com.lwp.cp.util;

/**
 * liwenpeng
 * 2018/7/7 16:25
 * CompanyProject
 * Descrobe:常量
 */
public interface Constant {

    //用户信息
    String  ISFIRSTLOGIN = "isfirstlogin";
    String USERNAME = "username";
    String PASSWORD = "password";


    //布局状态
    int SuccessView = 0;//加载成功视图
    int EMPTYVIEW  = 1;//内容为空
    int ERRORVIEW = 2;//错误
    int LOADINGVIEW =3;//加载中
    int NONETVIEW = 4;//无网络

    /**
     * int BannerType = 0;
      int ArticalType = 1;
     * */
    int BANNERTYPE = 0;
    int ARTICALTYPE = 1;
}
