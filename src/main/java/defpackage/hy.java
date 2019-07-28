package defpackage;

import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.n;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: OggPacket */
/* renamed from: hy */
final class hy {
    private final hz a = new hz();
    private final n b = new n(new byte[65025], 0);
    private int c = -1;
    private int d;
    private boolean e;

    hy() {
    }

    public void a() {
        this.a.a();
        this.b.a();
        this.c = -1;
        this.e = false;
    }

    public boolean a(ha haVar) throws IOException, InterruptedException {
        a.b(haVar != null);
        if (this.e) {
            this.e = false;
            this.b.a();
        }
        while (!this.e) {
            int i;
            int i2;
            if (this.c < 0) {
                if (!this.a.a(haVar, true)) {
                    return false;
                }
                i = this.a.h;
                if ((this.a.b & 1) == 1 && this.b.c() == 0) {
                    i += a(0);
                    i2 = this.d + 0;
                } else {
                    i2 = 0;
                }
                haVar.b(i);
                this.c = i2;
            }
            i = a(this.c);
            i2 = this.c + this.d;
            if (i > 0) {
                if (this.b.e() < this.b.c() + i) {
                    this.b.a = Arrays.copyOf(this.b.a, this.b.c() + i);
                }
                haVar.b(this.b.a, this.b.c(), i);
                this.b.b(this.b.c() + i);
                this.e = this.a.j[i2 + -1] != 255;
            }
            if (i2 == this.a.g) {
                i2 = -1;
            }
            this.c = i2;
        }
        return true;
    }

    public hz b() {
        return this.a;
    }

    public n c() {
        return this.b;
    }

    public void d() {
        if (this.b.a.length != 65025) {
            this.b.a = Arrays.copyOf(this.b.a, Math.max(65025, this.b.c()));
        }
    }

    private int a(int i) {
        int i2 = 0;
        this.d = 0;
        while (this.d + i < this.a.g) {
            int[] iArr = this.a.j;
            int i3 = this.d;
            this.d = i3 + 1;
            int i4 = iArr[i3 + i];
            i2 += i4;
            if (i4 != 255) {
                break;
            }
        }
        return i2;
    }
}
