package com.lwp.cp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * liwenpeng
 * 2018/7/1 20:55
 * CompanyProject
 * Descrobe:引导页viewpager的适配器
 */
public class GuideVpAdapter extends PagerAdapter {

    private int[] mImgRes ;
    private Context mContext;

    public GuideVpAdapter(int[] imgRes, Context context) {
        mImgRes = imgRes;
        mContext = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView view = new ImageView(mContext);
        view.setBackgroundResource(mImgRes[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mImgRes.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
