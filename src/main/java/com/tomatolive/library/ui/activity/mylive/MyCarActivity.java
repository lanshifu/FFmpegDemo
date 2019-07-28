package com.tomatolive.library.ui.activity.mylive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.model.MyCarEntity;
import com.tomatolive.library.ui.activity.home.WebViewActivity;
import com.tomatolive.library.ui.adapter.MyCarAdapter;
import com.tomatolive.library.ui.presenter.MyCarPresenter;
import com.tomatolive.library.ui.view.divider.RVDividerCarMall;
import com.tomatolive.library.ui.view.emptyview.RecyclerIncomeEmptyView;
import com.tomatolive.library.ui.view.iview.IMyCarView;
import com.tomatolive.library.utils.g;
import defpackage.ra;
import java.util.List;
import tv.danmaku.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener;

public class MyCarActivity extends BaseActivity<MyCarPresenter> implements IMyCarView {
    private MyCarAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;

    static /* synthetic */ void lambda$initListener$4(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }

    public void onDataListFail() {
    }

    public void onResultError(int i) {
    }

    public void onUseCarFail() {
    }

    /* Access modifiers changed, original: protected */
    public MyCarPresenter createPresenter() {
        return new MyCarPresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_car_my;
    }

    /* Access modifiers changed, original: protected */
    public View injectStateView() {
        return findViewById(R.id.fl_content_view);
    }

    public void initView(Bundle bundle) {
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.mSmartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        setActivityRightIconTitle(getString(R.string.fq_my_live_my_garage), R.drawable.fq_ic_my_live_car_help, new -$$Lambda$MyCarActivity$EF2hL1TDP2S0mS-KuIq5TF24lDw(this));
        initAdapter();
        ((MyCarPresenter) this.mPresenter).getMyCar(this.mStateView, this.pageNum, true, true);
    }

    public static /* synthetic */ void lambda$initView$0(MyCarActivity myCarActivity, View view) {
        Intent intent = new Intent(myCarActivity.mContext, WebViewActivity.class);
        intent.putExtra(OnNativeInvokeListener.ARG_URL, g.i("carDesc.html"));
        intent.putExtra("title", myCarActivity.mContext.getString(R.string.fq_car_desc));
        myCarActivity.mContext.startActivity(intent);
    }

    private void initAdapter() {
        ((DefaultItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 2));
        this.mRecyclerView.addItemDecoration(new RVDividerCarMall(this.mContext, 17170445));
        this.mAdapter = new MyCarAdapter(R.layout.fq_item_list_car_mall);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setEmptyView(new RecyclerIncomeEmptyView(this.mContext, 41));
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
    }

    public void initListener() {
        super.initListener();
        this.mStateView.setOnRetryClickListener(new -$$Lambda$MyCarActivity$S179k1720c5BQmw5regG-mY8hok(this));
        this.mSmartRefreshLayout.a(new -$$Lambda$MyCarActivity$DoHMNChda3-98jVEl_k-tZWL--4(this)).a(new -$$Lambda$MyCarActivity$0YQB33DZRRdfXhhR_BZF-RbbnxA(this));
        this.mAdapter.setOnItemClickListener(-$$Lambda$MyCarActivity$DK4Qpf8wcNBG3O8Ng4UZBKF8_s4.INSTANCE);
        this.mAdapter.setOnItemChildClickListener(new -$$Lambda$MyCarActivity$iLx04xocXDa376tXPvX_J2Hoj0I(this));
    }

    public static /* synthetic */ void lambda$initListener$1(MyCarActivity myCarActivity) {
        myCarActivity.pageNum = 1;
        ((MyCarPresenter) myCarActivity.mPresenter).getMyCar(myCarActivity.mStateView, myCarActivity.pageNum, true, true);
    }

    public static /* synthetic */ void lambda$initListener$2(MyCarActivity myCarActivity, ra raVar) {
        myCarActivity.pageNum = 1;
        ((MyCarPresenter) myCarActivity.mPresenter).getMyCar(myCarActivity.mStateView, myCarActivity.pageNum, false, true);
        myCarActivity.mSmartRefreshLayout.g();
    }

    public static /* synthetic */ void lambda$initListener$3(MyCarActivity myCarActivity, ra raVar) {
        myCarActivity.pageNum++;
        ((MyCarPresenter) myCarActivity.mPresenter).getMyCar(myCarActivity.mStateView, myCarActivity.pageNum, false, false);
        myCarActivity.mSmartRefreshLayout.h();
    }

    public static /* synthetic */ void lambda$initListener$5(MyCarActivity myCarActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        MyCarEntity myCarEntity = (MyCarEntity) baseQuickAdapter.getItem(i);
        if (view.getId() == R.id.tv_money && myCarEntity != null) {
            myCarEntity.isUsed = myCarEntity.isEquipage() ? "0" : "1";
            baseQuickAdapter.setData(i, myCarEntity);
            myCarActivity.showToast(myCarEntity.isEquipage() ? R.string.fq_car_equipage_success_tips : R.string.fq_car_equipage_cancel_tips);
            ((MyCarPresenter) myCarActivity.mPresenter).useCar(myCarEntity);
        }
    }

    public void onDataListSuccess(List<MyCarEntity> list, boolean z, boolean z2) {
        this.mAdapter.setNewData(list);
        if (z) {
            this.mSmartRefreshLayout.i();
        }
    }

    public void onUseCarSuccess(MyCarEntity myCarEntity) {
        ((MyCarPresenter) this.mPresenter).getMyCar(this.mStateView, this.pageNum, false, true);
    }
}
