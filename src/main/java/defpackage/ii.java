package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.a.a;
import com.google.android.exoplayer2.util.m;
import com.google.android.exoplayer2.util.n;
import defpackage.jd.d;

/* compiled from: Ac3Reader */
/* renamed from: ii */
public final class ii implements io {
    private final m a;
    private final n b;
    private final String c;
    private String d;
    private hj e;
    private int f;
    private int g;
    private boolean h;
    private long i;
    private Format j;
    private int k;
    private long l;

    public void b() {
    }

    public ii() {
        this(null);
    }

    public ii(String str) {
        this.a = new m(new byte[128]);
        this.b = new n(this.a.a);
        this.f = 0;
        this.c = str;
    }

    public void a() {
        this.f = 0;
        this.g = 0;
        this.h = false;
    }

    public void a(hb hbVar, d dVar) {
        dVar.a();
        this.d = dVar.c();
        this.e = hbVar.a(dVar.b(), 1);
    }

    public void a(long j, boolean z) {
        this.l = j;
    }

    public void a(n nVar) {
        while (nVar.b() > 0) {
            switch (this.f) {
                case 0:
                    if (!b(nVar)) {
                        break;
                    }
                    this.f = 1;
                    this.b.a[0] = (byte) 11;
                    this.b.a[1] = (byte) 119;
                    this.g = 2;
                    break;
                case 1:
                    if (!a(nVar, this.b.a, 128)) {
                        break;
                    }
                    c();
                    this.b.c(0);
                    this.e.a(this.b, 128);
                    this.f = 2;
                    break;
                case 2:
                    int min = Math.min(nVar.b(), this.k - this.g);
                    this.e.a(nVar, min);
                    this.g += min;
                    if (this.g != this.k) {
                        break;
                    }
                    this.e.a(this.l, 1, this.k, 0, null);
                    this.l += this.i;
                    this.f = 0;
                    break;
                default:
                    break;
            }
        }
    }

    private boolean a(n nVar, byte[] bArr, int i) {
        int min = Math.min(nVar.b(), i - this.g);
        nVar.a(bArr, this.g, min);
        this.g += min;
        return this.g == i;
    }

    private boolean b(n nVar) {
        while (true) {
            boolean z = false;
            if (nVar.b() <= 0) {
                return false;
            }
            if (this.h) {
                int g = nVar.g();
                if (g == 119) {
                    this.h = false;
                    return true;
                }
                if (g == 11) {
                    z = true;
                }
                this.h = z;
            } else {
                if (nVar.g() == 11) {
                    z = true;
                }
                this.h = z;
            }
        }
    }

    private void c() {
        this.a.a(0);
        a a = com.google.android.exoplayer2.audio.a.a(this.a);
        if (!(this.j != null && a.d == this.j.s && a.c == this.j.t && a.a == this.j.f)) {
            this.j = Format.a(this.d, a.a, null, -1, -1, a.d, a.c, null, null, 0, this.c);
            this.e.a(this.j);
        }
        this.k = a.e;
        this.i = (((long) a.f) * 1000000) / ((long) this.j.t);
    }
}
