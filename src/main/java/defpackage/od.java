package defpackage;

import android.text.TextUtils;
import com.one.tomato.utils.b;
import java.io.IOException;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

/* compiled from: ServerBaseUrlInterceptor */
/* renamed from: od */
public class od implements Interceptor {
    private boolean a = true;

    public od(boolean z) {
        this.a = z;
    }

    public Response intercept(Chain chain) throws IOException {
        if (!this.a) {
            return chain.proceed(chain.request());
        }
        Request request = chain.request();
        Builder newBuilder = request.newBuilder();
        HttpUrl url = request.url();
        List pathSegments = url.pathSegments();
        StringBuilder stringBuilder = new StringBuilder();
        int size = pathSegments.size();
        for (int i = 0; i < size; i++) {
            stringBuilder.append('/');
            stringBuilder.append((String) pathSegments.get(i));
        }
        HttpUrl.Builder newBuilder2 = url.newBuilder();
        int size2 = pathSegments.size();
        for (size = 0; size < size2; size++) {
            newBuilder2.removePathSegment(0);
        }
        String b = pd.a().b();
        String header = request.header("serverType");
        if (!TextUtils.isEmpty(header)) {
            Object obj = -1;
            if (header.hashCode() == -934521548 && header.equals("report")) {
                obj = null;
            }
            if (obj == null) {
                b = pd.a().i();
            }
        }
        HttpUrl parse = HttpUrl.parse(b);
        return chain.proceed(newBuilder.url(newBuilder2.scheme(parse.scheme()).host(parse.host()).port(parse.port()).setPathSegment(0, b.c(stringBuilder.toString())).build()).build());
    }
}
