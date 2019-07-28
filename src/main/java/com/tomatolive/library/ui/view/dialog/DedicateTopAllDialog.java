package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
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
import java.util.List;

public class DedicateTopAllDialog extends BaseBottomDialogFragment {
    private static final String ANCHORID_KEY = "anchorId_key";
    private DedicateTopAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar progressBar;

    public float getDimAmount() {
        return CropImageView.DEFAULT_ASPECT_RATIO;
    }

    @SuppressLint({"ValidFragment"})
    private DedicateTopAllDialog() {
    }

    public static DedicateTopAllDialog newInstance(String str) {
        Bundle bundle = new Bundle();
        DedicateTopAllDialog dedicateTopAllDialog = new DedicateTopAllDialog();
        bundle.putString(ANCHORID_KEY, str);
        dedicateTopAllDialog.setArguments(bundle);
        return dedicateTopAllDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_dedicate_top_all;
    }

    public void initView(View view) {
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progress_wheel);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mAdapter = new DedicateTopAdapter(R.layout.fq_item_list_dedicate_top_live, true);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.setEmptyView(new RecyclerIncomeEmptyView(this.mContext, 38));
        sendRequest(getArgumentsString(ANCHORID_KEY));
    }

    public double getHeightScale() {
        return this.maxHeightScale;
    }

    private void sendRequest(String str) {
        ApiRetrofit.getInstance().getApiService().getDedicateTopListService(new RequestParams().getContributionListParams("all", str)).map(new ServerResultFunction<List<AnchorEntity>>() {
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(xl.b()).observeOn(wd.a()).compose(bindToLifecycle()).subscribe(new r<List<AnchorEntity>>() {
            public void onSubscribe(b bVar) {
                DedicateTopAllDialog.this.showLoading(true);
            }

            public void onNext(List<AnchorEntity> list) {
                if (list != null) {
                    DedicateTopAllDialog.this.mAdapter.setNewData(list);
                }
            }

            public void onError(Throwable th) {
                DedicateTopAllDialog.this.showLoading(false);
            }

            public void onComplete() {
                DedicateTopAllDialog.this.showLoading(false);
            }
        });
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
}
