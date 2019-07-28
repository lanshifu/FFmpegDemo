package master.flame.danmaku.ui.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
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

@SuppressLint({"NewApi"})
public class DanmakuTextureView extends TextureView implements SurfaceTextureListener, yy, yz {
    protected int a = 0;
    private a b;
    private yv c;
    private boolean d;
    private boolean e = true;
    private yy.a f;
    private float g;
    private float h;
    private a i;
    private boolean j;
    private boolean k = true;
    private LinkedList<Long> l;

    public View getView() {
        return this;
    }

    public boolean isHardwareAccelerated() {
        return false;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public DanmakuTextureView(Context context) {
        super(context);
        e();
    }

    @TargetApi(11)
    private void e() {
        setLayerType(2, null);
        setOpaque(false);
        setWillNotCacheDrawing(true);
        setDrawingCacheEnabled(false);
        setWillNotDraw(true);
        setSurfaceTextureListener(this);
        yw.a(true, true);
        this.i = a.a((yy) this);
    }

    public DanmakuTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        e();
    }

    public DanmakuTextureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        e();
    }

    public zn getCurrentVisibleDanmakus() {
        return this.c != null ? this.c.g() : null;
    }

    public void setCallback(a aVar) {
        this.b = aVar;
        if (this.c != null) {
            this.c.a(aVar);
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.d = true;
    }

    public synchronized boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.d = false;
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.c != null) {
            this.c.a(i, i2);
        }
    }

    public DanmakuContext getConfig() {
        if (this.c == null) {
            return null;
        }
        return this.c.i();
    }

    private float f() {
        long a = aaf.a();
        this.l.addLast(Long.valueOf(a));
        Long l = (Long) this.l.peekFirst();
        float f = CropImageView.DEFAULT_ASPECT_RATIO;
        if (l == null) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        float longValue = (float) (a - l.longValue());
        if (this.l.size() > 50) {
            this.l.removeFirst();
        }
        if (longValue > CropImageView.DEFAULT_ASPECT_RATIO) {
            f = ((float) (this.l.size() * 1000)) / longValue;
        }
        return f;
    }

    public synchronized long b() {
        if (!this.d) {
            return 0;
        }
        long a = aaf.a();
        if (!isShown()) {
            return -1;
        }
        Canvas lockCanvas = lockCanvas();
        if (lockCanvas != null) {
            if (this.c != null) {
                b a2 = this.c.a(lockCanvas);
                if (this.j) {
                    if (this.l == null) {
                        this.l = new LinkedList();
                    }
                    aaf.a();
                    yw.a(lockCanvas, String.format(Locale.getDefault(), "fps %.2f,time:%d s,cache:%d,miss:%d", new Object[]{Float.valueOf(f()), Long.valueOf(getCurrentTime() / 1000), Long.valueOf(a2.r), Long.valueOf(a2.s)}));
                }
            }
            if (this.d) {
                unlockCanvasAndPost(lockCanvas);
            }
        }
        return aaf.a() - a;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean a = this.i.a(motionEvent);
        return !a ? super.onTouchEvent(motionEvent) : a;
    }

    public boolean d() {
        return this.e;
    }

    public boolean a() {
        return this.d;
    }

    public int getViewWidth() {
        return super.getWidth();
    }

    public int getViewHeight() {
        return super.getHeight();
    }

    public void setOnDanmakuClickListener(yy.a aVar) {
        this.f = aVar;
    }

    public void setOnDanmakuClickListener(yy.a aVar, float f, float f2) {
        this.f = aVar;
        this.g = f;
        this.h = f2;
    }

    public yy.a getOnDanmakuClickListener() {
        return this.f;
    }

    public float getXOff() {
        return this.g;
    }

    public float getYOff() {
        return this.h;
    }

    /* JADX WARNING: Missing block: B:11:0x0016, code skipped:
            return;
     */
    public synchronized void c() {
        /*
        r1 = this;
        monitor-enter(r1);
        r0 = r1.a();	 Catch:{ all -> 0x0017 }
        if (r0 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r1);
        return;
    L_0x0009:
        r0 = r1.lockCanvas();	 Catch:{ all -> 0x0017 }
        if (r0 == 0) goto L_0x0015;
    L_0x000f:
        defpackage.yw.a(r0);	 Catch:{ all -> 0x0017 }
        r1.unlockCanvasAndPost(r0);	 Catch:{ all -> 0x0017 }
    L_0x0015:
        monitor-exit(r1);
        return;
    L_0x0017:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: master.flame.danmaku.ui.widget.DanmakuTextureView.c():void");
    }

    public boolean isShown() {
        return this.k && super.isShown();
    }

    public void setDrawingThreadType(int i) {
        this.a = i;
    }

    public long getCurrentTime() {
        return this.c != null ? this.c.h() : 0;
    }
}
