package io.reactivex.internal.operators.observable;

import defpackage.wh;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.e;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableWithLatestFrom<T, U, R> extends a<T, R> {
    final wh<? super T, ? super U, ? extends R> b;
    final p<? extends U> c;

    static final class WithLatestFromObserver<T, U, R> extends AtomicReference<U> implements b, r<T> {
        private static final long serialVersionUID = -312246233408980075L;
        final wh<? super T, ? super U, ? extends R> combiner;
        final r<? super R> downstream;
        final AtomicReference<b> other = new AtomicReference();
        final AtomicReference<b> upstream = new AtomicReference();

        WithLatestFromObserver(r<? super R> rVar, wh<? super T, ? super U, ? extends R> whVar) {
            this.downstream = rVar;
            this.combiner = whVar;
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.upstream, bVar);
        }

        public void onNext(T t) {
            Object obj = get();
            if (obj != null) {
                try {
                    this.downstream.onNext(io.reactivex.internal.functions.a.a(this.combiner.apply(t, obj), "The combiner returned a null value"));
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    dispose();
                    this.downstream.onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this.other);
            this.downstream.onError(th);
        }

        public void onComplete() {
            DisposableHelper.dispose(this.other);
            this.downstream.onComplete();
        }

        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this.other);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) this.upstream.get());
        }

        public boolean setOther(b bVar) {
            return DisposableHelper.setOnce(this.other, bVar);
        }

        public void otherError(Throwable th) {
            DisposableHelper.dispose(this.upstream);
            this.downstream.onError(th);
        }
    }

    final class a implements r<U> {
        private final WithLatestFromObserver<T, U, R> b;

        public void onComplete() {
        }

        a(WithLatestFromObserver<T, U, R> withLatestFromObserver) {
            this.b = withLatestFromObserver;
        }

        public void onSubscribe(b bVar) {
            this.b.setOther(bVar);
        }

        public void onNext(U u) {
            this.b.lazySet(u);
        }

        public void onError(Throwable th) {
            this.b.otherError(th);
        }
    }

    public ObservableWithLatestFrom(p<T> pVar, wh<? super T, ? super U, ? extends R> whVar, p<? extends U> pVar2) {
        super(pVar);
        this.b = whVar;
        this.c = pVar2;
    }

    public void subscribeActual(r<? super R> rVar) {
        e eVar = new e(rVar);
        WithLatestFromObserver withLatestFromObserver = new WithLatestFromObserver(eVar, this.b);
        eVar.onSubscribe(withLatestFromObserver);
        this.c.subscribe(new a(withLatestFromObserver));
        this.a.subscribe(withLatestFromObserver);
    }
}
