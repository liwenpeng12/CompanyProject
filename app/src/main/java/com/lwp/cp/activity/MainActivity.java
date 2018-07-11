package com.lwp.cp.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.classic.common.MultipleStatusView;
import com.lwp.cp.R;
import com.lwp.cp.adapter.MainViewPagerAdapter;
import com.lwp.cp.base.BaseActivity;
import com.lwp.cp.base.BaseBean;
import com.lwp.cp.fragment.HomeFragment;
import com.lwp.cp.fragment.NavigationFragment;
import com.lwp.cp.fragment.ProjectFragment;
import com.lwp.cp.fragment.SettingsFragment;
import com.lwp.cp.fragment.SystemFragment;
import com.lwp.cp.listener.MainPagerchangedListener;
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

    @BindView(R.id.main_rg)
    RadioGroup mRadioGroup;

    @BindView(R.id.btn_home)
    Button mBtnHome;

    @BindView(R.id.rb_system)
    RadioButton mRbSystem;

    @BindView(R.id.rb_navigation)
    RadioButton mRbNavigation;

    @BindView(R.id.rb_project)
    RadioButton mRbProject;

    @BindView(R.id.rb_settings)
    RadioButton mRbSettings;


    private List<Fragment> mFragmentList;
    private HomeFragment mHomeFragment;
    private SystemFragment mSystemFragment;
    private NavigationFragment mNavigationFragment;
    private ProjectFragment mProjectFragment;
    private SettingsFragment mSettingsFragment;


    public void initData() {
        getPermision();
        setFragment();

    }

    private void setFragment() {
        if (mFragmentList == null){
            mFragmentList = new ArrayList<>();
        }
//体系
        if (mSystemFragment == null){
            mSystemFragment = new SystemFragment();
            mFragmentList.add(mSystemFragment);
        }
        //导航
        if (mNavigationFragment == null){
            mNavigationFragment = new NavigationFragment();
            mFragmentList.add(mNavigationFragment);
         }
//首页
        if (mHomeFragment == null){
            mHomeFragment = new HomeFragment();
            mFragmentList.add(mHomeFragment);
        }
        //x项目
         if (mProjectFragment == null){
            mProjectFragment = new ProjectFragment();
             mFragmentList.add(mProjectFragment);
         }
         //我的
         if (mSettingsFragment == null){
            mSettingsFragment = new SettingsFragment();
             mFragmentList.add(mSettingsFragment);
         }
        mViewpager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(),mFragmentList));
        mViewpager.setCurrentItem(2);
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewpager.setCurrentItem(position);
                showToast("当前位置:"+position);
                switch (position){
                    case 0:
                        mRadioGroup.check(R.id.rb_system);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.rb_navigation);
                        break;
                    case 3:
                        mRadioGroup.check(R.id.rb_project);
                        break;
                    case 4:
                        mRadioGroup.check(R.id.rb_settings);
                        break;
                    case 2:

                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_system:
                        mViewpager.setCurrentItem(0);
                        mRbSystem.setChecked(true);
                        break;
                    case R.id.rb_navigation:
                        mViewpager.setCurrentItem(1);
                        mRbNavigation.setChecked(true);
                        break;
                    case R.id.rb_project:
                        mViewpager.setCurrentItem(3);
                        mRbProject.setChecked(true);
                        break;
                    case R.id.rb_settings:
                        mViewpager.setCurrentItem(4);
                        mRbSettings.setChecked(true);
                        break;
                }
            }
        });
        mBtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   resetRadioButtonState();
                mViewpager.setCurrentItem(2);
            }
        });

    }

    public void resetRadioButtonState(){
 mRadioGroup.clearCheck();

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
