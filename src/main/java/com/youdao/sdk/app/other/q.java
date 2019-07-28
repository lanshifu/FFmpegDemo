package com.youdao.sdk.app.other;

import java.security.MessageDigest;
import java.util.concurrent.atomic.AtomicLong;

public class q {
    private static final AtomicLong a = new AtomicLong(1);

    public static String a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            byte[] bytes = str.getBytes("UTF-8");
            instance.update(bytes, 0, bytes.length);
            int length = instance.digest().length;
            for (int i = 0; i < length; i++) {
                stringBuilder.append(String.format("%02X", new Object[]{Byte.valueOf(bytes[i])}));
            }
            return stringBuilder.toString().toLowerCase();
        } catch (Exception unused) {
            return "";
        }
    }
}
