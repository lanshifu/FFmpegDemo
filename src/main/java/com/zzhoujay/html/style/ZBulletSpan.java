package com.zzhoujay.html.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.text.Layout;
import android.text.Spanned;
import android.text.style.BulletSpan;
import com.yalantis.ucrop.view.CropImageView;

public class ZBulletSpan extends BulletSpan {
    private static Path a;
    private final int b = 15;
    private final boolean c = false;
    private final int d = 0;

    public int getLeadingMargin(boolean z) {
        return this.b + 10;
    }

    public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
        if (((Spanned) charSequence).getSpanStart(this) == i6) {
            Style style = paint.getStyle();
            int i8 = 0;
            if (this.c) {
                i8 = paint.getColor();
                paint.setColor(this.d);
            }
            paint.setStyle(Style.FILL);
            if (canvas.isHardwareAccelerated()) {
                if (a == null) {
                    a = new Path();
                    a.addCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 6.0f, Direction.CW);
                }
                canvas.save();
                canvas.translate((float) (i + (i2 * 5)), ((float) (i3 + i5)) / 2.0f);
                canvas.drawPath(a, paint);
                canvas.restore();
            } else {
                canvas.drawCircle((float) (i + (i2 * 5)), ((float) (i3 + i5)) / 2.0f, 5.0f, paint);
            }
            if (this.c) {
                paint.setColor(i8);
            }
            paint.setStyle(style);
        }
    }
}
