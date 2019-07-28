package com.tencent.liteav.c;

import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.b;
import com.tencent.liteav.d.g;
import com.tencent.ugc.TXRecordCommon;
import java.io.File;
import java.io.IOException;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: VideoOutputConfig */
public class i {
    private static i t;
    public int a = TXRecordCommon.AUDIO_SAMPLERATE_48000;
    public int b = 1;
    public int c;
    public int d = 98304;
    public int e = 3;
    public int f = 5000;
    public int g = 20;
    public g h;
    public String i;
    public int j;
    public long k;
    public long l;
    public boolean m;
    public boolean n = true;
    public String o;
    public int p;
    public int q;
    public boolean r;
    public int s;
    private MediaFormat u;
    private MediaFormat v;
    private int w = 0;
    private MediaFormat x;

    public static i a() {
        if (t == null) {
            t = new i();
        }
        return t;
    }

    protected i() {
    }

    public boolean b() {
        return TextUtils.isEmpty(this.i);
    }

    public boolean c() {
        return new File(this.i).exists();
    }

    public boolean d() {
        return TextUtils.isEmpty(this.o);
    }

    public boolean e() {
        return this.r && this.m;
    }

    public void f() {
        if (!d()) {
            File file = new File(this.o);
            if (file.exists()) {
                file.delete();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void g() {
        if (!b()) {
            File file = new File(this.i);
            if (file.exists()) {
                file.delete();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int h() {
        if (this.q == 0) {
            return this.d;
        }
        return this.q;
    }

    public int i() {
        if (!this.m) {
            if (this.p == 0) {
                switch (this.j) {
                    case 0:
                    case 1:
                        this.f = 2400;
                        break;
                    case 2:
                        this.f = 6500;
                        break;
                    case 3:
                        this.f = 9600;
                        break;
                }
            }
            this.f = this.p;
        } else if (this.h.a >= 1280 || this.h.b >= 1280) {
            this.f = 15000;
        } else {
            this.f = 24000;
        }
        return this.f;
    }

    public int j() {
        try {
            if (this.x != null && VERSION.SDK_INT >= 16) {
                this.g = this.x.getInteger("frame-rate");
            }
        } catch (NullPointerException unused) {
            this.g = 20;
        }
        return this.g;
    }

    public int k() {
        try {
            if (this.x != null && VERSION.SDK_INT >= 16) {
                this.e = this.x.getInteger("i-frame-interval");
            }
        } catch (NullPointerException unused) {
            this.e = 3;
        }
        return this.e;
    }

    public g a(g gVar) {
        if (gVar.a == 0 || gVar.b == 0) {
            return gVar;
        }
        if (k.a().d() != 2) {
            switch (this.j) {
                case 0:
                    gVar = b(gVar);
                    break;
                case 1:
                    gVar = c(gVar);
                    break;
                case 2:
                    gVar = d(gVar);
                    break;
                case 3:
                    gVar = e(gVar);
                    break;
            }
        }
        switch (this.j) {
            case 0:
                gVar = i(gVar);
                break;
            case 1:
                gVar = h(gVar);
                break;
            case 2:
                gVar = g(gVar);
                break;
            case 3:
                gVar = f(gVar);
                break;
        }
        g gVar2 = new g();
        gVar2.c = gVar.c;
        int e = j.a().e();
        if (e == 90 || e == 270) {
            gVar2.a = ((gVar.b + 15) / 16) * 16;
            gVar2.b = ((gVar.a + 15) / 16) * 16;
        } else {
            gVar2.a = ((gVar.a + 15) / 16) * 16;
            gVar2.b = ((gVar.b + 15) / 16) * 16;
        }
        return gVar2;
    }

    private g f(g gVar) {
        int i;
        int i2;
        g gVar2 = new g();
        if (((float) gVar.a) / ((float) gVar.b) >= 0.5625f) {
            i = 720;
            i2 = (int) ((((float) gVar.b) * 720.0f) / ((float) gVar.a));
        } else {
            i = (int) ((((float) gVar.a) * 1280.0f) / ((float) gVar.b));
            i2 = 1280;
        }
        gVar2.a = ((i + 15) / 16) * 16;
        gVar2.b = ((i2 + 15) / 16) * 16;
        return gVar2;
    }

    private g g(g gVar) {
        int i;
        int i2;
        g gVar2 = new g();
        if (((float) gVar.a) / ((float) gVar.b) >= 0.5625f) {
            i = 540;
            i2 = (int) ((((float) gVar.b) * 540.0f) / ((float) gVar.a));
        } else {
            i = (int) ((((float) gVar.a) * 960.0f) / ((float) gVar.b));
            i2 = 960;
        }
        gVar2.a = ((i + 15) / 16) * 16;
        gVar2.b = ((i2 + 15) / 16) * 16;
        return gVar2;
    }

    private g h(g gVar) {
        int i;
        int i2;
        g gVar2 = new g();
        if (((float) gVar.a) / ((float) gVar.b) >= 0.5625f) {
            i = 360;
            i2 = (int) ((((float) gVar.b) * 360.0f) / ((float) gVar.a));
        } else {
            i = (int) ((((float) gVar.a) * 640.0f) / ((float) gVar.b));
            i2 = 640;
        }
        gVar2.a = ((i + 15) / 16) * 16;
        gVar2.b = ((i2 + 15) / 16) * 16;
        return gVar2;
    }

    private g i(g gVar) {
        int i;
        int i2;
        g gVar2 = new g();
        if (((float) gVar.a) / ((float) gVar.b) >= 0.5625f) {
            i = 720;
            i2 = (int) ((((float) gVar.b) * 720.0f) / ((float) gVar.a));
        } else {
            i = (int) ((((float) gVar.a) * 1280.0f) / ((float) gVar.b));
            i2 = 1280;
        }
        gVar2.a = ((i + 15) / 16) * 16;
        gVar2.b = ((i2 + 15) / 16) * 16;
        return gVar2;
    }

    /* Access modifiers changed, original: protected */
    public g b(g gVar) {
        g gVar2 = new g();
        int i = 360;
        if ((gVar.a <= 640 && gVar.b <= 360) || (gVar.a <= 360 && gVar.b <= 640)) {
            return a(gVar.c, gVar);
        }
        int i2;
        float f = (((float) gVar.a) * 1.0f) / ((float) gVar.b);
        if (gVar.a >= gVar.b) {
            i = (int) (360.0f * f);
            if (i >= 640) {
                i = 640;
            }
            i2 = (int) (((float) i) / f);
        } else {
            int i3 = (int) (640.0f * f);
            if (i3 < 360) {
                i = i3;
            }
            i2 = (int) (((float) i) / f);
        }
        i2 = ((i2 + 1) >> 1) << 1;
        gVar2.a = ((i + 1) >> 1) << 1;
        gVar2.b = i2;
        return a(gVar.c, gVar2);
    }

    /* Access modifiers changed, original: protected */
    public g c(g gVar) {
        g gVar2 = new g();
        int i = 480;
        if ((gVar.a <= 640 && gVar.b <= 480) || (gVar.a <= 480 && gVar.b <= 640)) {
            return a(gVar.c, gVar);
        }
        int i2;
        float f = (((float) gVar.a) * 1.0f) / ((float) gVar.b);
        if (gVar.a >= gVar.b) {
            i = (int) (480.0f * f);
            if (i >= 640) {
                i = 640;
            }
            i2 = (int) (((float) i) / f);
        } else {
            int i3 = (int) (640.0f * f);
            if (i3 < 480) {
                i = i3;
            }
            i2 = (int) (((float) i) / f);
        }
        i2 = ((i2 + 1) >> 1) << 1;
        gVar2.a = ((i + 1) >> 1) << 1;
        gVar2.b = i2;
        return a(gVar.c, gVar2);
    }

    /* Access modifiers changed, original: protected */
    public g d(g gVar) {
        g gVar2 = new g();
        int i = 544;
        if ((gVar.a <= 960 && gVar.b <= 544) || (gVar.a <= 544 && gVar.b <= 960)) {
            return a(gVar.c, gVar);
        }
        int i2;
        float f = (((float) gVar.a) * 1.0f) / ((float) gVar.b);
        if (gVar.a >= gVar.b) {
            i = (int) (544.0f * f);
            if (i >= 960) {
                i = 960;
            }
            i2 = (int) (((float) i) / f);
        } else {
            int i3 = (int) (960.0f * f);
            if (i3 < 544) {
                i = i3;
            }
            i2 = (int) (((float) i) / f);
        }
        i2 = ((i2 + 1) >> 1) << 1;
        gVar2.a = ((i + 1) >> 1) << 1;
        gVar2.b = i2;
        return a(gVar.c, gVar2);
    }

    /* Access modifiers changed, original: protected */
    public g e(g gVar) {
        g gVar2 = new g();
        int i = 720;
        if ((gVar.a <= 1280 && gVar.b <= 720) || (gVar.a <= 720 && gVar.b <= 1280)) {
            return a(gVar.c, gVar);
        }
        int i2;
        float f = (((float) gVar.a) * 1.0f) / ((float) gVar.b);
        if (gVar.a >= gVar.b) {
            i = (int) (720.0f * f);
            if (i >= 1280) {
                i = 1280;
            }
            i2 = (int) (((float) i) / f);
        } else {
            int i3 = (int) (1280.0f * f);
            if (i3 < 720) {
                i = i3;
            }
            i2 = (int) (((float) i) / f);
        }
        i2 = ((i2 + 1) >> 1) << 1;
        gVar2.a = ((i + 1) >> 1) << 1;
        gVar2.b = i2;
        return a(gVar.c, gVar2);
    }

    private g a(int i, g gVar) {
        if (i == 90 || i == 270) {
            i = gVar.a;
            gVar.a = gVar.b;
            gVar.b = i;
        }
        return gVar;
    }

    public void a(MediaFormat mediaFormat) {
        this.u = mediaFormat;
    }

    public void b(MediaFormat mediaFormat) {
        this.x = mediaFormat;
    }

    public void c(MediaFormat mediaFormat) {
        this.v = mediaFormat;
    }

    public boolean l() {
        return (this.u == null && this.v == null) ? false : true;
    }

    public boolean m() {
        return this.u == null && this.v != null;
    }

    public MediaFormat n() {
        b bVar = new b();
        MediaFormat mediaFormat = null;
        if (this.v == null) {
            if (this.u == null) {
                return null;
            }
            if (VERSION.SDK_INT >= 16) {
                bVar.b = this.u.getInteger("sample-rate");
                bVar.a = this.u.getInteger("channel-count");
                if (this.u.containsKey(IjkMediaMeta.IJKM_KEY_BITRATE)) {
                    bVar.c = this.u.getInteger(IjkMediaMeta.IJKM_KEY_BITRATE);
                }
            }
        } else if (VERSION.SDK_INT >= 16) {
            int integer;
            int integer2;
            if (this.u == null) {
                integer = this.v.getInteger("sample-rate");
                integer2 = this.v.getInteger("channel-count");
                bVar.b = integer;
                bVar.a = integer2;
                if (this.v.containsKey(IjkMediaMeta.IJKM_KEY_BITRATE)) {
                    bVar.c = this.v.getInteger(IjkMediaMeta.IJKM_KEY_BITRATE);
                }
            } else {
                this.u.getInteger("sample-rate");
                bVar.b = this.v.getInteger("sample-rate");
                integer = this.u.getInteger("channel-count");
                integer2 = this.v.getInteger("channel-count");
                if (integer < integer2) {
                    integer = integer2;
                }
                bVar.a = integer;
                if (this.u.containsKey(IjkMediaMeta.IJKM_KEY_BITRATE)) {
                    bVar.c = this.u.getInteger(IjkMediaMeta.IJKM_KEY_BITRATE);
                }
            }
        }
        if (VERSION.SDK_INT >= 16) {
            mediaFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", bVar.b, bVar.a);
            if (bVar.c != 0) {
                mediaFormat.setInteger(IjkMediaMeta.IJKM_KEY_BITRATE, bVar.c);
            }
        }
        if (this.u != null && VERSION.SDK_INT >= 16 && this.u.containsKey("max-input-size")) {
            this.c = this.u.getInteger("max-input-size");
        }
        this.a = bVar.b;
        this.b = bVar.a;
        if (bVar.c != 0) {
            this.d = bVar.c;
        }
        return mediaFormat;
    }

    public void o() {
        if (!TextUtils.isEmpty(this.o)) {
            File file = new File(this.o);
            if (file.exists()) {
                boolean delete = file.delete();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("clear delete process path:");
                stringBuilder.append(delete);
                TXCLog.i("VideoOutputConfig", stringBuilder.toString());
            }
        }
        this.k = 0;
        this.o = null;
        this.i = null;
        this.v = null;
        this.u = null;
        this.p = 0;
        this.q = 0;
        this.n = true;
    }

    public int p() {
        if (TextUtils.isEmpty(this.i)) {
            return 0;
        }
        return (int) (new File(this.i).length() / IjkMediaMeta.AV_CH_SIDE_RIGHT);
    }

    public int q() {
        return Math.round((float) ((this.k / 1000) / 1000));
    }
}
