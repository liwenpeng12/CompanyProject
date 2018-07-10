package com.lwp.cp.listener;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

/**
 * liwenpeng
 * 2018/7/1 21:07
 * CompanyProject
 * Descrobe:引导界面的pagerchanedlistener
 */
public class GuidePagerChangedListener implements ViewPager.OnPageChangeListener {

    private Button mButton;
    private Context mContext;

    public GuidePagerChangedListener(Button enter) {
        mButton = enter;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 2){
            mButton.setVisibility(View.VISIBLE);
        }else {
            mButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
