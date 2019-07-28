package io.reactivex.disposables;

import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.a;

/* compiled from: Disposables */
public final class c {
    public static b a(Runnable runnable) {
        a.a((Object) runnable, "run is null");
        return new RunnableDisposable(runnable);
    }

    public static b a() {
        return a(Functions.b);
    }

    public static b b() {
        return EmptyDisposable.INSTANCE;
    }
}
