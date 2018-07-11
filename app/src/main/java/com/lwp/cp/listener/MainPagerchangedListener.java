package com.lwp.cp.listener;

import android.support.v4.view.ViewPager;

/**
 * liwenpeng
 * 2018/7/11 11:53
 * PlayAndroid
 * Descrobe:
 */
public class MainPagerchangedListener implements ViewPager.OnPageChangeListener {


    private ViewPager mViewpager;

    public MainPagerchangedListener(ViewPager mViewpager) {
        this.mViewpager = mViewpager;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mViewpager.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
