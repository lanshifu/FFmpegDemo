package com.tencent.liteav.videodecoder;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.view.Surface;
import com.tencent.liteav.basic.d.a;
import com.tencent.liteav.basic.g.b;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.rtmp.TXLiveConstants;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/* compiled from: TXCVideoMediaCodecDecoder */
public class c implements a {
    private BufferInfo a = new BufferInfo();
    private MediaCodec b = null;
    private String c = "video/avc";
    private int d = 540;
    private int e = 960;
    private long f = 0;
    private long g = 0;
    private boolean h = true;
    private boolean i = false;
    private boolean j = false;
    private Surface k = null;
    private int l = 0;
    private ArrayList<b> m = new ArrayList();
    private d n;
    private WeakReference<a> o;

    public void setListener(d dVar) {
        this.n = dVar;
    }

    public void setNotifyListener(WeakReference<a> weakReference) {
        this.o = weakReference;
    }

    public int config(Surface surface) {
        if (surface == null) {
            return -1;
        }
        this.k = surface;
        return 0;
    }

    public void decode(b bVar) {
        boolean z = true;
        if (bVar.codecId != 1) {
            z = false;
        }
        a(z);
        this.m.add(bVar);
        while (!this.m.isEmpty()) {
            int size = this.m.size();
            b();
            if (size == this.m.size()) {
                return;
            }
        }
    }

    public int start(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, boolean z, boolean z2) {
        return a(byteBuffer, byteBuffer2, z2);
    }

    public void stop() {
        a();
    }

    public boolean isHevc() {
        return this.j;
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a6 A:{SYNTHETIC, Splitter:B:46:0x00a6} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a6 A:{SYNTHETIC, Splitter:B:46:0x00a6} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a6 A:{SYNTHETIC, Splitter:B:46:0x00a6} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a6 A:{SYNTHETIC, Splitter:B:46:0x00a6} */
    private int a(java.nio.ByteBuffer r7, java.nio.ByteBuffer r8, boolean r9) {
        /*
        r6 = this;
        r0 = 1;
        r1 = 0;
        r2 = 0;
        r3 = -1;
        r4 = r6.b;	 Catch:{ Exception -> 0x00a1 }
        if (r4 != 0) goto L_0x007e;
    L_0x0008:
        r4 = r6.k;	 Catch:{ Exception -> 0x00a1 }
        if (r4 != 0) goto L_0x000e;
    L_0x000c:
        goto L_0x007e;
    L_0x000e:
        r6.j = r9;	 Catch:{ Exception -> 0x00a1 }
        r9 = r6.j;	 Catch:{ Exception -> 0x00a1 }
        if (r9 == 0) goto L_0x0019;
    L_0x0014:
        r9 = "video/hevc";
        r6.c = r9;	 Catch:{ Exception -> 0x00a1 }
        goto L_0x001d;
    L_0x0019:
        r9 = "video/avc";
        r6.c = r9;	 Catch:{ Exception -> 0x00a1 }
    L_0x001d:
        r9 = r6.c;	 Catch:{ Exception -> 0x00a1 }
        r4 = r6.d;	 Catch:{ Exception -> 0x00a1 }
        r5 = r6.e;	 Catch:{ Exception -> 0x00a1 }
        r9 = android.media.MediaFormat.createVideoFormat(r9, r4, r5);	 Catch:{ Exception -> 0x00a1 }
        if (r7 == 0) goto L_0x002e;
    L_0x0029:
        r4 = "csd-0";
        r9.setByteBuffer(r4, r7);	 Catch:{ Exception -> 0x00a1 }
    L_0x002e:
        if (r8 == 0) goto L_0x0035;
    L_0x0030:
        r7 = "csd-1";
        r9.setByteBuffer(r7, r8);	 Catch:{ Exception -> 0x00a1 }
    L_0x0035:
        r7 = r6.c;	 Catch:{ Exception -> 0x00a1 }
        r7 = android.media.MediaCodec.createDecoderByType(r7);	 Catch:{ Exception -> 0x00a1 }
        r6.b = r7;	 Catch:{ Exception -> 0x00a1 }
        r7 = r6.b;	 Catch:{ Exception -> 0x007b }
        r8 = r6.k;	 Catch:{ Exception -> 0x007b }
        r7.configure(r9, r8, r1, r2);	 Catch:{ Exception -> 0x007b }
        r7 = 2;
        r8 = r6.b;	 Catch:{ Exception -> 0x0077 }
        r8.setVideoScalingMode(r0);	 Catch:{ Exception -> 0x0077 }
        r0 = 3;
        r7 = r6.b;	 Catch:{ Exception -> 0x0074 }
        r7.start();	 Catch:{ Exception -> 0x0074 }
        r0 = 4;
        r7 = "MediaCodecDecoder";
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0071 }
        r8.<init>();	 Catch:{ Exception -> 0x0071 }
        r9 = "decode: start decoder success, is hevc: ";
        r8.append(r9);	 Catch:{ Exception -> 0x0071 }
        r9 = r6.j;	 Catch:{ Exception -> 0x0071 }
        r8.append(r9);	 Catch:{ Exception -> 0x0071 }
        r8 = r8.toString();	 Catch:{ Exception -> 0x0071 }
        com.tencent.liteav.basic.log.TXCLog.w(r7, r8);	 Catch:{ Exception -> 0x0071 }
        r6.l = r2;	 Catch:{ Exception -> 0x006d }
        goto L_0x0102;
    L_0x006d:
        r7 = move-exception;
        r2 = 4;
        r3 = 0;
        goto L_0x00a2;
    L_0x0071:
        r7 = move-exception;
        r2 = 4;
        goto L_0x00a2;
    L_0x0074:
        r7 = move-exception;
        r2 = 3;
        goto L_0x00a2;
    L_0x0077:
        r8 = move-exception;
        r7 = r8;
        r2 = 2;
        goto L_0x00a2;
    L_0x007b:
        r7 = move-exception;
        r2 = 1;
        goto L_0x00a2;
    L_0x007e:
        r7 = "MediaCodecDecoder";
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a1 }
        r8.<init>();	 Catch:{ Exception -> 0x00a1 }
        r9 = "decode: init decoder error, can not init for decoder=";
        r8.append(r9);	 Catch:{ Exception -> 0x00a1 }
        r9 = r6.b;	 Catch:{ Exception -> 0x00a1 }
        r8.append(r9);	 Catch:{ Exception -> 0x00a1 }
        r9 = ",surface=";
        r8.append(r9);	 Catch:{ Exception -> 0x00a1 }
        r9 = r6.k;	 Catch:{ Exception -> 0x00a1 }
        r8.append(r9);	 Catch:{ Exception -> 0x00a1 }
        r8 = r8.toString();	 Catch:{ Exception -> 0x00a1 }
        com.tencent.liteav.basic.log.TXCLog.e(r7, r8);	 Catch:{ Exception -> 0x00a1 }
        return r3;
    L_0x00a1:
        r7 = move-exception;
    L_0x00a2:
        r8 = r6.b;
        if (r8 == 0) goto L_0x00d9;
    L_0x00a6:
        r8 = r6.b;	 Catch:{ Exception -> 0x00b7 }
        r8.release();	 Catch:{ Exception -> 0x00b7 }
        r8 = "MediaCodecDecoder";
        r9 = "decode: , decoder release success";
        com.tencent.liteav.basic.log.TXCLog.w(r8, r9);	 Catch:{ Exception -> 0x00b7 }
    L_0x00b2:
        r6.b = r1;
        goto L_0x00d9;
    L_0x00b5:
        r7 = move-exception;
        goto L_0x00d6;
    L_0x00b7:
        r8 = move-exception;
        r9 = "MediaCodecDecoder";
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b5 }
        r0.<init>();	 Catch:{ all -> 0x00b5 }
        r4 = "decode: , decoder release exception: ";
        r0.append(r4);	 Catch:{ all -> 0x00b5 }
        r4 = r7.toString();	 Catch:{ all -> 0x00b5 }
        r0.append(r4);	 Catch:{ all -> 0x00b5 }
        r0 = r0.toString();	 Catch:{ all -> 0x00b5 }
        com.tencent.liteav.basic.log.TXCLog.e(r9, r0);	 Catch:{ all -> 0x00b5 }
        r8.printStackTrace();	 Catch:{ all -> 0x00b5 }
        goto L_0x00b2;
    L_0x00d6:
        r6.b = r1;
        throw r7;
    L_0x00d9:
        r8 = "MediaCodecDecoder";
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r0 = "decode: init decoder ";
        r9.append(r0);
        r9.append(r2);
        r0 = " step exception: ";
        r9.append(r0);
        r0 = r7.toString();
        r9.append(r0);
        r9 = r9.toString();
        com.tencent.liteav.basic.log.TXCLog.e(r8, r9);
        r7.printStackTrace();
        r6.f();
        r2 = r3;
    L_0x0102:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.videodecoder.c.a(java.nio.ByteBuffer, java.nio.ByteBuffer, boolean):int");
    }

    private void a() {
        StringBuilder stringBuilder;
        if (this.b != null) {
            try {
                this.b.stop();
                TXCLog.w("MediaCodecDecoder", "decode: stop decoder sucess");
                try {
                    this.m.clear();
                    this.f = 0;
                    this.h = true;
                } catch (Exception e) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("decode: release decoder exception: ");
                    stringBuilder.append(e.toString());
                    TXCLog.e("MediaCodecDecoder", stringBuilder.toString());
                    e.printStackTrace();
                } catch (Throwable th) {
                    this.b = null;
                }
            } catch (Exception e2) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("decode: stop decoder Exception: ");
                stringBuilder.append(e2.toString());
                TXCLog.e("MediaCodecDecoder", stringBuilder.toString());
                e2.printStackTrace();
                try {
                } catch (Exception e22) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("decode: release decoder exception: ");
                    stringBuilder.append(e22.toString());
                    TXCLog.e("MediaCodecDecoder", stringBuilder.toString());
                    e22.printStackTrace();
                } catch (Throwable th2) {
                    this.b = null;
                }
            } finally {
                try {
                    this.b.release();
                    TXCLog.w("MediaCodecDecoder", "decode: release decoder sucess");
                } catch (Exception e3) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("decode: release decoder exception: ");
                    stringBuilder2.append(e3.toString());
                    TXCLog.e("MediaCodecDecoder", stringBuilder2.toString());
                    e3.printStackTrace();
                } catch (Throwable th3) {
                    this.b = null;
                }
                this.b = null;
            }
        }
    }

    @TargetApi(16)
    private void b() {
        if (this.b == null) {
            TXCLog.e("MediaCodecDecoder", "null decoder");
            return;
        }
        b bVar = (b) this.m.get(0);
        if (bVar == null || bVar.nalData.length == 0) {
            TXCLog.e("MediaCodecDecoder", "decode: empty buffer");
            this.m.remove(0);
            return;
        }
        ByteBuffer[] inputBuffers = this.b.getInputBuffers();
        if (inputBuffers == null || inputBuffers.length == 0) {
            TXCLog.e("MediaCodecDecoder", "decode: getInputBuffers failed");
            return;
        }
        int dequeueInputBuffer;
        int dequeueOutputBuffer;
        try {
            dequeueInputBuffer = this.b.dequeueInputBuffer(10000);
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("decode: dequeueInputBuffer Exception!! ");
            stringBuilder.append(e);
            TXCLog.e("MediaCodecDecoder", stringBuilder.toString());
            dequeueInputBuffer = -10000;
        }
        if (dequeueInputBuffer >= 0) {
            inputBuffers[dequeueInputBuffer].put(bVar.nalData);
            this.b.queueInputBuffer(dequeueInputBuffer, 0, bVar.nalData.length, bVar.pts, 0);
            this.m.remove(0);
            if (this.f == 0) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("decode: input buffer available, dequeueInputBuffer index: ");
                stringBuilder2.append(dequeueInputBuffer);
                TXCLog.w("MediaCodecDecoder", stringBuilder2.toString());
            }
        } else {
            TXCLog.w("MediaCodecDecoder", "decode: input buffer not available, dequeueInputBuffer failed");
        }
        try {
            dequeueOutputBuffer = this.b.dequeueOutputBuffer(this.a, 10000);
        } catch (Exception e2) {
            g();
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("decode: dequeueOutputBuffer exception!!");
            stringBuilder3.append(e2);
            TXCLog.e("MediaCodecDecoder", stringBuilder3.toString());
            dequeueOutputBuffer = -10000;
        }
        if (dequeueOutputBuffer >= 0) {
            a(dequeueOutputBuffer, this.a.presentationTimeUs, this.a.presentationTimeUs);
            this.l = 0;
        } else if (dequeueOutputBuffer == -1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e3) {
                e3.printStackTrace();
            }
            TXCLog.d("MediaCodecDecoder", "decode: no output from decoder available when timeout");
            g();
        } else if (dequeueOutputBuffer == -3) {
            TXCLog.d("MediaCodecDecoder", "decode: output buffers changed");
        } else if (dequeueOutputBuffer == -2) {
            c();
        } else {
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append("decode: unexpected result from decoder.dequeueOutputBuffer: ");
            stringBuilder4.append(dequeueOutputBuffer);
            TXCLog.e("MediaCodecDecoder", stringBuilder4.toString());
        }
    }

    private void a(int i, long j, long j2) {
        this.b.releaseOutputBuffer(i, true);
        if ((this.a.flags & 4) != 0) {
            TXCLog.d("MediaCodecDecoder", "output EOS");
        }
        try {
            if (this.n != null) {
                this.n.a(null, this.d, this.e, j, j2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        d();
    }

    private void c() {
        MediaFormat outputFormat = this.b.getOutputFormat();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("decode output format changed: ");
        stringBuilder.append(outputFormat);
        TXCLog.d("MediaCodecDecoder", stringBuilder.toString());
        int abs = Math.abs(outputFormat.getInteger("crop-right") - outputFormat.getInteger("crop-left")) + 1;
        int abs2 = Math.abs(outputFormat.getInteger("crop-bottom") - outputFormat.getInteger("crop-top")) + 1;
        int integer = outputFormat.getInteger("width");
        int integer2 = outputFormat.getInteger("height");
        abs = Math.min(abs, integer);
        integer2 = Math.min(abs2, integer2);
        if (abs != this.d || integer2 != this.e) {
            this.d = abs;
            this.e = integer2;
            try {
                if (this.n != null) {
                    this.n.a(this.d, this.e);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("decode: video size change to w:");
            stringBuilder2.append(abs);
            stringBuilder2.append(",h:");
            stringBuilder2.append(integer2);
            TXCLog.d("MediaCodecDecoder", stringBuilder2.toString());
        } else if (this.h) {
            this.h = false;
            if (this.n != null) {
                this.n.a(this.d, this.e);
            }
        }
    }

    private void d() {
        if (this.f == 0) {
            TXCLog.w("MediaCodecDecoder", "decode first frame sucess");
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f > 0 && currentTimeMillis > this.f + 1000 && currentTimeMillis > this.g + 2000 && this.g != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("frame interval[");
            stringBuilder.append(currentTimeMillis - this.f);
            stringBuilder.append("] > ");
            stringBuilder.append(1000);
            TXCLog.e("MediaCodecDecoder", stringBuilder.toString());
            this.g = currentTimeMillis;
        }
        if (this.g == 0) {
            this.g = currentTimeMillis;
        }
        this.f = currentTimeMillis;
    }

    private boolean e() {
        int i;
        StringBuilder stringBuilder;
        if (VERSION.SDK_INT >= 21) {
            for (MediaCodecInfo mediaCodecInfo : new MediaCodecList(1).getCodecInfos()) {
                for (String contains : mediaCodecInfo.getSupportedTypes()) {
                    if (contains.contains("video/hevc")) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("decode: video/hevc MediaCodecInfo: ");
                        stringBuilder.append(mediaCodecInfo.getName());
                        stringBuilder.append(",encoder:");
                        stringBuilder.append(mediaCodecInfo.isEncoder());
                        TXCLog.e("MediaCodecDecoder", stringBuilder.toString());
                        return true;
                    }
                }
            }
            return false;
        }
        if (VERSION.SDK_INT >= 16) {
            int codecCount = MediaCodecList.getCodecCount();
            for (i = 0; i < codecCount; i++) {
                MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
                for (String contains2 : codecInfoAt.getSupportedTypes()) {
                    if (contains2.contains("video/hevc")) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("video/hevc MediaCodecInfo: ");
                        stringBuilder.append(codecInfoAt.getName());
                        stringBuilder.append(",encoder:");
                        stringBuilder.append(codecInfoAt.isEncoder());
                        TXCLog.e("MediaCodecDecoder", stringBuilder.toString());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void f() {
        if (!this.i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("decode hw decode error, hevc: ");
            stringBuilder.append(this.j);
            TXCLog.w("MediaCodecDecoder", stringBuilder.toString());
            if (this.j) {
                com.tencent.liteav.basic.util.b.a(this.o, (int) TXLiveConstants.PLAY_ERR_HEVC_DECODE_FAIL, "h265解码失败");
            } else {
                com.tencent.liteav.basic.util.b.a(this.o, (int) TXLiveConstants.PLAY_WARNING_HW_ACCELERATION_FAIL, "硬解启动失败，采用软解");
            }
            this.i = true;
        }
    }

    private void g() {
        if (this.l >= 40) {
            f();
            this.l = 0;
            return;
        }
        this.l++;
    }

    private void a(boolean z) {
        if (this.j != z) {
            this.j = z;
            if (!this.i) {
                if (!this.j || e()) {
                    a();
                    a(null, null, this.j);
                } else {
                    a();
                    f();
                }
            }
        }
    }
}
