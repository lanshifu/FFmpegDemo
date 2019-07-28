package com.tencent.liteav.basic.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/* compiled from: TXCThread */
public class c {
    private Handler a;
    private Looper b;
    private boolean c = true;
    private Thread d;

    public c(String str) {
        HandlerThread handlerThread = new HandlerThread(str);
        handlerThread.start();
        this.b = handlerThread.getLooper();
        this.a = new Handler(this.b);
        this.d = handlerThread;
    }

    public Handler a() {
        return this.a;
    }

    /* Access modifiers changed, original: protected */
    public void finalize() throws Throwable {
        if (this.c) {
            this.a.getLooper().quit();
        }
        super.finalize();
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public void a(final java.lang.Runnable r6) {
        /*
        r5 = this;
        r0 = 1;
        r0 = new boolean[r0];
        r1 = java.lang.Thread.currentThread();
        r2 = r5.d;
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0013;
    L_0x000f:
        r6.run();
        goto L_0x0033;
    L_0x0013:
        r1 = r5.a;
        monitor-enter(r1);
        r2 = 0;
        r0[r2] = r2;	 Catch:{ all -> 0x0034 }
        r3 = r5.a;	 Catch:{ all -> 0x0034 }
        r4 = new com.tencent.liteav.basic.util.c$1;	 Catch:{ all -> 0x0034 }
        r4.<init>(r6, r0);	 Catch:{ all -> 0x0034 }
        r3.post(r4);	 Catch:{ all -> 0x0034 }
    L_0x0023:
        r6 = r0[r2];	 Catch:{ all -> 0x0034 }
        if (r6 != 0) goto L_0x0032;
    L_0x0027:
        r6 = r5.a;	 Catch:{ Exception -> 0x002d }
        r6.wait();	 Catch:{ Exception -> 0x002d }
        goto L_0x0023;
    L_0x002d:
        r6 = move-exception;
        r6.printStackTrace();	 Catch:{ all -> 0x0034 }
        goto L_0x0023;
    L_0x0032:
        monitor-exit(r1);	 Catch:{ all -> 0x0034 }
    L_0x0033:
        return;
    L_0x0034:
        r6 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0034 }
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.basic.util.c.a(java.lang.Runnable):void");
    }

    public void b(Runnable runnable) {
        this.a.post(runnable);
    }

    public void a(Runnable runnable, long j) {
        this.a.postDelayed(runnable, j);
    }
}
