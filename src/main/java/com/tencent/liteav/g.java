package com.tencent.liteav;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import android.view.TextureView;
import com.tencent.liteav.basic.d.a;
import com.tencent.liteav.basic.datareport.TXCDRApi;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.module.TXCStatus;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.liteav.h.a.b;
import com.tencent.liteav.network.TXCStreamDownloader;
import com.tencent.liteav.network.h;
import com.tencent.liteav.renderer.i;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer.ITXAudioRawDataListener;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tencent.ugc.TXRecordCommon.ITXVideoRecordListener;
import com.tencent.ugc.TXRecordCommon.TXRecordResult;
import com.yalantis.ucrop.view.CropImageView;

/* compiled from: TXCLivePlayer */
public class g extends s implements a, k.a, h, com.tencent.liteav.renderer.a.a, com.tencent.liteav.renderer.h {
    private ITXAudioRawDataListener A;
    private String B;
    private boolean C;
    private long D;
    private long E;
    private boolean F;
    private k a;
    private com.tencent.liteav.renderer.a f;
    private TXCStreamDownloader g;
    private Handler h;
    private TextureView i;
    private boolean j;
    private boolean k;
    private int l;
    private int m;
    private int n;
    private boolean o;
    private com.tencent.liteav.h.a p;
    private ITXVideoRecordListener q;
    private d r;
    private int s;
    private int t;
    private i u;
    private i v;
    private float[] w;
    private float[] x;
    private String y;
    private int z;

    public void a(long j) {
    }

    public boolean f() {
        return true;
    }

    public g(Context context) {
        super(context);
        this.a = null;
        this.f = null;
        this.g = null;
        this.j = false;
        this.k = false;
        this.l = 0;
        this.m = 0;
        this.n = 16;
        this.o = false;
        this.s = 0;
        this.t = 0;
        this.u = null;
        this.v = null;
        this.w = new float[]{1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, -1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f};
        this.x = new float[16];
        this.y = "";
        this.B = "";
        this.C = false;
        this.D = 0;
        this.E = 0;
        this.F = false;
        this.h = new Handler(Looper.getMainLooper());
        this.f = new com.tencent.liteav.renderer.a();
        this.f.a((a) this);
    }

    public void a(TXCloudVideoView tXCloudVideoView) {
        if (!(this.d == null || this.d == tXCloudVideoView)) {
            TextureView videoView = this.d.getVideoView();
            if (videoView != null) {
                this.d.removeView(videoView);
            }
        }
        super.a(tXCloudVideoView);
        if (this.d != null) {
            this.i = this.d.getVideoView();
            if (this.i == null) {
                this.i = new TextureView(this.d.getContext());
            }
            this.d.addVideoView(this.i);
        }
        if (this.f != null) {
            this.f.a(this.i);
        }
    }

    public void a(Surface surface) {
        if (this.f != null) {
            this.f.a(surface);
        }
    }

    public void a(int i, int i2) {
        if (this.f != null) {
            this.f.c(i, i2);
        }
    }

    public void a(i iVar) {
        super.a(iVar);
        if (this.a != null) {
            this.a.a(iVar);
        }
    }

    public int a(String str, int i) {
        if (c()) {
            TXCLog.w("TXCLivePlayer", "play: ignore start play when is playing");
            return -2;
        }
        this.y = str;
        this.z = i;
        b(str);
        this.k = true;
        e(i);
        int b = b(str, i);
        if (b != 0) {
            this.k = false;
            l();
            k();
            if (this.i != null) {
                this.i.setVisibility(8);
            }
        } else {
            m();
            r();
            TXCDRApi.txReportDAU(this.c, com.tencent.liteav.basic.datareport.a.bq);
            try {
                if (Class.forName("com.tencent.liteav.demo.play.SuperPlayerView") != null) {
                    TXCDRApi.txReportDAU(this.c, com.tencent.liteav.basic.datareport.a.bB);
                }
            } catch (Exception unused) {
            }
        }
        return b;
    }

    public int a(boolean z) {
        if (c()) {
            TXCLog.v("TXCLivePlayer", "play: stop");
            this.k = false;
            l();
            k();
            if (this.i != null && z) {
                this.i.setVisibility(8);
            }
            n();
            s();
            o();
            return 0;
        }
        TXCLog.w("TXCLivePlayer", "play: ignore stop play when not started");
        return -2;
    }

    public int a(String str) {
        if (!c() || this.g == null) {
            return -1;
        }
        boolean switchStream = this.g.switchStream(str);
        if (this.a != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" stream_switch video cache ");
            stringBuilder.append(this.a.e());
            stringBuilder.append(" audio cache ");
            stringBuilder.append(this.a.d());
            TXCLog.w("TXCLivePlayer", stringBuilder.toString());
        }
        if (!switchStream) {
            return -2;
        }
        this.y = str;
        return 0;
    }

    public void a() {
        a(false);
    }

    public void b() {
        a(this.y, this.z);
    }

    public boolean c() {
        return this.k;
    }

    public void a(int i) {
        if (this.a != null) {
            this.a.a(i);
        }
    }

    public void b(int i) {
        if (this.a != null) {
            this.a.b(i);
        }
    }

    public void b(boolean z) {
        this.j = z;
        if (this.a != null) {
            this.a.c(this.j);
        }
    }

    public void a(Context context, int i) {
        k.a(context, i);
    }

    public void a(ITXVideoRecordListener iTXVideoRecordListener) {
        this.q = iTXVideoRecordListener;
    }

    public int c(int i) {
        if (this.o) {
            TXCLog.e("TXCLivePlayer", "startRecord: there is existing uncompleted record task");
            return -1;
        }
        this.o = true;
        this.f.a((com.tencent.liteav.renderer.h) this);
        this.f.a((com.tencent.liteav.renderer.a.a) this);
        TXCDRApi.txReportDAU(this.c, com.tencent.liteav.basic.datareport.a.av);
        return 0;
    }

    public TextureView d() {
        return this.i;
    }

    public void a(ITXAudioRawDataListener iTXAudioRawDataListener) {
        this.A = iTXAudioRawDataListener;
    }

    public int e() {
        if (this.o) {
            this.o = false;
            if (this.p != null) {
                this.p.a();
                this.p = null;
            }
            return 0;
        }
        TXCLog.w("TXCLivePlayer", "stopRecord: no recording task exist");
        return -1;
    }

    public boolean a(byte[] bArr) {
        return this.a != null ? this.a.a(bArr) : false;
    }

    public void a(t tVar) {
        if (this.a != null) {
            this.a.a(tVar);
        }
    }

    private void h() {
        if (this.p == null) {
            this.s = this.f.m();
            this.t = this.f.n();
            com.tencent.liteav.h.a.a j = j();
            this.p = new com.tencent.liteav.h.a(this.c);
            this.p.a(j);
            this.p.a(new b() {
                public void a(int i, String str, String str2, String str3) {
                    if (g.this.q != null) {
                        TXRecordResult tXRecordResult = new TXRecordResult();
                        if (i == 0) {
                            tXRecordResult.retCode = 0;
                        } else {
                            tXRecordResult.retCode = -1;
                        }
                        tXRecordResult.descMsg = str;
                        tXRecordResult.videoPath = str2;
                        tXRecordResult.coverPath = str3;
                        g.this.q.onRecordComplete(tXRecordResult);
                    }
                    g.this.f.a(null);
                    g.this.f.a(null);
                }

                public void a(long j) {
                    if (g.this.q != null) {
                        g.this.q.onRecordProgress(j);
                    }
                }
            });
        }
        if (this.u == null) {
            this.u = new i(Boolean.valueOf(true));
            this.u.b();
            this.u.b(this.s, this.t);
            this.u.a(this.s, this.t);
        }
        if (this.v == null) {
            this.v = new i(Boolean.valueOf(false));
            this.v.b();
            this.v.b(this.f.k(), this.f.l());
            this.v.a(this.f.k(), this.f.l());
            Matrix.setIdentityM(this.x, 0);
        }
    }

    private void i() {
        if (this.u != null) {
            this.u.c();
            this.u = null;
        }
        if (this.v != null) {
            this.v.c();
            this.v = null;
        }
    }

    private com.tencent.liteav.h.a.a j() {
        int i;
        int i2;
        if (this.s <= 0 || this.t <= 0) {
            i = 480;
            i2 = 640;
        } else {
            i = this.s;
            i2 = this.t;
        }
        com.tencent.liteav.h.a.a aVar = new com.tencent.liteav.h.a.a();
        aVar.a = i;
        aVar.b = i2;
        aVar.c = 20;
        double d = (double) (i * i);
        Double.isNaN(d);
        d *= 1.0d;
        double d2 = (double) (i2 * i2);
        Double.isNaN(d2);
        aVar.d = (int) (Math.sqrt(d + d2) * 1.2d);
        aVar.h = this.l;
        aVar.i = this.m;
        aVar.j = this.n;
        aVar.f = com.tencent.liteav.h.a.a(this.c, ".mp4");
        aVar.g = com.tencent.liteav.h.a.a(this.c, ".jpg");
        aVar.e = this.f.b();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("record config: ");
        stringBuilder.append(aVar);
        TXCLog.d("TXCLivePlayer", stringBuilder.toString());
        return aVar;
    }

    private void e(int i) {
        boolean z = false;
        if (this.i != null) {
            this.i.setVisibility(0);
        }
        this.a = new k(this.c, 1);
        this.a.a((a) this);
        this.a.a(this.f);
        this.a.a((k.a) this);
        this.a.a(this.b);
        this.a.setID(this.B);
        k kVar = this.a;
        if (i == 5) {
            z = true;
        }
        kVar.b(z);
        this.a.c(this.j);
    }

    private void k() {
        if (this.a != null) {
            this.a.c();
            this.a.a(null);
            this.a.a(null);
            this.a.a(null);
            this.a = null;
        }
    }

    private int b(String str, int i) {
        int i2 = 0;
        if (i == 0) {
            this.g = new TXCStreamDownloader(this.c, 1);
        } else if (i == 5) {
            this.g = new TXCStreamDownloader(this.c, 4);
        } else {
            this.g = new TXCStreamDownloader(this.c, 0);
        }
        this.g.setID(this.B);
        this.g.setListener(this);
        this.g.setNotifyListener(this);
        this.g.setHeaders(this.b.p);
        if (i == 5) {
            i2 = 1;
        }
        if (i2 != 0) {
            this.g.setRetryTimes(5);
            this.g.setRetryInterval(1);
        } else {
            this.g.setRetryTimes(this.b.e);
            this.g.setRetryInterval(this.b.f);
        }
        return this.g.start(str, this.b.j, this.b.l, this.b.k);
    }

    private void l() {
        if (this.g != null) {
            this.g.setListener(null);
            this.g.setNotifyListener(null);
            this.g.stop();
            this.g = null;
        }
    }

    private void m() {
        this.r = new d(this.c);
        this.r.a(this.y);
        this.r.a(this.z == 5);
        this.r.d(this.B);
        this.r.a();
    }

    private void n() {
        if (this.r != null) {
            this.r.c();
            this.r = null;
        }
    }

    private void b(String str) {
        this.B = String.format("%s-%d", new Object[]{str, Long.valueOf(TXCTimeUtil.getTimeTick() % 10000)});
        if (this.a != null) {
            this.a.setID(this.B);
        }
        if (this.f != null) {
            this.f.setID(this.B);
        }
        if (this.g != null) {
            this.g.setID(this.B);
        }
        if (this.r != null) {
            this.r.d(this.B);
        }
    }

    public void g() {
        this.E = 0;
        if (!this.C) {
            this.C = true;
            if (this.h != null) {
                this.h.postDelayed(new Runnable() {
                    public void run() {
                        if (g.this.C) {
                            g.this.q();
                        }
                    }
                }, 1000);
            }
        }
    }

    private void o() {
        this.C = false;
    }

    private void q() {
        if (this.D > 0) {
            Bundle bundle = new Bundle();
            bundle.putInt(TXLiveConstants.EVT_PLAY_PROGRESS, (int) (this.D / 1000));
            bundle.putInt(TXLiveConstants.EVT_PLAY_PROGRESS_MS, (int) this.D);
            onNotifyEvent(TXLiveConstants.PLAY_EVT_PLAY_PROGRESS, bundle);
        }
        if (this.h != null && this.C) {
            this.h.postDelayed(new Runnable() {
                public void run() {
                    if (g.this.C) {
                        g.this.q();
                    }
                }
            }, 1000);
        }
    }

    private void r() {
        this.F = true;
        if (this.h != null) {
            this.h.postDelayed(new Runnable() {
                public void run() {
                    if (g.this.F) {
                        g.this.t();
                    }
                }
            }, 2000);
        }
    }

    private void s() {
        this.F = false;
    }

    private void t() {
        int[] a = com.tencent.liteav.basic.util.b.a();
        int i = a[0] / 10;
        int i2 = a[1] / 10;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append("/");
        stringBuilder.append(i2);
        stringBuilder.append("%");
        String stringBuilder2 = stringBuilder.toString();
        i = TXCStatus.d(this.B, 7102);
        int d = TXCStatus.d(this.B, 7101);
        String c = TXCStatus.c(this.B, 7110);
        int e = (int) TXCStatus.e(this.B, 6002);
        Bundle bundle = new Bundle();
        if (this.f != null) {
            bundle.putInt(TXLiveConstants.NET_STATUS_VIDEO_WIDTH, this.f.m());
            bundle.putInt(TXLiveConstants.NET_STATUS_VIDEO_HEIGHT, this.f.n());
        }
        if (this.a != null) {
            bundle.putInt(TXLiveConstants.NET_STATUS_CACHE_SIZE, (int) this.a.e());
            bundle.putInt(TXLiveConstants.NET_STATUS_CODEC_CACHE, (int) this.a.d());
            bundle.putInt(TXLiveConstants.NET_STATUS_VIDEO_CACHE_SIZE, (int) this.a.f());
            bundle.putInt(TXLiveConstants.NET_STATUS_V_DEC_CACHE_SIZE, this.a.g());
            bundle.putInt(TXLiveConstants.NET_STATUS_AV_PLAY_INTERVAL, (int) this.a.h());
            bundle.putString(TXLiveConstants.NET_STATUS_AUDIO_INFO, this.a.m());
            bundle.putInt(TXLiveConstants.NET_STATUS_NET_JITTER, this.a.i());
            bundle.putInt(TXLiveConstants.NET_STATUS_AV_RECV_INTERVAL, (int) this.a.j());
            bundle.putFloat(TXLiveConstants.NET_STATUS_AUDIO_PLAY_SPEED, this.a.k());
            int i3 = e == 0 ? 15 : e;
            String str = TXLiveConstants.NET_STATUS_VIDEO_GOP;
            double l = (double) (((float) ((this.a.l() * 10) / i3)) / 10.0f);
            Double.isNaN(l);
            bundle.putInt(str, (int) (l + 0.5d));
        }
        bundle.putInt(TXLiveConstants.NET_STATUS_NET_SPEED, d + i);
        bundle.putInt(TXLiveConstants.NET_STATUS_VIDEO_FPS, e);
        bundle.putInt(TXLiveConstants.NET_STATUS_VIDEO_BITRATE, d);
        bundle.putInt(TXLiveConstants.NET_STATUS_AUDIO_BITRATE, i);
        bundle.putCharSequence(TXLiveConstants.NET_STATUS_SERVER_IP, c);
        bundle.putCharSequence(TXLiveConstants.NET_STATUS_CPU_USAGE, stringBuilder2);
        com.tencent.liteav.basic.util.b.a(this.e, 15001, bundle);
        if (this.a != null) {
            this.a.n();
        }
        if (this.r != null) {
            this.r.f();
        }
        if (this.h != null && this.F) {
            this.h.postDelayed(new Runnable() {
                public void run() {
                    if (g.this.F) {
                        g.this.t();
                    }
                }
            }, 2000);
        }
    }

    public void onPullAudio(com.tencent.liteav.basic.g.a aVar) {
        if (this.k && this.a != null) {
            this.a.a(aVar);
        }
    }

    public void onPullNAL(com.tencent.liteav.basic.g.b bVar) {
        if (this.k) {
            try {
                if (this.a != null) {
                    this.a.a(bVar);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onNotifyEvent(final int i, final Bundle bundle) {
        if (TXLiveConstants.PLAY_ERR_GET_RTMP_ACC_URL_FAIL == i && this.a != null) {
            this.a.c((int) com.tencent.liteav.basic.f.b.a().a("Audio", "SmoothModeAdjust"));
        }
        if (this.h != null) {
            this.h.post(new Runnable() {
                public void run() {
                    com.tencent.liteav.basic.util.b.a(g.this.e, i, bundle);
                    if (i == TXLiveConstants.PLAY_WARNING_RECONNECT && g.this.a != null) {
                        g.this.a.r();
                    }
                }
            });
        }
    }

    public int a(int i, float[] fArr) {
        com.tencent.liteav.h.a aVar = this.p;
        if (!(!this.o || aVar == null || this.u == null)) {
            int d = this.u.d(i);
            aVar.a(d, TXCTimeUtil.getTimeTick());
            this.f.a(d, this.s, this.t, false, 0);
        }
        if (this.o) {
            h();
        } else {
            i();
        }
        return i;
    }

    public void d(int i) {
        com.tencent.liteav.h.a aVar = this.p;
        if (!(!this.o || aVar == null || this.v == null)) {
            this.v.a(this.w);
            aVar.a(this.v.d(i), TXCTimeUtil.getTimeTick());
            this.v.a(this.x);
            this.v.c(i);
        }
        if (this.o) {
            h();
        } else {
            i();
        }
    }

    public void a(SurfaceTexture surfaceTexture) {
        i();
        e();
    }

    public void a(byte[] bArr, long j) {
        if (this.p != null) {
            if (j <= 0) {
                j = TXCTimeUtil.getTimeTick();
            }
            this.p.a(bArr, j);
        }
        if (this.A != null) {
            this.A.onPcmDataAvailable(bArr, j);
        }
        if (this.E <= 0) {
            this.E = j;
        } else {
            this.D = j - this.E;
        }
    }

    public void a(com.tencent.liteav.basic.g.a aVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onPlayAudioInfoChanged, samplerate=");
        stringBuilder.append(aVar.sampleRate);
        stringBuilder.append(", channels=");
        stringBuilder.append(aVar.channelsPerSample);
        stringBuilder.append(", bits=");
        stringBuilder.append(aVar.bitsPerChannel);
        TXCLog.d("TXCLivePlayer", stringBuilder.toString());
        this.l = aVar.channelsPerSample;
        this.m = aVar.sampleRate;
        if (aVar.bitsPerChannel > 1) {
            this.n = aVar.bitsPerChannel;
        }
        if (this.A != null) {
            this.A.onAudioInfoChanged(aVar.sampleRate, aVar.channelsPerSample, aVar.bitsPerChannel);
        }
    }
}
