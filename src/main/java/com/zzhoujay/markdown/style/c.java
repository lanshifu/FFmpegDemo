package com.zzhoujay.markdown.style;

import android.graphics.Paint.FontMetricsInt;
import android.text.style.LineHeightSpan;

/* compiled from: ScaleHeightSpan */
public class c implements LineHeightSpan {
    private float a;

    public c(float f) {
        this.a = f;
    }

    public void chooseHeight(CharSequence charSequence, int i, int i2, int i3, int i4, FontMetricsInt fontMetricsInt) {
        fontMetricsInt.ascent = (int) (((float) fontMetricsInt.ascent) * this.a);
        fontMetricsInt.top = (int) (((float) fontMetricsInt.top) * this.a);
        fontMetricsInt.descent = (int) (((float) fontMetricsInt.descent) * this.a);
        fontMetricsInt.bottom = (int) (((float) fontMetricsInt.bottom) * this.a);
    }
}
