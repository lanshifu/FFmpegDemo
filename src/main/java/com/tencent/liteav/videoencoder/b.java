package com.tencent.liteav.videoencoder;

import android.opengl.GLES20;
import android.os.Bundle;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.liteav.basic.util.c;
import com.tencent.liteav.beauty.b.p;
import com.tencent.rtmp.TXLiveConstants;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import javax.microedition.khronos.egl.EGLContext;

/* compiled from: TXCVideoEncoder */
public class b extends com.tencent.liteav.basic.module.a {
    private static Integer r = Integer.valueOf(1);
    private static final String u = b.class.getSimpleName();
    private static int v = 0;
    private c a = null;
    private d b = null;
    private WeakReference<com.tencent.liteav.basic.d.a> c = null;
    private int d = 0;
    private int e = 2;
    private int f = 1;
    private Timer g = null;
    private TimerTask h = null;
    private LinkedList<Runnable> i = new LinkedList();
    private TXSVideoEncoderParam j;
    private float k = CropImageView.DEFAULT_ASPECT_RATIO;
    private float l = CropImageView.DEFAULT_ASPECT_RATIO;
    private float m = CropImageView.DEFAULT_ASPECT_RATIO;
    private int n = 0;
    private int o = 0;
    private com.tencent.liteav.basic.e.b p;
    private c q;
    private boolean s;
    private p t;

    /* compiled from: TXCVideoEncoder */
    static class a extends TimerTask {
        private WeakReference<b> a;

        public a(b bVar) {
            this.a = new WeakReference(bVar);
        }

        public void run() {
            if (this.a != null) {
                b bVar = (b) this.a.get();
                if (bVar != null) {
                    if (bVar.n < bVar.o) {
                        int[] a = com.tencent.liteav.basic.util.b.a();
                        b.k(bVar);
                        bVar.k = bVar.k + ((float) (a[0] / 10));
                        bVar.l = bVar.l + ((float) (a[1] / 10));
                        bVar.m = bVar.m + ((float) ((bVar.c() * 100) / ((long) bVar.j.fps)));
                        return;
                    }
                    if (com.tencent.liteav.basic.f.b.a().a(bVar.k / ((float) bVar.o), bVar.l / ((float) bVar.o), bVar.m / ((float) bVar.o)) && com.tencent.liteav.basic.f.b.a().c() != 0) {
                        bVar.f();
                    }
                    bVar.e();
                }
            }
        }
    }

    static /* synthetic */ int k(b bVar) {
        int i = bVar.n + 1;
        bVar.n = i;
        return i;
    }

    public b(int i) {
        this.e = i;
    }

    public int a(TXSVideoEncoderParam tXSVideoEncoderParam) {
        int start;
        this.j = tXSVideoEncoderParam;
        int c = tXSVideoEncoderParam.enableBlackList ? com.tencent.liteav.basic.f.b.a().c() : 2;
        if (this.e == 1 && c != 0) {
            this.a = new a();
            this.f = 1;
            a((int) TXLiveConstants.PUSH_EVT_START_VIDEO_ENCODER, "启动硬编", 1);
        } else if (this.e == 3 && tXSVideoEncoderParam.width == 720 && tXSVideoEncoderParam.height == 1280 && c != 0) {
            this.a = new a();
            this.f = 1;
            a((int) TXLiveConstants.PUSH_EVT_START_VIDEO_ENCODER, "启动硬编", 1);
        } else {
            this.a = new TXCSWVideoEncoder();
            this.f = 2;
            a((int) TXLiveConstants.PUSH_EVT_START_VIDEO_ENCODER, "启动软编", 2);
        }
        setStatusValue(4004, Long.valueOf((long) this.f));
        if (this.a != null) {
            if (this.b != null) {
                this.a.setListener(this.b);
            }
            if (this.d != 0) {
                this.a.setBitrate(this.d);
            }
            this.a.setID(getID());
            start = this.a.start(tXSVideoEncoderParam);
            if (start != 0) {
                return start;
            }
        }
        start = 10000002;
        if (this.e == 3) {
            this.k = CropImageView.DEFAULT_ASPECT_RATIO;
            this.l = CropImageView.DEFAULT_ASPECT_RATIO;
            this.m = CropImageView.DEFAULT_ASPECT_RATIO;
            this.n = 0;
            this.o = com.tencent.liteav.basic.f.b.a().f();
            d();
        }
        return start;
    }

    public void setID(String str) {
        super.setID(str);
        if (this.a != null) {
            this.a.setID(str);
        }
        setStatusValue(4004, Long.valueOf((long) this.f));
    }

    public EGLContext a(final int i, final int i2) {
        EGLContext eGLContext = null;
        if (this.s) {
            if (this.p != null) {
                eGLContext = this.p.c();
            }
            return eGLContext;
        }
        this.s = true;
        synchronized (r) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("CVGLThread");
            Integer num = r;
            r = Integer.valueOf(r.intValue() + 1);
            stringBuilder.append(num);
            this.q = new c(stringBuilder.toString());
        }
        final boolean[] zArr = new boolean[1];
        this.q.a(new Runnable() {
            public void run() {
                b.this.p = com.tencent.liteav.basic.e.b.a(null, null, null, i, i2);
                zArr[0] = b.this.p != null;
            }
        });
        if (zArr[0]) {
            return this.p.c();
        }
        return null;
    }

    /* Access modifiers changed, original: protected */
    public void a(Runnable runnable) {
        synchronized (this.i) {
            this.i.add(runnable);
        }
    }

    /* JADX WARNING: Missing block: B:8:0x0011, code skipped:
            if (r0 != null) goto L_0x0014;
     */
    /* JADX WARNING: Missing block: B:9:0x0013, code skipped:
            return false;
     */
    /* JADX WARNING: Missing block: B:10:0x0014, code skipped:
            r0.run();
     */
    /* JADX WARNING: Missing block: B:11:0x0018, code skipped:
            return true;
     */
    private boolean a(java.util.Queue<java.lang.Runnable> r3) {
        /*
        r2 = this;
        monitor-enter(r3);
        r0 = r3.isEmpty();	 Catch:{ all -> 0x0019 }
        r1 = 0;
        if (r0 == 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r3);	 Catch:{ all -> 0x0019 }
        return r1;
    L_0x000a:
        r0 = r3.poll();	 Catch:{ all -> 0x0019 }
        r0 = (java.lang.Runnable) r0;	 Catch:{ all -> 0x0019 }
        monitor-exit(r3);	 Catch:{ all -> 0x0019 }
        if (r0 != 0) goto L_0x0014;
    L_0x0013:
        return r1;
    L_0x0014:
        r0.run();
        r3 = 1;
        return r3;
    L_0x0019:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0019 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.videoencoder.b.a(java.util.Queue):boolean");
    }

    public long a(byte[] bArr, int i, int i2, int i3, long j) {
        if (this.p == null) {
            return -1;
        }
        final int i4 = i2;
        final int i5 = i3;
        final int i6 = i;
        final byte[] bArr2 = bArr;
        final long j2 = j;
        this.q.b(new Runnable() {
            public void run() {
                if (!(b.this.t != null && b.this.t.o() == i4 && b.this.t.p() == i5)) {
                    if (b.this.t != null) {
                        b.this.t.e();
                        b.this.t = null;
                    }
                    b.this.t = new p(i6);
                    if (b.this.t.c()) {
                        b.this.t.a(true);
                        b.this.t.a(i4, i5);
                    } else {
                        b.this.p.b();
                        b.this.p = null;
                        b.this.t = null;
                        return;
                    }
                }
                b.this.t.a(bArr2);
                GLES20.glViewport(0, 0, i4, i5);
                int r = b.this.t.r();
                GLES20.glFlush();
                b.this.a(r, b.this.j.width, b.this.j.height, j2);
            }
        });
        return 0;
    }

    public void a() {
        if (this.q != null) {
            final com.tencent.liteav.basic.e.b bVar = this.p;
            this.q.b(new Runnable() {
                public void run() {
                    b.this.i.clear();
                    if (b.this.a != null) {
                        b.this.a.stop();
                    }
                    if (b.this.t != null) {
                        b.this.t.e();
                        b.this.t = null;
                    }
                    if (bVar != null) {
                        bVar.b();
                    }
                }
            });
            this.q = null;
            this.p = null;
        } else {
            this.i.clear();
            if (this.a != null) {
                this.a.stop();
            }
        }
        if (this.e == 3) {
            this.k = CropImageView.DEFAULT_ASPECT_RATIO;
            this.l = CropImageView.DEFAULT_ASPECT_RATIO;
            this.m = CropImageView.DEFAULT_ASPECT_RATIO;
            this.n = 0;
            e();
        }
        this.b = null;
        this.d = 0;
    }

    public long a(int i, int i2, int i3, long j) {
        while (a(this.i)) {
        }
        return this.a != null ? this.a.pushVideoFrame(i, i2, i3, j) : 10000002;
    }

    public long b(int i, int i2, int i3, long j) {
        while (a(this.i)) {
        }
        return this.a != null ? this.a.pushVideoFrameSync(i, i2, i3, j) : 10000002;
    }

    public void b() {
        if (this.a != null) {
            this.a.signalEOSAndFlush();
        }
    }

    public void a(com.tencent.liteav.basic.d.a aVar) {
        this.c = new WeakReference(aVar);
    }

    public void a(d dVar) {
        this.b = dVar;
        a(new Runnable() {
            public void run() {
                if (b.this.a != null) {
                    b.this.a.setListener(b.this.b);
                }
            }
        });
    }

    public void a(int i) {
        this.d = i;
        a(new Runnable() {
            public void run() {
                if (b.this.a != null) {
                    b.this.a.setBitrate(b.this.d);
                }
            }
        });
    }

    public long c() {
        return this.a != null ? this.a.getRealFPS() : 0;
    }

    private void d() {
        if (this.h == null) {
            this.h = new a(this);
        }
        this.g = new Timer();
        this.g.schedule(this.h, 1000, 1000);
    }

    private void e() {
        if (this.g != null) {
            this.g.cancel();
            this.g = null;
        }
        if (this.h != null) {
            this.h = null;
        }
    }

    private void a(int i, String str) {
        if (this.c != null) {
            com.tencent.liteav.basic.d.a aVar = (com.tencent.liteav.basic.d.a) this.c.get();
            if (aVar != null) {
                Bundle bundle = new Bundle();
                bundle.putInt("EVT_ID", i);
                bundle.putLong("EVT_TIME", TXCTimeUtil.getTimeTick());
                bundle.putCharSequence(TXLiveConstants.EVT_DESCRIPTION, str);
                aVar.onNotifyEvent(i, bundle);
            }
        }
    }

    private void a(int i, String str, int i2) {
        if (this.c != null) {
            com.tencent.liteav.basic.d.a aVar = (com.tencent.liteav.basic.d.a) this.c.get();
            if (aVar != null) {
                Bundle bundle = new Bundle();
                bundle.putInt("EVT_ID", i);
                bundle.putLong("EVT_TIME", TXCTimeUtil.getTimeTick());
                bundle.putCharSequence(TXLiveConstants.EVT_DESCRIPTION, str);
                bundle.putInt("EVT_PARAM1", i2);
                aVar.onNotifyEvent(i, bundle);
            }
        }
    }

    private void f() {
        a(new Runnable() {
            public void run() {
                b.this.a((int) TXLiveConstants.PUSH_WARNING_VIDEO_ENCODE_SW_SWITCH_HW, "软编切硬编");
                if (b.this.a != null) {
                    b.this.a.setListener(null);
                    b.this.a.stop();
                }
                b.this.a = new a();
                b.this.f = 1;
                b.this.setStatusValue(4004, Long.valueOf((long) b.this.f));
                b.this.a.start(b.this.j);
                if (b.this.b != null) {
                    b.this.a.setListener(b.this.b);
                }
                if (b.this.d != 0) {
                    b.this.a.setBitrate(b.this.d);
                }
                b.this.a.setID(b.this.getID());
            }
        });
        TXCLog.w("TXCVideoEncoder", "switchSWToHW");
    }
}
