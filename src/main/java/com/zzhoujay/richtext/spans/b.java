package com.zzhoujay.richtext.spans;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import android.view.View;
import defpackage.tw;
import defpackage.tx;
import java.util.List;

/* compiled from: ClickableImageSpan */
public class b extends ImageSpan implements d {
    private float a;
    private final int b;
    private final List<String> c;
    private final tx d;
    private final tw e;

    public b(Drawable drawable, b bVar, tw twVar, tx txVar) {
        super(drawable, bVar.getSource());
        this.c = bVar.c;
        this.b = bVar.b;
        this.e = twVar;
        this.d = txVar;
    }

    public b(Drawable drawable, List<String> list, int i, tw twVar, tx txVar) {
        super(drawable, (String) list.get(i));
        this.c = list;
        this.b = i;
        this.e = twVar;
        this.d = txVar;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        super.draw(canvas, charSequence, i, i2, f, i3, i4, i5, paint);
        this.a = f;
    }

    public boolean a(int i) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            Rect bounds = drawable.getBounds();
            float f = (float) i;
            if (f <= ((float) bounds.right) + this.a && f >= ((float) bounds.left) + this.a) {
                return true;
            }
        }
        return false;
    }

    public void onClick(View view) {
        if (this.e != null) {
            this.e.a(this.c, this.b);
        }
    }

    public boolean a(View view) {
        return this.d != null && this.d.a(this.c, this.b);
    }

    public b a() {
        return new b(null, this.c, this.b, null, null);
    }
}
