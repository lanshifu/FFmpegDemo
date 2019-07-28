package defpackage;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.a;
import java.io.IOException;
import java.util.ArrayDeque;

/* compiled from: DefaultEbmlReader */
/* renamed from: hl */
final class hl implements hm {
    private final byte[] a = new byte[8];
    private final ArrayDeque<a> b = new ArrayDeque();
    private final hq c = new hq();
    private hn d;
    private int e;
    private int f;
    private long g;

    /* compiled from: DefaultEbmlReader */
    /* renamed from: hl$a */
    private static final class a {
        private final int a;
        private final long b;

        private a(int i, long j) {
            this.a = i;
            this.b = j;
        }
    }

    public void a(hn hnVar) {
        this.d = hnVar;
    }

    public void a() {
        this.e = 0;
        this.b.clear();
        this.c.a();
    }

    public boolean a(ha haVar) throws IOException, InterruptedException {
        a.b(this.d != null);
        while (true) {
            if (this.b.isEmpty() || haVar.c() < ((a) this.b.peek()).b) {
                if (this.e == 0) {
                    long a = this.c.a(haVar, true, false, 4);
                    if (a == -2) {
                        a = b(haVar);
                    }
                    if (a == -1) {
                        return false;
                    }
                    this.f = (int) a;
                    this.e = 1;
                }
                if (this.e == 1) {
                    this.g = this.c.a(haVar, false, true, 8);
                    this.e = 2;
                }
                int a2 = this.d.a(this.f);
                StringBuilder stringBuilder;
                switch (a2) {
                    case 0:
                        haVar.b((int) this.g);
                        this.e = 0;
                    case 1:
                        long c = haVar.c();
                        this.b.push(new a(this.f, this.g + c));
                        this.d.a(this.f, c, this.g);
                        this.e = 0;
                        return true;
                    case 2:
                        if (this.g <= 8) {
                            this.d.a(this.f, a(haVar, (int) this.g));
                            this.e = 0;
                            return true;
                        }
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Invalid integer size: ");
                        stringBuilder.append(this.g);
                        throw new ParserException(stringBuilder.toString());
                    case 3:
                        if (this.g <= 2147483647L) {
                            this.d.a(this.f, c(haVar, (int) this.g));
                            this.e = 0;
                            return true;
                        }
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("String element size: ");
                        stringBuilder.append(this.g);
                        throw new ParserException(stringBuilder.toString());
                    case 4:
                        this.d.a(this.f, (int) this.g, haVar);
                        this.e = 0;
                        return true;
                    case 5:
                        if (this.g == 4 || this.g == 8) {
                            this.d.a(this.f, b(haVar, (int) this.g));
                            this.e = 0;
                            return true;
                        }
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Invalid float size: ");
                        stringBuilder.append(this.g);
                        throw new ParserException(stringBuilder.toString());
                    default:
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Invalid element type ");
                        stringBuilder2.append(a2);
                        throw new ParserException(stringBuilder2.toString());
                }
            }
            this.d.c(((a) this.b.pop()).a);
            return true;
        }
    }

    private long b(ha haVar) throws IOException, InterruptedException {
        haVar.a();
        while (true) {
            haVar.c(this.a, 0, 4);
            int a = hq.a(this.a[0]);
            if (a != -1 && a <= 4) {
                int a2 = (int) hq.a(this.a, a, false);
                if (this.d.b(a2)) {
                    haVar.b(a);
                    return (long) a2;
                }
            }
            haVar.b(1);
        }
    }

    private long a(ha haVar, int i) throws IOException, InterruptedException {
        int i2 = 0;
        haVar.b(this.a, 0, i);
        long j = 0;
        while (i2 < i) {
            j = (j << 8) | ((long) (this.a[i2] & 255));
            i2++;
        }
        return j;
    }

    private double b(ha haVar, int i) throws IOException, InterruptedException {
        long a = a(haVar, i);
        if (i == 4) {
            return (double) Float.intBitsToFloat((int) a);
        }
        return Double.longBitsToDouble(a);
    }

    private String c(ha haVar, int i) throws IOException, InterruptedException {
        if (i == 0) {
            return "";
        }
        byte[] bArr = new byte[i];
        haVar.b(bArr, 0, i);
        while (i > 0 && bArr[i - 1] == (byte) 0) {
            i--;
        }
        return new String(bArr, 0, i);
    }
}
