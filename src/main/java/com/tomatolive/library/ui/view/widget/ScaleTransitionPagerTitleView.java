package com.tomatolive.library.ui.view.widget;

import android.content.Context;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

public class ScaleTransitionPagerTitleView extends ColorTransitionPagerTitleView {
    private float mMinScale = 0.86f;

    public ScaleTransitionPagerTitleView(Context context) {
        super(context);
    }

    public void onEnter(int i, int i2, float f, boolean z) {
        super.onEnter(i, i2, f, z);
        setScaleX(this.mMinScale + ((1.0f - this.mMinScale) * f));
        setScaleY(this.mMinScale + ((1.0f - this.mMinScale) * f));
    }

    public void onLeave(int i, int i2, float f, boolean z) {
        super.onLeave(i, i2, f, z);
        setScaleX(((this.mMinScale - 1.0f) * f) + 1.0f);
        setScaleY(((this.mMinScale - 1.0f) * f) + 1.0f);
    }

    public float getMinScale() {
        return this.mMinScale;
    }

    public void setMinScale(float f) {
        this.mMinScale = f;
    }
}
