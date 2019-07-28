package com.tencent.liteav.videoencoder;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaCodecInfo.EncoderCapabilities;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.opengl.GLES20;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.Surface;
import com.tencent.liteav.basic.e.b;
import com.tencent.liteav.basic.e.g;
import com.tencent.liteav.basic.e.j;
import com.tencent.liteav.basic.e.k;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.c;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import com.yalantis.ucrop.view.CropImageView;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import javax.microedition.khronos.egl.EGL10;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: TXCHWVideoEncoder */
public class a extends c {
    private static final String a = "a";
    private boolean A;
    private boolean B;
    private ByteBuffer[] C;
    private byte[] D;
    private volatile long E;
    private long F;
    private long G;
    private int H;
    private int I;
    private boolean J;
    private int K;
    private int L;
    private int M;
    private int N;
    private int b;
    private long c;
    private long d;
    private long e;
    private long f;
    private int g;
    private boolean h;
    private boolean i;
    private long j;
    private long k;
    private long l;
    private long m;
    private long n;
    private boolean o;
    private long p;
    private long q;
    private MediaCodec r;
    private c s;
    private Runnable t;
    private Runnable u;
    private Runnable v;
    private ArrayDeque<Long> w;
    private Object x;
    private Surface y;
    private boolean z;

    private void d(int i) {
    }

    public a() {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = false;
        this.i = true;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.p = 0;
        this.q = 0;
        this.r = null;
        this.s = null;
        this.t = new Runnable() {
            public void run() {
                a.this.c();
            }
        };
        this.u = new Runnable() {
            public void run() {
                a.this.b(10);
            }
        };
        this.v = new Runnable() {
            public void run() {
                a.this.b(1);
            }
        };
        this.w = new ArrayDeque(10);
        this.y = null;
        this.z = true;
        this.A = true;
        this.B = false;
        this.C = null;
        this.D = null;
        this.E = 0;
        this.F = 0;
        this.G = 0;
        this.K = 0;
        this.L = 0;
        this.M = 0;
        this.N = -1;
        this.s = new c("HWVideoEncoder");
    }

    public int start(final TXSVideoEncoderParam tXSVideoEncoderParam) {
        super.start(tXSVideoEncoderParam);
        final boolean[] zArr = new boolean[1];
        if (VERSION.SDK_INT < 18) {
            zArr[0] = false;
        } else {
            synchronized (this) {
                this.s.a(new Runnable() {
                    public void run() {
                        if (a.this.mInit) {
                            a.this.c();
                        }
                        zArr[0] = a.this.a(tXSVideoEncoderParam);
                    }
                });
            }
        }
        if (!zArr[0]) {
            callDelegate(10000004);
        }
        if (zArr[0]) {
            return 0;
        }
        return 10000004;
    }

    public void stop() {
        this.A = true;
        synchronized (this) {
            this.s.a(new Runnable() {
                public void run() {
                    if (a.this.mInit) {
                        a.this.c();
                    }
                }
            });
        }
    }

    public void setFPS(final int i) {
        this.s.b(new Runnable() {
            public void run() {
                a.this.d(i);
            }
        });
    }

    public void setBitrate(final int i) {
        this.b = i;
        this.s.b(new Runnable() {
            public void run() {
                a.this.c(i);
            }
        });
    }

    public long getRealFPS() {
        return this.d;
    }

    public long getRealBitrate() {
        return this.c;
    }

    public long pushVideoFrame(int i, int i2, int i3, long j) {
        if (this.A) {
            return 10000004;
        }
        GLES20.glFinish();
        this.E = j;
        this.N = i;
        this.K++;
        if (this.J) {
            d();
        }
        this.s.b(this.u);
        return 0;
    }

    public long pushVideoFrameSync(int i, int i2, int i3, long j) {
        if (this.A) {
            return 10000004;
        }
        GLES20.glFinish();
        this.E = j;
        this.N = i;
        this.K++;
        if (this.J) {
            d();
        }
        this.s.a(this.v);
        return 0;
    }

    public void signalEOSAndFlush() {
        if (!this.A) {
            this.s.a(new Runnable() {
                public void run() {
                    if (a.this.r != null) {
                        try {
                            a.this.r.signalEndOfInputStream();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        while (a.this.a(10) >= 0) {
                        }
                        a.this.c();
                    }
                }
            });
        }
    }

    @TargetApi(16)
    private MediaFormat a(int i, int i2, int i3, int i4, int i5) {
        if (i == 0 || i2 == 0 || i3 == 0 || i4 == 0) {
            return null;
        }
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", i, i2);
        createVideoFormat.setInteger(IjkMediaMeta.IJKM_KEY_BITRATE, i3 * Filter.K);
        createVideoFormat.setInteger("frame-rate", i4);
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("i-frame-interval", i5);
        return createVideoFormat;
    }

    @TargetApi(16)
    private MediaFormat a(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        MediaFormat a = a(i, i2, i3, i4, i5);
        if (a == null) {
            return null;
        }
        if (VERSION.SDK_INT >= 21) {
            MediaCodecInfo a2 = a("video/avc");
            if (a2 == null) {
                return a;
            }
            CodecCapabilities capabilitiesForType = a2.getCapabilitiesForType("video/avc");
            EncoderCapabilities encoderCapabilities = capabilitiesForType.getEncoderCapabilities();
            if (encoderCapabilities.isBitrateModeSupported(i6)) {
                a.setInteger("bitrate-mode", i6);
            } else if (encoderCapabilities.isBitrateModeSupported(2)) {
                a.setInteger("bitrate-mode", 2);
            }
            a.setInteger("complexity", ((Integer) encoderCapabilities.getComplexityRange().clamp(Integer.valueOf(5))).intValue());
            if (VERSION.SDK_INT >= 23) {
                i5 = 0;
                for (CodecProfileLevel codecProfileLevel : capabilitiesForType.profileLevels) {
                    if (codecProfileLevel.profile <= i7 && codecProfileLevel.profile > r7) {
                        i5 = codecProfileLevel.profile;
                        a.setInteger("profile", codecProfileLevel.profile);
                        a.setInteger("level", codecProfileLevel.level);
                    }
                }
            }
        }
        return a;
    }

    @TargetApi(16)
    private static MediaCodecInfo a(String str) {
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                for (String equalsIgnoreCase : codecInfoAt.getSupportedTypes()) {
                    if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                        return codecInfoAt;
                    }
                }
                continue;
            }
        }
        return null;
    }

    private void a(long j) {
        this.w.add(Long.valueOf(j));
    }

    private long a() {
        Long l = (Long) this.w.poll();
        if (l == null) {
            return 0;
        }
        return l.longValue();
    }

    private boolean a(Surface surface, int i, int i2) {
        if (surface == null) {
            return false;
        }
        if (this.B) {
            EGLContext eGLContext = (EGLContext) this.mGLContextExternal;
            if (eGLContext == null) {
                eGLContext = EGL14.EGL_NO_CONTEXT;
            }
            this.x = com.tencent.liteav.basic.e.c.a(null, eGLContext, surface, i, i2);
        } else {
            javax.microedition.khronos.egl.EGLContext eGLContext2 = (javax.microedition.khronos.egl.EGLContext) this.mGLContextExternal;
            if (eGLContext2 == null) {
                eGLContext2 = EGL10.EGL_NO_CONTEXT;
            }
            this.x = b.a(null, eGLContext2, surface, i, i2);
        }
        if (this.x == null) {
            return false;
        }
        GLES20.glClearColor(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        this.mEncodeFilter = new g();
        this.mEncodeFilter.a(k.e, k.a(j.NORMAL, false, false));
        if (this.mEncodeFilter.c()) {
            GLES20.glViewport(0, 0, i, i2);
            return true;
        }
        this.mEncodeFilter = null;
        return false;
    }

    private void b() {
        if (this.mEncodeFilter != null) {
            this.mEncodeFilter.e();
            this.mEncodeFilter = null;
        }
        if (this.x instanceof b) {
            ((b) this.x).b();
            this.x = null;
        }
        if (this.x instanceof com.tencent.liteav.basic.e.c) {
            ((com.tencent.liteav.basic.e.c) this.x).b();
            this.x = null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x0125 A:{SYNTHETIC, Splitter:B:53:0x0125} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0134 A:{Catch:{ Exception -> 0x013b }} */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x013f  */
    @android.annotation.TargetApi(18)
    private boolean a(com.tencent.liteav.videoencoder.TXSVideoEncoderParam r14) {
        /*
        r13 = this;
        r0 = 0;
        r13.A = r0;
        r13.z = r0;
        r1 = 0;
        r13.c = r1;
        r13.d = r1;
        r13.e = r1;
        r13.f = r1;
        r13.g = r0;
        r13.j = r1;
        r13.k = r1;
        r13.l = r1;
        r13.m = r1;
        r13.n = r1;
        r13.p = r1;
        r13.q = r1;
        r3 = 0;
        r13.C = r3;
        r13.D = r3;
        r13.E = r1;
        r1 = -1;
        r13.H = r1;
        r1 = r14.width;
        r13.mOutputWidth = r1;
        r1 = r14.height;
        r13.mOutputHeight = r1;
        r1 = r14.gop;
        r13.I = r1;
        r1 = r14.fullIFrame;
        r13.J = r1;
        r1 = r14.syncOutput;
        r13.o = r1;
        r1 = r14.enableEGL14;
        r13.B = r1;
        r1 = r13.w;
        r1.clear();
        r1 = 1;
        if (r14 == 0) goto L_0x015d;
    L_0x0049:
        r2 = r14.width;
        if (r2 == 0) goto L_0x015d;
    L_0x004d:
        r2 = r14.height;
        if (r2 == 0) goto L_0x015d;
    L_0x0051:
        r2 = r14.fps;
        if (r2 == 0) goto L_0x015d;
    L_0x0055:
        r2 = r14.gop;
        if (r2 != 0) goto L_0x005b;
    L_0x0059:
        goto L_0x015d;
    L_0x005b:
        r2 = r14.annexb;
        r13.h = r2;
        r2 = r14.appendSpsPps;
        r13.i = r2;
        r2 = r13.b;
        if (r2 != 0) goto L_0x008e;
    L_0x0067:
        r2 = r14.width;
        r4 = r14.width;
        r2 = r2 * r4;
        r4 = (double) r2;
        r6 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        java.lang.Double.isNaN(r4);
        r4 = r4 * r6;
        r2 = r14.height;
        r6 = r14.height;
        r2 = r2 * r6;
        r6 = (double) r2;
        java.lang.Double.isNaN(r6);
        r4 = r4 + r6;
        r4 = java.lang.Math.sqrt(r4);
        r6 = 4608083138725491507; // 0x3ff3333333333333 float:4.172325E-8 double:1.2;
        r4 = r4 * r6;
        r2 = (int) r4;
        r13.b = r2;
    L_0x008e:
        r2 = r13.b;
        r4 = (long) r2;
        r13.j = r4;
        r2 = r14.fps;
        r13.g = r2;
        r2 = r14.encoderMode;
        r4 = 2;
        switch(r2) {
            case 1: goto L_0x009d;
            case 2: goto L_0x00a1;
            case 3: goto L_0x009f;
            default: goto L_0x009d;
        };
    L_0x009d:
        r11 = 2;
        goto L_0x00a2;
    L_0x009f:
        r11 = 0;
        goto L_0x00a2;
    L_0x00a1:
        r11 = 1;
    L_0x00a2:
        r2 = com.tencent.liteav.basic.f.b.a();
        r2 = r2.c();
        if (r2 != r1) goto L_0x00ae;
    L_0x00ac:
        r14.encoderProfile = r1;
    L_0x00ae:
        r2 = r14.encoderProfile;
        switch(r2) {
            case 1: goto L_0x00b3;
            case 2: goto L_0x00b3;
            case 3: goto L_0x00b3;
            default: goto L_0x00b3;
        };
    L_0x00b3:
        r12 = 1;
        r2 = 5;
        r6 = r14.width;	 Catch:{ Exception -> 0x011d }
        r7 = r14.height;	 Catch:{ Exception -> 0x011d }
        r8 = r13.b;	 Catch:{ Exception -> 0x011d }
        r9 = r14.fps;	 Catch:{ Exception -> 0x011d }
        r10 = r14.gop;	 Catch:{ Exception -> 0x011d }
        r5 = r13;
        r5 = r5.a(r6, r7, r8, r9, r10, r11, r12);	 Catch:{ Exception -> 0x011d }
        if (r5 != 0) goto L_0x00c9;
    L_0x00c6:
        r13.z = r1;	 Catch:{ Exception -> 0x011d }
        return r0;
    L_0x00c9:
        r6 = "video/avc";
        r6 = android.media.MediaCodec.createEncoderByType(r6);	 Catch:{ Exception -> 0x011d }
        r13.r = r6;	 Catch:{ Exception -> 0x011d }
        r6 = r13.r;	 Catch:{ Exception -> 0x00d7 }
        r6.configure(r5, r3, r3, r1);	 Catch:{ Exception -> 0x00d7 }
        goto L_0x00ff;
    L_0x00d7:
        r5 = move-exception;
        r6 = r5 instanceof java.lang.IllegalArgumentException;	 Catch:{ Exception -> 0x011b }
        if (r6 != 0) goto L_0x00e8;
    L_0x00dc:
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x011b }
        r7 = 21;
        if (r6 < r7) goto L_0x00e7;
    L_0x00e2:
        r6 = r5 instanceof android.media.MediaCodec.CodecException;	 Catch:{ Exception -> 0x011b }
        if (r6 == 0) goto L_0x00e7;
    L_0x00e6:
        goto L_0x00e8;
    L_0x00e7:
        throw r5;	 Catch:{ Exception -> 0x011b }
    L_0x00e8:
        r6 = r13.r;	 Catch:{ Exception -> 0x011b }
        r8 = r14.width;	 Catch:{ Exception -> 0x011b }
        r9 = r14.height;	 Catch:{ Exception -> 0x011b }
        r10 = r13.b;	 Catch:{ Exception -> 0x011b }
        r11 = r14.fps;	 Catch:{ Exception -> 0x011b }
        r12 = r14.gop;	 Catch:{ Exception -> 0x011b }
        r7 = r13;
        r7 = r7.a(r8, r9, r10, r11, r12);	 Catch:{ Exception -> 0x011b }
        r6.configure(r7, r3, r3, r1);	 Catch:{ Exception -> 0x011b }
        r5.printStackTrace();	 Catch:{ Exception -> 0x011b }
    L_0x00ff:
        r4 = 3;
        r5 = r13.r;	 Catch:{ Exception -> 0x011b }
        r5 = r5.createInputSurface();	 Catch:{ Exception -> 0x011b }
        r13.y = r5;	 Catch:{ Exception -> 0x011b }
        r4 = 4;
        r5 = r13.r;	 Catch:{ Exception -> 0x011b }
        r5.start();	 Catch:{ Exception -> 0x011b }
        r4 = r13.r;	 Catch:{ Exception -> 0x0117 }
        r4 = r4.getOutputBuffers();	 Catch:{ Exception -> 0x0117 }
        r13.C = r4;	 Catch:{ Exception -> 0x0117 }
        goto L_0x013b;
    L_0x0117:
        r4 = move-exception;
        r5 = r4;
        r4 = 5;
        goto L_0x0120;
    L_0x011b:
        r5 = move-exception;
        goto L_0x0120;
    L_0x011d:
        r4 = move-exception;
        r5 = r4;
        r4 = 1;
    L_0x0120:
        r5.printStackTrace();
        if (r4 < r2) goto L_0x012e;
    L_0x0125:
        r2 = r13.r;	 Catch:{ Exception -> 0x013b }
        if (r2 == 0) goto L_0x012e;
    L_0x0129:
        r2 = r13.r;	 Catch:{ Exception -> 0x013b }
        r2.stop();	 Catch:{ Exception -> 0x013b }
    L_0x012e:
        r13.r = r3;	 Catch:{ Exception -> 0x013b }
        r2 = r13.y;	 Catch:{ Exception -> 0x013b }
        if (r2 == 0) goto L_0x0139;
    L_0x0134:
        r2 = r13.y;	 Catch:{ Exception -> 0x013b }
        r2.release();	 Catch:{ Exception -> 0x013b }
    L_0x0139:
        r13.y = r3;	 Catch:{ Exception -> 0x013b }
    L_0x013b:
        r2 = r13.r;
        if (r2 == 0) goto L_0x015a;
    L_0x013f:
        r2 = r13.C;
        if (r2 == 0) goto L_0x015a;
    L_0x0143:
        r2 = r13.y;
        if (r2 != 0) goto L_0x0148;
    L_0x0147:
        goto L_0x015a;
    L_0x0148:
        r2 = r13.y;
        r3 = r14.width;
        r14 = r14.height;
        r14 = r13.a(r2, r3, r14);
        if (r14 != 0) goto L_0x0157;
    L_0x0154:
        r13.z = r1;
        return r0;
    L_0x0157:
        r13.mInit = r1;
        return r1;
    L_0x015a:
        r13.z = r1;
        return r0;
    L_0x015d:
        r13.z = r1;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.videoencoder.a.a(com.tencent.liteav.videoencoder.TXSVideoEncoderParam):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:130:0x0283 A:{Catch:{ IllegalStateException -> 0x028b }} */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x0283 A:{Catch:{ IllegalStateException -> 0x028b }} */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x0283 A:{Catch:{ IllegalStateException -> 0x028b }} */
    private int a(int r30) {
        /*
        r29 = this;
        r14 = r29;
        r0 = r14.r;
        r1 = -1;
        if (r0 != 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = new android.media.MediaCodec$BufferInfo;
        r0.<init>();
        r2 = r14.r;	 Catch:{ IllegalStateException -> 0x0290 }
        r3 = r30;
        r3 = r3 * 1000;
        r3 = (long) r3;	 Catch:{ IllegalStateException -> 0x0290 }
        r15 = r2.dequeueOutputBuffer(r0, r3);	 Catch:{ IllegalStateException -> 0x0290 }
        r12 = 0;
        if (r15 != r1) goto L_0x001c;
    L_0x001b:
        return r12;
    L_0x001c:
        r2 = -3;
        r13 = 1;
        if (r15 != r2) goto L_0x0029;
    L_0x0020:
        r0 = r14.r;
        r0 = r0.getOutputBuffers();
        r14.C = r0;
        return r13;
    L_0x0029:
        r10 = -2;
        if (r15 != r10) goto L_0x0036;
    L_0x002c:
        r0 = r14.r;
        r0 = r0.getOutputFormat();
        r14.callDelegate(r0);
        return r13;
    L_0x0036:
        if (r15 >= 0) goto L_0x0039;
    L_0x0038:
        return r1;
    L_0x0039:
        r2 = r14.C;
        r11 = r2[r15];
        if (r11 != 0) goto L_0x0047;
    L_0x003f:
        r2 = r14;
        r28 = r15;
        r1 = 0;
        r21 = -1;
        goto L_0x027f;
    L_0x0047:
        r2 = r0.size;
        r2 = new byte[r2];
        r3 = r0.offset;
        r11.position(r3);
        r3 = r0.offset;
        r4 = r0.size;
        r3 = r3 + r4;
        r11.limit(r3);
        r3 = r0.size;
        r11.get(r2, r12, r3);
        r3 = r2.length;
        r4 = r0.size;
        r5 = 5;
        r6 = 2;
        r19 = 4;
        if (r4 <= r5) goto L_0x00a6;
    L_0x0066:
        r4 = r2[r12];
        if (r4 != 0) goto L_0x00a6;
    L_0x006a:
        r4 = r2[r13];
        if (r4 != 0) goto L_0x00a6;
    L_0x006e:
        r4 = r2[r6];
        if (r4 != 0) goto L_0x00a6;
    L_0x0072:
        r4 = 3;
        r7 = r2[r4];
        if (r7 != 0) goto L_0x00a6;
    L_0x0077:
        r7 = r2[r19];
        if (r7 != 0) goto L_0x00a6;
    L_0x007b:
        r5 = r2[r5];
        if (r5 != 0) goto L_0x00a6;
    L_0x007f:
        r5 = r2.length;
        r5 = r5 + -4;
        if (r4 >= r5) goto L_0x009f;
    L_0x0084:
        r5 = r2[r4];
        if (r5 != 0) goto L_0x009c;
    L_0x0088:
        r5 = r4 + 1;
        r5 = r2[r5];
        if (r5 != 0) goto L_0x009c;
    L_0x008e:
        r5 = r4 + 2;
        r5 = r2[r5];
        if (r5 != 0) goto L_0x009c;
    L_0x0094:
        r5 = r4 + 3;
        r5 = r2[r5];
        if (r5 != r13) goto L_0x009c;
    L_0x009a:
        r3 = r3 - r4;
        goto L_0x00a0;
    L_0x009c:
        r4 = r4 + 1;
        goto L_0x007f;
    L_0x009f:
        r4 = 0;
    L_0x00a0:
        r5 = new byte[r3];
        java.lang.System.arraycopy(r2, r4, r5, r12, r3);
        goto L_0x00a7;
    L_0x00a6:
        r5 = r2;
    L_0x00a7:
        r3 = r0.size;
        r8 = 0;
        if (r3 != 0) goto L_0x00c3;
    L_0x00ac:
        r0 = r0.flags;
        r0 = r0 & 4;
        if (r0 == 0) goto L_0x003f;
    L_0x00b2:
        r0 = r14.mListener;
        if (r0 == 0) goto L_0x00bb;
    L_0x00b6:
        r0 = r14.mListener;
        r0.onEncodeNAL(r8, r12);
    L_0x00bb:
        r2 = r14;
        r28 = r15;
    L_0x00be:
        r1 = 0;
    L_0x00bf:
        r21 = -2;
        goto L_0x027f;
    L_0x00c3:
        r3 = r0.flags;
        r3 = r3 & r6;
        if (r3 != r6) goto L_0x00e9;
    L_0x00c8:
        r0 = r14.h;
        if (r0 == 0) goto L_0x00d5;
    L_0x00cc:
        r0 = r5.clone();
        r0 = (byte[]) r0;
        r14.D = r0;
        goto L_0x00e1;
    L_0x00d5:
        r0 = r5.clone();
        r0 = (byte[]) r0;
        r0 = r14.a(r0);
        r14.D = r0;
    L_0x00e1:
        r2 = r14;
        r28 = r15;
        r1 = 0;
        r21 = 1;
        goto L_0x027f;
    L_0x00e9:
        r3 = r0.flags;
        r3 = r3 & r13;
        if (r3 != r13) goto L_0x0129;
    L_0x00ee:
        r14.H = r1;
        r1 = r14.h;
        if (r1 == 0) goto L_0x010d;
    L_0x00f4:
        r1 = r14.D;
        r1 = r1.length;
        r3 = r5.length;
        r1 = r1 + r3;
        r1 = new byte[r1];
        r3 = r14.D;
        r4 = r14.D;
        r4 = r4.length;
        java.lang.System.arraycopy(r3, r12, r1, r12, r4);
        r3 = r14.D;
        r3 = r3.length;
        r4 = r5.length;
        java.lang.System.arraycopy(r5, r12, r1, r3, r4);
        r5 = r1;
    L_0x010b:
        r3 = 0;
        goto L_0x0133;
    L_0x010d:
        r1 = r14.a(r5);
        r3 = r14.D;
        r3 = r3.length;
        r4 = r1.length;
        r3 = r3 + r4;
        r3 = new byte[r3];
        r4 = r14.D;
        r5 = r14.D;
        r5 = r5.length;
        java.lang.System.arraycopy(r4, r12, r3, r12, r5);
        r4 = r14.D;
        r4 = r4.length;
        r5 = r1.length;
        java.lang.System.arraycopy(r1, r12, r3, r4, r5);
        r5 = r3;
        goto L_0x010b;
    L_0x0129:
        r1 = r14.h;
        if (r1 != 0) goto L_0x0132;
    L_0x012d:
        r1 = r14.a(r5);
        r5 = r1;
    L_0x0132:
        r3 = 1;
    L_0x0133:
        r1 = r14.J;
        if (r1 != 0) goto L_0x0147;
    L_0x0137:
        r1 = r14.H;
        r1 = r1 + r13;
        r14.H = r1;
        r4 = r14.g;
        r6 = r14.I;
        r4 = r4 * r6;
        if (r1 != r4) goto L_0x0147;
    L_0x0144:
        r29.d();
    L_0x0147:
        r6 = r29.a();
        r8 = r0.presentationTimeUs;
        r16 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r8 = r8 / r16;
        r12 = r14.G;
        r23 = r11;
        r10 = 0;
        r1 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1));
        if (r1 != 0) goto L_0x015d;
    L_0x015b:
        r14.G = r6;
    L_0x015d:
        r12 = r14.F;
        r1 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1));
        if (r1 != 0) goto L_0x0165;
    L_0x0163:
        r14.F = r8;
    L_0x0165:
        r12 = r14.G;
        r10 = r14.F;
        r12 = r12 - r10;
        r24 = r8 + r12;
        r8 = r14.n;
        r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        r26 = 1;
        if (r1 > 0) goto L_0x0178;
    L_0x0174:
        r6 = r14.n;
        r6 = r6 + r26;
    L_0x0178:
        r1 = (r6 > r24 ? 1 : (r6 == r24 ? 0 : -1));
        if (r1 <= 0) goto L_0x017e;
    L_0x017c:
        r6 = r24;
    L_0x017e:
        r14.n = r6;
        r6 = com.tencent.liteav.basic.util.TXCTimeUtil.getTimeTick();
        if (r3 != 0) goto L_0x01bc;
    L_0x0186:
        r8 = r14.e;
        r8 = r8 + r16;
        r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r1 <= 0) goto L_0x01b1;
    L_0x018e:
        r8 = r14.p;
        r8 = (double) r8;
        r10 = 4665518107723300864; // 0x40bf400000000000 float:0.0 double:8000.0;
        java.lang.Double.isNaN(r8);
        r8 = r8 * r10;
        r10 = r14.e;
        r10 = r6 - r10;
        r10 = (double) r10;
        java.lang.Double.isNaN(r10);
        r8 = r8 / r10;
        r10 = 4652218415073722368; // 0x4090000000000000 float:0.0 double:1024.0;
        r8 = r8 / r10;
        r8 = (long) r8;
        r14.c = r8;
        r8 = 0;
        r14.p = r8;
        r14.e = r6;
        goto L_0x01b3;
    L_0x01b1:
        r8 = 0;
    L_0x01b3:
        r10 = r14.k;
        r10 = r10 + r26;
        r14.k = r10;
        r14.l = r8;
        goto L_0x01c2;
    L_0x01bc:
        r8 = r14.l;
        r8 = r8 + r26;
        r14.l = r8;
    L_0x01c2:
        r8 = r14.p;
        r1 = r5.length;
        r10 = (long) r1;
        r8 = r8 + r10;
        r14.p = r8;
        r8 = r14.f;
        r10 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        r8 = r8 + r10;
        r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r1 <= 0) goto L_0x01f2;
    L_0x01d2:
        r8 = r14.q;
        r8 = (double) r8;
        r10 = 4652007308841189376; // 0x408f400000000000 float:0.0 double:1000.0;
        java.lang.Double.isNaN(r8);
        r8 = r8 * r10;
        r10 = r14.f;
        r10 = r6 - r10;
        r10 = (double) r10;
        java.lang.Double.isNaN(r10);
        r8 = r8 / r10;
        r8 = (long) r8;
        r14.d = r8;
        r8 = 0;
        r14.q = r8;
        r14.f = r6;
        goto L_0x01f4;
    L_0x01f2:
        r8 = 0;
    L_0x01f4:
        r6 = r14.q;
        r6 = r6 + r26;
        r14.q = r6;
        r1 = r0.offset;
        r10 = r23;
        r10.position(r1);
        r1 = r14.i;
        if (r1 == 0) goto L_0x0236;
    L_0x0205:
        r6 = r14.k;
        r11 = r14.l;
        r1 = r14.m;
        if (r3 != 0) goto L_0x0210;
    L_0x020d:
        r17 = r8;
        goto L_0x0215;
    L_0x0210:
        r8 = r14.l;
        r8 = r8 - r26;
        goto L_0x020d;
    L_0x0215:
        r16 = 0;
        r8 = r1;
        r1 = r29;
        r2 = r5;
        r4 = r6;
        r6 = r11;
        r12 = 0;
        r20 = r10;
        r22 = -2;
        r10 = r17;
        r21 = 1;
        r12 = r24;
        r28 = r15;
        r14 = r24;
        r17 = r20;
        r18 = r0;
        r1.callDelegate(r2, r3, r4, r6, r8, r10, r12, r14, r16, r17, r18);
    L_0x0233:
        r2 = r29;
        goto L_0x025f;
    L_0x0236:
        r20 = r10;
        r28 = r15;
        r21 = 1;
        r22 = -2;
        r4 = r14.k;
        r6 = r14.l;
        r10 = r14.m;
        if (r3 != 0) goto L_0x0248;
    L_0x0246:
        r12 = r8;
        goto L_0x024d;
    L_0x0248:
        r8 = r14.l;
        r8 = r8 - r26;
        goto L_0x0246;
    L_0x024d:
        r16 = 0;
        r1 = r29;
        r8 = r10;
        r10 = r12;
        r12 = r24;
        r14 = r24;
        r17 = r20;
        r18 = r0;
        r1.callDelegate(r2, r3, r4, r6, r8, r10, r12, r14, r16, r17, r18);
        goto L_0x0233;
    L_0x025f:
        r3 = r2.m;
        r3 = r3 + r26;
        r2.m = r3;
        r1 = r2.M;
        r1 = r1 + 1;
        r2.M = r1;
        r0 = r0.flags;
        r0 = r0 & 4;
        if (r0 == 0) goto L_0x027e;
    L_0x0271:
        r0 = r2.mListener;
        if (r0 == 0) goto L_0x00be;
    L_0x0275:
        r0 = r2.mListener;
        r1 = 0;
        r3 = 0;
        r0.onEncodeNAL(r3, r1);
        goto L_0x00bf;
    L_0x027e:
        r1 = 0;
    L_0x027f:
        r0 = r2.r;	 Catch:{ IllegalStateException -> 0x028b }
        if (r0 == 0) goto L_0x028f;
    L_0x0283:
        r0 = r2.r;	 Catch:{ IllegalStateException -> 0x028b }
        r3 = r28;
        r0.releaseOutputBuffer(r3, r1);	 Catch:{ IllegalStateException -> 0x028b }
        goto L_0x028f;
    L_0x028b:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x028f:
        return r21;
    L_0x0290:
        r0 = move-exception;
        r2 = r14;
        r0.printStackTrace();
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.videoencoder.a.a(int):int");
    }

    private byte[] a(byte[] bArr) {
        int i;
        int length = bArr.length;
        byte[] bArr2 = new byte[(length + 20)];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i4 < length) {
            if (bArr[i4] != (byte) 0 || bArr[i4 + 1] != (byte) 0 || bArr[i4 + 2] != (byte) 1) {
                if (bArr[i4] == (byte) 0 && bArr[i4 + 1] == (byte) 0 && bArr[i4 + 2] == (byte) 0 && bArr[i4 + 3] == (byte) 1) {
                    i3 = a(i4, i2, bArr2, bArr, i3);
                    i4 += 4;
                }
                if (i4 != length - 4 && (bArr[i4 + 1] != (byte) 0 || bArr[i4 + 2] != (byte) 0 || bArr[i4 + 3] != (byte) 1)) {
                    i = length;
                    break;
                }
                i4++;
            } else {
                i3 = a(i4, i2, bArr2, bArr, i3);
                i4 += 3;
            }
            i2 = i4;
            if (i4 != length - 4) {
            }
            i4++;
        }
        i = i4;
        int a = a(i, i2, bArr2, bArr, i3);
        byte[] bArr3 = new byte[a];
        System.arraycopy(bArr2, 0, bArr3, 0, a);
        return bArr3;
    }

    private int a(int i, int i2, byte[] bArr, byte[] bArr2, int i3) {
        if (i2 <= 0 || i <= i2) {
            return i3;
        }
        i -= i2;
        try {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[4]);
            wrap.asIntBuffer().put(i);
            wrap.order(ByteOrder.BIG_ENDIAN);
            System.arraycopy(wrap.array(), 0, bArr, i3, 4);
            System.arraycopy(bArr2, i2, bArr, i3 + 4, i);
            return i3 + (i + 4);
        } catch (Exception e) {
            e.printStackTrace();
            TXCLog.e(a, "setNalData exception");
            return i3;
        }
    }

    @TargetApi(18)
    private void b(int i) {
        if (!this.z && this.x != null) {
            int a;
            a(this.E);
            this.mEncodeFilter.b(this.N);
            if (this.x instanceof com.tencent.liteav.basic.e.c) {
                ((com.tencent.liteav.basic.e.c) this.x).a(this.E * 1000000);
                ((com.tencent.liteav.basic.e.c) this.x).c();
            }
            if (this.x instanceof b) {
                ((b) this.x).a();
            }
            while (true) {
                a = a(i);
                if (a <= 0) {
                    break;
                }
            }
            if (a == -1 || a == -2) {
                if (a == -1) {
                    callDelegate(10000005);
                }
                this.z = true;
                c();
                return;
            }
            this.L++;
        }
    }

    private void c() {
        if (this.mInit) {
            this.z = true;
            this.A = true;
            b();
            try {
                this.r.stop();
                try {
                    this.r.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IllegalStateException e2) {
                e2.printStackTrace();
                this.r.release();
            } catch (Throwable th) {
                try {
                    this.r.release();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                throw th;
            }
            this.r = null;
            this.N = -1;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f = 0;
            this.g = 0;
            this.j = 0;
            this.k = 0;
            this.l = 0;
            this.m = 0;
            this.n = 0;
            this.p = 0;
            this.q = 0;
            this.mGLContextExternal = null;
            this.C = null;
            this.D = null;
            this.E = 0;
            this.mOutputWidth = 0;
            this.mOutputHeight = 0;
            this.mInit = false;
            this.mListener = null;
            this.w.clear();
        }
    }

    private void c(int i) {
        if (this.j != ((long) this.b)) {
            this.j = (long) this.b;
            if (VERSION.SDK_INT >= 19 && this.r != null) {
                Bundle bundle = new Bundle();
                bundle.putInt("video-bitrate", this.b * Filter.K);
                this.r.setParameters(bundle);
            }
        }
    }

    private void d() {
        if (VERSION.SDK_INT >= 19 && this.r != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("request-sync", 0);
            this.r.setParameters(bundle);
        }
    }
}
