package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: RealBufferedSource */
final class n implements e {
    public final c a = new c();
    public final r b;
    boolean c;

    n(r rVar) {
        if (rVar != null) {
            this.b = rVar;
            return;
        }
        throw new NullPointerException("source == null");
    }

    public c b() {
        return this.a;
    }

    public long read(c cVar, long j) throws IOException {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("byteCount < 0: ");
            stringBuilder.append(j);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (this.c) {
            throw new IllegalStateException("closed");
        } else if (this.a.b == 0 && this.b.read(this.a, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) == -1) {
            return -1;
        } else {
            return this.a.read(cVar, Math.min(j, this.a.b));
        }
    }

    public boolean f() throws IOException {
        if (!this.c) {
            return this.a.f() && this.b.read(this.a, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) == -1;
        } else {
            throw new IllegalStateException("closed");
        }
    }

    public void a(long j) throws IOException {
        if (!b(j)) {
            throw new EOFException();
        }
    }

    public boolean b(long j) throws IOException {
        if (j < 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("byteCount < 0: ");
            stringBuilder.append(j);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (this.c) {
            throw new IllegalStateException("closed");
        } else {
            while (this.a.b < j) {
                if (this.b.read(this.a, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) == -1) {
                    return false;
                }
            }
            return true;
        }
    }

    public byte i() throws IOException {
        a(1);
        return this.a.i();
    }

    public ByteString d(long j) throws IOException {
        a(j);
        return this.a.d(j);
    }

    public byte[] v() throws IOException {
        this.a.a(this.b);
        return this.a.v();
    }

    public byte[] h(long j) throws IOException {
        a(j);
        return this.a.h(j);
    }

    public void a(byte[] bArr) throws IOException {
        try {
            a((long) bArr.length);
            this.a.a(bArr);
        } catch (EOFException e) {
            int i = 0;
            while (this.a.b > 0) {
                int a = this.a.a(bArr, i, (int) this.a.b);
                if (a != -1) {
                    i += a;
                } else {
                    throw new AssertionError();
                }
            }
            throw e;
        }
    }

    public int read(ByteBuffer byteBuffer) throws IOException {
        if (this.a.b == 0 && this.b.read(this.a, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) == -1) {
            return -1;
        }
        return this.a.read(byteBuffer);
    }

    public void a(c cVar, long j) throws IOException {
        try {
            a(j);
            this.a.a(cVar, j);
        } catch (EOFException e) {
            cVar.a(this.a);
            throw e;
        }
    }

    public long a(q qVar) throws IOException {
        if (qVar != null) {
            long j = 0;
            while (this.b.read(this.a, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) != -1) {
                long h = this.a.h();
                if (h > 0) {
                    j += h;
                    qVar.write(this.a, h);
                }
            }
            if (this.a.a() <= 0) {
                return j;
            }
            j += this.a.a();
            qVar.write(this.a, this.a.a());
            return j;
        }
        throw new IllegalArgumentException("sink == null");
    }

    public String e(long j) throws IOException {
        a(j);
        return this.a.e(j);
    }

    public String a(Charset charset) throws IOException {
        if (charset != null) {
            this.a.a(this.b);
            return this.a.a(charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    public String t() throws IOException {
        return f(Long.MAX_VALUE);
    }

    public String f(long j) throws IOException {
        StringBuilder stringBuilder;
        if (j >= 0) {
            long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
            long a = a((byte) 10, 0, j2);
            if (a != -1) {
                return this.a.g(a);
            }
            if (j2 < Long.MAX_VALUE && b(j2) && this.a.c(j2 - 1) == (byte) 13 && b(1 + j2) && this.a.c(j2) == (byte) 10) {
                return this.a.g(j2);
            }
            c cVar = new c();
            this.a.a(cVar, 0, Math.min(32, this.a.a()));
            stringBuilder = new StringBuilder();
            stringBuilder.append("\\n not found: limit=");
            stringBuilder.append(Math.min(this.a.a(), j));
            stringBuilder.append(" content=");
            stringBuilder.append(cVar.r().hex());
            stringBuilder.append(8230);
            throw new EOFException(stringBuilder.toString());
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("limit < 0: ");
        stringBuilder.append(j);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public short j() throws IOException {
        a(2);
        return this.a.j();
    }

    public short m() throws IOException {
        a(2);
        return this.a.m();
    }

    public int k() throws IOException {
        a(4);
        return this.a.k();
    }

    public int n() throws IOException {
        a(4);
        return this.a.n();
    }

    public long l() throws IOException {
        a(8);
        return this.a.l();
    }

    public long o() throws IOException {
        a(8);
        return this.a.o();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002b  */
    public long p() throws java.io.IOException {
        /*
        r6 = this;
        r0 = 1;
        r6.a(r0);
        r0 = 0;
        r1 = 0;
    L_0x0007:
        r2 = r1 + 1;
        r3 = (long) r2;
        r3 = r6.b(r3);
        if (r3 == 0) goto L_0x0040;
    L_0x0010:
        r3 = r6.a;
        r4 = (long) r1;
        r3 = r3.c(r4);
        r4 = 48;
        if (r3 < r4) goto L_0x001f;
    L_0x001b:
        r4 = 57;
        if (r3 <= r4) goto L_0x0026;
    L_0x001f:
        if (r1 != 0) goto L_0x0028;
    L_0x0021:
        r4 = 45;
        if (r3 == r4) goto L_0x0026;
    L_0x0025:
        goto L_0x0028;
    L_0x0026:
        r1 = r2;
        goto L_0x0007;
    L_0x0028:
        if (r1 == 0) goto L_0x002b;
    L_0x002a:
        goto L_0x0040;
    L_0x002b:
        r1 = new java.lang.NumberFormatException;
        r2 = 1;
        r2 = new java.lang.Object[r2];
        r3 = java.lang.Byte.valueOf(r3);
        r2[r0] = r3;
        r0 = "Expected leading [0-9] or '-' character but was %#x";
        r0 = java.lang.String.format(r0, r2);
        r1.<init>(r0);
        throw r1;
    L_0x0040:
        r0 = r6.a;
        r0 = r0.p();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.n.p():long");
    }

    public long q() throws IOException {
        a(1);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!b((long) i2)) {
                break;
            }
            byte c = this.a.c((long) i);
            if ((c >= (byte) 48 && c <= (byte) 57) || ((c >= (byte) 97 && c <= (byte) 102) || (c >= (byte) 65 && c <= (byte) 70))) {
                i = i2;
            } else if (i == 0) {
                throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", new Object[]{Byte.valueOf(c)}));
            }
        }
        return this.a.q();
    }

    public void i(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            if (this.a.b == 0 && this.b.read(this.a, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j, this.a.a());
            this.a.i(min);
            j -= min;
        }
    }

    public long a(byte b) throws IOException {
        return a(b, 0, Long.MAX_VALUE);
    }

    public long a(byte b, long j, long j2) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        } else if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", new Object[]{Long.valueOf(j), Long.valueOf(j2)}));
        } else {
            while (j < j2) {
                long a = this.a.a(b, j, j2);
                if (a != -1) {
                    return a;
                }
                a = this.a.b;
                if (a >= j2 || this.b.read(this.a, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) == -1) {
                    return -1;
                }
                j = Math.max(j, a);
            }
            return -1;
        }
    }

    public boolean a(long j, ByteString byteString) throws IOException {
        return a(j, byteString, 0, byteString.size());
    }

    public boolean a(long j, ByteString byteString, int i, int i2) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        } else if (j < 0 || i < 0 || i2 < 0 || byteString.size() - i < i2) {
            return false;
        } else {
            int i3 = 0;
            while (i3 < i2) {
                long j2 = ((long) i3) + j;
                if (!b(1 + j2) || this.a.c(j2) != byteString.getByte(i + i3)) {
                    return false;
                }
                i3++;
            }
            return true;
        }
    }

    public InputStream g() {
        return new InputStream() {
            public int read() throws IOException {
                if (n.this.c) {
                    throw new IOException("closed");
                } else if (n.this.a.b == 0 && n.this.b.read(n.this.a, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) == -1) {
                    return -1;
                } else {
                    return n.this.a.i() & 255;
                }
            }

            public int read(byte[] bArr, int i, int i2) throws IOException {
                if (n.this.c) {
                    throw new IOException("closed");
                }
                t.a((long) bArr.length, (long) i, (long) i2);
                if (n.this.a.b == 0 && n.this.b.read(n.this.a, IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) == -1) {
                    return -1;
                }
                return n.this.a.a(bArr, i, i2);
            }

            public int available() throws IOException {
                if (!n.this.c) {
                    return (int) Math.min(n.this.a.b, 2147483647L);
                }
                throw new IOException("closed");
            }

            public void close() throws IOException {
                n.this.close();
            }

            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(n.this);
                stringBuilder.append(".inputStream()");
                return stringBuilder.toString();
            }
        };
    }

    public boolean isOpen() {
        return this.c ^ 1;
    }

    public void close() throws IOException {
        if (!this.c) {
            this.c = true;
            this.b.close();
            this.a.w();
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
