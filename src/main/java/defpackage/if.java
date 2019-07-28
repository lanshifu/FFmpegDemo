package defpackage;

import android.util.Log;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.n;
import java.util.Arrays;

/* compiled from: VorbisUtil */
/* renamed from: if */
final class if {

    /* compiled from: VorbisUtil */
    /* renamed from: if$a */
    public static final class a {
        public final int a;
        public final int b;
        public final long[] c;
        public final int d;
        public final boolean e;

        public a(int i, int i2, long[] jArr, int i3, boolean z) {
            this.a = i;
            this.b = i2;
            this.c = jArr;
            this.d = i3;
            this.e = z;
        }
    }

    /* compiled from: VorbisUtil */
    /* renamed from: if$b */
    public static final class b {
        public final String a;
        public final String[] b;
        public final int c;

        public b(String str, String[] strArr, int i) {
            this.a = str;
            this.b = strArr;
            this.c = i;
        }
    }

    /* compiled from: VorbisUtil */
    /* renamed from: if$c */
    public static final class c {
        public final boolean a;
        public final int b;
        public final int c;
        public final int d;

        public c(boolean z, int i, int i2, int i3) {
            this.a = z;
            this.b = i;
            this.c = i2;
            this.d = i3;
        }
    }

    /* compiled from: VorbisUtil */
    /* renamed from: if$d */
    public static final class d {
        public final long a;
        public final int b;
        public final long c;
        public final int d;
        public final int e;
        public final int f;
        public final int g;
        public final int h;
        public final boolean i;
        public final byte[] j;

        public d(long j, int i, long j2, int i2, int i3, int i4, int i5, int i6, boolean z, byte[] bArr) {
            this.a = j;
            this.b = i;
            this.c = j2;
            this.d = i2;
            this.e = i3;
            this.f = i4;
            this.g = i5;
            this.h = i6;
            this.i = z;
            this.j = bArr;
        }
    }

    public static int a(int i) {
        int i2 = 0;
        while (i > 0) {
            i2++;
            i >>>= 1;
        }
        return i2;
    }

    public static d a(n nVar) throws ParserException {
        n nVar2 = nVar;
        if.a(1, nVar2, false);
        long n = nVar.n();
        int g = nVar.g();
        long n2 = nVar.n();
        int p = nVar.p();
        int p2 = nVar.p();
        int p3 = nVar.p();
        int g2 = nVar.g();
        return new d(n, g, n2, p, p2, p3, (int) Math.pow(2.0d, (double) (g2 & 15)), (int) Math.pow(2.0d, (double) ((g2 & 240) >> 4)), (nVar.g() & 1) > 0, Arrays.copyOf(nVar2.a, nVar.c()));
    }

    public static b b(n nVar) throws ParserException {
        int i = 0;
        if.a(3, nVar, false);
        String e = nVar.e((int) nVar.n());
        int length = 11 + e.length();
        long n = nVar.n();
        String[] strArr = new String[((int) n)];
        length += 4;
        while (((long) i) < n) {
            length += 4;
            strArr[i] = nVar.e((int) nVar.n());
            length += strArr[i].length();
            i++;
        }
        if ((nVar.g() & 1) != 0) {
            return new b(e, strArr, length + 1);
        }
        throw new ParserException("framing bit expected to be set");
    }

    public static boolean a(int i, n nVar, boolean z) throws ParserException {
        StringBuilder stringBuilder;
        if (nVar.b() < 7) {
            if (z) {
                return false;
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("too short header: ");
            stringBuilder.append(nVar.b());
            throw new ParserException(stringBuilder.toString());
        } else if (nVar.g() != i) {
            if (z) {
                return false;
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("expected header type ");
            stringBuilder.append(Integer.toHexString(i));
            throw new ParserException(stringBuilder.toString());
        } else if (nVar.g() == 118 && nVar.g() == 111 && nVar.g() == 114 && nVar.g() == 98 && nVar.g() == 105 && nVar.g() == 115) {
            return true;
        } else {
            if (z) {
                return false;
            }
            throw new ParserException("expected characters 'vorbis'");
        }
    }

    public static c[] a(n nVar, int i) throws ParserException {
        int i2;
        int i3 = 0;
        if.a(5, nVar, false);
        int g = nVar.g() + 1;
        id idVar = new id(nVar.a);
        idVar.b(nVar.d() * 8);
        for (i2 = 0; i2 < g; i2++) {
            if.d(idVar);
        }
        i2 = idVar.a(6) + 1;
        while (i3 < i2) {
            if (idVar.a(16) == 0) {
                i3++;
            } else {
                throw new ParserException("placeholder of time domain transforms not zeroed out");
            }
        }
        if.c(idVar);
        if.b(idVar);
        if.a(i, idVar);
        c[] a = if.a(idVar);
        if (idVar.a()) {
            return a;
        }
        throw new ParserException("framing bit after modes not set as expected");
    }

    private static c[] a(id idVar) {
        int a = idVar.a(6) + 1;
        c[] cVarArr = new c[a];
        for (int i = 0; i < a; i++) {
            cVarArr[i] = new c(idVar.a(), idVar.a(16), idVar.a(16), idVar.a(8));
        }
        return cVarArr;
    }

    private static void a(int i, id idVar) throws ParserException {
        int a = idVar.a(6) + 1;
        for (int i2 = 0; i2 < a; i2++) {
            int a2 = idVar.a(16);
            if (a2 != 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("mapping type other than 0 not supported: ");
                stringBuilder.append(a2);
                Log.e("VorbisUtil", stringBuilder.toString());
            } else {
                int a3;
                a2 = idVar.a() ? idVar.a(4) + 1 : 1;
                if (idVar.a()) {
                    a3 = idVar.a(8) + 1;
                    for (int i3 = 0; i3 < a3; i3++) {
                        int i4 = i - 1;
                        idVar.b(if.a(i4));
                        idVar.b(if.a(i4));
                    }
                }
                if (idVar.a(2) == 0) {
                    if (a2 > 1) {
                        for (a3 = 0; a3 < i; a3++) {
                            idVar.b(4);
                        }
                    }
                    for (int i5 = 0; i5 < a2; i5++) {
                        idVar.b(8);
                        idVar.b(8);
                        idVar.b(8);
                    }
                } else {
                    throw new ParserException("to reserved bits must be zero after mapping coupling steps");
                }
            }
        }
    }

    private static void b(id idVar) throws ParserException {
        int a = idVar.a(6) + 1;
        int i = 0;
        while (i < a) {
            if (idVar.a(16) <= 2) {
                int i2;
                idVar.b(24);
                idVar.b(24);
                idVar.b(24);
                int a2 = idVar.a(6) + 1;
                idVar.b(8);
                int[] iArr = new int[a2];
                for (i2 = 0; i2 < a2; i2++) {
                    iArr[i2] = ((idVar.a() ? idVar.a(5) : 0) * 8) + idVar.a(3);
                }
                for (i2 = 0; i2 < a2; i2++) {
                    for (int i3 = 0; i3 < 8; i3++) {
                        if ((iArr[i2] & (1 << i3)) != 0) {
                            idVar.b(8);
                        }
                    }
                }
                i++;
            } else {
                throw new ParserException("residueType greater than 2 is not decodable");
            }
        }
    }

    private static void c(id idVar) throws ParserException {
        int a = idVar.a(6) + 1;
        for (int i = 0; i < a; i++) {
            int a2 = idVar.a(16);
            int a3;
            switch (a2) {
                case 0:
                    idVar.b(8);
                    idVar.b(16);
                    idVar.b(16);
                    idVar.b(6);
                    idVar.b(8);
                    a3 = idVar.a(4) + 1;
                    for (a2 = 0; a2 < a3; a2++) {
                        idVar.b(8);
                    }
                    break;
                case 1:
                    int a4;
                    a3 = idVar.a(5);
                    int[] iArr = new int[a3];
                    int i2 = -1;
                    for (a2 = 0; a2 < a3; a2++) {
                        iArr[a2] = idVar.a(4);
                        if (iArr[a2] > i2) {
                            i2 = iArr[a2];
                        }
                    }
                    int[] iArr2 = new int[(i2 + 1)];
                    for (i2 = 0; i2 < iArr2.length; i2++) {
                        iArr2[i2] = idVar.a(3) + 1;
                        a4 = idVar.a(2);
                        if (a4 > 0) {
                            idVar.b(8);
                        }
                        for (int i3 = 0; i3 < (1 << a4); i3++) {
                            idVar.b(8);
                        }
                    }
                    idVar.b(2);
                    int a5 = idVar.a(4);
                    i2 = 0;
                    a4 = 0;
                    for (int i4 = 0; i4 < a3; i4++) {
                        i2 += iArr2[iArr[i4]];
                        while (a4 < i2) {
                            idVar.b(a5);
                            a4++;
                        }
                    }
                    break;
                default:
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("floor type greater than 1 not decodable: ");
                    stringBuilder.append(a2);
                    throw new ParserException(stringBuilder.toString());
            }
        }
    }

    private static a d(id idVar) throws ParserException {
        if (idVar.a(24) == 5653314) {
            int i;
            int i2;
            int a = idVar.a(16);
            int a2 = idVar.a(24);
            long[] jArr = new long[a2];
            boolean a3 = idVar.a();
            long j = 0;
            if (a3) {
                int a4 = idVar.a(5) + 1;
                i = 0;
                while (i < jArr.length) {
                    int a5 = idVar.a(if.a(a2 - i));
                    int i3 = i;
                    for (i = 0; i < a5 && i3 < jArr.length; i++) {
                        jArr[i3] = (long) a4;
                        i3++;
                    }
                    a4++;
                    i = i3;
                }
            } else {
                boolean a6 = idVar.a();
                for (i2 = 0; i2 < jArr.length; i2++) {
                    if (!a6) {
                        jArr[i2] = (long) (idVar.a(5) + 1);
                    } else if (idVar.a()) {
                        jArr[i2] = (long) (idVar.a(5) + 1);
                    } else {
                        jArr[i2] = 0;
                    }
                }
            }
            i2 = idVar.a(4);
            if (i2 <= 2) {
                if (i2 == 1 || i2 == 2) {
                    idVar.b(32);
                    idVar.b(32);
                    i = idVar.a(4) + 1;
                    idVar.b(1);
                    if (i2 != 1) {
                        j = ((long) a2) * ((long) a);
                    } else if (a != 0) {
                        j = if.a((long) a2, (long) a);
                    }
                    idVar.b((int) (j * ((long) i)));
                }
                return new a(a, a2, jArr, i2, a3);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("lookup type greater than 2 not decodable: ");
            stringBuilder.append(i2);
            throw new ParserException(stringBuilder.toString());
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("expected code book to start with [0x56, 0x43, 0x42] at ");
        stringBuilder2.append(idVar.b());
        throw new ParserException(stringBuilder2.toString());
    }

    private static long a(long j, long j2) {
        double d = (double) j;
        double d2 = (double) j2;
        Double.isNaN(d2);
        return (long) Math.floor(Math.pow(d, 1.0d / d2));
    }
}
