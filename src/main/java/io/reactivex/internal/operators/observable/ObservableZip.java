package io.reactivex.internal.operators.observable;

import defpackage.wm;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableZip<T, R> extends k<R> {
    final p<? extends T>[] a;
    final Iterable<? extends p<? extends T>> b;
    final wm<? super Object[], ? extends R> c;
    final int d;
    final boolean e;

    static final class ZipCoordinator<T, R> extends AtomicInteger implements b {
        private static final long serialVersionUID = 2983708048395377667L;
        volatile boolean cancelled;
        final boolean delayError;
        final r<? super R> downstream;
        final a<T, R>[] observers;
        final T[] row;
        final wm<? super Object[], ? extends R> zipper;

        ZipCoordinator(r<? super R> rVar, wm<? super Object[], ? extends R> wmVar, int i, boolean z) {
            this.downstream = rVar;
            this.zipper = wmVar;
            this.observers = new a[i];
            this.row = new Object[i];
            this.delayError = z;
        }

        public void subscribe(p<? extends T>[] pVarArr, int i) {
            a[] aVarArr = this.observers;
            int length = aVarArr.length;
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                aVarArr[i3] = new a(this, i);
            }
            lazySet(0);
            this.downstream.onSubscribe(this);
            while (i2 < length && !this.cancelled) {
                pVarArr[i2].subscribe(aVarArr[i2]);
                i2++;
            }
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelSources();
                if (getAndIncrement() == 0) {
                    clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* Access modifiers changed, original: 0000 */
        public void cancel() {
            clear();
            cancelSources();
        }

        /* Access modifiers changed, original: 0000 */
        public void cancelSources() {
            for (a a : this.observers) {
                a.a();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void clear() {
            for (a aVar : this.observers) {
                aVar.b.clear();
            }
        }

        public void drain() {
            if (getAndIncrement() == 0) {
                a[] aVarArr = this.observers;
                r rVar = this.downstream;
                Object[] objArr = this.row;
                boolean z = this.delayError;
                int i = 1;
                while (true) {
                    int i2 = 0;
                    int i3 = 0;
                    for (a aVar : aVarArr) {
                        if (objArr[i2] == null) {
                            boolean z2 = aVar.c;
                            Object poll = aVar.b.poll();
                            boolean z3 = poll == null;
                            if (!checkTerminated(z2, z3, rVar, z, aVar)) {
                                if (z3) {
                                    i3++;
                                } else {
                                    objArr[i2] = poll;
                                }
                            } else {
                                return;
                            }
                        } else if (aVar.c && !z) {
                            Throwable th = aVar.d;
                            if (th != null) {
                                cancel();
                                rVar.onError(th);
                                return;
                            }
                        }
                        i2++;
                    }
                    if (i3 != 0) {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        try {
                            rVar.onNext(io.reactivex.internal.functions.a.a(this.zipper.apply(objArr.clone()), "The zipper returned a null value"));
                            Arrays.fill(objArr, null);
                        } catch (Throwable th2) {
                            io.reactivex.exceptions.a.b(th2);
                            cancel();
                            rVar.onError(th2);
                            return;
                        }
                    }
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public boolean checkTerminated(boolean z, boolean z2, r<? super R> rVar, boolean z3, a<?, ?> aVar) {
            if (this.cancelled) {
                cancel();
                return true;
            }
            if (z) {
                Throwable th;
                if (!z3) {
                    th = aVar.d;
                    if (th != null) {
                        cancel();
                        rVar.onError(th);
                        return true;
                    } else if (z2) {
                        cancel();
                        rVar.onComplete();
                        return true;
                    }
                } else if (z2) {
                    th = aVar.d;
                    cancel();
                    if (th != null) {
                        rVar.onError(th);
                    } else {
                        rVar.onComplete();
                    }
                    return true;
                }
            }
            return false;
        }
    }

    static final class a<T, R> implements r<T> {
        final ZipCoordinator<T, R> a;
        final io.reactivex.internal.queue.a<T> b;
        volatile boolean c;
        Throwable d;
        final AtomicReference<b> e = new AtomicReference();

        a(ZipCoordinator<T, R> zipCoordinator, int i) {
            this.a = zipCoordinator;
            this.b = new io.reactivex.internal.queue.a(i);
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.e, bVar);
        }

        public void onNext(T t) {
            this.b.offer(t);
            this.a.drain();
        }

        public void onError(Throwable th) {
            this.d = th;
            this.c = true;
            this.a.drain();
        }

        public void onComplete() {
            this.c = true;
            this.a.drain();
        }

        public void a() {
            DisposableHelper.dispose(this.e);
        }
    }

    public ObservableZip(p<? extends T>[] pVarArr, Iterable<? extends p<? extends T>> iterable, wm<? super Object[], ? extends R> wmVar, int i, boolean z) {
        this.a = pVarArr;
        this.b = iterable;
        this.c = wmVar;
        this.d = i;
        this.e = z;
    }

    public void subscribeActual(r<? super R> rVar) {
        p[] pVarArr;
        int i;
        p[] pVarArr2 = this.a;
        if (pVarArr2 == null) {
            Object obj = new k[8];
            pVarArr = obj;
            i = 0;
            for (p pVar : this.b) {
                if (i == pVarArr.length) {
                    Object obj2 = new p[((i >> 2) + i)];
                    System.arraycopy(pVarArr, 0, obj2, 0, i);
                    pVarArr = obj2;
                }
                int i2 = i + 1;
                pVarArr[i] = pVar;
                i = i2;
            }
        } else {
            pVarArr = pVarArr2;
            i = pVarArr2.length;
        }
        if (i == 0) {
            EmptyDisposable.complete((r) rVar);
        } else {
            new ZipCoordinator(rVar, this.c, i, this.e).subscribe(pVarArr, this.d);
        }
    }
}
