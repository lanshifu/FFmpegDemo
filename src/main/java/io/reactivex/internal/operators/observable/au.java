package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.g;
import io.reactivex.h;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableLastMaybe */
public final class au<T> extends g<T> {
    final p<T> a;

    /* compiled from: ObservableLastMaybe */
    static final class a<T> implements b, r<T> {
        final h<? super T> a;
        b b;
        T c;

        a(h<? super T> hVar) {
            this.a = hVar;
        }

        public void dispose() {
            this.b.dispose();
            this.b = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.b == DisposableHelper.DISPOSED;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.b, bVar)) {
                this.b = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.c = t;
        }

        public void onError(Throwable th) {
            this.b = DisposableHelper.DISPOSED;
            this.c = null;
            this.a.onError(th);
        }

        public void onComplete() {
            this.b = DisposableHelper.DISPOSED;
            Object obj = this.c;
            if (obj != null) {
                this.c = null;
                this.a.onSuccess(obj);
                return;
            }
            this.a.onComplete();
        }
    }

    public au(p<T> pVar) {
        this.a = pVar;
    }

    /* Access modifiers changed, original: protected */
    public void b(h<? super T> hVar) {
        this.a.subscribe(new a(hVar));
    }
}
