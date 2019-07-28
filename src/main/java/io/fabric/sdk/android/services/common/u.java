package io.fabric.sdk.android.services.common;

import android.os.SystemClock;
import android.util.Log;

/* compiled from: TimingMetric */
public class u {
    private final String a;
    private final String b;
    private final boolean c;
    private long d;
    private long e;

    public u(String str, String str2) {
        this.a = str;
        this.b = str2;
        this.c = Log.isLoggable(str2, 2) ^ 1;
    }

    public synchronized void a() {
        if (!this.c) {
            this.d = SystemClock.elapsedRealtime();
            this.e = 0;
        }
    }

    public synchronized void b() {
        if (!this.c) {
            if (this.e == 0) {
                this.e = SystemClock.elapsedRealtime() - this.d;
                c();
            }
        }
    }

    private void c() {
        String str = this.b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        stringBuilder.append(": ");
        stringBuilder.append(this.e);
        stringBuilder.append("ms");
        Log.v(str, stringBuilder.toString());
    }
}
