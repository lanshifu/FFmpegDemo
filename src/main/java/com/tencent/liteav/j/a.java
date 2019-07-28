package com.tencent.liteav.j;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import com.tencent.liteav.basic.log.TXCLog;
import com.yalantis.ucrop.view.CropImageView;

/* compiled from: BitmapUtil */
public class a {
    public static long a(int i) {
        switch (i) {
            case 3:
            case 6:
                return 1500;
            case 4:
            case 5:
                return 100;
            default:
                return 1000;
        }
    }

    public static long b(int i) {
        switch (i) {
            case 3:
                return 1000;
            case 4:
            case 5:
                return 2500;
            case 6:
                return 1500;
            default:
                return 500;
        }
    }

    public static Bitmap a(float f, Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (!(createBitmap == bitmap || bitmap == null || bitmap.isRecycled())) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap a(Bitmap bitmap, int i) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAlpha(i);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRect(rectF, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    public static float a(int i, long j) {
        long a = a(i);
        long b = b(i);
        long j2 = a + b;
        j -= (j / j2) * j2;
        StringBuilder stringBuilder;
        if (j < 0 || j > a) {
            float f = ((float) (j - a)) / ((float) b);
            stringBuilder = new StringBuilder();
            stringBuilder.append("setBitmapsAndDisplayRatio, in motion status, cropOffsetRatio = ");
            stringBuilder.append(f);
            stringBuilder.append(", remainTimeMs = ");
            stringBuilder.append(j);
            TXCLog.i("BitmapUtil", stringBuilder.toString());
            return f;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("setBitmapsAndDisplayRatio, in stay status, cropOffsetRatio = 0, remainTimeMs = ");
        stringBuilder.append(j);
        TXCLog.i("BitmapUtil", stringBuilder.toString());
        return CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public static float b(int i, long j) {
        long a = a(i);
        long b = b(i);
        long j2 = a + b;
        j -= (j / j2) * j2;
        switch (i) {
            case 3:
                if ((j < 0 || j > a) && j > a && j <= j2) {
                    return 1.0f - (((float) (j - a)) / ((float) b));
                }
            case 4:
                if ((j < 0 || j > a) && j > a && j < j2) {
                    return ((((float) (j - a)) * 0.1f) / ((float) b)) + 1.0f;
                }
            case 5:
                if (j >= 0 && j <= a) {
                    return 1.1f;
                }
                if (j > a && j <= j2) {
                    return 1.1f - ((((float) (j - a)) * 0.1f) / ((float) b));
                }
                break;
        }
        return 1.0f;
    }

    /* JADX WARNING: Missing block: B:15:0x004a, code skipped:
            if (r16 <= (r13 + r10)) goto L_0x0075;
     */
    public static float c(int r19, long r20) {
        /*
        r2 = a(r19);
        r4 = b(r19);
        r6 = r2 + r4;
        r8 = r20 / r6;
        r8 = r8 * r6;
        r0 = r20 - r8;
        r8 = 0;
        r10 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        switch(r19) {
            case 4: goto L_0x0031;
            case 5: goto L_0x0031;
            case 6: goto L_0x001a;
            default: goto L_0x0017;
        };
    L_0x0017:
        r18 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        goto L_0x0075;
    L_0x001a:
        r11 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r11 < 0) goto L_0x0023;
    L_0x001e:
        r8 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r8 > 0) goto L_0x0023;
    L_0x0022:
        goto L_0x0077;
    L_0x0023:
        r8 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r8 <= 0) goto L_0x0017;
    L_0x0027:
        r8 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
        if (r8 > 0) goto L_0x0017;
    L_0x002b:
        r0 = r0 - r2;
        r0 = (float) r0;
        r1 = (float) r4;
        r0 = r0 / r1;
        r10 = r10 - r0;
        goto L_0x0077;
    L_0x0031:
        r11 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        r8 = 4605380978949069210; // 0x3fe999999999999a float:-1.5881868E-23 double:0.8;
        if (r11 < 0) goto L_0x004d;
    L_0x003a:
        r11 = (double) r0;
        r13 = (double) r2;
        r16 = r11;
        r10 = (double) r4;
        java.lang.Double.isNaN(r10);
        r10 = r10 * r8;
        java.lang.Double.isNaN(r13);
        r13 = r13 + r10;
        r10 = (r16 > r13 ? 1 : (r16 == r13 ? 0 : -1));
        if (r10 > 0) goto L_0x004d;
    L_0x004c:
        goto L_0x0075;
    L_0x004d:
        r10 = (double) r0;
        r12 = (double) r2;
        r14 = (double) r4;
        java.lang.Double.isNaN(r14);
        r14 = r14 * r8;
        java.lang.Double.isNaN(r12);
        r12 = r12 + r14;
        r8 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1));
        if (r8 <= 0) goto L_0x0017;
    L_0x005d:
        r8 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
        if (r8 > 0) goto L_0x0017;
    L_0x0061:
        r0 = r0 - r2;
        r0 = (float) r0;
        r1 = (float) r4;
        r2 = 1061997773; // 0x3f4ccccd float:0.8 double:5.246966156E-315;
        r2 = r2 * r1;
        r0 = r0 - r2;
        r2 = 1045220557; // 0x3e4ccccd float:0.2 double:5.164075695E-315;
        r1 = r1 * r2;
        r0 = r0 / r1;
        r18 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r10 = r18 - r0;
        goto L_0x0077;
    L_0x0075:
        r10 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
    L_0x0077:
        return r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.j.a.c(int, long):float");
    }

    public static int d(int i, long j) {
        long a = a(i);
        long b = b(i);
        long j2 = a + b;
        j -= (j / j2) * j2;
        if (i != 3) {
            return 0;
        }
        return ((j <= 0 || j > a) && j > a && j <= j2) ? (int) ((((float) (j - a)) / ((float) b)) * 360.0f) : 0;
    }
}
