package com.tomatolive.library.http.utils;

class Base64Util {
    private static byte[] base64DecodeChars = new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 62, (byte) -1, (byte) -1, (byte) -1, (byte) 63, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1};
    private static final char[] base64EncodeChars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    private Base64Util() {
    }

    public static String encode(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            i = bArr[i] & 255;
            if (i2 == length) {
                stringBuilder.append(base64EncodeChars[i >>> 2]);
                stringBuilder.append(base64EncodeChars[(i & 3) << 4]);
                stringBuilder.append("==");
                break;
            }
            int i3 = i2 + 1;
            i2 = bArr[i2] & 255;
            if (i3 == length) {
                stringBuilder.append(base64EncodeChars[i >>> 2]);
                stringBuilder.append(base64EncodeChars[((i & 3) << 4) | ((i2 & 240) >>> 4)]);
                stringBuilder.append(base64EncodeChars[(i2 & 15) << 2]);
                stringBuilder.append("=");
                break;
            }
            int i4 = i3 + 1;
            i3 = bArr[i3] & 255;
            stringBuilder.append(base64EncodeChars[i >>> 2]);
            stringBuilder.append(base64EncodeChars[((i & 3) << 4) | ((i2 & 240) >>> 4)]);
            stringBuilder.append(base64EncodeChars[((i2 & 15) << 2) | ((i3 & 192) >>> 6)]);
            stringBuilder.append(base64EncodeChars[i3 & 63]);
            i = i4;
        }
        return stringBuilder.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x007f A:{LOOP_END, LOOP:0: B:1:0x000d->B:32:0x007f} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0089 A:{SYNTHETIC, EDGE_INSN: B:41:0x0089->B:33:0x0089 ?: BREAK  } */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0089 A:{SYNTHETIC, EDGE_INSN: B:37:0x0089->B:33:0x0089 ?: BREAK  } */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0089 A:{SYNTHETIC, EDGE_INSN: B:40:0x0089->B:33:0x0089 ?: BREAK  } */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0023 A:{LOOP_START, PHI: r4 , LOOP:2: B:7:0x0023->B:10:0x0030} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0089 A:{SYNTHETIC, EDGE_INSN: B:38:0x0089->B:33:0x0089 ?: BREAK  } */
    public static byte[] decode(java.lang.String r8) throws java.lang.Exception {
        /*
        r0 = "GBK";
        r8 = r8.getBytes(r0);
        r0 = r8.length;
        r1 = new java.io.ByteArrayOutputStream;
        r1.<init>(r0);
        r2 = 0;
    L_0x000d:
        if (r2 >= r0) goto L_0x0089;
    L_0x000f:
        r3 = base64DecodeChars;
        r4 = r2 + 1;
        r2 = r8[r2];
        r2 = r3[r2];
        r3 = -1;
        if (r4 >= r0) goto L_0x001f;
    L_0x001a:
        if (r2 == r3) goto L_0x001d;
    L_0x001c:
        goto L_0x001f;
    L_0x001d:
        r2 = r4;
        goto L_0x000f;
    L_0x001f:
        if (r2 != r3) goto L_0x0023;
    L_0x0021:
        goto L_0x0089;
    L_0x0023:
        r5 = base64DecodeChars;
        r6 = r4 + 1;
        r4 = r8[r4];
        r4 = r5[r4];
        if (r6 >= r0) goto L_0x0032;
    L_0x002d:
        if (r4 == r3) goto L_0x0030;
    L_0x002f:
        goto L_0x0032;
    L_0x0030:
        r4 = r6;
        goto L_0x0023;
    L_0x0032:
        if (r4 != r3) goto L_0x0035;
    L_0x0034:
        goto L_0x0089;
    L_0x0035:
        r2 = r2 << 2;
        r5 = r4 & 48;
        r5 = r5 >>> 4;
        r2 = r2 | r5;
        r1.write(r2);
    L_0x003f:
        r2 = r6 + 1;
        r5 = r8[r6];
        r6 = 61;
        if (r5 != r6) goto L_0x004c;
    L_0x0047:
        r8 = r1.toByteArray();
        return r8;
    L_0x004c:
        r7 = base64DecodeChars;
        r5 = r7[r5];
        if (r2 >= r0) goto L_0x0057;
    L_0x0052:
        if (r5 == r3) goto L_0x0055;
    L_0x0054:
        goto L_0x0057;
    L_0x0055:
        r6 = r2;
        goto L_0x003f;
    L_0x0057:
        if (r5 != r3) goto L_0x005a;
    L_0x0059:
        goto L_0x0089;
    L_0x005a:
        r4 = r4 & 15;
        r4 = r4 << 4;
        r7 = r5 & 60;
        r7 = r7 >>> 2;
        r4 = r4 | r7;
        r1.write(r4);
    L_0x0066:
        r4 = r2 + 1;
        r2 = r8[r2];
        if (r2 != r6) goto L_0x0071;
    L_0x006c:
        r8 = r1.toByteArray();
        return r8;
    L_0x0071:
        r7 = base64DecodeChars;
        r2 = r7[r2];
        if (r4 >= r0) goto L_0x007c;
    L_0x0077:
        if (r2 == r3) goto L_0x007a;
    L_0x0079:
        goto L_0x007c;
    L_0x007a:
        r2 = r4;
        goto L_0x0066;
    L_0x007c:
        if (r2 != r3) goto L_0x007f;
    L_0x007e:
        goto L_0x0089;
    L_0x007f:
        r3 = r5 & 3;
        r3 = r3 << 6;
        r2 = r2 | r3;
        r1.write(r2);
        r2 = r4;
        goto L_0x000d;
    L_0x0089:
        r8 = r1.toByteArray();
        return r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.http.utils.Base64Util.decode(java.lang.String):byte[]");
    }
}
