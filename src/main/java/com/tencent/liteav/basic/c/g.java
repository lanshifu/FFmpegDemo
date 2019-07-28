package com.tencent.liteav.basic.c;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;

/* compiled from: RSAUtils */
public class g {
    public static final byte[] a = "#PART#".getBytes();

    public static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        PrivateKey generatePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(bArr2));
        Cipher instance = Cipher.getInstance("RSA/None/PKCS1Padding");
        instance.init(2, generatePrivate);
        return instance.doFinal(bArr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0089 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0067  */
    public static byte[] b(byte[] r12, byte[] r13) throws java.lang.Exception {
        /*
        r0 = a;
        r0 = r0.length;
        if (r0 > 0) goto L_0x000a;
    L_0x0005:
        r12 = a(r12, r13);
        return r12;
    L_0x000a:
        r1 = r12.length;
        r2 = new java.util.ArrayList;
        r3 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r2.<init>(r3);
        r3 = 0;
        r4 = 0;
        r5 = 0;
    L_0x0015:
        if (r4 >= r1) goto L_0x008b;
    L_0x0017:
        r6 = r12[r4];
        r7 = r1 + -1;
        r8 = 1;
        if (r4 != r7) goto L_0x003f;
    L_0x001e:
        r6 = r1 - r5;
        r6 = new byte[r6];
        r7 = r6.length;
        java.lang.System.arraycopy(r12, r5, r6, r3, r7);
        r5 = a(r6, r13);
        r6 = r5.length;
        r7 = 0;
    L_0x002c:
        if (r7 >= r6) goto L_0x003a;
    L_0x002e:
        r9 = r5[r7];
        r9 = java.lang.Byte.valueOf(r9);
        r2.add(r9);
        r7 = r7 + 1;
        goto L_0x002c;
    L_0x003a:
        r5 = r4 + r0;
        r4 = r5 + -1;
        goto L_0x0064;
    L_0x003f:
        r7 = a;
        r7 = r7[r3];
        if (r6 != r7) goto L_0x0064;
    L_0x0045:
        if (r0 <= r8) goto L_0x0062;
    L_0x0047:
        r6 = r4 + r0;
        if (r6 >= r1) goto L_0x0064;
    L_0x004b:
        r6 = 1;
        r7 = 0;
    L_0x004d:
        if (r6 >= r0) goto L_0x0065;
    L_0x004f:
        r9 = a;
        r9 = r9[r6];
        r10 = r4 + r6;
        r10 = r12[r10];
        if (r9 == r10) goto L_0x005a;
    L_0x0059:
        goto L_0x0065;
    L_0x005a:
        r9 = r0 + -1;
        if (r6 != r9) goto L_0x005f;
    L_0x005e:
        r7 = 1;
    L_0x005f:
        r6 = r6 + 1;
        goto L_0x004d;
    L_0x0062:
        r7 = 1;
        goto L_0x0065;
    L_0x0064:
        r7 = 0;
    L_0x0065:
        if (r7 == 0) goto L_0x0089;
    L_0x0067:
        r6 = r4 - r5;
        r6 = new byte[r6];
        r7 = r6.length;
        java.lang.System.arraycopy(r12, r5, r6, r3, r7);
        r5 = a(r6, r13);
        r6 = r5.length;
        r7 = 0;
    L_0x0075:
        if (r7 >= r6) goto L_0x0083;
    L_0x0077:
        r9 = r5[r7];
        r9 = java.lang.Byte.valueOf(r9);
        r2.add(r9);
        r7 = r7 + 1;
        goto L_0x0075;
    L_0x0083:
        r4 = r4 + r0;
        r5 = r4 + -1;
        r11 = r5;
        r5 = r4;
        r4 = r11;
    L_0x0089:
        r4 = r4 + r8;
        goto L_0x0015;
    L_0x008b:
        r12 = r2.size();
        r12 = new byte[r12];
        r13 = r2.iterator();
    L_0x0095:
        r0 = r13.hasNext();
        if (r0 == 0) goto L_0x00ab;
    L_0x009b:
        r0 = r13.next();
        r0 = (java.lang.Byte) r0;
        r1 = r3 + 1;
        r0 = r0.byteValue();
        r12[r3] = r0;
        r3 = r1;
        goto L_0x0095;
    L_0x00ab:
        return r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.basic.c.g.b(byte[], byte[]):byte[]");
    }
}
