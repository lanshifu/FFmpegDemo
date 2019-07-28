package com.tomatolive.library.ui.activity.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.model.event.AttentionEvent;
import com.tomatolive.library.model.event.BaseEvent;
import com.tomatolive.library.model.event.LiveTopAttentionEvent;
import com.tomatolive.library.ui.adapter.RankingAdapter;
import com.tomatolive.library.ui.presenter.RankingPresenter;
import com.tomatolive.library.ui.view.headview.RankingHeadView;
import com.tomatolive.library.ui.view.iview.IRankingView;
import com.tomatolive.library.ui.view.widget.LoadingView;
import com.tomatolive.library.utils.b;
import defpackage.ra;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.c;

public class RankingActivity extends BaseActivity<RankingPresenter> implements IRankingView {
    private static final int DAY_TOP_VALUE = 1;
    private static final int MONTH_TOP_VALUE = 2;
    private static final int WEEK_TOP_VALUE = 3;
    private final int CHARM_DAY_KEY = 11;
    private final int CHARM_MONTH_KEY = 12;
    private final int CHARM_WEEK_KEY = 13;
    private final int STRENGTH_DAY_KEY = 21;
    private final int STRENGTH_MONTH_KEY = 22;
    private final int STRENGTH_WEEK_KEY = 23;
    private int dayTagValue = 1;
    private LoadingView ivLoadingView;
    private SparseArray<List<AnchorEntity>> listMap = new SparseArray();
    private LinearLayout llContentBg;
    private LinearLayout llEmptyView;
    private RankingAdapter mAdapter;
    private RankingHeadView mHeadView;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private RelativeLayout rlBottomTopBg;
    private int topTagValue = 4;
    private TextView tvCharmTop;
    private TextView tvDayTop;
    private TextView tvMonthTop;
    private TextView tvStrengthTop;
    private TextView tvWeekTop;

    /* Access modifiers changed, original: protected */
    public RankingPresenter createPresenter() {
        return new RankingPresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_ranking;
    }

    /* Access modifiers changed, original: protected */
    public View injectStateView() {
        return findViewById(R.id.fl_content_view);
    }

    public void initView(Bundle bundle) {
        setActivityTitle(R.string.fq_home_top);
        this.tvDayTop = (TextView) findViewById(R.id.tv_day_top);
        this.tvMonthTop = (TextView) findViewById(R.id.tv_month_top);
        this.tvWeekTop = (TextView) findViewById(R.id.tv_all_top);
        this.tvCharmTop = (TextView) findViewById(R.id.tv_charm_top);
        this.tvStrengthTop = (TextView) findViewById(R.id.tv_strength_top);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.llEmptyView = (LinearLayout) findViewById(R.id.ll_empty_view);
        this.ivLoadingView = (LoadingView) findViewById(R.id.iv_loading);
        this.llContentBg = (LinearLayout) findViewById(R.id.ll_content_bg);
        TextView textView = (TextView) findViewById(R.id.tv_bottom_top);
        this.mSmartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        this.rlBottomTopBg = (RelativeLayout) findViewById(R.id.rl_bottom_top_bg);
        this.mSmartRefreshLayout.b(false);
        textView.getPaint().setFlags(8);
        textView.getPaint().setAntiAlias(true);
        initAdapter();
        setTextViewDrawable(4);
        ((RankingPresenter) this.mPresenter).getRankConfig(this.ivLoadingView, this.llContentBg);
    }

    private void initAdapter() {
        ((DefaultItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.mHeadView = new RankingHeadView(this.mContext);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mAdapter = new RankingAdapter(R.layout.fq_item_list_live_top_new, 4);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.addHeaderView(this.mHeadView);
    }

    public void initListener() {
        super.initListener();
        this.tvCharmTop.setOnClickListener(new -$$Lambda$RankingActivity$jBvDG-9xetYi8AR89xep4WxsJJk(this));
        this.tvStrengthTop.setOnClickListener(new -$$Lambda$RankingActivity$GkrmlT-tghdRz-isdYdNqgmYiV0(this));
        this.tvDayTop.setOnClickListener(new -$$Lambda$RankingActivity$epdRZEvlt9Pv6MmuXk_HDUsTd_o(this));
        this.tvMonthTop.setOnClickListener(new -$$Lambda$RankingActivity$0YfTxIFra9lJI0QtvytnQ6kaCL0(this));
        this.tvWeekTop.setOnClickListener(new -$$Lambda$RankingActivity$ekDekoqAPMzmuW2NxVT-VWkjmGg(this));
        this.mSmartRefreshLayout.a(new -$$Lambda$RankingActivity$rQAmE_cUaP3lBVwULhwG3dLr4u0(this)).a(new -$$Lambda$RankingActivity$NBkpsKJ4V8u6h_N4y0OtRlUgTsY(this));
        this.mStateView.setOnRetryClickListener(new -$$Lambda$RankingActivity$LzSvczEyBHCAnuFjGdjmrgqroq4(this));
        this.mAdapter.setOnItemChildClickListener(new -$$Lambda$RankingActivity$Zzo5pzxktjvxua-XxaICpb2n2q4(this));
        this.mAdapter.setOnItemClickListener(new -$$Lambda$RankingActivity$RrJtTkguhi3EWfnamf8N0KkIR9M(this));
        this.mHeadView.setOnAvatarClickListener(new -$$Lambda$RankingActivity$RURKvXP1wlSMQPg8T8PGYNEggMY(this));
        this.mHeadView.setOnBtnAttentionClickListener(new -$$Lambda$RankingActivity$9offCFYWFLepuZKbxIpeQnGD_5k(this));
        findViewById(R.id.tv_bottom_top).setOnClickListener(new -$$Lambda$RankingActivity$W_N-1DGQsljh4DUqrVy8siC7QWw(this));
    }

    public static /* synthetic */ void lambda$initListener$0(RankingActivity rankingActivity, View view) {
        rankingActivity.setTextViewDrawable(4);
        rankingActivity.mAdapter.setType(4);
        rankingActivity.sendRequest(true, false);
    }

    public static /* synthetic */ void lambda$initListener$1(RankingActivity rankingActivity, View view) {
        rankingActivity.setTextViewDrawable(5);
        rankingActivity.mAdapter.setType(5);
        rankingActivity.sendRequest(true, false);
    }

    public static /* synthetic */ void lambda$initListener$5(RankingActivity rankingActivity, ra raVar) {
        rankingActivity.removeData();
        rankingActivity.sendRequest(false, false);
        rankingActivity.mSmartRefreshLayout.g();
    }

    public static /* synthetic */ void lambda$initListener$7(RankingActivity rankingActivity) {
        rankingActivity.listMap.clear();
        rankingActivity.sendRequest(true, false);
    }

    public static /* synthetic */ void lambda$initListener$8(RankingActivity rankingActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (view.getId() == R.id.tv_attention) {
            AnchorEntity anchorEntity = (AnchorEntity) baseQuickAdapter.getItem(i);
            if (anchorEntity != null && b.a(rankingActivity.mContext, anchorEntity.userId)) {
                anchorEntity.followStatus = anchorEntity.isAttention() ? "0" : "1";
                baseQuickAdapter.setData(i, anchorEntity);
                rankingActivity.showToast(anchorEntity.isAttention() ? R.string.fq_text_attention_success : R.string.fq_text_attention_cancel_success);
                ((RankingPresenter) rankingActivity.mPresenter).attentionAnchor(anchorEntity.userId, anchorEntity.isAttention());
            }
        }
    }

    public static /* synthetic */ void lambda$initListener$9(RankingActivity rankingActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (rankingActivity.topTagValue != 5) {
            AnchorEntity anchorEntity = (AnchorEntity) baseQuickAdapter.getItem(i);
            if (anchorEntity != null && b.a(rankingActivity.mContext)) {
                b.a(rankingActivity.mContext, 3, b.a(anchorEntity));
            }
        }
    }

    public static /* synthetic */ void lambda$initListener$10(RankingActivity rankingActivity, LiveEntity liveEntity) {
        if (rankingActivity.topTagValue != 5 && b.a(rankingActivity.mContext)) {
            b.a(rankingActivity.mContext, 3, liveEntity);
        }
    }

    public static /* synthetic */ void lambda$initListener$11(RankingActivity rankingActivity, View view, AnchorEntity anchorEntity) {
        if (b.a(rankingActivity.mContext, anchorEntity.userId) && rankingActivity.topTagValue != 5) {
            anchorEntity.followStatus = anchorEntity.isAttention() ? "0" : "1";
            view.setSelected(anchorEntity.isAttention());
            rankingActivity.showToast(anchorEntity.isAttention() ? R.string.fq_text_attention_success : R.string.fq_text_attention_cancel_success);
            ((RankingPresenter) rankingActivity.mPresenter).attentionAnchor(anchorEntity.userId, anchorEntity.isAttention());
        }
    }

    public static /* synthetic */ void lambda$null$12(RankingActivity rankingActivity, AnchorEntity anchorEntity) {
        rankingActivity.showToast(anchorEntity.isAttention() ? R.string.fq_text_attention_success : R.string.fq_text_attention_cancel_success);
        ((RankingPresenter) rankingActivity.mPresenter).attentionAnchor(anchorEntity.userId, anchorEntity.isAttention());
    }

    private void hideTopTagView(int i, boolean z, boolean z2) {
        this.dayTagValue = i;
        boolean z3 = false;
        this.tvDayTop.setSelected(i == 1);
        this.tvMonthTop.setSelected(i == 2);
        TextView textView = this.tvWeekTop;
        if (i == 3) {
            z3 = true;
        }
        textView.setSelected(z3);
        setTagTextViewDrawable(i);
        sendRequest(z, z2);
    }

    private void setTagTextViewDrawable(int i) {
        Drawable drawable = ContextCompat.getDrawable(this.mContext, R.drawable.fq_shape_top_tag_red_divider);
        this.tvDayTop.setCompoundDrawablesWithIntrinsicBounds(null, null, null, i == 1 ? drawable : null);
        this.tvMonthTop.setCompoundDrawablesWithIntrinsicBounds(null, null, null, i == 2 ? drawable : null);
        TextView textView = this.tvWeekTop;
        if (i != 3) {
            drawable = null;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable);
    }

    private void sendRequest(boolean z, boolean z2) {
        boolean z3 = z && !z2;
        List charmDataList;
        if (this.topTagValue == 4) {
            charmDataList = getCharmDataList(this.dayTagValue);
            if (charmDataList != null) {
                initDataList(charmDataList);
            } else if (this.mPresenter != null) {
                ((RankingPresenter) this.mPresenter).getCharmTopList(this.mStateView, getDateType(), this.dayTagValue, z3, z2);
            }
        } else {
            charmDataList = getStrengthDataList(this.dayTagValue);
            if (charmDataList != null) {
                initDataList(charmDataList);
            } else if (this.mPresenter != null) {
                ((RankingPresenter) this.mPresenter).getStrengthTopList(this.mStateView, getDateType(), this.dayTagValue, z3, z2);
            }
        }
    }

    private void setTextViewDrawable(int i) {
        this.topTagValue = i;
        boolean z = false;
        this.tvCharmTop.setSelected(i == 4);
        TextView textView = this.tvStrengthTop;
        if (i == 5) {
            z = true;
        }
        textView.setSelected(z);
    }

    private String getDateType() {
        switch (this.dayTagValue) {
            case 1:
                return "day";
            case 2:
                return "month";
            case 3:
                return "week";
            default:
                return "day";
        }
    }

    public void onResultError(int i) {
        showContentView();
    }

    public void onCharmTopListSuccess(List<AnchorEntity> list, int i, boolean z) {
        if (z) {
            showContentView();
        }
        if (list != null) {
            putCharmDataList(list, i);
            initDataList(list);
        }
    }

    public void onStrengthTopListSuccess(List<AnchorEntity> list, int i, boolean z) {
        if (z) {
            showContentView();
        }
        if (list != null) {
            putStrengthDataList(list, i);
            initDataList(list);
        }
    }

    public void onAttentionSuccess() {
        c.a().d(new AttentionEvent());
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0063  */
    public void onRankConfigSuccess(java.util.List<java.lang.String> r8) {
        /*
        r7 = this;
        r0 = 1;
        r1 = 0;
        if (r8 == 0) goto L_0x0083;
    L_0x0004:
        r2 = r8.isEmpty();
        if (r2 == 0) goto L_0x000c;
    L_0x000a:
        goto L_0x0083;
    L_0x000c:
        r2 = r8.iterator();
    L_0x0010:
        r3 = r2.hasNext();
        if (r3 == 0) goto L_0x007b;
    L_0x0016:
        r3 = r2.next();
        r3 = (java.lang.String) r3;
        r4 = -1;
        r5 = r3.hashCode();
        r6 = 96673; // 0x179a1 float:1.35468E-40 double:4.7763E-319;
        if (r5 == r6) goto L_0x0054;
    L_0x0026:
        r6 = 99228; // 0x1839c float:1.39048E-40 double:4.9025E-319;
        if (r5 == r6) goto L_0x004a;
    L_0x002b:
        r6 = 3645428; // 0x379ff4 float:5.108333E-39 double:1.8010807E-317;
        if (r5 == r6) goto L_0x0040;
    L_0x0030:
        r6 = 104080000; // 0x6342280 float:3.3879584E-35 double:5.14223524E-316;
        if (r5 == r6) goto L_0x0036;
    L_0x0035:
        goto L_0x005e;
    L_0x0036:
        r5 = "month";
        r3 = r3.equals(r5);
        if (r3 == 0) goto L_0x005e;
    L_0x003e:
        r3 = 2;
        goto L_0x005f;
    L_0x0040:
        r5 = "week";
        r3 = r3.equals(r5);
        if (r3 == 0) goto L_0x005e;
    L_0x0048:
        r3 = 1;
        goto L_0x005f;
    L_0x004a:
        r5 = "day";
        r3 = r3.equals(r5);
        if (r3 == 0) goto L_0x005e;
    L_0x0052:
        r3 = 0;
        goto L_0x005f;
    L_0x0054:
        r5 = "all";
        r3 = r3.equals(r5);
        if (r3 == 0) goto L_0x005e;
    L_0x005c:
        r3 = 3;
        goto L_0x005f;
    L_0x005e:
        r3 = -1;
    L_0x005f:
        switch(r3) {
            case 0: goto L_0x0075;
            case 1: goto L_0x006f;
            case 2: goto L_0x0069;
            case 3: goto L_0x0063;
            default: goto L_0x0062;
        };
    L_0x0062:
        goto L_0x0010;
    L_0x0063:
        r3 = r7.rlBottomTopBg;
        r3.setVisibility(r1);
        goto L_0x0010;
    L_0x0069:
        r3 = r7.tvMonthTop;
        r3.setVisibility(r1);
        goto L_0x0010;
    L_0x006f:
        r3 = r7.tvWeekTop;
        r3.setVisibility(r1);
        goto L_0x0010;
    L_0x0075:
        r3 = r7.tvDayTop;
        r3.setVisibility(r1);
        goto L_0x0010;
    L_0x007b:
        r8 = r7.formatTopValue(r8);
        r7.hideTopTagView(r8, r1, r0);
        return;
    L_0x0083:
        r8 = r7.tvDayTop;
        r8.setVisibility(r1);
        r8 = r7.tvWeekTop;
        r8.setVisibility(r1);
        r8 = r7.tvMonthTop;
        r8.setVisibility(r1);
        r7.hideTopTagView(r0, r1, r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.activity.home.RankingActivity.onRankConfigSuccess(java.util.List):void");
    }

    public void onRankConfigFail() {
        this.tvDayTop.setVisibility(0);
        this.tvWeekTop.setVisibility(0);
        this.tvMonthTop.setVisibility(0);
        hideTopTagView(1, false, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0048 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004c A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0049 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0048 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004c A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0049 A:{RETURN} */
    /* JADX WARNING: Missing block: B:17:0x0041, code skipped:
            if (r7.equals("day") != false) goto L_0x0045;
     */
    private int formatTopValue(java.util.List<java.lang.String> r7) {
        /*
        r6 = this;
        r0 = 1;
        if (r7 == 0) goto L_0x004d;
    L_0x0003:
        r1 = r7.isEmpty();
        if (r1 == 0) goto L_0x000a;
    L_0x0009:
        goto L_0x004d;
    L_0x000a:
        r1 = 0;
        r7 = r7.get(r1);
        r7 = (java.lang.String) r7;
        r2 = -1;
        r3 = r7.hashCode();
        r4 = 99228; // 0x1839c float:1.39048E-40 double:4.9025E-319;
        r5 = 2;
        if (r3 == r4) goto L_0x003b;
    L_0x001c:
        r1 = 3645428; // 0x379ff4 float:5.108333E-39 double:1.8010807E-317;
        if (r3 == r1) goto L_0x0031;
    L_0x0021:
        r1 = 104080000; // 0x6342280 float:3.3879584E-35 double:5.14223524E-316;
        if (r3 == r1) goto L_0x0027;
    L_0x0026:
        goto L_0x0044;
    L_0x0027:
        r1 = "month";
        r7 = r7.equals(r1);
        if (r7 == 0) goto L_0x0044;
    L_0x002f:
        r1 = 2;
        goto L_0x0045;
    L_0x0031:
        r1 = "week";
        r7 = r7.equals(r1);
        if (r7 == 0) goto L_0x0044;
    L_0x0039:
        r1 = 1;
        goto L_0x0045;
    L_0x003b:
        r3 = "day";
        r7 = r7.equals(r3);
        if (r7 == 0) goto L_0x0044;
    L_0x0043:
        goto L_0x0045;
    L_0x0044:
        r1 = -1;
    L_0x0045:
        switch(r1) {
            case 0: goto L_0x004c;
            case 1: goto L_0x004a;
            case 2: goto L_0x0049;
            default: goto L_0x0048;
        };
    L_0x0048:
        return r0;
    L_0x0049:
        return r5;
    L_0x004a:
        r7 = 3;
        return r7;
    L_0x004c:
        return r0;
    L_0x004d:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.activity.home.RankingActivity.formatTopValue(java.util.List):int");
    }

    private void initDataList(List<AnchorEntity> list) {
        if (list != null) {
            this.mHeadView.initData(list, this.topTagValue);
            if (list.size() >= 1) {
                this.mAdapter.setNewData(list.subList(1, list.size()));
            }
            int i = 4;
            this.llEmptyView.setVisibility(list.size() == 0 ? 0 : 4);
            RecyclerView recyclerView = this.mRecyclerView;
            if (list.size() != 0) {
                i = 0;
            }
            recyclerView.setVisibility(i);
        }
    }

    private void putCharmDataList(List<AnchorEntity> list, int i) {
        switch (i) {
            case 1:
                this.listMap.put(11, list);
                break;
            case 2:
                this.listMap.put(12, list);
                break;
            case 3:
                this.listMap.put(13, list);
                break;
            default:
                return;
        }
    }

    private void putStrengthDataList(List<AnchorEntity> list, int i) {
        switch (i) {
            case 1:
                this.listMap.put(21, list);
                break;
            case 2:
                this.listMap.put(22, list);
                break;
            case 3:
                this.listMap.put(23, list);
                break;
            default:
                return;
        }
    }

    private List<AnchorEntity> getCharmDataList(int i) {
        switch (i) {
            case 1:
                return (List) this.listMap.get(11);
            case 2:
                return (List) this.listMap.get(12);
            case 3:
                return (List) this.listMap.get(13);
            default:
                return new ArrayList();
        }
    }

    private List<AnchorEntity> getStrengthDataList(int i) {
        switch (i) {
            case 1:
                return (List) this.listMap.get(21);
            case 2:
                return (List) this.listMap.get(22);
            case 3:
                return (List) this.listMap.get(23);
            default:
                return new ArrayList();
        }
    }

    private void removeData() {
        if (this.topTagValue == 4) {
            switch (this.dayTagValue) {
                case 1:
                    this.listMap.remove(11);
                    break;
                case 2:
                    this.listMap.remove(12);
                    break;
                case 3:
                    this.listMap.remove(13);
                    break;
                default:
                    return;
            }
        }
        switch (this.dayTagValue) {
            case 1:
                this.listMap.remove(21);
                break;
            case 2:
                this.listMap.remove(22);
                break;
            case 3:
                this.listMap.remove(23);
                break;
            default:
                return;
        }
    }

    public void onEventMainThreadSticky(BaseEvent baseEvent) {
        super.onEventMainThreadSticky(baseEvent);
        if (baseEvent instanceof LiveTopAttentionEvent) {
            removeData();
            sendRequest(false, true);
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

    private void showContentView() {
        this.ivLoadingView.setVisibility(4);
        this.llContentBg.setVisibility(0);
        this.ivLoadingView.stopLoading();
    }
}
