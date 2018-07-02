package com.liwenpeng.companyproject.activity;

import android.Manifest;

import com.liwenpeng.companyproject.R;
import com.liwenpeng.companyproject.base.BaseActivity;
import com.liwenpeng.companyproject.base.BaseBean;
import com.liwenpeng.companyproject.base.BasePresenter;
import com.liwenpeng.companyproject.base.BaseView;
import com.liwenpeng.companyproject.http.Api;
import com.liwenpeng.companyproject.model.HomeResponseBean;
import com.liwenpeng.companyproject.util.CommonObservableTransformer;
import com.liwenpeng.companyproject.util.ToastUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.android.ActivityEvent;

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
        Disposable subscribe = new RxPermissions(this).requestEach(Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION).compose(this.<Permission>bindUntilEvent(ActivityEvent.DESTROY)).
                subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                if (permission.granted) {
                    showToast("权限申请成功");
                } else if (permission.shouldShowRequestPermissionRationale) {
                    showToast("用户拒绝该权限");
                } else {
                    showToast("用户拒绝改权限，请在设置里进行打开，否则影响正常使用");
                }
            }
        });
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
