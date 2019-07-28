package com.tomato.ucrop.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.tomato.ucrop.model.b;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.rp;
import defpackage.ru;
import defpackage.rx;
import defpackage.sa;

public class TransformImageView extends ImageView {
    protected final float[] a;
    protected final float[] b;
    protected Matrix c;
    protected int d;
    protected int e;
    protected a f;
    protected boolean g;
    protected boolean h;
    private final float[] i;
    private float[] j;
    private float[] k;
    private int l;
    private String m;
    private String n;
    private b o;

    public interface a {
        void a();

        void a(float f);

        void a(@NonNull Exception exception);

        void b(float f);
    }

    public TransformImageView(Context context) {
        this(context, null);
    }

    public TransformImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TransformImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new float[8];
        this.b = new float[2];
        this.i = new float[9];
        this.c = new Matrix();
        this.g = false;
        this.h = false;
        this.l = 0;
        d();
    }

    public void setTransformImageListener(a aVar) {
        this.f = aVar;
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType == ScaleType.MATRIX) {
            super.setScaleType(scaleType);
        } else {
            Log.w("TransformImageView", "Invalid ScaleType. Only ScaleType.MATRIX can be used");
        }
    }

    public void setMaxBitmapSize(int i) {
        this.l = i;
    }

    public int getMaxBitmapSize() {
        if (this.l <= 0) {
            this.l = ru.a(getContext());
        }
        return this.l;
    }

    public void setImageBitmap(Bitmap bitmap) {
        setImageDrawable(new rx(bitmap));
    }

    public String getImageInputPath() {
        return this.m;
    }

    public String getImageOutputPath() {
        return this.n;
    }

    public b getExifInfo() {
        return this.o;
    }

    public void setImageUri(@NonNull Uri uri, @Nullable Uri uri2) throws Exception {
        int maxBitmapSize = getMaxBitmapSize();
        ru.a(getContext(), uri, uri2, maxBitmapSize, maxBitmapSize, new rp() {
            public void a(@NonNull Bitmap bitmap, @NonNull b bVar, @NonNull String str, @Nullable String str2) {
                TransformImageView.this.m = str;
                TransformImageView.this.n = str2;
                TransformImageView.this.o = bVar;
                TransformImageView.this.g = true;
                TransformImageView.this.setImageBitmap(bitmap);
            }

            public void a(@NonNull Exception exception) {
                Log.e("TransformImageView", "onFailure: setImageUri", exception);
                if (TransformImageView.this.f != null) {
                    TransformImageView.this.f.a(exception);
                }
            }
        });
    }

    public float getCurrentScale() {
        return a(this.c);
    }

    public float a(@NonNull Matrix matrix) {
        return (float) Math.sqrt(Math.pow((double) a(matrix, 0), 2.0d) + Math.pow((double) a(matrix, 3), 2.0d));
    }

    public float getCurrentAngle() {
        return b(this.c);
    }

    public float b(@NonNull Matrix matrix) {
        return (float) (-(Math.atan2((double) a(matrix, 1), (double) a(matrix, 0)) * 57.29577951308232d));
    }

    public void setImageMatrix(Matrix matrix) {
        super.setImageMatrix(matrix);
        this.c.set(matrix);
        a();
    }

    @Nullable
    public Bitmap getViewBitmap() {
        return (getDrawable() == null || !(getDrawable() instanceof rx)) ? null : ((rx) getDrawable()).a();
    }

    public void a(float f, float f2) {
        if (f != CropImageView.DEFAULT_ASPECT_RATIO || f2 != CropImageView.DEFAULT_ASPECT_RATIO) {
            this.c.postTranslate(f, f2);
            setImageMatrix(this.c);
        }
    }

    public void c(float f, float f2, float f3) {
        if (f != CropImageView.DEFAULT_ASPECT_RATIO) {
            this.c.postScale(f, f, f2, f3);
            setImageMatrix(this.c);
            if (this.f != null) {
                this.f.b(a(this.c));
            }
        }
    }

    public void d(float f, float f2, float f3) {
        if (f != CropImageView.DEFAULT_ASPECT_RATIO) {
            this.c.postRotate(f, f2, f3);
            setImageMatrix(this.c);
            if (this.f != null) {
                this.f.a(b(this.c));
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void d() {
        setScaleType(ScaleType.MATRIX);
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z || (this.g && !this.h)) {
            int paddingLeft = getPaddingLeft();
            i = getPaddingTop();
            i3 = getHeight() - getPaddingBottom();
            this.d = (getWidth() - getPaddingRight()) - paddingLeft;
            this.e = i3 - i;
            b();
        }
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            Log.d("TransformImageView", String.format("Image size: [%d:%d]", new Object[]{Integer.valueOf((int) ((float) drawable.getIntrinsicWidth())), Integer.valueOf((int) ((float) drawable.getIntrinsicHeight()))}));
            RectF rectF = new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, r1, r0);
            this.j = sa.a(rectF);
            this.k = sa.b(rectF);
            this.h = true;
            if (this.f != null) {
                this.f.a();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public float a(@NonNull Matrix matrix, @IntRange(from = 0, to = 9) int i) {
        matrix.getValues(this.i);
        return this.i[i];
    }

    private void a() {
        this.c.mapPoints(this.a, this.j);
        this.c.mapPoints(this.b, this.k);
    }
}
