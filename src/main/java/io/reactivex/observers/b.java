package io.reactivex.observers;

import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.e;
import io.reactivex.r;

/* compiled from: DefaultObserver */
public abstract class b<T> implements r<T> {
    private io.reactivex.disposables.b upstream;

    /* Access modifiers changed, original: protected */
    public void onStart() {
    }

    public final void onSubscribe(io.reactivex.disposables.b bVar) {
        if (e.a(this.upstream, bVar, getClass())) {
            this.upstream = bVar;
            onStart();
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void cancel() {
        io.reactivex.disposables.b bVar = this.upstream;
        this.upstream = DisposableHelper.DISPOSED;
        bVar.dispose();
    }
}
