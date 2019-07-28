package com.youdao.sdk.ydonlinetranslate;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.youdao.sdk.app.Language;
import com.youdao.sdk.app.i;
import com.youdao.sdk.ydtranslate.TranslateSdk;
import defpackage.st;
import defpackage.sx;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public final class SpeechTranslateParameters {
    private final String mFormat;
    private final Language mFrom;
    private final int mRate;
    private final String mSound;
    private final String mSource;
    private final int mTimeout;
    private final Language mTo;
    private final String mVoice;

    public static final class Builder {
        private String format = st.d;
        private Language from = Language.AUTO;
        private int rate = st.a;
        private String sound = st.f;
        private String source;
        private int timeout = 10000;
        private Language to = Language.AUTO;
        private String voice = st.h;

        public final Builder voice(String str) {
            this.voice = str;
            return this;
        }

        public final Builder source(String str) {
            this.source = str;
            return this;
        }

        public final Builder from(Language language) {
            this.from = language;
            return this;
        }

        public final Builder to(Language language) {
            this.to = language;
            return this;
        }

        public final Builder timeout(int i) {
            this.timeout = i;
            return this;
        }

        public final Builder rate(int i) {
            this.rate = i;
            return this;
        }

        public final SpeechTranslateParameters build() {
            return new SpeechTranslateParameters(this);
        }
    }

    public SpeechTranslateParameters(Builder builder) {
        this.mSource = builder.source;
        this.mFrom = builder.from;
        this.mTo = builder.to;
        this.mTimeout = builder.timeout;
        this.mFormat = builder.format;
        this.mRate = builder.rate;
        this.mSound = builder.sound;
        this.mVoice = builder.voice;
    }

    public final String getSource() {
        return this.mSource;
    }

    public final String getAppKey() {
        return i.a;
    }

    public Language getFrom() {
        return this.mFrom;
    }

    public Language getTo() {
        return this.mTo;
    }

    public final int getTimeout() {
        if (this.mTimeout < 1) {
            return 10000;
        }
        return this.mTimeout;
    }

    /* Access modifiers changed, original: 0000 */
    public Map<String, String> params(Context context, String str) {
        String appKey = getAppKey();
        sx sxVar = new sx(context);
        sxVar.p(appKey);
        Map c = sxVar.c();
        if (getFrom() != null) {
            c.put("from", getFrom().getCode());
        }
        if (getTo() != null) {
            c.put("to", getTo().getCode());
        }
        int nextInt = new Random().nextInt(1000);
        String sign = new TranslateSdk().sign(context, appKey, str, String.valueOf(nextInt));
        c.put("q", str);
        c.put("salt", String.valueOf(nextInt));
        c.put("signType", "v1");
        c.put("docType", "json");
        c.put("sign", sign);
        c.put("source", this.mSource);
        c.put(IjkMediaMeta.IJKM_KEY_FORMAT, this.mFormat);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mRate);
        stringBuilder.append("");
        c.put("rate", stringBuilder.toString());
        c.put("type", "1");
        c.put("channel", "1");
        c.put("sound", this.mSound);
        c.put("voice", this.mVoice);
        if (this.mTimeout > 0) {
            c.put("timeout", String.valueOf(this.mTimeout));
        }
        return c;
    }

    public String paramString(Context context, String str) {
        Map params = params(context, str);
        StringBuilder stringBuilder = new StringBuilder("");
        for (Entry entry : params.entrySet()) {
            String str2 = (String) entry.getKey();
            String str3 = (String) entry.getValue();
            if (!TextUtils.isEmpty(str3)) {
                stringBuilder.append(str2);
                stringBuilder.append("=");
                stringBuilder.append(Uri.encode(str3));
                stringBuilder.append("&");
            }
        }
        return stringBuilder.toString();
    }
}
