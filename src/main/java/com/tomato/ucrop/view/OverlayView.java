package com.tomato.ucrop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.one.tomato.ucrop.R;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.rr;
import defpackage.sa;

public class OverlayView extends View {
    private int A;
    private rr B;
    private boolean C;
    protected int a;
    protected int b;
    protected float[] c;
    protected float[] d;
    private boolean e;
    private final RectF f;
    private final RectF g;
    private int h;
    private int i;
    private float j;
    private float[] k;
    private boolean l;
    private boolean m;
    private boolean n;
    private int o;
    private Path p;
    private Paint q;
    private Paint r;
    private Paint s;
    private Paint t;
    private boolean u;
    private float v;
    private float w;
    private int x;
    private int y;
    private int z;

    public OverlayView(Context context) {
        this(context, null);
    }

    public OverlayView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public OverlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = true;
        this.f = new RectF();
        this.g = new RectF();
        this.k = null;
        this.p = new Path();
        this.q = new Paint(1);
        this.r = new Paint(1);
        this.s = new Paint(1);
        this.t = new Paint(1);
        this.u = false;
        this.v = -1.0f;
        this.w = -1.0f;
        this.x = -1;
        this.y = getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_rect_corner_touch_threshold);
        this.z = getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_rect_min_size);
        this.A = getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_rect_corner_touch_area_line_length);
        a();
    }

    public rr getOverlayViewChangeListener() {
        return this.B;
    }

    public void setOverlayViewChangeListener(rr rrVar) {
        this.B = rrVar;
    }

    @NonNull
    public RectF getCropViewRect() {
        return this.f;
    }

    public void setFreestyleCropEnabled(boolean z) {
        this.u = z;
    }

    public void setDragFrame(boolean z) {
        this.e = z;
    }

    public void setCircleDimmedLayer(boolean z) {
        this.n = z;
    }

    public void setCropGridRowCount(@IntRange(from = 0) int i) {
        this.h = i;
        this.k = null;
    }

    public void setCropGridColumnCount(@IntRange(from = 0) int i) {
        this.i = i;
        this.k = null;
    }

    public void setShowCropFrame(boolean z) {
        this.l = z;
    }

    public void setShowCropGrid(boolean z) {
        this.m = z;
    }

    public void setDimmedColor(@ColorInt int i) {
        this.o = i;
    }

    public void setCropFrameStrokeWidth(@IntRange(from = 0) int i) {
        this.s.setStrokeWidth((float) i);
    }

    public void setCropGridStrokeWidth(@IntRange(from = 0) int i) {
        this.r.setStrokeWidth((float) i);
    }

    public void setCropFrameColor(@ColorInt int i) {
        this.s.setColor(i);
    }

    public void setCropGridColor(@ColorInt int i) {
        this.r.setColor(i);
    }

    public void setTargetAspectRatio(float f) {
        this.j = f;
        if (this.a > 0) {
            setupCropBounds();
            postInvalidate();
            return;
        }
        this.C = true;
    }

    public void setupCropBounds() {
        int i = (int) (((float) this.a) / this.j);
        int i2;
        if (i > this.b) {
            i = (int) (((float) this.b) * this.j);
            i2 = (this.a - i) / 2;
            this.f.set((float) (getPaddingLeft() + i2), (float) getPaddingTop(), (float) ((getPaddingLeft() + i) + i2), (float) (getPaddingTop() + this.b));
        } else {
            i2 = (this.b - i) / 2;
            this.f.set((float) getPaddingLeft(), (float) (getPaddingTop() + i2), (float) (getPaddingLeft() + this.a), (float) ((getPaddingTop() + i) + i2));
        }
        if (this.B != null) {
            this.B.a(this.f);
        }
        b();
    }

    private void b() {
        this.c = sa.a(this.f);
        this.d = sa.b(this.f);
        this.k = null;
        this.p.reset();
        this.p.addCircle(this.f.centerX(), this.f.centerY(), Math.min(this.f.width(), this.f.height()) / 2.0f, Direction.CW);
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        if (VERSION.SDK_INT < 18 && VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            int paddingLeft = getPaddingLeft();
            i = getPaddingTop();
            i3 = getHeight() - getPaddingBottom();
            this.a = (getWidth() - getPaddingRight()) - paddingLeft;
            this.b = i3 - i;
            if (this.C) {
                this.C = false;
                setTargetAspectRatio(this.j);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        a(canvas);
        b(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.f.isEmpty() || !this.u) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if ((motionEvent.getAction() & 255) == 0) {
            if (this.v < CropImageView.DEFAULT_ASPECT_RATIO) {
                this.v = x;
                this.w = y;
            }
            this.x = b(x, y);
            if (this.x != -1) {
                z = true;
            }
            return z;
        } else if ((motionEvent.getAction() & 255) == 2 && motionEvent.getPointerCount() == 1 && this.x != -1) {
            float min = Math.min(Math.max(x, (float) getPaddingLeft()), (float) (getWidth() - getPaddingRight()));
            x = Math.min(Math.max(y, (float) getPaddingTop()), (float) (getHeight() - getPaddingBottom()));
            a(min, x);
            this.v = min;
            this.w = x;
            return true;
        } else {
            if ((motionEvent.getAction() & 255) == 1) {
                this.v = -1.0f;
                this.w = -1.0f;
                this.x = -1;
                if (this.B != null) {
                    this.B.a(this.f);
                }
            }
            return false;
        }
    }

    private void a(float f, float f2) {
        this.g.set(this.f);
        switch (this.x) {
            case 0:
                if (this.e) {
                    this.g.set(f, f2, this.f.right, this.f.bottom);
                    break;
                }
                break;
            case 1:
                if (this.e) {
                    this.g.set(this.f.left, f2, f, this.f.bottom);
                    break;
                }
                break;
            case 2:
                if (this.e) {
                    this.g.set(this.f.left, this.f.top, f, f2);
                    break;
                }
                break;
            case 3:
                if (this.e) {
                    this.g.set(f, this.f.top, this.f.right, f2);
                    break;
                }
                break;
            case 4:
                this.g.offset(f - this.v, f2 - this.w);
                if (this.g.left > ((float) getLeft()) && this.g.top > ((float) getTop()) && this.g.right < ((float) getRight()) && this.g.bottom < ((float) getBottom())) {
                    this.f.set(this.g);
                    b();
                    postInvalidate();
                }
                return;
        }
        Object obj = null;
        Object obj2 = this.g.height() >= ((float) this.z) ? 1 : null;
        if (this.g.width() >= ((float) this.z)) {
            obj = 1;
        }
        this.f.set((obj != null ? this.g : this.f).left, (obj2 != null ? this.g : this.f).top, (obj != null ? this.g : this.f).right, (obj2 != null ? this.g : this.f).bottom);
        if (!(obj2 == null && obj == null)) {
            b();
            postInvalidate();
        }
    }

    private int b(float f, float f2) {
        double d = (double) this.y;
        int i = -1;
        for (int i2 = 0; i2 < 8; i2 += 2) {
            double sqrt = Math.sqrt(Math.pow((double) (f - this.c[i2]), 2.0d) + Math.pow((double) (f2 - this.c[i2 + 1]), 2.0d));
            if (sqrt < d) {
                i = i2 / 2;
                d = sqrt;
            }
        }
        return (i >= 0 || !this.f.contains(f, f2)) ? i : 4;
    }

    /* Access modifiers changed, original: protected */
    public void a(@NonNull Canvas canvas) {
        canvas.save();
        if (this.n) {
            canvas.clipPath(this.p, Op.DIFFERENCE);
        } else {
            canvas.clipRect(this.f, Op.DIFFERENCE);
        }
        canvas.drawColor(this.o);
        canvas.restore();
        if (this.n) {
            canvas.drawCircle(this.f.centerX(), this.f.centerY(), Math.min(this.f.width(), this.f.height()) / 2.0f, this.q);
        }
    }

    /* Access modifiers changed, original: protected */
    public void b(@NonNull Canvas canvas) {
        if (this.m) {
            if (this.k == null && !this.f.isEmpty()) {
                int i;
                this.k = new float[((this.h * 4) + (this.i * 4))];
                int i2 = 0;
                int i3 = 0;
                while (i2 < this.h) {
                    int i4 = i3 + 1;
                    this.k[i3] = this.f.left;
                    i = i4 + 1;
                    float f = ((float) i2) + 1.0f;
                    this.k[i4] = (this.f.height() * (f / ((float) (this.h + 1)))) + this.f.top;
                    int i5 = i + 1;
                    this.k[i] = this.f.right;
                    i = i5 + 1;
                    this.k[i5] = (this.f.height() * (f / ((float) (this.h + 1)))) + this.f.top;
                    i2++;
                    i3 = i;
                }
                for (int i6 = 0; i6 < this.i; i6++) {
                    i = i3 + 1;
                    float f2 = ((float) i6) + 1.0f;
                    this.k[i3] = (this.f.width() * (f2 / ((float) (this.i + 1)))) + this.f.left;
                    i3 = i + 1;
                    this.k[i] = this.f.top;
                    i = i3 + 1;
                    this.k[i3] = (this.f.width() * (f2 / ((float) (this.i + 1)))) + this.f.left;
                    i3 = i + 1;
                    this.k[i] = this.f.bottom;
                }
            }
            if (this.k != null) {
                canvas.drawLines(this.k, this.r);
            }
        }
        if (this.l) {
            canvas.drawRect(this.f, this.s);
        }
        if (this.u) {
            canvas.save();
            this.g.set(this.f);
            this.g.inset((float) this.A, (float) (-this.A));
            canvas.clipRect(this.g, Op.DIFFERENCE);
            this.g.set(this.f);
            this.g.inset((float) (-this.A), (float) this.A);
            canvas.clipRect(this.g, Op.DIFFERENCE);
            canvas.drawRect(this.f, this.t);
            canvas.restore();
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(@NonNull TypedArray typedArray) {
        this.n = typedArray.getBoolean(R.styleable.ucrop_UCropView_ucrop_circle_dimmed_layer, false);
        this.o = typedArray.getColor(R.styleable.ucrop_UCropView_ucrop_dimmed_color, getResources().getColor(R.color.ucrop_color_default_dimmed));
        this.q.setColor(this.o);
        this.q.setStyle(Style.STROKE);
        this.q.setStrokeWidth(1.0f);
        b(typedArray);
        this.l = typedArray.getBoolean(R.styleable.ucrop_UCropView_ucrop_show_frame, true);
        c(typedArray);
        this.m = typedArray.getBoolean(R.styleable.ucrop_UCropView_ucrop_show_grid, true);
    }

    private void b(@NonNull TypedArray typedArray) {
        int dimensionPixelSize = typedArray.getDimensionPixelSize(R.styleable.ucrop_UCropView_ucrop_frame_stroke_size, getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_frame_stoke_width));
        int color = typedArray.getColor(R.styleable.ucrop_UCropView_ucrop_frame_color, getResources().getColor(R.color.ucrop_color_default_crop_frame));
        this.s.setStrokeWidth((float) dimensionPixelSize);
        this.s.setColor(color);
        this.s.setStyle(Style.STROKE);
        this.t.setStrokeWidth((float) (dimensionPixelSize * 3));
        this.t.setColor(color);
        this.t.setStyle(Style.STROKE);
    }

    private void c(@NonNull TypedArray typedArray) {
        int dimensionPixelSize = typedArray.getDimensionPixelSize(R.styleable.ucrop_UCropView_ucrop_grid_stroke_size, getResources().getDimensionPixelSize(R.dimen.ucrop_default_crop_grid_stoke_width));
        int color = typedArray.getColor(R.styleable.ucrop_UCropView_ucrop_grid_color, getResources().getColor(R.color.ucrop_color_default_crop_grid));
        this.r.setStrokeWidth((float) dimensionPixelSize);
        this.r.setColor(color);
        this.h = typedArray.getInt(R.styleable.ucrop_UCropView_ucrop_grid_row_count, 2);
        this.i = typedArray.getInt(R.styleable.ucrop_UCropView_ucrop_grid_column_count, 2);
    }
}
