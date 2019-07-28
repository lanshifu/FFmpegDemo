package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.yalantis.ucrop.view.CropImageView;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.ImageHolder.ScaleType;

/* compiled from: DrawableWrapper */
/* renamed from: uc */
public class uc extends Drawable {
    private Drawable a;
    private float b = 1.0f;
    private float c = 1.0f;
    private float d = CropImageView.DEFAULT_ASPECT_RATIO;
    private float e = CropImageView.DEFAULT_ASPECT_RATIO;
    private Paint f = new Paint();
    private boolean g;
    private ub h;

    public uc(ImageHolder imageHolder) {
        this.f.setAntiAlias(true);
        this.h = new ub(imageHolder);
        this.g = false;
        b(this.h.c);
        a(this.h.a);
    }

    public uc(ub ubVar) {
        this.f.setAntiAlias(true);
        this.h = ubVar;
        this.g = true;
        b(ubVar.c);
        a(ubVar.a);
    }

    public void draw(@NonNull Canvas canvas) {
        canvas.save();
        if (this.a != null) {
            canvas.clipRect(getBounds());
            if (this.a instanceof BitmapDrawable) {
                Bitmap bitmap = ((BitmapDrawable) this.a).getBitmap();
                if (bitmap == null || bitmap.isRecycled()) {
                    return;
                }
            }
            b(canvas);
        }
        a(canvas);
        canvas.restore();
    }

    private void a(Canvas canvas) {
        if (this.h != null && this.h.c != null && this.h.c.a() && this.h.a != null) {
            float d = this.h.c.d();
            canvas.drawRoundRect(this.h.a, d, d, this.f);
        }
    }

    public void a(Drawable drawable) {
        this.a = drawable;
    }

    public void setAlpha(int i) {
        if (this.a != null) {
            this.a.setAlpha(i);
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.a != null) {
            this.a.setColorFilter(colorFilter);
        }
    }

    public int getOpacity() {
        return this.a == null ? -2 : this.a.getOpacity();
    }

    public ub a() {
        return this.h;
    }

    private void b(ua uaVar) {
        if (uaVar != null) {
            this.f.setColor(uaVar.c());
            this.f.setStrokeWidth(uaVar.b());
            this.f.setStyle(Style.STROKE);
        }
    }

    public void b() {
        int width;
        int height;
        Rect bounds;
        if (this.a instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) this.a).getBitmap();
            if (bitmap != null && !bitmap.isRecycled()) {
                width = bitmap.getWidth();
                height = bitmap.getHeight();
            } else {
                return;
            }
        } else if (this.a instanceof ud) {
            ud udVar = (ud) this.a;
            width = udVar.b();
            height = udVar.a();
        } else {
            bounds = this.a.getBounds();
            width = bounds.width();
            height = bounds.height();
        }
        int i = height;
        int i2 = width;
        bounds = getBounds();
        int width2 = bounds.width();
        int height2 = bounds.height();
        if (width2 > 0 && height2 > 0) {
            switch (this.h == null ? ScaleType.none : this.h.b) {
                case none:
                    a(i2, i, width2, height2);
                    break;
                case center:
                    b(i2, i, width2, height2);
                    break;
                case center_crop:
                    f(i2, i, width2, height2);
                    break;
                case center_inside:
                    e(i2, i, width2, height2);
                    break;
                case fit_center:
                    a(i2, i, width2, height2, 0);
                    break;
                case fit_start:
                    a(i2, i, width2, height2, -1);
                    break;
                case fit_end:
                    a(i2, i, width2, height2, 1);
                    break;
                case fit_xy:
                    d(i2, i, width2, height2);
                    break;
                case fit_auto:
                    c(i2, i, width2, height2);
                    break;
            }
        }
    }

    private void b(Canvas canvas) {
        canvas.save();
        canvas.scale(this.b, this.c);
        canvas.translate(this.d, this.e);
        this.a.draw(canvas);
        canvas.restore();
    }

    private void a(int i, int i2, int i3, int i4) {
        this.d = CropImageView.DEFAULT_ASPECT_RATIO;
        this.e = CropImageView.DEFAULT_ASPECT_RATIO;
        this.b = 1.0f;
        this.c = 1.0f;
    }

    private void b(int i, int i2, int i3, int i4) {
        this.d = ((float) (i3 - i)) / 2.0f;
        this.e = ((float) (i4 - i2)) / 2.0f;
        this.b = 1.0f;
        this.c = 1.0f;
    }

    private void c(int i, int i2, int i3, int i4) {
        float f = (float) i3;
        float f2 = f / ((float) i);
        i2 = (int) (((float) i2) * f2);
        this.b = f2;
        this.c = f2;
        this.d = CropImageView.DEFAULT_ASPECT_RATIO;
        this.e = CropImageView.DEFAULT_ASPECT_RATIO;
        setBounds(0, 0, i3, i2);
        if (this.g && this.h != null) {
            this.h.a.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f, (float) i2);
        }
    }

    private void d(int i, int i2, int i3, int i4) {
        this.b = ((float) i3) / ((float) i);
        this.c = ((float) i4) / ((float) i2);
        this.d = CropImageView.DEFAULT_ASPECT_RATIO;
        this.e = CropImageView.DEFAULT_ASPECT_RATIO;
    }

    private void a(int i, int i2, int i3, int i4, int i5) {
        float f = (float) i3;
        float f2 = (float) i;
        float f3 = f / f2;
        float f4 = (float) i4;
        float f5 = (float) i2;
        float f6 = f4 / f5;
        float min = Math.min(f3, f6);
        Object obj = f3 > f6 ? 1 : null;
        f -= f2 * min;
        f6 = f / 2.0f;
        f4 -= f5 * min;
        f2 = f4 / 2.0f;
        if (i5 < 0) {
            if (obj != null) {
                f6 = CropImageView.DEFAULT_ASPECT_RATIO;
            } else {
                f2 = CropImageView.DEFAULT_ASPECT_RATIO;
            }
        } else if (i5 > 0) {
            if (obj != null) {
                f6 = f;
            } else {
                f2 = f4;
            }
        }
        this.b = min;
        this.c = min;
        this.d = f6 / min;
        this.e = f2 / min;
    }

    /* JADX WARNING: Missing block: B:3:0x0010, code skipped:
            if (r0 < 1.0f) goto L_0x001b;
     */
    private void e(int r5, int r6, int r7, int r8) {
        /*
        r4 = this;
        r7 = (float) r7;
        r5 = (float) r5;
        r0 = r7 / r5;
        r8 = (float) r8;
        r6 = (float) r6;
        r1 = r8 / r6;
        r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        r3 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        if (r2 >= 0) goto L_0x0013;
    L_0x000e:
        r1 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1));
        if (r1 >= 0) goto L_0x0019;
    L_0x0012:
        goto L_0x001b;
    L_0x0013:
        r0 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r0 >= 0) goto L_0x0019;
    L_0x0017:
        r0 = r1;
        goto L_0x001b;
    L_0x0019:
        r0 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
    L_0x001b:
        r5 = r5 * r0;
        r6 = r6 * r0;
        r7 = r7 - r5;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r5 = r5 * r0;
        r7 = r7 / r5;
        r4.d = r7;
        r8 = r8 - r6;
        r8 = r8 / r5;
        r4.e = r8;
        r4.b = r0;
        r4.c = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uc.e(int, int, int, int):void");
    }

    /* JADX WARNING: Missing block: B:3:0x0010, code skipped:
            if (r0 > 1.0f) goto L_0x001b;
     */
    private void f(int r5, int r6, int r7, int r8) {
        /*
        r4 = this;
        r7 = (float) r7;
        r5 = (float) r5;
        r0 = r7 / r5;
        r8 = (float) r8;
        r6 = (float) r6;
        r1 = r8 / r6;
        r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        r3 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        if (r2 <= 0) goto L_0x0013;
    L_0x000e:
        r1 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1));
        if (r1 <= 0) goto L_0x0019;
    L_0x0012:
        goto L_0x001b;
    L_0x0013:
        r0 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r0 <= 0) goto L_0x0019;
    L_0x0017:
        r0 = r1;
        goto L_0x001b;
    L_0x0019:
        r0 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
    L_0x001b:
        r5 = r5 * r0;
        r6 = r6 * r0;
        r7 = r7 - r5;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r5 = r5 * r0;
        r7 = r7 / r5;
        r4.d = r7;
        r8 = r8 - r6;
        r8 = r8 / r5;
        r4.e = r8;
        r4.b = r0;
        r4.c = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uc.f(int, int, int, int):void");
    }

    private void a(RectF rectF) {
        a(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    private void a(float f, float f2, float f3, float f4) {
        setBounds((int) f, (int) f2, (int) f3, (int) f4);
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        if (!this.g && this.h != null) {
            this.h.a.set((float) i, (float) i2, (float) i3, (float) i4);
        }
    }

    public void setBounds(@NonNull Rect rect) {
        super.setBounds(rect);
        if (!this.g && this.h != null) {
            this.h.a.set(rect);
        }
    }

    public void a(ScaleType scaleType) {
        if (!this.g && this.h != null) {
            this.h.b = scaleType;
        }
    }

    public void a(ua uaVar) {
        if (!this.g && this.h != null) {
            this.h.c.a(uaVar);
        }
    }

    public boolean c() {
        return this.g;
    }
}
