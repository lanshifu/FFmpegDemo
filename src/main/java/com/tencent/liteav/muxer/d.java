package com.tencent.liteav.muxer;

import android.annotation.TargetApi;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.text.TextUtils;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.muxer.jni.TXSWMuxerJNI;
import com.tencent.liteav.muxer.jni.TXSWMuxerJNI.AVOptions;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;

@TargetApi(18)
/* compiled from: TXCMP4SWMuxer */
public class d implements a {
    public static float a = 0.5f;
    public static float b = 0.8f;
    public static float c = 1.25f;
    public static float d = 2.0f;
    private int e = 2;
    private TXSWMuxerJNI f;
    private String g = null;
    private MediaFormat h = null;
    private MediaFormat i = null;
    private int j = 0;
    private int k = 0;
    private boolean l = false;
    private boolean m = false;
    private ConcurrentLinkedQueue<a> n = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<a> o = new ConcurrentLinkedQueue();
    private long p = -1;
    private long q = -1;
    private long r = -1;

    /* compiled from: TXCMP4SWMuxer */
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

    public void a(int i) {
        this.e = i;
    }

    public synchronized void a(MediaFormat mediaFormat) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("addVideoTrack:");
        stringBuilder.append(mediaFormat);
        TXCLog.d("TXCMP4SWMuxer", stringBuilder.toString());
        this.h = mediaFormat;
        this.n.clear();
    }

    public synchronized void b(MediaFormat mediaFormat) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("addAudioTrack:");
        stringBuilder.append(mediaFormat);
        TXCLog.d("TXCMP4SWMuxer", stringBuilder.toString());
        this.i = mediaFormat;
        this.o.clear();
    }

    public synchronized boolean c() {
        if (this.h != null) {
            return true;
        }
        return false;
    }

    public synchronized boolean d() {
        if (this.i != null) {
            return true;
        }
        return false;
    }

    private ByteBuffer e() {
        ByteBuffer byteBuffer = this.i.getByteBuffer("csd-0");
        byteBuffer.position(0);
        return byteBuffer;
    }

    private ByteBuffer f() {
        return this.h.getByteBuffer("csd-0");
    }

    private ByteBuffer g() {
        return this.h.getByteBuffer("csd-1");
    }

    public synchronized int a() {
        if (this.g != null) {
            if (!this.g.isEmpty()) {
                if (!c()) {
                    TXCLog.e("TXCMP4SWMuxer", "video track not set yet!");
                    return -2;
                } else if (this.f != null) {
                    TXCLog.w("TXCMP4SWMuxer", "start has been called. stop must be called before start");
                    return 0;
                } else {
                    int integer;
                    TXCLog.d("TXCMP4SWMuxer", "start");
                    this.f = new TXSWMuxerJNI();
                    AVOptions aVOptions = new AVOptions();
                    if (this.h != null) {
                        integer = this.h.getInteger("width");
                        aVOptions.videoHeight = this.h.getInteger("height");
                        aVOptions.videoWidth = integer;
                        aVOptions.videoGOP = this.h.containsKey("i-frame-interval") ? this.h.getInteger("i-frame-interval") : 3;
                    }
                    if (this.i != null) {
                        integer = this.i.getInteger("channel-count");
                        int integer2 = this.i.getInteger("sample-rate");
                        aVOptions.audioChannels = integer;
                        aVOptions.audioSampleRate = integer2;
                    }
                    ByteBuffer f = f();
                    ByteBuffer g = g();
                    ByteBuffer byteBuffer = null;
                    if (this.i != null) {
                        byteBuffer = e();
                    }
                    if (f != null) {
                        if (g != null) {
                            if (this.i == null || byteBuffer != null) {
                                this.f.a(f, f.capacity(), g, g.capacity());
                                if (this.i != null) {
                                    this.f.a(byteBuffer, byteBuffer.capacity());
                                }
                                this.f.a(aVOptions);
                                this.f.a(this.g);
                                this.f.a();
                                this.p = -1;
                                this.l = true;
                                this.m = false;
                                this.q = -1;
                                this.r = -1;
                                return 0;
                            }
                            TXCLog.e("TXCMP4SWMuxer", "audio format contains error csd!");
                            return -3;
                        }
                    }
                    TXCLog.e("TXCMP4SWMuxer", "video format contains error csd!");
                    return -3;
                }
            }
        }
        TXCLog.e("TXCMP4SWMuxer", "target path not set yet!");
        return -1;
    }

    public synchronized int b() {
        if (this.f != null) {
            i();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("stop. start flag = ");
            stringBuilder.append(this.l);
            stringBuilder.append(", video key frame set = ");
            stringBuilder.append(this.m);
            TXCLog.d("TXCMP4SWMuxer", stringBuilder.toString());
            try {
                if (this.l && this.m) {
                    this.f.b();
                }
                this.f.c();
                this.f = null;
                this.l = false;
                this.f = null;
                this.m = false;
                this.n.clear();
                this.o.clear();
                this.h = null;
                this.i = null;
                this.q = -1;
                this.r = -1;
            } catch (Exception e) {
                try {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("muxer stop/release exception: ");
                    stringBuilder2.append(e);
                    TXCLog.e("TXCMP4SWMuxer", stringBuilder2.toString());
                } finally {
                    this.l = false;
                    this.f = null;
                    this.m = false;
                    this.n.clear();
                    this.o.clear();
                    this.h = null;
                    this.i = null;
                    this.q = -1;
                    this.r = -1;
                }
                return -1;
            }
        }
        return 0;
    }

    public synchronized void a(String str) {
        this.g = str;
        if (!TextUtils.isEmpty(this.g)) {
            File file = new File(this.g);
            if (file.exists()) {
                file.delete();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return;
    }

    public synchronized void b(byte[] bArr, int i, int i2, long j, int i3) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i2);
        allocateDirect.put(bArr, i, i2);
        BufferInfo bufferInfo = new BufferInfo();
        bufferInfo.presentationTimeUs = j;
        bufferInfo.offset = 0;
        bufferInfo.size = i2;
        bufferInfo.flags = i3;
        b(allocateDirect, bufferInfo);
    }

    public synchronized void a(byte[] bArr, int i, int i2, long j, int i3) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i2);
        allocateDirect.put(bArr, i, i2);
        BufferInfo bufferInfo = new BufferInfo();
        bufferInfo.presentationTimeUs = j;
        bufferInfo.offset = 0;
        bufferInfo.size = i2;
        bufferInfo.flags = i3;
        a(allocateDirect, bufferInfo);
    }

    /* JADX WARNING: Missing block: B:13:0x0059, code skipped:
            return;
     */
    public synchronized void b(java.nio.ByteBuffer r7, android.media.MediaCodec.BufferInfo r8) {
        /*
        r6 = this;
        monitor-enter(r6);
        r0 = r6.f;	 Catch:{ all -> 0x005a }
        r1 = 1;
        if (r0 != 0) goto L_0x0023;
    L_0x0006:
        r6.a(r1, r7, r8);	 Catch:{ all -> 0x005a }
        r7 = "TXCMP4SWMuxer";
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x005a }
        r0.<init>();	 Catch:{ all -> 0x005a }
        r1 = "cache frame before muexer ready. ptsUs: ";
        r0.append(r1);	 Catch:{ all -> 0x005a }
        r1 = r8.presentationTimeUs;	 Catch:{ all -> 0x005a }
        r0.append(r1);	 Catch:{ all -> 0x005a }
        r8 = r0.toString();	 Catch:{ all -> 0x005a }
        com.tencent.liteav.basic.log.TXCLog.w(r7, r8);	 Catch:{ all -> 0x005a }
        monitor-exit(r6);
        return;
    L_0x0023:
        r2 = r6.p;	 Catch:{ all -> 0x005a }
        r4 = 0;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 >= 0) goto L_0x0050;
    L_0x002b:
        r6.a(r1, r7, r8);	 Catch:{ all -> 0x005a }
        r7 = r6.h();	 Catch:{ all -> 0x005a }
        r6.p = r7;	 Catch:{ all -> 0x005a }
        r7 = "TXCMP4SWMuxer";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x005a }
        r8.<init>();	 Catch:{ all -> 0x005a }
        r0 = "first frame offset = ";
        r8.append(r0);	 Catch:{ all -> 0x005a }
        r0 = r6.p;	 Catch:{ all -> 0x005a }
        r8.append(r0);	 Catch:{ all -> 0x005a }
        r8 = r8.toString();	 Catch:{ all -> 0x005a }
        com.tencent.liteav.basic.log.TXCLog.d(r7, r8);	 Catch:{ all -> 0x005a }
        r6.j();	 Catch:{ all -> 0x005a }
        goto L_0x0058;
    L_0x0050:
        r0 = r8.presentationTimeUs;	 Catch:{ all -> 0x005a }
        r6.a(r0);	 Catch:{ all -> 0x005a }
        r6.c(r7, r8);	 Catch:{ all -> 0x005a }
    L_0x0058:
        monitor-exit(r6);
        return;
    L_0x005a:
        r7 = move-exception;
        monitor-exit(r6);
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.muxer.d.b(java.nio.ByteBuffer, android.media.MediaCodec$BufferInfo):void");
    }

    public synchronized void a(ByteBuffer byteBuffer, BufferInfo bufferInfo) {
        a(false, byteBuffer, bufferInfo);
    }

    private void c(ByteBuffer byteBuffer, BufferInfo bufferInfo) {
        StringBuilder stringBuilder;
        long j = bufferInfo.presentationTimeUs - this.p;
        if (j < 0) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("pts error! first frame offset timeus = ");
            stringBuilder2.append(this.p);
            stringBuilder2.append(", current timeus = ");
            stringBuilder2.append(bufferInfo.presentationTimeUs);
            TXCLog.e("TXCMP4SWMuxer", stringBuilder2.toString());
            j = this.q > 0 ? this.q : 0;
        }
        if (j < this.q) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("video is not in chronological order. current frame's pts(");
            stringBuilder3.append(j);
            stringBuilder3.append(") smaller than pre frame's pts(");
            stringBuilder3.append(this.q);
            stringBuilder3.append(")");
            TXCLog.w("TXCMP4SWMuxer", stringBuilder3.toString());
        } else {
            this.q = j;
        }
        if (this.e != 2) {
            if (this.e == 3) {
                j = (long) (((float) j) * b);
            } else if (this.e == 4) {
                j = (long) (((float) j) * a);
            } else if (this.e == 1) {
                j = (long) (((float) j) * c);
            } else if (this.e == 0) {
                j = (long) (((float) j) * d);
            }
        }
        bufferInfo.presentationTimeUs = j;
        try {
            byteBuffer.position(bufferInfo.offset);
            byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
            this.f.a(byteBuffer, 1, bufferInfo.offset, bufferInfo.size, bufferInfo.flags == 1 ? 1 : 0, bufferInfo.presentationTimeUs);
            if ((bufferInfo.flags & 1) != 0) {
                this.m = true;
            }
        } catch (IllegalStateException e) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("write frame IllegalStateException: ");
            stringBuilder.append(e);
            TXCLog.e("TXCMP4SWMuxer", stringBuilder.toString());
        } catch (IllegalArgumentException e2) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("write frame IllegalArgumentException: ");
            stringBuilder.append(e2);
            TXCLog.e("TXCMP4SWMuxer", stringBuilder.toString());
        }
    }

    private void d(ByteBuffer byteBuffer, BufferInfo bufferInfo) {
        long j = bufferInfo.presentationTimeUs - this.p;
        StringBuilder stringBuilder;
        if (this.p < 0 || j < 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("drop sample. first frame offset timeus = ");
            stringBuilder.append(this.p);
            stringBuilder.append(", current sample timeus = ");
            stringBuilder.append(bufferInfo.presentationTimeUs);
            TXCLog.w("TXCMP4SWMuxer", stringBuilder.toString());
            return;
        }
        if (j < this.r) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("audio is not in chronological order. current audio's pts pts(");
            stringBuilder2.append(j);
            stringBuilder2.append(") must larger than pre audio's pts(");
            stringBuilder2.append(this.r);
            stringBuilder2.append(")");
            TXCLog.e("TXCMP4SWMuxer", stringBuilder2.toString());
            j = this.r + 1;
        } else {
            this.r = j;
        }
        if (this.e != 2) {
            if (this.e == 3) {
                j = (long) (((float) j) * b);
            } else if (this.e == 4) {
                j = (long) (((float) j) * a);
            } else if (this.e == 1) {
                j = (long) (((float) j) * c);
            } else if (this.e == 0) {
                j = (long) (((float) j) * d);
            }
        }
        bufferInfo.presentationTimeUs = j;
        try {
            byteBuffer.position(bufferInfo.offset);
            byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
            this.f.a(byteBuffer, 0, bufferInfo.offset, bufferInfo.size, bufferInfo.flags, bufferInfo.presentationTimeUs);
        } catch (IllegalStateException e) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("write sample IllegalStateException: ");
            stringBuilder.append(e);
            TXCLog.e("TXCMP4SWMuxer", stringBuilder.toString());
        } catch (IllegalArgumentException e2) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("write sample IllegalArgumentException: ");
            stringBuilder.append(e2);
            TXCLog.e("TXCMP4SWMuxer", stringBuilder.toString());
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
            if (!z) {
                this.o.add(aVar);
            } else if (this.n.size() < 200) {
                this.n.add(aVar);
            } else {
                TXCLog.e("TXCMP4SWMuxer", "drop video frame. video cache size is larger than 200");
            }
        }
    }

    private long h() {
        long j = this.n.size() > 0 ? ((a) this.n.peek()).b().presentationTimeUs : 0;
        if (this.o.size() <= 0) {
            return j;
        }
        a aVar = (a) this.o.peek();
        if (aVar == null || aVar.b() == null) {
            return j;
        }
        long j2 = ((a) this.o.peek()).b().presentationTimeUs;
        return j > j2 ? j2 : j;
    }

    private void i() {
        a aVar;
        while (this.n.size() > 0) {
            aVar = (a) this.n.poll();
            c(aVar.a(), aVar.b());
        }
        while (this.o.size() > 0) {
            aVar = (a) this.o.poll();
            d(aVar.a(), aVar.b());
        }
    }

    private void j() {
        while (this.n.size() > 0) {
            a aVar = (a) this.n.poll();
            a(aVar.b().presentationTimeUs);
            c(aVar.a(), aVar.b());
        }
    }

    private void a(long j) {
        while (this.o.size() > 0) {
            if (((a) this.o.peek()).b() == null) {
                TXCLog.e("TXCMP4SWMuxer", "flushAudioCache, bufferInfo is null");
                this.o.remove();
            } else if (((a) this.o.peek()).b().presentationTimeUs < j) {
                a aVar = (a) this.o.poll();
                d(aVar.a(), aVar.b());
            } else {
                return;
            }
        }
    }
}
