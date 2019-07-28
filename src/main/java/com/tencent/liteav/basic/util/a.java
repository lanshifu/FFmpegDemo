package com.tencent.liteav.basic.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.tencent.liteav.basic.log.TXCLog;
import java.io.File;
import java.io.IOException;

/* compiled from: FileUtil */
public class a {
    public static boolean a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        boolean z = false;
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
            z = true;
        }
        return z;
    }

    @TargetApi(18)
    public static long a(StatFs statFs) {
        if (VERSION.SDK_INT >= 18) {
            return statFs.getAvailableBytes();
        }
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    public static boolean a(long j) {
        return a(new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath())) > j;
    }

    public static boolean a(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            z = true;
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0054 A:{SYNTHETIC, Splitter:B:26:0x0054} */
    public static java.lang.String b(java.lang.String r4) {
        /*
        r0 = new java.io.File;
        r0.<init>(r4);
        r4 = new java.lang.StringBuilder;
        r1 = "";
        r4.<init>(r1);
        r1 = r0.isFile();
        r2 = 0;
        if (r1 != 0) goto L_0x0014;
    L_0x0013:
        return r2;
    L_0x0014:
        r1 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x0049 }
        r3 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x0049 }
        r3.<init>(r0);	 Catch:{ IOException -> 0x0049 }
        r1.<init>(r3);	 Catch:{ IOException -> 0x0049 }
        r0 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x0049 }
        r0.<init>(r1);	 Catch:{ IOException -> 0x0049 }
    L_0x0023:
        r1 = r0.readLine();	 Catch:{ IOException -> 0x0044, all -> 0x0041 }
        if (r1 == 0) goto L_0x002d;
    L_0x0029:
        r4.append(r1);	 Catch:{ IOException -> 0x0044, all -> 0x0041 }
        goto L_0x0023;
    L_0x002d:
        r0.close();	 Catch:{ IOException -> 0x0044, all -> 0x0041 }
        r4 = r4.toString();	 Catch:{ IOException -> 0x0044, all -> 0x0041 }
        r0.close();	 Catch:{ IOException -> 0x0038 }
        return r4;
    L_0x0038:
        r4 = move-exception;
        r0 = new java.lang.RuntimeException;
        r1 = "IOException occurred. ";
        r0.<init>(r1, r4);
        throw r0;
    L_0x0041:
        r4 = move-exception;
        r2 = r0;
        goto L_0x0052;
    L_0x0044:
        r4 = move-exception;
        r2 = r0;
        goto L_0x004a;
    L_0x0047:
        r4 = move-exception;
        goto L_0x0052;
    L_0x0049:
        r4 = move-exception;
    L_0x004a:
        r0 = new java.lang.RuntimeException;	 Catch:{ all -> 0x0047 }
        r1 = "IOException occurred. ";
        r0.<init>(r1, r4);	 Catch:{ all -> 0x0047 }
        throw r0;	 Catch:{ all -> 0x0047 }
    L_0x0052:
        if (r2 == 0) goto L_0x0061;
    L_0x0054:
        r2.close();	 Catch:{ IOException -> 0x0058 }
        goto L_0x0061;
    L_0x0058:
        r4 = move-exception;
        r0 = new java.lang.RuntimeException;
        r1 = "IOException occurred. ";
        r0.<init>(r1, r4);
        throw r0;
    L_0x0061:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.basic.util.a.b(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x002c A:{SYNTHETIC, Splitter:B:21:0x002c} */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0034 A:{Catch:{ Exception -> 0x0030 }} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002c A:{SYNTHETIC, Splitter:B:21:0x002c} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0034 A:{Catch:{ Exception -> 0x0030 }} */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x003f A:{SYNTHETIC, Splitter:B:30:0x003f} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0047 A:{Catch:{ Exception -> 0x0043 }} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x003f A:{SYNTHETIC, Splitter:B:30:0x003f} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0047 A:{Catch:{ Exception -> 0x0043 }} */
    public static void a(java.lang.String r2, byte[] r3) {
        /*
        r0 = 0;
        r1 = new java.io.File;	 Catch:{ Exception -> 0x0025, all -> 0x0022 }
        r1.<init>(r2);	 Catch:{ Exception -> 0x0025, all -> 0x0022 }
        r2 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0025, all -> 0x0022 }
        r2.<init>(r1);	 Catch:{ Exception -> 0x0025, all -> 0x0022 }
        r1 = new java.io.BufferedOutputStream;	 Catch:{ Exception -> 0x0020 }
        r1.<init>(r2);	 Catch:{ Exception -> 0x0020 }
        r1.write(r3);	 Catch:{ Exception -> 0x001d, all -> 0x001a }
        r1.close();	 Catch:{ Exception -> 0x0030 }
        r2.close();	 Catch:{ Exception -> 0x0030 }
        goto L_0x003b;
    L_0x001a:
        r3 = move-exception;
        r0 = r1;
        goto L_0x003d;
    L_0x001d:
        r3 = move-exception;
        r0 = r1;
        goto L_0x0027;
    L_0x0020:
        r3 = move-exception;
        goto L_0x0027;
    L_0x0022:
        r3 = move-exception;
        r2 = r0;
        goto L_0x003d;
    L_0x0025:
        r3 = move-exception;
        r2 = r0;
    L_0x0027:
        r3.printStackTrace();	 Catch:{ all -> 0x003c }
        if (r0 == 0) goto L_0x0032;
    L_0x002c:
        r0.close();	 Catch:{ Exception -> 0x0030 }
        goto L_0x0032;
    L_0x0030:
        r2 = move-exception;
        goto L_0x0038;
    L_0x0032:
        if (r2 == 0) goto L_0x003b;
    L_0x0034:
        r2.close();	 Catch:{ Exception -> 0x0030 }
        goto L_0x003b;
    L_0x0038:
        r2.printStackTrace();
    L_0x003b:
        return;
    L_0x003c:
        r3 = move-exception;
    L_0x003d:
        if (r0 == 0) goto L_0x0045;
    L_0x003f:
        r0.close();	 Catch:{ Exception -> 0x0043 }
        goto L_0x0045;
    L_0x0043:
        r2 = move-exception;
        goto L_0x004b;
    L_0x0045:
        if (r2 == 0) goto L_0x004e;
    L_0x0047:
        r2.close();	 Catch:{ Exception -> 0x0043 }
        goto L_0x004e;
    L_0x004b:
        r2.printStackTrace();
    L_0x004e:
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.basic.util.a.a(java.lang.String, byte[]):void");
    }

    public static boolean a(Context context, String str) {
        StringBuilder stringBuilder;
        try {
            String[] list = context.getAssets().list("");
            for (String equals : list) {
                if (equals.equals(str.trim())) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(" exist");
                    TXCLog.i("isAssetFileExists", stringBuilder.toString());
                    return true;
                }
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" not exist");
            TXCLog.i("isAssetFileExists", stringBuilder.toString());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" not exist");
            TXCLog.i("isAssetFileExists", stringBuilder.toString());
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0045 A:{SYNTHETIC, Splitter:B:30:0x0045} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0052 A:{SYNTHETIC, Splitter:B:37:0x0052} */
    public static java.lang.String b(android.content.Context r2, java.lang.String r3) {
        /*
        r0 = 0;
        r2 = r2.getAssets();	 Catch:{ IOException -> 0x003f }
        r2 = r2.open(r3);	 Catch:{ IOException -> 0x003f }
        r3 = r2.available();	 Catch:{ IOException -> 0x0039, all -> 0x0037 }
        r3 = new byte[r3];	 Catch:{ IOException -> 0x0039, all -> 0x0037 }
        r0 = r2.read(r3);	 Catch:{ IOException -> 0x0039, all -> 0x0037 }
        if (r0 > 0) goto L_0x0022;
    L_0x0015:
        r3 = "";
        if (r2 == 0) goto L_0x0021;
    L_0x0019:
        r2.close();	 Catch:{ IOException -> 0x001d }
        goto L_0x0021;
    L_0x001d:
        r2 = move-exception;
        r2.printStackTrace();
    L_0x0021:
        return r3;
    L_0x0022:
        r2.close();	 Catch:{ IOException -> 0x0039, all -> 0x0037 }
        r0 = new java.lang.String;	 Catch:{ IOException -> 0x0039, all -> 0x0037 }
        r1 = "utf-8";
        r0.<init>(r3, r1);	 Catch:{ IOException -> 0x0039, all -> 0x0037 }
        if (r2 == 0) goto L_0x0036;
    L_0x002e:
        r2.close();	 Catch:{ IOException -> 0x0032 }
        goto L_0x0036;
    L_0x0032:
        r2 = move-exception;
        r2.printStackTrace();
    L_0x0036:
        return r0;
    L_0x0037:
        r3 = move-exception;
        goto L_0x0050;
    L_0x0039:
        r3 = move-exception;
        r0 = r2;
        goto L_0x0040;
    L_0x003c:
        r3 = move-exception;
        r2 = r0;
        goto L_0x0050;
    L_0x003f:
        r3 = move-exception;
    L_0x0040:
        r3.printStackTrace();	 Catch:{ all -> 0x003c }
        if (r0 == 0) goto L_0x004d;
    L_0x0045:
        r0.close();	 Catch:{ IOException -> 0x0049 }
        goto L_0x004d;
    L_0x0049:
        r2 = move-exception;
        r2.printStackTrace();
    L_0x004d:
        r2 = "";
        return r2;
    L_0x0050:
        if (r2 == 0) goto L_0x005a;
    L_0x0052:
        r2.close();	 Catch:{ IOException -> 0x0056 }
        goto L_0x005a;
    L_0x0056:
        r2 = move-exception;
        r2.printStackTrace();
    L_0x005a:
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.basic.util.a.b(android.content.Context, java.lang.String):java.lang.String");
    }
}
