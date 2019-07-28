package com.zzhoujay.markdown.style;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.text.Layout;
import android.text.Spanned;
import android.text.style.QuoteSpan;
import android.widget.TextView;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.tl;
import java.lang.ref.WeakReference;

public class QuotaBulletSpan extends QuoteSpan {
    private static Path a;
    private static Path b;
    private final String c;
    private int d = 0;
    private int e;
    private int f;
    private WeakReference<TextView> g;
    private int h;

    public QuotaBulletSpan(int i, int i2, int i3, int i4, int i5, TextView textView) {
        super(i3);
        this.h = i;
        this.d = i2;
        if (i5 <= 0) {
            this.c = null;
        } else if (i2 == 1) {
            this.c = tl.a(i5);
        } else if (i2 >= 2) {
            this.c = tl.b(i5 - 1);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(i5);
            stringBuilder.append("");
            this.c = stringBuilder.toString();
        }
        this.e = i4;
        this.g = new WeakReference(textView);
    }

    @TargetApi(11)
    public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
        int i8;
        Canvas canvas2 = canvas;
        Paint paint2 = paint;
        int i9 = i;
        int i10 = i3;
        int i11 = i5;
        Style style = paint.getStyle();
        int color = paint.getColor();
        paint2.setStyle(Style.FILL);
        paint2.setColor(getColor());
        for (int i12 = 0; i12 <= this.h; i12++) {
            i8 = (i12 * 55) + i9;
            canvas.drawRect((float) i8, (float) i10, (float) (i8 + (i2 * 15)), (float) i11, paint);
        }
        paint2.setStyle(style);
        paint2.setColor(color);
        if (((Spanned) charSequence).getSpanStart(this) == i6) {
            i8 = paint.getColor();
            paint2.setColor(this.e);
            if (this.c != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.c);
                stringBuilder.append('.');
                canvas2.drawText(stringBuilder.toString(), ((((float) i9) - paint2.measureText(this.c)) + ((float) this.f)) - 40.0f, (float) i4, paint2);
            } else {
                Style style2 = paint.getStyle();
                if (this.d == 1) {
                    paint2.setStyle(Style.STROKE);
                } else {
                    paint2.setStyle(Style.FILL);
                }
                if (canvas.isHardwareAccelerated()) {
                    Path path;
                    if (this.d >= 2) {
                        if (b == null) {
                            b = new Path();
                            b.addRect(-7.2000003f, -7.2000003f, 7.2000003f, 7.2000003f, Direction.CW);
                        }
                        path = b;
                    } else {
                        if (a == null) {
                            a = new Path();
                            a.addCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 7.2000003f, Direction.CW);
                        }
                        path = a;
                    }
                    canvas.save();
                    canvas2.translate((float) ((this.f + i9) - 40), ((float) (i10 + i11)) / 2.0f);
                    canvas2.drawPath(path, paint2);
                    canvas.restore();
                } else {
                    canvas2.drawCircle((float) ((this.f + i9) - 40), ((float) (i10 + i11)) / 2.0f, 6.0f, paint2);
                }
                paint2.setStyle(style2);
            }
            paint2.setColor(i8);
        }
    }

    public int getLeadingMargin(boolean z) {
        if (this.g == null && this.f != 0) {
            return this.f;
        }
        TextView textView = (TextView) this.g.get();
        if (this.c == null || textView == null) {
            this.f = ((this.d + 1) * 52) + 40;
        } else {
            this.f = (int) (((textView.getPaint().measureText(this.c) + 40.0f) * ((float) (this.d + 1))) + 40.0f);
        }
        this.f += (this.h + 1) * 55;
        return this.f;
    }
}
