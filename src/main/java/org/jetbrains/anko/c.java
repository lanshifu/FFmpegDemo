package org.jetbrains.anko;

import defpackage.xv;
import java.util.concurrent.Callable;

/* compiled from: Async.kt */
final class c implements Callable {
    private final /* synthetic */ xv a;

    c(xv xvVar) {
        this.a = xvVar;
    }

    public final /* synthetic */ V call() {
        return this.a.invoke();
    }
}
