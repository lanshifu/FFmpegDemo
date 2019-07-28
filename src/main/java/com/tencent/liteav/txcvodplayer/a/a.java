package com.tencent.liteav.txcvodplayer.a;

/* compiled from: TXCVodCacheInfo */
public class a {
    String a;
    String b;
    String c;

    a(String str, String str2, String str3) {
        this.a = str2;
        this.b = str;
        this.c = str3;
    }

    public String a() {
        return "mp4".equals(this.c) ? c() : null;
    }

    public String b() {
        return "m3u8".equals(this.c) ? c() : null;
    }

    public String c() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        stringBuilder.append("/");
        stringBuilder.append(this.b);
        return stringBuilder.toString();
    }

    public String d() {
        return this.b;
    }
}
