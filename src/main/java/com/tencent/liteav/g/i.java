package com.tencent.liteav.g;

import android.media.MediaFormat;
import android.os.Build.VERSION;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.e;
import com.tencent.liteav.e.ac;
import java.io.IOException;

/* compiled from: VideoExtractConfig */
public class i {
    public String a;
    public k b;
    private e c;
    private ac d;
    private f e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private MediaFormat j;
    private MediaFormat k;

    public void a(String str) {
        this.a = str;
    }

    public void a() {
        if (this.c != null) {
            this.c.o();
        }
    }

    public int b() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("createMediaExtractor videoSourcePath:");
        stringBuilder.append(this.a);
        TXCLog.i("VideoExtractConfig", stringBuilder.toString());
        this.c = new e();
        try {
            return this.c.a(this.a);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void c() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("resetVideoMediaExtractor videoSourcePath:");
        stringBuilder.append(this.a);
        TXCLog.i("VideoExtractConfig", stringBuilder.toString());
        this.c.a(0);
    }

    public void d() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("resetAudioMediaExtractor videoSourcePath:");
        stringBuilder.append(this.a);
        TXCLog.i("VideoExtractConfig", stringBuilder.toString());
        this.c.c(0);
    }

    public MediaFormat e() {
        return this.j == null ? this.c.l() : this.j;
    }

    public MediaFormat f() {
        return this.k == null ? this.c.m() : this.k;
    }

    public int g() {
        return this.c.g();
    }

    public long h() {
        if (VERSION.SDK_INT >= 16) {
            MediaFormat e = e();
            if (e != null) {
                return e.getLong("durationUs");
            }
        }
        return 0;
    }

    public long i() {
        if (VERSION.SDK_INT >= 16) {
            MediaFormat f = f();
            if (f != null) {
                return f.getLong("durationUs");
            }
        }
        return 0;
    }

    public long j() {
        if (f() == null) {
            TXCLog.i("VideoExtractConfig", "getAudioFormat is null");
            return h();
        } else if (e() != null) {
            long h = h();
            long i = i();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getDuration vd:");
            stringBuilder.append(h);
            stringBuilder.append(",ad:");
            stringBuilder.append(i);
            TXCLog.i("VideoExtractConfig", stringBuilder.toString());
            if (h <= i) {
                h = i;
            }
            return h;
        } else {
            TXCLog.i("VideoExtractConfig", "getVideoFormat is null");
            return 0;
        }
    }

    public void k() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("createVideoDecoder videoSourcePath1111:");
        stringBuilder.append(this.a);
        TXCLog.i("VideoExtractConfig", stringBuilder.toString());
        if (this.b.c == null) {
            TXCLog.e("VideoExtractConfig", "createVideoDecoder videoGLTextureInfo.surface is null");
            return;
        }
        this.d = new ac();
        this.j = this.c.l();
        this.d.a(this.j);
        this.d.a(this.c.l(), this.b.c);
        this.d.a();
        this.f = false;
        this.h = false;
    }

    public void l() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("destroyVideoDecoder videoSourcePath:");
        stringBuilder.append(this.a);
        TXCLog.i("VideoExtractConfig", stringBuilder.toString());
        if (this.d != null) {
            this.d.b();
            this.d = null;
        }
    }

    public void m() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("createAudioDecoder videoSourcePath:");
        stringBuilder.append(this.a);
        TXCLog.i("VideoExtractConfig", stringBuilder.toString());
        this.e = new f();
        this.k = this.c.m();
        this.e.a(this.k);
        this.e.a(this.k, null);
        this.e.a();
        if (this.k == null) {
            this.g = true;
            this.i = true;
            return;
        }
        this.g = false;
        this.i = false;
    }

    public void n() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("destroyAudioDecoder videoSourcePath:");
        stringBuilder.append(this.a);
        TXCLog.i("VideoExtractConfig", stringBuilder.toString());
        if (this.e != null) {
            this.e.b();
            this.e = null;
        }
    }

    public boolean o() {
        return this.h;
    }

    public boolean p() {
        return this.i;
    }

    public void q() {
        if (this.f) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("readVideoFrame source:");
            stringBuilder.append(this.a);
            stringBuilder.append(" readEOF!");
            TXCLog.i("VideoExtractConfig", stringBuilder.toString());
            return;
        }
        e c = this.d.c();
        if (c != null) {
            c = this.c.a(c);
            if (this.c.c(c)) {
                this.f = true;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("readVideoFrame source:");
                stringBuilder2.append(this.a);
                stringBuilder2.append(" readEOF!");
                TXCLog.i("VideoExtractConfig", stringBuilder2.toString());
            }
            this.d.a(c);
        }
    }

    public void r() {
        if (this.g) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("readAudioFrame source:");
            stringBuilder.append(this.a);
            stringBuilder.append(" readEOF!");
            TXCLog.i("VideoExtractConfig", stringBuilder.toString());
            return;
        }
        e c = this.e.c();
        if (c != null) {
            c = this.c.b(c);
            if (this.c.d(c)) {
                this.g = true;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("readAudioFrame source:");
                stringBuilder2.append(this.a);
                stringBuilder2.append(" readEOF!");
                TXCLog.i("VideoExtractConfig", stringBuilder2.toString());
            }
            this.e.a(c);
        }
    }

    public e s() {
        e d = this.d.d();
        if (d == null || d.o() == null) {
            return null;
        }
        a(d);
        if (d.p()) {
            TXCLog.i("VideoExtractConfig", "getDecodeVideoFrame frame.isEndFrame");
            this.h = true;
        }
        return d;
    }

    public e t() {
        e d = this.e.d();
        if (d == null || d.o() == null) {
            return null;
        }
        b(d);
        if (d.p()) {
            TXCLog.i("VideoExtractConfig", "getDecodeAudioFrame frame.isEndFrame");
            this.i = true;
        }
        return d;
    }

    private void a(e eVar) {
        eVar.j(this.c.b());
        eVar.k(this.c.c());
        eVar.e(this.c.f());
        eVar.f(this.c.e());
    }

    private void b(e eVar) {
        eVar.g(this.c.h());
        eVar.h(this.c.i());
    }
}
