package defpackage;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ab;
import com.google.android.exoplayer2.extractor.mp4.d;
import com.google.android.exoplayer2.extractor.mp4.i;
import com.google.android.exoplayer2.extractor.mp4.j;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.trackselection.e;
import com.google.android.exoplayer2.upstream.g;
import com.google.android.exoplayer2.upstream.n;
import com.google.android.exoplayer2.util.z;
import defpackage.km.b;
import java.io.IOException;
import java.util.List;

/* compiled from: DefaultSsChunkSource */
/* renamed from: ki */
public class ki implements kj {
    private final n a;
    private final int b;
    private final e c;
    private final jl[] d;
    private final com.google.android.exoplayer2.upstream.e e;
    private km f;
    private int g;
    private IOException h;

    /* compiled from: DefaultSsChunkSource */
    /* renamed from: ki$a */
    public static final class a implements defpackage.kj.a {
        private final com.google.android.exoplayer2.upstream.e.a a;

        public a(com.google.android.exoplayer2.upstream.e.a aVar) {
            this.a = aVar;
        }

        public kj a(n nVar, km kmVar, int i, e eVar, j[] jVarArr) {
            return new ki(nVar, kmVar, i, eVar, this.a.a(), jVarArr);
        }
    }

    public void a(jk jkVar) {
    }

    public ki(n nVar, km kmVar, int i, e eVar, com.google.android.exoplayer2.upstream.e eVar2, j[] jVarArr) {
        km kmVar2 = kmVar;
        int i2 = i;
        e eVar3 = eVar;
        this.a = nVar;
        this.f = kmVar2;
        this.b = i2;
        this.c = eVar3;
        this.e = eVar2;
        b bVar = kmVar2.c[i2];
        this.d = new jl[eVar.g()];
        int i3 = 0;
        while (i3 < this.d.length) {
            int b = eVar3.b(i3);
            Format format = bVar.c[b];
            int i4 = i3;
            i iVar = r7;
            i iVar2 = new i(b, bVar.a, bVar.b, -9223372036854775807L, kmVar2.d, format, 0, jVarArr, bVar.a == 2 ? 4 : 0, null, null);
            this.d[i4] = new jl(new d(3, null, iVar, null), bVar.a, format);
            i3 = i4 + 1;
        }
    }

    public long a(long j, ab abVar) {
        b bVar = this.f.c[this.b];
        int a = bVar.a(j);
        long a2 = bVar.a(a);
        long a3 = (a2 >= j || a >= bVar.d - 1) ? a2 : bVar.a(a + 1);
        return z.a(j, abVar, a2, a3);
    }

    public void a(km kmVar) {
        b bVar = this.f.c[this.b];
        int i = bVar.d;
        b bVar2 = kmVar.c[this.b];
        if (i == 0 || bVar2.d == 0) {
            this.g += i;
        } else {
            int i2 = i - 1;
            long a = bVar.a(i2) + bVar.b(i2);
            long a2 = bVar2.a(0);
            if (a <= a2) {
                this.g += i;
            } else {
                this.g += bVar.a(a2);
            }
        }
        this.f = kmVar;
    }

    public void a() throws IOException {
        if (this.h == null) {
            this.a.d();
            return;
        }
        throw this.h;
    }

    public int a(long j, List<? extends jt> list) {
        if (this.h != null || this.c.g() < 2) {
            return list.size();
        }
        return this.c.a(j, list);
    }

    public final void a(jt jtVar, long j, long j2, jm jmVar) {
        long j3 = j;
        long j4 = j2;
        jm jmVar2 = jmVar;
        if (this.h == null) {
            b bVar = this.f.c[this.b];
            if (bVar.d == 0) {
                jmVar2.b = this.f.a ^ 1;
                return;
            }
            int a;
            if (jtVar == null) {
                a = bVar.a(j4);
            } else {
                a = (int) (jtVar.f() - ((long) this.g));
                if (a < 0) {
                    this.h = new BehindLiveWindowException();
                    return;
                }
            }
            int i = a;
            if (i >= bVar.d) {
                jmVar2.b = this.f.a ^ 1;
                return;
            }
            this.c.a(j, j4 - j3, a(j3));
            long a2 = bVar.a(i);
            long b = a2 + bVar.b(i);
            long j5 = jtVar == null ? j4 : -9223372036854775807L;
            int i2 = i + this.g;
            int a3 = this.c.a();
            jmVar2.a = ki.a(this.c.h(), this.e, bVar.a(this.c.b(a3), i), null, i2, a2, b, j5, this.c.b(), this.c.c(), this.d[a3]);
        }
    }

    public boolean a(jk jkVar, boolean z, Exception exception) {
        return z && jp.a(this.c, this.c.a(jkVar.d), exception);
    }

    private static jt a(Format format, com.google.android.exoplayer2.upstream.e eVar, Uri uri, String str, int i, long j, long j2, long j3, int i2, Object obj, jl jlVar) {
        Format format2 = format;
        com.google.android.exoplayer2.upstream.e eVar2 = eVar;
        long j4 = j;
        long j5 = j;
        long j6 = j2;
        long j7 = j3;
        int i3 = i2;
        Object obj2 = obj;
        jl jlVar2 = jlVar;
        g gVar = r25;
        g gVar2 = new g(uri, 0, -1, str);
        return new jq(eVar2, gVar, format2, i3, obj2, j4, j6, j7, (long) i, 1, j5, jlVar2);
    }

    private long a(long j) {
        if (!this.f.a) {
            return -9223372036854775807L;
        }
        b bVar = this.f.c[this.b];
        int i = bVar.d - 1;
        return (bVar.a(i) + bVar.b(i)) - j;
    }
}
