package com.tencent.liteav.j;

import com.tencent.liteav.c.g;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: TimeProvider */
public class e {
    private static e a;
    private AtomicLong b = new AtomicLong(0);
    private AtomicLong c = new AtomicLong(0);
    private AtomicLong d = new AtomicLong(0);
    private AtomicLong e = new AtomicLong(0);
    private AtomicLong f = new AtomicLong(0);
    private AtomicLong g = new AtomicLong(0);

    public static e a() {
        if (a == null) {
            a = new e();
        }
        return a;
    }

    private e() {
    }

    public static long a(com.tencent.liteav.d.e eVar) {
        if (g.a().b()) {
            return eVar.u();
        }
        return eVar.t();
    }

    public void a(long j) {
        this.b.set(j);
    }

    public void b(long j) {
        this.c.set(j);
    }

    public void c(long j) {
        this.d.set(j);
    }

    public void d(long j) {
        this.e.set(j);
    }

    public void e(long j) {
        this.f.set(j);
    }

    public void f(long j) {
        this.g.set(j);
    }

    public void b() {
        this.b.set(0);
        this.c.set(0);
        this.d.set(0);
        this.e.set(0);
        this.f.set(0);
        this.g.set(0);
    }
}
