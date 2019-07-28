package defpackage;

import io.reactivex.a;
import io.reactivex.b;
import io.reactivex.e;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.g;
import io.reactivex.h;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.k;
import io.reactivex.r;
import io.reactivex.s;
import io.reactivex.t;
import io.reactivex.u;
import java.util.concurrent.Callable;

/* compiled from: RxJavaPlugins */
/* renamed from: xk */
public final class xk {
    static volatile wl<? super Throwable> a;
    static volatile wm<? super Runnable, ? extends Runnable> b;
    static volatile wm<? super Callable<s>, ? extends s> c;
    static volatile wm<? super Callable<s>, ? extends s> d;
    static volatile wm<? super Callable<s>, ? extends s> e;
    static volatile wm<? super Callable<s>, ? extends s> f;
    static volatile wm<? super s, ? extends s> g;
    static volatile wm<? super s, ? extends s> h;
    static volatile wm<? super s, ? extends s> i;
    static volatile wm<? super e, ? extends e> j;
    static volatile wm<? super k, ? extends k> k;
    static volatile wm<? super xi, ? extends xi> l;
    static volatile wm<? super g, ? extends g> m;
    static volatile wm<? super t, ? extends t> n;
    static volatile wm<? super a, ? extends a> o;
    static volatile wh<? super e, ? super aar, ? extends aar> p;
    static volatile wh<? super g, ? super h, ? extends h> q;
    static volatile wh<? super k, ? super r, ? extends r> r;
    static volatile wh<? super t, ? super u, ? extends u> s;
    static volatile wh<? super a, ? super b, ? extends b> t;
    static volatile wj u;
    static volatile boolean v;
    static volatile boolean w;

    public static boolean a() {
        return w;
    }

    public static s a(Callable<s> callable) {
        io.reactivex.internal.functions.a.a((Object) callable, "Scheduler Callable can't be null");
        wm wmVar = c;
        if (wmVar == null) {
            return xk.e(callable);
        }
        return xk.a(wmVar, (Callable) callable);
    }

    public static s b(Callable<s> callable) {
        io.reactivex.internal.functions.a.a((Object) callable, "Scheduler Callable can't be null");
        wm wmVar = e;
        if (wmVar == null) {
            return xk.e(callable);
        }
        return xk.a(wmVar, (Callable) callable);
    }

    public static s c(Callable<s> callable) {
        io.reactivex.internal.functions.a.a((Object) callable, "Scheduler Callable can't be null");
        wm wmVar = f;
        if (wmVar == null) {
            return xk.e(callable);
        }
        return xk.a(wmVar, (Callable) callable);
    }

    public static s d(Callable<s> callable) {
        io.reactivex.internal.functions.a.a((Object) callable, "Scheduler Callable can't be null");
        wm wmVar = d;
        if (wmVar == null) {
            return xk.e(callable);
        }
        return xk.a(wmVar, (Callable) callable);
    }

    public static s a(s sVar) {
        wm wmVar = g;
        if (wmVar == null) {
            return sVar;
        }
        return (s) xk.a(wmVar, (Object) sVar);
    }

    public static void a(Throwable th) {
        wl wlVar = a;
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        } else if (!xk.b(th)) {
            th = new UndeliverableException(th);
        }
        if (wlVar != null) {
            try {
                wlVar.accept(th);
                return;
            } catch (Throwable th2) {
                th2.printStackTrace();
                xk.c(th2);
            }
        }
        th.printStackTrace();
        xk.c(th);
    }

    static boolean b(Throwable th) {
        if ((th instanceof OnErrorNotImplementedException) || (th instanceof MissingBackpressureException) || (th instanceof IllegalStateException) || (th instanceof NullPointerException) || (th instanceof IllegalArgumentException) || (th instanceof CompositeException)) {
            return true;
        }
        return false;
    }

    static void c(Throwable th) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }

    public static s b(s sVar) {
        wm wmVar = h;
        if (wmVar == null) {
            return sVar;
        }
        return (s) xk.a(wmVar, (Object) sVar);
    }

    public static s c(s sVar) {
        wm wmVar = i;
        if (wmVar == null) {
            return sVar;
        }
        return (s) xk.a(wmVar, (Object) sVar);
    }

    public static Runnable a(Runnable runnable) {
        io.reactivex.internal.functions.a.a((Object) runnable, "run is null");
        wm wmVar = b;
        if (wmVar == null) {
            return runnable;
        }
        return (Runnable) xk.a(wmVar, (Object) runnable);
    }

    public static void a(wl<? super Throwable> wlVar) {
        if (v) {
            throw new IllegalStateException("Plugins can't be changed anymore");
        }
        a = wlVar;
    }

    public static <T> aar<? super T> a(e<T> eVar, aar<? super T> aar) {
        wh whVar = p;
        return whVar != null ? (aar) xk.a(whVar, eVar, aar) : aar;
    }

    public static <T> r<? super T> a(k<T> kVar, r<? super T> rVar) {
        wh whVar = r;
        return whVar != null ? (r) xk.a(whVar, kVar, rVar) : rVar;
    }

    public static <T> u<? super T> a(t<T> tVar, u<? super T> uVar) {
        wh whVar = s;
        return whVar != null ? (u) xk.a(whVar, tVar, uVar) : uVar;
    }

    public static b a(a aVar, b bVar) {
        wh whVar = t;
        return whVar != null ? (b) xk.a(whVar, aVar, bVar) : bVar;
    }

    public static <T> h<? super T> a(g<T> gVar, h<? super T> hVar) {
        wh whVar = q;
        return whVar != null ? (h) xk.a(whVar, gVar, hVar) : hVar;
    }

    public static <T> g<T> a(g<T> gVar) {
        wm wmVar = m;
        return wmVar != null ? (g) xk.a(wmVar, (Object) gVar) : gVar;
    }

    public static <T> e<T> a(e<T> eVar) {
        wm wmVar = j;
        return wmVar != null ? (e) xk.a(wmVar, (Object) eVar) : eVar;
    }

    public static <T> k<T> a(k<T> kVar) {
        wm wmVar = k;
        return wmVar != null ? (k) xk.a(wmVar, (Object) kVar) : kVar;
    }

    public static <T> xi<T> a(xi<T> xiVar) {
        wm wmVar = l;
        return wmVar != null ? (xi) xk.a(wmVar, (Object) xiVar) : xiVar;
    }

    public static <T> t<T> a(t<T> tVar) {
        wm wmVar = n;
        return wmVar != null ? (t) xk.a(wmVar, (Object) tVar) : tVar;
    }

    public static a a(a aVar) {
        wm wmVar = o;
        return wmVar != null ? (a) xk.a(wmVar, (Object) aVar) : aVar;
    }

    public static boolean b() {
        wj wjVar = u;
        if (wjVar == null) {
            return false;
        }
        try {
            return wjVar.a();
        } catch (Throwable th) {
            RuntimeException a = ExceptionHelper.a(th);
        }
    }

    static <T, R> R a(wm<T, R> wmVar, T t) {
        try {
            return wmVar.apply(t);
        } catch (Throwable th) {
            RuntimeException a = ExceptionHelper.a(th);
        }
    }

    static <T, U, R> R a(wh<T, U, R> whVar, T t, U u) {
        try {
            return whVar.apply(t, u);
        } catch (Throwable th) {
            RuntimeException a = ExceptionHelper.a(th);
        }
    }

    static s e(Callable<s> callable) {
        try {
            return (s) io.reactivex.internal.functions.a.a(callable.call(), "Scheduler Callable result can't be null");
        } catch (Throwable th) {
            RuntimeException a = ExceptionHelper.a(th);
        }
    }

    static s a(wm<? super Callable<s>, ? extends s> wmVar, Callable<s> callable) {
        return (s) io.reactivex.internal.functions.a.a(xk.a((wm) wmVar, (Object) callable), "Scheduler Callable result can't be null");
    }
}
