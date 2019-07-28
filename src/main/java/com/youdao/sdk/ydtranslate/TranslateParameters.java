package com.youdao.sdk.ydtranslate;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.youdao.sdk.app.Language;
import com.youdao.sdk.app.i;
import defpackage.st;
import defpackage.sx;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public final class TranslateParameters {
    private final Language mFrom;
    private final String mSound;
    private final String mSource;
    private final int mTimeout;
    private final Language mTo;
    private final String mVoice;
    private final boolean useAutoConvertLine;
    private final boolean useAutoConvertWord;

    public static final class Builder {
        private Language from;
        private String sound = st.g;
        private String source;
        private int timeout;
        private Language to;
        private boolean useAutoConvertLine = true;
        private boolean useAutoConvertWord = true;
        private String voice = st.h;

        public final Builder voice(String str) {
            this.voice = str;
            return this;
        }

        public final Builder sound(String str) {
            this.sound = str;
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

        public final Builder useAutoConvertLine(boolean z) {
            this.useAutoConvertLine = z;
            return this;
        }

        public final Builder useAutoConvertWord(boolean z) {
            this.useAutoConvertWord = z;
            return this;
        }

        public final TranslateParameters build() {
            return new TranslateParameters(this);
        }
    }

    public TranslateParameters(Builder builder) {
        this.mSource = builder.source;
        this.mFrom = builder.from;
        this.mTo = builder.to;
        this.mTimeout = builder.timeout;
        this.useAutoConvertLine = builder.useAutoConvertLine;
        this.useAutoConvertWord = builder.useAutoConvertWord;
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
        if (this.mFrom == null) {
            return Language.CHINESE;
        }
        return this.mFrom;
    }

    public Language getTo() {
        if (this.mTo == null) {
            return Language.ENGLISH;
        }
        return this.mTo;
    }

    public boolean isUseAutoConvertLine() {
        return this.useAutoConvertLine;
    }

    public boolean isUseAutoConvertWord() {
        return this.useAutoConvertWord;
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
        c.put("offline", "0");
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

    public String getTranslateType() {
        StringBuilder stringBuilder;
        if (this.mFrom == Language.SPANISH && this.mTo == Language.CHINESE) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.mTo.getCode());
            stringBuilder.append("-");
            stringBuilder.append(this.mFrom.getCode());
            return stringBuilder.toString();
        } else if (this.mFrom == Language.INDO && this.mTo == Language.ENGLISH) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.mTo.getCode());
            stringBuilder.append("-");
            stringBuilder.append(this.mFrom.getCode());
            return stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.mFrom.getCode());
            stringBuilder.append("-");
            stringBuilder.append(this.mTo.getCode());
            return stringBuilder.toString();
        }
    }
}
