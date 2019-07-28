package com.tencent.liteav.e;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import com.tencent.liteav.basic.e.i;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.e;
import com.yalantis.ucrop.view.CropImageView;
import java.util.List;

/* compiled from: BitmapCombineRender */
public class f {
    private final String a = "BitmapCombineRender";
    private com.tencent.liteav.l.a b;
    private int c;
    private int d;
    private int e;
    private a f;
    private a g;
    private int h = -1;

    /* compiled from: BitmapCombineRender */
    protected class a {
        public int a;
        public int b;
        public int c;

        protected a() {
        }
    }

    public f(Context context, int i, int i2) {
        this.b = new com.tencent.liteav.l.a(context);
        this.c = i;
        this.d = i2;
    }

    public int a(e eVar, int i, boolean z) {
        if (z) {
            return this.h;
        }
        List w = eVar.w();
        if (w == null || w.size() == 0) {
            TXCLog.e("BitmapCombineRender", "bitmapList is empty");
            return -1;
        }
        int i2;
        com.tencent.liteav.basic.e.a aVar;
        Bitmap bitmap = (Bitmap) w.get(0);
        if (this.f == null) {
            this.f = new a();
            this.f.a = i.a(bitmap, -1, false);
            this.f.b = bitmap.getWidth();
            this.f.c = bitmap.getHeight();
        } else if (bitmap.getWidth() == this.f.b && bitmap.getHeight() == this.f.c) {
            i.a(bitmap, this.f.a, false);
        } else {
            GLES20.glDeleteTextures(1, new int[]{this.f.a}, 0);
            this.f.a = i.a(bitmap, -1, false);
            this.f.b = bitmap.getWidth();
            this.f.c = bitmap.getHeight();
        }
        com.tencent.liteav.basic.e.a a = a(bitmap.getWidth(), bitmap.getHeight());
        com.tencent.liteav.basic.e.a aVar2 = new com.tencent.liteav.basic.e.a(0, 0, 0, 0);
        if (w.size() > 1) {
            Bitmap bitmap2 = (Bitmap) w.get(1);
            if (this.g == null) {
                this.g = new a();
                this.g.a = i.a(bitmap2, -1, false);
                this.g.b = bitmap2.getWidth();
                this.g.c = bitmap2.getHeight();
            } else if (bitmap2.getWidth() == this.g.b && bitmap2.getHeight() == this.g.c) {
                i.a(bitmap2, this.g.a, false);
            } else {
                GLES20.glDeleteTextures(1, new int[]{this.g.a}, 0);
                this.g.a = i.a(bitmap2, -1, false);
                this.g.b = bitmap2.getWidth();
                this.g.c = bitmap2.getHeight();
            }
            com.tencent.liteav.basic.e.a a2 = a(bitmap2.getWidth(), bitmap2.getHeight(), i);
            i2 = this.g.a;
            aVar = a2;
        } else {
            aVar = aVar2;
            i2 = -1;
        }
        switch (i) {
            case 1:
                this.h = a(this.f.a, i2, eVar.e(), i, a, aVar);
                return this.h;
            case 2:
                this.h = b(this.f.a, i2, eVar.e(), i, a, aVar);
                return this.h;
            case 3:
                this.h = e(this.f.a, i2, eVar.e(), i, a, aVar);
                return this.h;
            case 4:
            case 5:
                this.h = c(this.f.a, i2, eVar.e(), i, a, aVar);
                return this.h;
            case 6:
                this.h = d(this.f.a, i2, eVar.e(), i, a, aVar);
                return this.h;
            default:
                return -1;
        }
    }

    private com.tencent.liteav.basic.e.a a(int i, int i2) {
        com.tencent.liteav.basic.e.a aVar = new com.tencent.liteav.basic.e.a(0, 0, this.c, this.d);
        float f = (float) i;
        float f2 = (float) i2;
        float f3;
        float f4;
        if (f / f2 >= ((float) this.c) / ((float) this.d)) {
            f3 = (float) this.c;
            f4 = ((float) (this.c * i2)) / f;
            aVar.a = 0;
            aVar.b = ((int) (((float) this.d) - f4)) / 2;
            aVar.c = (int) f3;
            aVar.d = (int) f4;
        } else {
            f3 = ((float) (this.d * i)) / f2;
            f4 = (float) this.d;
            aVar.a = ((int) (((float) this.c) - f3)) / 2;
            aVar.b = 0;
            aVar.c = (int) f3;
            aVar.d = (int) f4;
        }
        return aVar;
    }

    private com.tencent.liteav.basic.e.a a(int i, int i2, int i3) {
        com.tencent.liteav.basic.e.a aVar = new com.tencent.liteav.basic.e.a(0, 0, this.c, this.d);
        float f = (float) i;
        float f2 = (float) i2;
        float f3;
        float f4;
        if (f / f2 >= ((float) this.c) / ((float) this.d)) {
            f3 = (float) this.c;
            f4 = ((float) (this.c * i2)) / f;
            if (i3 == 1) {
                aVar.a = this.c;
            } else {
                aVar.a = 0;
            }
            if (i3 == 2) {
                aVar.b = this.d + (((int) (((float) this.d) - f4)) / 2);
            } else {
                aVar.b = ((int) (((float) this.d) - f4)) / 2;
            }
            aVar.c = (int) f3;
            aVar.d = (int) f4;
        } else {
            f3 = ((float) (this.d * i)) / f2;
            f4 = (float) this.d;
            if (i3 == 1) {
                aVar.a = this.c + (((int) (((float) this.c) - f3)) / 2);
            } else {
                aVar.a = ((int) (((float) this.c) - f3)) / 2;
            }
            if (i3 == 2) {
                aVar.b = this.d;
            } else {
                aVar.b = 0;
            }
            aVar.c = (int) f3;
            aVar.d = (int) f4;
        }
        return aVar;
    }

    private int a(int i, int i2, long j, int i3, com.tencent.liteav.basic.e.a aVar, com.tencent.liteav.basic.e.a aVar2) {
        float a = com.tencent.liteav.j.a.a(i3, j / 1000);
        com.tencent.liteav.basic.h.a[] aVarArr = new com.tencent.liteav.basic.h.a[2];
        com.tencent.liteav.basic.h.a aVar3 = new com.tencent.liteav.basic.h.a();
        aVar3.a = i;
        aVar3.b = 0;
        aVar3.c = aVar.c;
        aVar3.d = aVar.d;
        aVar3.g = aVar;
        aVarArr[0] = aVar3;
        com.tencent.liteav.basic.h.a aVar4 = new com.tencent.liteav.basic.h.a();
        aVar4.a = i2;
        aVar4.b = 0;
        aVar4.c = aVar2.c;
        aVar4.d = aVar2.d;
        aVar4.g = aVar2;
        aVarArr[1] = aVar4;
        i2 = (int) (((float) this.c) * a);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("processTwoPicLeftRightCombine, cropOffsetRatio = ");
        stringBuilder.append(a);
        stringBuilder.append(", cropOffset = ");
        stringBuilder.append(i2);
        TXCLog.i("BitmapCombineRender", stringBuilder.toString());
        com.tencent.liteav.basic.e.a aVar5 = new com.tencent.liteav.basic.e.a(i2, 0, this.c, this.d);
        this.b.a((this.c * 2) + this.e, this.d);
        this.b.a(aVar5);
        return this.b.a(aVarArr, 0);
    }

    private int b(int i, int i2, long j, int i3, com.tencent.liteav.basic.e.a aVar, com.tencent.liteav.basic.e.a aVar2) {
        float a = com.tencent.liteav.j.a.a(i3, j / 1000);
        com.tencent.liteav.basic.h.a[] aVarArr = new com.tencent.liteav.basic.h.a[2];
        com.tencent.liteav.basic.h.a aVar3 = new com.tencent.liteav.basic.h.a();
        aVar3.a = i;
        aVar3.b = 0;
        aVar3.c = aVar.c;
        aVar3.d = aVar.d;
        aVar3.g = aVar;
        aVarArr[0] = aVar3;
        com.tencent.liteav.basic.h.a aVar4 = new com.tencent.liteav.basic.h.a();
        aVar4.a = i2;
        aVar4.b = 0;
        aVar4.c = aVar2.c;
        aVar4.d = aVar2.d;
        aVar4.g = aVar2;
        aVarArr[1] = aVar4;
        i2 = (int) (((float) this.d) * a);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("processTwoPicUpDownCombine, cropOffsetRatio = ");
        stringBuilder.append(a);
        stringBuilder.append(", cropOffset = ");
        stringBuilder.append(i2);
        TXCLog.i("BitmapCombineRender", stringBuilder.toString());
        com.tencent.liteav.basic.e.a aVar5 = new com.tencent.liteav.basic.e.a(0, i2, this.c, this.d);
        this.b.a(this.c, (this.d * 2) + this.e);
        this.b.a(aVar5);
        return this.b.a(aVarArr, 0);
    }

    private int c(int i, int i2, long j, int i3, com.tencent.liteav.basic.e.a aVar, com.tencent.liteav.basic.e.a aVar2) {
        j /= 1000;
        float b = com.tencent.liteav.j.a.b(i3, j);
        float c = com.tencent.liteav.j.a.c(i3, j);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("processTwoPicZoom, scaleRatio = ");
        stringBuilder.append(b);
        stringBuilder.append(", alpha = ");
        stringBuilder.append(c);
        TXCLog.i("BitmapCombineRender", stringBuilder.toString());
        com.tencent.liteav.basic.h.a[] aVarArr = new com.tencent.liteav.basic.h.a[2];
        com.tencent.liteav.basic.h.a aVar3 = new com.tencent.liteav.basic.h.a();
        aVar3.a = i;
        aVar3.b = 0;
        aVar3.c = aVar.c;
        aVar3.d = aVar.d;
        aVar3.g = aVar;
        aVarArr[0] = aVar3;
        com.tencent.liteav.basic.h.a aVar4 = new com.tencent.liteav.basic.h.a();
        aVar4.a = i2;
        aVar4.b = 0;
        aVar4.c = aVar2.c;
        aVar4.d = aVar2.d;
        aVar4.g = aVar2;
        aVarArr[1] = aVar4;
        if (aVar3.e == null) {
            aVar3.e = new com.tencent.liteav.basic.h.a.a();
        }
        aVar3.e.a = b;
        aVar3.e.c = c;
        if (i2 >= 0) {
            aVar4.e = new com.tencent.liteav.basic.h.a.a();
            if (i3 == 5) {
                aVar4.e.a = 1.1f;
            }
            aVar4.e.c = 1.0f - c;
        }
        this.b.a(this.c, this.d);
        this.b.a(null);
        return this.b.a(aVarArr, 0);
    }

    private int d(int i, int i2, long j, int i3, com.tencent.liteav.basic.e.a aVar, com.tencent.liteav.basic.e.a aVar2) {
        float c = com.tencent.liteav.j.a.c(i3, j / 1000);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("processTwoPicFaceInOut, alpha = ");
        stringBuilder.append(c);
        TXCLog.i("BitmapCombineRender", stringBuilder.toString());
        com.tencent.liteav.basic.h.a[] aVarArr = new com.tencent.liteav.basic.h.a[2];
        com.tencent.liteav.basic.h.a aVar3 = new com.tencent.liteav.basic.h.a();
        aVar3.a = i;
        aVar3.b = 0;
        aVar3.c = aVar.c;
        aVar3.d = aVar.d;
        aVar3.g = aVar;
        aVarArr[0] = aVar3;
        com.tencent.liteav.basic.h.a aVar4 = new com.tencent.liteav.basic.h.a();
        aVar4.a = i2;
        aVar4.b = 0;
        aVar4.c = aVar2.c;
        aVar4.d = aVar2.d;
        aVar4.g = aVar2;
        aVarArr[1] = aVar4;
        aVar3.e = new com.tencent.liteav.basic.h.a.a();
        aVar3.e.c = c;
        if (i2 >= 0) {
            aVar4.e = new com.tencent.liteav.basic.h.a.a();
            aVar4.e.c = 1.0f - c;
        }
        this.b.a(this.c, this.d);
        this.b.a(null);
        return this.b.a(aVarArr, 0);
    }

    private int e(int i, int i2, long j, int i3, com.tencent.liteav.basic.e.a aVar, com.tencent.liteav.basic.e.a aVar2) {
        j /= 1000;
        int d = com.tencent.liteav.j.a.d(i3, j);
        float b = com.tencent.liteav.j.a.b(i3, j);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("processTwoPicRotation, rotation = ");
        stringBuilder.append(d);
        stringBuilder.append(", scale = ");
        stringBuilder.append(b);
        TXCLog.i("BitmapCombineRender", stringBuilder.toString());
        com.tencent.liteav.basic.h.a[] aVarArr = new com.tencent.liteav.basic.h.a[2];
        com.tencent.liteav.basic.h.a aVar3 = new com.tencent.liteav.basic.h.a();
        aVar3.a = i;
        aVar3.b = 0;
        aVar3.c = aVar.c;
        aVar3.d = aVar.d;
        aVar3.g = aVar;
        aVarArr[0] = aVar3;
        com.tencent.liteav.basic.h.a aVar4 = new com.tencent.liteav.basic.h.a();
        aVar4.a = i2;
        aVar4.b = 0;
        aVar4.c = aVar2.c;
        aVar4.d = aVar2.d;
        aVar4.g = aVar2;
        aVarArr[1] = aVar4;
        aVar3.e = new com.tencent.liteav.basic.h.a.a();
        aVar3.e.b = d;
        aVar3.e.a = b;
        aVar3.e.d = true;
        if (i2 >= 0) {
            aVar4.e = new com.tencent.liteav.basic.h.a.a();
        }
        if (d != 0) {
            aVar3.e.d = true;
            if (aVar4.e != null) {
                aVar4.e.d = true;
            }
        } else {
            aVar3.e.c = 1.0f;
            if (aVar4.e != null) {
                aVar4.e.c = CropImageView.DEFAULT_ASPECT_RATIO;
            }
        }
        this.b.a(this.c, this.d);
        this.b.a(null);
        return this.b.a(aVarArr, 0);
    }

    public void a() {
        int i;
        int[] iArr = new int[2];
        if (this.f != null) {
            iArr[0] = this.f.a;
            i = 1;
        } else {
            i = 0;
        }
        if (this.g != null) {
            iArr[1] = this.g.a;
            i++;
        }
        GLES20.glDeleteTextures(i, iArr, 0);
        this.f = null;
        this.g = null;
        this.b.a();
    }
}
