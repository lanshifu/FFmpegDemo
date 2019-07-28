package com.tomatolive.library.ui.activity.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.d;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.base.BaseFragment;
import com.tomatolive.library.model.LabelEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.model.db.SearchKeywordEntity;
import com.tomatolive.library.model.event.BaseEvent;
import com.tomatolive.library.model.event.KeywordEvent;
import com.tomatolive.library.model.event.SearchEvent;
import com.tomatolive.library.ui.adapter.HomeLiveAdapter;
import com.tomatolive.library.ui.adapter.MenuTabAdapter;
import com.tomatolive.library.ui.adapter.SearchKeywordAdapter;
import com.tomatolive.library.ui.fragment.SearchAllFragment;
import com.tomatolive.library.ui.fragment.SearchAllFragment.OnFragmentInteractionListener;
import com.tomatolive.library.ui.fragment.SearchAnchorFragment;
import com.tomatolive.library.ui.fragment.SearchLiveFragment;
import com.tomatolive.library.ui.presenter.SearchPresenter;
import com.tomatolive.library.ui.view.divider.RVDividerLinear;
import com.tomatolive.library.ui.view.divider.RVDividerLive;
import com.tomatolive.library.ui.view.headview.SearchHistoryHeadView;
import com.tomatolive.library.ui.view.iview.ISearchView;
import com.tomatolive.library.ui.view.widget.tagview.TagView.OnTagClickListener;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.c;
import com.tomatolive.library.utils.e;
import com.tomatolive.library.utils.t;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

public class SearchActivity extends BaseActivity<SearchPresenter> implements OnFragmentInteractionListener, ISearchView {
    private EditText etSearch;
    private FrameLayout flKeywordBg;
    private List<BaseFragment> fragmentList;
    private SearchHistoryHeadView headView;
    private boolean isTagKey = false;
    private HomeLiveAdapter mAdapter;
    private d mImmersionBar;
    private LinearLayout mLLViewPagerBg;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewKeyword;
    private ViewPager mViewPager;
    private MagicIndicator magicIndicator;
    private SearchKeywordAdapter searchKeywordAdapter;

    public void onResultError(int i) {
    }

    /* Access modifiers changed, original: protected */
    public SearchPresenter createPresenter() {
        return new SearchPresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_search;
    }

    /* Access modifiers changed, original: protected */
    public View injectStateView() {
        return findViewById(R.id.fl_content_view);
    }

    /* Access modifiers changed, original: protected */
    public void initImmersionBar() {
        this.mImmersionBar = d.a(this);
        this.mImmersionBar.a(true, d.d() ? CropImageView.DEFAULT_ASPECT_RATIO : 0.2f).b(true).a(R.color.fq_colorWhite).b();
    }

    public void initView(Bundle bundle) {
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.mRecyclerViewKeyword = (RecyclerView) findViewById(R.id.recycler_view_keyword);
        this.mLLViewPagerBg = (LinearLayout) findViewById(R.id.ll_view_pager);
        this.magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
        this.mViewPager = (ViewPager) findViewById(R.id.view_pager);
        this.etSearch = (EditText) findViewById(R.id.et_search);
        this.flKeywordBg = (FrameLayout) findViewById(R.id.fl_keyword_bg);
        initKeywordAdapter();
        initAdapter();
        ((SearchPresenter) this.mPresenter).getLiveEnjoyList(this.mStateView);
        ((SearchPresenter) this.mPresenter).getHotKeyList();
    }

    public void initListener() {
        super.initListener();
        this.mStateView.setOnRetryClickListener(new -$$Lambda$SearchActivity$uUzdjp5drpUrzLPAAaKrPNtTrgU(this));
        findViewById(R.id.tv_cancel).setOnClickListener(new -$$Lambda$SearchActivity$13vrveHjt-jngE508shU4niCfyo(this));
        this.headView.setOnTagClickListener(new OnTagClickListener() {
            public void onSelectedTagDrag(int i, String str) {
            }

            public void onTagCrossClick(int i) {
            }

            public void onTagLongClick(int i, String str) {
            }

            public void onTagClick(int i, String str) {
                SearchActivity.this.isTagKey = true;
                SearchActivity.this.setSearchText(str);
            }
        }, new OnTagClickListener() {
            public void onSelectedTagDrag(int i, String str) {
            }

            public void onTagCrossClick(int i) {
            }

            public void onTagLongClick(int i, String str) {
            }

            public void onTagClick(int i, String str) {
                SearchActivity.this.isTagKey = true;
                SearchActivity.this.setSearchText(str);
            }
        });
        this.headView.findViewById(R.id.tv_clear).setOnClickListener(new -$$Lambda$SearchActivity$r9uxXfyYvxhsO_zjV_7GDUV3w0s(this));
        this.etSearch.setOnEditorActionListener(new -$$Lambda$SearchActivity$o7ZY93tzOpIuyclM6hQoE5cBDms(this));
        this.searchKeywordAdapter.setOnItemClickListener(new -$$Lambda$SearchActivity$vdTT_FVIxHeNCM0WZK-lXT40mAM(this));
        this.mAdapter.setOnItemClickListener(new -$$Lambda$SearchActivity$aTZktMs4Nr_FODXV_sqRPwUmoG0(this));
    }

    public static /* synthetic */ void lambda$initListener$0(SearchActivity searchActivity) {
        ((SearchPresenter) searchActivity.mPresenter).getLiveEnjoyList(searchActivity.mStateView);
        ((SearchPresenter) searchActivity.mPresenter).getHotKeyList();
    }

    public static /* synthetic */ void lambda$initListener$1(SearchActivity searchActivity, View view) {
        t.b(searchActivity.mActivity);
        searchActivity.onBackPressed();
    }

    public static /* synthetic */ void lambda$initListener$2(SearchActivity searchActivity, View view) {
        c.b(SearchKeywordEntity.class);
        searchActivity.headView.initHistoryTagList(c.a(SearchKeywordEntity.class, "insertTime desc"));
    }

    public static /* synthetic */ boolean lambda$initListener$3(SearchActivity searchActivity, TextView textView, int i, KeyEvent keyEvent) {
        if (i == 3) {
            String trim = searchActivity.etSearch.getText().toString().trim();
            if (e.a(trim)) {
                searchActivity.showToast(R.string.fq_no_emoji_search);
                return true;
            } else if (TextUtils.isEmpty(trim)) {
                searchActivity.showToast(R.string.fq_text_search_content);
                return false;
            } else {
                t.b(searchActivity.mActivity);
                c.a(trim);
                searchActivity.postSearch(trim);
            }
        }
        return true;
    }

    public static /* synthetic */ void lambda$initListener$4(SearchActivity searchActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        LabelEntity labelEntity = (LabelEntity) searchActivity.searchKeywordAdapter.getItem(i);
        if (labelEntity != null && !TextUtils.isEmpty(labelEntity.keyword)) {
            searchActivity.postSearch(labelEntity.keyword);
        }
    }

    public static /* synthetic */ void lambda$initListener$5(SearchActivity searchActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        LiveEntity liveEntity = (LiveEntity) baseQuickAdapter.getItem(i);
        if (b.a(searchActivity.mContext)) {
            b.a(searchActivity.mContext, 2, liveEntity);
        }
    }

    private void initAdapter() {
        ((DefaultItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.headView = new SearchHistoryHeadView(this.mContext);
        this.mAdapter = new HomeLiveAdapter(R.layout.fq_item_list_live_view_new);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 2));
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.addItemDecoration(new RVDividerLive(this.mContext, R.color.fq_colorWhite, true, false));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.bindToRecyclerView(this.mRecyclerView);
        this.mAdapter.addHeaderView(this.headView);
        this.headView.initHistoryTagList(c.a(SearchKeywordEntity.class, "insertTime desc"));
    }

    private void initKeywordAdapter() {
        this.mRecyclerViewKeyword.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mRecyclerViewKeyword.addItemDecoration(new RVDividerLinear(this.mContext, R.color.fq_view_divider_color));
        this.searchKeywordAdapter = new SearchKeywordAdapter(R.layout.fq_item_list_search_keyword);
        this.mRecyclerViewKeyword.setAdapter(this.searchKeywordAdapter);
        this.searchKeywordAdapter.bindToRecyclerView(this.mRecyclerViewKeyword);
    }

    private void initFragmentList() {
        this.fragmentList = new ArrayList();
        this.fragmentList.add(SearchAllFragment.newInstance());
        this.fragmentList.add(SearchAnchorFragment.newInstance());
        this.fragmentList.add(SearchLiveFragment.newInstance());
    }

    private void setSearchText(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.etSearch.setText(str);
            this.etSearch.setSelection(str.length());
            c.a(str);
            postSearch(str);
        }
    }

    private void postSearch(String str) {
        if (this.fragmentList == null) {
            initFragmentList();
            initMagicIndicator(this.mContext, getSupportFragmentManager(), this.magicIndicator, this.mViewPager, this.fragmentList, Arrays.asList(this.mContext.getResources().getStringArray(R.array.fq_search_tab_menu)), 0);
        }
        int i = 4;
        this.flKeywordBg.setVisibility(4);
        this.mLLViewPagerBg.setVisibility(TextUtils.isEmpty(str) ? 4 : 0);
        RecyclerView recyclerView = this.mRecyclerView;
        if (TextUtils.isEmpty(str)) {
            i = 0;
        }
        recyclerView.setVisibility(i);
        org.greenrobot.eventbus.c.a().d(new SearchEvent(str));
    }

    private void initMagicIndicator(Context context, FragmentManager fragmentManager, MagicIndicator magicIndicator, final ViewPager viewPager, final List<BaseFragment> list, final List<String> list2, int i) {
        magicIndicator.setBackgroundColor(ContextCompat.getColor(context, R.color.fq_colorWhite));
        viewPager.setAdapter(new MenuTabAdapter(list, list2, fragmentManager));
        CommonNavigator commonNavigator = new CommonNavigator(context);
        commonNavigator.setAdapter(new aaj() {
            public int getCount() {
                return list == null ? 0 : list.size();
            }

            public aam getTitleView(Context context, int i) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText((CharSequence) list2.get(i));
                simplePagerTitleView.setTextSize(18.0f);
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(context, R.color.fq_tab_menu_text_color));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(context, R.color.fq_tab_menu_text_select_color));
                simplePagerTitleView.setOnClickListener(new -$$Lambda$SearchActivity$3$U31T4EzJCsGbOtD8kQg9H26dfKI(viewPager, i));
                return simplePagerTitleView;
            }

            public aal getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setColors(Integer.valueOf(ContextCompat.getColor(context, R.color.fq_tab_menu_text_select_color)));
                linePagerIndicator.setLineHeight((float) com.blankj.utilcode.util.b.a(2.0f));
                linePagerIndicator.setRoundRadius((float) aai.a(context, 3.0d));
                linePagerIndicator.setLineWidth((float) com.blankj.utilcode.util.b.a(18.0f));
                linePagerIndicator.setMode(2);
                return linePagerIndicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        net.lucode.hackware.magicindicator.c.a(magicIndicator, viewPager);
        viewPager.setOffscreenPageLimit(list.size());
        viewPager.setCurrentItem(i);
    }

    public void onPagerSelectedListener(int i) {
        if (i >= 0) {
            this.mViewPager.setCurrentItem(i);
        }
    }

    public void onLiveListSuccess(List<LiveEntity> list) {
        this.mAdapter.setNewData(list);
    }

    public void onHotKeyListSuccess(List<LabelEntity> list) {
        this.headView.initHotTagList(list);
    }

    public void onAutoKeyListSuccess(String str, List<LabelEntity> list) {
        this.flKeywordBg.setVisibility(TextUtils.isEmpty(str) ? 4 : 0);
        this.searchKeywordAdapter.setKeyWord(str);
        this.searchKeywordAdapter.setNewData(list);
    }

    public void onEventMainThreadSticky(BaseEvent baseEvent) {
        super.onEventMainThreadSticky(baseEvent);
        if (baseEvent instanceof KeywordEvent) {
            this.headView.initHistoryTagList(c.a(SearchKeywordEntity.class, "insertTime desc"));
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.mImmersionBar != null) {
            this.mImmersionBar.c();
        }
    }
}
