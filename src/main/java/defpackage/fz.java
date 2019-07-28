package defpackage;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import com.yalantis.ucrop.view.CropImageView;

/* compiled from: AlmostRippleDrawable */
/* renamed from: fz */
public class fz extends gb implements Animatable {
    private float a = CropImageView.DEFAULT_ASPECT_RATIO;
    private Interpolator b = new AccelerateDecelerateInterpolator();
    private long c;
    private boolean d = false;
    private boolean e = false;
    private int f = 250;
    private float g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private final Runnable m = new 1();

    /* compiled from: AlmostRippleDrawable */
    /* renamed from: fz$1 */
    class 1 implements Runnable {
        1() {
        }

        public void run() {
            long uptimeMillis = SystemClock.uptimeMillis();
            long a = uptimeMillis - fz.this.c;
            if (a < ((long) fz.this.f)) {
                float interpolation = fz.this.b.getInterpolation(((float) a) / ((float) fz.this.f));
                fz.this.scheduleSelf(fz.this.m, uptimeMillis + 16);
                fz.this.a(interpolation);
                return;
            }
            fz.this.unscheduleSelf(fz.this.m);
            fz.this.e = false;
            fz.this.a(1.0f);
        }
    }

    private int a(int i) {
        return (i * 100) >> 8;
    }

    public void start() {
    }

    public void stop() {
    }

    public fz(@NonNull ColorStateList colorStateList) {
        super(colorStateList);
        a(colorStateList);
    }

    public void a(@NonNull ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        this.i = colorStateList.getColorForState(new int[]{16842910, 16842908}, defaultColor);
        this.h = colorStateList.getColorForState(new int[]{16842910, 16842919}, defaultColor);
        this.j = colorStateList.getColorForState(new int[]{-16842910}, defaultColor);
        this.i = fz.a(130, this.i);
        this.h = fz.a(130, this.h);
        this.j = fz.a(130, this.j);
    }

    private static int a(int i, int i2) {
        return Color.argb((Color.alpha(i2) * (i + (i >> 7))) >> 8, Color.red(i2), Color.green(i2), Color.blue(i2));
    }

    public void a(Canvas canvas, Paint paint) {
        Rect bounds = getBounds();
        int min = Math.min(bounds.width(), bounds.height());
        float f = this.a;
        int i = this.k;
        int i2 = this.l;
        float f2 = (float) (min / 2);
        float f3 = f2 * f;
        if (f > CropImageView.DEFAULT_ASPECT_RATIO) {
            if (i2 != 0) {
                paint.setColor(i2);
                paint.setAlpha(a(Color.alpha(i2)));
                canvas.drawCircle((float) bounds.centerX(), (float) bounds.centerY(), f2, paint);
            }
            if (i != 0) {
                paint.setColor(i);
                paint.setAlpha(b(Color.alpha(i)));
                canvas.drawCircle((float) bounds.centerX(), (float) bounds.centerY(), f3, paint);
            }
        }
    }

    public boolean setState(int[] iArr) {
        Object obj = null;
        for (int i : getState()) {
            if (i == 16842919) {
                obj = 1;
            }
        }
        super.setState(iArr);
        Object obj2 = 1;
        Object obj3 = null;
        Object obj4 = null;
        for (int i2 : iArr) {
            if (i2 == 16842908) {
                obj4 = 1;
            } else if (i2 == 16842919) {
                obj3 = 1;
            } else if (i2 == 16842910) {
                obj2 = null;
            }
        }
        int i3;
        if (obj2 != null) {
            unscheduleSelf(this.m);
            this.k = this.j;
            this.l = 0;
            this.a = 0.5f;
            invalidateSelf();
        } else if (obj3 != null) {
            a();
            i3 = this.h;
            this.l = i3;
            this.k = i3;
        } else if (obj != null) {
            i3 = this.h;
            this.l = i3;
            this.k = i3;
            b();
        } else if (obj4 != null) {
            this.k = this.i;
            this.l = 0;
            this.a = 1.0f;
            invalidateSelf();
        } else {
            this.k = 0;
            this.l = 0;
            this.a = CropImageView.DEFAULT_ASPECT_RATIO;
            invalidateSelf();
        }
        return true;
    }

    public void a() {
        unscheduleSelf(this.m);
        if (this.a < 1.0f) {
            this.d = false;
            this.e = true;
            this.g = this.a;
            this.f = (int) ((1.0f - ((this.g - CropImageView.DEFAULT_ASPECT_RATIO) / 1.0f)) * 250.0f);
            this.c = SystemClock.uptimeMillis();
            scheduleSelf(this.m, this.c + 16);
        }
    }

    public void b() {
        unscheduleSelf(this.m);
        if (this.a > CropImageView.DEFAULT_ASPECT_RATIO) {
            this.d = true;
            this.e = true;
            this.g = this.a;
            this.f = (int) ((1.0f - ((this.g - 1.0f) / -1.0f)) * 250.0f);
            this.c = SystemClock.uptimeMillis();
            scheduleSelf(this.m, this.c + 16);
        }
    }

    private void a(float f) {
        float f2 = this.g;
        this.a = f2 + (((this.d ? CropImageView.DEFAULT_ASPECT_RATIO : 1.0f) - f2) * f);
        invalidateSelf();
    }

    public boolean isRunning() {
        return this.e;
    }
}
