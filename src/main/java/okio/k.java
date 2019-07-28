package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* compiled from: InflaterSource */
public final class k implements r {
    private final e a;
    private final Inflater b;
    private int c;
    private boolean d;

    public k(r rVar, Inflater inflater) {
        this(l.a(rVar), inflater);
    }

    k(e eVar, Inflater inflater) {
        if (eVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (inflater != null) {
            this.a = eVar;
            this.b = inflater;
        } else {
            throw new IllegalArgumentException("inflater == null");
        }
    }

    public long read(c cVar, long j) throws IOException {
        if (j < 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("byteCount < 0: ");
            stringBuilder.append(j);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (this.d) {
            throw new IllegalStateException("closed");
        } else if (j == 0) {
            return 0;
        } else {
            o f;
            while (true) {
                boolean a = a();
                try {
                    f = cVar.f(1);
                    int inflate = this.b.inflate(f.a, f.c, (int) Math.min(j, (long) (8192 - f.c)));
                    if (inflate > 0) {
                        f.c += inflate;
                        long j2 = (long) inflate;
                        cVar.b += j2;
                        return j2;
                    } else if (this.b.finished()) {
                        break;
                    } else if (this.b.needsDictionary()) {
                        break;
                    } else if (a) {
                        throw new EOFException("source exhausted prematurely");
                    }
                } catch (DataFormatException e) {
                    throw new IOException(e);
                }
            }
            b();
            if (f.b == f.c) {
                cVar.a = f.c();
                p.a(f);
            }
            return -1;
        }
    }

    public boolean a() throws IOException {
        if (!this.b.needsInput()) {
            return false;
        }
        b();
        if (this.b.getRemaining() != 0) {
            throw new IllegalStateException("?");
        } else if (this.a.f()) {
            return true;
        } else {
            o oVar = this.a.b().a;
            this.c = oVar.c - oVar.b;
            this.b.setInput(oVar.a, oVar.b, this.c);
            return false;
        }
    }

    private void b() throws IOException {
        if (this.c != 0) {
            int remaining = this.c - this.b.getRemaining();
            this.c -= remaining;
            this.a.i((long) remaining);
        }
    }

    public s timeout() {
        return this.a.timeout();
    }

    public void close() throws IOException {
        if (!this.d) {
            this.b.end();
            this.d = true;
            this.a.close();
        }
    }
}
