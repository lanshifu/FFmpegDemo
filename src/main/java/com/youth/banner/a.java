package com.youth.banner;

import android.content.Context;
import android.widget.Scroller;

/* compiled from: BannerScroller */
public class a extends Scroller {
    private int a = 800;

    public a(Context context) {
        super(context);
    }

    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        super.startScroll(i, i2, i3, i4, this.a);
    }

    public void startScroll(int i, int i2, int i3, int i4) {
        super.startScroll(i, i2, i3, i4, this.a);
    }

    public void a(int i) {
        this.a = i;
    }
}
