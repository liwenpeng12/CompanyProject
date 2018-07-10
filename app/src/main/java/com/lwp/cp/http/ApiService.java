package com.lwp.cp.http;

import com.lwp.cp.base.BaseBean;
import com.lwp.cp.model.response.BannerResponse;
import com.lwp.cp.model.response.HomeResponseBean;
import com.lwp.cp.model.response.LoginResponse;
import com.lwp.cp.model.response.RegisterResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    /**
     * 首页banner
     * */
    @GET("banner/json")
    Observable<BannerResponse> getBanner();

    /**
     * 登录
     * */
    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginResponse> execLogin(@FieldMap Map<String,String> map);

    /**
     * 注册
     * */
    @FormUrlEncoded
    @POST("user/register")
    Observable<RegisterResponse> exetRegister(@FieldMap Map<String,String> map);
}
