package com.tomatolive.library.utils;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: StringUtils */
public class u {
    public static String a(int i) {
        String str = "abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random(System.currentTimeMillis());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append(str.charAt(random.nextInt(62)));
        }
        return stringBuilder.toString();
    }

    public static String a() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                stringBuilder.append('0');
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }

    public static boolean a(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static String a(String str, int i) {
        if (TextUtils.isEmpty(str) || str.length() <= i) {
            return str;
        }
        str = str.substring(0, i);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("...");
        return stringBuilder.toString();
    }

    public static String a(String str) {
        return str.replaceAll(" ", "").replaceAll("-", "");
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0066 A:{SYNTHETIC, Splitter:B:26:0x0066} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0075 A:{SYNTHETIC, Splitter:B:32:0x0075} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0066 A:{SYNTHETIC, Splitter:B:26:0x0066} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0056 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x000d} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:15:0x0056, code skipped:
            r9 = th;
     */
    /* JADX WARNING: Missing block: B:16:0x0058, code skipped:
            r9 = e;
     */
    /* JADX WARNING: Missing block: B:17:0x0059, code skipped:
            r4 = null;
     */
    /* JADX WARNING: Missing block: B:33:?, code skipped:
            r2.close();
     */
    /* JADX WARNING: Missing block: B:34:0x0079, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:35:0x007a, code skipped:
            r0.printStackTrace();
     */
    public static java.lang.String a(java.io.File r9) {
        /*
        r0 = 16;
        r1 = 0;
        r0 = new char[r0];	 Catch:{ Exception -> 0x005f }
        r0 = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};	 Catch:{ Exception -> 0x005f }
        r2 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x005f }
        r2.<init>(r9);	 Catch:{ Exception -> 0x005f }
        r3 = r2.getChannel();	 Catch:{ Exception -> 0x0058, all -> 0x0056 }
        r4 = java.nio.channels.FileChannel.MapMode.READ_ONLY;	 Catch:{ Exception -> 0x0058, all -> 0x0056 }
        r5 = 0;
        r7 = r9.length();	 Catch:{ Exception -> 0x0058, all -> 0x0056 }
        r9 = r3.map(r4, r5, r7);	 Catch:{ Exception -> 0x0058, all -> 0x0056 }
        r3 = "MD5";
        r3 = java.security.MessageDigest.getInstance(r3);	 Catch:{ Exception -> 0x0058, all -> 0x0056 }
        r3.update(r9);	 Catch:{ Exception -> 0x0058, all -> 0x0056 }
        r9 = r3.digest();	 Catch:{ Exception -> 0x0058, all -> 0x0056 }
        r3 = r9.length;	 Catch:{ Exception -> 0x0058, all -> 0x0056 }
        r4 = new java.lang.StringBuffer;	 Catch:{ Exception -> 0x0058, all -> 0x0056 }
        r3 = r3 * 2;
        r4.<init>(r3);	 Catch:{ Exception -> 0x0058, all -> 0x0056 }
        r1 = r9.length;	 Catch:{ Exception -> 0x0054, all -> 0x0056 }
        r3 = 0;
    L_0x0034:
        if (r3 >= r1) goto L_0x004b;
    L_0x0036:
        r5 = r9[r3];	 Catch:{ Exception -> 0x0054, all -> 0x0056 }
        r6 = r5 & 240;
        r6 = r6 >> 4;
        r6 = r0[r6];	 Catch:{ Exception -> 0x0054, all -> 0x0056 }
        r5 = r5 & 15;
        r5 = r0[r5];	 Catch:{ Exception -> 0x0054, all -> 0x0056 }
        r4.append(r6);	 Catch:{ Exception -> 0x0054, all -> 0x0056 }
        r4.append(r5);	 Catch:{ Exception -> 0x0054, all -> 0x0056 }
        r3 = r3 + 1;
        goto L_0x0034;
    L_0x004b:
        r2.close();	 Catch:{ Exception -> 0x004f }
        goto L_0x0069;
    L_0x004f:
        r9 = move-exception;
        r9.printStackTrace();
        goto L_0x0069;
    L_0x0054:
        r9 = move-exception;
        goto L_0x005a;
    L_0x0056:
        r9 = move-exception;
        goto L_0x0073;
    L_0x0058:
        r9 = move-exception;
        r4 = r1;
    L_0x005a:
        r1 = r2;
        goto L_0x0061;
    L_0x005c:
        r9 = move-exception;
        r2 = r1;
        goto L_0x0073;
    L_0x005f:
        r9 = move-exception;
        r4 = r1;
    L_0x0061:
        r9.printStackTrace();	 Catch:{ all -> 0x005c }
        if (r1 == 0) goto L_0x0069;
    L_0x0066:
        r1.close();	 Catch:{ Exception -> 0x004f }
    L_0x0069:
        if (r4 == 0) goto L_0x0070;
    L_0x006b:
        r9 = r4.toString();
        goto L_0x0072;
    L_0x0070:
        r9 = "";
    L_0x0072:
        return r9;
    L_0x0073:
        if (r2 == 0) goto L_0x007d;
    L_0x0075:
        r2.close();	 Catch:{ Exception -> 0x0079 }
        goto L_0x007d;
    L_0x0079:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x007d:
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.u.a(java.io.File):java.lang.String");
    }

    public static byte[] b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        str = str.toLowerCase();
        byte[] bArr = new byte[(str.length() / 2)];
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) ((((byte) (Character.digit(str.charAt(i), 16) & 255)) << 4) | ((byte) (Character.digit(str.charAt(i + 1), 16) & 255)));
            i += 2;
        }
        return bArr;
    }

    public static String b(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            if ((bArr[i] & 255) < 16) {
                stringBuilder.append("0");
            }
            stringBuilder.append(Integer.toHexString(bArr[i] & 255));
        }
        return stringBuilder.toString().toLowerCase();
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003a  */
    public static java.lang.String c(java.lang.String r3) throws java.io.IOException {
        /*
        r0 = android.text.TextUtils.isEmpty(r3);
        if (r0 == 0) goto L_0x0007;
    L_0x0006:
        return r3;
    L_0x0007:
        r0 = 0;
        r1 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x003e, all -> 0x0030 }
        r1.<init>();	 Catch:{ Exception -> 0x003e, all -> 0x0030 }
        r2 = new java.util.zip.GZIPOutputStream;	 Catch:{ Exception -> 0x002e, all -> 0x002b }
        r2.<init>(r1);	 Catch:{ Exception -> 0x002e, all -> 0x002b }
        r0 = r3.getBytes();	 Catch:{ Exception -> 0x0040, all -> 0x0029 }
        r2.write(r0);	 Catch:{ Exception -> 0x0040, all -> 0x0029 }
        r2.close();	 Catch:{ Exception -> 0x0040, all -> 0x0029 }
        r0 = "ISO-8859-1";
        r0 = r1.toString(r0);	 Catch:{ Exception -> 0x0040, all -> 0x0029 }
        r2.close();
        r1.close();
        return r0;
    L_0x0029:
        r3 = move-exception;
        goto L_0x0033;
    L_0x002b:
        r3 = move-exception;
        r2 = r0;
        goto L_0x0033;
    L_0x002e:
        r2 = r0;
        goto L_0x0040;
    L_0x0030:
        r3 = move-exception;
        r1 = r0;
        r2 = r1;
    L_0x0033:
        if (r2 == 0) goto L_0x0038;
    L_0x0035:
        r2.close();
    L_0x0038:
        if (r1 == 0) goto L_0x003d;
    L_0x003a:
        r1.close();
    L_0x003d:
        throw r3;
    L_0x003e:
        r1 = r0;
        r2 = r1;
    L_0x0040:
        if (r2 == 0) goto L_0x0045;
    L_0x0042:
        r2.close();
    L_0x0045:
        if (r1 == 0) goto L_0x004a;
    L_0x0047:
        r1.close();
    L_0x004a:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.u.c(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0055  */
    public static java.lang.String d(java.lang.String r6) throws java.io.IOException {
        /*
        r0 = android.text.TextUtils.isEmpty(r6);
        if (r0 == 0) goto L_0x0007;
    L_0x0006:
        return r6;
    L_0x0007:
        r0 = 0;
        r1 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x0059, all -> 0x0046 }
        r1.<init>();	 Catch:{ Exception -> 0x0059, all -> 0x0046 }
        r2 = new java.io.ByteArrayInputStream;	 Catch:{ Exception -> 0x0044, all -> 0x0041 }
        r3 = "ISO-8859-1";
        r3 = r6.getBytes(r3);	 Catch:{ Exception -> 0x0044, all -> 0x0041 }
        r2.<init>(r3);	 Catch:{ Exception -> 0x0044, all -> 0x0041 }
        r3 = new java.util.zip.GZIPInputStream;	 Catch:{ Exception -> 0x005b, all -> 0x003f }
        r3.<init>(r2);	 Catch:{ Exception -> 0x005b, all -> 0x003f }
        r0 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        r0 = new byte[r0];	 Catch:{ Exception -> 0x003d, all -> 0x003a }
    L_0x0021:
        r4 = r3.read(r0);	 Catch:{ Exception -> 0x003d, all -> 0x003a }
        if (r4 < 0) goto L_0x002c;
    L_0x0027:
        r5 = 0;
        r1.write(r0, r5, r4);	 Catch:{ Exception -> 0x003d, all -> 0x003a }
        goto L_0x0021;
    L_0x002c:
        r0 = r1.toString();	 Catch:{ Exception -> 0x003d, all -> 0x003a }
        r1.close();
        r3.close();
        r2.close();
        return r0;
    L_0x003a:
        r6 = move-exception;
        r0 = r3;
        goto L_0x0049;
    L_0x003d:
        r0 = r3;
        goto L_0x005b;
    L_0x003f:
        r6 = move-exception;
        goto L_0x0049;
    L_0x0041:
        r6 = move-exception;
        r2 = r0;
        goto L_0x0049;
    L_0x0044:
        r2 = r0;
        goto L_0x005b;
    L_0x0046:
        r6 = move-exception;
        r1 = r0;
        r2 = r1;
    L_0x0049:
        if (r1 == 0) goto L_0x004e;
    L_0x004b:
        r1.close();
    L_0x004e:
        if (r0 == 0) goto L_0x0053;
    L_0x0050:
        r0.close();
    L_0x0053:
        if (r2 == 0) goto L_0x0058;
    L_0x0055:
        r2.close();
    L_0x0058:
        throw r6;
    L_0x0059:
        r1 = r0;
        r2 = r1;
    L_0x005b:
        if (r1 == 0) goto L_0x0060;
    L_0x005d:
        r1.close();
    L_0x0060:
        if (r0 == 0) goto L_0x0065;
    L_0x0062:
        r0.close();
    L_0x0065:
        if (r2 == 0) goto L_0x006a;
    L_0x0067:
        r2.close();
    L_0x006a:
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.u.d(java.lang.String):java.lang.String");
    }

    public static SpannableString a(Context context, String str, String str2, @ColorRes int i) {
        SpannableString spannableString = new SpannableString(str);
        Matcher matcher = Pattern.compile(str2).matcher(str);
        while (matcher.find()) {
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, i)), matcher.start(), matcher.end(), 33);
        }
        return spannableString;
    }
}
