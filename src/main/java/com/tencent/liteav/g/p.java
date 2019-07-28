package com.tencent.liteav.g;

import android.content.Context;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.opengl.EGLContext;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.c.j;
import com.tencent.liteav.d.e;
import com.tencent.liteav.e.g;
import com.tencent.liteav.e.i;
import com.tencent.liteav.e.l;
import com.tencent.liteav.e.n;
import com.tencent.liteav.e.o;
import com.tencent.liteav.e.q;
import com.tencent.liteav.e.r;
import com.tencent.liteav.f.b;
import com.tencent.liteav.i.c.a;
import com.tencent.liteav.muxer.c;
import com.tencent.liteav.videoencoder.TXSVideoEncoderParam;
import com.tencent.liteav.videoencoder.d;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: VideoJoinGenerate */
public class p {
    private LinkedBlockingQueue<e> a;
    private Context b;
    private boolean c;
    private l d;
    private n e;
    private q f;
    private t g;
    private s h;
    private b i;
    private a j;
    private com.tencent.liteav.videoencoder.b k;
    private com.tencent.liteav.e.a l;
    private c m;
    private boolean n;
    private j o;
    private o p = new o() {
        public void a(EGLContext eGLContext) {
            TXCLog.d("VideoJoinGenerate", "OnContextListener onContext");
            if (p.this.k != null) {
                TXSVideoEncoderParam tXSVideoEncoderParam = new TXSVideoEncoderParam();
                tXSVideoEncoderParam.width = p.this.h.h.a;
                tXSVideoEncoderParam.height = p.this.h.h.b;
                tXSVideoEncoderParam.fps = p.this.h.j();
                tXSVideoEncoderParam.glContext = eGLContext;
                tXSVideoEncoderParam.enableEGL14 = true;
                tXSVideoEncoderParam.enableBlackList = false;
                tXSVideoEncoderParam.appendSpsPps = false;
                tXSVideoEncoderParam.annexb = true;
                tXSVideoEncoderParam.fullIFrame = false;
                tXSVideoEncoderParam.gop = p.this.h.k();
                if (p.this.c) {
                    tXSVideoEncoderParam.encoderMode = 1;
                    tXSVideoEncoderParam.encoderProfile = 3;
                    tXSVideoEncoderParam.record = true;
                } else {
                    tXSVideoEncoderParam.encoderMode = 3;
                    tXSVideoEncoderParam.encoderProfile = 1;
                }
                p.this.k.a(p.this.h.i());
                p.this.k.a(p.this.v);
                p.this.k.a(tXSVideoEncoderParam);
            }
            p.this.l = new com.tencent.liteav.e.a();
            p.this.l.a(p.this.s);
            p.this.l.a(p.this.u);
            q qVar = new q();
            qVar.channelCount = p.this.h.b;
            qVar.sampleRate = p.this.h.a;
            qVar.maxInputSize = p.this.h.c;
            qVar.audioBitrate = p.this.h.h();
            p.this.l.a(qVar);
            if (p.this.d != null) {
                p.this.d.a(p.this.o);
                p.this.d.a(p.this.q);
                p.this.d.a(p.this.r);
                p.this.d.a();
            }
        }
    };
    private c q = new c() {
        public void a(e eVar, i iVar) {
            try {
                p.this.a.put(eVar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (p.this.e != null) {
                p.this.e.a(eVar, iVar);
            }
        }
    };
    private a r = new a() {
        public void a(e eVar, i iVar) {
            if (p.this.i != null) {
                p.this.i.a(eVar);
            }
        }
    };
    private g s = new g() {
        public void a(int i) {
            p.this.d.a(i <= 5);
        }
    };
    private i t = new i() {
        public void a(e eVar) {
            if (eVar != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("didAudioProcessFrame frame:");
                stringBuilder.append(eVar.e());
                TXCLog.d("VideoJoinGenerate", stringBuilder.toString());
                if (!eVar.p() || p.this.k == null) {
                    if (p.this.l != null) {
                        p.this.l.a(eVar);
                    }
                    if (p.this.i != null) {
                        p.this.i.i();
                    }
                    return;
                }
                p.this.k.b();
                TXCLog.d("VideoJoinGenerate", "signalEOSAndFlush");
            }
        }
    };
    private r u = new r() {
        public void a(ByteBuffer byteBuffer, BufferInfo bufferInfo) {
            if (p.this.m != null) {
                p.this.m.a(byteBuffer, bufferInfo);
            }
        }

        public void a(MediaFormat mediaFormat) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Audio onEncodeFormat format:");
            stringBuilder.append(mediaFormat);
            TXCLog.i("VideoJoinGenerate", stringBuilder.toString());
            if (p.this.m != null) {
                p.this.m.b(mediaFormat);
                if (p.this.m.c()) {
                    p.this.m.a();
                    p.this.n = true;
                }
            }
        }

        public void a() {
            TXCLog.i("VideoJoinGenerate", "===Audio onEncodeComplete===");
            p.this.b();
            p.this.c();
        }
    };
    private d v = new d() {
        public void onEncodeNAL(com.tencent.liteav.basic.g.b bVar, int i) {
            if (i != 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("mVideoEncodeListener, errCode = ");
                stringBuilder.append(i);
                TXCLog.i("VideoJoinGenerate", stringBuilder.toString());
            } else if (bVar == null || bVar.nalData == null) {
                TXCLog.i("VideoJoinGenerate", "nal is null ===Video onEncodeComplete===");
                p.this.b();
                p.this.c();
            } else {
                e eVar = null;
                try {
                    eVar = (e) p.this.a.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (eVar.p()) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("frame.isEnd===Video onEncodeComplete===:");
                    stringBuilder2.append(eVar.p());
                    stringBuilder2.append(", nal:");
                    stringBuilder2.append(bVar);
                    TXCLog.i("VideoJoinGenerate", stringBuilder2.toString());
                    p.this.b();
                    p.this.c();
                    return;
                }
                synchronized (this) {
                    if (!(p.this.m == null || bVar == null || bVar.nalData == null)) {
                        if (p.this.n) {
                            a(bVar, eVar);
                        } else if (bVar.nalType == 0) {
                            MediaFormat a = com.tencent.liteav.basic.util.b.a(bVar.nalData, p.this.h.h.a, p.this.h.h.b);
                            if (a != null) {
                                p.this.m.a(a);
                                p.this.m.a();
                                p.this.n = true;
                            }
                            a(bVar, eVar);
                        }
                    }
                }
                p.this.a(eVar.e());
            }
        }

        private void a(com.tencent.liteav.basic.g.b bVar, e eVar) {
            int i = bVar.info == null ? bVar.nalType == 0 ? 1 : 0 : bVar.info.flags;
            if (p.this.m != null) {
                p.this.m.b(bVar.nalData, 0, bVar.nalData.length, eVar.e(), i);
            }
        }

        public void onEncodeFormat(MediaFormat mediaFormat) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Video onEncodeFormat format:");
            stringBuilder.append(mediaFormat);
            TXCLog.i("VideoJoinGenerate", stringBuilder.toString());
            if (p.this.m != null) {
                p.this.m.a(mediaFormat);
                if (p.this.m.d()) {
                    TXCLog.i("VideoJoinGenerate", "Has Audio, Video Muxer start");
                    p.this.m.a();
                    p.this.n = true;
                }
            }
        }
    };
    private d w = new d() {
        public void a(List<Surface> list) {
            TXCLog.i("VideoJoinGenerate", "IVideoJoinRenderListener onSurfaceTextureAvailable");
            if (p.this.f != null) {
                p.this.f.a();
                p.this.f.b();
            }
        }

        public void a(int i, int i2) {
            if (p.this.f != null) {
                com.tencent.liteav.d.g gVar = new com.tencent.liteav.d.g();
                int e = j.a().e();
                if (e == 90 || e == 270) {
                    gVar.a = i2;
                    gVar.b = i;
                } else {
                    gVar.a = i;
                    gVar.b = i2;
                }
                p.this.f.a(gVar);
            }
        }

        public void b(List<Surface> list) {
            TXCLog.i("VideoJoinGenerate", "IVideoJoinRenderListener onSurfaceTextureDestroy");
            if (p.this.f != null) {
                p.this.f.c();
                p.this.f.d();
            }
        }

        public int a(int i, float[] fArr, e eVar) {
            if (p.this.f != null) {
                p.this.f.a(fArr);
                p.this.f.a(i, eVar);
            }
            return 0;
        }
    };
    private l x = new l() {
        public int b(int i, e eVar) {
            return i;
        }

        public void a(int i, e eVar) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("didProcessFrame frame:");
            stringBuilder.append(eVar.e());
            TXCLog.i("VideoJoinGenerate", stringBuilder.toString());
            if (!eVar.p() || p.this.k == null) {
                if (p.this.k != null) {
                    p.this.k.b(i, eVar.m(), eVar.n(), eVar.e() / 1000);
                }
                p.this.d.c();
                return;
            }
            p.this.k.b();
            TXCLog.d("VideoJoinGenerate", "signalEOSAndFlush");
        }
    };
    private Handler y = new Handler(Looper.getMainLooper());

    public p(Context context) {
        this.b = context;
        this.a = new LinkedBlockingQueue();
        this.e = new n();
        this.d = new l();
        this.f = new q(context);
        this.f.a(this.x);
        this.g = t.a();
        this.h = s.r();
        this.c = com.tencent.liteav.basic.util.b.g();
    }

    public void a(a aVar) {
        this.j = aVar;
    }

    public void a() {
        TXCLog.i("VideoJoinGenerate", "start");
        this.h.g();
        this.a.clear();
        this.h.k = this.g.m();
        this.i = new b();
        this.i.a();
        this.i.a(this.t);
        MediaFormat g = this.g.g();
        if (g != null) {
            this.h.d(g);
            this.i.a(g);
        }
        com.tencent.liteav.d.g a = this.h.a(this.g.h());
        this.h.h = a;
        this.f.a(a);
        List c = t.a().c();
        this.o = new j();
        this.o.a(c);
        this.e.a(this.o);
        this.e.a(a);
        this.e.a(this.p);
        this.e.a(this.w);
        this.e.a();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mUseSWEncoder:");
        stringBuilder.append(this.c);
        TXCLog.i("VideoJoinGenerate", stringBuilder.toString());
        int i = 2;
        if (this.k == null) {
            this.k = new com.tencent.liteav.videoencoder.b(this.c ? 2 : 1);
        }
        Context context = this.b;
        if (this.c) {
            i = 0;
        }
        this.m = new c(context, i);
        this.m.a(this.h.i);
    }

    public void b() {
        TXCLog.i("VideoJoinGenerate", "stop");
        if (this.d != null) {
            this.d.a(null);
            this.d.a(null);
            this.d.b();
        }
        if (this.e != null) {
            this.e.a(null);
            this.e.a(null);
            this.e.b();
        }
        if (this.i != null) {
            this.i.d();
            this.i.a(null);
            this.i.b();
            this.i = null;
        }
        if (this.k != null) {
            this.k.a(null);
            this.k.a();
        }
        if (this.l != null) {
            this.l.a(null);
            this.l.a(null);
            this.l.a();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("stop muxer :");
        stringBuilder.append(this.n);
        TXCLog.i("VideoJoinGenerate", stringBuilder.toString());
        this.n = false;
        if (this.m != null) {
            this.m.b();
            this.m = null;
        }
    }

    private void c() {
        n.a().b();
        this.y.post(new Runnable() {
            public void run() {
                if (p.this.j != null) {
                    com.tencent.liteav.i.a.d dVar = new com.tencent.liteav.i.a.d();
                    dVar.a = 0;
                    dVar.b = "Join Complete";
                    TXCLog.d("VideoJoinGenerate", "===onJoinComplete===");
                    p.this.j.a(dVar);
                }
            }
        });
    }

    private void a(final long j) {
        this.y.post(new Runnable() {
            public void run() {
                if (p.this.j != null) {
                    long j = p.this.h.k;
                    if (j > 0) {
                        float f = (((float) j) * 1.0f) / ((float) j);
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("onJoinProgress timestamp:");
                        stringBuilder.append(j);
                        stringBuilder.append(",progress:");
                        stringBuilder.append(f);
                        stringBuilder.append(",duration:");
                        stringBuilder.append(j);
                        TXCLog.d("VideoJoinGenerate", stringBuilder.toString());
                        p.this.j.a(f);
                    }
                }
            }
        });
    }
}
