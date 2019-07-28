package master.flame.danmaku.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.yv;
import defpackage.yv.a;
import defpackage.yw;
import defpackage.yy;
import defpackage.yz;
import defpackage.zf;
import defpackage.zn;
import defpackage.zy;
import java.util.LinkedList;
import java.util.Locale;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;

public class DanmakuView extends View implements yy, yz {
    protected volatile yv a;
    protected int b = 0;
    protected boolean c = false;
    protected boolean d;
    private a e;
    private HandlerThread f;
    private boolean g;
    private boolean h = true;
    private yy.a i;
    private float j;
    private float k;
    private a l;
    private boolean m;
    private boolean n = true;
    private Object o = new Object();
    private boolean p = false;
    private long q;
    private LinkedList<Long> r;
    private int s = 0;
    private Runnable t = new Runnable() {
        public void run() {
            yv yvVar = DanmakuView.this.a;
            if (yvVar != null) {
                DanmakuView.this.s = DanmakuView.this.s + 1;
                if (DanmakuView.this.s > 4 || super.isShown()) {
                    yvVar.d();
                } else {
                    yvVar.postDelayed(this, (long) (DanmakuView.this.s * 100));
                }
            }
        }
    };

    public View getView() {
        return this;
    }

    public DanmakuView(Context context) {
        super(context);
        n();
    }

    private void n() {
        this.q = Thread.currentThread().getId();
        setBackgroundColor(0);
        setDrawingCacheBackgroundColor(0);
        yw.a(true, false);
        this.l = a.a((yy) this);
    }

    public DanmakuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        n();
    }

    public DanmakuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        n();
    }

    public void a(zf zfVar) {
        if (this.a != null) {
            this.a.a(zfVar);
        }
    }

    public zn getCurrentVisibleDanmakus() {
        return this.a != null ? this.a.g() : null;
    }

    public void setCallback(a aVar) {
        this.e = aVar;
        if (this.a != null) {
            this.a.a(aVar);
        }
    }

    public void e() {
        f();
        if (this.r != null) {
            this.r.clear();
        }
    }

    public void f() {
        o();
    }

    /* JADX WARNING: Missing block: B:19:0x0026, code skipped:
            return;
     */
    private synchronized void o() {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.a;	 Catch:{ all -> 0x0027 }
        if (r0 != 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r2);
        return;
    L_0x0007:
        r0 = r2.a;	 Catch:{ all -> 0x0027 }
        r1 = 0;
        r2.a = r1;	 Catch:{ all -> 0x0027 }
        r2.t();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x0014;
    L_0x0011:
        r0.a();	 Catch:{ all -> 0x0027 }
    L_0x0014:
        r0 = r2.f;	 Catch:{ all -> 0x0027 }
        r2.f = r1;	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x0025;
    L_0x001a:
        r0.join();	 Catch:{ InterruptedException -> 0x001e }
        goto L_0x0022;
    L_0x001e:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x0027 }
    L_0x0022:
        r0.quit();	 Catch:{ all -> 0x0027 }
    L_0x0025:
        monitor-exit(r2);
        return;
    L_0x0027:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: master.flame.danmaku.ui.widget.DanmakuView.o():void");
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    public synchronized Looper a(int i) {
        if (this.f != null) {
            this.f.quit();
            this.f = null;
        }
        switch (i) {
            case 1:
                return Looper.getMainLooper();
            case 2:
                i = -8;
                break;
            case 3:
                i = 19;
                break;
            default:
                i = 0;
                break;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DFM Handler Thread #");
        stringBuilder.append(i);
        this.f = new HandlerThread(stringBuilder.toString(), i);
        this.f.start();
        return this.f.getLooper();
    }

    private void p() {
        if (this.a == null) {
            this.a = new yv(a(this.b), this, this.n);
        }
    }

    public void a(zy zyVar, DanmakuContext danmakuContext) {
        p();
        this.a.a(danmakuContext);
        this.a.a(zyVar);
        this.a.a(this.e);
        this.a.e();
    }

    public boolean g() {
        return this.a != null && this.a.c();
    }

    public DanmakuContext getConfig() {
        if (this.a == null) {
            return null;
        }
        return this.a.i();
    }

    public void a(boolean z) {
        this.m = z;
    }

    private float q() {
        long a = aaf.a();
        this.r.addLast(Long.valueOf(a));
        Long l = (Long) this.r.peekFirst();
        float f = CropImageView.DEFAULT_ASPECT_RATIO;
        if (l == null) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        float longValue = (float) (a - l.longValue());
        if (this.r.size() > 50) {
            this.r.removeFirst();
        }
        if (longValue > CropImageView.DEFAULT_ASPECT_RATIO) {
            f = ((float) (this.r.size() * 1000)) / longValue;
        }
        return f;
    }

    public long b() {
        if (!this.g) {
            return 0;
        }
        if (!isShown()) {
            return -1;
        }
        long a = aaf.a();
        h();
        return aaf.a() - a;
    }

    @SuppressLint({"NewApi"})
    private void r() {
        this.c = true;
        if (VERSION.SDK_INT >= 16) {
            postInvalidateOnAnimation();
        } else {
            postInvalidate();
        }
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x001b */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:13|14|(2:18|(2:30|20)(2:21|35))(1:29)) */
    /* JADX WARNING: Missing block: B:19:0x0029, code skipped:
            if (r4.a.b() != false) goto L_0x002b;
     */
    /* JADX WARNING: Missing block: B:21:0x002c, code skipped:
            java.lang.Thread.currentThread().interrupt();
     */
    public void h() {
        /*
        r4 = this;
        r0 = r4.n;
        if (r0 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r4.r();
        r0 = r4.o;
        monitor-enter(r0);
    L_0x000b:
        r1 = r4.p;	 Catch:{ all -> 0x0039 }
        if (r1 != 0) goto L_0x0034;
    L_0x000f:
        r1 = r4.a;	 Catch:{ all -> 0x0039 }
        if (r1 == 0) goto L_0x0034;
    L_0x0013:
        r1 = r4.o;	 Catch:{ InterruptedException -> 0x001b }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r1.wait(r2);	 Catch:{ InterruptedException -> 0x001b }
        goto L_0x000b;
    L_0x001b:
        r1 = r4.n;	 Catch:{ all -> 0x0039 }
        if (r1 == 0) goto L_0x0034;
    L_0x001f:
        r1 = r4.a;	 Catch:{ all -> 0x0039 }
        if (r1 == 0) goto L_0x0034;
    L_0x0023:
        r1 = r4.a;	 Catch:{ all -> 0x0039 }
        r1 = r1.b();	 Catch:{ all -> 0x0039 }
        if (r1 == 0) goto L_0x002c;
    L_0x002b:
        goto L_0x0034;
    L_0x002c:
        r1 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0039 }
        r1.interrupt();	 Catch:{ all -> 0x0039 }
        goto L_0x000b;
    L_0x0034:
        r1 = 0;
        r4.p = r1;	 Catch:{ all -> 0x0039 }
        monitor-exit(r0);	 Catch:{ all -> 0x0039 }
        return;
    L_0x0039:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0039 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: master.flame.danmaku.ui.widget.DanmakuView.h():void");
    }

    private void s() {
        this.d = true;
        h();
    }

    private void t() {
        synchronized (this.o) {
            this.p = true;
            this.o.notifyAll();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        if (this.n || this.c) {
            if (this.d) {
                yw.a(canvas);
                this.d = false;
            } else if (this.a != null) {
                b a = this.a.a(canvas);
                if (this.m) {
                    if (this.r == null) {
                        this.r = new LinkedList();
                    }
                    yw.a(canvas, String.format(Locale.getDefault(), "fps %.2f,time:%d s,cache:%d,miss:%d", new Object[]{Float.valueOf(q()), Long.valueOf(getCurrentTime() / 1000), Long.valueOf(a.r), Long.valueOf(a.s)}));
                }
            }
            this.c = false;
            t();
            return;
        }
        super.onDraw(canvas);
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.a != null) {
            this.a.a(i3 - i, i4 - i2);
        }
        this.g = true;
    }

    public void i() {
        if (this.a != null) {
            this.a.removeCallbacks(this.t);
            this.a.f();
        }
    }

    public void j() {
        if (this.a != null && this.a.c()) {
            this.s = 0;
            this.a.post(this.t);
        } else if (this.a == null) {
            l();
        }
    }

    public boolean k() {
        return this.a != null ? this.a.b() : false;
    }

    public void l() {
        f();
        m();
    }

    public void m() {
        a(0);
    }

    public void a(long j) {
        Handler handler = this.a;
        if (handler == null) {
            p();
            handler = this.a;
        } else {
            handler.removeCallbacksAndMessages(null);
        }
        if (handler != null) {
            handler.obtainMessage(1, Long.valueOf(j)).sendToTarget();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean a = this.l.a(motionEvent);
        return !a ? super.onTouchEvent(motionEvent) : a;
    }

    public void b(boolean z) {
        this.h = z;
    }

    public boolean d() {
        return this.h;
    }

    public boolean a() {
        return this.g;
    }

    public int getViewWidth() {
        return super.getWidth();
    }

    public int getViewHeight() {
        return super.getHeight();
    }

    public void c() {
        if (a()) {
            if (!this.n || Thread.currentThread().getId() == this.q) {
                this.d = true;
                r();
            } else {
                s();
            }
        }
    }

    public boolean isShown() {
        return this.n && super.isShown();
    }

    public void setDrawingThreadType(int i) {
        this.b = i;
    }

    public long getCurrentTime() {
        return this.a != null ? this.a.h() : 0;
    }

    @SuppressLint({"NewApi"})
    public boolean isHardwareAccelerated() {
        return VERSION.SDK_INT >= 11 ? super.isHardwareAccelerated() : false;
    }

    public void setOnDanmakuClickListener(yy.a aVar) {
        this.i = aVar;
    }

    public void setOnDanmakuClickListener(yy.a aVar, float f, float f2) {
        this.i = aVar;
        this.j = f;
        this.k = f2;
    }

    public yy.a getOnDanmakuClickListener() {
        return this.i;
    }

    public float getXOff() {
        return this.j;
    }

    public float getYOff() {
        return this.k;
    }
}
