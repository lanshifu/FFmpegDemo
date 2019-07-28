package com.tomatolive.library.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseFragment;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.model.event.AttentionEvent;
import com.tomatolive.library.model.event.BaseEvent;
import com.tomatolive.library.model.event.LoginEvent;
import com.tomatolive.library.model.event.LogoutEvent;
import com.tomatolive.library.ui.adapter.HomeLiveAdapter;
import com.tomatolive.library.ui.adapter.RecommendAdapter;
import com.tomatolive.library.ui.presenter.HomeAttentionPresenter;
import com.tomatolive.library.ui.view.divider.RVDividerLive;
import com.tomatolive.library.ui.view.divider.RVDividerRecommendGrid;
import com.tomatolive.library.ui.view.emptyview.AttentionEmptyView;
import com.tomatolive.library.ui.view.emptyview.RecyclerEmptyView;
import com.tomatolive.library.ui.view.headview.RecommendHeadView;
import com.tomatolive.library.ui.view.iview.IHomeAttentionView;
import com.tomatolive.library.utils.b;
import defpackage.ra;
import java.util.List;

public class HomeAttentionFragment extends BaseFragment<HomeAttentionPresenter> implements IHomeAttentionView {
    private AttentionEmptyView mAttentionEmptyView;
    private HomeLiveAdapter mOpenAdapter;
    private RecommendAdapter mRecommendAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewRecommend;
    private SmartRefreshLayout mSmartRefreshLayout;

    public void onAttentionSuccess() {
    }

    public void onResultError(int i) {
    }

    public static HomeAttentionFragment newInstance() {
        Bundle bundle = new Bundle();
        HomeAttentionFragment homeAttentionFragment = new HomeAttentionFragment();
        homeAttentionFragment.setArguments(bundle);
        return homeAttentionFragment;
    }

    /* Access modifiers changed, original: protected */
    public HomeAttentionPresenter createPresenter() {
        return new HomeAttentionPresenter(this.mContext, this);
    }

    public int getLayoutId() {
        return R.layout.fq_fragment_home_attention;
    }

    /* Access modifiers changed, original: protected */
    public View injectStateView(View view) {
        return view.findViewById(R.id.fl_content_view);
    }

    public void initView(View view, @Nullable Bundle bundle) {
        this.mSmartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.mRecyclerViewRecommend = (RecyclerView) view.findViewById(R.id.recycler_view_recommend);
        initOpenAdapter();
        initRecommendAdapter();
        sendRequest(true, true);
    }

    private void initOpenAdapter() {
        this.mOpenAdapter = new HomeLiveAdapter(this, R.layout.fq_item_list_live_view_new);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 2));
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.addItemDecoration(new RVDividerLive(this.mContext, R.color.fq_colorWhite));
        this.mRecyclerView.setAdapter(this.mOpenAdapter);
        this.mOpenAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mOpenAdapter.setEmptyView(new RecyclerEmptyView(this.mContext));
    }

    private void initRecommendAdapter() {
        this.mAttentionEmptyView = new AttentionEmptyView(this.mContext);
        this.mRecyclerViewRecommend.setLayoutManager(new GridLayoutManager(this.mContext, 3));
        this.mRecyclerViewRecommend.addItemDecoration(new RVDividerRecommendGrid(this.mContext, R.color.fq_colorWhite));
        this.mRecommendAdapter = new RecommendAdapter(R.layout.fq_item_list_recommend);
        this.mRecyclerViewRecommend.setAdapter(this.mRecommendAdapter);
        this.mRecommendAdapter.bindToRecyclerView(this.mRecyclerViewRecommend);
        this.mRecommendAdapter.setEmptyView(this.mAttentionEmptyView);
        this.mRecommendAdapter.addHeaderView(new RecommendHeadView(this.mContext));
    }

    private void sendRequest(boolean z, boolean z2) {
        if (b.f()) {
            ((HomeAttentionPresenter) this.mPresenter).getAttentionAnchorListList(this.mStateView, this.pageNum, z, z2);
        } else {
            ((HomeAttentionPresenter) this.mPresenter).getRecommendAnchorList(this.mStateView, z);
        }
    }

    public void initListener() {
        super.initListener();
        this.mStateView.setOnRetryClickListener(new -$$Lambda$HomeAttentionFragment$4WUvtwgX_AONLfsIgrjgfVfmP5E(this));
        this.mSmartRefreshLayout.a(new -$$Lambda$HomeAttentionFragment$ZbakIF9z9KmPnVwaI0_op9_XluY(this)).a(new -$$Lambda$HomeAttentionFragment$KTPTfxqIe55CxbHezwRoXtNJeH8(this));
        this.mOpenAdapter.setOnItemClickListener(new -$$Lambda$HomeAttentionFragment$rs4IRIzZbLgT1xf1iJkJfln-myg(this));
        this.mRecommendAdapter.setOnItemChildClickListener(new -$$Lambda$HomeAttentionFragment$Zg5jmkvnM_wTZFEt6EDJ0P4y59Q(this));
        this.mRecommendAdapter.setOnItemClickListener(new -$$Lambda$HomeAttentionFragment$GeExGWI_6Lgc5hV7fwH1_t4t-Pc(this));
    }

    public static /* synthetic */ void lambda$initListener$0(HomeAttentionFragment homeAttentionFragment) {
        homeAttentionFragment.pageNum = 1;
        homeAttentionFragment.sendRequest(true, true);
    }

    public static /* synthetic */ void lambda$initListener$1(HomeAttentionFragment homeAttentionFragment, ra raVar) {
        homeAttentionFragment.pageNum = 1;
        homeAttentionFragment.sendRequest(false, true);
        homeAttentionFragment.mSmartRefreshLayout.g();
    }

    public static /* synthetic */ void lambda$initListener$2(HomeAttentionFragment homeAttentionFragment, ra raVar) {
        homeAttentionFragment.pageNum++;
        homeAttentionFragment.sendRequest(false, false);
        homeAttentionFragment.mSmartRefreshLayout.h();
    }

    public static /* synthetic */ void lambda$initListener$3(HomeAttentionFragment homeAttentionFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        LiveEntity liveEntity = (LiveEntity) baseQuickAdapter.getItem(i);
        if (b.a(homeAttentionFragment.mContext)) {
            b.a(homeAttentionFragment.mContext, 1, liveEntity);
        }
    }

    public static /* synthetic */ void lambda$initListener$4(HomeAttentionFragment homeAttentionFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (view.getId() == R.id.tv_attention) {
            AnchorEntity anchorEntity = (AnchorEntity) baseQuickAdapter.getItem(i);
            if (anchorEntity != null && b.a(homeAttentionFragment.mContext, anchorEntity.anchor_id)) {
                int i2 = 1;
                i = view.isSelected() ^ 1;
                view.setSelected(i);
                homeAttentionFragment.showToast(i != 0 ? R.string.fq_text_attention_success : R.string.fq_text_attention_cancel_success);
                HomeAttentionPresenter homeAttentionPresenter = (HomeAttentionPresenter) homeAttentionFragment.mPresenter;
                String str = anchorEntity.anchor_id;
                if (i == 0) {
                    i2 = 0;
                }
                homeAttentionPresenter.attentionAnchor(str, i2);
            }
        }
    }

    public static /* synthetic */ void lambda$initListener$5(HomeAttentionFragment homeAttentionFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        AnchorEntity anchorEntity = (AnchorEntity) baseQuickAdapter.getItem(i);
        if (anchorEntity != null && b.a(homeAttentionFragment.mContext)) {
            b.a(homeAttentionFragment.mContext, 1, b.a(anchorEntity));
        }
    }

    public void onAttentionListSuccess(List<LiveEntity> list, boolean z, boolean z2) {
        if (list == null || list.size() == 0) {
            this.mRecyclerView.setVisibility(4);
            this.mRecyclerViewRecommend.setVisibility(0);
            ((HomeAttentionPresenter) this.mPresenter).getRecommendAnchorList(this.mStateView, false);
            return;
        }
        this.mRecyclerView.setVisibility(0);
        this.mRecyclerViewRecommend.setVisibility(4);
        this.mSmartRefreshLayout.b(true);
        if (z2) {
            this.mOpenAdapter.setNewData(list);
        } else {
            this.mOpenAdapter.addData(list);
        }
        if (z) {
            this.mSmartRefreshLayout.i();
        }
    }

    public void onAttentionListFail() {
        ((HomeAttentionPresenter) this.mPresenter).getRecommendAnchorList(this.mStateView, false);
    }

    public void onRecommendListSuccess(List<AnchorEntity> list) {
        this.mRecyclerView.setVisibility(4);
        int i = 0;
        this.mRecyclerViewRecommend.setVisibility(0);
        this.mSmartRefreshLayout.b(false);
        if (list != null) {
            if (list.size() > 0) {
                i = 1;
            }
            this.mAttentionEmptyView.hideRecommendTextView(i ^ 1);
            this.mRecommendAdapter.setNewData(list);
        }
    }

    public void onRecommendListFail() {
        this.mRecyclerView.setVisibility(4);
        this.mRecyclerViewRecommend.setVisibility(0);
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if (baseEvent instanceof AttentionEvent) {
            this.pageNum = 1;
            ((HomeAttentionPresenter) this.mPresenter).getAttentionAnchorListList(this.mStateView, this.pageNum, true, true);
        } else if (baseEvent instanceof LoginEvent) {
            this.pageNum = 1;
            ((HomeAttentionPresenter) this.mPresenter).getAttentionAnchorListList(this.mStateView, this.pageNum, true, true);
        } else {
            if (baseEvent instanceof LogoutEvent) {
                this.mRecyclerView.setVisibility(4);
                this.mRecyclerViewRecommend.setVisibility(0);
                this.mSmartRefreshLayout.b(false);
                this.pageNum = 1;
                ((HomeAttentionPresenter) this.mPresenter).getRecommendAnchorList(this.mStateView, true);
            }
        }
    }
}
