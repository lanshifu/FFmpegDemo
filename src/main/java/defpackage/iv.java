package defpackage;

import com.google.android.exoplayer2.util.a;
import java.util.Arrays;

/* compiled from: NalUnitTargetBuffer */
/* renamed from: iv */
final class iv {
    public byte[] a;
    public int b;
    private final int c;
    private boolean d;
    private boolean e;

    public iv(int i, int i2) {
        this.c = i;
        this.a = new byte[(i2 + 3)];
        this.a[2] = (byte) 1;
    }

    public void a() {
        this.d = false;
        this.e = false;
    }

    public boolean b() {
        return this.e;
    }

    public void a(int i) {
        boolean z = true;
        a.b(this.d ^ 1);
        if (i != this.c) {
            z = false;
        }
        this.d = z;
        if (this.d) {
            this.b = 3;
            this.e = false;
        }
    }

    public void a(byte[] bArr, int i, int i2) {
        if (this.d) {
            i2 -= i;
            if (this.a.length < this.b + i2) {
                this.a = Arrays.copyOf(this.a, (this.b + i2) * 2);
            }
            System.arraycopy(bArr, i, this.a, this.b, i2);
            this.b += i2;
        }
    }

    public boolean b(int i) {
        if (!this.d) {
            return false;
        }
        this.b -= i;
        this.d = false;
        this.e = true;
        return true;
    }
}
