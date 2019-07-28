package okio;

import java.io.IOException;
import java.util.zip.Deflater;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

/* compiled from: DeflaterSink */
public final class f implements q {
    private final d a;
    private final Deflater b;
    private boolean c;

    public f(q qVar, Deflater deflater) {
        this(l.a(qVar), deflater);
    }

    f(d dVar, Deflater deflater) {
        if (dVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (deflater != null) {
            this.a = dVar;
            this.b = deflater;
        } else {
            throw new IllegalArgumentException("inflater == null");
        }
    }

    public void write(c cVar, long j) throws IOException {
        t.a(cVar.b, 0, j);
        while (j > 0) {
            o oVar = cVar.a;
            int min = (int) Math.min(j, (long) (oVar.c - oVar.b));
            this.b.setInput(oVar.a, oVar.b, min);
            a(false);
            long j2 = (long) min;
            cVar.b -= j2;
            oVar.b += min;
            if (oVar.b == oVar.c) {
                cVar.a = oVar.c();
                p.a(oVar);
            }
            j -= j2;
        }
    }

    @IgnoreJRERequirement
    private void a(boolean z) throws IOException {
        o f;
        c b = this.a.b();
        while (true) {
            int deflate;
            f = b.f(1);
            if (z) {
                deflate = this.b.deflate(f.a, f.c, 8192 - f.c, 2);
            } else {
                deflate = this.b.deflate(f.a, f.c, 8192 - f.c);
            }
            if (deflate > 0) {
                f.c += deflate;
                b.b += (long) deflate;
                this.a.z();
            } else if (this.b.needsInput()) {
                break;
            }
        }
        if (f.b == f.c) {
            b.a = f.c();
            p.a(f);
        }
    }

    public void flush() throws IOException {
        a(true);
        this.a.flush();
    }

    /* Access modifiers changed, original: 0000 */
    public void a() throws IOException {
        this.b.finish();
        a(false);
    }

    public void close() throws IOException {
        if (!this.c) {
            Throwable th = null;
            try {
                a();
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.b.end();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            try {
                this.a.close();
            } catch (Throwable th32) {
                if (th == null) {
                    th = th32;
                }
            }
            this.c = true;
            if (th != null) {
                t.a(th);
            }
        }
    }

    public s timeout() {
        return this.a.timeout();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DeflaterSink(");
        stringBuilder.append(this.a);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
