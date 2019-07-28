package com.tomatolive.library.http.interceptor;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.tomatolive.library.utils.b;
import java.io.IOException;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

public class BaseUrlManagerInterceptor implements Interceptor {
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url();
        Builder newBuilder = request.newBuilder();
        HttpUrl parse = HttpUrl.parse(b.a());
        if (parse == null) {
            return chain.proceed(chain.request());
        }
        List headers = request.headers("urlName");
        if (headers != null && headers.size() > 0) {
            newBuilder.removeHeader("urlName");
            if (TextUtils.equals("upload", (String) headers.get(0))) {
                parse = HttpUrl.parse(b.c());
            }
        }
        if (parse == null) {
            return chain.proceed(chain.request());
        }
        return chain.proceed(newBuilder.url(url.newBuilder().scheme(parse.scheme()).host(parse.host()).port(parse.port()).build()).build());
    }
}
