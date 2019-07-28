package defpackage;

import android.util.Log;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.m;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.w;
import defpackage.jd.d;

/* compiled from: PesReader */
/* renamed from: iw */
public final class iw implements jd {
    private final io a;
    private final m b = new m(new byte[10]);
    private int c = 0;
    private int d;
    private w e;
    private boolean f;
    private boolean g;
    private boolean h;
    private int i;
    private int j;
    private boolean k;
    private long l;

    public iw(io ioVar) {
        this.a = ioVar;
    }

    public void a(w wVar, hb hbVar, d dVar) {
        this.e = wVar;
        this.a.a(hbVar, dVar);
    }

    public final void a() {
        this.c = 0;
        this.d = 0;
        this.h = false;
        this.a.a();
    }

    public final void a(n nVar, boolean z) throws ParserException {
        if (z) {
            switch (this.c) {
                case 2:
                    Log.w("PesReader", "Unexpected start indicator reading extended header");
                    break;
                case 3:
                    if (this.j != -1) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Unexpected start indicator: expected ");
                        stringBuilder.append(this.j);
                        stringBuilder.append(" more bytes");
                        Log.w("PesReader", stringBuilder.toString());
                    }
                    this.a.b();
                    break;
            }
            a(1);
        }
        while (nVar.b() > 0) {
            int i = 0;
            switch (this.c) {
                case 0:
                    nVar.d(nVar.b());
                    break;
                case 1:
                    if (!a(nVar, this.b.a, 9)) {
                        break;
                    }
                    if (b()) {
                        i = 2;
                    }
                    a(i);
                    break;
                case 2:
                    if (a(nVar, this.b.a, Math.min(10, this.i)) && a(nVar, null, this.i)) {
                        c();
                        this.a.a(this.l, this.k);
                        a(3);
                        break;
                    }
                case 3:
                    int b = nVar.b();
                    if (this.j != -1) {
                        i = b - this.j;
                    }
                    if (i > 0) {
                        b -= i;
                        nVar.b(nVar.d() + b);
                    }
                    this.a.a(nVar);
                    if (this.j == -1) {
                        break;
                    }
                    this.j -= b;
                    if (this.j != 0) {
                        break;
                    }
                    this.a.b();
                    a(1);
                    break;
                default:
                    break;
            }
        }
    }

    private void a(int i) {
        this.c = i;
        this.d = 0;
    }

    private boolean a(n nVar, byte[] bArr, int i) {
        int min = Math.min(nVar.b(), i - this.d);
        boolean z = true;
        if (min <= 0) {
            return true;
        }
        if (bArr == null) {
            nVar.d(min);
        } else {
            nVar.a(bArr, this.d, min);
        }
        this.d += min;
        if (this.d != i) {
            z = false;
        }
        return z;
    }

    private boolean b() {
        this.b.a(0);
        int c = this.b.c(24);
        if (c != 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unexpected start code prefix: ");
            stringBuilder.append(c);
            Log.w("PesReader", stringBuilder.toString());
            this.j = -1;
            return false;
        }
        this.b.b(8);
        c = this.b.c(16);
        this.b.b(5);
        this.k = this.b.e();
        this.b.b(2);
        this.f = this.b.e();
        this.g = this.b.e();
        this.b.b(6);
        this.i = this.b.c(8);
        if (c == 0) {
            this.j = -1;
        } else {
            this.j = ((c + 6) - 9) - this.i;
        }
        return true;
    }

    private void c() {
        this.b.a(0);
        this.l = -9223372036854775807L;
        if (this.f) {
            this.b.b(4);
            long c = ((long) this.b.c(3)) << 30;
            this.b.b(1);
            c |= (long) (this.b.c(15) << 15);
            this.b.b(1);
            c |= (long) this.b.c(15);
            this.b.b(1);
            if (!this.h && this.g) {
                this.b.b(4);
                long c2 = ((long) this.b.c(3)) << 30;
                this.b.b(1);
                c2 |= (long) (this.b.c(15) << 15);
                this.b.b(1);
                c2 |= (long) this.b.c(15);
                this.b.b(1);
                this.e.b(c2);
                this.h = true;
            }
            this.l = this.e.b(c);
        }
    }
}
