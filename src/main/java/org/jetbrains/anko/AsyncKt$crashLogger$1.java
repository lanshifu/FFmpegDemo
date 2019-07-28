package org.jetbrains.anko;

import defpackage.xw;
import kotlin.d;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.f;

/* compiled from: Async.kt */
final class AsyncKt$crashLogger$1 extends Lambda implements xw<Throwable, d> {
    public static final AsyncKt$crashLogger$1 INSTANCE = new AsyncKt$crashLogger$1();

    AsyncKt$crashLogger$1() {
        super(1);
    }

    public final void invoke(Throwable th) {
        f.b(th, "throwable");
        th.printStackTrace();
    }
}
