package com.lwp.cp.base;

/**
 * liwenpeng
 * 2018/6/30 13:27
 * CompanyProject
 * Descrobe:Presenter基类
 */
public abstract class BasePresenter<V> {
   private V mView;

    public V getView() {
        return mView;
    }

    public void attachView(V v) {
        this.mView = v;
    }

    public void deTachView(){
        mView = null;
    }



    protected abstract void initData();

}
