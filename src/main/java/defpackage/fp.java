package defpackage;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: RetryIntercepter */
/* renamed from: fp */
public class fp implements Interceptor {
    public int a;
    private int b = 0;

    public fp(int i) {
        this.a = i;
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response proceed = chain.proceed(request);
        while (!proceed.isSuccessful() && this.b < this.a) {
            this.b++;
            b a = aat.a("TTT");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("retryNum=");
            stringBuilder.append(this.b);
            a.a(stringBuilder.toString(), new Object[0]);
            proceed = chain.proceed(request);
        }
        return proceed;
    }

    public void a(int i) {
        this.b = i;
    }
}
