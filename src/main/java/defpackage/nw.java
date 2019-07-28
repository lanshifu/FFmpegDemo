package defpackage;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* compiled from: AnimatorProxy */
/* renamed from: nw */
public final class nw extends Animation {
    public static final boolean a = (Integer.valueOf(VERSION.SDK).intValue() < 11);
    private static final WeakHashMap<View, nw> b = new WeakHashMap();
    private final WeakReference<View> c;
    private final Camera d = new Camera();
    private boolean e;
    private float f = 1.0f;
    private float g;
    private float h;
    private float i;
    private float j;
    private float k;
    private float l = 1.0f;
    private float m = 1.0f;
    private float n;
    private float o;
    private final RectF p = new RectF();
    private final RectF q = new RectF();
    private final Matrix r = new Matrix();

    public static nw a(View view) {
        Animation animation = (nw) b.get(view);
        if (animation != null && animation == view.getAnimation()) {
            return animation;
        }
        nw nwVar = new nw(view);
        b.put(view, nwVar);
        return nwVar;
    }

    private nw(View view) {
        setDuration(0);
        setFillAfter(true);
        view.setAnimation(this);
        this.c = new WeakReference(view);
    }

    public float a() {
        return this.f;
    }

    public void a(float f) {
        if (this.f != f) {
            this.f = f;
            View view = (View) this.c.get();
            if (view != null) {
                view.invalidate();
            }
        }
    }

    public float b() {
        return this.k;
    }

    public void b(float f) {
        if (this.k != f) {
            k();
            this.k = f;
            l();
        }
    }

    public float c() {
        return this.i;
    }

    public void c(float f) {
        if (this.i != f) {
            k();
            this.i = f;
            l();
        }
    }

    public float d() {
        return this.j;
    }

    public void d(float f) {
        if (this.j != f) {
            k();
            this.j = f;
            l();
        }
    }

    public float e() {
        return this.l;
    }

    public void e(float f) {
        if (this.l != f) {
            k();
            this.l = f;
            l();
        }
    }

    public float f() {
        return this.m;
    }

    public void f(float f) {
        if (this.m != f) {
            k();
            this.m = f;
            l();
        }
    }

    public float g() {
        return this.n;
    }

    public void g(float f) {
        if (this.n != f) {
            k();
            this.n = f;
            l();
        }
    }

    public float h() {
        return this.o;
    }

    public void h(float f) {
        if (this.o != f) {
            k();
            this.o = f;
            l();
        }
    }

    public float i() {
        View view = (View) this.c.get();
        if (view == null) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        return ((float) view.getLeft()) + this.n;
    }

    public void i(float f) {
        View view = (View) this.c.get();
        if (view != null) {
            g(f - ((float) view.getLeft()));
        }
    }

    public float j() {
        View view = (View) this.c.get();
        if (view == null) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        return ((float) view.getTop()) + this.o;
    }

    public void j(float f) {
        View view = (View) this.c.get();
        if (view != null) {
            h(f - ((float) view.getTop()));
        }
    }

    private void k() {
        View view = (View) this.c.get();
        if (view != null) {
            a(this.p, view);
        }
    }

    private void l() {
        View view = (View) this.c.get();
        if (view != null && view.getParent() != null) {
            RectF rectF = this.q;
            a(rectF, view);
            rectF.union(this.p);
            ((View) view.getParent()).invalidate((int) Math.floor((double) rectF.left), (int) Math.floor((double) rectF.top), (int) Math.ceil((double) rectF.right), (int) Math.ceil((double) rectF.bottom));
        }
    }

    private void a(RectF rectF, View view) {
        float f;
        rectF.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) view.getWidth(), (float) view.getHeight());
        Matrix matrix = this.r;
        matrix.reset();
        a(matrix, view);
        this.r.mapRect(rectF);
        rectF.offset((float) view.getLeft(), (float) view.getTop());
        if (rectF.right < rectF.left) {
            f = rectF.right;
            rectF.right = rectF.left;
            rectF.left = f;
        }
        if (rectF.bottom < rectF.top) {
            f = rectF.top;
            rectF.top = rectF.bottom;
            rectF.bottom = f;
        }
    }

    private void a(Matrix matrix, View view) {
        float width = (float) view.getWidth();
        float height = (float) view.getHeight();
        boolean z = this.e;
        float f = z ? this.g : width / 2.0f;
        float f2 = z ? this.h : height / 2.0f;
        float f3 = this.i;
        float f4 = this.j;
        float f5 = this.k;
        if (!(f3 == CropImageView.DEFAULT_ASPECT_RATIO && f4 == CropImageView.DEFAULT_ASPECT_RATIO && f5 == CropImageView.DEFAULT_ASPECT_RATIO)) {
            Camera camera = this.d;
            camera.save();
            camera.rotateX(f3);
            camera.rotateY(f4);
            camera.rotateZ(-f5);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f, -f2);
            matrix.postTranslate(f, f2);
        }
        f3 = this.l;
        f4 = this.m;
        if (!(f3 == 1.0f && f4 == 1.0f)) {
            matrix.postScale(f3, f4);
            matrix.postTranslate((-(f / width)) * ((f3 * width) - width), (-(f2 / height)) * ((f4 * height) - height));
        }
        matrix.postTranslate(this.n, this.o);
    }

    /* Access modifiers changed, original: protected */
    public void applyTransformation(float f, Transformation transformation) {
        View view = (View) this.c.get();
        if (view != null) {
            transformation.setAlpha(this.f);
            a(transformation.getMatrix(), view);
        }
    }
}
