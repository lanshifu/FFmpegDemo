package com.tomatolive.library.ui.activity.mylive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.model.BannedEntity;
import com.tomatolive.library.model.MenuEntity;
import com.tomatolive.library.model.event.BannedEvent;
import com.tomatolive.library.model.event.BaseEvent;
import com.tomatolive.library.ui.adapter.BannedSearchAdapter;
import com.tomatolive.library.ui.adapter.HouseAdapter;
import com.tomatolive.library.ui.presenter.HouseSettingPresenter;
import com.tomatolive.library.ui.view.dialog.SureCancelDialog;
import com.tomatolive.library.ui.view.divider.RVDividerLinear;
import com.tomatolive.library.ui.view.emptyview.RecyclerIncomeEmptyView;
import com.tomatolive.library.ui.view.iview.IHouseSettingView;
import com.tomatolive.library.utils.t;
import com.tomatolive.library.utils.z;
import defpackage.mj;
import defpackage.ra;
import defpackage.wd;
import io.reactivex.disposables.b;
import io.reactivex.r;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.greenrobot.eventbus.c;

public class HouseSettingActivity extends BaseActivity<HouseSettingPresenter> implements IHouseSettingView {
    private AtomicInteger atomicIntegerTotalCount = new AtomicInteger(0);
    private EditText etSearch;
    private boolean isSearch = false;
    private String keyword = "";
    private HouseAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewSearch;
    private BannedSearchAdapter mSearchAdapter;
    private SmartRefreshLayout mSmartRefreshLayout;
    private TextView tvCancel;
    private TextView tvCurrentCount;

    public void onResultError(int i) {
    }

    /* Access modifiers changed, original: protected */
    public HouseSettingPresenter createPresenter() {
        return new HouseSettingPresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_banned_setting;
    }

    /* Access modifiers changed, original: protected */
    public View injectStateView() {
        return findViewById(R.id.fl_content_view);
    }

    public void initView(Bundle bundle) {
        int i = 0;
        this.isSearch = getIntent().getBooleanExtra("searchResult", false);
        setActivityRightIconTitle(getString(R.string.fq_my_live_house_setting), R.drawable.fq_ic_search_gray_dark, new -$$Lambda$HouseSettingActivity$VsdLFqxR57umRrlp-QMdNf0Rcjs(this));
        this.etSearch = (EditText) findViewById(R.id.et_search);
        this.tvCancel = (TextView) findViewById(R.id.tv_cancel);
        this.tvCurrentCount = (TextView) findViewById(R.id.tv_current_count);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.mRecyclerViewSearch = (RecyclerView) findViewById(R.id.recycler_search);
        this.mSmartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        this.mSmartRefreshLayout.b(this.isSearch ^ 1);
        this.etSearch.setHint(R.string.fq_my_live_house_manager_search_hint);
        int i2 = 8;
        findViewById(R.id.tb_prepare_title_bar).setVisibility(this.isSearch ? 8 : 0);
        this.tvCurrentCount.setVisibility(this.isSearch ? 8 : 0);
        View findViewById = findViewById(R.id.ll_search_bg);
        if (this.isSearch) {
            i2 = 0;
        }
        findViewById.setVisibility(i2);
        RecyclerView recyclerView = this.mRecyclerViewSearch;
        if (!this.isSearch) {
            i = 4;
        }
        recyclerView.setVisibility(i);
        findViewById(R.id.rl_content).setBackgroundColor(ContextCompat.getColor(this.mContext, this.isSearch ? R.color.fq_colorWhite : 17170445));
        initAdapter();
        initSearchAdapter();
        if (this.isSearch) {
            this.mStateView.showContent();
        } else {
            ((HouseSettingPresenter) this.mPresenter).getDataList(this.mStateView, this.isSearch, this.keyword, this.pageNum, true, false);
        }
    }

    public static /* synthetic */ void lambda$initView$0(HouseSettingActivity houseSettingActivity, View view) {
        Intent intent = new Intent(houseSettingActivity.mContext, HouseSettingActivity.class);
        intent.putExtra("searchResult", true);
        houseSettingActivity.startActivity(intent);
    }

    private void initAdapter() {
        ((DefaultItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mRecyclerView.addItemDecoration(new RVDividerLinear(this.mContext, R.color.fq_view_divider_color));
        this.mAdapter = new HouseAdapter(R.layout.fq_item_list_house_setting, this.isSearch);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.setEmptyView(new RecyclerIncomeEmptyView(this.mContext, 35));
    }

    private void initSearchAdapter() {
        this.mRecyclerViewSearch.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mRecyclerViewSearch.addItemDecoration(new RVDividerLinear(this.mContext, R.color.fq_view_divider_color));
        this.mSearchAdapter = new BannedSearchAdapter(R.layout.fq_item_list_search_setting);
        this.mRecyclerViewSearch.setAdapter(this.mSearchAdapter);
        this.mSearchAdapter.bindToRecyclerView(this.mRecyclerViewSearch);
    }

    public void initListener() {
        super.initListener();
        this.mStateView.setOnRetryClickListener(new -$$Lambda$HouseSettingActivity$TQn1X1I_2inBAORMgSPy54bA35c(this));
        this.mSmartRefreshLayout.a(new -$$Lambda$HouseSettingActivity$YCypfvizVNDKRFnk7j8x6STM3Oo(this)).a(new -$$Lambda$HouseSettingActivity$xnDl-b89fXs7bEr6pxFZt9nrWek(this));
        mj.a(this.etSearch).map(-$$Lambda$EV0Wr-jn67y9yssyeTjc0isZurk.INSTANCE).debounce(300, TimeUnit.MILLISECONDS).subscribeOn(wd.a()).observeOn(wd.a()).compose(bindToLifecycle()).subscribe(new r<String>() {
            public void onComplete() {
            }

            public void onError(Throwable th) {
            }

            public void onSubscribe(b bVar) {
            }

            public void onNext(String str) {
                if (TextUtils.isEmpty(str)) {
                    HouseSettingActivity.this.mRecyclerViewSearch.setVisibility(4);
                    HouseSettingActivity.this.mRecyclerView.setVisibility(0);
                    return;
                }
                HouseSettingActivity.this.mRecyclerViewSearch.setVisibility(0);
                HouseSettingActivity.this.mRecyclerView.setVisibility(4);
                HouseSettingActivity.this.mSearchAdapter.setNewData(HouseSettingActivity.this.getMenuList(str));
            }
        });
        this.mAdapter.setOnItemChildClickListener(new -$$Lambda$HouseSettingActivity$MyJ0xYWt1bhm8EaZGtvLQ0J8m3E(this));
        this.mSearchAdapter.setOnItemClickListener(new -$$Lambda$HouseSettingActivity$lGOCsRJX6y6Tr0AciuN4Nyyf9sc(this));
        this.tvCancel.setOnClickListener(new -$$Lambda$HouseSettingActivity$v3FIUQnJAR5LPMPS-oA6lNSoC2I(this));
    }

    public static /* synthetic */ void lambda$initListener$1(HouseSettingActivity houseSettingActivity) {
        houseSettingActivity.pageNum = 1;
        ((HouseSettingPresenter) houseSettingActivity.mPresenter).getDataList(houseSettingActivity.mStateView, houseSettingActivity.isSearch, houseSettingActivity.keyword, houseSettingActivity.pageNum, true, true);
    }

    public static /* synthetic */ void lambda$initListener$3(HouseSettingActivity houseSettingActivity, ra raVar) {
        houseSettingActivity.pageNum++;
        ((HouseSettingPresenter) houseSettingActivity.mPresenter).getDataList(houseSettingActivity.mStateView, houseSettingActivity.isSearch, houseSettingActivity.keyword, houseSettingActivity.pageNum, false, false);
        houseSettingActivity.mSmartRefreshLayout.h();
    }

    public static /* synthetic */ void lambda$initListener$5(HouseSettingActivity houseSettingActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        BannedEntity bannedEntity = (BannedEntity) baseQuickAdapter.getItem(i);
        if (bannedEntity != null && view.getId() == R.id.tv_banned) {
            if (TextUtils.equals(z.a().c(), bannedEntity.userId)) {
                houseSettingActivity.showToast(R.string.fq_dont_setting_house_manager_yourself);
            } else if (bannedEntity.isHouseManager()) {
                SureCancelDialog.newInstance(houseSettingActivity.getString(R.string.fq_sure_cancel_house_manager), new -$$Lambda$HouseSettingActivity$sIXBBbctf20mLkWY5mjyCigvQ3U(houseSettingActivity, bannedEntity, i)).show(houseSettingActivity.getSupportFragmentManager());
            } else {
                ((HouseSettingPresenter) houseSettingActivity.mPresenter).houseSetting(bannedEntity.userId, bannedEntity.isHouseManager() ? 2 : 1, i, bannedEntity);
            }
        }
    }

    public static /* synthetic */ void lambda$null$4(HouseSettingActivity houseSettingActivity, BannedEntity bannedEntity, int i, View view) {
        ((HouseSettingPresenter) houseSettingActivity.mPresenter).houseSetting(bannedEntity.userId, bannedEntity.isHouseManager() ? 2 : 1, i, bannedEntity);
    }

    public static /* synthetic */ void lambda$initListener$6(HouseSettingActivity houseSettingActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        t.b(houseSettingActivity.mActivity);
        houseSettingActivity.mRecyclerViewSearch.setVisibility(8);
        MenuEntity menuEntity = (MenuEntity) baseQuickAdapter.getItem(i);
        houseSettingActivity.keyword = menuEntity != null ? menuEntity.getMenuTitle() : "";
        ((HouseSettingPresenter) houseSettingActivity.mPresenter).getSearchUsersList(houseSettingActivity.mStateView, houseSettingActivity.keyword, true);
    }

    public static /* synthetic */ void lambda$initListener$7(HouseSettingActivity houseSettingActivity, View view) {
        t.b(houseSettingActivity.mActivity);
        houseSettingActivity.onBackPressed();
    }

    public void onDataListSuccess(int i, List<BannedEntity> list, boolean z, boolean z2) {
        this.atomicIntegerTotalCount.set(i);
        setCurrentCount(i);
        if (z2) {
            this.mAdapter.setNewData(list);
        } else {
            this.mAdapter.addData(list);
        }
        if (z) {
            this.mSmartRefreshLayout.i();
        }
    }

    public void onSearchDataListSuccess(List<BannedEntity> list) {
        if (list != null) {
            this.etSearch.setText("");
            this.mAdapter.setNewData(list);
        }
    }

    public void onHouseSettingSuccess(int i, BannedEntity bannedEntity) {
        bannedEntity.managerStatus = bannedEntity.isHouseManager() ? "0" : "1";
        this.mAdapter.setData(i, bannedEntity);
        showToast(bannedEntity.isHouseManager() ? R.string.fq_setting_house_manager_suc : R.string.fq_cancel_house_manager_suc);
        if (!(this.isSearch || bannedEntity.isHouseManager())) {
            this.mAdapter.remove(i);
            this.atomicIntegerTotalCount.decrementAndGet();
            setCurrentCount(this.atomicIntegerTotalCount.get());
        }
        if (this.isSearch) {
            c.a().c(new BannedEvent());
        }
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        super.onEventMainThread(baseEvent);
        if ((baseEvent instanceof BannedEvent) && !this.isSearch) {
            this.pageNum = 1;
            ((HouseSettingPresenter) this.mPresenter).getDataList(this.mStateView, false, this.keyword, this.pageNum, true, true);
        }
    }

    private List<MenuEntity> getMenuList(String str) {
        ArrayList arrayList = new ArrayList();
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setMenuTitle(str);
        arrayList.add(menuEntity);
        return arrayList;
    }

    private void setCurrentCount(int i) {
        if (i > 200) {
            i = 200;
        }
        this.tvCurrentCount.setVisibility(i > 0 ? 0 : 4);
        this.tvCurrentCount.setText(getString(R.string.fq_my_live_current_house_personal, new Object[]{Integer.valueOf(i), Integer.valueOf(200)}));
    }
}
