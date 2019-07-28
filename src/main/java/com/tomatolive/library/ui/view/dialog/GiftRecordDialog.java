package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tomatolive.library.R;
import com.tomatolive.library.http.ApiRetrofit;
import com.tomatolive.library.http.HttpResultPageModel;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.http.function.HttpResultFunction;
import com.tomatolive.library.http.function.ServerResultFunction;
import com.tomatolive.library.model.ReceiveGiftRecordEntity;
import com.tomatolive.library.ui.adapter.GiftRecordAdapter;
import com.tomatolive.library.ui.adapter.GiftRecordAdapter.OnGiftRecordListener;
import com.tomatolive.library.ui.view.emptyview.RecyclerIncomeEmptyView;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.ra;
import defpackage.wd;
import defpackage.xl;

public class GiftRecordDialog extends BaseBottomDialogFragment {
    private static final String LIVECOUNT = "livecount";
    private GiftRecordAdapter adapter;
    private boolean isDownFresh;
    private OnGiftRecordListener listener;
    private SmartRefreshLayout mSmartRefreshLayout;
    private int pageNum = 0;

    public float getDimAmount() {
        return CropImageView.DEFAULT_ASPECT_RATIO;
    }

    @SuppressLint({"ValidFragment"})
    private GiftRecordDialog() {
    }

    private void setOnGiftRecordListener(OnGiftRecordListener onGiftRecordListener) {
        this.listener = onGiftRecordListener;
    }

    public static GiftRecordDialog newInstance(OnGiftRecordListener onGiftRecordListener) {
        GiftRecordDialog giftRecordDialog = new GiftRecordDialog();
        giftRecordDialog.setOnGiftRecordListener(onGiftRecordListener);
        return giftRecordDialog;
    }

    public static GiftRecordDialog newInstance(int i, OnGiftRecordListener onGiftRecordListener) {
        GiftRecordDialog giftRecordDialog = new GiftRecordDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(LIVECOUNT, i);
        giftRecordDialog.setArguments(bundle);
        giftRecordDialog.setOnGiftRecordListener(onGiftRecordListener);
        return giftRecordDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_layout_bottom_gift_record_view;
    }

    public void initView(View view) {
        this.mSmartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        this.adapter = new GiftRecordAdapter(R.layout.fq_item_list_gift_record_view);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_operate);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        recyclerView.setAdapter(this.adapter);
        this.adapter.bindToRecyclerView(recyclerView);
        this.adapter.setEmptyView(new RecyclerIncomeEmptyView(this.mContext, 40));
        this.adapter.setHeaderAndEmpty(true);
        this.adapter.setOnGiftRecordListener(new -$$Lambda$GiftRecordDialog$BtfP8qqcnUQ7vYzAKFoOcaEPdB0(this));
        this.mSmartRefreshLayout.a(new -$$Lambda$GiftRecordDialog$dKFZO0YrmTSw1Vg5DondxpU3bNw(this)).a(new -$$Lambda$GiftRecordDialog$HQcBnv8MDQ15yxnidfoT5h1HBnI(this));
        this.isDownFresh = true;
        this.pageNum = 1;
        this.mSmartRefreshLayout.j();
        sendRequest();
    }

    public static /* synthetic */ void lambda$initView$0(GiftRecordDialog giftRecordDialog, ReceiveGiftRecordEntity receiveGiftRecordEntity) {
        if (giftRecordDialog.listener != null) {
            giftRecordDialog.listener.onItemClick(receiveGiftRecordEntity);
        }
    }

    public static /* synthetic */ void lambda$initView$1(GiftRecordDialog giftRecordDialog, ra raVar) {
        giftRecordDialog.isDownFresh = true;
        giftRecordDialog.pageNum = 1;
        giftRecordDialog.sendRequest();
        giftRecordDialog.mSmartRefreshLayout.g();
    }

    public static /* synthetic */ void lambda$initView$2(GiftRecordDialog giftRecordDialog, ra raVar) {
        giftRecordDialog.isDownFresh = false;
        giftRecordDialog.pageNum++;
        giftRecordDialog.sendRequest();
        giftRecordDialog.mSmartRefreshLayout.h();
    }

    public double getHeightScale() {
        return this.maxHeightScale;
    }

    private void sendRequest() {
        ApiRetrofit.getInstance().getApiService().getAnchorGiftRecordListService(new RequestParams().getReceiveGiftRecordParams(this.pageNum, getArgumentsInt(LIVECOUNT))).map(new ServerResultFunction<HttpResultPageModel<ReceiveGiftRecordEntity>>() {
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(xl.b()).observeOn(wd.a()).compose(bindToLifecycle()).subscribe(new HttpRxObserver(getContext(), new ResultCallBack<HttpResultPageModel<ReceiveGiftRecordEntity>>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(HttpResultPageModel<ReceiveGiftRecordEntity> httpResultPageModel) {
                if (httpResultPageModel != null && httpResultPageModel.dataList != null) {
                    if (GiftRecordDialog.this.isDownFresh) {
                        GiftRecordDialog.this.adapter.setNewData(httpResultPageModel.dataList);
                    } else {
                        GiftRecordDialog.this.adapter.addData(httpResultPageModel.dataList);
                    }
                    if (httpResultPageModel.isMorePage()) {
                        GiftRecordDialog.this.mSmartRefreshLayout.i();
                    }
                }
            }
        }));
    }
}
