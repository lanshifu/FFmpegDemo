package io.reactivex.observers;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.e;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: DisposableObserver */
public abstract class c<T> implements b, r<T> {
    final AtomicReference<b> f = new AtomicReference();

    /* Access modifiers changed, original: protected */
    public void a() {
    }

    public final void onSubscribe(b bVar) {
        if (e.a(this.f, bVar, getClass())) {
            a();
        }
    }

    public final boolean isDisposed() {
        return this.f.get() == DisposableHelper.DISPOSED;
    }

    public final void dispose() {
        DisposableHelper.dispose(this.f);
    }
}
