package org.jetbrains.anko;

import defpackage.xv;
import defpackage.xw;
import kotlin.d;
import kotlin.jvm.internal.Lambda;

/* compiled from: Async.kt */
final class AsyncKt$doAsync$1 extends Lambda implements xv<d> {
    final /* synthetic */ a $context;
    final /* synthetic */ xw $exceptionHandler;
    final /* synthetic */ xw $task;

    AsyncKt$doAsync$1(xw xwVar, a aVar, xw xwVar2) {
        this.$task = xwVar;
        this.$context = aVar;
        this.$exceptionHandler = xwVar2;
        super(0);
    }

    public final void invoke() {
        d dVar;
        try {
            dVar = (d) this.$task.invoke(this.$context);
        } catch (Throwable th) {
            xw xwVar = this.$exceptionHandler;
            if ((xwVar != null ? (d) xwVar.invoke(th) : null) == null) {
                dVar = d.a;
            }
        }
    }
}
