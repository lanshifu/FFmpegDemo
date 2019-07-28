package com.tomatolive.library.http.interceptor;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.tomatolive.library.utils.s;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.c;

public class SignRequestInterceptor implements Interceptor {
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        String method = request.method();
        if (TextUtils.equals(method, "GET") || TextUtils.equals(method, "PUT")) {
            HttpUrl url = request.url();
            Set<String> queryParameterNames = url.queryParameterNames();
            TreeMap treeMap = new TreeMap();
            for (String str : queryParameterNames) {
                treeMap.put(str, url.queryParameter(str));
            }
            return chain.proceed(request.newBuilder().url(url.newBuilder().addQueryParameter("sign", s.a(request, treeMap, "utf-8")).build()).build());
        } else if (!TextUtils.equals(method, "POST")) {
            return chain.proceed(request);
        } else {
            RequestBody body = request.body();
            if (body instanceof MultipartBody) {
                return chain.proceed(request);
            }
            Gson gson = new Gson();
            c cVar = new c();
            body.getClass();
            body.writeTo(cVar);
            return chain.proceed(request.newBuilder().header("sign", s.a(request, (TreeMap) gson.fromJson(cVar.s(), TreeMap.class), "utf-8")).build());
        }
    }
}
