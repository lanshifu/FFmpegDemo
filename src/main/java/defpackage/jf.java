package defpackage;

import com.google.android.exoplayer2.util.z;
import defpackage.hh.a;

/* compiled from: WavHeader */
/* renamed from: jf */
final class jf implements hh {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private long g;
    private long h;

    public boolean a() {
        return true;
    }

    public jf(int i, int i2, int i3, int i4, int i5, int i6) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = i6;
    }

    public void a(long j, long j2) {
        this.g = j;
        this.h = j2;
    }

    public boolean c() {
        return (this.g == 0 || this.h == 0) ? false : true;
    }

    public long b() {
        return ((this.h / ((long) this.d)) * 1000000) / ((long) this.b);
    }

    public a b(long j) {
        long a = z.a((((((long) this.c) * j) / 1000000) / ((long) this.d)) * ((long) this.d), 0, this.h - ((long) this.d));
        long j2 = this.g + a;
        long a2 = a(j2);
        hi hiVar = new hi(a2, j2);
        if (a2 >= j || a == this.h - ((long) this.d)) {
            return new a(hiVar);
        }
        j2 += (long) this.d;
        return new a(hiVar, new hi(a(j2), j2));
    }

    public long a(long j) {
        return (Math.max(0, j - this.g) * 1000000) / ((long) this.c);
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return (this.b * this.e) * this.a;
    }

    public int f() {
        return this.b;
    }

    public int g() {
        return this.a;
    }

    public int h() {
        return this.f;
    }
}
