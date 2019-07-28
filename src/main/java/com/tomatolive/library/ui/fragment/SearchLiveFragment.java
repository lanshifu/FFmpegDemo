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
import com.tomatolive.library.model.event.SearchEvent;
import com.tomatolive.library.ui.adapter.HomeLiveAdapter;
import com.tomatolive.library.ui.presenter.SearchLivePresenter;
import com.tomatolive.library.ui.view.divider.RVDividerLive;
import com.tomatolive.library.ui.view.emptyview.RecyclerEmptyView;
import com.tomatolive.library.ui.view.iview.ISearchLiveView;
import com.tomatolive.library.utils.b;
import defpackage.ra;
import java.util.List;

public class SearchLiveFragment extends BaseFragment<SearchLivePresenter> implements ISearchLiveView {
    private String keyword = "";
    private HomeLiveAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;

    public void onResultError(int i) {
    }

    public static SearchLiveFragment newInstance() {
        Bundle bundle = new Bundle();
        SearchLiveFragment searchLiveFragment = new SearchLiveFragment();
        searchLiveFragment.setArguments(bundle);
        return searchLiveFragment;
    }

    /* Access modifiers changed, original: protected */
    public SearchLivePresenter createPresenter() {
        return new SearchLivePresenter(this.mContext, this);
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
        this.mSmartRefreshLayout.c(false);
        initAdapter();
    }

    private void initAdapter() {
        this.mAdapter = new HomeLiveAdapter(this, R.layout.fq_item_list_live_view_new);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 2));
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.addItemDecoration(new RVDividerLive(this.mContext, R.color.fq_colorWhite));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.setEmptyView(new RecyclerEmptyView(this.mContext, 31));
    }

    public void initListener() {
        super.initListener();
        this.mStateView.setOnRetryClickListener(new -$$Lambda$SearchLiveFragment$RIEXcMJuAaAyul-hmvZF5m_HqIs(this));
        this.mSmartRefreshLayout.a(new -$$Lambda$SearchLiveFragment$e9M5SjHLwdwob0LFCM7CZOQbCRI(this)).a(new -$$Lambda$SearchLiveFragment$stHx2I8N896tjdroB0lRc2DKtC4(this));
        this.mAdapter.setOnItemClickListener(new -$$Lambda$SearchLiveFragment$uiRnygmj9w71pbqI4dK89R8tcDU(this));
    }

    public static /* synthetic */ void lambda$initListener$0(SearchLiveFragment searchLiveFragment) {
        searchLiveFragment.pageNum = 1;
        ((SearchLivePresenter) searchLiveFragment.mPresenter).getLiveList(searchLiveFragment.mStateView, searchLiveFragment.keyword, searchLiveFragment.pageNum, true, true);
    }

    public static /* synthetic */ void lambda$initListener$2(SearchLiveFragment searchLiveFragment, ra raVar) {
        searchLiveFragment.pageNum++;
        ((SearchLivePresenter) searchLiveFragment.mPresenter).getLiveList(searchLiveFragment.mStateView, searchLiveFragment.keyword, searchLiveFragment.pageNum, false, false);
        searchLiveFragment.mSmartRefreshLayout.h();
    }

    public static /* synthetic */ void lambda$initListener$3(SearchLiveFragment searchLiveFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        LiveEntity liveEntity = (LiveEntity) baseQuickAdapter.getItem(i);
        if (b.a(searchLiveFragment.mContext)) {
            b.a(searchLiveFragment.mContext, 2, liveEntity);
        }
    }

    public void onEventMainThreadSticky(BaseEvent baseEvent) {
        super.onEventMainThreadSticky(baseEvent);
        if (baseEvent instanceof SearchEvent) {
            this.keyword = ((SearchEvent) baseEvent).keyword;
            this.pageNum = 1;
            ((SearchLivePresenter) this.mPresenter).getLiveList(this.mStateView, this.keyword, this.pageNum, true, true);
        }
    }

    public void onDataListSuccess(List<LiveEntity> list, boolean z, boolean z2) {
        if (z2) {
            this.mAdapter.setNewData(list);
        } else {
            this.mAdapter.addData(list);
        }
        if (z) {
            this.mSmartRefreshLayout.i();
        }
    }
}
