package io.reactivex.internal.operators.observable;

import defpackage.xk;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.j;
import io.reactivex.k;
import io.reactivex.observers.c;
import io.reactivex.p;
import io.reactivex.r;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: BlockingObservableLatest */
public final class b<T> implements Iterable<T> {
    final p<T> a;

    /* compiled from: BlockingObservableLatest */
    static final class a<T> extends c<j<T>> implements Iterator<T> {
        j<T> a;
        final Semaphore b = new Semaphore(0);
        final AtomicReference<j<T>> c = new AtomicReference();

        public void onComplete() {
        }

        a() {
        }

        /* renamed from: a */
        public void onNext(j<T> jVar) {
            if ((this.c.getAndSet(jVar) == null ? 1 : null) != null) {
                this.b.release();
            }
        }

        public void onError(Throwable th) {
            xk.a(th);
        }

        public boolean hasNext() {
            if (this.a == null || !this.a.b()) {
                if (this.a == null) {
                    try {
                        io.reactivex.internal.util.c.a();
                        this.b.acquire();
                        j jVar = (j) this.c.getAndSet(null);
                        this.a = jVar;
                        if (jVar.b()) {
                            throw ExceptionHelper.a(jVar.e());
                        }
                    } catch (InterruptedException e) {
                        dispose();
                        this.a = j.a(e);
                        throw ExceptionHelper.a(e);
                    }
                }
                return this.a.c();
            }
            throw ExceptionHelper.a(this.a.e());
        }

        public T next() {
            if (hasNext()) {
                Object d = this.a.d();
                this.a = null;
                return d;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException("Read-only iterator.");
        }
    }

    public b(p<T> pVar) {
        this.a = pVar;
    }

    public Iterator<T> iterator() {
        r aVar = new a();
        k.wrap(this.a).materialize().subscribe(aVar);
        return aVar;
    }
}
