package defpackage;

import com.one.tomato.mvp.base.okhttp.cookie.store.a;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/* compiled from: CookieJarImpl */
/* renamed from: nz */
public class nz implements CookieJar {
    private a a;

    public nz(a aVar) {
        if (aVar != null) {
            this.a = aVar;
            return;
        }
        throw new IllegalArgumentException("cookieStore can not be null!");
    }

    public synchronized void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        this.a.a(httpUrl, list);
    }

    public synchronized List<Cookie> loadForRequest(HttpUrl httpUrl) {
        return this.a.a(httpUrl);
    }
}
