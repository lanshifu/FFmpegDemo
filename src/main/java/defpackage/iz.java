package defpackage;

import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.w;
import com.google.android.exoplayer2.util.z;
import defpackage.jd.d;

/* compiled from: SectionReader */
/* renamed from: iz */
public final class iz implements jd {
    private final iy a;
    private final n b = new n(32);
    private int c;
    private int d;
    private boolean e;
    private boolean f;

    public iz(iy iyVar) {
        this.a = iyVar;
    }

    public void a(w wVar, hb hbVar, d dVar) {
        this.a.a(wVar, hbVar, dVar);
        this.f = true;
    }

    public void a() {
        this.f = true;
    }

    public void a(n nVar, boolean z) {
        int g = z ? nVar.g() + nVar.d() : -1;
        if (this.f) {
            if (z) {
                this.f = false;
                nVar.c(g);
                this.d = 0;
            } else {
                return;
            }
        }
        while (nVar.b() > 0) {
            boolean z2 = true;
            int g2;
            if (this.d < 3) {
                if (this.d == 0) {
                    g2 = nVar.g();
                    nVar.c(nVar.d() - 1);
                    if (g2 == 255) {
                        this.f = true;
                        return;
                    }
                }
                g2 = Math.min(nVar.b(), 3 - this.d);
                nVar.a(this.b.a, this.d, g2);
                this.d += g2;
                if (this.d == 3) {
                    this.b.a(3);
                    this.b.d(1);
                    g2 = this.b.g();
                    int g3 = this.b.g();
                    if ((g2 & 128) == 0) {
                        z2 = false;
                    }
                    this.e = z2;
                    this.c = (((g2 & 15) << 8) | g3) + 3;
                    if (this.b.e() < this.c) {
                        byte[] bArr = this.b.a;
                        this.b.a(Math.min(4098, Math.max(this.c, bArr.length * 2)));
                        System.arraycopy(bArr, 0, this.b.a, 0, 3);
                    }
                }
            } else {
                g2 = Math.min(nVar.b(), this.c - this.d);
                nVar.a(this.b.a, this.d, g2);
                this.d += g2;
                if (this.d != this.c) {
                    continue;
                } else {
                    if (!this.e) {
                        this.b.a(this.c);
                    } else if (z.a(this.b.a, 0, this.c, -1) != 0) {
                        this.f = true;
                        return;
                    } else {
                        this.b.a(this.c - 4);
                    }
                    this.a.a(this.b);
                    this.d = 0;
                }
            }
        }
    }
}
