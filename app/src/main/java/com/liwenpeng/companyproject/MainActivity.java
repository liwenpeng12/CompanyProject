package com.liwenpeng.companyproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.liwenpeng.companyproject.base.BaseActivity;

import java.util.Observable;

import io.reactivex.Flowable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {

        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        io.reactivex.Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
          //      setLoading(true);
                Log.d("lwp","before sleep");
//                Thread.sleep(2000);
                Log.d("lwp","after sleep");
                emitter.onNext(1);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).doOnNext(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("lwp","doonnext");
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("lwp","main");
             //   setLoading(false);
            }
        });
    }
}
