package com.tomatolive.library.utils;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;

/* compiled from: HandlerUtils */
public class k {
    private HandlerThread a;
    private Handler b;

    /* compiled from: HandlerUtils */
    private static class a {
        private static final k a = new k();
    }

    private k() {
    }

    public static k a() {
        return a.a;
    }

    public Handler a(String str, Callback callback) {
        if (this.a != null && this.a.isAlive()) {
            return null;
        }
        this.a = new HandlerThread(str);
        this.a.start();
        this.b = new Handler(this.a.getLooper(), callback);
        return this.b;
    }

    public void b() {
        if (this.b != null) {
            this.b.removeCallbacksAndMessages(null);
            this.b = null;
        }
        if (this.a != null) {
            try {
                if (VERSION.SDK_INT >= 18) {
                    this.a.quitSafely();
                } else {
                    this.a.quit();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                this.a = null;
            }
            this.a = null;
        }
    }
}
