package com.youdao.sdk.ydonlinetranslate;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.youdao.sdk.app.i;
import com.youdao.sdk.ydtranslate.TranslateSdk;
import defpackage.st;
import defpackage.sx;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public final class OcrTranslateParameters {
    private final LanguageOcrTranslate mFrom;
    private final String mSource;
    private final int mTimeout;
    private final LanguageOcrTranslate mTo;

    public static final class Builder {
        private String format = st.d;
        private LanguageOcrTranslate from = LanguageOcrTranslate.AUTO;
        private int rate = st.a;
        private String sound = st.f;
        private String source;
        private int timeout = 10000;
        private LanguageOcrTranslate to = LanguageOcrTranslate.CHINESE;
        private String voice = st.h;

        public final Builder voice(String str) {
            this.voice = str;
            return this;
        }

        public final Builder source(String str) {
            this.source = str;
            return this;
        }

        public final Builder from(LanguageOcrTranslate languageOcrTranslate) {
            this.from = languageOcrTranslate;
            return this;
        }

        public final Builder to(LanguageOcrTranslate languageOcrTranslate) {
            this.to = languageOcrTranslate;
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

        public final OcrTranslateParameters build() {
            return new OcrTranslateParameters(this);
        }
    }

    public OcrTranslateParameters(Builder builder) {
        this.mSource = builder.source;
        this.mFrom = builder.from;
        this.mTo = builder.to;
        this.mTimeout = builder.timeout;
    }

    public final String getSource() {
        return this.mSource;
    }

    public final String getAppKey() {
        return i.a;
    }

    public LanguageOcrTranslate getFrom() {
        return this.mFrom;
    }

    public LanguageOcrTranslate getTo() {
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
        c.put("type", "1");
        c.put("channel", "1");
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
