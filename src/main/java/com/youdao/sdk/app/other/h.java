package com.youdao.sdk.app.other;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import defpackage.sy;

public class h {
    private static volatile h a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private final String h = Build.MANUFACTURER;
    private final String i = Build.MODEL;
    private final String j = Build.PRODUCT;
    private final String k = "1.7.3";
    private final String l = "v1";
    private final String m = d(this.p);
    private final String n = VERSION.SDK;
    private final String o = VERSION.RELEASE;
    private final Context p;
    private final ConnectivityManager q = ((ConnectivityManager) this.p.getSystemService("connectivity"));
    private final String r = c(this.p);

    public enum a {
        UNKNOWN(0),
        ETHERNET(1),
        WIFI(2),
        MOBILE(3);
        
        private final int mId;

        private a(int i) {
            this.mId = i;
        }

        public String toString() {
            return Integer.toString(this.mId);
        }

        private static a a(int i) {
            if (i == 9) {
                return ETHERNET;
            }
            switch (i) {
                case 0:
                case 2:
                case 3:
                case 4:
                case 5:
                    return MOBILE;
                case 1:
                    return WIFI;
                default:
                    return UNKNOWN;
            }
        }
    }

    public static h a(Context context) {
        h hVar = a;
        if (hVar == null) {
            synchronized (h.class) {
                hVar = a;
                if (hVar == null) {
                    hVar = new h(context);
                    a = hVar;
                }
            }
        }
        return hVar;
    }

    private h(Context context) {
        this.p = context.getApplicationContext();
        TelephonyManager telephonyManager = (TelephonyManager) this.p.getSystemService("phone");
        this.b = telephonyManager.getNetworkOperator();
        if (telephonyManager.getPhoneType() == 2 && telephonyManager.getSimState() == 5) {
            this.b = telephonyManager.getSimOperator();
        }
        this.c = telephonyManager.getNetworkCountryIso();
        try {
            this.d = telephonyManager.getNetworkOperatorName();
        } catch (SecurityException unused) {
            this.d = null;
        }
        this.e = e(this.p);
        this.f = Secure.getString(context.getContentResolver(), "android_id");
        this.g = b(this.p);
    }

    private static String b(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(displayMetrics.widthPixels);
        stringBuilder.append("x");
        stringBuilder.append(displayMetrics.heightPixels);
        return stringBuilder.toString();
    }

    private static String c(Context context) {
        try {
            return context.getPackageName();
        } catch (Exception unused) {
            sy.a("Failed to retrieve PackageInfo#versionName.");
            return null;
        }
    }

    private static String d(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            sy.a("Failed to retrieve PackageInfo#versionName.");
            return null;
        }
    }

    private static String e(Context context) {
        String string = Secure.getString(context.getContentResolver(), "android_id");
        if (string == null) {
            string = "";
        } else {
            string = q.a(string);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("sha:");
        stringBuilder.append(string);
        return stringBuilder.toString();
    }

    public String a() {
        int i = this.p.getResources().getConfiguration().orientation;
        String str = "u";
        if (i == 1) {
            return "p";
        }
        if (i == 2) {
            return "l";
        }
        return i == 3 ? "s" : str;
    }

    public a b() {
        int i = -1;
        if (this.p.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0) {
            NetworkInfo activeNetworkInfo = this.q.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                i = activeNetworkInfo.getType();
            }
        }
        return a.a(i);
    }

    public String c() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.p.getSystemService("phone");
            if (telephonyManager != null) {
                return telephonyManager.getDeviceId();
            }
        } catch (SecurityException unused) {
        }
        return "";
    }

    public int d() {
        if (this.p.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return -1;
        }
        NetworkInfo activeNetworkInfo = this.q.getActiveNetworkInfo();
        if (a.a(activeNetworkInfo != null ? activeNetworkInfo.getType() : -1) == a.MOBILE) {
            return activeNetworkInfo.getSubtype();
        }
        return -1;
    }

    public float e() {
        return this.p.getResources().getDisplayMetrics().density;
    }

    public String f() {
        return this.b;
    }

    public String g() {
        return this.c;
    }

    public String h() {
        return this.d;
    }

    public String i() {
        return this.e;
    }

    public String j() {
        return this.f;
    }

    public String k() {
        return this.g;
    }

    public String l() {
        return this.h;
    }

    public String m() {
        return this.i;
    }

    public String n() {
        return this.j;
    }

    public String o() {
        return this.k;
    }

    public String p() {
        return this.l;
    }

    public String q() {
        return this.m;
    }

    public String r() {
        return this.r;
    }
}
