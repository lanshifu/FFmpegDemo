package defpackage;

import android.content.Context;
import com.youdao.sdk.app.other.h;
import com.youdao.sdk.app.other.l;
import com.youdao.sdk.app.other.n;
import java.util.Map;

/* renamed from: sx */
public class sx extends l {
    public sx(Context context) {
        super(context);
    }

    public sx p(String str) {
        this.c = str;
        return this;
    }

    public Map<String, String> c() {
        e(this.c);
        g(this.d);
        h a = h.a(this.b);
        f(a.o());
        a(a.p());
        a(a.l(), a.m(), a.n());
        c(a.i());
        d(a.j());
        h(n.c());
        i(a.a());
        a(a.e());
        String f = a.f();
        j(f);
        k(f);
        n(a.c());
        l(a.g());
        m(a.h());
        a(a.b());
        a(a.d());
        b(a.q());
        o(a.r());
        d();
        a();
        q(a.k());
        return this.a;
    }

    /* Access modifiers changed, original: protected */
    public void d() {
        a("osType", "Android");
    }

    /* Access modifiers changed, original: protected */
    public void q(String str) {
        a("screen", str);
    }

    /* Access modifiers changed, original: protected */
    public void f(String str) {
        a("sdkversion", str);
    }

    /* Access modifiers changed, original: protected */
    public void a(String str) {
        a("version", str);
    }
}
