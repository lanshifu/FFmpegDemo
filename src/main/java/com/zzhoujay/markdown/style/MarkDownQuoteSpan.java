package com.zzhoujay.markdown.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.text.Layout;
import android.text.style.QuoteSpan;

public class MarkDownQuoteSpan extends QuoteSpan {
    public int getLeadingMargin(boolean z) {
        return 55;
    }

    public MarkDownQuoteSpan(int i) {
        super(i);
    }

    public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
        Paint paint2 = paint;
        int i8 = i;
        Style style = paint.getStyle();
        int color = paint.getColor();
        paint.setStyle(Style.FILL);
        paint.setColor(getColor());
        canvas.drawRect((float) i8, (float) i3, (float) (i8 + (i2 * 15)), (float) i5, paint);
        paint.setStyle(style);
        paint.setColor(color);
    }
}
