package com.tencent.liteav.g;

import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.e;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: VideoJoinDecAndDemuxPreview */
public class m {
    private final String a = "VideoJoinDecAndDemuxPreview";
    private t b = t.a();
    private c c;
    private a d;
    private List<i> e;
    private AtomicInteger f = new AtomicInteger(1);
    private HandlerThread g = new HandlerThread("video_handler_thread");
    private HandlerThread h;
    private b i;
    private a j;
    private i k;
    private i l;
    private long m = -1;
    private long n = -1;
    private long o = -1;
    private long p;
    private boolean q;
    private e r;
    private long s;
    private long t;
    private boolean u = true;
    private e v;
    private long w;
    private long x;
    private Object y = new Object();
    private long z;

    /* compiled from: VideoJoinDecAndDemuxPreview */
    class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 201:
                    m.this.k();
                    m.this.j.sendEmptyMessage(202);
                    return;
                case 202:
                    m.this.e();
                    return;
                case 203:
                    m.this.j.removeMessages(202);
                    m.this.l();
                    m.this.o = -1;
                    m.this.v = null;
                    m.this.t = 0;
                    return;
                case 204:
                    m.this.j.removeMessages(202);
                    m.this.o = -1;
                    m.this.v = null;
                    return;
                default:
                    return;
            }
        }
    }

    /* compiled from: VideoJoinDecAndDemuxPreview */
    class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 101:
                    m.this.m();
                    m.this.i.sendEmptyMessage(102);
                    return;
                case 102:
                    m.this.g();
                    return;
                case 103:
                    m.this.i.removeMessages(102);
                    m.this.n();
                    m.this.r = null;
                    m.this.m = -1;
                    m.this.n = -1;
                    m.this.p = -1;
                    m.this.s = 0;
                    return;
                case 104:
                    m.this.i.removeMessages(102);
                    m.this.r = null;
                    m.this.m = -1;
                    m.this.n = -1;
                    m.this.p = -1;
                    return;
                default:
                    return;
            }
        }
    }

    public m() {
        this.g.start();
        this.i = new b(this.g.getLooper());
        this.h = new HandlerThread("audio_handler_thread");
        this.h.start();
        this.j = new a(this.h.getLooper());
    }

    public void a(List<i> list) {
        this.e = list;
    }

    public void a(c cVar) {
        synchronized (this.y) {
            this.c = cVar;
        }
    }

    public void a(a aVar) {
        synchronized (this.y) {
            this.d = aVar;
        }
    }

    public synchronized void a() {
        TXCLog.e("VideoJoinDecAndDemuxPreview", "start");
        if (this.f.get() == 2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("start ignore, mCurrentState = ");
            stringBuilder.append(this.f.get());
            TXCLog.e("VideoJoinDecAndDemuxPreview", stringBuilder.toString());
            return;
        }
        this.u = true;
        this.b.l();
        this.f.set(2);
        this.i.sendEmptyMessage(101);
        this.j.sendEmptyMessage(201);
    }

    public synchronized void b() {
        TXCLog.e("VideoJoinDecAndDemuxPreview", "stop");
        if (this.f.get() == 1) {
            TXCLog.e("VideoJoinDecAndDemuxPreview", "stop(), mCurrentState in stop, ignore");
            return;
        }
        this.f.set(1);
        this.i.sendEmptyMessage(103);
        this.j.sendEmptyMessage(203);
    }

    public synchronized void c() {
        int i = this.f.get();
        if (i != 3) {
            if (i != 1) {
                this.f.set(3);
                this.i.sendEmptyMessage(104);
                this.j.sendEmptyMessage(204);
                return;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("pause ignore, current state = ");
        stringBuilder.append(i);
        TXCLog.e("VideoJoinDecAndDemuxPreview", stringBuilder.toString());
    }

    public synchronized void d() {
        int i = this.f.get();
        if (i != 1) {
            if (i != 2) {
                this.f.set(2);
                this.i.sendEmptyMessage(102);
                this.j.sendEmptyMessage(202);
                return;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("resume ignore, state = ");
        stringBuilder.append(i);
        TXCLog.e("VideoJoinDecAndDemuxPreview", stringBuilder.toString());
    }

    public void a(boolean z) {
        this.u = z;
    }

    private void e() {
        if (this.u) {
            e eVar = null;
            if (this.l.f() != null) {
                this.l.r();
                eVar = this.l.t();
                if (eVar == null) {
                    this.j.sendEmptyMessage(202);
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("before:");
                stringBuilder.append(eVar.e());
                stringBuilder.append(",after:");
                stringBuilder.append(eVar.e() + this.t);
                TXCLog.w("VideoJoinDecAndDemuxPreview", stringBuilder.toString());
                eVar.a(eVar.e() + this.t);
                if (eVar != null) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("====:");
                    stringBuilder.append(eVar.e());
                    stringBuilder.append(",len:");
                    stringBuilder.append(eVar.g());
                    stringBuilder.append(",mEOFAudioFrameUs:");
                    stringBuilder.append(this.t);
                    stringBuilder.append(",flag:");
                    stringBuilder.append(eVar.f());
                    TXCLog.i("lyj", stringBuilder.toString());
                }
            } else if (VERSION.SDK_INT >= 16) {
                eVar = f();
            }
            if (!eVar.p()) {
                if (this.v == null) {
                    this.o = System.currentTimeMillis();
                }
                this.v = eVar;
                a(eVar);
                this.j.sendEmptyMessage(202);
            } else if (this.b.k()) {
                if (this.b.j() && this.k.o()) {
                    TXCLog.i("VideoJoinDecAndDemuxPreview", "throw last audio");
                    a(eVar);
                    synchronized (this) {
                        this.f.set(1);
                    }
                }
                this.j.sendEmptyMessage(203);
            } else {
                long j = 1024000000 / ((long) this.v.j());
                this.t = this.v.e() + j;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("mEOFAudioFrameUs:");
                stringBuilder2.append(this.t);
                stringBuilder2.append(",mCurrentAudioDuration:");
                stringBuilder2.append(this.x);
                TXCLog.i("VideoJoinDecAndDemuxPreview", stringBuilder2.toString());
                if (this.t < this.x) {
                    int i = (int) ((this.x - this.t) / j);
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("count:");
                    stringBuilder3.append(i);
                    TXCLog.i("VideoJoinDecAndDemuxPreview", stringBuilder3.toString());
                    for (int i2 = 0; i2 < i; i2++) {
                        a(j);
                    }
                    this.t = this.x;
                }
                i();
                this.j.sendEmptyMessage(202);
            }
            return;
        }
        this.j.sendEmptyMessageDelayed(202, 10);
    }

    private e f() {
        if (VERSION.SDK_INT < 16) {
            return null;
        }
        MediaFormat g = t.a().g();
        if (g == null) {
            return null;
        }
        int integer = g.getInteger("sample-rate");
        int integer2 = g.getInteger("channel-count");
        long j = 1024000000 / ((long) integer);
        int i = integer2 * IjkMediaMeta.FF_PROFILE_H264_INTRA;
        String str = "audio/mp4a-latm";
        e eVar = new e(str, ByteBuffer.allocate(i), new BufferInfo());
        eVar.g(integer);
        eVar.h(integer2);
        eVar.d(i);
        long j2 = 0;
        if (this.v != null) {
            j2 = this.v.e() + j;
        }
        if (j2 >= this.x) {
            eVar.c(4);
        }
        eVar.a(j2);
        return eVar;
    }

    public void a(long j) {
        this.v.a(ByteBuffer.allocate(this.v.g()));
        e eVar = new e(this.v.a(), this.v.b(), this.v.o());
        eVar.a(this.v.c());
        eVar.b(this.v.d());
        eVar.g(this.v.j());
        eVar.h(this.v.k());
        eVar.a(this.v.e() + j);
        this.v = eVar;
        TXCLog.d("VideoJoinDecAndDemuxPreview", "------insertEmptyAudioFrame--------");
        a(eVar);
    }

    private void g() {
        if (this.m >= 0) {
            if (this.q) {
                b(this.r);
            } else if (h()) {
                b(this.r);
            } else {
                this.i.sendEmptyMessageDelayed(102, 5);
                return;
            }
        }
        this.k.q();
        e s = this.k.s();
        if (s == null) {
            this.i.sendEmptyMessage(102);
            return;
        }
        s.a(s.e() + this.s);
        StringBuilder stringBuilder;
        if (s.p()) {
            if (this.b.j()) {
                if (this.b.k() && this.l.p()) {
                    TXCLog.i("VideoJoinDecAndDemuxPreview", "throw last video");
                    b(s);
                    this.f.set(1);
                    this.w = 0;
                }
                this.i.sendEmptyMessage(103);
            } else {
                this.s = this.r.e();
                stringBuilder = new StringBuilder();
                stringBuilder.append("mEOFVideoFrameUs:");
                stringBuilder.append(this.s);
                stringBuilder.append(",mCurrentVideoDuration:");
                stringBuilder.append(this.w);
                TXCLog.i("VideoJoinDecAndDemuxPreview", stringBuilder.toString());
                if (this.s != this.w) {
                    this.s = this.w;
                }
                j();
                this.i.sendEmptyMessage(102);
            }
        } else if (s.e() > this.w) {
            TXCLog.i("VideoJoinDecAndDemuxPreview", "dropOne");
            this.i.sendEmptyMessage(102);
        } else {
            this.r = s;
            this.m = s.e() / 1000;
            if (this.n < 0) {
                this.n = this.m;
                if (this.o > 0) {
                    this.p = this.o;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("mTimelineMs get AudioFrame:");
                    stringBuilder.append(this.o);
                    TXCLog.i("VideoJoinDecAndDemuxPreview", stringBuilder.toString());
                } else {
                    this.p = System.currentTimeMillis();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("mTimelineMs get SystemTime:");
                    stringBuilder.append(this.p);
                    TXCLog.i("VideoJoinDecAndDemuxPreview", stringBuilder.toString());
                }
                this.q = true;
                this.i.sendEmptyMessage(102);
            } else {
                this.q = false;
                this.i.sendEmptyMessageDelayed(102, 5);
            }
        }
    }

    private boolean h() {
        long currentTimeMillis = System.currentTimeMillis();
        this.m = this.r.e() / 1000;
        return Math.abs(this.m - this.n) < currentTimeMillis - this.p;
    }

    private void i() {
        if (this.b.i()) {
            this.l = this.b.e();
            this.x += this.l.j();
            this.j.sendEmptyMessage(202);
            return;
        }
        TXCLog.e("VideoJoinDecAndDemuxPreview", "isAllReadEOF");
        this.j.sendEmptyMessage(203);
    }

    private void j() {
        if (this.b.f()) {
            this.k = this.b.d();
            this.w += this.k.j();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Duration :");
            stringBuilder.append(this.k.j());
            TXCLog.i("VideoJoinDecAndDemuxPreview", stringBuilder.toString());
            this.i.sendEmptyMessage(102);
            return;
        }
        TXCLog.e("VideoJoinDecAndDemuxPreview", "isAllReadEOF");
        this.i.sendEmptyMessage(103);
    }

    private void k() {
        TXCLog.i("VideoJoinDecAndDemuxPreview", "startAudioDecoder");
        List c = t.a().c();
        for (int i = 0; i < c.size(); i++) {
            i iVar = (i) c.get(i);
            iVar.d();
            iVar.m();
        }
        this.l = this.b.e();
        this.x = this.l.j();
    }

    private void l() {
        TXCLog.i("VideoJoinDecAndDemuxPreview", "stopAudioDecoder");
        List c = t.a().c();
        for (int i = 0; i < c.size(); i++) {
            ((i) c.get(i)).n();
        }
    }

    private void m() {
        TXCLog.i("VideoJoinDecAndDemuxPreview", "startVideoDecoder");
        List c = t.a().c();
        for (int i = 0; i < c.size(); i++) {
            i iVar = (i) c.get(i);
            iVar.c();
            iVar.k();
        }
        this.k = this.b.d();
        this.w = this.k.j();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Duration :");
        stringBuilder.append(this.k.j());
        TXCLog.i("VideoJoinDecAndDemuxPreview", stringBuilder.toString());
    }

    private void n() {
        TXCLog.i("VideoJoinDecAndDemuxPreview", "stopVideoDecoder");
        List c = t.a().c();
        for (int i = 0; i < c.size(); i++) {
            ((i) c.get(i)).l();
        }
    }

    private void a(e eVar) {
        synchronized (this.y) {
            if (this.d != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("source:");
                stringBuilder.append(this.l.a);
                stringBuilder.append(",throwOutAudioFrame: ");
                stringBuilder.append(eVar.e());
                TXCLog.d("VideoJoinDecAndDemuxPreview", stringBuilder.toString());
                this.d.a(eVar, this.l);
            }
        }
    }

    private void b(e eVar) {
        synchronized (this.y) {
            if (!(this.c == null || eVar.e() == this.z)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("source:");
                stringBuilder.append(this.k.a);
                stringBuilder.append(",throwOutVideoFrame: ");
                stringBuilder.append(eVar.e());
                TXCLog.d("VideoJoinDecAndDemuxPreview", stringBuilder.toString());
                this.c.a(eVar, this.k);
                this.z = eVar.e();
            }
        }
    }
}
