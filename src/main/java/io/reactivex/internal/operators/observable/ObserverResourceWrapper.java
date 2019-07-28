package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicReference;

public final class ObserverResourceWrapper<T> extends AtomicReference<b> implements b, r<T> {
    private static final long serialVersionUID = -8612022020200669122L;
    final r<? super T> downstream;
    final AtomicReference<b> upstream = new AtomicReference();

    public ObserverResourceWrapper(r<? super T> rVar) {
        this.downstream = rVar;
    }

    public void onSubscribe(b bVar) {
        if (DisposableHelper.setOnce(this.upstream, bVar)) {
            this.downstream.onSubscribe(this);
        }
    }

    public void onNext(T t) {
        this.downstream.onNext(t);
    }

    public void onError(Throwable th) {
        dispose();
        this.downstream.onError(th);
    }

    public void onComplete() {
        dispose();
        this.downstream.onComplete();
    }

    public void dispose() {
        DisposableHelper.dispose(this.upstream);
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return this.upstream.get() == DisposableHelper.DISPOSED;
    }

    public void setResource(b bVar) {
        DisposableHelper.set(this, bVar);
    }
}
