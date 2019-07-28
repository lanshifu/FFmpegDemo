package org.jetbrains.anko;

import android.os.Handler;
import android.os.Looper;
import kotlin.jvm.internal.f;

/* compiled from: Async.kt */
final class e {
    public static final e a = null;
    private static final Handler b = null;
    private static final Thread c = null;

    static {
        e eVar = new e();
    }

    private e() {
        a = this;
        b = new Handler(Looper.getMainLooper());
        Object thread = Looper.getMainLooper().getThread();
        f.a(thread, "Looper.getMainLooper().thread");
        c = thread;
    }

    public final Handler a() {
        return b;
    }

    public final Thread b() {
        return c;
    }
}
