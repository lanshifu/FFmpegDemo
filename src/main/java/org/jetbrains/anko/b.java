package org.jetbrains.anko;

import defpackage.xw;
import java.lang.ref.WeakReference;
import java.util.concurrent.Future;
import kotlin.d;
import kotlin.jvm.internal.f;

/* compiled from: Async.kt */
public final class b {
    private static final xw<Throwable, d> a = AsyncKt$crashLogger$1.INSTANCE;

    /* compiled from: Async.kt */
    static final class a implements Runnable {
        final /* synthetic */ xw a;
        final /* synthetic */ Object b;

        a(xw xwVar, Object obj) {
            this.a = xwVar;
            this.b = obj;
        }

        public final void run() {
            this.a.invoke(this.b);
        }
    }

    public static final <T> boolean a(a<T> aVar, xw<? super T, d> xwVar) {
        f.b(aVar, "$receiver");
        f.b(xwVar, "f");
        Object obj = aVar.a().get();
        if (obj == null) {
            return false;
        }
        if (f.a(e.a.b(), Thread.currentThread())) {
            xwVar.invoke(obj);
        } else {
            e.a.a().post(new a(xwVar, obj));
        }
        return true;
    }

    public static /* bridge */ /* synthetic */ Future a(Object obj, xw xwVar, xw xwVar2, int i, Object obj2) {
        if ((i & 1) != 0) {
            xwVar = a;
        }
        return a(obj, xwVar, xwVar2);
    }

    public static final <T> Future<d> a(T t, xw<? super Throwable, d> xwVar, xw<? super a<T>, d> xwVar2) {
        f.b(xwVar2, "task");
        return d.a.a(new AsyncKt$doAsync$1(xwVar2, new a(new WeakReference(t)), xwVar));
    }
}
