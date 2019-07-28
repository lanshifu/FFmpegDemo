package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* compiled from: GzipSource */
public final class j implements r {
    private int a = 0;
    private final e b;
    private final Inflater c;
    private final k d;
    private final CRC32 e = new CRC32();

    public j(r rVar) {
        if (rVar != null) {
            this.c = new Inflater(true);
            this.b = l.a(rVar);
            this.d = new k(this.b, this.c);
            return;
        }
        throw new IllegalArgumentException("source == null");
    }

    public long read(c cVar, long j) throws IOException {
        if (j < 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("byteCount < 0: ");
            stringBuilder.append(j);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (j == 0) {
            return 0;
        } else {
            if (this.a == 0) {
                a();
                this.a = 1;
            }
            if (this.a == 1) {
                long j2 = cVar.b;
                j = this.d.read(cVar, j);
                if (j != -1) {
                    a(cVar, j2, j);
                    return j;
                }
                this.a = 2;
            }
            if (this.a == 2) {
                b();
                this.a = 3;
                if (!this.b.f()) {
                    throw new IOException("gzip finished without exhausting source");
                }
            }
            return -1;
        }
    }

    private void a() throws IOException {
        this.b.a(10);
        byte c = this.b.b().c(3);
        Object obj = ((c >> 1) & 1) == 1 ? 1 : null;
        if (obj != null) {
            a(this.b.b(), 0, 10);
        }
        a("ID1ID2", 8075, this.b.j());
        this.b.i(8);
        if (((c >> 2) & 1) == 1) {
            this.b.a(2);
            if (obj != null) {
                a(this.b.b(), 0, 2);
            }
            long m = (long) this.b.b().m();
            this.b.a(m);
            if (obj != null) {
                a(this.b.b(), 0, m);
            }
            this.b.i(m);
        }
        if (((c >> 3) & 1) == 1) {
            long a = this.b.a((byte) 0);
            if (a != -1) {
                if (obj != null) {
                    a(this.b.b(), 0, a + 1);
                }
                this.b.i(a + 1);
            } else {
                throw new EOFException();
            }
        }
        if (((c >> 4) & 1) == 1) {
            long a2 = this.b.a((byte) 0);
            if (a2 != -1) {
                if (obj != null) {
                    a(this.b.b(), 0, a2 + 1);
                }
                this.b.i(a2 + 1);
            } else {
                throw new EOFException();
            }
        }
        if (obj != null) {
            a("FHCRC", this.b.m(), (short) ((int) this.e.getValue()));
            this.e.reset();
        }
    }

    private void b() throws IOException {
        a("CRC", this.b.n(), (int) this.e.getValue());
        a("ISIZE", this.b.n(), (int) this.c.getBytesWritten());
    }

    public s timeout() {
        return this.b.timeout();
    }

    public void close() throws IOException {
        this.d.close();
    }

    private void a(c cVar, long j, long j2) {
        o oVar = cVar.a;
        while (j >= ((long) (oVar.c - oVar.b))) {
            j -= (long) (oVar.c - oVar.b);
            oVar = oVar.f;
        }
        while (j2 > 0) {
            int i = (int) (((long) oVar.b) + j);
            int min = (int) Math.min((long) (oVar.c - i), j2);
            this.e.update(oVar.a, i, min);
            j2 -= (long) min;
            oVar = oVar.f;
            j = 0;
        }
    }

    private void a(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i)}));
        }
    }
}
