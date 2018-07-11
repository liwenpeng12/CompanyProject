package com.lwp.cp.fragment;

import com.lwp.cp.R;
import com.lwp.cp.base.BaseFragment;
import com.lwp.cp.presenter.ProjectPresenter;
import com.lwp.cp.view.ProjectView;

/**
 * liwenpeng
 * 2018/7/11 11:43
 * PlayAndroid
 * Descrobe:
 */
public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectView{
    @Override
    protected ProjectPresenter createPresenter() {
        return new ProjectPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_project;
    }
}
