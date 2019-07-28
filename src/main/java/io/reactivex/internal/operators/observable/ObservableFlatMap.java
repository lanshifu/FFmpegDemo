package io.reactivex.internal.operators.observable;

import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import defpackage.wm;
import defpackage.wy;
import defpackage.xc;
import defpackage.xd;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.p;
import io.reactivex.r;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableFlatMap<T, U> extends a<T, U> {
    final wm<? super T, ? extends p<? extends U>> b;
    final boolean c;
    final int d;
    final int e;

    static final class InnerObserver<T, U> extends AtomicReference<b> implements r<U> {
        private static final long serialVersionUID = -4606175640614850599L;
        volatile boolean done;
        int fusionMode;
        final long id;
        final MergeObserver<T, U> parent;
        volatile xd<U> queue;

        InnerObserver(MergeObserver<T, U> mergeObserver, long j) {
            this.id = j;
            this.parent = mergeObserver;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar) && (bVar instanceof wy)) {
                wy wyVar = (wy) bVar;
                int requestFusion = wyVar.requestFusion(7);
                if (requestFusion == 1) {
                    this.fusionMode = requestFusion;
                    this.queue = wyVar;
                    this.done = true;
                    this.parent.drain();
                } else if (requestFusion == 2) {
                    this.fusionMode = requestFusion;
                    this.queue = wyVar;
                }
            }
        }

        public void onNext(U u) {
            if (this.fusionMode == 0) {
                this.parent.tryEmit(u, this);
            } else {
                this.parent.drain();
            }
        }

        public void onError(Throwable th) {
            if (this.parent.errors.addThrowable(th)) {
                if (!this.parent.delayErrors) {
                    this.parent.disposeAll();
                }
                this.done = true;
                this.parent.drain();
                return;
            }
            xk.a(th);
        }

        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }

    static final class MergeObserver<T, U> extends AtomicInteger implements b, r<T> {
        static final InnerObserver<?, ?>[] CANCELLED = new InnerObserver[0];
        static final InnerObserver<?, ?>[] EMPTY = new InnerObserver[0];
        private static final long serialVersionUID = -2117620485640801370L;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final r<? super U> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        long lastId;
        int lastIndex;
        final wm<? super T, ? extends p<? extends U>> mapper;
        final int maxConcurrency;
        final AtomicReference<InnerObserver<?, ?>[]> observers;
        volatile xc<U> queue;
        Queue<p<? extends U>> sources;
        long uniqueId;
        b upstream;
        int wip;

        MergeObserver(r<? super U> rVar, wm<? super T, ? extends p<? extends U>> wmVar, boolean z, int i, int i2) {
            this.downstream = rVar;
            this.mapper = wmVar;
            this.delayErrors = z;
            this.maxConcurrency = i;
            this.bufferSize = i2;
            if (i != Filter.MAX) {
                this.sources = new ArrayDeque(i);
            }
            this.observers = new AtomicReference(EMPTY);
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    p pVar = (p) a.a(this.mapper.apply(t), "The mapper returned a null ObservableSource");
                    if (this.maxConcurrency != Filter.MAX) {
                        synchronized (this) {
                            if (this.wip == this.maxConcurrency) {
                                this.sources.offer(pVar);
                                return;
                            }
                            this.wip++;
                        }
                    }
                    subscribeInner(pVar);
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.upstream.dispose();
                    onError(th);
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void subscribeInner(p<? extends U> pVar) {
            p pVar2;
            while (pVar2 instanceof Callable) {
                if (tryEmitScalar((Callable) pVar2) && this.maxConcurrency != Filter.MAX) {
                    p pVar3;
                    Object obj = null;
                    synchronized (this) {
                        pVar3 = (p) this.sources.poll();
                        if (pVar3 == null) {
                            this.wip--;
                            obj = 1;
                        }
                    }
                    if (obj != null) {
                        drain();
                        return;
                    }
                    pVar2 = pVar3;
                } else {
                    return;
                }
            }
            long j = this.uniqueId;
            this.uniqueId = 1 + j;
            InnerObserver innerObserver = new InnerObserver(this, j);
            if (addInner(innerObserver)) {
                pVar2.subscribe(innerObserver);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public boolean addInner(InnerObserver<T, U> innerObserver) {
            InnerObserver[] innerObserverArr;
            InnerObserver[] innerObserverArr2;
            do {
                innerObserverArr = (InnerObserver[]) this.observers.get();
                if (innerObserverArr == CANCELLED) {
                    innerObserver.dispose();
                    return false;
                }
                int length = innerObserverArr.length;
                innerObserverArr2 = new InnerObserver[(length + 1)];
                System.arraycopy(innerObserverArr, 0, innerObserverArr2, 0, length);
                innerObserverArr2[length] = innerObserver;
            } while (!this.observers.compareAndSet(innerObserverArr, innerObserverArr2));
            return true;
        }

        /* Access modifiers changed, original: 0000 */
        public void removeInner(InnerObserver<T, U> innerObserver) {
            InnerObserver[] innerObserverArr;
            Object obj;
            do {
                innerObserverArr = (InnerObserver[]) this.observers.get();
                int length = innerObserverArr.length;
                if (length != 0) {
                    int i = -1;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (innerObserverArr[i2] == innerObserver) {
                            i = i2;
                            break;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            obj = EMPTY;
                        } else {
                            Object obj2 = new InnerObserver[(length - 1)];
                            System.arraycopy(innerObserverArr, 0, obj2, 0, i);
                            System.arraycopy(innerObserverArr, i + 1, obj2, i, (length - i) - 1);
                            obj = obj2;
                        }
                    } else {
                        return;
                    }
                }
                return;
            } while (!this.observers.compareAndSet(innerObserverArr, obj));
        }

        /* Access modifiers changed, original: 0000 */
        public boolean tryEmitScalar(Callable<? extends U> callable) {
            try {
                Object call = callable.call();
                if (call == null) {
                    return true;
                }
                if (get() == 0 && compareAndSet(0, 1)) {
                    this.downstream.onNext(call);
                    if (decrementAndGet() == 0) {
                        return true;
                    }
                }
                xc xcVar = this.queue;
                if (xcVar == null) {
                    if (this.maxConcurrency == Filter.MAX) {
                        xcVar = new io.reactivex.internal.queue.a(this.bufferSize);
                    } else {
                        xcVar = new SpscArrayQueue(this.maxConcurrency);
                    }
                    this.queue = xcVar;
                }
                if (!xcVar.offer(call)) {
                    onError(new IllegalStateException("Scalar queue full?!"));
                    return true;
                } else if (getAndIncrement() != 0) {
                    return false;
                }
                drainLoop();
                return true;
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.errors.addThrowable(th);
                drain();
                return true;
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void tryEmit(U u, InnerObserver<T, U> innerObserver) {
            if (get() == 0 && compareAndSet(0, 1)) {
                this.downstream.onNext(u);
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            xd xdVar = innerObserver.queue;
            if (xdVar == null) {
                xdVar = new io.reactivex.internal.queue.a(this.bufferSize);
                innerObserver.queue = xdVar;
            }
            xdVar.offer(u);
            if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        public void onError(Throwable th) {
            if (this.done) {
                xk.a(th);
                return;
            }
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
            } else {
                xk.a(th);
            }
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                if (disposeAll()) {
                    Throwable terminate = this.errors.terminate();
                    if (terminate != null && terminate != ExceptionHelper.a) {
                        xk.a(terminate);
                    }
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void drainLoop() {
            r rVar = this.downstream;
            int i = 1;
            while (!checkTerminate()) {
                Object poll;
                int size;
                xc xcVar = this.queue;
                if (xcVar != null) {
                    while (!checkTerminate()) {
                        poll = xcVar.poll();
                        if (poll != null) {
                            rVar.onNext(poll);
                        }
                    }
                    return;
                }
                boolean z = this.done;
                xc xcVar2 = this.queue;
                InnerObserver[] innerObserverArr = (InnerObserver[]) this.observers.get();
                int length = innerObserverArr.length;
                if (this.maxConcurrency != Filter.MAX) {
                    synchronized (this) {
                        size = this.sources.size();
                    }
                } else {
                    size = 0;
                }
                if (z && ((xcVar2 == null || xcVar2.isEmpty()) && length == 0 && size == 0)) {
                    Throwable terminate = this.errors.terminate();
                    if (terminate != ExceptionHelper.a) {
                        if (terminate == null) {
                            rVar.onComplete();
                        } else {
                            rVar.onError(terminate);
                        }
                    }
                    return;
                }
                if (length != 0) {
                    long j = this.lastId;
                    size = this.lastIndex;
                    if (length <= size || innerObserverArr[size].id != j) {
                        if (length <= size) {
                            size = 0;
                        }
                        int i2 = size;
                        for (size = 0; size < length && innerObserverArr[i2].id != j; size++) {
                            i2++;
                            if (i2 == length) {
                                i2 = 0;
                            }
                        }
                        this.lastIndex = i2;
                        this.lastId = innerObserverArr[i2].id;
                        size = i2;
                    }
                    int i3 = 0;
                    poll = null;
                    while (i3 < length) {
                        if (!checkTerminate()) {
                            InnerObserver innerObserver = innerObserverArr[size];
                            xd xdVar = innerObserver.queue;
                            if (xdVar != null) {
                                while (true) {
                                    try {
                                        Object poll2 = xdVar.poll();
                                        if (poll2 == null) {
                                            break;
                                        }
                                        rVar.onNext(poll2);
                                        if (checkTerminate()) {
                                            return;
                                        }
                                    } catch (Throwable th) {
                                        io.reactivex.exceptions.a.b(th);
                                        innerObserver.dispose();
                                        this.errors.addThrowable(th);
                                        if (!checkTerminate()) {
                                            removeInner(innerObserver);
                                            size++;
                                            if (size == length) {
                                                size = 0;
                                            }
                                            poll = 1;
                                        } else {
                                            return;
                                        }
                                    }
                                }
                            }
                            boolean z2 = innerObserver.done;
                            xd xdVar2 = innerObserver.queue;
                            if (z2 && (xdVar2 == null || xdVar2.isEmpty())) {
                                removeInner(innerObserver);
                                if (!checkTerminate()) {
                                    poll = 1;
                                } else {
                                    return;
                                }
                            }
                            size++;
                            if (size == length) {
                                size = 0;
                            }
                            i3++;
                        } else {
                            return;
                        }
                    }
                    this.lastIndex = size;
                    this.lastId = innerObserverArr[size].id;
                } else {
                    poll = null;
                }
                if (poll == null) {
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else if (this.maxConcurrency != Filter.MAX) {
                    synchronized (this) {
                        p pVar = (p) this.sources.poll();
                        if (pVar == null) {
                            this.wip--;
                        } else {
                            subscribeInner(pVar);
                        }
                    }
                } else {
                    continue;
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public boolean checkTerminate() {
            if (this.cancelled) {
                return true;
            }
            Throwable th = (Throwable) this.errors.get();
            if (this.delayErrors || th == null) {
                return false;
            }
            disposeAll();
            th = this.errors.terminate();
            if (th != ExceptionHelper.a) {
                this.downstream.onError(th);
            }
            return true;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean disposeAll() {
            this.upstream.dispose();
            int i = 0;
            if (((InnerObserver[]) this.observers.get()) != CANCELLED) {
                InnerObserver[] innerObserverArr = (InnerObserver[]) this.observers.getAndSet(CANCELLED);
                if (innerObserverArr != CANCELLED) {
                    int length = innerObserverArr.length;
                    while (i < length) {
                        innerObserverArr[i].dispose();
                        i++;
                    }
                    return true;
                }
            }
            return false;
        }
    }

    public ObservableFlatMap(p<T> pVar, wm<? super T, ? extends p<? extends U>> wmVar, boolean z, int i, int i2) {
        super(pVar);
        this.b = wmVar;
        this.c = z;
        this.d = i;
        this.e = i2;
    }

    public void subscribeActual(r<? super U> rVar) {
        if (!ObservableScalarXMap.a(this.a, rVar, this.b)) {
            this.a.subscribe(new MergeObserver(rVar, this.b, this.c, this.d, this.e));
        }
    }
}
