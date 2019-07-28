package it.sephiroth.android.library.imagezoom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.xn;
import defpackage.xo;
import defpackage.xp;

public abstract class ImageViewTouchBase extends ImageView {
    private float a;
    private float b;
    private boolean c;
    private boolean d;
    private int e;
    private int f;
    private PointF g;
    private boolean h;
    private boolean i;
    private a j;
    protected xo k;
    protected Matrix l;
    protected Matrix m;
    protected Matrix n;
    protected Handler o;
    protected Runnable p;
    protected boolean q;
    protected final Matrix r;
    protected final float[] s;
    protected DisplayType t;
    protected final int u;
    protected RectF v;
    protected RectF w;
    protected RectF x;
    private b y;

    public enum DisplayType {
        NONE,
        FIT_TO_SCREEN,
        FIT_IF_BIGGER
    }

    public interface a {
        void a(Drawable drawable);
    }

    public interface b {
        void a(boolean z, int i, int i2, int i3, int i4);
    }

    /* Access modifiers changed, original: protected */
    public void a(float f) {
    }

    /* Access modifiers changed, original: protected */
    public void c(float f) {
    }

    /* Access modifiers changed, original: protected */
    public void d() {
    }

    @SuppressLint({"Override"})
    public float getRotation() {
        return CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public ImageViewTouchBase(Context context) {
        this(context, null);
    }

    public ImageViewTouchBase(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ImageViewTouchBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = new xn();
        this.l = new Matrix();
        this.m = new Matrix();
        this.o = new Handler();
        this.p = null;
        this.q = false;
        this.a = -1.0f;
        this.b = -1.0f;
        this.r = new Matrix();
        this.s = new float[9];
        this.e = -1;
        this.f = -1;
        this.g = new PointF();
        this.t = DisplayType.NONE;
        this.u = 200;
        this.v = new RectF();
        this.w = new RectF();
        this.x = new RectF();
        a(context, attributeSet, i);
    }

    public void setOnDrawableChangedListener(a aVar) {
        this.j = aVar;
    }

    public void setOnLayoutChangeListener(b bVar) {
        this.y = bVar;
    }

    /* Access modifiers changed, original: protected */
    public void a(Context context, AttributeSet attributeSet, int i) {
        setScaleType(ScaleType.MATRIX);
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType == ScaleType.MATRIX) {
            super.setScaleType(scaleType);
        } else {
            Log.w("ImageViewTouchBase", "Unsupported scaletype. Only MATRIX can be used");
        }
    }

    public void setDisplayType(DisplayType displayType) {
        if (displayType != this.t) {
            this.q = false;
            this.t = displayType;
            this.h = true;
            requestLayout();
        }
    }

    public DisplayType getDisplayType() {
        return this.t;
    }

    /* Access modifiers changed, original: protected */
    public void setMinScale(float f) {
        this.b = f;
    }

    /* Access modifiers changed, original: protected */
    public void setMaxScale(float f) {
        this.a = f;
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i;
        int i8 = i2;
        int i9 = i3;
        int i10 = i4;
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            i5 = this.e;
            i6 = this.f;
            this.e = i9 - i7;
            this.f = i10 - i8;
            i5 = this.e - i5;
            i6 = this.f - i6;
            this.g.x = ((float) this.e) / 2.0f;
            this.g.y = ((float) this.f) / 2.0f;
        } else {
            i5 = 0;
            i6 = 0;
        }
        Runnable runnable = this.p;
        if (runnable != null) {
            this.p = null;
            runnable.run();
        }
        Drawable drawable = getDrawable();
        if (drawable == null) {
            if (this.i) {
                a(drawable);
            }
            if (z || this.i || this.h) {
                b(i7, i8, i9, i10);
            }
            if (this.i) {
                this.i = false;
            }
            if (this.h) {
                this.h = false;
            }
        } else if (z || this.h || this.i) {
            float f;
            a(this.t);
            float c = c(this.l);
            float scale = getScale();
            float min = Math.min(1.0f, 1.0f / c);
            a(drawable, this.l);
            float c2 = c(this.l);
            if (this.i || this.h) {
                float scale2;
                if (this.n != null) {
                    this.m.set(this.n);
                    this.n = null;
                    scale2 = getScale();
                } else {
                    this.m.reset();
                    scale2 = a(this.t);
                }
                f = scale2;
                setImageMatrix(getImageViewMatrix());
                if (f != getScale()) {
                    b(f);
                }
            } else if (z) {
                if (!this.d) {
                    this.b = -1.0f;
                }
                if (!this.c) {
                    this.a = -1.0f;
                }
                setImageMatrix(getImageViewMatrix());
                b((float) (-i5), (float) (-i6));
                if (this.q) {
                    f = ((double) Math.abs(scale - min)) > 0.001d ? (c / c2) * scale : 1.0f;
                    b(f);
                } else {
                    f = a(this.t);
                    b(f);
                }
            } else {
                f = 1.0f;
            }
            this.q = false;
            if (f > getMaxScale() || f < getMinScale()) {
                b(f);
            }
            a(true, true);
            if (this.i) {
                a(drawable);
            }
            if (z || this.i || this.h) {
                b(i7, i8, i9, i10);
            }
            if (this.h) {
                this.h = false;
            }
            if (this.i) {
                this.i = false;
            }
        }
    }

    public void a() {
        this.m = new Matrix();
        float a = a(this.t);
        setImageMatrix(getImageViewMatrix());
        if (a != getScale()) {
            b(a);
        }
        postInvalidate();
    }

    /* Access modifiers changed, original: protected */
    public float a(DisplayType displayType) {
        if (displayType == DisplayType.FIT_TO_SCREEN) {
            return 1.0f;
        }
        if (displayType == DisplayType.FIT_IF_BIGGER) {
            return Math.min(1.0f, 1.0f / c(this.l));
        }
        return 1.0f / c(this.l);
    }

    public void setImageResource(int i) {
        setImageDrawable(getContext().getResources().getDrawable(i));
    }

    public void setImageBitmap(Bitmap bitmap) {
        setImageBitmap(bitmap, null, -1.0f, -1.0f);
    }

    public void setImageBitmap(Bitmap bitmap, Matrix matrix, float f, float f2) {
        if (bitmap != null) {
            setImageDrawable(new xp(bitmap), matrix, f, f2);
        } else {
            setImageDrawable(null, matrix, f, f2);
        }
    }

    public void setImageDrawable(Drawable drawable) {
        setImageDrawable(drawable, null, -1.0f, -1.0f);
    }

    public void setImageDrawable(Drawable drawable, Matrix matrix, float f, float f2) {
        if (getWidth() <= 0) {
            final Drawable drawable2 = drawable;
            final Matrix matrix2 = matrix;
            final float f3 = f;
            final float f4 = f2;
            this.p = new Runnable() {
                public void run() {
                    ImageViewTouchBase.this.setImageDrawable(drawable2, matrix2, f3, f4);
                }
            };
            return;
        }
        a(drawable, matrix, f, f2);
    }

    /* Access modifiers changed, original: protected */
    public void a(Drawable drawable, Matrix matrix, float f, float f2) {
        if (drawable != null) {
            super.setImageDrawable(drawable);
        } else {
            this.l.reset();
            super.setImageDrawable(null);
        }
        if (f == -1.0f || f2 == -1.0f) {
            this.b = -1.0f;
            this.a = -1.0f;
            this.d = false;
            this.c = false;
        } else {
            f = Math.min(f, f2);
            f2 = Math.max(f, f2);
            this.b = f;
            this.a = f2;
            this.d = true;
            this.c = true;
            if (this.t == DisplayType.FIT_TO_SCREEN || this.t == DisplayType.FIT_IF_BIGGER) {
                if (this.b >= 1.0f) {
                    this.d = false;
                    this.b = -1.0f;
                }
                if (this.a <= 1.0f) {
                    this.c = true;
                    this.a = -1.0f;
                }
            }
        }
        if (matrix != null) {
            this.n = new Matrix(matrix);
        }
        this.i = true;
        requestLayout();
    }

    /* Access modifiers changed, original: protected */
    public void a(Drawable drawable) {
        b(drawable);
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, int i2, int i3, int i4) {
        if (this.y != null) {
            this.y.a(true, i, i2, i3, i4);
        }
    }

    /* Access modifiers changed, original: protected */
    public void b(Drawable drawable) {
        if (this.j != null) {
            this.j.a(drawable);
        }
    }

    /* Access modifiers changed, original: protected */
    public void b(int i, int i2, int i3, int i4) {
        a(i, i2, i3, i4);
    }

    /* Access modifiers changed, original: protected */
    public float b() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return 1.0f;
        }
        return Math.max(((float) drawable.getIntrinsicWidth()) / ((float) this.e), ((float) drawable.getIntrinsicHeight()) / ((float) this.f)) * 8.0f;
    }

    /* Access modifiers changed, original: protected */
    public float c() {
        if (getDrawable() == null) {
            return 1.0f;
        }
        return Math.min(1.0f, 1.0f / c(this.l));
    }

    public float getMaxScale() {
        if (this.a == -1.0f) {
            this.a = b();
        }
        return this.a;
    }

    public float getMinScale() {
        if (this.b == -1.0f) {
            this.b = c();
        }
        return this.b;
    }

    public Matrix getImageViewMatrix() {
        return a(this.m);
    }

    public Matrix a(Matrix matrix) {
        this.r.set(this.l);
        this.r.postConcat(matrix);
        return this.r;
    }

    public void setImageMatrix(Matrix matrix) {
        Matrix imageMatrix = getImageMatrix();
        Object obj = ((matrix != null || imageMatrix.isIdentity()) && (matrix == null || imageMatrix.equals(matrix))) ? null : 1;
        super.setImageMatrix(matrix);
        if (obj != null) {
            d();
        }
    }

    public Matrix getDisplayMatrix() {
        return new Matrix(this.m);
    }

    /* Access modifiers changed, original: protected */
    public void a(Drawable drawable, Matrix matrix) {
        float f = (float) this.e;
        float f2 = (float) this.f;
        float intrinsicWidth = (float) drawable.getIntrinsicWidth();
        float intrinsicHeight = (float) drawable.getIntrinsicHeight();
        matrix.reset();
        float min;
        if (intrinsicWidth > f || intrinsicHeight > f2) {
            min = Math.min(f / intrinsicWidth, f2 / intrinsicHeight);
            matrix.postScale(min, min);
            matrix.postTranslate((f - (intrinsicWidth * min)) / 2.0f, (f2 - (intrinsicHeight * min)) / 2.0f);
            return;
        }
        min = Math.min(f / intrinsicWidth, f2 / intrinsicHeight);
        matrix.postScale(min, min);
        matrix.postTranslate((f - (intrinsicWidth * min)) / 2.0f, (f2 - (intrinsicHeight * min)) / 2.0f);
    }

    /* Access modifiers changed, original: protected */
    public float a(Matrix matrix, int i) {
        matrix.getValues(this.s);
        return this.s[i];
    }

    public RectF getBitmapRect() {
        return b(this.m);
    }

    /* Access modifiers changed, original: protected */
    public RectF b(Matrix matrix) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }
        matrix = a(matrix);
        this.v.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        matrix.mapRect(this.v);
        return this.v;
    }

    /* Access modifiers changed, original: protected */
    public float c(Matrix matrix) {
        return a(matrix, 0);
    }

    public float getScale() {
        return c(this.m);
    }

    public float getBaseScale() {
        return c(this.l);
    }

    /* Access modifiers changed, original: protected */
    public void a(boolean z, boolean z2) {
        if (getDrawable() != null) {
            RectF a = a(this.m, z, z2);
            if (!(a.left == CropImageView.DEFAULT_ASPECT_RATIO && a.top == CropImageView.DEFAULT_ASPECT_RATIO)) {
                b(a.left, a.top);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0049  */
    public android.graphics.RectF a(android.graphics.Matrix r6, boolean r7, boolean r8) {
        /*
        r5 = this;
        r0 = r5.getDrawable();
        r1 = 0;
        if (r0 != 0) goto L_0x000d;
    L_0x0007:
        r6 = new android.graphics.RectF;
        r6.<init>(r1, r1, r1, r1);
        return r6;
    L_0x000d:
        r0 = r5.w;
        r0.set(r1, r1, r1, r1);
        r6 = r5.b(r6);
        r0 = r6.height();
        r2 = r6.width();
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r8 == 0) goto L_0x0046;
    L_0x0022:
        r8 = r5.f;
        r8 = (float) r8;
        r4 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r4 >= 0) goto L_0x002f;
    L_0x0029:
        r8 = r8 - r0;
        r8 = r8 / r3;
        r0 = r6.top;
        r8 = r8 - r0;
        goto L_0x0047;
    L_0x002f:
        r0 = r6.top;
        r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        if (r0 <= 0) goto L_0x0039;
    L_0x0035:
        r8 = r6.top;
        r8 = -r8;
        goto L_0x0047;
    L_0x0039:
        r0 = r6.bottom;
        r8 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r8 >= 0) goto L_0x0046;
    L_0x003f:
        r8 = r5.f;
        r8 = (float) r8;
        r0 = r6.bottom;
        r8 = r8 - r0;
        goto L_0x0047;
    L_0x0046:
        r8 = 0;
    L_0x0047:
        if (r7 == 0) goto L_0x006c;
    L_0x0049:
        r7 = r5.e;
        r7 = (float) r7;
        r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1));
        if (r0 >= 0) goto L_0x0057;
    L_0x0050:
        r7 = r7 - r2;
        r7 = r7 / r3;
        r6 = r6.left;
        r6 = r7 - r6;
        goto L_0x006d;
    L_0x0057:
        r0 = r6.left;
        r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        if (r0 <= 0) goto L_0x0061;
    L_0x005d:
        r6 = r6.left;
        r6 = -r6;
        goto L_0x006d;
    L_0x0061:
        r0 = r6.right;
        r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1));
        if (r0 >= 0) goto L_0x006c;
    L_0x0067:
        r6 = r6.right;
        r6 = r7 - r6;
        goto L_0x006d;
    L_0x006c:
        r6 = 0;
    L_0x006d:
        r7 = r5.w;
        r7.set(r6, r8, r1, r1);
        r6 = r5.w;
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: it.sephiroth.android.library.imagezoom.ImageViewTouchBase.a(android.graphics.Matrix, boolean, boolean):android.graphics.RectF");
    }

    /* Access modifiers changed, original: protected */
    public void b(float f, float f2) {
        if (f != CropImageView.DEFAULT_ASPECT_RATIO || f2 != CropImageView.DEFAULT_ASPECT_RATIO) {
            this.m.postTranslate(f, f2);
            setImageMatrix(getImageViewMatrix());
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(float f, float f2, float f3) {
        this.m.postScale(f, f, f2, f3);
        setImageMatrix(getImageViewMatrix());
    }

    /* Access modifiers changed, original: protected */
    public PointF getCenter() {
        return this.g;
    }

    /* Access modifiers changed, original: protected */
    public void b(float f) {
        if (f > getMaxScale()) {
            f = getMaxScale();
        }
        if (f < getMinScale()) {
            f = getMinScale();
        }
        PointF center = getCenter();
        b(f, center.x, center.y);
    }

    public void c(float f, float f2) {
        PointF center = getCenter();
        a(f, center.x, center.y, f2);
    }

    /* Access modifiers changed, original: protected */
    public void b(float f, float f2, float f3) {
        if (f > getMaxScale()) {
            f = getMaxScale();
        }
        a(f / getScale(), f2, f3);
        c(getScale());
        a(true, true);
    }

    public void d(float f, float f2) {
        a((double) f, (double) f2);
    }

    /* Access modifiers changed, original: protected */
    public void a(double d, double d2) {
        RectF bitmapRect = getBitmapRect();
        this.x.set((float) d, (float) d2, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
        a(bitmapRect, this.x);
        b(this.x.left, this.x.top);
        a(true, true);
    }

    /* Access modifiers changed, original: protected */
    public void a(RectF rectF, RectF rectF2) {
        if (rectF != null) {
            if (rectF.top >= CropImageView.DEFAULT_ASPECT_RATIO && rectF.bottom <= ((float) this.f)) {
                rectF2.top = CropImageView.DEFAULT_ASPECT_RATIO;
            }
            if (rectF.left >= CropImageView.DEFAULT_ASPECT_RATIO && rectF.right <= ((float) this.e)) {
                rectF2.left = CropImageView.DEFAULT_ASPECT_RATIO;
            }
            if (rectF.top + rectF2.top >= CropImageView.DEFAULT_ASPECT_RATIO && rectF.bottom > ((float) this.f)) {
                rectF2.top = (float) ((int) (CropImageView.DEFAULT_ASPECT_RATIO - rectF.top));
            }
            if (rectF.bottom + rectF2.top <= ((float) (this.f + 0)) && rectF.top < CropImageView.DEFAULT_ASPECT_RATIO) {
                rectF2.top = (float) ((int) (((float) (this.f + 0)) - rectF.bottom));
            }
            if (rectF.left + rectF2.left >= CropImageView.DEFAULT_ASPECT_RATIO) {
                rectF2.left = (float) ((int) (CropImageView.DEFAULT_ASPECT_RATIO - rectF.left));
            }
            if (rectF.right + rectF2.left <= ((float) (this.e + 0))) {
                rectF2.left = (float) ((int) (((float) (this.e + 0)) - rectF.right));
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(float f, float f2, double d) {
        final double d2 = (double) f;
        final double d3 = (double) f2;
        final long currentTimeMillis = System.currentTimeMillis();
        final double d4 = d;
        this.o.post(new Runnable() {
            double a = 0.0d;
            double b = 0.0d;

            public void run() {
                double min = Math.min(d4, (double) (System.currentTimeMillis() - currentTimeMillis));
                double d = min;
                double a = ImageViewTouchBase.this.k.a(d, 0.0d, d2, d4);
                double a2 = ImageViewTouchBase.this.k.a(d, 0.0d, d3, d4);
                ImageViewTouchBase.this.a(a - this.a, a2 - this.b);
                this.a = a;
                this.b = a2;
                if (min < d4) {
                    ImageViewTouchBase.this.o.post(this);
                    return;
                }
                RectF a3 = ImageViewTouchBase.this.a(ImageViewTouchBase.this.m, true, true);
                if (a3.left != CropImageView.DEFAULT_ASPECT_RATIO || a3.top != CropImageView.DEFAULT_ASPECT_RATIO) {
                    ImageViewTouchBase.this.d(a3.left, a3.top);
                }
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void a(float f, float f2, float f3, float f4) {
        if (f > getMaxScale()) {
            f = getMaxScale();
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final float scale = getScale();
        final float f5 = f - scale;
        Matrix matrix = new Matrix(this.m);
        matrix.postScale(f, f, f2, f3);
        RectF a = a(matrix, true, true);
        final float f6 = f2 + (a.left * f);
        final float f7 = f3 + (a.top * f);
        final float f8 = f4;
        this.o.post(new Runnable() {
            public void run() {
                float min = Math.min(f8, (float) (System.currentTimeMillis() - currentTimeMillis));
                ImageViewTouchBase.this.b(scale + ((float) ImageViewTouchBase.this.k.b((double) min, 0.0d, (double) f5, (double) f8)), f6, f7);
                if (min < f8) {
                    ImageViewTouchBase.this.o.post(this);
                    return;
                }
                ImageViewTouchBase.this.a(ImageViewTouchBase.this.getScale());
                ImageViewTouchBase.this.a(true, true);
            }
        });
    }
}
