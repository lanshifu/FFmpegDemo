package com.tencent.liteav.e;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.LongSparseArray;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.c.g;
import com.tencent.liteav.c.i;
import com.tencent.liteav.d.e;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@TargetApi(16)
/* compiled from: VideoDecAndDemuxPreview */
public class x extends c {
    private int A;
    private long B;
    private long C;
    private int D;
    private boolean E;
    private long F;
    private int G;
    private long H;
    private long I;
    private long J;
    private long K;
    private long L;
    private AtomicBoolean M;
    private e N;
    private long O;
    private AtomicBoolean P;
    private long Q;
    private AtomicBoolean R;
    private final String k;
    private LongSparseArray<e> l;
    private LongSparseArray<e> m;
    private b n;
    private HandlerThread o;
    private a p;
    private HandlerThread q;
    private AtomicBoolean r;
    private AtomicBoolean s;
    private AtomicBoolean t;
    private AtomicBoolean u;
    private AtomicInteger v;
    private e w;
    private e x;
    private volatile boolean y;
    private long z;

    /* compiled from: VideoDecAndDemuxPreview */
    class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 201:
                    x.this.b();
                    x.this.p.sendEmptyMessage(202);
                    break;
                case 202:
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("avsync audio frame start AudioDecodeHandler, mCurrentState = ");
                    stringBuilder.append(x.this.v);
                    stringBuilder.append(", mAudioDecodeEOF = ");
                    stringBuilder.append(x.this.u);
                    TXCLog.i("VideoDecAndDemuxPreview", stringBuilder.toString());
                    while (x.this.v.get() != 1 && !x.this.u.get()) {
                        try {
                            if (x.this.v.get() == 3) {
                                x.this.w = null;
                                x.this.O = -1;
                                Thread.sleep(10);
                            } else if (x.this.y) {
                                x.this.v();
                                x.this.w();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            if (x.this.v.get() == 1) {
                            }
                        }
                    }
                    x.this.w = null;
                    x.this.O = -1;
                    if (x.this.v.get() != 1) {
                        TXCLog.i("VideoDecAndDemuxPreview", "AudioDecodeHandler, in MSG_AUDIO_DECODE_START, send MSG_AUDIO_DECODE_STOP");
                        x.this.p.sendEmptyMessage(203);
                        break;
                    }
                    TXCLog.d("VideoDecAndDemuxPreview", "AudioDecodeHandler, loop decode end state is init, ignore to stop");
                    return;
                case 203:
                    TXCLog.i("VideoDecAndDemuxPreview", "AudioDecodeHandler, audio decode stop!");
                    x.this.p.removeMessages(202);
                    if (x.this.c != null) {
                        x.this.c.b();
                        x.this.c = null;
                        break;
                    }
                    break;
                case 204:
                    x.this.w = null;
                    x.this.O = -1;
                    x.this.p.removeMessages(202);
                    break;
            }
        }
    }

    /* compiled from: VideoDecAndDemuxPreview */
    class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            e c;
            switch (i) {
                case 5:
                    try {
                        x.this.a.a(x.this.Q);
                        c = x.this.b.c();
                        if (c != null) {
                            x.this.b.a(x.this.a.a(c));
                            c = x.this.b.d();
                            if (c != null) {
                                c.j(x.this.d());
                                c.k(x.this.e());
                                c.e(x.this.p());
                                if (g.a().b() && c.e() <= x.this.g.get()) {
                                    x.this.b.b(c);
                                }
                                x.this.c(c);
                                break;
                            }
                            TXCLog.e("VideoDecAndDemuxPreview", "VideoDecodeHandler, preview at time, frame is null");
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                case 6:
                    TXCLog.i("VideoDecAndDemuxPreview", "preview at time : configureVideo()");
                    x.this.a();
                    x.this.a.a(x.this.Q);
                    x.this.n.sendEmptyMessage(5);
                    break;
                default:
                    switch (i) {
                        case 101:
                            TXCLog.i("VideoDecAndDemuxPreview", "normal : configureVideo()");
                            x.this.a();
                            if (g.a().b()) {
                                x.this.a.a(x.this.h.get());
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("VideoDecodeHandler, reverse, seekVideo time = ");
                                stringBuilder.append(x.this.h);
                                TXCLog.i("VideoDecAndDemuxPreview", stringBuilder.toString());
                            }
                            x.this.n.sendEmptyMessage(102);
                            break;
                        case 102:
                            try {
                                if (x.this.K >= 0) {
                                    if (x.this.N.p()) {
                                        TXCLog.i("VideoDecAndDemuxPreview", "is end video frame, to stop decode");
                                        x.this.c(x.this.N);
                                        x.this.n.sendEmptyMessage(103);
                                        return;
                                    } else if (x.this.M.get()) {
                                        x.this.c(x.this.N);
                                    } else if (x.this.t()) {
                                        x.this.c(x.this.N);
                                    } else {
                                        x.this.n.sendEmptyMessageDelayed(102, 5);
                                        return;
                                    }
                                }
                                x.this.u();
                                c = x.this.s();
                                if (c != null) {
                                    x.this.N = x.this.b(c);
                                    if (x.this.I >= 0) {
                                        x.this.M.compareAndSet(true, false);
                                        x.this.n.sendEmptyMessageDelayed(102, 5);
                                        break;
                                    }
                                    x.this.I = x.this.K;
                                    if (x.this.O > 0) {
                                        x.this.J = x.this.O;
                                    } else {
                                        x.this.J = System.currentTimeMillis();
                                    }
                                    StringBuilder stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append("avsync first video frame ts : ");
                                    stringBuilder2.append(x.this.I);
                                    stringBuilder2.append(", first systime : ");
                                    stringBuilder2.append(x.this.J);
                                    stringBuilder2.append(", current systime ");
                                    stringBuilder2.append(System.currentTimeMillis());
                                    TXCLog.d("VideoDecAndDemuxPreview", stringBuilder2.toString());
                                    x.this.M.set(true);
                                    x.this.n.sendEmptyMessage(102);
                                    break;
                                }
                                x.this.n.sendEmptyMessage(102);
                                return;
                            } catch (InterruptedException e2) {
                                e2.printStackTrace();
                                break;
                            }
                        case 103:
                            TXCLog.i("VideoDecAndDemuxPreview", "VideoDecodeHandler, video decode stop!");
                            if (x.this.t.get() && x.this.u.get()) {
                                x.this.v.set(1);
                            }
                            x.this.n.removeMessages(102);
                            synchronized (x.this) {
                                x.this.r();
                            }
                            if (x.this.b != null) {
                                x.this.b.b();
                                x.this.b = null;
                                break;
                            }
                            break;
                        case 104:
                            TXCLog.i("VideoDecAndDemuxPreview", "video decode pause");
                            x.this.n.removeMessages(102);
                            synchronized (x.this) {
                                x.this.r();
                            }
                            break;
                    }
                    break;
            }
        }
    }

    public x() {
        this.k = "VideoDecAndDemuxPreview";
        this.y = true;
        this.z = -1;
        this.B = 0;
        this.C = 0;
        this.E = false;
        this.H = -1;
        this.I = -1;
        this.J = -1;
        this.K = -1;
        this.L = -1;
        this.O = -1;
        this.m = new LongSparseArray();
        this.l = new LongSparseArray();
        this.g = new AtomicLong(0);
        this.h = new AtomicLong(0);
        this.v = new AtomicInteger(1);
        this.r = new AtomicBoolean(false);
        this.s = new AtomicBoolean(false);
        this.R = new AtomicBoolean(false);
        this.t = new AtomicBoolean(false);
        this.u = new AtomicBoolean(false);
        this.M = new AtomicBoolean(false);
        this.P = new AtomicBoolean(false);
        this.o = new HandlerThread("video_handler_thread");
        this.o.start();
        this.n = new b(this.o.getLooper());
        this.q = new HandlerThread("audio_handler_thread");
        this.q.start();
        this.p = new a(this.q.getLooper());
    }

    /* JADX WARNING: Missing block: B:20:0x00e7, code skipped:
            return;
     */
    public synchronized void l() {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = "VideoDecAndDemuxPreview";
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e8 }
        r1.<init>();	 Catch:{ all -> 0x00e8 }
        r2 = "start(), mCurrentState = ";
        r1.append(r2);	 Catch:{ all -> 0x00e8 }
        r2 = r4.v;	 Catch:{ all -> 0x00e8 }
        r1.append(r2);	 Catch:{ all -> 0x00e8 }
        r1 = r1.toString();	 Catch:{ all -> 0x00e8 }
        com.tencent.liteav.basic.log.TXCLog.i(r0, r1);	 Catch:{ all -> 0x00e8 }
        r0 = r4.v;	 Catch:{ all -> 0x00e8 }
        r0 = r0.get();	 Catch:{ all -> 0x00e8 }
        r1 = 2;
        if (r0 != r1) goto L_0x0040;
    L_0x0022:
        r0 = "VideoDecAndDemuxPreview";
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e8 }
        r1.<init>();	 Catch:{ all -> 0x00e8 }
        r2 = "start ignore, mCurrentState = ";
        r1.append(r2);	 Catch:{ all -> 0x00e8 }
        r2 = r4.v;	 Catch:{ all -> 0x00e8 }
        r2 = r2.get();	 Catch:{ all -> 0x00e8 }
        r1.append(r2);	 Catch:{ all -> 0x00e8 }
        r1 = r1.toString();	 Catch:{ all -> 0x00e8 }
        com.tencent.liteav.basic.log.TXCLog.e(r0, r1);	 Catch:{ all -> 0x00e8 }
        monitor-exit(r4);
        return;
    L_0x0040:
        r0 = r4.l;	 Catch:{ all -> 0x00e8 }
        r0.clear();	 Catch:{ all -> 0x00e8 }
        r0 = r4.M;	 Catch:{ all -> 0x00e8 }
        r2 = 0;
        r0.set(r2);	 Catch:{ all -> 0x00e8 }
        r0 = r4.R;	 Catch:{ all -> 0x00e8 }
        r3 = 1;
        r0.set(r3);	 Catch:{ all -> 0x00e8 }
        r0 = r4.P;	 Catch:{ all -> 0x00e8 }
        r0.getAndSet(r2);	 Catch:{ all -> 0x00e8 }
        r0 = r4.r;	 Catch:{ all -> 0x00e8 }
        r0.getAndSet(r2);	 Catch:{ all -> 0x00e8 }
        r0 = r4.s;	 Catch:{ all -> 0x00e8 }
        r0.getAndSet(r2);	 Catch:{ all -> 0x00e8 }
        r0 = r4.t;	 Catch:{ all -> 0x00e8 }
        r0.getAndSet(r2);	 Catch:{ all -> 0x00e8 }
        r0 = r4.u;	 Catch:{ all -> 0x00e8 }
        r0.getAndSet(r2);	 Catch:{ all -> 0x00e8 }
        r0 = 0;
        r4.N = r0;	 Catch:{ all -> 0x00e8 }
        r4.y = r3;	 Catch:{ all -> 0x00e8 }
        r2 = -1;
        r4.H = r2;	 Catch:{ all -> 0x00e8 }
        r0 = r4.v;	 Catch:{ all -> 0x00e8 }
        r0 = r0.get();	 Catch:{ all -> 0x00e8 }
        r2 = 3;
        if (r0 != r2) goto L_0x00a6;
    L_0x007c:
        r0 = "VideoDecAndDemuxPreview";
        r2 = "start(), state pause, seek then send MSG_VIDEO_DECODE_START and MSG_AUDIO_DECODE_START";
        com.tencent.liteav.basic.log.TXCLog.i(r0, r2);	 Catch:{ all -> 0x00e8 }
        r0 = r4.v;	 Catch:{ all -> 0x00e8 }
        r0.set(r1);	 Catch:{ all -> 0x00e8 }
        r0 = r4.g;	 Catch:{ all -> 0x00e8 }
        r0 = r0.get();	 Catch:{ all -> 0x00e8 }
        r4.b(r0);	 Catch:{ all -> 0x00e8 }
        r0 = r4.n;	 Catch:{ all -> 0x00e8 }
        r1 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        r0.sendEmptyMessage(r1);	 Catch:{ all -> 0x00e8 }
        r0 = r4.h();	 Catch:{ all -> 0x00e8 }
        if (r0 == 0) goto L_0x00e6;
    L_0x009e:
        r0 = r4.p;	 Catch:{ all -> 0x00e8 }
        r1 = 202; // 0xca float:2.83E-43 double:1.0E-321;
        r0.sendEmptyMessage(r1);	 Catch:{ all -> 0x00e8 }
        goto L_0x00e6;
    L_0x00a6:
        r0 = r4.v;	 Catch:{ all -> 0x00e8 }
        r0 = r0.get();	 Catch:{ all -> 0x00e8 }
        r2 = 4;
        if (r0 != r2) goto L_0x00bd;
    L_0x00af:
        r0 = "VideoDecAndDemuxPreview";
        r1 = "start(), state preview at time, stop then start";
        com.tencent.liteav.basic.log.TXCLog.i(r0, r1);	 Catch:{ all -> 0x00e8 }
        r4.m();	 Catch:{ all -> 0x00e8 }
        r4.l();	 Catch:{ all -> 0x00e8 }
        goto L_0x00e6;
    L_0x00bd:
        r0 = "VideoDecAndDemuxPreview";
        r2 = "start(), state init, seek then send MSG_VIDEO_DECODE_CONFIG and MSG_AUDIO_DECODE_CONFIG";
        com.tencent.liteav.basic.log.TXCLog.i(r0, r2);	 Catch:{ all -> 0x00e8 }
        r0 = r4.v;	 Catch:{ all -> 0x00e8 }
        r0.set(r1);	 Catch:{ all -> 0x00e8 }
        r0 = r4.g;	 Catch:{ all -> 0x00e8 }
        r0 = r0.get();	 Catch:{ all -> 0x00e8 }
        r4.b(r0);	 Catch:{ all -> 0x00e8 }
        r0 = r4.n;	 Catch:{ all -> 0x00e8 }
        r1 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r0.sendEmptyMessage(r1);	 Catch:{ all -> 0x00e8 }
        r0 = r4.h();	 Catch:{ all -> 0x00e8 }
        if (r0 == 0) goto L_0x00e6;
    L_0x00df:
        r0 = r4.p;	 Catch:{ all -> 0x00e8 }
        r1 = 201; // 0xc9 float:2.82E-43 double:9.93E-322;
        r0.sendEmptyMessage(r1);	 Catch:{ all -> 0x00e8 }
    L_0x00e6:
        monitor-exit(r4);
        return;
    L_0x00e8:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.e.x.l():void");
    }

    public void m() {
        if (this.v.get() == 1) {
            TXCLog.e("VideoDecAndDemuxPreview", "stop(), mCurrentState in stop, ignore");
            return;
        }
        this.v.set(1);
        TXCLog.i("VideoDecAndDemuxPreview", "stop(), send MSG_VIDEO_DECODE_STOP");
        this.n.sendEmptyMessage(103);
        if (h()) {
            TXCLog.i("VideoDecAndDemuxPreview", "stop(), send MSG_AUDIO_DECODE_STOP");
            this.p.sendEmptyMessage(203);
        }
    }

    /* JADX WARNING: Missing block: B:11:0x0036, code skipped:
            return;
     */
    public synchronized void n() {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = r4.v;	 Catch:{ all -> 0x004f }
        r0 = r0.get();	 Catch:{ all -> 0x004f }
        r1 = 3;
        if (r0 == r1) goto L_0x0037;
    L_0x000a:
        r2 = 1;
        if (r0 != r2) goto L_0x000e;
    L_0x000d:
        goto L_0x0037;
    L_0x000e:
        r0 = r4.v;	 Catch:{ all -> 0x004f }
        r0.set(r1);	 Catch:{ all -> 0x004f }
        r0 = "VideoDecAndDemuxPreview";
        r1 = "pause(), send MSG_VIDEO_DECODE_PAUSE";
        com.tencent.liteav.basic.log.TXCLog.i(r0, r1);	 Catch:{ all -> 0x004f }
        r0 = r4.n;	 Catch:{ all -> 0x004f }
        r1 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        r0.sendEmptyMessage(r1);	 Catch:{ all -> 0x004f }
        r0 = r4.h();	 Catch:{ all -> 0x004f }
        if (r0 == 0) goto L_0x0035;
    L_0x0027:
        r0 = "VideoDecAndDemuxPreview";
        r1 = "pause(), send MSG_AUDIO_DECODE_PAUSE";
        com.tencent.liteav.basic.log.TXCLog.i(r0, r1);	 Catch:{ all -> 0x004f }
        r0 = r4.p;	 Catch:{ all -> 0x004f }
        r1 = 204; // 0xcc float:2.86E-43 double:1.01E-321;
        r0.sendEmptyMessage(r1);	 Catch:{ all -> 0x004f }
    L_0x0035:
        monitor-exit(r4);
        return;
    L_0x0037:
        r1 = "VideoDecAndDemuxPreview";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004f }
        r2.<init>();	 Catch:{ all -> 0x004f }
        r3 = "pause ignore, current state = ";
        r2.append(r3);	 Catch:{ all -> 0x004f }
        r2.append(r0);	 Catch:{ all -> 0x004f }
        r0 = r2.toString();	 Catch:{ all -> 0x004f }
        com.tencent.liteav.basic.log.TXCLog.e(r1, r0);	 Catch:{ all -> 0x004f }
        monitor-exit(r4);
        return;
    L_0x004f:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.e.x.n():void");
    }

    /* JADX WARNING: Missing block: B:13:0x0039, code skipped:
            return;
     */
    public synchronized void o() {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = r4.v;	 Catch:{ all -> 0x0052 }
        r0 = r0.get();	 Catch:{ all -> 0x0052 }
        r1 = 1;
        if (r0 == r1) goto L_0x003a;
    L_0x000a:
        r1 = 2;
        if (r0 == r1) goto L_0x003a;
    L_0x000d:
        r2 = 4;
        if (r0 != r2) goto L_0x0011;
    L_0x0010:
        goto L_0x003a;
    L_0x0011:
        r0 = r4.v;	 Catch:{ all -> 0x0052 }
        r0.set(r1);	 Catch:{ all -> 0x0052 }
        r0 = "VideoDecAndDemuxPreview";
        r1 = "resume(), send MSG_VIDEO_DECODE_START";
        com.tencent.liteav.basic.log.TXCLog.i(r0, r1);	 Catch:{ all -> 0x0052 }
        r0 = r4.n;	 Catch:{ all -> 0x0052 }
        r1 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        r0.sendEmptyMessage(r1);	 Catch:{ all -> 0x0052 }
        r0 = r4.h();	 Catch:{ all -> 0x0052 }
        if (r0 == 0) goto L_0x0038;
    L_0x002a:
        r0 = "VideoDecAndDemuxPreview";
        r1 = "resume(), send MSG_AUDIO_DECODE_START";
        com.tencent.liteav.basic.log.TXCLog.i(r0, r1);	 Catch:{ all -> 0x0052 }
        r0 = r4.p;	 Catch:{ all -> 0x0052 }
        r1 = 202; // 0xca float:2.83E-43 double:1.0E-321;
        r0.sendEmptyMessage(r1);	 Catch:{ all -> 0x0052 }
    L_0x0038:
        monitor-exit(r4);
        return;
    L_0x003a:
        r1 = "VideoDecAndDemuxPreview";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0052 }
        r2.<init>();	 Catch:{ all -> 0x0052 }
        r3 = "resume ignore, state = ";
        r2.append(r3);	 Catch:{ all -> 0x0052 }
        r2.append(r0);	 Catch:{ all -> 0x0052 }
        r0 = r2.toString();	 Catch:{ all -> 0x0052 }
        com.tencent.liteav.basic.log.TXCLog.e(r1, r0);	 Catch:{ all -> 0x0052 }
        monitor-exit(r4);
        return;
    L_0x0052:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.e.x.o():void");
    }

    public void a(long j) {
        this.Q = j * 1000;
        StringBuilder stringBuilder;
        if (this.v.get() == 3 || this.v.get() == 4) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("previewAtTime, state = ");
            stringBuilder.append(this.v.get());
            stringBuilder.append(", send MSG_VIDEO_DECODE_PREVIEW_START");
            TXCLog.i("VideoDecAndDemuxPreview", stringBuilder.toString());
            this.v.set(4);
            this.n.sendEmptyMessage(5);
            return;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("previewAtTime, state = ");
        stringBuilder.append(this.v.get());
        stringBuilder.append(", send MSG_VIDEO_DECODE_PREVIEW_CONFIG");
        TXCLog.i("VideoDecAndDemuxPreview", stringBuilder.toString());
        this.v.set(4);
        synchronized (this) {
            r();
        }
        this.n.sendEmptyMessage(6);
    }

    public void a(boolean z) {
        this.y = z;
    }

    public synchronized void a(long j, long j2) {
        this.g.getAndSet(j);
        this.h.getAndSet(j2);
        r();
    }

    private void r() {
        this.N = null;
        this.x = null;
        this.K = -1;
        this.L = -1;
        this.I = -1;
        this.J = -1;
        this.M.set(false);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("avsync video frame reset first systime ");
        stringBuilder.append(this.J);
        TXCLog.d("VideoDecAndDemuxPreview", stringBuilder.toString());
        b(this.B, this.C);
    }

    public void b(long j, long j2) {
        if (j == 0 && j2 == 0) {
            this.D = 0;
        } else {
            this.D = 3;
        }
        this.B = j;
        this.C = j2;
        this.E = false;
    }

    public int p() {
        return this.a.f();
    }

    @TargetApi(18)
    public void k() {
        if (this.o != null) {
            this.o.quitSafely();
        }
        if (this.q != null) {
            this.q.quitSafely();
        }
        if (this.l != null) {
            this.l.clear();
        }
        if (this.m != null) {
            this.m.clear();
        }
        this.w = null;
        this.x = null;
    }

    private e b(e eVar) {
        if (!com.tencent.liteav.f.g.a().c()) {
            eVar.b(eVar.e());
            return eVar;
        } else if (this.x == null) {
            TXCLog.i("VideoDecAndDemuxPreview", "processSpeedFrame, mLastVideoFrame is null, this is first frame, not to speed");
            return eVar;
        } else if (eVar.p()) {
            TXCLog.i("VideoDecAndDemuxPreview", "processSpeedFrame, this frame is end frame, not to speed");
            return eVar;
        } else {
            long t = this.x.t() + ((long) (((float) (eVar.e() - this.x.e())) / com.tencent.liteav.f.g.a().a(eVar.e())));
            eVar.b(t);
            this.K = t / 1000;
            return eVar;
        }
    }

    private synchronized void b(long j) {
        if (this.P.get()) {
            TXCLog.e("VideoDecAndDemuxPreview", "seekPosition, had seeked");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("======================准备开始定位video和audio起点=====================开始时间mStartTime = ");
        stringBuilder.append(this.g);
        TXCLog.d("VideoDecAndDemuxPreview", stringBuilder.toString());
        this.a.a(j);
        j = this.a.p();
        this.a.c(j);
        long q = this.a.q();
        TXCLog.d("VideoDecAndDemuxPreview", "======================定位结束=====================");
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("==============seekTime==========");
        stringBuilder2.append(this.g);
        TXCLog.d("VideoDecAndDemuxPreview", stringBuilder2.toString());
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("==============startVdts==========");
        stringBuilder2.append(j);
        TXCLog.d("VideoDecAndDemuxPreview", stringBuilder2.toString());
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("==============startAdts==========");
        stringBuilder3.append(q);
        TXCLog.d("VideoDecAndDemuxPreview", stringBuilder3.toString());
        this.P.getAndSet(true);
    }

    private void c(e eVar) {
        if (this.f != null) {
            this.f.a(eVar);
        }
        this.x = eVar;
    }

    @Nullable
    private e s() {
        e d = this.b.d();
        if (d == null || d.o() == null) {
            return null;
        }
        e eVar = (e) this.l.get(d.e());
        if (eVar != null) {
            d = this.b.a(eVar, d);
            if (g.a().b()) {
                d.a(eVar.v());
            }
        } else {
            d.j(d());
            d.k(e());
        }
        StringBuilder stringBuilder;
        if (d.e() >= this.g.get() || d.p()) {
            if (d.e() > this.h.get()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("VideoFrame pts :");
                stringBuilder.append(d.e());
                stringBuilder.append(" after  duration (");
                stringBuilder.append(this.h);
                stringBuilder.append(")");
                TXCLog.d("VideoDecAndDemuxPreview", stringBuilder.toString());
                if (g.a().b()) {
                    return null;
                }
                d = this.b.b(d);
            }
            if (d.p()) {
                this.t.getAndSet(true);
                TXCLog.d("VideoDecAndDemuxPreview", "==================preview decode Video END==========================");
                if (this.u.get()) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("================== VIDEO SEND END OF FILE ==========================");
                    stringBuilder2.append(d.toString());
                    TXCLog.d("VideoDecAndDemuxPreview", stringBuilder2.toString());
                } else {
                    TXCLog.d("VideoDecAndDemuxPreview", "-------------- preview Audio NOT END ----------------");
                    return d;
                }
            }
            this.N = d;
            this.K = this.N.e() / 1000;
            return d;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("VideoFrame pts :");
        stringBuilder.append(d.e());
        stringBuilder.append(" before  startTime (");
        stringBuilder.append(this.g);
        stringBuilder.append(")");
        TXCLog.d("VideoDecAndDemuxPreview", stringBuilder.toString());
        return null;
    }

    private boolean t() {
        this.L = System.currentTimeMillis();
        this.K = this.N.t() / 1000;
        return Math.abs(this.K - this.I) < this.L - this.J;
    }

    private synchronized void u() throws InterruptedException {
        if (this.r.get()) {
            TXCLog.e("VideoDecAndDemuxPreview", "readVideoFrame, read video end of file, ignore");
            return;
        }
        e c = this.b.c();
        if (c != null) {
            boolean a;
            if ((this.D == 3 || this.D == 2) && this.g.get() <= this.B && this.a.r() >= this.C) {
                this.a.a(this.B);
                this.D--;
                this.E = true;
            }
            c = this.a.a(c);
            if (this.A <= 0) {
                this.A = j();
                if (this.A != 0) {
                    this.G = (1000 / this.A) * 1000;
                }
            }
            if (this.E) {
                c.a(this.F + ((long) this.G));
            }
            this.F = c.e();
            if (this.H < 0) {
                this.H = this.F;
            }
            if (g.a().b()) {
                if (c.p()) {
                    this.F = a(c);
                    this.H = this.F;
                }
                a = a(this.F, (long) this.G, c);
                if (!a) {
                    long abs = Math.abs(this.H - this.F);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("reverse newVPts = ");
                    stringBuilder.append(abs);
                    stringBuilder.append(", mFirstVideoReadPTS = ");
                    stringBuilder.append(this.H);
                    stringBuilder.append(", curFixFrame.getSampleTime() = ");
                    stringBuilder.append(this.F);
                    TXCLog.i("VideoDecAndDemuxPreview", stringBuilder.toString());
                    c.a(abs);
                    c.c(abs);
                    c.d(this.F);
                }
            } else {
                a = this.a.c(c);
            }
            if (a) {
                this.r.set(true);
                TXCLog.d("VideoDecAndDemuxPreview", "read video end");
            }
            this.l.put(c.e(), c);
            this.b.a(c);
        }
    }

    private synchronized void v() {
        if (!this.s.get()) {
            e c = this.c.c();
            if (c != null) {
                c = this.a.b(c);
                if (this.a.d(c)) {
                    this.s.set(true);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("audio endOfFile:");
                    stringBuilder.append(this.s.get());
                    TXCLog.d("VideoDecAndDemuxPreview", stringBuilder.toString());
                    TXCLog.d("VideoDecAndDemuxPreview", "read audio end");
                }
                this.m.put(c.e(), c);
                this.c.a(c);
            }
        }
    }

    private synchronized void w() {
        e d = this.c.d();
        if (d != null) {
            if (this.c.e() && this.R.get()) {
                MediaFormat f = f();
                f.setInteger("sample-rate", d.j());
                i.a().a(f);
                this.R.set(false);
            }
            if (d.o() != null) {
                e eVar = (e) this.m.get(d.e());
                eVar = eVar != null ? this.c.a(eVar, d) : d;
                if (eVar != null) {
                    StringBuilder stringBuilder;
                    if (eVar.e() >= this.g.get() || eVar.p()) {
                        StringBuilder stringBuilder2;
                        if (eVar.e() > this.h.get()) {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("AudioFrame pts :");
                            stringBuilder2.append(eVar.e());
                            stringBuilder2.append(" after  duration (");
                            stringBuilder2.append(this.h);
                            stringBuilder2.append(")");
                            TXCLog.d("VideoDecAndDemuxPreview", stringBuilder2.toString());
                            eVar = this.c.b(eVar);
                        }
                        if (eVar.p()) {
                            this.u.set(true);
                            TXCLog.d("VideoDecAndDemuxPreview", "==================preview decode Audio END==========================");
                            if (this.t.get()) {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("================== AUDIO SEND END OF FILE ==========================");
                                stringBuilder2.append(eVar.toString());
                                TXCLog.d("VideoDecAndDemuxPreview", stringBuilder2.toString());
                            } else {
                                TXCLog.d("VideoDecAndDemuxPreview", "-------------- preview VIDEO NOT END ----------------");
                                return;
                            }
                        }
                        if (this.w == null) {
                            this.w = d;
                            this.O = System.currentTimeMillis();
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("avsync first audio frame ts : ");
                            stringBuilder.append(this.w.e());
                            stringBuilder.append(", first systime : ");
                            stringBuilder.append(this.O);
                            TXCLog.d("VideoDecAndDemuxPreview", stringBuilder.toString());
                        }
                        if (this.z == -1) {
                            this.z = System.currentTimeMillis();
                        }
                        if (this.e != null) {
                            this.e.a(eVar);
                        }
                        this.w = eVar;
                        this.z = System.currentTimeMillis();
                        return;
                    }
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("AudioFrame pts :");
                    stringBuilder.append(eVar.e());
                    stringBuilder.append(" before  startTime (");
                    stringBuilder.append(this.g);
                    stringBuilder.append(")");
                    TXCLog.d("VideoDecAndDemuxPreview", stringBuilder.toString());
                }
            }
        }
    }

    public boolean q() {
        return this.t.get();
    }
}
