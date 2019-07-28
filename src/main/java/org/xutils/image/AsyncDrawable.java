package org.xutils.image;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import java.lang.ref.WeakReference;

public final class AsyncDrawable extends Drawable {
    private final WeakReference<a> a;
    private Drawable b;

    public AsyncDrawable(a aVar, Drawable drawable) {
        if (aVar != null) {
            this.b = drawable;
            while (this.b instanceof AsyncDrawable) {
                this.b = ((AsyncDrawable) this.b).b;
            }
            this.a = new WeakReference(aVar);
            return;
        }
        throw new IllegalArgumentException("imageLoader may not be null");
    }

    public a getImageLoader() {
        return (a) this.a.get();
    }

    public void setBaseDrawable(Drawable drawable) {
        this.b = drawable;
    }

    public Drawable getBaseDrawable() {
        return this.b;
    }

    public void draw(Canvas canvas) {
        if (this.b != null) {
            this.b.draw(canvas);
        }
    }

    public void setAlpha(int i) {
        if (this.b != null) {
            this.b.setAlpha(i);
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.b != null) {
            this.b.setColorFilter(colorFilter);
        }
    }

    public int getOpacity() {
        return this.b == null ? -3 : this.b.getOpacity();
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        if (this.b != null) {
            this.b.setBounds(i, i2, i3, i4);
        }
    }

    public void setBounds(Rect rect) {
        if (this.b != null) {
            this.b.setBounds(rect);
        }
    }

    public void setChangingConfigurations(int i) {
        if (this.b != null) {
            this.b.setChangingConfigurations(i);
        }
    }

    public int getChangingConfigurations() {
        return this.b == null ? 0 : this.b.getChangingConfigurations();
    }

    public void setDither(boolean z) {
        if (this.b != null) {
            this.b.setDither(z);
        }
    }

    public void setFilterBitmap(boolean z) {
        if (this.b != null) {
            this.b.setFilterBitmap(z);
        }
    }

    public void invalidateSelf() {
        if (this.b != null) {
            this.b.invalidateSelf();
        }
    }

    public void scheduleSelf(Runnable runnable, long j) {
        if (this.b != null) {
            this.b.scheduleSelf(runnable, j);
        }
    }

    public void unscheduleSelf(Runnable runnable) {
        if (this.b != null) {
            this.b.unscheduleSelf(runnable);
        }
    }

    public void setColorFilter(int i, Mode mode) {
        if (this.b != null) {
            this.b.setColorFilter(i, mode);
        }
    }

    public void clearColorFilter() {
        if (this.b != null) {
            this.b.clearColorFilter();
        }
    }

    public boolean isStateful() {
        return this.b != null && this.b.isStateful();
    }

    public boolean setState(int[] iArr) {
        return this.b != null && this.b.setState(iArr);
    }

    public int[] getState() {
        return this.b == null ? null : this.b.getState();
    }

    public Drawable getCurrent() {
        return this.b == null ? null : this.b.getCurrent();
    }

    public boolean setVisible(boolean z, boolean z2) {
        return this.b != null && this.b.setVisible(z, z2);
    }

    public Region getTransparentRegion() {
        return this.b == null ? null : this.b.getTransparentRegion();
    }

    public int getIntrinsicWidth() {
        return this.b == null ? 0 : this.b.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        return this.b == null ? 0 : this.b.getIntrinsicHeight();
    }

    public int getMinimumWidth() {
        return this.b == null ? 0 : this.b.getMinimumWidth();
    }

    public int getMinimumHeight() {
        return this.b == null ? 0 : this.b.getMinimumHeight();
    }

    public boolean getPadding(Rect rect) {
        return this.b != null && this.b.getPadding(rect);
    }

    public Drawable mutate() {
        return this.b == null ? null : this.b.mutate();
    }

    public ConstantState getConstantState() {
        return this.b == null ? null : this.b.getConstantState();
    }

    /* Access modifiers changed, original: protected */
    public void finalize() throws Throwable {
        super.finalize();
        a imageLoader = getImageLoader();
        if (imageLoader != null) {
            imageLoader.cancel();
        }
    }
}
