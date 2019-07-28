package io.reactivex;

import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import com.tomatolive.library.ui.view.widget.matisse.internal.loader.AlbumLoader;
import defpackage.wf;
import defpackage.wg;
import defpackage.wh;
import defpackage.wi;
import defpackage.wj;
import defpackage.wl;
import defpackage.wm;
import defpackage.wn;
import defpackage.wo;
import defpackage.wp;
import defpackage.wq;
import defpackage.wr;
import defpackage.ws;
import defpackage.wt;
import defpackage.wv;
import defpackage.xb;
import defpackage.xi;
import defpackage.xj;
import defpackage.xk;
import defpackage.xl;
import defpackage.xm;
import io.reactivex.disposables.b;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.observers.ForEachWhileObserver;
import io.reactivex.internal.observers.LambdaObserver;
import io.reactivex.internal.observers.d;
import io.reactivex.internal.observers.e;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureError;
import io.reactivex.internal.operators.mixed.ObservableConcatMapCompletable;
import io.reactivex.internal.operators.mixed.ObservableConcatMapMaybe;
import io.reactivex.internal.operators.mixed.ObservableConcatMapSingle;
import io.reactivex.internal.operators.mixed.ObservableSwitchMapCompletable;
import io.reactivex.internal.operators.mixed.ObservableSwitchMapMaybe;
import io.reactivex.internal.operators.mixed.ObservableSwitchMapSingle;
import io.reactivex.internal.operators.observable.BlockingObservableIterable;
import io.reactivex.internal.operators.observable.ObservableAmb;
import io.reactivex.internal.operators.observable.ObservableBuffer;
import io.reactivex.internal.operators.observable.ObservableBufferBoundary;
import io.reactivex.internal.operators.observable.ObservableCache;
import io.reactivex.internal.operators.observable.ObservableCombineLatest;
import io.reactivex.internal.operators.observable.ObservableConcatMap;
import io.reactivex.internal.operators.observable.ObservableConcatMapEager;
import io.reactivex.internal.operators.observable.ObservableConcatWithCompletable;
import io.reactivex.internal.operators.observable.ObservableConcatWithMaybe;
import io.reactivex.internal.operators.observable.ObservableConcatWithSingle;
import io.reactivex.internal.operators.observable.ObservableCreate;
import io.reactivex.internal.operators.observable.ObservableDebounceTimed;
import io.reactivex.internal.operators.observable.ObservableDoFinally;
import io.reactivex.internal.operators.observable.ObservableFlatMap;
import io.reactivex.internal.operators.observable.ObservableFlatMapCompletableCompletable;
import io.reactivex.internal.operators.observable.ObservableFlatMapMaybe;
import io.reactivex.internal.operators.observable.ObservableFlatMapSingle;
import io.reactivex.internal.operators.observable.ObservableGroupBy;
import io.reactivex.internal.operators.observable.ObservableGroupJoin;
import io.reactivex.internal.operators.observable.ObservableInterval;
import io.reactivex.internal.operators.observable.ObservableIntervalRange;
import io.reactivex.internal.operators.observable.ObservableJoin;
import io.reactivex.internal.operators.observable.ObservableMergeWithCompletable;
import io.reactivex.internal.operators.observable.ObservableMergeWithMaybe;
import io.reactivex.internal.operators.observable.ObservableMergeWithSingle;
import io.reactivex.internal.operators.observable.ObservableObserveOn;
import io.reactivex.internal.operators.observable.ObservablePublish;
import io.reactivex.internal.operators.observable.ObservablePublishSelector;
import io.reactivex.internal.operators.observable.ObservableRange;
import io.reactivex.internal.operators.observable.ObservableRangeLong;
import io.reactivex.internal.operators.observable.ObservableRepeat;
import io.reactivex.internal.operators.observable.ObservableRepeatUntil;
import io.reactivex.internal.operators.observable.ObservableRepeatWhen;
import io.reactivex.internal.operators.observable.ObservableReplay;
import io.reactivex.internal.operators.observable.ObservableRetryBiPredicate;
import io.reactivex.internal.operators.observable.ObservableRetryPredicate;
import io.reactivex.internal.operators.observable.ObservableRetryWhen;
import io.reactivex.internal.operators.observable.ObservableSampleTimed;
import io.reactivex.internal.operators.observable.ObservableSampleWithObservable;
import io.reactivex.internal.operators.observable.ObservableScalarXMap;
import io.reactivex.internal.operators.observable.ObservableSequenceEqualSingle;
import io.reactivex.internal.operators.observable.ObservableSkipLast;
import io.reactivex.internal.operators.observable.ObservableSkipLastTimed;
import io.reactivex.internal.operators.observable.ObservableSubscribeOn;
import io.reactivex.internal.operators.observable.ObservableSwitchMap;
import io.reactivex.internal.operators.observable.ObservableTakeLast;
import io.reactivex.internal.operators.observable.ObservableTakeLastTimed;
import io.reactivex.internal.operators.observable.ObservableTakeUntil;
import io.reactivex.internal.operators.observable.ObservableThrottleFirstTimed;
import io.reactivex.internal.operators.observable.ObservableThrottleLatest;
import io.reactivex.internal.operators.observable.ObservableTimeout;
import io.reactivex.internal.operators.observable.ObservableTimeoutTimed;
import io.reactivex.internal.operators.observable.ObservableTimer;
import io.reactivex.internal.operators.observable.ObservableUnsubscribeOn;
import io.reactivex.internal.operators.observable.ObservableUsing;
import io.reactivex.internal.operators.observable.ObservableWindow;
import io.reactivex.internal.operators.observable.ObservableWindowBoundary;
import io.reactivex.internal.operators.observable.ObservableWindowBoundarySupplier;
import io.reactivex.internal.operators.observable.ObservableWithLatestFrom;
import io.reactivex.internal.operators.observable.ObservableWithLatestFromMany;
import io.reactivex.internal.operators.observable.ObservableZip;
import io.reactivex.internal.operators.observable.aa;
import io.reactivex.internal.operators.observable.ac;
import io.reactivex.internal.operators.observable.ad;
import io.reactivex.internal.operators.observable.ae;
import io.reactivex.internal.operators.observable.af;
import io.reactivex.internal.operators.observable.ag;
import io.reactivex.internal.operators.observable.ah;
import io.reactivex.internal.operators.observable.ai;
import io.reactivex.internal.operators.observable.aj;
import io.reactivex.internal.operators.observable.ak;
import io.reactivex.internal.operators.observable.al;
import io.reactivex.internal.operators.observable.am;
import io.reactivex.internal.operators.observable.an;
import io.reactivex.internal.operators.observable.ao;
import io.reactivex.internal.operators.observable.ap;
import io.reactivex.internal.operators.observable.aq;
import io.reactivex.internal.operators.observable.ar;
import io.reactivex.internal.operators.observable.as;
import io.reactivex.internal.operators.observable.at;
import io.reactivex.internal.operators.observable.au;
import io.reactivex.internal.operators.observable.av;
import io.reactivex.internal.operators.observable.aw;
import io.reactivex.internal.operators.observable.ax;
import io.reactivex.internal.operators.observable.ay;
import io.reactivex.internal.operators.observable.az;
import io.reactivex.internal.operators.observable.ba;
import io.reactivex.internal.operators.observable.bb;
import io.reactivex.internal.operators.observable.bc;
import io.reactivex.internal.operators.observable.bd;
import io.reactivex.internal.operators.observable.be;
import io.reactivex.internal.operators.observable.bf;
import io.reactivex.internal.operators.observable.bg;
import io.reactivex.internal.operators.observable.bh;
import io.reactivex.internal.operators.observable.bi;
import io.reactivex.internal.operators.observable.bj;
import io.reactivex.internal.operators.observable.bk;
import io.reactivex.internal.operators.observable.bl;
import io.reactivex.internal.operators.observable.bm;
import io.reactivex.internal.operators.observable.bn;
import io.reactivex.internal.operators.observable.bo;
import io.reactivex.internal.operators.observable.bp;
import io.reactivex.internal.operators.observable.bq;
import io.reactivex.internal.operators.observable.br;
import io.reactivex.internal.operators.observable.bs;
import io.reactivex.internal.operators.observable.bt;
import io.reactivex.internal.operators.observable.bu;
import io.reactivex.internal.operators.observable.bv;
import io.reactivex.internal.operators.observable.bw;
import io.reactivex.internal.operators.observable.bx;
import io.reactivex.internal.operators.observable.by;
import io.reactivex.internal.operators.observable.c;
import io.reactivex.internal.operators.observable.f;
import io.reactivex.internal.operators.observable.h;
import io.reactivex.internal.operators.observable.i;
import io.reactivex.internal.operators.observable.j;
import io.reactivex.internal.operators.observable.l;
import io.reactivex.internal.operators.observable.n;
import io.reactivex.internal.operators.observable.p;
import io.reactivex.internal.operators.observable.q;
import io.reactivex.internal.operators.observable.r;
import io.reactivex.internal.operators.observable.s;
import io.reactivex.internal.operators.observable.t;
import io.reactivex.internal.operators.observable.u;
import io.reactivex.internal.operators.observable.v;
import io.reactivex.internal.operators.observable.w;
import io.reactivex.internal.operators.observable.x;
import io.reactivex.internal.operators.observable.y;
import io.reactivex.internal.operators.observable.z;
import io.reactivex.internal.util.ArrayListSupplier;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.HashMapSupplier;
import io.reactivex.observers.TestObserver;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* compiled from: Observable */
public abstract class k<T> implements p<T> {
    public abstract void subscribeActual(r<? super T> rVar);

    public static <T> k<T> amb(Iterable<? extends p<? extends T>> iterable) {
        a.a((Object) iterable, "sources is null");
        return xk.a(new ObservableAmb(null, iterable));
    }

    public static <T> k<T> ambArray(p<? extends T>... pVarArr) {
        a.a((Object) pVarArr, "sources is null");
        int length = pVarArr.length;
        if (length == 0) {
            return empty();
        }
        if (length == 1) {
            return wrap(pVarArr[0]);
        }
        return xk.a(new ObservableAmb(pVarArr, null));
    }

    public static int bufferSize() {
        return e.a();
    }

    public static <T, R> k<R> combineLatest(wm<? super Object[], ? extends R> wmVar, int i, p<? extends T>... pVarArr) {
        return combineLatest((p[]) pVarArr, (wm) wmVar, i);
    }

    public static <T, R> k<R> combineLatest(Iterable<? extends p<? extends T>> iterable, wm<? super Object[], ? extends R> wmVar) {
        return combineLatest((Iterable) iterable, (wm) wmVar, bufferSize());
    }

    public static <T, R> k<R> combineLatest(Iterable<? extends p<? extends T>> iterable, wm<? super Object[], ? extends R> wmVar, int i) {
        a.a((Object) iterable, "sources is null");
        a.a((Object) wmVar, "combiner is null");
        a.a(i, "bufferSize");
        return xk.a(new ObservableCombineLatest(null, iterable, wmVar, i << 1, false));
    }

    public static <T, R> k<R> combineLatest(p<? extends T>[] pVarArr, wm<? super Object[], ? extends R> wmVar) {
        return combineLatest((p[]) pVarArr, (wm) wmVar, bufferSize());
    }

    public static <T, R> k<R> combineLatest(p<? extends T>[] pVarArr, wm<? super Object[], ? extends R> wmVar, int i) {
        a.a((Object) pVarArr, "sources is null");
        if (pVarArr.length == 0) {
            return empty();
        }
        a.a((Object) wmVar, "combiner is null");
        a.a(i, "bufferSize");
        return xk.a(new ObservableCombineLatest(pVarArr, null, wmVar, i << 1, false));
    }

    public static <T1, T2, R> k<R> combineLatest(p<? extends T1> pVar, p<? extends T2> pVar2, wh<? super T1, ? super T2, ? extends R> whVar) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        return combineLatest(Functions.a((wh) whVar), bufferSize(), pVar, pVar2);
    }

    public static <T1, T2, T3, R> k<R> combineLatest(p<? extends T1> pVar, p<? extends T2> pVar2, p<? extends T3> pVar3, wn<? super T1, ? super T2, ? super T3, ? extends R> wnVar) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        return combineLatest(Functions.a((wn) wnVar), bufferSize(), pVar, pVar2, pVar3);
    }

    public static <T1, T2, T3, T4, R> k<R> combineLatest(p<? extends T1> pVar, p<? extends T2> pVar2, p<? extends T3> pVar3, p<? extends T4> pVar4, wo<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> woVar) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        a.a((Object) pVar4, "source4 is null");
        return combineLatest(Functions.a((wo) woVar), bufferSize(), pVar, pVar2, pVar3, pVar4);
    }

    public static <T1, T2, T3, T4, T5, R> k<R> combineLatest(p<? extends T1> pVar, p<? extends T2> pVar2, p<? extends T3> pVar3, p<? extends T4> pVar4, p<? extends T5> pVar5, wp<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> wpVar) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        a.a((Object) pVar4, "source4 is null");
        a.a((Object) pVar5, "source5 is null");
        return combineLatest(Functions.a((wp) wpVar), bufferSize(), pVar, pVar2, pVar3, pVar4, pVar5);
    }

    public static <T1, T2, T3, T4, T5, T6, R> k<R> combineLatest(p<? extends T1> pVar, p<? extends T2> pVar2, p<? extends T3> pVar3, p<? extends T4> pVar4, p<? extends T5> pVar5, p<? extends T6> pVar6, wq<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> wqVar) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        a.a((Object) pVar4, "source4 is null");
        a.a((Object) pVar5, "source5 is null");
        a.a((Object) pVar6, "source6 is null");
        return combineLatest(Functions.a((wq) wqVar), bufferSize(), pVar, pVar2, pVar3, pVar4, pVar5, pVar6);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> k<R> combineLatest(p<? extends T1> pVar, p<? extends T2> pVar2, p<? extends T3> pVar3, p<? extends T4> pVar4, p<? extends T5> pVar5, p<? extends T6> pVar6, p<? extends T7> pVar7, wr<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> wrVar) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        a.a((Object) pVar4, "source4 is null");
        a.a((Object) pVar5, "source5 is null");
        a.a((Object) pVar6, "source6 is null");
        a.a((Object) pVar7, "source7 is null");
        return combineLatest(Functions.a((wr) wrVar), bufferSize(), pVar, pVar2, pVar3, pVar4, pVar5, pVar6, pVar7);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> k<R> combineLatest(p<? extends T1> pVar, p<? extends T2> pVar2, p<? extends T3> pVar3, p<? extends T4> pVar4, p<? extends T5> pVar5, p<? extends T6> pVar6, p<? extends T7> pVar7, p<? extends T8> pVar8, ws<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> wsVar) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        a.a((Object) pVar4, "source4 is null");
        a.a((Object) pVar5, "source5 is null");
        a.a((Object) pVar6, "source6 is null");
        a.a((Object) pVar7, "source7 is null");
        a.a((Object) pVar8, "source8 is null");
        return combineLatest(Functions.a((ws) wsVar), bufferSize(), pVar, pVar2, pVar3, pVar4, pVar5, pVar6, pVar7, pVar8);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> k<R> combineLatest(p<? extends T1> pVar, p<? extends T2> pVar2, p<? extends T3> pVar3, p<? extends T4> pVar4, p<? extends T5> pVar5, p<? extends T6> pVar6, p<? extends T7> pVar7, p<? extends T8> pVar8, p<? extends T9> pVar9, wt<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> wtVar) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        a.a((Object) pVar4, "source4 is null");
        a.a((Object) pVar5, "source5 is null");
        a.a((Object) pVar6, "source6 is null");
        a.a((Object) pVar7, "source7 is null");
        a.a((Object) pVar8, "source8 is null");
        a.a((Object) pVar9, "source9 is null");
        return combineLatest(Functions.a((wt) wtVar), bufferSize(), pVar, pVar2, pVar3, pVar4, pVar5, pVar6, pVar7, pVar8, pVar9);
    }

    public static <T, R> k<R> combineLatestDelayError(p<? extends T>[] pVarArr, wm<? super Object[], ? extends R> wmVar) {
        return combineLatestDelayError((p[]) pVarArr, (wm) wmVar, bufferSize());
    }

    public static <T, R> k<R> combineLatestDelayError(wm<? super Object[], ? extends R> wmVar, int i, p<? extends T>... pVarArr) {
        return combineLatestDelayError((p[]) pVarArr, (wm) wmVar, i);
    }

    public static <T, R> k<R> combineLatestDelayError(p<? extends T>[] pVarArr, wm<? super Object[], ? extends R> wmVar, int i) {
        a.a(i, "bufferSize");
        a.a((Object) wmVar, "combiner is null");
        if (pVarArr.length == 0) {
            return empty();
        }
        return xk.a(new ObservableCombineLatest(pVarArr, null, wmVar, i << 1, true));
    }

    public static <T, R> k<R> combineLatestDelayError(Iterable<? extends p<? extends T>> iterable, wm<? super Object[], ? extends R> wmVar) {
        return combineLatestDelayError((Iterable) iterable, (wm) wmVar, bufferSize());
    }

    public static <T, R> k<R> combineLatestDelayError(Iterable<? extends p<? extends T>> iterable, wm<? super Object[], ? extends R> wmVar, int i) {
        a.a((Object) iterable, "sources is null");
        a.a((Object) wmVar, "combiner is null");
        a.a(i, "bufferSize");
        return xk.a(new ObservableCombineLatest(null, iterable, wmVar, i << 1, true));
    }

    public static <T> k<T> concat(Iterable<? extends p<? extends T>> iterable) {
        a.a((Object) iterable, "sources is null");
        return fromIterable(iterable).concatMapDelayError(Functions.a(), bufferSize(), false);
    }

    public static <T> k<T> concat(p<? extends p<? extends T>> pVar) {
        return concat((p) pVar, bufferSize());
    }

    public static <T> k<T> concat(p<? extends p<? extends T>> pVar, int i) {
        a.a((Object) pVar, "sources is null");
        a.a(i, "prefetch");
        return xk.a(new ObservableConcatMap(pVar, Functions.a(), i, ErrorMode.IMMEDIATE));
    }

    public static <T> k<T> concat(p<? extends T> pVar, p<? extends T> pVar2) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        return concatArray(pVar, pVar2);
    }

    public static <T> k<T> concat(p<? extends T> pVar, p<? extends T> pVar2, p<? extends T> pVar3) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        return concatArray(pVar, pVar2, pVar3);
    }

    public static <T> k<T> concat(p<? extends T> pVar, p<? extends T> pVar2, p<? extends T> pVar3, p<? extends T> pVar4) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        a.a((Object) pVar4, "source4 is null");
        return concatArray(pVar, pVar2, pVar3, pVar4);
    }

    public static <T> k<T> concatArray(p<? extends T>... pVarArr) {
        if (pVarArr.length == 0) {
            return empty();
        }
        if (pVarArr.length == 1) {
            return wrap(pVarArr[0]);
        }
        return xk.a(new ObservableConcatMap(fromArray(pVarArr), Functions.a(), bufferSize(), ErrorMode.BOUNDARY));
    }

    public static <T> k<T> concatArrayDelayError(p<? extends T>... pVarArr) {
        if (pVarArr.length == 0) {
            return empty();
        }
        if (pVarArr.length == 1) {
            return wrap(pVarArr[0]);
        }
        return concatDelayError(fromArray(pVarArr));
    }

    public static <T> k<T> concatArrayEager(p<? extends T>... pVarArr) {
        return concatArrayEager(bufferSize(), bufferSize(), pVarArr);
    }

    public static <T> k<T> concatArrayEager(int i, int i2, p<? extends T>... pVarArr) {
        return fromArray(pVarArr).concatMapEagerDelayError(Functions.a(), i, i2, false);
    }

    public static <T> k<T> concatArrayEagerDelayError(p<? extends T>... pVarArr) {
        return concatArrayEagerDelayError(bufferSize(), bufferSize(), pVarArr);
    }

    public static <T> k<T> concatArrayEagerDelayError(int i, int i2, p<? extends T>... pVarArr) {
        return fromArray(pVarArr).concatMapEagerDelayError(Functions.a(), i, i2, true);
    }

    public static <T> k<T> concatDelayError(Iterable<? extends p<? extends T>> iterable) {
        a.a((Object) iterable, "sources is null");
        return concatDelayError(fromIterable(iterable));
    }

    public static <T> k<T> concatDelayError(p<? extends p<? extends T>> pVar) {
        return concatDelayError(pVar, bufferSize(), true);
    }

    public static <T> k<T> concatDelayError(p<? extends p<? extends T>> pVar, int i, boolean z) {
        a.a((Object) pVar, "sources is null");
        a.a(i, "prefetch is null");
        return xk.a(new ObservableConcatMap(pVar, Functions.a(), i, z ? ErrorMode.END : ErrorMode.BOUNDARY));
    }

    public static <T> k<T> concatEager(p<? extends p<? extends T>> pVar) {
        return concatEager((p) pVar, bufferSize(), bufferSize());
    }

    public static <T> k<T> concatEager(p<? extends p<? extends T>> pVar, int i, int i2) {
        return wrap(pVar).concatMapEager(Functions.a(), i, i2);
    }

    public static <T> k<T> concatEager(Iterable<? extends p<? extends T>> iterable) {
        return concatEager((Iterable) iterable, bufferSize(), bufferSize());
    }

    public static <T> k<T> concatEager(Iterable<? extends p<? extends T>> iterable, int i, int i2) {
        return fromIterable(iterable).concatMapEagerDelayError(Functions.a(), i, i2, false);
    }

    public static <T> k<T> create(n<T> nVar) {
        a.a((Object) nVar, "source is null");
        return xk.a(new ObservableCreate(nVar));
    }

    public static <T> k<T> defer(Callable<? extends p<? extends T>> callable) {
        a.a((Object) callable, "supplier is null");
        return xk.a(new r(callable));
    }

    public static <T> k<T> empty() {
        return xk.a(ae.a);
    }

    public static <T> k<T> error(Callable<? extends Throwable> callable) {
        a.a((Object) callable, "errorSupplier is null");
        return xk.a(new af(callable));
    }

    public static <T> k<T> error(Throwable th) {
        a.a((Object) th, "e is null");
        return error(Functions.a((Object) th));
    }

    public static <T> k<T> fromArray(T... tArr) {
        a.a((Object) tArr, "items is null");
        if (tArr.length == 0) {
            return empty();
        }
        if (tArr.length == 1) {
            return just(tArr[0]);
        }
        return xk.a(new ai(tArr));
    }

    public static <T> k<T> fromCallable(Callable<? extends T> callable) {
        a.a((Object) callable, "supplier is null");
        return xk.a(new aj(callable));
    }

    public static <T> k<T> fromFuture(Future<? extends T> future) {
        a.a((Object) future, "future is null");
        return xk.a(new ak(future, 0, null));
    }

    public static <T> k<T> fromFuture(Future<? extends T> future, long j, TimeUnit timeUnit) {
        a.a((Object) future, "future is null");
        a.a((Object) timeUnit, "unit is null");
        return xk.a(new ak(future, j, timeUnit));
    }

    public static <T> k<T> fromFuture(Future<? extends T> future, long j, TimeUnit timeUnit, s sVar) {
        a.a((Object) sVar, "scheduler is null");
        return fromFuture(future, j, timeUnit).subscribeOn(sVar);
    }

    public static <T> k<T> fromFuture(Future<? extends T> future, s sVar) {
        a.a((Object) sVar, "scheduler is null");
        return fromFuture(future).subscribeOn(sVar);
    }

    public static <T> k<T> fromIterable(Iterable<? extends T> iterable) {
        a.a((Object) iterable, "source is null");
        return xk.a(new al(iterable));
    }

    public static <T> k<T> fromPublisher(aaq<? extends T> aaq) {
        a.a((Object) aaq, "publisher is null");
        return xk.a(new am(aaq));
    }

    public static <T> k<T> generate(wl<d<T>> wlVar) {
        a.a((Object) wlVar, "generator  is null");
        return generate(Functions.e(), as.a((wl) wlVar), Functions.b());
    }

    public static <T, S> k<T> generate(Callable<S> callable, wg<S, d<T>> wgVar) {
        a.a((Object) wgVar, "generator  is null");
        return generate((Callable) callable, as.a((wg) wgVar), Functions.b());
    }

    public static <T, S> k<T> generate(Callable<S> callable, wg<S, d<T>> wgVar, wl<? super S> wlVar) {
        a.a((Object) wgVar, "generator  is null");
        return generate((Callable) callable, as.a((wg) wgVar), (wl) wlVar);
    }

    public static <T, S> k<T> generate(Callable<S> callable, wh<S, d<T>, S> whVar) {
        return generate((Callable) callable, (wh) whVar, Functions.b());
    }

    public static <T, S> k<T> generate(Callable<S> callable, wh<S, d<T>, S> whVar, wl<? super S> wlVar) {
        a.a((Object) callable, "initialState is null");
        a.a((Object) whVar, "generator  is null");
        a.a((Object) wlVar, "disposeState is null");
        return xk.a(new ao(callable, whVar, wlVar));
    }

    public static k<Long> interval(long j, long j2, TimeUnit timeUnit) {
        return interval(j, j2, timeUnit, xl.a());
    }

    public static k<Long> interval(long j, long j2, TimeUnit timeUnit, s sVar) {
        a.a((Object) timeUnit, "unit is null");
        a.a((Object) sVar, "scheduler is null");
        return xk.a(new ObservableInterval(Math.max(0, j), Math.max(0, j2), timeUnit, sVar));
    }

    public static k<Long> interval(long j, TimeUnit timeUnit) {
        return interval(j, j, timeUnit, xl.a());
    }

    public static k<Long> interval(long j, TimeUnit timeUnit, s sVar) {
        return interval(j, j, timeUnit, sVar);
    }

    public static k<Long> intervalRange(long j, long j2, long j3, long j4, TimeUnit timeUnit) {
        return intervalRange(j, j2, j3, j4, timeUnit, xl.a());
    }

    public static k<Long> intervalRange(long j, long j2, long j3, long j4, TimeUnit timeUnit, s sVar) {
        long j5 = j2;
        long j6 = j3;
        Object obj = timeUnit;
        Object obj2 = sVar;
        if (j5 < 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("count >= 0 required but it was ");
            stringBuilder.append(j5);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (j5 == 0) {
            return empty().delay(j6, (TimeUnit) obj, (s) obj2);
        } else {
            j5 = (j5 - 1) + j;
            if (j <= 0 || j5 >= 0) {
                a.a(obj, "unit is null");
                a.a(obj2, "scheduler is null");
                return xk.a(new ObservableIntervalRange(j, j5, Math.max(0, j6), Math.max(0, j4), timeUnit, sVar));
            }
            throw new IllegalArgumentException("Overflow! start + count is bigger than Long.MAX_VALUE");
        }
    }

    public static <T> k<T> just(T t) {
        a.a((Object) t, "The item is null");
        return xk.a(new at(t));
    }

    public static <T> k<T> just(T t, T t2) {
        a.a((Object) t, "The first item is null");
        a.a((Object) t2, "The second item is null");
        return fromArray(t, t2);
    }

    public static <T> k<T> just(T t, T t2, T t3) {
        a.a((Object) t, "The first item is null");
        a.a((Object) t2, "The second item is null");
        a.a((Object) t3, "The third item is null");
        return fromArray(t, t2, t3);
    }

    public static <T> k<T> just(T t, T t2, T t3, T t4) {
        a.a((Object) t, "The first item is null");
        a.a((Object) t2, "The second item is null");
        a.a((Object) t3, "The third item is null");
        a.a((Object) t4, "The fourth item is null");
        return fromArray(t, t2, t3, t4);
    }

    public static <T> k<T> just(T t, T t2, T t3, T t4, T t5) {
        a.a((Object) t, "The first item is null");
        a.a((Object) t2, "The second item is null");
        a.a((Object) t3, "The third item is null");
        a.a((Object) t4, "The fourth item is null");
        a.a((Object) t5, "The fifth item is null");
        return fromArray(t, t2, t3, t4, t5);
    }

    public static <T> k<T> just(T t, T t2, T t3, T t4, T t5, T t6) {
        a.a((Object) t, "The first item is null");
        a.a((Object) t2, "The second item is null");
        a.a((Object) t3, "The third item is null");
        a.a((Object) t4, "The fourth item is null");
        a.a((Object) t5, "The fifth item is null");
        a.a((Object) t6, "The sixth item is null");
        return fromArray(t, t2, t3, t4, t5, t6);
    }

    public static <T> k<T> just(T t, T t2, T t3, T t4, T t5, T t6, T t7) {
        a.a((Object) t, "The first item is null");
        a.a((Object) t2, "The second item is null");
        a.a((Object) t3, "The third item is null");
        a.a((Object) t4, "The fourth item is null");
        a.a((Object) t5, "The fifth item is null");
        a.a((Object) t6, "The sixth item is null");
        a.a((Object) t7, "The seventh item is null");
        return fromArray(t, t2, t3, t4, t5, t6, t7);
    }

    public static <T> k<T> just(T t, T t2, T t3, T t4, T t5, T t6, T t7, T t8) {
        a.a((Object) t, "The first item is null");
        a.a((Object) t2, "The second item is null");
        a.a((Object) t3, "The third item is null");
        a.a((Object) t4, "The fourth item is null");
        a.a((Object) t5, "The fifth item is null");
        a.a((Object) t6, "The sixth item is null");
        a.a((Object) t7, "The seventh item is null");
        a.a((Object) t8, "The eighth item is null");
        return fromArray(t, t2, t3, t4, t5, t6, t7, t8);
    }

    public static <T> k<T> just(T t, T t2, T t3, T t4, T t5, T t6, T t7, T t8, T t9) {
        a.a((Object) t, "The first item is null");
        a.a((Object) t2, "The second item is null");
        a.a((Object) t3, "The third item is null");
        a.a((Object) t4, "The fourth item is null");
        a.a((Object) t5, "The fifth item is null");
        a.a((Object) t6, "The sixth item is null");
        a.a((Object) t7, "The seventh item is null");
        a.a((Object) t8, "The eighth item is null");
        a.a((Object) t9, "The ninth item is null");
        return fromArray(t, t2, t3, t4, t5, t6, t7, t8, t9);
    }

    public static <T> k<T> just(T t, T t2, T t3, T t4, T t5, T t6, T t7, T t8, T t9, T t10) {
        a.a((Object) t, "The first item is null");
        a.a((Object) t2, "The second item is null");
        a.a((Object) t3, "The third item is null");
        a.a((Object) t4, "The fourth item is null");
        a.a((Object) t5, "The fifth item is null");
        a.a((Object) t6, "The sixth item is null");
        a.a((Object) t7, "The seventh item is null");
        a.a((Object) t8, "The eighth item is null");
        a.a((Object) t9, "The ninth item is null");
        a.a((Object) t10, "The tenth item is null");
        return fromArray(t, t2, t3, t4, t5, t6, t7, t8, t9, t10);
    }

    public static <T> k<T> merge(Iterable<? extends p<? extends T>> iterable, int i, int i2) {
        return fromIterable(iterable).flatMap(Functions.a(), false, i, i2);
    }

    public static <T> k<T> mergeArray(int i, int i2, p<? extends T>... pVarArr) {
        return fromArray(pVarArr).flatMap(Functions.a(), false, i, i2);
    }

    public static <T> k<T> merge(Iterable<? extends p<? extends T>> iterable) {
        return fromIterable(iterable).flatMap(Functions.a());
    }

    public static <T> k<T> merge(Iterable<? extends p<? extends T>> iterable, int i) {
        return fromIterable(iterable).flatMap(Functions.a(), i);
    }

    public static <T> k<T> merge(p<? extends p<? extends T>> pVar) {
        a.a((Object) pVar, "sources is null");
        return xk.a(new ObservableFlatMap(pVar, Functions.a(), false, Filter.MAX, bufferSize()));
    }

    public static <T> k<T> merge(p<? extends p<? extends T>> pVar, int i) {
        a.a((Object) pVar, "sources is null");
        a.a(i, "maxConcurrency");
        return xk.a(new ObservableFlatMap(pVar, Functions.a(), false, i, bufferSize()));
    }

    public static <T> k<T> merge(p<? extends T> pVar, p<? extends T> pVar2) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        return fromArray(pVar, pVar2).flatMap(Functions.a(), false, 2);
    }

    public static <T> k<T> merge(p<? extends T> pVar, p<? extends T> pVar2, p<? extends T> pVar3) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        return fromArray(pVar, pVar2, pVar3).flatMap(Functions.a(), false, 3);
    }

    public static <T> k<T> merge(p<? extends T> pVar, p<? extends T> pVar2, p<? extends T> pVar3, p<? extends T> pVar4) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        a.a((Object) pVar4, "source4 is null");
        return fromArray(pVar, pVar2, pVar3, pVar4).flatMap(Functions.a(), false, 4);
    }

    public static <T> k<T> mergeArray(p<? extends T>... pVarArr) {
        return fromArray(pVarArr).flatMap(Functions.a(), pVarArr.length);
    }

    public static <T> k<T> mergeDelayError(Iterable<? extends p<? extends T>> iterable) {
        return fromIterable(iterable).flatMap(Functions.a(), true);
    }

    public static <T> k<T> mergeDelayError(Iterable<? extends p<? extends T>> iterable, int i, int i2) {
        return fromIterable(iterable).flatMap(Functions.a(), true, i, i2);
    }

    public static <T> k<T> mergeArrayDelayError(int i, int i2, p<? extends T>... pVarArr) {
        return fromArray(pVarArr).flatMap(Functions.a(), true, i, i2);
    }

    public static <T> k<T> mergeDelayError(Iterable<? extends p<? extends T>> iterable, int i) {
        return fromIterable(iterable).flatMap(Functions.a(), true, i);
    }

    public static <T> k<T> mergeDelayError(p<? extends p<? extends T>> pVar) {
        a.a((Object) pVar, "sources is null");
        return xk.a(new ObservableFlatMap(pVar, Functions.a(), true, Filter.MAX, bufferSize()));
    }

    public static <T> k<T> mergeDelayError(p<? extends p<? extends T>> pVar, int i) {
        a.a((Object) pVar, "sources is null");
        a.a(i, "maxConcurrency");
        return xk.a(new ObservableFlatMap(pVar, Functions.a(), true, i, bufferSize()));
    }

    public static <T> k<T> mergeDelayError(p<? extends T> pVar, p<? extends T> pVar2) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        return fromArray(pVar, pVar2).flatMap(Functions.a(), true, 2);
    }

    public static <T> k<T> mergeDelayError(p<? extends T> pVar, p<? extends T> pVar2, p<? extends T> pVar3) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        return fromArray(pVar, pVar2, pVar3).flatMap(Functions.a(), true, 3);
    }

    public static <T> k<T> mergeDelayError(p<? extends T> pVar, p<? extends T> pVar2, p<? extends T> pVar3, p<? extends T> pVar4) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        a.a((Object) pVar4, "source4 is null");
        return fromArray(pVar, pVar2, pVar3, pVar4).flatMap(Functions.a(), true, 4);
    }

    public static <T> k<T> mergeArrayDelayError(p<? extends T>... pVarArr) {
        return fromArray(pVarArr).flatMap(Functions.a(), true, pVarArr.length);
    }

    public static <T> k<T> never() {
        return xk.a(ba.a);
    }

    public static k<Integer> range(int i, int i2) {
        if (i2 < 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("count >= 0 required but it was ");
            stringBuilder.append(i2);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (i2 == 0) {
            return empty();
        } else {
            if (i2 == 1) {
                return just(Integer.valueOf(i));
            }
            if (((long) i) + ((long) (i2 - 1)) <= 2147483647L) {
                return xk.a(new ObservableRange(i, i2));
            }
            throw new IllegalArgumentException("Integer overflow");
        }
    }

    public static k<Long> rangeLong(long j, long j2) {
        if (j2 < 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("count >= 0 required but it was ");
            stringBuilder.append(j2);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (j2 == 0) {
            return empty();
        } else {
            if (j2 == 1) {
                return just(Long.valueOf(j));
            }
            long j3 = (j2 - 1) + j;
            if (j <= 0 || j3 >= 0) {
                return xk.a(new ObservableRangeLong(j, j2));
            }
            throw new IllegalArgumentException("Overflow! start + count is bigger than Long.MAX_VALUE");
        }
    }

    public static <T> t<Boolean> sequenceEqual(p<? extends T> pVar, p<? extends T> pVar2) {
        return sequenceEqual(pVar, pVar2, a.a(), bufferSize());
    }

    public static <T> t<Boolean> sequenceEqual(p<? extends T> pVar, p<? extends T> pVar2, wi<? super T, ? super T> wiVar) {
        return sequenceEqual(pVar, pVar2, wiVar, bufferSize());
    }

    public static <T> t<Boolean> sequenceEqual(p<? extends T> pVar, p<? extends T> pVar2, wi<? super T, ? super T> wiVar, int i) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) wiVar, "isEqual is null");
        a.a(i, "bufferSize");
        return xk.a(new ObservableSequenceEqualSingle(pVar, pVar2, wiVar, i));
    }

    public static <T> t<Boolean> sequenceEqual(p<? extends T> pVar, p<? extends T> pVar2, int i) {
        return sequenceEqual(pVar, pVar2, a.a(), i);
    }

    public static <T> k<T> switchOnNext(p<? extends p<? extends T>> pVar, int i) {
        a.a((Object) pVar, "sources is null");
        a.a(i, "bufferSize");
        return xk.a(new ObservableSwitchMap(pVar, Functions.a(), i, false));
    }

    public static <T> k<T> switchOnNext(p<? extends p<? extends T>> pVar) {
        return switchOnNext(pVar, bufferSize());
    }

    public static <T> k<T> switchOnNextDelayError(p<? extends p<? extends T>> pVar) {
        return switchOnNextDelayError(pVar, bufferSize());
    }

    public static <T> k<T> switchOnNextDelayError(p<? extends p<? extends T>> pVar, int i) {
        a.a((Object) pVar, "sources is null");
        a.a(i, "prefetch");
        return xk.a(new ObservableSwitchMap(pVar, Functions.a(), i, true));
    }

    public static k<Long> timer(long j, TimeUnit timeUnit) {
        return timer(j, timeUnit, xl.a());
    }

    public static k<Long> timer(long j, TimeUnit timeUnit, s sVar) {
        a.a((Object) timeUnit, "unit is null");
        a.a((Object) sVar, "scheduler is null");
        return xk.a(new ObservableTimer(Math.max(j, 0), timeUnit, sVar));
    }

    public static <T> k<T> unsafeCreate(p<T> pVar) {
        a.a((Object) pVar, "source is null");
        a.a((Object) pVar, "onSubscribe is null");
        if (!(pVar instanceof k)) {
            return xk.a(new an(pVar));
        }
        throw new IllegalArgumentException("unsafeCreate(Observable) should be upgraded");
    }

    public static <T, D> k<T> using(Callable<? extends D> callable, wm<? super D, ? extends p<? extends T>> wmVar, wl<? super D> wlVar) {
        return using(callable, wmVar, wlVar, true);
    }

    public static <T, D> k<T> using(Callable<? extends D> callable, wm<? super D, ? extends p<? extends T>> wmVar, wl<? super D> wlVar, boolean z) {
        a.a((Object) callable, "resourceSupplier is null");
        a.a((Object) wmVar, "sourceSupplier is null");
        a.a((Object) wlVar, "disposer is null");
        return xk.a(new ObservableUsing(callable, wmVar, wlVar, z));
    }

    public static <T> k<T> wrap(p<T> pVar) {
        a.a((Object) pVar, "source is null");
        if (pVar instanceof k) {
            return xk.a((k) pVar);
        }
        return xk.a(new an(pVar));
    }

    public static <T, R> k<R> zip(Iterable<? extends p<? extends T>> iterable, wm<? super Object[], ? extends R> wmVar) {
        a.a((Object) wmVar, "zipper is null");
        a.a((Object) iterable, "sources is null");
        return xk.a(new ObservableZip(null, iterable, wmVar, bufferSize(), false));
    }

    public static <T, R> k<R> zip(p<? extends p<? extends T>> pVar, wm<? super Object[], ? extends R> wmVar) {
        a.a((Object) wmVar, "zipper is null");
        a.a((Object) pVar, "sources is null");
        return xk.a(new bu((p) pVar, 16).flatMap(as.c((wm) wmVar)));
    }

    public static <T1, T2, R> k<R> zip(p<? extends T1> pVar, p<? extends T2> pVar2, wh<? super T1, ? super T2, ? extends R> whVar) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        return zipArray(Functions.a((wh) whVar), false, bufferSize(), pVar, pVar2);
    }

    public static <T1, T2, R> k<R> zip(p<? extends T1> pVar, p<? extends T2> pVar2, wh<? super T1, ? super T2, ? extends R> whVar, boolean z) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        return zipArray(Functions.a((wh) whVar), z, bufferSize(), pVar, pVar2);
    }

    public static <T1, T2, R> k<R> zip(p<? extends T1> pVar, p<? extends T2> pVar2, wh<? super T1, ? super T2, ? extends R> whVar, boolean z, int i) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        return zipArray(Functions.a((wh) whVar), z, i, pVar, pVar2);
    }

    public static <T1, T2, T3, R> k<R> zip(p<? extends T1> pVar, p<? extends T2> pVar2, p<? extends T3> pVar3, wn<? super T1, ? super T2, ? super T3, ? extends R> wnVar) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        return zipArray(Functions.a((wn) wnVar), false, bufferSize(), pVar, pVar2, pVar3);
    }

    public static <T1, T2, T3, T4, R> k<R> zip(p<? extends T1> pVar, p<? extends T2> pVar2, p<? extends T3> pVar3, p<? extends T4> pVar4, wo<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> woVar) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        a.a((Object) pVar4, "source4 is null");
        return zipArray(Functions.a((wo) woVar), false, bufferSize(), pVar, pVar2, pVar3, pVar4);
    }

    public static <T1, T2, T3, T4, T5, R> k<R> zip(p<? extends T1> pVar, p<? extends T2> pVar2, p<? extends T3> pVar3, p<? extends T4> pVar4, p<? extends T5> pVar5, wp<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> wpVar) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        a.a((Object) pVar4, "source4 is null");
        a.a((Object) pVar5, "source5 is null");
        return zipArray(Functions.a((wp) wpVar), false, bufferSize(), pVar, pVar2, pVar3, pVar4, pVar5);
    }

    public static <T1, T2, T3, T4, T5, T6, R> k<R> zip(p<? extends T1> pVar, p<? extends T2> pVar2, p<? extends T3> pVar3, p<? extends T4> pVar4, p<? extends T5> pVar5, p<? extends T6> pVar6, wq<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> wqVar) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        a.a((Object) pVar4, "source4 is null");
        a.a((Object) pVar5, "source5 is null");
        a.a((Object) pVar6, "source6 is null");
        return zipArray(Functions.a((wq) wqVar), false, bufferSize(), pVar, pVar2, pVar3, pVar4, pVar5, pVar6);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> k<R> zip(p<? extends T1> pVar, p<? extends T2> pVar2, p<? extends T3> pVar3, p<? extends T4> pVar4, p<? extends T5> pVar5, p<? extends T6> pVar6, p<? extends T7> pVar7, wr<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> wrVar) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        a.a((Object) pVar4, "source4 is null");
        a.a((Object) pVar5, "source5 is null");
        a.a((Object) pVar6, "source6 is null");
        a.a((Object) pVar7, "source7 is null");
        return zipArray(Functions.a((wr) wrVar), false, bufferSize(), pVar, pVar2, pVar3, pVar4, pVar5, pVar6, pVar7);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> k<R> zip(p<? extends T1> pVar, p<? extends T2> pVar2, p<? extends T3> pVar3, p<? extends T4> pVar4, p<? extends T5> pVar5, p<? extends T6> pVar6, p<? extends T7> pVar7, p<? extends T8> pVar8, ws<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> wsVar) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        a.a((Object) pVar4, "source4 is null");
        a.a((Object) pVar5, "source5 is null");
        a.a((Object) pVar6, "source6 is null");
        a.a((Object) pVar7, "source7 is null");
        a.a((Object) pVar8, "source8 is null");
        return zipArray(Functions.a((ws) wsVar), false, bufferSize(), pVar, pVar2, pVar3, pVar4, pVar5, pVar6, pVar7, pVar8);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> k<R> zip(p<? extends T1> pVar, p<? extends T2> pVar2, p<? extends T3> pVar3, p<? extends T4> pVar4, p<? extends T5> pVar5, p<? extends T6> pVar6, p<? extends T7> pVar7, p<? extends T8> pVar8, p<? extends T9> pVar9, wt<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> wtVar) {
        a.a((Object) pVar, "source1 is null");
        a.a((Object) pVar2, "source2 is null");
        a.a((Object) pVar3, "source3 is null");
        a.a((Object) pVar4, "source4 is null");
        a.a((Object) pVar5, "source5 is null");
        a.a((Object) pVar6, "source6 is null");
        a.a((Object) pVar7, "source7 is null");
        a.a((Object) pVar8, "source8 is null");
        a.a((Object) pVar9, "source9 is null");
        return zipArray(Functions.a((wt) wtVar), false, bufferSize(), pVar, pVar2, pVar3, pVar4, pVar5, pVar6, pVar7, pVar8, pVar9);
    }

    public static <T, R> k<R> zipArray(wm<? super Object[], ? extends R> wmVar, boolean z, int i, p<? extends T>... pVarArr) {
        if (pVarArr.length == 0) {
            return empty();
        }
        a.a((Object) wmVar, "zipper is null");
        a.a(i, "bufferSize");
        return xk.a(new ObservableZip(pVarArr, null, wmVar, i, z));
    }

    public static <T, R> k<R> zipIterable(Iterable<? extends p<? extends T>> iterable, wm<? super Object[], ? extends R> wmVar, boolean z, int i) {
        a.a((Object) wmVar, "zipper is null");
        a.a((Object) iterable, "sources is null");
        a.a(i, "bufferSize");
        return xk.a(new ObservableZip(null, iterable, wmVar, i, z));
    }

    public final t<Boolean> all(wv<? super T> wvVar) {
        a.a((Object) wvVar, "predicate is null");
        return xk.a(new f(this, wvVar));
    }

    public final k<T> ambWith(p<? extends T> pVar) {
        a.a((Object) pVar, "other is null");
        return ambArray(this, pVar);
    }

    public final t<Boolean> any(wv<? super T> wvVar) {
        a.a((Object) wvVar, "predicate is null");
        return xk.a(new h(this, wvVar));
    }

    public final <R> R as(l<T, ? extends R> lVar) {
        return ((l) a.a((Object) lVar, "converter is null")).a(this);
    }

    public final T blockingFirst() {
        r dVar = new d();
        subscribe(dVar);
        Object a = dVar.a();
        if (a != null) {
            return a;
        }
        throw new NoSuchElementException();
    }

    public final T blockingFirst(T t) {
        r dVar = new d();
        subscribe(dVar);
        T a = dVar.a();
        return a != null ? a : t;
    }

    public final void blockingForEach(wl<? super T> wlVar) {
        Iterator it = blockingIterable().iterator();
        while (it.hasNext()) {
            try {
                wlVar.accept(it.next());
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                ((b) it).dispose();
                RuntimeException a = ExceptionHelper.a(th);
            }
        }
    }

    public final Iterable<T> blockingIterable() {
        return blockingIterable(bufferSize());
    }

    public final Iterable<T> blockingIterable(int i) {
        a.a(i, "bufferSize");
        return new BlockingObservableIterable(this, i);
    }

    public final T blockingLast() {
        r eVar = new e();
        subscribe(eVar);
        Object a = eVar.a();
        if (a != null) {
            return a;
        }
        throw new NoSuchElementException();
    }

    public final T blockingLast(T t) {
        r eVar = new e();
        subscribe(eVar);
        T a = eVar.a();
        return a != null ? a : t;
    }

    public final Iterable<T> blockingLatest() {
        return new io.reactivex.internal.operators.observable.b(this);
    }

    public final Iterable<T> blockingMostRecent(T t) {
        return new c(this, t);
    }

    public final Iterable<T> blockingNext() {
        return new io.reactivex.internal.operators.observable.d(this);
    }

    public final T blockingSingle() {
        Object a = singleElement().a();
        if (a != null) {
            return a;
        }
        throw new NoSuchElementException();
    }

    public final T blockingSingle(T t) {
        return single(t).a();
    }

    public final Future<T> toFuture() {
        return (Future) subscribeWith(new io.reactivex.internal.observers.h());
    }

    public final void blockingSubscribe() {
        i.a(this);
    }

    public final void blockingSubscribe(wl<? super T> wlVar) {
        i.a(this, wlVar, Functions.f, Functions.c);
    }

    public final void blockingSubscribe(wl<? super T> wlVar, wl<? super Throwable> wlVar2) {
        i.a(this, wlVar, wlVar2, Functions.c);
    }

    public final void blockingSubscribe(wl<? super T> wlVar, wl<? super Throwable> wlVar2, wf wfVar) {
        i.a(this, wlVar, wlVar2, wfVar);
    }

    public final void blockingSubscribe(r<? super T> rVar) {
        i.a(this, rVar);
    }

    public final k<List<T>> buffer(int i) {
        return buffer(i, i);
    }

    public final k<List<T>> buffer(int i, int i2) {
        return buffer(i, i2, ArrayListSupplier.asCallable());
    }

    public final <U extends Collection<? super T>> k<U> buffer(int i, int i2, Callable<U> callable) {
        a.a(i, AlbumLoader.COLUMN_COUNT);
        a.a(i2, "skip");
        a.a((Object) callable, "bufferSupplier is null");
        return xk.a(new ObservableBuffer(this, i, i2, callable));
    }

    public final <U extends Collection<? super T>> k<U> buffer(int i, Callable<U> callable) {
        return buffer(i, i, (Callable) callable);
    }

    public final k<List<T>> buffer(long j, long j2, TimeUnit timeUnit) {
        return buffer(j, j2, timeUnit, xl.a(), ArrayListSupplier.asCallable());
    }

    public final k<List<T>> buffer(long j, long j2, TimeUnit timeUnit, s sVar) {
        return buffer(j, j2, timeUnit, sVar, ArrayListSupplier.asCallable());
    }

    public final <U extends Collection<? super T>> k<U> buffer(long j, long j2, TimeUnit timeUnit, s sVar, Callable<U> callable) {
        Object obj = timeUnit;
        a.a(obj, "unit is null");
        Object obj2 = sVar;
        a.a(obj2, "scheduler is null");
        Object obj3 = callable;
        a.a(obj3, "bufferSupplier is null");
        return xk.a(new l(this, j, j2, obj, obj2, obj3, Filter.MAX, false));
    }

    public final k<List<T>> buffer(long j, TimeUnit timeUnit) {
        return buffer(j, timeUnit, xl.a(), (int) Filter.MAX);
    }

    public final k<List<T>> buffer(long j, TimeUnit timeUnit, int i) {
        return buffer(j, timeUnit, xl.a(), i);
    }

    public final k<List<T>> buffer(long j, TimeUnit timeUnit, s sVar, int i) {
        return buffer(j, timeUnit, sVar, i, ArrayListSupplier.asCallable(), false);
    }

    public final <U extends Collection<? super T>> k<U> buffer(long j, TimeUnit timeUnit, s sVar, int i, Callable<U> callable, boolean z) {
        TimeUnit timeUnit2 = timeUnit;
        a.a((Object) timeUnit, "unit is null");
        Object obj = sVar;
        a.a(obj, "scheduler is null");
        Object obj2 = callable;
        a.a(obj2, "bufferSupplier is null");
        int i2 = i;
        a.a(i2, AlbumLoader.COLUMN_COUNT);
        return xk.a(new l(this, j, j, timeUnit2, obj, obj2, i2, z));
    }

    public final k<List<T>> buffer(long j, TimeUnit timeUnit, s sVar) {
        return buffer(j, timeUnit, sVar, Filter.MAX, ArrayListSupplier.asCallable(), false);
    }

    public final <TOpening, TClosing> k<List<T>> buffer(p<? extends TOpening> pVar, wm<? super TOpening, ? extends p<? extends TClosing>> wmVar) {
        return buffer((p) pVar, (wm) wmVar, ArrayListSupplier.asCallable());
    }

    public final <TOpening, TClosing, U extends Collection<? super T>> k<U> buffer(p<? extends TOpening> pVar, wm<? super TOpening, ? extends p<? extends TClosing>> wmVar, Callable<U> callable) {
        a.a((Object) pVar, "openingIndicator is null");
        a.a((Object) wmVar, "closingIndicator is null");
        a.a((Object) callable, "bufferSupplier is null");
        return xk.a(new ObservableBufferBoundary(this, pVar, wmVar, callable));
    }

    public final <B> k<List<T>> buffer(p<B> pVar) {
        return buffer((p) pVar, ArrayListSupplier.asCallable());
    }

    public final <B> k<List<T>> buffer(p<B> pVar, int i) {
        a.a(i, "initialCapacity");
        return buffer((p) pVar, Functions.a(i));
    }

    public final <B, U extends Collection<? super T>> k<U> buffer(p<B> pVar, Callable<U> callable) {
        a.a((Object) pVar, "boundary is null");
        a.a((Object) callable, "bufferSupplier is null");
        return xk.a(new io.reactivex.internal.operators.observable.k(this, pVar, callable));
    }

    public final <B> k<List<T>> buffer(Callable<? extends p<B>> callable) {
        return buffer((Callable) callable, ArrayListSupplier.asCallable());
    }

    public final <B, U extends Collection<? super T>> k<U> buffer(Callable<? extends p<B>> callable, Callable<U> callable2) {
        a.a((Object) callable, "boundarySupplier is null");
        a.a((Object) callable2, "bufferSupplier is null");
        return xk.a(new j(this, callable, callable2));
    }

    public final k<T> cache() {
        return ObservableCache.a(this);
    }

    public final k<T> cacheWithInitialCapacity(int i) {
        return ObservableCache.a(this, i);
    }

    public final <U> k<U> cast(Class<U> cls) {
        a.a((Object) cls, "clazz is null");
        return map(Functions.a((Class) cls));
    }

    public final <U> t<U> collect(Callable<? extends U> callable, wg<? super U, ? super T> wgVar) {
        a.a((Object) callable, "initialValueSupplier is null");
        a.a((Object) wgVar, "collector is null");
        return xk.a(new n(this, callable, wgVar));
    }

    public final <U> t<U> collectInto(U u, wg<? super U, ? super T> wgVar) {
        a.a((Object) u, "initialValue is null");
        return collect(Functions.a((Object) u), wgVar);
    }

    public final <R> k<R> compose(q<? super T, ? extends R> qVar) {
        return wrap(((q) a.a((Object) qVar, "composer is null")).apply(this));
    }

    public final <R> k<R> concatMap(wm<? super T, ? extends p<? extends R>> wmVar) {
        return concatMap(wmVar, 2);
    }

    public final <R> k<R> concatMap(wm<? super T, ? extends p<? extends R>> wmVar, int i) {
        a.a((Object) wmVar, "mapper is null");
        a.a(i, "prefetch");
        if (!(this instanceof xb)) {
            return xk.a(new ObservableConcatMap(this, wmVar, i, ErrorMode.IMMEDIATE));
        }
        Object call = ((xb) this).call();
        if (call == null) {
            return empty();
        }
        return ObservableScalarXMap.a(call, wmVar);
    }

    public final <R> k<R> concatMapDelayError(wm<? super T, ? extends p<? extends R>> wmVar) {
        return concatMapDelayError(wmVar, bufferSize(), true);
    }

    public final <R> k<R> concatMapDelayError(wm<? super T, ? extends p<? extends R>> wmVar, int i, boolean z) {
        a.a((Object) wmVar, "mapper is null");
        a.a(i, "prefetch");
        if (this instanceof xb) {
            Object call = ((xb) this).call();
            if (call == null) {
                return empty();
            }
            return ObservableScalarXMap.a(call, wmVar);
        }
        return xk.a(new ObservableConcatMap(this, wmVar, i, z ? ErrorMode.END : ErrorMode.BOUNDARY));
    }

    public final <R> k<R> concatMapEager(wm<? super T, ? extends p<? extends R>> wmVar) {
        return concatMapEager(wmVar, Filter.MAX, bufferSize());
    }

    public final <R> k<R> concatMapEager(wm<? super T, ? extends p<? extends R>> wmVar, int i, int i2) {
        a.a((Object) wmVar, "mapper is null");
        a.a(i, "maxConcurrency");
        a.a(i2, "prefetch");
        return xk.a(new ObservableConcatMapEager(this, wmVar, ErrorMode.IMMEDIATE, i, i2));
    }

    public final <R> k<R> concatMapEagerDelayError(wm<? super T, ? extends p<? extends R>> wmVar, boolean z) {
        return concatMapEagerDelayError(wmVar, Filter.MAX, bufferSize(), z);
    }

    public final <R> k<R> concatMapEagerDelayError(wm<? super T, ? extends p<? extends R>> wmVar, int i, int i2, boolean z) {
        a.a((Object) wmVar, "mapper is null");
        a.a(i, "maxConcurrency");
        a.a(i2, "prefetch");
        return xk.a(new ObservableConcatMapEager(this, wmVar, z ? ErrorMode.END : ErrorMode.BOUNDARY, i, i2));
    }

    public final a concatMapCompletable(wm<? super T, ? extends c> wmVar) {
        return concatMapCompletable(wmVar, 2);
    }

    public final a concatMapCompletable(wm<? super T, ? extends c> wmVar, int i) {
        a.a((Object) wmVar, "mapper is null");
        a.a(i, "capacityHint");
        return xk.a(new ObservableConcatMapCompletable(this, wmVar, ErrorMode.IMMEDIATE, i));
    }

    public final a concatMapCompletableDelayError(wm<? super T, ? extends c> wmVar) {
        return concatMapCompletableDelayError(wmVar, true, 2);
    }

    public final a concatMapCompletableDelayError(wm<? super T, ? extends c> wmVar, boolean z) {
        return concatMapCompletableDelayError(wmVar, z, 2);
    }

    public final a concatMapCompletableDelayError(wm<? super T, ? extends c> wmVar, boolean z, int i) {
        a.a((Object) wmVar, "mapper is null");
        a.a(i, "prefetch");
        return xk.a(new ObservableConcatMapCompletable(this, wmVar, z ? ErrorMode.END : ErrorMode.BOUNDARY, i));
    }

    public final <U> k<U> concatMapIterable(wm<? super T, ? extends Iterable<? extends U>> wmVar) {
        a.a((Object) wmVar, "mapper is null");
        return xk.a(new ah(this, wmVar));
    }

    public final <U> k<U> concatMapIterable(wm<? super T, ? extends Iterable<? extends U>> wmVar, int i) {
        a.a((Object) wmVar, "mapper is null");
        a.a(i, "prefetch");
        return concatMap(as.b((wm) wmVar), i);
    }

    public final <R> k<R> concatMapMaybe(wm<? super T, ? extends i<? extends R>> wmVar) {
        return concatMapMaybe(wmVar, 2);
    }

    public final <R> k<R> concatMapMaybe(wm<? super T, ? extends i<? extends R>> wmVar, int i) {
        a.a((Object) wmVar, "mapper is null");
        a.a(i, "prefetch");
        return xk.a(new ObservableConcatMapMaybe(this, wmVar, ErrorMode.IMMEDIATE, i));
    }

    public final <R> k<R> concatMapMaybeDelayError(wm<? super T, ? extends i<? extends R>> wmVar) {
        return concatMapMaybeDelayError(wmVar, true, 2);
    }

    public final <R> k<R> concatMapMaybeDelayError(wm<? super T, ? extends i<? extends R>> wmVar, boolean z) {
        return concatMapMaybeDelayError(wmVar, z, 2);
    }

    public final <R> k<R> concatMapMaybeDelayError(wm<? super T, ? extends i<? extends R>> wmVar, boolean z, int i) {
        a.a((Object) wmVar, "mapper is null");
        a.a(i, "prefetch");
        return xk.a(new ObservableConcatMapMaybe(this, wmVar, z ? ErrorMode.END : ErrorMode.BOUNDARY, i));
    }

    public final <R> k<R> concatMapSingle(wm<? super T, ? extends v<? extends R>> wmVar) {
        return concatMapSingle(wmVar, 2);
    }

    public final <R> k<R> concatMapSingle(wm<? super T, ? extends v<? extends R>> wmVar, int i) {
        a.a((Object) wmVar, "mapper is null");
        a.a(i, "prefetch");
        return xk.a(new ObservableConcatMapSingle(this, wmVar, ErrorMode.IMMEDIATE, i));
    }

    public final <R> k<R> concatMapSingleDelayError(wm<? super T, ? extends v<? extends R>> wmVar) {
        return concatMapSingleDelayError(wmVar, true, 2);
    }

    public final <R> k<R> concatMapSingleDelayError(wm<? super T, ? extends v<? extends R>> wmVar, boolean z) {
        return concatMapSingleDelayError(wmVar, z, 2);
    }

    public final <R> k<R> concatMapSingleDelayError(wm<? super T, ? extends v<? extends R>> wmVar, boolean z, int i) {
        a.a((Object) wmVar, "mapper is null");
        a.a(i, "prefetch");
        return xk.a(new ObservableConcatMapSingle(this, wmVar, z ? ErrorMode.END : ErrorMode.BOUNDARY, i));
    }

    public final k<T> concatWith(p<? extends T> pVar) {
        a.a((Object) pVar, "other is null");
        return concat((p) this, (p) pVar);
    }

    public final k<T> concatWith(v<? extends T> vVar) {
        a.a((Object) vVar, "other is null");
        return xk.a(new ObservableConcatWithSingle(this, vVar));
    }

    public final k<T> concatWith(i<? extends T> iVar) {
        a.a((Object) iVar, "other is null");
        return xk.a(new ObservableConcatWithMaybe(this, iVar));
    }

    public final k<T> concatWith(c cVar) {
        a.a((Object) cVar, "other is null");
        return xk.a(new ObservableConcatWithCompletable(this, cVar));
    }

    public final t<Boolean> contains(Object obj) {
        a.a(obj, "element is null");
        return any(Functions.c(obj));
    }

    public final t<Long> count() {
        return xk.a(new p(this));
    }

    public final <U> k<T> debounce(wm<? super T, ? extends p<U>> wmVar) {
        a.a((Object) wmVar, "debounceSelector is null");
        return xk.a(new q(this, wmVar));
    }

    public final k<T> debounce(long j, TimeUnit timeUnit) {
        return debounce(j, timeUnit, xl.a());
    }

    public final k<T> debounce(long j, TimeUnit timeUnit, s sVar) {
        a.a((Object) timeUnit, "unit is null");
        a.a((Object) sVar, "scheduler is null");
        return xk.a(new ObservableDebounceTimed(this, j, timeUnit, sVar));
    }

    public final k<T> defaultIfEmpty(T t) {
        a.a((Object) t, "defaultItem is null");
        return switchIfEmpty(just(t));
    }

    public final <U> k<T> delay(wm<? super T, ? extends p<U>> wmVar) {
        a.a((Object) wmVar, "itemDelay is null");
        return flatMap(as.a((wm) wmVar));
    }

    public final k<T> delay(long j, TimeUnit timeUnit) {
        return delay(j, timeUnit, xl.a(), false);
    }

    public final k<T> delay(long j, TimeUnit timeUnit, boolean z) {
        return delay(j, timeUnit, xl.a(), z);
    }

    public final k<T> delay(long j, TimeUnit timeUnit, s sVar) {
        return delay(j, timeUnit, sVar, false);
    }

    public final k<T> delay(long j, TimeUnit timeUnit, s sVar, boolean z) {
        a.a((Object) timeUnit, "unit is null");
        a.a((Object) sVar, "scheduler is null");
        return xk.a(new s(this, j, timeUnit, sVar, z));
    }

    public final <U, V> k<T> delay(p<U> pVar, wm<? super T, ? extends p<V>> wmVar) {
        return delaySubscription(pVar).delay(wmVar);
    }

    public final <U> k<T> delaySubscription(p<U> pVar) {
        a.a((Object) pVar, "other is null");
        return xk.a(new t(this, pVar));
    }

    public final k<T> delaySubscription(long j, TimeUnit timeUnit) {
        return delaySubscription(j, timeUnit, xl.a());
    }

    public final k<T> delaySubscription(long j, TimeUnit timeUnit, s sVar) {
        return delaySubscription(timer(j, timeUnit, sVar));
    }

    public final <T2> k<T2> dematerialize() {
        return xk.a(new u(this));
    }

    public final k<T> distinct() {
        return distinct(Functions.a(), Functions.g());
    }

    public final <K> k<T> distinct(wm<? super T, K> wmVar) {
        return distinct(wmVar, Functions.g());
    }

    public final <K> k<T> distinct(wm<? super T, K> wmVar, Callable<? extends Collection<? super K>> callable) {
        a.a((Object) wmVar, "keySelector is null");
        a.a((Object) callable, "collectionSupplier is null");
        return xk.a(new w(this, wmVar, callable));
    }

    public final k<T> distinctUntilChanged() {
        return distinctUntilChanged(Functions.a());
    }

    public final <K> k<T> distinctUntilChanged(wm<? super T, K> wmVar) {
        a.a((Object) wmVar, "keySelector is null");
        return xk.a(new x(this, wmVar, a.a()));
    }

    public final k<T> distinctUntilChanged(wi<? super T, ? super T> wiVar) {
        a.a((Object) wiVar, "comparer is null");
        return xk.a(new x(this, Functions.a(), wiVar));
    }

    public final k<T> doAfterNext(wl<? super T> wlVar) {
        a.a((Object) wlVar, "onAfterNext is null");
        return xk.a(new y(this, wlVar));
    }

    public final k<T> doAfterTerminate(wf wfVar) {
        a.a((Object) wfVar, "onFinally is null");
        return doOnEach(Functions.b(), Functions.b(), Functions.c, wfVar);
    }

    public final k<T> doFinally(wf wfVar) {
        a.a((Object) wfVar, "onFinally is null");
        return xk.a(new ObservableDoFinally(this, wfVar));
    }

    public final k<T> doOnDispose(wf wfVar) {
        return doOnLifecycle(Functions.b(), wfVar);
    }

    public final k<T> doOnComplete(wf wfVar) {
        return doOnEach(Functions.b(), Functions.b(), wfVar, Functions.c);
    }

    private k<T> doOnEach(wl<? super T> wlVar, wl<? super Throwable> wlVar2, wf wfVar, wf wfVar2) {
        a.a((Object) wlVar, "onNext is null");
        a.a((Object) wlVar2, "onError is null");
        a.a((Object) wfVar, "onComplete is null");
        a.a((Object) wfVar2, "onAfterTerminate is null");
        return xk.a(new z(this, wlVar, wlVar2, wfVar, wfVar2));
    }

    public final k<T> doOnEach(wl<? super j<T>> wlVar) {
        a.a((Object) wlVar, "consumer is null");
        return doOnEach(Functions.a((wl) wlVar), Functions.b((wl) wlVar), Functions.c((wl) wlVar), Functions.c);
    }

    public final k<T> doOnEach(r<? super T> rVar) {
        a.a((Object) rVar, "observer is null");
        return doOnEach(as.a((r) rVar), as.b((r) rVar), as.c((r) rVar), Functions.c);
    }

    public final k<T> doOnError(wl<? super Throwable> wlVar) {
        return doOnEach(Functions.b(), wlVar, Functions.c, Functions.c);
    }

    public final k<T> doOnLifecycle(wl<? super b> wlVar, wf wfVar) {
        a.a((Object) wlVar, "onSubscribe is null");
        a.a((Object) wfVar, "onDispose is null");
        return xk.a(new aa(this, wlVar, wfVar));
    }

    public final k<T> doOnNext(wl<? super T> wlVar) {
        return doOnEach(wlVar, Functions.b(), Functions.c, Functions.c);
    }

    public final k<T> doOnSubscribe(wl<? super b> wlVar) {
        return doOnLifecycle(wlVar, Functions.c);
    }

    public final k<T> doOnTerminate(wf wfVar) {
        a.a((Object) wfVar, "onTerminate is null");
        return doOnEach(Functions.b(), Functions.a(wfVar), wfVar, Functions.c);
    }

    public final g<T> elementAt(long j) {
        if (j >= 0) {
            return xk.a(new ac(this, j));
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("index >= 0 required but it was ");
        stringBuilder.append(j);
        throw new IndexOutOfBoundsException(stringBuilder.toString());
    }

    public final t<T> elementAt(long j, T t) {
        if (j >= 0) {
            a.a((Object) t, "defaultItem is null");
            return xk.a(new ad(this, j, t));
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("index >= 0 required but it was ");
        stringBuilder.append(j);
        throw new IndexOutOfBoundsException(stringBuilder.toString());
    }

    public final t<T> elementAtOrError(long j) {
        if (j >= 0) {
            return xk.a(new ad(this, j, null));
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("index >= 0 required but it was ");
        stringBuilder.append(j);
        throw new IndexOutOfBoundsException(stringBuilder.toString());
    }

    public final k<T> filter(wv<? super T> wvVar) {
        a.a((Object) wvVar, "predicate is null");
        return xk.a(new ag(this, wvVar));
    }

    public final g<T> firstElement() {
        return elementAt(0);
    }

    public final t<T> first(T t) {
        return elementAt(0, t);
    }

    public final t<T> firstOrError() {
        return elementAtOrError(0);
    }

    public final <R> k<R> flatMap(wm<? super T, ? extends p<? extends R>> wmVar) {
        return flatMap((wm) wmVar, false);
    }

    public final <R> k<R> flatMap(wm<? super T, ? extends p<? extends R>> wmVar, boolean z) {
        return flatMap((wm) wmVar, z, (int) Filter.MAX);
    }

    public final <R> k<R> flatMap(wm<? super T, ? extends p<? extends R>> wmVar, boolean z, int i) {
        return flatMap((wm) wmVar, z, i, bufferSize());
    }

    public final <R> k<R> flatMap(wm<? super T, ? extends p<? extends R>> wmVar, boolean z, int i, int i2) {
        a.a((Object) wmVar, "mapper is null");
        a.a(i, "maxConcurrency");
        a.a(i2, "bufferSize");
        if (!(this instanceof xb)) {
            return xk.a(new ObservableFlatMap(this, wmVar, z, i, i2));
        }
        Object call = ((xb) this).call();
        if (call == null) {
            return empty();
        }
        return ObservableScalarXMap.a(call, wmVar);
    }

    public final <R> k<R> flatMap(wm<? super T, ? extends p<? extends R>> wmVar, wm<? super Throwable, ? extends p<? extends R>> wmVar2, Callable<? extends p<? extends R>> callable) {
        a.a((Object) wmVar, "onNextMapper is null");
        a.a((Object) wmVar2, "onErrorMapper is null");
        a.a((Object) callable, "onCompleteSupplier is null");
        return merge(new ay(this, wmVar, wmVar2, callable));
    }

    public final <R> k<R> flatMap(wm<? super T, ? extends p<? extends R>> wmVar, wm<Throwable, ? extends p<? extends R>> wmVar2, Callable<? extends p<? extends R>> callable, int i) {
        a.a((Object) wmVar, "onNextMapper is null");
        a.a((Object) wmVar2, "onErrorMapper is null");
        a.a((Object) callable, "onCompleteSupplier is null");
        return merge(new ay(this, wmVar, wmVar2, callable), i);
    }

    public final <R> k<R> flatMap(wm<? super T, ? extends p<? extends R>> wmVar, int i) {
        return flatMap((wm) wmVar, false, i, bufferSize());
    }

    public final <U, R> k<R> flatMap(wm<? super T, ? extends p<? extends U>> wmVar, wh<? super T, ? super U, ? extends R> whVar) {
        return flatMap(wmVar, whVar, false, bufferSize(), bufferSize());
    }

    public final <U, R> k<R> flatMap(wm<? super T, ? extends p<? extends U>> wmVar, wh<? super T, ? super U, ? extends R> whVar, boolean z) {
        return flatMap(wmVar, whVar, z, bufferSize(), bufferSize());
    }

    public final <U, R> k<R> flatMap(wm<? super T, ? extends p<? extends U>> wmVar, wh<? super T, ? super U, ? extends R> whVar, boolean z, int i) {
        return flatMap(wmVar, whVar, z, i, bufferSize());
    }

    public final <U, R> k<R> flatMap(wm<? super T, ? extends p<? extends U>> wmVar, wh<? super T, ? super U, ? extends R> whVar, boolean z, int i, int i2) {
        a.a((Object) wmVar, "mapper is null");
        a.a((Object) whVar, "combiner is null");
        return flatMap(as.a((wm) wmVar, (wh) whVar), z, i, i2);
    }

    public final <U, R> k<R> flatMap(wm<? super T, ? extends p<? extends U>> wmVar, wh<? super T, ? super U, ? extends R> whVar, int i) {
        return flatMap(wmVar, whVar, false, i, bufferSize());
    }

    public final a flatMapCompletable(wm<? super T, ? extends c> wmVar) {
        return flatMapCompletable(wmVar, false);
    }

    public final a flatMapCompletable(wm<? super T, ? extends c> wmVar, boolean z) {
        a.a((Object) wmVar, "mapper is null");
        return xk.a(new ObservableFlatMapCompletableCompletable(this, wmVar, z));
    }

    public final <U> k<U> flatMapIterable(wm<? super T, ? extends Iterable<? extends U>> wmVar) {
        a.a((Object) wmVar, "mapper is null");
        return xk.a(new ah(this, wmVar));
    }

    public final <U, V> k<V> flatMapIterable(wm<? super T, ? extends Iterable<? extends U>> wmVar, wh<? super T, ? super U, ? extends V> whVar) {
        a.a((Object) wmVar, "mapper is null");
        a.a((Object) whVar, "resultSelector is null");
        return flatMap(as.b((wm) wmVar), whVar, false, bufferSize(), bufferSize());
    }

    public final <R> k<R> flatMapMaybe(wm<? super T, ? extends i<? extends R>> wmVar) {
        return flatMapMaybe(wmVar, false);
    }

    public final <R> k<R> flatMapMaybe(wm<? super T, ? extends i<? extends R>> wmVar, boolean z) {
        a.a((Object) wmVar, "mapper is null");
        return xk.a(new ObservableFlatMapMaybe(this, wmVar, z));
    }

    public final <R> k<R> flatMapSingle(wm<? super T, ? extends v<? extends R>> wmVar) {
        return flatMapSingle(wmVar, false);
    }

    public final <R> k<R> flatMapSingle(wm<? super T, ? extends v<? extends R>> wmVar, boolean z) {
        a.a((Object) wmVar, "mapper is null");
        return xk.a(new ObservableFlatMapSingle(this, wmVar, z));
    }

    public final b forEach(wl<? super T> wlVar) {
        return subscribe((wl) wlVar);
    }

    public final b forEachWhile(wv<? super T> wvVar) {
        return forEachWhile(wvVar, Functions.f, Functions.c);
    }

    public final b forEachWhile(wv<? super T> wvVar, wl<? super Throwable> wlVar) {
        return forEachWhile(wvVar, wlVar, Functions.c);
    }

    public final b forEachWhile(wv<? super T> wvVar, wl<? super Throwable> wlVar, wf wfVar) {
        a.a((Object) wvVar, "onNext is null");
        a.a((Object) wlVar, "onError is null");
        a.a((Object) wfVar, "onComplete is null");
        r forEachWhileObserver = new ForEachWhileObserver(wvVar, wlVar, wfVar);
        subscribe(forEachWhileObserver);
        return forEachWhileObserver;
    }

    public final <K> k<xj<K, T>> groupBy(wm<? super T, ? extends K> wmVar) {
        return groupBy(wmVar, Functions.a(), false, bufferSize());
    }

    public final <K> k<xj<K, T>> groupBy(wm<? super T, ? extends K> wmVar, boolean z) {
        return groupBy(wmVar, Functions.a(), z, bufferSize());
    }

    public final <K, V> k<xj<K, V>> groupBy(wm<? super T, ? extends K> wmVar, wm<? super T, ? extends V> wmVar2) {
        return groupBy(wmVar, wmVar2, false, bufferSize());
    }

    public final <K, V> k<xj<K, V>> groupBy(wm<? super T, ? extends K> wmVar, wm<? super T, ? extends V> wmVar2, boolean z) {
        return groupBy(wmVar, wmVar2, z, bufferSize());
    }

    public final <K, V> k<xj<K, V>> groupBy(wm<? super T, ? extends K> wmVar, wm<? super T, ? extends V> wmVar2, boolean z, int i) {
        a.a((Object) wmVar, "keySelector is null");
        a.a((Object) wmVar2, "valueSelector is null");
        a.a(i, "bufferSize");
        return xk.a(new ObservableGroupBy(this, wmVar, wmVar2, i, z));
    }

    public final <TRight, TLeftEnd, TRightEnd, R> k<R> groupJoin(p<? extends TRight> pVar, wm<? super T, ? extends p<TLeftEnd>> wmVar, wm<? super TRight, ? extends p<TRightEnd>> wmVar2, wh<? super T, ? super k<TRight>, ? extends R> whVar) {
        a.a((Object) pVar, "other is null");
        a.a((Object) wmVar, "leftEnd is null");
        a.a((Object) wmVar2, "rightEnd is null");
        a.a((Object) whVar, "resultSelector is null");
        return xk.a(new ObservableGroupJoin(this, pVar, wmVar, wmVar2, whVar));
    }

    public final k<T> hide() {
        return xk.a(new ap(this));
    }

    public final a ignoreElements() {
        return xk.a(new ar(this));
    }

    public final t<Boolean> isEmpty() {
        return all(Functions.d());
    }

    public final <TRight, TLeftEnd, TRightEnd, R> k<R> join(p<? extends TRight> pVar, wm<? super T, ? extends p<TLeftEnd>> wmVar, wm<? super TRight, ? extends p<TRightEnd>> wmVar2, wh<? super T, ? super TRight, ? extends R> whVar) {
        a.a((Object) pVar, "other is null");
        a.a((Object) wmVar, "leftEnd is null");
        a.a((Object) wmVar2, "rightEnd is null");
        a.a((Object) whVar, "resultSelector is null");
        return xk.a(new ObservableJoin(this, pVar, wmVar, wmVar2, whVar));
    }

    public final g<T> lastElement() {
        return xk.a(new au(this));
    }

    public final t<T> last(T t) {
        a.a((Object) t, "defaultItem is null");
        return xk.a(new av(this, t));
    }

    public final t<T> lastOrError() {
        return xk.a(new av(this, null));
    }

    public final <R> k<R> lift(o<? extends R, ? super T> oVar) {
        a.a((Object) oVar, "onLift is null");
        return xk.a(new aw(this, oVar));
    }

    public final <R> k<R> map(wm<? super T, ? extends R> wmVar) {
        a.a((Object) wmVar, "mapper is null");
        return xk.a(new ax(this, wmVar));
    }

    public final k<j<T>> materialize() {
        return xk.a(new az(this));
    }

    public final k<T> mergeWith(p<? extends T> pVar) {
        a.a((Object) pVar, "other is null");
        return merge((p) this, (p) pVar);
    }

    public final k<T> mergeWith(v<? extends T> vVar) {
        a.a((Object) vVar, "other is null");
        return xk.a(new ObservableMergeWithSingle(this, vVar));
    }

    public final k<T> mergeWith(i<? extends T> iVar) {
        a.a((Object) iVar, "other is null");
        return xk.a(new ObservableMergeWithMaybe(this, iVar));
    }

    public final k<T> mergeWith(c cVar) {
        a.a((Object) cVar, "other is null");
        return xk.a(new ObservableMergeWithCompletable(this, cVar));
    }

    public final k<T> observeOn(s sVar) {
        return observeOn(sVar, false, bufferSize());
    }

    public final k<T> observeOn(s sVar, boolean z) {
        return observeOn(sVar, z, bufferSize());
    }

    public final k<T> observeOn(s sVar, boolean z, int i) {
        a.a((Object) sVar, "scheduler is null");
        a.a(i, "bufferSize");
        return xk.a(new ObservableObserveOn(this, sVar, z, i));
    }

    public final <U> k<U> ofType(Class<U> cls) {
        a.a((Object) cls, "clazz is null");
        return filter(Functions.b((Class) cls)).cast(cls);
    }

    public final k<T> onErrorResumeNext(wm<? super Throwable, ? extends p<? extends T>> wmVar) {
        a.a((Object) wmVar, "resumeFunction is null");
        return xk.a(new bb(this, wmVar, false));
    }

    public final k<T> onErrorResumeNext(p<? extends T> pVar) {
        a.a((Object) pVar, "next is null");
        return onErrorResumeNext(Functions.b((Object) pVar));
    }

    public final k<T> onErrorReturn(wm<? super Throwable, ? extends T> wmVar) {
        a.a((Object) wmVar, "valueSupplier is null");
        return xk.a(new bc(this, wmVar));
    }

    public final k<T> onErrorReturnItem(T t) {
        a.a((Object) t, "item is null");
        return onErrorReturn(Functions.b((Object) t));
    }

    public final k<T> onExceptionResumeNext(p<? extends T> pVar) {
        a.a((Object) pVar, "next is null");
        return xk.a(new bb(this, Functions.b((Object) pVar), true));
    }

    public final k<T> onTerminateDetach() {
        return xk.a(new v(this));
    }

    public final xi<T> publish() {
        return ObservablePublish.a((p) this);
    }

    public final <R> k<R> publish(wm<? super k<T>, ? extends p<R>> wmVar) {
        a.a((Object) wmVar, "selector is null");
        return xk.a(new ObservablePublishSelector(this, wmVar));
    }

    public final g<T> reduce(wh<T, T, T> whVar) {
        a.a((Object) whVar, "reducer is null");
        return xk.a(new bd(this, whVar));
    }

    public final <R> t<R> reduce(R r, wh<R, ? super T, R> whVar) {
        a.a((Object) r, "seed is null");
        a.a((Object) whVar, "reducer is null");
        return xk.a(new be(this, r, whVar));
    }

    public final <R> t<R> reduceWith(Callable<R> callable, wh<R, ? super T, R> whVar) {
        a.a((Object) callable, "seedSupplier is null");
        a.a((Object) whVar, "reducer is null");
        return xk.a(new bf(this, callable, whVar));
    }

    public final k<T> repeat() {
        return repeat(Long.MAX_VALUE);
    }

    public final k<T> repeat(long j) {
        if (j < 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("times >= 0 required but it was ");
            stringBuilder.append(j);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (j == 0) {
            return empty();
        } else {
            return xk.a(new ObservableRepeat(this, j));
        }
    }

    public final k<T> repeatUntil(wj wjVar) {
        a.a((Object) wjVar, "stop is null");
        return xk.a(new ObservableRepeatUntil(this, wjVar));
    }

    public final k<T> repeatWhen(wm<? super k<Object>, ? extends p<?>> wmVar) {
        a.a((Object) wmVar, "handler is null");
        return xk.a(new ObservableRepeatWhen(this, wmVar));
    }

    public final xi<T> replay() {
        return ObservableReplay.a((p) this);
    }

    public final <R> k<R> replay(wm<? super k<T>, ? extends p<R>> wmVar) {
        a.a((Object) wmVar, "selector is null");
        return ObservableReplay.a(as.a(this), (wm) wmVar);
    }

    public final <R> k<R> replay(wm<? super k<T>, ? extends p<R>> wmVar, int i) {
        a.a((Object) wmVar, "selector is null");
        a.a(i, "bufferSize");
        return ObservableReplay.a(as.a(this, i), (wm) wmVar);
    }

    public final <R> k<R> replay(wm<? super k<T>, ? extends p<R>> wmVar, int i, long j, TimeUnit timeUnit) {
        return replay(wmVar, i, j, timeUnit, xl.a());
    }

    public final <R> k<R> replay(wm<? super k<T>, ? extends p<R>> wmVar, int i, long j, TimeUnit timeUnit, s sVar) {
        a.a((Object) wmVar, "selector is null");
        a.a(i, "bufferSize");
        a.a((Object) timeUnit, "unit is null");
        a.a((Object) sVar, "scheduler is null");
        return ObservableReplay.a(as.a(this, i, j, timeUnit, sVar), (wm) wmVar);
    }

    public final <R> k<R> replay(wm<? super k<T>, ? extends p<R>> wmVar, int i, s sVar) {
        a.a((Object) wmVar, "selector is null");
        a.a((Object) sVar, "scheduler is null");
        a.a(i, "bufferSize");
        return ObservableReplay.a(as.a(this, i), as.a((wm) wmVar, sVar));
    }

    public final <R> k<R> replay(wm<? super k<T>, ? extends p<R>> wmVar, long j, TimeUnit timeUnit) {
        return replay((wm) wmVar, j, timeUnit, xl.a());
    }

    public final <R> k<R> replay(wm<? super k<T>, ? extends p<R>> wmVar, long j, TimeUnit timeUnit, s sVar) {
        a.a((Object) wmVar, "selector is null");
        a.a((Object) timeUnit, "unit is null");
        a.a((Object) sVar, "scheduler is null");
        return ObservableReplay.a(as.a(this, j, timeUnit, sVar), (wm) wmVar);
    }

    public final <R> k<R> replay(wm<? super k<T>, ? extends p<R>> wmVar, s sVar) {
        a.a((Object) wmVar, "selector is null");
        a.a((Object) sVar, "scheduler is null");
        return ObservableReplay.a(as.a(this), as.a((wm) wmVar, sVar));
    }

    public final xi<T> replay(int i) {
        a.a(i, "bufferSize");
        return ObservableReplay.a((p) this, i);
    }

    public final xi<T> replay(int i, long j, TimeUnit timeUnit) {
        return replay(i, j, timeUnit, xl.a());
    }

    public final xi<T> replay(int i, long j, TimeUnit timeUnit, s sVar) {
        a.a(i, "bufferSize");
        a.a((Object) timeUnit, "unit is null");
        a.a((Object) sVar, "scheduler is null");
        return ObservableReplay.a(this, j, timeUnit, sVar, i);
    }

    public final xi<T> replay(int i, s sVar) {
        a.a(i, "bufferSize");
        return ObservableReplay.a(replay(i), sVar);
    }

    public final xi<T> replay(long j, TimeUnit timeUnit) {
        return replay(j, timeUnit, xl.a());
    }

    public final xi<T> replay(long j, TimeUnit timeUnit, s sVar) {
        a.a((Object) timeUnit, "unit is null");
        a.a((Object) sVar, "scheduler is null");
        return ObservableReplay.a(this, j, timeUnit, sVar);
    }

    public final xi<T> replay(s sVar) {
        a.a((Object) sVar, "scheduler is null");
        return ObservableReplay.a(replay(), sVar);
    }

    public final k<T> retry() {
        return retry(Long.MAX_VALUE, Functions.c());
    }

    public final k<T> retry(wi<? super Integer, ? super Throwable> wiVar) {
        a.a((Object) wiVar, "predicate is null");
        return xk.a(new ObservableRetryBiPredicate(this, wiVar));
    }

    public final k<T> retry(long j) {
        return retry(j, Functions.c());
    }

    public final k<T> retry(long j, wv<? super Throwable> wvVar) {
        if (j >= 0) {
            a.a((Object) wvVar, "predicate is null");
            return xk.a(new ObservableRetryPredicate(this, j, wvVar));
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("times >= 0 required but it was ");
        stringBuilder.append(j);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public final k<T> retry(wv<? super Throwable> wvVar) {
        return retry(Long.MAX_VALUE, wvVar);
    }

    public final k<T> retryUntil(wj wjVar) {
        a.a((Object) wjVar, "stop is null");
        return retry(Long.MAX_VALUE, Functions.a(wjVar));
    }

    public final k<T> retryWhen(wm<? super k<Throwable>, ? extends p<?>> wmVar) {
        a.a((Object) wmVar, "handler is null");
        return xk.a(new ObservableRetryWhen(this, wmVar));
    }

    public final void safeSubscribe(r<? super T> rVar) {
        a.a((Object) rVar, "s is null");
        if (rVar instanceof io.reactivex.observers.d) {
            subscribe((r) rVar);
        } else {
            subscribe(new io.reactivex.observers.d(rVar));
        }
    }

    public final k<T> sample(long j, TimeUnit timeUnit) {
        return sample(j, timeUnit, xl.a());
    }

    public final k<T> sample(long j, TimeUnit timeUnit, boolean z) {
        return sample(j, timeUnit, xl.a(), z);
    }

    public final k<T> sample(long j, TimeUnit timeUnit, s sVar) {
        a.a((Object) timeUnit, "unit is null");
        a.a((Object) sVar, "scheduler is null");
        return xk.a(new ObservableSampleTimed(this, j, timeUnit, sVar, false));
    }

    public final k<T> sample(long j, TimeUnit timeUnit, s sVar, boolean z) {
        a.a((Object) timeUnit, "unit is null");
        a.a((Object) sVar, "scheduler is null");
        return xk.a(new ObservableSampleTimed(this, j, timeUnit, sVar, z));
    }

    public final <U> k<T> sample(p<U> pVar) {
        a.a((Object) pVar, "sampler is null");
        return xk.a(new ObservableSampleWithObservable(this, pVar, false));
    }

    public final <U> k<T> sample(p<U> pVar, boolean z) {
        a.a((Object) pVar, "sampler is null");
        return xk.a(new ObservableSampleWithObservable(this, pVar, z));
    }

    public final k<T> scan(wh<T, T, T> whVar) {
        a.a((Object) whVar, "accumulator is null");
        return xk.a(new bg(this, whVar));
    }

    public final <R> k<R> scan(R r, wh<R, ? super T, R> whVar) {
        a.a((Object) r, "seed is null");
        return scanWith(Functions.a((Object) r), whVar);
    }

    public final <R> k<R> scanWith(Callable<R> callable, wh<R, ? super T, R> whVar) {
        a.a((Object) callable, "seedSupplier is null");
        a.a((Object) whVar, "accumulator is null");
        return xk.a(new bh(this, callable, whVar));
    }

    public final k<T> serialize() {
        return xk.a(new bi(this));
    }

    public final k<T> share() {
        return publish().a();
    }

    public final g<T> singleElement() {
        return xk.a(new bj(this));
    }

    public final t<T> single(T t) {
        a.a((Object) t, "defaultItem is null");
        return xk.a(new bk(this, t));
    }

    public final t<T> singleOrError() {
        return xk.a(new bk(this, null));
    }

    public final k<T> skip(long j) {
        if (j <= 0) {
            return xk.a(this);
        }
        return xk.a(new bl(this, j));
    }

    public final k<T> skip(long j, TimeUnit timeUnit) {
        return skipUntil(timer(j, timeUnit));
    }

    public final k<T> skip(long j, TimeUnit timeUnit, s sVar) {
        return skipUntil(timer(j, timeUnit, sVar));
    }

    public final k<T> skipLast(int i) {
        if (i < 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("count >= 0 required but it was ");
            stringBuilder.append(i);
            throw new IndexOutOfBoundsException(stringBuilder.toString());
        } else if (i == 0) {
            return xk.a(this);
        } else {
            return xk.a(new ObservableSkipLast(this, i));
        }
    }

    public final k<T> skipLast(long j, TimeUnit timeUnit) {
        return skipLast(j, timeUnit, xl.c(), false, bufferSize());
    }

    public final k<T> skipLast(long j, TimeUnit timeUnit, boolean z) {
        return skipLast(j, timeUnit, xl.c(), z, bufferSize());
    }

    public final k<T> skipLast(long j, TimeUnit timeUnit, s sVar) {
        return skipLast(j, timeUnit, sVar, false, bufferSize());
    }

    public final k<T> skipLast(long j, TimeUnit timeUnit, s sVar, boolean z) {
        return skipLast(j, timeUnit, sVar, z, bufferSize());
    }

    public final k<T> skipLast(long j, TimeUnit timeUnit, s sVar, boolean z, int i) {
        a.a((Object) timeUnit, "unit is null");
        a.a((Object) sVar, "scheduler is null");
        a.a(i, "bufferSize");
        return xk.a(new ObservableSkipLastTimed(this, j, timeUnit, sVar, i << 1, z));
    }

    public final <U> k<T> skipUntil(p<U> pVar) {
        a.a((Object) pVar, "other is null");
        return xk.a(new bm(this, pVar));
    }

    public final k<T> skipWhile(wv<? super T> wvVar) {
        a.a((Object) wvVar, "predicate is null");
        return xk.a(new bn(this, wvVar));
    }

    public final k<T> sorted() {
        return toList().b().map(Functions.a(Functions.h())).flatMapIterable(Functions.a());
    }

    public final k<T> sorted(Comparator<? super T> comparator) {
        a.a((Object) comparator, "sortFunction is null");
        return toList().b().map(Functions.a((Comparator) comparator)).flatMapIterable(Functions.a());
    }

    public final k<T> startWith(Iterable<? extends T> iterable) {
        return concatArray(fromIterable(iterable), this);
    }

    public final k<T> startWith(p<? extends T> pVar) {
        a.a((Object) pVar, "other is null");
        return concatArray(pVar, this);
    }

    public final k<T> startWith(T t) {
        a.a((Object) t, "item is null");
        return concatArray(just(t), this);
    }

    public final k<T> startWithArray(T... tArr) {
        if (fromArray(tArr) == empty()) {
            return xk.a(this);
        }
        return concatArray(fromArray(tArr), this);
    }

    public final b subscribe() {
        return subscribe(Functions.b(), Functions.f, Functions.c, Functions.b());
    }

    public final b subscribe(wl<? super T> wlVar) {
        return subscribe(wlVar, Functions.f, Functions.c, Functions.b());
    }

    public final b subscribe(wl<? super T> wlVar, wl<? super Throwable> wlVar2) {
        return subscribe(wlVar, wlVar2, Functions.c, Functions.b());
    }

    public final b subscribe(wl<? super T> wlVar, wl<? super Throwable> wlVar2, wf wfVar) {
        return subscribe(wlVar, wlVar2, wfVar, Functions.b());
    }

    public final b subscribe(wl<? super T> wlVar, wl<? super Throwable> wlVar2, wf wfVar, wl<? super b> wlVar3) {
        a.a((Object) wlVar, "onNext is null");
        a.a((Object) wlVar2, "onError is null");
        a.a((Object) wfVar, "onComplete is null");
        a.a((Object) wlVar3, "onSubscribe is null");
        r lambdaObserver = new LambdaObserver(wlVar, wlVar2, wfVar, wlVar3);
        subscribe(lambdaObserver);
        return lambdaObserver;
    }

    public final void subscribe(r<? super T> rVar) {
        a.a((Object) rVar, "observer is null");
        try {
            Object a = xk.a(this, (r) rVar);
            a.a(a, "The RxJavaPlugins.onSubscribe hook returned a null Observer. Please change the handler provided to RxJavaPlugins.setOnObservableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            subscribeActual(a);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            xk.a(th);
            new NullPointerException("Actually not, but can't throw other exceptions due to RS").initCause(th);
        }
    }

    public final <E extends r<? super T>> E subscribeWith(E e) {
        subscribe((r) e);
        return e;
    }

    public final k<T> subscribeOn(s sVar) {
        a.a((Object) sVar, "scheduler is null");
        return xk.a(new ObservableSubscribeOn(this, sVar));
    }

    public final k<T> switchIfEmpty(p<? extends T> pVar) {
        a.a((Object) pVar, "other is null");
        return xk.a(new bo(this, pVar));
    }

    public final <R> k<R> switchMap(wm<? super T, ? extends p<? extends R>> wmVar) {
        return switchMap(wmVar, bufferSize());
    }

    public final <R> k<R> switchMap(wm<? super T, ? extends p<? extends R>> wmVar, int i) {
        a.a((Object) wmVar, "mapper is null");
        a.a(i, "bufferSize");
        if (!(this instanceof xb)) {
            return xk.a(new ObservableSwitchMap(this, wmVar, i, false));
        }
        Object call = ((xb) this).call();
        if (call == null) {
            return empty();
        }
        return ObservableScalarXMap.a(call, wmVar);
    }

    public final a switchMapCompletable(wm<? super T, ? extends c> wmVar) {
        a.a((Object) wmVar, "mapper is null");
        return xk.a(new ObservableSwitchMapCompletable(this, wmVar, false));
    }

    public final a switchMapCompletableDelayError(wm<? super T, ? extends c> wmVar) {
        a.a((Object) wmVar, "mapper is null");
        return xk.a(new ObservableSwitchMapCompletable(this, wmVar, true));
    }

    public final <R> k<R> switchMapMaybe(wm<? super T, ? extends i<? extends R>> wmVar) {
        a.a((Object) wmVar, "mapper is null");
        return xk.a(new ObservableSwitchMapMaybe(this, wmVar, false));
    }

    public final <R> k<R> switchMapMaybeDelayError(wm<? super T, ? extends i<? extends R>> wmVar) {
        a.a((Object) wmVar, "mapper is null");
        return xk.a(new ObservableSwitchMapMaybe(this, wmVar, true));
    }

    public final <R> k<R> switchMapSingle(wm<? super T, ? extends v<? extends R>> wmVar) {
        a.a((Object) wmVar, "mapper is null");
        return xk.a(new ObservableSwitchMapSingle(this, wmVar, false));
    }

    public final <R> k<R> switchMapSingleDelayError(wm<? super T, ? extends v<? extends R>> wmVar) {
        a.a((Object) wmVar, "mapper is null");
        return xk.a(new ObservableSwitchMapSingle(this, wmVar, true));
    }

    public final <R> k<R> switchMapDelayError(wm<? super T, ? extends p<? extends R>> wmVar) {
        return switchMapDelayError(wmVar, bufferSize());
    }

    public final <R> k<R> switchMapDelayError(wm<? super T, ? extends p<? extends R>> wmVar, int i) {
        a.a((Object) wmVar, "mapper is null");
        a.a(i, "bufferSize");
        if (!(this instanceof xb)) {
            return xk.a(new ObservableSwitchMap(this, wmVar, i, true));
        }
        Object call = ((xb) this).call();
        if (call == null) {
            return empty();
        }
        return ObservableScalarXMap.a(call, wmVar);
    }

    public final k<T> take(long j) {
        if (j >= 0) {
            return xk.a(new bp(this, j));
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("count >= 0 required but it was ");
        stringBuilder.append(j);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public final k<T> take(long j, TimeUnit timeUnit) {
        return takeUntil(timer(j, timeUnit));
    }

    public final k<T> take(long j, TimeUnit timeUnit, s sVar) {
        return takeUntil(timer(j, timeUnit, sVar));
    }

    public final k<T> takeLast(int i) {
        if (i < 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("count >= 0 required but it was ");
            stringBuilder.append(i);
            throw new IndexOutOfBoundsException(stringBuilder.toString());
        } else if (i == 0) {
            return xk.a(new aq(this));
        } else {
            if (i == 1) {
                return xk.a(new bq(this));
            }
            return xk.a(new ObservableTakeLast(this, i));
        }
    }

    public final k<T> takeLast(long j, long j2, TimeUnit timeUnit) {
        return takeLast(j, j2, timeUnit, xl.c(), false, bufferSize());
    }

    public final k<T> takeLast(long j, long j2, TimeUnit timeUnit, s sVar) {
        return takeLast(j, j2, timeUnit, sVar, false, bufferSize());
    }

    public final k<T> takeLast(long j, long j2, TimeUnit timeUnit, s sVar, boolean z, int i) {
        long j3 = j;
        a.a((Object) timeUnit, "unit is null");
        a.a((Object) sVar, "scheduler is null");
        a.a(i, "bufferSize");
        if (j3 >= 0) {
            return xk.a(new ObservableTakeLastTimed(this, j, j2, timeUnit, sVar, i, z));
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("count >= 0 required but it was ");
        stringBuilder.append(j);
        throw new IndexOutOfBoundsException(stringBuilder.toString());
    }

    public final k<T> takeLast(long j, TimeUnit timeUnit) {
        return takeLast(j, timeUnit, xl.c(), false, bufferSize());
    }

    public final k<T> takeLast(long j, TimeUnit timeUnit, boolean z) {
        return takeLast(j, timeUnit, xl.c(), z, bufferSize());
    }

    public final k<T> takeLast(long j, TimeUnit timeUnit, s sVar) {
        return takeLast(j, timeUnit, sVar, false, bufferSize());
    }

    public final k<T> takeLast(long j, TimeUnit timeUnit, s sVar, boolean z) {
        return takeLast(j, timeUnit, sVar, z, bufferSize());
    }

    public final k<T> takeLast(long j, TimeUnit timeUnit, s sVar, boolean z, int i) {
        return takeLast(Long.MAX_VALUE, j, timeUnit, sVar, z, i);
    }

    public final <U> k<T> takeUntil(p<U> pVar) {
        a.a((Object) pVar, "other is null");
        return xk.a(new ObservableTakeUntil(this, pVar));
    }

    public final k<T> takeUntil(wv<? super T> wvVar) {
        a.a((Object) wvVar, "predicate is null");
        return xk.a(new br(this, wvVar));
    }

    public final k<T> takeWhile(wv<? super T> wvVar) {
        a.a((Object) wvVar, "predicate is null");
        return xk.a(new bs(this, wvVar));
    }

    public final k<T> throttleFirst(long j, TimeUnit timeUnit) {
        return throttleFirst(j, timeUnit, xl.a());
    }

    public final k<T> throttleFirst(long j, TimeUnit timeUnit, s sVar) {
        a.a((Object) timeUnit, "unit is null");
        a.a((Object) sVar, "scheduler is null");
        return xk.a(new ObservableThrottleFirstTimed(this, j, timeUnit, sVar));
    }

    public final k<T> throttleLast(long j, TimeUnit timeUnit) {
        return sample(j, timeUnit);
    }

    public final k<T> throttleLast(long j, TimeUnit timeUnit, s sVar) {
        return sample(j, timeUnit, sVar);
    }

    public final k<T> throttleLatest(long j, TimeUnit timeUnit) {
        return throttleLatest(j, timeUnit, xl.a(), false);
    }

    public final k<T> throttleLatest(long j, TimeUnit timeUnit, boolean z) {
        return throttleLatest(j, timeUnit, xl.a(), z);
    }

    public final k<T> throttleLatest(long j, TimeUnit timeUnit, s sVar) {
        return throttleLatest(j, timeUnit, sVar, false);
    }

    public final k<T> throttleLatest(long j, TimeUnit timeUnit, s sVar, boolean z) {
        a.a((Object) timeUnit, "unit is null");
        a.a((Object) sVar, "scheduler is null");
        return xk.a(new ObservableThrottleLatest(this, j, timeUnit, sVar, z));
    }

    public final k<T> throttleWithTimeout(long j, TimeUnit timeUnit) {
        return debounce(j, timeUnit);
    }

    public final k<T> throttleWithTimeout(long j, TimeUnit timeUnit, s sVar) {
        return debounce(j, timeUnit, sVar);
    }

    public final k<xm<T>> timeInterval() {
        return timeInterval(TimeUnit.MILLISECONDS, xl.a());
    }

    public final k<xm<T>> timeInterval(s sVar) {
        return timeInterval(TimeUnit.MILLISECONDS, sVar);
    }

    public final k<xm<T>> timeInterval(TimeUnit timeUnit) {
        return timeInterval(timeUnit, xl.a());
    }

    public final k<xm<T>> timeInterval(TimeUnit timeUnit, s sVar) {
        a.a((Object) timeUnit, "unit is null");
        a.a((Object) sVar, "scheduler is null");
        return xk.a(new bt(this, timeUnit, sVar));
    }

    public final <V> k<T> timeout(wm<? super T, ? extends p<V>> wmVar) {
        return timeout0(null, wmVar, null);
    }

    public final <V> k<T> timeout(wm<? super T, ? extends p<V>> wmVar, p<? extends T> pVar) {
        a.a((Object) pVar, "other is null");
        return timeout0(null, wmVar, pVar);
    }

    public final k<T> timeout(long j, TimeUnit timeUnit) {
        return timeout0(j, timeUnit, null, xl.a());
    }

    public final k<T> timeout(long j, TimeUnit timeUnit, p<? extends T> pVar) {
        a.a((Object) pVar, "other is null");
        return timeout0(j, timeUnit, pVar, xl.a());
    }

    public final k<T> timeout(long j, TimeUnit timeUnit, s sVar, p<? extends T> pVar) {
        a.a((Object) pVar, "other is null");
        return timeout0(j, timeUnit, pVar, sVar);
    }

    public final k<T> timeout(long j, TimeUnit timeUnit, s sVar) {
        return timeout0(j, timeUnit, null, sVar);
    }

    public final <U, V> k<T> timeout(p<U> pVar, wm<? super T, ? extends p<V>> wmVar) {
        a.a((Object) pVar, "firstTimeoutIndicator is null");
        return timeout0(pVar, wmVar, null);
    }

    public final <U, V> k<T> timeout(p<U> pVar, wm<? super T, ? extends p<V>> wmVar, p<? extends T> pVar2) {
        a.a((Object) pVar, "firstTimeoutIndicator is null");
        a.a((Object) pVar2, "other is null");
        return timeout0(pVar, wmVar, pVar2);
    }

    private k<T> timeout0(long j, TimeUnit timeUnit, p<? extends T> pVar, s sVar) {
        a.a((Object) timeUnit, "timeUnit is null");
        a.a((Object) sVar, "scheduler is null");
        return xk.a(new ObservableTimeoutTimed(this, j, timeUnit, sVar, pVar));
    }

    private <U, V> k<T> timeout0(p<U> pVar, wm<? super T, ? extends p<V>> wmVar, p<? extends T> pVar2) {
        a.a((Object) wmVar, "itemTimeoutIndicator is null");
        return xk.a(new ObservableTimeout(this, pVar, wmVar, pVar2));
    }

    public final k<xm<T>> timestamp() {
        return timestamp(TimeUnit.MILLISECONDS, xl.a());
    }

    public final k<xm<T>> timestamp(s sVar) {
        return timestamp(TimeUnit.MILLISECONDS, sVar);
    }

    public final k<xm<T>> timestamp(TimeUnit timeUnit) {
        return timestamp(timeUnit, xl.a());
    }

    public final k<xm<T>> timestamp(TimeUnit timeUnit, s sVar) {
        a.a((Object) timeUnit, "unit is null");
        a.a((Object) sVar, "scheduler is null");
        return map(Functions.a(timeUnit, sVar));
    }

    public final <R> R to(wm<? super k<T>, R> wmVar) {
        try {
            return ((wm) a.a((Object) wmVar, "converter is null")).apply(this);
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            RuntimeException a = ExceptionHelper.a(th);
        }
    }

    public final t<List<T>> toList() {
        return toList(16);
    }

    public final t<List<T>> toList(int i) {
        a.a(i, "capacityHint");
        return xk.a(new bv((p) this, i));
    }

    public final <U extends Collection<? super T>> t<U> toList(Callable<U> callable) {
        a.a((Object) callable, "collectionSupplier is null");
        return xk.a(new bv((p) this, (Callable) callable));
    }

    public final <K> t<Map<K, T>> toMap(wm<? super T, ? extends K> wmVar) {
        a.a((Object) wmVar, "keySelector is null");
        return collect(HashMapSupplier.asCallable(), Functions.a((wm) wmVar));
    }

    public final <K, V> t<Map<K, V>> toMap(wm<? super T, ? extends K> wmVar, wm<? super T, ? extends V> wmVar2) {
        a.a((Object) wmVar, "keySelector is null");
        a.a((Object) wmVar2, "valueSelector is null");
        return collect(HashMapSupplier.asCallable(), Functions.a((wm) wmVar, (wm) wmVar2));
    }

    public final <K, V> t<Map<K, V>> toMap(wm<? super T, ? extends K> wmVar, wm<? super T, ? extends V> wmVar2, Callable<? extends Map<K, V>> callable) {
        a.a((Object) wmVar, "keySelector is null");
        a.a((Object) wmVar2, "valueSelector is null");
        a.a((Object) callable, "mapSupplier is null");
        return collect(callable, Functions.a((wm) wmVar, (wm) wmVar2));
    }

    public final <K> t<Map<K, Collection<T>>> toMultimap(wm<? super T, ? extends K> wmVar) {
        return toMultimap(wmVar, Functions.a(), HashMapSupplier.asCallable(), ArrayListSupplier.asFunction());
    }

    public final <K, V> t<Map<K, Collection<V>>> toMultimap(wm<? super T, ? extends K> wmVar, wm<? super T, ? extends V> wmVar2) {
        return toMultimap(wmVar, wmVar2, HashMapSupplier.asCallable(), ArrayListSupplier.asFunction());
    }

    public final <K, V> t<Map<K, Collection<V>>> toMultimap(wm<? super T, ? extends K> wmVar, wm<? super T, ? extends V> wmVar2, Callable<? extends Map<K, Collection<V>>> callable, wm<? super K, ? extends Collection<? super V>> wmVar3) {
        a.a((Object) wmVar, "keySelector is null");
        a.a((Object) wmVar2, "valueSelector is null");
        a.a((Object) callable, "mapSupplier is null");
        a.a((Object) wmVar3, "collectionFactory is null");
        return collect(callable, Functions.a(wmVar, wmVar2, wmVar3));
    }

    public final <K, V> t<Map<K, Collection<V>>> toMultimap(wm<? super T, ? extends K> wmVar, wm<? super T, ? extends V> wmVar2, Callable<Map<K, Collection<V>>> callable) {
        return toMultimap(wmVar, wmVar2, callable, ArrayListSupplier.asFunction());
    }

    public final e<T> toFlowable(BackpressureStrategy backpressureStrategy) {
        io.reactivex.internal.operators.flowable.c cVar = new io.reactivex.internal.operators.flowable.c(this);
        switch (backpressureStrategy) {
            case DROP:
                return cVar.c();
            case LATEST:
                return cVar.d();
            case MISSING:
                return cVar;
            case ERROR:
                return xk.a(new FlowableOnBackpressureError(cVar));
            default:
                return cVar.b();
        }
    }

    public final t<List<T>> toSortedList() {
        return toSortedList(Functions.f());
    }

    public final t<List<T>> toSortedList(Comparator<? super T> comparator) {
        a.a((Object) comparator, "comparator is null");
        return toList().a(Functions.a((Comparator) comparator));
    }

    public final t<List<T>> toSortedList(Comparator<? super T> comparator, int i) {
        a.a((Object) comparator, "comparator is null");
        return toList(i).a(Functions.a((Comparator) comparator));
    }

    public final t<List<T>> toSortedList(int i) {
        return toSortedList(Functions.f(), i);
    }

    public final k<T> unsubscribeOn(s sVar) {
        a.a((Object) sVar, "scheduler is null");
        return xk.a(new ObservableUnsubscribeOn(this, sVar));
    }

    public final k<k<T>> window(long j) {
        return window(j, j, bufferSize());
    }

    public final k<k<T>> window(long j, long j2) {
        return window(j, j2, bufferSize());
    }

    public final k<k<T>> window(long j, long j2, int i) {
        a.a(j, AlbumLoader.COLUMN_COUNT);
        a.a(j2, "skip");
        a.a(i, "bufferSize");
        return xk.a(new ObservableWindow(this, j, j2, i));
    }

    public final k<k<T>> window(long j, long j2, TimeUnit timeUnit) {
        return window(j, j2, timeUnit, xl.a(), bufferSize());
    }

    public final k<k<T>> window(long j, long j2, TimeUnit timeUnit, s sVar) {
        return window(j, j2, timeUnit, sVar, bufferSize());
    }

    public final k<k<T>> window(long j, long j2, TimeUnit timeUnit, s sVar, int i) {
        long j3 = j;
        a.a(j, "timespan");
        long j4 = j2;
        a.a(j4, "timeskip");
        int i2 = i;
        a.a(i2, "bufferSize");
        Object obj = sVar;
        a.a(obj, "scheduler is null");
        Object obj2 = timeUnit;
        a.a(obj2, "unit is null");
        return xk.a(new bx(this, j3, j4, obj2, obj, Long.MAX_VALUE, i2, false));
    }

    public final k<k<T>> window(long j, TimeUnit timeUnit) {
        return window(j, timeUnit, xl.a(), Long.MAX_VALUE, false);
    }

    public final k<k<T>> window(long j, TimeUnit timeUnit, long j2) {
        return window(j, timeUnit, xl.a(), j2, false);
    }

    public final k<k<T>> window(long j, TimeUnit timeUnit, long j2, boolean z) {
        return window(j, timeUnit, xl.a(), j2, z);
    }

    public final k<k<T>> window(long j, TimeUnit timeUnit, s sVar) {
        return window(j, timeUnit, sVar, Long.MAX_VALUE, false);
    }

    public final k<k<T>> window(long j, TimeUnit timeUnit, s sVar, long j2) {
        return window(j, timeUnit, sVar, j2, false);
    }

    public final k<k<T>> window(long j, TimeUnit timeUnit, s sVar, long j2, boolean z) {
        return window(j, timeUnit, sVar, j2, z, bufferSize());
    }

    public final k<k<T>> window(long j, TimeUnit timeUnit, s sVar, long j2, boolean z, int i) {
        int i2 = i;
        a.a(i2, "bufferSize");
        Object obj = sVar;
        a.a(obj, "scheduler is null");
        Object obj2 = timeUnit;
        a.a(obj2, "unit is null");
        long j3 = j2;
        a.a(j3, AlbumLoader.COLUMN_COUNT);
        return xk.a(new bx(this, j, j, obj2, obj, j3, i2, z));
    }

    public final <B> k<k<T>> window(p<B> pVar) {
        return window((p) pVar, bufferSize());
    }

    public final <B> k<k<T>> window(p<B> pVar, int i) {
        a.a((Object) pVar, "boundary is null");
        a.a(i, "bufferSize");
        return xk.a(new ObservableWindowBoundary(this, pVar, i));
    }

    public final <U, V> k<k<T>> window(p<U> pVar, wm<? super U, ? extends p<V>> wmVar) {
        return window((p) pVar, (wm) wmVar, bufferSize());
    }

    public final <U, V> k<k<T>> window(p<U> pVar, wm<? super U, ? extends p<V>> wmVar, int i) {
        a.a((Object) pVar, "openingIndicator is null");
        a.a((Object) wmVar, "closingIndicator is null");
        a.a(i, "bufferSize");
        return xk.a(new bw(this, pVar, wmVar, i));
    }

    public final <B> k<k<T>> window(Callable<? extends p<B>> callable) {
        return window((Callable) callable, bufferSize());
    }

    public final <B> k<k<T>> window(Callable<? extends p<B>> callable, int i) {
        a.a((Object) callable, "boundary is null");
        a.a(i, "bufferSize");
        return xk.a(new ObservableWindowBoundarySupplier(this, callable, i));
    }

    public final <U, R> k<R> withLatestFrom(p<? extends U> pVar, wh<? super T, ? super U, ? extends R> whVar) {
        a.a((Object) pVar, "other is null");
        a.a((Object) whVar, "combiner is null");
        return xk.a(new ObservableWithLatestFrom(this, whVar, pVar));
    }

    public final <T1, T2, R> k<R> withLatestFrom(p<T1> pVar, p<T2> pVar2, wn<? super T, ? super T1, ? super T2, R> wnVar) {
        a.a((Object) pVar, "o1 is null");
        a.a((Object) pVar2, "o2 is null");
        a.a((Object) wnVar, "combiner is null");
        return withLatestFrom(new p[]{pVar, pVar2}, Functions.a((wn) wnVar));
    }

    public final <T1, T2, T3, R> k<R> withLatestFrom(p<T1> pVar, p<T2> pVar2, p<T3> pVar3, wo<? super T, ? super T1, ? super T2, ? super T3, R> woVar) {
        a.a((Object) pVar, "o1 is null");
        a.a((Object) pVar2, "o2 is null");
        a.a((Object) pVar3, "o3 is null");
        a.a((Object) woVar, "combiner is null");
        return withLatestFrom(new p[]{pVar, pVar2, pVar3}, Functions.a((wo) woVar));
    }

    public final <T1, T2, T3, T4, R> k<R> withLatestFrom(p<T1> pVar, p<T2> pVar2, p<T3> pVar3, p<T4> pVar4, wp<? super T, ? super T1, ? super T2, ? super T3, ? super T4, R> wpVar) {
        a.a((Object) pVar, "o1 is null");
        a.a((Object) pVar2, "o2 is null");
        a.a((Object) pVar3, "o3 is null");
        a.a((Object) pVar4, "o4 is null");
        a.a((Object) wpVar, "combiner is null");
        return withLatestFrom(new p[]{pVar, pVar2, pVar3, pVar4}, Functions.a((wp) wpVar));
    }

    public final <R> k<R> withLatestFrom(p<?>[] pVarArr, wm<? super Object[], R> wmVar) {
        a.a((Object) pVarArr, "others is null");
        a.a((Object) wmVar, "combiner is null");
        return xk.a(new ObservableWithLatestFromMany((p) this, (p[]) pVarArr, (wm) wmVar));
    }

    public final <R> k<R> withLatestFrom(Iterable<? extends p<?>> iterable, wm<? super Object[], R> wmVar) {
        a.a((Object) iterable, "others is null");
        a.a((Object) wmVar, "combiner is null");
        return xk.a(new ObservableWithLatestFromMany((p) this, (Iterable) iterable, (wm) wmVar));
    }

    public final <U, R> k<R> zipWith(Iterable<U> iterable, wh<? super T, ? super U, ? extends R> whVar) {
        a.a((Object) iterable, "other is null");
        a.a((Object) whVar, "zipper is null");
        return xk.a(new by(this, iterable, whVar));
    }

    public final <U, R> k<R> zipWith(p<? extends U> pVar, wh<? super T, ? super U, ? extends R> whVar) {
        a.a((Object) pVar, "other is null");
        return zip(this, pVar, whVar);
    }

    public final <U, R> k<R> zipWith(p<? extends U> pVar, wh<? super T, ? super U, ? extends R> whVar, boolean z) {
        return zip((p) this, (p) pVar, (wh) whVar, z);
    }

    public final <U, R> k<R> zipWith(p<? extends U> pVar, wh<? super T, ? super U, ? extends R> whVar, boolean z, int i) {
        return zip((p) this, (p) pVar, (wh) whVar, z, i);
    }

    public final TestObserver<T> test() {
        r testObserver = new TestObserver();
        subscribe(testObserver);
        return testObserver;
    }

    public final TestObserver<T> test(boolean z) {
        r testObserver = new TestObserver();
        if (z) {
            testObserver.dispose();
        }
        subscribe(testObserver);
        return testObserver;
    }
}
