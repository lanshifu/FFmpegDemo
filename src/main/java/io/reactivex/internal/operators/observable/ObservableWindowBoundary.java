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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableWindowBoundary<T, B> extends a<T, k<T>> {
    final p<B> b;
    final int c;

    static final class WindowBoundaryMainObserver<T, B> extends AtomicInteger implements b, r<T>, Runnable {
        static final Object NEXT_WINDOW = new Object();
        private static final long serialVersionUID = 2233020065421370272L;
        final a<T, B> boundaryObserver = new a(this);
        final int capacityHint;
        volatile boolean done;
        final r<? super k<T>> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final MpscLinkedQueue<Object> queue = new MpscLinkedQueue();
        final AtomicBoolean stopWindows = new AtomicBoolean();
        final AtomicReference<b> upstream = new AtomicReference();
        UnicastSubject<T> window;
        final AtomicInteger windows = new AtomicInteger(1);

        WindowBoundaryMainObserver(r<? super k<T>> rVar, int i) {
            this.downstream = rVar;
            this.capacityHint = i;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this.upstream, bVar)) {
                innerNext();
            }
        }

        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        public void onError(Throwable th) {
            this.boundaryObserver.dispose();
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            xk.a(th);
        }

        public void onComplete() {
            this.boundaryObserver.dispose();
            this.done = true;
            drain();
        }

        public void dispose() {
            if (this.stopWindows.compareAndSet(false, true)) {
                this.boundaryObserver.dispose();
                if (this.windows.decrementAndGet() == 0) {
                    DisposableHelper.dispose(this.upstream);
                }
            }
        }

        public boolean isDisposed() {
            return this.stopWindows.get();
        }

        public void run() {
            if (this.windows.decrementAndGet() == 0) {
                DisposableHelper.dispose(this.upstream);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void innerNext() {
            this.queue.offer(NEXT_WINDOW);
            drain();
        }

        /* Access modifiers changed, original: 0000 */
        public void innerError(Throwable th) {
            DisposableHelper.dispose(this.upstream);
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            xk.a(th);
        }

        /* Access modifiers changed, original: 0000 */
        public void innerComplete() {
            DisposableHelper.dispose(this.upstream);
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
                                rVar.onNext(unicastSubject);
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
                this.a.innerNext();
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

    public ObservableWindowBoundary(p<T> pVar, p<B> pVar2, int i) {
        super(pVar);
        this.b = pVar2;
        this.c = i;
    }

    public void subscribeActual(r<? super k<T>> rVar) {
        WindowBoundaryMainObserver windowBoundaryMainObserver = new WindowBoundaryMainObserver(rVar, this.c);
        rVar.onSubscribe(windowBoundaryMainObserver);
        this.b.subscribe(windowBoundaryMainObserver.boundaryObserver);
        this.a.subscribe(windowBoundaryMainObserver);
    }
}
