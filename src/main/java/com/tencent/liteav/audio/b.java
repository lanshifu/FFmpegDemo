package com.tencent.liteav.audio;

import android.content.Context;
import com.tencent.liteav.audio.impl.Record.TXCAudioSysRecordController;
import com.tencent.liteav.audio.impl.Record.c;
import com.tencent.liteav.audio.impl.Record.f;
import com.tencent.liteav.audio.impl.Record.g;
import com.tencent.liteav.audio.impl.TXCTraeJNI;
import com.tencent.liteav.audio.impl.e;
import com.tencent.liteav.basic.a.a;
import com.tencent.liteav.basic.log.TXCLog;
import java.lang.ref.WeakReference;

/* compiled from: TXCAudioRecorder */
public class b implements e {
    public static final int a = a.e;
    public static final int b = a.f;
    public static final int c = a.h;
    public static final int d = TXEAudioDef.TXE_REVERB_TYPE_0;
    public static final int e = TXEAudioDef.TXE_AEC_NONE;
    public static final int f = TXEAudioDef.TXE_AUDIO_TYPE_AAC;
    static b g = new b();
    private static final String h;
    private WeakReference<d> i;
    private int j = a;
    private int k = b;
    private int l = a.h;
    private int m = d;
    private boolean n = false;
    private int o = e;
    private boolean p = false;
    private boolean q = false;
    private Context r;
    private float s = 1.0f;
    private int t = -1;
    private int u = -1;
    private c v = null;

    public void b(boolean z) {
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AudioCenter:");
        stringBuilder.append(b.class.getSimpleName());
        h = stringBuilder.toString();
    }

    private b() {
    }

    public static b a() {
        return g;
    }

    public int a(Context context) {
        if (context == null) {
            TXCLog.e(h, "invalid param, start record failed!");
            return TXEAudioDef.TXE_AUDIO_COMMON_ERR_INVALID_PARAMS;
        }
        this.r = context.getApplicationContext();
        com.tencent.liteav.audio.impl.a.a().a(this.r);
        com.tencent.liteav.audio.impl.c.a().a(this.r);
        com.tencent.liteav.audio.impl.c.a().a((e) this);
        if (com.tencent.liteav.audio.impl.b.b(this.o) != TXEAudioDef.TXE_AUDIO_RECORD_ERR_OK) {
            String str = h;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("start recorder failed, with aec type ");
            stringBuilder.append(this.o);
            stringBuilder.append(", invalid aec player has started!");
            TXCLog.w(str, stringBuilder.toString());
        }
        if (this.v == null || !this.v.isRecording()) {
            if (this.v == null) {
                if (this.o == TXEAudioDef.TXE_AEC_TRAE) {
                    this.v = new g();
                } else {
                    this.v = new TXCAudioSysRecordController();
                }
            }
            if (this.v != null) {
                g();
                return this.v.startRecord(this.r);
            }
            TXCLog.e(h, "start Record failed! controller is null!");
            return TXEAudioDef.TXE_AUDIO_COMMON_ERR_INVALID_PARAMS;
        }
        TXCLog.e(h, "record has started, can not start again!");
        return TXEAudioDef.TXE_AUDIO_RECORD_ERR_REPEAT_OPTION;
    }

    public int b() {
        int i = TXEAudioDef.TXE_AUDIO_RECORD_ERR_OK;
        if (this.v != null) {
            i = this.v.stopRecord();
            this.v = null;
        }
        this.i = null;
        this.j = a;
        this.k = b;
        this.l = a.h;
        this.m = d;
        this.n = false;
        this.o = e;
        this.p = false;
        this.q = false;
        this.r = null;
        this.s = 1.0f;
        this.t = -1;
        this.u = -1;
        g();
        com.tencent.liteav.audio.impl.c.a().b((e) this);
        return i;
    }

    public boolean c() {
        return this.v != null ? this.v.isRecording() : false;
    }

    public void a(boolean z) {
        this.q = z;
        if (this.v != null) {
            this.v.setIsCustomRecord(z);
        }
    }

    public void a(d dVar) {
        this.i = new WeakReference(dVar);
        if (this.v != null) {
            this.v.setListener(dVar);
        }
    }

    public int d() {
        return this.k;
    }

    public int e() {
        return this.j;
    }

    public void a(int i) {
        String str = h;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setSampleRate: ");
        stringBuilder.append(i);
        TXCLog.i(str, stringBuilder.toString());
        this.j = i;
        if (this.v != null) {
            this.v.setSamplerate(i);
        }
    }

    public void c(int i) {
        String str = h;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setChannels: ");
        stringBuilder.append(i);
        TXCLog.i(str, stringBuilder.toString());
        this.k = i;
        if (this.v != null) {
            this.v.setChannels(i);
        }
    }

    public void d(int i) {
        String str = h;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setReverbType: ");
        stringBuilder.append(i);
        TXCLog.i(str, stringBuilder.toString());
        this.m = i;
        if (this.v != null) {
            this.v.setReverbType(i);
        }
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
            if (this.o != i) {
                if (this.v != null && this.v.isRecording()) {
                    this.v.stopRecord();
                    this.v = null;
                    this.o = i;
                    a(this.r);
                }
                this.o = i;
            }
            if (this.v != null) {
                this.v.setAECType(i);
            }
            return;
        }
        TXCLog.e(h, "set aec type failed, check trae library failed!!");
    }

    public void c(boolean z) {
        String str = h;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setMute: ");
        stringBuilder.append(z);
        TXCLog.i(str, stringBuilder.toString());
        this.n = z;
        if (this.v != null) {
            this.v.setMute(z);
        }
    }

    public void a(float f) {
        String str = h;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setVolume: ");
        stringBuilder.append(f);
        TXCLog.i(str, stringBuilder.toString());
        this.s = f;
        if (this.v != null) {
            this.v.setVolume(f);
        }
    }

    public void a(int i, int i2) {
        String str = h;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setChangerType: ");
        stringBuilder.append(i);
        stringBuilder.append(" ");
        stringBuilder.append(i2);
        TXCLog.i(str, stringBuilder.toString());
        this.t = i;
        this.u = i2;
        if (this.v != null) {
            this.v.setChangerType(i, i2);
        }
    }

    public void a(byte[] bArr) {
        if (this.v != null) {
            this.v.sendCustomPCMData(bArr);
        }
    }

    public int f() {
        String str;
        StringBuilder stringBuilder;
        if (f.a().c()) {
            if (this.o != TXEAudioDef.TXE_AEC_TRAE) {
                return this.o;
            }
            str = h;
            stringBuilder = new StringBuilder();
            stringBuilder.append("audio mic has start, but aec type is trae!!");
            stringBuilder.append(this.o);
            TXCLog.e(str, stringBuilder.toString());
            return TXEAudioDef.TXE_AEC_NONE;
        } else if (!TXCTraeJNI.nativeTraeIsRecording()) {
            return TXEAudioDef.TXE_AEC_NONE;
        } else {
            if (this.o == TXEAudioDef.TXE_AEC_TRAE) {
                return this.o;
            }
            str = h;
            stringBuilder = new StringBuilder();
            stringBuilder.append("trae engine has start, but aec type is not trae!!");
            stringBuilder.append(this.o);
            TXCLog.e(str, stringBuilder.toString());
            return TXEAudioDef.TXE_AEC_TRAE;
        }
    }

    private void g() {
        if (this.i != null) {
            a((d) this.i.get());
        }
        a(this.q);
        a(this.j);
        c(this.k);
        d(this.m);
        a(this.o, this.r);
        b(this.p);
        c(this.n);
        a(this.s);
        a(this.t, this.u);
    }

    public void b(int i) {
        switch (i) {
            case 0:
                if (this.v != null) {
                    this.v.setMute(this.n);
                    return;
                }
                return;
            case 1:
                if (this.v != null) {
                    this.v.setMute(true);
                    return;
                }
                return;
            case 2:
                if (this.v != null) {
                    this.v.setMute(true);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
