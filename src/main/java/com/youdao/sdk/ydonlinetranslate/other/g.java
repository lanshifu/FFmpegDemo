package com.youdao.sdk.ydonlinetranslate.other;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.rtmp.TXLiveConstants;
import com.tomatolive.library.websocket.nvwebsocket.ConnectSocketParams;
import com.youdao.sdk.app.Language;
import com.youdao.sdk.app.b;
import com.youdao.sdk.app.c;
import com.youdao.sdk.app.d;
import com.youdao.sdk.app.i;
import com.youdao.sdk.ydtranslate.Translate;
import com.youdao.sdk.ydtranslate.TranslateErrorCode;
import com.youdao.sdk.ydtranslate.TranslateListener;
import com.youdao.sdk.ydtranslate.TranslateParameters;
import com.youdao.sdk.ydtranslate.WebExplain;
import defpackage.sy;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener;

public class g {
    public static void a(String str, TranslateListener translateListener, TranslateParameters translateParameters, Context context, String str2) {
        HashMap hashMap = new HashMap();
        String[] a = b.a(translateParameters.paramString(context, str));
        hashMap.put("s", a[0]);
        hashMap.put("et", a[1]);
        String str3 = i.a() ? "http://openapi-sg.youdao.com" : "http://openapi.youdao.com";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str3);
        stringBuilder.append("/openapi?");
        str3 = stringBuilder.toString();
        if (translateParameters.getFrom() == Language.INDO || translateParameters.getTo() == Language.INDO) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("http://fanyi-test.youdao.com/translate_a?q=");
            stringBuilder2.append(URLEncoder.encode(str));
            stringBuilder2.append("&from=");
            stringBuilder2.append(translateParameters.getFrom().getCode());
            stringBuilder2.append("&to=");
            stringBuilder2.append(translateParameters.getTo().getCode());
            str3 = stringBuilder2.toString();
        }
        c.a(str3, hashMap, new h(str, translateListener, str2), translateParameters.getTimeout());
    }

    public static Translate a(String str, String str2) {
        Translate translate = new Translate();
        try {
            JSONObject jSONObject = new JSONObject(str);
            translate.setTranslations(d.a(jSONObject, "translation"));
            translate.setErrorCode(d.a(jSONObject, "errorCode", TranslateErrorCode.JSON_PARSE_ERROR.getCode()));
            translate.setQuery(d.a(jSONObject, "query", str2));
            JSONObject b = d.b(jSONObject, "webdict");
            if (b != null) {
                translate.setDeeplink(d.a(b, OnNativeInvokeListener.ARG_URL, ""));
            }
            b = d.b(jSONObject, "dict");
            if (b != null) {
                translate.setDictDeeplink(d.a(b, OnNativeInvokeListener.ARG_URL, ""));
            }
            str = d.a(jSONObject, "l", "");
            int i = 0;
            if (!TextUtils.isEmpty(str)) {
                String[] split = str.split(ConnectSocketParams.EFFECT_TYPE_BIG);
                if (split.length == 2) {
                    translate.setFrom(split[0]);
                    translate.setTo(split[1]);
                    Language language = Language.getLanguage(translate.getFrom());
                    Language language2 = Language.getLanguage(translate.getTo());
                    if (language != Language.CHINESE) {
                        translate.setLe(language.getVoiceCode());
                    } else if (language2 == Language.CHINESE) {
                        translate.setLe(Language.ENGLISH.getVoiceCode());
                    } else {
                        translate.setLe(language2.getVoiceCode());
                    }
                }
            }
            translate.setSpeakUrl(d.a(jSONObject, "speakUrl", ""));
            translate.setResultSpeakUrl(d.a(jSONObject, "tSpeakUrl", ""));
            if (!jSONObject.isNull("basic")) {
                b = jSONObject.getJSONObject("basic");
                translate.setPhonetic(d.a(b, "phonetic", ""));
                translate.setUkPhonetic(d.a(b, "uk-phonetic", ""));
                translate.setUsPhonetic(d.a(b, "us-phonetic", ""));
                translate.setUSSpeakUrl(d.a(b, "us-speech", ""));
                translate.setUKSpeakUrl(d.a(b, "uk-speech", ""));
                translate.setExplains(d.a(b, "explains"));
            }
            if (!jSONObject.isNull("web")) {
                JSONArray jSONArray = jSONObject.getJSONArray("web");
                ArrayList arrayList = new ArrayList();
                while (i < jSONArray.length()) {
                    jSONObject = jSONArray.getJSONObject(i);
                    WebExplain webExplain = new WebExplain();
                    webExplain.setKey(d.a(jSONObject, "key", ""));
                    webExplain.setMeans(d.a(jSONObject, "value"));
                    arrayList.add(webExplain);
                    i++;
                }
                translate.setWebExplains(arrayList);
            }
        } catch (Exception e) {
            sy.c("json parse error", e);
        }
        return translate;
    }

    public static TranslateErrorCode a(int i) {
        if (i == 1) {
            return TranslateErrorCode.HTTP_REQUEST_ERROR;
        }
        if (i == 100) {
            return TranslateErrorCode.INPUT_PARAM_ILLEGAL;
        }
        if (i == 101) {
            return TranslateErrorCode.INPUT_PARAM_ILLEGAL_MUST;
        }
        if (i == 102) {
            return TranslateErrorCode.INPUT_PARAM_ILLEGAL_NOT_SPPORT_LANG;
        }
        if (i == 103) {
            return TranslateErrorCode.INPUT_PARAM_ILLEGAL_TEXT_TOO_LONG;
        }
        if (i == 104) {
            return TranslateErrorCode.INPUT_PARAM_ILLEGAL_VER_NOT_SUPPORTED;
        }
        if (i == 105) {
            return TranslateErrorCode.INPUT_PARAM_ILLEGAL_SIGN_NOT_SUPPORTED;
        }
        if (i == 106) {
            return TranslateErrorCode.INPUT_PARAM_ILLEGAL_RESPONSE;
        }
        if (i == 107) {
            return TranslateErrorCode.INPUT_PARAM_ILLEGAL_ENCRYPT;
        }
        if (i == 108) {
            return TranslateErrorCode.INPUT_PARAM_ILLEGAL_KEY_INVALID;
        }
        if (i == 109) {
            return TranslateErrorCode.INVALID_BATCH_LOG;
        }
        if (i == 110) {
            return TranslateErrorCode.INVALID_INSTANCE_KEY;
        }
        if (i == 111) {
            return TranslateErrorCode.INVALID_DEVELOPERID;
        }
        if (i == 112) {
            return TranslateErrorCode.INVALID_PRODUCTID;
        }
        if (i == 113) {
            return TranslateErrorCode.INVALID_TEXTS_INPUT;
        }
        if (i == 201) {
            return TranslateErrorCode.INPUT_DECRYPTION_ERROR;
        }
        if (i == 202) {
            return TranslateErrorCode.INPUT_DECRYPTION_ERROR_SIGN;
        }
        if (i == 203) {
            return TranslateErrorCode.INVALID_IP;
        }
        if (i == 301) {
            return TranslateErrorCode.SERVER_LOOKUP_DICT_ERROR;
        }
        if (i == 302) {
            return TranslateErrorCode.SERVER_LOOKUP_MINORITY_ERROR;
        }
        if (i == 303) {
            return TranslateErrorCode.SERVER_LOOKUP_ERROR;
        }
        if (i == 401) {
            return TranslateErrorCode.ACCOUNT_OVERDUE_BILL;
        }
        if (i == 411) {
            return TranslateErrorCode.TRANS_MAX_QUERY_COUNT_ERROR;
        }
        if (i == 412) {
            return TranslateErrorCode.TRANS_MAX_QUERY_LENGTH_ERROR;
        }
        if (i == TXLiveConstants.PLAY_EVT_RCV_FIRST_I_FRAME) {
            return TranslateErrorCode.TRANS_LANGUAGE_ERROR;
        }
        if (i == TXLiveConstants.PLAY_EVT_PLAY_BEGIN) {
            return TranslateErrorCode.TRANS_CHARACTER_ERROR;
        }
        return TranslateErrorCode.UN_SPECIFIC_ERROR;
    }
}
