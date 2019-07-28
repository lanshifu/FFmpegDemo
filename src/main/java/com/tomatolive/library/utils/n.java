package com.tomatolive.library.utils;

import android.text.TextUtils;
import java.security.MessageDigest;

/* compiled from: MD5Utils */
public class n {
    private static char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String a(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            return u.a(instance.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(String str) {
        return a(str.getBytes());
    }

    private static byte[] c(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(str.getBytes("UTF-8"));
            return instance.digest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            if ((bArr[i] & 255) < 16) {
                stringBuilder.append("0");
            }
            stringBuilder.append(Long.toString((long) (bArr[i] & 255), 16));
        }
        return stringBuilder.toString();
    }

    public static String b(String str) {
        try {
            if (TextUtils.isEmpty(b(c(str)))) {
                return str;
            }
            return new String(b(c(str)).getBytes("UTF-8"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }
}
