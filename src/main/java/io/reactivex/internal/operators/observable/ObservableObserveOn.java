package io.reactivex.internal.operators.observable;

import defpackage.wy;
import defpackage.xd;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.schedulers.i;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.s;
import io.reactivex.s.c;

public final class ObservableObserveOn<T> extends a<T, T> {
    final s b;
    final boolean c;
    final int d;

    static final class ObserveOnObserver<T> extends BasicIntQueueDisposable<T> implements r<T>, Runnable {
        private static final long serialVersionUID = 6576896619930983584L;
        final int bufferSize;
        final boolean delayError;
        volatile boolean disposed;
        volatile boolean done;
        final r<? super T> downstream;
        Throwable error;
        boolean outputFused;
        xd<T> queue;
        int sourceMode;
        b upstream;
        final c worker;

        ObserveOnObserver(r<? super T> rVar, c cVar, boolean z, int i) {
            this.downstream = rVar;
            this.worker = cVar;
            this.delayError = z;
            this.bufferSize = i;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                if (bVar instanceof wy) {
                    wy wyVar = (wy) bVar;
                    int requestFusion = wyVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = wyVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        schedule();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = wyVar;
                        this.downstream.onSubscribe(this);
                        return;
                    }
                }
                this.queue = new a(this.bufferSize);
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                if (this.sourceMode != 2) {
                    this.queue.offer(t);
                }
                schedule();
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                xk.a(th);
                return;
            }
            this.error = th;
            this.done = true;
            schedule();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                schedule();
            }
        }

        public void dispose() {
            if (!this.disposed) {
                this.disposed = true;
                this.upstream.dispose();
                this.worker.dispose();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.disposed;
        }

        /* Access modifiers changed, original: 0000 */
        public void schedule() {
            if (getAndIncrement() == 0) {
                this.worker.a((Runnable) this);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void drainNormal() {
            xd xdVar = this.queue;
            r rVar = this.downstream;
            int i = 1;
            while (!checkTerminated(this.done, xdVar.isEmpty(), rVar)) {
                while (true) {
                    boolean z = this.done;
                    try {
                        Object poll = xdVar.poll();
                        boolean z2 = poll == null;
                        if (!checkTerminated(z, z2, rVar)) {
                            if (z2) {
                                i = addAndGet(-i);
                                if (i == 0) {
                                    return;
                                }
                            }
                            rVar.onNext(poll);
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.disposed = true;
                        this.upstream.dispose();
                        xdVar.clear();
                        rVar.onError(th);
                        this.worker.dispose();
                        return;
                    }
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void drainFused() {
            int i = 1;
            while (!this.disposed) {
                boolean z = this.done;
                Throwable th = this.error;
                if (this.delayError || !z || th == null) {
                    this.downstream.onNext(null);
                    if (z) {
                        this.disposed = true;
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            this.downstream.onError(th2);
                        } else {
                            this.downstream.onComplete();
                        }
                        this.worker.dispose();
                        return;
                    }
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
                this.disposed = true;
                this.downstream.onError(this.error);
                this.worker.dispose();
                return;
            }
        }

        public void run() {
            if (this.outputFused) {
                drainFused();
            } else {
                drainNormal();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public boolean checkTerminated(boolean z, boolean z2, r<? super T> rVar) {
            if (this.disposed) {
                this.queue.clear();
                return true;
            }
            if (z) {
                Throwable th = this.error;
                if (this.delayError) {
                    if (z2) {
                        this.disposed = true;
                        if (th != null) {
                            rVar.onError(th);
                        } else {
                            rVar.onComplete();
                        }
                        this.worker.dispose();
                        return true;
                    }
                } else if (th != null) {
                    this.disposed = true;
                    this.queue.clear();
                    rVar.onError(th);
                    this.worker.dispose();
                    return true;
                } else if (z2) {
                    this.disposed = true;
                    rVar.onComplete();
                    this.worker.dispose();
                    return true;
                }
            }
            return false;
        }

        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        public T poll() throws Exception {
            return this.queue.poll();
        }

        public void clear() {
            this.queue.clear();
        }

        public boolean isEmpty() {
            return this.queue.isEmpty();
        }
    }

    public ObservableObserveOn(p<T> pVar, s sVar, boolean z, int i) {
        super(pVar);
        this.b = sVar;
        this.c = z;
        this.d = i;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        if (this.b instanceof i) {
            this.a.subscribe(rVar);
            return;
        }
        this.a.subscribe(new ObserveOnObserver(rVar, this.b.a(), this.c, this.d));
    }
}
