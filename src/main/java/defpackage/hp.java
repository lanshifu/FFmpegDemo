package defpackage;

import com.google.android.exoplayer2.util.n;
import java.io.IOException;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: Sniffer */
/* renamed from: hp */
final class hp {
    private final n a = new n(8);
    private int b;

    public boolean a(ha haVar) throws IOException, InterruptedException {
        ha haVar2 = haVar;
        long d = haVar.d();
        int i = (d > -1 ? 1 : (d == -1 ? 0 : -1));
        long j = IjkMediaMeta.AV_CH_SIDE_RIGHT;
        if (i != 0 && d <= IjkMediaMeta.AV_CH_SIDE_RIGHT) {
            j = d;
        }
        i = (int) j;
        haVar2.c(this.a.a, 0, 4);
        long m = this.a.m();
        this.b = 4;
        while (true) {
            boolean z = true;
            if (m != 440786851) {
                int i2 = this.b + 1;
                this.b = i2;
                if (i2 == i) {
                    return false;
                }
                haVar2.c(this.a.a, 0, 1);
                m = ((long) (this.a.a[0] & 255)) | ((m << 8) & -256);
            } else {
                m = b(haVar);
                long j2 = (long) this.b;
                if (m == Long.MIN_VALUE || (d != -1 && j2 + m >= d)) {
                    return false;
                }
                while (true) {
                    long j3 = j2 + m;
                    if (((long) this.b) >= j3) {
                        if (((long) this.b) != j3) {
                            z = false;
                        }
                        return z;
                    } else if (b(haVar) == Long.MIN_VALUE) {
                        return false;
                    } else {
                        d = b(haVar);
                        if (d < 0 || d > 2147483647L) {
                            return false;
                        }
                        if (d != 0) {
                            int i3 = (int) d;
                            haVar2.c(i3);
                            this.b += i3;
                        }
                    }
                }
                return false;
            }
        }
    }

    private long b(ha haVar) throws IOException, InterruptedException {
        int i = 0;
        haVar.c(this.a.a, 0, 1);
        int i2 = this.a.a[0] & 255;
        if (i2 == 0) {
            return Long.MIN_VALUE;
        }
        int i3 = 128;
        int i4 = 0;
        while ((i2 & i3) == 0) {
            i3 >>= 1;
            i4++;
        }
        i2 &= i3 ^ -1;
        haVar.c(this.a.a, 1, i4);
        while (i < i4) {
            i++;
            i2 = (this.a.a[i] & 255) + (i2 << 8);
        }
        this.b += i4 + 1;
        return (long) i2;
    }
}
