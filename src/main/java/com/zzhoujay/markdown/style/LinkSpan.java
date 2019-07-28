package com.zzhoujay.markdown.style;

import android.text.TextPaint;
import android.text.style.URLSpan;

public class LinkSpan extends URLSpan {
    private int a;

    public LinkSpan(String str, int i) {
        super(str);
        this.a = i;
    }

    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setColor(this.a);
        textPaint.setUnderlineText(false);
    }
}
