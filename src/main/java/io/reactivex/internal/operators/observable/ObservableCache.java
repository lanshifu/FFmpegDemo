package io.reactivex.internal.operators.observable;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.g;
import io.reactivex.k;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableCache<T> extends a<T, T> {
    final a<T> b;
    final AtomicBoolean c = new AtomicBoolean();

    static final class ReplayDisposable<T> extends AtomicInteger implements b {
        private static final long serialVersionUID = 7058506693698832024L;
        volatile boolean cancelled;
        final r<? super T> child;
        Object[] currentBuffer;
        int currentIndexInBuffer;
        int index;
        final a<T> state;

        ReplayDisposable(r<? super T> rVar, a<T> aVar) {
            this.child = rVar;
            this.state = aVar;
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.state.b(this);
            }
        }

        public void replay() {
            if (getAndIncrement() == 0) {
                r rVar = this.child;
                int i = 1;
                while (!this.cancelled) {
                    int c = this.state.c();
                    if (c != 0) {
                        Object[] objArr = this.currentBuffer;
                        if (objArr == null) {
                            objArr = this.state.b();
                            this.currentBuffer = objArr;
                        }
                        int length = objArr.length - 1;
                        int i2 = this.index;
                        int i3 = this.currentIndexInBuffer;
                        while (i2 < c) {
                            if (!this.cancelled) {
                                if (i3 == length) {
                                    objArr = (Object[]) objArr[length];
                                    i3 = 0;
                                }
                                if (!NotificationLite.accept(objArr[i3], rVar)) {
                                    i3++;
                                    i2++;
                                } else {
                                    return;
                                }
                            }
                            return;
                        }
                        if (!this.cancelled) {
                            this.index = i2;
                            this.currentIndexInBuffer = i3;
                            this.currentBuffer = objArr;
                        } else {
                            return;
                        }
                    }
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }
    }

    static final class a<T> extends g implements r<T> {
        static final ReplayDisposable[] d = new ReplayDisposable[0];
        static final ReplayDisposable[] e = new ReplayDisposable[0];
        final k<? extends T> a;
        final SequentialDisposable b = new SequentialDisposable();
        final AtomicReference<ReplayDisposable<T>[]> c = new AtomicReference(d);
        volatile boolean f;
        boolean g;

        a(k<? extends T> kVar, int i) {
            super(i);
            this.a = kVar;
        }

        public boolean a(ReplayDisposable<T> replayDisposable) {
            ReplayDisposable[] replayDisposableArr;
            ReplayDisposable[] replayDisposableArr2;
            do {
                replayDisposableArr = (ReplayDisposable[]) this.c.get();
                if (replayDisposableArr == e) {
                    return false;
                }
                int length = replayDisposableArr.length;
                replayDisposableArr2 = new ReplayDisposable[(length + 1)];
                System.arraycopy(replayDisposableArr, 0, replayDisposableArr2, 0, length);
                replayDisposableArr2[length] = replayDisposable;
            } while (!this.c.compareAndSet(replayDisposableArr, replayDisposableArr2));
            return true;
        }

        public void b(ReplayDisposable<T> replayDisposable) {
            ReplayDisposable[] replayDisposableArr;
            Object obj;
            do {
                replayDisposableArr = (ReplayDisposable[]) this.c.get();
                int length = replayDisposableArr.length;
                if (length != 0) {
                    int i = -1;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (replayDisposableArr[i2].equals(replayDisposable)) {
                            i = i2;
                            break;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            obj = d;
                        } else {
                            Object obj2 = new ReplayDisposable[(length - 1)];
                            System.arraycopy(replayDisposableArr, 0, obj2, 0, i);
                            System.arraycopy(replayDisposableArr, i + 1, obj2, i, (length - i) - 1);
                            obj = obj2;
                        }
                    } else {
                        return;
                    }
                }
                return;
            } while (!this.c.compareAndSet(replayDisposableArr, obj));
        }

        public void onSubscribe(b bVar) {
            this.b.update(bVar);
        }

        public void a() {
            this.a.subscribe((r) this);
            this.f = true;
        }

        public void onNext(T t) {
            if (!this.g) {
                a(NotificationLite.next(t));
                for (ReplayDisposable replay : (ReplayDisposable[]) this.c.get()) {
                    replay.replay();
                }
            }
        }

        public void onError(Throwable th) {
            if (!this.g) {
                this.g = true;
                a(NotificationLite.error(th));
                this.b.dispose();
                for (ReplayDisposable replay : (ReplayDisposable[]) this.c.getAndSet(e)) {
                    replay.replay();
                }
            }
        }

        public void onComplete() {
            if (!this.g) {
                this.g = true;
                a(NotificationLite.complete());
                this.b.dispose();
                for (ReplayDisposable replay : (ReplayDisposable[]) this.c.getAndSet(e)) {
                    replay.replay();
                }
            }
        }
    }

    public static <T> k<T> a(k<T> kVar) {
        return a(kVar, 16);
    }

    public static <T> k<T> a(k<T> kVar, int i) {
        io.reactivex.internal.functions.a.a(i, "capacityHint");
        return xk.a(new ObservableCache(kVar, new a(kVar, i)));
    }

    private ObservableCache(k<T> kVar, a<T> aVar) {
        super(kVar);
        this.b = aVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        ReplayDisposable replayDisposable = new ReplayDisposable(rVar, this.b);
        rVar.onSubscribe(replayDisposable);
        this.b.a(replayDisposable);
        if (!this.c.get() && this.c.compareAndSet(false, true)) {
            this.b.a();
        }
        replayDisposable.replay();
    }
}
