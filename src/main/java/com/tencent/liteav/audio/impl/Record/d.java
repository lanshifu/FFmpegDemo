package com.tencent.liteav.audio.impl.Record;

import android.content.Context;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;

/* compiled from: TXCAudioCustomRecord */
public class d extends b implements Runnable {
    private boolean d = false;
    private Thread e = null;
    private byte[] f = new byte[20480];
    private int g = 0;
    private int h = 0;

    public void a(Context context, int i, int i2, int i3) {
        super.a(context, i, i2, i3);
        c();
        this.d = true;
        this.e = new Thread(this, "AudioCustomRecord Thread");
        this.e.start();
    }

    public void c() {
        this.d = false;
        long currentTimeMillis = System.currentTimeMillis();
        if (!(this.e == null || !this.e.isAlive() || Thread.currentThread().getId() == this.e.getId())) {
            try {
                this.e.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("custom record stop Exception: ");
                stringBuilder.append(e.getMessage());
                TXCLog.e("AudioCenter:TXCAudioCustomRecord", stringBuilder.toString());
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("stop record cost time(MS): ");
        stringBuilder2.append(System.currentTimeMillis() - currentTimeMillis);
        TXCLog.i("AudioCenter:TXCAudioCustomRecord", stringBuilder2.toString());
        this.e = null;
    }

    public boolean d() {
        return this.d;
    }

    /* JADX WARNING: Missing block: B:11:0x003d, code skipped:
            return;
     */
    public synchronized void a(byte[] r5) {
        /*
        r4 = this;
        monitor-enter(r4);
        if (r5 == 0) goto L_0x0040;
    L_0x0003:
        r0 = r4.f();	 Catch:{ all -> 0x003e }
        r1 = r5.length;	 Catch:{ all -> 0x003e }
        if (r0 >= r1) goto L_0x000b;
    L_0x000a:
        goto L_0x0040;
    L_0x000b:
        r0 = r4.g;	 Catch:{ all -> 0x003e }
        r1 = r5.length;	 Catch:{ all -> 0x003e }
        r0 = r0 + r1;
        r1 = r4.f;	 Catch:{ all -> 0x003e }
        r1 = r1.length;	 Catch:{ all -> 0x003e }
        r2 = 0;
        if (r0 > r1) goto L_0x0024;
    L_0x0015:
        r0 = r4.f;	 Catch:{ all -> 0x003e }
        r1 = r4.g;	 Catch:{ all -> 0x003e }
        r3 = r5.length;	 Catch:{ all -> 0x003e }
        java.lang.System.arraycopy(r5, r2, r0, r1, r3);	 Catch:{ all -> 0x003e }
        r0 = r4.g;	 Catch:{ all -> 0x003e }
        r5 = r5.length;	 Catch:{ all -> 0x003e }
        r0 = r0 + r5;
        r4.g = r0;	 Catch:{ all -> 0x003e }
        goto L_0x003c;
    L_0x0024:
        r0 = r4.f;	 Catch:{ all -> 0x003e }
        r0 = r0.length;	 Catch:{ all -> 0x003e }
        r1 = r4.g;	 Catch:{ all -> 0x003e }
        r0 = r0 - r1;
        r1 = r4.f;	 Catch:{ all -> 0x003e }
        r3 = r4.g;	 Catch:{ all -> 0x003e }
        java.lang.System.arraycopy(r5, r2, r1, r3, r0);	 Catch:{ all -> 0x003e }
        r1 = r5.length;	 Catch:{ all -> 0x003e }
        r1 = r1 - r0;
        r4.g = r1;	 Catch:{ all -> 0x003e }
        r1 = r4.f;	 Catch:{ all -> 0x003e }
        r3 = r4.g;	 Catch:{ all -> 0x003e }
        java.lang.System.arraycopy(r5, r0, r1, r2, r3);	 Catch:{ all -> 0x003e }
    L_0x003c:
        monitor-exit(r4);
        return;
    L_0x003e:
        r5 = move-exception;
        goto L_0x0069;
    L_0x0040:
        r0 = "AudioCenter:TXCAudioCustomRecord";
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x003e }
        r1.<init>();	 Catch:{ all -> 0x003e }
        r2 = "缓冲区不够. 自定义数据长度 = ";
        r1.append(r2);	 Catch:{ all -> 0x003e }
        if (r5 != 0) goto L_0x0050;
    L_0x004e:
        r5 = -1;
        goto L_0x0051;
    L_0x0050:
        r5 = r5.length;	 Catch:{ all -> 0x003e }
    L_0x0051:
        r1.append(r5);	 Catch:{ all -> 0x003e }
        r5 = ", 剩余缓冲区长度 = ";
        r1.append(r5);	 Catch:{ all -> 0x003e }
        r5 = r4.f();	 Catch:{ all -> 0x003e }
        r1.append(r5);	 Catch:{ all -> 0x003e }
        r5 = r1.toString();	 Catch:{ all -> 0x003e }
        com.tencent.liteav.basic.log.TXCLog.e(r0, r5);	 Catch:{ all -> 0x003e }
        monitor-exit(r4);
        return;
    L_0x0069:
        monitor-exit(r4);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.audio.impl.Record.d.a(byte[]):void");
    }

    private int e() {
        return ((this.g + this.f.length) - this.h) % this.f.length;
    }

    private int f() {
        return this.f.length - e();
    }

    public void run() {
        if (this.d) {
            a();
            int i = ((this.b * Filter.K) * this.c) / 8;
            byte[] bArr = new byte[i];
            while (this.d && !Thread.interrupted()) {
                if (i <= e()) {
                    synchronized (this) {
                        if (this.h + i <= this.f.length) {
                            System.arraycopy(this.f, this.h, bArr, 0, i);
                            this.h += i;
                        } else {
                            int length = this.f.length - this.h;
                            System.arraycopy(this.f, this.h, bArr, 0, length);
                            this.h = i - length;
                            System.arraycopy(this.f, 0, bArr, length, this.h);
                        }
                    }
                    a(bArr, bArr.length, TXCTimeUtil.getTimeTick());
                } else {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            b();
            return;
        }
        TXCLog.w("AudioCenter:TXCAudioCustomRecord", "audio custom record: abandom start audio sys record thread!");
    }
}
