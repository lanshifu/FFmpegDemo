package com.tomatolive.library.ui.activity.mylive;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.model.db.WatchRecordEntity;
import com.tomatolive.library.ui.adapter.WatchRecordAdapter;
import com.tomatolive.library.ui.presenter.WatchRecordPresenter;
import com.tomatolive.library.ui.view.dialog.SureCancelDialog;
import com.tomatolive.library.ui.view.divider.RVDividerLinear;
import com.tomatolive.library.ui.view.emptyview.RecyclerIncomeEmptyView;
import com.tomatolive.library.ui.view.iview.IWatchRecordView;
import com.tomatolive.library.ui.view.widget.StateView.OnRetryClickListener;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.c;
import com.tomatolive.library.utils.z;
import defpackage.wd;
import defpackage.wm;
import defpackage.xl;
import io.reactivex.k;
import io.reactivex.r;
import java.util.List;

public class WatchRecordActivity extends BaseActivity<WatchRecordPresenter> implements IWatchRecordView {
    private WatchRecordAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;

    public void onDataListFail() {
    }

    public void onResultError(int i) {
    }

    /* Access modifiers changed, original: protected */
    public WatchRecordPresenter createPresenter() {
        return new WatchRecordPresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_watch_record;
    }

    /* Access modifiers changed, original: protected */
    public View injectStateView() {
        return findViewById(R.id.fl_content_view);
    }

    public void initView(Bundle bundle) {
        setActivityRightTitle(R.string.fq_my_live_watch_record, R.string.fq_text_history_clear, new OnClickListener() {
            public void onClick(View view) {
                if (WatchRecordActivity.this.mAdapter == null || WatchRecordActivity.this.mAdapter.getData().size() != 0) {
                    SureCancelDialog.newInstance(WatchRecordActivity.this.getString(R.string.fq_sure_clear_all_record), new OnClickListener() {
                        public void onClick(View view) {
                            c.b(WatchRecordEntity.class);
                            WatchRecordActivity.this.mAdapter.clearAll();
                        }
                    }).show(WatchRecordActivity.this.getSupportFragmentManager());
                }
            }
        });
        this.mSmartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.mSmartRefreshLayout.c(false);
        this.mSmartRefreshLayout.b(false);
        initAdapter();
        loadLocalData();
    }

    private void initAdapter() {
        ((DefaultItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mRecyclerView.addItemDecoration(new RVDividerLinear(this.mContext, R.color.fq_view_divider_color));
        this.mAdapter = new WatchRecordAdapter(R.layout.fq_item_list_watch_record);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.setEmptyView(new RecyclerIncomeEmptyView(this.mContext, 39));
    }

    public void initListener() {
        super.initListener();
        this.mStateView.setOnRetryClickListener(new OnRetryClickListener() {
            public void onRetryClick() {
                WatchRecordActivity.this.pageNum = 1;
                WatchRecordActivity.this.loadLocalData();
            }
        });
        this.mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, final int i) {
                final LiveEntity access$200 = WatchRecordActivity.this.formatLiveEntity((WatchRecordEntity) baseQuickAdapter.getItem(i));
                if (view.getId() != R.id.rl_content) {
                    if (view.getId() == R.id.tv_delete) {
                        SureCancelDialog.newInstance(WatchRecordActivity.this.getString(R.string.fq_sure_del_all_record), new OnClickListener() {
                            public void onClick(View view) {
                                c.b(WatchRecordEntity.class, "liveId = ?", access$200.liveId);
                                WatchRecordActivity.this.mAdapter.remove(i);
                            }
                        }).show(WatchRecordActivity.this.getSupportFragmentManager());
                    }
                } else if (b.a(WatchRecordActivity.this.mContext)) {
                    b.a(WatchRecordActivity.this.mContext, 1, access$200);
                }
            }
        });
    }

    public void onDataListSuccess(List<LiveEntity> list, boolean z, boolean z2) {
        if (z) {
            this.mSmartRefreshLayout.i();
        }
    }

    public void onDeleteSuccess(int i) {
        if (i > -1) {
            this.mAdapter.remove(i);
        }
    }

    public void onDeleteAllSuccess() {
        this.mAdapter.clearAll();
        this.mSmartRefreshLayout.i();
    }

    private void loadLocalData() {
        k.just(Boolean.valueOf(true)).map(new wm<Boolean, List<WatchRecordEntity>>() {
            public List<WatchRecordEntity> apply(Boolean bool) {
                return c.a(WatchRecordEntity.class, "liveTime desc", "userId = ?", z.a().c());
            }
        }).subscribeOn(xl.b()).observeOn(wd.a()).compose(bindToLifecycle()).subscribe(new r<List<WatchRecordEntity>>() {
            public void onSubscribe(io.reactivex.disposables.b bVar) {
                if (WatchRecordActivity.this.mStateView != null) {
                    WatchRecordActivity.this.mStateView.showLoading();
                }
            }

            public void onNext(List<WatchRecordEntity> list) {
                WatchRecordActivity.this.mAdapter.setNewData(list);
            }

            public void onError(Throwable th) {
                if (WatchRecordActivity.this.mStateView != null) {
                    WatchRecordActivity.this.mStateView.showRetry();
                }
            }

            public void onComplete() {
                if (WatchRecordActivity.this.mStateView != null) {
                    WatchRecordActivity.this.mStateView.showContent();
                }
            }
        });
    }

    private LiveEntity formatLiveEntity(WatchRecordEntity watchRecordEntity) {
        LiveEntity liveEntity = new LiveEntity();
        if (watchRecordEntity == null) {
            return liveEntity;
        }
        liveEntity.liveId = watchRecordEntity.liveId;
        liveEntity.liveCoverUrl = watchRecordEntity.coverUrl;
        liveEntity.tag = watchRecordEntity.label;
        liveEntity.topic = watchRecordEntity.title;
        liveEntity.nickname = watchRecordEntity.anchorNickname;
        return liveEntity;
    }
}
