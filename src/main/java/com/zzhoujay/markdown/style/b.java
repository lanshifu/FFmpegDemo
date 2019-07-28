package com.zzhoujay.markdown.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.style.ReplacementSpan;

/* compiled from: CodeSpan */
public class b extends ReplacementSpan {
    private Drawable a;
    private float b;
    private int c;

    public b(int i) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadius(10.0f);
        this.a = gradientDrawable;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        this.b = paint.measureText("t");
        this.c = (int) (paint.measureText(charSequence, i, i2) + (this.b * 2.0f));
        return this.c;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        float f2 = f;
        int i6 = (int) f2;
        this.a.setBounds(i6, i3, this.c + i6, i5);
        Canvas canvas2 = canvas;
        this.a.draw(canvas);
        canvas2.drawText(charSequence, i, i2, f2 + this.b, (float) i4, paint);
    }
}
