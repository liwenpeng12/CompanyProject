package com.lwp.cp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * liwenpeng
 * 2018/7/10 23:53
 * PlayAndroid
 * Descrobe:
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public MainViewPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

}
