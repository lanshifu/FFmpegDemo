package defpackage;

import com.google.android.exoplayer2.util.a;
import java.io.EOFException;
import java.io.IOException;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: DefaultOggSeeker */
/* renamed from: hv */
final class hv implements ia {
    private final hz a = new hz();
    private final long b;
    private final long c;
    private final ic d;
    private int e;
    private long f;
    private long g;
    private long h;
    private long i;
    private long j;
    private long k;
    private long l;

    /* compiled from: DefaultOggSeeker */
    /* renamed from: hv$a */
    private class a implements hh {
        public boolean a() {
            return true;
        }

        private a() {
        }

        public defpackage.hh.a b(long j) {
            if (j == 0) {
                return new defpackage.hh.a(new hi(0, hv.this.b));
            }
            return new defpackage.hh.a(new hi(j, hv.this.a(hv.this.b, hv.this.d.b(j), 30000)));
        }

        public long b() {
            return hv.this.d.a(hv.this.f);
        }
    }

    public hv(long j, long j2, ic icVar, int i, long j3) {
        boolean z = j >= 0 && j2 > j;
        a.a(z);
        this.d = icVar;
        this.b = j;
        this.c = j2;
        if (((long) i) == j2 - j) {
            this.f = j3;
            this.e = 3;
            return;
        }
        this.e = 0;
    }

    public long a(ha haVar) throws IOException, InterruptedException {
        switch (this.e) {
            case 0:
                this.g = haVar.c();
                this.e = 1;
                long j = this.c - 65307;
                if (j > this.g) {
                    return j;
                }
                break;
            case 1:
                break;
            case 2:
                long j2 = 0;
                if (this.h != 0) {
                    long a = a(this.h, haVar);
                    if (a >= 0) {
                        return a;
                    }
                    j2 = a(haVar, this.h, -(a + 2));
                }
                this.e = 3;
                return -(j2 + 2);
            case 3:
                return -1;
            default:
                throw new IllegalStateException();
        }
        this.f = c(haVar);
        this.e = 3;
        return this.g;
    }

    public long a(long j) {
        boolean z = this.e == 3 || this.e == 2;
        a.a(z);
        long j2 = 0;
        if (j != 0) {
            j2 = this.d.b(j);
        }
        this.h = j2;
        this.e = 2;
        b();
        return this.h;
    }

    /* renamed from: a */
    public a c() {
        return this.f != 0 ? new a() : null;
    }

    public void b() {
        this.i = this.b;
        this.j = this.c;
        this.k = 0;
        this.l = this.f;
    }

    public long a(long j, ha haVar) throws IOException, InterruptedException {
        ha haVar2 = haVar;
        long j2 = 2;
        if (this.i == this.j) {
            return -(this.k + 2);
        }
        long c = haVar.c();
        if (a(haVar2, this.j)) {
            this.a.a(haVar2, false);
            haVar.a();
            long j3 = j - this.a.c;
            int i = this.a.h + this.a.i;
            if (j3 < 0 || j3 > 72000) {
                if (j3 < 0) {
                    this.j = c;
                    this.l = this.a.c;
                } else {
                    long j4 = (long) i;
                    this.i = haVar.c() + j4;
                    this.k = this.a.c;
                    if ((this.j - this.i) + j4 < 100000) {
                        haVar2.b(i);
                        return -(this.k + 2);
                    }
                }
                if (this.j - this.i < 100000) {
                    this.j = this.i;
                    return this.i;
                }
                c = (long) i;
                if (j3 > 0) {
                    j2 = 1;
                }
                return Math.min(Math.max((haVar.c() - (c * j2)) + ((j3 * (this.j - this.i)) / (this.l - this.k)), this.i), this.j - 1);
            }
            haVar2.b(i);
            return -(this.a.c + 2);
        } else if (this.i != c) {
            return this.i;
        } else {
            throw new IOException("No ogg page can be found.");
        }
    }

    private long a(long j, long j2, long j3) {
        j += ((j2 * (this.c - this.b)) / this.f) - j3;
        if (j < this.b) {
            j = this.b;
        }
        return j >= this.c ? this.c - 1 : j;
    }

    /* Access modifiers changed, original: 0000 */
    public void b(ha haVar) throws IOException, InterruptedException {
        if (!a(haVar, this.c)) {
            throw new EOFException();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a(ha haVar, long j) throws IOException, InterruptedException {
        j = Math.min(j + 3, this.c);
        byte[] bArr = new byte[IjkMediaMeta.FF_PROFILE_H264_INTRA];
        int length = bArr.length;
        while (true) {
            int i;
            int i2 = 0;
            if (haVar.c() + ((long) length) > j) {
                length = (int) (j - haVar.c());
                if (length < 4) {
                    return false;
                }
            }
            haVar.b(bArr, 0, length, false);
            while (true) {
                i = length - 3;
                if (i2 >= i) {
                    break;
                } else if (bArr[i2] == (byte) 79 && bArr[i2 + 1] == (byte) 103 && bArr[i2 + 2] == (byte) 103 && bArr[i2 + 3] == (byte) 83) {
                    haVar.b(i2);
                    return true;
                } else {
                    i2++;
                }
            }
            haVar.b(i);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public long c(ha haVar) throws IOException, InterruptedException {
        b(haVar);
        this.a.a();
        while ((this.a.b & 4) != 4 && haVar.c() < this.c) {
            this.a.a(haVar, false);
            haVar.b(this.a.h + this.a.i);
        }
        return this.a.c;
    }

    /* Access modifiers changed, original: 0000 */
    public long a(ha haVar, long j, long j2) throws IOException, InterruptedException {
        this.a.a(haVar, false);
        while (this.a.c < j) {
            haVar.b(this.a.h + this.a.i);
            j2 = this.a.c;
            this.a.a(haVar, false);
        }
        haVar.a();
        return j2;
    }
}
