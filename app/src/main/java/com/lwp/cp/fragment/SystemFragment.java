package com.lwp.cp.fragment;

import com.lwp.cp.R;
import com.lwp.cp.base.BaseFragment;
import com.lwp.cp.presenter.SystemPresenter;
import com.lwp.cp.view.SystemView;

/**
 * liwenpeng
 * 2018/7/11 11:34
 * PlayAndroid
 * Descrobe:
 */
public class SystemFragment extends BaseFragment<SystemPresenter> implements SystemView{
    @Override
    protected SystemPresenter createPresenter() {
        return new SystemPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_system;
    }
}
