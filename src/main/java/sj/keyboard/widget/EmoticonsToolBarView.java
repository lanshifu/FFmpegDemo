package sj.keyboard.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.keyboard.view.R;
import java.io.IOException;
import java.util.ArrayList;
import sj.keyboard.data.PageSetEntity;
import sj.keyboard.utils.imageloader.ImageLoader;

public class EmoticonsToolBarView extends RelativeLayout {
    protected HorizontalScrollView hsv_toolbar;
    protected LinearLayout ly_tool;
    protected int mBtnWidth;
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected OnToolBarItemClickListener mItemClickListeners;
    protected ArrayList<View> mToolBtnList;

    public interface OnToolBarItemClickListener {
        void onToolBarItemClick(PageSetEntity pageSetEntity);
    }

    public EmoticonsToolBarView(Context context) {
        this(context, null);
    }

    public EmoticonsToolBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mToolBtnList = new ArrayList();
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.mInflater.inflate(R.layout.view_emoticonstoolbar, this);
        this.mContext = context;
        this.mBtnWidth = (int) context.getResources().getDimension(R.dimen.bar_tool_btn_width);
        this.hsv_toolbar = (HorizontalScrollView) findViewById(R.id.hsv_toolbar);
        this.ly_tool = (LinearLayout) findViewById(R.id.ly_tool);
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (getChildCount() > 3) {
            throw new IllegalArgumentException("can host only two direct child");
        }
    }

    public void addFixedToolItemView(View view, boolean z) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.hsv_toolbar.getLayoutParams();
        if (view.getId() <= 0) {
            view.setId(z ? R.id.id_toolbar_right : R.id.id_toolbar_left);
        }
        if (z) {
            layoutParams.addRule(11);
            layoutParams2.addRule(0, view.getId());
        } else {
            layoutParams.addRule(9);
            layoutParams2.addRule(1, view.getId());
        }
        addView(view, layoutParams);
        this.hsv_toolbar.setLayoutParams(layoutParams2);
    }

    /* Access modifiers changed, original: protected */
    public View getCommonItemToolBtn() {
        return this.mInflater == null ? null : this.mInflater.inflate(R.layout.item_toolbtn, null);
    }

    /* Access modifiers changed, original: protected */
    public void initItemToolBtn(View view, int i, final PageSetEntity pageSetEntity, OnClickListener onClickListener) {
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_icon);
        if (i > 0) {
            imageView.setImageResource(i);
        }
        imageView.setLayoutParams(new LinearLayout.LayoutParams(this.mBtnWidth, -1));
        if (pageSetEntity != null) {
            imageView.setTag(R.id.id_tag_pageset, pageSetEntity);
            try {
                ImageLoader.getInstance(this.mContext).displayImage(pageSetEntity.getIconUri(), imageView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (onClickListener == null) {
            onClickListener = new OnClickListener() {
                public void onClick(View view) {
                    if (EmoticonsToolBarView.this.mItemClickListeners != null && pageSetEntity != null) {
                        EmoticonsToolBarView.this.mItemClickListeners.onToolBarItemClick(pageSetEntity);
                    }
                }
            };
        }
        view.setOnClickListener(onClickListener);
    }

    /* Access modifiers changed, original: protected */
    public View getToolBgBtn(View view) {
        return view.findViewById(R.id.iv_icon);
    }

    public void addFixedToolItemView(boolean z, int i, PageSetEntity pageSetEntity, OnClickListener onClickListener) {
        View commonItemToolBtn = getCommonItemToolBtn();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.hsv_toolbar.getLayoutParams();
        if (commonItemToolBtn.getId() <= 0) {
            commonItemToolBtn.setId(z ? R.id.id_toolbar_right : R.id.id_toolbar_left);
        }
        if (z) {
            layoutParams.addRule(11);
            layoutParams2.addRule(0, commonItemToolBtn.getId());
        } else {
            layoutParams.addRule(9);
            layoutParams2.addRule(1, commonItemToolBtn.getId());
        }
        addView(commonItemToolBtn, layoutParams);
        this.hsv_toolbar.setLayoutParams(layoutParams2);
        initItemToolBtn(commonItemToolBtn, i, pageSetEntity, onClickListener);
    }

    public void addToolItemView(PageSetEntity pageSetEntity) {
        addToolItemView(0, pageSetEntity, null);
    }

    public void addToolItemView(int i, OnClickListener onClickListener) {
        addToolItemView(i, null, onClickListener);
    }

    public void addToolItemView(int i, PageSetEntity pageSetEntity, OnClickListener onClickListener) {
        View commonItemToolBtn = getCommonItemToolBtn();
        initItemToolBtn(commonItemToolBtn, i, pageSetEntity, onClickListener);
        this.ly_tool.addView(commonItemToolBtn);
        this.mToolBtnList.add(getToolBgBtn(commonItemToolBtn));
    }

    public void setToolBtnSelect(String str) {
        if (!TextUtils.isEmpty(str)) {
            int i = 0;
            for (int i2 = 0; i2 < this.mToolBtnList.size(); i2++) {
                Object tag = ((View) this.mToolBtnList.get(i2)).getTag(R.id.id_tag_pageset);
                if (tag != null && (tag instanceof PageSetEntity) && str.equals(((PageSetEntity) tag).getUuid())) {
                    ((View) this.mToolBtnList.get(i2)).setBackgroundColor(getResources().getColor(R.color.toolbar_btn_select));
                    i = i2;
                } else {
                    ((View) this.mToolBtnList.get(i2)).setBackgroundResource(R.drawable.btn_toolbtn_bg);
                }
            }
            scrollToBtnPosition(i);
        }
    }

    /* Access modifiers changed, original: protected */
    public void scrollToBtnPosition(final int i) {
        if (i < this.ly_tool.getChildCount()) {
            this.hsv_toolbar.post(new Runnable() {
                public void run() {
                    int scrollX = EmoticonsToolBarView.this.hsv_toolbar.getScrollX();
                    int left = EmoticonsToolBarView.this.ly_tool.getChildAt(i).getLeft();
                    if (left < scrollX) {
                        EmoticonsToolBarView.this.hsv_toolbar.scrollTo(left, 0);
                        return;
                    }
                    left += EmoticonsToolBarView.this.ly_tool.getChildAt(i).getWidth();
                    scrollX += EmoticonsToolBarView.this.hsv_toolbar.getWidth();
                    if (left > scrollX) {
                        EmoticonsToolBarView.this.hsv_toolbar.scrollTo(left - scrollX, 0);
                    }
                }
            });
        }
    }

    public void setBtnWidth(int i) {
        this.mBtnWidth = i;
    }

    public void setOnToolBarItemClickListener(OnToolBarItemClickListener onToolBarItemClickListener) {
        this.mItemClickListeners = onToolBarItemClickListener;
    }
}
