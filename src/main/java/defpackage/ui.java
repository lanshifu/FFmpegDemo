package defpackage;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: ImageKit */
/* renamed from: ui */
public class ui {
    public static boolean a(InputStream inputStream) {
        if (inputStream.markSupported()) {
            inputStream.mark(10);
            byte[] bArr = new byte[4];
            try {
                inputStream.read(bArr, 0, bArr.length);
                inputStream.reset();
                return "47494638".equals(ui.b(bArr));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean a(byte[] bArr) {
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, 0, bArr2, 0, 4);
        return "47494638".equals(ui.b(bArr2));
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0031 A:{SYNTHETIC, Splitter:B:24:0x0031} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0039 A:{Catch:{ IOException -> 0x0035 }} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0045 A:{SYNTHETIC, Splitter:B:35:0x0045} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x004d A:{Catch:{ IOException -> 0x0049 }} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0031 A:{SYNTHETIC, Splitter:B:24:0x0031} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0039 A:{Catch:{ IOException -> 0x0035 }} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0045 A:{SYNTHETIC, Splitter:B:35:0x0045} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x004d A:{Catch:{ IOException -> 0x0049 }} */
    public static boolean a(java.lang.String r3) {
        /*
        r0 = 0;
        r1 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x002a, all -> 0x0027 }
        r1.<init>(r3);	 Catch:{ FileNotFoundException -> 0x002a, all -> 0x0027 }
        r3 = new java.io.BufferedInputStream;	 Catch:{ FileNotFoundException -> 0x0025 }
        r3.<init>(r1);	 Catch:{ FileNotFoundException -> 0x0025 }
        r0 = defpackage.ui.a(r3);	 Catch:{ FileNotFoundException -> 0x0020, all -> 0x001b }
        r3.close();	 Catch:{ IOException -> 0x0016 }
        r1.close();	 Catch:{ IOException -> 0x0016 }
        goto L_0x001a;
    L_0x0016:
        r3 = move-exception;
        r3.printStackTrace();
    L_0x001a:
        return r0;
    L_0x001b:
        r0 = move-exception;
        r2 = r0;
        r0 = r3;
        r3 = r2;
        goto L_0x0043;
    L_0x0020:
        r0 = move-exception;
        r2 = r0;
        r0 = r3;
        r3 = r2;
        goto L_0x002c;
    L_0x0025:
        r3 = move-exception;
        goto L_0x002c;
    L_0x0027:
        r3 = move-exception;
        r1 = r0;
        goto L_0x0043;
    L_0x002a:
        r3 = move-exception;
        r1 = r0;
    L_0x002c:
        r3.printStackTrace();	 Catch:{ all -> 0x0042 }
        if (r0 == 0) goto L_0x0037;
    L_0x0031:
        r0.close();	 Catch:{ IOException -> 0x0035 }
        goto L_0x0037;
    L_0x0035:
        r3 = move-exception;
        goto L_0x003d;
    L_0x0037:
        if (r1 == 0) goto L_0x0040;
    L_0x0039:
        r1.close();	 Catch:{ IOException -> 0x0035 }
        goto L_0x0040;
    L_0x003d:
        r3.printStackTrace();
    L_0x0040:
        r3 = 0;
        return r3;
    L_0x0042:
        r3 = move-exception;
    L_0x0043:
        if (r0 == 0) goto L_0x004b;
    L_0x0045:
        r0.close();	 Catch:{ IOException -> 0x0049 }
        goto L_0x004b;
    L_0x0049:
        r0 = move-exception;
        goto L_0x0051;
    L_0x004b:
        if (r1 == 0) goto L_0x0054;
    L_0x004d:
        r1.close();	 Catch:{ IOException -> 0x0049 }
        goto L_0x0054;
    L_0x0051:
        r0.printStackTrace();
    L_0x0054:
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ui.a(java.lang.String):boolean");
    }

    private static String b(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }
}
