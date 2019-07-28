package io.reactivex.internal.operators.observable;

import defpackage.wm;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.subjects.PublishSubject;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservablePublishSelector<T, R> extends a<T, R> {
    final wm<? super k<T>, ? extends p<R>> b;

    static final class TargetObserver<T, R> extends AtomicReference<b> implements b, r<R> {
        private static final long serialVersionUID = 854110278590336484L;
        final r<? super R> downstream;
        b upstream;

        TargetObserver(r<? super R> rVar) {
            this.downstream = rVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(R r) {
            this.downstream.onNext(r);
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this);
            this.downstream.onError(th);
        }

        public void onComplete() {
            DisposableHelper.dispose(this);
            this.downstream.onComplete();
        }

        public void dispose() {
            this.upstream.dispose();
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }
    }

    static final class a<T, R> implements r<T> {
        final PublishSubject<T> a;
        final AtomicReference<b> b;

        a(PublishSubject<T> publishSubject, AtomicReference<b> atomicReference) {
            this.a = publishSubject;
            this.b = atomicReference;
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.b, bVar);
        }

        public void onNext(T t) {
            this.a.onNext(t);
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onComplete() {
            this.a.onComplete();
        }
    }

    public ObservablePublishSelector(p<T> pVar, wm<? super k<T>, ? extends p<R>> wmVar) {
        super(pVar);
        this.b = wmVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super R> rVar) {
        PublishSubject a = PublishSubject.a();
        try {
            p pVar = (p) io.reactivex.internal.functions.a.a(this.b.apply(a), "The selector returned a null ObservableSource");
            TargetObserver targetObserver = new TargetObserver(rVar);
            pVar.subscribe(targetObserver);
            this.a.subscribe(new a(a, targetObserver));
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, (r) rVar);
        }
    }
}
