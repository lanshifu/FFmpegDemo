package io.reactivex.internal.operators.observable;

import defpackage.wm;
import defpackage.xj;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.p;
import io.reactivex.r;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableGroupBy<T, K, V> extends a<T, xj<K, V>> {
    final wm<? super T, ? extends K> b;
    final wm<? super T, ? extends V> c;
    final int d;
    final boolean e;

    public static final class GroupByObserver<T, K, V> extends AtomicInteger implements b, r<T> {
        static final Object NULL_KEY = new Object();
        private static final long serialVersionUID = -3688291656102519502L;
        final int bufferSize;
        final AtomicBoolean cancelled = new AtomicBoolean();
        final boolean delayError;
        final r<? super xj<K, V>> downstream;
        final Map<Object, a<K, V>> groups;
        final wm<? super T, ? extends K> keySelector;
        b upstream;
        final wm<? super T, ? extends V> valueSelector;

        public GroupByObserver(r<? super xj<K, V>> rVar, wm<? super T, ? extends K> wmVar, wm<? super T, ? extends V> wmVar2, int i, boolean z) {
            this.downstream = rVar;
            this.keySelector = wmVar;
            this.valueSelector = wmVar2;
            this.bufferSize = i;
            this.delayError = z;
            this.groups = new ConcurrentHashMap();
            lazySet(1);
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            try {
                Object obj;
                Object apply = this.keySelector.apply(t);
                if (apply != null) {
                    obj = apply;
                } else {
                    obj = NULL_KEY;
                }
                a aVar = (a) this.groups.get(obj);
                if (aVar == null) {
                    if (!this.cancelled.get()) {
                        aVar = a.a(apply, this.bufferSize, this, this.delayError);
                        this.groups.put(obj, aVar);
                        getAndIncrement();
                        this.downstream.onNext(aVar);
                    } else {
                        return;
                    }
                }
                try {
                    aVar.a(io.reactivex.internal.functions.a.a(this.valueSelector.apply(t), "The value supplied is null"));
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.upstream.dispose();
                    onError(th);
                }
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                this.upstream.dispose();
                onError(th2);
            }
        }

        public void onError(Throwable th) {
            ArrayList<a> arrayList = new ArrayList(this.groups.values());
            this.groups.clear();
            for (a a : arrayList) {
                a.a(th);
            }
            this.downstream.onError(th);
        }

        public void onComplete() {
            ArrayList<a> arrayList = new ArrayList(this.groups.values());
            this.groups.clear();
            for (a a : arrayList) {
                a.a();
            }
            this.downstream.onComplete();
        }

        public void dispose() {
            if (this.cancelled.compareAndSet(false, true) && decrementAndGet() == 0) {
                this.upstream.dispose();
            }
        }

        public boolean isDisposed() {
            return this.cancelled.get();
        }

        public void cancel(K k) {
            Object k2;
            if (k2 == null) {
                k2 = NULL_KEY;
            }
            this.groups.remove(k2);
            if (decrementAndGet() == 0) {
                this.upstream.dispose();
            }
        }
    }

    static final class State<T, K> extends AtomicInteger implements b, p<T> {
        private static final long serialVersionUID = -3852313036005250360L;
        final AtomicReference<r<? super T>> actual = new AtomicReference();
        final AtomicBoolean cancelled = new AtomicBoolean();
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final K key;
        final AtomicBoolean once = new AtomicBoolean();
        final GroupByObserver<?, K, T> parent;
        final io.reactivex.internal.queue.a<T> queue;

        State(int i, GroupByObserver<?, K, T> groupByObserver, K k, boolean z) {
            this.queue = new io.reactivex.internal.queue.a(i);
            this.parent = groupByObserver;
            this.key = k;
            this.delayError = z;
        }

        public void dispose() {
            if (this.cancelled.compareAndSet(false, true) && getAndIncrement() == 0) {
                this.actual.lazySet(null);
                this.parent.cancel(this.key);
            }
        }

        public boolean isDisposed() {
            return this.cancelled.get();
        }

        public void subscribe(r<? super T> rVar) {
            if (this.once.compareAndSet(false, true)) {
                rVar.onSubscribe(this);
                this.actual.lazySet(rVar);
                if (this.cancelled.get()) {
                    this.actual.lazySet(null);
                    return;
                } else {
                    drain();
                    return;
                }
            }
            EmptyDisposable.error(new IllegalStateException("Only one Observer allowed!"), (r) rVar);
        }

        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                io.reactivex.internal.queue.a aVar = this.queue;
                boolean z = this.delayError;
                r rVar = (r) this.actual.get();
                int i = 1;
                while (true) {
                    if (rVar != null) {
                        while (true) {
                            boolean z2 = this.done;
                            Object poll = aVar.poll();
                            boolean z3 = poll == null;
                            if (!checkTerminated(z2, z3, rVar, z)) {
                                if (z3) {
                                    break;
                                }
                                rVar.onNext(poll);
                            } else {
                                return;
                            }
                        }
                    }
                    i = addAndGet(-i);
                    if (i != 0) {
                        if (rVar == null) {
                            rVar = (r) this.actual.get();
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        public boolean checkTerminated(boolean z, boolean z2, r<? super T> rVar, boolean z3) {
            if (this.cancelled.get()) {
                this.queue.clear();
                this.parent.cancel(this.key);
                this.actual.lazySet(null);
                return true;
            }
            if (z) {
                Throwable th;
                if (!z3) {
                    th = this.error;
                    if (th != null) {
                        this.queue.clear();
                        this.actual.lazySet(null);
                        rVar.onError(th);
                        return true;
                    } else if (z2) {
                        this.actual.lazySet(null);
                        rVar.onComplete();
                        return true;
                    }
                } else if (z2) {
                    th = this.error;
                    this.actual.lazySet(null);
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

    static final class a<K, T> extends xj<K, T> {
        final State<T, K> a;

        public static <T, K> a<K, T> a(K k, int i, GroupByObserver<?, K, T> groupByObserver, boolean z) {
            return new a(k, new State(i, groupByObserver, k, z));
        }

        protected a(K k, State<T, K> state) {
            super(k);
            this.a = state;
        }

        /* Access modifiers changed, original: protected */
        public void subscribeActual(r<? super T> rVar) {
            this.a.subscribe(rVar);
        }

        public void a(T t) {
            this.a.onNext(t);
        }

        public void a(Throwable th) {
            this.a.onError(th);
        }

        public void a() {
            this.a.onComplete();
        }
    }

    public ObservableGroupBy(p<T> pVar, wm<? super T, ? extends K> wmVar, wm<? super T, ? extends V> wmVar2, int i, boolean z) {
        super(pVar);
        this.b = wmVar;
        this.c = wmVar2;
        this.d = i;
        this.e = z;
    }

    public void subscribeActual(r<? super xj<K, V>> rVar) {
        this.a.subscribe(new GroupByObserver(rVar, this.b, this.c, this.d, this.e));
    }
}
