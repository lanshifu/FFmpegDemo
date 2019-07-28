package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.tomatolive.library.R;
import com.tomatolive.library.http.ApiRetrofit;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.function.ServerResultFunction;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.ui.adapter.RankingAdapter;
import com.tomatolive.library.ui.view.emptyview.RecyclerIncomeEmptyView;
import com.tomatolive.library.utils.b;
import defpackage.wd;
import defpackage.xl;
import io.reactivex.r;
import java.util.List;

public class RankingAllDialog extends BaseBottomDialogFragment {
    private static final String TOP_TAG_VALUE = "topTagValue";
    private RankingAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private OnAttentionAnchorListener onAttentionAnchorListener;
    private ProgressBar progressBar;
    private int topTagValue;

    public interface OnAttentionAnchorListener {
        void OnAttentionAnchor(AnchorEntity anchorEntity);
    }

    @SuppressLint({"ValidFragment"})
    private RankingAllDialog() {
    }

    public static RankingAllDialog newInstance(int i, OnAttentionAnchorListener onAttentionAnchorListener) {
        Bundle bundle = new Bundle();
        RankingAllDialog rankingAllDialog = new RankingAllDialog();
        bundle.putInt(TOP_TAG_VALUE, i);
        rankingAllDialog.setArguments(bundle);
        rankingAllDialog.setOnAttentionAnchorListener(onAttentionAnchorListener);
        return rankingAllDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_live_top_all;
    }

    public void initView(View view) {
        this.topTagValue = getArgumentsInt(TOP_TAG_VALUE, 4);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progress_wheel);
        ((DefaultItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mAdapter = new RankingAdapter(R.layout.fq_item_list_live_top_new, 4);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.setEmptyView(new RecyclerIncomeEmptyView(this.mContext, 42));
        sendRequest();
    }

    /* Access modifiers changed, original: protected */
    public void initListener(View view) {
        super.initListener(view);
        this.mAdapter.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (RankingAllDialog.this.topTagValue != 5) {
                    AnchorEntity anchorEntity = (AnchorEntity) baseQuickAdapter.getItem(i);
                    if (anchorEntity != null) {
                        LiveEntity a = b.a(anchorEntity);
                        if (b.a(RankingAllDialog.this.mContext)) {
                            RankingAllDialog.this.dismiss();
                            b.a(RankingAllDialog.this.mContext, 3, a);
                        }
                    }
                }
            }
        });
        this.mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (view.getId() == R.id.tv_attention && RankingAllDialog.this.onAttentionAnchorListener != null) {
                    AnchorEntity anchorEntity = (AnchorEntity) baseQuickAdapter.getItem(i);
                    if (anchorEntity != null && b.a(RankingAllDialog.this.mContext, anchorEntity.userId)) {
                        anchorEntity.followStatus = anchorEntity.isAttention() ? "0" : "1";
                        baseQuickAdapter.setData(i, anchorEntity);
                        RankingAllDialog.this.onAttentionAnchorListener.OnAttentionAnchor(anchorEntity);
                    }
                }
            }
        });
    }

    public double getHeightScale() {
        return this.maxHeightScale;
    }

    private void sendRequest() {
        if (this.topTagValue == 4) {
            ApiRetrofit.getInstance().getApiService().getCharmTopListService(new RequestParams().getHomeTopParams("all")).map(new ServerResultFunction<List<AnchorEntity>>() {
            }).subscribeOn(xl.b()).observeOn(wd.a()).compose(bindToLifecycle()).subscribe(new r<List<AnchorEntity>>() {
                public void onSubscribe(io.reactivex.disposables.b bVar) {
                    RankingAllDialog.this.showLoading(true);
                }

                public void onNext(List<AnchorEntity> list) {
                    if (list != null) {
                        RankingAllDialog.this.mAdapter.setType(4);
                        RankingAllDialog.this.mAdapter.setNewData(list);
                    }
                }

                public void onError(Throwable th) {
                    RankingAllDialog.this.showLoading(false);
                }

                public void onComplete() {
                    RankingAllDialog.this.showLoading(false);
                }
            });
        } else {
            ApiRetrofit.getInstance().getApiService().getStrengthTopListService(new RequestParams().getHomeStrengthTopParams("all")).map(new ServerResultFunction<List<AnchorEntity>>() {
            }).subscribeOn(xl.b()).observeOn(wd.a()).compose(bindToLifecycle()).subscribe(new r<List<AnchorEntity>>() {
                public void onSubscribe(io.reactivex.disposables.b bVar) {
                    RankingAllDialog.this.showLoading(true);
                }

                public void onNext(List<AnchorEntity> list) {
                    if (list != null) {
                        RankingAllDialog.this.mAdapter.setType(5);
                        RankingAllDialog.this.mAdapter.setNewData(list);
                    }
                }

                public void onError(Throwable th) {
                    RankingAllDialog.this.showLoading(false);
                }

                public void onComplete() {
                    RankingAllDialog.this.showLoading(false);
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

    private void setOnAttentionAnchorListener(OnAttentionAnchorListener onAttentionAnchorListener) {
        this.onAttentionAnchorListener = onAttentionAnchorListener;
    }
}
