package com.lwp.cp.fragment;

import com.lwp.cp.R;
import com.lwp.cp.base.BaseFragment;
import com.lwp.cp.presenter.SettingsPresenter;
import com.lwp.cp.view.SettingsView;

/**
 * liwenpeng
 * 2018/7/11 11:46
 * PlayAndroid
 * Descrobe:
 */
public class SettingsFragment extends BaseFragment<SettingsPresenter> implements SettingsView {
    @Override
    protected SettingsPresenter createPresenter() {
        return new SettingsPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_settings;
    }
}
