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
import com.tomatolive.library.model.ExpenseMenuEntity;
import com.tomatolive.library.model.IncomeEntity;
import com.tomatolive.library.ui.activity.mylive.IncomeDetailActivity;
import com.tomatolive.library.ui.adapter.IncomeMenuAdapter;
import com.tomatolive.library.ui.presenter.ConsumePresenter;
import com.tomatolive.library.ui.view.emptyview.RecyclerIncomeEmptyView;
import com.tomatolive.library.ui.view.headview.IncomeListHeadView;
import com.tomatolive.library.ui.view.iview.IConsumeView;
import com.tomatolive.library.utils.d;
import com.tomatolive.library.utils.p;
import defpackage.ra;
import java.util.Date;
import java.util.List;

public class ConsumeFragment extends BaseFragment<ConsumePresenter> implements IConsumeView {
    private IncomeMenuAdapter mAdapter;
    private String mChoosedDate = d.b("yyyy-MM-dd");
    private IncomeListHeadView mDateQueryView;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;

    public void onResultError(int i) {
    }

    public static ConsumeFragment newInstance() {
        Bundle bundle = new Bundle();
        ConsumeFragment consumeFragment = new ConsumeFragment();
        consumeFragment.setArguments(bundle);
        return consumeFragment;
    }

    /* Access modifiers changed, original: protected */
    public ConsumePresenter createPresenter() {
        return new ConsumePresenter(this.mContext, this);
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
        ((ConsumePresenter) this.mPresenter).getDataList(this.mStateView, true, this.mChoosedDate);
    }

    private void initAdapter() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mAdapter = new IncomeMenuAdapter(R.layout.fq_item_list_income_menu, true);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.setEmptyView(new RecyclerIncomeEmptyView(this.mContext, 37));
    }

    public void initListener() {
        super.initListener();
        this.mDateQueryView.setOnDateSelectedListener(new -$$Lambda$ConsumeFragment$ZnDQTmwxqBDBrOh_sjtUkzJukOY(this));
        this.mStateView.setOnRetryClickListener(new -$$Lambda$ConsumeFragment$hGAmTjfI7H42CJtjMCFaA2WhPRk(this));
        this.mSmartRefreshLayout.a(new -$$Lambda$ConsumeFragment$WE3ozmsJMc66p5qKCeTlSGi_Ukk(this));
        this.mAdapter.setOnItemClickListener(new -$$Lambda$ConsumeFragment$pOPCBuunI0-1c6v9EKu3Uy6s9X4(this));
    }

    public static /* synthetic */ void lambda$initListener$0(ConsumeFragment consumeFragment, Date date) {
        consumeFragment.mChoosedDate = d.a(date, "yyyy-MM-dd");
        ((ConsumePresenter) consumeFragment.mPresenter).getDataList(consumeFragment.mStateView, true, consumeFragment.mChoosedDate);
    }

    public static /* synthetic */ void lambda$initListener$2(ConsumeFragment consumeFragment, ra raVar) {
        ((ConsumePresenter) consumeFragment.mPresenter).getDataList(consumeFragment.mStateView, false, consumeFragment.mChoosedDate);
        consumeFragment.mSmartRefreshLayout.g();
    }

    public static /* synthetic */ void lambda$initListener$3(ConsumeFragment consumeFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        IncomeEntity incomeEntity = (IncomeEntity) baseQuickAdapter.getItem(i);
        if (incomeEntity != null) {
            Intent intent = new Intent(consumeFragment.mContext, IncomeDetailActivity.class);
            intent.putExtra("resultItem", p.a(incomeEntity.incomeType));
            intent.putExtra("resultFlag", true);
            intent.putExtra("resultCount", incomeEntity.incomeCount);
            intent.putExtra("resultDate", consumeFragment.mChoosedDate);
            consumeFragment.startActivity(intent);
        }
    }

    public void onDataListSuccess(List<IncomeEntity> list, ExpenseMenuEntity expenseMenuEntity) {
        this.mDateQueryView.setCurrentGold(expenseMenuEntity.getTotalExpense());
        this.mAdapter.setNewData(list);
    }
}
