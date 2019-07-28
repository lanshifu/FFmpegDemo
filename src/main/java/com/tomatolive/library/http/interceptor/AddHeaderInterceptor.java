package com.tomatolive.library.http.interceptor;

import android.support.annotation.NonNull;
import com.blankj.utilcode.util.c;
import com.tomatolive.library.a;
import com.tomatolive.library.utils.u;
import com.tomatolive.library.utils.z;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Response;

public class AddHeaderInterceptor implements Interceptor {
    public static final String DEVICE_ID = "deviceId";
    public static final String RANDOM_STR = "randomStr";
    public static final String TIME_STAMP_STR = "timeStampStr";

    public Response intercept(@NonNull Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().header("deviceType", "android").header("appId", z.a().e()).header("appKey", a.a().e).header("sdkVersion", "1.6.7").header("osVersion", c.a()).header("deviceName", c.d()).header(TIME_STAMP_STR, String.valueOf(System.currentTimeMillis() / 1000)).header(RANDOM_STR, u.a(16)).header(DEVICE_ID, c.b()).header("token", z.a().b()).header("openId", z.a().d()).header("userId", z.a().c()).build());
    }
}
