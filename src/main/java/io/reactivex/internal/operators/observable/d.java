package io.reactivex.internal.operators.observable;

import defpackage.xk;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.j;
import io.reactivex.observers.c;
import io.reactivex.p;
import io.reactivex.r;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: BlockingObservableNext */
public final class d<T> implements Iterable<T> {
    final p<T> a;

    /* compiled from: BlockingObservableNext */
    static final class a<T> implements Iterator<T> {
        private final b<T> a;
        private final p<T> b;
        private T c;
        private boolean d = true;
        private boolean e = true;
        private Throwable f;
        private boolean g;

        a(p<T> pVar, b<T> bVar) {
            this.b = pVar;
            this.a = bVar;
        }

        public boolean hasNext() {
            if (this.f == null) {
                boolean z = false;
                if (!this.d) {
                    return false;
                }
                if (!this.e || a()) {
                    z = true;
                }
                return z;
            }
            throw ExceptionHelper.a(this.f);
        }

        private boolean a() {
            if (!this.g) {
                this.g = true;
                this.a.c();
                new az(this.b).subscribe((r) this.a);
            }
            try {
                j b = this.a.b();
                if (b.c()) {
                    this.e = false;
                    this.c = b.d();
                    return true;
                }
                this.d = false;
                if (b.a()) {
                    return false;
                }
                this.f = b.e();
                throw ExceptionHelper.a(this.f);
            } catch (InterruptedException e) {
                this.a.dispose();
                this.f = e;
                throw ExceptionHelper.a(e);
            }
        }

        public T next() {
            if (this.f != null) {
                throw ExceptionHelper.a(this.f);
            } else if (hasNext()) {
                this.e = true;
                return this.c;
            } else {
                throw new NoSuchElementException("No more elements");
            }
        }

        public void remove() {
            throw new UnsupportedOperationException("Read only iterator");
        }
    }

    /* compiled from: BlockingObservableNext */
    static final class b<T> extends c<j<T>> {
        final AtomicInteger a = new AtomicInteger();
        private final BlockingQueue<j<T>> b = new ArrayBlockingQueue(1);

        public void onComplete() {
        }

        b() {
        }

        public void onError(Throwable th) {
            xk.a(th);
        }

        /* renamed from: a */
        public void onNext(j<T> jVar) {
            if (this.a.getAndSet(0) == 1 || !jVar.c()) {
                Object jVar2;
                while (!this.b.offer(jVar2)) {
                    j jVar3 = (j) this.b.poll();
                    if (!(jVar3 == null || jVar3.c())) {
                        jVar2 = jVar3;
                    }
                }
            }
        }

        public j<T> b() throws InterruptedException {
            c();
            io.reactivex.internal.util.c.a();
            return (j) this.b.take();
        }

        /* Access modifiers changed, original: 0000 */
        public void c() {
            this.a.set(1);
        }
    }

    public d(p<T> pVar) {
        this.a = pVar;
    }

    public Iterator<T> iterator() {
        return new a(this.a, new b());
    }
}
