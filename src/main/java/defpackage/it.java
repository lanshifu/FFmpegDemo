package defpackage;

import android.support.annotation.Nullable;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.c;
import com.google.android.exoplayer2.util.m;
import com.google.android.exoplayer2.util.n;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import defpackage.jd.d;
import java.util.Collections;

/* compiled from: LatmReader */
/* renamed from: it */
public final class it implements io {
    private final String a;
    private final n b = new n(Filter.K);
    private final m c = new m(this.b.a);
    private hj d;
    private Format e;
    private String f;
    private int g;
    private int h;
    private int i;
    private int j;
    private long k;
    private boolean l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private long q;
    private int r;
    private long s;
    private int t;

    public void b() {
    }

    public it(@Nullable String str) {
        this.a = str;
    }

    public void a() {
        this.g = 0;
        this.l = false;
    }

    public void a(hb hbVar, d dVar) {
        dVar.a();
        this.d = hbVar.a(dVar.b(), 1);
        this.f = dVar.c();
    }

    public void a(long j, boolean z) {
        this.k = j;
    }

    public void a(n nVar) throws ParserException {
        while (nVar.b() > 0) {
            int g;
            switch (this.g) {
                case 0:
                    if (nVar.g() != 86) {
                        break;
                    }
                    this.g = 1;
                    break;
                case 1:
                    g = nVar.g();
                    if ((g & 224) != 224) {
                        if (g == 86) {
                            break;
                        }
                        this.g = 0;
                        break;
                    }
                    this.j = g;
                    this.g = 2;
                    break;
                case 2:
                    this.i = ((this.j & -225) << 8) | nVar.g();
                    if (this.i > this.b.a.length) {
                        a(this.i);
                    }
                    this.h = 0;
                    this.g = 3;
                    break;
                case 3:
                    g = Math.min(nVar.b(), this.i - this.h);
                    nVar.a(this.c.a, this.h, g);
                    this.h += g;
                    if (this.h != this.i) {
                        break;
                    }
                    this.c.a(0);
                    a(this.c);
                    this.g = 0;
                    break;
                default:
                    break;
            }
        }
    }

    private void a(m mVar) throws ParserException {
        if (!mVar.e()) {
            this.l = true;
            b(mVar);
        } else if (!this.l) {
            return;
        }
        if (this.m != 0) {
            throw new ParserException();
        } else if (this.n == 0) {
            a(mVar, e(mVar));
            if (this.p) {
                mVar.b((int) this.q);
            }
        } else {
            throw new ParserException();
        }
    }

    private void b(m mVar) throws ParserException {
        m mVar2 = mVar;
        int c = mVar2.c(1);
        this.m = c == 1 ? mVar2.c(1) : 0;
        if (this.m == 0) {
            if (c == 1) {
                it.f(mVar);
            }
            if (mVar.e()) {
                this.n = mVar2.c(6);
                int c2 = mVar2.c(4);
                int c3 = mVar2.c(3);
                if (c2 == 0 && c3 == 0) {
                    if (c == 0) {
                        c3 = mVar.b();
                        int d = d(mVar);
                        mVar2.a(c3);
                        byte[] bArr = new byte[((d + 7) / 8)];
                        mVar2.a(bArr, 0, d);
                        Format a = Format.a(this.f, "audio/mp4a-latm", null, -1, -1, this.t, this.r, Collections.singletonList(bArr), null, 0, this.a);
                        if (!a.equals(this.e)) {
                            this.e = a;
                            this.s = 1024000000 / ((long) a.t);
                            this.d.a(a);
                        }
                    } else {
                        mVar2.b(((int) it.f(mVar)) - d(mVar));
                    }
                    c(mVar);
                    this.p = mVar.e();
                    this.q = 0;
                    if (this.p) {
                        if (c == 1) {
                            this.q = it.f(mVar);
                        } else {
                            boolean e;
                            do {
                                e = mVar.e();
                                this.q = (this.q << 8) + ((long) mVar2.c(8));
                            } while (e);
                        }
                    }
                    if (mVar.e()) {
                        mVar2.b(8);
                        return;
                    }
                    return;
                }
                throw new ParserException();
            }
            throw new ParserException();
        }
        throw new ParserException();
    }

    private void c(m mVar) {
        this.o = mVar.c(3);
        switch (this.o) {
            case 0:
                mVar.b(8);
                return;
            case 1:
                mVar.b(9);
                return;
            case 3:
            case 4:
            case 5:
                mVar.b(6);
                return;
            case 6:
            case 7:
                mVar.b(1);
                return;
            default:
                return;
        }
    }

    private int d(m mVar) throws ParserException {
        int a = mVar.a();
        Pair a2 = c.a(mVar, true);
        this.r = ((Integer) a2.first).intValue();
        this.t = ((Integer) a2.second).intValue();
        return a - mVar.a();
    }

    private int e(m mVar) throws ParserException {
        if (this.o == 0) {
            int i = 0;
            int c;
            do {
                c = mVar.c(8);
                i += c;
            } while (c == 255);
            return i;
        }
        throw new ParserException();
    }

    private void a(m mVar, int i) {
        int b = mVar.b();
        if ((b & 7) == 0) {
            this.b.c(b >> 3);
        } else {
            mVar.a(this.b.a, 0, i * 8);
            this.b.c(0);
        }
        this.d.a(this.b, i);
        this.d.a(this.k, 1, i, 0, null);
        this.k += this.s;
    }

    private void a(int i) {
        this.b.a(i);
        this.c.a(this.b.a);
    }

    private static long f(m mVar) {
        return (long) mVar.c((mVar.c(2) + 1) * 8);
    }
}
