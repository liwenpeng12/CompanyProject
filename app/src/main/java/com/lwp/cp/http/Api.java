package com.lwp.cp.http;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.lwp.cp.BuildConfig;
import com.lwp.cp.base.BaseApplication;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

/**
 * liwenpeng
 * 2018/6/30 16:06
 * CompanyProject
 * Descrobe:获取refrofit对象
 */
public class Api {

    private static  ApiService mApiService;

    private static final int DEFAULT_READTIMEOUT = 10;
    private static final int DEFAULT_TIMEOUT = 10;

    private static final String BASEURL = "http://www.wanandroid.com/";

    /**
     * 得到唯一的Api对象
     * */
    public static ApiService getApi(){
        if (mApiService == null){
            //手动创建一个OkHttpClient并设置超时时间
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            builder.readTimeout(DEFAULT_READTIMEOUT,TimeUnit.SECONDS);
            //日志拦截器
//            if (BuildConfig.DEBUG){
//                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                builder.addInterceptor(loggingInterceptor);
//            }
            ClearableCookieJar cookieJar =
                    new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApplication.getmContext()));
            builder.cookieJar(cookieJar);
            builder.addInterceptor(new HnRequestLogInterceptor());
            /**
             * 拦截器
             * */
//            builder.addInterceptor(new Interceptor() {
//                @Override
//                public okhttp3.Response intercept(Chain chain) throws IOException {
//                    Request request = chain.request();
//                    HttpUrl.Builder authorizedUrlBuilder = request.url()
//                            .newBuilder();
//                    Request newRequest = request.newBuilder()
//                            .method(request.method(), request.body())
//                            .url(authorizedUrlBuilder.build())
//                            .build();
//                    return  chain.proceed(newRequest);
//                }
//            });


            mApiService = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASEURL)
                    .build().create(ApiService.class);

        }
        return mApiService;
    }


//    class myHeaderInterceptor implements Interceptor{
//       @Override
//             public Response intercept(Chain chain) throws IOException {
//           Response proceed = chain.proceed(chain.request());
//           return proceed.hea
//           return null;
//    }
//}


}
