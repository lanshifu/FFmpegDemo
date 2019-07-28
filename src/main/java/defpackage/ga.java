package defpackage;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import com.yalantis.ucrop.view.CropImageView;

/* compiled from: MarkerDrawable */
/* renamed from: ga */
public class ga extends gb implements Animatable {
    Path a = new Path();
    RectF b = new RectF();
    Matrix c = new Matrix();
    private float d = CropImageView.DEFAULT_ASPECT_RATIO;
    private Interpolator e = new AccelerateDecelerateInterpolator();
    private long f;
    private boolean g = false;
    private boolean h = false;
    private int i = 250;
    private float j;
    private float k;
    private int l;
    private int m;
    private int n;
    private a o;
    private final Runnable p = new 1();

    /* compiled from: MarkerDrawable */
    /* renamed from: ga$1 */
    class 1 implements Runnable {
        1() {
        }

        public void run() {
            long uptimeMillis = SystemClock.uptimeMillis();
            long a = uptimeMillis - ga.this.f;
            if (a < ((long) ga.this.i)) {
                float interpolation = ga.this.e.getInterpolation(((float) a) / ((float) ga.this.i));
                ga.this.scheduleSelf(ga.this.p, uptimeMillis + 16);
                ga.this.a(interpolation);
                return;
            }
            ga.this.unscheduleSelf(ga.this.p);
            ga.this.h = false;
            ga.this.a(1.0f);
            ga.this.d();
        }
    }

    /* compiled from: MarkerDrawable */
    /* renamed from: ga$a */
    public interface a {
        void a();

        void b();
    }

    public void start() {
    }

    public ga(@NonNull ColorStateList colorStateList, int i) {
        super(colorStateList);
        this.j = (float) i;
        this.m = colorStateList.getColorForState(new int[]{16842910, 16842919}, colorStateList.getDefaultColor());
        this.n = colorStateList.getDefaultColor();
    }

    public void a(int i) {
        this.l = i;
    }

    public void a(int i, int i2) {
        this.m = i;
        this.n = i2;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(Canvas canvas, Paint paint) {
        if (!this.a.isEmpty()) {
            paint.setStyle(Style.FILL);
            paint.setColor(ga.a(this.m, this.n, this.d));
            canvas.drawPath(this.a, paint);
        }
    }

    public Path a() {
        return this.a;
    }

    /* Access modifiers changed, original: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        a(rect);
    }

    private void a(Rect rect) {
        float f = this.d;
        Path path = this.a;
        RectF rectF = this.b;
        Matrix matrix = this.c;
        path.reset();
        int min = Math.min(rect.width(), rect.height());
        float f2 = this.j;
        f2 += (((float) min) - f2) * f;
        float f3 = f2 / 2.0f;
        float f4 = 1.0f - f;
        f = f3 * f4;
        float[] fArr = new float[]{f3, f3, f3, f3, f3, f3, f, f};
        rectF.set((float) rect.left, (float) rect.top, ((float) rect.left) + f2, ((float) rect.top) + f2);
        path.addRoundRect(rectF, fArr, Direction.CCW);
        matrix.reset();
        matrix.postRotate(-45.0f, ((float) rect.left) + f3, ((float) rect.top) + f3);
        matrix.postTranslate((((float) rect.width()) - f2) / 2.0f, CropImageView.DEFAULT_ASPECT_RATIO);
        matrix.postTranslate(CropImageView.DEFAULT_ASPECT_RATIO, ((((float) rect.bottom) - f2) - ((float) this.l)) * f4);
        path.transform(matrix);
    }

    private void a(float f) {
        float f2 = this.k;
        this.d = f2 + (((this.g ? CropImageView.DEFAULT_ASPECT_RATIO : 1.0f) - f2) * f);
        a(getBounds());
        invalidateSelf();
    }

    public void b() {
        unscheduleSelf(this.p);
        this.g = false;
        if (this.d < 1.0f) {
            this.h = true;
            this.k = this.d;
            this.i = (int) ((1.0f - this.d) * 250.0f);
            this.f = SystemClock.uptimeMillis();
            scheduleSelf(this.p, this.f + 16);
            return;
        }
        d();
    }

    public void c() {
        this.g = true;
        unscheduleSelf(this.p);
        if (this.d > CropImageView.DEFAULT_ASPECT_RATIO) {
            this.h = true;
            this.k = this.d;
            this.i = 250 - ((int) ((1.0f - this.d) * 250.0f));
            this.f = SystemClock.uptimeMillis();
            scheduleSelf(this.p, this.f + 16);
            return;
        }
        d();
    }

    public void a(a aVar) {
        this.o = aVar;
    }

    private void d() {
        if (this.o == null) {
            return;
        }
        if (this.g) {
            this.o.a();
        } else {
            this.o.b();
        }
    }

    public void stop() {
        unscheduleSelf(this.p);
    }

    public boolean isRunning() {
        return this.h;
    }

    private static int a(int i, int i2, float f) {
        float f2 = 1.0f - f;
        return Color.argb((int) ((((float) Color.alpha(i)) * f) + (((float) Color.alpha(i2)) * f2)), (int) ((((float) Color.red(i)) * f) + (((float) Color.red(i2)) * f2)), (int) ((((float) Color.green(i)) * f) + (((float) Color.green(i2)) * f2)), (int) ((((float) Color.blue(i)) * f) + (((float) Color.blue(i2)) * f2)));
    }
}
