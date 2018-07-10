package com.lwp.cp.presenter;

import com.lwp.cp.activity.MainActivity;
import com.lwp.cp.base.BaseApplication;
import com.lwp.cp.base.BaseBean;
import com.lwp.cp.base.BasePresenter;
import com.lwp.cp.http.Api;
import com.lwp.cp.http.ApiTransformer;
import com.lwp.cp.http.RxException;
import com.lwp.cp.model.response.BannerResponse;
import com.lwp.cp.model.response.HomeResponseBean;
import com.lwp.cp.model.response.ZipBannerArticalInfo;
import com.lwp.cp.util.Constant;
import com.lwp.cp.util.LogUtils;
import com.lwp.cp.util.NetUtil;
import com.lwp.cp.view.MainnView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * liwenpeng
 * 2018/7/8 14:17
 * CompanyProject
 * Descrobe:
 */
public class MainPresenter extends BasePresenter<MainnView> {

    private LifecycleProvider activityLifeCycle;
    private BaseBean<List<BannerResponse>> responseList;
    private Disposable bannersubscribe;
    private Disposable subscribe;

    @Override
    protected void initData() {
        getView().onLoading();
    }
}
