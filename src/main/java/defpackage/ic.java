package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.n;
import java.io.IOException;

/* compiled from: StreamReader */
/* renamed from: ic */
abstract class ic {
    private final hy a = new hy();
    private hj b;
    private hb c;
    private ia d;
    private long e;
    private long f;
    private long g;
    private int h;
    private int i;
    private a j;
    private long k;
    private boolean l;
    private boolean m;

    /* compiled from: StreamReader */
    /* renamed from: ic$a */
    static class a {
        Format a;
        ia b;

        a() {
        }
    }

    /* compiled from: StreamReader */
    /* renamed from: ic$b */
    private static final class b implements ia {
        public long a(long j) {
            return 0;
        }

        public long a(ha haVar) throws IOException, InterruptedException {
            return -1;
        }

        private b() {
        }

        public hh c() {
            return new defpackage.hh.b(-9223372036854775807L);
        }
    }

    public abstract boolean a(n nVar, long j, a aVar) throws IOException, InterruptedException;

    public abstract long b(n nVar);

    /* Access modifiers changed, original: 0000 */
    public void a(hb hbVar, hj hjVar) {
        this.c = hbVar;
        this.b = hjVar;
        a(true);
    }

    /* Access modifiers changed, original: protected */
    public void a(boolean z) {
        if (z) {
            this.j = new a();
            this.f = 0;
            this.h = 0;
        } else {
            this.h = 1;
        }
        this.e = -1;
        this.g = 0;
    }

    /* Access modifiers changed, original: final */
    public final void a(long j, long j2) {
        this.a.a();
        if (j == 0) {
            a(this.l ^ 1);
        } else if (this.h != 0) {
            this.e = this.d.a(j2);
            this.h = 2;
        }
    }

    /* Access modifiers changed, original: final */
    public final int a(ha haVar, hg hgVar) throws IOException, InterruptedException {
        switch (this.h) {
            case 0:
                return a(haVar);
            case 1:
                haVar.b((int) this.f);
                this.h = 2;
                return 0;
            case 2:
                return b(haVar, hgVar);
            default:
                throw new IllegalStateException();
        }
    }

    private int a(ha haVar) throws IOException, InterruptedException {
        boolean z = true;
        while (z) {
            if (this.a.a(haVar)) {
                this.k = haVar.c() - this.f;
                z = a(this.a.c(), this.f, this.j);
                if (z) {
                    this.f = haVar.c();
                }
            } else {
                this.h = 3;
                return -1;
            }
        }
        this.i = this.j.a.t;
        if (!this.m) {
            this.b.a(this.j.a);
            this.m = true;
        }
        if (this.j.b != null) {
            this.d = this.j.b;
        } else if (haVar.d() == -1) {
            this.d = new b();
        } else {
            hz b = this.a.b();
            this.d = new hv(this.f, haVar.d(), this, b.h + b.i, b.c);
        }
        this.j = null;
        this.h = 2;
        this.a.d();
        return 0;
    }

    private int b(ha haVar, hg hgVar) throws IOException, InterruptedException {
        ha haVar2 = haVar;
        long a = this.d.a(haVar2);
        if (a >= 0) {
            hgVar.a = a;
            return 1;
        }
        if (a < -1) {
            c(-(a + 2));
        }
        if (!this.l) {
            this.c.a(this.d.c());
            this.l = true;
        }
        if (this.k > 0 || this.a.a(haVar2)) {
            this.k = 0;
            n c = this.a.c();
            a = b(c);
            if (a >= 0 && this.g + a >= this.e) {
                long a2 = a(this.g);
                this.b.a(c, c.c());
                this.b.a(a2, 1, c.c(), 0, null);
                this.e = -1;
            }
            this.g += a;
            return 0;
        }
        this.h = 3;
        return -1;
    }

    /* Access modifiers changed, original: protected */
    public long a(long j) {
        return (j * 1000000) / ((long) this.i);
    }

    /* Access modifiers changed, original: protected */
    public long b(long j) {
        return (((long) this.i) * j) / 1000000;
    }

    /* Access modifiers changed, original: protected */
    public void c(long j) {
        this.g = j;
    }
}
