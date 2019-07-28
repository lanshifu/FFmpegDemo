package io.reactivex.internal.operators.observable;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.k;
import io.reactivex.observers.c;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.subjects.UnicastSubject;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableWindowBoundarySupplier<T, B> extends a<T, k<T>> {
    final Callable<? extends p<B>> b;
    final int c;

    static final class WindowBoundaryMainObserver<T, B> extends AtomicInteger implements b, r<T>, Runnable {
        static final a<Object, Object> BOUNDARY_DISPOSED = new a(null);
        static final Object NEXT_WINDOW = new Object();
        private static final long serialVersionUID = 2233020065421370272L;
        final AtomicReference<a<T, B>> boundaryObserver = new AtomicReference();
        final int capacityHint;
        volatile boolean done;
        final r<? super k<T>> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final Callable<? extends p<B>> other;
        final MpscLinkedQueue<Object> queue = new MpscLinkedQueue();
        final AtomicBoolean stopWindows = new AtomicBoolean();
        b upstream;
        UnicastSubject<T> window;
        final AtomicInteger windows = new AtomicInteger(1);

        WindowBoundaryMainObserver(r<? super k<T>> rVar, int i, Callable<? extends p<B>> callable) {
            this.downstream = rVar;
            this.capacityHint = i;
            this.other = callable;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
                this.queue.offer(NEXT_WINDOW);
                drain();
            }
        }

        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        public void onError(Throwable th) {
            disposeBoundary();
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            xk.a(th);
        }

        public void onComplete() {
            disposeBoundary();
            this.done = true;
            drain();
        }

        public void dispose() {
            if (this.stopWindows.compareAndSet(false, true)) {
                disposeBoundary();
                if (this.windows.decrementAndGet() == 0) {
                    this.upstream.dispose();
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void disposeBoundary() {
            b bVar = (b) this.boundaryObserver.getAndSet(BOUNDARY_DISPOSED);
            if (bVar != null && bVar != BOUNDARY_DISPOSED) {
                bVar.dispose();
            }
        }

        public boolean isDisposed() {
            return this.stopWindows.get();
        }

        public void run() {
            if (this.windows.decrementAndGet() == 0) {
                this.upstream.dispose();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void innerNext(a<T, B> aVar) {
            this.boundaryObserver.compareAndSet(aVar, null);
            this.queue.offer(NEXT_WINDOW);
            drain();
        }

        /* Access modifiers changed, original: 0000 */
        public void innerError(Throwable th) {
            this.upstream.dispose();
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            xk.a(th);
        }

        /* Access modifiers changed, original: 0000 */
        public void innerComplete() {
            this.upstream.dispose();
            this.done = true;
            drain();
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                r rVar = this.downstream;
                MpscLinkedQueue mpscLinkedQueue = this.queue;
                AtomicThrowable atomicThrowable = this.errors;
                int i = 1;
                while (this.windows.get() != 0) {
                    UnicastSubject unicastSubject = this.window;
                    boolean z = this.done;
                    Throwable terminate;
                    if (!z || atomicThrowable.get() == null) {
                        Object poll = mpscLinkedQueue.poll();
                        Object obj = poll == null ? 1 : null;
                        if (z && obj != null) {
                            terminate = atomicThrowable.terminate();
                            if (terminate == null) {
                                if (unicastSubject != null) {
                                    this.window = null;
                                    unicastSubject.onComplete();
                                }
                                rVar.onComplete();
                            } else {
                                if (unicastSubject != null) {
                                    this.window = null;
                                    unicastSubject.onError(terminate);
                                }
                                rVar.onError(terminate);
                            }
                            return;
                        } else if (obj != null) {
                            i = addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        } else if (poll != NEXT_WINDOW) {
                            unicastSubject.onNext(poll);
                        } else {
                            if (unicastSubject != null) {
                                this.window = null;
                                unicastSubject.onComplete();
                            }
                            if (!this.stopWindows.get()) {
                                unicastSubject = UnicastSubject.a(this.capacityHint, (Runnable) this);
                                this.window = unicastSubject;
                                this.windows.getAndIncrement();
                                try {
                                    p pVar = (p) io.reactivex.internal.functions.a.a(this.other.call(), "The other Callable returned a null ObservableSource");
                                    a aVar = new a(this);
                                    if (this.boundaryObserver.compareAndSet(null, aVar)) {
                                        pVar.subscribe(aVar);
                                        rVar.onNext(unicastSubject);
                                    }
                                } catch (Throwable th) {
                                    io.reactivex.exceptions.a.b(th);
                                    atomicThrowable.addThrowable(th);
                                    this.done = true;
                                }
                            }
                        }
                    } else {
                        mpscLinkedQueue.clear();
                        terminate = atomicThrowable.terminate();
                        if (unicastSubject != null) {
                            this.window = null;
                            unicastSubject.onError(terminate);
                        }
                        rVar.onError(terminate);
                        return;
                    }
                }
                mpscLinkedQueue.clear();
                this.window = null;
            }
        }
    }

    static final class a<T, B> extends c<B> {
        final WindowBoundaryMainObserver<T, B> a;
        boolean b;

        a(WindowBoundaryMainObserver<T, B> windowBoundaryMainObserver) {
            this.a = windowBoundaryMainObserver;
        }

        public void onNext(B b) {
            if (!this.b) {
                this.b = true;
                dispose();
                this.a.innerNext(this);
            }
        }

        public void onError(Throwable th) {
            if (this.b) {
                xk.a(th);
                return;
            }
            this.b = true;
            this.a.innerError(th);
        }

        public void onComplete() {
            if (!this.b) {
                this.b = true;
                this.a.innerComplete();
            }
        }
    }

    public ObservableWindowBoundarySupplier(p<T> pVar, Callable<? extends p<B>> callable, int i) {
        super(pVar);
        this.b = callable;
        this.c = i;
    }

    public void subscribeActual(r<? super k<T>> rVar) {
        this.a.subscribe(new WindowBoundaryMainObserver(rVar, this.c, this.b));
    }
}
