package defpackage;

import android.support.annotation.Nullable;
import android.util.Base64;
import com.google.android.exoplayer2.ab;
import com.google.android.exoplayer2.extractor.mp4.j;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.d;
import com.google.android.exoplayer2.source.i;
import com.google.android.exoplayer2.source.k;
import com.google.android.exoplayer2.source.o;
import com.google.android.exoplayer2.source.o.a;
import com.google.android.exoplayer2.trackselection.e;
import com.google.android.exoplayer2.upstream.b;
import com.google.android.exoplayer2.upstream.n;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: SsMediaPeriod */
/* renamed from: kk */
final class kk implements i, a<jn<kj>> {
    private final kj.a a;
    private final n b;
    private final int c;
    private final k.a d;
    private final b e;
    private final TrackGroupArray f;
    private final j[] g;
    private final d h;
    @Nullable
    private i.a i;
    private km j;
    private jn<kj>[] k;
    private o l;
    private boolean m;

    public kk(km kmVar, kj.a aVar, d dVar, int i, k.a aVar2, n nVar, b bVar) {
        this.a = aVar;
        this.b = nVar;
        this.c = i;
        this.d = aVar2;
        this.e = bVar;
        this.h = dVar;
        this.f = kk.b(kmVar);
        km.a aVar3 = kmVar.b;
        if (aVar3 != null) {
            byte[] a = kk.a(aVar3.a);
            this.g = new j[]{new j(true, null, 8, a, 0, 0, null)};
        } else {
            this.g = null;
        }
        this.j = kmVar;
        this.k = kk.a(0);
        this.l = dVar.a(this.k);
        aVar2.a();
    }

    public void a(km kmVar) {
        this.j = kmVar;
        for (jn a : this.k) {
            ((kj) a.a()).a(kmVar);
        }
        this.i.a(this);
    }

    public void f() {
        for (jn f : this.k) {
            f.f();
        }
        this.i = null;
        this.d.b();
    }

    public void a(i.a aVar, long j) {
        this.i = aVar;
        aVar.a(this);
    }

    public void d_() throws IOException {
        this.b.d();
    }

    public TrackGroupArray b() {
        return this.f;
    }

    public long a(e[] eVarArr, boolean[] zArr, com.google.android.exoplayer2.source.n[] nVarArr, boolean[] zArr2, long j) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < eVarArr.length) {
            jn jnVar;
            if (nVarArr[i] != null) {
                jnVar = (jn) nVarArr[i];
                if (eVarArr[i] == null || !zArr[i]) {
                    jnVar.f();
                    nVarArr[i] = null;
                } else {
                    arrayList.add(jnVar);
                }
            }
            if (nVarArr[i] == null && eVarArr[i] != null) {
                jnVar = a(eVarArr[i], j);
                arrayList.add(jnVar);
                nVarArr[i] = jnVar;
                zArr2[i] = true;
            }
            i++;
        }
        this.k = kk.a(arrayList.size());
        arrayList.toArray(this.k);
        this.l = this.h.a(this.k);
        return j;
    }

    public void a(long j, boolean z) {
        for (jn a : this.k) {
            a.a(j, z);
        }
    }

    public void a(long j) {
        this.l.a(j);
    }

    public boolean c(long j) {
        return this.l.c(j);
    }

    public long e() {
        return this.l.e();
    }

    public long c() {
        if (!this.m) {
            this.d.c();
            this.m = true;
        }
        return -9223372036854775807L;
    }

    public long d() {
        return this.l.d();
    }

    public long b(long j) {
        for (jn b : this.k) {
            b.b(j);
        }
        return j;
    }

    public long a(long j, ab abVar) {
        for (jn jnVar : this.k) {
            if (jnVar.a == 2) {
                return jnVar.a(j, abVar);
            }
        }
        return j;
    }

    public void a(jn<kj> jnVar) {
        this.i.a(this);
    }

    private jn<kj> a(e eVar, long j) {
        int a = this.f.a(eVar.f());
        return new jn(this.j.c[a].a, null, null, this.a.a(this.b, this.j, a, eVar, this.g), this, this.e, j, this.c, this.d);
    }

    private static TrackGroupArray b(km kmVar) {
        TrackGroup[] trackGroupArr = new TrackGroup[kmVar.c.length];
        for (int i = 0; i < kmVar.c.length; i++) {
            trackGroupArr[i] = new TrackGroup(kmVar.c[i].c);
        }
        return new TrackGroupArray(trackGroupArr);
    }

    private static jn<kj>[] a(int i) {
        return new jn[i];
    }

    private static byte[] a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bArr.length; i += 2) {
            stringBuilder.append((char) bArr[i]);
        }
        String stringBuilder2 = stringBuilder.toString();
        bArr = Base64.decode(stringBuilder2.substring(stringBuilder2.indexOf("<KID>") + 5, stringBuilder2.indexOf("</KID>")), 0);
        kk.a(bArr, 0, 3);
        kk.a(bArr, 1, 2);
        kk.a(bArr, 4, 5);
        kk.a(bArr, 6, 7);
        return bArr;
    }

    private static void a(byte[] bArr, int i, int i2) {
        byte b = bArr[i];
        bArr[i] = bArr[i2];
        bArr[i2] = b;
    }
}
