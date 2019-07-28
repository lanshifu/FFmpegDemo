package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.t;
import io.reactivex.u;
import java.util.NoSuchElementException;

/* compiled from: ObservableLastSingle */
public final class av<T> extends t<T> {
    final p<T> a;
    final T b;

    /* compiled from: ObservableLastSingle */
    static final class a<T> implements b, r<T> {
        final u<? super T> a;
        final T b;
        b c;
        T d;

        a(u<? super T> uVar, T t) {
            this.a = uVar;
            this.b = t;
        }

        public void dispose() {
            this.c.dispose();
            this.c = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.c == DisposableHelper.DISPOSED;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.c, bVar)) {
                this.c = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.d = t;
        }

        public void onError(Throwable th) {
            this.c = DisposableHelper.DISPOSED;
            this.d = null;
            this.a.onError(th);
        }

        public void onComplete() {
            this.c = DisposableHelper.DISPOSED;
            Object obj = this.d;
            if (obj != null) {
                this.d = null;
                this.a.onSuccess(obj);
                return;
            }
            obj = this.b;
            if (obj != null) {
                this.a.onSuccess(obj);
            } else {
                this.a.onError(new NoSuchElementException());
            }
        }
    }

    public av(p<T> pVar, T t) {
        this.a = pVar;
        this.b = t;
    }

    /* Access modifiers changed, original: protected */
    public void b(u<? super T> uVar) {
        this.a.subscribe(new a(uVar, this.b));
    }
}
