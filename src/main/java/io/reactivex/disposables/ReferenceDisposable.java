package io.reactivex.disposables;

import io.reactivex.internal.functions.a;
import java.util.concurrent.atomic.AtomicReference;

abstract class ReferenceDisposable<T> extends AtomicReference<T> implements b {
    private static final long serialVersionUID = 6537757548749041217L;

    public abstract void onDisposed(T t);

    ReferenceDisposable(T t) {
        super(a.a((Object) t, "value is null"));
    }

    public final void dispose() {
        if (get() != null) {
            Object andSet = getAndSet(null);
            if (andSet != null) {
                onDisposed(andSet);
            }
        }
    }

    public final boolean isDisposed() {
        return get() == null;
    }
}
