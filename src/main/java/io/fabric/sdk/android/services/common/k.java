package io.fabric.sdk.android.services.common;

import android.content.Context;
import android.content.SharedPreferences;

/* compiled from: DataCollectionArbiter */
public class k {
    private static k a;
    private static Object b = new Object();
    private final SharedPreferences c;
    private volatile boolean d;
    private volatile boolean e;
    private final n f;
    private boolean g = false;

    public static k a(Context context) {
        k kVar;
        synchronized (b) {
            if (a == null) {
                a = new k(context);
            }
            kVar = a;
        }
        return kVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x006c  */
    private k(android.content.Context r7) {
        /*
        r6 = this;
        r6.<init>();
        r0 = 0;
        r6.g = r0;
        if (r7 == 0) goto L_0x0070;
    L_0x0008:
        r1 = "com.google.firebase.crashlytics.prefs";
        r1 = r7.getSharedPreferences(r1, r0);
        r6.c = r1;
        r1 = io.fabric.sdk.android.services.common.o.a(r7);
        r6.f = r1;
        r1 = r6.c;
        r2 = "firebase_crashlytics_collection_enabled";
        r1 = r1.contains(r2);
        r2 = 1;
        if (r1 == 0) goto L_0x002b;
    L_0x0021:
        r1 = r6.c;
        r3 = "firebase_crashlytics_collection_enabled";
        r1 = r1.getBoolean(r3, r2);
    L_0x0029:
        r3 = 1;
        goto L_0x0062;
    L_0x002b:
        r1 = r7.getPackageManager();	 Catch:{ NameNotFoundException -> 0x0054 }
        if (r1 == 0) goto L_0x0060;
    L_0x0031:
        r3 = r7.getPackageName();	 Catch:{ NameNotFoundException -> 0x0054 }
        r4 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r1 = r1.getApplicationInfo(r3, r4);	 Catch:{ NameNotFoundException -> 0x0054 }
        if (r1 == 0) goto L_0x0060;
    L_0x003d:
        r3 = r1.metaData;	 Catch:{ NameNotFoundException -> 0x0054 }
        if (r3 == 0) goto L_0x0060;
    L_0x0041:
        r3 = r1.metaData;	 Catch:{ NameNotFoundException -> 0x0054 }
        r4 = "firebase_crashlytics_collection_enabled";
        r3 = r3.containsKey(r4);	 Catch:{ NameNotFoundException -> 0x0054 }
        if (r3 == 0) goto L_0x0060;
    L_0x004b:
        r1 = r1.metaData;	 Catch:{ NameNotFoundException -> 0x0054 }
        r3 = "firebase_crashlytics_collection_enabled";
        r1 = r1.getBoolean(r3);	 Catch:{ NameNotFoundException -> 0x0054 }
        goto L_0x0029;
    L_0x0054:
        r1 = move-exception;
        r3 = io.fabric.sdk.android.c.g();
        r4 = "Fabric";
        r5 = "Unable to get PackageManager. Falling through";
        r3.a(r4, r5, r1);
    L_0x0060:
        r1 = 1;
        r3 = 0;
    L_0x0062:
        r6.e = r1;
        r6.d = r3;
        r7 = io.fabric.sdk.android.services.common.CommonUtils.n(r7);
        if (r7 == 0) goto L_0x006d;
    L_0x006c:
        r0 = 1;
    L_0x006d:
        r6.g = r0;
        return;
    L_0x0070:
        r7 = new java.lang.RuntimeException;
        r0 = "null context";
        r7.<init>(r0);
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.common.k.<init>(android.content.Context):void");
    }

    public boolean a() {
        if (this.g && this.d) {
            return this.e;
        }
        return this.f != null ? this.f.a() : true;
    }

    public boolean b() {
        return this.e;
    }
}
