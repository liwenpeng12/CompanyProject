package com.liwenpeng.companyproject.http;

import com.liwenpeng.companyproject.base.BaseBean;
import com.liwenpeng.companyproject.model.HomeResponseBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * liwenpeng
 * 2018/6/30 23:42
 * CompanyProject
 * Descrobe:统一请求接口
 */
public interface ApiService {

    /**
     *首页文章列表
     */
    @GET("article/list/{page}/json")
    Observable<BaseBean<HomeResponseBean>> getHomeArtical(@Path("page") String path);
}
