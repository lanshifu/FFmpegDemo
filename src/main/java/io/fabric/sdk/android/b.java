package io.fabric.sdk.android;

import android.util.Log;

/* compiled from: DefaultLogger */
public class b implements k {
    private int a;

    public b(int i) {
        this.a = i;
    }

    public b() {
        this.a = 4;
    }

    public boolean a(String str, int i) {
        return this.a <= i || Log.isLoggable(str, i);
    }

    public void a(String str, String str2, Throwable th) {
        if (a(str, 3)) {
            Log.d(str, str2, th);
        }
    }

    public void b(String str, String str2, Throwable th) {
        if (a(str, 2)) {
            Log.v(str, str2, th);
        }
    }

    public void c(String str, String str2, Throwable th) {
        if (a(str, 4)) {
            Log.i(str, str2, th);
        }
    }

    public void d(String str, String str2, Throwable th) {
        if (a(str, 5)) {
            Log.w(str, str2, th);
        }
    }

    public void e(String str, String str2, Throwable th) {
        if (a(str, 6)) {
            Log.e(str, str2, th);
        }
    }

    public void a(String str, String str2) {
        a(str, str2, null);
    }

    public void b(String str, String str2) {
        b(str, str2, null);
    }

    public void c(String str, String str2) {
        c(str, str2, null);
    }

    public void d(String str, String str2) {
        d(str, str2, null);
    }

    public void e(String str, String str2) {
        e(str, str2, null);
    }

    public void a(int i, String str, String str2) {
        a(i, str, str2, false);
    }

    public void a(int i, String str, String str2, boolean z) {
        if (z || a(str, i)) {
            Log.println(i, str, str2);
        }
    }
}
