package com.liwenpeng.companyproject.base;

/**
 * liwenpeng
 * 2018/6/30 13:27
 * CompanyProject
 * Descrobe:Presenter基类
 */
public abstract class BasePresenter<V extends BaseView> {
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
}
