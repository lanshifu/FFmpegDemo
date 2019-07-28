package io.reactivex.internal.operators.observable;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableAmb<T> extends k<T> {
    final p<? extends T>[] a;
    final Iterable<? extends p<? extends T>> b;

    static final class AmbInnerObserver<T> extends AtomicReference<b> implements r<T> {
        private static final long serialVersionUID = -1185974347409665484L;
        final r<? super T> downstream;
        final int index;
        final a<T> parent;
        boolean won;

        AmbInnerObserver(a<T> aVar, int i, r<? super T> rVar) {
            this.parent = aVar;
            this.index = i;
            this.downstream = rVar;
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        public void onNext(T t) {
            if (this.won) {
                this.downstream.onNext(t);
            } else if (this.parent.a(this.index)) {
                this.won = true;
                this.downstream.onNext(t);
            } else {
                ((b) get()).dispose();
            }
        }

        public void onError(Throwable th) {
            if (this.won) {
                this.downstream.onError(th);
            } else if (this.parent.a(this.index)) {
                this.won = true;
                this.downstream.onError(th);
            } else {
                xk.a(th);
            }
        }

        public void onComplete() {
            if (this.won) {
                this.downstream.onComplete();
            } else if (this.parent.a(this.index)) {
                this.won = true;
                this.downstream.onComplete();
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }

    static final class a<T> implements b {
        final r<? super T> a;
        final AmbInnerObserver<T>[] b;
        final AtomicInteger c = new AtomicInteger();

        a(r<? super T> rVar, int i) {
            this.a = rVar;
            this.b = new AmbInnerObserver[i];
        }

        public void a(p<? extends T>[] pVarArr) {
            AmbInnerObserver[] ambInnerObserverArr = this.b;
            int length = ambInnerObserverArr.length;
            int i = 0;
            int i2 = 0;
            while (i2 < length) {
                int i3 = i2 + 1;
                ambInnerObserverArr[i2] = new AmbInnerObserver(this, i3, this.a);
                i2 = i3;
            }
            this.c.lazySet(0);
            this.a.onSubscribe(this);
            while (i < length && this.c.get() == 0) {
                pVarArr[i].subscribe(ambInnerObserverArr[i]);
                i++;
            }
        }

        public boolean a(int i) {
            int i2 = this.c.get();
            boolean z = true;
            int i3 = 0;
            if (i2 != 0) {
                if (i2 != i) {
                    z = false;
                }
                return z;
            } else if (!this.c.compareAndSet(0, i)) {
                return false;
            } else {
                AmbInnerObserver[] ambInnerObserverArr = this.b;
                int length = ambInnerObserverArr.length;
                while (i3 < length) {
                    int i4 = i3 + 1;
                    if (i4 != i) {
                        ambInnerObserverArr[i3].dispose();
                    }
                    i3 = i4;
                }
                return true;
            }
        }

        public void dispose() {
            if (this.c.get() != -1) {
                this.c.lazySet(-1);
                for (AmbInnerObserver dispose : this.b) {
                    dispose.dispose();
                }
            }
        }

        public boolean isDisposed() {
            return this.c.get() == -1;
        }
    }

    public ObservableAmb(p<? extends T>[] pVarArr, Iterable<? extends p<? extends T>> iterable) {
        this.a = pVarArr;
        this.b = iterable;
    }

    public void subscribeActual(r<? super T> rVar) {
        int i;
        p[] pVarArr = this.a;
        if (pVarArr == null) {
            pVarArr = new k[8];
            try {
                i = 0;
                for (p pVar : this.b) {
                    if (pVar == null) {
                        EmptyDisposable.error(new NullPointerException("One of the sources is null"), (r) rVar);
                        return;
                    }
                    if (i == pVarArr.length) {
                        p[] pVarArr2 = new p[((i >> 2) + i)];
                        System.arraycopy(pVarArr, 0, pVarArr2, 0, i);
                        pVarArr = pVarArr2;
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
            EmptyDisposable.complete((r) rVar);
        } else if (i == 1) {
            pVarArr[0].subscribe(rVar);
        } else {
            new a(rVar, i).a(pVarArr);
        }
    }
}
