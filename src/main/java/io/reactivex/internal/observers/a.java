package io.reactivex.internal.observers;

import defpackage.wy;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.r;

/* compiled from: BasicFuseableObserver */
public abstract class a<T, R> implements r<T>, wy<R> {
    protected final r<? super R> a;
    protected b b;
    protected wy<T> c;
    protected boolean d;
    protected int e;

    /* Access modifiers changed, original: protected */
    public boolean a() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void b() {
    }

    public a(r<? super R> rVar) {
        this.a = rVar;
    }

    public final void onSubscribe(b bVar) {
        if (DisposableHelper.validate(this.b, bVar)) {
            this.b = bVar;
            if (bVar instanceof wy) {
                this.c = (wy) bVar;
            }
            if (a()) {
                this.a.onSubscribe(this);
                b();
            }
        }
    }

    public void onError(Throwable th) {
        if (this.d) {
            xk.a(th);
            return;
        }
        this.d = true;
        this.a.onError(th);
    }

    /* Access modifiers changed, original: protected|final */
    public final void a(Throwable th) {
        io.reactivex.exceptions.a.b(th);
        this.b.dispose();
        onError(th);
    }

    public void onComplete() {
        if (!this.d) {
            this.d = true;
            this.a.onComplete();
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final int a(int i) {
        wy wyVar = this.c;
        if (wyVar == null || (i & 4) != 0) {
            return 0;
        }
        i = wyVar.requestFusion(i);
        if (i != 0) {
            this.e = i;
        }
        return i;
    }

    public void dispose() {
        this.b.dispose();
    }

    public boolean isDisposed() {
        return this.b.isDisposed();
    }

    public boolean isEmpty() {
        return this.c.isEmpty();
    }

    public void clear() {
        this.c.clear();
    }

    public final boolean offer(R r) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
