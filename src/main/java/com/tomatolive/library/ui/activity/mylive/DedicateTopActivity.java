package com.tomatolive.library.ui.activity.mylive;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.adapter.DedicateTopAdapter;
import com.tomatolive.library.ui.presenter.DedicateTopPresenter;
import com.tomatolive.library.ui.view.emptyview.RecyclerIncomeEmptyView;
import com.tomatolive.library.ui.view.iview.IDedicateTopView;
import defpackage.ra;
import java.util.ArrayList;
import java.util.List;

public class DedicateTopActivity extends BaseActivity<DedicateTopPresenter> implements IDedicateTopView {
    private static final int ALL_TOP_VALUE = 2;
    private static final int DAY_TOP_VALUE = 1;
    private static final int MONTH_TOP_VALUE = 4;
    private static final int WEEK_TOP_VALUE = 3;
    private final int CHARM_All_KEY = 13;
    private final int CHARM_DAY_KEY = 11;
    private final int CHARM_MONTH_KEY = 14;
    private final int CHARM_WEEK_KEY = 12;
    private int dayTagValue = 1;
    private SparseArray<List<AnchorEntity>> listMap = new SparseArray();
    private DedicateTopAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private TextView tvAllTop;
    private TextView tvDayTop;
    private TextView tvMonthTop;
    private TextView tvWeekTop;

    public void onResultError(int i) {
    }

    /* Access modifiers changed, original: protected */
    public DedicateTopPresenter createPresenter() {
        return new DedicateTopPresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_dedicate_top;
    }

    /* Access modifiers changed, original: protected */
    public View injectStateView() {
        return findViewById(R.id.fl_content_view);
    }

    public void initView(Bundle bundle) {
        setActivityTitle(R.string.fq_my_live_dedicate);
        this.tvDayTop = (TextView) findViewById(R.id.tv_day_top);
        this.tvWeekTop = (TextView) findViewById(R.id.tv_week_top);
        this.tvMonthTop = (TextView) findViewById(R.id.tv_month_top);
        this.tvAllTop = (TextView) findViewById(R.id.tv_all_top);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.mSmartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        initAdapter();
        hideTopTagView(1, true, true);
    }

    private void initAdapter() {
        ((DefaultItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mAdapter = new DedicateTopAdapter(R.layout.fq_item_list_dedicate_top);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.setEmptyView(new RecyclerIncomeEmptyView(this.mContext, 33));
    }

    public void initListener() {
        super.initListener();
        this.tvDayTop.setOnClickListener(new -$$Lambda$DedicateTopActivity$HVhvWA5fEl6jPQTiiQ0pK3oM790(this));
        this.tvMonthTop.setOnClickListener(new -$$Lambda$DedicateTopActivity$SHX4jXUm3CKQy_EZEmy46IS3Qas(this));
        this.tvWeekTop.setOnClickListener(new -$$Lambda$DedicateTopActivity$UBzNA-LPrGfLW6zugywh34b6qqc(this));
        this.tvAllTop.setOnClickListener(new -$$Lambda$DedicateTopActivity$MzTE0zH1-QzoILiDJBlB_2c39s8(this));
        this.mStateView.setOnRetryClickListener(new -$$Lambda$DedicateTopActivity$PgwYHAJFKJY2StUVNPy139vqWNc(this));
        this.mSmartRefreshLayout.a(new -$$Lambda$DedicateTopActivity$eCFPMQN7-yHanU6Ibxop1s853Oc(this)).a(new -$$Lambda$DedicateTopActivity$RHm7MIlqR8jSkYJ9VpG-s3iIV4k(this));
    }

    public static /* synthetic */ void lambda$initListener$4(DedicateTopActivity dedicateTopActivity) {
        dedicateTopActivity.listMap.clear();
        dedicateTopActivity.pageNum = 1;
        dedicateTopActivity.sendRequest(true, true);
    }

    public static /* synthetic */ void lambda$initListener$5(DedicateTopActivity dedicateTopActivity, ra raVar) {
        dedicateTopActivity.removeData();
        dedicateTopActivity.pageNum = 1;
        dedicateTopActivity.sendRequest(false, true);
        dedicateTopActivity.mSmartRefreshLayout.g();
    }

    public static /* synthetic */ void lambda$initListener$6(DedicateTopActivity dedicateTopActivity, ra raVar) {
        dedicateTopActivity.pageNum++;
        dedicateTopActivity.sendRequest(false, false);
        dedicateTopActivity.mSmartRefreshLayout.h();
    }

    private void hideTopTagView(int i, boolean z, boolean z2) {
        this.dayTagValue = i;
        boolean z3 = false;
        this.tvDayTop.setSelected(i == 1);
        this.tvWeekTop.setSelected(i == 3);
        this.tvMonthTop.setSelected(i == 4);
        TextView textView = this.tvAllTop;
        if (i == 2) {
            z3 = true;
        }
        textView.setSelected(z3);
        setTextViewDrawable(i);
        sendRequest(z, z2);
    }

    private void setTextViewDrawable(int i) {
        Drawable drawable = getResources().getDrawable(R.drawable.fq_shape_top_tag_red_divider);
        this.tvDayTop.setCompoundDrawablesWithIntrinsicBounds(null, null, null, i == 1 ? drawable : null);
        this.tvMonthTop.setCompoundDrawablesWithIntrinsicBounds(null, null, null, i == 4 ? drawable : null);
        this.tvWeekTop.setCompoundDrawablesWithIntrinsicBounds(null, null, null, i == 3 ? drawable : null);
        TextView textView = this.tvAllTop;
        if (i != 2) {
            drawable = null;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable);
    }

    private void sendRequest(boolean z, boolean z2) {
        List charmDataList = getCharmDataList(this.dayTagValue);
        if (charmDataList != null) {
            this.mAdapter.setNewData(charmDataList);
        } else {
            ((DedicateTopPresenter) this.mPresenter).getDataList(this.mStateView, getDateType(), this.pageNum, z, z2);
        }
    }

    private String getDateType() {
        switch (this.dayTagValue) {
            case 1:
                return "day";
            case 2:
                return "all";
            case 3:
                return "week";
            case 4:
                return "month";
            default:
                return "day";
        }
    }

    public void onDataListSuccess(List<AnchorEntity> list, boolean z, boolean z2) {
        putCharmDataList(list, this.dayTagValue);
        this.mAdapter.setNewData(list);
        this.mSmartRefreshLayout.i();
    }

    private void putCharmDataList(List<AnchorEntity> list, int i) {
        if (this.listMap == null) {
            this.listMap = new SparseArray();
        }
        switch (i) {
            case 1:
                this.listMap.put(11, formatList(list));
                return;
            case 2:
                this.listMap.put(13, formatList(list));
                return;
            case 3:
                this.listMap.put(12, formatList(list));
                return;
            case 4:
                this.listMap.put(14, formatList(list));
                return;
            default:
                return;
        }
    }

    private List<AnchorEntity> formatList(List<AnchorEntity> list) {
        if (list == null) {
            return new ArrayList();
        }
        if (list.isEmpty()) {
            list = new ArrayList();
        }
        return list;
    }

    private List<AnchorEntity> getCharmDataList(int i) {
        if (this.listMap == null) {
            return null;
        }
        switch (i) {
            case 1:
                return (List) this.listMap.get(11);
            case 2:
                return (List) this.listMap.get(13);
            case 3:
                return (List) this.listMap.get(12);
            case 4:
                return (List) this.listMap.get(14);
            default:
                return new ArrayList();
        }
    }

    private void removeData() {
        if (this.listMap != null) {
            switch (this.dayTagValue) {
                case 1:
                    this.listMap.remove(11);
                    break;
                case 2:
                    this.listMap.remove(13);
                    break;
                case 3:
                    this.listMap.remove(12);
                    break;
                case 4:
                    this.listMap.remove(14);
                    break;
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.listMap != null) {
            this.listMap.clear();
            this.listMap = null;
        }
    }
}
