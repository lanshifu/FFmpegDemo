package com.tomatolive.library.download;

import android.support.annotation.NonNull;
import com.tomatolive.library.utils.b;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

public class DownBaseUrlManagerInterceptor implements Interceptor {
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url();
        Builder newBuilder = request.newBuilder();
        HttpUrl parse = HttpUrl.parse(b.b());
        if (parse == null) {
            return chain.proceed(chain.request());
        }
        return chain.proceed(newBuilder.url(url.newBuilder().scheme(parse.scheme()).host(parse.host()).port(parse.port()).build()).build());
    }
}
