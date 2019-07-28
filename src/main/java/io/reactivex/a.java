package io.reactivex;

import defpackage.xe;
import defpackage.xf;
import defpackage.xg;
import defpackage.xh;
import defpackage.xk;

/* compiled from: Completable */
public abstract class a implements c {
    public abstract void b(b bVar);

    public static a a(c... cVarArr) {
        io.reactivex.internal.functions.a.a((Object) cVarArr, "sources is null");
        if (cVarArr.length == 0) {
            return a();
        }
        if (cVarArr.length == 1) {
            return a(cVarArr[0]);
        }
        return xk.a(new xe(cVarArr, null));
    }

    public static a a() {
        return xk.a(xf.a);
    }

    public static a a(Throwable th) {
        io.reactivex.internal.functions.a.a((Object) th, "error is null");
        return xk.a(new xg(th));
    }

    private static NullPointerException b(Throwable th) {
        NullPointerException nullPointerException = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
        nullPointerException.initCause(th);
        return nullPointerException;
    }

    public static a a(c cVar) {
        io.reactivex.internal.functions.a.a((Object) cVar, "source is null");
        if (cVar instanceof a) {
            return xk.a((a) cVar);
        }
        return xk.a(new xh(cVar));
    }

    public final void a(b bVar) {
        NullPointerException e;
        io.reactivex.internal.functions.a.a((Object) bVar, "s is null");
        try {
            bVar = xk.a(this, bVar);
            io.reactivex.internal.functions.a.a((Object) bVar, "The RxJavaPlugins.onSubscribe hook returned a null CompletableObserver. Please check the handler provided to RxJavaPlugins.setOnCompletableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            b(bVar);
        } catch (NullPointerException e2) {
            throw e2;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            xk.a(th);
            e2 = b(th);
        }
    }
}
