package defpackage;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.os.SystemClock;
import android.support.annotation.NonNull;

/* compiled from: ThumbDrawable */
/* renamed from: gc */
public class gc extends gb implements Animatable {
    private final int a;
    private boolean b;
    private boolean c;
    private Runnable d = new 1();

    /* compiled from: ThumbDrawable */
    /* renamed from: gc$1 */
    class 1 implements Runnable {
        1() {
        }

        public void run() {
            gc.this.b = true;
            gc.this.invalidateSelf();
            gc.this.c = false;
        }
    }

    public void start() {
    }

    public gc(@NonNull ColorStateList colorStateList, int i) {
        super(colorStateList);
        this.a = i;
    }

    public int getIntrinsicWidth() {
        return this.a;
    }

    public int getIntrinsicHeight() {
        return this.a;
    }

    public void a(Canvas canvas, Paint paint) {
        if (!this.b) {
            Rect bounds = getBounds();
            canvas.drawCircle((float) bounds.centerX(), (float) bounds.centerY(), (float) (this.a / 2), paint);
        }
    }

    public void a() {
        scheduleSelf(this.d, SystemClock.uptimeMillis() + 100);
        this.c = true;
    }

    public void b() {
        this.b = false;
        this.c = false;
        unscheduleSelf(this.d);
        invalidateSelf();
    }

    public void stop() {
        b();
    }

    public boolean isRunning() {
        return this.c;
    }
}
