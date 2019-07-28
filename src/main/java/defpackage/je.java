package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import java.io.IOException;

/* compiled from: WavExtractor */
/* renamed from: je */
public final class je implements gz {
    public static final hc a = new 1();
    private hb b;
    private hj c;
    private jf d;
    private int e;
    private int f;

    /* compiled from: WavExtractor */
    /* renamed from: je$1 */
    static class 1 implements hc {
        1() {
        }

        public gz[] a() {
            return new gz[]{new je()};
        }
    }

    public void c() {
    }

    public boolean a(ha haVar) throws IOException, InterruptedException {
        return jg.a(haVar) != null;
    }

    public void a(hb hbVar) {
        this.b = hbVar;
        this.c = hbVar.a(0, 1);
        this.d = null;
        hbVar.a();
    }

    public void a(long j, long j2) {
        this.f = 0;
    }

    public int a(ha haVar, hg hgVar) throws IOException, InterruptedException {
        if (this.d == null) {
            this.d = jg.a(haVar);
            if (this.d != null) {
                this.c.a(Format.a(null, "audio/raw", null, this.d.e(), 32768, this.d.g(), this.d.f(), this.d.h(), null, null, 0, null));
                this.e = this.d.d();
            } else {
                throw new ParserException("Unsupported or unrecognized wav header.");
            }
        }
        if (!this.d.c()) {
            jg.a(haVar, this.d);
            this.b.a(this.d);
        }
        int a = this.c.a(haVar, 32768 - this.f, true);
        if (a != -1) {
            this.f += a;
        }
        int i = this.f / this.e;
        if (i > 0) {
            long a2 = this.d.a(haVar.c() - ((long) this.f));
            int i2 = i * this.e;
            this.f -= i2;
            this.c.a(a2, 1, i2, this.f, null);
        }
        if (a == -1) {
            return -1;
        }
        return 0;
    }
}
