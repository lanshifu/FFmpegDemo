package defpackage;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.n;
import java.io.IOException;

/* compiled from: OggExtractor */
/* renamed from: hx */
public class hx implements gz {
    public static final hc a = new 1();
    private hb b;
    private ic c;
    private boolean d;

    /* compiled from: OggExtractor */
    /* renamed from: hx$1 */
    static class 1 implements hc {
        1() {
        }

        public gz[] a() {
            return new gz[]{new hx()};
        }
    }

    public void c() {
    }

    public boolean a(ha haVar) throws IOException, InterruptedException {
        try {
            return b(haVar);
        } catch (ParserException unused) {
            return false;
        }
    }

    public void a(hb hbVar) {
        this.b = hbVar;
    }

    public void a(long j, long j2) {
        if (this.c != null) {
            this.c.a(j, j2);
        }
    }

    public int a(ha haVar, hg hgVar) throws IOException, InterruptedException {
        if (this.c == null) {
            if (b(haVar)) {
                haVar.a();
            } else {
                throw new ParserException("Failed to determine bitstream type");
            }
        }
        if (!this.d) {
            hj a = this.b.a(0, 1);
            this.b.a();
            this.c.a(this.b, a);
            this.d = true;
        }
        return this.c.a(haVar, hgVar);
    }

    private boolean b(ha haVar) throws IOException, InterruptedException {
        hz hzVar = new hz();
        if (!hzVar.a(haVar, true) || (hzVar.b & 2) != 2) {
            return false;
        }
        int min = Math.min(hzVar.i, 8);
        n nVar = new n(min);
        haVar.c(nVar.a, 0, min);
        if (hw.a(hx.a(nVar))) {
            this.c = new hw();
        } else if (ie.a(hx.a(nVar))) {
            this.c = new ie();
        } else if (!ib.a(hx.a(nVar))) {
            return false;
        } else {
            this.c = new ib();
        }
        return true;
    }

    private static n a(n nVar) {
        nVar.c(0);
        return nVar;
    }
}
