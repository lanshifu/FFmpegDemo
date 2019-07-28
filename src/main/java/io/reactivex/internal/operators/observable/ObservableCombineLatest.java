package io.reactivex.internal.operators.observable;

import defpackage.wm;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableCombineLatest<T, R> extends k<R> {
    final p<? extends T>[] a;
    final Iterable<? extends p<? extends T>> b;
    final wm<? super Object[], ? extends R> c;
    final int d;
    final boolean e;

    static final class CombinerObserver<T, R> extends AtomicReference<b> implements r<T> {
        private static final long serialVersionUID = -4823716997131257941L;
        final int index;
        final LatestCoordinator<T, R> parent;

        CombinerObserver(LatestCoordinator<T, R> latestCoordinator, int i) {
            this.parent = latestCoordinator;
            this.index = i;
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        public void onNext(T t) {
            this.parent.innerNext(this.index, t);
        }

        public void onError(Throwable th) {
            this.parent.innerError(this.index, th);
        }

        public void onComplete() {
            this.parent.innerComplete(this.index);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }

    static final class LatestCoordinator<T, R> extends AtomicInteger implements b {
        private static final long serialVersionUID = 8567835998786448817L;
        int active;
        volatile boolean cancelled;
        final wm<? super Object[], ? extends R> combiner;
        int complete;
        final boolean delayError;
        volatile boolean done;
        final r<? super R> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        Object[] latest;
        final CombinerObserver<T, R>[] observers;
        final a<Object[]> queue;

        LatestCoordinator(r<? super R> rVar, wm<? super Object[], ? extends R> wmVar, int i, int i2, boolean z) {
            this.downstream = rVar;
            this.combiner = wmVar;
            this.delayError = z;
            this.latest = new Object[i];
            CombinerObserver[] combinerObserverArr = new CombinerObserver[i];
            for (int i3 = 0; i3 < i; i3++) {
                combinerObserverArr[i3] = new CombinerObserver(this, i3);
            }
            this.observers = combinerObserverArr;
            this.queue = new a(i2);
        }

        public void subscribe(p<? extends T>[] pVarArr) {
            CombinerObserver[] combinerObserverArr = this.observers;
            int length = combinerObserverArr.length;
            this.downstream.onSubscribe(this);
            for (int i = 0; i < length && !this.done && !this.cancelled; i++) {
                pVarArr[i].subscribe(combinerObserverArr[i]);
            }
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelSources();
                if (getAndIncrement() == 0) {
                    clear(this.queue);
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* Access modifiers changed, original: 0000 */
        public void cancelSources() {
            for (CombinerObserver dispose : this.observers) {
                dispose.dispose();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void clear(a<?> aVar) {
            synchronized (this) {
                this.latest = null;
            }
            aVar.clear();
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                a aVar = this.queue;
                r rVar = this.downstream;
                boolean z = this.delayError;
                int i = 1;
                while (!this.cancelled) {
                    if (z || this.errors.get() == null) {
                        boolean z2 = this.done;
                        Object[] objArr = (Object[]) aVar.poll();
                        Object obj = objArr == null ? 1 : null;
                        if (z2 && obj != null) {
                            clear(aVar);
                            Throwable terminate = this.errors.terminate();
                            if (terminate == null) {
                                rVar.onComplete();
                            } else {
                                rVar.onError(terminate);
                            }
                            return;
                        } else if (obj != null) {
                            i = addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        } else {
                            try {
                                rVar.onNext(io.reactivex.internal.functions.a.a(this.combiner.apply(objArr), "The combiner returned a null value"));
                            } catch (Throwable th) {
                                io.reactivex.exceptions.a.b(th);
                                this.errors.addThrowable(th);
                                cancelSources();
                                clear(aVar);
                                rVar.onError(this.errors.terminate());
                                return;
                            }
                        }
                    }
                    cancelSources();
                    clear(aVar);
                    rVar.onError(this.errors.terminate());
                    return;
                }
                clear(aVar);
            }
        }

        /* Access modifiers changed, original: 0000 */
        /* JADX WARNING: Missing block: B:14:0x0023, code skipped:
            if (r4 == null) goto L_0x0028;
     */
        /* JADX WARNING: Missing block: B:15:0x0025, code skipped:
            drain();
     */
        /* JADX WARNING: Missing block: B:16:0x0028, code skipped:
            return;
     */
        public void innerNext(int r4, T r5) {
            /*
            r3 = this;
            monitor-enter(r3);
            r0 = r3.latest;	 Catch:{ all -> 0x0029 }
            if (r0 != 0) goto L_0x0007;
        L_0x0005:
            monitor-exit(r3);	 Catch:{ all -> 0x0029 }
            return;
        L_0x0007:
            r1 = r0[r4];	 Catch:{ all -> 0x0029 }
            r2 = r3.active;	 Catch:{ all -> 0x0029 }
            if (r1 != 0) goto L_0x0011;
        L_0x000d:
            r2 = r2 + 1;
            r3.active = r2;	 Catch:{ all -> 0x0029 }
        L_0x0011:
            r0[r4] = r5;	 Catch:{ all -> 0x0029 }
            r4 = r0.length;	 Catch:{ all -> 0x0029 }
            if (r2 != r4) goto L_0x0021;
        L_0x0016:
            r4 = r3.queue;	 Catch:{ all -> 0x0029 }
            r5 = r0.clone();	 Catch:{ all -> 0x0029 }
            r4.offer(r5);	 Catch:{ all -> 0x0029 }
            r4 = 1;
            goto L_0x0022;
        L_0x0021:
            r4 = 0;
        L_0x0022:
            monitor-exit(r3);	 Catch:{ all -> 0x0029 }
            if (r4 == 0) goto L_0x0028;
        L_0x0025:
            r3.drain();
        L_0x0028:
            return;
        L_0x0029:
            r4 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0029 }
            throw r4;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableCombineLatest$LatestCoordinator.innerNext(int, java.lang.Object):void");
        }

        /* Access modifiers changed, original: 0000 */
        /* JADX WARNING: Missing block: B:16:0x0023, code skipped:
            if (r1 == r4.length) goto L_0x0025;
     */
        public void innerError(int r3, java.lang.Throwable r4) {
            /*
            r2 = this;
            r0 = r2.errors;
            r0 = r0.addThrowable(r4);
            if (r0 == 0) goto L_0x0036;
        L_0x0008:
            r4 = r2.delayError;
            r0 = 1;
            if (r4 == 0) goto L_0x002c;
        L_0x000d:
            monitor-enter(r2);
            r4 = r2.latest;	 Catch:{ all -> 0x0029 }
            if (r4 != 0) goto L_0x0014;
        L_0x0012:
            monitor-exit(r2);	 Catch:{ all -> 0x0029 }
            return;
        L_0x0014:
            r3 = r4[r3];	 Catch:{ all -> 0x0029 }
            if (r3 != 0) goto L_0x001a;
        L_0x0018:
            r3 = 1;
            goto L_0x001b;
        L_0x001a:
            r3 = 0;
        L_0x001b:
            if (r3 != 0) goto L_0x0025;
        L_0x001d:
            r1 = r2.complete;	 Catch:{ all -> 0x0029 }
            r1 = r1 + r0;
            r2.complete = r1;	 Catch:{ all -> 0x0029 }
            r4 = r4.length;	 Catch:{ all -> 0x0029 }
            if (r1 != r4) goto L_0x0027;
        L_0x0025:
            r2.done = r0;	 Catch:{ all -> 0x0029 }
        L_0x0027:
            monitor-exit(r2);	 Catch:{ all -> 0x0029 }
            goto L_0x002d;
        L_0x0029:
            r3 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x0029 }
            throw r3;
        L_0x002c:
            r3 = 1;
        L_0x002d:
            if (r3 == 0) goto L_0x0032;
        L_0x002f:
            r2.cancelSources();
        L_0x0032:
            r2.drain();
            goto L_0x0039;
        L_0x0036:
            defpackage.xk.a(r4);
        L_0x0039:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableCombineLatest$LatestCoordinator.innerError(int, java.lang.Throwable):void");
        }

        /* Access modifiers changed, original: 0000 */
        /* JADX WARNING: Missing block: B:12:0x0017, code skipped:
            if (r2 == r0.length) goto L_0x0019;
     */
        /* JADX WARNING: Missing block: B:15:0x001c, code skipped:
            if (r4 == null) goto L_0x0021;
     */
        /* JADX WARNING: Missing block: B:16:0x001e, code skipped:
            cancelSources();
     */
        /* JADX WARNING: Missing block: B:17:0x0021, code skipped:
            drain();
     */
        /* JADX WARNING: Missing block: B:18:0x0024, code skipped:
            return;
     */
        public void innerComplete(int r4) {
            /*
            r3 = this;
            monitor-enter(r3);
            r0 = r3.latest;	 Catch:{ all -> 0x0025 }
            if (r0 != 0) goto L_0x0007;
        L_0x0005:
            monitor-exit(r3);	 Catch:{ all -> 0x0025 }
            return;
        L_0x0007:
            r4 = r0[r4];	 Catch:{ all -> 0x0025 }
            r1 = 1;
            if (r4 != 0) goto L_0x000e;
        L_0x000c:
            r4 = 1;
            goto L_0x000f;
        L_0x000e:
            r4 = 0;
        L_0x000f:
            if (r4 != 0) goto L_0x0019;
        L_0x0011:
            r2 = r3.complete;	 Catch:{ all -> 0x0025 }
            r2 = r2 + r1;
            r3.complete = r2;	 Catch:{ all -> 0x0025 }
            r0 = r0.length;	 Catch:{ all -> 0x0025 }
            if (r2 != r0) goto L_0x001b;
        L_0x0019:
            r3.done = r1;	 Catch:{ all -> 0x0025 }
        L_0x001b:
            monitor-exit(r3);	 Catch:{ all -> 0x0025 }
            if (r4 == 0) goto L_0x0021;
        L_0x001e:
            r3.cancelSources();
        L_0x0021:
            r3.drain();
            return;
        L_0x0025:
            r4 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0025 }
            throw r4;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableCombineLatest$LatestCoordinator.innerComplete(int):void");
        }
    }

    public ObservableCombineLatest(p<? extends T>[] pVarArr, Iterable<? extends p<? extends T>> iterable, wm<? super Object[], ? extends R> wmVar, int i, boolean z) {
        this.a = pVarArr;
        this.b = iterable;
        this.c = wmVar;
        this.d = i;
        this.e = z;
    }

    public void subscribeActual(r<? super R> rVar) {
        int i;
        p[] pVarArr = this.a;
        if (pVarArr == null) {
            Object obj = new k[8];
            Object obj2 = obj;
            int i2 = 0;
            for (p pVar : this.b) {
                if (i2 == obj2.length) {
                    Object obj3 = new p[((i2 >> 2) + i2)];
                    System.arraycopy(obj2, 0, obj3, 0, i2);
                    obj2 = obj3;
                }
                i = i2 + 1;
                obj2[i2] = pVar;
                i2 = i;
            }
            i = i2;
            pVarArr = obj2;
        } else {
            i = pVarArr.length;
        }
        if (i == 0) {
            EmptyDisposable.complete((r) rVar);
            return;
        }
        new LatestCoordinator(rVar, this.c, i, this.d, this.e).subscribe(pVarArr);
    }
}
