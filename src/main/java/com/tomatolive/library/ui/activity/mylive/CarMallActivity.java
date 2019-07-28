package com.tomatolive.library.ui.activity.mylive;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.model.CarEntity;
import com.tomatolive.library.model.CarHistoryRecordEntity;
import com.tomatolive.library.ui.adapter.CarMallAdapter;
import com.tomatolive.library.ui.interfaces.impl.SimpleAnimatorListener;
import com.tomatolive.library.ui.presenter.CarMallPresenter;
import com.tomatolive.library.ui.view.dialog.CarBuyDialog;
import com.tomatolive.library.ui.view.divider.RVDividerCarMall;
import com.tomatolive.library.ui.view.emptyview.RecyclerIncomeEmptyView;
import com.tomatolive.library.ui.view.headview.CarMallHeadView;
import com.tomatolive.library.ui.view.iview.ICarMallView;
import com.tomatolive.library.utils.k;
import defpackage.ra;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CarMallActivity extends BaseActivity<CarMallPresenter> implements Callback, ICarMallView {
    private static final int CAR_NOTICE = 10004;
    private CarMallAdapter mAdapter;
    private boolean mCanShowNotice = true;
    private ConcurrentLinkedQueue<CarHistoryRecordEntity> mCarNoticeQueue = new ConcurrentLinkedQueue();
    private Handler mHandler;
    private CarMallHeadView mHeadView;
    private final long mPlayPeriod = 9000;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;

    public void onDataListFail() {
    }

    public void onResultError(int i) {
    }

    /* Access modifiers changed, original: protected */
    public CarMallPresenter createPresenter() {
        return new CarMallPresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_car_mall;
    }

    /* Access modifiers changed, original: protected */
    public View injectStateView() {
        return findViewById(R.id.fl_content_view);
    }

    public void initView(Bundle bundle) {
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.mSmartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        this.mHandler = k.a().a(getClass().getName(), this);
        setActivityRightTitle(getString(R.string.fq_my_live_car_mall), getString(R.string.fq_my_live_my_garage), new -$$Lambda$CarMallActivity$7oZbCDEfzXo3_fISX7k5RD7Rm2k(this));
        initAdapter();
        ((CarMallPresenter) this.mPresenter).getCarPurchaseCarouselRecord();
        ((CarMallPresenter) this.mPresenter).getCarList(this.mStateView, this.pageNum, true, true);
    }

    private void initAdapter() {
        this.mAdapter = new CarMallAdapter(R.layout.fq_item_list_car_mall);
        this.mHeadView = new CarMallHeadView(this.mContext);
        ((DefaultItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 2));
        this.mRecyclerView.addItemDecoration(new RVDividerCarMall(this.mContext, 17170445, true));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setEmptyView(new RecyclerIncomeEmptyView(this.mContext, 41));
        this.mAdapter.addHeaderView(this.mHeadView);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
    }

    public void initListener() {
        super.initListener();
        this.mStateView.setOnRetryClickListener(new -$$Lambda$CarMallActivity$sZw9g_qPnl7Ix8HFROafBk_3uGE(this));
        this.mSmartRefreshLayout.a(new -$$Lambda$CarMallActivity$rxMTV4FlMdJgdtxyLiqn2gKl1Ys(this)).a(new -$$Lambda$CarMallActivity$b7KZBoSBgvRz0ppQHhH1P_kv9sQ(this));
        this.mHeadView.setCarNoticeAnimListener(new SimpleAnimatorListener() {
            public void onAnimationStart(Animator animator) {
                CarMallActivity.this.mCanShowNotice = false;
            }

            public void onAnimationEnd(Animator animator) {
                CarMallActivity.this.mCanShowNotice = true;
                if (CarMallActivity.this.mCarNoticeQueue.isEmpty()) {
                    CarMallActivity.this.mHeadView.setVisibility(8);
                }
            }

            public void onAnimationCancel(Animator animator) {
                CarMallActivity.this.mCanShowNotice = true;
            }
        });
        this.mAdapter.setOnItemClickListener(new -$$Lambda$CarMallActivity$DKr38EGB28U8hOWmxY6bH-LNyFQ(this));
        this.mAdapter.setOnItemChildClickListener(new -$$Lambda$CarMallActivity$dFY5JXbBqlBcfO4HTBjaIsFM--8(this));
    }

    public static /* synthetic */ void lambda$initListener$1(CarMallActivity carMallActivity) {
        carMallActivity.pageNum = 1;
        ((CarMallPresenter) carMallActivity.mPresenter).getCarList(carMallActivity.mStateView, carMallActivity.pageNum, true, true);
    }

    public static /* synthetic */ void lambda$initListener$2(CarMallActivity carMallActivity, ra raVar) {
        carMallActivity.pageNum = 1;
        ((CarMallPresenter) carMallActivity.mPresenter).getCarList(carMallActivity.mStateView, carMallActivity.pageNum, false, true);
        carMallActivity.mSmartRefreshLayout.g();
    }

    public static /* synthetic */ void lambda$initListener$3(CarMallActivity carMallActivity, ra raVar) {
        carMallActivity.pageNum++;
        ((CarMallPresenter) carMallActivity.mPresenter).getCarList(carMallActivity.mStateView, carMallActivity.pageNum, false, false);
        carMallActivity.mSmartRefreshLayout.h();
    }

    public static /* synthetic */ void lambda$initListener$4(CarMallActivity carMallActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        CarEntity carEntity = (CarEntity) baseQuickAdapter.getItem(i);
        if (carEntity != null) {
            Intent intent = new Intent(carMallActivity.mContext, CarMallDetailActivity.class);
            intent.putExtra("resultItem", carEntity);
            carMallActivity.startActivity(intent);
        }
    }

    public static /* synthetic */ void lambda$initListener$6(CarMallActivity carMallActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        CarEntity carEntity = (CarEntity) baseQuickAdapter.getItem(i);
        if (view.getId() == R.id.tv_money && carEntity != null) {
            if (carEntity.isPublicCar()) {
                CarBuyDialog.newInstance(carEntity, new -$$Lambda$CarMallActivity$76bF_Hcbjut5yXn_Lsca9BBfs_Y(carMallActivity, carEntity)).show(carMallActivity.getSupportFragmentManager());
            } else {
                carMallActivity.showToast(R.string.fq_private_car_buy_tips);
            }
        }
    }

    public void onBuyCarSuccess() {
        showToast(R.string.fq_purchase_succeeded);
    }

    public void onDataListSuccess(List<CarEntity> list, boolean z, boolean z2) {
        this.mAdapter.setNewData(list);
        if (z) {
            this.mSmartRefreshLayout.i();
        }
    }

    public void onGetCarPurchaseCarouselRecordSuccess(List<CarHistoryRecordEntity> list) {
        if (this.mCarNoticeQueue == null) {
            this.mCarNoticeQueue = new ConcurrentLinkedQueue();
        } else {
            this.mCarNoticeQueue.clear();
        }
        this.mCarNoticeQueue.addAll(list);
        if (this.mHandler.hasMessages(10004) || list.isEmpty()) {
            this.mHeadView.setVisibility(8);
        } else {
            this.mHandler.sendEmptyMessage(10004);
        }
    }

    public void onGetCarPurchaseCarouselRecordFail() {
        this.mHeadView.setVisibility(8);
    }

    public boolean handleMessage(Message message) {
        if (message.what == 10004) {
            dealCarNotice();
        }
        return true;
    }

    private void dealCarNotice() {
        if (this.mCanShowNotice) {
            this.mCanShowNotice = false;
            CarHistoryRecordEntity carHistoryRecordEntity = (CarHistoryRecordEntity) this.mCarNoticeQueue.poll();
            if (carHistoryRecordEntity != null) {
                runOnUiThread(new -$$Lambda$CarMallActivity$Rrj-3gYAlWgovarPSbG2qcgOvfk(this, carHistoryRecordEntity));
            }
        }
        if (this.mCarNoticeQueue != null && !this.mCarNoticeQueue.isEmpty() && this.mHandler != null) {
            this.mHandler.sendEmptyMessage(10004);
        }
    }

    public static /* synthetic */ void lambda$dealCarNotice$7(CarMallActivity carMallActivity, CarHistoryRecordEntity carHistoryRecordEntity) {
        carMallActivity.mHeadView.setVisibility(0);
        carMallActivity.mHeadView.setCarNoticeNoticeAnim(carHistoryRecordEntity.getUserName(), carHistoryRecordEntity.getPostTime(), carHistoryRecordEntity.getCarName(), 9000);
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        k.a().b();
        if (this.mCarNoticeQueue != null) {
            this.mCarNoticeQueue.clear();
        }
        if (this.mHeadView != null) {
            this.mHeadView.onDestroy();
        }
    }
}
