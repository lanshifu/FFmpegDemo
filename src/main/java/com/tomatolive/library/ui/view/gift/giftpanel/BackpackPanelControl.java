package com.tomatolive.library.ui.view.gift.giftpanel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.tomatolive.library.R;
import com.tomatolive.library.model.BackpackItemEntity;
import com.tomatolive.library.ui.view.divider.RVDividerGiftAdapter;
import com.tomatolive.library.utils.p;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BackpackPanelControl {
    private int columns = 4;
    private GiftClickListener giftClickListener;
    private boolean isClearStatus = true;
    private boolean isScrolling = false;
    private int lastValue = -1;
    private int mAdapterSelectPosition = -1;
    private Context mContext;
    private List<BackpackItemEntity> mDatas = new ArrayList();
    private LinearLayout mDotsLayout;
    private LayoutInflater mInflater;
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager mViewpager;
    private int mViewpagerPosition = 0;
    private List<View> mViews = new ArrayList();
    private int rows = 2;

    public interface GiftClickListener {
        void onClick(BackpackItemEntity backpackItemEntity);
    }

    private class PageChangeListener implements OnPageChangeListener {
        private boolean left;
        private boolean right;

        private PageChangeListener() {
            this.left = false;
            this.right = false;
        }

        /* synthetic */ PageChangeListener(BackpackPanelControl backpackPanelControl, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void onPageScrolled(int i, float f, int i2) {
            if (BackpackPanelControl.this.isScrolling) {
                if (BackpackPanelControl.this.lastValue > i2) {
                    this.right = true;
                    this.left = false;
                } else if (BackpackPanelControl.this.lastValue < i2) {
                    this.right = false;
                    this.left = true;
                } else if (BackpackPanelControl.this.lastValue == i2) {
                    this.left = false;
                    this.right = false;
                }
            }
            BackpackPanelControl.this.lastValue = i2;
        }

        public void onPageSelected(int i) {
            BackpackPanelControl.this.mViewpagerPosition = i;
            for (int i2 = 0; i2 < BackpackPanelControl.this.mDotsLayout.getChildCount(); i2++) {
                BackpackPanelControl.this.mDotsLayout.getChildAt(i2).setSelected(false);
            }
            BackpackPanelControl.this.mDotsLayout.getChildAt(i).setSelected(true);
        }

        public void onPageScrollStateChanged(int i) {
            BackpackPanelControl backpackPanelControl = BackpackPanelControl.this;
            boolean z = true;
            if (i != 1) {
                z = false;
            }
            backpackPanelControl.isScrolling = z;
            if (i == 2) {
                this.left = false;
                this.right = false;
            }
        }
    }

    public BackpackPanelControl(Context context, ViewPager viewPager, LinearLayout linearLayout) {
        this.mContext = context;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.mViewpager = viewPager;
        this.mDotsLayout = linearLayout;
        initPortraitBackpack();
    }

    public void updateBackpackList(List<BackpackItemEntity> list) {
        this.mDatas = formatBackpackList(list);
        if (this.mViews == null) {
            this.mViews = new ArrayList();
        }
        this.mViews.clear();
        this.mViewPagerAdapter.notifyDataSetChanged();
        this.mDotsLayout.removeAllViews();
        this.mViewpager.removeAllViews();
        initPortraitBackpack();
    }

    public boolean isCountEnabled() {
        return this.mDatas != null && this.mDatas.size() > 0;
    }

    private void initPortraitBackpack() {
        int pagerCount = getPagerCount(this.mDatas.size(), this.columns, this.rows);
        if (this.mViews == null) {
            this.mViews = new ArrayList();
        }
        this.mViews.clear();
        int i = 0;
        if (pagerCount != 0) {
            for (int i2 = 0; i2 < pagerCount; i2++) {
                this.mViews.add(viewPagerItem(this.mContext, i2, this.mDatas, this.columns, this.rows));
                LayoutParams layoutParams = new LayoutParams(16, 16);
                layoutParams.setMargins(10, 0, 10, 0);
                if (pagerCount > 1) {
                    this.mDotsLayout.addView(dotsItem(i2), layoutParams);
                }
            }
        }
        this.mViewPagerAdapter = new ViewPagerAdapter(this.mViews);
        this.mViewpager.setAdapter(this.mViewPagerAdapter);
        this.mViewpager.addOnPageChangeListener(new PageChangeListener(this, null));
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

    private View viewPagerItem(Context context, int i, List<BackpackItemEntity> list, int i2, int i3) {
        RVDividerGiftAdapter rVDividerGiftAdapter = new RVDividerGiftAdapter(context, R.color.fq_view_divider_color66);
        RecyclerView recyclerView = (RecyclerView) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.fq_face_gridview, null).findViewById(R.id.chart_face_gv);
        recyclerView.setLayoutManager(new GridLayoutManager(context, i2));
        recyclerView.addItemDecoration(rVDividerGiftAdapter);
        ((DefaultItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        i2 *= i3;
        i3 = i * i2;
        i2 *= i + 1;
        if (i2 > list.size()) {
            i2 = list.size();
        }
        final BackpackAdapter backpackAdapter = new BackpackAdapter(new ArrayList(list.subList(i3, i2)));
        recyclerView.setAdapter(backpackAdapter);
        backpackAdapter.bindToRecyclerView(recyclerView);
        if (this.mViewpagerPosition == i && this.mAdapterSelectPosition >= 0) {
            backpackAdapter.setSelectedPosition(this.mAdapterSelectPosition);
            if (this.giftClickListener != null) {
                this.giftClickListener.onClick((BackpackItemEntity) backpackAdapter.getItem(this.mAdapterSelectPosition));
            }
        }
        backpackAdapter.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                BackpackItemEntity backpackItemEntity = (BackpackItemEntity) baseQuickAdapter.getItem(i);
                if (backpackItemEntity != null && !backpackItemEntity.isStayTuned) {
                    BackpackPanelControl.this.clearSelectedPosition();
                    BackpackPanelControl.this.mAdapterSelectPosition = i;
                    backpackAdapter.setSelectedPosition(i);
                    if (BackpackPanelControl.this.giftClickListener != null) {
                        BackpackPanelControl.this.giftClickListener.onClick(backpackItemEntity);
                    }
                }
            }
        });
        return recyclerView;
    }

    private int getPagerCount(int i, int i2, int i3) {
        i2 *= i3;
        return i % i2 == 0 ? i / i2 : (i / i2) + 1;
    }

    private List<BackpackItemEntity> formatBackpackList(List<BackpackItemEntity> list) {
        ArrayList arrayList = new ArrayList(list);
        if (arrayList.size() == 0) {
            return arrayList;
        }
        int size = arrayList.size() % 8;
        if (size <= 0) {
            return arrayList;
        }
        size = 8 - size;
        for (int i = 0; i < size; i++) {
            BackpackItemEntity backpackItemEntity = new BackpackItemEntity();
            backpackItemEntity.isStayTuned = true;
            arrayList.add(backpackItemEntity);
        }
        return arrayList;
    }

    private void clearSelectedPosition() {
        for (View view : this.mViews) {
            if (view instanceof RecyclerView) {
                Adapter adapter = ((RecyclerView) view).getAdapter();
                if ((adapter instanceof BackpackAdapter) && this.isClearStatus) {
                    ((BackpackAdapter) adapter).clearSelectedPosition();
                    if (this.giftClickListener != null) {
                        this.giftClickListener.onClick(null);
                    }
                }
            }
        }
    }

    public void updateSelectedItemCount() {
        View view = (View) this.mViews.get(this.mViewpagerPosition);
        if (view instanceof RecyclerView) {
            Adapter adapter = ((RecyclerView) view).getAdapter();
            if (adapter instanceof BackpackAdapter) {
                BackpackAdapter backpackAdapter = (BackpackAdapter) adapter;
                if (this.mAdapterSelectPosition >= 0) {
                    BackpackItemEntity backpackItemEntity = (BackpackItemEntity) backpackAdapter.getItem(this.mAdapterSelectPosition);
                    AtomicInteger atomicInteger = new AtomicInteger(p.a(backpackItemEntity.count));
                    atomicInteger.decrementAndGet();
                    if (atomicInteger.get() == 0) {
                        backpackAdapter.setSelectedPosition(-1);
                        backpackAdapter.remove(this.mAdapterSelectPosition);
                        this.mAdapterSelectPosition = -1;
                        if (this.giftClickListener != null) {
                            this.giftClickListener.onClick(null);
                        }
                        backpackItemEntity = new BackpackItemEntity();
                        backpackItemEntity.isStayTuned = true;
                        backpackAdapter.addData(7, backpackItemEntity);
                        if (isClearSelectedView(backpackAdapter)) {
                            int i = 8;
                            ((View) this.mViews.get(this.mViewpagerPosition)).setVisibility(8);
                            this.mViews.remove(this.mViewpagerPosition);
                            this.mViewPagerAdapter.notifyDataSetChanged();
                            LinearLayout linearLayout = this.mDotsLayout;
                            if (this.mViews.size() > 1) {
                                i = 0;
                            }
                            linearLayout.setVisibility(i);
                        }
                        return;
                    }
                    backpackItemEntity.count = String.valueOf(atomicInteger.get());
                    backpackAdapter.setData(this.mAdapterSelectPosition, backpackItemEntity);
                }
            }
        }
    }

    private boolean isClearSelectedView(BackpackAdapter backpackAdapter) {
        ArrayList arrayList = new ArrayList();
        for (BackpackItemEntity backpackItemEntity : backpackAdapter.getData()) {
            if (backpackItemEntity.isStayTuned) {
                arrayList.add(Boolean.valueOf(backpackItemEntity.isStayTuned));
            }
        }
        return arrayList.size() == 8;
    }

    public boolean isEmpty() {
        return this.mViews.size() == 0;
    }

    public void setGiftClickListener(GiftClickListener giftClickListener) {
        this.giftClickListener = giftClickListener;
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
