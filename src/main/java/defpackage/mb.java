package defpackage;

import com.google.zxing.WriterException;
import com.google.zxing.common.a;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: MatrixUtil */
/* renamed from: mb */
final class mb {
    private static final int[][] a = new int[][]{new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};
    private static final int[][] b = new int[][]{new int[]{0, 0, 0, 0, 0, 0, 0, 0}};
    private static final int[][] c;
    private static final int[][] d = new int[][]{new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};
    private static final int[][] e = new int[][]{new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, IjkMediaMeta.FF_PROFILE_H264_HIGH_422, -1}, new int[]{6, 30, 54, 78, 102, 126, -1}, new int[]{6, 26, 52, 78, 104, 130, -1}, new int[]{6, 30, 56, 82, 108, 134, -1}, new int[]{6, 34, 60, 86, 112, 138, -1}, new int[]{6, 30, 58, 86, 114, 142, -1}, new int[]{6, 34, 62, 90, 118, 146, -1}, new int[]{6, 30, 54, 78, 102, 126, 150}, new int[]{6, 24, 50, 76, 102, 128, 154}, new int[]{6, 28, 54, 80, 106, 132, 158}, new int[]{6, 32, 58, 84, 110, 136, 162}, new int[]{6, 26, 54, 82, 110, 138, 166}, new int[]{6, 30, 58, 86, 114, 142, 170}};
    private static final int[][] f = new int[][]{new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};

    static int a(int i) {
        int i2 = 0;
        while (i != 0) {
            i >>>= 1;
            i2++;
        }
        return i2;
    }

    private static boolean b(int i) {
        return i == -1;
    }

    private static boolean c(int i) {
        return i == -1 || i == 0 || i == 1;
    }

    static {
        int[][] iArr = new int[7][];
        iArr[0] = new int[]{0};
        iArr[1] = new int[]{0};
        iArr[2] = new int[]{0};
        iArr[3] = new int[]{0};
        iArr[4] = new int[]{0};
        iArr[5] = new int[]{0};
        iArr[6] = new int[]{0};
        c = iArr;
    }

    static void a(ly lyVar) {
        lyVar.a((byte) -1);
    }

    static void a(a aVar, ErrorCorrectionLevel errorCorrectionLevel, int i, int i2, ly lyVar) throws WriterException {
        mb.a(lyVar);
        mb.a(i, lyVar);
        mb.a(errorCorrectionLevel, i2, lyVar);
        mb.b(i, lyVar);
        mb.a(aVar, i2, lyVar);
    }

    static void a(int i, ly lyVar) throws WriterException {
        mb.d(lyVar);
        mb.c(lyVar);
        mb.c(i, lyVar);
        mb.b(lyVar);
    }

    static void a(ErrorCorrectionLevel errorCorrectionLevel, int i, ly lyVar) throws WriterException {
        a aVar = new a();
        mb.a(errorCorrectionLevel, i, aVar);
        for (i = 0; i < aVar.a(); i++) {
            boolean a = aVar.a((aVar.a() - 1) - i);
            lyVar.a(f[i][0], f[i][1], a);
            if (i < 8) {
                lyVar.a((lyVar.b() - i) - 1, 8, a);
            } else {
                lyVar.a(8, (lyVar.a() - 7) + (i - 8), a);
            }
        }
    }

    static void b(int i, ly lyVar) throws WriterException {
        if (i >= 7) {
            a aVar = new a();
            mb.a(i, aVar);
            i = 0;
            int i2 = 17;
            while (i < 6) {
                int i3 = i2;
                for (i2 = 0; i2 < 3; i2++) {
                    boolean a = aVar.a(i3);
                    i3--;
                    lyVar.a(i, (lyVar.a() - 11) + i2, a);
                    lyVar.a((lyVar.a() - 11) + i2, i, a);
                }
                i++;
                i2 = i3;
            }
        }
    }

    static void a(a aVar, int i, ly lyVar) throws WriterException {
        int b = lyVar.b() - 1;
        int a = lyVar.a() - 1;
        int i2 = 0;
        int i3 = -1;
        while (b > 0) {
            if (b == 6) {
                b--;
            }
            while (a >= 0 && a < lyVar.a()) {
                int i4 = i2;
                for (i2 = 0; i2 < 2; i2++) {
                    int i5 = b - i2;
                    if (mb.b(lyVar.a(i5, a))) {
                        boolean a2;
                        if (i4 < aVar.a()) {
                            a2 = aVar.a(i4);
                            i4++;
                        } else {
                            a2 = false;
                        }
                        if (i != -1 && ma.a(i, i5, a)) {
                            a2 = !a2;
                        }
                        lyVar.a(i5, a, a2);
                    }
                }
                a += i3;
                i2 = i4;
            }
            i3 = -i3;
            a += i3;
            b -= 2;
        }
        if (i2 != aVar.a()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Not all bits consumed: ");
            stringBuilder.append(i2);
            stringBuilder.append('/');
            stringBuilder.append(aVar.a());
            throw new WriterException(stringBuilder.toString());
        }
    }

    static int a(int i, int i2) {
        int a = mb.a(i2);
        i <<= a - 1;
        while (mb.a(i) >= a) {
            i ^= i2 << (mb.a(i) - a);
        }
        return i;
    }

    static void a(ErrorCorrectionLevel errorCorrectionLevel, int i, a aVar) throws WriterException {
        if (mc.h(i)) {
            int bits = (errorCorrectionLevel.getBits() << 3) | i;
            aVar.a(bits, 5);
            aVar.a(mb.a(bits, 1335), 10);
            a aVar2 = new a();
            aVar2.a(21522, 15);
            aVar.b(aVar2);
            if (aVar.a() != 15) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("should not happen but we got: ");
                stringBuilder.append(aVar.a());
                throw new WriterException(stringBuilder.toString());
            }
            return;
        }
        throw new WriterException("Invalid mask pattern");
    }

    static void a(int i, a aVar) throws WriterException {
        aVar.a(i, 6);
        aVar.a(mb.a(i, 7973), 12);
        if (aVar.a() != 18) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("should not happen but we got: ");
            stringBuilder.append(aVar.a());
            throw new WriterException(stringBuilder.toString());
        }
    }

    private static void b(ly lyVar) throws WriterException {
        int i = 8;
        while (i < lyVar.b() - 8) {
            int i2 = i + 1;
            int i3 = i2 % 2;
            if (mb.c(lyVar.a(i, 6))) {
                if (mb.b(lyVar.a(i, 6))) {
                    lyVar.a(i, 6, i3);
                }
                if (mb.c(lyVar.a(6, i))) {
                    if (mb.b(lyVar.a(6, i))) {
                        lyVar.a(6, i, i3);
                    }
                    i = i2;
                } else {
                    throw new WriterException();
                }
            }
            throw new WriterException();
        }
    }

    private static void c(ly lyVar) throws WriterException {
        if (lyVar.a(8, lyVar.a() - 8) != (byte) 0) {
            lyVar.a(8, lyVar.a() - 8, 1);
            return;
        }
        throw new WriterException();
    }

    private static void a(int i, int i2, ly lyVar) throws WriterException {
        if (b[0].length == 8 && b.length == 1) {
            int i3 = 0;
            while (i3 < 8) {
                int i4 = i + i3;
                if (mb.b(lyVar.a(i4, i2))) {
                    lyVar.a(i4, i2, b[0][i3]);
                    i3++;
                } else {
                    throw new WriterException();
                }
            }
            return;
        }
        throw new WriterException("Bad horizontal separation pattern");
    }

    private static void b(int i, int i2, ly lyVar) throws WriterException {
        if (c[0].length == 1 && c.length == 7) {
            int i3 = 0;
            while (i3 < 7) {
                int i4 = i2 + i3;
                if (mb.b(lyVar.a(i, i4))) {
                    lyVar.a(i, i4, c[i3][0]);
                    i3++;
                } else {
                    throw new WriterException();
                }
            }
            return;
        }
        throw new WriterException("Bad vertical separation pattern");
    }

    private static void c(int i, int i2, ly lyVar) throws WriterException {
        if (d[0].length == 5 && d.length == 5) {
            for (int i3 = 0; i3 < 5; i3++) {
                int i4 = 0;
                while (i4 < 5) {
                    int i5 = i + i4;
                    int i6 = i2 + i3;
                    if (mb.b(lyVar.a(i5, i6))) {
                        lyVar.a(i5, i6, d[i3][i4]);
                        i4++;
                    } else {
                        throw new WriterException();
                    }
                }
            }
            return;
        }
        throw new WriterException("Bad position adjustment");
    }

    private static void d(int i, int i2, ly lyVar) throws WriterException {
        if (a[0].length == 7 && a.length == 7) {
            for (int i3 = 0; i3 < 7; i3++) {
                int i4 = 0;
                while (i4 < 7) {
                    int i5 = i + i4;
                    int i6 = i2 + i3;
                    if (mb.b(lyVar.a(i5, i6))) {
                        lyVar.a(i5, i6, a[i3][i4]);
                        i4++;
                    } else {
                        throw new WriterException();
                    }
                }
            }
            return;
        }
        throw new WriterException("Bad position detection pattern");
    }

    private static void d(ly lyVar) throws WriterException {
        int length = a[0].length;
        mb.d(0, 0, lyVar);
        mb.d(lyVar.b() - length, 0, lyVar);
        mb.d(0, lyVar.b() - length, lyVar);
        length = b[0].length;
        int i = length - 1;
        mb.a(0, i, lyVar);
        mb.a(lyVar.b() - length, i, lyVar);
        mb.a(0, lyVar.b() - length, lyVar);
        length = c.length;
        mb.b(length, 0, lyVar);
        mb.b((lyVar.a() - length) - 1, 0, lyVar);
        mb.b(length, lyVar.a() - length, lyVar);
    }

    private static void c(int i, ly lyVar) throws WriterException {
        if (i >= 2) {
            i--;
            int[] iArr = e[i];
            i = e[i].length;
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    int i4 = iArr[i2];
                    int i5 = iArr[i3];
                    if (!(i5 == -1 || i4 == -1 || !mb.b(lyVar.a(i5, i4)))) {
                        mb.c(i5 - 2, i4 - 2, lyVar);
                    }
                }
            }
        }
    }
}
