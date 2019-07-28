package okio;

import java.io.OutputStream;

/* compiled from: Buffer */
class c$1 extends OutputStream {
    final /* synthetic */ c a;

    public void close() {
    }

    public void flush() {
    }

    c$1(c cVar) {
        this.a = cVar;
    }

    public void write(int i) {
        this.a.b((byte) i);
    }

    public void write(byte[] bArr, int i, int i2) {
        this.a.b(bArr, i, i2);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        stringBuilder.append(".outputStream()");
        return stringBuilder.toString();
    }
}
