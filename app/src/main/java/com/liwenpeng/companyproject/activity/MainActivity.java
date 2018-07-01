package com.liwenpeng.companyproject.activity;

import com.liwenpeng.companyproject.R;
import com.liwenpeng.companyproject.base.BaseActivity;
import com.liwenpeng.companyproject.base.BaseBean;
import com.liwenpeng.companyproject.base.BasePresenter;
import com.liwenpeng.companyproject.base.BaseView;
import com.liwenpeng.companyproject.http.Api;
import com.liwenpeng.companyproject.model.HomeResponseBean;
import com.liwenpeng.companyproject.util.CommonObservableTransformer;
import com.liwenpeng.companyproject.util.ToastUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * liwenpeng
 * 2018/7/1 15:44
 * CompanyProject
 * Descrobe:主页面
 */
public class MainActivity extends BaseActivity {
    @Override
    public void initData() {
        Disposable subscribe = Api.getApi().getHomeArtical("2").compose(this.<BaseBean<HomeResponseBean>>bindToLifecycle()).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BaseBean<HomeResponseBean>>() {
            @Override
            public void accept(BaseBean<HomeResponseBean> homeResponseBeanBaseBean) throws Exception {
                List<HomeResponseBean.DatasBean> datas = homeResponseBeanBaseBean.t.getDatas();

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
              //  ToastUtils.showShort("" + throwable.toString());
                LogD(throwable.toString());
            }
        });
    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }
}
