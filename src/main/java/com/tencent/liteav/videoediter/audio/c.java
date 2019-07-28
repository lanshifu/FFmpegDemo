package com.tencent.liteav.videoediter.audio;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.g.e;
import com.tencent.liteav.g.f;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

@TargetApi(18)
/* compiled from: TXAudioMixer */
public class c {
    public static String[] a = new String[]{"audio/mp4a-latm", "audio/mpeg"};
    private static final String b = "c";
    private boolean A = true;
    private AtomicInteger c = new AtomicInteger(-1);
    private MediaFormat d;
    private e e;
    private MediaFormat f;
    private String g;
    private volatile long h = -1;
    private volatile long i = -1;
    private int j;
    private int k;
    private int l;
    private int m;
    private List<com.tencent.liteav.d.e> n;
    private a o;
    private f p;
    private AtomicBoolean q = new AtomicBoolean(false);
    private AtomicBoolean r = new AtomicBoolean(false);
    private ReentrantLock s = new ReentrantLock();
    private Condition t = this.s.newCondition();
    private Condition u = this.s.newCondition();
    private e v;
    private TXSkpResample w;
    private f x = new f();
    private com.tencent.liteav.d.e y;
    private Handler z = new Handler(Looper.getMainLooper());

    /* compiled from: TXAudioMixer */
    private class a extends Thread {
        public a() {
            super("Mixer-BGM-Decoder-Thread");
        }

        public void run() {
            super.run();
            TXCLog.d(c.b, "================= start thread===================");
            try {
                c.this.l();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (c.this.A && c.this.c.get() == 1) {
                c.this.z.post(new Runnable() {
                    public void run() {
                        c.this.i();
                    }
                });
            }
            TXCLog.d(c.b, "================= finish thread===================");
        }
    }

    public c() {
        this.x.a(1.0f);
    }

    public void a(float f) {
        this.x.a(f);
    }

    public int a(String str) throws IOException {
        if (!(this.g == null || this.g.equals(str))) {
            this.h = -1;
            this.i = -1;
        }
        if (TextUtils.isEmpty(str)) {
            d();
            this.f = null;
            return 0;
        }
        if (this.c.get() == 0 || this.c.get() == 1) {
            d();
        }
        this.g = str;
        return g();
    }

    public void a(long j, long j2) {
        this.h = j * 1000;
        this.i = j2 * 1000;
        if (this.e != null) {
            this.e.c(this.h);
        }
        String str = b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("bgm startTime :");
        stringBuilder.append(this.h);
        stringBuilder.append(",bgm endTime:");
        stringBuilder.append(this.i);
        Log.d(str, stringBuilder.toString());
    }

    public void b(float f) {
        this.x.b(f);
    }

    public void a(MediaFormat mediaFormat) {
        if (mediaFormat == null) {
            TXCLog.e(b, "target media format can't be null");
            return;
        }
        this.d = mediaFormat;
        this.k = this.d.getInteger("channel-count");
        this.j = this.d.getInteger("sample-rate");
        o();
    }

    private int g() throws IOException {
        Object obj;
        this.c.getAndSet(0);
        p();
        String string = this.f.getString(IMediaFormat.KEY_MIME);
        for (Object obj2 : a) {
            if (string != null && string.equals(obj2)) {
                obj = 1;
                break;
            }
        }
        obj = null;
        if (obj == null) {
            this.c.getAndSet(2);
            return -1;
        }
        h();
        q();
        o();
        return 0;
    }

    private void h() throws IOException {
        this.p = new f();
        this.p.a(this.e.m());
        this.p.a(this.e.m(), null);
        this.p.a();
    }

    private void i() {
        try {
            a(this.g);
            a();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void a() {
        if (this.c.get() == -1 || this.c.get() == 2) {
            TXCLog.e(b, "you should set bgm info first");
        } else if (this.c.get() == 1) {
            TXCLog.e(b, "decode have been started");
        } else {
            this.c.getAndSet(1);
            j();
        }
    }

    public short[] a(short[] sArr) {
        if (this.c.get() != 1) {
            TXCLog.e(b, "you should start first");
            return sArr;
        }
        return this.x.a(sArr, a(sArr.length));
    }

    private short[] a(int i) {
        com.tencent.liteav.d.e c = c();
        if (c == null) {
            return null;
        }
        short[] a = a(c);
        if (a == null) {
            return null;
        }
        short[] copyOf = Arrays.copyOf(a, i);
        int length = a.length;
        if (length < i) {
            while (length < i) {
                c = c();
                if (c == null) {
                    return null;
                }
                short[] a2 = a(c);
                if (a2.length + length > i) {
                    a = a(copyOf, length, a2);
                    if (a != null) {
                        length += a2.length - a.length;
                        this.y = b(a);
                    }
                } else {
                    a(copyOf, length, a2);
                    length += a2.length;
                    this.y = null;
                }
            }
        } else if (length > i) {
            this.y = b(Arrays.copyOfRange(a, i, a.length));
        } else if (length == i) {
            copyOf = a(c);
            this.y = null;
        }
        return copyOf;
    }

    private short[] a(com.tencent.liteav.d.e eVar) {
        if (eVar instanceof a) {
            return ((a) eVar).z();
        }
        return b.a(eVar.b(), eVar.g());
    }

    private short[] a(short[] sArr, int i, short[] sArr2) {
        int i2 = 0;
        while (i2 < sArr2.length && i < sArr.length) {
            sArr[i] = sArr2[i2];
            i++;
            i2++;
        }
        return (sArr2.length - i2) + 1 > 0 ? Arrays.copyOfRange(sArr2, i2, sArr2.length) : null;
    }

    public boolean b() {
        return !this.A && this.r.get();
    }

    public com.tencent.liteav.d.e c() {
        com.tencent.liteav.d.e eVar = null;
        if (this.y != null) {
            com.tencent.liteav.d.e eVar2 = this.y;
            this.y = null;
            return eVar2;
        } else if (!this.A && this.r.get()) {
            return null;
        } else {
            while (this.n != null && this.n.size() == 0) {
                this.s.lock();
                try {
                    this.u.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Throwable th) {
                    this.s.unlock();
                }
                this.s.unlock();
            }
            if (this.n != null && this.n.size() <= 10) {
                this.s.lock();
                this.t.signal();
                this.s.unlock();
            }
            while (true) {
                if (!((eVar != null && eVar.g() != 0) || this.n == null || this.n.size() == 0)) {
                    eVar = (com.tencent.liteav.d.e) this.n.remove(0);
                }
            }
            return eVar;
        }
    }

    public void d() {
        if (this.c.get() != -1) {
            this.c.getAndSet(2);
            TXCLog.i(b, "============================start cancel mix task=============================");
            u();
            s();
            r();
            t();
            this.z.removeCallbacksAndMessages(null);
            TXCLog.i(b, "============================cancel finish =============================");
        }
    }

    private void j() {
        k();
        this.o = new a();
        this.o.start();
    }

    private void k() {
        if (!(this.o == null || !this.o.isAlive() || this.o.isInterrupted())) {
            this.o.interrupt();
            this.o = null;
        }
        r();
        q();
        this.q.getAndSet(false);
        this.r.getAndSet(false);
    }

    public MediaFormat e() {
        return this.f;
    }

    public void a(boolean z) {
        this.A = z;
    }

    private void l() throws Exception {
        TXCLog.d(b, "================= start decode===================");
        while (this.c.get() == 1 && !Thread.currentThread().isInterrupted()) {
            if (this.r.get()) {
                TXCLog.d(b, "=================解码完毕===================");
                break;
            }
            com.tencent.liteav.d.e n;
            try {
                m();
                n = n();
            } catch (Exception unused) {
                n = null;
            }
            if (n != null) {
                n = b(n);
                if (n != null) {
                    if (this.n != null && this.n.size() == 20) {
                        this.s.lock();
                        try {
                            this.t.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (Throwable th) {
                            this.s.unlock();
                        }
                        this.s.unlock();
                    }
                    if (this.n != null && this.n.size() == 0) {
                        if (n != null) {
                            this.n.add(n);
                        }
                        this.s.lock();
                        this.u.signal();
                        this.s.unlock();
                    } else if (!(this.n == null || n == null)) {
                        this.n.add(n);
                    }
                }
            }
        }
        TXCLog.d(b, "=================decode finish===================");
    }

    private void m() throws InterruptedException {
        if (!this.q.get()) {
            com.tencent.liteav.d.e c = this.p.c();
            if (c != null) {
                c = this.e.b(c);
                if (this.e.d(c)) {
                    this.q.getAndSet(true);
                    String str = b;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("audio endOfFile:");
                    stringBuilder.append(this.q.get());
                    TXCLog.d(str, stringBuilder.toString());
                    TXCLog.d(b, "read audio end");
                }
                this.p.a(c);
            }
        }
    }

    private com.tencent.liteav.d.e n() {
        if (this.c.get() != 1) {
            return null;
        }
        com.tencent.liteav.d.e d = this.p.d();
        if (d == null || d.o() == null) {
            return null;
        }
        if (d.e() < this.h && (d.o().flags & 4) == 0) {
            return null;
        }
        if (d.e() > this.i) {
            this.r.getAndSet(true);
            return null;
        } else if ((d.o().flags & 4) == 0) {
            return d;
        } else {
            TXCLog.d(b, "==================generate decode Audio END==========================");
            this.r.getAndSet(true);
            return d;
        }
    }

    /* JADX WARNING: Missing block: B:30:0x0057, code skipped:
            return r3;
     */
    private com.tencent.liteav.d.e b(com.tencent.liteav.d.e r3) throws java.lang.InterruptedException {
        /*
        r2 = this;
        r0 = r3.o();
        r0 = r0.flags;
        r1 = 2;
        if (r0 != r1) goto L_0x000a;
    L_0x0009:
        return r3;
    L_0x000a:
        r0 = r2.k;
        r1 = r2.m;
        if (r0 != r1) goto L_0x0017;
    L_0x0010:
        r0 = r2.j;
        r1 = r2.l;
        if (r0 != r1) goto L_0x0017;
    L_0x0016:
        return r3;
    L_0x0017:
        r0 = r3.b();
        r1 = r3.g();
        r0 = com.tencent.liteav.videoediter.audio.b.a(r0, r1);
        if (r0 == 0) goto L_0x0057;
    L_0x0025:
        r1 = r0.length;
        if (r1 != 0) goto L_0x0029;
    L_0x0028:
        goto L_0x0057;
    L_0x0029:
        r1 = r2.v;
        if (r1 == 0) goto L_0x0056;
    L_0x002d:
        r1 = r2.w;
        if (r1 != 0) goto L_0x0032;
    L_0x0031:
        goto L_0x0056;
    L_0x0032:
        r3 = r2.k;
        r1 = r2.m;
        if (r3 == r1) goto L_0x003e;
    L_0x0038:
        r3 = r2.v;
        r0 = r3.a(r0);
    L_0x003e:
        r3 = r2.j;
        r1 = r2.l;
        if (r3 == r1) goto L_0x0051;
    L_0x0044:
        r3 = r2.w;
        r0 = r3.doResample(r0);
        if (r0 == 0) goto L_0x004f;
    L_0x004c:
        r3 = r0.length;
        if (r3 != 0) goto L_0x0051;
    L_0x004f:
        r3 = 0;
        return r3;
    L_0x0051:
        r3 = r2.b(r0);
        return r3;
    L_0x0056:
        return r3;
    L_0x0057:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.videoediter.audio.c.b(com.tencent.liteav.d.e):com.tencent.liteav.d.e");
    }

    private com.tencent.liteav.d.e b(short[] sArr) {
        if (sArr == null || sArr.length == 0) {
            return null;
        }
        a aVar = new a();
        aVar.a(sArr);
        aVar.d(sArr.length * 2);
        aVar.h(this.k);
        aVar.g(this.j);
        return aVar;
    }

    @TargetApi(16)
    private void o() {
        if (this.f != null && this.d != null) {
            if (this.v == null) {
                this.v = new e();
            }
            this.v.a(this.m, this.k);
            if (this.w == null) {
                this.w = new TXSkpResample();
            }
            this.w.init(this.l, this.j);
            TXCLog.i(b, "TXChannelResample and TXSkpResample have been created!!!");
        }
    }

    private void p() throws IOException {
        this.e = new e(true);
        this.e.a(this.g);
        this.f = this.e.m();
        this.m = this.f.getInteger("channel-count");
        this.l = this.f.getInteger("sample-rate");
        if (this.h == -1 && this.i == -1) {
            this.h = 0;
            this.i = this.f.getLong("durationUs") * 1000;
        }
        this.e.c(this.h);
    }

    private void q() {
        this.n = new LinkedList();
        this.n = Collections.synchronizedList(this.n);
    }

    private void r() {
        if (this.n != null) {
            TXCLog.i(b, "clean audio frame queue");
            this.n.clear();
            this.n = null;
        }
    }

    private void s() {
        if (this.e != null) {
            TXCLog.i(b, "release media extractor");
            this.e.o();
            this.e = null;
        }
    }

    private void t() {
        if (this.v != null) {
            this.v = null;
            TXCLog.i(b, "release chanel resample ");
        }
        if (this.w != null) {
            TXCLog.i(b, "release skp resample ");
            this.w.destroy();
            this.w = null;
        }
    }

    private void u() {
        if (!(this.o == null || !this.o.isAlive() || this.o.isInterrupted())) {
            TXCLog.i(b, "interrupt the decode thread");
            this.o.interrupt();
            this.o = null;
        }
        if (this.p != null) {
            TXCLog.i(b, "stop audio decode");
            this.p.b();
            this.p = null;
        }
    }
}
