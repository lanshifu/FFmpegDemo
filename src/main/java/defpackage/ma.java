package defpackage;

/* compiled from: MaskUtil */
/* renamed from: ma */
final class ma {
    static int a(ly lyVar) {
        return ma.a(lyVar, true) + ma.a(lyVar, false);
    }

    static int b(ly lyVar) {
        byte[][] c = lyVar.c();
        int b = lyVar.b();
        int a = lyVar.a();
        int i = 0;
        int i2 = 0;
        while (i < a - 1) {
            int i3 = i2;
            i2 = 0;
            while (i2 < b - 1) {
                byte b2 = c[i][i2];
                int i4 = i2 + 1;
                if (b2 == c[i][i4]) {
                    int i5 = i + 1;
                    if (b2 == c[i5][i2] && b2 == c[i5][i4]) {
                        i3 += 3;
                    }
                }
                i2 = i4;
            }
            i++;
            i2 = i3;
        }
        return i2;
    }

    /* JADX WARNING: Missing block: B:39:0x0090, code skipped:
            if (r0[r3][r6] == (byte) 0) goto L_0x0092;
     */
    static int c(defpackage.ly r10) {
        /*
        r0 = r10.c();
        r1 = r10.b();
        r10 = r10.a();
        r2 = 0;
        r3 = 0;
        r4 = 0;
    L_0x000f:
        if (r3 >= r10) goto L_0x011b;
    L_0x0011:
        r5 = r4;
        r4 = 0;
    L_0x0013:
        if (r4 >= r1) goto L_0x0116;
    L_0x0015:
        r6 = r4 + 6;
        r7 = 1;
        if (r6 >= r1) goto L_0x0094;
    L_0x001a:
        r8 = r0[r3];
        r8 = r8[r4];
        if (r8 != r7) goto L_0x0094;
    L_0x0020:
        r8 = r0[r3];
        r9 = r4 + 1;
        r8 = r8[r9];
        if (r8 != 0) goto L_0x0094;
    L_0x0028:
        r8 = r0[r3];
        r9 = r4 + 2;
        r8 = r8[r9];
        if (r8 != r7) goto L_0x0094;
    L_0x0030:
        r8 = r0[r3];
        r9 = r4 + 3;
        r8 = r8[r9];
        if (r8 != r7) goto L_0x0094;
    L_0x0038:
        r8 = r0[r3];
        r9 = r4 + 4;
        r8 = r8[r9];
        if (r8 != r7) goto L_0x0094;
    L_0x0040:
        r8 = r0[r3];
        r9 = r4 + 5;
        r8 = r8[r9];
        if (r8 != 0) goto L_0x0094;
    L_0x0048:
        r8 = r0[r3];
        r6 = r8[r6];
        if (r6 != r7) goto L_0x0094;
    L_0x004e:
        r6 = r4 + 10;
        if (r6 >= r1) goto L_0x0070;
    L_0x0052:
        r8 = r0[r3];
        r9 = r4 + 7;
        r8 = r8[r9];
        if (r8 != 0) goto L_0x0070;
    L_0x005a:
        r8 = r0[r3];
        r9 = r4 + 8;
        r8 = r8[r9];
        if (r8 != 0) goto L_0x0070;
    L_0x0062:
        r8 = r0[r3];
        r9 = r4 + 9;
        r8 = r8[r9];
        if (r8 != 0) goto L_0x0070;
    L_0x006a:
        r8 = r0[r3];
        r6 = r8[r6];
        if (r6 == 0) goto L_0x0092;
    L_0x0070:
        r6 = r4 + -4;
        if (r6 < 0) goto L_0x0094;
    L_0x0074:
        r8 = r0[r3];
        r9 = r4 + -1;
        r8 = r8[r9];
        if (r8 != 0) goto L_0x0094;
    L_0x007c:
        r8 = r0[r3];
        r9 = r4 + -2;
        r8 = r8[r9];
        if (r8 != 0) goto L_0x0094;
    L_0x0084:
        r8 = r0[r3];
        r9 = r4 + -3;
        r8 = r8[r9];
        if (r8 != 0) goto L_0x0094;
    L_0x008c:
        r8 = r0[r3];
        r6 = r8[r6];
        if (r6 != 0) goto L_0x0094;
    L_0x0092:
        r5 = r5 + 40;
    L_0x0094:
        r6 = r3 + 6;
        if (r6 >= r10) goto L_0x0112;
    L_0x0098:
        r8 = r0[r3];
        r8 = r8[r4];
        if (r8 != r7) goto L_0x0112;
    L_0x009e:
        r8 = r3 + 1;
        r8 = r0[r8];
        r8 = r8[r4];
        if (r8 != 0) goto L_0x0112;
    L_0x00a6:
        r8 = r3 + 2;
        r8 = r0[r8];
        r8 = r8[r4];
        if (r8 != r7) goto L_0x0112;
    L_0x00ae:
        r8 = r3 + 3;
        r8 = r0[r8];
        r8 = r8[r4];
        if (r8 != r7) goto L_0x0112;
    L_0x00b6:
        r8 = r3 + 4;
        r8 = r0[r8];
        r8 = r8[r4];
        if (r8 != r7) goto L_0x0112;
    L_0x00be:
        r8 = r3 + 5;
        r8 = r0[r8];
        r8 = r8[r4];
        if (r8 != 0) goto L_0x0112;
    L_0x00c6:
        r6 = r0[r6];
        r6 = r6[r4];
        if (r6 != r7) goto L_0x0112;
    L_0x00cc:
        r6 = r3 + 10;
        if (r6 >= r10) goto L_0x00ee;
    L_0x00d0:
        r7 = r3 + 7;
        r7 = r0[r7];
        r7 = r7[r4];
        if (r7 != 0) goto L_0x00ee;
    L_0x00d8:
        r7 = r3 + 8;
        r7 = r0[r7];
        r7 = r7[r4];
        if (r7 != 0) goto L_0x00ee;
    L_0x00e0:
        r7 = r3 + 9;
        r7 = r0[r7];
        r7 = r7[r4];
        if (r7 != 0) goto L_0x00ee;
    L_0x00e8:
        r6 = r0[r6];
        r6 = r6[r4];
        if (r6 == 0) goto L_0x0110;
    L_0x00ee:
        r6 = r3 + -4;
        if (r6 < 0) goto L_0x0112;
    L_0x00f2:
        r7 = r3 + -1;
        r7 = r0[r7];
        r7 = r7[r4];
        if (r7 != 0) goto L_0x0112;
    L_0x00fa:
        r7 = r3 + -2;
        r7 = r0[r7];
        r7 = r7[r4];
        if (r7 != 0) goto L_0x0112;
    L_0x0102:
        r7 = r3 + -3;
        r7 = r0[r7];
        r7 = r7[r4];
        if (r7 != 0) goto L_0x0112;
    L_0x010a:
        r6 = r0[r6];
        r6 = r6[r4];
        if (r6 != 0) goto L_0x0112;
    L_0x0110:
        r5 = r5 + 40;
    L_0x0112:
        r4 = r4 + 1;
        goto L_0x0013;
    L_0x0116:
        r3 = r3 + 1;
        r4 = r5;
        goto L_0x000f;
    L_0x011b:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ma.c(ly):int");
    }

    static int d(ly lyVar) {
        byte[][] c = lyVar.c();
        int b = lyVar.b();
        int a = lyVar.a();
        int i = 0;
        int i2 = 0;
        while (i < a) {
            int i3 = i2;
            for (i2 = 0; i2 < b; i2++) {
                if (c[i][i2] == (byte) 1) {
                    i3++;
                }
            }
            i++;
            i2 = i3;
        }
        double d = (double) i2;
        double a2 = (double) (lyVar.a() * lyVar.b());
        Double.isNaN(d);
        Double.isNaN(a2);
        return (Math.abs((int) (((d / a2) * 100.0d) - 50.0d)) / 5) * 10;
    }

    static boolean a(int i, int i2, int i3) {
        if (mc.h(i)) {
            switch (i) {
                case 0:
                    i = (i3 + i2) & 1;
                    break;
                case 1:
                    i = i3 & 1;
                    break;
                case 2:
                    i = i2 % 3;
                    break;
                case 3:
                    i = (i3 + i2) % 3;
                    break;
                case 4:
                    i = ((i3 >>> 1) + (i2 / 3)) & 1;
                    break;
                case 5:
                    i3 *= i2;
                    i = (i3 & 1) + (i3 % 3);
                    break;
                case 6:
                    i3 *= i2;
                    i = ((i3 & 1) + (i3 % 3)) & 1;
                    break;
                case 7:
                    i = (((i3 * i2) % 3) + ((i3 + i2) & 1)) & 1;
                    break;
                default:
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Invalid mask pattern: ");
                    stringBuilder.append(i);
                    throw new IllegalArgumentException(stringBuilder.toString());
            }
            if (i == 0) {
                return true;
            }
            return false;
        }
        throw new IllegalArgumentException("Invalid mask pattern");
    }

    private static int a(ly lyVar, boolean z) {
        int a = z ? lyVar.a() : lyVar.b();
        int b = z ? lyVar.b() : lyVar.a();
        byte[][] c = lyVar.c();
        int i = 0;
        int i2 = 0;
        byte b2 = (byte) -1;
        while (i < a) {
            byte b3 = b2;
            int i3 = 0;
            int i4 = i2;
            i2 = 0;
            while (i2 < b) {
                byte b4 = z ? c[i][i2] : c[i2][i];
                if (b4 == b3) {
                    i3++;
                    if (i3 == 5) {
                        i4 += 3;
                    } else if (i3 > 5) {
                        i4++;
                    }
                } else {
                    b3 = b4;
                    i3 = 1;
                }
                i2++;
            }
            i++;
            i2 = i4;
            b2 = b3;
        }
        return i2;
    }
}
