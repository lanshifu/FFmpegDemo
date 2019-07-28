package io.reactivex.internal.operators.observable;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableDelaySubscriptionOther */
public final class t<T, U> extends k<T> {
    final p<? extends T> a;
    final p<U> b;

    /* compiled from: ObservableDelaySubscriptionOther */
    final class a implements r<U> {
        final SequentialDisposable a;
        final r<? super T> b;
        boolean c;

        /* compiled from: ObservableDelaySubscriptionOther */
        final class a implements r<T> {
            a() {
            }

            public void onSubscribe(b bVar) {
                a.this.a.update(bVar);
            }

            public void onNext(T t) {
                a.this.b.onNext(t);
            }

            public void onError(Throwable th) {
                a.this.b.onError(th);
            }

            public void onComplete() {
                a.this.b.onComplete();
            }
        }

        a(SequentialDisposable sequentialDisposable, r<? super T> rVar) {
            this.a = sequentialDisposable;
            this.b = rVar;
        }

        public void onSubscribe(b bVar) {
            this.a.update(bVar);
        }

        public void onNext(U u) {
            onComplete();
        }

        public void onError(Throwable th) {
            if (this.c) {
                xk.a(th);
                return;
            }
            this.c = true;
            this.b.onError(th);
        }

        public void onComplete() {
            if (!this.c) {
                this.c = true;
                t.this.a.subscribe(new a());
            }
        }
    }

    public t(p<? extends T> pVar, p<U> pVar2) {
        this.a = pVar;
        this.b = pVar2;
    }

    public void subscribeActual(r<? super T> rVar) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        rVar.onSubscribe(sequentialDisposable);
        this.b.subscribe(new a(sequentialDisposable, rVar));
    }
}
