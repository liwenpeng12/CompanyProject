package com.liwenpeng.companyproject.base;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.liwenpeng.companyproject.R;
import com.liwenpeng.companyproject.util.ClickUtils;
import com.liwenpeng.companyproject.util.KeyboardUtils;
import com.liwenpeng.companyproject.util.StatusBarUtil;
import com.liwenpeng.companyproject.util.ToastUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @autor zcs
 * @time 2017/11/22
 * @describe Activity基类
 */

public abstract class BaseActivity<V extends BaseView ,P extends BasePresenter<V>> extends RxAppCompatActivity {

    public final String CurrentTAG = getClass().getSimpleName();

    private boolean isHideStatuBar = true;

    private P mPresenter;
    private V mView;
    private Unbinder bind;

    public P getPresenter() {
        return mPresenter;
    }

    public V getView() {
        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId()!= 0){
            setContentView(getLayoutId());
        }
        //强制竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
Log.d("lwp","currenttag:"+CurrentTAG);
        ButterKnife.bind(this);
        bind = ButterKnife.bind(this);
        if (mPresenter == null){
            mPresenter = createPresenter();
        }
        if (mView == null){
            mView = createView();
        }
        if (mPresenter != null && mView != null){
            mPresenter.attachView(mView);
        }

        setLayout();
        EventBus.getDefault().register(this);
        initView();
        initData();

    }

    /**
     * 视图的初始化
     * */
    public abstract void initData();
    public abstract void initView();

    /**
     * 视图布局
     * */
    public  abstract int getLayoutId();

    /**
     * Presenter和View接口
     * */
    public abstract P createPresenter();
    public abstract V createView();

    public void LogD(String msg){
        if (BaseApplication.isIsDebugMode()){
            Log.d(CurrentTAG,msg);
        }
    }
    public void LogE(String msg){
        if (BaseApplication.isIsDebugMode()){
            Log.e(CurrentTAG,msg);
        }
    }
    /**
     * 吐司展示
     * */
    public void showToast(String msg){
        ToastUtils.showShort(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.deTachView();
        }
        bind.unbind();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 是否快速点击
     * 防止过快点击造成多次事件执行
     * */
    public Boolean IsFastClick(){
        return ClickUtils.isFastClick();
    }

    public void setLayout() {
        //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //  getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        if (isHideStatuBar) {
            StatusBarUtil.transparencyBar(this);
//            StatusBarUtil.setStatusBarColor(this, R.color.color_039aff);
        } else {
            //透明状态栏
            // StatusBarUtil.transparencyBar(this);
            StatusBarUtil.StatusBarLightMode2(BaseActivity.this);
        }
        /**
         * 设置状态栏字体颜色为白色
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            //  window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(getResources().getColor(R.color.color_039aff));
             window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }




    /**
     * 隐藏软键盘
     */
    public void hideSoftKeyboard() {
        KeyboardUtils.hideSoftInput(this);
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftKeyboard(View v) {
        KeyboardUtils.hideSoftInput(v);
    }

    /**
     * 显示软键盘
     */
    public void showSoftKeyboard() {
        KeyboardUtils.showSoftInput(this);
    }

    /**
     * 显示软键盘
     */
    public void showSoftKeyboard(View v) {
        KeyboardUtils.showSoftInput(v);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(String event) {
        if (event.equals("login")) {
            finish();
        }
    }
}
