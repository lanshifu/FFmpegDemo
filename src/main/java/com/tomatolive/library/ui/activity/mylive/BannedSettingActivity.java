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
import com.tomatolive.library.ui.adapter.BannedAdapter;
import com.tomatolive.library.ui.adapter.BannedSearchAdapter;
import com.tomatolive.library.ui.presenter.BannedPresenter;
import com.tomatolive.library.ui.view.dialog.BottomDialogUtils;
import com.tomatolive.library.ui.view.dialog.MyLiveBannedDialog;
import com.tomatolive.library.ui.view.divider.RVDividerLinear;
import com.tomatolive.library.ui.view.emptyview.RecyclerIncomeEmptyView;
import com.tomatolive.library.ui.view.iview.IBannedView;
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
import org.greenrobot.eventbus.c;

public class BannedSettingActivity extends BaseActivity<BannedPresenter> implements IBannedView {
    private EditText etSearch;
    private boolean isSearch = false;
    private String keyword = "";
    private BannedAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewSearch;
    private BannedSearchAdapter mSearchAdapter;
    private SmartRefreshLayout mSmartRefreshLayout;
    private TextView tvCancel;
    private TextView tvCurrentCount;

    public void onResultError(int i) {
    }

    /* Access modifiers changed, original: protected */
    public BannedPresenter createPresenter() {
        return new BannedPresenter(this.mContext, this);
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
        setActivityRightIconTitle(getString(R.string.fq_my_live_banned_setting), R.drawable.fq_ic_search_gray_dark, new -$$Lambda$BannedSettingActivity$UoaXWNP6ta1WnvwJTQ8pPez9IK0(this));
        this.etSearch = (EditText) findViewById(R.id.et_search);
        this.tvCancel = (TextView) findViewById(R.id.tv_cancel);
        this.tvCurrentCount = (TextView) findViewById(R.id.tv_current_count);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.mRecyclerViewSearch = (RecyclerView) findViewById(R.id.recycler_search);
        this.mSmartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        this.mSmartRefreshLayout.b(this.isSearch ^ 1);
        this.etSearch.setHint(R.string.fq_my_live_banned_search_hint);
        int i2 = 8;
        findViewById(R.id.tb_prepare_title_bar).setVisibility(this.isSearch ? 8 : 0);
        findViewById(R.id.ll_search_bg).setVisibility(this.isSearch ? 0 : 8);
        TextView textView = this.tvCurrentCount;
        if (!this.isSearch) {
            i2 = 0;
        }
        textView.setVisibility(i2);
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
            ((BannedPresenter) this.mPresenter).getDataList(this.mStateView, this.isSearch, this.keyword, this.pageNum, true, false);
        }
    }

    public static /* synthetic */ void lambda$initView$0(BannedSettingActivity bannedSettingActivity, View view) {
        Intent intent = new Intent(bannedSettingActivity.mContext, BannedSettingActivity.class);
        intent.putExtra("searchResult", true);
        bannedSettingActivity.startActivity(intent);
    }

    private void initAdapter() {
        ((DefaultItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mRecyclerView.addItemDecoration(new RVDividerLinear(this.mContext, R.color.fq_view_divider_color));
        this.mAdapter = new BannedAdapter(R.layout.fq_item_list_banned_setting);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.setEmptyView(new RecyclerIncomeEmptyView(this.mContext, 36));
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
        this.mStateView.setOnRetryClickListener(new -$$Lambda$BannedSettingActivity$3ok-tmh2YlZTnhx_v9fGLxu4SBs(this));
        this.mSmartRefreshLayout.a(new -$$Lambda$BannedSettingActivity$6PASvIr7aXycqq2JTBUhl26UkBI(this)).a(new -$$Lambda$BannedSettingActivity$3ZdX_Bk2MQurfuv9yBKuXAhJLE0(this));
        mj.a(this.etSearch).map(-$$Lambda$EV0Wr-jn67y9yssyeTjc0isZurk.INSTANCE).debounce(300, TimeUnit.MILLISECONDS).subscribeOn(wd.a()).observeOn(wd.a()).compose(bindToLifecycle()).subscribe(new r<String>() {
            public void onComplete() {
            }

            public void onError(Throwable th) {
            }

            public void onSubscribe(b bVar) {
            }

            public void onNext(String str) {
                if (TextUtils.isEmpty(str)) {
                    BannedSettingActivity.this.mRecyclerViewSearch.setVisibility(4);
                    BannedSettingActivity.this.mRecyclerView.setVisibility(0);
                    return;
                }
                BannedSettingActivity.this.mRecyclerViewSearch.setVisibility(0);
                BannedSettingActivity.this.mRecyclerView.setVisibility(4);
                BannedSettingActivity.this.mSearchAdapter.setNewData(BannedSettingActivity.this.getMenuList(str));
            }
        });
        this.mAdapter.setOnItemChildClickListener(new -$$Lambda$BannedSettingActivity$WazH9QglIgeoMOvZY3PAOcz05So(this));
        this.mSearchAdapter.setOnItemClickListener(new -$$Lambda$BannedSettingActivity$Aq0EHz0ZtfxzuXVUejVCBK9S2e8(this));
        this.tvCancel.setOnClickListener(new -$$Lambda$BannedSettingActivity$8VHcBNkBWOKKIbpDvRWtqffPPYs(this));
    }

    public static /* synthetic */ void lambda$initListener$1(BannedSettingActivity bannedSettingActivity) {
        bannedSettingActivity.pageNum = 1;
        ((BannedPresenter) bannedSettingActivity.mPresenter).getDataList(bannedSettingActivity.mStateView, bannedSettingActivity.isSearch, bannedSettingActivity.keyword, bannedSettingActivity.pageNum, true, true);
    }

    public static /* synthetic */ void lambda$initListener$3(BannedSettingActivity bannedSettingActivity, ra raVar) {
        bannedSettingActivity.pageNum++;
        ((BannedPresenter) bannedSettingActivity.mPresenter).getDataList(bannedSettingActivity.mStateView, bannedSettingActivity.isSearch, bannedSettingActivity.keyword, bannedSettingActivity.pageNum, false, false);
        bannedSettingActivity.mSmartRefreshLayout.h();
    }

    public static /* synthetic */ void lambda$initListener$6(BannedSettingActivity bannedSettingActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        BannedEntity bannedEntity = (BannedEntity) baseQuickAdapter.getItem(i);
        if (bannedEntity != null && view.getId() == R.id.tv_banned) {
            if (TextUtils.equals(z.a().c(), bannedEntity.userId)) {
                bannedSettingActivity.showToast(R.string.fq_dont_setting_bannd_yourself);
            } else if (bannedEntity.isBanned()) {
                MyLiveBannedDialog.newInstance(bannedEntity.name, new -$$Lambda$BannedSettingActivity$ptmdHzYpA7E1h7xVTMwa6L1Qa7I(bannedSettingActivity, bannedEntity, baseQuickAdapter, i)).show(bannedSettingActivity.getSupportFragmentManager());
            } else {
                BottomDialogUtils.showBannedDialog(bannedSettingActivity.mContext, new -$$Lambda$BannedSettingActivity$a0QHmU4A-pLJQUV2TE0z2yna_bo(bannedSettingActivity, bannedEntity, baseQuickAdapter, i));
            }
        }
    }

    public static /* synthetic */ void lambda$null$4(BannedSettingActivity bannedSettingActivity, BannedEntity bannedEntity, BaseQuickAdapter baseQuickAdapter, int i, View view) {
        bannedEntity.banPostStatus = bannedEntity.isBanned() ? "0" : "1";
        baseQuickAdapter.setData(i, bannedEntity);
        ((BannedPresenter) bannedSettingActivity.mPresenter).bannedSetting(bannedEntity.userId, bannedEntity.isBanned() ? 1 : 2, bannedEntity.duration, i, bannedEntity);
    }

    public static /* synthetic */ void lambda$null$5(BannedSettingActivity bannedSettingActivity, BannedEntity bannedEntity, BaseQuickAdapter baseQuickAdapter, int i, long j) {
        bannedEntity.banPostStatus = bannedEntity.isBanned() ? "0" : "1";
        bannedEntity.duration = String.valueOf(j);
        baseQuickAdapter.setData(i, bannedEntity);
        ((BannedPresenter) bannedSettingActivity.mPresenter).bannedSetting(bannedEntity.userId, bannedEntity.isBanned() ? 1 : 2, String.valueOf(j), i, bannedEntity);
    }

    public static /* synthetic */ void lambda$initListener$7(BannedSettingActivity bannedSettingActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        t.b(bannedSettingActivity.mActivity);
        bannedSettingActivity.mRecyclerViewSearch.setVisibility(8);
        MenuEntity menuEntity = (MenuEntity) baseQuickAdapter.getItem(i);
        if (menuEntity != null) {
            bannedSettingActivity.keyword = menuEntity.getMenuTitle();
            ((BannedPresenter) bannedSettingActivity.mPresenter).getSearchUsersList(bannedSettingActivity.mStateView, menuEntity.getMenuTitle(), true);
        }
    }

    public static /* synthetic */ void lambda$initListener$8(BannedSettingActivity bannedSettingActivity, View view) {
        t.b(bannedSettingActivity.mActivity);
        bannedSettingActivity.onBackPressed();
    }

    public void onDataListSuccess(int i, List<BannedEntity> list, boolean z, boolean z2) {
        this.tvCurrentCount.setVisibility(i > 0 ? 0 : 4);
        this.tvCurrentCount.setText(getString(R.string.fq_my_live_current_banned_personal, new Object[]{Integer.valueOf(i)}));
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

    public void onBannedSettingSuccess(int i, BannedEntity bannedEntity) {
        if (!(this.isSearch || bannedEntity.isBanned())) {
            this.mAdapter.remove(i);
            this.tvCurrentCount.setVisibility(this.mAdapter.getData().size() > 0 ? 0 : 4);
            this.tvCurrentCount.setText(getString(R.string.fq_my_live_current_banned_personal, new Object[]{Integer.valueOf(this.mAdapter.getData().size())}));
        }
        if (this.isSearch) {
            c.a().c(new BannedEvent());
        }
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        super.onEventMainThread(baseEvent);
        if ((baseEvent instanceof BannedEvent) && !this.isSearch) {
            this.pageNum = 1;
            ((BannedPresenter) this.mPresenter).getDataList(this.mStateView, false, this.keyword, this.pageNum, true, true);
        }
    }

    private List<MenuEntity> getMenuList(String str) {
        ArrayList arrayList = new ArrayList();
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setMenuTitle(str);
        arrayList.add(menuEntity);
        return arrayList;
    }
}
