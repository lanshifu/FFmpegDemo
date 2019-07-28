package defpackage;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ab;
import com.google.android.exoplayer2.source.k;
import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.source.n;
import com.google.android.exoplayer2.source.o;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.Loader.a;
import com.google.android.exoplayer2.upstream.Loader.d;
import com.google.android.exoplayer2.upstream.b;
import com.google.android.exoplayer2.util.z;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: ChunkSampleStream */
/* renamed from: jn */
public class jn<T extends jo> implements n, o, a<jk>, d {
    public final int a;
    long b;
    boolean c;
    private final int[] d;
    private final Format[] e;
    private final boolean[] f;
    private final T g;
    private final o.a<jn<T>> h;
    private final k.a i;
    private final int j;
    private final Loader k = new Loader("Loader:ChunkSampleStream");
    private final jm l = new jm();
    private final ArrayList<ji> m = new ArrayList();
    private final List<ji> n = Collections.unmodifiableList(this.m);
    private final m o;
    private final m[] p;
    private final jj q;
    private Format r;
    @Nullable
    private b<T> s;
    private long t;
    private long u;

    /* compiled from: ChunkSampleStream */
    /* renamed from: jn$b */
    public interface b<T extends jo> {
        void a(jn<T> jnVar);
    }

    /* compiled from: ChunkSampleStream */
    /* renamed from: jn$a */
    public final class a implements n {
        public final jn<T> a;
        private final m c;
        private final int d;
        private boolean e;

        public void c() throws IOException {
        }

        public a(jn<T> jnVar, m mVar, int i) {
            this.a = jnVar;
            this.c = mVar;
            this.d = i;
        }

        public boolean b() {
            return jn.this.c || (!jn.this.h() && this.c.d());
        }

        public int a_(long j) {
            int b;
            if (!jn.this.c || j <= this.c.i()) {
                b = this.c.b(j, true, true);
                if (b == -1) {
                    b = 0;
                }
            } else {
                b = this.c.n();
            }
            if (b > 0) {
                d();
            }
            return b;
        }

        public int a(com.google.android.exoplayer2.m mVar, gs gsVar, boolean z) {
            if (jn.this.h()) {
                return -3;
            }
            int a = this.c.a(mVar, gsVar, z, jn.this.c, jn.this.b);
            if (a == -4) {
                d();
            }
            return a;
        }

        public void a() {
            com.google.android.exoplayer2.util.a.b(jn.this.f[this.d]);
            jn.this.f[this.d] = false;
        }

        private void d() {
            if (!this.e) {
                jn.this.i.a(jn.this.d[this.d], jn.this.e[this.d], 0, null, jn.this.u);
                this.e = true;
            }
        }
    }

    public jn(int i, int[] iArr, Format[] formatArr, T t, o.a<jn<T>> aVar, b bVar, long j, int i2, k.a aVar2) {
        int i3;
        this.a = i;
        this.d = iArr;
        this.e = formatArr;
        this.g = t;
        this.h = aVar;
        this.i = aVar2;
        this.j = i2;
        int i4 = 0;
        if (iArr == null) {
            i3 = 0;
        } else {
            i3 = iArr.length;
        }
        this.p = new m[i3];
        this.f = new boolean[i3];
        int i5 = i3 + 1;
        int[] iArr2 = new int[i5];
        m[] mVarArr = new m[i5];
        this.o = new m(bVar);
        iArr2[0] = i;
        mVarArr[0] = this.o;
        while (i4 < i3) {
            m mVar = new m(bVar);
            this.p[i4] = mVar;
            int i6 = i4 + 1;
            mVarArr[i6] = mVar;
            iArr2[i6] = iArr[i4];
            i4 = i6;
        }
        this.q = new jj(iArr2, mVarArr);
        this.t = j;
        this.u = j;
    }

    public void a(long j, boolean z) {
        int e = this.o.e();
        this.o.a(j, z, true);
        int e2 = this.o.e();
        if (e2 > e) {
            long j2 = this.o.j();
            for (int i = 0; i < this.p.length; i++) {
                this.p[i].a(j2, z, this.f[i]);
            }
            b(e2);
        }
    }

    public a a(long j, int i) {
        for (int i2 = 0; i2 < this.p.length; i2++) {
            if (this.d[i2] == i) {
                com.google.android.exoplayer2.util.a.b(this.f[i2] ^ 1);
                this.f[i2] = true;
                this.p[i2].k();
                this.p[i2].b(j, true, true);
                return new a(this, this.p[i2], i2);
            }
        }
        throw new IllegalStateException();
    }

    public T a() {
        return this.g;
    }

    public long d() {
        if (this.c) {
            return Long.MIN_VALUE;
        }
        if (h()) {
            return this.t;
        }
        long j = this.u;
        ji i = i();
        if (!i.g()) {
            i = this.m.size() > 1 ? (ji) this.m.get(this.m.size() - 2) : null;
        }
        if (i != null) {
            j = Math.max(j, i.h);
        }
        return Math.max(j, this.o.i());
    }

    public long a(long j, ab abVar) {
        return this.g.a(j, abVar);
    }

    public void b(long j) {
        boolean z;
        int i;
        this.u = j;
        this.o.k();
        int i2 = 0;
        if (h()) {
            z = false;
        } else {
            ji jiVar = null;
            i = 0;
            while (i < this.m.size()) {
                ji jiVar2 = (ji) this.m.get(i);
                long j2 = jiVar2.g;
                if (j2 == j && jiVar2.a == -9223372036854775807L) {
                    jiVar = jiVar2;
                    break;
                } else if (j2 > j) {
                    break;
                } else {
                    i++;
                }
            }
            if (jiVar != null) {
                z = this.o.c(jiVar.a(0));
                this.b = Long.MIN_VALUE;
            } else {
                z = this.o.b(j, true, (j > e() ? 1 : (j == e() ? 0 : -1)) < 0) != -1;
                this.b = this.u;
            }
        }
        if (z) {
            for (m mVar : this.p) {
                mVar.k();
                mVar.b(j, true, false);
            }
            return;
        }
        this.t = j;
        this.c = false;
        this.m.clear();
        if (this.k.a()) {
            this.k.b();
            return;
        }
        this.o.a();
        m[] mVarArr = this.p;
        int length = mVarArr.length;
        while (i2 < length) {
            mVarArr[i2].a();
            i2++;
        }
    }

    public void f() {
        a(null);
    }

    public void a(@Nullable b<T> bVar) {
        this.s = bVar;
        this.o.m();
        for (m m : this.p) {
            m.m();
        }
        this.k.a(this);
    }

    public void g() {
        this.o.a();
        for (m a : this.p) {
            a.a();
        }
        if (this.s != null) {
            this.s.a(this);
        }
    }

    public boolean b() {
        return this.c || (!h() && this.o.d());
    }

    public void c() throws IOException {
        this.k.d();
        if (!this.k.a()) {
            this.g.a();
        }
    }

    public int a(com.google.android.exoplayer2.m mVar, gs gsVar, boolean z) {
        if (h()) {
            return -3;
        }
        int a = this.o.a(mVar, gsVar, z, this.c, this.b);
        if (a == -4) {
            a(this.o.f(), 1);
        }
        return a;
    }

    public int a_(long j) {
        int i = 0;
        if (h()) {
            return 0;
        }
        if (!this.c || j <= this.o.i()) {
            int b = this.o.b(j, true, true);
            if (b != -1) {
                i = b;
            }
        } else {
            i = this.o.n();
        }
        if (i > 0) {
            a(this.o.f(), i);
        }
        return i;
    }

    public void a(jk jkVar, long j, long j2) {
        jk jkVar2 = jkVar;
        long j3 = j;
        long j4 = j2;
        this.g.a(jkVar2);
        this.i.a(jkVar2.b, jkVar2.c, this.a, jkVar2.d, jkVar2.e, jkVar2.f, jkVar2.g, jkVar2.h, j3, j4, jkVar.e());
        this.h.a(this);
    }

    public void a(jk jkVar, long j, long j2, boolean z) {
        jk jkVar2 = jkVar;
        this.i.b(jkVar2.b, jkVar2.c, this.a, jkVar2.d, jkVar2.e, jkVar2.f, jkVar2.g, jkVar2.h, j, j2, jkVar.e());
        if (!z) {
            this.o.a();
            for (m a : this.p) {
                a.a();
            }
            this.h.a(this);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0081 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007a  */
    public int a(defpackage.jk r24, long r25, long r27, java.io.IOException r29) {
        /*
        r23 = this;
        r0 = r23;
        r1 = r24;
        r17 = r24.e();
        r2 = r23.a(r24);
        r3 = r0.m;
        r3 = r3.size();
        r4 = 1;
        r3 = r3 - r4;
        r5 = 0;
        r7 = (r17 > r5 ? 1 : (r17 == r5 ? 0 : -1));
        r21 = 0;
        if (r7 == 0) goto L_0x0027;
    L_0x001c:
        if (r2 == 0) goto L_0x0027;
    L_0x001e:
        r5 = r0.a(r3);
        if (r5 != 0) goto L_0x0025;
    L_0x0024:
        goto L_0x0027;
    L_0x0025:
        r5 = 0;
        goto L_0x0028;
    L_0x0027:
        r5 = 1;
    L_0x0028:
        r6 = r0.g;
        r15 = r29;
        r6 = r6.a(r1, r5, r15);
        if (r6 == 0) goto L_0x0059;
    L_0x0032:
        if (r5 != 0) goto L_0x003c;
    L_0x0034:
        r2 = "ChunkSampleStream";
        r3 = "Ignoring attempt to cancel non-cancelable load.";
        android.util.Log.w(r2, r3);
        goto L_0x0059;
    L_0x003c:
        if (r2 == 0) goto L_0x0056;
    L_0x003e:
        r2 = r0.d(r3);
        if (r2 != r1) goto L_0x0046;
    L_0x0044:
        r2 = 1;
        goto L_0x0047;
    L_0x0046:
        r2 = 0;
    L_0x0047:
        com.google.android.exoplayer2.util.a.b(r2);
        r2 = r0.m;
        r2 = r2.isEmpty();
        if (r2 == 0) goto L_0x0056;
    L_0x0052:
        r2 = r0.u;
        r0.t = r2;
    L_0x0056:
        r22 = 1;
        goto L_0x005b;
    L_0x0059:
        r22 = 0;
    L_0x005b:
        r2 = r0.i;
        r3 = r1.b;
        r4 = r1.c;
        r5 = r0.a;
        r6 = r1.d;
        r7 = r1.e;
        r8 = r1.f;
        r9 = r1.g;
        r11 = r1.h;
        r13 = r25;
        r15 = r27;
        r19 = r29;
        r20 = r22;
        r2.a(r3, r4, r5, r6, r7, r8, r9, r11, r13, r15, r17, r19, r20);
        if (r22 == 0) goto L_0x0081;
    L_0x007a:
        r1 = r0.h;
        r1.a(r0);
        r1 = 2;
        return r1;
    L_0x0081:
        return r21;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jn.a(jk, long, long, java.io.IOException):int");
    }

    public boolean c(long j) {
        boolean z = false;
        if (this.c || this.k.a()) {
            return false;
        }
        jt jtVar;
        long j2;
        boolean h = h();
        if (h) {
            jtVar = null;
            j2 = this.t;
        } else {
            jtVar = i();
            j2 = jtVar.h;
        }
        this.g.a(jtVar, j, j2, this.l);
        boolean z2 = this.l.b;
        jk jkVar = this.l.a;
        this.l.a();
        if (z2) {
            this.t = -9223372036854775807L;
            this.c = true;
            return true;
        } else if (jkVar == null) {
            return false;
        } else {
            if (a(jkVar)) {
                ji jiVar = (ji) jkVar;
                if (h) {
                    long j3;
                    if (jiVar.g == this.t) {
                        z = true;
                    }
                    if (z) {
                        j3 = Long.MIN_VALUE;
                    } else {
                        j3 = this.t;
                    }
                    this.b = j3;
                    this.t = -9223372036854775807L;
                }
                jiVar.a(this.q);
                this.m.add(jiVar);
            }
            long a = this.k.a(jkVar, this, this.j);
            this.i.a(jkVar.b, jkVar.c, this.a, jkVar.d, jkVar.e, jkVar.f, jkVar.g, jkVar.h, a);
            return true;
        }
    }

    public long e() {
        if (h()) {
            return this.t;
        }
        return this.c ? Long.MIN_VALUE : i().h;
    }

    public void a(long j) {
        if (!this.k.a() && !h()) {
            int size = this.m.size();
            int a = this.g.a(j, this.n);
            if (size > a) {
                while (a < size) {
                    if (!a(a)) {
                        break;
                    }
                    a++;
                }
                a = size;
                if (a != size) {
                    long j2 = i().h;
                    ji d = d(a);
                    if (this.m.isEmpty()) {
                        this.t = this.u;
                    }
                    this.c = false;
                    this.i.a(this.a, d.g, j2);
                }
            }
        }
    }

    private boolean a(jk jkVar) {
        return jkVar instanceof ji;
    }

    private boolean a(int i) {
        ji jiVar = (ji) this.m.get(i);
        if (this.o.f() > jiVar.a(0)) {
            return true;
        }
        int i2 = 0;
        while (i2 < this.p.length) {
            int f = this.p[i2].f();
            i2++;
            if (f > jiVar.a(i2)) {
                return true;
            }
        }
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean h() {
        return this.t != -9223372036854775807L;
    }

    private void b(int i) {
        i = b(i, 0);
        if (i > 0) {
            z.a(this.m, 0, i);
        }
    }

    private void a(int i, int i2) {
        int b = b(i - i2, 0);
        if (i2 == 1) {
            i = b;
        } else {
            i = b(i - 1, b);
        }
        while (b <= i) {
            c(b);
            b++;
        }
    }

    private void c(int i) {
        ji jiVar = (ji) this.m.get(i);
        Format format = jiVar.d;
        if (!format.equals(this.r)) {
            this.i.a(this.a, format, jiVar.e, jiVar.f, jiVar.g);
        }
        this.r = format;
    }

    private int b(int i, int i2) {
        do {
            i2++;
            if (i2 >= this.m.size()) {
                return this.m.size() - 1;
            }
        } while (((ji) this.m.get(i2)).a(0) <= i);
        return i2 - 1;
    }

    private ji i() {
        return (ji) this.m.get(this.m.size() - 1);
    }

    private ji d(int i) {
        ji jiVar = (ji) this.m.get(i);
        z.a(this.m, i, this.m.size());
        int i2 = 0;
        this.o.b(jiVar.a(0));
        while (i2 < this.p.length) {
            m mVar = this.p[i2];
            i2++;
            mVar.b(jiVar.a(i2));
        }
        return jiVar;
    }
}
