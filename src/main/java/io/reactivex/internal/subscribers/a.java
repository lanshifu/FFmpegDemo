package io.reactivex.internal.subscribers;

import defpackage.ww;
import defpackage.xa;
import defpackage.xk;
import io.reactivex.internal.subscriptions.SubscriptionHelper;

/* compiled from: BasicFuseableConditionalSubscriber */
public abstract class a<T, R> implements ww<T>, xa<R> {
    protected final ww<? super R> b;
    protected aas c;
    protected xa<T> d;
    protected boolean e;
    protected int f;

    /* Access modifiers changed, original: protected */
    public boolean a() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void b() {
    }

    public a(ww<? super R> wwVar) {
        this.b = wwVar;
    }

    public final void onSubscribe(aas aas) {
        if (SubscriptionHelper.validate(this.c, aas)) {
            this.c = aas;
            if (aas instanceof xa) {
                this.d = (xa) aas;
            }
            if (a()) {
                this.b.onSubscribe(this);
                b();
            }
        }
    }

    public void onError(Throwable th) {
        if (this.e) {
            xk.a(th);
            return;
        }
        this.e = true;
        this.b.onError(th);
    }

    /* Access modifiers changed, original: protected|final */
    public final void a(Throwable th) {
        io.reactivex.exceptions.a.b(th);
        this.c.cancel();
        onError(th);
    }

    public void onComplete() {
        if (!this.e) {
            this.e = true;
            this.b.onComplete();
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final int a(int i) {
        xa xaVar = this.d;
        if (xaVar == null || (i & 4) != 0) {
            return 0;
        }
        i = xaVar.requestFusion(i);
        if (i != 0) {
            this.f = i;
        }
        return i;
    }

    public void request(long j) {
        this.c.request(j);
    }

    public void cancel() {
        this.c.cancel();
    }

    public boolean isEmpty() {
        return this.d.isEmpty();
    }

    public void clear() {
        this.d.clear();
    }

    public final boolean offer(R r) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
