package com.lwp.cp.http;

import com.lwp.cp.base.BaseBean;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * liwenpeng
 * 2018/7/2 23:32
 * CompanyProject
 * Descrobe:
 */
public class ApiTransformer<R extends BaseBean<T>,T> implements ObservableTransformer<R,T> {
    private LifecycleProvider provider;

    public ApiTransformer(LifecycleProvider provider) {
        this.provider = provider;
    }

    @Override
    public ObservableSource<T> apply(Observable<R> upstream) {
        Observable compose = upstream.flatMap(new Function<R, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(R r) throws Exception {
                return null;
            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                compose(provider.<T>bindUntilEvent(ActivityEvent.DESTROY));
        return compose;
    }


}
