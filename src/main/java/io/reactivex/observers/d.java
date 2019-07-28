package io.reactivex.observers;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.r;

/* compiled from: SafeObserver */
public final class d<T> implements b, r<T> {
    final r<? super T> a;
    b b;
    boolean c;

    public d(r<? super T> rVar) {
        this.a = rVar;
    }

    public void onSubscribe(b bVar) {
        if (DisposableHelper.validate(this.b, bVar)) {
            this.b = bVar;
            try {
                this.a.onSubscribe(this);
            } catch (Throwable th) {
                a.b(th);
                xk.a(new CompositeException(th, th));
            }
        }
    }

    public void dispose() {
        this.b.dispose();
    }

    public boolean isDisposed() {
        return this.b.isDisposed();
    }

    public void onNext(T t) {
        if (!this.c) {
            if (this.b == null) {
                a();
            } else if (t == null) {
                NullPointerException nullPointerException = new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
                try {
                    this.b.dispose();
                    onError(nullPointerException);
                } catch (Throwable th) {
                    a.b(th);
                    onError(new CompositeException(nullPointerException, th));
                }
            } else {
                try {
                    this.a.onNext(t);
                } catch (Throwable th2) {
                    a.b(th2);
                    onError(new CompositeException(th, th2));
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a() {
        this.c = true;
        NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
        try {
            this.a.onSubscribe(EmptyDisposable.INSTANCE);
            try {
                this.a.onError(nullPointerException);
            } catch (Throwable th) {
                a.b(th);
                xk.a(new CompositeException(nullPointerException, th));
            }
        } catch (Throwable th2) {
            a.b(th2);
            xk.a(new CompositeException(nullPointerException, th2));
        }
    }

    public void onError(Throwable th) {
        if (this.c) {
            xk.a(th);
            return;
        }
        this.c = true;
        if (this.b == null) {
            NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
            try {
                this.a.onSubscribe(EmptyDisposable.INSTANCE);
                try {
                    this.a.onError(new CompositeException(th, nullPointerException));
                } catch (Throwable th2) {
                    a.b(th2);
                    xk.a(new CompositeException(th, nullPointerException, th2));
                }
                return;
            } catch (Throwable th22) {
                a.b(th22);
                xk.a(new CompositeException(th, nullPointerException, th22));
                return;
            }
        }
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        try {
            this.a.onError(th);
        } catch (Throwable th3) {
            a.b(th3);
            xk.a(new CompositeException(th, th3));
        }
    }

    public void onComplete() {
        if (!this.c) {
            this.c = true;
            if (this.b == null) {
                b();
                return;
            }
            try {
                this.a.onComplete();
            } catch (Throwable th) {
                a.b(th);
                xk.a(th);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        NullPointerException nullPointerException = new NullPointerException("Subscription not set!");
        try {
            this.a.onSubscribe(EmptyDisposable.INSTANCE);
            try {
                this.a.onError(nullPointerException);
            } catch (Throwable th) {
                a.b(th);
                xk.a(new CompositeException(nullPointerException, th));
            }
        } catch (Throwable th2) {
            a.b(th2);
            xk.a(new CompositeException(nullPointerException, th2));
        }
    }
}
