package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.f;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.k;
import io.reactivex.r;

/* compiled from: ObservableFromPublisher */
public final class am<T> extends k<T> {
    final aaq<? extends T> a;

    /* compiled from: ObservableFromPublisher */
    static final class a<T> implements b, f<T> {
        final r<? super T> a;
        aas b;

        a(r<? super T> rVar) {
            this.a = rVar;
        }

        public void onComplete() {
            this.a.onComplete();
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onNext(T t) {
            this.a.onNext(t);
        }

        public void onSubscribe(aas aas) {
            if (SubscriptionHelper.validate(this.b, aas)) {
                this.b = aas;
                this.a.onSubscribe(this);
                aas.request(Long.MAX_VALUE);
            }
        }

        public void dispose() {
            this.b.cancel();
            this.b = SubscriptionHelper.CANCELLED;
        }

        public boolean isDisposed() {
            return this.b == SubscriptionHelper.CANCELLED;
        }
    }

    public am(aaq<? extends T> aaq) {
        this.a = aaq;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.a.a(new a(rVar));
    }
}
