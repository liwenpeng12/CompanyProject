package com.liwenpeng.companyproject.base;

import android.app.Activity;
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
import com.liwenpeng.companyproject.util.KeyboardUtils;
import com.liwenpeng.companyproject.util.StatusBarUtil;
import com.liwenpeng.companyproject.util.ToastUtils;


/**
 * @autor zcs
 * @time 2017/11/22
 * @describe Activity基类
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    Toast mToast;
    protected ProgressDialog waitDialog;
    private boolean isHideStatuBar = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContext = this;
       // EventBus.getDefault().register(this);
        initViews();
        initData();
        initListener();
        setLayout();
        //setLayout();
    }

    @Override
    protected void onDestroy() {
      //  EventBus.getDefault().unregister(this);
       // OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(), this);
        super.onDestroy();
    }

    protected abstract int getLayoutId();

    protected void initViews() {
        if (getLayoutId() != 0)
            setContentView(getLayoutId());

    }

    protected void initData() {
    }

    protected void initListener() {

    }

    public Toast toast(CharSequence toast) {
//        @SuppressLint("ShowToast") Toast t = Toast.makeText(this, toast, Toast.LENGTH_SHORT);
        if (mToast == null) {
            mToast = Toast.makeText(this, toast, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(toast);
        }
        mToast.show();
        return mToast;
    }

    public void snake(CharSequence msg) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show();
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

    protected void openActivity_(Class<?> cls) {
        hideSoftKeyboard();
        Intent i = new Intent(mContext, cls);
        startActivity(i);
    }

    protected void openActivity(Class<?> cls, Bundle bundle) {
        Intent i = new Intent(mContext, cls);
        i.putExtras(bundle);
        startActivity(i);
    }

    protected void openActivityForResult(Class<?> cls, int requestCode) {
        Intent i = new Intent(mContext, cls);
        startActivityForResult(i, requestCode);
    }

    protected void openActivityForResult(Class<?> cls, int requestCode, Bundle bundle) {
        Intent i = new Intent(mContext, cls);
        i.putExtras(bundle);
        startActivityForResult(i, requestCode);
    }

    public void openActivity(Class<? extends Activity> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    public void showToast(CharSequence c) {
        ToastUtils.showShort(c);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
           /* if (KeyBoardUtils.isShouldHideInput(view, ev)) {
                KeyBoardUtils.hideSoftInput(this, view.getWindowToken());
            }*/
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setLoading(boolean isLoading) {
        try {
            if (isLoading) {
                if (waitDialog == null || !waitDialog.isShowing()) {
                    waitDialog = new ProgressDialog(BaseActivity.this, R.style.MyDialogStyleBottom);
                    waitDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    waitDialog.setCanceledOnTouchOutside(false);
                    ImageView view = new ImageView(BaseActivity.this);
                    view.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    Animation loadAnimation = AnimationUtils.loadAnimation(
                            BaseActivity.this, R.anim.rotate);
                    view.startAnimation(loadAnimation);
                    loadAnimation.start();
                    view.setImageResource(R.mipmap.loading);
                    waitDialog.show();

                    waitDialog.setContentView(view);
                }
            } else {
                if (waitDialog != null && waitDialog.isShowing()) {
                    waitDialog.dismiss();
                    waitDialog = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Called in Android UI's main thread
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessage(String event) {
//        if (event.equals("login")) {
//            finish();
//        }
//    }


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
            // window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
