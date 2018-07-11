package com.lwp.cp.fragment;

import com.lwp.cp.R;
import com.lwp.cp.base.BaseFragment;
import com.lwp.cp.presenter.NavigationPresenter;
import com.lwp.cp.view.INavigationView;

/**
 * liwenpeng
 * 2018/7/11 11:41
 * PlayAndroid
 * Descrobe:
 */
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements INavigationView{
    @Override
    protected NavigationPresenter createPresenter() {
        return new NavigationPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_navgation;
    }
}
