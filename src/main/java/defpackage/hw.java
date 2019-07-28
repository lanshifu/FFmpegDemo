package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.g;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;
import defpackage.ic.a;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/* compiled from: FlacReader */
/* renamed from: hw */
final class hw extends ic {
    private g a;
    private a b;

    /* compiled from: FlacReader */
    /* renamed from: hw$a */
    private class a implements hh, ia {
        private long[] b;
        private long[] c;
        private long d = -1;
        private long e = -1;

        public boolean a() {
            return true;
        }

        public hh c() {
            return this;
        }

        public void c(long j) {
            this.d = j;
        }

        public void a(n nVar) {
            nVar.d(1);
            int k = nVar.k() / 18;
            this.b = new long[k];
            this.c = new long[k];
            for (int i = 0; i < k; i++) {
                this.b[i] = nVar.q();
                this.c[i] = nVar.q();
                nVar.d(2);
            }
        }

        public long a(ha haVar) throws IOException, InterruptedException {
            if (this.e < 0) {
                return -1;
            }
            long j = -(this.e + 2);
            this.e = -1;
            return j;
        }

        public long a(long j) {
            j = hw.this.b(j);
            this.e = this.b[z.a(this.b, j, true, true)];
            return j;
        }

        public defpackage.hh.a b(long j) {
            int a = z.a(this.b, hw.this.b(j), true, true);
            long a2 = hw.this.a(this.b[a]);
            hi hiVar = new hi(a2, this.d + this.c[a]);
            if (a2 >= j || a == this.b.length - 1) {
                return new defpackage.hh.a(hiVar);
            }
            a++;
            return new defpackage.hh.a(hiVar, new hi(hw.this.a(this.b[a]), this.d + this.c[a]));
        }

        public long b() {
            return hw.this.a.b();
        }
    }

    hw() {
    }

    public static boolean a(n nVar) {
        return nVar.b() >= 5 && nVar.g() == 127 && nVar.m() == 1179402563;
    }

    /* Access modifiers changed, original: protected */
    public void a(boolean z) {
        super.a(z);
        if (z) {
            this.a = null;
            this.b = null;
        }
    }

    private static boolean a(byte[] bArr) {
        return bArr[0] == (byte) -1;
    }

    /* Access modifiers changed, original: protected */
    public long b(n nVar) {
        if (hw.a(nVar.a)) {
            return (long) c(nVar);
        }
        return -1;
    }

    /* Access modifiers changed, original: protected */
    public boolean a(n nVar, long j, a aVar) throws IOException, InterruptedException {
        byte[] bArr = nVar.a;
        if (this.a == null) {
            this.a = new g(bArr, 17);
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 9, nVar.c());
            copyOfRange[4] = Byte.MIN_VALUE;
            aVar.a = Format.a(null, "audio/flac", null, -1, this.a.a(), this.a.f, this.a.e, Collections.singletonList(copyOfRange), null, 0, null);
        } else if ((bArr[0] & 127) == 3) {
            this.b = new a();
            this.b.a(nVar);
        } else if (hw.a(bArr)) {
            if (this.b != null) {
                this.b.c(j);
                aVar.b = this.b;
            }
            return false;
        }
        return true;
    }

    private int c(n nVar) {
        int i = (nVar.a[2] & 255) >> 4;
        switch (i) {
            case 1:
                return 192;
            case 2:
            case 3:
            case 4:
            case 5:
                return 576 << (i - 2);
            case 6:
            case 7:
                nVar.d(4);
                nVar.A();
                i = i == 6 ? nVar.g() : nVar.h();
                nVar.c(0);
                return i + 1;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                return 256 << (i - 8);
            default:
                return -1;
        }
    }
}
