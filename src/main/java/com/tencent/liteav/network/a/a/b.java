package com.tencent.liteav.network.a.a;

import com.tencent.liteav.network.a.b.a;
import com.tencent.liteav.network.a.e;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.IDN;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: DnsMessage */
public final class b {
    public static byte[] a(String str, int i) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream(IjkMediaMeta.FF_PROFILE_H264_CONSTRAINED);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        a aVar = new a();
        aVar.a(8);
        try {
            dataOutputStream.writeShort((short) i);
            dataOutputStream.writeShort((short) aVar.a());
            dataOutputStream.writeShort(1);
            dataOutputStream.writeShort(0);
            dataOutputStream.writeShort(0);
            dataOutputStream.writeShort(0);
            dataOutputStream.flush();
            b(byteArrayOutputStream, str);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    private static void a(OutputStream outputStream, String str) throws IOException {
        for (String toASCII : str.split("[.。．｡]")) {
            byte[] bytes = IDN.toASCII(toASCII).getBytes();
            outputStream.write(bytes.length);
            outputStream.write(bytes, 0, bytes.length);
        }
        outputStream.write(0);
    }

    private static void b(OutputStream outputStream, String str) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        a(outputStream, str);
        dataOutputStream.writeShort(1);
        dataOutputStream.writeShort(1);
    }

    public static e[] a(byte[] bArr, int i, String str) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        if (readUnsignedShort == i) {
            i = dataInputStream.readUnsignedShort();
            int i2 = 1;
            Object obj = ((i >> 8) & 1) == 1 ? 1 : null;
            if (((i >> 7) & 1) != 1) {
                i2 = 0;
            }
            if (i2 == 0 || obj == null) {
                throw new com.tencent.liteav.network.a.a(str, "the dns server cant support recursion ");
            }
            i = dataInputStream.readUnsignedShort();
            int readUnsignedShort2 = dataInputStream.readUnsignedShort();
            dataInputStream.readUnsignedShort();
            dataInputStream.readUnsignedShort();
            a(dataInputStream, bArr, i);
            return b(dataInputStream, bArr, readUnsignedShort2);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("the answer id ");
        stringBuilder.append(readUnsignedShort);
        stringBuilder.append(" is not match ");
        stringBuilder.append(i);
        throw new com.tencent.liteav.network.a.a(str, stringBuilder.toString());
    }

    private static String a(DataInputStream dataInputStream, byte[] bArr) throws IOException {
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        if ((readUnsignedByte & 192) == 192) {
            readUnsignedByte = ((readUnsignedByte & 63) << 8) + dataInputStream.readUnsignedByte();
            HashSet hashSet = new HashSet();
            hashSet.add(Integer.valueOf(readUnsignedByte));
            return a(bArr, readUnsignedByte, hashSet);
        } else if (readUnsignedByte == 0) {
            return "";
        } else {
            byte[] bArr2 = new byte[readUnsignedByte];
            dataInputStream.readFully(bArr2);
            String toUnicode = IDN.toUnicode(new String(bArr2));
            String a = a(dataInputStream, bArr);
            if (a.length() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(toUnicode);
                stringBuilder.append(".");
                stringBuilder.append(a);
                toUnicode = stringBuilder.toString();
            }
            return toUnicode;
        }
    }

    private static String a(byte[] bArr, int i, HashSet<Integer> hashSet) throws IOException {
        int i2 = bArr[i] & 255;
        if ((i2 & 192) == 192) {
            i2 = ((i2 & 63) << 8) + (bArr[i + 1] & 255);
            if (hashSet.contains(Integer.valueOf(i2))) {
                throw new com.tencent.liteav.network.a.a("", "Cyclic offsets detected.");
            }
            hashSet.add(Integer.valueOf(i2));
            return a(bArr, i2, (HashSet) hashSet);
        } else if (i2 == 0) {
            return "";
        } else {
            i++;
            String str = new String(bArr, i, i2);
            String a = a(bArr, i + i2, (HashSet) hashSet);
            if (a.length() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(".");
                stringBuilder.append(a);
                str = stringBuilder.toString();
            }
            return str;
        }
    }

    private static void a(DataInputStream dataInputStream, byte[] bArr, int i) throws IOException {
        while (true) {
            int i2 = i - 1;
            if (i > 0) {
                a(dataInputStream, bArr);
                dataInputStream.readUnsignedShort();
                dataInputStream.readUnsignedShort();
                i = i2;
            } else {
                return;
            }
        }
    }

    private static e[] b(DataInputStream dataInputStream, byte[] bArr, int i) throws IOException {
        e[] eVarArr = new e[i];
        int i2 = 0;
        while (true) {
            int i3 = i - 1;
            if (i <= 0) {
                return eVarArr;
            }
            i = i2 + 1;
            eVarArr[i2] = b(dataInputStream, bArr);
            i2 = i;
            i = i3;
        }
    }

    private static e b(DataInputStream dataInputStream, byte[] bArr) throws IOException {
        String hostAddress;
        a(dataInputStream, bArr);
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        dataInputStream.readUnsignedShort();
        long readUnsignedShort2 = (((long) dataInputStream.readUnsignedShort()) << 16) + ((long) dataInputStream.readUnsignedShort());
        int readUnsignedShort3 = dataInputStream.readUnsignedShort();
        if (readUnsignedShort == 1) {
            bArr = new byte[4];
            dataInputStream.readFully(bArr);
            hostAddress = InetAddress.getByAddress(bArr).getHostAddress();
        } else if (readUnsignedShort != 5) {
            for (int i = 0; i < readUnsignedShort3; i++) {
                dataInputStream.readByte();
            }
            hostAddress = null;
        } else {
            hostAddress = a(dataInputStream, bArr);
        }
        if (hostAddress != null) {
            return new e(hostAddress, readUnsignedShort, (int) readUnsignedShort2, System.currentTimeMillis() / 1000);
        }
        throw new UnknownHostException("no record");
    }
}
