package io.fabric.sdk.android.services.common;

import android.os.Process;

/* compiled from: BackgroundPriorityRunnable */
public abstract class h implements Runnable {
    public abstract void a();

    public final void run() {
        Process.setThreadPriority(10);
        a();
    }
}
