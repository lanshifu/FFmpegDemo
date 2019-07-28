package sj.keyboard.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.keyboard.view.R;
import sj.keyboard.utils.EmoticonsKeyboardUtils;
import sj.keyboard.widget.SoftKeyboardSizeWatchLayout.OnResizeListener;

public abstract class AutoHeightLayout extends SoftKeyboardSizeWatchLayout implements OnResizeListener {
    private static final int ID_CHILD = R.id.id_autolayout;
    protected boolean mConfigurationChangedFlag = false;
    protected Context mContext;
    protected int mMaxParentHeight;
    protected int mSoftKeyboardHeight;
    private OnMaxParentHeightChangeListener maxParentHeightChangeListener;

    public interface OnMaxParentHeightChangeListener {
        void onMaxParentHeightChange(int i);
    }

    public void OnSoftClose() {
    }

    public abstract void onSoftKeyboardHeightChanged(int i);

    public AutoHeightLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mSoftKeyboardHeight = EmoticonsKeyboardUtils.getDefKeyboardHeight(this.mContext);
        addOnResizeListener(this);
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        int childCount = getChildCount();
        if (childCount <= 1) {
            super.addView(view, i, layoutParams);
            RelativeLayout.LayoutParams layoutParams2;
            if (childCount == 0) {
                if (view.getId() < 0) {
                    view.setId(ID_CHILD);
                }
                layoutParams2 = (RelativeLayout.LayoutParams) view.getLayoutParams();
                layoutParams2.addRule(12);
                view.setLayoutParams(layoutParams2);
                return;
            } else if (childCount == 1) {
                layoutParams2 = (RelativeLayout.LayoutParams) view.getLayoutParams();
                layoutParams2.addRule(2, ID_CHILD);
                view.setLayoutParams(layoutParams2);
                return;
            } else {
                return;
            }
        }
        throw new IllegalStateException("can host only one direct child");
    }

    /* Access modifiers changed, original: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        onSoftKeyboardHeightChanged(this.mSoftKeyboardHeight);
    }

    /* Access modifiers changed, original: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        if (this.mMaxParentHeight == 0) {
            this.mMaxParentHeight = i2;
        }
    }

    public void updateMaxParentHeight(int i) {
        this.mMaxParentHeight = i;
        if (this.maxParentHeightChangeListener != null) {
            this.maxParentHeightChangeListener.onMaxParentHeightChange(i);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mConfigurationChangedFlag = true;
        this.mScreenHeight = 0;
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        if (this.mConfigurationChangedFlag) {
            this.mConfigurationChangedFlag = false;
            Rect rect = new Rect();
            ((Activity) this.mContext).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            if (this.mScreenHeight == 0) {
                this.mScreenHeight = rect.bottom;
            }
            this.mMaxParentHeight = this.mScreenHeight - rect.bottom;
        }
        if (this.mMaxParentHeight != 0) {
            super.onMeasure(i, MeasureSpec.makeMeasureSpec(this.mMaxParentHeight, MeasureSpec.getMode(i2)));
            return;
        }
        super.onMeasure(i, i2);
    }

    public void OnSoftPop(int i) {
        if (this.mSoftKeyboardHeight != i) {
            this.mSoftKeyboardHeight = i;
            EmoticonsKeyboardUtils.setDefKeyboardHeight(this.mContext, this.mSoftKeyboardHeight);
            onSoftKeyboardHeightChanged(this.mSoftKeyboardHeight);
        }
    }

    public void setOnMaxParentHeightChangeListener(OnMaxParentHeightChangeListener onMaxParentHeightChangeListener) {
        this.maxParentHeightChangeListener = onMaxParentHeightChangeListener;
    }
}
