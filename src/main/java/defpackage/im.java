package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.h;
import com.google.android.exoplayer2.util.n;
import defpackage.jd.d;

/* compiled from: DtsReader */
/* renamed from: im */
public final class im implements io {
    private final n a = new n(new byte[18]);
    private final String b;
    private String c;
    private hj d;
    private int e = 0;
    private int f;
    private int g;
    private long h;
    private Format i;
    private int j;
    private long k;

    public void b() {
    }

    public im(String str) {
        this.b = str;
    }

    public void a() {
        this.e = 0;
        this.f = 0;
        this.g = 0;
    }

    public void a(hb hbVar, d dVar) {
        dVar.a();
        this.c = dVar.c();
        this.d = hbVar.a(dVar.b(), 1);
    }

    public void a(long j, boolean z) {
        this.k = j;
    }

    public void a(n nVar) {
        while (nVar.b() > 0) {
            switch (this.e) {
                case 0:
                    if (!b(nVar)) {
                        break;
                    }
                    this.e = 1;
                    break;
                case 1:
                    if (!a(nVar, this.a.a, 18)) {
                        break;
                    }
                    c();
                    this.a.c(0);
                    this.d.a(this.a, 18);
                    this.e = 2;
                    break;
                case 2:
                    int min = Math.min(nVar.b(), this.j - this.f);
                    this.d.a(nVar, min);
                    this.f += min;
                    if (this.f != this.j) {
                        break;
                    }
                    this.d.a(this.k, 1, this.j, 0, null);
                    this.k += this.h;
                    this.e = 0;
                    break;
                default:
                    break;
            }
        }
    }

    private boolean a(n nVar, byte[] bArr, int i) {
        int min = Math.min(nVar.b(), i - this.f);
        nVar.a(bArr, this.f, min);
        this.f += min;
        return this.f == i;
    }

    private boolean b(n nVar) {
        while (nVar.b() > 0) {
            this.g <<= 8;
            this.g |= nVar.g();
            if (h.a(this.g)) {
                this.a.a[0] = (byte) ((this.g >> 24) & 255);
                this.a.a[1] = (byte) ((this.g >> 16) & 255);
                this.a.a[2] = (byte) ((this.g >> 8) & 255);
                this.a.a[3] = (byte) (this.g & 255);
                this.f = 4;
                this.g = 0;
                return true;
            }
        }
        return false;
    }

    private void c() {
        byte[] bArr = this.a.a;
        if (this.i == null) {
            this.i = h.a(bArr, this.c, this.b, null);
            this.d.a(this.i);
        }
        this.j = h.b(bArr);
        this.h = (long) ((int) ((((long) h.a(bArr)) * 1000000) / ((long) this.i.t)));
    }
}
