package com.tencent.liteav.videodecoder;

import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.rtmp.TXLiveConstants;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: TXCVideoDecoder */
public class b implements com.tencent.liteav.basic.d.a, d {
    boolean a = true;
    boolean b = true;
    boolean c = false;
    boolean d = false;
    Surface e;
    d f;
    private int g;
    private ByteBuffer h;
    private ByteBuffer i;
    private long j;
    private boolean k = false;
    private ArrayList<com.tencent.liteav.basic.g.b> l = new ArrayList();
    private a m;
    private WeakReference<com.tencent.liteav.basic.d.a> n;

    /* compiled from: TXCVideoDecoder */
    private static class a extends Handler {
        a a;
        d b;
        WeakReference<com.tencent.liteav.basic.d.a> c;
        boolean d;
        boolean e;
        Surface f;
        private ByteBuffer g;
        private ByteBuffer h;

        public a(Looper looper) {
            super(looper);
        }

        public void a(boolean z, boolean z2, Surface surface, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, d dVar, com.tencent.liteav.basic.d.a aVar) {
            this.e = z;
            this.d = z2;
            this.f = surface;
            this.g = byteBuffer;
            this.h = byteBuffer2;
            this.b = dVar;
            this.c = new WeakReference(aVar);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 100:
                    a(((Boolean) message.obj).booleanValue());
                    return;
                case 101:
                    try {
                        Bundle data = message.getData();
                        a(data.getByteArray("nal"), data.getLong("pts"), data.getLong("dts"), data.getInt("codecId"));
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                case 102:
                    b();
                    return;
                case 103:
                    boolean z = false;
                    boolean z2 = message.arg1 == 1;
                    if (message.arg2 == 1) {
                        z = true;
                    }
                    a(z2, z);
                    return;
                default:
                    return;
            }
        }

        public boolean a() {
            return this.a != null ? this.a.isHevc() : false;
        }

        private void a(byte[] bArr, long j, long j2, int i) {
            com.tencent.liteav.basic.g.b bVar = new com.tencent.liteav.basic.g.b();
            bVar.nalData = bArr;
            bVar.pts = j;
            bVar.dts = j2;
            bVar.codecId = i;
            if (this.a != null) {
                this.a.decode(bVar);
            }
        }

        private void b() {
            if (this.a != null) {
                this.a.stop();
                this.a.setListener(null);
                this.a.setNotifyListener(null);
                this.a = null;
            }
            Looper.myLooper().quit();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("play:decode: stop decode hwdec: ");
            stringBuilder.append(this.d);
            TXCLog.w("TXCVideoDecoder", stringBuilder.toString());
        }

        private void a(boolean z, boolean z2) {
            this.d = z;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("play:decode: restart decode hwdec: ");
            stringBuilder.append(this.d);
            TXCLog.w("TXCVideoDecoder", stringBuilder.toString());
            if (this.a != null) {
                this.a.stop();
                this.a.setListener(null);
                this.a.setNotifyListener(null);
                this.a = null;
            }
            a(z2);
        }

        private void a(boolean z) {
            StringBuilder stringBuilder;
            if (this.a != null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("play:decode: start decode ignore hwdec: ");
                stringBuilder.append(this.d);
                TXCLog.i("TXCVideoDecoder", stringBuilder.toString());
                return;
            }
            if (this.d) {
                this.a = new c();
            } else {
                this.a = new TXCVideoFfmpegDecoder();
            }
            this.a.setListener(this.b);
            this.a.setNotifyListener(this.c);
            this.a.config(this.f);
            this.a.start(this.g, this.h, z, this.e);
            stringBuilder = new StringBuilder();
            stringBuilder.append("play:decode: start decode hwdec: ");
            stringBuilder.append(this.d);
            stringBuilder.append(", hevc: ");
            stringBuilder.append(this.e);
            TXCLog.w("TXCVideoDecoder", stringBuilder.toString());
        }
    }

    public void onNotifyEvent(int i, Bundle bundle) {
        com.tencent.liteav.basic.util.b.a(this.n, this.j, i, bundle);
    }

    public void a(long j) {
        this.j = j;
    }

    public void a(d dVar) {
        this.f = dVar;
    }

    public boolean a() {
        return this.b;
    }

    public void a(com.tencent.liteav.basic.d.a aVar) {
        this.n = new WeakReference(aVar);
    }

    public int a(SurfaceTexture surfaceTexture, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, boolean z) {
        return a(new Surface(surfaceTexture), byteBuffer, byteBuffer2, z);
    }

    public int a(Surface surface, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, boolean z) {
        this.e = surface;
        this.h = byteBuffer;
        this.i = byteBuffer2;
        this.a = z;
        return 0;
    }

    public void a(boolean z) {
        this.b = z;
    }

    private void b(com.tencent.liteav.basic.g.b bVar) {
        boolean z = bVar.nalType == 0;
        Bundle bundle = new Bundle();
        bundle.putBoolean("iframe", z);
        bundle.putByteArray("nal", bVar.nalData);
        bundle.putLong("pts", bVar.pts);
        bundle.putLong("dts", bVar.dts);
        bundle.putInt("codecId", bVar.codecId);
        Message message = new Message();
        message.what = 101;
        message.setData(bundle);
        a aVar = this.m;
        if (aVar != null) {
            aVar.sendMessage(message);
        }
        this.g++;
    }

    public void a(com.tencent.liteav.basic.g.b bVar) {
        try {
            Object obj = bVar.nalType == 0 ? 1 : null;
            if (this.d || obj != null) {
                if (!(this.d || obj == null)) {
                    TXCLog.w("TXCVideoDecoder", "play:decode: push first i frame");
                    this.d = true;
                }
                if (!(this.k || bVar.codecId != 1 || this.b)) {
                    TXCLog.w("TXCVideoDecoder", "play:decode: hevc decode error  ");
                    com.tencent.liteav.basic.util.b.a(this.n, (int) TXLiveConstants.PLAY_ERR_HEVC_DECODE_FAIL, "h265解码失败");
                    this.k = true;
                }
                if (this.m != null) {
                    if (!this.l.isEmpty()) {
                        Iterator it = this.l.iterator();
                        while (it.hasNext()) {
                            b((com.tencent.liteav.basic.g.b) it.next());
                        }
                    }
                    this.l.clear();
                    b(bVar);
                } else {
                    if (!(obj == null || this.l.isEmpty())) {
                        this.l.clear();
                    }
                    this.l.add(bVar);
                    if (!this.k) {
                        b();
                    }
                }
                return;
            }
            TXCLog.i("TXCVideoDecoder", "play:decode: push nal ignore p frame when not got i frame");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Missing block: B:15:0x007c, code skipped:
            r1 = new android.os.Bundle();
            r1.putInt("EVT_ID", com.tencent.rtmp.TXLiveConstants.PLAY_EVT_START_VIDEO_DECODER);
            r1.putLong("EVT_TIME", com.tencent.liteav.basic.util.TXCTimeUtil.getTimeTick());
            r2 = com.tencent.rtmp.TXLiveConstants.EVT_DESCRIPTION;
     */
    /* JADX WARNING: Missing block: B:16:0x0095, code skipped:
            if (r11.b == false) goto L_0x009a;
     */
    /* JADX WARNING: Missing block: B:17:0x0097, code skipped:
            r4 = "启动硬解";
     */
    /* JADX WARNING: Missing block: B:18:0x009a, code skipped:
            r4 = "启动软解";
     */
    /* JADX WARNING: Missing block: B:19:0x009c, code skipped:
            r1.putCharSequence(r2, r4);
            r2 = "EVT_PARAM1";
     */
    /* JADX WARNING: Missing block: B:20:0x00a3, code skipped:
            if (r11.b == false) goto L_0x00a7;
     */
    /* JADX WARNING: Missing block: B:21:0x00a5, code skipped:
            r4 = 1;
     */
    /* JADX WARNING: Missing block: B:22:0x00a7, code skipped:
            r4 = 2;
     */
    /* JADX WARNING: Missing block: B:23:0x00a8, code skipped:
            r1.putInt(r2, r4);
            com.tencent.liteav.basic.util.b.a(r11.n, r11.j, (int) com.tencent.rtmp.TXLiveConstants.PLAY_EVT_START_VIDEO_DECODER, r1);
     */
    /* JADX WARNING: Missing block: B:24:0x00b2, code skipped:
            return 0;
     */
    public int b() {
        /*
        r11 = this;
        r0 = r11.b;
        r1 = -1;
        if (r0 == 0) goto L_0x0011;
    L_0x0005:
        r0 = r11.e;
        if (r0 != 0) goto L_0x0011;
    L_0x0009:
        r0 = "TXCVideoDecoder";
        r2 = "play:decode: start decoder error when not setup surface";
        com.tencent.liteav.basic.log.TXCLog.i(r0, r2);
        return r1;
    L_0x0011:
        monitor-enter(r11);
        r0 = r11.m;	 Catch:{ all -> 0x00b3 }
        if (r0 == 0) goto L_0x001f;
    L_0x0016:
        r0 = "TXCVideoDecoder";
        r2 = "play:decode: start decoder error when decoder is started";
        com.tencent.liteav.basic.log.TXCLog.e(r0, r2);	 Catch:{ all -> 0x00b3 }
        monitor-exit(r11);	 Catch:{ all -> 0x00b3 }
        return r1;
    L_0x001f:
        r0 = 0;
        r11.g = r0;	 Catch:{ all -> 0x00b3 }
        r11.k = r0;	 Catch:{ all -> 0x00b3 }
        r1 = new android.os.HandlerThread;	 Catch:{ all -> 0x00b3 }
        r2 = "VDecoder";
        r1.<init>(r2);	 Catch:{ all -> 0x00b3 }
        r1.start();	 Catch:{ all -> 0x00b3 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b3 }
        r2.<init>();	 Catch:{ all -> 0x00b3 }
        r3 = "VDecoder";
        r2.append(r3);	 Catch:{ all -> 0x00b3 }
        r3 = r1.getId();	 Catch:{ all -> 0x00b3 }
        r2.append(r3);	 Catch:{ all -> 0x00b3 }
        r2 = r2.toString();	 Catch:{ all -> 0x00b3 }
        r1.setName(r2);	 Catch:{ all -> 0x00b3 }
        r2 = new com.tencent.liteav.videodecoder.b$a;	 Catch:{ all -> 0x00b3 }
        r1 = r1.getLooper();	 Catch:{ all -> 0x00b3 }
        r2.<init>(r1);	 Catch:{ all -> 0x00b3 }
        r4 = r11.c;	 Catch:{ all -> 0x00b3 }
        r5 = r11.b;	 Catch:{ all -> 0x00b3 }
        r6 = r11.e;	 Catch:{ all -> 0x00b3 }
        r7 = r11.h;	 Catch:{ all -> 0x00b3 }
        r8 = r11.i;	 Catch:{ all -> 0x00b3 }
        r3 = r2;
        r9 = r11;
        r10 = r11;
        r3.a(r4, r5, r6, r7, r8, r9, r10);	 Catch:{ all -> 0x00b3 }
        r1 = "TXCVideoDecoder";
        r3 = "play:decode: start decode thread";
        com.tencent.liteav.basic.log.TXCLog.w(r1, r3);	 Catch:{ all -> 0x00b3 }
        r1 = android.os.Message.obtain();	 Catch:{ all -> 0x00b3 }
        r3 = 100;
        r1.what = r3;	 Catch:{ all -> 0x00b3 }
        r3 = r11.a;	 Catch:{ all -> 0x00b3 }
        r3 = java.lang.Boolean.valueOf(r3);	 Catch:{ all -> 0x00b3 }
        r1.obj = r3;	 Catch:{ all -> 0x00b3 }
        r2.sendMessage(r1);	 Catch:{ all -> 0x00b3 }
        r11.m = r2;	 Catch:{ all -> 0x00b3 }
        monitor-exit(r11);	 Catch:{ all -> 0x00b3 }
        r1 = new android.os.Bundle;
        r1.<init>();
        r2 = "EVT_ID";
        r3 = 2008; // 0x7d8 float:2.814E-42 double:9.92E-321;
        r1.putInt(r2, r3);
        r2 = "EVT_TIME";
        r4 = com.tencent.liteav.basic.util.TXCTimeUtil.getTimeTick();
        r1.putLong(r2, r4);
        r2 = "EVT_MSG";
        r4 = r11.b;
        if (r4 == 0) goto L_0x009a;
    L_0x0097:
        r4 = "启动硬解";
        goto L_0x009c;
    L_0x009a:
        r4 = "启动软解";
    L_0x009c:
        r1.putCharSequence(r2, r4);
        r2 = "EVT_PARAM1";
        r4 = r11.b;
        if (r4 == 0) goto L_0x00a7;
    L_0x00a5:
        r4 = 1;
        goto L_0x00a8;
    L_0x00a7:
        r4 = 2;
    L_0x00a8:
        r1.putInt(r2, r4);
        r2 = r11.n;
        r4 = r11.j;
        com.tencent.liteav.basic.util.b.a(r2, r4, r3, r1);
        return r0;
    L_0x00b3:
        r0 = move-exception;
        monitor-exit(r11);	 Catch:{ all -> 0x00b3 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.videodecoder.b.b():int");
    }

    public void c() {
        synchronized (this) {
            if (this.m != null) {
                this.m.sendEmptyMessage(102);
            }
            this.m = null;
        }
        this.l.clear();
        this.d = false;
        this.g = 0;
    }

    public void b(boolean z) {
        synchronized (this) {
            this.b = z;
            this.l.clear();
            this.d = false;
            this.g = 0;
            Message obtain = Message.obtain();
            obtain.what = 103;
            obtain.arg1 = this.b;
            obtain.arg2 = this.a;
            if (this.m != null) {
                this.m.sendMessage(obtain);
            }
        }
    }

    public void a(byte[] bArr, long j, int i) {
        a aVar = this.m;
        if (aVar != null && !aVar.d && aVar.a != null) {
            ((TXCVideoFfmpegDecoder) aVar.a).loadNativeData(bArr, j, i);
        }
    }

    public int d() {
        return this.g + this.l.size();
    }

    public void a(SurfaceTexture surfaceTexture, int i, int i2, long j, long j2) {
        if (this.f != null) {
            this.f.a(surfaceTexture, i, i2, j, j2);
        }
        if (this.g > 0) {
            this.g--;
        }
    }

    public void a(long j, int i, int i2, long j2, long j3) {
        if (this.f != null) {
            this.f.a(j, i, i2, j2, j3);
        }
        if (this.g > 0) {
            this.g--;
        }
    }

    public void a(int i, int i2) {
        if (this.f != null) {
            this.f.a(i, i2);
        }
    }

    public boolean e() {
        a aVar = this.m;
        return aVar != null ? aVar.a() : false;
    }
}
