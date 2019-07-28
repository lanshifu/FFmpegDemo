package com.wj.rebound.ui;

import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.FrameLayout.LayoutParams;

/* compiled from: Util */
public abstract class a {
    public static int a(float f, Resources resources) {
        return (int) TypedValue.applyDimension(1, f, resources.getDisplayMetrics());
    }

    public static LayoutParams a(int i, int i2) {
        return new LayoutParams(i, i2);
    }

    public static LayoutParams a() {
        return a(-1, -1);
    }

    public static LayoutParams b() {
        return a(-1, -2);
    }
}
