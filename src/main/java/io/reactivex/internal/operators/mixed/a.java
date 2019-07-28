package io.reactivex.internal.operators.mixed;

import defpackage.wm;
import io.reactivex.b;
import io.reactivex.c;
import io.reactivex.i;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.operators.maybe.MaybeToObservable;
import io.reactivex.internal.operators.single.SingleToObservable;
import io.reactivex.r;
import io.reactivex.v;
import java.util.concurrent.Callable;

/* compiled from: ScalarXMapZHelper */
final class a {
    static <T> boolean a(Object obj, wm<? super T, ? extends c> wmVar, b bVar) {
        if (!(obj instanceof Callable)) {
            return false;
        }
        c cVar = null;
        try {
            obj = ((Callable) obj).call();
            if (obj != null) {
                cVar = (c) io.reactivex.internal.functions.a.a(wmVar.apply(obj), "The mapper returned a null CompletableSource");
            }
            if (cVar == null) {
                EmptyDisposable.complete(bVar);
            } else {
                cVar.a(bVar);
            }
            return true;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, bVar);
            return true;
        }
    }

    static <T, R> boolean a(Object obj, wm<? super T, ? extends i<? extends R>> wmVar, r<? super R> rVar) {
        if (!(obj instanceof Callable)) {
            return false;
        }
        i iVar = null;
        try {
            obj = ((Callable) obj).call();
            if (obj != null) {
                iVar = (i) io.reactivex.internal.functions.a.a(wmVar.apply(obj), "The mapper returned a null MaybeSource");
            }
            if (iVar == null) {
                EmptyDisposable.complete((r) rVar);
            } else {
                iVar.a(MaybeToObservable.a(rVar));
            }
            return true;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, (r) rVar);
            return true;
        }
    }

    static <T, R> boolean b(Object obj, wm<? super T, ? extends v<? extends R>> wmVar, r<? super R> rVar) {
        if (!(obj instanceof Callable)) {
            return false;
        }
        v vVar = null;
        try {
            obj = ((Callable) obj).call();
            if (obj != null) {
                vVar = (v) io.reactivex.internal.functions.a.a(wmVar.apply(obj), "The mapper returned a null SingleSource");
            }
            if (vVar == null) {
                EmptyDisposable.complete((r) rVar);
            } else {
                vVar.a(SingleToObservable.a(rVar));
            }
            return true;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, (r) rVar);
            return true;
        }
    }
}
