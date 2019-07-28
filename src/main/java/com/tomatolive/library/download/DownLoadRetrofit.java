package com.tomatolive.library.download;

import com.tomatolive.library.http.ApiService;
import com.tomatolive.library.http.interceptor.AddHeaderInterceptor;
import com.tomatolive.library.http.utils.HttpsUtils;
import com.tomatolive.library.http.utils.HttpsUtils.SSLParams;
import com.tomatolive.library.utils.b;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit.Builder;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DownLoadRetrofit {
    private ApiService mApiService;

    private static class SingletonHolder {
        private static final DownLoadRetrofit INSTANCE = new DownLoadRetrofit();

        private SingletonHolder() {
        }
    }

    private DownLoadRetrofit() {
        this.mApiService = null;
        try {
            SSLParams sslSocketFactory = HttpsUtils.getSslSocketFactory(null, null, null);
            this.mApiService = (ApiService) new Builder().baseUrl(b.b()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(new DownBaseUrlManagerInterceptor()).addInterceptor(new AddHeaderInterceptor()).connectTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager).hostnameVerifier(-$$Lambda$DownLoadRetrofit$zxOJ7ZXEdWEJptYCXR9uSPYv7Rw.INSTANCE).build()).build().create(ApiService.class);
        } catch (Exception unused) {
        }
    }

    public static DownLoadRetrofit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public ApiService getApiService() {
        return this.mApiService;
    }

    public boolean isApiService() {
        return this.mApiService != null;
    }
}
