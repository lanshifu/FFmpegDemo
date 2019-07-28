package defpackage;

import com.google.android.exoplayer2.upstream.e;
import com.google.android.exoplayer2.util.z;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: DefaultExtractorInput */
/* renamed from: gw */
public final class gw implements ha {
    private final byte[] a = new byte[4096];
    private final e b;
    private final long c;
    private long d;
    private byte[] e = new byte[65536];
    private int f;
    private int g;

    public gw(e eVar, long j, long j2) {
        this.b = eVar;
        this.d = j;
        this.c = j2;
    }

    public int a(byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        int d = d(bArr, i, i2);
        if (d == 0) {
            d = a(bArr, i, i2, 0, true);
        }
        g(d);
        return d;
    }

    public boolean a(byte[] bArr, int i, int i2, boolean z) throws IOException, InterruptedException {
        int d = d(bArr, i, i2);
        while (d < i2 && d != -1) {
            d = a(bArr, i, i2, d, z);
        }
        g(d);
        return d != -1;
    }

    public void b(byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        a(bArr, i, i2, false);
    }

    public int a(int i) throws IOException, InterruptedException {
        int e = e(i);
        if (e == 0) {
            e = a(this.a, 0, Math.min(i, this.a.length), 0, true);
        }
        g(e);
        return e;
    }

    public boolean a(int i, boolean z) throws IOException, InterruptedException {
        int e = e(i);
        while (e < i && e != -1) {
            e = a(this.a, -e, Math.min(i, this.a.length + e), e, z);
        }
        g(e);
        return e != -1;
    }

    public void b(int i) throws IOException, InterruptedException {
        a(i, false);
    }

    public boolean b(byte[] bArr, int i, int i2, boolean z) throws IOException, InterruptedException {
        if (!b(i2, z)) {
            return false;
        }
        System.arraycopy(this.e, this.f - i2, bArr, i, i2);
        return true;
    }

    public void c(byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        b(bArr, i, i2, false);
    }

    public boolean b(int i, boolean z) throws IOException, InterruptedException {
        d(i);
        int min = Math.min(this.g - this.f, i);
        while (min < i) {
            min = a(this.e, this.f, i, min, z);
            if (min == -1) {
                return false;
            }
        }
        this.f += i;
        this.g = Math.max(this.g, this.f);
        return true;
    }

    public void c(int i) throws IOException, InterruptedException {
        b(i, false);
    }

    public void a() {
        this.f = 0;
    }

    public long b() {
        return this.d + ((long) this.f);
    }

    public long c() {
        return this.d;
    }

    public long d() {
        return this.c;
    }

    private void d(int i) {
        int i2 = this.f + i;
        if (i2 > this.e.length) {
            this.e = Arrays.copyOf(this.e, z.a(this.e.length * 2, 65536 + i2, i2 + 524288));
        }
    }

    private int e(int i) {
        i = Math.min(this.g, i);
        f(i);
        return i;
    }

    private int d(byte[] bArr, int i, int i2) {
        if (this.g == 0) {
            return 0;
        }
        i2 = Math.min(this.g, i2);
        System.arraycopy(this.e, 0, bArr, i, i2);
        f(i2);
        return i2;
    }

    private void f(int i) {
        this.g -= i;
        this.f = 0;
        Object obj = this.e;
        if (this.g < this.e.length - 524288) {
            obj = new byte[(this.g + 65536)];
        }
        System.arraycopy(this.e, i, obj, 0, this.g);
        this.e = obj;
    }

    private int a(byte[] bArr, int i, int i2, int i3, boolean z) throws InterruptedException, IOException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        int a = this.b.a(bArr, i + i3, i2 - i3);
        if (a != -1) {
            return i3 + a;
        }
        if (i3 == 0 && z) {
            return -1;
        }
        throw new EOFException();
    }

    private void g(int i) {
        if (i != -1) {
            this.d += (long) i;
        }
    }
}
