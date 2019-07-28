package master.flame.danmaku.danmaku.model.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.text.TextPaint;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.zf;
import defpackage.zs;
import java.util.HashMap;
import java.util.Map;
import master.flame.danmaku.danmaku.model.android.a.a;

/* compiled from: SimpleTextCacheStuffer */
public class i extends b {
    private static final Map<Float, Float> a = new HashMap();

    /* Access modifiers changed, original: protected */
    public void a(zf zfVar, Canvas canvas, float f, float f2) {
    }

    /* Access modifiers changed, original: protected */
    public Float a(zf zfVar, Paint paint) {
        Float valueOf = Float.valueOf(paint.getTextSize());
        Float f = (Float) a.get(valueOf);
        if (f != null) {
            return f;
        }
        FontMetrics fontMetrics = paint.getFontMetrics();
        f = Float.valueOf((fontMetrics.descent - fontMetrics.ascent) + fontMetrics.leading);
        a.put(valueOf, f);
        return f;
    }

    public void measure(zf zfVar, TextPaint textPaint, boolean z) {
        float f = CropImageView.DEFAULT_ASPECT_RATIO;
        Float valueOf = Float.valueOf(CropImageView.DEFAULT_ASPECT_RATIO);
        if (zfVar.c == null) {
            if (zfVar.b != null) {
                f = textPaint.measureText(zfVar.b.toString());
                valueOf = a(zfVar, textPaint);
            }
            zfVar.n = f;
            zfVar.o = valueOf.floatValue();
            return;
        }
        valueOf = a(zfVar, textPaint);
        for (String str : zfVar.c) {
            if (str.length() > 0) {
                f = Math.max(textPaint.measureText(str), f);
            }
        }
        zfVar.n = f;
        zfVar.o = ((float) zfVar.c.length) * valueOf.floatValue();
    }

    /* Access modifiers changed, original: protected */
    public void a(zf zfVar, String str, Canvas canvas, float f, float f2, Paint paint) {
        if (str != null) {
            canvas.drawText(str, f, f2, paint);
        } else {
            canvas.drawText(zfVar.b.toString(), f, f2, paint);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(zf zfVar, String str, Canvas canvas, float f, float f2, TextPaint textPaint, boolean z) {
        if (z && (zfVar instanceof zs)) {
            textPaint.setAlpha(255);
        }
        if (str != null) {
            canvas.drawText(str, f, f2, textPaint);
        } else {
            canvas.drawText(zfVar.b.toString(), f, f2, textPaint);
        }
    }

    public void clearCaches() {
        a.clear();
    }

    public void drawDanmaku(zf zfVar, Canvas canvas, float f, float f2, boolean z, a aVar) {
        float f3;
        float f4;
        zf zfVar2 = zfVar;
        boolean z2 = z;
        a aVar2 = aVar;
        float f5 = f + ((float) zfVar2.l);
        float f6 = f2 + ((float) zfVar2.l);
        if (zfVar2.k != 0) {
            f5 += 4.0f;
            f6 += 4.0f;
        }
        float f7 = f5;
        float f8 = f6;
        aVar2.b(z2);
        Paint a = aVar2.a(zfVar2, z2);
        a(zfVar, canvas, f, f2);
        boolean z3 = true;
        float f9;
        if (zfVar2.c != null) {
            String[] strArr = zfVar2.c;
            String[] strArr2;
            if (strArr.length == 1) {
                if (aVar2.a(zfVar2)) {
                    float f10;
                    aVar2.a(zfVar2, a, true);
                    f5 = f8 - a.ascent();
                    if (aVar2.i) {
                        f10 = f5 + aVar2.e;
                        f3 = aVar2.d + f7;
                    } else {
                        f10 = f5;
                        f3 = f7;
                    }
                    strArr2 = strArr;
                    f9 = f10;
                    z3 = false;
                    a(zfVar, strArr[0], canvas, f3, f9, a);
                } else {
                    strArr2 = strArr;
                    z3 = false;
                }
                aVar2.a(zfVar2, a, z3);
                a(zfVar, strArr2[z3], canvas, f7, f8 - a.ascent(), a, z);
            } else {
                strArr2 = strArr;
                float length = (zfVar2.o - ((float) (zfVar2.l * 2))) / ((float) strArr2.length);
                int i = 0;
                while (i < strArr2.length) {
                    int i2;
                    if (strArr2[i] == null || strArr2[i].length() == 0) {
                        i2 = i;
                    } else {
                        boolean z4;
                        if (aVar2.a(zfVar2)) {
                            float f11;
                            aVar2.a(zfVar2, a, z3);
                            f5 = ((((float) i) * length) + f8) - a.ascent();
                            if (aVar2.i) {
                                f11 = f5 + aVar2.e;
                                f3 = aVar2.d + f7;
                            } else {
                                f11 = f5;
                                f3 = f7;
                            }
                            i2 = i;
                            z4 = false;
                            a(zfVar, strArr2[i], canvas, f3, f11, a);
                        } else {
                            i2 = i;
                            z4 = false;
                        }
                        aVar2.a(zfVar2, a, z4);
                        a(zfVar, strArr2[i2], canvas, f7, ((((float) i2) * length) + f8) - a.ascent(), a, z);
                    }
                    i = i2 + 1;
                    z3 = true;
                }
            }
        } else {
            if (aVar2.a(zfVar2)) {
                aVar2.a(zfVar2, a, true);
                f5 = f8 - a.ascent();
                if (aVar2.i) {
                    f9 = f5 + aVar2.e;
                    f3 = aVar2.d + f7;
                } else {
                    f9 = f5;
                    f3 = f7;
                }
                a(zfVar, null, canvas, f3, f9, a);
            }
            aVar2.a(zfVar2, a, false);
            a(zfVar, null, canvas, f7, f8 - a.ascent(), a, z);
        }
        if (zfVar2.i != 0) {
            f3 = (f2 + zfVar2.o) - ((float) aVar2.c);
            f4 = f + zfVar2.n;
            canvas.drawLine(f, f3, f4, f3, aVar2.c(zfVar2));
        }
        if (zfVar2.k != 0) {
            f4 = f + zfVar2.n;
            f3 = f2 + zfVar2.o;
            canvas.drawRect(f, f2, f4, f3, aVar2.b(zfVar2));
        }
    }
}
