package io.reactivex.internal.operators.observable;

import defpackage.wh;
import defpackage.wm;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.subjects.UnicastSubject;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableGroupJoin<TLeft, TRight, TLeftEnd, TRightEnd, R> extends a<TLeft, R> {
    final p<? extends TRight> b;
    final wm<? super TLeft, ? extends p<TLeftEnd>> c;
    final wm<? super TRight, ? extends p<TRightEnd>> d;
    final wh<? super TLeft, ? super k<TRight>, ? extends R> e;

    interface a {
        void innerClose(boolean z, LeftRightEndObserver leftRightEndObserver);

        void innerCloseError(Throwable th);

        void innerComplete(LeftRightObserver leftRightObserver);

        void innerError(Throwable th);

        void innerValue(boolean z, Object obj);
    }

    static final class GroupJoinDisposable<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements b, a {
        static final Integer LEFT_CLOSE = Integer.valueOf(3);
        static final Integer LEFT_VALUE = Integer.valueOf(1);
        static final Integer RIGHT_CLOSE = Integer.valueOf(4);
        static final Integer RIGHT_VALUE = Integer.valueOf(2);
        private static final long serialVersionUID = -6071216598687999801L;
        final AtomicInteger active;
        volatile boolean cancelled;
        final io.reactivex.disposables.a disposables = new io.reactivex.disposables.a();
        final r<? super R> downstream;
        final AtomicReference<Throwable> error = new AtomicReference();
        final wm<? super TLeft, ? extends p<TLeftEnd>> leftEnd;
        int leftIndex;
        final Map<Integer, UnicastSubject<TRight>> lefts = new LinkedHashMap();
        final io.reactivex.internal.queue.a<Object> queue = new io.reactivex.internal.queue.a(k.bufferSize());
        final wh<? super TLeft, ? super k<TRight>, ? extends R> resultSelector;
        final wm<? super TRight, ? extends p<TRightEnd>> rightEnd;
        int rightIndex;
        final Map<Integer, TRight> rights = new LinkedHashMap();

        GroupJoinDisposable(r<? super R> rVar, wm<? super TLeft, ? extends p<TLeftEnd>> wmVar, wm<? super TRight, ? extends p<TRightEnd>> wmVar2, wh<? super TLeft, ? super k<TRight>, ? extends R> whVar) {
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
            for (UnicastSubject onError : this.lefts.values()) {
                onError.onError(a);
            }
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
                        for (UnicastSubject onComplete : this.lefts.values()) {
                            onComplete.onComplete();
                        }
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
                        UnicastSubject a;
                        int i2;
                        p pVar;
                        b leftRightEndObserver;
                        LeftRightEndObserver leftRightEndObserver2;
                        if (num == LEFT_VALUE) {
                            a = UnicastSubject.a();
                            i2 = this.leftIndex;
                            this.leftIndex = i2 + 1;
                            this.lefts.put(Integer.valueOf(i2), a);
                            try {
                                pVar = (p) io.reactivex.internal.functions.a.a(this.leftEnd.apply(obj), "The leftEnd returned a null ObservableSource");
                                leftRightEndObserver = new LeftRightEndObserver(this, true, i2);
                                this.disposables.a(leftRightEndObserver);
                                pVar.subscribe(leftRightEndObserver);
                                if (((Throwable) this.error.get()) != null) {
                                    aVar.clear();
                                    cancelAll();
                                    errorAll(rVar);
                                    return;
                                }
                                try {
                                    rVar.onNext(io.reactivex.internal.functions.a.a(this.resultSelector.apply(obj, a), "The resultSelector returned a null value"));
                                    for (Object onNext : this.rights.values()) {
                                        a.onNext(onNext);
                                    }
                                } catch (Throwable th) {
                                    fail(th, rVar, aVar);
                                    return;
                                }
                            } catch (Throwable th2) {
                                fail(th2, rVar, aVar);
                                return;
                            }
                        } else if (num == RIGHT_VALUE) {
                            i2 = this.rightIndex;
                            this.rightIndex = i2 + 1;
                            this.rights.put(Integer.valueOf(i2), obj);
                            try {
                                pVar = (p) io.reactivex.internal.functions.a.a(this.rightEnd.apply(obj), "The rightEnd returned a null ObservableSource");
                                leftRightEndObserver = new LeftRightEndObserver(this, false, i2);
                                this.disposables.a(leftRightEndObserver);
                                pVar.subscribe(leftRightEndObserver);
                                if (((Throwable) this.error.get()) != null) {
                                    aVar.clear();
                                    cancelAll();
                                    errorAll(rVar);
                                    return;
                                }
                                for (UnicastSubject onNext2 : this.lefts.values()) {
                                    onNext2.onNext(obj);
                                }
                            } catch (Throwable th22) {
                                fail(th22, rVar, aVar);
                                return;
                            }
                        } else if (num == LEFT_CLOSE) {
                            leftRightEndObserver2 = (LeftRightEndObserver) obj;
                            a = (UnicastSubject) this.lefts.remove(Integer.valueOf(leftRightEndObserver2.index));
                            this.disposables.b(leftRightEndObserver2);
                            if (a != null) {
                                a.onComplete();
                            }
                        } else if (num == RIGHT_CLOSE) {
                            leftRightEndObserver2 = (LeftRightEndObserver) obj;
                            this.rights.remove(Integer.valueOf(leftRightEndObserver2.index));
                            this.disposables.b(leftRightEndObserver2);
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

    static final class LeftRightEndObserver extends AtomicReference<b> implements b, r<Object> {
        private static final long serialVersionUID = 1883890389173668373L;
        final int index;
        final boolean isLeft;
        final a parent;

        LeftRightEndObserver(a aVar, boolean z, int i) {
            this.parent = aVar;
            this.isLeft = z;
            this.index = i;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) get());
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        public void onNext(Object obj) {
            if (DisposableHelper.dispose(this)) {
                this.parent.innerClose(this.isLeft, this);
            }
        }

        public void onError(Throwable th) {
            this.parent.innerCloseError(th);
        }

        public void onComplete() {
            this.parent.innerClose(this.isLeft, this);
        }
    }

    static final class LeftRightObserver extends AtomicReference<b> implements b, r<Object> {
        private static final long serialVersionUID = 1883890389173668373L;
        final boolean isLeft;
        final a parent;

        LeftRightObserver(a aVar, boolean z) {
            this.parent = aVar;
            this.isLeft = z;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) get());
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        public void onNext(Object obj) {
            this.parent.innerValue(this.isLeft, obj);
        }

        public void onError(Throwable th) {
            this.parent.innerError(th);
        }

        public void onComplete() {
            this.parent.innerComplete(this);
        }
    }

    public ObservableGroupJoin(p<TLeft> pVar, p<? extends TRight> pVar2, wm<? super TLeft, ? extends p<TLeftEnd>> wmVar, wm<? super TRight, ? extends p<TRightEnd>> wmVar2, wh<? super TLeft, ? super k<TRight>, ? extends R> whVar) {
        super(pVar);
        this.b = pVar2;
        this.c = wmVar;
        this.d = wmVar2;
        this.e = whVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super R> rVar) {
        GroupJoinDisposable groupJoinDisposable = new GroupJoinDisposable(rVar, this.c, this.d, this.e);
        rVar.onSubscribe(groupJoinDisposable);
        b leftRightObserver = new LeftRightObserver(groupJoinDisposable, true);
        groupJoinDisposable.disposables.a(leftRightObserver);
        b leftRightObserver2 = new LeftRightObserver(groupJoinDisposable, false);
        groupJoinDisposable.disposables.a(leftRightObserver2);
        this.a.subscribe(leftRightObserver);
        this.b.subscribe(leftRightObserver2);
    }
}
