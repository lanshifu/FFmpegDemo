package io.reactivex.internal.operators.observable;

import io.reactivex.internal.disposables.ArrayCompositeDisposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.e;
import io.reactivex.p;
import io.reactivex.r;

/* compiled from: ObservableSkipUntil */
public final class bm<T, U> extends a<T, T> {
    final p<U> b;

    /* compiled from: ObservableSkipUntil */
    final class a implements r<U> {
        final ArrayCompositeDisposable a;
        final b<T> b;
        final e<T> c;
        io.reactivex.disposables.b d;

        a(ArrayCompositeDisposable arrayCompositeDisposable, b<T> bVar, e<T> eVar) {
            this.a = arrayCompositeDisposable;
            this.b = bVar;
            this.c = eVar;
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.d, bVar)) {
                this.d = bVar;
                this.a.setResource(1, bVar);
            }
        }

        public void onNext(U u) {
            this.d.dispose();
            this.b.d = true;
        }

        public void onError(Throwable th) {
            this.a.dispose();
            this.c.onError(th);
        }

        public void onComplete() {
            this.b.d = true;
        }
    }

    /* compiled from: ObservableSkipUntil */
    static final class b<T> implements r<T> {
        final r<? super T> a;
        final ArrayCompositeDisposable b;
        io.reactivex.disposables.b c;
        volatile boolean d;
        boolean e;

        b(r<? super T> rVar, ArrayCompositeDisposable arrayCompositeDisposable) {
            this.a = rVar;
            this.b = arrayCompositeDisposable;
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.c, bVar)) {
                this.c = bVar;
                this.b.setResource(0, bVar);
            }
        }

        public void onNext(T t) {
            if (this.e) {
                this.a.onNext(t);
            } else if (this.d) {
                this.e = true;
                this.a.onNext(t);
            }
        }

        public void onError(Throwable th) {
            this.b.dispose();
            this.a.onError(th);
        }

        public void onComplete() {
            this.b.dispose();
            this.a.onComplete();
        }
    }

    public bm(p<T> pVar, p<U> pVar2) {
        super(pVar);
        this.b = pVar2;
    }

    public void subscribeActual(r<? super T> rVar) {
        e eVar = new e(rVar);
        ArrayCompositeDisposable arrayCompositeDisposable = new ArrayCompositeDisposable(2);
        eVar.onSubscribe(arrayCompositeDisposable);
        b bVar = new b(eVar, arrayCompositeDisposable);
        this.b.subscribe(new a(arrayCompositeDisposable, bVar, eVar));
        this.a.subscribe(bVar);
    }
}
