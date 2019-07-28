package io.reactivex.internal.operators.observable;

import defpackage.wi;
import defpackage.wx;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.ArrayCompositeDisposable;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.t;
import io.reactivex.u;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableSequenceEqualSingle<T> extends t<Boolean> implements wx<Boolean> {
    final p<? extends T> a;
    final p<? extends T> b;
    final wi<? super T, ? super T> c;
    final int d;

    static final class EqualCoordinator<T> extends AtomicInteger implements b {
        private static final long serialVersionUID = -6178010334400373240L;
        volatile boolean cancelled;
        final wi<? super T, ? super T> comparer;
        final u<? super Boolean> downstream;
        final p<? extends T> first;
        final a<T>[] observers;
        final ArrayCompositeDisposable resources = new ArrayCompositeDisposable(2);
        final p<? extends T> second;
        T v1;
        T v2;

        EqualCoordinator(u<? super Boolean> uVar, int i, p<? extends T> pVar, p<? extends T> pVar2, wi<? super T, ? super T> wiVar) {
            this.downstream = uVar;
            this.first = pVar;
            this.second = pVar2;
            this.comparer = wiVar;
            r3 = new a[2];
            this.observers = r3;
            r3[0] = new a(this, 0, i);
            r3[1] = new a(this, 1, i);
        }

        /* Access modifiers changed, original: 0000 */
        public boolean setDisposable(b bVar, int i) {
            return this.resources.setResource(i, bVar);
        }

        /* Access modifiers changed, original: 0000 */
        public void subscribe() {
            a[] aVarArr = this.observers;
            this.first.subscribe(aVarArr[0]);
            this.second.subscribe(aVarArr[1]);
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.resources.dispose();
                if (getAndIncrement() == 0) {
                    a[] aVarArr = this.observers;
                    aVarArr[0].b.clear();
                    aVarArr[1].b.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* Access modifiers changed, original: 0000 */
        public void cancel(io.reactivex.internal.queue.a<T> aVar, io.reactivex.internal.queue.a<T> aVar2) {
            this.cancelled = true;
            aVar.clear();
            aVar2.clear();
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                a[] aVarArr = this.observers;
                a aVar = aVarArr[0];
                io.reactivex.internal.queue.a aVar2 = aVar.b;
                a aVar3 = aVarArr[1];
                io.reactivex.internal.queue.a aVar4 = aVar3.b;
                int i = 1;
                while (!this.cancelled) {
                    boolean z = aVar.d;
                    if (z) {
                        Throwable th = aVar.e;
                        if (th != null) {
                            cancel(aVar2, aVar4);
                            this.downstream.onError(th);
                            return;
                        }
                    }
                    boolean z2 = aVar3.d;
                    if (z2) {
                        Throwable th2 = aVar3.e;
                        if (th2 != null) {
                            cancel(aVar2, aVar4);
                            this.downstream.onError(th2);
                            return;
                        }
                    }
                    if (this.v1 == null) {
                        this.v1 = aVar2.poll();
                    }
                    Object obj = this.v1 == null ? 1 : null;
                    if (this.v2 == null) {
                        this.v2 = aVar4.poll();
                    }
                    Object obj2 = this.v2 == null ? 1 : null;
                    if (z && z2 && obj != null && obj2 != null) {
                        this.downstream.onSuccess(Boolean.valueOf(true));
                        return;
                    } else if (z && z2 && obj != obj2) {
                        cancel(aVar2, aVar4);
                        this.downstream.onSuccess(Boolean.valueOf(false));
                        return;
                    } else {
                        if (obj == null && obj2 == null) {
                            try {
                                if (this.comparer.a(this.v1, this.v2)) {
                                    this.v1 = null;
                                    this.v2 = null;
                                } else {
                                    cancel(aVar2, aVar4);
                                    this.downstream.onSuccess(Boolean.valueOf(false));
                                    return;
                                }
                            } catch (Throwable th3) {
                                io.reactivex.exceptions.a.b(th3);
                                cancel(aVar2, aVar4);
                                this.downstream.onError(th3);
                                return;
                            }
                        }
                        if (obj != null || obj2 != null) {
                            i = addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        }
                    }
                }
                aVar2.clear();
                aVar4.clear();
            }
        }
    }

    static final class a<T> implements r<T> {
        final EqualCoordinator<T> a;
        final io.reactivex.internal.queue.a<T> b;
        final int c;
        volatile boolean d;
        Throwable e;

        a(EqualCoordinator<T> equalCoordinator, int i, int i2) {
            this.a = equalCoordinator;
            this.c = i;
            this.b = new io.reactivex.internal.queue.a(i2);
        }

        public void onSubscribe(b bVar) {
            this.a.setDisposable(bVar, this.c);
        }

        public void onNext(T t) {
            this.b.offer(t);
            this.a.drain();
        }

        public void onError(Throwable th) {
            this.e = th;
            this.d = true;
            this.a.drain();
        }

        public void onComplete() {
            this.d = true;
            this.a.drain();
        }
    }

    public ObservableSequenceEqualSingle(p<? extends T> pVar, p<? extends T> pVar2, wi<? super T, ? super T> wiVar, int i) {
        this.a = pVar;
        this.b = pVar2;
        this.c = wiVar;
        this.d = i;
    }

    public void b(u<? super Boolean> uVar) {
        EqualCoordinator equalCoordinator = new EqualCoordinator(uVar, this.d, this.a, this.b, this.c);
        uVar.onSubscribe(equalCoordinator);
        equalCoordinator.subscribe();
    }

    public k<Boolean> j_() {
        return xk.a(new ObservableSequenceEqual(this.a, this.b, this.c, this.d));
    }
}
