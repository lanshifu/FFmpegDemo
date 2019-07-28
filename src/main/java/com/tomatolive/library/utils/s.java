package com.tomatolive.library.utils;

import android.text.TextUtils;
import com.tomatolive.library.a;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.interceptor.AddHeaderInterceptor;
import java.util.Map.Entry;
import java.util.TreeMap;
import okhttp3.Request;

/* compiled from: SignRequestUtils */
public class s {
    public static String a(Request request, TreeMap<String, Object> treeMap, String str) {
        TreeMap treeMap2 = new TreeMap(-$$Lambda$TEfSBt3hRUlBSSARfPEHsJesTtE.INSTANCE);
        treeMap2.putAll(treeMap);
        treeMap2.put(AddHeaderInterceptor.TIME_STAMP_STR, request.header(AddHeaderInterceptor.TIME_STAMP_STR));
        treeMap2.put(AddHeaderInterceptor.RANDOM_STR, request.header(AddHeaderInterceptor.RANDOM_STR));
        treeMap2.put(AddHeaderInterceptor.DEVICE_ID, request.header(AddHeaderInterceptor.DEVICE_ID));
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : treeMap2.entrySet()) {
            String str2 = (String) entry.getKey();
            Object value = entry.getValue();
            if (!(TextUtils.equals(RequestParams.PAGE_SIZE, str2) || TextUtils.equals(RequestParams.PAGE_NUMBER, str2))) {
                if (value != null) {
                    stringBuilder.append(str2);
                    stringBuilder.append("=");
                    stringBuilder.append(value);
                    stringBuilder.append("&");
                }
            }
        }
        if (stringBuilder.indexOf("&") != -1) {
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("&"));
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(a.a().g);
        stringBuilder2.append("_");
        stringBuilder2.append(stringBuilder.toString().toUpperCase());
        return n.b(stringBuilder2.toString());
    }

    public static String a() {
        return u.a(16);
    }

    public static String b() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
}
