package io.reactivex.internal.operators.observable;

import defpackage.xk;
import io.reactivex.internal.functions.a;
import io.reactivex.o;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableLift */
public final class aw<R, T> extends a<T, R> {
    final o<? extends R, ? super T> b;

    public aw(p<T> pVar, o<? extends R, ? super T> oVar) {
        super(pVar);
        this.b = oVar;
    }

    public void subscribeActual(r<? super R> rVar) {
        try {
            Object a = this.b.a(rVar);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Operator ");
            stringBuilder.append(this.b);
            stringBuilder.append(" returned a null Observer");
            this.a.subscribe((r) a.a(a, stringBuilder.toString()));
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            xk.a(th);
            new NullPointerException("Actually not, but can't throw other exceptions due to RS").initCause(th);
        }
    }
}
