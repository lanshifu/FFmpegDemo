package defpackage;

import com.google.android.exoplayer2.util.a;

/* compiled from: VorbisBitArray */
/* renamed from: id */
final class id {
    private final byte[] a;
    private final int b;
    private int c;
    private int d;

    public id(byte[] bArr) {
        this.a = bArr;
        this.b = bArr.length;
    }

    public boolean a() {
        boolean z = (((this.a[this.c] & 255) >> this.d) & 1) == 1;
        b(1);
        return z;
    }

    public int a(int i) {
        int i2 = this.c;
        int min = Math.min(i, 8 - this.d);
        int i3 = i2 + 1;
        i2 = ((this.a[i2] & 255) >> this.d) & (255 >> (8 - min));
        while (min < i) {
            i2 |= (this.a[i3] & 255) << min;
            min += 8;
            i3++;
        }
        i2 &= -1 >>> (32 - i);
        b(i);
        return i2;
    }

    public void b(int i) {
        int i2 = i / 8;
        this.c += i2;
        this.d += i - (i2 * 8);
        if (this.d > 7) {
            this.c++;
            this.d -= 8;
        }
        c();
    }

    public int b() {
        return (this.c * 8) + this.d;
    }

    private void c() {
        boolean z = this.c >= 0 && (this.c < this.b || (this.c == this.b && this.d == 0));
        a.b(z);
    }
}
