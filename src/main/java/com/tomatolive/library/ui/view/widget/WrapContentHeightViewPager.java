package com.tomatolive.library.ui.view.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

public class WrapContentHeightViewPager extends ViewPager {
    public WrapContentHeightViewPager(Context context) {
        super(context);
    }

    public WrapContentHeightViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        i2 = getChildCount();
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            View childAt = getChildAt(i4);
            childAt.measure(i, MeasureSpec.makeMeasureSpec(0, 0));
            if (childAt.getMeasuredHeight() > i3) {
                i3 = childAt.getMeasuredHeight();
            }
        }
        if (i3 > 0) {
            setMeasuredDimension(getMeasuredWidth(), i3);
        }
    }
}
