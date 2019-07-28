package com.tomatolive.library.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import com.blankj.utilcode.util.k;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.melnykov.fab.FloatingActionButton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseFragment;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.BannerEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.model.event.LabelMenuEvent;
import com.tomatolive.library.ui.activity.home.AnchorAuthResultActivity;
import com.tomatolive.library.ui.activity.home.WebViewActivity;
import com.tomatolive.library.ui.activity.live.PrepareLiveActivity;
import com.tomatolive.library.ui.adapter.HomeLiveAdapter;
import com.tomatolive.library.ui.presenter.HomeHotPresenter;
import com.tomatolive.library.ui.view.dialog.AnchorAuthDialog;
import com.tomatolive.library.ui.view.dialog.WarnDialog;
import com.tomatolive.library.ui.view.divider.RVDividerLive;
import com.tomatolive.library.ui.view.headview.HomeHotHeadView;
import com.tomatolive.library.ui.view.iview.IHomeHotView;
import com.tomatolive.library.utils.a;
import com.tomatolive.library.utils.b;
import defpackage.mh;
import defpackage.ra;
import defpackage.sh;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.c;
import tv.danmaku.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener;

public class HomeHotFragment extends BaseFragment<HomeHotPresenter> implements IHomeHotView {
    private FloatingActionButton fab;
    private HomeHotHeadView headView;
    private HomeLiveAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;

    public void onBannerListFail() {
    }

    public void onLiveListFail() {
    }

    public void onResultError(int i) {
    }

    public void onTopListFail() {
    }

    public static HomeHotFragment newInstance() {
        Bundle bundle = new Bundle();
        HomeHotFragment homeHotFragment = new HomeHotFragment();
        homeHotFragment.setArguments(bundle);
        return homeHotFragment;
    }

    /* Access modifiers changed, original: protected */
    public HomeHotPresenter createPresenter() {
        return new HomeHotPresenter(this.mContext, this);
    }

    public int getLayoutId() {
        return R.layout.fq_fragment_home_hot;
    }

    /* Access modifiers changed, original: protected */
    public View injectStateView(View view) {
        return view.findViewById(R.id.fl_content_view);
    }

    public void initView(View view, @Nullable Bundle bundle) {
        this.mSmartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.fab = (FloatingActionButton) view.findViewById(R.id.fab);
        initAdapter();
        if (k.a().b("labelMenu", true)) {
            sendRequest(true);
        }
    }

    private void initAdapter() {
        ((DefaultItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.headView = new HomeHotHeadView(this.mContext);
        this.mAdapter = new HomeLiveAdapter(this, R.layout.fq_item_list_live_view_new);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 2));
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.addItemDecoration(new RVDividerLive(this.mContext, R.color.fq_colorWhite, true, true));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.addHeaderView(this.headView);
        this.mAdapter.setEmptyView(R.layout.fq_layout_empty_view_warp, this.mRecyclerView);
        this.mAdapter.setHeaderAndEmpty(true);
        this.fab.a(this.mRecyclerView);
        a.b(this.fab);
    }

    public void initListener() {
        super.initListener();
        this.mStateView.setOnRetryClickListener(new -$$Lambda$HomeHotFragment$PL7oyOTnVmToU_uHnYIWcqC6qoE(this));
        this.mSmartRefreshLayout.a(new -$$Lambda$HomeHotFragment$9_oiZnaXV5wzEKWYiYTGZ9Vxlxw(this)).a(new -$$Lambda$HomeHotFragment$u5V1NC_rSbP3nv76UeXY1NRvp50(this));
        mh.a(this.fab).throttleFirst(2, TimeUnit.SECONDS).subscribe(new sh<Object>() {
            public void accept(Object obj) {
                if (b.a(HomeHotFragment.this.mContext)) {
                    ((HomeHotPresenter) HomeHotFragment.this.mPresenter).onAnchorAuth();
                }
            }
        });
        this.mAdapter.setOnItemClickListener(new -$$Lambda$HomeHotFragment$FYtBy0GnARSunIUXJWos-DqoeF8(this));
        this.headView.initBannerClickListener(new -$$Lambda$HomeHotFragment$Q86_CptUl2zLAJE0z9_r9syxR7Y(this));
    }

    public static /* synthetic */ void lambda$initListener$0(HomeHotFragment homeHotFragment) {
        if (k.a().b("labelMenu", true)) {
            homeHotFragment.sendRequest(true);
        } else {
            c.a().c(new LabelMenuEvent());
        }
    }

    public static /* synthetic */ void lambda$initListener$1(HomeHotFragment homeHotFragment, ra raVar) {
        if (k.a().b("labelMenu", true)) {
            homeHotFragment.sendRequest(false);
            homeHotFragment.mSmartRefreshLayout.g();
            return;
        }
        c.a().c(new LabelMenuEvent());
        homeHotFragment.mSmartRefreshLayout.g();
    }

    public static /* synthetic */ void lambda$initListener$2(HomeHotFragment homeHotFragment, ra raVar) {
        homeHotFragment.pageNum++;
        ((HomeHotPresenter) homeHotFragment.mPresenter).getLiveList(homeHotFragment.mStateView, homeHotFragment.pageNum, false, false);
        homeHotFragment.mSmartRefreshLayout.h();
    }

    public static /* synthetic */ void lambda$initListener$3(HomeHotFragment homeHotFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        LiveEntity liveEntity = (LiveEntity) baseQuickAdapter.getItem(i);
        if (b.a(homeHotFragment.mContext)) {
            b.a(homeHotFragment.mContext, 1, liveEntity);
        }
    }

    public static /* synthetic */ void lambda$initListener$4(HomeHotFragment homeHotFragment, BannerEntity bannerEntity) {
        if (bannerEntity != null) {
            com.tomatolive.library.a.a().a(homeHotFragment.mContext, new RequestParams().getBannerStatisticsParams(bannerEntity.id, bannerEntity.name));
            if (TextUtils.equals("1", bannerEntity.method)) {
                LiveEntity formatLiveEntity = homeHotFragment.formatLiveEntity(bannerEntity);
                if (b.a(homeHotFragment.mContext)) {
                    b.a(homeHotFragment.mContext, 1, formatLiveEntity);
                } else {
                    return;
                }
            }
            Intent intent = new Intent(homeHotFragment.getActivity(), WebViewActivity.class);
            intent.putExtra(OnNativeInvokeListener.ARG_URL, bannerEntity.url);
            intent.putExtra("title", bannerEntity.name);
            homeHotFragment.startActivity(intent);
        }
    }

    private void sendRequest(boolean z) {
        this.pageNum = 1;
        ((HomeHotPresenter) this.mPresenter).getLiveList(this.mStateView, this.pageNum, z, true);
        ((HomeHotPresenter) this.mPresenter).getBannerList("1");
        ((HomeHotPresenter) this.mPresenter).getTopList();
    }

    public void onAnchorAuthSuccess(AnchorEntity anchorEntity) {
        if (anchorEntity != null) {
            switch (anchorEntity.isChecked) {
                case -2:
                    AnchorAuthDialog.newInstance().show(getChildFragmentManager());
                    break;
                case -1:
                case 0:
                    Intent intent = new Intent(this.mContext, AnchorAuthResultActivity.class);
                    intent.putExtra("authType", anchorEntity.isChecked);
                    startActivity(intent);
                    break;
                case 1:
                    if (anchorEntity.isFrozen != 1) {
                        startActivity(PrepareLiveActivity.class);
                        break;
                    } else {
                        WarnDialog.newInstance(WarnDialog.FROZEN_TIP).show(getChildFragmentManager());
                        break;
                    }
            }
        }
    }

    public void onLiveListSuccess(List<LiveEntity> list, boolean z, boolean z2) {
        if (z2) {
            this.mAdapter.setNewData(list);
        } else {
            this.mAdapter.addData(list);
        }
        if (z) {
            this.mSmartRefreshLayout.i();
        }
    }

    public void onBannerListSuccess(List<BannerEntity> list) {
        if (this.headView != null) {
            this.headView.initBannerImages(list);
        }
    }

    public void onTopListSuccess(List<AnchorEntity> list) {
        if (this.headView != null) {
            this.headView.initTopList(list);
        }
    }

    private LiveEntity formatLiveEntity(BannerEntity bannerEntity) {
        LiveEntity liveEntity = new LiveEntity();
        if (bannerEntity == null) {
            return null;
        }
        liveEntity.liveId = bannerEntity.url;
        return liveEntity;
    }
}
