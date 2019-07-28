package okio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: RealBufferedSink */
final class m implements d {
    public final c a = new c();
    public final q b;
    boolean c;

    m(q qVar) {
        if (qVar != null) {
            this.b = qVar;
            return;
        }
        throw new NullPointerException("sink == null");
    }

    public c b() {
        return this.a;
    }

    public void write(c cVar, long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.write(cVar, j);
        z();
    }

    public d b(ByteString byteString) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(byteString);
        return z();
    }

    public d b(String str) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(str);
        return z();
    }

    public d c(byte[] bArr) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.b(bArr);
        return z();
    }

    public d c(byte[] bArr, int i, int i2) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.b(bArr, i, i2);
        return z();
    }

    public int write(ByteBuffer byteBuffer) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        int write = this.a.write(byteBuffer);
        z();
        return write;
    }

    public long a(r rVar) throws IOException {
        if (rVar != null) {
            long j = 0;
            while (true) {
                long read = rVar.read(this.a, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER);
                if (read == -1) {
                    return j;
                }
                j += read;
                z();
            }
        } else {
            throw new IllegalArgumentException("source == null");
        }
    }

    public d k(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.b(i);
        return z();
    }

    public d j(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.c(i);
        return z();
    }

    public d i(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.d(i);
        return z();
    }

    public d h(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.e(i);
        return z();
    }

    public d p(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.k(j);
        return z();
    }

    public d o(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.l(j);
        return z();
    }

    public d n(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.m(j);
        return z();
    }

    public d z() throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        long h = this.a.h();
        if (h > 0) {
            this.b.write(this.a, h);
        }
        return this;
    }

    public d e() throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        long a = this.a.a();
        if (a > 0) {
            this.b.write(this.a, a);
        }
        return this;
    }

    public OutputStream c() {
        return new OutputStream() {
            public void write(int i) throws IOException {
                if (m.this.c) {
                    throw new IOException("closed");
                }
                m.this.a.b((byte) i);
                m.this.z();
            }

            public void write(byte[] bArr, int i, int i2) throws IOException {
                if (m.this.c) {
                    throw new IOException("closed");
                }
                m.this.a.b(bArr, i, i2);
                m.this.z();
            }

            public void flush() throws IOException {
                if (!m.this.c) {
                    m.this.flush();
                }
            }

            public void close() throws IOException {
                m.this.close();
            }

            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(m.this);
                stringBuilder.append(".outputStream()");
                return stringBuilder.toString();
            }
        };
    }

    public void flush() throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        if (this.a.b > 0) {
            this.b.write(this.a, this.a.b);
        }
        this.b.flush();
    }

    public boolean isOpen() {
        return this.c ^ 1;
    }

    public void close() throws IOException {
        if (!this.c) {
            Throwable th = null;
            try {
                if (this.a.b > 0) {
                    this.b.write(this.a, this.a.b);
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.b.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            this.c = true;
            if (th != null) {
                t.a(th);
            }
        }
    }

    public s timeout() {
        return this.b.timeout();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("buffer(");
        stringBuilder.append(this.b);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
