package com.liwenpeng.companyproject.activity;

import android.content.Intent;
import android.database.Observable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.liwenpeng.companyproject.R;
import com.liwenpeng.companyproject.base.BaseActivity;
import com.liwenpeng.companyproject.base.BasePresenter;
import com.liwenpeng.companyproject.base.BaseView;
import com.liwenpeng.companyproject.util.SpUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * liwenpeng
 * 2018/6/30 22:40
 * CompanyProject
 * Descrobe:闪屏页
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.splash_iv)
    ImageView splashIv;
    @BindView(R.id.splash_ib)
    TextView splashIb;
    private Handler mHandler;

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        postDelay();

        try {
            timeLimit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 倒计时
     */
    private void timeLimit() {
        Disposable subscribe = Flowable.intervalRange(2, 3, 0, 1, TimeUnit.SECONDS).
                compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY)).
                map(new Function<Long, Integer>() {

                    @Override
                    public Integer apply(Long aLong) throws Exception {
                        int value = aLong.intValue();
                        int i = 4 - value;
                        return i;
                    }
                }).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogD("正在接受:" + integer);
            }
        }).compose(this.<Integer>bindToLifecycle()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer aLong) throws Exception {
                        RxTextView.text(splashIb).accept("倒计 " + aLong + "s");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        try {
                            throw throwable;
                        } catch (Throwable throwable1) {
                            throwable1.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 延迟进入界面操作
     */
    private void postDelay() {
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LogD("当前线程：" + Thread.currentThread().getName());
                if (SpUtil.getBoolean(SplashActivity.this, "first", true)) {
                    //    finish();
                    toGuideActivity();
                    finish();
                } else {
                    // finish();
                    toMainActivity();
                    finish();
                }

            }
        }, 3000);
    }

    private void toGuideActivity() {
        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
        SpUtil.setBoolean(SplashActivity.this, "first", false);
    }

    private void toMainActivity() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
