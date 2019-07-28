package it.sephiroth.android.library.imagezoom;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.ViewConfiguration;
import com.yalantis.ucrop.view.CropImageView;

public class ImageViewTouch extends ImageViewTouchBase {
    protected ScaleGestureDetector a;
    protected GestureDetector b;
    protected int c;
    protected float d;
    protected int e;
    protected OnGestureListener f;
    protected OnScaleGestureListener g;
    protected boolean h;
    protected boolean i;
    protected boolean j;
    private b y;
    private c z;

    public class a extends SimpleOnGestureListener {
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (ImageViewTouch.this.z != null) {
                ImageViewTouch.this.z.onSingleTapConfirmed();
            }
            return ImageViewTouch.this.a(motionEvent);
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onDoubleTap. double tap enabled? ");
            stringBuilder.append(ImageViewTouch.this.h);
            Log.i("ImageViewTouchBase", stringBuilder.toString());
            if (ImageViewTouch.this.h) {
                ImageViewTouch.this.q = true;
                ImageViewTouch.this.a(Math.min(ImageViewTouch.this.getMaxScale(), Math.max(ImageViewTouch.this.a(ImageViewTouch.this.getScale(), ImageViewTouch.this.getMaxScale()), ImageViewTouch.this.getMinScale())), motionEvent.getX(), motionEvent.getY(), 200.0f);
                ImageViewTouch.this.invalidate();
            }
            if (ImageViewTouch.this.y != null) {
                ImageViewTouch.this.y.a();
            }
            return super.onDoubleTap(motionEvent);
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (ImageViewTouch.this.isLongClickable() && !ImageViewTouch.this.a.isInProgress()) {
                ImageViewTouch.this.setPressed(true);
                ImageViewTouch.this.performLongClick();
            }
        }

        /* JADX WARNING: Missing block: B:14:0x002d, code skipped:
            return false;
     */
        /* JADX WARNING: Missing block: B:15:0x002e, code skipped:
            return false;
     */
        public boolean onScroll(android.view.MotionEvent r4, android.view.MotionEvent r5, float r6, float r7) {
            /*
            r3 = this;
            r0 = it.sephiroth.android.library.imagezoom.ImageViewTouch.this;
            r0 = r0.j;
            r1 = 0;
            if (r0 != 0) goto L_0x0008;
        L_0x0007:
            return r1;
        L_0x0008:
            if (r4 == 0) goto L_0x002e;
        L_0x000a:
            if (r5 != 0) goto L_0x000d;
        L_0x000c:
            goto L_0x002e;
        L_0x000d:
            r0 = r4.getPointerCount();
            r2 = 1;
            if (r0 > r2) goto L_0x002d;
        L_0x0014:
            r0 = r5.getPointerCount();
            if (r0 <= r2) goto L_0x001b;
        L_0x001a:
            goto L_0x002d;
        L_0x001b:
            r0 = it.sephiroth.android.library.imagezoom.ImageViewTouch.this;
            r0 = r0.a;
            r0 = r0.isInProgress();
            if (r0 == 0) goto L_0x0026;
        L_0x0025:
            return r1;
        L_0x0026:
            r0 = it.sephiroth.android.library.imagezoom.ImageViewTouch.this;
            r4 = r0.a(r4, r5, r6, r7);
            return r4;
        L_0x002d:
            return r1;
        L_0x002e:
            return r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: it.sephiroth.android.library.imagezoom.ImageViewTouch$a.onScroll(android.view.MotionEvent, android.view.MotionEvent, float, float):boolean");
        }

        /* JADX WARNING: Missing block: B:15:0x0035, code skipped:
            return false;
     */
        public boolean onFling(android.view.MotionEvent r4, android.view.MotionEvent r5, float r6, float r7) {
            /*
            r3 = this;
            r0 = it.sephiroth.android.library.imagezoom.ImageViewTouch.this;
            r0 = r0.j;
            r1 = 0;
            if (r0 != 0) goto L_0x0008;
        L_0x0007:
            return r1;
        L_0x0008:
            r0 = r4.getPointerCount();
            r2 = 1;
            if (r0 > r2) goto L_0x0035;
        L_0x000f:
            r0 = r5.getPointerCount();
            if (r0 <= r2) goto L_0x0016;
        L_0x0015:
            goto L_0x0035;
        L_0x0016:
            r0 = it.sephiroth.android.library.imagezoom.ImageViewTouch.this;
            r0 = r0.a;
            r0 = r0.isInProgress();
            if (r0 == 0) goto L_0x0021;
        L_0x0020:
            return r1;
        L_0x0021:
            r0 = it.sephiroth.android.library.imagezoom.ImageViewTouch.this;
            r0 = r0.getScale();
            r2 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
            r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            if (r0 != 0) goto L_0x002e;
        L_0x002d:
            return r1;
        L_0x002e:
            r0 = it.sephiroth.android.library.imagezoom.ImageViewTouch.this;
            r4 = r0.b(r4, r5, r6, r7);
            return r4;
        L_0x0035:
            return r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: it.sephiroth.android.library.imagezoom.ImageViewTouch$a.onFling(android.view.MotionEvent, android.view.MotionEvent, float, float):boolean");
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return ImageViewTouch.this.d(motionEvent);
        }

        public boolean onDown(MotionEvent motionEvent) {
            return ImageViewTouch.this.b(motionEvent);
        }
    }

    public interface b {
        void a();
    }

    public interface c {
        void onSingleTapConfirmed();
    }

    public class d extends SimpleOnScaleGestureListener {
        protected boolean a = false;

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float currentSpan = scaleGestureDetector.getCurrentSpan() - scaleGestureDetector.getPreviousSpan();
            float scale = ImageViewTouch.this.getScale() * scaleGestureDetector.getScaleFactor();
            if (ImageViewTouch.this.i) {
                if (this.a && currentSpan != CropImageView.DEFAULT_ASPECT_RATIO) {
                    ImageViewTouch.this.q = true;
                    ImageViewTouch.this.b(Math.min(ImageViewTouch.this.getMaxScale(), Math.max(scale, ImageViewTouch.this.getMinScale() - 0.1f)), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                    ImageViewTouch.this.e = 1;
                    ImageViewTouch.this.invalidate();
                    return true;
                } else if (!this.a) {
                    this.a = true;
                }
            }
            return true;
        }
    }

    public boolean a(MotionEvent motionEvent) {
        return true;
    }

    public boolean b(MotionEvent motionEvent) {
        return true;
    }

    public boolean d(MotionEvent motionEvent) {
        return true;
    }

    public ImageViewTouch(Context context) {
        super(context);
        this.h = true;
        this.i = true;
        this.j = true;
    }

    public ImageViewTouch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ImageViewTouch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = true;
        this.i = true;
        this.j = true;
    }

    /* Access modifiers changed, original: protected */
    public void a(Context context, AttributeSet attributeSet, int i) {
        super.a(context, attributeSet, i);
        this.c = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.f = getGestureListener();
        this.g = getScaleListener();
        this.a = new ScaleGestureDetector(getContext(), this.g);
        this.b = new GestureDetector(getContext(), this.f, null, true);
        this.e = 1;
    }

    public void setDoubleTapListener(b bVar) {
        this.y = bVar;
    }

    public void setSingleTapListener(c cVar) {
        this.z = cVar;
    }

    public void setDoubleTapEnabled(boolean z) {
        this.h = z;
    }

    public void setScaleEnabled(boolean z) {
        this.i = z;
    }

    public void setScrollEnabled(boolean z) {
        this.j = z;
    }

    public boolean getDoubleTapEnabled() {
        return this.h;
    }

    /* Access modifiers changed, original: protected */
    public OnGestureListener getGestureListener() {
        return new a();
    }

    /* Access modifiers changed, original: protected */
    public OnScaleGestureListener getScaleListener() {
        return new d();
    }

    /* Access modifiers changed, original: protected */
    public void a(Drawable drawable, Matrix matrix, float f, float f2) {
        super.a(drawable, matrix, f, f2);
        this.d = getMaxScale() / 3.0f;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.a.onTouchEvent(motionEvent);
        if (!this.a.isInProgress()) {
            this.b.onTouchEvent(motionEvent);
        }
        if ((motionEvent.getAction() & 255) != 1) {
            return true;
        }
        return c(motionEvent);
    }

    /* Access modifiers changed, original: protected */
    public void a(float f) {
        if (f < getMinScale()) {
            c(getMinScale(), 50.0f);
        }
    }

    /* Access modifiers changed, original: protected */
    public float a(float f, float f2) {
        if (this.e != 1) {
            this.e = 1;
            return 1.0f;
        } else if ((this.d * 2.0f) + f <= f2) {
            return f + this.d;
        } else {
            this.e = -1;
            return f2;
        }
    }

    public boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (getScale() == 1.0f) {
            return false;
        }
        this.q = true;
        d(-f, -f2);
        invalidate();
        return true;
    }

    public boolean b(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float x = motionEvent2.getX() - motionEvent.getX();
        float y = motionEvent2.getY() - motionEvent.getY();
        if (Math.abs(f) <= 800.0f && Math.abs(f2) <= 800.0f) {
            return false;
        }
        this.q = true;
        a(x / 2.0f, y / 2.0f, 300.0d);
        invalidate();
        return true;
    }

    public boolean c(MotionEvent motionEvent) {
        if (getScale() < getMinScale()) {
            c(getMinScale(), 50.0f);
        }
        return true;
    }

    public boolean a(int i) {
        RectF bitmapRect = getBitmapRect();
        a(bitmapRect, this.x);
        Rect rect = new Rect();
        getGlobalVisibleRect(rect);
        boolean z = false;
        if (bitmapRect == null) {
            return false;
        }
        if (bitmapRect.right < ((float) rect.right) || i >= 0) {
            if (((double) Math.abs(bitmapRect.left - this.x.left)) > 1.0d) {
                z = true;
            }
            return z;
        }
        if (Math.abs(bitmapRect.right - ((float) rect.right)) > 1.0f) {
            z = true;
        }
        return z;
    }
}
