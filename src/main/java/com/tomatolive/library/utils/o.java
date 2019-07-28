package com.tomatolive.library.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.tomatolive.library.a;

/* compiled from: NetUtils */
public class o {
    public static boolean a() {
        NetworkInfo b = b();
        return b != null && b.isAvailable() && b.isConnected();
    }

    @SuppressLint({"MissingPermission"})
    public static NetworkInfo b() {
        Application c = a.a().c();
        NetworkInfo networkInfo = null;
        if (c == null) {
            return null;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) c.getSystemService("connectivity");
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return networkInfo;
    }

    public static int c() {
        NetworkInfo b = b();
        if (b != null && b.isConnected()) {
            if (b.getType() == 1) {
                return 1;
            }
            if (b.getType() == 0) {
                return 0;
            }
        }
        return -1;
    }
}
