package okio;

import java.io.InputStream;

/* compiled from: Buffer */
class c$2 extends InputStream {
    final /* synthetic */ c a;

    public void close() {
    }

    c$2(c cVar) {
        this.a = cVar;
    }

    public int read() {
        return this.a.b > 0 ? this.a.i() & 255 : -1;
    }

    public int read(byte[] bArr, int i, int i2) {
        return this.a.a(bArr, i, i2);
    }

    public int available() {
        return (int) Math.min(this.a.b, 2147483647L);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        stringBuilder.append(".inputStream()");
        return stringBuilder.toString();
    }
}
