package kotlin;

import defpackage.xr;
import kotlin.jvm.internal.f;

/* compiled from: Exceptions.kt */
class b {
    public static final void a(Throwable th, Throwable th2) {
        f.b(th, "$this$addSuppressed");
        f.b(th2, "exception");
        xr.a.a(th, th2);
    }
}
