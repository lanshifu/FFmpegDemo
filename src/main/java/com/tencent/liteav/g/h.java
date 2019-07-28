package com.tencent.liteav.g;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.videoediter.ffmpeg.a;

/* compiled from: TXMediaRetriever */
public class h {
    private MediaMetadataRetriever a = new MediaMetadataRetriever();
    private a b = new a();

    public void a(String str) {
        try {
            this.a.setDataSource(str);
        } catch (IllegalArgumentException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("set data source error , path = ");
            stringBuilder.append(str);
            TXCLog.e("MediaMetadataRetrieverW", stringBuilder.toString());
            e.printStackTrace();
        }
        this.b.a(str);
    }

    public long a() {
        String extractMetadata = this.a.extractMetadata(9);
        if (!TextUtils.isEmpty(extractMetadata)) {
            return Long.parseLong(extractMetadata);
        }
        TXCLog.e("MediaMetadataRetrieverW", "getDuration error: duration is empty,use ff to get!");
        return c() > b() ? c() : b();
    }

    public long b() {
        return this.b.h();
    }

    public long c() {
        return this.b.f();
    }

    public int d() {
        String extractMetadata = this.a.extractMetadata(24);
        if (!TextUtils.isEmpty(extractMetadata)) {
            return Integer.parseInt(extractMetadata);
        }
        TXCLog.e("MediaMetadataRetrieverW", "getRotation error: rotation is empty,use ff to get!");
        return this.b.a();
    }

    public int e() {
        String extractMetadata = this.a.extractMetadata(19);
        if (!TextUtils.isEmpty(extractMetadata)) {
            return Integer.parseInt(extractMetadata);
        }
        TXCLog.e("MediaMetadataRetrieverW", "getHeight error: height is empty,use ff to get!");
        return this.b.c();
    }

    public int f() {
        String extractMetadata = this.a.extractMetadata(18);
        if (!TextUtils.isEmpty(extractMetadata)) {
            return Integer.parseInt(extractMetadata);
        }
        TXCLog.e("MediaMetadataRetrieverW", "getHeight error: height is empty,use ff to get!");
        return this.b.b();
    }

    public float g() {
        return this.b.d();
    }

    public long h() {
        return this.b.e();
    }

    public int i() {
        return this.b.g();
    }

    public Bitmap a(long j) {
        return this.a.getFrameAtTime(j, 3);
    }

    public Bitmap j() {
        return this.a.getFrameAtTime();
    }

    public void k() {
        this.a.release();
    }
}
