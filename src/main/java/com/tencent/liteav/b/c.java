package com.tencent.liteav.b;

import android.content.Context;
import android.opengl.EGLContext;
import android.view.Surface;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.beauty.d;
import com.tencent.liteav.d.e;
import com.tencent.liteav.d.g;
import com.tencent.liteav.e.m;
import com.tencent.liteav.e.o;
import com.tencent.ugc.TXRecordCommon;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: TXCombineDecAndRender */
public class c {
    private final m A = new m() {
        public void a(int i, int i2) {
        }

        public void a(boolean z) {
        }

        public void a(Surface surface) {
            TXCLog.i("TXCombineDecAndRender", "mVideoRenderCallback2 onSurfaceTextureAvailable");
            c.this.d.a(surface);
            c.this.p = new d(c.this.b, true);
        }

        public void b(Surface surface) {
            TXCLog.i("TXCombineDecAndRender", "mVideoRenderCallback2 onSurfaceTextureDestroy");
            if (c.this.p != null) {
                c.this.p.a();
                c.this.p = null;
            }
        }

        public int a(int i, float[] fArr, e eVar) {
            if (c.this.l) {
                TXCLog.i("TXCombineDecAndRender", "mVideoRenderCallback2 mDecodeVideoEnd, ignore");
                return 0;
            } else if (c.this.a(eVar, false)) {
                TXCLog.i("TXCombineDecAndRender", "mVideoRenderCallback2 onTextureProcess, end frame");
                return 0;
            } else if (c.this.w == null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("mVideoRenderCallback2 onTextureProcess, mCurRenderFrame is null, frame pts = ");
                stringBuilder.append(eVar.e());
                TXCLog.i("TXCombineDecAndRender", stringBuilder.toString());
                c.this.w = eVar;
                c.this.p.a(fArr);
                c.this.v = c.this.p.a(i, eVar.m(), eVar.n(), eVar.h(), 4, 0);
                return 0;
            } else {
                c.this.p.a(fArr);
                i = c.this.p.a(i, eVar.m(), eVar.n(), eVar.h(), 4, 0);
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("mVideoRenderCallback2 onTextureProcess, mCurRenderFrame is first pts = ");
                stringBuilder2.append(c.this.w.e());
                stringBuilder2.append(", process frame pts = ");
                stringBuilder2.append(eVar.e());
                TXCLog.i("TXCombineDecAndRender", stringBuilder2.toString());
                c.this.a(c.this.v, c.this.w, i, eVar);
                return 0;
            }
        }
    };
    private o B = new o() {
        public void a(EGLContext eGLContext) {
            TXCLog.d("TXCombineDecAndRender", "OnContextListener onContext");
            if (c.this.y != null) {
                c.this.y.a(eGLContext);
            }
            c.this.c.g();
            c.this.c.a(c.this.C);
            c.this.d.g();
            c.this.d.a(c.this.D);
            c.this.j.start();
        }
    };
    private com.tencent.liteav.b.a.a C = new com.tencent.liteav.b.a.a() {
        public void b(e eVar) {
            if (!c.this.n) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Video1 frame put one:");
                stringBuilder.append(eVar.e());
                stringBuilder.append(",VideoBlockingQueue size:");
                stringBuilder.append(c.this.f.size());
                TXCLog.i("TXCombineDecAndRender", stringBuilder.toString());
                try {
                    c.this.f.put(eVar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (c.this.e != null) {
                    c.this.e.a(eVar, 0);
                }
            }
        }

        public void a(e eVar) {
            if (!c.this.n) {
                if (c.this.g.size() > 3) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Audio1 frame put one:");
                stringBuilder.append(eVar.e());
                stringBuilder.append(", flag = ");
                stringBuilder.append(eVar.f());
                stringBuilder.append(", AudioBlockingQueue size:");
                stringBuilder.append(c.this.g.size());
                TXCLog.i("TXCombineDecAndRender", stringBuilder.toString());
                try {
                    c.this.g.put(eVar);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                c.this.e();
            }
        }
    };
    private com.tencent.liteav.b.a.a D = new com.tencent.liteav.b.a.a() {
        public void b(e eVar) {
            if (!c.this.n) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Video2 frame put one:");
                stringBuilder.append(eVar.e());
                stringBuilder.append(", flag = ");
                stringBuilder.append(eVar.f());
                stringBuilder.append(",VideoBlockingQueue2 size:");
                stringBuilder.append(c.this.h.size());
                TXCLog.i("TXCombineDecAndRender", stringBuilder.toString());
                try {
                    c.this.h.put(eVar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (c.this.e != null) {
                    c.this.e.a(eVar, 1);
                }
            }
        }

        public void a(e eVar) {
            if (!c.this.n) {
                if (c.this.i.size() > 3) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Audio2 frame put one:");
                stringBuilder.append(eVar.e());
                stringBuilder.append(", flag = ");
                stringBuilder.append(eVar.f());
                stringBuilder.append(",AudioBlockingQueue2 size:");
                stringBuilder.append(c.this.i.size());
                TXCLog.i("TXCombineDecAndRender", stringBuilder.toString());
                try {
                    c.this.i.put(eVar);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                c.this.e();
            }
        }
    };
    private final String a = "TXCombineDecAndRender";
    private Context b;
    private h c;
    private h d;
    private i e;
    private ArrayBlockingQueue<e> f;
    private ArrayBlockingQueue<e> g;
    private ArrayBlockingQueue<e> h;
    private ArrayBlockingQueue<e> i;
    private a j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private d o;
    private d p;
    private e q;
    private b r;
    private int s;
    private int t;
    private LinkedBlockingQueue<e> u;
    private int v = -1;
    private e w;
    private boolean x;
    private com.tencent.liteav.b.a.c y;
    private final m z = new m() {
        public void a(int i, int i2) {
        }

        public void a(boolean z) {
        }

        public void a(Surface surface) {
            TXCLog.i("TXCombineDecAndRender", "mVideoRenderCallback onSurfaceTextureAvailable");
            c.this.c.a(surface);
            c.this.o = new d(c.this.b, true);
        }

        public void b(Surface surface) {
            TXCLog.i("TXCombineDecAndRender", "mVideoRenderCallback onSurfaceTextureDestroy");
            if (c.this.o != null) {
                c.this.o.a();
                c.this.o = null;
            }
            if (c.this.q != null) {
                c.this.q.a();
            }
        }

        public int a(int i, float[] fArr, e eVar) {
            if (c.this.l) {
                TXCLog.i("TXCombineDecAndRender", "mVideoRenderCallback mDecodeVideoEnd, ignore");
                return 0;
            } else if (c.this.a(eVar, false)) {
                TXCLog.i("TXCombineDecAndRender", "mVideoRenderCallback onTextureProcess, end frame");
                return 0;
            } else if (c.this.w == null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("mVideoRenderCallback onTextureProcess, mCurRenderFrame is null, frame pts = ");
                stringBuilder.append(eVar.e());
                TXCLog.i("TXCombineDecAndRender", stringBuilder.toString());
                c.this.w = eVar;
                c.this.o.a(fArr);
                c.this.v = c.this.o.a(i, eVar.m(), eVar.n(), eVar.h(), 4, 0);
                return 0;
            } else {
                c.this.o.a(fArr);
                i = c.this.o.a(i, eVar.m(), eVar.n(), eVar.h(), 4, 0);
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("mVideoRenderCallback onTextureProcess, mCurRenderFrame is second pts = ");
                stringBuilder2.append(c.this.w.e());
                stringBuilder2.append(", process frame pts = ");
                stringBuilder2.append(eVar.e());
                TXCLog.i("TXCombineDecAndRender", stringBuilder2.toString());
                c.this.a(i, eVar, c.this.v, c.this.w);
                return 0;
            }
        }
    };

    /* compiled from: TXCombineDecAndRender */
    class a extends Thread {
        a() {
        }

        public void run() {
            setName("DecodeThread");
            try {
                TXCLog.i("TXCombineDecAndRender", "===DecodeThread Start===");
                while (!c.this.k && !c.this.n) {
                    c.this.c.i();
                    c.this.d.i();
                }
                c.this.f.clear();
                c.this.h.clear();
                TXCLog.i("TXCombineDecAndRender", "===DecodeThread Exit===");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public c(Context context) {
        this.b = context;
        this.c = new h();
        this.d = new h();
        this.e = new i(2);
        this.q = new e(this.b);
        this.r = new b();
        this.f = new ArrayBlockingQueue(1);
        this.h = new ArrayBlockingQueue(1);
        this.g = new ArrayBlockingQueue(10);
        this.i = new ArrayBlockingQueue(10);
        this.u = new LinkedBlockingQueue();
        this.c.a(this.f);
        this.d.a(this.h);
        this.c.b(this.g);
        this.d.b(this.i);
    }

    public void a(com.tencent.liteav.b.a.c cVar) {
        this.y = cVar;
    }

    public int a(List<String> list) {
        if (list == null || list.size() < 2) {
            return -1;
        }
        this.c.a((String) list.get(0));
        boolean z = true;
        this.d.a((String) list.get(1));
        if (this.c.d() > this.d.d()) {
            z = false;
        }
        this.x = z;
        this.r.a(this.c.a());
        this.r.b(this.d.a());
        this.r.a(a());
        this.r.a();
        return 0;
    }

    public void a(List<com.tencent.liteav.i.a.a> list, int i, int i2) {
        this.q.a(list, i, i2);
        this.s = i;
        this.t = i2;
    }

    public int a() {
        int e = this.c.e();
        int e2 = this.d.e();
        if (e < e2) {
            e = e2;
        }
        return e > 0 ? e : TXRecordCommon.AUDIO_SAMPLERATE_48000;
    }

    public int b() {
        int f = this.c.f();
        int f2 = this.d.f();
        if (f < f2) {
            f = f2;
        }
        return f >= 0 ? f : 10000;
    }

    public void c() {
        g gVar = new g();
        g gVar2 = new g();
        gVar.a = this.c.b();
        gVar.b = this.c.c();
        gVar2.a = this.d.b();
        gVar2.b = this.d.c();
        this.e.a(gVar, 0);
        this.e.a(gVar2, 1);
        this.e.a(this.z, 0);
        this.e.a(this.A, 1);
        this.e.a(this.B);
        this.e.a();
        this.n = false;
        this.k = false;
        this.l = false;
        this.m = false;
        this.j = new a();
    }

    public void d() {
        this.n = true;
        if (this.j != null && this.j.isAlive()) {
            try {
                this.j.join(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (this.c != null) {
            this.c.h();
        }
        if (this.d != null) {
            this.d.h();
        }
        if (this.r != null) {
            this.r.b();
        }
        if (this.e != null) {
            this.e.b();
        }
        this.g.clear();
        this.i.clear();
        this.w = null;
    }

    private void a(int i, e eVar, int i2, e eVar2) {
        StringBuilder stringBuilder;
        if (this.x) {
            if (eVar2.e() < eVar.e()) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("prepareToCombine, mFirstFpsSmall true, secondFrame pts < first pts, drop second, current second queue size = ");
                stringBuilder2.append(this.h.size());
                TXCLog.i("TXCombineDecAndRender", stringBuilder2.toString());
                this.w = eVar;
                this.v = i;
                this.h.remove();
                return;
            }
            TXCLog.i("TXCombineDecAndRender", "prepareToCombine, mFirstFpsSmall true, secondFrame pts >= first pts, to combine");
            i = this.q.a(i, i2, eVar, eVar2);
            if (this.y != null) {
                this.y.a(i, this.s, this.t, eVar);
            }
            this.f.remove();
            this.h.remove();
            stringBuilder = new StringBuilder();
            stringBuilder.append("prepareToCombine, after combine, remain queue queue2 size = ");
            stringBuilder.append(this.f.size());
            stringBuilder.append(", ");
            stringBuilder.append(this.h.size());
            TXCLog.i("TXCombineDecAndRender", stringBuilder.toString());
            this.v = -1;
            this.w = null;
        } else if (eVar2.e() > eVar.e()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("mFirstFpsSmall false, secondFrame pts > first pts, drop first, current first queue size = ");
            stringBuilder.append(this.f.size());
            TXCLog.i("TXCombineDecAndRender", stringBuilder.toString());
            this.w = eVar2;
            this.v = i2;
            this.f.remove();
        } else {
            TXCLog.i("TXCombineDecAndRender", "mFirstFpsSmall false, secondFrame pts <= first pts, to combine");
            i = this.q.a(i, i2, eVar, eVar2);
            if (this.y != null) {
                this.y.a(i, this.s, this.t, eVar);
            }
            this.f.remove();
            this.h.remove();
            this.v = -1;
            this.w = null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x009c  */
    private void e() {
        /*
        r6 = this;
        r0 = r6.g;
        r0 = r0.isEmpty();
        if (r0 == 0) goto L_0x0010;
    L_0x0008:
        r0 = "TXCombineDecAndRender";
        r1 = "combineAudioFrame, mAudioBlockingQueue is empty, ignore";
        com.tencent.liteav.basic.log.TXCLog.i(r0, r1);
        return;
    L_0x0010:
        r0 = r6.g;
        r0 = r0.peek();
        r0 = (com.tencent.liteav.d.e) r0;
        r1 = 1;
        r2 = r6.a(r0, r1);
        if (r2 == 0) goto L_0x0027;
    L_0x001f:
        r0 = "TXCombineDecAndRender";
        r1 = "combineAudioFrame, frame1 is end";
        com.tencent.liteav.basic.log.TXCLog.i(r0, r1);
        return;
    L_0x0027:
        r2 = r6.i;
        r2 = r2.isEmpty();
        if (r2 == 0) goto L_0x0037;
    L_0x002f:
        r0 = "TXCombineDecAndRender";
        r1 = "combineAudioFrame, mAudioBlockingQueue2 is empty, ignore";
        com.tencent.liteav.basic.log.TXCLog.i(r0, r1);
        return;
    L_0x0037:
        r2 = r6.i;
        r2 = r2.peek();
        r2 = (com.tencent.liteav.d.e) r2;
        r1 = r6.a(r2, r1);
        if (r1 == 0) goto L_0x004d;
    L_0x0045:
        r0 = "TXCombineDecAndRender";
        r1 = "combineAudioFrame, frame2 is end";
        com.tencent.liteav.basic.log.TXCLog.i(r0, r1);
        return;
    L_0x004d:
        r1 = r6.g;	 Catch:{ InterruptedException -> 0x0060 }
        r1 = r1.take();	 Catch:{ InterruptedException -> 0x0060 }
        r1 = (com.tencent.liteav.d.e) r1;	 Catch:{ InterruptedException -> 0x0060 }
        r0 = r6.i;	 Catch:{ InterruptedException -> 0x005e }
        r0 = r0.take();	 Catch:{ InterruptedException -> 0x005e }
        r0 = (com.tencent.liteav.d.e) r0;	 Catch:{ InterruptedException -> 0x005e }
        goto L_0x0068;
    L_0x005e:
        r0 = move-exception;
        goto L_0x0064;
    L_0x0060:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
    L_0x0064:
        r0.printStackTrace();
        r0 = r2;
    L_0x0068:
        r2 = "TXCombineDecAndRender";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "===combineAudioFrame===after take, size1:";
        r3.append(r4);
        r4 = r6.g;
        r4 = r4.size();
        r3.append(r4);
        r4 = ",size2:";
        r3.append(r4);
        r4 = r6.i;
        r4 = r4.size();
        r3.append(r4);
        r3 = r3.toString();
        com.tencent.liteav.basic.log.TXCLog.i(r2, r3);
        r2 = r6.r;
        r0 = r2.a(r1, r0);
        r1 = r6.y;
        if (r1 == 0) goto L_0x00a1;
    L_0x009c:
        r1 = r6.y;
        r1.a(r0);
    L_0x00a1:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.b.c.e():void");
    }

    private boolean a(e eVar, boolean z) {
        if (!eVar.p()) {
            return false;
        }
        if (this.y != null) {
            if (z) {
                TXCLog.i("TXCombineDecAndRender", "===judgeDecodeComplete=== audio end");
                this.m = true;
                this.g.clear();
                this.i.clear();
                this.y.c(eVar);
            } else {
                TXCLog.i("TXCombineDecAndRender", "===judgeDecodeComplete=== video end");
                this.l = true;
                this.y.b(eVar);
            }
            if (this.m && this.l) {
                TXCLog.i("TXCombineDecAndRender", "judgeDecodeComplete, video and audio both end");
                this.k = true;
                d();
            }
        }
        return true;
    }
}
