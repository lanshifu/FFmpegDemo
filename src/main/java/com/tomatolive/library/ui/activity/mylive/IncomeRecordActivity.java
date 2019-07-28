package com.tomatolive.library.ui.activity.mylive;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import com.blankj.utilcode.util.b;
import com.gyf.barlibrary.d;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.base.BaseFragment;
import com.tomatolive.library.base.a;
import com.tomatolive.library.model.LabelEntity;
import com.tomatolive.library.ui.adapter.HomeMenuTagAdapter;
import com.tomatolive.library.ui.fragment.ConsumeFragment;
import com.tomatolive.library.ui.fragment.IncomeFragment;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import net.lucode.hackware.magicindicator.c;

public class IncomeRecordActivity extends BaseActivity {
    private boolean isAuth = false;
    private d mImmersionBar;

    /* Access modifiers changed, original: protected */
    public a createPresenter() {
        return null;
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_income_record;
    }

    /* Access modifiers changed, original: protected */
    public void initImmersionBar() {
        this.mImmersionBar = d.a(this);
        this.mImmersionBar.a(true, d.d() ? CropImageView.DEFAULT_ASPECT_RATIO : 0.2f).b(true).a(R.color.fq_colorWhite).b();
    }

    public void initView(Bundle bundle) {
        this.isAuth = getIntent().getBooleanExtra("isAuth", false);
        initMagicIndicator(this.mContext, getSupportFragmentManager(), (MagicIndicator) findViewById(R.id.magic_indicator), (ViewPager) findViewById(R.id.view_pager), getFragmentList(), getLabelList(), 0);
    }

    public void initListener() {
        super.initListener();
        findViewById(R.id.ctv_back).setOnClickListener(new -$$Lambda$IncomeRecordActivity$PlnZUmc8rhyADzZuI6ul-otcS-0(this));
    }

    private List<BaseFragment> getFragmentList() {
        ArrayList arrayList = new ArrayList();
        if (this.isAuth) {
            arrayList.add(IncomeFragment.newInstance());
        }
        arrayList.add(ConsumeFragment.newInstance());
        return arrayList;
    }

    private List<LabelEntity> getLabelList() {
        ArrayList arrayList = new ArrayList();
        if (this.isAuth) {
            arrayList.add(new LabelEntity(getString(R.string.fq_my_live_income_detail)));
        }
        arrayList.add(new LabelEntity(getString(R.string.fq_my_live_consume_detail)));
        return arrayList;
    }

    private void initMagicIndicator(Context context, FragmentManager fragmentManager, MagicIndicator magicIndicator, final ViewPager viewPager, final List<BaseFragment> list, final List<LabelEntity> list2, int i) {
        magicIndicator.setBackgroundColor(ContextCompat.getColor(context, R.color.fq_colorWhite));
        viewPager.setAdapter(new HomeMenuTagAdapter(list, list2, fragmentManager));
        CommonNavigator commonNavigator = new CommonNavigator(context);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new aaj() {
            public int getCount() {
                return list == null ? 0 : list.size();
            }

            public aam getTitleView(Context context, int i) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(((LabelEntity) list2.get(i)).name);
                simplePagerTitleView.setTextSize(18.0f);
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(context, R.color.fq_colorBlack));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(context, list.size() == 1 ? R.color.fq_colorBlack : R.color.fq_tab_menu_text_select_color));
                simplePagerTitleView.setOnClickListener(new -$$Lambda$IncomeRecordActivity$1$ZxSZFhuUE4E8NTZpuQJurp9OpFE(viewPager, i));
                return simplePagerTitleView;
            }

            public aal getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                Integer[] numArr = new Integer[1];
                int i = 0;
                numArr[0] = Integer.valueOf(ContextCompat.getColor(IncomeRecordActivity.this.mContext, R.color.fq_tab_menu_text_select_color));
                linePagerIndicator.setColors(numArr);
                linePagerIndicator.setLineHeight((float) b.a(2.0f));
                linePagerIndicator.setLineWidth((float) b.a(30.0f));
                linePagerIndicator.setRoundRadius((float) b.a(3.0f));
                linePagerIndicator.setMode(2);
                if (list.size() <= 1) {
                    i = 4;
                }
                linePagerIndicator.setVisibility(i);
                return linePagerIndicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        c.a(magicIndicator, viewPager);
        viewPager.setOffscreenPageLimit(list.size());
        viewPager.setCurrentItem(i, false);
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.mImmersionBar != null) {
            this.mImmersionBar.c();
        }
    }
}
