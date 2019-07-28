package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.n;
import defpackage.jd.d;

/* compiled from: MpegAudioReader */
/* renamed from: iu */
public final class iu implements io {
    private final n a;
    private final hf b;
    private final String c;
    private String d;
    private hj e;
    private int f;
    private int g;
    private boolean h;
    private boolean i;
    private long j;
    private int k;
    private long l;

    public void b() {
    }

    public iu() {
        this(null);
    }

    public iu(String str) {
        this.f = 0;
        this.a = new n(4);
        this.a.a[0] = (byte) -1;
        this.b = new hf();
        this.c = str;
    }

    public void a() {
        this.f = 0;
        this.g = 0;
        this.i = false;
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
                    b(nVar);
                    break;
                case 1:
                    c(nVar);
                    break;
                case 2:
                    d(nVar);
                    break;
                default:
                    break;
            }
        }
    }

    private void b(n nVar) {
        byte[] bArr = nVar.a;
        int c = nVar.c();
        for (int d = nVar.d(); d < c; d++) {
            boolean z = (bArr[d] & 255) == 255;
            Object obj = (this.i && (bArr[d] & 224) == 224) ? 1 : null;
            this.i = z;
            if (obj != null) {
                nVar.c(d + 1);
                this.i = false;
                this.a.a[1] = bArr[d];
                this.g = 2;
                this.f = 1;
                return;
            }
        }
        nVar.c(c);
    }

    private void c(n nVar) {
        int min = Math.min(nVar.b(), 4 - this.g);
        nVar.a(this.a.a, this.g, min);
        this.g += min;
        if (this.g >= 4) {
            this.a.c(0);
            if (hf.a(this.a.o(), this.b)) {
                this.k = this.b.c;
                if (!this.h) {
                    this.j = (((long) this.b.g) * 1000000) / ((long) this.b.d);
                    this.e.a(Format.a(this.d, this.b.b, null, -1, 4096, this.b.e, this.b.d, null, null, 0, this.c));
                    this.h = true;
                }
                this.a.c(0);
                this.e.a(this.a, 4);
                this.f = 2;
                return;
            }
            this.g = 0;
            this.f = 1;
        }
    }

    private void d(n nVar) {
        int min = Math.min(nVar.b(), this.k - this.g);
        this.e.a(nVar, min);
        this.g += min;
        if (this.g >= this.k) {
            this.e.a(this.l, 1, this.k, 0, null);
            this.l += this.j;
            this.g = 0;
            this.f = 0;
        }
    }
}
