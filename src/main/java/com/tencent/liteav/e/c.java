package com.tencent.liteav.e;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.view.Surface;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.g.e;
import com.tencent.liteav.g.f;
import com.tencent.ugc.TXVideoEditConstants;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@TargetApi(16)
/* compiled from: BasicVideoDecDemux */
public class c {
    protected e a;
    protected ac b;
    protected f c;
    protected Surface d;
    protected h e;
    protected k f;
    protected AtomicLong g;
    protected AtomicLong h;
    protected int i;
    protected String j;
    private final String k = "BasicVideoDecDemux";
    private int l;

    public int a(String str) throws IOException {
        this.j = str;
        this.a = new e();
        int a = this.a.a(str);
        if (a != TXVideoEditConstants.ERR_UNSUPPORT_LARGE_RESOLUTION && a != 0) {
            return a;
        }
        this.l = 0;
        return a;
    }

    public void a(k kVar) {
        this.f = kVar;
    }

    public void a(h hVar) {
        this.e = hVar;
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        TXCLog.i("BasicVideoDecDemux", "configureVideo");
        this.b = new ac();
        this.b.a(this.a.l());
        this.b.a(this.a.l(), this.d);
        this.b.a();
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        TXCLog.i("BasicVideoDecDemux", "configureAudio");
        this.c = new f();
        MediaFormat m = this.a.m();
        this.c.a(m);
        this.c.a(m, null);
        this.c.a();
    }

    public synchronized void a(Surface surface) {
        this.d = surface;
    }

    public long c() {
        return this.a.a();
    }

    public int d() {
        return this.a.b();
    }

    public int e() {
        return this.a.c();
    }

    public MediaFormat f() {
        return this.a.m();
    }

    public MediaFormat g() {
        return this.a.l();
    }

    public boolean h() {
        boolean z = false;
        if (this.a == null) {
            return false;
        }
        if (this.a.m() != null) {
            z = true;
        }
        return z;
    }

    public boolean i() {
        return this.a.l() != null;
    }

    /* Access modifiers changed, original: protected */
    public long a(com.tencent.liteav.d.e eVar) {
        TXCLog.e("BasicVideoDecDemux", "seekFinalVideo, read is end frame, try to find final video frame(not end frame)");
        long j = (long) ((1000 / j()) * 1000);
        long j2 = this.a.j();
        int i = 1;
        if (j2 <= 0) {
            j2 = this.h.get();
        }
        while (i <= 3) {
            long j3 = j2 - (((long) i) * j);
            if (j3 < 0) {
                j3 = j2;
            }
            this.a.a(j3);
            this.a.a(eVar);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("seekReversePTS, seek End PTS = ");
            stringBuilder.append(j3);
            stringBuilder.append(", flags = ");
            stringBuilder.append(eVar.f());
            stringBuilder.append(", seekEndCount = ");
            stringBuilder.append(i);
            TXCLog.i("BasicVideoDecDemux", stringBuilder.toString());
            if (!eVar.p()) {
                return eVar.e();
            }
            i++;
        }
        return 0;
    }

    /* Access modifiers changed, original: protected */
    public boolean a(long j, long j2, com.tencent.liteav.d.e eVar) {
        long j3 = j;
        com.tencent.liteav.d.e eVar2 = eVar;
        if (j3 <= this.g.get()) {
            TXCLog.i("BasicVideoDecDemux", "seekReversePTS, lastReadPTS <= mStartTime");
            this.a.a(j3);
            this.i++;
            if (this.i < 2) {
                return false;
            }
            this.b.b(eVar2);
            return true;
        }
        long j4 = 1000;
        long j5 = j3 - 1000;
        this.a.a(j5);
        long p = this.a.p();
        if (p < j3) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("seekReversePTS, seekPTS = ");
            stringBuilder.append(j5);
            stringBuilder.append(", find previous pts = ");
            stringBuilder.append(p);
            TXCLog.i("BasicVideoDecDemux", stringBuilder.toString());
            return false;
        }
        int i = 1;
        while (true) {
            long j6 = j3 - ((((long) i) * j2) + j4);
            long j7 = 0;
            if (j6 >= 0) {
                j7 = j6;
            }
            this.a.a(j7);
            j6 = this.a.p();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("seekReversePTS, may be SEEK_TO_PREVIOUS_SYNC same to NEXT_SYNC, seekPTS = ");
            stringBuilder2.append(j7);
            stringBuilder2.append(", find previous pts = ");
            stringBuilder2.append(j6);
            stringBuilder2.append(", count = ");
            stringBuilder2.append(i);
            TXCLog.i("BasicVideoDecDemux", stringBuilder2.toString());
            if (j6 < j3) {
                return false;
            }
            i++;
            if (i > 10) {
                this.b.b(eVar2);
                return true;
            }
            j4 = 1000;
        }
    }

    public int j() {
        if (this.l != 0) {
            return this.l;
        }
        MediaFormat l = this.a.l();
        if (l != null) {
            try {
                this.l = l.getInteger("frame-rate");
            } catch (NullPointerException unused) {
                this.l = 20;
            }
        }
        return this.l;
    }

    public void k() {
        if (this.a != null) {
            this.a.o();
        }
    }
}
