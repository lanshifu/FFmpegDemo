package io.fabric.sdk.android.services.common;

import io.fabric.sdk.android.h;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.c;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

/* compiled from: AbstractSpiCall */
public abstract class a {
    private static final Pattern b = Pattern.compile("http(s?)://[^\\/]+", 2);
    protected final h a;
    private final String c;
    private final c d;
    private final HttpMethod e;
    private final String f;

    public a(h hVar, String str, String str2, c cVar, HttpMethod httpMethod) {
        if (str2 == null) {
            throw new IllegalArgumentException("url must not be null.");
        } else if (cVar != null) {
            this.a = hVar;
            this.f = str;
            this.c = a(str2);
            this.d = cVar;
            this.e = httpMethod;
        } else {
            throw new IllegalArgumentException("requestFactory must not be null.");
        }
    }

    /* Access modifiers changed, original: protected */
    public String a() {
        return this.c;
    }

    /* Access modifiers changed, original: protected */
    public HttpRequest b() {
        return a(Collections.emptyMap());
    }

    /* Access modifiers changed, original: protected */
    public HttpRequest a(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Crashlytics Android SDK/");
        stringBuilder.append(this.a.a());
        return this.d.a(this.e, a(), map).a(false).a(10000).a("User-Agent", stringBuilder.toString()).a("X-CRASHLYTICS-DEVELOPER-TOKEN", "470fa2b4ae81cd56ecbcda9735803434cec591fa");
    }

    private String a(String str) {
        return !CommonUtils.d(this.f) ? b.matcher(str).replaceFirst(this.f) : str;
    }
}
