package defpackage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: OkHttpManager */
/* renamed from: fm */
public class fm {
    private Builder a;
    private fp b;

    /* compiled from: OkHttpManager */
    /* renamed from: fm$a */
    private static class a {
        private static final fm a = new fm();
    }

    private fm() {
        this.b = new fp(3);
        this.a = new Builder().connectTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).addInterceptor(this.b).addInterceptor(new fo()).retryOnConnectionFailure(true);
    }

    public static fm a() {
        return a.a;
    }

    public Call a(String str, long j) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("bytes=");
        stringBuilder.append(j);
        stringBuilder.append("-");
        Request build = new Request.Builder().url(str).header("Range", stringBuilder.toString()).build();
        this.b.a(0);
        return this.a.build().newCall(build);
    }

    public Response a(String str) throws IOException {
        Request build = new Request.Builder().url(str).build();
        this.b.a(0);
        return this.a.build().newCall(build).execute();
    }
}
