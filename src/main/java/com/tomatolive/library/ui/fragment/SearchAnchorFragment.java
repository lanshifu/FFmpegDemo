package com.tomatolive.library.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
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
import com.tomatolive.library.model.event.SearchEvent;
import com.tomatolive.library.ui.adapter.SearchAnchorAdapter;
import com.tomatolive.library.ui.presenter.SearchAnchorPresenter;
import com.tomatolive.library.ui.view.divider.RVDividerLinear;
import com.tomatolive.library.ui.view.emptyview.RecyclerEmptyView;
import com.tomatolive.library.ui.view.iview.ISearchAnchorView;
import com.tomatolive.library.utils.b;
import defpackage.ra;
import java.util.List;
import org.greenrobot.eventbus.c;

public class SearchAnchorFragment extends BaseFragment<SearchAnchorPresenter> implements ISearchAnchorView {
    private String keyword = "";
    private SearchAnchorAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;

    public void onResultError(int i) {
    }

    public static SearchAnchorFragment newInstance() {
        Bundle bundle = new Bundle();
        SearchAnchorFragment searchAnchorFragment = new SearchAnchorFragment();
        searchAnchorFragment.setArguments(bundle);
        return searchAnchorFragment;
    }

    /* Access modifiers changed, original: protected */
    public SearchAnchorPresenter createPresenter() {
        return new SearchAnchorPresenter(this.mContext, this);
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
        ((DefaultItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mRecyclerView.addItemDecoration(new RVDividerLinear(this.mContext, R.color.fq_view_divider_color));
        this.mAdapter = new SearchAnchorAdapter(this, R.layout.fq_item_list_search_attention);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.setEmptyView(new RecyclerEmptyView(this.mContext, 30));
    }

    public void initListener() {
        super.initListener();
        this.mStateView.setOnRetryClickListener(new -$$Lambda$SearchAnchorFragment$wxAyKb2-kaPy5aU8iVp0DDOd6tc(this));
        this.mSmartRefreshLayout.a(new -$$Lambda$SearchAnchorFragment$pMf6JzBdggF36D4AH2QrE54qm8w(this)).a(new -$$Lambda$SearchAnchorFragment$yptKQnb694yXiPrfeDlmNBscfrY(this));
        this.mAdapter.setOnItemClickListener(new -$$Lambda$SearchAnchorFragment$aDBmjpuWXIKAQ7v0DtS043j2gwI(this));
        this.mAdapter.setOnItemChildClickListener(new -$$Lambda$SearchAnchorFragment$gXLL-6fdRqAOhIrZ_DmWufKmsJY(this));
    }

    public static /* synthetic */ void lambda$initListener$0(SearchAnchorFragment searchAnchorFragment) {
        searchAnchorFragment.pageNum = 1;
        ((SearchAnchorPresenter) searchAnchorFragment.mPresenter).getAnchorList(searchAnchorFragment.mStateView, searchAnchorFragment.keyword, searchAnchorFragment.pageNum, true, true);
    }

    public static /* synthetic */ void lambda$initListener$2(SearchAnchorFragment searchAnchorFragment, ra raVar) {
        searchAnchorFragment.pageNum++;
        ((SearchAnchorPresenter) searchAnchorFragment.mPresenter).getAnchorList(searchAnchorFragment.mStateView, searchAnchorFragment.keyword, searchAnchorFragment.pageNum, false, false);
        searchAnchorFragment.mSmartRefreshLayout.h();
    }

    public static /* synthetic */ void lambda$initListener$3(SearchAnchorFragment searchAnchorFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        LiveEntity formatLiveEntity = searchAnchorFragment.formatLiveEntity((AnchorEntity) baseQuickAdapter.getItem(i));
        if (b.a(searchAnchorFragment.mContext)) {
            b.a(searchAnchorFragment.mContext, 2, formatLiveEntity);
        }
    }

    public static /* synthetic */ void lambda$initListener$4(SearchAnchorFragment searchAnchorFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (view.getId() == R.id.tv_attention) {
            AnchorEntity anchorEntity = (AnchorEntity) baseQuickAdapter.getItem(i);
            if (anchorEntity != null && b.a(searchAnchorFragment.mContext, anchorEntity.userId)) {
                anchorEntity.followed = anchorEntity.isAttention() ? "0" : "1";
                baseQuickAdapter.setData(i, anchorEntity);
                searchAnchorFragment.showToast(anchorEntity.isAttention() ? R.string.fq_text_attention_success : R.string.fq_text_attention_cancel_success);
                ((SearchAnchorPresenter) searchAnchorFragment.mPresenter).attentionAnchor(anchorEntity.userId, anchorEntity.isAttention());
            }
        }
    }

    public void onEventMainThreadSticky(BaseEvent baseEvent) {
        super.onEventMainThreadSticky(baseEvent);
        if (baseEvent instanceof SearchEvent) {
            this.keyword = ((SearchEvent) baseEvent).keyword;
            this.mAdapter.setKeyWord(this.keyword);
            this.pageNum = 1;
            ((SearchAnchorPresenter) this.mPresenter).getAnchorList(this.mStateView, this.keyword, this.pageNum, true, true);
        }
    }

    public void onDataListSuccess(List<AnchorEntity> list, boolean z, boolean z2) {
        if (z2) {
            this.mAdapter.setNewData(list);
        } else {
            this.mAdapter.addData(list);
        }
        if (z) {
            this.mSmartRefreshLayout.i();
        }
    }

    public void onAttentionSuccess() {
        c.a().d(new AttentionEvent());
    }

    private LiveEntity formatLiveEntity(AnchorEntity anchorEntity) {
        LiveEntity liveEntity = new LiveEntity();
        if (anchorEntity != null) {
            liveEntity.anchorId = anchorEntity.userId;
            liveEntity.liveId = anchorEntity.liveId;
            liveEntity.isLiving = anchorEntity.isLiving;
            liveEntity.liveStatus = anchorEntity.isLiving;
            liveEntity.streamName = anchorEntity.streamName;
        }
        return liveEntity;
    }
}
