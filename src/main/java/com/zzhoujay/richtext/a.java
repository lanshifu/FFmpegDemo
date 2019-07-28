package com.zzhoujay.richtext;

import android.graphics.Color;
import android.support.annotation.ColorInt;

/* compiled from: LinkHolder */
public class a {
    private static final int a = Color.parseColor("#4078C0");
    private final String b;
    @ColorInt
    private int c = a;
    private boolean d = true;

    public a(String str) {
        this.b = str;
    }

    @ColorInt
    public int a() {
        return this.c;
    }

    public boolean b() {
        return this.d;
    }

    public String c() {
        return this.b;
    }
}
