package com.liwenpeng.companyproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.liwenpeng.companyproject.R;
import com.liwenpeng.companyproject.adapter.GuideVpAdapter;
import com.liwenpeng.companyproject.base.BaseActivity;
import com.liwenpeng.companyproject.base.BasePresenter;
import com.liwenpeng.companyproject.base.BaseView;
import com.liwenpeng.companyproject.listener.GuidePagerChangedListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

/**
 * liwenpeng
 * 2018/6/30 22:53
 * CompanyProject
 * Descrobe:引导页
 * RxView.clicks(btnLogin)
 * .throttleFirst(2, TimeUnit.SECONDS)
 * .subscribe(new Consumer<Object>() {
 *
 * @Override public void accept(Object o) throws Exception {
 * Toast.makeText(MainActivity.this, "11111111111", Toast.LENGTH_LONG).show();
 * }
 * });
 */
public class GuideActivity extends BaseActivity {
    @BindView(R.id.guide_vp)
    ViewPager guideVp;
    @BindView(R.id.guide_enter)
    Button guideEnter;
    @BindView(R.id.guide_rl)
    RelativeLayout guideRl;
    @BindView(R.id.guide_indicator)
    CircleIndicator guideIndicator;

    /**
     * 图片Id
     */
    private int[] imgRes = new int[]{R.mipmap.page_01, R.mipmap.page_02, R.mipmap.page_03};

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        guideVp.setAdapter(new GuideVpAdapter(imgRes, this));
        guideIndicator.setViewPager(guideVp);
        guideVp.setOnPageChangeListener(new GuidePagerChangedListener(guideEnter));


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }


    @OnClick(R.id.guide_enter)
    public void onViewClicked() {
        enterMainActivity();
    }

    private void enterMainActivity() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
