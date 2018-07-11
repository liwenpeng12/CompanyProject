package com.lwp.cp.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lwp.cp.R;
import com.lwp.cp.base.BaseApplication;
import com.lwp.cp.model.response.HomeResponseBean;

import java.util.List;

/**
 * liwenpeng
 * 2018/7/11 8:55
 * PlayAndroid
 * Descrobe:
 */
public class HomeRecycleviewAdapter extends BaseQuickAdapter<HomeResponseBean.DataBean.DatasBean,BaseViewHolder> {

    public HomeRecycleviewAdapter(int layoutResId, @Nullable List<HomeResponseBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeResponseBean.DataBean.DatasBean item) {
        helper.setText(R.id.tv_rv_title,item.getTitle());
        helper.setText(R.id.tv_rv_author,item.getAuthor());
        helper.setText(R.id.tv_rv_date,item.getNiceDate());
        helper.setText(R.id.tv_rv_des,item.getDesc());
        ImageView view = helper.getView(R.id.iv_rv_pic);
//        helper.setImageResource(R.id.iv_rv_pic,item.getEnvelopePic())
    //    Glide.with(BaseApplication.getmContext()).load(item.getEnvelopePic()).into(view);



    }

}
