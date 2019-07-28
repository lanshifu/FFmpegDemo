package com.tencent.liteav.f;

import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.e;
import com.tencent.liteav.e.i;
import com.tencent.liteav.videoediter.audio.TXJNIAudioResampler;
import com.tencent.liteav.videoediter.audio.c;
import com.tencent.liteav.videoediter.audio.d;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: AudioPreprocessChain */
public class b {
    public e a;
    private final String b = "AudioPreprocessChain";
    private d c;
    private com.tencent.liteav.videoediter.audio.e d;
    private TXJNIAudioResampler e;
    private c f;
    private i g;
    private int h;
    private int i;
    private LinkedList<Long> j = new LinkedList();
    private long k = -1;
    private long l = -1;
    private int m = 0;
    private com.tencent.liteav.c.b n = com.tencent.liteav.c.b.a();
    private com.tencent.liteav.d.b o;
    private float p;
    private g q = g.a();
    private boolean r = true;
    private HandlerThread s;
    private a t;
    private AtomicBoolean u = new AtomicBoolean(false);
    private final AtomicBoolean v = new AtomicBoolean(false);
    private Object w = new Object();
    private long x;
    private double y;
    private double z;

    /* compiled from: AudioPreprocessChain */
    class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            if (message.what == 10000) {
                a();
            }
        }

        private void a() {
            if (!b.this.v.get()) {
                if (b.this.u.get()) {
                    e c;
                    synchronized (b.this.w) {
                        c = b.this.f.c();
                        boolean b = b.this.f.b();
                    }
                    if (c == null && b) {
                        b.this.v.set(true);
                        e b2 = b();
                        if (b.this.g != null) {
                            b.this.g.a(b2);
                            return;
                        }
                    }
                    if (!(c == null || c.b() == null)) {
                        float a = b.this.b(c.g());
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("BgmHandler, bgmVolume = ");
                        stringBuilder.append(a);
                        TXCLog.i("AudioPreprocessChain", stringBuilder.toString());
                        if (a != 1.0f) {
                            b.this.c.a(a);
                            c = b.this.a(c.b(), b.this.c.a(com.tencent.liteav.videoediter.audio.b.a(c.b(), c.g())), c.e());
                        }
                        long b3 = b.this.a(c.g());
                        if (b3 == -1) {
                            b3 = 0;
                        }
                        c.a(b3);
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("BgmHandler pts:");
                        stringBuilder2.append(b3);
                        stringBuilder2.append(", duration:");
                        stringBuilder2.append(b.this.x);
                        TXCLog.i("AudioPreprocessChain", stringBuilder2.toString());
                        if (b.this.x == 0 && b.this.a != null) {
                            b.this.x = b.this.a.e();
                        }
                        if (b3 >= b.this.x) {
                            b.this.v.set(true);
                            e b4 = b();
                            if (b.this.g != null) {
                                b.this.g.a(b4);
                                return;
                            }
                        }
                        c.a(b3);
                        if (b.this.g != null) {
                            b.this.g.a(c);
                        }
                    }
                    sendEmptyMessageDelayed(10000, 10);
                    return;
                }
                sendEmptyMessageDelayed(10000, 10);
            }
        }

        private e b() {
            e eVar = new e();
            eVar.d(0);
            eVar.a(0);
            eVar.c(4);
            return eVar;
        }
    }

    public void a() {
        TXCLog.d("AudioPreprocessChain", "initFilter");
        this.e = new TXJNIAudioResampler();
        this.d = new com.tencent.liteav.videoediter.audio.e();
        synchronized (this.w) {
            this.f = new c();
        }
        this.c = new d();
        this.p = 1.0f;
        this.e.setSpeed(this.p);
    }

    public void b() {
        TXCLog.d("AudioPreprocessChain", "destroyFilter");
        this.k = -1;
        this.l = -1;
        this.m = 0;
        if (this.e != null) {
            this.e.destroy();
            this.e = null;
        }
        synchronized (this.w) {
            if (this.f != null) {
                this.f.d();
                this.f = null;
            }
        }
        if (this.d != null) {
            this.d = null;
        }
        if (this.j != null) {
            this.j.clear();
        }
    }

    public void a(MediaFormat mediaFormat) {
        if (mediaFormat == null) {
            TXCLog.e("AudioPreprocessChain", "setAudioFormat audioFormat is null");
            return;
        }
        this.o = new com.tencent.liteav.d.b();
        if (VERSION.SDK_INT >= 16) {
            this.o.b = mediaFormat.getInteger("sample-rate");
            this.o.a = mediaFormat.getInteger("channel-count");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setAudioFormat sampleRate:");
            stringBuilder.append(this.o.b);
            TXCLog.i("AudioPreprocessChain", stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("setAudioFormat channelCount:");
            stringBuilder.append(this.o.a);
            TXCLog.i("AudioPreprocessChain", stringBuilder.toString());
        }
        if (!(this.h == 0 || this.i == 0)) {
            this.e.setChannelCount(this.o.a);
            this.d.a(this.h, this.o.a);
            this.e.setSampleRate(this.i, this.o.b);
        }
        if (this.f != null) {
            this.f.a(mediaFormat);
        }
    }

    public void c() {
        TXCLog.i("AudioPreprocessChain", "start");
        if (TextUtils.isEmpty(this.n.a)) {
            this.n.h = false;
            return;
        }
        this.n.h = true;
        this.u.set(true);
        a(this.n.a);
        if (!(this.n.b == -1 || this.n.c == -1)) {
            a(this.n.b, this.n.c);
        }
        a(this.n.e);
        a(this.n.f);
        b(this.n.g);
        a(this.n.d);
        this.y = 0.0d;
        if (this.n.j) {
            k();
        }
    }

    private void k() {
        if (this.n.l > 0) {
            StringBuilder stringBuilder;
            if (this.n.e) {
                double d = (double) this.x;
                Double.isNaN(d);
                this.z = d / 1000000.0d;
                stringBuilder = new StringBuilder();
                stringBuilder.append("getBgmEndTimePts, is loop, mBgmEndTimeSec = ");
                stringBuilder.append(this.z);
                TXCLog.i("AudioPreprocessChain", stringBuilder.toString());
            } else {
                long j = (this.n.c * 1000) - (this.n.b * 1000);
                double d2 = (double) (this.x > j ? j : this.x);
                Double.isNaN(d2);
                this.z = d2 / 1000000.0d;
                stringBuilder = new StringBuilder();
                stringBuilder.append("getBgmEndTimePts, not loop, mVideoDurationUs = ");
                stringBuilder.append(this.x);
                stringBuilder.append(", bgmDurationUs = ");
                stringBuilder.append(j);
                stringBuilder.append(", so mBgmEndTimeSec = ");
                stringBuilder.append(this.z);
                TXCLog.i("AudioPreprocessChain", stringBuilder.toString());
            }
        }
    }

    public void d() {
        TXCLog.i("AudioPreprocessChain", "stop");
        if (!this.r) {
            if (this.t != null) {
                this.t.removeCallbacksAndMessages(null);
                this.s.quit();
            }
            this.v.set(true);
            this.s = null;
            this.t = null;
        }
    }

    public int e() {
        if (this.r) {
            TXCLog.w("AudioPreprocessChain", "tryStartAddBgmForNoAudioTrack, this has audio track, ignore!");
            return -1;
        }
        if (this.s == null) {
            this.s = new HandlerThread("bgm_handler_thread");
            this.s.start();
            this.t = new a(this.s.getLooper());
        }
        this.v.set(false);
        this.t.sendEmptyMessage(10000);
        return 0;
    }

    public void f() {
        TXCLog.i("AudioPreprocessChain", "pause");
        this.u.set(false);
    }

    public void g() {
        TXCLog.i("AudioPreprocessChain", "resume");
        this.u.set(true);
    }

    public int a(String str) {
        int a;
        try {
            a = this.f.a(str);
        } catch (IOException e) {
            e.printStackTrace();
            a = -1;
        }
        this.f.a();
        if (TextUtils.isEmpty(str)) {
            this.n.h = false;
        } else {
            this.n.h = true;
        }
        return a;
    }

    public void a(long j, long j2) {
        if (this.f != null) {
            this.f.a(j, j2);
        }
    }

    public void a(boolean z) {
        if (this.f != null) {
            this.f.a(z);
        }
    }

    public void a(long j) {
        this.n.d = j;
    }

    public void a(float f) {
        if (this.f != null) {
            this.f.b(f);
        }
    }

    public void b(float f) {
        if (this.f != null) {
            this.f.a(f);
        }
    }

    public void b(long j) {
        this.x = j;
    }

    public MediaFormat h() {
        return this.f.e();
    }

    public void a(i iVar) {
        this.g = iVar;
    }

    public void a(e eVar) {
        if (eVar == null) {
            TXCLog.e("AudioPreprocessChain", "processFrame, frame is null");
        } else if (eVar.q() || eVar.r()) {
            TXCLog.i("AudioPreprocessChain", "processFrame, frame is isUnNormallFrame");
            if (this.g != null) {
                this.g.a(eVar);
            }
        } else if (eVar.p()) {
            TXCLog.e("AudioPreprocessChain", "processFrame, frame is end");
            if (this.g != null) {
                this.g.a(eVar);
            }
        } else {
            if (this.q.c() || this.i != this.o.b) {
                this.p = this.q.a(eVar.e());
                this.e.setSpeed(this.p);
                if (this.k == -1) {
                    this.k = eVar.e();
                }
                this.j.add(l());
            } else {
                this.j.add(Long.valueOf(eVar.e()));
            }
            eVar = a(eVar.b(), b(eVar));
            if (this.g != null) {
                this.g.a(eVar);
            }
        }
    }

    public void i() {
        if (this.e != null) {
            short[] flushBuffer = this.e.flushBuffer();
            if (!(this.i == this.o.b || this.o.a != 2 || flushBuffer == null)) {
                this.d.a(1, 2);
                flushBuffer = this.d.a(flushBuffer);
            }
            e eVar = null;
            if (flushBuffer != null && flushBuffer.length > 0) {
                this.j.add(Long.valueOf(l().longValue()));
                eVar = a(null, flushBuffer);
            }
            if (eVar != null && this.g != null) {
                this.g.a(eVar);
            }
        }
    }

    private Long l() {
        long j;
        if (this.m == 0) {
            j = this.k;
        } else {
            j = this.k + ((((long) this.m) * 1024000000) / ((long) this.o.b));
        }
        this.m++;
        return Long.valueOf(j);
    }

    private long a(int i) {
        long j;
        if (this.l == -1) {
            j = this.k;
        } else {
            j = this.l;
        }
        this.l = ((((long) i) * 1000000) / ((long) ((this.o.b * this.o.a) * 2))) + j;
        return j;
    }

    private e a(ByteBuffer byteBuffer, short[] sArr) {
        if (sArr == null || sArr.length == 0) {
            return null;
        }
        if (this.j == null || this.j.size() == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("doMixer mTimeQueue:");
            stringBuilder.append(this.j);
            TXCLog.e("AudioPreprocessChain", stringBuilder.toString());
            return null;
        }
        long longValue = ((Long) this.j.pollFirst()).longValue();
        if (this.n.h) {
            if (longValue >= this.n.d) {
                this.f.a(b(sArr.length * 2));
                sArr = this.f.a(sArr);
            } else {
                this.c.a(this.n.f);
                sArr = this.c.a(sArr);
            }
            return a(byteBuffer, sArr, longValue);
        }
        this.c.a(this.n.f);
        return a(byteBuffer, this.c.a(sArr), longValue);
    }

    private float b(int i) {
        if (!this.n.j) {
            return this.n.g;
        }
        double d = this.y;
        double d2 = (double) i;
        double d3 = (double) (this.o.b * this.o.a);
        Double.isNaN(d3);
        d3 *= 2.0d;
        Double.isNaN(d2);
        this.y = d + (d2 / d3);
        float f = ((float) this.n.k) / 1000.0f;
        float f2 = ((float) this.n.l) / 1000.0f;
        if (this.n.k <= 0 || this.y > ((double) f)) {
            if (this.n.l > 0) {
                double d4 = this.y;
                double d5 = this.z;
                double d6 = (double) f2;
                Double.isNaN(d6);
                if (d4 >= d5 - d6) {
                    d = Math.log10((this.z + 1.0d) - this.y) / Math.log10(Math.pow(10.0d, Math.log10((double) (f2 + 1.0f)) / 1.0d));
                }
            }
            d = 1.0d;
        } else {
            d = Math.log10(this.y + 1.0d) / Math.log10(Math.pow(10.0d, Math.log10((double) (f + 1.0f)) / 1.0d));
        }
        if (d < 0.0d) {
            d = 0.0d;
        } else if (d > 1.0d) {
            d = 1.0d;
        }
        d2 = (double) this.n.g;
        Double.isNaN(d2);
        return (float) (d2 * d);
    }

    private e a(ByteBuffer byteBuffer, short[] sArr, long j) {
        int length = sArr.length * 2;
        byteBuffer = com.tencent.liteav.videoediter.audio.b.a(byteBuffer, sArr);
        e eVar = new e();
        eVar.d(length);
        eVar.a(byteBuffer);
        eVar.h(this.o.a);
        eVar.g(this.o.b);
        eVar.b(j);
        eVar.a(j);
        return eVar;
    }

    private short[] b(e eVar) {
        c(eVar);
        short[] a = com.tencent.liteav.videoediter.audio.b.a(eVar.b(), eVar.g());
        if (this.i == this.o.b || this.o.a != 2) {
            if (this.h != this.o.a) {
                a = this.d.a(a);
            }
            if (!(this.p == 1.0f && this.i == this.o.b)) {
                a = this.e.resample(a);
            }
            return a;
        }
        if (this.h == 2) {
            this.d.a(2, 1);
            if (a != null) {
                a = this.d.a(a);
            }
        }
        if (!(this.p == 1.0f && this.i == this.o.b)) {
            a = this.e.resample(a);
        }
        if (a != null) {
            this.d.a(1, 2);
            a = this.d.a(a);
        }
        return a;
    }

    private void c(e eVar) {
        if (this.h != eVar.k()) {
            this.h = eVar.k();
            TXCLog.i("AudioPreprocessChain", "setAudioFormat initResampler setChannelCount");
            this.e.setChannelCount(this.o.a);
            this.d.a(this.h, this.o.a);
        }
        if (this.i != eVar.j()) {
            this.i = eVar.j();
            TXCLog.i("AudioPreprocessChain", "setAudioFormat initResampler setSampleRate");
            this.e.setSampleRate(this.i, this.o.b);
        }
    }

    public void b(boolean z) {
        this.r = z;
    }

    public void c(boolean z) {
        this.u.set(z);
    }

    public boolean j() {
        return this.v.get();
    }
}
