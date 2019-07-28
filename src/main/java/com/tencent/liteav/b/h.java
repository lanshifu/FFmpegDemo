package com.tencent.liteav.b;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.util.LongSparseArray;
import android.view.Surface;
import com.tencent.liteav.b.a.a;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.e;
import com.tencent.liteav.g.f;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

@TargetApi(16)
/* compiled from: TXReaderLone */
public class h {
    private LongSparseArray<e> a = new LongSparseArray();
    private LongSparseArray<e> b = new LongSparseArray();
    private AtomicBoolean c = new AtomicBoolean(false);
    private AtomicBoolean d = new AtomicBoolean(false);
    private LinkedList<e> e = new LinkedList();
    private Surface f;
    private a g;
    private com.tencent.liteav.g.e h = new com.tencent.liteav.g.e();
    private g i;
    private f j;
    private e k;
    private e l;
    private ArrayBlockingQueue<e> m;
    private ArrayBlockingQueue<e> n;

    public h() {
        this.e.clear();
    }

    public int a(String str) {
        int a;
        try {
            a = this.h.a(str);
        } catch (IOException e) {
            e.printStackTrace();
            a = 0;
        }
        if (a < 0) {
            return a;
        }
        return 0;
    }

    public void a(Surface surface) {
        this.f = surface;
    }

    public MediaFormat a() {
        return this.h.m();
    }

    public int b() {
        return this.h.b();
    }

    public int c() {
        return this.h.c();
    }

    public int d() {
        return this.h.e();
    }

    public int e() {
        MediaFormat m = this.h.m();
        return m.containsKey("sample-rate") ? m.getInteger("sample-rate") : 0;
    }

    public int f() {
        MediaFormat m = this.h.m();
        return m.containsKey("max-input-size") ? m.getInteger("max-input-size") : 0;
    }

    public void a(a aVar) {
        this.g = aVar;
    }

    public void g() {
        this.i = new g();
        this.j = new f();
        MediaFormat m = this.h.m();
        this.j.a(m);
        this.j.a(m, null);
        this.j.a();
        this.i.a(this.h.l());
        this.i.a(this.h.l(), this.f);
        this.i.a();
    }

    public void h() {
        if (this.j != null) {
            this.j.b();
        }
        if (this.i != null) {
            this.i.b();
        }
        if (this.e != null) {
            this.e.clear();
        }
        if (this.b != null) {
            this.b.clear();
        }
        if (this.a != null) {
            this.a.clear();
        }
        this.h.o();
        this.c.compareAndSet(true, false);
        this.d.compareAndSet(true, false);
    }

    public void i() throws InterruptedException {
        j();
        k();
        l();
        m();
    }

    private void j() throws InterruptedException {
        if (this.c.get()) {
            TXCLog.d("TXReaderLone", "mReadVideoEOF, ignore");
            return;
        }
        e c = this.i.c();
        if (c != null) {
            c = this.h.a(c);
            if (this.h.c(c)) {
                this.c.compareAndSet(false, true);
                TXCLog.i("TXReaderLone", "==TXReaderLone Read Video End===");
            }
            this.b.put(c.e(), c);
            this.i.a(c);
        }
    }

    private void k() throws InterruptedException {
        if (!this.d.get()) {
            e c = this.j.c();
            if (c != null) {
                c = this.h.b(c);
                if (this.h.d(c)) {
                    this.d.compareAndSet(false, true);
                    TXCLog.i("TXReaderLone", "==TXReaderLone Read Audio End===");
                }
                this.a.put(c.e(), c);
                this.j.a(c);
            }
        }
    }

    private void l() {
        e d;
        if (this.e.size() == 0) {
            if (this.m == null || this.m.size() <= 0) {
                d = this.i.d();
                if (d != null && d.o() != null) {
                    if (this.k == null) {
                        this.k = d;
                    }
                    e eVar = (e) this.b.get(d.e());
                    if (eVar != null) {
                        d = this.i.a(eVar, d);
                    }
                    if ((d.o().flags & 4) != 0) {
                        TXCLog.i("TXReaderLone", "==TXReaderLone Decode Video End===");
                    }
                    this.e.add(d);
                } else {
                    return;
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("decodeVideoFrame, ignore because mVideoBlockingQueue.size() = ");
            stringBuilder.append(this.m.size());
            TXCLog.d("TXReaderLone", stringBuilder.toString());
            return;
        }
        if (this.e.size() > 0) {
            d = (e) this.e.get(0);
            if (this.k == null) {
                this.k = d;
            }
            if (this.g != null) {
                this.g.b(d);
            }
            if (!this.e.isEmpty() && this.e.size() > 0) {
                this.e.remove(0);
            }
            this.k = d;
        }
    }

    private void m() {
        if (this.n == null || this.n.size() <= 9) {
            e d = this.j.d();
            if (d != null && d.o() != null) {
                e eVar = (e) this.a.get(d.e());
                eVar = eVar != null ? this.j.a(eVar, d) : d;
                if (eVar == null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("decodeAudioFrame, fixFrame is null, sampleTime = ");
                    stringBuilder.append(d.e());
                    TXCLog.e("TXReaderLone", stringBuilder.toString());
                    return;
                }
                if ((eVar.o().flags & 4) != 0) {
                    TXCLog.i("TXReaderLone", "==TXReaderLone Decode Audio End===");
                }
                if (this.l == null) {
                    this.l = d;
                }
                if (this.g != null) {
                    this.g.a(eVar);
                }
                this.l = eVar;
                return;
            }
            return;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("decodeAudioFrame, ignore because mAudioBlockingQueue size = ");
        stringBuilder2.append(this.n.size());
        TXCLog.d("TXReaderLone", stringBuilder2.toString());
    }

    public void a(ArrayBlockingQueue<e> arrayBlockingQueue) {
        this.m = arrayBlockingQueue;
    }

    public void b(ArrayBlockingQueue<e> arrayBlockingQueue) {
        this.n = arrayBlockingQueue;
    }
}
