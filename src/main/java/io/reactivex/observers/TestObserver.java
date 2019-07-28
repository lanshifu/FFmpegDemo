package io.reactivex.observers;

import defpackage.wy;
import io.reactivex.b;
import io.reactivex.h;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.r;
import io.reactivex.u;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class TestObserver<T> extends a<T, TestObserver<T>> implements b, io.reactivex.disposables.b, h<T>, r<T>, u<T> {
    private final r<? super T> i;
    private final AtomicReference<io.reactivex.disposables.b> j;
    private wy<T> k;

    enum EmptyObserver implements r<Object> {
        INSTANCE;

        public void onComplete() {
        }

        public void onError(Throwable th) {
        }

        public void onNext(Object obj) {
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
        }
    }

    public TestObserver() {
        this(EmptyObserver.INSTANCE);
    }

    public TestObserver(r<? super T> rVar) {
        this.j = new AtomicReference();
        this.i = rVar;
    }

    public void onSubscribe(io.reactivex.disposables.b bVar) {
        this.e = Thread.currentThread();
        if (bVar == null) {
            this.c.add(new NullPointerException("onSubscribe received a null Subscription"));
        } else if (this.j.compareAndSet(null, bVar)) {
            if (this.g != 0 && (bVar instanceof wy)) {
                this.k = (wy) bVar;
                int requestFusion = this.k.requestFusion(this.g);
                this.h = requestFusion;
                if (requestFusion == 1) {
                    this.f = true;
                    this.e = Thread.currentThread();
                    while (true) {
                        try {
                            Object poll = this.k.poll();
                            if (poll == null) {
                                break;
                            }
                            this.b.add(poll);
                        } catch (Throwable th) {
                            this.c.add(th);
                        }
                    }
                    this.d++;
                    this.j.lazySet(DisposableHelper.DISPOSED);
                    return;
                }
            }
            this.i.onSubscribe(bVar);
        } else {
            bVar.dispose();
            if (this.j.get() != DisposableHelper.DISPOSED) {
                List list = this.c;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onSubscribe received multiple subscriptions: ");
                stringBuilder.append(bVar);
                list.add(new IllegalStateException(stringBuilder.toString()));
            }
        }
    }

    public void onNext(T t) {
        if (!this.f) {
            this.f = true;
            if (this.j.get() == null) {
                this.c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        this.e = Thread.currentThread();
        if (this.h == 2) {
            while (true) {
                try {
                    Object poll = this.k.poll();
                    if (poll == null) {
                        break;
                    }
                    this.b.add(poll);
                } catch (Throwable th) {
                    this.c.add(th);
                    this.k.dispose();
                }
            }
            return;
        }
        this.b.add(t);
        if (t == null) {
            this.c.add(new NullPointerException("onNext received a null value"));
        }
        this.i.onNext(t);
    }

    public void onError(Throwable th) {
        if (!this.f) {
            this.f = true;
            if (this.j.get() == null) {
                this.c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            this.e = Thread.currentThread();
            if (th == null) {
                this.c.add(new NullPointerException("onError received a null Throwable"));
            } else {
                this.c.add(th);
            }
            this.i.onError(th);
        } finally {
            this.a.countDown();
        }
    }

    public void onComplete() {
        if (!this.f) {
            this.f = true;
            if (this.j.get() == null) {
                this.c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            this.e = Thread.currentThread();
            this.d++;
            this.i.onComplete();
        } finally {
            this.a.countDown();
        }
    }

    public final void dispose() {
        DisposableHelper.dispose(this.j);
    }

    public final boolean isDisposed() {
        return DisposableHelper.isDisposed((io.reactivex.disposables.b) this.j.get());
    }

    public void onSuccess(T t) {
        onNext(t);
        onComplete();
    }
}
