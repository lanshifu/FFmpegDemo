package com.tencent.liteav.network.a;

import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

/* compiled from: Record */
public final class e {
    public final String a;
    public final int b;
    public final int c;
    public final long d;

    public e(String str, int i, int i2, long j) {
        this.a = str;
        this.b = i;
        int i3 = IjkMediaCodecInfo.RANK_LAST_CHANCE;
        if (i2 >= IjkMediaCodecInfo.RANK_LAST_CHANCE) {
            i3 = i2;
        }
        this.c = i3;
        this.d = j;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        if (!(this.a.equals(eVar.a) && this.b == eVar.b && this.c == eVar.c && this.d == eVar.d)) {
            z = false;
        }
        return z;
    }

    public boolean a() {
        return this.b == 5;
    }
}
