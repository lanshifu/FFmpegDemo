package defpackage;

import android.net.http.AndroidHttpClient;
import com.youdao.sdk.app.other.o;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/* renamed from: sv */
public class sv {
    public static AndroidHttpClient a(int i) {
        AndroidHttpClient newInstance = AndroidHttpClient.newInstance(o.a());
        HttpParams params = newInstance.getParams();
        HttpConnectionParams.setConnectionTimeout(params, i);
        HttpConnectionParams.setSoTimeout(params, i);
        HttpClientParams.setRedirecting(params, true);
        return newInstance;
    }
}
