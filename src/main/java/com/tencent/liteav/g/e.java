package com.tencent.liteav.g;

import android.annotation.TargetApi;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.videoediter.ffmpeg.a;
import com.tencent.ugc.TXVideoEditConstants;
import java.io.IOException;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

@TargetApi(16)
/* compiled from: MediaExtractorWrapper */
public class e {
    private static int g;
    private static int h;
    private a a;
    private MediaExtractor b;
    private MediaExtractor c;
    private MediaFormat d;
    private MediaFormat e;
    private long f;
    private int i;
    private long j;
    private String k;
    private boolean l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;

    public e() {
        this.f = -1;
        this.l = false;
        this.a = new a();
    }

    public e(boolean z) {
        this.f = -1;
        this.l = z;
        this.a = new a();
    }

    public int a(String str) throws IOException {
        this.k = str;
        if (this.b != null) {
            this.b.release();
        }
        if (this.c != null) {
            this.c.release();
        }
        if (this.l) {
            this.c = new MediaExtractor();
            this.c.setDataSource(str);
        } else {
            this.c = new MediaExtractor();
            this.b = new MediaExtractor();
            this.b.setDataSource(str);
            this.c.setDataSource(str);
        }
        this.a.a(str);
        return s();
    }

    private int s() {
        int trackCount = this.c.getTrackCount();
        if (trackCount == 0) {
            TXCLog.i("MediaExtractorWrapper", "prepareMediaFileInfo count == 0");
            return TXVideoEditConstants.ERR_UNSUPPORT_VIDEO_FORMAT;
        }
        int i;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" trackCount = ");
        stringBuilder.append(trackCount);
        TXCLog.i("MediaExtractorWrapper", stringBuilder.toString());
        for (i = 0; i < trackCount; i++) {
            MediaFormat trackFormat = this.c.getTrackFormat(i);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("prepareMediaFileInfo :");
            stringBuilder2.append(trackFormat.toString());
            TXCLog.i("MediaExtractorWrapper", stringBuilder2.toString());
            String string = trackFormat.getString(IMediaFormat.KEY_MIME);
            if (string.startsWith("video")) {
                g = i;
                this.d = trackFormat;
                if (this.b != null) {
                    this.b.selectTrack(i);
                }
            } else if (string.startsWith("audio")) {
                h = i;
                this.e = trackFormat;
                this.c.selectTrack(i);
                int integer = trackFormat.getInteger("channel-count");
                if (integer > 2 || integer < 1) {
                    return -1004;
                }
            } else {
                continue;
            }
        }
        this.i = g();
        if (this.d != null) {
            trackCount = b();
            i = c();
            if ((trackCount > i ? i : trackCount) > 1080) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("prepareMediaFileInfo W:");
                stringBuilder3.append(trackCount);
                stringBuilder3.append(",H:");
                stringBuilder3.append(i);
                TXCLog.i("MediaExtractorWrapper", stringBuilder3.toString());
            }
        }
        return 0;
    }

    public long a() {
        if (this.d == null) {
            return 0;
        }
        StringBuilder stringBuilder;
        if (this.e == null) {
            try {
                if (this.j == 0) {
                    this.j = this.d.getLong("durationUs");
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("mDuration = ");
                    stringBuilder.append(this.j);
                    TXCLog.d("MediaExtractorWrapper", stringBuilder.toString());
                }
                return this.j;
            } catch (NullPointerException unused) {
                TXCLog.d("MediaExtractorWrapper", "空指针异常");
                return 0;
            }
        }
        try {
            if (this.j == 0) {
                long j = this.d.getLong("durationUs");
                long j2 = this.e.getLong("durationUs");
                if (j <= j2) {
                    j = j2;
                }
                this.j = j;
                stringBuilder = new StringBuilder();
                stringBuilder.append("mDuration = ");
                stringBuilder.append(this.j);
                TXCLog.d("MediaExtractorWrapper", stringBuilder.toString());
            }
            return this.j;
        } catch (NullPointerException unused2) {
            TXCLog.d("MediaExtractorWrapper", "空指针异常");
            return 0;
        }
    }

    public int b() {
        if (this.r != 0) {
            return this.r;
        }
        try {
            if (this.d == null) {
                return 0;
            }
            this.r = this.d.getInteger("width");
            return this.r;
        } catch (NullPointerException unused) {
            return 0;
        }
    }

    public int c() {
        if (this.q != 0) {
            return this.q;
        }
        try {
            if (this.d == null) {
                return 0;
            }
            this.q = this.d.getInteger("height");
            return this.q;
        } catch (NullPointerException unused) {
            return 0;
        }
    }

    public int d() {
        if (this.p != 0) {
            return this.p;
        }
        try {
            if (this.d == null) {
                return 0;
            }
            this.p = this.d.getInteger("i-frame-interval");
            return this.p;
        } catch (NullPointerException unused) {
            return 0;
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0015 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:9|10) */
    /* JADX WARNING: Missing block: B:10:?, code skipped:
            r0 = r2.d.getInteger("video-framerate");
     */
    /* JADX WARNING: Missing block: B:11:0x001e, code skipped:
            r0 = 20;
     */
    public int e() {
        /*
        r2 = this;
        r0 = r2.o;
        if (r0 == 0) goto L_0x0007;
    L_0x0004:
        r0 = r2.o;
        return r0;
    L_0x0007:
        r0 = 0;
        r1 = r2.d;	 Catch:{ NullPointerException -> 0x0015 }
        if (r1 == 0) goto L_0x0020;
    L_0x000c:
        r0 = r2.d;	 Catch:{ NullPointerException -> 0x0015 }
        r1 = "frame-rate";
        r0 = r0.getInteger(r1);	 Catch:{ NullPointerException -> 0x0015 }
        goto L_0x0020;
    L_0x0015:
        r0 = r2.d;	 Catch:{ NullPointerException -> 0x001e }
        r1 = "video-framerate";
        r0 = r0.getInteger(r1);	 Catch:{ NullPointerException -> 0x001e }
        goto L_0x0020;
    L_0x001e:
        r0 = 20;
    L_0x0020:
        r2.o = r0;
        r0 = r2.o;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.g.e.e():int");
    }

    public int f() {
        return this.i;
    }

    public int g() {
        int i;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(this.k);
        String extractMetadata = mediaMetadataRetriever.extractMetadata(24);
        if (TextUtils.isEmpty(extractMetadata)) {
            TXCLog.e("MediaExtractorWrapper", "getRotation error: rotation is empty,rotation have been reset to zero");
            i = 0;
        } else {
            i = Integer.parseInt(extractMetadata);
        }
        mediaMetadataRetriever.release();
        this.i = i;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mRotation=");
        stringBuilder.append(this.i);
        stringBuilder.append(",rotation=");
        stringBuilder.append(i);
        TXCLog.d("MediaExtractorWrapper", stringBuilder.toString());
        return i;
    }

    public int h() {
        if (this.n != 0) {
            return this.n;
        }
        try {
            if (this.e == null) {
                return 0;
            }
            this.n = this.e.getInteger("sample-rate");
            return this.n;
        } catch (NullPointerException unused) {
            return 0;
        }
    }

    public int i() {
        if (this.m != 0) {
            return this.m;
        }
        try {
            if (this.e == null) {
                return 0;
            }
            this.m = this.e.getInteger("channel-count");
            return this.m;
        } catch (NullPointerException unused) {
            return 0;
        }
    }

    public long j() {
        if (this.d == null) {
            return 0;
        }
        try {
            return this.d.getLong("durationUs");
        } catch (Exception unused) {
            return 0;
        }
    }

    public long k() {
        if (this.e == null) {
            return 0;
        }
        try {
            return this.e.getLong("durationUs");
        } catch (Exception unused) {
            return 0;
        }
    }

    public com.tencent.liteav.d.e a(com.tencent.liteav.d.e eVar) {
        eVar.a(this.b.getSampleTime());
        int sampleTrackIndex = this.b.getSampleTrackIndex();
        eVar.a(sampleTrackIndex);
        eVar.c(this.b.getSampleFlags());
        eVar.d(this.b.readSampleData(eVar.b(), 0));
        eVar.b().position(0);
        eVar.f(e());
        eVar.e(f());
        eVar.g(h());
        eVar.h(i());
        eVar.j(b());
        eVar.k(c());
        eVar.a(false);
        if (this.f == -1 && sampleTrackIndex == n()) {
            this.f = eVar.e();
        }
        if (eVar.g() <= 0) {
            eVar.d(0);
            eVar.a(0);
            eVar.c(4);
        }
        return eVar;
    }

    public com.tencent.liteav.d.e b(com.tencent.liteav.d.e eVar) {
        eVar.a(this.c.getSampleTime());
        int sampleTrackIndex = this.c.getSampleTrackIndex();
        eVar.a(sampleTrackIndex);
        eVar.c(this.c.getSampleFlags());
        eVar.d(this.c.readSampleData(eVar.b(), 0));
        eVar.b().position(0);
        eVar.e(f());
        eVar.g(h());
        eVar.h(i());
        eVar.j(b());
        eVar.k(c());
        eVar.a(false);
        if (this.f == -1 && sampleTrackIndex == n()) {
            this.f = eVar.e();
        }
        if (eVar.g() <= 0) {
            eVar.d(0);
            eVar.a(0);
            eVar.c(4);
        }
        return eVar;
    }

    public MediaFormat l() {
        return this.d;
    }

    public MediaFormat m() {
        return this.e;
    }

    public int n() {
        return g;
    }

    public boolean c(com.tencent.liteav.d.e eVar) {
        if (eVar.f() == 4) {
            return true;
        }
        this.b.advance();
        return false;
    }

    public boolean d(com.tencent.liteav.d.e eVar) {
        if (eVar.f() == 4) {
            return true;
        }
        this.c.advance();
        return false;
    }

    public void a(long j) {
        this.b.seekTo(j, 0);
    }

    public void b(long j) {
        this.b.seekTo(j, 1);
    }

    public void c(long j) {
        this.c.seekTo(j, 0);
    }

    public void o() {
        if (this.b != null) {
            this.b.release();
        }
        if (this.c != null) {
            this.c.release();
        }
    }

    public long p() {
        return this.b.getSampleTime();
    }

    public long q() {
        return this.c.getSampleTime();
    }

    public long r() {
        return this.b.getSampleTime();
    }
}
