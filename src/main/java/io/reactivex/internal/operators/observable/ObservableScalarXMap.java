package io.reactivex.internal.operators.observable;

import defpackage.wm;
import defpackage.wy;
import defpackage.xk;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableScalarXMap {

    static final class a<T, R> extends k<R> {
        final T a;
        final wm<? super T, ? extends p<? extends R>> b;

        a(T t, wm<? super T, ? extends p<? extends R>> wmVar) {
            this.a = t;
            this.b = wmVar;
        }

        public void subscribeActual(r<? super R> rVar) {
            try {
                p pVar = (p) io.reactivex.internal.functions.a.a(this.b.apply(this.a), "The mapper returned a null ObservableSource");
                if (pVar instanceof Callable) {
                    try {
                        Object call = ((Callable) pVar).call();
                        if (call == null) {
                            EmptyDisposable.complete((r) rVar);
                            return;
                        }
                        ScalarDisposable scalarDisposable = new ScalarDisposable(rVar, call);
                        rVar.onSubscribe(scalarDisposable);
                        scalarDisposable.run();
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        EmptyDisposable.error(th, (r) rVar);
                        return;
                    }
                }
                pVar.subscribe(rVar);
            } catch (Throwable th2) {
                EmptyDisposable.error(th2, (r) rVar);
            }
        }
    }

    public static final class ScalarDisposable<T> extends AtomicInteger implements Runnable, wy<T> {
        static final int FUSED = 1;
        static final int ON_COMPLETE = 3;
        static final int ON_NEXT = 2;
        static final int START = 0;
        private static final long serialVersionUID = 3880992722410194083L;
        final r<? super T> observer;
        final T value;

        public ScalarDisposable(r<? super T> rVar, T t) {
            this.observer = rVar;
            this.value = t;
        }

        public boolean offer(T t) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        public boolean offer(T t, T t2) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        public T poll() throws Exception {
            if (get() != 1) {
                return null;
            }
            lazySet(3);
            return this.value;
        }

        public boolean isEmpty() {
            return get() != 1;
        }

        public void clear() {
            lazySet(3);
        }

        public void dispose() {
            set(3);
        }

        public boolean isDisposed() {
            return get() == 3;
        }

        public int requestFusion(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            lazySet(1);
            return 1;
        }

        public void run() {
            if (get() == 0 && compareAndSet(0, 2)) {
                this.observer.onNext(this.value);
                if (get() == 2) {
                    lazySet(3);
                    this.observer.onComplete();
                }
            }
        }
    }

    public static <T, R> boolean a(p<T> pVar, r<? super R> rVar, wm<? super T, ? extends p<? extends R>> wmVar) {
        if (!(pVar instanceof Callable)) {
            return false;
        }
        try {
            Object call = ((Callable) pVar).call();
            if (call == null) {
                EmptyDisposable.complete((r) rVar);
                return true;
            }
            try {
                p pVar2 = (p) io.reactivex.internal.functions.a.a(wmVar.apply(call), "The mapper returned a null ObservableSource");
                if (pVar2 instanceof Callable) {
                    try {
                        call = ((Callable) pVar2).call();
                        if (call == null) {
                            EmptyDisposable.complete((r) rVar);
                            return true;
                        }
                        ScalarDisposable scalarDisposable = new ScalarDisposable(rVar, call);
                        rVar.onSubscribe(scalarDisposable);
                        scalarDisposable.run();
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        EmptyDisposable.error(th, (r) rVar);
                        return true;
                    }
                }
                pVar2.subscribe(rVar);
                return true;
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                EmptyDisposable.error(th2, (r) rVar);
                return true;
            }
        } catch (Throwable th22) {
            io.reactivex.exceptions.a.b(th22);
            EmptyDisposable.error(th22, (r) rVar);
            return true;
        }
    }

    public static <T, U> k<U> a(T t, wm<? super T, ? extends p<? extends U>> wmVar) {
        return xk.a(new a(t, wmVar));
    }
}
