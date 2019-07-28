package com.tencent.liteav;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.Surface;
import com.tencent.liteav.a.b;
import com.tencent.liteav.audio.TXCLiveBGMPlayer;
import com.tencent.liteav.audio.TXEAudioDef;
import com.tencent.liteav.audio.d;
import com.tencent.liteav.audio.e;
import com.tencent.liteav.basic.datareport.TXCDRApi;
import com.tencent.liteav.basic.e.l;
import com.tencent.liteav.basic.e.n;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.liteav.beauty.f;
import com.tencent.liteav.renderer.TXCGLSurfaceView;
import com.tencent.liteav.videoencoder.TXSVideoEncoderParam;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePusher.OnBGMNotify;
import com.tencent.rtmp.TXLog;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;

/* compiled from: TXCCaptureAndEnc */
public class c extends com.tencent.liteav.basic.module.a implements b, d, com.tencent.liteav.basic.d.a, f, q, com.tencent.liteav.videoencoder.d {
    private static final String d = "c";
    private WeakReference<com.tencent.liteav.basic.d.a> A;
    private WeakReference<r> B;
    private boolean C = false;
    private int D = -1;
    private int E = -1;
    a a;
    e b = null;
    OnBGMNotify c = null;
    private p e = null;
    private com.tencent.liteav.beauty.d f = null;
    private int g = -1;
    private TXSVideoEncoderParam h = null;
    private com.tencent.liteav.videoencoder.b i = null;
    private Context j = null;
    private h k = null;
    private int l = 0;
    private int m = 0;
    private int n = 0;
    private float o = 1.0f;
    private int p = 0;
    private float q = CropImageView.DEFAULT_ASPECT_RATIO;
    private TXCloudVideoView r = null;
    private Surface s = null;
    private int t = 0;
    private int u = 0;
    private com.tencent.liteav.basic.e.d v = null;
    private int w = 0;
    private com.tencent.liteav.basic.g.c x;
    private long y = 0;
    private a z = null;

    /* compiled from: TXCCaptureAndEnc */
    public interface a {
        void onEncAudio(byte[] bArr, long j, int i, int i2);

        void onEncVideo(com.tencent.liteav.basic.g.b bVar);

        void onEncVideoFormat(MediaFormat mediaFormat);

        void onRecordPcm(byte[] bArr, long j, int i, int i2, int i3);

        void onRecordRawPcm(byte[] bArr, long j, int i, int i2, int i3, boolean z);
    }

    public void a(byte[] bArr, int i, int i2, int i3, long j) {
    }

    public c(Context context) {
        this.j = context.getApplicationContext();
        this.k = new h();
        this.f = new com.tencent.liteav.beauty.d(this.j, true);
        this.f.a((f) this);
        this.f.a((com.tencent.liteav.basic.d.a) this);
        this.h = new TXSVideoEncoderParam();
        this.i = null;
        this.a = new a(this);
        com.tencent.liteav.basic.f.b.a().a(this.j);
    }

    public void a(long j) {
        this.y = j;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(this.y);
        setID(stringBuilder.toString());
    }

    public void a(a aVar) {
        this.z = aVar;
    }

    public void a(h hVar) {
        int i = this.k.k;
        Object obj = (hVar == null || (this.k.x == hVar.x && this.k.y == hVar.y && this.k.z == hVar.z && this.k.C == hVar.C && this.k.A == hVar.A && this.k.B == hVar.B)) ? null : 1;
        if (hVar != null) {
            try {
                this.k = (h) hVar.clone();
            } catch (CloneNotSupportedException e) {
                this.k = new h();
                e.printStackTrace();
            }
        } else {
            this.k = new h();
        }
        k(i);
        if (i()) {
            u();
            w();
            if (this.e != null) {
                this.e.c(this.k.l);
            }
            if (obj != null) {
                v();
            }
        }
    }

    public void a(com.tencent.liteav.basic.d.a aVar) {
        this.A = new WeakReference(aVar);
    }

    public void a(byte[] bArr) {
        com.tencent.liteav.audio.b.a().a(bArr);
    }

    public void a(r rVar) {
        this.B = new WeakReference(rVar);
    }

    public int b() {
        return this.h.width;
    }

    public int c() {
        return this.h.height;
    }

    public void setID(String str) {
        super.setID(str);
        if (this.i != null) {
            this.i.setID(str);
        }
        if (this.f != null) {
            this.f.setID(str);
        }
    }

    public String d() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(com.tencent.liteav.audio.b.a().f());
        stringBuilder.append(" | ");
        stringBuilder.append(com.tencent.liteav.audio.b.a().e());
        stringBuilder.append(",");
        stringBuilder.append(com.tencent.liteav.audio.b.a().d());
        return stringBuilder.toString();
    }

    public int e() {
        if (i()) {
            String str = d;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ignore startPush when pushing, status:");
            stringBuilder.append(this.m);
            TXCLog.w(str, stringBuilder.toString());
            return -2;
        }
        TXCDRApi.initCrashReport(this.j);
        this.m = 1;
        TXCLog.d(d, "startPusher");
        u();
        com.tencent.liteav.audio.b.a().a((d) this);
        if ((this.k != null && this.k.F) || this.l == 1 || this.e == null || this.e.d()) {
            com.tencent.liteav.audio.b.a().a(this.j);
        } else if (this.e != null) {
            this.e.e(true);
        }
        w();
        TXCDRApi.txReportDAU(this.j, com.tencent.liteav.basic.datareport.a.br);
        return 0;
    }

    public void f() {
        if (i()) {
            TXCLog.d(d, "stopPusher");
            this.m = 0;
            com.tencent.liteav.audio.b.a().b();
            com.tencent.liteav.audio.b.a().a(null);
            s();
            this.q = CropImageView.DEFAULT_ASPECT_RATIO;
            this.k.I = false;
            if (this.a != null) {
                this.a.a();
            }
            this.x = null;
            return;
        }
        String str = d;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ignore stopPush when not pushing, status:");
        stringBuilder.append(this.m);
        TXCLog.w(str, stringBuilder.toString());
    }

    public void g() {
        if (this.m != 1) {
            String str = d;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ignore pause push when is not pushing, status:");
            stringBuilder.append(this.m);
            TXCLog.w(str, stringBuilder.toString());
            return;
        }
        this.m = 2;
        TXCLog.d(d, "pausePusher");
        if ((this.k.w & 1) == 1) {
            if (!(this.a == null || this.k.F || this.e == null)) {
                this.a.a(this.k.v, this.k.u, this.k.t, this.h.width, this.h.height);
            }
            if (this.e != null) {
                this.e.c();
            }
        }
        if ((this.k.w & 2) == 2) {
            com.tencent.liteav.audio.b.a().c(true);
        }
    }

    public void h() {
        if (this.m != 2) {
            String str = d;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ignore resume push when is not pause, status:");
            stringBuilder.append(this.m);
            TXCLog.w(str, stringBuilder.toString());
            return;
        }
        this.m = 1;
        TXCLog.d(d, "resumePusher");
        if ((this.k.w & 1) == 1) {
            if (!(this.a == null || this.k.F)) {
                this.a.a();
            }
            if (this.e != null) {
                this.e.b();
            }
            v();
        }
        if ((this.k.w & 2) == 2) {
            com.tencent.liteav.audio.b.a().c(this.C);
            if ((1 & this.k.K) == 0) {
                com.tencent.liteav.audio.b.a().b();
                com.tencent.liteav.audio.b.a().a(this.k.q);
                com.tencent.liteav.audio.b.a().a(this.k.s, this.j);
                com.tencent.liteav.audio.b.a().d(this.p);
                com.tencent.liteav.audio.b.a().a(this.D, this.E);
                com.tencent.liteav.audio.b.a().a(this.o);
                com.tencent.liteav.audio.b.a().c(this.C);
                com.tencent.liteav.audio.b.a().a((d) this);
                com.tencent.liteav.audio.b.a().a(this.j);
            }
        }
    }

    public void a(final int i, final int i2, final int i3) {
        if (this.e != null) {
            this.e.a(new Runnable() {
                public void run() {
                    if (!(i2 == 0 || i3 == 0)) {
                        c.this.k.a = i2;
                        c.this.k.b = i3;
                        if (c.this.e != null) {
                            c.this.e.a(i2, i3);
                        }
                    }
                    if (i != 0 && c.this.i != null) {
                        c.this.k.c = i;
                        c.this.i.a(i);
                    }
                }
            });
        }
    }

    public boolean i() {
        return this.m != 0;
    }

    public void j() {
        if (this.e != null) {
            this.e.a(new Runnable() {
                public void run() {
                    if (c.this.e != null) {
                        c.this.e.b(true);
                    }
                    c.this.c(c.this.h.width, c.this.h.height);
                }
            });
        }
    }

    public void a(TXCloudVideoView tXCloudVideoView) {
        if (this.k.F) {
            TXCLog.e(d, "enable pure audio push , so can not start preview!");
            return;
        }
        l gLSurfaceView;
        if (tXCloudVideoView != null) {
            gLSurfaceView = tXCloudVideoView.getGLSurfaceView();
            if (gLSurfaceView == null) {
                gLSurfaceView = new TXCGLSurfaceView(tXCloudVideoView.getContext());
                tXCloudVideoView.addVideoView((TXCGLSurfaceView) gLSurfaceView);
            }
        } else {
            gLSurfaceView = new com.tencent.liteav.basic.e.e();
        }
        this.l = 0;
        this.e = new b(this.j, this.k, gLSurfaceView);
        this.e.a((q) this);
        this.e.a((com.tencent.liteav.basic.d.a) this);
        this.e.a();
        this.e.b(this.n);
        this.r = tXCloudVideoView;
        if (this.r != null) {
            this.r.start(this.k.D, this.k.E, this.e);
        }
    }

    public void a(boolean z) {
        if (this.e != null) {
            this.e.a(z);
            this.e = null;
            if (this.r != null) {
                this.r.stop(z);
                this.r = null;
            }
            this.s = null;
            if (this.v != null) {
                this.v.a();
                this.v = null;
            }
        }
    }

    public void a(Surface surface) {
        if (this.r != null) {
            TXCLog.w(d, "camera preview view is not null, can't set surface");
        } else {
            this.s = surface;
        }
    }

    public void a(final int i, final int i2) {
        if (this.v != null) {
            this.v.a(new Runnable() {
                public void run() {
                    c.this.t = i;
                    c.this.u = i2;
                    if (c.this.x != null && c.this.v != null) {
                        c.this.a(c.this.x, true);
                    }
                }
            });
            return;
        }
        this.t = i;
        this.u = i2;
    }

    public void a(final n nVar) {
        if (this.r != null) {
            TXCGLSurfaceView gLSurfaceView = this.r.getGLSurfaceView();
            if (gLSurfaceView != null) {
                gLSurfaceView.a(new n() {
                    public void onTakePhotoComplete(Bitmap bitmap) {
                        if (nVar != null) {
                            nVar.onTakePhotoComplete(bitmap);
                        }
                    }
                });
            }
        } else if (this.v != null) {
            this.v.a(new n() {
                public void onTakePhotoComplete(Bitmap bitmap) {
                    if (nVar != null) {
                        nVar.onTakePhotoComplete(bitmap);
                    }
                }
            });
        }
    }

    public void k() {
        if (VERSION.SDK_INT < 21) {
            Bundle bundle = new Bundle();
            bundle.putString(TXLiveConstants.EVT_DESCRIPTION, "录屏失败,不支持的Android系统版本,需要5.0以上的系统");
            onNotifyEvent(TXLiveConstants.PUSH_ERR_SCREEN_CAPTURE_UNSURPORT, bundle);
            String str = d;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Screen capture need running on Android Lollipop or higher version, current:");
            stringBuilder.append(VERSION.SDK_INT);
            TXLog.e(str, stringBuilder.toString());
            return;
        }
        this.l = 1;
        this.e = new l(this.j, this.k);
        this.e.a((com.tencent.liteav.basic.d.a) this);
        this.e.a((q) this);
        this.e.a();
        TXCDRApi.txReportDAU(this.j, com.tencent.liteav.basic.datareport.a.aG);
    }

    public void l() {
        if (this.e != null) {
            s();
            this.e.a(false);
            this.e = null;
        }
    }

    public void a(int i) {
        this.n = i;
        if (this.e != null) {
            this.e.b(i);
        }
    }

    public boolean b(boolean z) {
        if (this.e == null) {
            return false;
        }
        return this.e.d(z);
    }

    public boolean b(int i, int i2, int i3) {
        if (this.f != null) {
            this.f.c(i);
            this.f.d(i2);
            this.f.e(i3);
        }
        return true;
    }

    public void b(int i) {
        if (this.f != null) {
            this.f.b(i);
        }
    }

    public void a(Bitmap bitmap) {
        if (this.f != null) {
            this.f.a(bitmap);
        }
    }

    public void a(String str) {
        if (this.f != null) {
            this.f.a(str);
        }
    }

    public void c(boolean z) {
        if (this.f != null) {
            this.f.b(z);
        }
    }

    @TargetApi(18)
    public boolean b(String str) {
        return this.f != null ? this.f.a(str, true) : false;
    }

    public void c(int i) {
        if (this.f != null) {
            this.f.g(i);
        }
    }

    public void d(int i) {
        if (this.f != null) {
            this.f.h(i);
        }
    }

    public void a(float f) {
        if (this.f != null) {
            this.f.a(f);
        }
    }

    public void e(int i) {
        if (this.f != null) {
            this.f.i(i);
        }
    }

    public void f(int i) {
        if (this.f != null) {
            this.f.j(i);
        }
    }

    public void g(int i) {
        if (this.f != null) {
            this.f.k(i);
        }
    }

    public void h(int i) {
        if (this.f != null) {
            this.f.l(i);
        }
    }

    public void d(boolean z) {
        this.C = z;
        com.tencent.liteav.audio.b.a().c(z);
    }

    public boolean m() {
        return this.C;
    }

    public void b(int i, int i2) {
        this.D = i;
        this.E = i2;
        com.tencent.liteav.audio.b.a().a(i, i2);
    }

    public void b(float f) {
        this.q = f;
        TXCLiveBGMPlayer.getInstance().setPitch(f);
    }

    public int n() {
        if (this.e == null) {
            return 0;
        }
        return this.e.e();
    }

    public boolean i(int i) {
        if (this.e == null) {
            return false;
        }
        return this.e.a(i);
    }

    public boolean e(boolean z) {
        if (this.e == null) {
            return false;
        }
        this.e.c(z);
        return true;
    }

    public void c(float f) {
        if (this.e != null) {
            this.e.a(f);
        }
    }

    public void j(int i) {
        this.p = i;
        com.tencent.liteav.audio.b.a().d(i);
        TXCDRApi.txReportDAU(this.j, com.tencent.liteav.basic.datareport.a.az);
    }

    private void a(int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putLong("EVT_USERID", this.y);
        bundle.putInt("EVT_ID", i);
        bundle.putLong("EVT_TIME", TXCTimeUtil.getTimeTick());
        if (str != null) {
            bundle.putCharSequence(TXLiveConstants.EVT_DESCRIPTION, str);
        }
        com.tencent.liteav.basic.util.b.a(this.A, i, bundle);
    }

    public boolean c(String str) {
        if (!com.tencent.liteav.audio.b.a().c()) {
            return false;
        }
        TXCDRApi.txReportDAU(this.j, com.tencent.liteav.basic.datareport.a.aA);
        return TXCLiveBGMPlayer.getInstance().startPlay(str, com.tencent.liteav.audio.b.a().f());
    }

    public boolean o() {
        TXCLiveBGMPlayer.getInstance().stopPlay();
        return true;
    }

    public boolean p() {
        TXCLiveBGMPlayer.getInstance().pause();
        return true;
    }

    public boolean q() {
        TXCLiveBGMPlayer.getInstance().resume();
        return true;
    }

    public boolean d(float f) {
        this.o = f;
        com.tencent.liteav.audio.b.a().a(f);
        return true;
    }

    public boolean e(float f) {
        TXCLiveBGMPlayer.getInstance().setVolume(f);
        return true;
    }

    public int d(String str) {
        return (int) TXCLiveBGMPlayer.getInstance().getMusicDuration(str);
    }

    public void a(OnBGMNotify onBGMNotify) {
        if (onBGMNotify == null) {
            this.b = null;
            this.c = null;
            TXCLiveBGMPlayer.getInstance().setOnPlayListener(null);
            return;
        }
        this.c = onBGMNotify;
        if (this.b == null) {
            this.b = new e() {
                public void a() {
                    if (c.this.c != null) {
                        c.this.c.onBGMStart();
                    }
                }

                public void a(int i) {
                    if (c.this.c != null) {
                        c.this.c.onBGMComplete(i);
                    }
                }

                public void a(long j, long j2) {
                    if (c.this.c != null) {
                        c.this.c.onBGMProgress(j, j2);
                    }
                }
            };
        }
        TXCLiveBGMPlayer.getInstance().setOnPlayListener(this.b);
    }

    public int a(byte[] bArr, int i, int i2, int i3) {
        int a = a(i2, i3, null);
        if (a != 0) {
            return a;
        }
        if (this.i != null) {
            this.i.a(bArr, i, i2, i3, TXCTimeUtil.getTimeTick());
        }
        return 0;
    }

    public int c(int i, int i2, int i3) {
        int a = a(i2, i3, ((EGL10) EGLContext.getEGL()).eglGetCurrentContext());
        if (a != 0) {
            return a;
        }
        if (this.i != null) {
            this.i.a(i, i2, i3, TXCTimeUtil.getTimeTick());
        }
        return 0;
    }

    public void onRecordRawPcmData(byte[] bArr, long j, int i, int i2, int i3, boolean z) {
        a aVar = this.z;
        if (aVar != null) {
            aVar.onRecordRawPcm(bArr, j, i, i2, i3, z);
        }
    }

    public void onRecordPcmData(byte[] bArr, long j, int i, int i2, int i3) {
        a aVar = this.z;
        if (aVar != null) {
            aVar.onRecordPcm(bArr, j, i, i2, i3);
        }
    }

    public void onRecordEncData(byte[] bArr, long j, int i, int i2, int i3) {
        a aVar = this.z;
        if (aVar != null) {
            aVar.onEncAudio(bArr, j, i, i2);
        }
    }

    public void onRecordError(int i, String str) {
        String str2 = d;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onRecordError code = ");
        stringBuilder.append(i);
        stringBuilder.append(":");
        stringBuilder.append(str);
        TXCLog.e(str2, stringBuilder.toString());
        if (i == TXEAudioDef.TXE_AUDIO_RECORD_ERR_NO_MIC_PERMIT) {
            a((int) TXLiveConstants.PUSH_ERR_OPEN_MIC_FAIL, "打开麦克风失败");
        }
    }

    public int a(com.tencent.liteav.basic.g.c cVar) {
        if (this.B != null) {
            r rVar = (r) this.B.get();
            if (rVar != null) {
                cVar.a = rVar.onTextureCustomProcess(cVar.a, cVar.d, cVar.e);
            }
        }
        a(cVar, false);
        return cVar.a;
    }

    public void a(com.tencent.liteav.basic.g.c cVar, long j) {
        d(cVar.a, cVar.d, cVar.e);
    }

    public void onEncodeNAL(com.tencent.liteav.basic.g.b bVar, int i) {
        if (i == 0) {
            a aVar = this.z;
            if (aVar != null && bVar != null) {
                aVar.onEncVideo(bVar);
            }
        } else if (i == 10000004 && this.g == 1) {
            this.k.j = 0;
            a((int) TXLiveConstants.PUSH_WARNING_HW_ACCELERATION_FAIL, "硬编码启动失败,采用软编码");
        }
    }

    public void onEncodeFormat(MediaFormat mediaFormat) {
        a aVar = this.z;
        if (aVar != null) {
            aVar.onEncVideoFormat(mediaFormat);
        }
    }

    public void onNotifyEvent(int i, Bundle bundle) {
        if (bundle != null) {
            bundle.putLong("EVT_USERID", this.y);
        }
        com.tencent.liteav.basic.util.b.a(this.A, i, bundle);
    }

    public void a(Bitmap bitmap, ByteBuffer byteBuffer, int i, int i2) {
        if (this.e != null) {
            final Bitmap bitmap2 = bitmap;
            final int i3 = i;
            final int i4 = i2;
            final ByteBuffer byteBuffer2 = byteBuffer;
            this.e.a(new Runnable() {
                public void run() {
                    try {
                        int width = bitmap2.getWidth();
                        int height = bitmap2.getHeight();
                        c.this.f.a(com.tencent.liteav.basic.util.b.a(width, height, i3, i4));
                        c.this.f.a(false);
                        c.this.f.a(i3, i4);
                        c.this.f.a(0);
                        c.this.f.a(byteBuffer2.array(), width, height, 0, 2, 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void a(final com.tencent.liteav.videoencoder.b bVar) {
        if (this.e != null) {
            this.e.a(new Runnable() {
                public void run() {
                    try {
                        if (bVar != null) {
                            bVar.a();
                            bVar.a(null);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (bVar != null) {
            try {
                bVar.a();
                bVar.a(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a() {
        com.tencent.liteav.audio.b.a().b();
        com.tencent.liteav.audio.b.a().a(null);
    }

    public void a(SurfaceTexture surfaceTexture) {
        if (this.f != null) {
            this.f.a();
        }
    }

    public void b(com.tencent.liteav.basic.g.c cVar) {
        if (this.f != null && !this.k.F && this.e != null) {
            if (!(this.h.height == cVar.g && this.h.width == cVar.f)) {
                d(cVar.f, cVar.g);
            }
            this.w = cVar.j;
            this.f.a(cVar, cVar.b, 0);
        }
    }

    public void r() {
        if (this.f != null) {
            this.f.a();
        }
        t();
        if (this.B != null) {
            r rVar = (r) this.B.get();
            if (rVar != null) {
                rVar.onTextureDestoryed();
            }
        }
    }

    private void a(int i, int i2, int i3, EGLContext eGLContext) {
        Object eGLContext2;
        String str = d;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("New encode size width = ");
        stringBuilder.append(i);
        stringBuilder.append(" height = ");
        stringBuilder.append(i2);
        stringBuilder.append(" encType = ");
        stringBuilder.append(i3);
        TXCLog.d(str, stringBuilder.toString());
        t();
        this.g = i3;
        this.i = new com.tencent.liteav.videoencoder.b(this.g);
        if ((this.k.K & 2) != 2) {
            eGLContext2 = this.e != null ? this.e.f() : null;
        } else if (eGLContext2 == null) {
            eGLContext2 = this.i.a(i, i2);
        }
        this.h.width = i;
        this.h.height = i2;
        this.h.fps = this.k.h;
        this.h.gop = this.k.i;
        this.h.encoderProfile = this.k.n ? 3 : 1;
        this.h.encoderMode = 1;
        this.h.glContext = eGLContext2;
        this.h.realTime = this.k.I;
        this.i.a((com.tencent.liteav.videoencoder.d) this);
        this.i.a((com.tencent.liteav.basic.d.a) this);
        this.i.a(this.h);
        this.i.a(this.k.c);
        this.i.setID(getID());
    }

    private void d(int i, int i2, int i3) {
        b(i2, i3, null);
        if (this.i != null) {
            this.i.a(i, i2, i3, TXCTimeUtil.getTimeTick());
        }
    }

    private int a(int i, int i2, EGLContext eGLContext) {
        if (this.k == null) {
            return -5;
        }
        int i3 = 1280;
        int i4 = 720;
        switch (this.k.k) {
            case 0:
                i3 = 368;
                i4 = 640;
                break;
            case 1:
                i3 = 544;
                i4 = 960;
                break;
            case 2:
                i3 = 720;
                i4 = 1280;
                break;
            case 3:
                i3 = 640;
                i4 = 368;
                break;
            case 4:
                i3 = 960;
                i4 = 544;
                break;
            case 5:
                break;
            default:
                TXCLog.e(d, "sendCustomYUVData: invalid video_resolution");
                return -1;
        }
        if (i3 > i || i4 > i2) {
            return -4;
        }
        if (this.k.F) {
            t();
            return -1000;
        }
        b(i3, i4, eGLContext);
        return 0;
    }

    private void b(int i, int i2, EGLContext eGLContext) {
        int i3 = 2;
        switch (this.k.j) {
            case 1:
                i3 = 1;
                break;
            case 2:
                i3 = 3;
                break;
        }
        if (this.l == 1) {
            i3 = 1;
        }
        int i4 = this.k.i;
        if (this.i == null || this.h.width != i || this.h.height != i2 || this.g != i3 || this.h.gop != i4) {
            a(i, i2, i3, eGLContext);
        }
    }

    public void s() {
        if (this.i != null) {
            if (this.e != null) {
                this.e.a(new Runnable() {
                    public void run() {
                        c.this.t();
                    }
                });
            } else {
                t();
            }
        }
    }

    private void t() {
        try {
            if (this.i != null) {
                this.i.a();
                this.i.a(null);
                this.i = null;
            }
            this.h.width = 0;
            this.h.height = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void u() {
        if ((this.k.K & 1) != 0) {
            com.tencent.liteav.audio.b.a().a(true);
            com.tencent.liteav.audio.b.a().c(this.k.r);
            com.tencent.liteav.audio.b.a().a(this.k.q);
        } else {
            com.tencent.liteav.audio.b.a().c(1);
            com.tencent.liteav.audio.b.a().a(this.k.q);
        }
        com.tencent.liteav.audio.b.a().a(this.k.s, this.j);
        com.tencent.liteav.audio.b.a().c(this.C);
        TXCLiveBGMPlayer.getInstance().switchAecType(com.tencent.liteav.audio.b.a().f());
        TXCLiveBGMPlayer.getInstance().setPitch(this.q);
    }

    private void k(int i) {
        if (this.e == null || !this.e.d() || i == this.k.k || this.k.M || this.l != 0) {
            this.k.a();
            if (this.e != null && this.e.d()) {
                this.e.a(new Runnable() {
                    public void run() {
                        if (c.this.e != null) {
                            c.this.e.a(c.this.k.a, c.this.k.b);
                            c.this.e.d(c.this.k.k);
                        }
                    }
                });
                return;
            }
            return;
        }
        this.e.a(new Runnable() {
            public void run() {
                c.this.k.a();
                if (c.this.e != null) {
                    c.this.e.d(c.this.k.k);
                    c.this.e.b(false);
                }
            }
        });
    }

    private void v() {
        if (this.e != null) {
            this.e.a(new Runnable() {
                public void run() {
                    c.this.c(c.this.h.width, c.this.h.height);
                }
            });
        }
    }

    private void c(int i, int i2) {
        if (this.k.C != -1.0f) {
            if (this.f != null) {
                this.f.a(this.k.x, this.k.A, this.k.B, this.k.C);
            }
        } else if (this.f != null && i != 0 && i2 != 0) {
            float f = (float) i;
            this.f.a(this.k.x, ((float) this.k.y) / f, ((float) this.k.z) / ((float) i2), this.k.x == null ? CropImageView.DEFAULT_ASPECT_RATIO : ((float) this.k.x.getWidth()) / f);
        }
    }

    private void w() {
        if (this.f == null) {
            return;
        }
        if (this.k.I) {
            this.f.f(0);
        } else {
            this.f.f(3);
        }
    }

    private void d(int i, int i2) {
        c(i, i2);
    }

    private void a(com.tencent.liteav.basic.g.c cVar, boolean z) {
        this.x = cVar;
        if (this.r == null) {
            if (this.s != null) {
                if (!(this.v == null || this.v.b() == this.s)) {
                    this.v.a();
                    this.v = null;
                }
                if (!(this.v != null || this.e == null || this.e.f() == null)) {
                    this.v = new com.tencent.liteav.basic.e.d();
                    this.v.a(this.e.f(), this.s);
                }
            } else if (this.v != null) {
                this.v.a();
                this.v = null;
            }
            if (this.v != null) {
                this.v.a(cVar.a, cVar.h, this.w, this.t, this.u, cVar.d, cVar.e, z);
            }
        } else if (this.e != null) {
            this.e.a(cVar);
        }
    }

    public void a(float f, float f2) {
        if (this.e != null && this.k.D) {
            this.e.a(f, f2);
        }
    }
}
