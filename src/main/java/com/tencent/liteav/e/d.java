package com.tencent.liteav.e;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.LongSparseArray;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.c.g;
import com.tencent.liteav.d.e;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@TargetApi(16)
/* compiled from: BasicVideoDecDemuxGenerater */
public abstract class d extends c {
    protected a A;
    protected HandlerThread B;
    protected volatile boolean C;
    protected e D;
    protected e E;
    protected int F;
    protected long G;
    protected long H;
    protected int I;
    protected boolean J;
    protected long K;
    protected int L;
    protected long M;
    protected long N;
    protected long O;
    protected AtomicBoolean P;
    protected AtomicBoolean Q;
    protected List<Long> R;
    protected int S;
    protected int T;
    protected AtomicBoolean U;
    private final String V;
    private long W;
    private com.tencent.liteav.g.e X;
    private long Y;
    private long Z;
    protected final int k;
    protected final int l;
    protected final int m;
    protected final int n;
    protected final int o;
    protected final int p;
    protected final int q;
    protected LongSparseArray<e> r;
    protected LongSparseArray<e> s;
    protected AtomicBoolean t;
    protected AtomicBoolean u;
    protected AtomicBoolean v;
    protected AtomicBoolean w;
    protected AtomicInteger x;
    protected b y;
    protected HandlerThread z;

    /* compiled from: BasicVideoDecDemuxGenerater */
    class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 201:
                    d.this.b();
                    d.this.A.sendEmptyMessage(202);
                    return;
                case 202:
                    break;
                case 203:
                    TXCLog.i("BasicVideoDecDemuxGenerater", "AudioDecodeHandler, audio decode stop!");
                    d.this.A.removeMessages(202);
                    if (d.this.c != null) {
                        d.this.c.b();
                        d.this.c = null;
                        return;
                    }
                    return;
                default:
                    return;
            }
            while (d.this.x.get() != 1 && !d.this.w.get()) {
                if (d.this.C) {
                    d.this.t();
                    e i = d.this.u();
                    if (i != null) {
                        i = d.this.b(i);
                        if (i.p()) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("is end audio frame, to stop decode, mVideoDecodeEOF = ");
                            stringBuilder.append(d.this.v);
                            TXCLog.i("BasicVideoDecDemuxGenerater", stringBuilder.toString());
                            if (d.this.v.get()) {
                                d.this.d(i);
                            }
                            d.this.A.sendEmptyMessage(203);
                            return;
                        }
                        d.this.d(i);
                    }
                }
            }
        }
    }

    /* compiled from: BasicVideoDecDemuxGenerater */
    protected class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            StringBuilder stringBuilder;
            e c;
            d dVar;
            switch (i) {
                case 5:
                    if (d.this.R != null && d.this.R.size() != 0) {
                        d.this.a();
                        d.this.S = 0;
                        d.this.T = 0;
                        d.this.y.sendEmptyMessage(6);
                        break;
                    }
                    return;
                case 6:
                    if (d.this.T < d.this.R.size()) {
                        if (d.this.W < 0) {
                            if (d.this.S < d.this.R.size()) {
                                d.this.W = ((Long) d.this.R.get(d.this.S)).longValue();
                                d.this.a.a(d.this.W);
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("VideoDecodeHandler, get pts = ");
                                stringBuilder.append(d.this.W);
                                stringBuilder.append(", mVideoGivenPtsInputIndex = ");
                                stringBuilder.append(d.this.S);
                                TXCLog.i("BasicVideoDecDemuxGenerater", stringBuilder.toString());
                            } else {
                                d.this.W = 0;
                            }
                        }
                        if (d.this.W >= 0) {
                            c = d.this.b.c();
                            if (c != null) {
                                if (d.this.S < d.this.R.size()) {
                                    d dVar2 = d.this;
                                    dVar2.S++;
                                    d.this.b.a(d.this.a.a(c));
                                    TXCLog.i("BasicVideoDecDemuxGenerater", "VideoDecodeHandler, freeFrame exist");
                                } else {
                                    TXCLog.i("BasicVideoDecDemuxGenerater", "VideoDecodeHandler, isReadGivenTimeEnd, set end flag");
                                    c.a(0);
                                    c.c(4);
                                    d.this.b.a(c);
                                }
                                d.this.W = -1;
                            }
                        }
                        if (d.this.U.get()) {
                            c = d.this.b.d();
                            if (c != null) {
                                c.e(d.this.n());
                                c.j(d.this.d());
                                c.k(d.this.e());
                                d.this.c(c);
                                dVar = d.this;
                                dVar.T++;
                                d.this.U.set(false);
                                if (d.this.T < d.this.R.size()) {
                                    d.this.y.sendEmptyMessageDelayed(6, 5);
                                    break;
                                }
                                TXCLog.i("BasicVideoDecDemuxGenerater", "VideoDecodeHandler, decode end");
                                d.this.y.sendEmptyMessage(7);
                                break;
                            }
                            d.this.y.sendEmptyMessageDelayed(6, 5);
                            return;
                        }
                        d.this.y.sendEmptyMessageDelayed(6, 5);
                        return;
                    }
                    return;
                case 7:
                    d.this.y.removeMessages(6);
                    if (d.this.b != null) {
                        d.this.b.b();
                        d.this.b = null;
                    }
                    d.this.x.set(1);
                    break;
                case 8:
                    if (d.this.R != null && d.this.R.size() != 0) {
                        d.this.q();
                        d.this.a();
                        d.this.S = 0;
                        d.this.T = 0;
                        d.this.Y = 0;
                        d.this.Z = 0;
                        sendEmptyMessage(9);
                        break;
                    }
                    return;
                case 9:
                    if (d.this.T < d.this.R.size()) {
                        if (d.this.S >= d.this.R.size()) {
                            sendEmptyMessage(11);
                            break;
                        }
                        d.this.W = ((Long) d.this.R.get(d.this.S)).longValue();
                        if (d.this.Y < d.this.W) {
                            d.this.Z = d.this.Y;
                            d.this.X.b(d.this.Z + 1);
                            d.this.Y = d.this.X.p();
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("nextSyncTime:");
                            stringBuilder.append(d.this.Y);
                            stringBuilder.append(",lastSyncTime");
                            stringBuilder.append(d.this.Z);
                            stringBuilder.append(",mGivenPts:");
                            stringBuilder.append(d.this.W);
                            TXCLog.i("BasicVideoDecDemuxGenerater", stringBuilder.toString());
                            if (d.this.Y != -1 && d.this.Y != d.this.Z) {
                                sendEmptyMessage(9);
                                break;
                            }
                            d.this.Y = d.this.Z;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("seek lastSyncTime:");
                            stringBuilder.append(d.this.Z);
                            stringBuilder.append(",index:");
                            stringBuilder.append(d.this.S);
                            TXCLog.i("BasicVideoDecDemuxGenerater", stringBuilder.toString());
                            sendEmptyMessage(10);
                            return;
                        }
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("seek lastSyncTime:");
                        stringBuilder.append(d.this.Z);
                        stringBuilder.append(",index:");
                        stringBuilder.append(d.this.S);
                        TXCLog.i("BasicVideoDecDemuxGenerater", stringBuilder.toString());
                        d.this.a.b(d.this.Z);
                        sendEmptyMessage(10);
                        d.this.Y = d.this.Z;
                        break;
                    }
                    return;
                    break;
                case 10:
                    try {
                        if (d.this.b != null) {
                            d.this.s();
                            c = d.this.r();
                            if (c != null) {
                                c.j(d.this.d());
                                c.k(d.this.e());
                                if (!c.p()) {
                                    if (c.e() < d.this.W) {
                                        sendEmptyMessage(10);
                                        break;
                                    }
                                    d.this.c(c);
                                    dVar = d.this;
                                    dVar.S++;
                                    sendEmptyMessage(9);
                                    break;
                                }
                                d.this.c(c);
                                sendEmptyMessage(11);
                                break;
                            }
                            sendEmptyMessageDelayed(10, 5);
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                case 11:
                    d.this.y.removeMessages(10);
                    if (d.this.b != null) {
                        d.this.b.b();
                        d.this.b = null;
                    }
                    d.this.x.set(1);
                    break;
                default:
                    StringBuilder stringBuilder2;
                    switch (i) {
                        case 101:
                            d.this.a();
                            if (g.a().b()) {
                                d.this.a.a(d.this.h.get());
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("VideoDecodeHandler, reverse, seekVideo time = ");
                                stringBuilder2.append(d.this.h);
                                TXCLog.i("BasicVideoDecDemuxGenerater", stringBuilder2.toString());
                            }
                            d.this.y.sendEmptyMessage(102);
                            break;
                        case 102:
                            try {
                                if (d.this.b != null) {
                                    d.this.s();
                                    c = d.this.r();
                                    if (c != null) {
                                        c = d.this.e(c);
                                        if (!c.p()) {
                                            d.this.c(c);
                                            break;
                                        }
                                        stringBuilder2 = new StringBuilder();
                                        stringBuilder2.append("is end video frame, to stop decode, mAudioDecodeEOF = ");
                                        stringBuilder2.append(d.this.w);
                                        TXCLog.i("BasicVideoDecDemuxGenerater", stringBuilder2.toString());
                                        if (!d.this.h()) {
                                            d.this.c(c);
                                        } else if (d.this.w.get()) {
                                            d.this.c(c);
                                        }
                                        d.this.y.sendEmptyMessage(103);
                                        break;
                                    }
                                    d.this.y.sendEmptyMessage(102);
                                    return;
                                }
                                return;
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                break;
                            }
                        case 103:
                            TXCLog.i("BasicVideoDecDemuxGenerater", "VideoDecodeHandler, video decode stop!");
                            d.this.y.removeMessages(102);
                            d.this.E = null;
                            d.this.N = -1;
                            d.this.P.set(false);
                            if (d.this.b != null) {
                                d.this.b.b();
                                d.this.b = null;
                                break;
                            }
                            break;
                    }
                    break;
            }
        }
    }

    public abstract void a(boolean z);

    public abstract void l();

    public abstract void m();

    public d() {
        this.V = "BasicVideoDecDemuxGenerater";
        this.k = 5;
        this.l = 6;
        this.m = 7;
        this.n = 8;
        this.o = 9;
        this.p = 10;
        this.q = 11;
        this.C = true;
        this.G = 0;
        this.H = 0;
        this.J = false;
        this.M = -1;
        this.N = -1;
        this.O = -1;
        this.W = -1;
        this.s = new LongSparseArray();
        this.r = new LongSparseArray();
        this.g = new AtomicLong(0);
        this.h = new AtomicLong(0);
        this.x = new AtomicInteger(1);
        this.t = new AtomicBoolean(false);
        this.u = new AtomicBoolean(false);
        this.v = new AtomicBoolean(false);
        this.w = new AtomicBoolean(false);
        this.P = new AtomicBoolean(false);
        this.Q = new AtomicBoolean(false);
    }

    public int n() {
        return this.a.f();
    }

    private void q() {
        this.X = new com.tencent.liteav.g.e();
        try {
            this.X.a(this.j);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private e b(e eVar) {
        if (!g.a().b()) {
            return eVar;
        }
        if (this.O < 0) {
            this.O = eVar.e();
        }
        this.M = eVar.e();
        long j = this.M - this.O;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("processReverseAudioFrame newVPts = ");
        stringBuilder.append(j);
        stringBuilder.append(", mFirstAudioFramePTS = ");
        stringBuilder.append(this.O);
        stringBuilder.append(", curAudioFrame pts = ");
        stringBuilder.append(this.M);
        TXCLog.i("BasicVideoDecDemuxGenerater", stringBuilder.toString());
        eVar.a(j);
        return eVar;
    }

    private void c(e eVar) {
        if (this.f != null) {
            this.f.a(eVar);
        }
        this.E = eVar;
    }

    private void d(e eVar) {
        if (this.e != null) {
            this.e.a(eVar);
        }
    }

    private e e(e eVar) {
        if (this.E == null) {
            TXCLog.i("BasicVideoDecDemuxGenerater", "processSpeedFrame, mLastVideoFrame is null");
            return eVar;
        } else if (eVar.p() || !com.tencent.liteav.f.g.a().c()) {
            return eVar;
        } else {
            eVar.b(this.E.t() + ((long) (((float) (eVar.e() - this.E.e())) / com.tencent.liteav.f.g.a().a(eVar.e()))));
            return eVar;
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(long j) {
        if (this.Q.get()) {
            TXCLog.e("BasicVideoDecDemuxGenerater", "seekPosition, had seeked");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("======================准备开始定位video和audio起点=====================开始时间mStartTime = ");
        stringBuilder.append(this.g);
        TXCLog.d("BasicVideoDecDemuxGenerater", stringBuilder.toString());
        this.a.a(j);
        j = this.a.p();
        this.a.c(j);
        long q = this.a.q();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("==============startVdts==========");
        stringBuilder2.append(j);
        TXCLog.d("BasicVideoDecDemuxGenerater", stringBuilder2.toString());
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("==============startAdts==========");
        stringBuilder3.append(q);
        TXCLog.d("BasicVideoDecDemuxGenerater", stringBuilder3.toString());
        this.Q.getAndSet(true);
    }

    @Nullable
    private e r() {
        e d = this.b.d();
        if (d == null || d.o() == null) {
            return null;
        }
        if (d.p()) {
            TXCLog.i("BasicVideoDecDemuxGenerater", "getDecodeVideoFrame, is end frame");
            d.j(d());
            d.k(e());
            this.v.getAndSet(true);
            return d;
        }
        long j = 0;
        e eVar = (e) this.r.get(d.e());
        if (eVar != null) {
            d = this.b.a(eVar, d);
            if (g.a().b()) {
                d.a(eVar.v());
                d.c(eVar.u());
                j = eVar.v();
            } else {
                j = d.e();
            }
        }
        StringBuilder stringBuilder;
        if (j < this.g.get()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("VideoFrame pts :");
            stringBuilder.append(j);
            stringBuilder.append(" before  startTime (");
            stringBuilder.append(this.g);
            stringBuilder.append(")");
            TXCLog.d("BasicVideoDecDemuxGenerater", stringBuilder.toString());
            return null;
        }
        if (j > this.h.get()) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("VideoFrame pts :");
            stringBuilder2.append(j);
            stringBuilder2.append(" after  duration (");
            stringBuilder2.append(this.h);
            stringBuilder2.append(")");
            TXCLog.d("BasicVideoDecDemuxGenerater", stringBuilder2.toString());
            if (g.a().b()) {
                return null;
            }
            d = this.b.b(d);
        }
        if (d.p()) {
            this.v.getAndSet(true);
            TXCLog.d("BasicVideoDecDemuxGenerater", "==================generate decode Video END==========================");
            if (this.w.get()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("================== VIDEO SEND END OF FILE ==========================");
                stringBuilder.append(d.toString());
                TXCLog.d("BasicVideoDecDemuxGenerater", stringBuilder.toString());
            } else {
                TXCLog.d("BasicVideoDecDemuxGenerater", "-------------- generate Audio NOT END ----------------");
                return d;
            }
        }
        return d;
    }

    private void s() {
        if (this.t.get()) {
            TXCLog.e("BasicVideoDecDemuxGenerater", "readVideoFrame, read video end of file, ignore");
            return;
        }
        e c = this.b.c();
        if (c != null) {
            boolean a;
            if ((this.I == 3 || this.I == 2) && this.a.r() >= this.H) {
                this.a.a(this.G);
                this.I--;
                this.J = true;
            }
            c = this.a.a(c);
            if (this.F <= 0) {
                this.F = j();
                if (this.F != 0) {
                    this.L = (1000 / this.F) * 1000;
                }
            }
            if (this.J) {
                c.a(this.K + ((long) this.L));
            }
            this.K = c.e();
            if (this.N < 0) {
                this.N = this.K;
            }
            if (g.a().b()) {
                if (c.p()) {
                    this.K = a(c);
                    this.N = this.K;
                }
                a = a(this.K, (long) this.L, c);
                if (!a) {
                    long abs = Math.abs(this.N - this.K);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("reverse newVPts = ");
                    stringBuilder.append(abs);
                    stringBuilder.append(", mFirstVideoFramePTS = ");
                    stringBuilder.append(this.N);
                    stringBuilder.append(", curFixFrame.getSampleTime() = ");
                    stringBuilder.append(this.K);
                    TXCLog.i("BasicVideoDecDemuxGenerater", stringBuilder.toString());
                    c.a(abs);
                    c.c(abs);
                    c.d(this.K);
                }
            } else {
                a = this.a.c(c);
            }
            if (a) {
                this.t.set(true);
                TXCLog.d("BasicVideoDecDemuxGenerater", "read video end");
            }
            this.r.put(c.e(), c);
            this.b.a(c);
        }
    }

    private void t() {
        if (!this.u.get()) {
            e c = this.c.c();
            if (c != null) {
                c = this.a.b(c);
                if (this.a.d(c)) {
                    this.u.set(true);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("audio endOfFile:");
                    stringBuilder.append(this.u.get());
                    TXCLog.d("BasicVideoDecDemuxGenerater", stringBuilder.toString());
                    TXCLog.d("BasicVideoDecDemuxGenerater", "read audio end");
                }
                this.s.put(c.e(), c);
                this.c.a(c);
            }
        }
    }

    private e u() {
        e d = this.c.d();
        if (d == null || d.o() == null) {
            return null;
        }
        e eVar = (e) this.s.get(d.e());
        eVar = eVar != null ? this.c.a(eVar, d) : d;
        if (eVar == null) {
            return null;
        }
        StringBuilder stringBuilder;
        if (eVar.e() >= this.g.get() || eVar.p()) {
            if (eVar.e() > this.h.get()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("AudioFrame pts :");
                stringBuilder.append(eVar.e());
                stringBuilder.append(" after  duration (");
                stringBuilder.append(this.h);
                stringBuilder.append(")");
                TXCLog.d("BasicVideoDecDemuxGenerater", stringBuilder.toString());
                eVar = this.c.b(eVar);
            }
            if (eVar.p()) {
                this.w.set(true);
                TXCLog.d("BasicVideoDecDemuxGenerater", "==================generate decode Audio END==========================");
                if (this.v.get()) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("================== AUDIO SEND END OF FILE ==========================");
                    stringBuilder.append(eVar.toString());
                    TXCLog.d("BasicVideoDecDemuxGenerater", stringBuilder.toString());
                } else {
                    TXCLog.d("BasicVideoDecDemuxGenerater", "-------------- generate VIDEO NOT END ----------------");
                    return eVar;
                }
            }
            if (this.D == null) {
                this.D = d;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("first AUDIO pts:");
                stringBuilder2.append(this.D.e());
                TXCLog.d("BasicVideoDecDemuxGenerater", stringBuilder2.toString());
            }
            this.D = eVar;
            return eVar;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("AudioFrame pts :");
        stringBuilder.append(eVar.e());
        stringBuilder.append(" before  startTime (");
        stringBuilder.append(this.g);
        stringBuilder.append(")");
        TXCLog.d("BasicVideoDecDemuxGenerater", stringBuilder.toString());
        return null;
    }

    public void a(long j, long j2) {
        this.g.getAndSet(j);
        this.h.getAndSet(j2);
    }

    public void b(long j, long j2) {
        if (j == 0 && j2 == 0) {
            this.I = 0;
            this.J = false;
        } else {
            this.I = 3;
        }
        this.G = j;
        this.H = j2;
        this.K = 0;
    }

    public void a(List<Long> list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setVideoGivenPtsList :");
        stringBuilder.append(list.size());
        Log.e("thumbnail", stringBuilder.toString());
        this.R.clear();
        this.R.addAll(list);
    }

    public boolean o() {
        return this.v.get();
    }

    public void p() {
        if (this.x.get() == 1) {
            TXCLog.e("BasicVideoDecDemuxGenerater", "getNextVideoFrame, current state is init, ignore");
        } else {
            this.y.sendEmptyMessage(102);
        }
    }

    public void b(boolean z) {
        this.C = z;
    }
}
