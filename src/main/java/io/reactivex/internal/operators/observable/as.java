package io.reactivex.internal.operators.observable;

import defpackage.wf;
import defpackage.wg;
import defpackage.wh;
import defpackage.wl;
import defpackage.wm;
import defpackage.xi;
import io.reactivex.internal.functions.Functions;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.s;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* compiled from: ObservableInternalHelper */
public final class as {

    /* compiled from: ObservableInternalHelper */
    static final class a<T> implements Callable<xi<T>> {
        private final io.reactivex.k<T> a;
        private final int b;

        a(io.reactivex.k<T> kVar, int i) {
            this.a = kVar;
            this.b = i;
        }

        /* renamed from: a */
        public xi<T> call() {
            return this.a.replay(this.b);
        }
    }

    /* compiled from: ObservableInternalHelper */
    static final class b<T> implements Callable<xi<T>> {
        private final io.reactivex.k<T> a;
        private final int b;
        private final long c;
        private final TimeUnit d;
        private final s e;

        b(io.reactivex.k<T> kVar, int i, long j, TimeUnit timeUnit, s sVar) {
            this.a = kVar;
            this.b = i;
            this.c = j;
            this.d = timeUnit;
            this.e = sVar;
        }

        /* renamed from: a */
        public xi<T> call() {
            return this.a.replay(this.b, this.c, this.d, this.e);
        }
    }

    /* compiled from: ObservableInternalHelper */
    static final class j<T> implements Callable<xi<T>> {
        private final io.reactivex.k<T> a;

        j(io.reactivex.k<T> kVar) {
            this.a = kVar;
        }

        /* renamed from: a */
        public xi<T> call() {
            return this.a.replay();
        }
    }

    /* compiled from: ObservableInternalHelper */
    static final class n<T> implements Callable<xi<T>> {
        private final io.reactivex.k<T> a;
        private final long b;
        private final TimeUnit c;
        private final s d;

        n(io.reactivex.k<T> kVar, long j, TimeUnit timeUnit, s sVar) {
            this.a = kVar;
            this.b = j;
            this.c = timeUnit;
            this.d = sVar;
        }

        /* renamed from: a */
        public xi<T> call() {
            return this.a.replay(this.b, this.c, this.d);
        }
    }

    /* compiled from: ObservableInternalHelper */
    static final class c<T, U> implements wm<T, p<U>> {
        private final wm<? super T, ? extends Iterable<? extends U>> a;

        c(wm<? super T, ? extends Iterable<? extends U>> wmVar) {
            this.a = wmVar;
        }

        /* renamed from: a */
        public p<U> apply(T t) throws Exception {
            return new al((Iterable) io.reactivex.internal.functions.a.a(this.a.apply(t), "The mapper returned a null Iterable"));
        }
    }

    /* compiled from: ObservableInternalHelper */
    static final class d<U, R, T> implements wm<U, R> {
        private final wh<? super T, ? super U, ? extends R> a;
        private final T b;

        d(wh<? super T, ? super U, ? extends R> whVar, T t) {
            this.a = whVar;
            this.b = t;
        }

        public R apply(U u) throws Exception {
            return this.a.apply(this.b, u);
        }
    }

    /* compiled from: ObservableInternalHelper */
    static final class e<T, R, U> implements wm<T, p<R>> {
        private final wh<? super T, ? super U, ? extends R> a;
        private final wm<? super T, ? extends p<? extends U>> b;

        e(wh<? super T, ? super U, ? extends R> whVar, wm<? super T, ? extends p<? extends U>> wmVar) {
            this.a = whVar;
            this.b = wmVar;
        }

        /* renamed from: a */
        public p<R> apply(T t) throws Exception {
            return new ax((p) io.reactivex.internal.functions.a.a(this.b.apply(t), "The mapper returned a null ObservableSource"), new d(this.a, t));
        }
    }

    /* compiled from: ObservableInternalHelper */
    static final class f<T, U> implements wm<T, p<T>> {
        final wm<? super T, ? extends p<U>> a;

        f(wm<? super T, ? extends p<U>> wmVar) {
            this.a = wmVar;
        }

        /* renamed from: a */
        public p<T> apply(T t) throws Exception {
            return new bp((p) io.reactivex.internal.functions.a.a(this.a.apply(t), "The itemDelay returned a null ObservableSource"), 1).map(Functions.b((Object) t)).defaultIfEmpty(t);
        }
    }

    /* compiled from: ObservableInternalHelper */
    static final class g<T> implements wf {
        final r<T> a;

        g(r<T> rVar) {
            this.a = rVar;
        }

        public void a() throws Exception {
            this.a.onComplete();
        }
    }

    /* compiled from: ObservableInternalHelper */
    static final class h<T> implements wl<Throwable> {
        final r<T> a;

        h(r<T> rVar) {
            this.a = rVar;
        }

        /* renamed from: a */
        public void accept(Throwable th) throws Exception {
            this.a.onError(th);
        }
    }

    /* compiled from: ObservableInternalHelper */
    static final class i<T> implements wl<T> {
        final r<T> a;

        i(r<T> rVar) {
            this.a = rVar;
        }

        public void accept(T t) throws Exception {
            this.a.onNext(t);
        }
    }

    /* compiled from: ObservableInternalHelper */
    static final class k<T, R> implements wm<io.reactivex.k<T>, p<R>> {
        private final wm<? super io.reactivex.k<T>, ? extends p<R>> a;
        private final s b;

        k(wm<? super io.reactivex.k<T>, ? extends p<R>> wmVar, s sVar) {
            this.a = wmVar;
            this.b = sVar;
        }

        /* renamed from: a */
        public p<R> apply(io.reactivex.k<T> kVar) throws Exception {
            return io.reactivex.k.wrap((p) io.reactivex.internal.functions.a.a(this.a.apply(kVar), "The selector returned a null ObservableSource")).observeOn(this.b);
        }
    }

    /* compiled from: ObservableInternalHelper */
    static final class l<T, S> implements wh<S, io.reactivex.d<T>, S> {
        final wg<S, io.reactivex.d<T>> a;

        l(wg<S, io.reactivex.d<T>> wgVar) {
            this.a = wgVar;
        }

        /* renamed from: a */
        public S apply(S s, io.reactivex.d<T> dVar) throws Exception {
            this.a.accept(s, dVar);
            return s;
        }
    }

    /* compiled from: ObservableInternalHelper */
    static final class m<T, S> implements wh<S, io.reactivex.d<T>, S> {
        final wl<io.reactivex.d<T>> a;

        m(wl<io.reactivex.d<T>> wlVar) {
            this.a = wlVar;
        }

        /* renamed from: a */
        public S apply(S s, io.reactivex.d<T> dVar) throws Exception {
            this.a.accept(dVar);
            return s;
        }
    }

    /* compiled from: ObservableInternalHelper */
    static final class o<T, R> implements wm<List<p<? extends T>>, p<? extends R>> {
        private final wm<? super Object[], ? extends R> a;

        o(wm<? super Object[], ? extends R> wmVar) {
            this.a = wmVar;
        }

        /* renamed from: a */
        public p<? extends R> apply(List<p<? extends T>> list) {
            return io.reactivex.k.zipIterable(list, this.a, false, io.reactivex.k.bufferSize());
        }
    }

    public static <T, S> wh<S, io.reactivex.d<T>, S> a(wl<io.reactivex.d<T>> wlVar) {
        return new m(wlVar);
    }

    public static <T, S> wh<S, io.reactivex.d<T>, S> a(wg<S, io.reactivex.d<T>> wgVar) {
        return new l(wgVar);
    }

    public static <T, U> wm<T, p<T>> a(wm<? super T, ? extends p<U>> wmVar) {
        return new f(wmVar);
    }

    public static <T> wl<T> a(r<T> rVar) {
        return new i(rVar);
    }

    public static <T> wl<Throwable> b(r<T> rVar) {
        return new h(rVar);
    }

    public static <T> wf c(r<T> rVar) {
        return new g(rVar);
    }

    public static <T, U, R> wm<T, p<R>> a(wm<? super T, ? extends p<? extends U>> wmVar, wh<? super T, ? super U, ? extends R> whVar) {
        return new e(whVar, wmVar);
    }

    public static <T, U> wm<T, p<U>> b(wm<? super T, ? extends Iterable<? extends U>> wmVar) {
        return new c(wmVar);
    }

    public static <T> Callable<xi<T>> a(io.reactivex.k<T> kVar) {
        return new j(kVar);
    }

    public static <T> Callable<xi<T>> a(io.reactivex.k<T> kVar, int i) {
        return new a(kVar, i);
    }

    public static <T> Callable<xi<T>> a(io.reactivex.k<T> kVar, int i, long j, TimeUnit timeUnit, s sVar) {
        return new b(kVar, i, j, timeUnit, sVar);
    }

    public static <T> Callable<xi<T>> a(io.reactivex.k<T> kVar, long j, TimeUnit timeUnit, s sVar) {
        return new n(kVar, j, timeUnit, sVar);
    }

    public static <T, R> wm<io.reactivex.k<T>, p<R>> a(wm<? super io.reactivex.k<T>, ? extends p<R>> wmVar, s sVar) {
        return new k(wmVar, sVar);
    }

    public static <T, R> wm<List<p<? extends T>>, p<? extends R>> c(wm<? super Object[], ? extends R> wmVar) {
        return new o(wmVar);
    }
}
