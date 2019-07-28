package com.tomatolive.library.http.utils;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class EncryptUtil {
    private static final String CHARSET = "utf-8";
    private static final String iv = "01234567";

    private EncryptUtil() {
    }

    public static String DESEncrypt(String str, String str2) throws Exception {
        return encode(str, str2);
    }

    public static String DESDecrypt(String str, String str2) throws Exception {
        return decode(str, str2);
    }

    public static String RSAEncrypt(String str, String str2) throws Exception {
        return encodeToString(encrypt(str2.getBytes(CHARSET), getPublicKey(str), IjkMediaMeta.FF_PROFILE_H264_INTRA, 11, "RSA/ECB/PKCS1Padding"));
    }

    public static String RSADecrypt(String str, String str2) throws Exception {
        return new String(decrypt(decode(str2), getPraivateKey(str), IjkMediaMeta.FF_PROFILE_H264_INTRA, 11, "RSA/ECB/PKCS1Padding"), CHARSET);
    }

    private static String encode(String str, String str2) throws Exception {
        SecretKey generateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec(str.getBytes()));
        Cipher instance = Cipher.getInstance("desede/CBC/PKCS5Padding");
        instance.init(1, generateSecret, new IvParameterSpec(iv.getBytes()));
        return encodeToString(instance.doFinal(str2.getBytes(CHARSET)));
    }

    private static String decode(String str, String str2) throws Exception {
        SecretKey generateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec(str.getBytes()));
        Cipher instance = Cipher.getInstance("desede/CBC/PKCS5Padding");
        instance.init(2, generateSecret, new IvParameterSpec(iv.getBytes()));
        return new String(instance.doFinal(decode(str2)), CHARSET);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0046 A:{SYNTHETIC, Splitter:B:26:0x0046} */
    private static byte[] decrypt(byte[] r3, java.security.PrivateKey r4, int r5, int r6, java.lang.String r7) throws java.lang.Exception {
        /*
        r5 = r5 / 8;
        r6 = r5 - r6;
        r0 = r3.length;
        r0 = r0 / r5;
        r1 = 0;
        r7 = javax.crypto.Cipher.getInstance(r7);	 Catch:{ Exception -> 0x003b }
        r2 = 2;
        r7.init(r2, r4);	 Catch:{ Exception -> 0x003b }
        r4 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x003b }
        r0 = r0 * r6;
        r4.<init>(r0);	 Catch:{ Exception -> 0x003b }
        r6 = 0;
    L_0x0017:
        r0 = r3.length;	 Catch:{ Exception -> 0x0036, all -> 0x0033 }
        if (r6 >= r0) goto L_0x0028;
    L_0x001a:
        r0 = r3.length;	 Catch:{ Exception -> 0x0036, all -> 0x0033 }
        r0 = r0 - r6;
        if (r0 <= r5) goto L_0x001f;
    L_0x001e:
        r0 = r5;
    L_0x001f:
        r0 = r7.doFinal(r3, r6, r0);	 Catch:{ Exception -> 0x0036, all -> 0x0033 }
        r4.write(r0);	 Catch:{ Exception -> 0x0036, all -> 0x0033 }
        r6 = r6 + r5;
        goto L_0x0017;
    L_0x0028:
        r4.flush();	 Catch:{ Exception -> 0x0036, all -> 0x0033 }
        r3 = r4.toByteArray();	 Catch:{ Exception -> 0x0036, all -> 0x0033 }
        r4.close();	 Catch:{ Exception -> 0x0032 }
    L_0x0032:
        return r3;
    L_0x0033:
        r3 = move-exception;
        r1 = r4;
        goto L_0x0044;
    L_0x0036:
        r3 = move-exception;
        r1 = r4;
        goto L_0x003c;
    L_0x0039:
        r3 = move-exception;
        goto L_0x0044;
    L_0x003b:
        r3 = move-exception;
    L_0x003c:
        r4 = new java.lang.Exception;	 Catch:{ all -> 0x0039 }
        r5 = "DEENCRYPT ERROR:";
        r4.<init>(r5, r3);	 Catch:{ all -> 0x0039 }
        throw r4;	 Catch:{ all -> 0x0039 }
    L_0x0044:
        if (r1 == 0) goto L_0x0049;
    L_0x0046:
        r1.close();	 Catch:{ Exception -> 0x0049 }
    L_0x0049:
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.http.utils.EncryptUtil.decrypt(byte[], java.security.PrivateKey, int, int, java.lang.String):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x004c A:{SYNTHETIC, Splitter:B:29:0x004c} */
    private static byte[] encrypt(byte[] r3, java.security.PublicKey r4, int r5, int r6, java.lang.String r7) throws java.lang.Exception {
        /*
        r5 = r5 / 8;
        r6 = r5 - r6;
        r0 = r3.length;
        r0 = r0 / r6;
        r1 = r3.length;
        r1 = r1 % r6;
        if (r1 == 0) goto L_0x000c;
    L_0x000a:
        r0 = r0 + 1;
    L_0x000c:
        r1 = 0;
        r7 = javax.crypto.Cipher.getInstance(r7);	 Catch:{ Exception -> 0x0041 }
        r2 = 1;
        r7.init(r2, r4);	 Catch:{ Exception -> 0x0041 }
        r4 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x0041 }
        r0 = r0 * r5;
        r4.<init>(r0);	 Catch:{ Exception -> 0x0041 }
        r5 = 0;
    L_0x001d:
        r0 = r3.length;	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        if (r5 >= r0) goto L_0x002e;
    L_0x0020:
        r0 = r3.length;	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        r0 = r0 - r5;
        if (r0 <= r6) goto L_0x0025;
    L_0x0024:
        r0 = r6;
    L_0x0025:
        r0 = r7.doFinal(r3, r5, r0);	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        r4.write(r0);	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        r5 = r5 + r6;
        goto L_0x001d;
    L_0x002e:
        r4.flush();	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        r3 = r4.toByteArray();	 Catch:{ Exception -> 0x003c, all -> 0x0039 }
        r4.close();	 Catch:{ Exception -> 0x0038 }
    L_0x0038:
        return r3;
    L_0x0039:
        r3 = move-exception;
        r1 = r4;
        goto L_0x004a;
    L_0x003c:
        r3 = move-exception;
        r1 = r4;
        goto L_0x0042;
    L_0x003f:
        r3 = move-exception;
        goto L_0x004a;
    L_0x0041:
        r3 = move-exception;
    L_0x0042:
        r4 = new java.lang.Exception;	 Catch:{ all -> 0x003f }
        r5 = "ENCRYPT ERROR:";
        r4.<init>(r5, r3);	 Catch:{ all -> 0x003f }
        throw r4;	 Catch:{ all -> 0x003f }
    L_0x004a:
        if (r1 == 0) goto L_0x004f;
    L_0x004c:
        r1.close();	 Catch:{ Exception -> 0x004f }
    L_0x004f:
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.http.utils.EncryptUtil.encrypt(byte[], java.security.PublicKey, int, int, java.lang.String):byte[]");
    }

    private static PrivateKey getPraivateKey(String str) throws Exception {
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decode(str)));
    }

    private static PublicKey getPublicKey(String str) throws Exception {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decode(str)));
    }

    private static String encodeToString(byte[] bArr) {
        return Base64Util.encode(bArr);
    }

    private static byte[] decode(String str) throws Exception {
        return Base64Util.decode(str);
    }
}
