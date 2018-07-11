package com.lwp.cp.presenter;

import com.lwp.cp.base.BaseApplication;
import com.lwp.cp.base.BaseBean;
import com.lwp.cp.base.BasePresenter;
import com.lwp.cp.http.Api;
import com.lwp.cp.model.response.BannerResponse;
import com.lwp.cp.model.response.HomeResponseBean;
import com.lwp.cp.model.response.ZipBannerArticalInfo;
import com.lwp.cp.util.Constant;
import com.lwp.cp.util.LogUtils;
import com.lwp.cp.util.NetUtil;
import com.lwp.cp.view.HomeView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * liwenpeng
 * 2018/7/10 20:44
 * PlayAndroid
 * Descrobe:
 */
public class HomePresenter extends BasePresenter<HomeView> {

     int currentPage = 0;
    @Override
    protected void initData() {
        if (NetUtil.isNetworkConnected(BaseApplication.getmContext())) {
            getView().onGetting(Constant.LOADINGVIEW);
            Observable<HomeResponseBean> baseBeanObservable =
                    Api.getApi().getHomeArtical(""+currentPage).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            Observable<BannerResponse> bannerResponseObservable =
                    Api.getApi().getBanner().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            Disposable subscribe = Observable.zip(baseBeanObservable, bannerResponseObservable,
                    new BiFunction<HomeResponseBean, BannerResponse, ZipBannerArticalInfo>() {
                        @Override
                        public ZipBannerArticalInfo apply(HomeResponseBean homeResponseBeanBaseBean, BannerResponse bannerResponse) throws Exception {
                            return new ZipBannerArticalInfo(homeResponseBeanBaseBean, bannerResponse);
                        }
                    }).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer<ZipBannerArticalInfo>() {
                @Override
                public void accept(ZipBannerArticalInfo zipBannerArticalInfo) throws Exception {

                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ZipBannerArticalInfo>() {
                @Override
                public void accept(ZipBannerArticalInfo zipBannerArticalInfo) throws Exception {
                    getView().onGetArticalSuccess(zipBannerArticalInfo.getHomeResponseBean());
                    getView().onGetBannerSuccess(zipBannerArticalInfo.getBannerResponse());
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    LogUtils.d("错误:"+throwable.toString());
                    getView().onGetFailed(Constant.ERRORVIEW);
                }
            });

        }else {
            getView().onGetFailed(Constant.NONETVIEW);
        }
    }

   public void  refreshData(){
       if (NetUtil.isNetworkConnected(BaseApplication.getmContext())) {
           getView().onGetting(Constant.LOADINGVIEW);
           Observable<HomeResponseBean> baseBeanObservable =
                   Api.getApi().getHomeArtical("0").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
           Observable<BannerResponse> bannerResponseObservable =
                   Api.getApi().getBanner().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
           Disposable subscribe = Observable.zip(baseBeanObservable, bannerResponseObservable,
                   new BiFunction<HomeResponseBean, BannerResponse, ZipBannerArticalInfo>() {
                       @Override
                       public ZipBannerArticalInfo apply(HomeResponseBean homeResponseBeanBaseBean, BannerResponse bannerResponse) throws Exception {
                           return new ZipBannerArticalInfo(homeResponseBeanBaseBean, bannerResponse);
                       }
                   }).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer<ZipBannerArticalInfo>() {
               @Override
               public void accept(ZipBannerArticalInfo zipBannerArticalInfo) throws Exception {

               }
           }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ZipBannerArticalInfo>() {
               @Override
               public void accept(ZipBannerArticalInfo zipBannerArticalInfo) throws Exception {
                   getView().onGetArticalSuccess(zipBannerArticalInfo.getHomeResponseBean());
                   getView().onGetBannerSuccess(zipBannerArticalInfo.getBannerResponse());
               }
           }, new Consumer<Throwable>() {
               @Override
               public void accept(Throwable throwable) throws Exception {
                   LogUtils.d("错误:"+throwable.toString());
                   getView().onGetFailed(Constant.ERRORVIEW);
               }
           });

       }else {
           getView().onGetFailed(Constant.NONETVIEW);
       }
   }

   public void loadMoreData(int page){
       if (NetUtil.isNetworkConnected(BaseApplication.getmContext())) {
           getView().onGetting(Constant.LOADINGVIEW);
           Observable<HomeResponseBean> baseBeanObservable =
                   Api.getApi().getHomeArtical(""+page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
           Observable<BannerResponse> bannerResponseObservable =
                   Api.getApi().getBanner().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
           Disposable subscribe = Observable.zip(baseBeanObservable, bannerResponseObservable,
                   new BiFunction<HomeResponseBean, BannerResponse, ZipBannerArticalInfo>() {
                       @Override
                       public ZipBannerArticalInfo apply(HomeResponseBean homeResponseBeanBaseBean, BannerResponse bannerResponse) throws Exception {
                           return new ZipBannerArticalInfo(homeResponseBeanBaseBean, bannerResponse);
                       }
                   }).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer<ZipBannerArticalInfo>() {
               @Override
               public void accept(ZipBannerArticalInfo zipBannerArticalInfo) throws Exception {

               }
           }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ZipBannerArticalInfo>() {
               @Override
               public void accept(ZipBannerArticalInfo zipBannerArticalInfo) throws Exception {
                   getView().onGetArticalSuccess(zipBannerArticalInfo.getHomeResponseBean());
                   getView().onGetBannerSuccess(zipBannerArticalInfo.getBannerResponse());
               }
           }, new Consumer<Throwable>() {
               @Override
               public void accept(Throwable throwable) throws Exception {
                   LogUtils.d("错误:"+throwable.toString());
                   getView().onGetFailed(Constant.ERRORVIEW);
               }
           });

       }else {
           getView().onGetFailed(Constant.NONETVIEW);
       }
   }
}
