package com.tencent.liteav.g;

import android.media.MediaFormat;
import android.os.Build.VERSION;
import com.tencent.liteav.c.i;
import com.tencent.liteav.d.g;

/* compiled from: VideoOutputListConfig */
public class s extends i {
    private static s v;
    public int t;
    public int u;

    public static s r() {
        if (v == null) {
            v = new s();
        }
        return v;
    }

    private s() {
    }

    public g a(boolean z) {
        g gVar = new g();
        gVar.c = 0;
        switch (this.u) {
            case 0:
                gVar.a = 360;
                gVar.b = 640;
                break;
            case 1:
                gVar.a = 480;
                gVar.b = 640;
                break;
            case 2:
                gVar.a = 540;
                gVar.b = 960;
                break;
            case 3:
                gVar.a = 720;
                gVar.b = 1280;
                break;
        }
        return z ? f(gVar) : gVar;
    }

    private g f(g gVar) {
        int i = gVar.b;
        gVar.b = gVar.a;
        gVar.a = i;
        return gVar;
    }

    public void d(MediaFormat mediaFormat) {
        if (VERSION.SDK_INT >= 16) {
            this.a = mediaFormat.getInteger("sample-rate");
            this.b = mediaFormat.getInteger("channel-count");
        }
    }
}
