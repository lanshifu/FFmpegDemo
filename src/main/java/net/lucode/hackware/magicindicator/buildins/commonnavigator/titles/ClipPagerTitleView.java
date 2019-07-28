package net.lucode.hackware.magicindicator.buildins.commonnavigator.titles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.view.View;
import android.view.View.MeasureSpec;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import com.yalantis.ucrop.view.CropImageView;

public class ClipPagerTitleView extends View implements aak {
    private String a;
    private int b;
    private int c;
    private boolean d;
    private float e;
    private Paint f;
    private Rect g = new Rect();

    public void onDeselected(int i, int i2) {
    }

    public void onSelected(int i, int i2) {
    }

    public ClipPagerTitleView(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        int a = aai.a(context, 16.0d);
        this.f = new Paint(1);
        this.f.setTextSize((float) a);
        int a2 = aai.a(context, 10.0d);
        setPadding(a2, 0, a2, 0);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        a();
        setMeasuredDimension(a(i), b(i2));
    }

    private int a(int i) {
        int mode = MeasureSpec.getMode(i);
        i = MeasureSpec.getSize(i);
        if (mode == CheckView.UNCHECKED) {
            return Math.min((this.g.width() + getPaddingLeft()) + getPaddingRight(), i);
        }
        if (mode != 0) {
            return i;
        }
        return (this.g.width() + getPaddingLeft()) + getPaddingRight();
    }

    private int b(int i) {
        int mode = MeasureSpec.getMode(i);
        i = MeasureSpec.getSize(i);
        if (mode == CheckView.UNCHECKED) {
            return Math.min((this.g.height() + getPaddingTop()) + getPaddingBottom(), i);
        }
        if (mode != 0) {
            return i;
        }
        return (this.g.height() + getPaddingTop()) + getPaddingBottom();
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        int width = (getWidth() - this.g.width()) / 2;
        FontMetrics fontMetrics = this.f.getFontMetrics();
        int height = (int) (((((float) getHeight()) - fontMetrics.bottom) - fontMetrics.top) / 2.0f);
        this.f.setColor(this.b);
        float f = (float) width;
        float f2 = (float) height;
        canvas.drawText(this.a, f, f2, this.f);
        canvas.save(2);
        if (this.d) {
            canvas.clipRect(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, ((float) getWidth()) * this.e, (float) getHeight());
        } else {
            canvas.clipRect(((float) getWidth()) * (1.0f - this.e), CropImageView.DEFAULT_ASPECT_RATIO, (float) getWidth(), (float) getHeight());
        }
        this.f.setColor(this.c);
        canvas.drawText(this.a, f, f2, this.f);
        canvas.restore();
    }

    public void onLeave(int i, int i2, float f, boolean z) {
        this.d = z ^ 1;
        this.e = 1.0f - f;
        invalidate();
    }

    public void onEnter(int i, int i2, float f, boolean z) {
        this.d = z;
        this.e = f;
        invalidate();
    }

    private void a() {
        this.f.getTextBounds(this.a, 0, this.a == null ? 0 : this.a.length(), this.g);
    }

    public String getText() {
        return this.a;
    }

    public void setText(String str) {
        this.a = str;
        requestLayout();
    }

    public float getTextSize() {
        return this.f.getTextSize();
    }

    public void setTextSize(float f) {
        this.f.setTextSize(f);
        requestLayout();
    }

    public int getTextColor() {
        return this.b;
    }

    public void setTextColor(int i) {
        this.b = i;
        invalidate();
    }

    public int getClipColor() {
        return this.c;
    }

    public void setClipColor(int i) {
        this.c = i;
        invalidate();
    }

    public int getContentLeft() {
        return (getLeft() + (getWidth() / 2)) - (this.g.width() / 2);
    }

    public int getContentTop() {
        FontMetrics fontMetrics = this.f.getFontMetrics();
        return (int) (((float) (getHeight() / 2)) - ((fontMetrics.bottom - fontMetrics.top) / 2.0f));
    }

    public int getContentRight() {
        return (getLeft() + (getWidth() / 2)) + (this.g.width() / 2);
    }

    public int getContentBottom() {
        FontMetrics fontMetrics = this.f.getFontMetrics();
        return (int) (((float) (getHeight() / 2)) + ((fontMetrics.bottom - fontMetrics.top) / 2.0f));
    }
}
