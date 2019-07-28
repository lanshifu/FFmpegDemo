package io.reactivex.internal.operators.observable;

import defpackage.wm;
import defpackage.xk;
import io.reactivex.disposables.a;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableBufferBoundary<T, U extends Collection<? super T>, Open, Close> extends a<T, U> {
    final Callable<U> b;
    final p<? extends Open> c;
    final wm<? super Open, ? extends p<? extends Close>> d;

    static final class BufferBoundaryObserver<T, C extends Collection<? super T>, Open, Close> extends AtomicInteger implements b, r<T> {
        private static final long serialVersionUID = -8466418554264089604L;
        final wm<? super Open, ? extends p<? extends Close>> bufferClose;
        final p<? extends Open> bufferOpen;
        final Callable<C> bufferSupplier;
        Map<Long, C> buffers = new LinkedHashMap();
        volatile boolean cancelled;
        volatile boolean done;
        final r<? super C> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        long index;
        final a observers = new a();
        final io.reactivex.internal.queue.a<C> queue = new io.reactivex.internal.queue.a(k.bufferSize());
        final AtomicReference<b> upstream = new AtomicReference();

        static final class BufferOpenObserver<Open> extends AtomicReference<b> implements b, r<Open> {
            private static final long serialVersionUID = -8498650778633225126L;
            final BufferBoundaryObserver<?, ?, Open, ?> parent;

            BufferOpenObserver(BufferBoundaryObserver<?, ?, Open, ?> bufferBoundaryObserver) {
                this.parent = bufferBoundaryObserver;
            }

            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            public void onNext(Open open) {
                this.parent.open(open);
            }

            public void onError(Throwable th) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.boundaryError(this, th);
            }

            public void onComplete() {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.openComplete(this);
            }

            public void dispose() {
                DisposableHelper.dispose(this);
            }

            public boolean isDisposed() {
                return get() == DisposableHelper.DISPOSED;
            }
        }

        BufferBoundaryObserver(r<? super C> rVar, p<? extends Open> pVar, wm<? super Open, ? extends p<? extends Close>> wmVar, Callable<C> callable) {
            this.downstream = rVar;
            this.bufferSupplier = callable;
            this.bufferOpen = pVar;
            this.bufferClose = wmVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this.upstream, bVar)) {
                bVar = new BufferOpenObserver(this);
                this.observers.a(bVar);
                this.bufferOpen.subscribe(bVar);
            }
        }

        public void onNext(T t) {
            synchronized (this) {
                Map map = this.buffers;
                if (map == null) {
                    return;
                }
                for (Collection add : map.values()) {
                    add.add(t);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                this.observers.dispose();
                synchronized (this) {
                    this.buffers = null;
                }
                this.done = true;
                drain();
                return;
            }
            xk.a(th);
        }

        public void onComplete() {
            this.observers.dispose();
            synchronized (this) {
                Map map = this.buffers;
                if (map == null) {
                    return;
                }
                for (Collection offer : map.values()) {
                    this.queue.offer(offer);
                }
                this.buffers = null;
                this.done = true;
                drain();
            }
        }

        public void dispose() {
            if (DisposableHelper.dispose(this.upstream)) {
                this.cancelled = true;
                this.observers.dispose();
                synchronized (this) {
                    this.buffers = null;
                }
                if (getAndIncrement() != 0) {
                    this.queue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) this.upstream.get());
        }

        /* Access modifiers changed, original: 0000 */
        public void open(Open open) {
            try {
                Collection collection = (Collection) io.reactivex.internal.functions.a.a(this.bufferSupplier.call(), "The bufferSupplier returned a null Collection");
                p pVar = (p) io.reactivex.internal.functions.a.a(this.bufferClose.apply(open), "The bufferClose returned a null ObservableSource");
                long j = this.index;
                this.index = 1 + j;
                synchronized (this) {
                    Map map = this.buffers;
                    if (map == null) {
                        return;
                    }
                    map.put(Long.valueOf(j), collection);
                    b bufferCloseObserver = new BufferCloseObserver(this, j);
                    this.observers.a(bufferCloseObserver);
                    pVar.subscribe(bufferCloseObserver);
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                DisposableHelper.dispose(this.upstream);
                onError(th);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void openComplete(BufferOpenObserver<Open> bufferOpenObserver) {
            this.observers.c(bufferOpenObserver);
            if (this.observers.a() == 0) {
                DisposableHelper.dispose(this.upstream);
                this.done = true;
                drain();
            }
        }

        /* Access modifiers changed, original: 0000 */
        /* JADX WARNING: Missing block: B:12:0x002d, code skipped:
            if (r4 == null) goto L_0x0031;
     */
        /* JADX WARNING: Missing block: B:13:0x002f, code skipped:
            r3.done = true;
     */
        /* JADX WARNING: Missing block: B:14:0x0031, code skipped:
            drain();
     */
        /* JADX WARNING: Missing block: B:15:0x0034, code skipped:
            return;
     */
        public void close(io.reactivex.internal.operators.observable.ObservableBufferBoundary.BufferCloseObserver<T, C> r4, long r5) {
            /*
            r3 = this;
            r0 = r3.observers;
            r0.c(r4);
            r4 = r3.observers;
            r4 = r4.a();
            r0 = 1;
            if (r4 != 0) goto L_0x0015;
        L_0x000e:
            r4 = r3.upstream;
            io.reactivex.internal.disposables.DisposableHelper.dispose(r4);
            r4 = 1;
            goto L_0x0016;
        L_0x0015:
            r4 = 0;
        L_0x0016:
            monitor-enter(r3);
            r1 = r3.buffers;	 Catch:{ all -> 0x0035 }
            if (r1 != 0) goto L_0x001d;
        L_0x001b:
            monitor-exit(r3);	 Catch:{ all -> 0x0035 }
            return;
        L_0x001d:
            r1 = r3.queue;	 Catch:{ all -> 0x0035 }
            r2 = r3.buffers;	 Catch:{ all -> 0x0035 }
            r5 = java.lang.Long.valueOf(r5);	 Catch:{ all -> 0x0035 }
            r5 = r2.remove(r5);	 Catch:{ all -> 0x0035 }
            r1.offer(r5);	 Catch:{ all -> 0x0035 }
            monitor-exit(r3);	 Catch:{ all -> 0x0035 }
            if (r4 == 0) goto L_0x0031;
        L_0x002f:
            r3.done = r0;
        L_0x0031:
            r3.drain();
            return;
        L_0x0035:
            r4 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0035 }
            throw r4;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableBufferBoundary$BufferBoundaryObserver.close(io.reactivex.internal.operators.observable.ObservableBufferBoundary$BufferCloseObserver, long):void");
        }

        /* Access modifiers changed, original: 0000 */
        public void boundaryError(b bVar, Throwable th) {
            DisposableHelper.dispose(this.upstream);
            this.observers.c(bVar);
            onError(th);
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                r rVar = this.downstream;
                io.reactivex.internal.queue.a aVar = this.queue;
                int i = 1;
                while (!this.cancelled) {
                    boolean z = this.done;
                    if (!z || this.errors.get() == null) {
                        Collection collection = (Collection) aVar.poll();
                        Object obj = collection == null ? 1 : null;
                        if (z && obj != null) {
                            rVar.onComplete();
                            return;
                        } else if (obj != null) {
                            i = addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        } else {
                            rVar.onNext(collection);
                        }
                    } else {
                        aVar.clear();
                        rVar.onError(this.errors.terminate());
                        return;
                    }
                }
                aVar.clear();
            }
        }
    }

    static final class BufferCloseObserver<T, C extends Collection<? super T>> extends AtomicReference<b> implements b, r<Object> {
        private static final long serialVersionUID = -8498650778633225126L;
        final long index;
        final BufferBoundaryObserver<T, C, ?, ?> parent;

        BufferCloseObserver(BufferBoundaryObserver<T, C, ?, ?> bufferBoundaryObserver, long j) {
            this.parent = bufferBoundaryObserver;
            this.index = j;
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        public void onNext(Object obj) {
            b bVar = (b) get();
            if (bVar != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                bVar.dispose();
                this.parent.close(this, this.index);
            }
        }

        public void onError(Throwable th) {
            if (get() != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.boundaryError(this, th);
                return;
            }
            xk.a(th);
        }

        public void onComplete() {
            if (get() != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.close(this, this.index);
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }
    }

    public ObservableBufferBoundary(p<T> pVar, p<? extends Open> pVar2, wm<? super Open, ? extends p<? extends Close>> wmVar, Callable<U> callable) {
        super(pVar);
        this.c = pVar2;
        this.d = wmVar;
        this.b = callable;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super U> rVar) {
        BufferBoundaryObserver bufferBoundaryObserver = new BufferBoundaryObserver(rVar, this.c, this.d, this.b);
        rVar.onSubscribe(bufferBoundaryObserver);
        this.a.subscribe(bufferBoundaryObserver);
    }
}
