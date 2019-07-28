package com.zzhoujay.markdown.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.style.LineHeightSpan;
import android.text.style.ReplacementSpan;
import android.util.Pair;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CodeBlockSpan */
public class a extends ReplacementSpan implements LineHeightSpan {
    private int a;
    private Drawable b;
    private int c;
    private int d;
    private CharSequence[] e;
    private List<Pair<Integer, Integer>> f;

    public a(int i, int i2, CharSequence... charSequenceArr) {
        this.a = i;
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i2);
        gradientDrawable.setCornerRadius(10.0f);
        this.b = gradientDrawable;
        this.e = charSequenceArr;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        if (fontMetricsInt != null && this.f == null) {
            this.f = new ArrayList();
            for (CharSequence charSequence2 : this.e) {
                this.f.addAll(b(charSequence2, 0, charSequence2.length(), paint));
            }
        }
        return this.a;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        float f2 = f;
        int i6 = i3;
        int i7 = (int) f2;
        this.b.setBounds(i7, i6, this.a + i7, i5);
        Canvas canvas2 = canvas;
        this.b.draw(canvas);
        f2 += 30.0f;
        int i8 = (this.c + (this.d / 2)) + i6;
        int i9 = 0;
        for (Pair pair : this.f) {
            CharSequence charSequence2 = this.e[i9];
            canvas.drawText(charSequence2, ((Integer) pair.first).intValue(), ((Integer) pair.second).intValue(), f2 + 30.0f, (float) i8, paint);
            if (((Integer) pair.second).intValue() >= charSequence2.length()) {
                i9++;
            }
            i8 += this.d;
        }
    }

    private int a(CharSequence charSequence, int i, int i2, Paint paint) {
        int i3 = i;
        while (paint.measureText(charSequence, i, i3) < ((float) (this.a - 60))) {
            i3++;
            if (i3 > i2) {
                break;
            }
        }
        return i3 - 1;
    }

    private int a(CharSequence charSequence, int i, int i2, int i3, int i4, Paint paint) {
        if (i3 > i2) {
            return i2;
        }
        while (paint.measureText(charSequence, i, i3) < ((float) (this.a - 60))) {
            i3++;
            if (i3 <= i2) {
                if (i3 > i4) {
                    break;
                }
            }
            break;
        }
        return i3 - 1;
    }

    private List<Pair<Integer, Integer>> b(CharSequence charSequence, int i, int i2, Paint paint) {
        ArrayList arrayList = new ArrayList();
        int a = a(charSequence, i, i2, paint);
        arrayList.add(new Pair(Integer.valueOf(i), Integer.valueOf(a)));
        i = a;
        while (a < i2) {
            i += a;
            i = a(charSequence, a, i2, i - 4, i + 4, paint);
            int i3 = i - a;
            arrayList.add(new Pair(Integer.valueOf(a), Integer.valueOf(i)));
            a = i;
            i = i3;
        }
        return arrayList;
    }

    public void chooseHeight(CharSequence charSequence, int i, int i2, int i3, int i4, FontMetricsInt fontMetricsInt) {
        int size = this.f.size();
        this.d = fontMetricsInt.bottom - fontMetricsInt.top;
        this.c = -fontMetricsInt.top;
        fontMetricsInt.ascent = fontMetricsInt.top;
        fontMetricsInt.bottom += size * this.d;
        fontMetricsInt.descent = fontMetricsInt.bottom;
    }
}
