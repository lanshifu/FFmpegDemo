package com.tencent.liteav.e;

import android.annotation.TargetApi;
import android.os.HandlerThread;

@TargetApi(16)
/* compiled from: VideoDecAndDemuxGenerate */
public class v extends d {
    private final String V;

    public void a(boolean z) {
    }

    public v() {
        this.V = "VideoDecAndDemuxGenerate";
        this.z = new HandlerThread("video_handler_thread");
        this.z.start();
        this.y = new b(this.z.getLooper());
        this.B = new HandlerThread("audio_handler_thread");
        this.B.start();
        this.A = new a(this.B.getLooper());
    }

    /* JADX WARNING: Missing block: B:15:0x0075, code skipped:
            return;
     */
    public synchronized void l() {
        /*
        r7 = this;
        monitor-enter(r7);
        r0 = r7.x;	 Catch:{ all -> 0x0094 }
        r0 = r0.get();	 Catch:{ all -> 0x0094 }
        r1 = 2;
        if (r0 == r1) goto L_0x0076;
    L_0x000a:
        r0 = r7.x;	 Catch:{ all -> 0x0094 }
        r0 = r0.get();	 Catch:{ all -> 0x0094 }
        r2 = 3;
        if (r0 != r2) goto L_0x0014;
    L_0x0013:
        goto L_0x0076;
    L_0x0014:
        r0 = r7.x;	 Catch:{ all -> 0x0094 }
        r0.set(r1);	 Catch:{ all -> 0x0094 }
        r0 = r7.P;	 Catch:{ all -> 0x0094 }
        r1 = 0;
        r0.set(r1);	 Catch:{ all -> 0x0094 }
        r0 = r7.Q;	 Catch:{ all -> 0x0094 }
        r0.getAndSet(r1);	 Catch:{ all -> 0x0094 }
        r0 = r7.t;	 Catch:{ all -> 0x0094 }
        r0.getAndSet(r1);	 Catch:{ all -> 0x0094 }
        r0 = r7.u;	 Catch:{ all -> 0x0094 }
        r0.getAndSet(r1);	 Catch:{ all -> 0x0094 }
        r0 = r7.v;	 Catch:{ all -> 0x0094 }
        r0.getAndSet(r1);	 Catch:{ all -> 0x0094 }
        r0 = r7.w;	 Catch:{ all -> 0x0094 }
        r0.getAndSet(r1);	 Catch:{ all -> 0x0094 }
        r7.i = r1;	 Catch:{ all -> 0x0094 }
        r0 = com.tencent.liteav.c.f.a();	 Catch:{ all -> 0x0094 }
        r0 = r0.b();	 Catch:{ all -> 0x0094 }
        if (r0 != 0) goto L_0x004a;
    L_0x0044:
        r0 = 0;
        r7.b(r0, r0);	 Catch:{ all -> 0x0094 }
        goto L_0x0057;
    L_0x004a:
        r1 = r0.a;	 Catch:{ all -> 0x0094 }
        r3 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r1 = r1 * r3;
        r5 = r0.b;	 Catch:{ all -> 0x0094 }
        r5 = r5 * r3;
        r7.b(r1, r5);	 Catch:{ all -> 0x0094 }
    L_0x0057:
        r0 = r7.g;	 Catch:{ all -> 0x0094 }
        r0 = r0.get();	 Catch:{ all -> 0x0094 }
        r7.a(r0);	 Catch:{ all -> 0x0094 }
        r0 = r7.y;	 Catch:{ all -> 0x0094 }
        r1 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r0.sendEmptyMessage(r1);	 Catch:{ all -> 0x0094 }
        r0 = r7.h();	 Catch:{ all -> 0x0094 }
        if (r0 == 0) goto L_0x0074;
    L_0x006d:
        r0 = r7.A;	 Catch:{ all -> 0x0094 }
        r1 = 201; // 0xc9 float:2.82E-43 double:9.93E-322;
        r0.sendEmptyMessage(r1);	 Catch:{ all -> 0x0094 }
    L_0x0074:
        monitor-exit(r7);
        return;
    L_0x0076:
        r0 = "VideoDecAndDemuxGenerate";
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0094 }
        r1.<init>();	 Catch:{ all -> 0x0094 }
        r2 = "start ignore, mCurrentState = ";
        r1.append(r2);	 Catch:{ all -> 0x0094 }
        r2 = r7.x;	 Catch:{ all -> 0x0094 }
        r2 = r2.get();	 Catch:{ all -> 0x0094 }
        r1.append(r2);	 Catch:{ all -> 0x0094 }
        r1 = r1.toString();	 Catch:{ all -> 0x0094 }
        com.tencent.liteav.basic.log.TXCLog.e(r0, r1);	 Catch:{ all -> 0x0094 }
        monitor-exit(r7);
        return;
    L_0x0094:
        r0 = move-exception;
        monitor-exit(r7);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.e.v.l():void");
    }

    /* JADX WARNING: Missing block: B:12:0x002d, code skipped:
            return;
     */
    public synchronized void m() {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.x;	 Catch:{ all -> 0x002e }
        r0 = r0.get();	 Catch:{ all -> 0x002e }
        r1 = 1;
        if (r0 != r1) goto L_0x0013;
    L_0x000a:
        r0 = "VideoDecAndDemuxGenerate";
        r1 = "stop ignore, mCurrentState is STATE_INIT";
        com.tencent.liteav.basic.log.TXCLog.e(r0, r1);	 Catch:{ all -> 0x002e }
        monitor-exit(r2);
        return;
    L_0x0013:
        r0 = r2.x;	 Catch:{ all -> 0x002e }
        r0.set(r1);	 Catch:{ all -> 0x002e }
        r0 = r2.y;	 Catch:{ all -> 0x002e }
        r1 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        r0.sendEmptyMessage(r1);	 Catch:{ all -> 0x002e }
        r0 = r2.h();	 Catch:{ all -> 0x002e }
        if (r0 == 0) goto L_0x002c;
    L_0x0025:
        r0 = r2.A;	 Catch:{ all -> 0x002e }
        r1 = 203; // 0xcb float:2.84E-43 double:1.003E-321;
        r0.sendEmptyMessage(r1);	 Catch:{ all -> 0x002e }
    L_0x002c:
        monitor-exit(r2);
        return;
    L_0x002e:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.e.v.m():void");
    }

    public void k() {
        if (this.z != null) {
            this.z.quit();
        }
        if (this.B != null) {
            this.B.quit();
        }
    }
}
