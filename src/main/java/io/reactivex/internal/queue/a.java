package io.reactivex.internal.queue;

import defpackage.xc;
import io.reactivex.internal.util.j;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* compiled from: SpscLinkedArrayQueue */
public final class a<T> implements xc<T> {
    static final int a = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    private static final Object j = new Object();
    final AtomicLong b = new AtomicLong();
    int c;
    long d;
    final int e;
    AtomicReferenceArray<Object> f;
    final int g;
    AtomicReferenceArray<Object> h;
    final AtomicLong i = new AtomicLong();

    private static int b(int i) {
        return i;
    }

    public a(int i) {
        i = j.a(Math.max(8, i));
        int i2 = i - 1;
        AtomicReferenceArray atomicReferenceArray = new AtomicReferenceArray(i + 1);
        this.f = atomicReferenceArray;
        this.e = i2;
        a(i);
        this.h = atomicReferenceArray;
        this.g = i2;
        this.d = (long) (i2 - 1);
        a(0);
    }

    public boolean offer(T t) {
        if (t != null) {
            AtomicReferenceArray atomicReferenceArray = this.f;
            long e = e();
            int i = this.e;
            int a = a(e, i);
            if (e < this.d) {
                return a(atomicReferenceArray, t, e, a);
            }
            long j = ((long) this.c) + e;
            if (b(atomicReferenceArray, a(j, i)) == null) {
                this.d = j - 1;
                return a(atomicReferenceArray, t, e, a);
            } else if (b(atomicReferenceArray, a(1 + e, i)) == null) {
                return a(atomicReferenceArray, t, e, a);
            } else {
                a(atomicReferenceArray, e, a, t, (long) i);
                return true;
            }
        }
        throw new NullPointerException("Null is not a valid element");
    }

    private boolean a(AtomicReferenceArray<Object> atomicReferenceArray, T t, long j, int i) {
        a((AtomicReferenceArray) atomicReferenceArray, i, (Object) t);
        a(j + 1);
        return true;
    }

    private void a(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i, T t, long j2) {
        AtomicReferenceArray atomicReferenceArray2 = new AtomicReferenceArray(atomicReferenceArray.length());
        this.f = atomicReferenceArray2;
        this.d = (j2 + j) - 1;
        a(atomicReferenceArray2, i, (Object) t);
        a((AtomicReferenceArray) atomicReferenceArray, atomicReferenceArray2);
        a((AtomicReferenceArray) atomicReferenceArray, i, j);
        a(j + 1);
    }

    private void a(AtomicReferenceArray<Object> atomicReferenceArray, AtomicReferenceArray<Object> atomicReferenceArray2) {
        a((AtomicReferenceArray) atomicReferenceArray, b(atomicReferenceArray.length() - 1), (Object) atomicReferenceArray2);
    }

    private AtomicReferenceArray<Object> a(AtomicReferenceArray<Object> atomicReferenceArray, int i) {
        i = b(i);
        AtomicReferenceArray atomicReferenceArray2 = (AtomicReferenceArray) b(atomicReferenceArray, i);
        a((AtomicReferenceArray) atomicReferenceArray, i, null);
        return atomicReferenceArray2;
    }

    public T poll() {
        AtomicReferenceArray atomicReferenceArray = this.h;
        long f = f();
        int i = this.g;
        int a = a(f, i);
        Object b = b(atomicReferenceArray, a);
        Object obj = b == j ? 1 : null;
        if (b != null && obj == null) {
            a(atomicReferenceArray, a, null);
            b(f + 1);
            return b;
        } else if (obj != null) {
            return a(a(atomicReferenceArray, i + 1), f, i);
        } else {
            return null;
        }
    }

    private T a(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i) {
        this.h = atomicReferenceArray;
        i = a(j, i);
        Object b = b(atomicReferenceArray, i);
        if (b != null) {
            a((AtomicReferenceArray) atomicReferenceArray, i, null);
            b(j + 1);
        }
        return b;
    }

    public T a() {
        AtomicReferenceArray atomicReferenceArray = this.h;
        long f = f();
        int i = this.g;
        Object b = b(atomicReferenceArray, a(f, i));
        return b == j ? b(a(atomicReferenceArray, i + 1), f, i) : b;
    }

    private T b(AtomicReferenceArray<Object> atomicReferenceArray, long j, int i) {
        this.h = atomicReferenceArray;
        return b(atomicReferenceArray, a(j, i));
    }

    public void clear() {
        while (true) {
            if (poll() == null) {
                if (isEmpty()) {
                    return;
                }
            }
        }
    }

    public int b() {
        long d = d();
        while (true) {
            long c = c();
            long d2 = d();
            if (d == d2) {
                return (int) (c - d2);
            }
            d = d2;
        }
    }

    public boolean isEmpty() {
        return c() == d();
    }

    private void a(int i) {
        this.c = Math.min(i / 4, a);
    }

    private long c() {
        return this.b.get();
    }

    private long d() {
        return this.i.get();
    }

    private long e() {
        return this.b.get();
    }

    private long f() {
        return this.i.get();
    }

    private void a(long j) {
        this.b.lazySet(j);
    }

    private void b(long j) {
        this.i.lazySet(j);
    }

    private static int a(long j, int i) {
        return b(((int) j) & i);
    }

    private static void a(AtomicReferenceArray<Object> atomicReferenceArray, int i, Object obj) {
        atomicReferenceArray.lazySet(i, obj);
    }

    private static <E> Object b(AtomicReferenceArray<Object> atomicReferenceArray, int i) {
        return atomicReferenceArray.get(i);
    }

    public boolean a(T t, T t2) {
        AtomicReferenceArray atomicReferenceArray = this.f;
        long c = c();
        int i = this.e;
        long j = 2 + c;
        int a;
        if (b(atomicReferenceArray, a(j, i)) == null) {
            a = a(c, i);
            a(atomicReferenceArray, a + 1, (Object) t2);
            a(atomicReferenceArray, a, (Object) t);
            a(j);
        } else {
            AtomicReferenceArray atomicReferenceArray2 = new AtomicReferenceArray(atomicReferenceArray.length());
            this.f = atomicReferenceArray2;
            a = a(c, i);
            a(atomicReferenceArray2, a + 1, (Object) t2);
            a(atomicReferenceArray2, a, (Object) t);
            a(atomicReferenceArray, atomicReferenceArray2);
            a(atomicReferenceArray, a, j);
            a(j);
        }
        return true;
    }
}
