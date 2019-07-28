package defpackage;

import io.reactivex.internal.schedulers.c;
import io.reactivex.internal.schedulers.d;
import io.reactivex.internal.schedulers.h;
import io.reactivex.internal.schedulers.i;
import io.reactivex.s;
import java.util.concurrent.Callable;

/* compiled from: Schedulers */
/* renamed from: xl */
public final class xl {
    static final s a = xk.d(new h());
    static final s b = xk.a(new b());
    static final s c = xk.b(new c());
    static final s d = i.c();
    static final s e = xk.c(new f());

    /* compiled from: Schedulers */
    /* renamed from: xl$a */
    static final class a {
        static final s a = new io.reactivex.internal.schedulers.a();
    }

    /* compiled from: Schedulers */
    /* renamed from: xl$b */
    static final class b implements Callable<s> {
        b() {
        }

        /* renamed from: a */
        public s call() throws Exception {
            return a.a;
        }
    }

    /* compiled from: Schedulers */
    /* renamed from: xl$c */
    static final class c implements Callable<s> {
        c() {
        }

        /* renamed from: a */
        public s call() throws Exception {
            return d.a;
        }
    }

    /* compiled from: Schedulers */
    /* renamed from: xl$d */
    static final class d {
        static final s a = new c();
    }

    /* compiled from: Schedulers */
    /* renamed from: xl$e */
    static final class e {
        static final s a = new d();
    }

    /* compiled from: Schedulers */
    /* renamed from: xl$f */
    static final class f implements Callable<s> {
        f() {
        }

        /* renamed from: a */
        public s call() throws Exception {
            return e.a;
        }
    }

    /* compiled from: Schedulers */
    /* renamed from: xl$g */
    static final class g {
        static final s a = new h();
    }

    /* compiled from: Schedulers */
    /* renamed from: xl$h */
    static final class h implements Callable<s> {
        h() {
        }

        /* renamed from: a */
        public s call() throws Exception {
            return g.a;
        }
    }

    public static s a() {
        return xk.a(b);
    }

    public static s b() {
        return xk.b(c);
    }

    public static s c() {
        return d;
    }

    public static s d() {
        return xk.c(e);
    }
}
