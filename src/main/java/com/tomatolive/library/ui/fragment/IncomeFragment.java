package com.tomatolive.library.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseFragment;
import com.tomatolive.library.model.IncomeEntity;
import com.tomatolive.library.model.IncomeMenuEntity;
import com.tomatolive.library.ui.activity.mylive.IncomeDetailActivity;
import com.tomatolive.library.ui.adapter.IncomeMenuAdapter;
import com.tomatolive.library.ui.presenter.IncomePresenter;
import com.tomatolive.library.ui.view.emptyview.RecyclerIncomeEmptyView;
import com.tomatolive.library.ui.view.headview.IncomeListHeadView;
import com.tomatolive.library.ui.view.iview.IIncomeView;
import com.tomatolive.library.utils.d;
import com.tomatolive.library.utils.p;
import defpackage.ra;
import java.util.Date;
import java.util.List;

public class IncomeFragment extends BaseFragment<IncomePresenter> implements IIncomeView {
    private IncomeMenuAdapter mAdapter;
    private String mChoosedDate = d.b("yyyy-MM-dd");
    private IncomeListHeadView mDateQueryView;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;

    public void onResultError(int i) {
    }

    public static IncomeFragment newInstance() {
        Bundle bundle = new Bundle();
        IncomeFragment incomeFragment = new IncomeFragment();
        incomeFragment.setArguments(bundle);
        return incomeFragment;
    }

    /* Access modifiers changed, original: protected */
    public IncomePresenter createPresenter() {
        return new IncomePresenter(this.mContext, this);
    }

    public int getLayoutId() {
        return R.layout.fq_fragment_income;
    }

    /* Access modifiers changed, original: protected */
    public View injectStateView(View view) {
        return view.findViewById(R.id.fl_content_view);
    }

    public void initView(View view, @Nullable Bundle bundle) {
        this.mSmartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.mDateQueryView = (IncomeListHeadView) view.findViewById(R.id.date_query_view);
        this.mSmartRefreshLayout.b(false);
        this.mDateQueryView.initData(false);
        initAdapter();
    }

    private void initAdapter() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mAdapter = new IncomeMenuAdapter(R.layout.fq_item_list_income_menu, false);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.setEmptyView(new RecyclerIncomeEmptyView(this.mContext, 32));
        ((IncomePresenter) this.mPresenter).getDataList(this.mStateView, true, this.mChoosedDate);
    }

    public void initListener() {
        super.initListener();
        this.mDateQueryView.setOnDateSelectedListener(new -$$Lambda$IncomeFragment$v0kzuiA16k6jx8yFuu9_ScmOk-0(this));
        this.mStateView.setOnRetryClickListener(new -$$Lambda$IncomeFragment$_aOJLhUr3QtL_6bYMJcvq7qv-Uc(this));
        this.mSmartRefreshLayout.a(new -$$Lambda$IncomeFragment$-yqSAhb2QiiXhRjuYG9Wwnd8K1g(this));
        this.mAdapter.setOnItemClickListener(new -$$Lambda$IncomeFragment$0M9Joy-tV2D8O8r01QsPob2k9lY(this));
    }

    public static /* synthetic */ void lambda$initListener$0(IncomeFragment incomeFragment, Date date) {
        incomeFragment.mChoosedDate = d.a(date, "yyyy-MM-dd");
        ((IncomePresenter) incomeFragment.mPresenter).getDataList(incomeFragment.mStateView, true, incomeFragment.mChoosedDate);
    }

    public static /* synthetic */ void lambda$initListener$2(IncomeFragment incomeFragment, ra raVar) {
        ((IncomePresenter) incomeFragment.mPresenter).getDataList(incomeFragment.mStateView, false, incomeFragment.mChoosedDate);
        incomeFragment.mSmartRefreshLayout.g();
    }

    public static /* synthetic */ void lambda$initListener$3(IncomeFragment incomeFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        IncomeEntity incomeEntity = (IncomeEntity) baseQuickAdapter.getItem(i);
        if (incomeEntity != null) {
            Intent intent = new Intent(incomeFragment.mContext, IncomeDetailActivity.class);
            intent.putExtra("resultItem", p.a(incomeEntity.incomeType));
            intent.putExtra("resultFlag", false);
            intent.putExtra("resultDate", incomeFragment.mChoosedDate);
            incomeFragment.startActivity(intent);
        }
    }

    public void onDataListSuccess(List<IncomeEntity> list, IncomeMenuEntity incomeMenuEntity) {
        this.mDateQueryView.setCurrentGold(incomeMenuEntity.getTotalIcome());
        this.mAdapter.setNewData(list);
    }
}
