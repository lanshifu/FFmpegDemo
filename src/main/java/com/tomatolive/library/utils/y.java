package com.tomatolive.library.utils;

import android.text.TextUtils;
import com.youdao.sdk.app.Language;
import com.youdao.sdk.app.e;
import com.youdao.sdk.ydonlinetranslate.Translator;
import com.youdao.sdk.ydtranslate.Translate;
import com.youdao.sdk.ydtranslate.TranslateErrorCode;
import com.youdao.sdk.ydtranslate.TranslateListener;
import com.youdao.sdk.ydtranslate.TranslateParameters.Builder;
import defpackage.st;
import java.util.List;

/* compiled from: TranslationUtils */
public class y {

    /* compiled from: TranslationUtils */
    public interface a {
        void onSuc(String str);
    }

    private static String b(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        if (list != null) {
            for (String append : list) {
                stringBuilder.append(append);
            }
        }
        return stringBuilder.toString();
    }

    public static void a(final String str, final a aVar) {
        Language a = e.a(Language.AUTO.getName());
        Translator.getInstance(new Builder().source("youdao").from(a).to(e.a(a())).sound(st.g).voice(st.k).timeout(3000).build()).lookup(str, "requestId", new TranslateListener() {
            private String c;
            private String d;

            public void onResult(List<Translate> list, List<String> list2, List<TranslateErrorCode> list3, String str) {
            }

            public void onResult(Translate translate, String str, String str2) {
                if (aVar != null) {
                    try {
                        this.d = y.b(translate.getTranslations());
                        this.c = y.b(translate.getExplains());
                        if (!TextUtils.isEmpty(this.d) || !TextUtils.isEmpty(this.c)) {
                            aVar.onSuc(TextUtils.isEmpty(this.d) ? this.c : this.d);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            public void onError(TranslateErrorCode translateErrorCode, String str) {
                if (aVar != null) {
                    aVar.onSuc(str);
                }
            }
        });
    }

    /* JADX WARNING: Missing block: B:13:0x005b, code skipped:
            if (r1.equals("zh-CN") != false) goto L_0x00b2;
     */
    private static java.lang.String a() {
        /*
        r0 = com.youdao.sdk.app.Language.CHINESE;
        r0 = r0.getName();
        r1 = android.os.Build.VERSION.SDK_INT;
        r2 = 0;
        r3 = 24;
        if (r1 < r3) goto L_0x0016;
    L_0x000d:
        r1 = android.os.LocaleList.getDefault();
        r1 = r1.get(r2);
        goto L_0x001a;
    L_0x0016:
        r1 = java.util.Locale.getDefault();
    L_0x001a:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = r1.getLanguage();
        r3.append(r4);
        r4 = "-";
        r3.append(r4);
        r1 = r1.getCountry();
        r3.append(r1);
        r1 = r3.toString();
        r3 = -1;
        r4 = r1.hashCode();
        switch(r4) {
            case 96598594: goto L_0x00a7;
            case 96747053: goto L_0x009c;
            case 97640813: goto L_0x0092;
            case 100828572: goto L_0x0088;
            case 102169200: goto L_0x007e;
            case 106935917: goto L_0x0073;
            case 108812813: goto L_0x0069;
            case 112149522: goto L_0x005e;
            case 115813226: goto L_0x0055;
            case 115813378: goto L_0x004b;
            case 115813762: goto L_0x0040;
            default: goto L_0x003e;
        };
    L_0x003e:
        goto L_0x00b1;
    L_0x0040:
        r2 = "zh-TW";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x00b1;
    L_0x0048:
        r2 = 2;
        goto L_0x00b2;
    L_0x004b:
        r2 = "zh-HK";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x00b1;
    L_0x0053:
        r2 = 1;
        goto L_0x00b2;
    L_0x0055:
        r4 = "zh-CN";
        r1 = r1.equals(r4);
        if (r1 == 0) goto L_0x00b1;
    L_0x005d:
        goto L_0x00b2;
    L_0x005e:
        r2 = "vi-VN";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x00b1;
    L_0x0066:
        r2 = 10;
        goto L_0x00b2;
    L_0x0069:
        r2 = "ru-RU";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x00b1;
    L_0x0071:
        r2 = 7;
        goto L_0x00b2;
    L_0x0073:
        r2 = "pt-PT";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x00b1;
    L_0x007b:
        r2 = 8;
        goto L_0x00b2;
    L_0x007e:
        r2 = "ko-KR";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x00b1;
    L_0x0086:
        r2 = 5;
        goto L_0x00b2;
    L_0x0088:
        r2 = "ja-JP";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x00b1;
    L_0x0090:
        r2 = 3;
        goto L_0x00b2;
    L_0x0092:
        r2 = "fr-FR";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x00b1;
    L_0x009a:
        r2 = 6;
        goto L_0x00b2;
    L_0x009c:
        r2 = "es-ES";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x00b1;
    L_0x00a4:
        r2 = 9;
        goto L_0x00b2;
    L_0x00a7:
        r2 = "en-US";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x00b1;
    L_0x00af:
        r2 = 4;
        goto L_0x00b2;
    L_0x00b1:
        r2 = -1;
    L_0x00b2:
        switch(r2) {
            case 0: goto L_0x00f5;
            case 1: goto L_0x00ee;
            case 2: goto L_0x00ee;
            case 3: goto L_0x00e7;
            case 4: goto L_0x00e0;
            case 5: goto L_0x00d9;
            case 6: goto L_0x00d2;
            case 7: goto L_0x00cb;
            case 8: goto L_0x00c4;
            case 9: goto L_0x00bd;
            case 10: goto L_0x00b6;
            default: goto L_0x00b5;
        };
    L_0x00b5:
        goto L_0x00fb;
    L_0x00b6:
        r0 = com.youdao.sdk.app.Language.Vietnamese;
        r0 = r0.getName();
        goto L_0x00fb;
    L_0x00bd:
        r0 = com.youdao.sdk.app.Language.SPANISH;
        r0 = r0.getName();
        goto L_0x00fb;
    L_0x00c4:
        r0 = com.youdao.sdk.app.Language.PORTUGUESE;
        r0 = r0.getName();
        goto L_0x00fb;
    L_0x00cb:
        r0 = com.youdao.sdk.app.Language.RUSSIAN;
        r0 = r0.getName();
        goto L_0x00fb;
    L_0x00d2:
        r0 = com.youdao.sdk.app.Language.FRENCH;
        r0 = r0.getName();
        goto L_0x00fb;
    L_0x00d9:
        r0 = com.youdao.sdk.app.Language.KOREAN;
        r0 = r0.getName();
        goto L_0x00fb;
    L_0x00e0:
        r0 = com.youdao.sdk.app.Language.ENGLISH;
        r0 = r0.getName();
        goto L_0x00fb;
    L_0x00e7:
        r0 = com.youdao.sdk.app.Language.JAPANESE;
        r0 = r0.getName();
        goto L_0x00fb;
    L_0x00ee:
        r0 = com.youdao.sdk.app.Language.TraditionalChinese;
        r0 = r0.getName();
        goto L_0x00fb;
    L_0x00f5:
        r0 = com.youdao.sdk.app.Language.CHINESE;
        r0 = r0.getName();
    L_0x00fb:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.y.a():java.lang.String");
    }
}
