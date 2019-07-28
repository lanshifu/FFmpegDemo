package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.e;
import com.google.android.exoplayer2.upstream.g;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.z;
import defpackage.jl.b;
import java.io.IOException;

/* compiled from: ContainerMediaChunk */
/* renamed from: jq */
public class jq extends ji {
    private final int k;
    private final long l;
    private final jl m;
    private volatile int n;
    private volatile boolean o;
    private volatile boolean p;

    public jq(e eVar, g gVar, Format format, int i, Object obj, long j, long j2, long j3, long j4, int i2, long j5, jl jlVar) {
        super(eVar, gVar, format, i, obj, j, j2, j3, j4);
        this.k = i2;
        this.l = j5;
        this.m = jlVar;
    }

    public long f() {
        return this.j + ((long) this.k);
    }

    public boolean g() {
        return this.p;
    }

    public final long e() {
        return (long) this.n;
    }

    public final void a() {
        this.o = true;
    }

    public final void b() throws IOException, InterruptedException {
        g a = this.b.a((long) this.n);
        ha gwVar;
        try {
            gwVar = new gw(this.i, a.c, this.i.a(a));
            if (this.n == 0) {
                b c = c();
                c.a(this.l);
                this.m.a(c, this.a == -9223372036854775807L ? 0 : this.a - this.l);
            }
            gz gzVar = this.m.a;
            boolean z = false;
            int i = 0;
            while (i == 0 && !this.o) {
                i = gzVar.a(gwVar, null);
            }
            if (i != 1) {
                z = true;
            }
            a.b(z);
            this.n = (int) (gwVar.c() - this.b.c);
            z.a(this.i);
            this.p = true;
        } catch (Throwable th) {
            z.a(this.i);
        }
    }
}
