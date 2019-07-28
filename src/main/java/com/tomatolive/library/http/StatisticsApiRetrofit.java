package com.tomatolive.library.http;

import com.tomatolive.library.http.interceptor.BaseUrlManagerInterceptor;
import com.tomatolive.library.http.utils.HttpsUtils;
import com.tomatolive.library.http.utils.HttpsUtils.SSLParams;
import com.tomatolive.library.utils.b;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit.Builder;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatisticsApiRetrofit {
    private ApiService mApiService;

    private static class SingletonHolder {
        private static final StatisticsApiRetrofit INSTANCE = new StatisticsApiRetrofit();

        private SingletonHolder() {
        }
    }

    private StatisticsApiRetrofit() {
        SSLParams sslSocketFactory = HttpsUtils.getSslSocketFactory(null, null, null);
        this.mApiService = (ApiService) new Builder().baseUrl(b.a()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(new BaseUrlManagerInterceptor()).connectTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager).hostnameVerifier(-$$Lambda$StatisticsApiRetrofit$t5GqMCVCsVzyqcEvlSFC0oBv6xc.INSTANCE).build()).build().create(ApiService.class);
    }

    public static StatisticsApiRetrofit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public ApiService getApiService() {
        return this.mApiService;
    }
}
