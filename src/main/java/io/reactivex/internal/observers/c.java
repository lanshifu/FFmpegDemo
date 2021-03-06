package io.reactivex.internal.observers;

import io.reactivex.disposables.b;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.r;
import java.util.concurrent.CountDownLatch;

/* compiled from: BlockingBaseObserver */
public abstract class c<T> extends CountDownLatch implements b, r<T> {
    T a;
    Throwable b;
    b c;
    volatile boolean d;

    public c() {
        super(1);
    }

    public final void onSubscribe(b bVar) {
        this.c = bVar;
        if (this.d) {
            bVar.dispose();
        }
    }

    public final void onComplete() {
        countDown();
    }

    public final void dispose() {
        this.d = true;
        b bVar = this.c;
        if (bVar != null) {
            bVar.dispose();
        }
    }

    public final boolean isDisposed() {
        return this.d;
    }

    public final T a() {
        if (getCount() != 0) {
            try {
                io.reactivex.internal.util.c.a();
                await();
            } catch (InterruptedException e) {
                dispose();
                throw ExceptionHelper.a(e);
            }
        }
        Throwable e2 = this.b;
        if (e2 == null) {
            return this.a;
        }
        throw ExceptionHelper.a(e2);
    }
}
