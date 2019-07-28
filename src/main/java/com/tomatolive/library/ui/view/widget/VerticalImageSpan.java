package com.tomatolive.library.ui.view.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

public class VerticalImageSpan extends ImageSpan {
    public VerticalImageSpan(Drawable drawable) {
        super(drawable);
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        Rect bounds = getDrawable().getBounds();
        if (fontMetricsInt != null) {
            FontMetricsInt fontMetricsInt2 = paint.getFontMetricsInt();
            i = fontMetricsInt2.bottom - fontMetricsInt2.top;
            int i3 = (bounds.bottom - bounds.top) / 2;
            i /= 4;
            i2 = i3 - i;
            i3 = -(i3 + i);
            fontMetricsInt.ascent = i3;
            fontMetricsInt.top = i3;
            fontMetricsInt.bottom = i2;
            fontMetricsInt.descent = i2;
        }
        return bounds.right;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        Drawable drawable = getDrawable();
        canvas.save();
        canvas.translate(f, (float) ((((i5 - i3) - drawable.getBounds().bottom) / 2) + i3));
        drawable.draw(canvas);
        canvas.restore();
    }
}
