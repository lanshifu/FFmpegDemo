package master.flame.danmaku.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.yv;
import defpackage.yv.a;
import defpackage.yw;
import defpackage.yy;
import defpackage.yz;
import defpackage.zn;
import java.util.LinkedList;
import java.util.Locale;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;

public class DanmakuSurfaceView extends SurfaceView implements Callback, yy, yz {
    protected int a = 0;
    private a b;
    private SurfaceHolder c;
    private yv d;
    private boolean e;
    private boolean f = true;
    private yy.a g;
    private float h;
    private float i;
    private a j;
    private boolean k;
    private boolean l = true;
    private LinkedList<Long> m;

    public View getView() {
        return this;
    }

    public boolean isHardwareAccelerated() {
        return false;
    }

    public DanmakuSurfaceView(Context context) {
        super(context);
        e();
    }

    private void e() {
        setZOrderMediaOverlay(true);
        setWillNotCacheDrawing(true);
        setDrawingCacheEnabled(false);
        setWillNotDraw(true);
        this.c = getHolder();
        this.c.addCallback(this);
        this.c.setFormat(-2);
        yw.a(true, true);
        this.j = a.a((yy) this);
    }

    public DanmakuSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        e();
    }

    public DanmakuSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        e();
    }

    public zn getCurrentVisibleDanmakus() {
        return this.d != null ? this.d.g() : null;
    }

    public void setCallback(a aVar) {
        this.b = aVar;
        if (this.d != null) {
            this.d.a(aVar);
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.e = true;
        Canvas lockCanvas = surfaceHolder.lockCanvas();
        if (lockCanvas != null) {
            yw.a(lockCanvas);
            surfaceHolder.unlockCanvasAndPost(lockCanvas);
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (this.d != null) {
            this.d.a(i2, i3);
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.e = false;
    }

    public DanmakuContext getConfig() {
        if (this.d == null) {
            return null;
        }
        return this.d.i();
    }

    private float f() {
        long a = aaf.a();
        this.m.addLast(Long.valueOf(a));
        Long l = (Long) this.m.peekFirst();
        float f = CropImageView.DEFAULT_ASPECT_RATIO;
        if (l == null) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        float longValue = (float) (a - l.longValue());
        if (this.m.size() > 50) {
            this.m.removeFirst();
        }
        if (longValue > CropImageView.DEFAULT_ASPECT_RATIO) {
            f = ((float) (this.m.size() * 1000)) / longValue;
        }
        return f;
    }

    public long b() {
        if (!this.e) {
            return 0;
        }
        if (!isShown()) {
            return -1;
        }
        long a = aaf.a();
        Canvas lockCanvas = this.c.lockCanvas();
        if (lockCanvas != null) {
            if (this.d != null) {
                b a2 = this.d.a(lockCanvas);
                if (this.k) {
                    if (this.m == null) {
                        this.m = new LinkedList();
                    }
                    aaf.a();
                    yw.a(lockCanvas, String.format(Locale.getDefault(), "fps %.2f,time:%d s,cache:%d,miss:%d", new Object[]{Float.valueOf(f()), Long.valueOf(getCurrentTime() / 1000), Long.valueOf(a2.r), Long.valueOf(a2.s)}));
                }
            }
            if (this.e) {
                this.c.unlockCanvasAndPost(lockCanvas);
            }
        }
        return aaf.a() - a;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean a = this.j.a(motionEvent);
        return !a ? super.onTouchEvent(motionEvent) : a;
    }

    public boolean d() {
        return this.f;
    }

    public boolean a() {
        return this.e;
    }

    public int getViewWidth() {
        return super.getWidth();
    }

    public int getViewHeight() {
        return super.getHeight();
    }

    public void setOnDanmakuClickListener(yy.a aVar) {
        this.g = aVar;
    }

    public void setOnDanmakuClickListener(yy.a aVar, float f, float f2) {
        this.g = aVar;
        this.h = f;
        this.i = f2;
    }

    public yy.a getOnDanmakuClickListener() {
        return this.g;
    }

    public float getXOff() {
        return this.h;
    }

    public float getYOff() {
        return this.i;
    }

    public void c() {
        if (a()) {
            Canvas lockCanvas = this.c.lockCanvas();
            if (lockCanvas != null) {
                yw.a(lockCanvas);
                this.c.unlockCanvasAndPost(lockCanvas);
            }
        }
    }

    public boolean isShown() {
        return this.l && super.isShown();
    }

    public void setDrawingThreadType(int i) {
        this.a = i;
    }

    public long getCurrentTime() {
        return this.d != null ? this.d.h() : 0;
    }
}
