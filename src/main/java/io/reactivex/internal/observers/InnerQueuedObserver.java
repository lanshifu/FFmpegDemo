package io.reactivex.internal.observers;

import defpackage.wy;
import defpackage.xd;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.k;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicReference;

public final class InnerQueuedObserver<T> extends AtomicReference<b> implements b, r<T> {
    private static final long serialVersionUID = -5417183359794346637L;
    volatile boolean done;
    int fusionMode;
    final i<T> parent;
    final int prefetch;
    xd<T> queue;

    public InnerQueuedObserver(i<T> iVar, int i) {
        this.parent = iVar;
        this.prefetch = i;
    }

    public void onSubscribe(b bVar) {
        if (DisposableHelper.setOnce(this, bVar)) {
            if (bVar instanceof wy) {
                wy wyVar = (wy) bVar;
                int requestFusion = wyVar.requestFusion(3);
                if (requestFusion == 1) {
                    this.fusionMode = requestFusion;
                    this.queue = wyVar;
                    this.done = true;
                    this.parent.innerComplete(this);
                    return;
                } else if (requestFusion == 2) {
                    this.fusionMode = requestFusion;
                    this.queue = wyVar;
                    return;
                }
            }
            this.queue = k.a(-this.prefetch);
        }
    }

    public void onNext(T t) {
        if (this.fusionMode == 0) {
            this.parent.innerNext(this, t);
        } else {
            this.parent.drain();
        }
    }

    public void onError(Throwable th) {
        this.parent.innerError(this, th);
    }

    public void onComplete() {
        this.parent.innerComplete(this);
    }

    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return DisposableHelper.isDisposed((b) get());
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone() {
        this.done = true;
    }

    public xd<T> queue() {
        return this.queue;
    }

    public int fusionMode() {
        return this.fusionMode;
    }
}
