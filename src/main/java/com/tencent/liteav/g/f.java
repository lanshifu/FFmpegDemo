package com.tencent.liteav.g;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.view.Surface;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.e;
import com.tencent.liteav.videoediter.ffmpeg.c;
import java.util.concurrent.atomic.AtomicBoolean;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

@TargetApi(16)
/* compiled from: TXAudioDecoderWrapper */
public class f implements b {
    private static final String[] c = new String[]{"Xiaomi - MI 3"};
    private AtomicBoolean a = new AtomicBoolean(false);
    private b b;
    private boolean d;

    private boolean b(MediaFormat mediaFormat) {
        String string = mediaFormat.getString(IMediaFormat.KEY_MIME);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" mime type = ");
        stringBuilder.append(string);
        TXCLog.i("TXAudioDecoderWrapper", stringBuilder.toString());
        if (string == null || !c.a(string)) {
            TXCLog.i("TXAudioDecoderWrapper", "isUseSw: use hw decoder!");
            return false;
        }
        TXCLog.i("TXAudioDecoderWrapper", "isUseSw: support mime type! use sw decoder!");
        return true;
    }

    public void a(MediaFormat mediaFormat) {
        if (mediaFormat == null) {
            this.a.set(false);
            return;
        }
        this.a.set(true);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("createDecoderByFormat: ");
        stringBuilder.append(mediaFormat.toString());
        TXCLog.i("TXAudioDecoderWrapper", stringBuilder.toString());
        this.d = b(mediaFormat);
        if (this.d) {
            this.b = new c();
        } else {
            this.b = new g();
        }
        this.b.a(mediaFormat);
    }

    public boolean e() {
        return this.d;
    }

    public void a(MediaFormat mediaFormat, Surface surface) {
        if (mediaFormat == null) {
            this.a.set(false);
            return;
        }
        this.a.set(true);
        this.b.a(mediaFormat, surface);
    }

    public void a() {
        if (this.a.get()) {
            this.b.a();
        }
    }

    public void b() {
        if (this.a.get()) {
            this.b.b();
        }
    }

    public e c() {
        return this.a.get() ? this.b.c() : null;
    }

    public void a(e eVar) {
        if (this.a.get()) {
            this.b.a(eVar);
        }
    }

    public e d() {
        return this.a.get() ? this.b.d() : null;
    }

    public e a(e eVar, e eVar2) {
        if (!this.a.get()) {
            return null;
        }
        eVar2.k(eVar.n());
        eVar2.j(eVar.m());
        eVar2.f(eVar.i());
        eVar2.e(eVar.h());
        eVar2.i(eVar.l());
        eVar2.h(eVar.k());
        eVar2.g(eVar.j());
        return eVar2;
    }

    public e b(e eVar) {
        if (!this.a.get()) {
            return null;
        }
        eVar.c(4);
        TXCLog.d("TXAudioDecoderWrapper", "------appendEndFrame----------");
        return eVar;
    }
}
