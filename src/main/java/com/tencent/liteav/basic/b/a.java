package com.tencent.liteav.basic.b;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.tencent.liteav.basic.g.b;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import java.util.LinkedList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: TXCVideoJitterBuffer */
public class a {
    private long A;
    private long B;
    private long C;
    private long D;
    private long E;
    private long F;
    private ReadWriteLock G;
    private b a;
    private LinkedList<b> b;
    private LinkedList<b> c;
    private long d;
    private long e;
    private volatile boolean f;
    private volatile float g;
    private long h;
    private long i;
    private long j;
    private long k;
    private long l;
    private long m;
    private long n;
    private long o;
    private boolean p;
    private HandlerThread q;
    private Handler r;
    private boolean s;
    private long t;
    private long u;
    private volatile long v;
    private volatile long w;
    private int x;
    private int y;
    private long z;

    static /* synthetic */ long j(a aVar) {
        long j = aVar.A + 1;
        aVar.A = j;
        return j;
    }

    static /* synthetic */ long l(a aVar) {
        long j = aVar.E + 1;
        aVar.E = j;
        return j;
    }

    public a() {
        this.a = null;
        this.b = new LinkedList();
        this.c = new LinkedList();
        this.d = 0;
        this.e = 15;
        this.f = false;
        this.g = 1.0f;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = false;
        this.q = null;
        this.r = null;
        this.s = false;
        this.t = 20;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.C = 0;
        this.D = 0;
        this.E = 0;
        this.F = 0;
        this.G = new ReentrantReadWriteLock();
        this.q = new HandlerThread("VideoJitterBufferHandler");
        this.q.start();
        this.G.writeLock().lock();
        this.r = new Handler(this.q.getLooper());
        this.G.writeLock().unlock();
    }

    public void a(final b bVar) {
        this.G.readLock().lock();
        if (this.r != null) {
            this.r.post(new Runnable() {
                public void run() {
                    a.this.a = bVar;
                }
            });
        }
        this.G.readLock().unlock();
    }

    public void a() {
        this.G.readLock().lock();
        if (this.r != null) {
            this.r.post(new Runnable() {
                public void run() {
                    a.this.s = true;
                    a.this.B = TXCTimeUtil.getTimeTick();
                }
            });
        }
        this.G.readLock().unlock();
        m();
    }

    public void b() {
        this.G.writeLock().lock();
        if (this.r != null) {
            this.r.post(new Runnable() {
                public void run() {
                    a.this.s = false;
                    a.this.l();
                    try {
                        Looper.myLooper().quit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        this.r = null;
        this.G.writeLock().unlock();
    }

    private void l() {
        this.b.clear();
        this.d = 0;
        this.c.clear();
        this.e = 15;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.v = 0;
        this.w = 0;
        this.y = 0;
        this.x = 0;
        this.f = false;
        this.g = 1.0f;
        this.k = 0;
        this.o = 0;
        this.l = 0;
        this.m = 0;
        this.C = 0;
        this.D = 0;
        this.E = 0;
        this.F = 0;
        this.p = false;
    }

    public void a(boolean z) {
        this.p = z;
    }

    private void m() {
        this.G.readLock().lock();
        if (this.r != null) {
            this.r.postDelayed(new Runnable() {
                public void run() {
                    b d;
                    long t;
                    while (a.this.b != null && !a.this.b.isEmpty() && !a.this.p) {
                        a.this.c();
                        d = a.this.n();
                        if (d == null) {
                            break;
                        } else if (!(d == null || a.this.a == null)) {
                            a.this.a.b(d);
                        }
                    }
                    while (!a.this.c.isEmpty() && a.this.a != null) {
                        d = (b) a.this.c.getFirst();
                        t = a.this.a.t();
                        if (0 == t) {
                            t = a.this.v;
                        }
                        if (d.pts > t) {
                            break;
                        }
                        a.this.a.c(d);
                        a.this.c.removeFirst();
                    }
                    t = TXCTimeUtil.getTimeTick();
                    if (t > a.this.B + 200) {
                        a.this.z = a.this.z + a.this.d();
                        a.j(a.this);
                        long e = a.this.e();
                        a.this.D = a.this.D + e;
                        a.l(a.this);
                        if (a.this.E > 0) {
                            a.this.F = a.this.D / a.this.E;
                        }
                        if (e > a.this.C) {
                            a.this.C = e;
                        }
                        a.this.B = t;
                    }
                    if (a.this.s) {
                        a.this.m();
                    }
                }
            }, this.t);
        }
        this.G.readLock().unlock();
    }

    public void a(final b bVar) {
        if (bVar != null) {
            if (bVar.pts > this.w || bVar.pts + 500 < this.w) {
                this.w = bVar.pts;
            }
            if (this.v > this.w) {
                this.v = this.w;
            }
            if (bVar.nalType == 0) {
                this.x = this.y;
                this.y = 1;
            } else if (bVar.nalType == 2 || bVar.nalType == 1) {
                this.y++;
            }
            this.G.readLock().lock();
            if (this.r != null) {
                this.r.post(new Runnable() {
                    public void run() {
                        if (bVar.nalType == 6) {
                            a.this.c.add(bVar);
                            return;
                        }
                        a.this.b.add(bVar);
                        a.this.d = (long) a.this.b.size();
                        if (a.this.a != null) {
                            a.this.d = a.this.d + ((long) a.this.a.u());
                        }
                        a.this.e(bVar.dts);
                    }
                });
            }
            this.G.readLock().unlock();
        }
    }

    private b n() {
        if (this.b == null || this.b.isEmpty()) {
            return null;
        }
        if (this.v > this.w) {
            this.v = this.w;
        }
        long timeTick = TXCTimeUtil.getTimeTick();
        Object obj = 1;
        if (this.k != 0) {
            long b;
            b bVar = (b) this.b.getFirst();
            if (bVar.dts > this.k) {
                b = b(bVar.dts - this.k);
            } else {
                long b2 = b(0);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("videojitter pull nal with invalid ts, current dts [");
                stringBuilder.append(bVar.dts);
                stringBuilder.append("] < last dts[ ");
                stringBuilder.append(this.k);
                stringBuilder.append("]!!! decInterval is ");
                stringBuilder.append(b2);
                TXCLog.w("TXCVideoJitterBuffer", stringBuilder.toString());
                b = b2;
            }
            if (this.o + b <= this.u + timeTick) {
                this.u = (this.u + timeTick) - (this.o + b);
                if (this.u > b) {
                    this.u = b;
                }
            } else {
                obj = null;
            }
        }
        if (obj == null) {
            return null;
        }
        b o = o();
        f(o.dts);
        this.o = timeTick;
        return o;
    }

    private long b(long j) {
        long j2 = 500;
        if (j > 500) {
            j = 500;
        }
        if (j > 0) {
            float d;
            if ((this.a != null ? this.a.t() : 0) > 0) {
                j2 = 50;
            } else if (this.f) {
                j2 = 200;
            }
            j2 = a(j, j2);
            if (this.f) {
                d = d(j2);
            } else {
                d = c(j2);
            }
            return (long) (((float) j) / d);
        } else if (this.e > 0) {
            return 1000 / this.e;
        } else {
            return 0;
        }
    }

    private long a(long j, long j2) {
        if (this.e > 0) {
            long j3 = 1000 / this.e;
            if (j < j3) {
                j = j3;
            }
        }
        return j > j2 ? j : j2;
    }

    private float c(long j) {
        if ((this.a != null ? this.a.u() : 0) > 24) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("videojitter pull nal with speed : ");
            stringBuilder.append(0.1f);
            TXCLog.e("TXCVideoJitterBuffer", stringBuilder.toString());
            return 0.1f;
        }
        long j2 = 0;
        long t = this.a != null ? this.a.t() : 0;
        float f = 1.0f;
        if (t <= 0) {
            t = this.w > this.v ? this.w - this.v : 0;
            long j3 = (long) (this.g * 1000.0f);
            if (this.a != null) {
                j2 = this.n * ((long) this.a.u());
            }
            if (j2 <= j3) {
                j2 = j3;
            }
            float f2 = t > j + j2 ? 1.1f : 1.0f;
            if (t > j2) {
                f = f2;
            }
        } else if (t >= this.v + j) {
            f = t >= (this.v + j) + 200 ? 2.2f : 1.2f;
        } else {
            t += j;
            if (this.v >= t) {
                f = this.v >= t + 200 ? 0.5f : 0.9f;
            }
        }
        return f;
    }

    private float d(long j) {
        if ((this.a != null ? this.a.u() : 0) > 24) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("videojitter pull nal with speed : ");
            stringBuilder.append(0.1f);
            TXCLog.e("TXCVideoJitterBuffer", stringBuilder.toString());
            return 0.1f;
        }
        long j2 = 0;
        long t = this.a != null ? this.a.t() : 0;
        float f = 1.0f;
        if (t <= 0 || this.a == null || 0 == this.a.s()) {
            t = this.w > this.v ? this.w - this.v : 0;
            long j3 = (long) (this.g * 1000.0f);
            if (this.a != null) {
                j2 = this.n * ((long) this.a.u());
            }
            if (j2 <= j3) {
                j2 = j3;
            }
            float f2 = t > j + j2 ? 1.2f : 1.0f;
            if (t > j2) {
                f = f2;
            }
        } else if (t >= this.v + j) {
            f = t >= (this.v + j) + 200 ? 2.2f : 1.5f;
        } else {
            t += j;
            if (this.v >= t && this.v < 400 + t) {
                f = this.v >= t + 200 ? 0.5f : 0.7f;
            }
        }
        return f;
    }

    /* Access modifiers changed, original: 0000 */
    public void c() {
        if (this.b.size() != 0) {
            b bVar = (b) this.b.getFirst();
            bVar = (b) this.b.getLast();
            int i = 0;
            int u = this.a != null ? this.a.u() : 0;
            if (!this.b.isEmpty() && u >= 24) {
                int i2 = 0;
                for (u = 0; u < this.b.size(); u++) {
                    if (((b) this.b.get(u)).nalType == 0) {
                        i2 = u;
                    }
                }
                while (!this.b.isEmpty() && i < i2) {
                    this.k = ((b) this.b.getFirst()).dts;
                    while (!this.c.isEmpty()) {
                        bVar = (b) this.c.getFirst();
                        if (bVar.pts > ((b) this.b.getFirst()).dts) {
                            break;
                        }
                        this.a.c(bVar);
                        this.c.removeFirst();
                    }
                    this.b.removeFirst();
                    i++;
                }
                if (i > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("videojitter cache too maney ， so drop ");
                    stringBuilder.append(i);
                    stringBuilder.append(" frames");
                    TXCLog.w("TXCVideoJitterBuffer", stringBuilder.toString());
                }
            }
        }
    }

    private b o() {
        if (this.b.isEmpty()) {
            return null;
        }
        b bVar = (b) this.b.getFirst();
        this.b.removeFirst();
        this.d = (long) this.b.size();
        if (this.a == null) {
            return bVar;
        }
        this.d += (long) this.a.u();
        return bVar;
    }

    private void e(long j) {
        if (this.j != 0) {
            if (this.i >= 5) {
                this.e = this.h / this.i;
                if (this.e > 200) {
                    this.e = 200;
                } else if (this.e < 1) {
                    this.e = 1;
                }
                if (this.e >= 30 && this.t != 5) {
                    this.t = 5;
                }
                this.h = 0;
                this.i = 0;
            } else {
                long j2 = j - this.j;
                if (j2 > 0) {
                    this.h += 1000 / j2;
                    this.i++;
                }
            }
        }
        this.j = j;
    }

    private void f(long j) {
        if (this.k != 0) {
            long j2;
            if (j > this.k) {
                j2 = j - this.k;
                if (j2 > 500) {
                    j2 = 500;
                }
            } else {
                j2 = this.e > 0 ? 1000 / this.e : 0;
            }
            this.l += j2;
            this.m++;
            if (this.m >= 5) {
                this.n = this.l / this.m;
                if (this.n > 500) {
                    this.n = 500;
                } else if (this.n < 5) {
                    this.n = 5;
                }
                this.l = 0;
                this.m = 0;
            }
        }
        this.k = j;
    }

    public void b(boolean z) {
        this.f = z;
    }

    public void a(float f) {
        this.g = f;
    }

    public void a(long j) {
        this.v = j;
        if (this.v > this.w) {
            this.v = this.w;
        }
    }

    public long d() {
        return this.w - this.v;
    }

    public long e() {
        return this.d;
    }

    public long f() {
        return this.v;
    }

    public long g() {
        return this.w;
    }

    public int h() {
        return this.x;
    }

    public long i() {
        return this.C;
    }

    public long j() {
        return this.F;
    }

    public long k() {
        long j = this.A != 0 ? this.z / this.A : 0;
        this.A = 0;
        this.z = 0;
        return j;
    }

    /* Access modifiers changed, original: protected */
    public void finalize() throws Throwable {
        super.finalize();
        try {
            b();
        } catch (Exception unused) {
        }
    }
}
