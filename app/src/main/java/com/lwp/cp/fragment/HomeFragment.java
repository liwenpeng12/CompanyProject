package com.lwp.cp.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.classic.common.MultipleStatusView;
import com.lwp.cp.R;
import com.lwp.cp.adapter.HomeRecycleviewAdapter;
import com.lwp.cp.base.BaseBean;
import com.lwp.cp.base.BaseFragment;
import com.lwp.cp.model.response.BannerResponse;
import com.lwp.cp.model.response.HomeResponseBean;
import com.lwp.cp.presenter.HomePresenter;
import com.lwp.cp.util.Constant;
import com.lwp.cp.util.GlideImageLoader;
import com.lwp.cp.view.HomeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

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
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;
@BindView(R.id.rv_home)
    RecyclerView mRecyclerView;
@BindView(R.id.tw_refresh)
SmartRefreshLayout mTwinklingRefreshLayout;
int page= 0;



    private List<String> mImgList = new ArrayList<>();
    private View mHeadView;
    private View mBottomView;


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
        mTwinklingRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getPresenter().refreshData();

                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        mTwinklingRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {

                getPresenter().loadMoreData(page++);
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
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
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(mImgList);
        banner.start();


    }

    @Override
    public void onGetArticalSuccess(HomeResponseBean responseList) {
        int size = responseList.getData().getDatas().size();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HomeRecycleviewAdapter adapter = new HomeRecycleviewAdapter(R.layout.home_rv_adapter, responseList.getData().getDatas());
        mHeadView = View.inflate(getActivity(), R.layout.home_header_view, null);
        mBottomView = View.inflate(getActivity(), R.layout.home_bottom_view, null);
        ImageButton imageButton = mHeadView.findViewById(R.id.ib_home_change);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RvTypeChanged();
            }
        });
        adapter.addHeaderView(mHeadView);
        adapter.addFooterView(mBottomView);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

    }

    @Override
    public LifecycleProvider getActivityLifeCycle() {
        return null;
    }

    public void RvTypeChanged(){
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? 2 : 1;
            }
        });
        mRecyclerView.setLayoutManager(layoutManager);
    }

}
