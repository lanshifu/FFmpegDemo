package defpackage;

import com.google.android.exoplayer2.util.z;
import defpackage.hs.a;

/* compiled from: ConstantBitrateSeeker */
/* renamed from: hr */
final class hr implements a {
    private final long a;
    private final int b;
    private final long c;
    private final int d;
    private final long e;

    public hr(long j, long j2, hf hfVar) {
        this.a = j2;
        this.b = hfVar.c;
        this.d = hfVar.f;
        if (j == -1) {
            this.c = -1;
            this.e = -9223372036854775807L;
            return;
        }
        this.c = j - j2;
        this.e = a(j);
    }

    public boolean a() {
        return this.c != -1;
    }

    public hh.a b(long j) {
        if (this.c == -1) {
            return new hh.a(new hi(0, this.a));
        }
        long a = z.a((((((long) this.d) * j) / 8000000) / ((long) this.b)) * ((long) this.b), 0, this.c - ((long) this.b));
        long j2 = this.a + a;
        long a2 = a(j2);
        hi hiVar = new hi(a2, j2);
        if (a2 >= j || a == this.c - ((long) this.b)) {
            return new hh.a(hiVar);
        }
        j2 += (long) this.b;
        return new hh.a(hiVar, new hi(a(j2), j2));
    }

    public long a(long j) {
        return ((Math.max(0, j - this.a) * 1000000) * 8) / ((long) this.d);
    }

    public long b() {
        return this.e;
    }
}
