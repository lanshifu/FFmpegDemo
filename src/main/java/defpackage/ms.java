package defpackage;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;

/* compiled from: RomUtils */
/* renamed from: ms */
public class ms {
    public static int a() {
        if (ms.d()) {
            return 1;
        }
        if (ms.c()) {
            return 2;
        }
        return ms.e() ? 3 : 4;
    }

    private static boolean c() {
        String str = Build.DISPLAY;
        if (!TextUtils.isEmpty(str) && str.contains("Flyme")) {
            for (String matches : str.split(" ")) {
                if (matches.matches("^[4-9]\\.(\\d+\\.)+\\S*")) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean d() {
        String a = ms.a("ro.miui.ui.version.code");
        if (!TextUtils.isEmpty(a)) {
            try {
                if (Integer.parseInt(a) >= 4) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static int b() {
        String a = ms.a("ro.miui.ui.version.code");
        if (!TextUtils.isEmpty(a)) {
            try {
                return Integer.parseInt(a);
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    private static boolean e() {
        return VERSION.SDK_INT >= 21;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0042 A:{SYNTHETIC, Splitter:B:21:0x0042} */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003b A:{SYNTHETIC, Splitter:B:13:0x003b} */
    public static java.lang.String a(java.lang.String r4) {
        /*
        r0 = 0;
        r1 = java.lang.Runtime.getRuntime();	 Catch:{ IOException -> 0x003f, all -> 0x0038 }
        r2 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x003f, all -> 0x0038 }
        r2.<init>();	 Catch:{ IOException -> 0x003f, all -> 0x0038 }
        r3 = "getprop ";
        r2.append(r3);	 Catch:{ IOException -> 0x003f, all -> 0x0038 }
        r2.append(r4);	 Catch:{ IOException -> 0x003f, all -> 0x0038 }
        r4 = r2.toString();	 Catch:{ IOException -> 0x003f, all -> 0x0038 }
        r4 = r1.exec(r4);	 Catch:{ IOException -> 0x003f, all -> 0x0038 }
        r1 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x003f, all -> 0x0038 }
        r2 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x003f, all -> 0x0038 }
        r4 = r4.getInputStream();	 Catch:{ IOException -> 0x003f, all -> 0x0038 }
        r2.<init>(r4);	 Catch:{ IOException -> 0x003f, all -> 0x0038 }
        r4 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r1.<init>(r2, r4);	 Catch:{ IOException -> 0x003f, all -> 0x0038 }
        r4 = r1.readLine();	 Catch:{ IOException -> 0x0040, all -> 0x0035 }
        r1.close();	 Catch:{ IOException -> 0x0040, all -> 0x0035 }
        r1.close();	 Catch:{ IOException -> 0x0034 }
    L_0x0034:
        return r4;
    L_0x0035:
        r4 = move-exception;
        r0 = r1;
        goto L_0x0039;
    L_0x0038:
        r4 = move-exception;
    L_0x0039:
        if (r0 == 0) goto L_0x003e;
    L_0x003b:
        r0.close();	 Catch:{ IOException -> 0x003e }
    L_0x003e:
        throw r4;
    L_0x003f:
        r1 = r0;
    L_0x0040:
        if (r1 == 0) goto L_0x0045;
    L_0x0042:
        r1.close();	 Catch:{ IOException -> 0x0045 }
    L_0x0045:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ms.a(java.lang.String):java.lang.String");
    }
}
