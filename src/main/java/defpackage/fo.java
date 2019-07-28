package defpackage;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: RepResultIntercepter */
/* renamed from: fo */
public class fo implements Interceptor {
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response proceed = chain.proceed(request);
        String httpUrl = request.url().toString();
        int code = proceed.code();
        boolean isSuccessful = proceed.isSuccessful();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(httpUrl);
        stringBuffer.append(",");
        stringBuffer.append(isSuccessful);
        stringBuffer.append(",");
        stringBuffer.append(code);
        fk.a("RepR", stringBuffer.toString());
        return proceed;
    }
}
