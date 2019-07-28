package com.zzhoujay.markdown.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.drawable.Drawable;
import android.text.style.LineHeightSpan;
import android.text.style.ReplacementSpan;

/* compiled from: UnderLineSpan */
public class d extends ReplacementSpan implements LineHeightSpan {
    private int a;
    private int b;
    private Drawable c;

    public d(Drawable drawable, int i, int i2) {
        this.a = i2;
        this.b = i;
        this.c = drawable;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        return (int) paint.measureText(charSequence, i, i2);
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        i = (int) f;
        this.c.setBounds(i, i5 - this.a, this.b + i, i5);
        this.c.draw(canvas);
    }

    public void chooseHeight(CharSequence charSequence, int i, int i2, int i3, int i4, FontMetricsInt fontMetricsInt) {
        fontMetricsInt.top /= 3;
        fontMetricsInt.ascent /= 3;
        fontMetricsInt.bottom /= 3;
        fontMetricsInt.descent /= 3;
    }
}
