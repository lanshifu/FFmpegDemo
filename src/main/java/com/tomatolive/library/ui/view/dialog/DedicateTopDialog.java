package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.http.ApiRetrofit;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.function.HttpResultFunction;
import com.tomatolive.library.http.function.ServerResultFunction;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.ui.adapter.DedicateTopAdapter;
import com.tomatolive.library.ui.view.emptyview.RecyclerIncomeEmptyView;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.wd;
import defpackage.xl;
import io.reactivex.disposables.b;
import io.reactivex.r;
import java.util.ArrayList;
import java.util.List;

public class DedicateTopDialog extends BaseBottomDialogFragment {
    private static final String ANCHORID_KEY = "anchorId_key";
    private static final int DAY_TOP_VALUE = 1;
    private static final String LIVE_RANK_CONFIG = "liveRankConfig";
    private static final String LIVE_TYPE = "liveType";
    private static final int MONTH_TOP_VALUE = 2;
    private static final int WEEK_TOP_VALUE = 3;
    private final int CHARM_DAY_KEY = 11;
    private final int CHARM_MONTH_KEY = 13;
    private final int CHARM_WEEK_KEY = 12;
    private int dayTagValue = 1;
    private SparseArray<List<AnchorEntity>> listMap = new SparseArray();
    private DedicateTopAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar progressBar;
    private TextView tvAllTop;
    private TextView tvDayTop;
    private TextView tvMonthTop;
    private TextView tvWeekTop;

    public float getDimAmount() {
        return CropImageView.DEFAULT_ASPECT_RATIO;
    }

    @SuppressLint({"ValidFragment"})
    private DedicateTopDialog() {
    }

    public static DedicateTopDialog newInstance(int i, String str, ArrayList<String> arrayList) {
        Bundle bundle = new Bundle();
        DedicateTopDialog dedicateTopDialog = new DedicateTopDialog();
        bundle.putInt(LIVE_TYPE, i);
        bundle.putString(ANCHORID_KEY, str);
        bundle.putStringArrayList(LIVE_RANK_CONFIG, arrayList);
        dedicateTopDialog.setArguments(bundle);
        return dedicateTopDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_dedicate_top;
    }

    public void initView(View view) {
        this.tvDayTop = (TextView) view.findViewById(R.id.tv_day_top);
        this.tvWeekTop = (TextView) view.findViewById(R.id.tv_week_top);
        this.tvMonthTop = (TextView) view.findViewById(R.id.tv_month_top);
        this.tvAllTop = (TextView) view.findViewById(R.id.tv_bottom_top);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progress_wheel);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mAdapter = new DedicateTopAdapter(R.layout.fq_item_list_dedicate_top_live, true);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.setEmptyView(new RecyclerIncomeEmptyView(this.mContext, 38));
        this.tvAllTop.getPaint().setFlags(8);
        this.tvAllTop.getPaint().setAntiAlias(true);
        initChangeView();
    }

    /* Access modifiers changed, original: protected */
    public void initListener(View view) {
        super.initListener(view);
        this.tvDayTop.setOnClickListener(new -$$Lambda$DedicateTopDialog$fOSp_FQIefamBhucBGCk4fvocK8(this));
        this.tvMonthTop.setOnClickListener(new -$$Lambda$DedicateTopDialog$b09MwUuheF0jGBOk29HAKo4L0fQ(this));
        this.tvWeekTop.setOnClickListener(new -$$Lambda$DedicateTopDialog$JtqFFmPKT58BPtfuP_hZ0oTgG1E(this));
        view.findViewById(R.id.tv_bottom_top).setOnClickListener(new -$$Lambda$DedicateTopDialog$wawTaAE8c-1vAe6vBzfuewEcr6M(this));
    }

    public static /* synthetic */ void lambda$initListener$3(DedicateTopDialog dedicateTopDialog, View view) {
        dedicateTopDialog.dismiss();
        DedicateTopAllDialog.newInstance(dedicateTopDialog.getArgumentsString(ANCHORID_KEY)).show(dedicateTopDialog.getFragmentManager());
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008e  */
    private void initChangeView() {
        /*
        r9 = this;
        r0 = "liveType";
        r1 = 2;
        r0 = r9.getArgumentsInt(r0, r1);
        r2 = 1;
        r3 = 0;
        if (r0 != r2) goto L_0x0023;
    L_0x000b:
        r0 = r9.tvDayTop;
        r0.setVisibility(r3);
        r0 = r9.tvWeekTop;
        r0.setVisibility(r3);
        r0 = r9.tvMonthTop;
        r0.setVisibility(r3);
        r0 = r9.tvAllTop;
        r0.setVisibility(r3);
        r9.hideTopTagView(r2);
        return;
    L_0x0023:
        r0 = r9.getArguments();
        r4 = "liveRankConfig";
        r0 = r0.getStringArrayList(r4);
        if (r0 == 0) goto L_0x00ae;
    L_0x002f:
        r4 = r0.isEmpty();
        if (r4 == 0) goto L_0x0037;
    L_0x0035:
        goto L_0x00ae;
    L_0x0037:
        r4 = r0.iterator();
    L_0x003b:
        r5 = r4.hasNext();
        if (r5 == 0) goto L_0x00a6;
    L_0x0041:
        r5 = r4.next();
        r5 = (java.lang.String) r5;
        r6 = -1;
        r7 = r5.hashCode();
        r8 = 96673; // 0x179a1 float:1.35468E-40 double:4.7763E-319;
        if (r7 == r8) goto L_0x007f;
    L_0x0051:
        r8 = 99228; // 0x1839c float:1.39048E-40 double:4.9025E-319;
        if (r7 == r8) goto L_0x0075;
    L_0x0056:
        r8 = 3645428; // 0x379ff4 float:5.108333E-39 double:1.8010807E-317;
        if (r7 == r8) goto L_0x006b;
    L_0x005b:
        r8 = 104080000; // 0x6342280 float:3.3879584E-35 double:5.14223524E-316;
        if (r7 == r8) goto L_0x0061;
    L_0x0060:
        goto L_0x0089;
    L_0x0061:
        r7 = "month";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x0089;
    L_0x0069:
        r5 = 2;
        goto L_0x008a;
    L_0x006b:
        r7 = "week";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x0089;
    L_0x0073:
        r5 = 1;
        goto L_0x008a;
    L_0x0075:
        r7 = "day";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x0089;
    L_0x007d:
        r5 = 0;
        goto L_0x008a;
    L_0x007f:
        r7 = "all";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x0089;
    L_0x0087:
        r5 = 3;
        goto L_0x008a;
    L_0x0089:
        r5 = -1;
    L_0x008a:
        switch(r5) {
            case 0: goto L_0x00a0;
            case 1: goto L_0x009a;
            case 2: goto L_0x0094;
            case 3: goto L_0x008e;
            default: goto L_0x008d;
        };
    L_0x008d:
        goto L_0x003b;
    L_0x008e:
        r5 = r9.tvAllTop;
        r5.setVisibility(r3);
        goto L_0x003b;
    L_0x0094:
        r5 = r9.tvMonthTop;
        r5.setVisibility(r3);
        goto L_0x003b;
    L_0x009a:
        r5 = r9.tvWeekTop;
        r5.setVisibility(r3);
        goto L_0x003b;
    L_0x00a0:
        r5 = r9.tvDayTop;
        r5.setVisibility(r3);
        goto L_0x003b;
    L_0x00a6:
        r0 = r9.formatTopValue(r0);
        r9.hideTopTagView(r0);
        return;
    L_0x00ae:
        r0 = r9.tvDayTop;
        r0.setVisibility(r3);
        r0 = r9.tvWeekTop;
        r0.setVisibility(r3);
        r0 = r9.tvMonthTop;
        r0.setVisibility(r3);
        r9.hideTopTagView(r2);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.view.dialog.DedicateTopDialog.initChangeView():void");
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
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.view.dialog.DedicateTopDialog.formatTopValue(java.util.List):int");
    }

    public double getHeightScale() {
        return this.maxHeightScale;
    }

    private void hideTopTagView(int i) {
        this.dayTagValue = i;
        boolean z = false;
        this.tvDayTop.setSelected(i == 1);
        this.tvMonthTop.setSelected(i == 2);
        TextView textView = this.tvWeekTop;
        if (i == 3) {
            z = true;
        }
        textView.setSelected(z);
        setTextViewDrawable(i);
        sendRequest(getArgumentsString(ANCHORID_KEY), i);
    }

    private void setTextViewDrawable(int i) {
        Drawable drawable = getResources().getDrawable(R.drawable.fq_shape_top_tag_red_divider);
        this.tvDayTop.setCompoundDrawablesWithIntrinsicBounds(null, null, null, i == 1 ? drawable : null);
        this.tvMonthTop.setCompoundDrawablesWithIntrinsicBounds(null, null, null, i == 2 ? drawable : null);
        TextView textView = this.tvWeekTop;
        if (i != 3) {
            drawable = null;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable);
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

    private void sendRequest(String str, final int i) {
        List charmDataList = getCharmDataList(i);
        if (charmDataList != null) {
            this.mAdapter.setNewData(charmDataList);
        } else {
            ApiRetrofit.getInstance().getApiService().getDedicateTopListService(new RequestParams().getContributionListParams(getDateType(), str)).map(new ServerResultFunction<List<AnchorEntity>>() {
            }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(xl.b()).observeOn(wd.a()).compose(bindToLifecycle()).subscribe(new r<List<AnchorEntity>>() {
                public void onSubscribe(b bVar) {
                    DedicateTopDialog.this.showLoading(true);
                }

                public void onNext(List<AnchorEntity> list) {
                    if (list != null) {
                        DedicateTopDialog.this.putCharmDataList(list, i);
                        DedicateTopDialog.this.mAdapter.setNewData(list);
                    }
                }

                public void onError(Throwable th) {
                    DedicateTopDialog.this.showLoading(false);
                }

                public void onComplete() {
                    DedicateTopDialog.this.showLoading(false);
                }
            });
        }
    }

    private void showLoading(boolean z) {
        int i = 4;
        this.progressBar.setVisibility(z ? 0 : 4);
        RecyclerView recyclerView = this.mRecyclerView;
        if (!z) {
            i = 0;
        }
        recyclerView.setVisibility(i);
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
            default:
                return new ArrayList();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.listMap != null) {
            this.listMap.clear();
            this.listMap = null;
        }
    }
}
