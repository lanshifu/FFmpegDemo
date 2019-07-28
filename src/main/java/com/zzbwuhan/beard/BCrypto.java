package com.zzbwuhan.beard;

public class BCrypto {
    public static native long decodeKey(String str, int i);

    public static native int decodeVideoBuf2(byte[] bArr, int i, long j, int i2);

    public static native void releaseKey(long j);

    static {
        System.loadLibrary("bcrypto");
    }
}
