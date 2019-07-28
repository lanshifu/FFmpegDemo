package defpackage;

import com.one.tomato.mvp.base.okhttp.download.d;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Response;

/* compiled from: ProgressInterceptor */
/* renamed from: oa */
public class oa implements Interceptor {
    public Response intercept(Chain chain) throws IOException {
        Response proceed = chain.proceed(chain.request());
        return proceed.newBuilder().body(new d(proceed.body())).build();
    }
}
