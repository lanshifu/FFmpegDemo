package org.jetbrains.anko;

import java.lang.ref.WeakReference;
import kotlin.jvm.internal.f;

/* compiled from: Async.kt */
public final class a<T> {
    private final WeakReference<T> a;

    public a(WeakReference<T> weakReference) {
        f.b(weakReference, "weakRef");
        this.a = weakReference;
    }

    public final WeakReference<T> a() {
        return this.a;
    }
}
