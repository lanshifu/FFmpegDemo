package com.tencent.liteav.audio;

import android.content.Context;
import com.tencent.liteav.audio.impl.Play.TXAudioJitterBufferReportInfo;
import com.tencent.liteav.audio.impl.Play.TXCAudioBasePlayController;
import com.tencent.liteav.audio.impl.Play.d;
import com.tencent.liteav.audio.impl.TXCTraeJNI;
import com.tencent.liteav.audio.impl.b;
import com.tencent.liteav.audio.impl.c;
import com.tencent.liteav.audio.impl.e;
import com.tencent.liteav.basic.log.TXCLog;
import com.yalantis.ucrop.view.CropImageView;

/* compiled from: TXCAudioPlayer */
public class a implements e {
    public static final int a = TXEAudioDef.TXE_AEC_NONE;
    public static float b = 5.0f;
    public static boolean c = true;
    public static float d = 5.0f;
    public static float e = 1.0f;
    public static boolean f = false;
    private static final String g;
    private TXCAudioBasePlayController h = null;
    private c i;
    private int j = a;
    private float k = b;
    private boolean l = c;
    private float m = d;
    private float n = e;
    private boolean o = false;
    private boolean p = false;
    private boolean q = f;
    private int r = 0;
    private Context s;

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AudioCenter:");
        stringBuilder.append(a.class.getSimpleName());
        g = stringBuilder.toString();
    }

    public synchronized int a(Context context) {
        if (context == null) {
            TXCLog.e(g, "invalid param, start play failed!");
            return TXEAudioDef.TXE_AUDIO_COMMON_ERR_INVALID_PARAMS;
        }
        if (b.c(this.j) != TXEAudioDef.TXE_AUDIO_PLAY_ERR_OK) {
            String str = g;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("start player failed, with aec type ");
            stringBuilder.append(this.j);
            stringBuilder.append(", invalid aec recorder has started!");
            TXCLog.w(str, stringBuilder.toString());
        }
        if (this.h == null || !this.h.isPlaying()) {
            this.s = context;
            com.tencent.liteav.audio.impl.a.a().a(context);
            c.a().a(this.s);
            c.a().a((e) this);
            if (this.h == null) {
                if (this.j == TXEAudioDef.TXE_AEC_TRAE) {
                    this.h = new com.tencent.liteav.audio.impl.Play.c(context.getApplicationContext());
                } else {
                    this.h = new com.tencent.liteav.audio.impl.Play.b(context.getApplicationContext());
                }
            }
            if (this.h != null) {
                a(this.j, this.s);
                a(this.i);
                a(this.k);
                a(this.l);
                b(this.m);
                c(this.n);
                c(this.o);
                b(this.p);
                a(this.r);
                d(this.q);
                return this.h.startPlay();
            }
            TXCLog.e(g, "start play failed! controller is null!");
            return TXEAudioDef.TXE_AUDIO_COMMON_ERR_INVALID_PARAMS;
        }
        TXCLog.e(g, "play has started, can not start again!");
        return TXEAudioDef.TXE_AUDIO_PLAY_ERR_REPEAT_OPTION;
    }

    public synchronized int a() {
        int i;
        i = TXEAudioDef.TXE_AUDIO_PLAY_ERR_OK;
        this.i = null;
        this.k = b;
        this.l = c;
        this.m = d;
        this.n = e;
        this.o = false;
        this.p = false;
        this.q = f;
        this.r = 0;
        this.s = null;
        if (this.h != null) {
            i = this.h.stopPlay();
            this.h = null;
        }
        c.a().b((e) this);
        return i;
    }

    public void a(boolean z, Context context) {
        if (!z) {
            a(TXEAudioDef.TXE_AEC_NONE, context);
        } else if (com.tencent.liteav.basic.f.b.a().g()) {
            a(TXEAudioDef.TXE_AEC_SYSTEM, context);
        } else {
            com.tencent.liteav.audio.impl.a.a(com.tencent.liteav.basic.f.b.a().b());
            a(TXEAudioDef.TXE_AEC_TRAE, context);
        }
    }

    private void a(int i, Context context) {
        if (i != TXEAudioDef.TXE_AEC_TRAE || TXCTraeJNI.nativeCheckTraeEngine(context)) {
            String str = g;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("set aec type to ");
            stringBuilder.append(i);
            stringBuilder.append(", cur type ");
            stringBuilder.append(this.j);
            TXCLog.i(str, stringBuilder.toString());
            this.j = i;
            return;
        }
        TXCLog.e(g, "set aec type failed, check trae library failed!!");
    }

    public synchronized int a(com.tencent.liteav.basic.g.a aVar) {
        if (this.h == null) {
            TXCLog.e(g, "play audio failed, controller not created yet!");
            return TXEAudioDef.TXE_AUDIO_PLAY_ERR_INVALID_STATE;
        }
        return this.h.playData(aVar);
    }

    public void a(c cVar) {
        this.i = cVar;
        if (this.h != null) {
            this.h.setListener(cVar);
        }
    }

    public void a(float f) {
        this.k = f;
        if (this.h != null) {
            this.h.setCacheTime(f);
        }
    }

    public void a(boolean z) {
        this.l = z;
        if (this.h != null) {
            this.h.enableAutojustCache(z);
        }
    }

    public void b(float f) {
        this.m = f;
        if (this.h != null) {
            this.h.setAutoAdjustMaxCache(f);
        }
    }

    public void c(float f) {
        this.n = f;
        if (this.h != null) {
            this.h.setAutoAdjustMinCache(f);
        }
    }

    public synchronized long b() {
        if (this.h == null) {
            return 0;
        }
        return this.h.getCacheDuration();
    }

    public synchronized long c() {
        if (this.h == null) {
            return 0;
        }
        return this.h.getCurPts();
    }

    public synchronized int d() {
        if (this.h == null) {
            return 0;
        }
        return this.h.getRecvJitter();
    }

    public synchronized long e() {
        if (this.h == null) {
            return 0;
        }
        return this.h.getCurRecvTS();
    }

    public synchronized float f() {
        if (this.h == null) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        return this.h.getCacheThreshold();
    }

    public void b(boolean z) {
        this.p = z;
        if (this.h != null) {
            this.h.enableHWAcceleration(z);
        }
    }

    public void c(boolean z) {
        this.o = z;
        if (this.h != null) {
            this.h.enableRealTimePlay(z);
        }
    }

    public void d(boolean z) {
        this.q = z;
        if (this.h != null) {
            this.h.setMute(z);
        }
    }

    public static void a(Context context, int i) {
        TXCAudioBasePlayController.setAudioMode(context, i);
    }

    public boolean g() {
        return this.h != null ? this.h.isPlaying() : false;
    }

    public void a(int i) {
        this.r = i;
        if (this.h != null) {
            this.h.setSmootheMode(this.r);
        }
    }

    public int h() {
        String str;
        StringBuilder stringBuilder;
        if (d.a().d()) {
            if (this.j != TXEAudioDef.TXE_AEC_TRAE) {
                return this.j;
            }
            str = g;
            stringBuilder = new StringBuilder();
            stringBuilder.append("audio track has start, but aec type is trae!!");
            stringBuilder.append(this.j);
            TXCLog.e(str, stringBuilder.toString());
            return TXEAudioDef.TXE_AEC_NONE;
        } else if (!TXCTraeJNI.nativeTraeIsPlaying()) {
            return TXEAudioDef.TXE_AEC_NONE;
        } else {
            if (this.j == TXEAudioDef.TXE_AEC_TRAE) {
                return this.j;
            }
            str = g;
            stringBuilder = new StringBuilder();
            stringBuilder.append("trae engine has start, but aec type is not trae!!");
            stringBuilder.append(this.j);
            TXCLog.e(str, stringBuilder.toString());
            return TXEAudioDef.TXE_AEC_TRAE;
        }
    }

    public TXAudioJitterBufferReportInfo i() {
        return this.h != null ? this.h.getReportInfo() : null;
    }

    public void b(int i) {
        switch (i) {
            case 0:
                if (this.h != null) {
                    this.h.setMute(this.q);
                    return;
                }
                return;
            case 1:
                if (this.h != null) {
                    this.h.setMute(true);
                    return;
                }
                return;
            case 2:
                if (this.h != null) {
                    this.h.setMute(true);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
