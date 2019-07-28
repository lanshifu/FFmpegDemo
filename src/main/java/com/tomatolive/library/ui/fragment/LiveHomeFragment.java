package com.tomatolive.library.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import com.blankj.utilcode.util.b;
import com.blankj.utilcode.util.k;
import com.gyf.barlibrary.d;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseFragment;
import com.tomatolive.library.model.LabelEntity;
import com.tomatolive.library.model.event.BaseEvent;
import com.tomatolive.library.model.event.LabelMenuEvent;
import com.tomatolive.library.ui.adapter.HomeMenuTagAdapter;
import com.tomatolive.library.ui.presenter.HomePresenter;
import com.tomatolive.library.ui.view.dialog.LiveKickOutDialog;
import com.tomatolive.library.ui.view.dialog.TokenInvalidDialog;
import com.tomatolive.library.ui.view.iview.IHomeView;
import defpackage.wl;
import java.util.ArrayList;
import java.util.List;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import net.lucode.hackware.magicindicator.c;

public class LiveHomeFragment extends BaseFragment<HomePresenter> implements IHomeView {
    private LocalBroadcastManager localBroadcastManager;
    private ViewPager mViewPager;
    private MagicIndicator magicIndicator;
    private MyKickOutBroadCastReceiver myKickOutBroadCastReceiver;
    private MyTokenInvalidBroadCastReceiver myTokenInvalidBroadCastReceiver;

    private class MyKickOutBroadCastReceiver extends BroadcastReceiver {
        private MyKickOutBroadCastReceiver() {
        }

        /* synthetic */ MyKickOutBroadCastReceiver(LiveHomeFragment liveHomeFragment, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && TextUtils.equals(intent.getAction(), "LIVE_KICK_OUT_ACTION")) {
                LiveHomeFragment.this.showKickOutDialog();
            }
        }
    }

    private class MyTokenInvalidBroadCastReceiver extends BroadcastReceiver {
        private MyTokenInvalidBroadCastReceiver() {
        }

        /* synthetic */ MyTokenInvalidBroadCastReceiver(LiveHomeFragment liveHomeFragment, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && TextUtils.equals(intent.getAction(), "LIVE_TOKEN_INVALID_ACTION")) {
                LiveHomeFragment.this.showTokenInvalidDialog();
            }
        }
    }

    public void onResultError(int i) {
    }

    public static LiveHomeFragment newInstance() {
        Bundle bundle = new Bundle();
        LiveHomeFragment liveHomeFragment = new LiveHomeFragment();
        liveHomeFragment.setArguments(bundle);
        return liveHomeFragment;
    }

    /* Access modifiers changed, original: protected */
    public HomePresenter createPresenter() {
        return new HomePresenter(this.mContext, this);
    }

    public int getLayoutId() {
        this.mActivity.getWindow().setFlags(8192, 8192);
        return R.layout.fq_fragment_live_home;
    }

    /* Access modifiers changed, original: protected */
    public View injectStateView(View view) {
        return view.findViewById(R.id.fl_content_view);
    }

    public void initView(View view, @Nullable Bundle bundle) {
        initTitleBarStyle(view);
        this.magicIndicator = (MagicIndicator) view.findViewById(R.id.magic_indicator);
        this.mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        ((HomePresenter) this.mPresenter).getTagList(this.mStateView, true);
        ((HomePresenter) this.mPresenter).getSysParamsInfo();
        initPermission();
        view.findViewById(R.id.iv_search).setOnClickListener(new -$$Lambda$LiveHomeFragment$jPSy5Hgh6AnOsq7nmPOCtiTvOlA(this));
    }

    private void initPermission() {
        new RxPermissions(this.mActivity).requestEach(new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO"}).compose(bindToLifecycle()).subscribe(new wl<Permission>() {
            public void accept(Permission permission) throws Exception {
            }
        });
    }

    private void initTitleBarStyle(View view) {
        this.mImmersionBar = d.a(this);
        this.mImmersionBar.a().a(view.findViewById(R.id.title_top_view)).a(true).b();
    }

    public void onResume() {
        super.onResume();
        registerKickDialogReceiver();
        registerTokenDialogReceiver();
    }

    public void onPause() {
        super.onPause();
        unRegisterKickDialogReceiver();
        unRegisterTokenDialogReceiver();
    }

    private void registerKickDialogReceiver() {
        this.localBroadcastManager = LocalBroadcastManager.getInstance(this.mContext);
        IntentFilter intentFilter = new IntentFilter("LIVE_KICK_OUT_ACTION");
        this.myKickOutBroadCastReceiver = new MyKickOutBroadCastReceiver(this, null);
        this.localBroadcastManager.registerReceiver(this.myKickOutBroadCastReceiver, intentFilter);
    }

    private void showKickOutDialog() {
        LiveKickOutDialog.newInstance().show(getChildFragmentManager());
    }

    private void unRegisterKickDialogReceiver() {
        this.localBroadcastManager.unregisterReceiver(this.myKickOutBroadCastReceiver);
    }

    private void registerTokenDialogReceiver() {
        this.localBroadcastManager = LocalBroadcastManager.getInstance(this.mActivity);
        IntentFilter intentFilter = new IntentFilter("LIVE_TOKEN_INVALID_ACTION");
        this.myTokenInvalidBroadCastReceiver = new MyTokenInvalidBroadCastReceiver(this, null);
        this.localBroadcastManager.registerReceiver(this.myTokenInvalidBroadCastReceiver, intentFilter);
    }

    private void unRegisterTokenDialogReceiver() {
        if (this.localBroadcastManager != null && this.myTokenInvalidBroadCastReceiver != null) {
            this.localBroadcastManager.unregisterReceiver(this.myTokenInvalidBroadCastReceiver);
        }
    }

    public void initListener() {
        this.mStateView.setOnRetryClickListener(new -$$Lambda$LiveHomeFragment$_GDaZrCnYJtGrjc_cSElSkksUPs(this));
    }

    private List<BaseFragment> getFragmentList(List<LabelEntity> list) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(HomeAttentionFragment.newInstance());
        arrayList.add(HomeHotFragment.newInstance());
        arrayList.add(HomeSortFragment.newInstance("allTagId"));
        if (list != null && list.size() > 0) {
            for (LabelEntity labelEntity : list) {
                arrayList.add(HomeSortFragment.newInstance(labelEntity.name));
            }
        }
        return arrayList;
    }

    private List<LabelEntity> getLabelList(List<LabelEntity> list) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new LabelEntity(getString(R.string.fq_home_attention)));
        arrayList.add(new LabelEntity(getString(R.string.fq_home_hot)));
        arrayList.add(new LabelEntity(getString(R.string.fq_home_all)));
        if (list != null && list.size() > 0) {
            for (LabelEntity labelEntity : list) {
                arrayList.add(new LabelEntity(labelEntity.name));
            }
        }
        return arrayList;
    }

    private void initMagicIndicator(Context context, FragmentManager fragmentManager, MagicIndicator magicIndicator, final ViewPager viewPager, final List<BaseFragment> list, final List<LabelEntity> list2, int i) {
        viewPager.setAdapter(new HomeMenuTagAdapter(list, list2, fragmentManager));
        CommonNavigator commonNavigator = new CommonNavigator(context);
        commonNavigator.setAdapter(new aaj() {
            public int getCount() {
                return list == null ? 0 : list.size();
            }

            public aam getTitleView(Context context, int i) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(((LabelEntity) list2.get(i)).name);
                simplePagerTitleView.setTextSize(18.0f);
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(context, R.color.fq_tab_menu_text_color));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(context, R.color.fq_tab_menu_text_select_color));
                simplePagerTitleView.setOnClickListener(new -$$Lambda$LiveHomeFragment$2$qMSExcE1EyVkfT01qNR8cCpRhB8(viewPager, i));
                return simplePagerTitleView;
            }

            public aal getIndicator(Context context) {
                return LiveHomeFragment.this.getIPagerIndicator(context);
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        c.a(magicIndicator, viewPager);
        viewPager.setOffscreenPageLimit(list.size());
        viewPager.setCurrentItem(i, false);
    }

    public void onTagListSuccess(List<LabelEntity> list) {
        try {
            k.a().a("labelMenu", true);
            initMagicIndicator(this.mContext, getChildFragmentManager(), this.magicIndicator, this.mViewPager, getFragmentList(list), getLabelList(list), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onTagListFail() {
        this.mStateView.showContent();
        try {
            k.a().a("labelMenu", false);
            initMagicIndicator(this.mContext, getChildFragmentManager(), this.magicIndicator, this.mViewPager, getFragmentList(null), getLabelList(null), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showTokenInvalidDialog() {
        TokenInvalidDialog.newInstance().show(getChildFragmentManager());
    }

    public void onEventMainThreadSticky(BaseEvent baseEvent) {
        super.onEventMainThreadSticky(baseEvent);
        if (baseEvent instanceof LabelMenuEvent) {
            ((HomePresenter) this.mPresenter).getTagList(this.mStateView, false);
        }
    }

    private aal getIPagerIndicator(Context context) {
        LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
        linePagerIndicator.setColors(Integer.valueOf(ContextCompat.getColor(context, R.color.fq_tab_menu_text_select_color)));
        linePagerIndicator.setLineHeight((float) b.a(2.0f));
        linePagerIndicator.setRoundRadius((float) aai.a(context, 3.0d));
        linePagerIndicator.setLineWidth((float) b.a(18.0f));
        linePagerIndicator.setMode(2);
        return linePagerIndicator;
    }
}
