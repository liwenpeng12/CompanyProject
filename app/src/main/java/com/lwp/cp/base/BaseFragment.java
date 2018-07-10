package com.lwp.cp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lwp.cp.util.ClickUtils;
import com.lwp.cp.util.ToastUtils;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * liwenpeng
 * 2018/6/30 17:16
 * CompanyProject
 * Descrobe:fragment的封装
 */
public abstract class BaseFragment<P extends BasePresenter> extends RxFragment{

    private P mPresenter;
    private Context mContext;
    public  final String CurrentTAG = getClass().getSimpleName();
    private boolean isShow = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext  = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isShow = true;
        View view = inflater.inflate(getLayoutId(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getUserVisibleHint()){
            initData();
        }
    }

    /**
     * 数据初始化
     * */
      void initData(){};

    /**
     * 布局视图
     * */
    public abstract int getLayoutId();

    /**
     * 吐司
     * */
    public void showToast(String msg){
        ToastUtils.showShort(msg);
    }

    public void LogD(String msg){
        Log.d(CurrentTAG, msg);
    }

    public void LogE(String msg){
        Log.e(CurrentTAG, msg);
    }

    /**
     * 是否快速点击
     * 防止过快点击造成多次事件执行
     * */
    public Boolean IsFastClick(){
        return ClickUtils.isFastClick();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }
}
