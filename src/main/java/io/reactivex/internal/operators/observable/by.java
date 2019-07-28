package io.reactivex.internal.operators.observable;

import defpackage.wh;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.k;
import io.reactivex.r;
import java.util.Iterator;

/* compiled from: ObservableZipIterable */
public final class by<T, U, V> extends k<V> {
    final k<? extends T> a;
    final Iterable<U> b;
    final wh<? super T, ? super U, ? extends V> c;

    /* compiled from: ObservableZipIterable */
    static final class a<T, U, V> implements b, r<T> {
        final r<? super V> a;
        final Iterator<U> b;
        final wh<? super T, ? super U, ? extends V> c;
        b d;
        boolean e;

        a(r<? super V> rVar, Iterator<U> it, wh<? super T, ? super U, ? extends V> whVar) {
            this.a = rVar;
            this.b = it;
            this.c = whVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.d, bVar)) {
                this.d = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void dispose() {
            this.d.dispose();
        }

        public boolean isDisposed() {
            return this.d.isDisposed();
        }

        public void onNext(T t) {
            if (!this.e) {
                try {
                    try {
                        this.a.onNext(io.reactivex.internal.functions.a.a(this.c.apply(t, io.reactivex.internal.functions.a.a(this.b.next(), "The iterator returned a null value")), "The zipper function returned a null value"));
                        try {
                            if (!this.b.hasNext()) {
                                this.e = true;
                                this.d.dispose();
                                this.a.onComplete();
                            }
                        } catch (Throwable th) {
                            io.reactivex.exceptions.a.b(th);
                            a(th);
                        }
                    } catch (Throwable th2) {
                        io.reactivex.exceptions.a.b(th2);
                        a(th2);
                    }
                } catch (Throwable th22) {
                    io.reactivex.exceptions.a.b(th22);
                    a(th22);
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void a(Throwable th) {
            this.e = true;
            this.d.dispose();
            this.a.onError(th);
        }

        public void onError(Throwable th) {
            if (this.e) {
                xk.a(th);
                return;
            }
            this.e = true;
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.e) {
                this.e = true;
                this.a.onComplete();
            }
        }
    }

    public by(k<? extends T> kVar, Iterable<U> iterable, wh<? super T, ? super U, ? extends V> whVar) {
        this.a = kVar;
        this.b = iterable;
        this.c = whVar;
    }

    public void subscribeActual(r<? super V> rVar) {
        try {
            Iterator it = (Iterator) io.reactivex.internal.functions.a.a(this.b.iterator(), "The iterator returned by other is null");
            try {
                if (it.hasNext()) {
                    this.a.subscribe(new a(rVar, it, this.c));
                } else {
                    EmptyDisposable.complete((r) rVar);
                }
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
