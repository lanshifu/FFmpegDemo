package io.reactivex.internal.operators.single;

import io.reactivex.disposables.b;
import io.reactivex.e;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.u;
import io.reactivex.v;

public final class SingleToFlowable<T> extends e<T> {
    final v<? extends T> b;

    static final class SingleToFlowableObserver<T> extends DeferredScalarSubscription<T> implements u<T> {
        private static final long serialVersionUID = 187782011903685568L;
        b upstream;

        SingleToFlowableObserver(aar<? super T> aar) {
            super(aar);
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            complete(t);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        public void cancel() {
            super.cancel();
            this.upstream.dispose();
        }
    }

    public SingleToFlowable(v<? extends T> vVar) {
        this.b = vVar;
    }

    public void b(aar<? super T> aar) {
        this.b.a(new SingleToFlowableObserver(aar));
    }
}
