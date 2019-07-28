package net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import java.util.Arrays;
import java.util.List;
import net.lucode.hackware.magicindicator.a;

public class BezierPagerIndicator extends View implements aal {
    private List<aan> a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private Paint i;
    private Path j = new Path();
    private List<Integer> k;
    private Interpolator l = new AccelerateInterpolator();
    private Interpolator m = new DecelerateInterpolator();

    public void a(int i) {
    }

    public void b(int i) {
    }

    public BezierPagerIndicator(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.i = new Paint(1);
        this.i.setStyle(Style.FILL);
        this.g = (float) aai.a(context, 3.5d);
        this.h = (float) aai.a(context, 2.0d);
        this.f = (float) aai.a(context, 1.5d);
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        canvas.drawCircle(this.c, (((float) getHeight()) - this.f) - this.g, this.b, this.i);
        canvas.drawCircle(this.e, (((float) getHeight()) - this.f) - this.g, this.d, this.i);
        a(canvas);
    }

    private void a(Canvas canvas) {
        this.j.reset();
        float height = (((float) getHeight()) - this.f) - this.g;
        this.j.moveTo(this.e, height);
        this.j.lineTo(this.e, height - this.d);
        this.j.quadTo(this.e + ((this.c - this.e) / 2.0f), height, this.c, height - this.b);
        this.j.lineTo(this.c, this.b + height);
        this.j.quadTo(this.e + ((this.c - this.e) / 2.0f), height, this.e, this.d + height);
        this.j.close();
        canvas.drawPath(this.j, this.i);
    }

    public void a(int i, float f, int i2) {
        if (this.a != null && !this.a.isEmpty()) {
            if (this.k != null && this.k.size() > 0) {
                this.i.setColor(aah.a(f, ((Integer) this.k.get(Math.abs(i) % this.k.size())).intValue(), ((Integer) this.k.get(Math.abs(i + 1) % this.k.size())).intValue()));
            }
            aan a = a.a(this.a, i);
            aan a2 = a.a(this.a, i + 1);
            float f2 = (float) (a.a + ((a.c - a.a) / 2));
            float f3 = ((float) (a2.a + ((a2.c - a2.a) / 2))) - f2;
            this.c = (this.l.getInterpolation(f) * f3) + f2;
            this.e = f2 + (f3 * this.m.getInterpolation(f));
            this.b = this.g + ((this.h - this.g) * this.m.getInterpolation(f));
            this.d = this.h + ((this.g - this.h) * this.l.getInterpolation(f));
            invalidate();
        }
    }

    public void a(List<aan> list) {
        this.a = list;
    }

    public float getMaxCircleRadius() {
        return this.g;
    }

    public void setMaxCircleRadius(float f) {
        this.g = f;
    }

    public float getMinCircleRadius() {
        return this.h;
    }

    public void setMinCircleRadius(float f) {
        this.h = f;
    }

    public float getYOffset() {
        return this.f;
    }

    public void setYOffset(float f) {
        this.f = f;
    }

    public void setColors(Integer... numArr) {
        this.k = Arrays.asList(numArr);
    }

    public void setStartInterpolator(Interpolator interpolator) {
        this.l = interpolator;
        if (this.l == null) {
            this.l = new AccelerateInterpolator();
        }
    }

    public void setEndInterpolator(Interpolator interpolator) {
        this.m = interpolator;
        if (this.m == null) {
            this.m = new DecelerateInterpolator();
        }
    }
}
