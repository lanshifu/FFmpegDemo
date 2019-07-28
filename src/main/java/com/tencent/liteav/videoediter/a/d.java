package com.tencent.liteav.videoediter.a;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.e;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: TXMultiMediaExtractor */
public class d extends b {
    private static final String a = "com.tencent.liteav.videoediter.a.d";
    private ArrayList<String> b = new ArrayList();
    private int c = -1;
    private long d = 0;
    private long e = 0;

    public synchronized void a(List<String> list) {
        if (list != null) {
            if (list.size() > 0) {
                this.b.addAll(list);
            }
        }
    }

    /* JADX WARNING: Missing block: B:34:0x00a0, code skipped:
            return;
     */
    public synchronized void a(long r9) {
        /*
        r8 = this;
        monitor-enter(r8);
        r0 = 0;
        r2 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1));
        if (r2 > 0) goto L_0x000f;
    L_0x0007:
        r8.g();	 Catch:{ all -> 0x000c }
        monitor-exit(r8);
        return;
    L_0x000c:
        r9 = move-exception;
        goto L_0x00a1;
    L_0x000f:
        r8.g();	 Catch:{ all -> 0x000c }
        r2 = r8.b;	 Catch:{ all -> 0x000c }
        r2 = r2.size();	 Catch:{ all -> 0x000c }
        if (r2 <= 0) goto L_0x009f;
    L_0x001a:
        r2 = new com.tencent.liteav.videoediter.a.b;	 Catch:{ all -> 0x000c }
        r2.<init>();	 Catch:{ all -> 0x000c }
        r3 = 0;
    L_0x0020:
        r4 = r8.b;	 Catch:{ all -> 0x000c }
        r4 = r4.size();	 Catch:{ all -> 0x000c }
        if (r3 >= r4) goto L_0x005b;
    L_0x0028:
        r4 = r8.b;	 Catch:{ all -> 0x000c }
        r4 = r4.get(r3);	 Catch:{ all -> 0x000c }
        r4 = (java.lang.String) r4;	 Catch:{ all -> 0x000c }
        r2.a(r4);	 Catch:{ IOException -> 0x003e }
        r4 = r2.c();	 Catch:{ all -> 0x000c }
        r6 = 0;
        r4 = r4 + r0;
        r6 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1));
        if (r6 <= 0) goto L_0x0058;
    L_0x003d:
        goto L_0x005b;
    L_0x003e:
        r4 = move-exception;
        r4.printStackTrace();	 Catch:{ all -> 0x000c }
        r5 = a;	 Catch:{ all -> 0x000c }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x000c }
        r6.<init>();	 Catch:{ all -> 0x000c }
        r7 = "setDataSource IOException: ";
        r6.append(r7);	 Catch:{ all -> 0x000c }
        r6.append(r4);	 Catch:{ all -> 0x000c }
        r4 = r6.toString();	 Catch:{ all -> 0x000c }
        com.tencent.liteav.basic.log.TXCLog.e(r5, r4);	 Catch:{ all -> 0x000c }
    L_0x0058:
        r3 = r3 + 1;
        goto L_0x0020;
    L_0x005b:
        r2.e();	 Catch:{ all -> 0x000c }
        r2 = r8.b;	 Catch:{ all -> 0x000c }
        r2 = r2.size();	 Catch:{ all -> 0x000c }
        if (r3 >= r2) goto L_0x009f;
    L_0x0066:
        r8.c = r3;	 Catch:{ all -> 0x000c }
        r8.d = r0;	 Catch:{ all -> 0x000c }
        r0 = r8.b;	 Catch:{ IOException -> 0x0078 }
        r1 = r8.c;	 Catch:{ IOException -> 0x0078 }
        r0 = r0.get(r1);	 Catch:{ IOException -> 0x0078 }
        r0 = (java.lang.String) r0;	 Catch:{ IOException -> 0x0078 }
        super.a(r0);	 Catch:{ IOException -> 0x0078 }
        goto L_0x0092;
    L_0x0078:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x000c }
        r1 = a;	 Catch:{ all -> 0x000c }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x000c }
        r2.<init>();	 Catch:{ all -> 0x000c }
        r3 = "setDataSource IOException: ";
        r2.append(r3);	 Catch:{ all -> 0x000c }
        r2.append(r0);	 Catch:{ all -> 0x000c }
        r0 = r2.toString();	 Catch:{ all -> 0x000c }
        com.tencent.liteav.basic.log.TXCLog.e(r1, r0);	 Catch:{ all -> 0x000c }
    L_0x0092:
        r0 = r8.d;	 Catch:{ all -> 0x000c }
        r2 = 0;
        r9 = r9 - r0;
        super.a(r9);	 Catch:{ all -> 0x000c }
        r9 = super.d();	 Catch:{ all -> 0x000c }
        r8.e = r9;	 Catch:{ all -> 0x000c }
    L_0x009f:
        monitor-exit(r8);
        return;
    L_0x00a1:
        monitor-exit(r8);
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.videoediter.a.d.a(long):void");
    }

    @TargetApi(16)
    public int f() {
        if (this.b.size() <= 0) {
            return -1;
        }
        b bVar = new b();
        Iterator it = this.b.iterator();
        MediaFormat mediaFormat = null;
        MediaFormat mediaFormat2 = null;
        while (it.hasNext()) {
            try {
                bVar.a((String) it.next());
                MediaFormat a = bVar.a();
                MediaFormat b = bVar.b();
                if (mediaFormat == null && mediaFormat2 == null) {
                    mediaFormat = a;
                    mediaFormat2 = b;
                } else if ((mediaFormat != null && a == null) || ((mediaFormat == null && a != null) || ((mediaFormat2 != null && b == null) || (mediaFormat2 == null && b != null)))) {
                    return -2;
                } else {
                    if (mediaFormat != null && a != null) {
                        try {
                            if (Math.abs(mediaFormat.getInteger("frame-rate") - a.getInteger("frame-rate")) > 3) {
                                return -4;
                            }
                            if (mediaFormat.getInteger("width") != a.getInteger("width")) {
                                return -5;
                            }
                            if (mediaFormat.getInteger("height") != a.getInteger("height")) {
                                return -6;
                            }
                        } catch (NullPointerException unused) {
                            return -3;
                        }
                    } else if (!(mediaFormat2 == null || b == null)) {
                        if (mediaFormat2.getInteger("sample-rate") != b.getInteger("sample-rate")) {
                            return -7;
                        }
                        if (mediaFormat2.getInteger("channel-count") != b.getInteger("channel-count")) {
                            return -8;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                String str = a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("setDataSource IOException: ");
                stringBuilder.append(e);
                TXCLog.e(str, stringBuilder.toString());
            }
        }
        bVar.e();
        return 0;
    }

    public synchronized long c() {
        long j;
        j = 0;
        if (this.b.size() > 0) {
            b bVar = new b();
            for (int i = 0; i < this.b.size(); i++) {
                try {
                    bVar.a((String) this.b.get(i));
                    j += bVar.c();
                } catch (IOException e) {
                    e.printStackTrace();
                    String str = a;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("setDataSource IOException: ");
                    stringBuilder.append(e);
                    TXCLog.e(str, stringBuilder.toString());
                }
            }
            bVar.e();
        }
        return j;
    }

    public synchronized int a(e eVar) {
        int a;
        a = super.a(eVar);
        while (a < 0 && this.c < this.b.size() - 1) {
            this.d = this.e + 1000;
            this.c++;
            try {
                a((String) this.b.get(this.c));
                a = super.a(eVar);
            } catch (IOException e) {
                String str = a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("setDataSource IOException: ");
                stringBuilder.append(e);
                TXCLog.e(str, stringBuilder.toString());
                e.printStackTrace();
            }
        }
        if (a >= 0) {
            long e2 = eVar.e() + this.d;
            eVar.a(e2);
            if (this.e < e2) {
                this.e = e2;
            }
        } else {
            String str2 = a;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("readSampleData length = ");
            stringBuilder2.append(a);
            TXCLog.d(str2, stringBuilder2.toString());
        }
        return a;
    }

    private synchronized void g() {
        super.e();
        this.c = -1;
        this.d = 0;
        this.e = 0;
    }

    public synchronized void e() {
        super.e();
        this.b.clear();
        this.c = -1;
        this.d = 0;
        this.e = 0;
    }
}
