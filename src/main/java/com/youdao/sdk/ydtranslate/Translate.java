package com.youdao.sdk.ydtranslate;

import android.content.Context;
import android.text.TextUtils;
import com.youdao.sdk.app.Language;
import com.youdao.sdk.app.e;
import com.youdao.sdk.app.g;
import com.youdao.sdk.app.h;
import java.io.Serializable;
import java.util.List;

public class Translate implements Serializable {
    private String UKSpeakUrl;
    private String USSpeakUrl;
    private String deeplink;
    private String dictDeeplink;
    private int errorCode;
    private List<String> explains;
    private String from;
    private String le;
    private String phonetic;
    private String query;
    private String resultSpeakUrl;
    private String speakUrl;
    private String to;
    private List<String> translations;
    private String ukPhonetic;
    private String usPhonetic;
    private List<WebExplain> webExplains;

    public String getDictDeeplink() {
        return this.dictDeeplink;
    }

    public void setDictDeeplink(String str) {
        this.dictDeeplink = str;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String str) {
        this.to = str;
    }

    public String getLe() {
        return this.le;
    }

    public void setLe(String str) {
        this.le = str;
    }

    public String getDeeplink() {
        return this.deeplink;
    }

    public void setDeeplink(String str) {
        this.deeplink = str;
    }

    public void setUKSpeakUrl(String str) {
        this.UKSpeakUrl = str;
    }

    public void setUSSpeakUrl(String str) {
        this.USSpeakUrl = str;
    }

    public boolean success() {
        return this.errorCode == 0;
    }

    public List<WebExplain> getWebExplains() {
        return this.webExplains;
    }

    public void setWebExplains(List<WebExplain> list) {
        this.webExplains = list;
    }

    public String getUsPhonetic() {
        return this.usPhonetic;
    }

    public void setUsPhonetic(String str) {
        this.usPhonetic = str;
    }

    public String getPhonetic() {
        return this.phonetic;
    }

    public void setPhonetic(String str) {
        this.phonetic = str;
    }

    public String getUkPhonetic() {
        return this.ukPhonetic;
    }

    public void setUkPhonetic(String str) {
        this.ukPhonetic = str;
    }

    public List<String> getExplains() {
        return this.explains;
    }

    public void setExplains(List<String> list) {
        this.explains = list;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public List<String> getTranslations() {
        return this.translations;
    }

    public void setTranslations(List<String> list) {
        this.translations = list;
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String str) {
        this.query = str;
    }

    public void setSpeakUrl(String str) {
        this.speakUrl = str;
    }

    public void setResultSpeakUrl(String str) {
        this.resultSpeakUrl = str;
    }

    public void openMore(Context context) {
        if (TextUtils.isEmpty(this.deeplink) || TextUtils.isEmpty(this.dictDeeplink)) {
            if (!TextUtils.isEmpty(this.deeplink)) {
                h.a(context, this.query, this.deeplink, this.from, this.to, this.le);
            } else if (g.b(context)) {
                h.a(context, this.query, this.le, e.b(this.from), e.b(this.to));
            } else {
                this.to = this.to.replace(Language.CHINESE.getCode(), "zh-CN");
                this.to = this.to.replace(Language.ENGLISH.getCode(), "en");
                this.from = this.from.replace(Language.CHINESE.getCode(), "zh-CN");
                this.from = this.from.replace(Language.ENGLISH.getCode(), "en");
                h.a(context, this.query, null, this.from, this.to, this.le);
            }
            return;
        }
        h.a(context, this.query, this.le, e.b(this.from), e.b(this.to), this.deeplink, this.dictDeeplink);
    }

    public String getDictWebUrl() {
        if (TextUtils.isEmpty(this.deeplink)) {
            return h.a(this.query, this.le, e.b(this.from), e.b(this.to));
        }
        return this.deeplink;
    }

    public boolean openDict(Context context) {
        return h.b(context, this.query, this.le, e.b(this.from), e.b(this.to));
    }

    public String getResultSpeakUrl() {
        return this.resultSpeakUrl;
    }

    public String getSpeakUrl() {
        return this.speakUrl;
    }

    public String getUKSpeakUrl() {
        return this.UKSpeakUrl;
    }

    public String getUSSpeakUrl() {
        return this.USSpeakUrl;
    }
}
