package com.tomatolive.library.utils;

import android.text.TextUtils;
import com.blankj.utilcode.util.d;
import com.blankj.utilcode.util.p;
import com.tomatolive.library.http.utils.EncryptUtil;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/* compiled from: FileUtils */
public class g {
    private static final String a = System.getProperty("line.separator");
    private static final Charset b = Charset.forName("UTF-8");
    private static final char[] c = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final int d = ("kgmp3hash".length() + 32);

    public static File a(String str) {
        return j(str) ? null : new File(str);
    }

    public static boolean b(String str) {
        return a(a(str));
    }

    public static boolean a(File file) {
        return file != null && file.exists() && file.isFile();
    }

    public static boolean c(String str) {
        return b(a(str));
    }

    public static boolean b(File file) {
        return file != null && (!file.exists() || (file.isFile() && file.delete()));
    }

    private static boolean j(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean d(String str) {
        boolean isEmpty = TextUtils.isEmpty(str);
        File file = new File(str);
        boolean exists = file.exists();
        exists = file.isDirectory();
        boolean z = false;
        for (String str2 : file.list()) {
            File file2;
            StringBuilder stringBuilder;
            if (str.endsWith(File.separator)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(str2);
                file2 = new File(stringBuilder.toString());
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(File.separator);
                stringBuilder.append(str2);
                file2 = new File(stringBuilder.toString());
            }
            if (file2.isFile()) {
                file2.delete();
            }
            if (file2.isDirectory()) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append("/");
                stringBuilder2.append(str2);
                d(stringBuilder2.toString());
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append("/");
                stringBuilder2.append(str2);
                e(stringBuilder2.toString());
                z = true;
            }
        }
        return z;
    }

    public static boolean e(String str) {
        try {
            d(str);
            return new File(str).delete();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean f(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (new File(str).exists()) {
                z = true;
            }
            return z;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean c(File file) {
        return file != null && file.exists();
    }

    public static String g(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (!new File(str).exists()) {
            return "";
        }
        try {
            return EncryptUtil.DESDecrypt("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ4BUmnAVmr+0JE7zL195q1QIg1NAW/zln54MjEM6qv45OpTB/jp2zRdw9i4kNPk/IjrOPYPh4dv4+YgYWQkVs0vzVnzuse9rbfoEKDFbze9pyo3EfLnrSUhe3dJp9BWwnq105/zFPkgbx7Qv5T25CMbLTfD0cFHvOAN/HEN/l0HAgMBAAECgYBnFse1dacsgfqEd3a6v5UsyNaexPpGF7C97SAaPqox798zP185OSMrBG5OTQU5KvLVRltQt4seg5M2xzOjyc3TY4/XGZIQtRA35Sovx/s+NvSy6VD+L5SEJ4r8/SXR8mXKJfxKVg8jNOcWiRR4VuQmIozzPEFqZH9qIVJcVjWt0QJBAONHlIRN4OVFzsC3hhc1yv2vYZzzjXcTw8np9wlx1Qjup3rzVhbsAd1KXqpFCTZYlqSfrudDSwQCEXaFyu32uukCQQCx+LyJOoNbnMbPSkZOoQkQfPjgHqy0yAhqiNKT7ciIVyREFmpbB1Q9ptQWDmMwEBb2ksTBNCrgUOX/IE5KsYJvAkEA3Wz6Y5+gEJ7fHGBwUiKFXnxEZG3gD/gAkrHPjLMLMwWXw7BY2kIaWua+rbJOlFTghwhPlV25MvF04/zbRNVRKQJAE5Lv6Yft+p17oEDjCrLbdFzKYpv9EsUNZ+o0fuCgNZ6f9n0gpXJg6Yb3vJVIg3jBjc0Gptk9/f3nze+XrM9pMQJAQ2EroFmPUTuv86Ghwdjnh0z4DnJkr60a6ccoNiqNZpmTAND6og99djzJqGWFHGDGS/JLfbiVdafzj6QZ5TQBzQ==", d.a(str, "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0046 A:{SYNTHETIC, Splitter:B:24:0x0046} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0051 A:{SYNTHETIC, Splitter:B:30:0x0051} */
    public static boolean a(java.lang.String r4, java.lang.String r5) {
        /*
        r0 = 0;
        r1 = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ4BUmnAVmr+0JE7zL195q1QIg1NAW/zln54MjEM6qv45OpTB/jp2zRdw9i4kNPk/IjrOPYPh4dv4+YgYWQkVs0vzVnzuse9rbfoEKDFbze9pyo3EfLnrSUhe3dJp9BWwnq105/zFPkgbx7Qv5T25CMbLTfD0cFHvOAN/HEN/l0HAgMBAAECgYBnFse1dacsgfqEd3a6v5UsyNaexPpGF7C97SAaPqox798zP185OSMrBG5OTQU5KvLVRltQt4seg5M2xzOjyc3TY4/XGZIQtRA35Sovx/s+NvSy6VD+L5SEJ4r8/SXR8mXKJfxKVg8jNOcWiRR4VuQmIozzPEFqZH9qIVJcVjWt0QJBAONHlIRN4OVFzsC3hhc1yv2vYZzzjXcTw8np9wlx1Qjup3rzVhbsAd1KXqpFCTZYlqSfrudDSwQCEXaFyu32uukCQQCx+LyJOoNbnMbPSkZOoQkQfPjgHqy0yAhqiNKT7ciIVyREFmpbB1Q9ptQWDmMwEBb2ksTBNCrgUOX/IE5KsYJvAkEA3Wz6Y5+gEJ7fHGBwUiKFXnxEZG3gD/gAkrHPjLMLMwWXw7BY2kIaWua+rbJOlFTghwhPlV25MvF04/zbRNVRKQJAE5Lv6Yft+p17oEDjCrLbdFzKYpv9EsUNZ+o0fuCgNZ6f9n0gpXJg6Yb3vJVIg3jBjc0Gptk9/f3nze+XrM9pMQJAQ2EroFmPUTuv86Ghwdjnh0z4DnJkr60a6ccoNiqNZpmTAND6og99djzJqGWFHGDGS/JLfbiVdafzj6QZ5TQBzQ==";
        r4 = com.tomatolive.library.http.utils.EncryptUtil.DESEncrypt(r1, r4);	 Catch:{ Exception -> 0x003f }
        r1 = new java.io.File;	 Catch:{ Exception -> 0x003f }
        r1.<init>(r5);	 Catch:{ Exception -> 0x003f }
        r1 = r1.getParentFile();	 Catch:{ Exception -> 0x003f }
        r2 = r1.isDirectory();	 Catch:{ Exception -> 0x003f }
        if (r2 != 0) goto L_0x0019;
    L_0x0016:
        r1.mkdir();	 Catch:{ Exception -> 0x003f }
    L_0x0019:
        r1 = new java.io.BufferedWriter;	 Catch:{ Exception -> 0x003f }
        r2 = new java.io.OutputStreamWriter;	 Catch:{ Exception -> 0x003f }
        r3 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x003f }
        r3.<init>(r5);	 Catch:{ Exception -> 0x003f }
        r5 = "utf-8";
        r2.<init>(r3, r5);	 Catch:{ Exception -> 0x003f }
        r1.<init>(r2);	 Catch:{ Exception -> 0x003f }
        r1.write(r4);	 Catch:{ Exception -> 0x003a, all -> 0x0037 }
        r1.close();	 Catch:{ IOException -> 0x0031 }
        goto L_0x0035;
    L_0x0031:
        r4 = move-exception;
        r4.printStackTrace();
    L_0x0035:
        r4 = 1;
        return r4;
    L_0x0037:
        r4 = move-exception;
        r0 = r1;
        goto L_0x004f;
    L_0x003a:
        r4 = move-exception;
        r0 = r1;
        goto L_0x0040;
    L_0x003d:
        r4 = move-exception;
        goto L_0x004f;
    L_0x003f:
        r4 = move-exception;
    L_0x0040:
        r4.printStackTrace();	 Catch:{ all -> 0x003d }
        r4 = 0;
        if (r0 == 0) goto L_0x004e;
    L_0x0046:
        r0.close();	 Catch:{ IOException -> 0x004a }
        goto L_0x004e;
    L_0x004a:
        r5 = move-exception;
        r5.printStackTrace();
    L_0x004e:
        return r4;
    L_0x004f:
        if (r0 == 0) goto L_0x0059;
    L_0x0051:
        r0.close();	 Catch:{ IOException -> 0x0055 }
        goto L_0x0059;
    L_0x0055:
        r5 = move-exception;
        r5.printStackTrace();
    L_0x0059:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.g.a(java.lang.String, java.lang.String):boolean");
    }

    public static boolean b(String str, String str2) {
        boolean z = false;
        try {
            List a = p.a(str, str2);
            if (!(a == null || a.isEmpty())) {
                z = true;
            }
            return z;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static String h(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(".zip");
        return stringBuffer.toString();
    }

    public static String i(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("file:///android_asset/html/");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }
}
