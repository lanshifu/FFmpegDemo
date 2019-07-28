package com.tencent.liteav.e;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.opengl.EGLContext;
import android.view.Surface;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.c.i;
import com.tencent.liteav.c.j;
import com.tencent.liteav.c.k;
import com.tencent.liteav.d.g;
import com.tencent.liteav.j.b;
import com.tencent.liteav.muxer.c;
import com.tencent.liteav.videoencoder.TXSVideoEncoderParam;
import com.tencent.liteav.videoencoder.d;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: BasicVideoGenerate */
public abstract class e {
    private m A = new m() {
        public void a(boolean z) {
        }

        public void a(Surface surface) {
            TXCLog.i("BasicVideoGenerate", "IVideoRenderListener onSurfaceTextureAvailable");
            e.this.p = surface;
            if (e.this.f != null) {
                e.this.f.a();
                e.this.f.b();
            }
        }

        public void a(int i, int i2) {
            if (e.this.f != null) {
                g gVar = new g();
                int e = j.a().e();
                if (e == 90 || e == 270) {
                    gVar.a = i2;
                    gVar.b = i;
                } else {
                    gVar.a = i;
                    gVar.b = i2;
                }
                e.this.f.a(gVar);
            }
        }

        public void b(Surface surface) {
            TXCLog.i("BasicVideoGenerate", "IVideoRenderListener onSurfaceTextureDestroy");
            e.this.e();
            e.this.p = null;
            if (e.this.f != null) {
                e.this.f.c();
                e.this.f.d();
            }
            if (e.this.o != null) {
                e.this.o.a();
            }
        }

        public int a(int i, float[] fArr, com.tencent.liteav.d.e eVar) {
            b.e();
            if (e.this.o != null) {
                i = e.this.o.a(eVar, com.tencent.liteav.c.e.a().b(), eVar.r());
                eVar.l(i);
                eVar.m(0);
            }
            if (e.this.f != null) {
                e.this.f.a(fArr);
                e.this.f.a(i, eVar);
            }
            return 0;
        }
    };
    private l B = new l() {
        public void a(int i, com.tencent.liteav.d.e eVar) {
            b.c();
            long a = com.tencent.liteav.j.e.a(eVar);
            com.tencent.liteav.j.e.a().d(a);
            if (eVar.p()) {
                if (e.this.k.b()) {
                    if (e.this.k.j()) {
                        synchronized (e.this.v) {
                            if (e.this.h != null) {
                                e.this.h.b();
                                TXCLog.d("BasicVideoGenerate", "TailWaterMarkListener signalEOSAndFlush");
                                return;
                            }
                        }
                    }
                    if (e.this.s != null) {
                        e.this.s.remove(eVar);
                    }
                    if (!e.this.l.l() || ((k.a().d() != 2 && (k.a().d() != 1 || e.this.c.h())) || (e.this.g != null && e.this.g.j()))) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Encount EOF Video didProcessFrame appendTailWaterMark, mLastVideoFrame = ");
                        stringBuilder.append(e.this.u);
                        TXCLog.i("BasicVideoGenerate", stringBuilder.toString());
                        e.this.k.a = e.this.u;
                        e.this.k.b = e.this.t;
                        e.this.k.d();
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("mLastVideoFrame width, height = ");
                        stringBuilder.append(e.this.u.m());
                        stringBuilder.append(", ");
                        stringBuilder.append(e.this.u.n());
                        TXCLog.i("BasicVideoGenerate", stringBuilder.toString());
                        return;
                    }
                    TXCLog.w("BasicVideoGenerate", "Encount EOF Video Has No Audio Append BGM,BGM is not end");
                    return;
                } else if (!e.this.l.l() || ((k.a().d() != 2 && (k.a().d() != 1 || e.this.c.h())) || (e.this.g != null && e.this.g.j()))) {
                    synchronized (e.this.v) {
                        if (e.this.h != null) {
                            e.this.h.b();
                            TXCLog.d("BasicVideoGenerate", "signalEOSAndFlush");
                            return;
                        }
                    }
                } else {
                    TXCLog.w("BasicVideoGenerate", "Encount EOF Video Has No Audio Append BGM,BGM is not end");
                    return;
                }
            }
            synchronized (e.this.v) {
                if (e.this.h != null) {
                    e.this.h.b(i, eVar.m(), eVar.n(), a / 1000);
                }
            }
            if (e.this.l.e()) {
                try {
                    e.this.s.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (k.a().d() == 1) {
                    e.this.c.p();
                }
            } else if (k.a().d() == 1) {
                if (!e.this.c.o()) {
                    e.this.c.p();
                } else if (e.this.k.b()) {
                    e.this.k.f();
                }
            } else if (k.a().d() == 2) {
                if (!e.this.d.c()) {
                    e.this.d.h();
                } else if (e.this.k.b()) {
                    e.this.k.f();
                }
            }
            e.this.u = eVar;
        }

        public int b(int i, com.tencent.liteav.d.e eVar) {
            return e.this.a(i, eVar.m(), eVar.n(), eVar.e());
        }
    };
    private i C = new i() {
        public void a(com.tencent.liteav.d.e eVar) {
            if (eVar != null) {
                b.d();
                com.tencent.liteav.j.e.a().c(eVar.e());
                if (eVar.p()) {
                    if (e.this.k.b()) {
                        if (e.this.k.j()) {
                            synchronized (e.this.v) {
                                if (e.this.h != null) {
                                    e.this.h.b();
                                    TXCLog.d("BasicVideoGenerate", "Encount EOF TailWaterMarkListener signalEOSAndFlush");
                                    return;
                                }
                            }
                        } else if (!e.this.l.l() || ((k.a().d() != 2 || e.this.d.c()) && (k.a().d() != 1 || e.this.c.h() || e.this.c.o()))) {
                            TXCLog.i("BasicVideoGenerate", "Encount EOF Audio didProcessFrame appendTailWaterMark");
                            e.this.k.a = e.this.u;
                            e.this.k.b = e.this.t;
                            e.this.k.d();
                            return;
                        } else {
                            TXCLog.w("BasicVideoGenerate", "Encount EOF Video Has No Audio Append BGM,Video is not end");
                            return;
                        }
                    } else if ((k.a().d() == 2 || (k.a().d() == 1 && !e.this.c.h())) && ((e.this.d != null && e.this.d.c()) || (e.this.c != null && e.this.c.o()))) {
                        synchronized (e.this.v) {
                            if (e.this.h != null) {
                                e.this.h.b();
                                TXCLog.d("BasicVideoGenerate", "signalEOSAndFlush");
                                return;
                            }
                        }
                    }
                }
                if (e.this.q != null) {
                    e.this.q.a(eVar);
                }
                if (e.this.g != null) {
                    e.this.g.i();
                }
                e.this.t = eVar;
            }
        }
    };
    private d D = new d() {
        public void onEncodeNAL(com.tencent.liteav.basic.g.b bVar, int i) {
            if (i != 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("mVideoEncodeListener, errCode = ");
                stringBuilder.append(i);
                TXCLog.i("BasicVideoGenerate", stringBuilder.toString());
                return;
            }
            b.f();
            if (e.this.l.e()) {
                TXCLog.i("BasicVideoGenerate", "mVideoEncodeListener, input is full, output is full");
            } else if (bVar == null || bVar.nalData == null) {
                TXCLog.i("BasicVideoGenerate", "===Video onEncodeComplete===");
                e.this.b();
                e.this.d();
            } else {
                com.tencent.liteav.d.e eVar = null;
                try {
                    eVar = (com.tencent.liteav.d.e) e.this.s.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (eVar.p()) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("===Video onEncodeComplete===:");
                    stringBuilder2.append(eVar.p());
                    stringBuilder2.append(", nal:");
                    stringBuilder2.append(bVar);
                    TXCLog.i("BasicVideoGenerate", stringBuilder2.toString());
                    e.this.b();
                    e.this.d();
                    return;
                }
                synchronized (this) {
                    if (!(e.this.i == null || bVar == null || bVar.nalData == null)) {
                        if (e.this.r) {
                            a(bVar, eVar);
                        } else if (bVar.nalType == 0) {
                            MediaFormat a = com.tencent.liteav.basic.util.b.a(bVar.nalData, e.this.l.h.a, e.this.l.h.b);
                            if (a != null) {
                                e.this.i.a(a);
                                e.this.i.a();
                                e.this.r = true;
                            }
                            a(bVar, eVar);
                        }
                    }
                }
                e.this.a(eVar.t());
            }
        }

        private void a(com.tencent.liteav.basic.g.b bVar, com.tencent.liteav.d.e eVar) {
            long a = com.tencent.liteav.j.e.a(eVar);
            com.tencent.liteav.j.e.a().f(a);
            int i = bVar.info == null ? bVar.nalType == 0 ? 1 : 0 : bVar.info.flags;
            if (e.this.i != null) {
                e.this.i.b(bVar.nalData, 0, bVar.nalData.length, a, i);
            }
        }

        public void onEncodeFormat(MediaFormat mediaFormat) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Video onEncodeFormat format:");
            stringBuilder.append(mediaFormat);
            TXCLog.i("BasicVideoGenerate", stringBuilder.toString());
            if (!(e.this.l.e() || e.this.i == null)) {
                e.this.i.a(mediaFormat);
                if (!e.this.l.l()) {
                    TXCLog.i("muxer", "No Audio, Video Muxer start");
                    e.this.i.a();
                    e.this.r = true;
                } else if (e.this.i.d()) {
                    TXCLog.i("BasicVideoGenerate", "Has Audio, Video Muxer start");
                    e.this.i.a();
                    e.this.r = true;
                }
            }
        }
    };
    private r E = new r() {
        public void a(ByteBuffer byteBuffer, BufferInfo bufferInfo) {
            b.g();
            com.tencent.liteav.j.e.a().e(bufferInfo.presentationTimeUs);
            if (e.this.i != null) {
                e.this.i.a(byteBuffer, bufferInfo);
            }
        }

        public void a(MediaFormat mediaFormat) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Audio onEncodeFormat format:");
            stringBuilder.append(mediaFormat);
            TXCLog.i("BasicVideoGenerate", stringBuilder.toString());
            if (!(e.this.l.e() || e.this.i == null)) {
                e.this.i.b(mediaFormat);
                if ((k.a().d() == 2 || e.this.c.i()) && e.this.i.c()) {
                    e.this.i.a();
                    e.this.r = true;
                }
            }
        }

        public void a() {
            TXCLog.i("BasicVideoGenerate", "===Audio onEncodeComplete===");
            e.this.b();
            e.this.d();
        }
    };
    private g F = new g() {
        public void a(int i) {
            boolean z = false;
            if (k.a().d() == 1 && e.this.c.h()) {
                d dVar = e.this.c;
                if (i <= 5) {
                    z = true;
                }
                dVar.b(z);
            } else if (e.this.g != null) {
                com.tencent.liteav.f.b bVar = e.this.g;
                if (i <= 5) {
                    z = true;
                }
                bVar.c(z);
            }
        }
    };
    protected Context a;
    protected boolean b = false;
    protected d c;
    protected p d;
    protected aa e;
    protected com.tencent.liteav.f.k f;
    protected com.tencent.liteav.f.b g;
    protected com.tencent.liteav.videoencoder.b h;
    protected c i;
    protected com.tencent.liteav.c.c j;
    protected com.tencent.liteav.f.j k;
    protected i l;
    protected t m = new t() {
        public void a(com.tencent.liteav.d.e eVar) {
            if (e.this.g != null) {
                e.this.g.a(eVar);
            }
        }

        public void b(com.tencent.liteav.d.e eVar) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("TailWaterMarkListener onDecodeVideoFrame  frame:");
            stringBuilder.append(eVar.e());
            stringBuilder.append(", flag : ");
            stringBuilder.append(eVar.f());
            stringBuilder.append(", reverse time = ");
            stringBuilder.append(eVar.u());
            TXCLog.d("BasicVideoGenerate", stringBuilder.toString());
            try {
                e.this.s.put(eVar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (e.this.e != null) {
                e.this.e.a(eVar);
            }
        }
    };
    private final String n = "BasicVideoGenerate";
    private f o;
    private Surface p;
    private a q;
    private boolean r = false;
    private LinkedBlockingQueue<com.tencent.liteav.d.e> s;
    private com.tencent.liteav.d.e t;
    private com.tencent.liteav.d.e u;
    private Object v = new Object();
    private o w = new o() {
        public void a(EGLContext eGLContext) {
            TXCLog.d("BasicVideoGenerate", "OnContextListener onContext");
            if (e.this.p != null) {
                synchronized (e.this.v) {
                    if (e.this.h != null) {
                        TXSVideoEncoderParam tXSVideoEncoderParam = new TXSVideoEncoderParam();
                        tXSVideoEncoderParam.width = e.this.l.h.a;
                        tXSVideoEncoderParam.height = e.this.l.h.b;
                        tXSVideoEncoderParam.fps = e.this.l.j();
                        tXSVideoEncoderParam.glContext = eGLContext;
                        tXSVideoEncoderParam.enableEGL14 = true;
                        tXSVideoEncoderParam.enableBlackList = false;
                        tXSVideoEncoderParam.appendSpsPps = false;
                        tXSVideoEncoderParam.annexb = true;
                        tXSVideoEncoderParam.fullIFrame = e.this.l.m;
                        tXSVideoEncoderParam.gop = e.this.l.k();
                        if (e.this.b) {
                            tXSVideoEncoderParam.encoderMode = 1;
                            tXSVideoEncoderParam.encoderProfile = 3;
                            tXSVideoEncoderParam.record = true;
                        } else {
                            tXSVideoEncoderParam.encoderMode = 3;
                            tXSVideoEncoderParam.encoderProfile = 1;
                        }
                        e.this.h.a(e.this.l.i());
                        e.this.h.a(e.this.D);
                        e.this.h.a(tXSVideoEncoderParam);
                    }
                }
                if (e.this.l.l()) {
                    e.this.q = new a();
                    e.this.q.a(e.this.F);
                    e.this.q.a(e.this.E);
                    q qVar = new q();
                    qVar.channelCount = e.this.l.b;
                    qVar.sampleRate = e.this.l.a;
                    qVar.maxInputSize = e.this.l.c;
                    qVar.audioBitrate = e.this.l.h();
                    TXCLog.i("BasicVideoGenerate", "AudioEncoder.start");
                    e.this.q.a(qVar);
                    if (e.this.g != null) {
                        e.this.g.e();
                    }
                }
                if (k.a().d() == 1 && e.this.c != null) {
                    e.this.c.a(e.this.p);
                    e.this.c.a(e.this.x);
                    e.this.c.a(e.this.y);
                    e.this.c.l();
                } else if (k.a().d() == 2 && e.this.d != null) {
                    e.this.d.a(e.this.z);
                    e.this.d.d();
                }
                b.h();
                com.tencent.liteav.j.e.a().b();
            }
        }
    };
    private k x = new k() {
        public void a(com.tencent.liteav.d.e eVar) {
            b.a();
            com.tencent.liteav.j.e.a().b(eVar.e());
            try {
                e.this.s.put(eVar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (e.this.e != null) {
                e.this.e.a(eVar);
            }
        }
    };
    private h y = new h() {
        public void a(com.tencent.liteav.d.e eVar) {
            b.b();
            com.tencent.liteav.j.e.a().a(eVar.e());
            if (e.this.g != null) {
                e.this.g.a(eVar);
            }
        }
    };
    private j z = new j() {
        public void a(com.tencent.liteav.d.e eVar) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("mPicDecListener, onDecodeBitmapFrame  frame:");
            stringBuilder.append(eVar.e());
            stringBuilder.append(", flag : ");
            stringBuilder.append(eVar.f());
            TXCLog.d("BasicVideoGenerate", stringBuilder.toString());
            try {
                e.this.s.put(eVar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (e.this.e != null) {
                e.this.e.b(eVar);
            }
        }
    };

    public abstract int a(int i, int i2, int i3, long j);

    public abstract void a(long j);

    public abstract void d();

    public abstract void e();

    public e(Context context) {
        this.a = context;
        this.e = new aa();
        this.f = new com.tencent.liteav.f.k(context);
        this.f.a(this.B);
        this.s = new LinkedBlockingQueue();
        this.l = i.a();
        this.j = com.tencent.liteav.c.c.a();
        this.k = com.tencent.liteav.f.j.a();
        this.b = com.tencent.liteav.basic.util.b.g();
    }

    public void a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setVideoPath videoPath:");
        stringBuilder.append(str);
        TXCLog.i("BasicVideoGenerate", stringBuilder.toString());
        try {
            if (this.c == null) {
                this.c = new v();
            }
            this.c.a(str);
            if (this.c.h()) {
                this.l.a(this.c.f());
            }
            this.l.b(this.c.g());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void a(List<Bitmap> list, int i) {
        this.d = new p();
        this.d.a(false);
        this.d.a((List) list, i);
        this.o = new f(this.a, this.d.a(), this.d.b());
    }

    public void a() {
        TXCLog.i("BasicVideoGenerate", "start");
        this.s.clear();
        f();
        if (this.l.l()) {
            this.g = new com.tencent.liteav.f.b();
            this.g.a();
            this.g.a(this.C);
            this.g.b(this.l.l);
            if (k.a().d() == 1) {
                this.g.b(this.c.h());
            } else {
                this.g.b(false);
            }
            this.g.c();
            MediaFormat n = this.l.n();
            if (n != null) {
                this.g.a(n);
            }
            if ((k.a().d() == 2 || !this.c.h()) && this.i != null) {
                this.i.b(n);
            }
        }
        g gVar = new g();
        if (k.a().d() == 1) {
            gVar.a = this.c.d();
            gVar.b = this.c.e();
            gVar.c = this.c.n();
        } else if (k.a().d() == 2) {
            gVar.a = this.d.a();
            gVar.b = this.d.b();
        }
        gVar = this.l.a(gVar);
        this.l.h = gVar;
        if (this.f != null) {
            this.f.a(this.l.h);
        }
        this.e.a(gVar);
        this.e.a(this.w);
        this.e.a(this.A);
        this.e.a();
    }

    public void b() {
        if (this.c != null) {
            this.c.a(null);
            this.c.a(null);
            this.c.m();
        }
        if (this.d != null) {
            this.d.a(null);
            this.d.e();
        }
        if (this.e != null) {
            this.e.a(null);
            this.e.a(null);
            this.e.b();
        }
        if (this.l.l() && this.g != null) {
            this.g.d();
            this.g.a(null);
            this.g.b();
            this.g = null;
        }
        if (this.h != null) {
            this.h.a(null);
            this.h.a();
        }
        if (this.q != null) {
            this.q.a(null);
            this.q.a(null);
            this.q.a();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("stop muxer :");
        stringBuilder.append(this.r);
        TXCLog.i("BasicVideoGenerate", stringBuilder.toString());
        this.r = false;
        if (this.i != null) {
            this.i.b();
            this.i = null;
        }
    }

    public void c() {
        if (this.c != null) {
            this.c.k();
        }
        this.c = null;
        if (this.d != null) {
            this.d.i();
        }
        this.d = null;
        if (this.e != null) {
            this.e.c();
        }
        this.e = null;
        if (this.f != null) {
            this.f.a(null);
        }
        this.f = null;
        synchronized (this.v) {
            this.h = null;
        }
        if (this.q != null) {
            this.q.b();
        }
        this.q = null;
        this.E = null;
        this.C = null;
        this.E = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.F = null;
        this.z = null;
        this.m = null;
        this.B = null;
        this.D = null;
        this.A = null;
    }

    private void f() {
        StringBuilder stringBuilder;
        long c = k.a().d() == 1 ? this.c.c() : k.a().d() == 2 ? this.d.a(com.tencent.liteav.c.e.a().b()) * 1000 : 0;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("calculateDuration durationUs:");
        stringBuilder2.append(c);
        TXCLog.d("BasicVideoGenerate", stringBuilder2.toString());
        long b = this.j.b();
        long c2 = this.j.c();
        long j = c2 - b;
        if (j > 0) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("calculateDuration Cut durationUs:");
            stringBuilder3.append(j);
            TXCLog.d("BasicVideoGenerate", stringBuilder3.toString());
            if (k.a().d() == 1) {
                this.c.a(b, c2);
            } else if (k.a().d() == 2) {
                this.d.a(b / 1000, c2 / 1000);
            }
            c = j;
        } else if (k.a().d() == 1) {
            this.c.a(0, c);
        } else if (k.a().d() == 2) {
            this.d.a(0, c / 1000);
        }
        this.l.k = c;
        this.l.l = c;
        if (com.tencent.liteav.f.g.a().c()) {
            this.l.k = com.tencent.liteav.f.g.a().b(this.l.k);
            this.l.l = this.l.k;
            stringBuilder = new StringBuilder();
            stringBuilder.append("calculateDuration Speed durationUs:");
            stringBuilder.append(this.l.k);
            TXCLog.d("BasicVideoGenerate", stringBuilder.toString());
        }
        if (this.k.b()) {
            this.l.k += this.k.c();
            stringBuilder = new StringBuilder();
            stringBuilder.append("calculateDuration AddTailWaterMark durationUs:");
            stringBuilder.append(this.l.k);
            TXCLog.d("BasicVideoGenerate", stringBuilder.toString());
        }
    }

    public void a(boolean z) {
        if (this.f != null) {
            this.f.b(z);
        }
    }
}
