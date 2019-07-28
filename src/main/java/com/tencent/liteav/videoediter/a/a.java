package com.tencent.liteav.videoediter.a;

import android.annotation.TargetApi;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import com.tencent.liteav.basic.log.TXCLog;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

@TargetApi(18)
/* compiled from: TXCMP4Muxer */
public class a {
    private final boolean a = false;
    private MediaMuxer b;
    private String c = null;
    private MediaFormat d = null;
    private MediaFormat e = null;
    private int f = 1;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private boolean j = false;
    private boolean k = false;
    private ConcurrentLinkedQueue<a> l = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<a> m = new ConcurrentLinkedQueue();
    private long n = -1;
    private long o = -1;
    private long p = -1;

    /* compiled from: TXCMP4Muxer */
    private static class a {
        ByteBuffer a;
        BufferInfo b;

        public a(ByteBuffer byteBuffer, BufferInfo bufferInfo) {
            this.a = byteBuffer;
            this.b = bufferInfo;
        }

        public ByteBuffer a() {
            return this.a;
        }

        public BufferInfo b() {
            return this.b;
        }
    }

    public synchronized void a(MediaFormat mediaFormat) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("addVideoTrack:");
        stringBuilder.append(mediaFormat);
        TXCLog.d("TXCMP4HWMuxer", stringBuilder.toString());
        this.d = mediaFormat;
        this.g |= 1;
        this.l.clear();
    }

    public synchronized void b(MediaFormat mediaFormat) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("addAudioTrack:");
        stringBuilder.append(mediaFormat);
        TXCLog.d("TXCMP4HWMuxer", stringBuilder.toString());
        this.e = mediaFormat;
        this.g |= 2;
        this.m.clear();
    }

    /* JADX WARNING: Missing block: B:13:0x0011, code skipped:
            return r1;
     */
    public synchronized boolean a() {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.f;	 Catch:{ all -> 0x0012 }
        r1 = 1;
        r0 = r0 & r1;
        if (r0 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r2);
        return r1;
    L_0x0009:
        r0 = r2.g;	 Catch:{ all -> 0x0012 }
        r0 = r0 & r1;
        if (r0 == 0) goto L_0x000f;
    L_0x000e:
        goto L_0x0010;
    L_0x000f:
        r1 = 0;
    L_0x0010:
        monitor-exit(r2);
        return r1;
    L_0x0012:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.videoediter.a.a.a():boolean");
    }

    /* JADX WARNING: Missing block: B:13:0x0013, code skipped:
            return r1;
     */
    public synchronized boolean b() {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.f;	 Catch:{ all -> 0x0014 }
        r0 = r0 & 2;
        r1 = 1;
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r2);
        return r1;
    L_0x000a:
        r0 = r2.g;	 Catch:{ all -> 0x0014 }
        r0 = r0 & 2;
        if (r0 == 0) goto L_0x0011;
    L_0x0010:
        goto L_0x0012;
    L_0x0011:
        r1 = 0;
    L_0x0012:
        monitor-exit(r2);
        return r1;
    L_0x0014:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.videoediter.a.a.b():boolean");
    }

    public synchronized int c() {
        StringBuilder stringBuilder;
        if (this.c != null) {
            if (!this.c.isEmpty()) {
                if (!a()) {
                    TXCLog.e("TXCMP4HWMuxer", "video track not set yet!");
                    return -2;
                } else if (!b()) {
                    TXCLog.e("TXCMP4HWMuxer", "audio track not set yet!");
                    return -3;
                } else if (this.b != null) {
                    TXCLog.w("TXCMP4HWMuxer", "start has been called. stop must be called before start");
                    return 0;
                } else {
                    TXCLog.d("TXCMP4HWMuxer", "start");
                    try {
                        this.b = new MediaMuxer(this.c, 0);
                        if (this.d != null) {
                            try {
                                this.i = this.b.addTrack(this.d);
                            } catch (IllegalArgumentException e) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("addVideoTrack IllegalArgumentException: ");
                                stringBuilder.append(e);
                                TXCLog.e("TXCMP4HWMuxer", stringBuilder.toString());
                                return -5;
                            } catch (IllegalStateException e2) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("addVideoTrack IllegalStateException: ");
                                stringBuilder.append(e2);
                                TXCLog.e("TXCMP4HWMuxer", stringBuilder.toString());
                                return -6;
                            }
                        }
                        if (this.e != null) {
                            try {
                                this.h = this.b.addTrack(this.e);
                            } catch (IllegalArgumentException e3) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("addAudioTrack IllegalArgumentException: ");
                                stringBuilder.append(e3);
                                TXCLog.e("TXCMP4HWMuxer", stringBuilder.toString());
                                return -7;
                            } catch (IllegalStateException e22) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("addAudioTrack IllegalStateException: ");
                                stringBuilder.append(e22);
                                TXCLog.e("TXCMP4HWMuxer", stringBuilder.toString());
                                return -8;
                            }
                        }
                        this.b.start();
                        this.n = -1;
                        this.j = true;
                        this.k = false;
                        this.o = -1;
                        this.p = -1;
                        return 0;
                    } catch (IOException e4) {
                        e4.printStackTrace();
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("create MediaMuxer exception:");
                        stringBuilder.append(e4);
                        TXCLog.e("TXCMP4HWMuxer", stringBuilder.toString());
                        return -4;
                    }
                }
            }
        }
        TXCLog.e("TXCMP4HWMuxer", "target path not set yet!");
        return -1;
    }

    public synchronized int d() {
        if (this.b != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("stop. start flag = ");
            stringBuilder.append(this.j);
            stringBuilder.append(", video key frame set = ");
            stringBuilder.append(this.k);
            TXCLog.d("TXCMP4HWMuxer", stringBuilder.toString());
            try {
                if (this.j && this.k) {
                    this.b.stop();
                }
                this.b.release();
                this.j = false;
                this.b = null;
                this.g = 0;
                this.k = false;
                this.l.clear();
                this.m.clear();
                this.d = null;
                this.e = null;
                this.o = -1;
                this.p = -1;
            } catch (Exception e) {
                try {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("muxer stop/release exception: ");
                    stringBuilder2.append(e);
                    TXCLog.e("TXCMP4HWMuxer", stringBuilder2.toString());
                } finally {
                    this.j = false;
                    this.b = null;
                    this.g = 0;
                    this.k = false;
                    this.l.clear();
                    this.m.clear();
                    this.d = null;
                    this.e = null;
                    this.o = -1;
                    this.p = -1;
                }
                return -1;
            }
        }
        return 0;
    }

    public synchronized void a(String str) {
        this.c = str;
    }

    public synchronized void a(ByteBuffer byteBuffer, BufferInfo bufferInfo) {
        if (this.b == null) {
            a(true, byteBuffer, bufferInfo);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("cache frame before muexer ready. ptsUs: ");
            stringBuilder.append(bufferInfo.presentationTimeUs);
            TXCLog.w("TXCMP4HWMuxer", stringBuilder.toString());
            return;
        }
        if (this.n < 0) {
            this.n = e();
            f();
        }
        c(byteBuffer, bufferInfo);
    }

    public synchronized void b(ByteBuffer byteBuffer, BufferInfo bufferInfo) {
        if (this.b != null) {
            if (this.n >= 0) {
                d(byteBuffer, bufferInfo);
                return;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("cache sample before muexer ready. ptsUs: ");
        stringBuilder.append(bufferInfo.presentationTimeUs);
        TXCLog.w("TXCMP4HWMuxer", stringBuilder.toString());
        a(false, byteBuffer, bufferInfo);
    }

    private void c(ByteBuffer byteBuffer, BufferInfo bufferInfo) {
        long j = bufferInfo.presentationTimeUs - this.n;
        StringBuilder stringBuilder;
        if (j < 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("drop frame. first frame offset timeus = ");
            stringBuilder.append(this.n);
            stringBuilder.append(", current timeus = ");
            stringBuilder.append(bufferInfo.presentationTimeUs);
            TXCLog.e("TXCMP4HWMuxer", stringBuilder.toString());
        } else if (j < this.o) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("drop frame. current frame's pts(");
            stringBuilder2.append(j);
            stringBuilder2.append(") must larger than pre frame's pts(");
            stringBuilder2.append(this.o);
            stringBuilder2.append(")");
            TXCLog.e("TXCMP4HWMuxer", stringBuilder2.toString());
        } else {
            this.o = j;
            bufferInfo.presentationTimeUs = j;
            try {
                byteBuffer.position(bufferInfo.offset);
                byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
                this.b.writeSampleData(this.i, byteBuffer, bufferInfo);
                if ((bufferInfo.flags & 1) != 0) {
                    this.k = true;
                }
            } catch (IllegalStateException e) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("write frame IllegalStateException: ");
                stringBuilder.append(e);
                TXCLog.e("TXCMP4HWMuxer", stringBuilder.toString());
            }
        }
    }

    private void d(ByteBuffer byteBuffer, BufferInfo bufferInfo) {
        long j = bufferInfo.presentationTimeUs - this.n;
        StringBuilder stringBuilder;
        if (this.n < 0 || j < 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("drop sample. first frame offset timeus = ");
            stringBuilder.append(this.n);
            stringBuilder.append(", current sample timeus = ");
            stringBuilder.append(bufferInfo.presentationTimeUs);
            TXCLog.w("TXCMP4HWMuxer", stringBuilder.toString());
        } else if (j < this.p) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("drop sample. current sample's pts pts(");
            stringBuilder2.append(j);
            stringBuilder2.append(") must larger than pre frame's pts(");
            stringBuilder2.append(this.p);
            stringBuilder2.append(")");
            TXCLog.e("TXCMP4HWMuxer", stringBuilder2.toString());
        } else {
            this.p = j;
            bufferInfo.presentationTimeUs = j;
            try {
                this.b.writeSampleData(this.h, byteBuffer, bufferInfo);
            } catch (IllegalStateException e) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("write sample IllegalStateException: ");
                stringBuilder.append(e);
                TXCLog.e("TXCMP4HWMuxer", stringBuilder.toString());
            }
        }
    }

    private void a(boolean z, ByteBuffer byteBuffer, BufferInfo bufferInfo) {
        if (byteBuffer != null && bufferInfo != null) {
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(byteBuffer.capacity());
            byteBuffer.rewind();
            if (bufferInfo.size > 0) {
                byteBuffer.position(bufferInfo.offset);
                byteBuffer.limit(bufferInfo.size);
            }
            allocateDirect.rewind();
            allocateDirect.put(byteBuffer);
            BufferInfo bufferInfo2 = new BufferInfo();
            bufferInfo2.set(bufferInfo.offset, bufferInfo.size, bufferInfo.presentationTimeUs, bufferInfo.flags);
            a aVar = new a(allocateDirect, bufferInfo2);
            if (z) {
                if (this.l.size() < 200) {
                    this.l.add(aVar);
                } else {
                    TXCLog.e("TXCMP4HWMuxer", "drop video frame. video cache size is larger than 200");
                }
            } else if (this.m.size() < IjkMediaCodecInfo.RANK_SECURE) {
                this.m.add(aVar);
            } else {
                TXCLog.e("TXCMP4HWMuxer", "drop audio frame. audio cache size is larger than 300");
            }
        }
    }

    private long e() {
        long j = this.l.size() > 0 ? ((a) this.l.peek()).b().presentationTimeUs : 0;
        if (this.m.size() <= 0) {
            return j;
        }
        a aVar = (a) this.m.peek();
        if (aVar == null || aVar.b() == null) {
            return j;
        }
        long j2 = ((a) this.m.peek()).b().presentationTimeUs;
        return j > j2 ? j2 : j;
    }

    private void f() {
        a aVar;
        while (this.l.size() > 0) {
            aVar = (a) this.l.poll();
            c(aVar.a(), aVar.b());
        }
        while (this.m.size() > 0) {
            aVar = (a) this.m.poll();
            d(aVar.a(), aVar.b());
        }
    }
}
