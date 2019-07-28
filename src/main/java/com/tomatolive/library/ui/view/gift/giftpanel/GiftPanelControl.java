package com.tomatolive.library.ui.view.gift.giftpanel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tomatolive.library.R;
import com.tomatolive.library.model.GiftDownloadItemEntity;
import com.tomatolive.library.ui.view.divider.RVDividerGiftAdapter;
import java.util.ArrayList;
import java.util.List;

public class GiftPanelControl {
    private int columns = 4;
    private GiftClickListener giftClickListener;
    private boolean isClearStatus = true;
    private boolean isScrolling = false;
    private int lastValue = -1;
    private int mAdapterSelectPosition = -1;
    private Context mContext;
    private List<GiftDownloadItemEntity> mDatas;
    private LinearLayout mDotsLayout;
    private LayoutInflater mInflater;
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager mViewpager;
    private int mViewpagerPosition = 0;
    private List<View> mViews = new ArrayList();
    private int rows = 2;

    public interface GiftClickListener {
        void onClick(GiftDownloadItemEntity giftDownloadItemEntity);
    }

    private class PageChangeListener implements OnPageChangeListener {
        private boolean left;
        private boolean right;

        private PageChangeListener() {
            this.left = false;
            this.right = false;
        }

        public void onPageScrolled(int i, float f, int i2) {
            if (GiftPanelControl.this.isScrolling) {
                if (GiftPanelControl.this.lastValue > i2) {
                    this.right = true;
                    this.left = false;
                } else if (GiftPanelControl.this.lastValue < i2) {
                    this.right = false;
                    this.left = true;
                } else if (GiftPanelControl.this.lastValue == i2) {
                    this.left = false;
                    this.right = false;
                }
            }
            GiftPanelControl.this.lastValue = i2;
        }

        public void onPageSelected(int i) {
            GiftPanelControl.this.mViewpagerPosition = i;
            for (int i2 = 0; i2 < GiftPanelControl.this.mDotsLayout.getChildCount(); i2++) {
                GiftPanelControl.this.mDotsLayout.getChildAt(i2).setSelected(false);
            }
            GiftPanelControl.this.mDotsLayout.getChildAt(i).setSelected(true);
        }

        public void onPageScrollStateChanged(int i) {
            GiftPanelControl giftPanelControl = GiftPanelControl.this;
            boolean z = true;
            if (i != 1) {
                z = false;
            }
            giftPanelControl.isScrolling = z;
            if (i == 2) {
                this.left = false;
                this.right = false;
            }
        }
    }

    public GiftPanelControl(Context context, ViewPager viewPager, LinearLayout linearLayout, List<GiftDownloadItemEntity> list) {
        this.mContext = context;
        this.mDatas = list;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.mViewpager = viewPager;
        this.mDotsLayout = linearLayout;
        initPortraitGift();
    }

    public void updateGiftList(List<GiftDownloadItemEntity> list) {
        this.mDatas = list;
        this.mViewPagerAdapter.notifyDataSetChanged();
        this.mDotsLayout.removeAllViews();
        this.mViewpager.removeAllViews();
        initPortraitGift();
    }

    private void initPortraitGift() {
        if (this.mDatas == null) {
            this.mDatas = new ArrayList();
        }
        int pagerCount = getPagerCount(this.mDatas.size(), this.columns, this.rows);
        if (this.mViews == null) {
            this.mViews = new ArrayList();
        }
        this.mViews.clear();
        int i = 0;
        for (int i2 = 0; i2 < pagerCount; i2++) {
            this.mViews.add(viewPagerItem(this.mContext, i2, this.mDatas, this.columns, this.rows));
            LayoutParams layoutParams = new LayoutParams(16, 16);
            layoutParams.setMargins(10, 0, 10, 0);
            if (pagerCount > 1) {
                this.mDotsLayout.addView(dotsItem(i2), layoutParams);
            }
        }
        this.mViewPagerAdapter = new ViewPagerAdapter(this.mViews);
        this.mViewpager.setAdapter(this.mViewPagerAdapter);
        this.mViewpager.addOnPageChangeListener(new PageChangeListener());
        this.mViewpager.setCurrentItem(this.mViewpagerPosition);
        if (pagerCount > 1) {
            this.mDotsLayout.getChildAt(this.mViewpagerPosition).setSelected(true);
        }
        LinearLayout linearLayout = this.mDotsLayout;
        if (pagerCount <= 1) {
            i = 8;
        }
        linearLayout.setVisibility(i);
    }

    @SuppressLint({"InflateParams"})
    private ImageView dotsItem(int i) {
        ImageView imageView = (ImageView) this.mInflater.inflate(R.layout.fq_dot_image, null).findViewById(R.id.face_dot);
        imageView.setId(i);
        return imageView;
    }

    public void isClearStatus(boolean z) {
        this.isClearStatus = z;
    }

    private View viewPagerItem(Context context, int i, List<GiftDownloadItemEntity> list, int i2, int i3) {
        RVDividerGiftAdapter rVDividerGiftAdapter = new RVDividerGiftAdapter(context, R.color.fq_view_divider_color66);
        RecyclerView recyclerView = (RecyclerView) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.fq_face_gridview, null).findViewById(R.id.chart_face_gv);
        recyclerView.setLayoutManager(new GridLayoutManager(context, i2));
        recyclerView.addItemDecoration(rVDividerGiftAdapter);
        i2 *= i3;
        i3 = i * i2;
        i2 *= i + 1;
        if (i2 > list.size()) {
            i2 = list.size();
        }
        GiftAdapter giftAdapter = new GiftAdapter(new ArrayList(list.subList(i3, i2)));
        recyclerView.setAdapter(giftAdapter);
        giftAdapter.bindToRecyclerView(recyclerView);
        if (this.mViewpagerPosition == i && this.mAdapterSelectPosition >= 0) {
            giftAdapter.setSelectedPosition(this.mAdapterSelectPosition);
            if (this.giftClickListener != null) {
                this.giftClickListener.onClick((GiftDownloadItemEntity) giftAdapter.getItem(this.mAdapterSelectPosition));
            }
        }
        giftAdapter.setOnItemClickListener(new -$$Lambda$GiftPanelControl$yVR1s76AcIar06ldwmyIClydvok(this, giftAdapter));
        return recyclerView;
    }

    public static /* synthetic */ void lambda$viewPagerItem$0(GiftPanelControl giftPanelControl, GiftAdapter giftAdapter, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        GiftDownloadItemEntity giftDownloadItemEntity = (GiftDownloadItemEntity) baseQuickAdapter.getItem(i);
        if (giftDownloadItemEntity != null && !TextUtils.isEmpty(giftDownloadItemEntity.jsonname) && !giftDownloadItemEntity.isStayTuned) {
            giftPanelControl.clearSelectedPosition();
            giftPanelControl.mAdapterSelectPosition = i;
            giftAdapter.setSelectedPosition(i);
            if (giftPanelControl.giftClickListener != null) {
                giftPanelControl.giftClickListener.onClick(giftDownloadItemEntity);
            }
        }
    }

    private int getPagerCount(int i, int i2, int i3) {
        i2 *= i3;
        return i % i2 == 0 ? i / i2 : (i / i2) + 1;
    }

    public void setGiftClickListener(GiftClickListener giftClickListener) {
        this.giftClickListener = giftClickListener;
    }

    private void clearSelectedPosition() {
        for (View view : this.mViews) {
            if (view instanceof RecyclerView) {
                Adapter adapter = ((RecyclerView) view).getAdapter();
                if ((adapter instanceof GiftAdapter) && this.isClearStatus) {
                    ((GiftAdapter) adapter).clearSelectedPosition();
                    if (this.giftClickListener != null) {
                        this.giftClickListener.onClick(null);
                    }
                }
            }
        }
    }

    public void onDestroy() {
        setGiftClickListener(null);
        if (this.mDatas != null) {
            this.mDatas.clear();
        }
        if (this.mViews != null) {
            this.mViews.clear();
        }
        if (this.mViewPagerAdapter != null) {
            this.mViewPagerAdapter.notifyDataSetChanged();
        }
        if (this.mContext != null) {
            this.mContext = null;
        }
    }
}
