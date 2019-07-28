package net.lucode.hackware.magicindicator.buildins.circlenavigator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import java.util.ArrayList;
import java.util.List;

public class CircleNavigator extends View implements aag {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private Interpolator g = new LinearInterpolator();
    private Paint h = new Paint(1);
    private List<PointF> i = new ArrayList();
    private float j;
    private boolean k;
    private a l;
    private float m;
    private float n;
    private int o;
    private boolean p = true;

    public interface a {
        void a(int i);
    }

    public void a() {
    }

    public void b() {
    }

    public void b(int i) {
    }

    public CircleNavigator(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.o = ViewConfiguration.get(context).getScaledTouchSlop();
        this.a = aai.a(context, 3.0d);
        this.d = aai.a(context, 8.0d);
        this.c = aai.a(context, 1.0d);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(c(i), d(i2));
    }

    private int c(int i) {
        int mode = MeasureSpec.getMode(i);
        i = MeasureSpec.getSize(i);
        if (mode == CheckView.UNCHECKED || mode == 0) {
            return (((((this.f * this.a) * 2) + ((this.f - 1) * this.d)) + getPaddingLeft()) + getPaddingRight()) + (this.c * 2);
        }
        if (mode != 1073741824) {
            return 0;
        }
        return i;
    }

    private int d(int i) {
        int mode = MeasureSpec.getMode(i);
        i = MeasureSpec.getSize(i);
        if (mode == CheckView.UNCHECKED || mode == 0) {
            return (((this.a * 2) + (this.c * 2)) + getPaddingTop()) + getPaddingBottom();
        }
        if (mode != 1073741824) {
            return 0;
        }
        return i;
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        this.h.setColor(this.b);
        a(canvas);
        b(canvas);
    }

    private void a(Canvas canvas) {
        this.h.setStyle(Style.STROKE);
        this.h.setStrokeWidth((float) this.c);
        int size = this.i.size();
        for (int i = 0; i < size; i++) {
            PointF pointF = (PointF) this.i.get(i);
            canvas.drawCircle(pointF.x, pointF.y, (float) this.a, this.h);
        }
    }

    private void b(Canvas canvas) {
        this.h.setStyle(Style.FILL);
        if (this.i.size() > 0) {
            canvas.drawCircle(this.j, (float) ((int) ((((float) getHeight()) / 2.0f) + 0.5f)), (float) this.a, this.h);
        }
    }

    private void c() {
        this.i.clear();
        if (this.f > 0) {
            int height = (int) ((((float) getHeight()) / 2.0f) + 0.5f);
            int i = (this.a * 2) + this.d;
            int paddingLeft = (this.a + ((int) ((((float) this.c) / 2.0f) + 0.5f))) + getPaddingLeft();
            for (int i2 = 0; i2 < this.f; i2++) {
                this.i.add(new PointF((float) paddingLeft, (float) height));
                paddingLeft += i;
            }
            this.j = ((PointF) this.i.get(this.e)).x;
        }
    }

    public void a(int i, float f, int i2) {
        if (this.p && !this.i.isEmpty()) {
            i2 = Math.min(this.i.size() - 1, i);
            PointF pointF = (PointF) this.i.get(i2);
            this.j = pointF.x + ((((PointF) this.i.get(Math.min(this.i.size() - 1, i + 1))).x - pointF.x) * this.g.getInterpolation(f));
            invalidate();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                if (this.k) {
                    this.m = x;
                    this.n = y;
                    return true;
                }
                break;
            case 1:
                if (this.l != null && Math.abs(x - this.m) <= ((float) this.o) && Math.abs(y - this.n) <= ((float) this.o)) {
                    y = Float.MAX_VALUE;
                    int i = 0;
                    for (int i2 = 0; i2 < this.i.size(); i2++) {
                        float abs = Math.abs(((PointF) this.i.get(i2)).x - x);
                        if (abs < y) {
                            i = i2;
                            y = abs;
                        }
                    }
                    this.l.a(i);
                    break;
                }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void a(int i) {
        this.e = i;
        if (!this.p) {
            this.j = ((PointF) this.i.get(this.e)).x;
            invalidate();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        c();
    }

    public int getRadius() {
        return this.a;
    }

    public void setRadius(int i) {
        this.a = i;
        c();
        invalidate();
    }

    public int getCircleColor() {
        return this.b;
    }

    public void setCircleColor(int i) {
        this.b = i;
        invalidate();
    }

    public int getStrokeWidth() {
        return this.c;
    }

    public void setStrokeWidth(int i) {
        this.c = i;
        invalidate();
    }

    public int getCircleSpacing() {
        return this.d;
    }

    public void setCircleSpacing(int i) {
        this.d = i;
        c();
        invalidate();
    }

    public Interpolator getStartInterpolator() {
        return this.g;
    }

    public void setStartInterpolator(Interpolator interpolator) {
        this.g = interpolator;
        if (this.g == null) {
            this.g = new LinearInterpolator();
        }
    }

    public int getCircleCount() {
        return this.f;
    }

    public void setCircleCount(int i) {
        this.f = i;
    }

    public void setTouchable(boolean z) {
        this.k = z;
    }

    public void setFollowTouch(boolean z) {
        this.p = z;
    }

    public a getCircleClickListener() {
        return this.l;
    }

    public void setCircleClickListener(a aVar) {
        if (!this.k) {
            this.k = true;
        }
        this.l = aVar;
    }
}
