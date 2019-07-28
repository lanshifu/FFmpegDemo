package com.youdao.sdk.app;

import defpackage.sy;
import java.util.Map;

public class c {

    public interface a {
        void onError(HttpErrorCode httpErrorCode);

        void onResult(String str);
    }

    public static void a(String str, Map<String, String> map, a aVar, int i) {
        try {
            new Thread(new com.youdao.sdk.app.other.c(map, str, i, aVar)).start();
        } catch (Exception e) {
            sy.c("Unspecified error occured in request", e);
            aVar.onError(HttpErrorCode.UNSPECIFICERROR);
        }
    }
}
