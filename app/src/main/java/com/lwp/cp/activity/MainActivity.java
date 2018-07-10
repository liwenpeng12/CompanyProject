package com.lwp.cp.activity;

import android.Manifest;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.classic.common.MultipleStatusView;
import com.lwp.cp.R;
import com.lwp.cp.base.BaseActivity;
import com.lwp.cp.base.BaseBean;
import com.lwp.cp.model.response.BannerResponse;
import com.lwp.cp.model.response.HomeResponseBean;
import com.lwp.cp.presenter.MainPresenter;
import com.lwp.cp.util.Constant;
import com.lwp.cp.util.GlideImageLoader;
import com.lwp.cp.view.MainnView;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * liwenpeng
 * 2018/7/1 15:44
 * CompanyProject
 * Descrobe:主页面
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainnView {



    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;
    private List<String> mImgList = new ArrayList<>();
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.fl_main)
    FrameLayout frameLayout;


    @Override
    public void initdata() {
        getPermision();


    }


    private void getPermision() {
        super.initdata();
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
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }


    @Override
    public void onGetting(int type) {
        if (type == Constant.LOADINGVIEW){
            multipleStatusView.showLoading();
        }
    }

    @Override
    public void onGetFailed(int type) {
        if (type == Constant.EMPTYVIEW){
            multipleStatusView.showEmpty();
        }
        if (type == Constant.NONETVIEW){
            multipleStatusView.showNoNetwork();
        }
        if (type == Constant.ERRORVIEW){
            multipleStatusView.showError();
        }

    }

    @Override
    public void onGetBannerSuccess(BannerResponse responseList) {
        multipleStatusView.showContent();
        int size = responseList.getData().size();
        for (int i = 0; i < size; i++) {
            mImgList.add(responseList.getData().get(i).getImagePath());
        }
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(mImgList);
        banner.start();


    }

    @Override
    public void onGetArticalSuccess(BaseBean<HomeResponseBean> responseList) {



    }

    @Override
    public LifecycleProvider getActivityLifeCycle() {
        return MainActivity.this;
    }


}
