package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.e;
import com.google.android.exoplayer2.upstream.g;
import com.google.android.exoplayer2.util.z;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: DataChunk */
/* renamed from: jr */
public abstract class jr extends jk {
    private byte[] a;
    private int j;
    private volatile boolean k;

    public abstract void a(byte[] bArr, int i) throws IOException;

    public jr(e eVar, g gVar, int i, Format format, int i2, Object obj, byte[] bArr) {
        super(eVar, gVar, i, format, i2, obj, -9223372036854775807L, -9223372036854775807L);
        this.a = bArr;
    }

    public byte[] c() {
        return this.a;
    }

    public long e() {
        return (long) this.j;
    }

    public final void a() {
        this.k = true;
    }

    public final void b() throws IOException, InterruptedException {
        try {
            this.i.a(this.b);
            int i = 0;
            this.j = 0;
            while (i != -1 && !this.k) {
                f();
                i = this.i.a(this.a, this.j, 16384);
                if (i != -1) {
                    this.j += i;
                }
            }
            if (!this.k) {
                a(this.a, this.j);
            }
            z.a(this.i);
        } catch (Throwable th) {
            z.a(this.i);
        }
    }

    private void f() {
        if (this.a == null) {
            this.a = new byte[16384];
        } else if (this.a.length < this.j + 16384) {
            this.a = Arrays.copyOf(this.a, this.a.length + 16384);
        }
    }
}
