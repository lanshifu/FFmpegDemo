package io.reactivex;

import defpackage.wf;
import defpackage.wl;
import defpackage.wm;
import defpackage.wv;
import defpackage.xk;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.operators.flowable.FlowableInternalHelper.RequestMax;
import io.reactivex.internal.operators.flowable.FlowableObserveOn;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureBuffer;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureDrop;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureLatest;
import io.reactivex.internal.operators.flowable.FlowableTakeUntil;
import io.reactivex.internal.operators.flowable.b;
import io.reactivex.internal.operators.flowable.d;
import io.reactivex.internal.subscribers.LambdaSubscriber;
import io.reactivex.internal.subscribers.StrictSubscriber;

/* compiled from: Flowable */
public abstract class e<T> implements aaq<T> {
    static final int a = Math.max(1, Integer.getInteger("rx2.buffer-size", 128).intValue());

    public abstract void b(aar<? super T> aar);

    public static int a() {
        return a;
    }

    public static <T> e<T> a(T t) {
        a.a((Object) t, "item is null");
        return xk.a(new d(t));
    }

    public final <U> e<U> a(Class<U> cls) {
        a.a((Object) cls, "clazz is null");
        return a(Functions.a((Class) cls));
    }

    public final e<T> a(wv<? super T> wvVar) {
        a.a((Object) wvVar, "predicate is null");
        return xk.a(new b(this, wvVar));
    }

    public final <R> e<R> a(wm<? super T, ? extends R> wmVar) {
        a.a((Object) wmVar, "mapper is null");
        return xk.a(new io.reactivex.internal.operators.flowable.e(this, wmVar));
    }

    public final e<T> a(s sVar) {
        return a(sVar, false, a());
    }

    public final e<T> a(s sVar, boolean z, int i) {
        a.a((Object) sVar, "scheduler is null");
        a.a(i, "bufferSize");
        return xk.a(new FlowableObserveOn(this, sVar, z, i));
    }

    public final <U> e<U> b(Class<U> cls) {
        a.a((Object) cls, "clazz is null");
        return a(Functions.b((Class) cls)).a((Class) cls);
    }

    public final e<T> b() {
        return a(a(), false, true);
    }

    public final e<T> a(int i, boolean z, boolean z2) {
        a.a(i, "bufferSize");
        return xk.a(new FlowableOnBackpressureBuffer(this, i, z2, z, Functions.c));
    }

    public final e<T> c() {
        return xk.a(new FlowableOnBackpressureDrop(this));
    }

    public final e<T> d() {
        return xk.a(new FlowableOnBackpressureLatest(this));
    }

    public final io.reactivex.disposables.b a(wl<? super T> wlVar) {
        return a(wlVar, Functions.f, Functions.c, RequestMax.INSTANCE);
    }

    public final io.reactivex.disposables.b a(wl<? super T> wlVar, wl<? super Throwable> wlVar2, wf wfVar, wl<? super aas> wlVar3) {
        a.a((Object) wlVar, "onNext is null");
        a.a((Object) wlVar2, "onError is null");
        a.a((Object) wfVar, "onComplete is null");
        a.a((Object) wlVar3, "onSubscribe is null");
        f lambdaSubscriber = new LambdaSubscriber(wlVar, wlVar2, wfVar, wlVar3);
        a(lambdaSubscriber);
        return lambdaSubscriber;
    }

    public final void a(aar<? super T> aar) {
        if (aar instanceof f) {
            a((f) aar);
            return;
        }
        a.a((Object) aar, "s is null");
        a(new StrictSubscriber(aar));
    }

    public final void a(f<? super T> fVar) {
        a.a((Object) fVar, "s is null");
        try {
            aar a = xk.a(this, (aar) fVar);
            a.a((Object) a, "The RxJavaPlugins.onSubscribe hook returned a null FlowableSubscriber. Please check the handler provided to RxJavaPlugins.setOnFlowableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            b(a);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            xk.a(th);
            new NullPointerException("Actually not, but can't throw other exceptions due to RS").initCause(th);
        }
    }

    public final <U> e<T> a(aaq<U> aaq) {
        a.a((Object) aaq, "other is null");
        return xk.a(new FlowableTakeUntil(this, aaq));
    }
}
