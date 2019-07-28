package com.tomatolive.library.http;

import com.tomatolive.library.http.interceptor.AddHeaderInterceptor;
import com.tomatolive.library.http.interceptor.BaseUrlManagerInterceptor;
import com.tomatolive.library.http.interceptor.SignRequestInterceptor;
import com.tomatolive.library.http.utils.CustomGsonConverterFactory;
import com.tomatolive.library.http.utils.HttpsUtils;
import com.tomatolive.library.http.utils.HttpsUtils.SSLParams;
import com.tomatolive.library.utils.b;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit.Builder;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class ApiRetrofit {
    private ApiService mApiService;

    private static class SingletonHolder {
        private static final ApiRetrofit INSTANCE = new ApiRetrofit();

        private SingletonHolder() {
        }
    }

    private ApiRetrofit() {
        this.mApiService = null;
        try {
            SSLParams sslSocketFactory = HttpsUtils.getSslSocketFactory(null, null, null);
            this.mApiService = (ApiService) new Builder().baseUrl(b.a()).addConverterFactory(CustomGsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(new BaseUrlManagerInterceptor()).addInterceptor(new AddHeaderInterceptor()).addInterceptor(new SignRequestInterceptor()).connectTimeout(6, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager).hostnameVerifier(-$$Lambda$ApiRetrofit$iu-5P2JclTluLIXZx0dZfIDluCk.INSTANCE).build()).build().create(ApiService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ApiRetrofit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public ApiService getApiService() {
        return this.mApiService;
    }

    public boolean isApiService() {
        return this.mApiService != null;
    }
}
