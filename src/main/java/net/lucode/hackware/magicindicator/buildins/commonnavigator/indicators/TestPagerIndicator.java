package net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.View;
import java.util.List;
import net.lucode.hackware.magicindicator.a;

public class TestPagerIndicator extends View implements aal {
    private Paint a;
    private int b;
    private int c;
    private RectF d = new RectF();
    private RectF e = new RectF();
    private List<aan> f;

    public void a(int i) {
    }

    public void b(int i) {
    }

    public TestPagerIndicator(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.a = new Paint(1);
        this.a.setStyle(Style.STROKE);
        this.b = -65536;
        this.c = -16711936;
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        this.a.setColor(this.b);
        canvas.drawRect(this.d, this.a);
        this.a.setColor(this.c);
        canvas.drawRect(this.e, this.a);
    }

    public void a(int i, float f, int i2) {
        if (this.f != null && !this.f.isEmpty()) {
            aan a = a.a(this.f, i);
            aan a2 = a.a(this.f, i + 1);
            this.d.left = ((float) a.a) + (((float) (a2.a - a.a)) * f);
            this.d.top = ((float) a.b) + (((float) (a2.b - a.b)) * f);
            this.d.right = ((float) a.c) + (((float) (a2.c - a.c)) * f);
            this.d.bottom = ((float) a.d) + (((float) (a2.d - a.d)) * f);
            this.e.left = ((float) a.e) + (((float) (a2.e - a.e)) * f);
            this.e.top = ((float) a.f) + (((float) (a2.f - a.f)) * f);
            this.e.right = ((float) a.g) + (((float) (a2.g - a.g)) * f);
            this.e.bottom = ((float) a.h) + (((float) (a2.h - a.h)) * f);
            invalidate();
        }
    }

    public void a(List<aan> list) {
        this.f = list;
    }

    public int getOutRectColor() {
        return this.b;
    }

    public void setOutRectColor(int i) {
        this.b = i;
    }

    public int getInnerRectColor() {
        return this.c;
    }

    public void setInnerRectColor(int i) {
        this.c = i;
    }
}
