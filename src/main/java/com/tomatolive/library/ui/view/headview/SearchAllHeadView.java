package com.tomatolive.library.ui.view.headview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.tomatolive.library.R;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.adapter.SearchAllAnchorAdapter;
import com.tomatolive.library.ui.view.divider.RVDividerRecommendSearchGrid;
import java.util.List;

public class SearchAllHeadView extends LinearLayout {
    private SearchAllAnchorAdapter adapter;
    private AnchorItemClickListener anchorItemClickListener;
    private LinearLayout llAnchorBg;
    private LinearLayout llLiveBg;
    private RecyclerView recyclerView;

    public interface AnchorItemClickListener {
        void onAnchorItemClickListener(AnchorEntity anchorEntity, int i);
    }

    public SearchAllHeadView(Context context) {
        this(context, null);
    }

    public SearchAllHeadView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SearchAllHeadView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.fq_layout_head_view_search_all, this);
        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.llLiveBg = (LinearLayout) findViewById(R.id.ll_live_bg);
        this.llAnchorBg = (LinearLayout) findViewById(R.id.ll_anchor_bg);
        initAdapter();
    }

    private void initAdapter() {
        this.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        this.recyclerView.addItemDecoration(new RVDividerRecommendSearchGrid(getContext(), R.color.fq_colorWhite));
        this.adapter = new SearchAllAnchorAdapter(R.layout.fq_item_list_search_anchor);
        this.recyclerView.setAdapter(this.adapter);
        this.adapter.bindToRecyclerView(this.recyclerView);
        this.adapter.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (SearchAllHeadView.this.anchorItemClickListener != null) {
                    SearchAllHeadView.this.anchorItemClickListener.onAnchorItemClickListener((AnchorEntity) baseQuickAdapter.getItem(i), i);
                }
            }
        });
    }

    public void initData(List<AnchorEntity> list, String str) {
        this.adapter.setKeyword(str);
        if (list.size() > 3) {
            this.adapter.setNewData(list.subList(0, 3));
        } else {
            this.adapter.setNewData(list);
        }
    }

    public void setHideLiveBg(boolean z) {
        this.llLiveBg.setVisibility(z ? 4 : 0);
    }

    public void setHideAnchorBg(boolean z) {
        int i = 0;
        this.llAnchorBg.setVisibility(z ? 8 : 0);
        RecyclerView recyclerView = this.recyclerView;
        if (z) {
            i = 8;
        }
        recyclerView.setVisibility(i);
    }

    public boolean isAnchorListData() {
        return this.adapter != null && this.adapter.getData().size() > 0;
    }

    public AnchorItemClickListener getAnchorItemClickListener() {
        return this.anchorItemClickListener;
    }

    public void setAnchorItemClickListener(AnchorItemClickListener anchorItemClickListener) {
        this.anchorItemClickListener = anchorItemClickListener;
    }
}
