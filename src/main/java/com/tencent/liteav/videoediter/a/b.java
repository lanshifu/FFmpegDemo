package com.tencent.liteav.videoediter.a;

import android.annotation.TargetApi;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import com.tencent.liteav.basic.log.TXCLog;
import java.io.IOException;
import java.util.HashMap;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

@TargetApi(16)
/* compiled from: TXMediaExtractor */
public class b {
    private MediaExtractor a;
    private MediaExtractor b;
    private HashMap<Integer, MediaFormat> c = new HashMap();
    private MediaFormat d;
    private MediaFormat e;
    private long f = 0;
    private boolean g = true;
    private boolean h = true;

    public synchronized void a(String str) throws IOException {
        f();
        this.a = new MediaExtractor();
        this.a.setDataSource(str);
        int trackCount = this.a.getTrackCount();
        for (int i = 0; i < trackCount; i++) {
            MediaFormat trackFormat = this.a.getTrackFormat(i);
            if (trackFormat != null) {
                this.c.put(Integer.valueOf(i), trackFormat);
                String string = trackFormat.getString(IMediaFormat.KEY_MIME);
                if (string != null && string.startsWith("video")) {
                    this.d = trackFormat;
                    this.a.selectTrack(i);
                    this.g = false;
                } else if (string != null && string.startsWith("audio")) {
                    this.e = trackFormat;
                    this.b = new MediaExtractor();
                    this.b.setDataSource(str);
                    this.b.selectTrack(i);
                    this.h = false;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("track index: ");
                stringBuilder.append(i);
                stringBuilder.append(", format: ");
                stringBuilder.append(trackFormat);
                TXCLog.d("TXMediaExtractor", stringBuilder.toString());
                long j = trackFormat.getLong("durationUs");
                if (this.f < j) {
                    this.f = j;
                }
            }
        }
    }

    public synchronized MediaFormat a() {
        return this.d;
    }

    public synchronized MediaFormat b() {
        return this.e;
    }

    public synchronized long c() {
        return this.f;
    }

    public synchronized void a(long j) {
        if (this.a != null) {
            this.a.seekTo(j, 0);
            this.g = false;
        }
        if (!(this.b == null || this.a == null)) {
            this.b.seekTo(this.a.getSampleTime(), 0);
            this.h = false;
        }
    }

    public synchronized long d() {
        long j;
        j = 0;
        if (this.a != null) {
            j = this.a.getSampleTime();
        }
        if (this.b != null && r0 > this.b.getSampleTime()) {
            j = this.b.getSampleTime();
        }
        return j;
    }

    /* JADX WARNING: Missing block: B:28:0x0052, code skipped:
            return -1;
     */
    /* JADX WARNING: Missing block: B:48:0x00b5, code skipped:
            return r4;
     */
    /* JADX WARNING: Missing block: B:57:0x00ce, code skipped:
            return -1;
     */
    public synchronized int a(com.tencent.liteav.d.e r9) {
        /*
        r8 = this;
        monitor-enter(r8);
        r0 = -1;
        r1 = 4;
        r2 = 0;
        if (r9 == 0) goto L_0x00b8;
    L_0x0006:
        r3 = r9.b();	 Catch:{ all -> 0x00b6 }
        if (r3 != 0) goto L_0x000e;
    L_0x000c:
        goto L_0x00b8;
    L_0x000e:
        r3 = 0;
        r4 = r8.g;	 Catch:{ all -> 0x00b6 }
        if (r4 == 0) goto L_0x0016;
    L_0x0013:
        r3 = r8.b;	 Catch:{ all -> 0x00b6 }
        goto L_0x003a;
    L_0x0016:
        r4 = r8.h;	 Catch:{ all -> 0x00b6 }
        if (r4 == 0) goto L_0x001d;
    L_0x001a:
        r3 = r8.a;	 Catch:{ all -> 0x00b6 }
        goto L_0x003a;
    L_0x001d:
        r4 = r8.a;	 Catch:{ all -> 0x00b6 }
        if (r4 == 0) goto L_0x003a;
    L_0x0021:
        r4 = r8.b;	 Catch:{ all -> 0x00b6 }
        if (r4 == 0) goto L_0x003a;
    L_0x0025:
        r3 = r8.a;	 Catch:{ all -> 0x00b6 }
        r3 = r3.getSampleTime();	 Catch:{ all -> 0x00b6 }
        r5 = r8.b;	 Catch:{ all -> 0x00b6 }
        r5 = r5.getSampleTime();	 Catch:{ all -> 0x00b6 }
        r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r7 <= 0) goto L_0x0038;
    L_0x0035:
        r3 = r8.b;	 Catch:{ all -> 0x00b6 }
        goto L_0x003a;
    L_0x0038:
        r3 = r8.a;	 Catch:{ all -> 0x00b6 }
    L_0x003a:
        if (r3 != 0) goto L_0x0053;
    L_0x003c:
        r3 = "TXMediaExtractor";
        r4 = "extractor = null!";
        com.tencent.liteav.basic.log.TXCLog.w(r3, r4);	 Catch:{ all -> 0x00b6 }
        if (r9 == 0) goto L_0x0051;
    L_0x0045:
        r3 = r9.b();	 Catch:{ all -> 0x00b6 }
        if (r3 == 0) goto L_0x0051;
    L_0x004b:
        r9.d(r2);	 Catch:{ all -> 0x00b6 }
        r9.c(r1);	 Catch:{ all -> 0x00b6 }
    L_0x0051:
        monitor-exit(r8);
        return r0;
    L_0x0053:
        r4 = r9.b();	 Catch:{ all -> 0x00b6 }
        r4 = r3.readSampleData(r4, r2);	 Catch:{ all -> 0x00b6 }
        if (r4 >= 0) goto L_0x0076;
    L_0x005d:
        r5 = r8.a;	 Catch:{ all -> 0x00b6 }
        r6 = 1;
        if (r3 != r5) goto L_0x0065;
    L_0x0062:
        r8.g = r6;	 Catch:{ all -> 0x00b6 }
        goto L_0x0067;
    L_0x0065:
        r8.h = r6;	 Catch:{ all -> 0x00b6 }
    L_0x0067:
        r3 = r8.g;	 Catch:{ all -> 0x00b6 }
        if (r3 == 0) goto L_0x000e;
    L_0x006b:
        r3 = r8.h;	 Catch:{ all -> 0x00b6 }
        if (r3 == 0) goto L_0x000e;
    L_0x006f:
        r9.d(r2);	 Catch:{ all -> 0x00b6 }
        r9.c(r1);	 Catch:{ all -> 0x00b6 }
        goto L_0x00b4;
    L_0x0076:
        r0 = r3.getSampleTime();	 Catch:{ all -> 0x00b6 }
        r5 = r3.getSampleFlags();	 Catch:{ all -> 0x00b6 }
        r6 = r3.getSampleTrackIndex();	 Catch:{ all -> 0x00b6 }
        r7 = r8.c;	 Catch:{ all -> 0x00b6 }
        r7 = r7.size();	 Catch:{ all -> 0x00b6 }
        if (r6 >= r7) goto L_0x00a1;
    L_0x008a:
        r7 = r8.c;	 Catch:{ all -> 0x00b6 }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ all -> 0x00b6 }
        r6 = r7.get(r6);	 Catch:{ all -> 0x00b6 }
        r6 = (android.media.MediaFormat) r6;	 Catch:{ all -> 0x00b6 }
        if (r6 == 0) goto L_0x00a1;
    L_0x0098:
        r7 = "mime";
        r6 = r6.getString(r7);	 Catch:{ all -> 0x00b6 }
        r9.a(r6);	 Catch:{ all -> 0x00b6 }
    L_0x00a1:
        r9.a(r0);	 Catch:{ all -> 0x00b6 }
        r9.c(r5);	 Catch:{ all -> 0x00b6 }
        r9.d(r4);	 Catch:{ all -> 0x00b6 }
        r9 = r9.b();	 Catch:{ all -> 0x00b6 }
        r9.position(r2);	 Catch:{ all -> 0x00b6 }
        r3.advance();	 Catch:{ all -> 0x00b6 }
    L_0x00b4:
        monitor-exit(r8);
        return r4;
    L_0x00b6:
        r9 = move-exception;
        goto L_0x00cf;
    L_0x00b8:
        r3 = "TXMediaExtractor";
        r4 = "frame input is invalid";
        com.tencent.liteav.basic.log.TXCLog.e(r3, r4);	 Catch:{ all -> 0x00b6 }
        if (r9 == 0) goto L_0x00cd;
    L_0x00c1:
        r3 = r9.b();	 Catch:{ all -> 0x00b6 }
        if (r3 == 0) goto L_0x00cd;
    L_0x00c7:
        r9.d(r2);	 Catch:{ all -> 0x00b6 }
        r9.c(r1);	 Catch:{ all -> 0x00b6 }
    L_0x00cd:
        monitor-exit(r8);
        return r0;
    L_0x00cf:
        monitor-exit(r8);
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.videoediter.a.b.a(com.tencent.liteav.d.e):int");
    }

    private synchronized void f() {
        if (this.a != null) {
            this.a.release();
            this.a = null;
        }
        if (this.b != null) {
            this.b.release();
            this.b = null;
        }
        this.c.clear();
        this.d = null;
        this.e = null;
        this.f = 0;
        this.g = true;
        this.h = true;
    }

    public synchronized void e() {
        f();
    }
}
