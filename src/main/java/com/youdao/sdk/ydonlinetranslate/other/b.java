package com.youdao.sdk.ydonlinetranslate.other;

import android.content.Context;
import com.youdao.sdk.app.c;
import com.youdao.sdk.app.i;
import com.youdao.sdk.ydonlinetranslate.OcrTranslateListener;
import com.youdao.sdk.ydonlinetranslate.OcrTranslateParameters;
import com.youdao.sdk.ydtranslate.TranslateErrorCode;
import java.util.HashMap;

public class b {
    public static void a(String str, OcrTranslateListener ocrTranslateListener, OcrTranslateParameters ocrTranslateParameters, Context context, String str2) {
        HashMap hashMap = new HashMap();
        String[] a = com.youdao.sdk.app.b.a(ocrTranslateParameters.paramString(context, str));
        hashMap.put("s", a[0]);
        hashMap.put("et", a[1]);
        String str3 = i.a() ? "http://openapi-sg.youdao.com" : "http://nb036x.corp.youdao.com:8681";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str3);
        stringBuilder.append("/ocrtransopenapi");
        c.a(stringBuilder.toString(), hashMap, new c(ocrTranslateListener, str, str2), ocrTranslateParameters.getTimeout());
    }

    private static TranslateErrorCode b(int i) {
        TranslateErrorCode a = g.a(i);
        if (a != TranslateErrorCode.UN_SPECIFIC_ERROR) {
            return a;
        }
        if (i == 5001) {
            return TranslateErrorCode.TRANS_UNSUPPORT_OCRTYPE;
        }
        if (i == 5002) {
            return TranslateErrorCode.TRANS_UNSUPPORT_OCR_IMG_TYPE;
        }
        if (i == 5003) {
            return TranslateErrorCode.TRANS_UNSUPPORT_OCR_LANG_TYPE;
        }
        if (i == 5004) {
            return TranslateErrorCode.TRANS_QUERY_IMAGE_TOO_LARGE;
        }
        if (i == 5005) {
            return TranslateErrorCode.TRANS_UNSUPPORT_OCR_IMG_FILE;
        }
        if (i == 5006) {
            return TranslateErrorCode.TRANS_EMPTY_IMG_FILE;
        }
        if (i == 5201) {
            return TranslateErrorCode.TRANS_IMG_DECRYPT_ERROR;
        }
        if (i == 5301) {
            return TranslateErrorCode.TRANS_OCR_PARA_QUERY_FAILED;
        }
        if (i == 5411) {
            return TranslateErrorCode.TRANS_OCR_MAX_QUERY_COUNT_ERROR;
        }
        if (i == 5412) {
            return TranslateErrorCode.TRANS_OCR_MAX_QUERY_SIZE_ERROR;
        }
        return TranslateErrorCode.UN_SPECIFIC_ERROR;
    }
}
