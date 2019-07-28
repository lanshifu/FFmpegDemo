package defpackage;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.m;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.w;
import com.google.android.exoplayer2.util.z;
import defpackage.hh.b;
import defpackage.jd.a;
import defpackage.jd.c;
import defpackage.jd.d;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: TsExtractor */
/* renamed from: jc */
public final class jc implements gz {
    public static final hc a = new 1();
    private static final long b = ((long) z.g("AC-3"));
    private static final long c = ((long) z.g("EAC3"));
    private static final long d = ((long) z.g("HEVC"));
    private final int e;
    private final List<w> f;
    private final n g;
    private final SparseIntArray h;
    private final c i;
    private final SparseArray<jd> j;
    private final SparseBooleanArray k;
    private hb l;
    private int m;
    private boolean n;
    private jd o;
    private int p;

    /* compiled from: TsExtractor */
    /* renamed from: jc$1 */
    static class 1 implements hc {
        1() {
        }

        public gz[] a() {
            return new gz[]{new jc()};
        }
    }

    /* compiled from: TsExtractor */
    /* renamed from: jc$a */
    private class a implements iy {
        private final m b = new m(new byte[4]);

        public void a(w wVar, hb hbVar, d dVar) {
        }

        public void a(n nVar) {
            if (nVar.g() == 0) {
                nVar.d(7);
                int b = nVar.b() / 4;
                for (int i = 0; i < b; i++) {
                    nVar.a(this.b, 4);
                    int c = this.b.c(16);
                    this.b.b(3);
                    if (c == 0) {
                        this.b.b(13);
                    } else {
                        c = this.b.c(13);
                        jc.this.j.put(c, new iz(new b(c)));
                        jc.this.m = jc.this.m + 1;
                    }
                }
                if (jc.this.e != 2) {
                    jc.this.j.remove(0);
                }
            }
        }
    }

    /* compiled from: TsExtractor */
    /* renamed from: jc$b */
    private class b implements iy {
        private final m b = new m(new byte[5]);
        private final SparseArray<jd> c = new SparseArray();
        private final SparseIntArray d = new SparseIntArray();
        private final int e;

        public void a(w wVar, hb hbVar, d dVar) {
        }

        public b(int i) {
            this.e = i;
        }

        public void a(n nVar) {
            n nVar2 = nVar;
            if (nVar.g() == 2) {
                w wVar;
                int i = 0;
                if (jc.this.e == 1 || jc.this.e == 2 || jc.this.m == 1) {
                    wVar = (w) jc.this.f.get(0);
                } else {
                    wVar = new w(((w) jc.this.f.get(0)).a());
                    jc.this.f.add(wVar);
                }
                nVar2.d(2);
                int h = nVar.h();
                int i2 = 5;
                nVar2.d(5);
                nVar2.a(this.b, 2);
                int i3 = 4;
                this.b.b(4);
                nVar2.d(this.b.c(12));
                if (jc.this.e == 2 && jc.this.o == null) {
                    jc.this.o = jc.this.i.a(21, new defpackage.jd.b(21, null, null, new byte[0]));
                    jc.this.o.a(wVar, jc.this.l, new d(h, 21, 8192));
                }
                this.c.clear();
                this.d.clear();
                int b = nVar.b();
                while (b > 0) {
                    nVar2.a(this.b, i2);
                    int c = this.b.c(8);
                    this.b.b(3);
                    int c2 = this.b.c(13);
                    this.b.b(i3);
                    int c3 = this.b.c(12);
                    defpackage.jd.b a = a(nVar2, c3);
                    if (c == 6) {
                        c = a.a;
                    }
                    b -= c3 + 5;
                    i3 = jc.this.e == 2 ? c : c2;
                    if (!jc.this.k.get(i3)) {
                        Object f;
                        if (jc.this.e == 2 && c == 21) {
                            f = jc.this.o;
                        } else {
                            f = jc.this.i.a(c, a);
                        }
                        if (jc.this.e != 2 || c2 < this.d.get(i3, 8192)) {
                            this.d.put(i3, c2);
                            this.c.put(i3, f);
                        }
                    }
                    i2 = 5;
                    i3 = 4;
                }
                int size = this.d.size();
                for (i2 = 0; i2 < size; i2++) {
                    b = this.d.keyAt(i2);
                    jc.this.k.put(b, true);
                    jd jdVar = (jd) this.c.valueAt(i2);
                    if (jdVar != null) {
                        if (jdVar != jc.this.o) {
                            jdVar.a(wVar, jc.this.l, new d(h, b, 8192));
                        }
                        jc.this.j.put(this.d.valueAt(i2), jdVar);
                    }
                }
                if (jc.this.e != 2) {
                    jc.this.j.remove(this.e);
                    jc jcVar = jc.this;
                    if (jc.this.e != 1) {
                        i = jc.this.m - 1;
                    }
                    jcVar.m = i;
                    if (jc.this.m == 0) {
                        jc.this.l.a();
                        jc.this.n = true;
                    }
                } else if (!jc.this.n) {
                    jc.this.l.a();
                    jc.this.m = 0;
                    jc.this.n = true;
                }
            }
        }

        private defpackage.jd.b a(n nVar, int i) {
            int d = nVar.d();
            i += d;
            String str = null;
            int i2 = -1;
            List list = null;
            while (nVar.d() < i) {
                int g = nVar.g();
                int d2 = nVar.d() + nVar.g();
                if (g == 5) {
                    long m = nVar.m();
                    if (m != jc.b) {
                        if (m != jc.c) {
                            if (m == jc.d) {
                                i2 = 36;
                            }
                            nVar.d(d2 - nVar.d());
                        }
                        i2 = 135;
                        nVar.d(d2 - nVar.d());
                    }
                } else if (g != 106) {
                    if (g != IjkMediaMeta.FF_PROFILE_H264_HIGH_422) {
                        if (g == 123) {
                            i2 = 138;
                        } else if (g == 10) {
                            str = nVar.e(3).trim();
                        } else if (g == 89) {
                            ArrayList arrayList = new ArrayList();
                            while (nVar.d() < d2) {
                                String trim = nVar.e(3).trim();
                                g = nVar.g();
                                byte[] bArr = new byte[4];
                                nVar.a(bArr, 0, 4);
                                arrayList.add(new a(trim, g, bArr));
                            }
                            list = arrayList;
                            i2 = 89;
                        }
                        nVar.d(d2 - nVar.d());
                    }
                    i2 = 135;
                    nVar.d(d2 - nVar.d());
                }
                i2 = 129;
                nVar.d(d2 - nVar.d());
            }
            nVar.c(i);
            return new defpackage.jd.b(i2, str, list, Arrays.copyOfRange(nVar.a, d, i));
        }
    }

    public void c() {
    }

    public jc() {
        this(0);
    }

    public jc(int i) {
        this(1, i);
    }

    public jc(int i, int i2) {
        this(i, new w(0), new il(i2));
    }

    public jc(int i, w wVar, c cVar) {
        this.i = (c) com.google.android.exoplayer2.util.a.a(cVar);
        this.e = i;
        if (i == 1 || i == 2) {
            this.f = Collections.singletonList(wVar);
        } else {
            this.f = new ArrayList();
            this.f.add(wVar);
        }
        this.g = new n(new byte[9400], 0);
        this.k = new SparseBooleanArray();
        this.j = new SparseArray();
        this.h = new SparseIntArray();
        e();
    }

    public boolean a(ha haVar) throws IOException, InterruptedException {
        byte[] bArr = this.g.a;
        haVar.c(bArr, 0, 940);
        int i = 0;
        while (i < 188) {
            int i2 = 0;
            while (i2 != 5) {
                if (bArr[(i2 * 188) + i] != (byte) 71) {
                    i++;
                } else {
                    i2++;
                }
            }
            haVar.b(i);
            return true;
        }
        return false;
    }

    public void a(hb hbVar) {
        this.l = hbVar;
        hbVar.a(new b(-9223372036854775807L));
    }

    public void a(long j, long j2) {
        int size = this.f.size();
        for (int i = 0; i < size; i++) {
            ((w) this.f.get(i)).d();
        }
        this.g.a();
        this.h.clear();
        e();
        this.p = 0;
    }

    public int a(ha haVar, hg hgVar) throws IOException, InterruptedException {
        int b;
        int a;
        byte[] bArr = this.g.a;
        if (9400 - this.g.d() < 188) {
            b = this.g.b();
            if (b > 0) {
                System.arraycopy(bArr, this.g.d(), bArr, 0, b);
            }
            this.g.a(bArr, b);
        }
        while (this.g.b() < 188) {
            b = this.g.c();
            a = haVar.a(bArr, b, 9400 - b);
            if (a == -1) {
                return -1;
            }
            this.g.b(b + a);
        }
        int c = this.g.c();
        b = this.g.d();
        int i = b;
        while (i < c && bArr[i] != (byte) 71) {
            i++;
        }
        this.g.c(i);
        int i2 = i + 188;
        if (i2 > c) {
            this.p += i - b;
            if (this.e != 2 || this.p <= 376) {
                return 0;
            }
            throw new ParserException("Cannot find sync byte. Most likely not a Transport Stream.");
        }
        this.p = 0;
        b = this.g.o();
        if ((8388608 & b) != 0) {
            this.g.c(i2);
            return 0;
        }
        boolean z = (4194304 & b) != 0;
        int i3 = (2096896 & b) >> 8;
        Object obj = (b & 32) != 0 ? 1 : null;
        jd jdVar = ((b & 16) != 0 ? 1 : null) != null ? (jd) this.j.get(i3) : null;
        if (jdVar == null) {
            this.g.c(i2);
            return 0;
        }
        if (this.e != 2) {
            b &= 15;
            a = this.h.get(i3, b - 1);
            this.h.put(i3, b);
            if (a == b) {
                this.g.c(i2);
                return 0;
            } else if (b != ((a + 1) & 15)) {
                jdVar.a();
            }
        }
        if (obj != null) {
            this.g.d(this.g.g());
        }
        this.g.b(i2);
        jdVar.a(this.g, z);
        this.g.b(c);
        this.g.c(i2);
        return 0;
    }

    private void e() {
        this.k.clear();
        this.j.clear();
        SparseArray a = this.i.a();
        int size = a.size();
        for (int i = 0; i < size; i++) {
            this.j.put(a.keyAt(i), a.valueAt(i));
        }
        this.j.put(0, new iz(new a()));
        this.o = null;
    }
}
