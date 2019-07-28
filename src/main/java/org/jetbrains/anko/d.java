package org.jetbrains.anko;

import defpackage.xv;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import kotlin.jvm.internal.f;

/* compiled from: Async.kt */
public final class d {
    public static final d a = null;
    private static ExecutorService b;

    static {
        d dVar = new d();
    }

    private d() {
        a = this;
        Object newScheduledThreadPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        f.a(newScheduledThreadPool, "Executors.newScheduledTh…().availableProcessors())");
        b = (ExecutorService) newScheduledThreadPool;
    }

    public final <T> Future<T> a(xv<? extends T> xvVar) {
        f.b(xvVar, "task");
        Object submit = b.submit(xvVar == null ? null : new c(xvVar));
        f.a(submit, "executor.submit(task)");
        return submit;
    }
}
