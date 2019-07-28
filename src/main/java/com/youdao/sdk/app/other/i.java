package com.youdao.sdk.app.other;

import android.util.Base64;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class i {
    public static byte[] a(String str, String str2) {
        return a(str, str2.getBytes());
    }

    public static byte[] a(String str, byte[] bArr) {
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes()));
            Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
            instance.init(1, generateSecret, new IvParameterSpec(str.getBytes()));
            return instance.doFinal(bArr);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static byte[] b(String str, byte[] bArr) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            SecretKey generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes()));
            Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
            instance.init(2, generateSecret, new IvParameterSpec(str.getBytes()));
            return instance.doFinal(bArr);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static String b(String str, String str2) {
        try {
            return new String(b(str, Base64.decode(str2.getBytes(), 0)));
        } catch (Exception unused) {
            return "";
        }
    }
}
