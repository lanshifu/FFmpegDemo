package com.tomato.ucrop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import com.one.tomato.ucrop.R;
import com.tomato.ucrop.model.c;
import defpackage.ro;
import defpackage.rq;
import defpackage.rs;
import defpackage.rv;
import defpackage.sa;
import java.lang.ref.WeakReference;
import java.util.Arrays;

public class CropImageView extends TransformImageView {
    private final RectF i;
    private final Matrix j;
    private float k;
    private float l;
    private rq m;
    private Runnable n;
    private Runnable o;
    private float p;
    private float q;
    private int r;
    private int s;
    private long t;

    private static class a implements Runnable {
        private final WeakReference<CropImageView> a;
        private final long b;
        private final long c = System.currentTimeMillis();
        private final float d;
        private final float e;
        private final float f;
        private final float g;
        private final float h;
        private final float i;
        private final boolean j;

        public a(CropImageView cropImageView, long j, float f, float f2, float f3, float f4, float f5, float f6, boolean z) {
            this.a = new WeakReference(cropImageView);
            this.b = j;
            this.d = f;
            this.e = f2;
            this.f = f3;
            this.g = f4;
            this.h = f5;
            this.i = f6;
            this.j = z;
        }

        public void run() {
            CropImageView cropImageView = (CropImageView) this.a.get();
            if (cropImageView != null) {
                float min = (float) Math.min(this.b, System.currentTimeMillis() - this.c);
                float a = rv.a(min, com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO, this.f, (float) this.b);
                float a2 = rv.a(min, com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO, this.g, (float) this.b);
                float b = rv.b(min, com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO, this.i, (float) this.b);
                if (min < ((float) this.b)) {
                    cropImageView.a(a - (cropImageView.b[0] - this.d), a2 - (cropImageView.b[1] - this.e));
                    if (!this.j) {
                        cropImageView.b(this.h + b, cropImageView.i.centerX(), cropImageView.i.centerY());
                    }
                    if (!cropImageView.c()) {
                        cropImageView.post(this);
                    }
                }
            }
        }
    }

    private static class b implements Runnable {
        private final WeakReference<CropImageView> a;
        private final long b;
        private final long c = System.currentTimeMillis();
        private final float d;
        private final float e;
        private final float f;
        private final float g;

        public b(CropImageView cropImageView, long j, float f, float f2, float f3, float f4) {
            this.a = new WeakReference(cropImageView);
            this.b = j;
            this.d = f;
            this.e = f2;
            this.f = f3;
            this.g = f4;
        }

        public void run() {
            CropImageView cropImageView = (CropImageView) this.a.get();
            if (cropImageView != null) {
                float min = (float) Math.min(this.b, System.currentTimeMillis() - this.c);
                float b = rv.b(min, com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO, this.e, (float) this.b);
                if (min < ((float) this.b)) {
                    cropImageView.b(this.d + b, this.f, this.g);
                    cropImageView.post(this);
                } else {
                    cropImageView.setImageToWrapCropBounds();
                }
            }
        }
    }

    public CropImageView(Context context) {
        this(context, null);
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CropImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = new RectF();
        this.j = new Matrix();
        this.l = 10.0f;
        this.o = null;
        this.r = 0;
        this.s = 0;
        this.t = 500;
    }

    public void a(@NonNull CompressFormat compressFormat, int i, @Nullable ro roVar) {
        a();
        setImageToWrapCropBounds(false);
        new rs(getContext(), getViewBitmap(), new c(this.i, sa.b(this.a), getCurrentScale(), getCurrentAngle()), new com.tomato.ucrop.model.a(this.r, this.s, compressFormat, i, getImageInputPath(), getImageOutputPath(), getExifInfo()), roVar).execute(new Void[0]);
    }

    public float getMaxScale() {
        return this.p;
    }

    public float getMinScale() {
        return this.q;
    }

    public float getTargetAspectRatio() {
        return this.k;
    }

    public void setCropRect(RectF rectF) {
        this.k = rectF.width() / rectF.height();
        this.i.set(rectF.left - ((float) getPaddingLeft()), rectF.top - ((float) getPaddingTop()), rectF.right - ((float) getPaddingRight()), rectF.bottom - ((float) getPaddingBottom()));
        f();
        setImageToWrapCropBounds();
    }

    public void setTargetAspectRatio(float f) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            this.k = f;
            return;
        }
        if (f == com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) {
            this.k = ((float) drawable.getIntrinsicWidth()) / ((float) drawable.getIntrinsicHeight());
        } else {
            this.k = f;
        }
        if (this.m != null) {
            this.m.a(this.k);
        }
    }

    @Nullable
    public rq getCropBoundsChangeListener() {
        return this.m;
    }

    public void setCropBoundsChangeListener(@Nullable rq rqVar) {
        this.m = rqVar;
    }

    public void setMaxResultImageSizeX(@IntRange(from = 10) int i) {
        this.r = i;
    }

    public void setMaxResultImageSizeY(@IntRange(from = 10) int i) {
        this.s = i;
    }

    public void setImageToWrapCropBoundsAnimDuration(@IntRange(from = 100) long j) {
        if (j > 0) {
            this.t = j;
            return;
        }
        throw new IllegalArgumentException("Animation duration cannot be negative value.");
    }

    public void setMaxScaleMultiplier(float f) {
        this.l = f;
    }

    public void a(float f) {
        a(f, this.i.centerX(), this.i.centerY());
    }

    public void a(float f, float f2, float f3) {
        if (f >= getMinScale()) {
            c(f / getCurrentScale(), f2, f3);
        }
    }

    public void b(float f) {
        b(f, this.i.centerX(), this.i.centerY());
    }

    public void b(float f, float f2, float f3) {
        if (f <= getMaxScale()) {
            c(f / getCurrentScale(), f2, f3);
        }
    }

    public void c(float f, float f2, float f3) {
        if (f > 1.0f && getCurrentScale() * f <= getMaxScale()) {
            super.c(f, f2, f3);
        } else if (f < 1.0f && getCurrentScale() * f >= getMinScale()) {
            super.c(f, f2, f3);
        }
    }

    public void c(float f) {
        d(f, this.i.centerX(), this.i.centerY());
    }

    public void a() {
        removeCallbacks(this.n);
        removeCallbacks(this.o);
    }

    public void setImageToWrapCropBounds() {
        setImageToWrapCropBounds(true);
    }

    public void setImageToWrapCropBounds(boolean z) {
        if (this.h && !c()) {
            float f;
            float f2;
            float f3;
            float f4 = this.b[0];
            float f5 = this.b[1];
            float currentScale = getCurrentScale();
            float centerX = this.i.centerX() - f4;
            float centerY = this.i.centerY() - f5;
            this.j.reset();
            this.j.setTranslate(centerX, centerY);
            float[] copyOf = Arrays.copyOf(this.a, this.a.length);
            this.j.mapPoints(copyOf);
            boolean a = a(copyOf);
            if (a) {
                float[] e = e();
                f = -(e[1] + e[3]);
                f2 = -(e[0] + e[2]);
                f3 = com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO;
            } else {
                RectF rectF = new RectF(this.i);
                this.j.reset();
                this.j.setRotate(getCurrentAngle());
                this.j.mapRect(rectF);
                copyOf = sa.a(this.a);
                f2 = centerX;
                f3 = (Math.max(rectF.width() / copyOf[0], rectF.height() / copyOf[1]) * currentScale) - currentScale;
                f = centerY;
            }
            if (z) {
                a aVar = new a(this, this.t, f4, f5, f2, f, currentScale, f3, a);
                this.n = aVar;
                post(aVar);
                return;
            }
            a(f2, f);
            if (!a) {
                b(currentScale + f3, this.i.centerX(), this.i.centerY());
            }
        }
    }

    private float[] e() {
        this.j.reset();
        this.j.setRotate(-getCurrentAngle());
        float[] copyOf = Arrays.copyOf(this.a, this.a.length);
        float[] a = sa.a(this.i);
        this.j.mapPoints(copyOf);
        this.j.mapPoints(a);
        RectF b = sa.b(copyOf);
        RectF b2 = sa.b(a);
        float f = b.left - b2.left;
        float f2 = b.top - b2.top;
        float f3 = b.right - b2.right;
        float f4 = b.bottom - b2.bottom;
        a = new float[4];
        if (f <= com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) {
            f = com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO;
        }
        a[0] = f;
        if (f2 <= com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) {
            f2 = com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO;
        }
        a[1] = f2;
        if (f3 >= com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) {
            f3 = com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO;
        }
        a[2] = f3;
        if (f4 >= com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) {
            f4 = com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO;
        }
        a[3] = f4;
        this.j.reset();
        this.j.setRotate(getCurrentAngle());
        this.j.mapPoints(a);
        return a;
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        super.b();
        Drawable drawable = getDrawable();
        if (drawable != null) {
            float intrinsicWidth = (float) drawable.getIntrinsicWidth();
            float intrinsicHeight = (float) drawable.getIntrinsicHeight();
            if (this.k == com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) {
                this.k = intrinsicWidth / intrinsicHeight;
            }
            int i = (int) (((float) this.d) / this.k);
            int i2;
            if (i > this.e) {
                i = (int) (((float) this.e) * this.k);
                i2 = (this.d - i) / 2;
                this.i.set((float) i2, com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO, (float) (i + i2), (float) this.e);
            } else {
                i2 = (this.e - i) / 2;
                this.i.set(com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO, (float) i2, (float) this.d, (float) (i + i2));
            }
            b(intrinsicWidth, intrinsicHeight);
            c(intrinsicWidth, intrinsicHeight);
            if (this.m != null) {
                this.m.a(this.k);
            }
            if (this.f != null) {
                this.f.b(getCurrentScale());
                this.f.a(getCurrentAngle());
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean c() {
        return a(this.a);
    }

    /* Access modifiers changed, original: protected */
    public boolean a(float[] fArr) {
        this.j.reset();
        this.j.setRotate(-getCurrentAngle());
        fArr = Arrays.copyOf(fArr, fArr.length);
        this.j.mapPoints(fArr);
        float[] a = sa.a(this.i);
        this.j.mapPoints(a);
        return sa.b(fArr).contains(sa.b(a));
    }

    /* Access modifiers changed, original: protected */
    public void a(float f, float f2, float f3, long j) {
        if (f > getMaxScale()) {
            f = getMaxScale();
        }
        float currentScale = getCurrentScale();
        b bVar = new b(this, j, currentScale, f - currentScale, f2, f3);
        this.o = bVar;
        post(bVar);
    }

    private void f() {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            b((float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        }
    }

    private void b(float f, float f2) {
        this.q = Math.min(Math.min(this.i.width() / f, this.i.width() / f2), Math.min(this.i.height() / f2, this.i.height() / f));
        this.p = this.q * this.l;
    }

    private void c(float f, float f2) {
        float width = this.i.width();
        float height = this.i.height();
        float max = Math.max(this.i.width() / f, this.i.height() / f2);
        width = ((width - (f * max)) / 2.0f) + this.i.left;
        height = ((height - (f2 * max)) / 2.0f) + this.i.top;
        this.c.reset();
        this.c.postScale(max, max);
        this.c.postTranslate(width, height);
        setImageMatrix(this.c);
    }

    /* Access modifiers changed, original: protected */
    public void a(@NonNull TypedArray typedArray) {
        float abs = Math.abs(typedArray.getFloat(R.styleable.ucrop_UCropView_ucrop_aspect_ratio_x, com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO));
        float abs2 = Math.abs(typedArray.getFloat(R.styleable.ucrop_UCropView_ucrop_aspect_ratio_y, com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO));
        if (abs == com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO || abs2 == com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) {
            this.k = com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO;
        } else {
            this.k = abs / abs2;
        }
    }
}
