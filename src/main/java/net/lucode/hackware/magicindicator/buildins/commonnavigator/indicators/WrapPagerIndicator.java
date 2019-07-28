package net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.util.List;
import net.lucode.hackware.magicindicator.a;

public class WrapPagerIndicator extends View implements aal {
    private int a;
    private int b;
    private int c;
    private float d;
    private Interpolator e = new LinearInterpolator();
    private Interpolator f = new LinearInterpolator();
    private List<aan> g;
    private Paint h;
    private RectF i = new RectF();
    private boolean j;

    public void a(int i) {
    }

    public void b(int i) {
    }

    public WrapPagerIndicator(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.h = new Paint(1);
        this.h.setStyle(Style.FILL);
        this.a = aai.a(context, 6.0d);
        this.b = aai.a(context, 10.0d);
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        this.h.setColor(this.c);
        canvas.drawRoundRect(this.i, this.d, this.d, this.h);
    }

    public void a(int i, float f, int i2) {
        if (this.g != null && !this.g.isEmpty()) {
            aan a = a.a(this.g, i);
            aan a2 = a.a(this.g, i + 1);
            this.i.left = ((float) (a.e - this.b)) + (((float) (a2.e - a.e)) * this.f.getInterpolation(f));
            this.i.top = (float) (a.f - this.a);
            this.i.right = ((float) (a.g + this.b)) + (((float) (a2.g - a.g)) * this.e.getInterpolation(f));
            this.i.bottom = (float) (a.h + this.a);
            if (!this.j) {
                this.d = this.i.height() / 2.0f;
            }
            invalidate();
        }
    }

    public void a(List<aan> list) {
        this.g = list;
    }

    public Paint getPaint() {
        return this.h;
    }

    public int getVerticalPadding() {
        return this.a;
    }

    public void setVerticalPadding(int i) {
        this.a = i;
    }

    public int getHorizontalPadding() {
        return this.b;
    }

    public void setHorizontalPadding(int i) {
        this.b = i;
    }

    public int getFillColor() {
        return this.c;
    }

    public void setFillColor(int i) {
        this.c = i;
    }

    public float getRoundRadius() {
        return this.d;
    }

    public void setRoundRadius(float f) {
        this.d = f;
        this.j = true;
    }

    public Interpolator getStartInterpolator() {
        return this.e;
    }

    public void setStartInterpolator(Interpolator interpolator) {
        this.e = interpolator;
        if (this.e == null) {
            this.e = new LinearInterpolator();
        }
    }

    public Interpolator getEndInterpolator() {
        return this.f;
    }

    public void setEndInterpolator(Interpolator interpolator) {
        this.f = interpolator;
        if (this.f == null) {
            this.f = new LinearInterpolator();
        }
    }
}
