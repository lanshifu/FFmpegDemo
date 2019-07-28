package defpackage;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.za.a;
import java.util.LinkedList;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import tv.cjump.jni.DeviceUtils;

/* compiled from: DrawHandler */
/* renamed from: yv */
public class yv extends Handler {
    private boolean A;
    private boolean B = (1 ^ DeviceUtils.f());
    private boolean C;
    public za a;
    private DanmakuContext b;
    private b c;
    private long d = 0;
    private boolean e = true;
    private long f;
    private boolean g;
    private a h;
    private zh i = new zh();
    private zy j;
    private yz k;
    private boolean l = true;
    private zd m;
    private final b n = new b();
    private LinkedList<Long> o = new LinkedList();
    private zb p;
    private boolean q;
    private long r = 30;
    private long s = 60;
    private long t = 16;
    private long u;
    private long v;
    private boolean w;
    private long x;
    private long y;
    private boolean z;

    /* compiled from: DrawHandler */
    /* renamed from: yv$1 */
    class 1 implements Runnable {
        1() {
        }

        public void run() {
            yv.this.d = 0;
            yv.this.g = true;
            if (yv.this.h != null) {
                yv.this.h.prepared();
            }
        }
    }

    /* compiled from: DrawHandler */
    /* renamed from: yv$a */
    public interface a {
        void danmakuShown(zf zfVar);

        void drawingFinished();

        void prepared();

        void updateTimer(zh zhVar);
    }

    @TargetApi(16)
    /* compiled from: DrawHandler */
    /* renamed from: yv$b */
    private class b implements FrameCallback {
        private b() {
        }

        /* synthetic */ b(yv yvVar, 1 1) {
            this();
        }

        public void doFrame(long j) {
            yv.this.sendEmptyMessage(2);
        }
    }

    public yv(Looper looper, yz yzVar, boolean z) {
        super(looper);
        a(yzVar);
        if (z) {
            a(null);
        } else {
            c(false);
        }
        this.l = z;
    }

    private void a(yz yzVar) {
        this.k = yzVar;
    }

    public void a(boolean z) {
        this.B = z;
    }

    public void b(boolean z) {
        this.C = z;
    }

    public void a(DanmakuContext danmakuContext) {
        this.b = danmakuContext;
    }

    public void a(zy zyVar) {
        this.j = zyVar;
        zh timer = zyVar.getTimer();
        if (timer != null) {
            this.i = timer;
        }
    }

    public void a(a aVar) {
        this.h = aVar;
    }

    public void a() {
        this.e = true;
        sendEmptyMessage(6);
    }

    public boolean b() {
        return this.e;
    }

    /* JADX WARNING: Removed duplicated region for block: B:125:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Missing block: B:45:0x00e6, code skipped:
            removeMessages(3);
            removeMessages(2);
     */
    /* JADX WARNING: Missing block: B:46:0x00ee, code skipped:
            if (r11.a == null) goto L_0x00f5;
     */
    /* JADX WARNING: Missing block: B:47:0x00f0, code skipped:
            r11.a.a(2);
     */
    /* JADX WARNING: Missing block: B:49:0x00f6, code skipped:
            if (r0 != 6) goto L_0x00fc;
     */
    /* JADX WARNING: Missing block: B:50:0x00f8, code skipped:
            removeCallbacksAndMessages(null);
     */
    /* JADX WARNING: Missing block: B:51:0x00fc, code skipped:
            r11.e = true;
            n();
            r11.d = r11.i.a;
     */
    /* JADX WARNING: Missing block: B:52:0x0109, code skipped:
            if (r11.q == false) goto L_0x0111;
     */
    /* JADX WARNING: Missing block: B:53:0x010b, code skipped:
            q();
            j();
     */
    /* JADX WARNING: Missing block: B:55:0x0113, code skipped:
            if (r11.c == null) goto L_0x0124;
     */
    /* JADX WARNING: Missing block: B:57:0x0119, code skipped:
            if (android.os.Build.VERSION.SDK_INT < 16) goto L_0x0124;
     */
    /* JADX WARNING: Missing block: B:58:0x011b, code skipped:
            android.view.Choreographer.getInstance().removeFrameCallback(r11.c);
     */
    /* JADX WARNING: Missing block: B:59:0x0124, code skipped:
            if (r0 != 6) goto L_?;
     */
    /* JADX WARNING: Missing block: B:61:0x0128, code skipped:
            if (r11.a == null) goto L_0x012f;
     */
    /* JADX WARNING: Missing block: B:62:0x012a, code skipped:
            r11.a.b();
     */
    /* JADX WARNING: Missing block: B:64:0x0131, code skipped:
            if (r11.j == null) goto L_0x0138;
     */
    /* JADX WARNING: Missing block: B:65:0x0133, code skipped:
            r11.j.release();
     */
    /* JADX WARNING: Missing block: B:67:0x0140, code skipped:
            if (getLooper() == android.os.Looper.getMainLooper()) goto L_?;
     */
    /* JADX WARNING: Missing block: B:68:0x0142, code skipped:
            getLooper().quit();
     */
    /* JADX WARNING: Missing block: B:84:0x018f, code skipped:
            r7 = (java.lang.Long) r12.obj;
     */
    /* JADX WARNING: Missing block: B:85:0x0193, code skipped:
            if (r7 == null) goto L_0x019c;
     */
    /* JADX WARNING: Missing block: B:86:0x0195, code skipped:
            r11.d = r7.longValue();
     */
    /* JADX WARNING: Missing block: B:87:0x019c, code skipped:
            r11.d = 0;
     */
    /* JADX WARNING: Missing block: B:89:0x01a1, code skipped:
            if (r0 != 4) goto L_0x01dd;
     */
    /* JADX WARNING: Missing block: B:90:0x01a3, code skipped:
            r11.e = true;
            j();
            r12 = (java.lang.Long) r12.obj;
            r11.f -= r12.longValue() - r11.i.a;
            r11.i.a(r12.longValue());
            r11.b.r.d();
     */
    /* JADX WARNING: Missing block: B:91:0x01cc, code skipped:
            if (r11.a == null) goto L_0x01d7;
     */
    /* JADX WARNING: Missing block: B:92:0x01ce, code skipped:
            r11.a.a(r12.longValue());
     */
    /* JADX WARNING: Missing block: B:93:0x01d7, code skipped:
            r11.d = r12.longValue();
     */
    /* JADX WARNING: Missing block: B:94:0x01dd, code skipped:
            removeMessages(7);
            r11.e = false;
     */
    /* JADX WARNING: Missing block: B:95:0x01e5, code skipped:
            if (r11.g == false) goto L_0x021b;
     */
    /* JADX WARNING: Missing block: B:96:0x01e7, code skipped:
            r11.n.a();
            r11.o.clear();
            r11.f = aaf.a() - r11.d;
            r11.i.a(r11.d);
            removeMessages(3);
            sendEmptyMessage(2);
            r11.a.a();
            q();
            r11.w = false;
     */
    /* JADX WARNING: Missing block: B:97:0x0213, code skipped:
            if (r11.a == null) goto L_?;
     */
    /* JADX WARNING: Missing block: B:98:0x0215, code skipped:
            r11.a.a(1);
     */
    /* JADX WARNING: Missing block: B:99:0x021b, code skipped:
            sendEmptyMessageDelayed(3, 100);
     */
    /* JADX WARNING: Missing block: B:100:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:112:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:113:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:114:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:121:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:122:?, code skipped:
            return;
     */
    public void handleMessage(android.os.Message r12) {
        /*
        r11 = this;
        r0 = r12.what;
        r1 = 100;
        r3 = 3;
        r4 = 2;
        r5 = 0;
        r6 = 1;
        switch(r0) {
            case 1: goto L_0x018f;
            case 2: goto L_0x016e;
            case 3: goto L_0x01dd;
            case 4: goto L_0x01a0;
            case 5: goto L_0x014b;
            case 6: goto L_0x00f5;
            case 7: goto L_0x00e6;
            case 8: goto L_0x00a1;
            case 9: goto L_0x006d;
            case 10: goto L_0x0043;
            case 11: goto L_0x003e;
            case 12: goto L_0x0027;
            case 13: goto L_0x0018;
            case 14: goto L_0x000d;
            default: goto L_0x000b;
        };
    L_0x000b:
        goto L_0x021e;
    L_0x000d:
        r12 = r11.a;
        if (r12 == 0) goto L_0x021e;
    L_0x0011:
        r12 = r11.a;
        r12.h();
        goto L_0x021e;
    L_0x0018:
        r12 = r11.a;
        if (r12 == 0) goto L_0x021e;
    L_0x001c:
        r12 = r11.a;
        r0 = r11.h();
        r12.c(r0);
        goto L_0x021e;
    L_0x0027:
        r12 = r11.e;
        if (r12 == 0) goto L_0x021e;
    L_0x002b:
        r12 = r11.k;
        if (r12 == 0) goto L_0x021e;
    L_0x002f:
        r12 = r11.a;
        r12.e();
        r12 = r11.k;
        r12.b();
        r11.q();
        goto L_0x021e;
    L_0x003e:
        r11.q();
        goto L_0x021e;
    L_0x0043:
        r0 = r11.b;
        r0 = r0.t;
        r1 = r11.b;
        r0.a(r1);
        r12 = r12.obj;
        r12 = (java.lang.Boolean) r12;
        if (r12 == 0) goto L_0x021e;
    L_0x0052:
        r12 = r12.booleanValue();
        if (r12 == 0) goto L_0x021e;
    L_0x0058:
        r12 = r11.b;
        r12 = r12.r;
        r12.d();
        r12 = r11.b;
        r12 = r12.r;
        r12.c();
        r12 = r11.a;
        r12.f();
        goto L_0x021e;
    L_0x006d:
        r11.l = r5;
        r1 = r11.k;
        if (r1 == 0) goto L_0x0078;
    L_0x0073:
        r1 = r11.k;
        r1.c();
    L_0x0078:
        r1 = r11.a;
        if (r1 == 0) goto L_0x0086;
    L_0x007c:
        r1 = r11.a;
        r1.e();
        r1 = r11.a;
        r1.g();
    L_0x0086:
        r12 = r12.obj;
        r12 = (java.lang.Boolean) r12;
        r1 = r12.booleanValue();
        if (r1 == 0) goto L_0x0099;
    L_0x0090:
        r1 = r11.a;
        if (r1 == 0) goto L_0x0099;
    L_0x0094:
        r1 = r11.a;
        r1.b();
    L_0x0099:
        r12 = r12.booleanValue();
        if (r12 != 0) goto L_0x00e6;
    L_0x009f:
        goto L_0x021e;
    L_0x00a1:
        r11.l = r6;
        r7 = r12.obj;
        r7 = (java.lang.Long) r7;
        r8 = r11.a;
        if (r8 == 0) goto L_0x00d1;
    L_0x00ab:
        if (r7 != 0) goto L_0x00bc;
    L_0x00ad:
        r7 = r11.i;
        r8 = r11.h();
        r7.a(r8);
        r7 = r11.a;
        r7.e();
        goto L_0x00d1;
    L_0x00bc:
        r8 = r11.a;
        r8.a();
        r8 = r11.a;
        r9 = r7.longValue();
        r8.a(r9);
        r7 = r11.a;
        r7.e();
        r7 = 1;
        goto L_0x00d2;
    L_0x00d1:
        r7 = 0;
    L_0x00d2:
        r8 = r11.e;
        if (r8 == 0) goto L_0x00df;
    L_0x00d6:
        r8 = r11.k;
        if (r8 == 0) goto L_0x00df;
    L_0x00da:
        r8 = r11.k;
        r8.b();
    L_0x00df:
        r11.q();
        if (r7 != 0) goto L_0x018f;
    L_0x00e4:
        goto L_0x021e;
    L_0x00e6:
        r11.removeMessages(r3);
        r11.removeMessages(r4);
        r12 = r11.a;
        if (r12 == 0) goto L_0x00f5;
    L_0x00f0:
        r12 = r11.a;
        r12.a(r4);
    L_0x00f5:
        r12 = 6;
        if (r0 != r12) goto L_0x00fc;
    L_0x00f8:
        r1 = 0;
        r11.removeCallbacksAndMessages(r1);
    L_0x00fc:
        r11.e = r6;
        r11.n();
        r1 = r11.i;
        r1 = r1.a;
        r11.d = r1;
        r1 = r11.q;
        if (r1 == 0) goto L_0x0111;
    L_0x010b:
        r11.q();
        r11.j();
    L_0x0111:
        r1 = r11.c;
        if (r1 == 0) goto L_0x0124;
    L_0x0115:
        r1 = android.os.Build.VERSION.SDK_INT;
        r2 = 16;
        if (r1 < r2) goto L_0x0124;
    L_0x011b:
        r1 = android.view.Choreographer.getInstance();
        r2 = r11.c;
        r1.removeFrameCallback(r2);
    L_0x0124:
        if (r0 != r12) goto L_0x021e;
    L_0x0126:
        r12 = r11.a;
        if (r12 == 0) goto L_0x012f;
    L_0x012a:
        r12 = r11.a;
        r12.b();
    L_0x012f:
        r12 = r11.j;
        if (r12 == 0) goto L_0x0138;
    L_0x0133:
        r12 = r11.j;
        r12.release();
    L_0x0138:
        r12 = r11.getLooper();
        r0 = android.os.Looper.getMainLooper();
        if (r12 == r0) goto L_0x021e;
    L_0x0142:
        r12 = r11.getLooper();
        r12.quit();
        goto L_0x021e;
    L_0x014b:
        r3 = aaf.a();
        r11.f = r3;
        r12 = r11.j;
        if (r12 == 0) goto L_0x0168;
    L_0x0155:
        r12 = r11.k;
        r12 = r12.a();
        if (r12 != 0) goto L_0x015e;
    L_0x015d:
        goto L_0x0168;
    L_0x015e:
        r12 = new yv$1;
        r12.<init>();
        r11.a(r12);
        goto L_0x021e;
    L_0x0168:
        r12 = 5;
        r11.sendEmptyMessageDelayed(r12, r1);
        goto L_0x021e;
    L_0x016e:
        r12 = r11.b;
        r12 = r12.v;
        if (r12 != 0) goto L_0x0179;
    L_0x0174:
        r11.m();
        goto L_0x021e;
    L_0x0179:
        r12 = r11.b;
        r12 = r12.v;
        if (r12 != r6) goto L_0x0184;
    L_0x017f:
        r11.l();
        goto L_0x021e;
    L_0x0184:
        r12 = r11.b;
        r12 = r12.v;
        if (r12 != r4) goto L_0x021e;
    L_0x018a:
        r11.k();
        goto L_0x021e;
    L_0x018f:
        r7 = r12.obj;
        r7 = (java.lang.Long) r7;
        if (r7 == 0) goto L_0x019c;
    L_0x0195:
        r7 = r7.longValue();
        r11.d = r7;
        goto L_0x01a0;
    L_0x019c:
        r7 = 0;
        r11.d = r7;
    L_0x01a0:
        r7 = 4;
        if (r0 != r7) goto L_0x01dd;
    L_0x01a3:
        r11.e = r6;
        r11.j();
        r12 = r12.obj;
        r12 = (java.lang.Long) r12;
        r7 = r12.longValue();
        r0 = r11.i;
        r9 = r0.a;
        r7 = r7 - r9;
        r9 = r11.f;
        r9 = r9 - r7;
        r11.f = r9;
        r0 = r11.i;
        r7 = r12.longValue();
        r0.a(r7);
        r0 = r11.b;
        r0 = r0.r;
        r0.d();
        r0 = r11.a;
        if (r0 == 0) goto L_0x01d7;
    L_0x01ce:
        r0 = r11.a;
        r7 = r12.longValue();
        r0.a(r7);
    L_0x01d7:
        r7 = r12.longValue();
        r11.d = r7;
    L_0x01dd:
        r12 = 7;
        r11.removeMessages(r12);
        r11.e = r5;
        r12 = r11.g;
        if (r12 == 0) goto L_0x021b;
    L_0x01e7:
        r12 = r11.n;
        r12.a();
        r12 = r11.o;
        r12.clear();
        r0 = aaf.a();
        r7 = r11.d;
        r0 = r0 - r7;
        r11.f = r0;
        r12 = r11.i;
        r0 = r11.d;
        r12.a(r0);
        r11.removeMessages(r3);
        r11.sendEmptyMessage(r4);
        r12 = r11.a;
        r12.a();
        r11.q();
        r11.w = r5;
        r12 = r11.a;
        if (r12 == 0) goto L_0x021e;
    L_0x0215:
        r12 = r11.a;
        r12.a(r6);
        goto L_0x021e;
    L_0x021b:
        r11.sendEmptyMessageDelayed(r3, r1);
    L_0x021e:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yv.handleMessage(android.os.Message):void");
    }

    private synchronized void j() {
        zb zbVar = this.p;
        this.p = null;
        if (zbVar != null) {
            synchronized (this.a) {
                this.a.notifyAll();
            }
            zbVar.a();
            try {
                zbVar.join(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return;
    }

    private void k() {
        if (!this.e) {
            long a = a(aaf.a());
            if (a >= 0 || this.C) {
                a = this.k.b();
                removeMessages(2);
                if (a > this.s) {
                    this.i.b(a);
                    this.o.clear();
                }
                if (this.l) {
                    if (this.n.p && this.B) {
                        long j = this.n.o - this.i.a;
                        if (j > 500) {
                            b(j - 10);
                            return;
                        }
                    }
                    if (a < this.t) {
                        sendEmptyMessageDelayed(2, this.t - a);
                        return;
                    } else {
                        sendEmptyMessage(2);
                        return;
                    }
                }
                b(10000000);
                return;
            }
            removeMessages(2);
            sendEmptyMessageDelayed(2, 60 - a);
        }
    }

    private void l() {
        if (this.p == null) {
            this.p = new zb("DFM Update") {
                public void run() {
                    long a = aaf.a();
                    while (!b() && !yv.this.e) {
                        long a2 = aaf.a();
                        if (yv.this.t - (aaf.a() - a) <= 1 || yv.this.C) {
                            a = yv.this.a(a2);
                            if (a >= 0 || yv.this.C) {
                                a = yv.this.k.b();
                                if (a > yv.this.s) {
                                    yv.this.i.b(a);
                                    yv.this.o.clear();
                                }
                                if (!yv.this.l) {
                                    yv.this.b(10000000);
                                } else if (yv.this.n.p && yv.this.B) {
                                    a = yv.this.n.o - yv.this.i.a;
                                    if (a > 500) {
                                        yv.this.q();
                                        yv.this.b(a - 10);
                                    }
                                }
                            } else {
                                aaf.a(60 - a);
                            }
                            a = a2;
                        } else {
                            aaf.a(1);
                        }
                    }
                }
            };
            this.p.start();
        }
    }

    @TargetApi(16)
    private void m() {
        if (!this.e) {
            Choreographer.getInstance().postFrameCallback(this.c);
            if (a(aaf.a()) < 0) {
                removeMessages(2);
                return;
            }
            long b = this.k.b();
            removeMessages(2);
            if (b > this.s) {
                this.i.b(b);
                this.o.clear();
            }
            if (this.l) {
                if (this.n.p && this.B) {
                    long j = this.n.o - this.i.a;
                    if (j > 500) {
                        b(j - 10);
                        return;
                    }
                }
                return;
            }
            b(10000000);
        }
    }

    private final long a(long j) {
        long j2 = 0;
        if (this.w || this.z) {
            return 0;
        }
        this.z = true;
        j -= this.f;
        if (this.C) {
            if (this.h != null) {
                this.h.updateTimer(this.i);
                j2 = this.i.a();
            }
        } else if (!this.l || this.n.p || this.A) {
            this.i.a(j);
            this.y = 0;
            if (this.h != null) {
                this.h.updateTimer(this.i);
            }
        } else {
            j -= this.i.a;
            long max = Math.max(this.t, r());
            if (j > 2000 || this.n.m > this.r || max > this.r) {
                long j3 = j;
                j = 0;
                j2 = j3;
            } else {
                long min = Math.min(this.r, Math.max(this.t, max + (j / this.t)));
                long j4 = min - this.v;
                if (j4 > 3 && j4 < 8 && this.v >= this.t && this.v <= this.r) {
                    min = this.v;
                }
                j -= min;
                this.v = min;
                j2 = min;
            }
            this.y = j;
            this.i.b(j2);
            if (this.h != null) {
                this.h.updateTimer(this.i);
            }
        }
        this.z = false;
        return j2;
    }

    private void n() {
        if (this.A) {
            a(aaf.a());
        }
    }

    private void o() {
        this.r = Math.max(33, (long) (((float) 16) * 2.5f));
        this.s = (long) (((float) this.r) * 2.5f);
        this.t = Math.max(16, 15);
        this.u = this.t + 3;
    }

    private void a(final Runnable runnable) {
        if (this.a == null) {
            this.a = a(this.k.d(), this.i, this.k.getContext(), this.k.getViewWidth(), this.k.getViewHeight(), this.k.isHardwareAccelerated(), new a() {
                public void a() {
                    yv.this.o();
                    runnable.run();
                }

                public void a(zf zfVar) {
                    if (!zfVar.f()) {
                        long s = zfVar.s() - yv.this.h();
                        if (s < yv.this.b.t.d && (yv.this.A || yv.this.n.p)) {
                            yv.this.q();
                        } else if (s > 0 && s <= yv.this.b.t.d) {
                            yv.this.sendEmptyMessageDelayed(11, s);
                        }
                    }
                }

                public void b(zf zfVar) {
                    if (yv.this.h != null) {
                        yv.this.h.danmakuShown(zfVar);
                    }
                }

                public void b() {
                    if (yv.this.h != null) {
                        yv.this.h.drawingFinished();
                    }
                }

                public void c() {
                    yv.this.p();
                }
            });
            return;
        }
        runnable.run();
    }

    public boolean c() {
        return this.g;
    }

    private za a(boolean z, zh zhVar, Context context, int i, int i2, boolean z2, a aVar) {
        this.m = this.b.b();
        this.m.a(i, i2);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.m.a(displayMetrics.density, displayMetrics.densityDpi, displayMetrics.scaledDensity);
        this.m.b(this.b.c);
        this.m.b(z2);
        za ytVar = z ? new yt(zhVar, this.b, aVar) : new yx(zhVar, this.b, aVar);
        ytVar.b(this.j);
        ytVar.c();
        obtainMessage(10, Boolean.valueOf(false)).sendToTarget();
        return ytVar;
    }

    public void a(zf zfVar) {
        if (this.a != null) {
            zfVar.F = this.b.r;
            zfVar.a(this.i);
            this.a.a(zfVar);
            obtainMessage(11).sendToTarget();
        }
    }

    public void d() {
        removeMessages(7);
        sendEmptyMessage(3);
    }

    public void e() {
        boolean z = false;
        this.g = false;
        if (VERSION.SDK_INT < 16 && this.b.v == (byte) 0) {
            this.b.v = (byte) 2;
        }
        if (this.b.v == (byte) 0) {
            this.c = new b(this, null);
        }
        if (this.b.v == (byte) 1) {
            z = true;
        }
        this.q = z;
        sendEmptyMessage(5);
    }

    public void f() {
        removeMessages(3);
        n();
        sendEmptyMessage(7);
    }

    public void a(Long l) {
        if (!this.l) {
            this.l = true;
            removeMessages(8);
            removeMessages(9);
            obtainMessage(8, l).sendToTarget();
        }
    }

    public long c(boolean z) {
        if (!this.l) {
            return this.i.a;
        }
        this.l = false;
        removeMessages(8);
        removeMessages(9);
        obtainMessage(9, Boolean.valueOf(z)).sendToTarget();
        return this.i.a;
    }

    public b a(Canvas canvas) {
        if (this.a == null) {
            return this.n;
        }
        if (!this.A) {
            zc zcVar = this.b.m;
            if (zcVar != null) {
                boolean d = zcVar.d();
                if (d || !this.e) {
                    int b = zcVar.b();
                    if (b == 2) {
                        long j = this.i.a;
                        long a = zcVar.a();
                        long j2 = a - j;
                        if (Math.abs(j2) > zcVar.c()) {
                            if (d && this.e) {
                                d();
                            }
                            this.a.a(j, a, j2);
                            this.i.a(a);
                            this.f -= j2;
                            this.y = 0;
                        }
                    } else if (b == 1 && d && !this.e) {
                        f();
                    }
                }
            }
        }
        this.m.a((Object) canvas);
        this.n.a(this.a.a(this.m));
        s();
        return this.n;
    }

    private void p() {
        if (this.e && this.l) {
            removeMessages(12);
            sendEmptyMessageDelayed(12, 100);
        }
    }

    private void q() {
        if (this.A) {
            if (this.a != null) {
                this.a.e();
            }
            if (this.q) {
                synchronized (this) {
                    this.o.clear();
                }
                synchronized (this.a) {
                    this.a.notifyAll();
                }
            } else {
                this.o.clear();
                removeMessages(2);
                sendEmptyMessage(2);
            }
            this.A = false;
        }
    }

    private void b(long j) {
        if (!b() && c() && !this.w) {
            this.n.q = aaf.a();
            this.A = true;
            if (this.q) {
                if (this.p != null) {
                    try {
                        synchronized (this.a) {
                            if (j == 10000000) {
                                this.a.wait();
                            } else {
                                this.a.wait(j);
                            }
                            sendEmptyMessage(11);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                    }
                }
            } else if (j == 10000000) {
                removeMessages(11);
                removeMessages(2);
            } else {
                removeMessages(11);
                removeMessages(2);
                sendEmptyMessageDelayed(11, j);
            }
        }
    }

    /* JADX WARNING: Missing block: B:16:0x0031, code skipped:
            return 0;
     */
    private synchronized long r() {
        /*
        r6 = this;
        monitor-enter(r6);
        r0 = r6.o;	 Catch:{ all -> 0x0032 }
        r0 = r0.size();	 Catch:{ all -> 0x0032 }
        r1 = 0;
        if (r0 > 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r6);
        return r1;
    L_0x000d:
        r3 = r6.o;	 Catch:{ all -> 0x0032 }
        r3 = r3.peekFirst();	 Catch:{ all -> 0x0032 }
        r3 = (java.lang.Long) r3;	 Catch:{ all -> 0x0032 }
        r4 = r6.o;	 Catch:{ all -> 0x0032 }
        r4 = r4.peekLast();	 Catch:{ all -> 0x0032 }
        r4 = (java.lang.Long) r4;	 Catch:{ all -> 0x0032 }
        if (r3 == 0) goto L_0x0030;
    L_0x001f:
        if (r4 != 0) goto L_0x0022;
    L_0x0021:
        goto L_0x0030;
    L_0x0022:
        r1 = r4.longValue();	 Catch:{ all -> 0x0032 }
        r3 = r3.longValue();	 Catch:{ all -> 0x0032 }
        r5 = 0;
        r1 = r1 - r3;
        r3 = (long) r0;	 Catch:{ all -> 0x0032 }
        r1 = r1 / r3;
        monitor-exit(r6);
        return r1;
    L_0x0030:
        monitor-exit(r6);
        return r1;
    L_0x0032:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yv.r():long");
    }

    private synchronized void s() {
        this.o.addLast(Long.valueOf(aaf.a()));
        if (this.o.size() > CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION) {
            this.o.removeFirst();
        }
    }

    public void a(int i, int i2) {
        if (this.m != null) {
            if (!(this.m.e() == i && this.m.f() == i2)) {
                this.m.a(i, i2);
                obtainMessage(10, Boolean.valueOf(true)).sendToTarget();
            }
        }
    }

    public zn g() {
        return this.a != null ? this.a.b(h()) : null;
    }

    public long h() {
        if (!this.g) {
            return 0;
        }
        if (this.w) {
            return this.x;
        }
        if (this.e || !this.A) {
            return this.i.a - this.y;
        }
        return aaf.a() - this.f;
    }

    public DanmakuContext i() {
        return this.b;
    }
}
