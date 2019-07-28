package com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;

public class PreviewViewPager extends ViewPager {
    public PreviewViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* Access modifiers changed, original: protected */
    public boolean canScroll(View view, boolean z, int i, int i2, int i3) {
        if (!(view instanceof ImageViewTouch)) {
            return super.canScroll(view, z, i, i2, i3);
        }
        boolean z2 = ((ImageViewTouch) view).a(i) || super.canScroll(view, z, i, i2, i3);
        return z2;
    }
}
