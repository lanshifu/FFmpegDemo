package defpackage;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.h;
import com.google.android.exoplayer2.l;
import com.google.android.exoplayer2.source.b;
import com.google.android.exoplayer2.source.d;
import com.google.android.exoplayer2.source.i;
import com.google.android.exoplayer2.source.j;
import com.google.android.exoplayer2.source.k;
import com.google.android.exoplayer2.source.p;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.Loader.a;
import com.google.android.exoplayer2.upstream.e;
import com.google.android.exoplayer2.upstream.n;
import com.google.android.exoplayer2.upstream.o;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: SsMediaSource */
/* renamed from: kl */
public final class kl extends b implements a<o<km>> {
    private final boolean a;
    private final Uri b;
    private final e.a c;
    private final kj.a d;
    private final d e;
    private final int f;
    private final long g;
    private final k.a h;
    private final o.a<? extends km> i;
    private final ArrayList<kk> j;
    @Nullable
    private final Object k;
    private e l;
    private Loader m;
    private n n;
    private long o;
    private km p;
    private Handler q;

    /* compiled from: SsMediaSource */
    /* renamed from: kl$1 */
    class 1 implements Runnable {
        1() {
        }

        public void run() {
            kl.this.e();
        }
    }

    static {
        l.a("goog.exo.smoothstreaming");
    }

    public void a(h hVar, boolean z) {
        if (this.a) {
            this.n = new n.a();
            c();
            return;
        }
        this.l = this.c.a();
        this.m = new Loader("Loader:Manifest");
        this.n = this.m;
        this.q = new Handler();
        e();
    }

    public void b() throws IOException {
        this.n.d();
    }

    public i a(j.a aVar, com.google.android.exoplayer2.upstream.b bVar) {
        com.google.android.exoplayer2.util.a.a(aVar.a == 0);
        kk kkVar = new kk(this.p, this.d, this.e, this.f, a(aVar), this.n, bVar);
        this.j.add(kkVar);
        return kkVar;
    }

    public void a(i iVar) {
        ((kk) iVar).f();
        this.j.remove(iVar);
    }

    public void a() {
        this.p = this.a ? this.p : null;
        this.l = null;
        this.o = 0;
        if (this.m != null) {
            this.m.c();
            this.m = null;
        }
        if (this.q != null) {
            this.q.removeCallbacksAndMessages(null);
            this.q = null;
        }
    }

    public void a(o<km> oVar, long j, long j2) {
        this.h.a(oVar.a, oVar.b, j, j2, oVar.d());
        this.p = (km) oVar.c();
        this.o = j - j2;
        c();
        d();
    }

    public void a(o<km> oVar, long j, long j2, boolean z) {
        this.h.b(oVar.a, oVar.b, j, j2, oVar.d());
    }

    public int a(o<km> oVar, long j, long j2, IOException iOException) {
        o<km> oVar2 = oVar;
        IOException iOException2 = iOException;
        boolean z = iOException2 instanceof ParserException;
        this.h.a(oVar2.a, oVar2.b, j, j2, oVar.d(), iOException2, z);
        return z ? 3 : 0;
    }

    private void c() {
        int i;
        for (i = 0; i < this.j.size(); i++) {
            ((kk) this.j.get(i)).a(this.p);
        }
        long j = Long.MIN_VALUE;
        long j2 = Long.MAX_VALUE;
        for (km.b bVar : this.p.c) {
            if (bVar.d > 0) {
                long min = Math.min(j2, bVar.a(0));
                j = Math.max(j, bVar.a(bVar.d - 1) + bVar.b(bVar.d - 1));
                j2 = min;
            }
        }
        if (j2 == Long.MAX_VALUE) {
            p pVar = new p(this.p.a ? -9223372036854775807L : 0, 0, 0, 0, true, this.p.a, this.k);
        } else if (this.p.a) {
            if (this.p.e != -9223372036854775807L && this.p.e > 0) {
                j2 = Math.max(j2, j - this.p.e);
            }
            long j3 = j2;
            long j4 = j - j3;
            long b = j4 - com.google.android.exoplayer2.b.b(this.g);
            if (b < 5000000) {
                b = Math.min(5000000, j4 / 2);
            }
            p pVar2 = new p(-9223372036854775807L, j4, j3, b, true, true, this.k);
        } else {
            long j5 = this.p.d != -9223372036854775807L ? this.p.d : j - j2;
            p pVar3 = new p(j2 + j5, j5, j2, 0, true, false, this.k);
        }
        a(r1, this.p);
    }

    private void d() {
        if (this.p.a) {
            this.q.postDelayed(new 1(), Math.max(0, (this.o + 5000) - SystemClock.elapsedRealtime()));
        }
    }

    private void e() {
        o oVar = new o(this.l, this.b, 4, this.i);
        this.h.a(oVar.a, oVar.b, this.m.a(oVar, this, this.f));
    }
}
