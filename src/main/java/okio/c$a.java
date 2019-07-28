package okio;

import java.io.Closeable;

/* compiled from: Buffer */
public final class c$a implements Closeable {
    public c a;
    public boolean b;
    public long c = -1;
    public byte[] d;
    public int e = -1;
    public int f = -1;
    private o g;

    public int a() {
        if (this.c == this.a.b) {
            throw new IllegalStateException();
        } else if (this.c == -1) {
            return a(0);
        } else {
            return a(this.c + ((long) (this.f - this.e)));
        }
    }

    public int a(long j) {
        if (j < -1 || j > this.a.b) {
            throw new ArrayIndexOutOfBoundsException(String.format("offset=%s > size=%s", new Object[]{Long.valueOf(j), Long.valueOf(this.a.b)}));
        } else if (j == -1 || j == this.a.b) {
            this.g = null;
            this.c = j;
            this.d = null;
            this.e = -1;
            this.f = -1;
            return -1;
        } else {
            long j2 = 0;
            long j3 = this.a.b;
            o oVar = this.a.a;
            o oVar2 = this.a.a;
            if (this.g != null) {
                long j4 = this.c - ((long) (this.e - this.g.b));
                if (j4 > j) {
                    oVar2 = this.g;
                    j3 = j4;
                } else {
                    oVar = this.g;
                    j2 = j4;
                }
            }
            if (j3 - j > j - j2) {
                while (j >= ((long) (oVar.c - oVar.b)) + j2) {
                    j2 += (long) (oVar.c - oVar.b);
                    oVar = oVar.f;
                }
            } else {
                j2 = j3;
                oVar = oVar2;
                while (j2 > j) {
                    oVar = oVar.g;
                    j2 -= (long) (oVar.c - oVar.b);
                }
            }
            if (this.b && oVar.d) {
                o b = oVar.b();
                if (this.a.a == oVar) {
                    this.a.a = b;
                }
                oVar = oVar.a(b);
                oVar.g.c();
            }
            this.g = oVar;
            this.c = j;
            this.d = oVar.a;
            this.e = oVar.b + ((int) (j - j2));
            this.f = oVar.c;
            return this.f - this.e;
        }
    }

    public void close() {
        if (this.a != null) {
            this.a = null;
            this.g = null;
            this.c = -1;
            this.d = null;
            this.e = -1;
            this.f = -1;
            return;
        }
        throw new IllegalStateException("not attached to a buffer");
    }
}
