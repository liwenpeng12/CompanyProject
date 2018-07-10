package com.lwp.cp.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.classic.common.MultipleStatusView;
import com.lwp.cp.R;
import com.lwp.cp.adapter.MainViewPagerAdapter;
import com.lwp.cp.base.BaseActivity;
import com.lwp.cp.base.BaseBean;
import com.lwp.cp.fragment.HomeFragment;
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



    @BindView(R.id.rl_main)
    RelativeLayout mRelativeLayout;

    @BindView(R.id.vp_main)
    ViewPager mViewpager;


    private List<Fragment> mFragmentList;
    private HomeFragment mHomeFragment;


    public void initData() {
        getPermision();
        setFragment();

    }

    private void setFragment() {
        if (mFragmentList == null){
            mFragmentList = new ArrayList<>();
        }
        if (mHomeFragment == null){
            mHomeFragment = new HomeFragment();
            mFragmentList.add(mHomeFragment);
        }
        mViewpager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(),mFragmentList));
        mViewpager.setCurrentItem(0);

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
    public void onLoading() {
        initData();
    }
}
