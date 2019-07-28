package io.reactivex.internal.operators.observable;

import defpackage.wh;
import defpackage.wm;
import defpackage.xk;
import io.reactivex.disposables.a;
import io.reactivex.disposables.b;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableJoin<TLeft, TRight, TLeftEnd, TRightEnd, R> extends a<TLeft, R> {
    final p<? extends TRight> b;
    final wm<? super TLeft, ? extends p<TLeftEnd>> c;
    final wm<? super TRight, ? extends p<TRightEnd>> d;
    final wh<? super TLeft, ? super TRight, ? extends R> e;

    static final class JoinDisposable<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements b, a {
        static final Integer LEFT_CLOSE = Integer.valueOf(3);
        static final Integer LEFT_VALUE = Integer.valueOf(1);
        static final Integer RIGHT_CLOSE = Integer.valueOf(4);
        static final Integer RIGHT_VALUE = Integer.valueOf(2);
        private static final long serialVersionUID = -6071216598687999801L;
        final AtomicInteger active;
        volatile boolean cancelled;
        final a disposables = new a();
        final r<? super R> downstream;
        final AtomicReference<Throwable> error = new AtomicReference();
        final wm<? super TLeft, ? extends p<TLeftEnd>> leftEnd;
        int leftIndex;
        final Map<Integer, TLeft> lefts = new LinkedHashMap();
        final io.reactivex.internal.queue.a<Object> queue = new io.reactivex.internal.queue.a(k.bufferSize());
        final wh<? super TLeft, ? super TRight, ? extends R> resultSelector;
        final wm<? super TRight, ? extends p<TRightEnd>> rightEnd;
        int rightIndex;
        final Map<Integer, TRight> rights = new LinkedHashMap();

        JoinDisposable(r<? super R> rVar, wm<? super TLeft, ? extends p<TLeftEnd>> wmVar, wm<? super TRight, ? extends p<TRightEnd>> wmVar2, wh<? super TLeft, ? super TRight, ? extends R> whVar) {
            this.downstream = rVar;
            this.leftEnd = wmVar;
            this.rightEnd = wmVar2;
            this.resultSelector = whVar;
            this.active = new AtomicInteger(2);
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* Access modifiers changed, original: 0000 */
        public void cancelAll() {
            this.disposables.dispose();
        }

        /* Access modifiers changed, original: 0000 */
        public void errorAll(r<?> rVar) {
            Throwable a = ExceptionHelper.a(this.error);
            this.lefts.clear();
            this.rights.clear();
            rVar.onError(a);
        }

        /* Access modifiers changed, original: 0000 */
        public void fail(Throwable th, r<?> rVar, io.reactivex.internal.queue.a<?> aVar) {
            io.reactivex.exceptions.a.b(th);
            ExceptionHelper.a(this.error, th);
            aVar.clear();
            cancelAll();
            errorAll(rVar);
        }

        /* Access modifiers changed, original: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                io.reactivex.internal.queue.a aVar = this.queue;
                r rVar = this.downstream;
                int i = 1;
                while (!this.cancelled) {
                    if (((Throwable) this.error.get()) != null) {
                        aVar.clear();
                        cancelAll();
                        errorAll(rVar);
                        return;
                    }
                    Object obj = this.active.get() == 0 ? 1 : null;
                    Integer num = (Integer) aVar.poll();
                    Object obj2 = num == null ? 1 : null;
                    if (obj != null && obj2 != null) {
                        this.lefts.clear();
                        this.rights.clear();
                        this.disposables.dispose();
                        rVar.onComplete();
                        return;
                    } else if (obj2 != null) {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        obj = aVar.poll();
                        LeftRightEndObserver leftRightEndObserver;
                        if (num == LEFT_VALUE) {
                            int i2 = this.leftIndex;
                            this.leftIndex = i2 + 1;
                            this.lefts.put(Integer.valueOf(i2), obj);
                            try {
                                p pVar = (p) io.reactivex.internal.functions.a.a(this.leftEnd.apply(obj), "The leftEnd returned a null ObservableSource");
                                b leftRightEndObserver2 = new LeftRightEndObserver(this, true, i2);
                                this.disposables.a(leftRightEndObserver2);
                                pVar.subscribe(leftRightEndObserver2);
                                if (((Throwable) this.error.get()) != null) {
                                    aVar.clear();
                                    cancelAll();
                                    errorAll(rVar);
                                    return;
                                }
                                for (Object apply : this.rights.values()) {
                                    try {
                                        rVar.onNext(io.reactivex.internal.functions.a.a(this.resultSelector.apply(obj, apply), "The resultSelector returned a null value"));
                                    } catch (Throwable th) {
                                        fail(th, rVar, aVar);
                                        return;
                                    }
                                }
                            } catch (Throwable th2) {
                                fail(th2, rVar, aVar);
                                return;
                            }
                        } else if (num == RIGHT_VALUE) {
                            int i3 = this.rightIndex;
                            this.rightIndex = i3 + 1;
                            this.rights.put(Integer.valueOf(i3), obj);
                            try {
                                p pVar2 = (p) io.reactivex.internal.functions.a.a(this.rightEnd.apply(obj), "The rightEnd returned a null ObservableSource");
                                b leftRightEndObserver3 = new LeftRightEndObserver(this, false, i3);
                                this.disposables.a(leftRightEndObserver3);
                                pVar2.subscribe(leftRightEndObserver3);
                                if (((Throwable) this.error.get()) != null) {
                                    aVar.clear();
                                    cancelAll();
                                    errorAll(rVar);
                                    return;
                                }
                                for (Object apply2 : this.lefts.values()) {
                                    try {
                                        rVar.onNext(io.reactivex.internal.functions.a.a(this.resultSelector.apply(apply2, obj), "The resultSelector returned a null value"));
                                    } catch (Throwable th22) {
                                        fail(th22, rVar, aVar);
                                        return;
                                    }
                                }
                            } catch (Throwable th222) {
                                fail(th222, rVar, aVar);
                                return;
                            }
                        } else if (num == LEFT_CLOSE) {
                            leftRightEndObserver = (LeftRightEndObserver) obj;
                            this.lefts.remove(Integer.valueOf(leftRightEndObserver.index));
                            this.disposables.b(leftRightEndObserver);
                        } else {
                            leftRightEndObserver = (LeftRightEndObserver) obj;
                            this.rights.remove(Integer.valueOf(leftRightEndObserver.index));
                            this.disposables.b(leftRightEndObserver);
                        }
                    }
                }
                aVar.clear();
            }
        }

        public void innerError(Throwable th) {
            if (ExceptionHelper.a(this.error, th)) {
                this.active.decrementAndGet();
                drain();
                return;
            }
            xk.a(th);
        }

        public void innerComplete(LeftRightObserver leftRightObserver) {
            this.disposables.c(leftRightObserver);
            this.active.decrementAndGet();
            drain();
        }

        public void innerValue(boolean z, Object obj) {
            synchronized (this) {
                this.queue.a(z ? LEFT_VALUE : RIGHT_VALUE, obj);
            }
            drain();
        }

        public void innerClose(boolean z, LeftRightEndObserver leftRightEndObserver) {
            synchronized (this) {
                this.queue.a(z ? LEFT_CLOSE : RIGHT_CLOSE, (Object) leftRightEndObserver);
            }
            drain();
        }

        public void innerCloseError(Throwable th) {
            if (ExceptionHelper.a(this.error, th)) {
                drain();
            } else {
                xk.a(th);
            }
        }
    }

    public ObservableJoin(p<TLeft> pVar, p<? extends TRight> pVar2, wm<? super TLeft, ? extends p<TLeftEnd>> wmVar, wm<? super TRight, ? extends p<TRightEnd>> wmVar2, wh<? super TLeft, ? super TRight, ? extends R> whVar) {
        super(pVar);
        this.b = pVar2;
        this.c = wmVar;
        this.d = wmVar2;
        this.e = whVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super R> rVar) {
        JoinDisposable joinDisposable = new JoinDisposable(rVar, this.c, this.d, this.e);
        rVar.onSubscribe(joinDisposable);
        b leftRightObserver = new LeftRightObserver(joinDisposable, true);
        joinDisposable.disposables.a(leftRightObserver);
        b leftRightObserver2 = new LeftRightObserver(joinDisposable, false);
        joinDisposable.disposables.a(leftRightObserver2);
        this.a.subscribe(leftRightObserver);
        this.b.subscribe(leftRightObserver2);
    }
}
