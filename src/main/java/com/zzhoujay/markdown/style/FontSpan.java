package com.zzhoujay.markdown.style;

import android.annotation.SuppressLint;
import android.text.ParcelableSpan;
import android.text.TextPaint;
import android.text.style.StyleSpan;

@SuppressLint({"ParcelCreator"})
public class FontSpan extends StyleSpan implements ParcelableSpan {
    private final float a;
    private final int b;

    public FontSpan(float f, int i, int i2) {
        super(i);
        this.a = f;
        this.b = i2;
    }

    public void updateMeasureState(TextPaint textPaint) {
        super.updateMeasureState(textPaint);
        textPaint.setTextSize(textPaint.getTextSize() * this.a);
    }

    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        updateMeasureState(textPaint);
        textPaint.setColor(this.b);
    }
}
