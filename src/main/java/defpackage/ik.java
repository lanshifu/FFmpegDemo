package defpackage;

import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.c;
import com.google.android.exoplayer2.util.m;
import com.google.android.exoplayer2.util.n;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import defpackage.jd.d;
import java.util.Arrays;
import java.util.Collections;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: AdtsReader */
/* renamed from: ik */
public final class ik implements io {
    private static final byte[] a = new byte[]{(byte) 73, (byte) 68, (byte) 51};
    private final boolean b;
    private final m c;
    private final n d;
    private final String e;
    private String f;
    private hj g;
    private hj h;
    private int i;
    private int j;
    private int k;
    private boolean l;
    private boolean m;
    private long n;
    private int o;
    private long p;
    private hj q;
    private long r;

    public void b() {
    }

    public ik(boolean z) {
        this(z, null);
    }

    public ik(boolean z, String str) {
        this.c = new m(new byte[7]);
        this.d = new n(Arrays.copyOf(a, 10));
        c();
        this.b = z;
        this.e = str;
    }

    public void a() {
        c();
    }

    public void a(hb hbVar, d dVar) {
        dVar.a();
        this.f = dVar.c();
        this.g = hbVar.a(dVar.b(), 1);
        if (this.b) {
            dVar.a();
            this.h = hbVar.a(dVar.b(), 4);
            this.h.a(Format.a(dVar.c(), "application/id3", null, -1, null));
            return;
        }
        this.h = new gy();
    }

    public void a(long j, boolean z) {
        this.p = j;
    }

    public void a(n nVar) throws ParserException {
        while (nVar.b() > 0) {
            switch (this.i) {
                case 0:
                    b(nVar);
                    break;
                case 1:
                    if (!a(nVar, this.d.a, 10)) {
                        break;
                    }
                    f();
                    break;
                case 2:
                    if (!a(nVar, this.c.a, this.l ? 7 : 5)) {
                        break;
                    }
                    g();
                    break;
                case 3:
                    c(nVar);
                    break;
                default:
                    break;
            }
        }
    }

    private boolean a(n nVar, byte[] bArr, int i) {
        int min = Math.min(nVar.b(), i - this.j);
        nVar.a(bArr, this.j, min);
        this.j += min;
        return this.j == i;
    }

    private void c() {
        this.i = 0;
        this.j = 0;
        this.k = 256;
    }

    private void d() {
        this.i = 1;
        this.j = a.length;
        this.o = 0;
        this.d.c(0);
    }

    private void a(hj hjVar, long j, int i, int i2) {
        this.i = 3;
        this.j = i;
        this.q = hjVar;
        this.r = j;
        this.o = i2;
    }

    private void e() {
        this.i = 2;
        this.j = 0;
    }

    private void b(n nVar) {
        byte[] bArr = nVar.a;
        int d = nVar.d();
        int c = nVar.c();
        while (d < c) {
            int i = d + 1;
            d = bArr[d] & 255;
            if (this.k != IjkMediaMeta.FF_PROFILE_H264_CONSTRAINED || d < 240 || d == 255) {
                d |= this.k;
                if (d == 329) {
                    this.k = 768;
                } else if (d == 511) {
                    this.k = IjkMediaMeta.FF_PROFILE_H264_CONSTRAINED;
                } else if (d == 836) {
                    this.k = Filter.K;
                } else if (d == 1075) {
                    d();
                    nVar.c(i);
                    return;
                } else if (this.k != 256) {
                    this.k = 256;
                    i--;
                }
                d = i;
            } else {
                boolean z = true;
                if ((d & 1) != 0) {
                    z = false;
                }
                this.l = z;
                e();
                nVar.c(i);
                return;
            }
        }
        nVar.c(d);
    }

    private void f() {
        this.h.a(this.d, 10);
        this.d.c(6);
        a(this.h, 0, 10, this.d.t() + 10);
    }

    private void g() throws ParserException {
        int c;
        this.c.a(0);
        if (this.m) {
            this.c.b(10);
        } else {
            c = this.c.c(2) + 1;
            if (c != 2) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Detected audio object type: ");
                stringBuilder.append(c);
                stringBuilder.append(", but assuming AAC LC.");
                Log.w("AdtsReader", stringBuilder.toString());
                c = 2;
            }
            int c2 = this.c.c(4);
            this.c.b(1);
            byte[] a = c.a(c, c2, this.c.c(3));
            Pair a2 = c.a(a);
            Format a3 = Format.a(this.f, "audio/mp4a-latm", null, -1, -1, ((Integer) a2.second).intValue(), ((Integer) a2.first).intValue(), Collections.singletonList(a), null, 0, this.e);
            this.n = 1024000000 / ((long) a3.t);
            this.g.a(a3);
            this.m = true;
        }
        this.c.b(4);
        c = (this.c.c(13) - 2) - 5;
        if (this.l) {
            c -= 2;
        }
        int i = c;
        a(this.g, this.n, 0, i);
    }

    private void c(n nVar) {
        int min = Math.min(nVar.b(), this.o - this.j);
        this.q.a(nVar, min);
        this.j += min;
        if (this.j == this.o) {
            this.q.a(this.p, 1, this.o, 0, null);
            this.p += this.r;
            c();
        }
    }
}
