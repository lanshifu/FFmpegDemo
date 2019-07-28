package com.zzhoujay.markdown.style;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.text.Layout;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.widget.TextView;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.tl;
import java.lang.ref.WeakReference;

public class MarkDownBulletSpan extends BulletSpan {
    private static Path f;
    private static Path g;
    private final boolean a;
    private final int b;
    private final String c;
    private int d = 0;
    private int e;
    private WeakReference<TextView> h;

    public MarkDownBulletSpan(int i, int i2, int i3, TextView textView) {
        super(40, i2);
        this.d = i;
        if (i3 <= 0) {
            this.c = null;
        } else if (this.d == 1) {
            this.c = tl.a(i3);
        } else if (this.d >= 2) {
            this.c = tl.b(i3 - 1);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(i3);
            stringBuilder.append("");
            this.c = stringBuilder.toString();
        }
        this.a = true;
        this.b = i2;
        this.h = new WeakReference(textView);
    }

    public MarkDownBulletSpan(int i, int i2, int i3) {
        super(40, i2);
        this.d = i;
        if (i3 <= 0) {
            this.c = null;
        } else if (i == 1) {
            this.c = tl.a(i3);
        } else if (i >= 2) {
            this.c = tl.b(i3 - 1);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(i3);
            stringBuilder.append("");
            this.c = stringBuilder.toString();
        }
        this.a = true;
        this.b = i2;
    }

    public int getLeadingMargin(boolean z) {
        TextView textView = this.h != null ? (TextView) this.h.get() : null;
        if (this.c == null || textView == null) {
            this.e = ((this.d + 1) * 52) + 40;
        } else {
            this.e = (int) (((textView.getPaint().measureText(this.c) + 40.0f) * ((float) (this.d + 1))) + 40.0f);
        }
        return this.e;
    }

    @TargetApi(11)
    public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
        Canvas canvas2 = canvas;
        Paint paint2 = paint;
        int i8 = i;
        if (((Spanned) charSequence).getSpanStart(this) == i6) {
            int i9 = 0;
            if (this.a) {
                i9 = paint.getColor();
                paint2.setColor(this.b);
            }
            if (this.c != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.c);
                stringBuilder.append('.');
                canvas2.drawText(stringBuilder.toString(), ((((float) i8) - paint2.measureText(this.c)) + ((float) this.e)) - 40.0f, (float) i4, paint2);
            } else {
                Style style = paint.getStyle();
                if (this.d == 1) {
                    paint2.setStyle(Style.STROKE);
                } else {
                    paint2.setStyle(Style.FILL);
                }
                if (canvas.isHardwareAccelerated()) {
                    Path path;
                    if (this.d >= 2) {
                        if (g == null) {
                            g = new Path();
                            g.addRect(-7.2000003f, -7.2000003f, 7.2000003f, 7.2000003f, Direction.CW);
                        }
                        path = g;
                    } else {
                        if (f == null) {
                            f = new Path();
                            f.addCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 7.2000003f, Direction.CW);
                        }
                        path = f;
                    }
                    canvas.save();
                    canvas2.translate((float) ((i8 + this.e) - 40), ((float) (i3 + i5)) / 2.0f);
                    canvas2.drawPath(path, paint2);
                    canvas.restore();
                } else {
                    canvas2.drawCircle((float) ((i8 + this.e) - 40), ((float) (i3 + i5)) / 2.0f, 6.0f, paint2);
                }
                paint2.setStyle(style);
            }
            if (this.a) {
                paint2.setColor(i9);
            }
        }
    }
}
