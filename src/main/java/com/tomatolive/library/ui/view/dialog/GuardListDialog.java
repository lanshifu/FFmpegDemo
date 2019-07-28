package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tomatolive.library.R;
import com.tomatolive.library.http.ApiRetrofit;
import com.tomatolive.library.http.HttpResultPageModel;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.http.function.HttpResultFunction;
import com.tomatolive.library.http.function.ServerResultFunction;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.ui.adapter.AnchorGuardAdapter;
import com.tomatolive.library.ui.view.emptyview.GuardListEmptyView;
import com.tomatolive.library.ui.view.headview.GuardListHeadView;
import com.tomatolive.library.ui.view.widget.StateView;
import com.tomatolive.library.utils.p;
import defpackage.ra;
import defpackage.wd;
import defpackage.xl;

public class GuardListDialog extends BaseDialogFragment {
    private static final String LIVE_TYPE = "liveType";
    private static final String SER_ITEM = "serItem";
    private GuardListEmptyView emptyView;
    private LiveEntity guardItem;
    private GuardListHeadView headView;
    private AnchorGuardAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private StateView mStateView;
    private OnClickListener openListener;
    private int pageNum = 1;
    private TextView tvContinue;
    private TextView tvOpen;

    public double getHeightScale() {
        return 0.7d;
    }

    @SuppressLint({"ValidFragment"})
    private GuardListDialog() {
    }

    public static GuardListDialog newInstance(int i, LiveEntity liveEntity, OnClickListener onClickListener) {
        GuardListDialog guardListDialog = new GuardListDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SER_ITEM, liveEntity);
        bundle.putInt(LIVE_TYPE, i);
        guardListDialog.setOpenGuardListener(onClickListener);
        guardListDialog.setArguments(bundle);
        return guardListDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_anchor_guard;
    }

    public void initView(View view) {
        this.guardItem = (LiveEntity) getArguments().getSerializable(SER_ITEM);
        int argumentsInt = getArgumentsInt(LIVE_TYPE, 2);
        this.mSmartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        ViewGroup viewGroup = (FrameLayout) view.findViewById(R.id.fl_content_view);
        this.tvOpen = (TextView) view.findViewById(R.id.tv_open);
        TextView textView = (TextView) view.findViewById(R.id.tv_guard_num);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_continue_bg);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_continue_tips);
        this.tvContinue = (TextView) view.findViewById(R.id.tv_continue);
        RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(R.id.rl_open_bg);
        View findViewById = view.findViewById(R.id.v_divider);
        this.emptyView = (GuardListEmptyView) view.findViewById(R.id.empty_view);
        this.mStateView = StateView.inject(viewGroup);
        findViewById.setVisibility(argumentsInt == 2 ? 0 : 4);
        relativeLayout2.setVisibility(argumentsInt == 2 ? 0 : 4);
        initAdapter();
        if (this.guardItem != null) {
            if (TextUtils.equals(this.guardItem.userGuardType, "0")) {
                relativeLayout.setVisibility(4);
                this.tvOpen.setVisibility(0);
                textView.setText(formatGuardCountStr());
            } else {
                relativeLayout.setVisibility(0);
                this.tvOpen.setVisibility(4);
                textView2.setText(Html.fromHtml(this.mContext.getString(R.string.fq_guard_open_maturity_date_tips, new Object[]{getGuardType(this.guardItem.userGuardType), this.guardItem.userGuardExpireTime})));
                textView.setText(formatGuardCountStr());
            }
        }
        sendRequest(true, true);
    }

    /* Access modifiers changed, original: protected */
    public void initListener(View view) {
        super.initListener(view);
        this.mStateView.setOnRetryClickListener(new -$$Lambda$GuardListDialog$6mTtmcrRWBFanmri4SiEZP5t7CE(this));
        this.mSmartRefreshLayout.a(new -$$Lambda$GuardListDialog$BJ0AGsi1Lj2E2NqT0SSF-UNHw94(this)).a(new -$$Lambda$GuardListDialog$vmOOwoNaZ9VH21UziaiH3sjHZdU(this));
        this.tvOpen.setOnClickListener(new -$$Lambda$GuardListDialog$KJtr5Cr1y9ggwEV8TAwdyxac44I(this));
        this.tvContinue.setOnClickListener(new -$$Lambda$GuardListDialog$b1jfADkpuCLDCri7I8i_YrXGvOI(this));
    }

    public static /* synthetic */ void lambda$initListener$0(GuardListDialog guardListDialog) {
        guardListDialog.pageNum = 1;
        guardListDialog.sendRequest(true, true);
    }

    public static /* synthetic */ void lambda$initListener$1(GuardListDialog guardListDialog, ra raVar) {
        guardListDialog.pageNum = 1;
        guardListDialog.sendRequest(true, false);
        guardListDialog.mSmartRefreshLayout.g();
    }

    public static /* synthetic */ void lambda$initListener$2(GuardListDialog guardListDialog, ra raVar) {
        guardListDialog.pageNum++;
        guardListDialog.sendRequest(false, false);
        guardListDialog.mSmartRefreshLayout.h();
    }

    public static /* synthetic */ void lambda$initListener$3(GuardListDialog guardListDialog, View view) {
        if (guardListDialog.openListener != null) {
            guardListDialog.dismiss();
            guardListDialog.openListener.onClick(view);
        }
    }

    public static /* synthetic */ void lambda$initListener$4(GuardListDialog guardListDialog, View view) {
        if (guardListDialog.openListener != null) {
            guardListDialog.dismiss();
            guardListDialog.openListener.onClick(view);
        }
    }

    private void initAdapter() {
        this.headView = new GuardListHeadView(this.mContext);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mAdapter = new AnchorGuardAdapter(R.layout.fq_item_list_anchor_guard);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.addHeaderView(this.headView);
    }

    private void sendRequest(final boolean z, boolean z2) {
        if (this.guardItem != null) {
            ApiRetrofit.getInstance().getApiService().getAnchorGuardListService(new RequestParams().getAnchorGuardListParams(this.guardItem.anchorId, this.pageNum)).map(new ServerResultFunction<HttpResultPageModel<AnchorEntity>>() {
            }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(xl.b()).observeOn(wd.a()).compose(bindToLifecycle()).subscribe(new HttpRxObserver(this.mContext, new ResultCallBack<HttpResultPageModel<AnchorEntity>>() {
                public void onError(int i, String str) {
                }

                public void onSuccess(HttpResultPageModel<AnchorEntity> httpResultPageModel) {
                    if (httpResultPageModel != null) {
                        if (z) {
                            GuardListDialog.this.headView.initData(httpResultPageModel.dataList);
                            if (httpResultPageModel.dataList.size() >= 1) {
                                if (p.a(((AnchorEntity) httpResultPageModel.dataList.get(0)).contribution, 0) > 0) {
                                    GuardListDialog.this.mAdapter.setNewData(httpResultPageModel.dataList.subList(1, httpResultPageModel.dataList.size()));
                                } else {
                                    GuardListDialog.this.mAdapter.setNewData(httpResultPageModel.dataList);
                                }
                            }
                        } else {
                            GuardListDialog.this.mAdapter.addData(httpResultPageModel.dataList);
                        }
                        if (httpResultPageModel.isMorePage()) {
                            GuardListDialog.this.mSmartRefreshLayout.i();
                        }
                        GuardListDialog.this.showContentView(httpResultPageModel.isNoEmptyData());
                    }
                }
            }, this.mStateView, z2));
        }
    }

    public void onDestroy() {
        super.onDestroy();
        setOpenGuardListener(null);
    }

    private void setOpenGuardListener(OnClickListener onClickListener) {
        this.openListener = onClickListener;
    }

    private java.lang.String getGuardType(java.lang.String r2) {
        /*
        r1 = this;
        r0 = r2.hashCode();
        switch(r0) {
            case 49: goto L_0x001c;
            case 50: goto L_0x0012;
            case 51: goto L_0x0008;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x0026;
    L_0x0008:
        r0 = "3";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0026;
    L_0x0010:
        r2 = 2;
        goto L_0x0027;
    L_0x0012:
        r0 = "2";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0026;
    L_0x001a:
        r2 = 1;
        goto L_0x0027;
    L_0x001c:
        r0 = "1";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0026;
    L_0x0024:
        r2 = 0;
        goto L_0x0027;
    L_0x0026:
        r2 = -1;
    L_0x0027:
        switch(r2) {
            case 0: goto L_0x003f;
            case 1: goto L_0x0036;
            case 2: goto L_0x002d;
            default: goto L_0x002a;
        };
    L_0x002a:
        r2 = "";
        return r2;
    L_0x002d:
        r2 = r1.mContext;
        r0 = com.tomatolive.library.R.string.fq_guard_year;
        r2 = r2.getString(r0);
        return r2;
    L_0x0036:
        r2 = r1.mContext;
        r0 = com.tomatolive.library.R.string.fq_guard_month;
        r2 = r2.getString(r0);
        return r2;
    L_0x003f:
        r2 = r1.mContext;
        r0 = com.tomatolive.library.R.string.fq_guard_week;
        r2 = r2.getString(r0);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.view.dialog.GuardListDialog.getGuardType(java.lang.String):java.lang.String");
    }

    private String formatGuardCountStr() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.mContext.getString(R.string.fq_guard_anchor));
        stringBuffer.append("(");
        stringBuffer.append(this.guardItem.anchorGuardCount);
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    private void showContentView(boolean z) {
        int i = 0;
        this.emptyView.setVisibility(z ? 4 : 0);
        RecyclerView recyclerView = this.mRecyclerView;
        if (!z) {
            i = 4;
        }
        recyclerView.setVisibility(i);
    }
}
