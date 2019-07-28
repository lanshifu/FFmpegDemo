package com.zzhoujay.richtext.spans;

import android.annotation.SuppressLint;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.view.View;
import com.zzhoujay.richtext.a;
import defpackage.ty;
import defpackage.tz;

@SuppressLint({"ParcelCreator"})
public class LongClickableURLSpan extends URLSpan implements d {
    private final ty a;
    private final tz b;
    private final a c;

    public LongClickableURLSpan(a aVar, ty tyVar, tz tzVar) {
        super(aVar.c());
        this.a = tyVar;
        this.b = tzVar;
        this.c = aVar;
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(this.c.a());
        textPaint.setUnderlineText(this.c.b());
    }

    public void onClick(View view) {
        if (this.a == null || !this.a.a(getURL())) {
            super.onClick(view);
        }
    }

    public boolean a(View view) {
        return this.b != null && this.b.a(getURL());
    }
}
