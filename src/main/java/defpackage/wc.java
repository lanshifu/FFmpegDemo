package defpackage;

import io.reactivex.exceptions.a;
import io.reactivex.s;
import java.util.concurrent.Callable;

/* compiled from: RxAndroidPlugins */
/* renamed from: wc */
public final class wc {
    private static volatile wm<Callable<s>, s> a;
    private static volatile wm<s, s> b;

    public static s a(Callable<s> callable) {
        if (callable != null) {
            wm wmVar = a;
            if (wmVar == null) {
                return wc.b(callable);
            }
            return wc.a(wmVar, (Callable) callable);
        }
        throw new NullPointerException("scheduler == null");
    }

    public static s a(s sVar) {
        if (sVar != null) {
            wm wmVar = b;
            if (wmVar == null) {
                return sVar;
            }
            return (s) wc.a(wmVar, (Object) sVar);
        }
        throw new NullPointerException("scheduler == null");
    }

    static s b(Callable<s> callable) {
        try {
            s sVar = (s) callable.call();
            if (sVar != null) {
                return sVar;
            }
            throw new NullPointerException("Scheduler Callable returned null");
        } catch (Throwable th) {
            RuntimeException a = a.a(th);
        }
    }

    static s a(wm<Callable<s>, s> wmVar, Callable<s> callable) {
        s sVar = (s) wc.a((wm) wmVar, (Object) callable);
        if (sVar != null) {
            return sVar;
        }
        throw new NullPointerException("Scheduler Callable returned null");
    }

    static <T, R> R a(wm<T, R> wmVar, T t) {
        try {
            return wmVar.apply(t);
        } catch (Throwable th) {
            RuntimeException a = a.a(th);
        }
    }
}
