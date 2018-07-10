package com.lwp.cp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.classic.common.MultipleStatusView;
import com.lwp.cp.R;
import com.lwp.cp.base.BaseBean;
import com.lwp.cp.base.BaseFragment;
import com.lwp.cp.model.response.BannerResponse;
import com.lwp.cp.model.response.HomeResponseBean;
import com.lwp.cp.presenter.HomePresenter;
import com.lwp.cp.util.Constant;
import com.lwp.cp.util.GlideImageLoader;
import com.lwp.cp.view.HomeView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * liwenpeng
 * 2018/7/10 19:34
 * CompanyProject
 * Descrobe:
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.fl_home)
    FrameLayout flHome;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;
    Unbinder unbinder;


//    @BindView(R.id.multiple_status_view)
//    MultipleStatusView multipleStatusView;

    private List<String> mImgList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void onGetting(int type) {
        if (type == Constant.LOADINGVIEW){
            multipleStatusView.showLoading();
        }
    }

    @Override
    public void onGetFailed(int type) {
        if (type == Constant.EMPTYVIEW){
            multipleStatusView.showEmpty();
        }
        if (type == Constant.NONETVIEW){
            multipleStatusView.showNoNetwork();
        }
        if (type == Constant.ERRORVIEW){
            multipleStatusView.showError();
        }
    }

    @Override
    public void onGetBannerSuccess(BannerResponse responseList) {
            multipleStatusView.showContent();
        int size = responseList.getData().size();
        for (int i = 0; i < size; i++) {
            mImgList.add(responseList.getData().get(i).getImagePath());
        }
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(mImgList);
        banner.start();


    }

    @Override
    public void onGetArticalSuccess(BaseBean<HomeResponseBean> responseList) {

    }

    @Override
    public LifecycleProvider getActivityLifeCycle() {
        return null;
    }

}
