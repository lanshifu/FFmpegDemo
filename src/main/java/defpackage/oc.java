package defpackage;

import android.text.TextUtils;
import com.one.tomato.mvp.base.okhttp.b;
import com.one.tomato.utils.g;
import com.one.tomato.utils.o;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: ResponseHeaderInterceptor */
/* renamed from: oc */
public class oc implements Interceptor {
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response proceed = chain.proceed(request);
        String header = proceed.header("code");
        StringBuilder stringBuilder;
        if ("301".equals(header)) {
            if (TextUtils.isEmpty(g.c())) {
                return proceed.newBuilder().build();
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("报301的请求地址：");
            stringBuilder.append(request.url().toString());
            o.b("TomatoCallback", stringBuilder.toString());
            b.a(false);
        } else if ("302".equals(header)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("报302的请求地址：");
            stringBuilder.append(request.url().toString());
            o.b("TomatoCallback", stringBuilder.toString());
            b.a(true);
        }
        oz.a().a(proceed.header("domainVersion"));
        return proceed.newBuilder().build();
    }
}
