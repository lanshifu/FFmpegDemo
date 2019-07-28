package org.xutils.common.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel.MapMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5 {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private MD5() {
    }

    public static String toHexString(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            stringBuilder.append(a[(b >> 4) & 15]);
            stringBuilder.append(a[b & 15]);
        }
        return stringBuilder.toString();
    }

    public static String md5(File file) throws IOException {
        Closeable channel;
        Throwable e;
        Closeable closeable = null;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            Closeable fileInputStream = new FileInputStream(file);
            try {
                channel = fileInputStream.getChannel();
            } catch (NoSuchAlgorithmException e2) {
                e = e2;
                channel = null;
                closeable = fileInputStream;
                try {
                    throw new RuntimeException(e);
                } catch (Throwable th) {
                    e = th;
                    IOUtil.closeQuietly(closeable);
                    IOUtil.closeQuietly(channel);
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                channel = null;
                closeable = fileInputStream;
                IOUtil.closeQuietly(closeable);
                IOUtil.closeQuietly(channel);
                throw e;
            }
            try {
                instance.update(channel.map(MapMode.READ_ONLY, 0, file.length()));
                byte[] digest = instance.digest();
                IOUtil.closeQuietly(fileInputStream);
                IOUtil.closeQuietly(channel);
                return toHexString(digest);
            } catch (NoSuchAlgorithmException e3) {
                e = e3;
                closeable = fileInputStream;
                throw new RuntimeException(e);
            } catch (Throwable th3) {
                e = th3;
                closeable = fileInputStream;
                IOUtil.closeQuietly(closeable);
                IOUtil.closeQuietly(channel);
                throw e;
            }
        } catch (NoSuchAlgorithmException e4) {
            e = e4;
            channel = null;
            throw new RuntimeException(e);
        } catch (Throwable th4) {
            e = th4;
            channel = null;
            IOUtil.closeQuietly(closeable);
            IOUtil.closeQuietly(channel);
            throw e;
        }
    }

    public static String md5(String str) {
        try {
            return toHexString(MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }
}
