package io.reactivex.internal.observers;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.c;
import io.reactivex.r;
import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: FutureObserver */
public final class h<T> extends CountDownLatch implements b, r<T>, Future<T> {
    T a;
    Throwable b;
    final AtomicReference<b> c = new AtomicReference();

    public void dispose() {
    }

    public h() {
        super(1);
    }

    public boolean cancel(boolean z) {
        DisposableHelper disposableHelper;
        do {
            disposableHelper = (b) this.c.get();
            if (disposableHelper == this || disposableHelper == DisposableHelper.DISPOSED) {
                return false;
            }
        } while (!this.c.compareAndSet(disposableHelper, DisposableHelper.DISPOSED));
        if (disposableHelper != null) {
            disposableHelper.dispose();
        }
        countDown();
        return true;
    }

    public boolean isCancelled() {
        return DisposableHelper.isDisposed((b) this.c.get());
    }

    public boolean isDone() {
        return getCount() == 0;
    }

    public T get() throws InterruptedException, ExecutionException {
        if (getCount() != 0) {
            c.a();
            await();
        }
        if (isCancelled()) {
            throw new CancellationException();
        }
        Throwable th = this.b;
        if (th == null) {
            return this.a;
        }
        throw new ExecutionException(th);
    }

    public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        if (getCount() != 0) {
            c.a();
            if (!await(j, timeUnit)) {
                throw new TimeoutException(ExceptionHelper.a(j, timeUnit));
            }
        }
        if (isCancelled()) {
            throw new CancellationException();
        }
        Throwable th = this.b;
        if (th == null) {
            return this.a;
        }
        throw new ExecutionException(th);
    }

    public void onSubscribe(b bVar) {
        DisposableHelper.setOnce(this.c, bVar);
    }

    public void onNext(T t) {
        if (this.a != null) {
            ((b) this.c.get()).dispose();
            onError(new IndexOutOfBoundsException("More than one element received"));
            return;
        }
        this.a = t;
    }

    public void onError(Throwable th) {
        if (this.b == null) {
            this.b = th;
            DisposableHelper disposableHelper;
            do {
                disposableHelper = (b) this.c.get();
                if (disposableHelper == this || disposableHelper == DisposableHelper.DISPOSED) {
                    xk.a(th);
                    return;
                }
            } while (!this.c.compareAndSet(disposableHelper, this));
            countDown();
            return;
        }
        xk.a(th);
    }

    public void onComplete() {
        if (this.a == null) {
            onError(new NoSuchElementException("The source is empty"));
            return;
        }
        DisposableHelper disposableHelper;
        do {
            disposableHelper = (b) this.c.get();
            if (disposableHelper == this || disposableHelper == DisposableHelper.DISPOSED) {
                return;
            }
        } while (!this.c.compareAndSet(disposableHelper, this));
        countDown();
    }

    public boolean isDisposed() {
        return isDone();
    }
}
