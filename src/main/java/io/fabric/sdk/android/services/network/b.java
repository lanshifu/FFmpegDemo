package io.fabric.sdk.android.services.network;

import io.fabric.sdk.android.k;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* compiled from: DefaultHttpRequestFactory */
public class b implements c {
    private final k a;
    private e b;
    private SSLSocketFactory c;
    private boolean d;

    public b() {
        this(new io.fabric.sdk.android.b());
    }

    public b(k kVar) {
        this.a = kVar;
    }

    public void a(e eVar) {
        if (this.b != eVar) {
            this.b = eVar;
            a();
        }
    }

    private synchronized void a() {
        this.d = false;
        this.c = null;
    }

    public HttpRequest a(HttpMethod httpMethod, String str, Map<String, String> map) {
        HttpRequest a;
        switch (httpMethod) {
            case GET:
                a = HttpRequest.a((CharSequence) str, (Map) map, true);
                break;
            case POST:
                a = HttpRequest.b((CharSequence) str, (Map) map, true);
                break;
            case PUT:
                a = HttpRequest.d((CharSequence) str);
                break;
            case DELETE:
                a = HttpRequest.e((CharSequence) str);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method!");
        }
        if (a(str) && this.b != null) {
            SSLSocketFactory b = b();
            if (b != null) {
                ((HttpsURLConnection) a.a()).setSSLSocketFactory(b);
            }
        }
        return a;
    }

    private boolean a(String str) {
        return str != null && str.toLowerCase(Locale.US).startsWith("https");
    }

    private synchronized SSLSocketFactory b() {
        if (this.c == null && !this.d) {
            this.c = c();
        }
        return this.c;
    }

    private synchronized SSLSocketFactory c() {
        SSLSocketFactory a;
        this.d = true;
        try {
            a = d.a(this.b);
            this.a.a("Fabric", "Custom SSL pinning enabled");
        } catch (Exception e) {
            this.a.e("Fabric", "Exception while validating pinned certs", e);
            return null;
        }
        return a;
    }
}
