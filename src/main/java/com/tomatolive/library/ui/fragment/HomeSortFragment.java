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
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.model.event.BaseEvent;
import com.tomatolive.library.model.event.StartLiveEvent;
import com.tomatolive.library.ui.adapter.HomeLiveAdapter;
import com.tomatolive.library.ui.presenter.HomeSortPresenter;
import com.tomatolive.library.ui.view.divider.RVDividerLive;
import com.tomatolive.library.ui.view.emptyview.RecyclerEmptyView;
import com.tomatolive.library.ui.view.iview.IHomeSortView;
import com.tomatolive.library.utils.b;
import defpackage.ra;
import java.util.List;

public class HomeSortFragment extends BaseFragment<HomeSortPresenter> implements IHomeSortView {
    private HomeLiveAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private String tagId;

    public boolean isLazyLoad() {
        return true;
    }

    public void onLiveListFail() {
    }

    public void onResultError(int i) {
    }

    public static HomeSortFragment newInstance(String str) {
        Bundle bundle = new Bundle();
        HomeSortFragment homeSortFragment = new HomeSortFragment();
        bundle.putString("tagId", str);
        homeSortFragment.setArguments(bundle);
        return homeSortFragment;
    }

    /* Access modifiers changed, original: protected */
    public HomeSortPresenter createPresenter() {
        return new HomeSortPresenter(this.mContext, this);
    }

    public void getBundle(Bundle bundle) {
        super.getBundle(bundle);
        this.tagId = bundle.getString("tagId");
    }

    public int getLayoutId() {
        return R.layout.fq_fragment_home_sort;
    }

    /* Access modifiers changed, original: protected */
    public View injectStateView(View view) {
        return view.findViewById(R.id.fl_content_view);
    }

    public void initView(View view, @Nullable Bundle bundle) {
        this.mSmartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        initAdapter();
    }

    private void initAdapter() {
        this.mAdapter = new HomeLiveAdapter(this, R.layout.fq_item_list_live_view_new);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 2));
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.addItemDecoration(new RVDividerLive(this.mContext, R.color.fq_colorWhite));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.setEmptyView(new RecyclerEmptyView(this.mContext));
    }

    public void onLazyLoad() {
        ((HomeSortPresenter) this.mPresenter).getLiveList(this.mStateView, this.tagId, this.pageNum, true, true);
    }

    public void initListener() {
        super.initListener();
        this.mStateView.setOnRetryClickListener(new -$$Lambda$HomeSortFragment$O26izLz0AQXdlyZLpi59ucXhDLw(this));
        this.mSmartRefreshLayout.a(new -$$Lambda$HomeSortFragment$ZpPxsWXBdCRcZf6A5GTmfMoiyK4(this)).a(new -$$Lambda$HomeSortFragment$6RN7NFTqAgnMzlnJ4gtM5nXyAMM(this));
        this.mAdapter.setOnItemClickListener(new -$$Lambda$HomeSortFragment$dvjy4A_xxgPIQz_9R4CPHiX91ss(this));
    }

    public static /* synthetic */ void lambda$initListener$0(HomeSortFragment homeSortFragment) {
        homeSortFragment.pageNum = 1;
        ((HomeSortPresenter) homeSortFragment.mPresenter).getLiveList(homeSortFragment.mStateView, homeSortFragment.tagId, homeSortFragment.pageNum, true, true);
    }

    public static /* synthetic */ void lambda$initListener$1(HomeSortFragment homeSortFragment, ra raVar) {
        homeSortFragment.pageNum = 1;
        ((HomeSortPresenter) homeSortFragment.mPresenter).getLiveList(homeSortFragment.mStateView, homeSortFragment.tagId, homeSortFragment.pageNum, false, true);
        homeSortFragment.mSmartRefreshLayout.g();
    }

    public static /* synthetic */ void lambda$initListener$2(HomeSortFragment homeSortFragment, ra raVar) {
        homeSortFragment.pageNum++;
        ((HomeSortPresenter) homeSortFragment.mPresenter).getLiveList(homeSortFragment.mStateView, homeSortFragment.tagId, homeSortFragment.pageNum, false, false);
        homeSortFragment.mSmartRefreshLayout.h();
    }

    public static /* synthetic */ void lambda$initListener$3(HomeSortFragment homeSortFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        LiveEntity liveEntity = (LiveEntity) baseQuickAdapter.getItem(i);
        if (b.a(homeSortFragment.mContext)) {
            b.a(homeSortFragment.mContext, 1, liveEntity);
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

    public void onEventMainThreadSticky(BaseEvent baseEvent) {
        super.onEventMainThreadSticky(baseEvent);
        if (baseEvent instanceof StartLiveEvent) {
            this.pageNum = 1;
            ((HomeSortPresenter) this.mPresenter).getLiveList(this.mStateView, this.tagId, this.pageNum, true, true);
        }
    }
}
