package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableSwitchIfEmpty */
public final class bo<T> extends a<T, T> {
    final p<? extends T> b;

    /* compiled from: ObservableSwitchIfEmpty */
    static final class a<T> implements r<T> {
        final r<? super T> a;
        final p<? extends T> b;
        final SequentialDisposable c = new SequentialDisposable();
        boolean d = true;

        a(r<? super T> rVar, p<? extends T> pVar) {
            this.a = rVar;
            this.b = pVar;
        }

        public void onSubscribe(b bVar) {
            this.c.update(bVar);
        }

        public void onNext(T t) {
            if (this.d) {
                this.d = false;
            }
            this.a.onNext(t);
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onComplete() {
            if (this.d) {
                this.d = false;
                this.b.subscribe(this);
                return;
            }
            this.a.onComplete();
        }
    }

    public bo(p<T> pVar, p<? extends T> pVar2) {
        super(pVar);
        this.b = pVar2;
    }

    public void subscribeActual(r<? super T> rVar) {
        a aVar = new a(rVar, this.b);
        rVar.onSubscribe(aVar.c);
        this.a.subscribe(aVar);
    }
}
