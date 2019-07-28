package com.tencent.liteav;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.util.Log;
import com.tencent.liteav.audio.TXEAudioDef;
import com.tencent.liteav.audio.c;
import com.tencent.liteav.audio.impl.Play.TXAudioJitterBufferReportInfo;
import com.tencent.liteav.basic.b.b;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.liteav.renderer.f;
import com.tencent.liteav.renderer.g;
import com.tencent.liteav.videodecoder.d;
import com.tencent.rtmp.TXLiveConstants;
import com.yalantis.ucrop.view.CropImageView;

/* compiled from: TXCRenderAndDec */
public class k extends com.tencent.liteav.basic.module.a implements c, b, com.tencent.liteav.basic.d.a, g, d {
    private Context a = null;
    private i b = null;
    private com.tencent.liteav.videodecoder.b c = null;
    private f d = null;
    private com.tencent.liteav.basic.b.a e = null;
    private com.tencent.liteav.audio.a f = null;
    private com.tencent.liteav.basic.d.a g = null;
    private boolean h = false;
    private int i = 0;
    private long j = 0;
    private byte[] k = null;
    private t l = null;
    private int m;
    private boolean n = false;
    private String o;
    private final float p = com.tencent.liteav.basic.a.a.o;
    private final float q = com.tencent.liteav.basic.a.a.p;
    private final float r = com.tencent.liteav.basic.a.a.q;
    private final float s = 0.3f;
    private boolean t = false;
    private a u = null;

    /* compiled from: TXCRenderAndDec */
    public interface a {
        void a(long j);

        void a(SurfaceTexture surfaceTexture);

        void a(com.tencent.liteav.basic.g.a aVar);

        void a(byte[] bArr, long j);
    }

    public void onPlayError(int i, String str) {
    }

    public k(Context context, int i) {
        this.a = context;
        this.m = i;
        com.tencent.liteav.basic.f.b.a().a(this.a);
    }

    public void a(f fVar) {
        this.d = fVar;
        if (!(this.d == null || this.g == null)) {
            this.d.a((com.tencent.liteav.basic.d.a) this);
        }
        if (this.d != null && this.b != null) {
            this.d.a(this.b.d);
        }
    }

    public void a(com.tencent.liteav.basic.d.a aVar) {
        this.g = aVar;
    }

    public void a(i iVar) {
        this.b = iVar;
        v();
    }

    public void a(long j) {
        this.j = j;
    }

    public void setID(String str) {
        super.setID(str);
        if (this.d != null) {
            this.d.setID(getID());
        }
    }

    public boolean b() {
        return this.t;
    }

    public void a(boolean z) {
        this.t = z;
    }

    public void a(a aVar) {
        this.u = aVar;
    }

    public void b(boolean z) {
        this.h = z;
        this.n = true;
        if (this.d != null) {
            this.d.a((g) this);
            this.d.i();
            this.d.setID(getID());
        }
        this.c = new com.tencent.liteav.videodecoder.b();
        this.c.a(this.j);
        this.c.a((d) this);
        this.c.a((com.tencent.liteav.basic.d.a) this);
        this.f = new com.tencent.liteav.audio.a();
        this.f.a((c) this);
        d(this.h);
        this.f.a(this.i);
        this.f.a(this.a);
        this.e = new com.tencent.liteav.basic.b.a();
        this.e.a((b) this);
        this.e.a();
        a();
        v();
    }

    public void c() {
        this.h = false;
        this.i = 0;
        if (this.c != null) {
            this.c.a(null);
            this.c.a(null);
            this.c.c();
        }
        if (this.f != null) {
            this.f.a(null);
            this.f.a();
        }
        if (this.e != null) {
            this.e.a(null);
            this.e.b();
        }
        if (this.d != null) {
            this.d.j();
            this.d.a(null);
        }
    }

    public void a(com.tencent.liteav.basic.g.a aVar) {
        if (this.f != null) {
            this.f.a(aVar);
        } else {
            TXCLog.w("TXCRenderAndDec", "decAudio fail which audio play hasn't been created!");
        }
    }

    public void a(com.tencent.liteav.basic.g.b bVar) {
        try {
            if (this.e != null) {
                this.e.a(bVar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(int i) {
        if (this.d != null) {
            this.d.b(i);
        }
    }

    public void b(int i) {
        if (this.d != null) {
            this.d.c(i);
        }
    }

    public void c(boolean z) {
        if (this.f != null) {
            this.f.d(z);
        }
    }

    public void c(int i) {
        this.i = i;
        if (this.f != null) {
            this.f.a(this.i);
        }
    }

    public static void a(Context context, int i) {
        com.tencent.liteav.audio.a.a(context, i);
    }

    public long d() {
        return this.f != null ? this.f.b() : 0;
    }

    public long e() {
        return this.e != null ? this.e.d() : 0;
    }

    public long f() {
        return this.e != null ? this.e.e() : 0;
    }

    public int g() {
        return this.c != null ? this.c.d() : 0;
    }

    public long h() {
        return (this.e == null || this.f == null) ? 0 : this.f.c() - this.e.f();
    }

    public int i() {
        return this.f != null ? this.f.d() : 0;
    }

    public long j() {
        return (this.f == null || this.e == null) ? 0 : this.f.e() - this.e.g();
    }

    public float k() {
        return this.f != null ? this.f.f() : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public int l() {
        return this.e != null ? this.e.h() : 0;
    }

    public String m() {
        int h;
        if (this.f != null) {
            h = this.f.h();
        } else {
            h = TXEAudioDef.TXE_AEC_NONE;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(h);
        stringBuilder.append(" | ");
        stringBuilder.append(this.o);
        return stringBuilder.toString();
    }

    public void n() {
        long j = 0;
        if (this.f != null) {
            TXAudioJitterBufferReportInfo i = this.f.i();
            if (i != null) {
                long j2 = i.mLoadCnt == 0 ? 0 : (long) (i.mLoadTime / i.mLoadCnt);
                long j3 = i.mTimeTotalCacheTimeCnt == 0 ? 0 : i.mTimeTotalCacheTime / i.mTimeTotalCacheTimeCnt;
                long j4 = (long) (i.mTimeTotalJittCnt == 0 ? 0 : i.mTimeTotalJitt / i.mTimeTotalJittCnt);
                setStatusValue(TXLiveConstants.PLAY_EVT_CONNECT_SUCC, Long.valueOf(j2));
                setStatusValue(TXLiveConstants.PLAY_EVT_RTMP_STREAM_BEGIN, Long.valueOf((long) i.mLoadCnt));
                setStatusValue(TXLiveConstants.PLAY_EVT_RCV_FIRST_I_FRAME, Long.valueOf((long) i.mLoadMaxTime));
                setStatusValue(TXLiveConstants.PLAY_EVT_PLAY_BEGIN, Long.valueOf((long) i.mSpeedCnt));
                setStatusValue(TXLiveConstants.PLAY_EVT_PLAY_PROGRESS, Long.valueOf((long) i.mNoDataCnt));
                setStatusValue(TXLiveConstants.PLAY_EVT_PLAY_LOADING, Long.valueOf((long) i.mAvgCacheTime));
                setStatusValue(TXLiveConstants.PLAY_EVT_START_VIDEO_DECODER, Long.valueOf((long) i.mIsRealTime));
                setStatusValue(TXLiveConstants.PLAY_EVT_GET_PLAYINFO_SUCC, Long.valueOf(j3));
                setStatusValue(TXLiveConstants.PLAY_EVT_CHANGE_ROTATION, Long.valueOf(j4));
                setStatusValue(TXLiveConstants.PLAY_EVT_VOD_LOADING_END, Long.valueOf((long) i.mTimeDropCnt));
            }
        }
        if (this.e != null) {
            setStatusValue(TXLiveConstants.PLAY_EVT_PLAY_END, Long.valueOf(f()));
            setStatusValue(6007, Long.valueOf(this.e.k()));
            setStatusValue(6008, Long.valueOf(this.e.j()));
            setStatusValue(6009, Long.valueOf(this.e.i()));
        }
        if (this.c != null) {
            if (this.c.a()) {
                j = 1;
            }
            setStatusValue(5002, Long.valueOf(j));
        }
    }

    public com.tencent.liteav.basic.b.a o() {
        return this.e;
    }

    public com.tencent.liteav.audio.a p() {
        return this.f;
    }

    public f q() {
        return this.d;
    }

    public boolean a(byte[] bArr) {
        synchronized (this) {
            this.k = bArr;
        }
        return true;
    }

    public void a(t tVar) {
        synchronized (this) {
            this.l = tVar;
        }
    }

    private void c(SurfaceTexture surfaceTexture) {
        com.tencent.liteav.videodecoder.b bVar = this.c;
        if (bVar != null) {
            bVar.c();
            bVar.a(this.b.i);
            if (surfaceTexture != null) {
                bVar.a(surfaceTexture, null, null, this.h ^ 1);
                bVar.b();
            }
        }
    }

    private void a() {
        c(this.d != null ? this.d.a() : null);
    }

    private void v() {
        d(this.h);
        if (this.f != null) {
            this.f.a(this.b.a);
            this.f.a(this.b.g);
            this.f.c(this.b.c);
            this.f.b(this.b.b);
            setStatusValue(TXLiveConstants.PLAY_EVT_GET_MESSAGE, Long.valueOf((long) (this.b.c * 1000.0f)));
            setStatusValue(TXLiveConstants.PLAY_EVT_VOD_PLAY_PREPARED, Long.valueOf((long) (this.b.b * 1000.0f)));
            setStatusValue(TXLiveConstants.PLAY_EVT_STREAM_SWITCH_SUCC, Long.valueOf(0));
        }
        if (this.e != null) {
            this.e.a(this.b.c);
        }
        if (this.c != null && this.c.a() && this.b.c < 0.3f && this.b.b < 0.3f) {
            this.b.i = false;
            this.c.c();
            a();
        }
        if (this.d != null) {
            this.d.a(this.b.d);
        }
    }

    private void d(boolean z) {
        if (z) {
            float f = this.b.a;
            f = this.b.c;
            float f2 = this.b.b;
            if (f > this.q) {
                f = this.q;
            }
            if (f2 > this.r) {
                f2 = this.r;
            }
            if (f >= f2) {
                f = this.q;
                f2 = this.r;
            }
            this.b.g = true;
            this.b.a = f;
            this.b.c = f;
            this.b.b = f2;
            if (this.f != null) {
                this.f.a(true, this.a);
                this.f.c(true);
            }
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setupRealTimePlayParams current cache time : min-cache[");
            stringBuilder.append(this.b.c);
            stringBuilder.append("], max-cache[");
            stringBuilder.append(this.b.b);
            stringBuilder.append("], org-cache[");
            stringBuilder.append(this.b.a);
            stringBuilder.append("]");
            TXCLog.e("TXCRenderAndDec", stringBuilder.toString());
            if (this.f != null) {
                if (this.b == null || !this.b.h) {
                    this.f.a(false, this.a);
                } else {
                    this.f.a(true, this.a);
                }
                this.f.c(false);
            }
            if (this.b.a > this.b.b || this.b.a < this.b.c) {
                this.b.a = this.b.b;
            }
        }
        if (this.e != null) {
            this.e.b(z);
        }
    }

    private void a(int i, String str) {
        com.tencent.liteav.basic.d.a aVar = this.g;
        if (aVar != null) {
            Bundle bundle = new Bundle();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("TXCRenderAndDec notifyEvent: mUserID  ");
            stringBuilder.append(this.j);
            Log.i("TXCRenderAndDec", stringBuilder.toString());
            bundle.putLong("EVT_USERID", this.j);
            bundle.putInt("EVT_ID", i);
            bundle.putLong("EVT_TIME", TXCTimeUtil.getTimeTick());
            if (str != null) {
                bundle.putCharSequence(TXLiveConstants.EVT_DESCRIPTION, str);
            }
            aVar.onNotifyEvent(i, bundle);
        }
    }

    private void w() {
        com.tencent.liteav.videodecoder.b bVar = this.c;
        if (bVar != null) {
            TXCLog.w("TXCRenderAndDec", "switch to soft decoder when hw error");
            bVar.c();
            this.b.i = false;
            d(this.h);
            a();
        }
    }

    public void r() {
        com.tencent.liteav.videodecoder.b bVar = this.c;
        if (bVar != null && bVar.e()) {
            bVar.b(true);
        }
    }

    public void onNotifyEvent(int i, Bundle bundle) {
        if (i == TXLiveConstants.PLAY_WARNING_HW_ACCELERATION_FAIL) {
            w();
        } else if (i == TXLiveConstants.PLAY_EVT_RCV_FIRST_I_FRAME && this.n) {
            a((int) TXLiveConstants.PLAY_EVT_PLAY_BEGIN, "视频播放开始");
            this.n = false;
        }
        com.tencent.liteav.basic.d.a aVar = this.g;
        if (aVar != null) {
            aVar.onNotifyEvent(i, bundle);
        }
    }

    public void a(SurfaceTexture surfaceTexture) {
        c(surfaceTexture);
    }

    public void b(SurfaceTexture surfaceTexture) {
        try {
            TXCLog.w("TXCRenderAndDec", "play:stop decode when surface texture release");
            if (this.c != null) {
                this.c.c();
            }
            if (this.u != null) {
                this.u.a(surfaceTexture);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long s() {
        try {
            if (this.f != null) {
                return this.f.b();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long t() {
        try {
            if (this.f != null) {
                return this.f.c();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void b(com.tencent.liteav.basic.g.b bVar) {
        try {
            if (this.c != null) {
                this.c.a(bVar);
            } else if (this.e != null) {
                this.e.a(bVar.pts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void c(com.tencent.liteav.basic.g.b bVar) {
        Bundle bundle = new Bundle();
        bundle.putByteArray(TXLiveConstants.EVT_GET_MSG, bVar.nalData);
        onNotifyEvent(TXLiveConstants.PLAY_EVT_GET_MESSAGE, bundle);
    }

    public int u() {
        try {
            if (this.c != null) {
                return this.c.d();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void a(SurfaceTexture surfaceTexture, int i, int i2, long j, long j2) {
        if (this.d != null) {
            this.d.a(surfaceTexture, i, i2);
            if (this.u != null) {
                this.u.a(j);
            }
            if (this.e != null) {
                this.e.a(j);
            }
        }
    }

    public void a(long j, int i, int i2, long j2, long j3) {
        Object obj = null;
        if (!(this.l == null || this.k == null)) {
            synchronized (this) {
                byte[] bArr = this.k;
                this.k = null;
                if (!(this.l == null || bArr == null || this.c == null)) {
                    if (bArr.length <= ((i * i2) * 3) / 2) {
                        this.c.a(bArr, j, bArr.length);
                        this.l.onVideoRawDataAvailable(bArr, i, i2, (int) j2);
                        obj = 1;
                    } else {
                        TXCLog.e("TXCRenderAndDec", "raw data buffer length is too large");
                    }
                }
            }
        }
        if (obj == null) {
            if (j > 0 && this.d != null) {
                this.d.a(j, i, i2);
            }
            if (this.e != null) {
                this.e.a(j2);
            }
        }
    }

    public void a(int i, int i2) {
        if (this.d != null) {
            this.d.b(i, i2);
        }
        Bundle bundle = new Bundle();
        bundle.putCharSequence(TXLiveConstants.EVT_DESCRIPTION, "分辨率改变");
        bundle.putInt("EVT_PARAM1", i);
        bundle.putInt("EVT_PARAM2", i2);
        bundle.putLong("EVT_TIME", TXCTimeUtil.getTimeTick());
        onNotifyEvent(TXLiveConstants.PLAY_EVT_CHANGE_RESOLUTION, bundle);
    }

    public void onPlayAudioInfoChanged(com.tencent.liteav.basic.g.a aVar, com.tencent.liteav.basic.g.a aVar2) {
        if (this.u != null) {
            this.u.a(aVar2);
        }
        if (aVar != null && aVar2 != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(aVar.sampleRate);
            stringBuilder.append(",");
            stringBuilder.append(aVar.channelsPerSample);
            stringBuilder.append(" | ");
            stringBuilder.append(aVar2.sampleRate);
            stringBuilder.append(",");
            stringBuilder.append(aVar2.channelsPerSample);
            this.o = stringBuilder.toString();
        }
    }

    public void onPlayPcmData(byte[] bArr, long j) {
        if (this.u != null) {
            this.u.a(bArr, j);
        }
    }

    public void onPlayJitterStateNotify(int i) {
        if (i == TXEAudioDef.TXE_AUDIO_JITTER_STATE_LOADING) {
            if (!(this.e == null || this.h)) {
                this.e.a(true);
            }
            a((int) TXLiveConstants.PLAY_EVT_PLAY_LOADING, "视频缓冲中...");
        } else if (i == TXEAudioDef.TXE_AUDIO_JITTER_STATE_FIRST_LAODING) {
            a((int) TXLiveConstants.PLAY_EVT_PLAY_LOADING, "视频缓冲中...");
        } else if (i == TXEAudioDef.TXE_AUDIO_JITTER_STATE_PLAYING) {
            if (this.e != null) {
                this.e.a(false);
            }
            a((int) TXLiveConstants.PLAY_EVT_PLAY_BEGIN, "视频播放开始");
        } else if (i == TXEAudioDef.TXE_AUDIO_JITTER_STATE_FIRST_PLAY) {
            if (this.e != null) {
                this.e.a(false);
            }
            if (this.n) {
                a((int) TXLiveConstants.PLAY_EVT_PLAY_BEGIN, "视频播放开始");
                this.n = false;
            }
        }
    }
}
