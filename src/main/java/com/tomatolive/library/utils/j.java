package com.tomatolive.library.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/* compiled from: GsonUtils */
public class j {
    private static final Gson a = a(true);
    private static final Gson b = a(false);

    public static String a(Object obj) {
        return a(obj, true);
    }

    public static String a(Object obj, boolean z) {
        return (z ? a : b).toJson(obj);
    }

    public static <T> T a(String str, Class<T> cls) {
        return a.fromJson(str, cls);
    }

    private static Gson a(boolean z) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.disableHtmlEscaping();
        if (z) {
            gsonBuilder.serializeNulls();
        }
        return gsonBuilder.create();
    }
}
