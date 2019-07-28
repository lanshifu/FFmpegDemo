package defpackage;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

/* compiled from: StateDrawable */
/* renamed from: gb */
public abstract class gb extends Drawable {
    private ColorStateList a;
    private final Paint b;
    private int c;
    private int d = 255;

    public abstract void a(Canvas canvas, Paint paint);

    public int getOpacity() {
        return -3;
    }

    public gb(@NonNull ColorStateList colorStateList) {
        b(colorStateList);
        this.b = new Paint(1);
    }

    public boolean isStateful() {
        return this.a.isStateful() || super.isStateful();
    }

    public boolean setState(int[] iArr) {
        return a(iArr) || super.setState(iArr);
    }

    private boolean a(int[] iArr) {
        int colorForState = this.a.getColorForState(iArr, this.c);
        if (colorForState == this.c) {
            return false;
        }
        this.c = colorForState;
        invalidateSelf();
        return true;
    }

    public void draw(Canvas canvas) {
        this.b.setColor(this.c);
        this.b.setAlpha(b(Color.alpha(this.c)));
        a(canvas, this.b);
    }

    public void b(@NonNull ColorStateList colorStateList) {
        this.a = colorStateList;
        this.c = colorStateList.getDefaultColor();
    }

    public void setAlpha(int i) {
        this.d = i;
        invalidateSelf();
    }

    /* Access modifiers changed, original: 0000 */
    public int b(int i) {
        return (i * (this.d + (this.d >> 7))) >> 8;
    }

    public int getAlpha() {
        return this.d;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.b.setColorFilter(colorFilter);
    }
}
