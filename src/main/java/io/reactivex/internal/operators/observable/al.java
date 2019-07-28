package io.reactivex.internal.operators.observable;

import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.observers.b;
import io.reactivex.k;
import io.reactivex.r;
import java.util.Iterator;

/* compiled from: ObservableFromIterable */
public final class al<T> extends k<T> {
    final Iterable<? extends T> a;

    /* compiled from: ObservableFromIterable */
    static final class a<T> extends b<T> {
        final r<? super T> a;
        final Iterator<? extends T> b;
        volatile boolean c;
        boolean d;
        boolean e;
        boolean f;

        a(r<? super T> rVar, Iterator<? extends T> it) {
            this.a = rVar;
            this.b = it;
        }

        /* Access modifiers changed, original: 0000 */
        public void a() {
            while (!isDisposed()) {
                try {
                    this.a.onNext(io.reactivex.internal.functions.a.a(this.b.next(), "The iterator returned a null value"));
                    if (!isDisposed()) {
                        try {
                            if (!this.b.hasNext()) {
                                if (!isDisposed()) {
                                    this.a.onComplete();
                                }
                                return;
                            }
                        } catch (Throwable th) {
                            io.reactivex.exceptions.a.b(th);
                            this.a.onError(th);
                            return;
                        }
                    }
                    return;
                } catch (Throwable th2) {
                    io.reactivex.exceptions.a.b(th2);
                    this.a.onError(th2);
                    return;
                }
            }
        }

        public int requestFusion(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            this.d = true;
            return 1;
        }

        public T poll() {
            if (this.e) {
                return null;
            }
            if (!this.f) {
                this.f = true;
            } else if (!this.b.hasNext()) {
                this.e = true;
                return null;
            }
            return io.reactivex.internal.functions.a.a(this.b.next(), "The iterator returned a null value");
        }

        public boolean isEmpty() {
            return this.e;
        }

        public void clear() {
            this.e = true;
        }

        public void dispose() {
            this.c = true;
        }

        public boolean isDisposed() {
            return this.c;
        }
    }

    public al(Iterable<? extends T> iterable) {
        this.a = iterable;
    }

    public void subscribeActual(r<? super T> rVar) {
        try {
            Iterator it = this.a.iterator();
            try {
                if (it.hasNext()) {
                    a aVar = new a(rVar, it);
                    rVar.onSubscribe(aVar);
                    if (!aVar.d) {
                        aVar.a();
                    }
                    return;
                }
                EmptyDisposable.complete((r) rVar);
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                EmptyDisposable.error(th, (r) rVar);
            }
        } catch (Throwable th2) {
            io.reactivex.exceptions.a.b(th2);
            EmptyDisposable.error(th2, (r) rVar);
        }
    }
}
