package sj.keyboard.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.keyboard.view.R;
import java.util.ArrayList;
import java.util.Iterator;
import sj.keyboard.data.PageSetEntity;
import sj.keyboard.utils.EmoticonsKeyboardUtils;

public class EmoticonsIndicatorView extends LinearLayout {
    private static final int MARGIN_LEFT = 4;
    protected Context mContext;
    protected Drawable mDrawableNomal;
    protected Drawable mDrawableSelect;
    protected ArrayList<ImageView> mImageViews;
    protected LayoutParams mLeftLayoutParams;

    public EmoticonsIndicatorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        setOrientation(0);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.EmoticonsIndicatorView, 0, 0);
        try {
            this.mDrawableSelect = obtainStyledAttributes.getDrawable(R.styleable.EmoticonsIndicatorView_bmpSelect);
            this.mDrawableNomal = obtainStyledAttributes.getDrawable(R.styleable.EmoticonsIndicatorView_bmpNomal);
            if (this.mDrawableNomal == null) {
                this.mDrawableNomal = getResources().getDrawable(R.drawable.indicator_point_nomal);
            }
            if (this.mDrawableSelect == null) {
                this.mDrawableSelect = getResources().getDrawable(R.drawable.indicator_point_select);
            }
            this.mLeftLayoutParams = new LayoutParams(-2, -2);
            this.mLeftLayoutParams.leftMargin = EmoticonsKeyboardUtils.dip2px(context, 4.0f);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void playTo(int i, PageSetEntity pageSetEntity) {
        if (checkPageSetEntity(pageSetEntity)) {
            updateIndicatorCount(pageSetEntity.getPageCount());
            Iterator it = this.mImageViews.iterator();
            while (it.hasNext()) {
                ((ImageView) it.next()).setImageDrawable(this.mDrawableNomal);
            }
            ((ImageView) this.mImageViews.get(i)).setImageDrawable(this.mDrawableSelect);
        }
    }

    public void playBy(int i, int i2, PageSetEntity pageSetEntity) {
        if (checkPageSetEntity(pageSetEntity)) {
            updateIndicatorCount(pageSetEntity.getPageCount());
            if (i < 0 || i2 < 0 || i2 == i) {
                i = 0;
                i2 = 0;
            }
            if (i < 0) {
                i = 0;
                i2 = 0;
            }
            ImageView imageView = (ImageView) this.mImageViews.get(i2);
            ((ImageView) this.mImageViews.get(i)).setImageDrawable(this.mDrawableNomal);
            imageView.setImageDrawable(this.mDrawableSelect);
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean checkPageSetEntity(PageSetEntity pageSetEntity) {
        if (pageSetEntity == null || !pageSetEntity.isShowIndicator()) {
            setVisibility(8);
            return false;
        }
        setVisibility(0);
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void updateIndicatorCount(int i) {
        if (this.mImageViews == null) {
            this.mImageViews = new ArrayList();
        }
        if (i > this.mImageViews.size()) {
            int size = this.mImageViews.size();
            while (size < i) {
                ImageView imageView = new ImageView(this.mContext);
                imageView.setImageDrawable(size == 0 ? this.mDrawableSelect : this.mDrawableNomal);
                addView(imageView, this.mLeftLayoutParams);
                this.mImageViews.add(imageView);
                size++;
            }
        }
        for (int i2 = 0; i2 < this.mImageViews.size(); i2++) {
            if (i2 >= i) {
                ((ImageView) this.mImageViews.get(i2)).setVisibility(8);
            } else {
                ((ImageView) this.mImageViews.get(i2)).setVisibility(0);
            }
        }
    }
}
