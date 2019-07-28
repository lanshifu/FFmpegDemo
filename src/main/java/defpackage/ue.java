package defpackage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: Base64 */
/* renamed from: ue */
public class ue {
    private static final Pattern a = Pattern.compile("^data:image/\\w+?;.*?base64,(.*)");

    /* compiled from: Base64 */
    /* renamed from: ue$a */
    private static abstract class a {
        public byte[] a;
        public int b;

        private a() {
        }
    }

    /* compiled from: Base64 */
    /* renamed from: ue$b */
    static class b extends a {
        private static final int[] c = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int[] d = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private int e;
        private int f;
        private final int[] g;

        public b(int i, byte[] bArr) {
            super();
            this.a = bArr;
            this.g = (i & 8) == 0 ? c : d;
            this.e = 0;
            this.f = 0;
        }

        /* JADX WARNING: Removed duplicated region for block: B:53:0x00ed  */
        /* JADX WARNING: Removed duplicated region for block: B:51:0x00e6  */
        /* JADX WARNING: Missing block: B:44:0x00d9, code skipped:
            r6 = r13;
     */
        public boolean a(byte[] r12, int r13, int r14, boolean r15) {
            /*
            r11 = this;
            r0 = r11.e;
            r1 = 0;
            r2 = 6;
            if (r0 != r2) goto L_0x0007;
        L_0x0006:
            return r1;
        L_0x0007:
            r14 = r14 + r13;
            r0 = r11.e;
            r3 = r11.f;
            r4 = r11.a;
            r5 = r11.g;
            r6 = r3;
            r3 = 0;
        L_0x0012:
            r7 = 4;
            if (r13 >= r14) goto L_0x00e3;
        L_0x0015:
            if (r0 != 0) goto L_0x005c;
        L_0x0017:
            r8 = r13 + 4;
            if (r8 > r14) goto L_0x0058;
        L_0x001b:
            r6 = r12[r13];
            r6 = r6 & 255;
            r6 = r5[r6];
            r6 = r6 << 18;
            r9 = r13 + 1;
            r9 = r12[r9];
            r9 = r9 & 255;
            r9 = r5[r9];
            r9 = r9 << 12;
            r6 = r6 | r9;
            r9 = r13 + 2;
            r9 = r12[r9];
            r9 = r9 & 255;
            r9 = r5[r9];
            r9 = r9 << r2;
            r6 = r6 | r9;
            r9 = r13 + 3;
            r9 = r12[r9];
            r9 = r9 & 255;
            r9 = r5[r9];
            r6 = r6 | r9;
            if (r6 < 0) goto L_0x0058;
        L_0x0043:
            r13 = r3 + 2;
            r9 = (byte) r6;
            r4[r13] = r9;
            r13 = r3 + 1;
            r9 = r6 >> 8;
            r9 = (byte) r9;
            r4[r13] = r9;
            r13 = r6 >> 16;
            r13 = (byte) r13;
            r4[r3] = r13;
            r3 = r3 + 3;
            r13 = r8;
            goto L_0x0017;
        L_0x0058:
            if (r13 < r14) goto L_0x005c;
        L_0x005a:
            goto L_0x00e3;
        L_0x005c:
            r8 = r13 + 1;
            r13 = r12[r13];
            r13 = r13 & 255;
            r13 = r5[r13];
            r9 = -2;
            r10 = -1;
            switch(r0) {
                case 0: goto L_0x00d5;
                case 1: goto L_0x00c8;
                case 2: goto L_0x00af;
                case 3: goto L_0x007c;
                case 4: goto L_0x0070;
                case 5: goto L_0x006b;
                default: goto L_0x0069;
            };
        L_0x0069:
            goto L_0x00e0;
        L_0x006b:
            if (r13 == r10) goto L_0x00e0;
        L_0x006d:
            r11.e = r2;
            return r1;
        L_0x0070:
            if (r13 != r9) goto L_0x0077;
        L_0x0072:
            r13 = r0 + 1;
            r0 = r13;
            goto L_0x00e0;
        L_0x0077:
            if (r13 == r10) goto L_0x00e0;
        L_0x0079:
            r11.e = r2;
            return r1;
        L_0x007c:
            if (r13 < 0) goto L_0x0097;
        L_0x007e:
            r0 = r6 << 6;
            r13 = r13 | r0;
            r0 = r3 + 2;
            r6 = (byte) r13;
            r4[r0] = r6;
            r0 = r3 + 1;
            r6 = r13 >> 8;
            r6 = (byte) r6;
            r4[r0] = r6;
            r0 = r13 >> 16;
            r0 = (byte) r0;
            r4[r3] = r0;
            r3 = r3 + 3;
            r6 = r13;
            r0 = 0;
            goto L_0x00e0;
        L_0x0097:
            if (r13 != r9) goto L_0x00aa;
        L_0x0099:
            r13 = r3 + 1;
            r0 = r6 >> 2;
            r0 = (byte) r0;
            r4[r13] = r0;
            r13 = r6 >> 10;
            r13 = (byte) r13;
            r4[r3] = r13;
            r3 = r3 + 2;
            r13 = 5;
            r0 = 5;
            goto L_0x00e0;
        L_0x00aa:
            if (r13 == r10) goto L_0x00e0;
        L_0x00ac:
            r11.e = r2;
            return r1;
        L_0x00af:
            if (r13 < 0) goto L_0x00b7;
        L_0x00b1:
            r6 = r6 << 6;
            r13 = r13 | r6;
            r0 = r0 + 1;
            goto L_0x00d9;
        L_0x00b7:
            if (r13 != r9) goto L_0x00c3;
        L_0x00b9:
            r13 = r3 + 1;
            r0 = r6 >> 4;
            r0 = (byte) r0;
            r4[r3] = r0;
            r3 = r13;
            r0 = 4;
            goto L_0x00e0;
        L_0x00c3:
            if (r13 == r10) goto L_0x00e0;
        L_0x00c5:
            r11.e = r2;
            return r1;
        L_0x00c8:
            if (r13 < 0) goto L_0x00d0;
        L_0x00ca:
            r6 = r6 << 6;
            r13 = r13 | r6;
            r0 = r0 + 1;
            goto L_0x00d9;
        L_0x00d0:
            if (r13 == r10) goto L_0x00e0;
        L_0x00d2:
            r11.e = r2;
            return r1;
        L_0x00d5:
            if (r13 < 0) goto L_0x00db;
        L_0x00d7:
            r0 = r0 + 1;
        L_0x00d9:
            r6 = r13;
            goto L_0x00e0;
        L_0x00db:
            if (r13 == r10) goto L_0x00e0;
        L_0x00dd:
            r11.e = r2;
            return r1;
        L_0x00e0:
            r13 = r8;
            goto L_0x0012;
        L_0x00e3:
            r12 = 1;
            if (r15 != 0) goto L_0x00ed;
        L_0x00e6:
            r11.e = r0;
            r11.f = r6;
            r11.b = r3;
            return r12;
        L_0x00ed:
            switch(r0) {
                case 0: goto L_0x010f;
                case 1: goto L_0x010c;
                case 2: goto L_0x0103;
                case 3: goto L_0x00f4;
                case 4: goto L_0x00f1;
                default: goto L_0x00f0;
            };
        L_0x00f0:
            goto L_0x010f;
        L_0x00f1:
            r11.e = r2;
            return r1;
        L_0x00f4:
            r13 = r3 + 1;
            r14 = r6 >> 10;
            r14 = (byte) r14;
            r4[r3] = r14;
            r3 = r13 + 1;
            r14 = r6 >> 2;
            r14 = (byte) r14;
            r4[r13] = r14;
            goto L_0x010f;
        L_0x0103:
            r13 = r3 + 1;
            r14 = r6 >> 4;
            r14 = (byte) r14;
            r4[r3] = r14;
            r3 = r13;
            goto L_0x010f;
        L_0x010c:
            r11.e = r2;
            return r1;
        L_0x010f:
            r11.e = r0;
            r11.b = r3;
            return r12;
            */
            throw new UnsupportedOperationException("Method not decompiled: defpackage.ue$b.a(byte[], int, int, boolean):boolean");
        }
    }

    public static boolean a(String str) {
        return a.matcher(str).find();
    }

    public static byte[] b(String str) {
        Matcher matcher = a.matcher(str);
        return matcher.find() ? ue.a(matcher.group(1), 0) : null;
    }

    public static byte[] a(String str, int i) {
        return ue.a(str.getBytes(), i);
    }

    public static byte[] a(byte[] bArr, int i) {
        return ue.a(bArr, 0, bArr.length, i);
    }

    public static byte[] a(byte[] bArr, int i, int i2, int i3) {
        b bVar = new b(i3, new byte[((i2 * 3) / 4)]);
        if (!bVar.a(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (bVar.b == bVar.a.length) {
            return bVar.a;
        } else {
            bArr = new byte[bVar.b];
            System.arraycopy(bVar.a, 0, bArr, 0, bVar.b);
            return bArr;
        }
    }
}
