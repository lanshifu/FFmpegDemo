package com.tomatolive.library.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseFragment;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.model.event.BaseEvent;
import com.tomatolive.library.model.event.SearchEvent;
import com.tomatolive.library.ui.adapter.HomeLiveAdapter;
import com.tomatolive.library.ui.presenter.SearchAllPresenter;
import com.tomatolive.library.ui.view.divider.RVDividerLive;
import com.tomatolive.library.ui.view.emptyview.RecyclerEmptyView;
import com.tomatolive.library.ui.view.headview.SearchAllHeadView;
import com.tomatolive.library.ui.view.iview.ISearchAllView;
import com.tomatolive.library.utils.b;
import defpackage.ra;
import java.util.List;

public class SearchAllFragment extends BaseFragment<SearchAllPresenter> implements ISearchAllView {
    private String keyword = "";
    private HomeLiveAdapter mAdapter;
    private SearchAllHeadView mHeadView;
    private RecyclerEmptyView mRecyclerEmptyView;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private OnFragmentInteractionListener onFragmentInteractionListener;

    public interface OnFragmentInteractionListener {
        void onPagerSelectedListener(int i);
    }

    public void onResultError(int i) {
    }

    public static SearchAllFragment newInstance() {
        Bundle bundle = new Bundle();
        SearchAllFragment searchAllFragment = new SearchAllFragment();
        searchAllFragment.setArguments(bundle);
        return searchAllFragment;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            this.onFragmentInteractionListener = (OnFragmentInteractionListener) context;
        }
    }

    /* Access modifiers changed, original: protected */
    public SearchAllPresenter createPresenter() {
        return new SearchAllPresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public View injectStateView(View view) {
        return view.findViewById(R.id.fl_content_view);
    }

    public int getLayoutId() {
        return R.layout.fq_fragment_search_all;
    }

    public void initView(View view, @Nullable Bundle bundle) {
        this.mSmartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.mRecyclerEmptyView = (RecyclerEmptyView) view.findViewById(R.id.empty_view);
        this.mSmartRefreshLayout.c(false);
        this.mSmartRefreshLayout.b(false);
        initAdapter();
    }

    private void initAdapter() {
        ((DefaultItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.mHeadView = new SearchAllHeadView(this.mContext);
        this.mAdapter = new HomeLiveAdapter(this, R.layout.fq_item_list_live_view_new);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 2));
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.addItemDecoration(new RVDividerLive(this.mContext, R.color.fq_colorWhite, true, true));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.addHeaderView(this.mHeadView);
        this.mAdapter.setHeaderAndEmpty(true);
        this.mHeadView.findViewById(R.id.tv_anchor_all).setOnClickListener(new -$$Lambda$SearchAllFragment$npAHu-Bg9OO2xNjw1S6f1jDE42U(this));
        this.mHeadView.findViewById(R.id.tv_live_all).setOnClickListener(new -$$Lambda$SearchAllFragment$HLNUT4KQRfgiTKVGip4xwFAoZoY(this));
        this.mAdapter.setOnItemClickListener(new -$$Lambda$SearchAllFragment$NXWtz7mX8MFwU5-ton00q6r_V7s(this));
        this.mHeadView.setAnchorItemClickListener(new -$$Lambda$SearchAllFragment$SaF0nKzTV3HYqJr6UgOkrcxiGAE(this));
    }

    public static /* synthetic */ void lambda$initAdapter$0(SearchAllFragment searchAllFragment, View view) {
        if (searchAllFragment.onFragmentInteractionListener != null) {
            searchAllFragment.onFragmentInteractionListener.onPagerSelectedListener(1);
        }
    }

    public static /* synthetic */ void lambda$initAdapter$1(SearchAllFragment searchAllFragment, View view) {
        if (searchAllFragment.onFragmentInteractionListener != null) {
            searchAllFragment.onFragmentInteractionListener.onPagerSelectedListener(2);
        }
    }

    public static /* synthetic */ void lambda$initAdapter$2(SearchAllFragment searchAllFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        LiveEntity liveEntity = (LiveEntity) baseQuickAdapter.getItem(i);
        if (b.a(searchAllFragment.mContext)) {
            b.a(searchAllFragment.mContext, 2, liveEntity);
        }
    }

    public static /* synthetic */ void lambda$initAdapter$3(SearchAllFragment searchAllFragment, AnchorEntity anchorEntity, int i) {
        if (b.a(searchAllFragment.mContext)) {
            b.a(searchAllFragment.mContext, 2, b.a(anchorEntity));
        }
    }

    public void initListener() {
        super.initListener();
        this.mStateView.setOnRetryClickListener(new -$$Lambda$SearchAllFragment$BGfk2P3zj-fYBG6yBVT5h0Ts9GM(this));
        this.mSmartRefreshLayout.a(new -$$Lambda$SearchAllFragment$W-CpzBlr9AQRWPvjynDRPtV_5n4(this)).a(new -$$Lambda$SearchAllFragment$JZB59-lDBwHkFzTx7GmL9ZXDgjA(this));
    }

    public static /* synthetic */ void lambda$initListener$4(SearchAllFragment searchAllFragment) {
        if (!TextUtils.isEmpty(searchAllFragment.keyword)) {
            ((SearchAllPresenter) searchAllFragment.mPresenter).getAnchorList(searchAllFragment.keyword);
        }
    }

    public static /* synthetic */ void lambda$initListener$6(SearchAllFragment searchAllFragment, ra raVar) {
        searchAllFragment.pageNum++;
        ((SearchAllPresenter) searchAllFragment.mPresenter).getLiveList(searchAllFragment.mStateView, searchAllFragment.keyword, searchAllFragment.pageNum, false, false);
        searchAllFragment.mSmartRefreshLayout.h();
    }

    public void onEventMainThreadSticky(BaseEvent baseEvent) {
        super.onEventMainThreadSticky(baseEvent);
        if (baseEvent instanceof SearchEvent) {
            this.keyword = ((SearchEvent) baseEvent).keyword;
            if (this.mStateView != null) {
                this.mStateView.showLoading();
            }
            ((SearchAllPresenter) this.mPresenter).getAnchorList(this.keyword);
        }
    }

    public void onAnchorListSuccess(List<AnchorEntity> list) {
        if (list != null) {
            this.mHeadView.initData(list, this.keyword);
            this.mHeadView.setHideAnchorBg(list.size() == 0);
            this.pageNum = 1;
            ((SearchAllPresenter) this.mPresenter).getLiveList(this.mStateView, this.keyword, this.pageNum, true, true);
        }
    }

    public void onAnchorListFail() {
        this.pageNum = 1;
        ((SearchAllPresenter) this.mPresenter).getLiveList(this.mStateView, this.keyword, this.pageNum, true, true);
    }

    public void onLiveListSuccess(List<LiveEntity> list, boolean z, boolean z2) {
        int i = 0;
        if (z2) {
            this.mAdapter.setNewData(list);
            this.mHeadView.setHideLiveBg(list.size() == 0);
        } else {
            this.mAdapter.addData(list);
        }
        if (z) {
            this.mSmartRefreshLayout.i();
        }
        RecyclerEmptyView recyclerEmptyView = this.mRecyclerEmptyView;
        int i2 = (this.mHeadView.isAnchorListData() || !(list == null || list.size() == 0)) ? 4 : 0;
        recyclerEmptyView.setVisibility(i2);
        RecyclerView recyclerView = this.mRecyclerView;
        if (!this.mHeadView.isAnchorListData() && (list == null || list.size() <= 0)) {
            i = 4;
        }
        recyclerView.setVisibility(i);
    }
}
