package io.reactivex.internal.operators.observable;

import defpackage.wm;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.f;
import io.reactivex.p;
import io.reactivex.r;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

public final class ObservableWithLatestFromMany<T, R> extends a<T, R> {
    final p<?>[] b;
    final Iterable<? extends p<?>> c;
    final wm<? super Object[], R> d;

    static final class WithLatestFromObserver<T, R> extends AtomicInteger implements b, r<T> {
        private static final long serialVersionUID = 1577321883966341961L;
        final wm<? super Object[], R> combiner;
        volatile boolean done;
        final r<? super R> downstream;
        final AtomicThrowable error;
        final WithLatestInnerObserver[] observers;
        final AtomicReference<b> upstream;
        final AtomicReferenceArray<Object> values;

        WithLatestFromObserver(r<? super R> rVar, wm<? super Object[], R> wmVar, int i) {
            this.downstream = rVar;
            this.combiner = wmVar;
            WithLatestInnerObserver[] withLatestInnerObserverArr = new WithLatestInnerObserver[i];
            for (int i2 = 0; i2 < i; i2++) {
                withLatestInnerObserverArr[i2] = new WithLatestInnerObserver(this, i2);
            }
            this.observers = withLatestInnerObserverArr;
            this.values = new AtomicReferenceArray(i);
            this.upstream = new AtomicReference();
            this.error = new AtomicThrowable();
        }

        /* Access modifiers changed, original: 0000 */
        public void subscribe(p<?>[] pVarArr, int i) {
            WithLatestInnerObserver[] withLatestInnerObserverArr = this.observers;
            AtomicReference atomicReference = this.upstream;
            for (int i2 = 0; i2 < i && !DisposableHelper.isDisposed((b) atomicReference.get()) && !this.done; i2++) {
                pVarArr[i2].subscribe(withLatestInnerObserverArr[i2]);
            }
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.upstream, bVar);
        }

        public void onNext(T t) {
            if (!this.done) {
                AtomicReferenceArray atomicReferenceArray = this.values;
                int length = atomicReferenceArray.length();
                Object[] objArr = new Object[(length + 1)];
                int i = 0;
                objArr[0] = t;
                while (i < length) {
                    Object obj = atomicReferenceArray.get(i);
                    if (obj != null) {
                        i++;
                        objArr[i] = obj;
                    } else {
                        return;
                    }
                }
                try {
                    f.a(this.downstream, io.reactivex.internal.functions.a.a(this.combiner.apply(objArr), "combiner returned a null value"), (AtomicInteger) this, this.error);
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    dispose();
                    onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                xk.a(th);
                return;
            }
            this.done = true;
            cancelAllBut(-1);
            f.a(this.downstream, th, (AtomicInteger) this, this.error);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                cancelAllBut(-1);
                f.a(this.downstream, (AtomicInteger) this, this.error);
            }
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) this.upstream.get());
        }

        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            for (WithLatestInnerObserver dispose : this.observers) {
                dispose.dispose();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void innerNext(int i, Object obj) {
            this.values.set(i, obj);
        }

        /* Access modifiers changed, original: 0000 */
        public void innerError(int i, Throwable th) {
            this.done = true;
            DisposableHelper.dispose(this.upstream);
            cancelAllBut(i);
            f.a(this.downstream, th, (AtomicInteger) this, this.error);
        }

        /* Access modifiers changed, original: 0000 */
        public void innerComplete(int i, boolean z) {
            if (!z) {
                this.done = true;
                cancelAllBut(i);
                f.a(this.downstream, (AtomicInteger) this, this.error);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void cancelAllBut(int i) {
            WithLatestInnerObserver[] withLatestInnerObserverArr = this.observers;
            for (int i2 = 0; i2 < withLatestInnerObserverArr.length; i2++) {
                if (i2 != i) {
                    withLatestInnerObserverArr[i2].dispose();
                }
            }
        }
    }

    static final class WithLatestInnerObserver extends AtomicReference<b> implements r<Object> {
        private static final long serialVersionUID = 3256684027868224024L;
        boolean hasValue;
        final int index;
        final WithLatestFromObserver<?, ?> parent;

        WithLatestInnerObserver(WithLatestFromObserver<?, ?> withLatestFromObserver, int i) {
            this.parent = withLatestFromObserver;
            this.index = i;
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        public void onNext(Object obj) {
            if (!this.hasValue) {
                this.hasValue = true;
            }
            this.parent.innerNext(this.index, obj);
        }

        public void onError(Throwable th) {
            this.parent.innerError(this.index, th);
        }

        public void onComplete() {
            this.parent.innerComplete(this.index, this.hasValue);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }

    final class a implements wm<T, R> {
        a() {
        }

        public R apply(T t) throws Exception {
            return io.reactivex.internal.functions.a.a(ObservableWithLatestFromMany.this.d.apply(new Object[]{t}), "The combiner returned a null value");
        }
    }

    public ObservableWithLatestFromMany(p<T> pVar, p<?>[] pVarArr, wm<? super Object[], R> wmVar) {
        super(pVar);
        this.b = pVarArr;
        this.c = null;
        this.d = wmVar;
    }

    public ObservableWithLatestFromMany(p<T> pVar, Iterable<? extends p<?>> iterable, wm<? super Object[], R> wmVar) {
        super(pVar);
        this.b = null;
        this.c = iterable;
        this.d = wmVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super R> rVar) {
        int i;
        p[] pVarArr = this.b;
        if (pVarArr == null) {
            pVarArr = new p[8];
            try {
                i = 0;
                for (p pVar : this.c) {
                    if (i == pVarArr.length) {
                        pVarArr = (p[]) Arrays.copyOf(pVarArr, (i >> 1) + i);
                    }
                    int i2 = i + 1;
                    pVarArr[i] = pVar;
                    i = i2;
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                EmptyDisposable.error(th, (r) rVar);
                return;
            }
        }
        i = pVarArr.length;
        if (i == 0) {
            new ax(this.a, new a()).subscribeActual(rVar);
            return;
        }
        WithLatestFromObserver withLatestFromObserver = new WithLatestFromObserver(rVar, this.d, i);
        rVar.onSubscribe(withLatestFromObserver);
        withLatestFromObserver.subscribe(pVarArr, i);
        this.a.subscribe(withLatestFromObserver);
    }
}
