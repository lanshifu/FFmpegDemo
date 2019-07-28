package net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.yalantis.ucrop.view.CropImageView;
import java.util.List;
import net.lucode.hackware.magicindicator.a;

public class TriangularPagerIndicator extends View implements aal {
    private List<aan> a;
    private Paint b;
    private int c;
    private int d;
    private int e;
    private int f;
    private boolean g;
    private float h;
    private Path i = new Path();
    private Interpolator j = new LinearInterpolator();
    private float k;

    public void a(int i) {
    }

    public void b(int i) {
    }

    public TriangularPagerIndicator(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.b = new Paint(1);
        this.b.setStyle(Style.FILL);
        this.c = aai.a(context, 3.0d);
        this.f = aai.a(context, 14.0d);
        this.e = aai.a(context, 8.0d);
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        this.b.setColor(this.d);
        if (this.g) {
            canvas.drawRect(CropImageView.DEFAULT_ASPECT_RATIO, (((float) getHeight()) - this.h) - ((float) this.e), (float) getWidth(), ((((float) getHeight()) - this.h) - ((float) this.e)) + ((float) this.c), this.b);
        } else {
            canvas.drawRect(CropImageView.DEFAULT_ASPECT_RATIO, ((float) (getHeight() - this.c)) - this.h, (float) getWidth(), ((float) getHeight()) - this.h, this.b);
        }
        this.i.reset();
        if (this.g) {
            this.i.moveTo(this.k - ((float) (this.f / 2)), (((float) getHeight()) - this.h) - ((float) this.e));
            this.i.lineTo(this.k, ((float) getHeight()) - this.h);
            this.i.lineTo(this.k + ((float) (this.f / 2)), (((float) getHeight()) - this.h) - ((float) this.e));
        } else {
            this.i.moveTo(this.k - ((float) (this.f / 2)), ((float) getHeight()) - this.h);
            this.i.lineTo(this.k, ((float) (getHeight() - this.e)) - this.h);
            this.i.lineTo(this.k + ((float) (this.f / 2)), ((float) getHeight()) - this.h);
        }
        this.i.close();
        canvas.drawPath(this.i, this.b);
    }

    public void a(int i, float f, int i2) {
        if (this.a != null && !this.a.isEmpty()) {
            aan a = a.a(this.a, i);
            aan a2 = a.a(this.a, i + 1);
            float f2 = (float) (a.a + ((a.c - a.a) / 2));
            this.k = f2 + ((((float) (a2.a + ((a2.c - a2.a) / 2))) - f2) * this.j.getInterpolation(f));
            invalidate();
        }
    }

    public void a(List<aan> list) {
        this.a = list;
    }

    public int getLineHeight() {
        return this.c;
    }

    public void setLineHeight(int i) {
        this.c = i;
    }

    public int getLineColor() {
        return this.d;
    }

    public void setLineColor(int i) {
        this.d = i;
    }

    public int getTriangleHeight() {
        return this.e;
    }

    public void setTriangleHeight(int i) {
        this.e = i;
    }

    public int getTriangleWidth() {
        return this.f;
    }

    public void setTriangleWidth(int i) {
        this.f = i;
    }

    public Interpolator getStartInterpolator() {
        return this.j;
    }

    public void setStartInterpolator(Interpolator interpolator) {
        this.j = interpolator;
        if (this.j == null) {
            this.j = new LinearInterpolator();
        }
    }

    public void setReverse(boolean z) {
        this.g = z;
    }

    public float getYOffset() {
        return this.h;
    }

    public void setYOffset(float f) {
        this.h = f;
    }
}
