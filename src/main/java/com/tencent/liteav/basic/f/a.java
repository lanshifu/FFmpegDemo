package com.tencent.liteav.basic.f;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;

/* compiled from: RSAUtils */
public final class a {
    private static String a = "RSA";

    public static byte[] a(byte[] bArr, PrivateKey privateKey) throws Exception {
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(2, privateKey);
        int length = bArr.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (true) {
            int i2 = length - i;
            if (i2 > 0) {
                byte[] doFinal;
                if (i2 >= instance.getBlockSize()) {
                    doFinal = instance.doFinal(bArr, i, instance.getBlockSize());
                } else {
                    doFinal = instance.doFinal(bArr, i, i2);
                }
                byteArrayOutputStream.write(doFinal);
                i += instance.getBlockSize();
            } else {
                bArr = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return bArr;
            }
        }
    }

    public static PrivateKey a(byte[] bArr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance(a).generatePrivate(new PKCS8EncodedKeySpec(bArr));
    }
}
