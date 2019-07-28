package com.tencent.liteav.g;

import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.e;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: VideoJoinDecAndDemuxGenerate */
public class l {
    private final String a = "VideoJoinDecAndDemuxGenerate";
    private c b;
    private a c;
    private j d;
    private AtomicInteger e = new AtomicInteger(1);
    private HandlerThread f = new HandlerThread("video_handler_thread");
    private HandlerThread g;
    private b h;
    private a i;
    private i j;
    private i k;
    private e l;
    private long m;
    private long n;
    private boolean o = true;
    private e p;
    private long q;
    private long r;

    /* compiled from: VideoJoinDecAndDemuxGenerate */
    class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 201:
                    l.this.d();
                    l.this.i.sendEmptyMessage(202);
                    return;
                case 202:
                    l.this.f();
                    return;
                case 203:
                    l.this.i.removeMessages(202);
                    l.this.e();
                    return;
                default:
                    return;
            }
        }
    }

    /* compiled from: VideoJoinDecAndDemuxGenerate */
    class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 101:
                    l.this.k();
                    l.this.h.sendEmptyMessage(102);
                    return;
                case 102:
                    l.this.h();
                    return;
                case 103:
                    l.this.h.removeMessages(102);
                    l.this.l();
                    return;
                case 104:
                    l.this.h.removeMessages(102);
                    return;
                default:
                    return;
            }
        }
    }

    public l() {
        this.f.start();
        this.h = new b(this.f.getLooper());
        this.g = new HandlerThread("audio_handler_thread");
        this.g.start();
        this.i = new a(this.g.getLooper());
    }

    public void a(j jVar) {
        this.d = jVar;
    }

    public void a(c cVar) {
        this.b = cVar;
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public synchronized void a() {
        TXCLog.e("VideoJoinDecAndDemuxGenerate", "start");
        if (this.e.get() == 2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("start ignore, mCurrentState = ");
            stringBuilder.append(this.e.get());
            TXCLog.e("VideoJoinDecAndDemuxGenerate", stringBuilder.toString());
            return;
        }
        this.q = 0;
        this.r = 0;
        this.m = 0;
        this.n = 0;
        this.p = null;
        this.l = null;
        this.d.h();
        this.e.set(2);
        this.h.sendEmptyMessage(101);
        this.i.sendEmptyMessage(201);
    }

    public synchronized void b() {
        TXCLog.e("VideoJoinDecAndDemuxGenerate", "stop");
        if (this.e.get() == 1) {
            TXCLog.e("VideoJoinDecAndDemuxGenerate", "stop(), mCurrentState in stop, ignore");
            return;
        }
        this.e.set(1);
        this.h.sendEmptyMessage(103);
        this.i.sendEmptyMessage(203);
    }

    public synchronized void c() {
        if (this.e.get() == 1) {
            TXCLog.e("VideoJoinDecAndDemuxGenerate", "getNextVideoFrame, current state is init, ignore");
        } else {
            this.h.sendEmptyMessage(102);
        }
    }

    public void a(boolean z) {
        this.o = z;
    }

    private void d() {
        TXCLog.i("VideoJoinDecAndDemuxGenerate", "startAudioDecoder");
        List a = this.d.a();
        for (int i = 0; i < a.size(); i++) {
            i iVar = (i) a.get(i);
            iVar.d();
            iVar.m();
        }
        this.k = this.d.c();
        this.r = this.k.j();
    }

    private void e() {
        TXCLog.i("VideoJoinDecAndDemuxGenerate", "stopAudioDecoder");
        List a = this.d.a();
        for (int i = 0; i < a.size(); i++) {
            ((i) a.get(i)).n();
        }
    }

    private void f() {
        if (this.o) {
            e eVar = null;
            if (this.k.f() != null) {
                this.k.r();
                eVar = this.k.t();
                if (eVar == null) {
                    this.i.sendEmptyMessage(202);
                    return;
                }
                eVar.a(eVar.e() + this.n);
            } else if (VERSION.SDK_INT >= 16) {
                eVar = g();
            }
            if (!eVar.p()) {
                this.p = eVar;
                a(eVar);
                this.i.sendEmptyMessage(202);
            } else if (this.d.g()) {
                if (this.d.f() && this.j.o()) {
                    TXCLog.i("VideoJoinDecAndDemuxGenerate", "throw last audio");
                    a(eVar);
                }
                this.i.sendEmptyMessage(203);
            } else {
                long j = 1024000000 / ((long) this.p.j());
                this.n = this.p.e() + j;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("mEOFAudioFrameUs:");
                stringBuilder.append(this.n);
                stringBuilder.append(",mCurrentAudioDuration:");
                stringBuilder.append(this.r);
                TXCLog.i("VideoJoinDecAndDemuxGenerate", stringBuilder.toString());
                if (this.n < this.r) {
                    int i = (int) ((this.r - this.n) / j);
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("count:");
                    stringBuilder2.append(i);
                    TXCLog.i("VideoJoinDecAndDemuxGenerate", stringBuilder2.toString());
                    for (int i2 = 0; i2 < i; i2++) {
                        a(j);
                    }
                    this.n = this.r;
                }
                i();
                this.i.sendEmptyMessage(202);
            }
            return;
        }
        this.i.sendEmptyMessageDelayed(202, 10);
    }

    private e g() {
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
        if (this.p != null) {
            j2 = this.p.e() + j;
        }
        if (j2 >= this.r) {
            eVar.c(4);
        }
        eVar.a(j2);
        return eVar;
    }

    public void a(long j) {
        ByteBuffer allocate = ByteBuffer.allocate(this.p.g());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mCurrentAudioFrame.getLength():");
        stringBuilder.append(this.p.g());
        Log.i("lyj", stringBuilder.toString());
        this.p.a(allocate);
        e eVar = new e(this.p.a(), this.p.b(), this.p.o());
        eVar.a(this.p.c());
        eVar.b(this.p.d());
        eVar.g(this.p.j());
        long e = this.p.e() + j;
        eVar.a(e);
        eVar.b(e);
        eVar.c(e);
        eVar.c(this.p.f());
        this.p = eVar;
        a(eVar);
    }

    private void h() {
        this.j.q();
        e s = this.j.s();
        if (s == null) {
            this.h.sendEmptyMessage(102);
            return;
        }
        if (this.m != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("before:");
            stringBuilder.append(s.e());
            stringBuilder.append(",after:");
            stringBuilder.append(s.e() + this.m);
            TXCLog.i("VideoJoinDecAndDemuxGenerate", stringBuilder.toString());
        }
        s.a(s.e() + this.m);
        if (s.p()) {
            if (this.d.f()) {
                if (this.d.g() && this.k.p()) {
                    TXCLog.i("VideoJoinDecAndDemuxGenerate", "throw last video");
                    b(s);
                }
                this.h.sendEmptyMessage(103);
            } else {
                this.m = this.l.e();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("mEOFVideoFrameUs:");
                stringBuilder2.append(this.m);
                stringBuilder2.append(",mCurrentVideoDuration:");
                stringBuilder2.append(this.q);
                TXCLog.i("VideoJoinDecAndDemuxGenerate", stringBuilder2.toString());
                if (this.m != this.q) {
                    this.m = this.q;
                }
                j();
                this.h.sendEmptyMessage(102);
            }
        } else if (s.e() > this.q) {
            TXCLog.i("VideoJoinDecAndDemuxGenerate", "dropOne");
            c();
        } else {
            this.l = s;
            b(s);
        }
    }

    private void i() {
        if (this.d.e()) {
            this.k = this.d.c();
            this.r += this.k.j();
            return;
        }
        TXCLog.e("VideoJoinDecAndDemuxGenerate", "nextAudioExtractorConfig isAllReadEOF");
    }

    private void j() {
        if (this.d.d()) {
            this.j = this.d.b();
            this.q += this.j.j();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Duration :");
            stringBuilder.append(this.k.j());
            TXCLog.i("VideoJoinDecAndDemuxGenerate", stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("AudioDuration :");
            stringBuilder.append(this.j.i());
            stringBuilder.append(", VideoDuration:");
            stringBuilder.append(this.j.h());
            TXCLog.i("VideoJoinDecAndDemuxGenerate", stringBuilder.toString());
            return;
        }
        TXCLog.e("VideoJoinDecAndDemuxGenerate", "nextVideoExtractorConfig isAllReadEOF");
    }

    private void k() {
        TXCLog.i("VideoJoinDecAndDemuxGenerate", "startVideoDecoder");
        List a = this.d.a();
        for (int i = 0; i < a.size(); i++) {
            i iVar = (i) a.get(i);
            iVar.c();
            iVar.k();
        }
        this.j = this.d.b();
        this.q = this.j.j();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Duration :");
        stringBuilder.append(this.j.j());
        TXCLog.i("VideoJoinDecAndDemuxGenerate", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("AudioDuration :");
        stringBuilder.append(this.j.i());
        stringBuilder.append(", VideoDuration:");
        stringBuilder.append(this.j.h());
        TXCLog.i("VideoJoinDecAndDemuxGenerate", stringBuilder.toString());
    }

    private void l() {
        TXCLog.i("VideoJoinDecAndDemuxGenerate", "stopVideoDecoder");
        List a = this.d.a();
        for (int i = 0; i < a.size(); i++) {
            ((i) a.get(i)).l();
        }
    }

    private void a(e eVar) {
        if (this.c != null) {
            this.c.a(eVar, this.k);
        }
    }

    private void b(e eVar) {
        if (this.b != null) {
            this.b.a(eVar, this.j);
        }
    }
}
