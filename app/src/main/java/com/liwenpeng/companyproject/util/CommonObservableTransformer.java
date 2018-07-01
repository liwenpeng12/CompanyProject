package com.liwenpeng.companyproject.util;

import com.liwenpeng.companyproject.base.BaseBean;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * liwenpeng
 * 2018/7/1 21:42
 * CompanyProject
 * Descrobe:统一线程处理
 */
public class CommonObservableTransformer<T extends BaseBean<T>> implements ObservableTransformer {
    @Override
    public ObservableSource<BaseBean<T>> apply(Observable upstream) {
        Observable observable = upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
}
