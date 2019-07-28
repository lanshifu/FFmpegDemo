package com.tomatolive.library.ui.activity.mylive;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.model.IncomeEntity;
import com.tomatolive.library.ui.adapter.IncomeDetailAdapter;
import com.tomatolive.library.ui.presenter.IncomeDetailPresenter;
import com.tomatolive.library.ui.view.emptyview.RecyclerIncomeEmptyView;
import com.tomatolive.library.ui.view.headview.IncomeListHeadView;
import com.tomatolive.library.ui.view.iview.IIncomeDetailView;
import com.tomatolive.library.utils.d;
import defpackage.ra;
import java.util.Date;
import java.util.List;

public class IncomeDetailActivity extends BaseActivity<IncomeDetailPresenter> implements IIncomeDetailView {
    private int incomeType;
    private boolean isExpend = false;
    private boolean isFree = true;
    private IncomeDetailAdapter mAdapter;
    private String mChoosedDate = d.b("yyyy-MM-dd");
    private IncomeListHeadView mDateQueryView;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;

    public void onResultError(int i) {
    }

    /* Access modifiers changed, original: protected */
    public IncomeDetailPresenter createPresenter() {
        return new IncomeDetailPresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_income_detail;
    }

    /* Access modifiers changed, original: protected */
    public View injectStateView() {
        return findViewById(R.id.fl_content_view);
    }

    public void initView(Bundle bundle) {
        boolean z = true;
        this.incomeType = getIntent().getIntExtra("resultItem", 1);
        this.isExpend = getIntent().getBooleanExtra("resultFlag", false);
        String stringExtra = getIntent().getStringExtra("resultDate");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.mChoosedDate = stringExtra;
        }
        setActivityTitle(formatTitleStr());
        this.mSmartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.mDateQueryView = (IncomeListHeadView) findViewById(R.id.date_query_view);
        IncomeListHeadView incomeListHeadView = this.mDateQueryView;
        if (this.incomeType != 4) {
            z = false;
        }
        incomeListHeadView.initData(z);
        initAdapter();
        this.mDateQueryView.setSelectDate(this.mChoosedDate);
    }

    private void initAdapter() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mAdapter = new IncomeDetailAdapter(R.layout.fq_item_list_income_detail, this.incomeType, this.isExpend);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.setEmptyView(new RecyclerIncomeEmptyView(this.mContext, this.isExpend ? 37 : 32));
        sendRequest(true, true);
    }

    private void sendRequest(boolean z, boolean z2) {
        if (this.incomeType == 4) {
            if (this.isExpend) {
                ((IncomeDetailPresenter) this.mPresenter).getPropsExpenseDataList(this.mStateView, this.pageNum, z, z2, this.mChoosedDate, this.isFree);
            } else {
                ((IncomeDetailPresenter) this.mPresenter).getPropsIncomeDataList(this.mStateView, this.pageNum, z, z2, this.mChoosedDate, this.isFree);
            }
            return;
        }
        if (this.isExpend) {
            ((IncomeDetailPresenter) this.mPresenter).getExpenseDataList(this.mStateView, this.pageNum, z, z2, this.incomeType, this.mChoosedDate);
        } else {
            ((IncomeDetailPresenter) this.mPresenter).getIncomeDataList(this.mStateView, this.pageNum, z, z2, this.incomeType, this.mChoosedDate);
        }
    }

    public void initListener() {
        super.initListener();
        this.mStateView.setOnRetryClickListener(new -$$Lambda$IncomeDetailActivity$OEubVopUvQtu-1zvHeNLD8_d8XY(this));
        this.mSmartRefreshLayout.a(new -$$Lambda$IncomeDetailActivity$lzoAJWOxxoRFBLmpembG429oOlQ(this)).a(new -$$Lambda$IncomeDetailActivity$hFpP0VWhlQ1Xy7ZWSI2Hs-bE0SU(this));
        this.mDateQueryView.setOnDateSelectedListener(new -$$Lambda$IncomeDetailActivity$6IZ13SEwetVIyTFKySmtsFOwFzA(this));
        this.mDateQueryView.setOnPropsDateSelectedListener(new -$$Lambda$IncomeDetailActivity$akMkuK1uHYgXfhlEo5EecBifNRA(this));
    }

    public static /* synthetic */ void lambda$initListener$0(IncomeDetailActivity incomeDetailActivity) {
        incomeDetailActivity.pageNum = 1;
        incomeDetailActivity.sendRequest(true, true);
    }

    public static /* synthetic */ void lambda$initListener$1(IncomeDetailActivity incomeDetailActivity, ra raVar) {
        incomeDetailActivity.pageNum = 1;
        incomeDetailActivity.sendRequest(false, true);
        incomeDetailActivity.mSmartRefreshLayout.g();
    }

    public static /* synthetic */ void lambda$initListener$2(IncomeDetailActivity incomeDetailActivity, ra raVar) {
        incomeDetailActivity.pageNum++;
        incomeDetailActivity.sendRequest(false, false);
        incomeDetailActivity.mSmartRefreshLayout.h();
    }

    public static /* synthetic */ void lambda$initListener$3(IncomeDetailActivity incomeDetailActivity, Date date) {
        incomeDetailActivity.pageNum = 1;
        incomeDetailActivity.mChoosedDate = d.a(date, "yyyy-MM-dd");
        incomeDetailActivity.sendRequest(false, true);
    }

    public static /* synthetic */ void lambda$initListener$4(IncomeDetailActivity incomeDetailActivity, Date date, boolean z) {
        incomeDetailActivity.isFree = z;
        incomeDetailActivity.pageNum = 1;
        incomeDetailActivity.mChoosedDate = d.a(date, "yyyy-MM-dd");
        incomeDetailActivity.sendRequest(false, true);
    }

    public void onDataListSuccess(List<IncomeEntity> list, boolean z, boolean z2, String str) {
        this.mDateQueryView.setCurrentGold(str);
        if (z2) {
            this.mAdapter.setNewData(list);
        } else {
            this.mAdapter.addData(list);
        }
        if (z) {
            this.mSmartRefreshLayout.i();
        }
    }

    private int formatTitleStr() {
        switch (this.incomeType) {
            case 1:
                return this.isExpend ? R.string.fq_gift_expend_detail : R.string.fq_gift_income_detail;
            case 2:
                return this.isExpend ? R.string.fq_guard_expend_detail : R.string.fq_guard_income_detail;
            case 3:
                return this.isExpend ? R.string.fq_car_expend_detail : R.string.fq_my_live_consume_detail;
            case 4:
                return this.isExpend ? R.string.fq_props_expend_detail : R.string.fq_props_income_detail;
            default:
                return this.isExpend ? R.string.fq_my_live_consume_detail : R.string.fq_my_live_income_detail;
        }
    }
}
