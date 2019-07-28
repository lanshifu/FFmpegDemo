package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.p;
import io.reactivex.r;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ObservableBuffer<T, U extends Collection<? super T>> extends a<T, U> {
    final int b;
    final int c;
    final Callable<U> d;

    static final class BufferSkipObserver<T, U extends Collection<? super T>> extends AtomicBoolean implements b, r<T> {
        private static final long serialVersionUID = -8223395059921494546L;
        final Callable<U> bufferSupplier;
        final ArrayDeque<U> buffers = new ArrayDeque();
        final int count;
        final r<? super U> downstream;
        long index;
        final int skip;
        b upstream;

        BufferSkipObserver(r<? super U> rVar, int i, int i2, Callable<U> callable) {
            this.downstream = rVar;
            this.count = i;
            this.skip = i2;
            this.bufferSupplier = callable;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void dispose() {
            this.upstream.dispose();
        }

        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        public void onNext(T t) {
            long j = this.index;
            this.index = 1 + j;
            if (j % ((long) this.skip) == 0) {
                try {
                    this.buffers.offer((Collection) io.reactivex.internal.functions.a.a(this.bufferSupplier.call(), "The bufferSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources."));
                } catch (Throwable th) {
                    this.buffers.clear();
                    this.upstream.dispose();
                    this.downstream.onError(th);
                    return;
                }
            }
            Iterator it = this.buffers.iterator();
            while (it.hasNext()) {
                Collection collection = (Collection) it.next();
                collection.add(t);
                if (this.count <= collection.size()) {
                    it.remove();
                    this.downstream.onNext(collection);
                }
            }
        }

        public void onError(Throwable th) {
            this.buffers.clear();
            this.downstream.onError(th);
        }

        public void onComplete() {
            while (!this.buffers.isEmpty()) {
                this.downstream.onNext(this.buffers.poll());
            }
            this.downstream.onComplete();
        }
    }

    static final class a<T, U extends Collection<? super T>> implements b, r<T> {
        final r<? super U> a;
        final int b;
        final Callable<U> c;
        U d;
        int e;
        b f;

        a(r<? super U> rVar, int i, Callable<U> callable) {
            this.a = rVar;
            this.b = i;
            this.c = callable;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean a() {
            try {
                this.d = (Collection) io.reactivex.internal.functions.a.a(this.c.call(), "Empty buffer supplied");
                return true;
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.d = null;
                if (this.f == null) {
                    EmptyDisposable.error(th, this.a);
                } else {
                    this.f.dispose();
                    this.a.onError(th);
                }
                return false;
            }
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.f, bVar)) {
                this.f = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f.dispose();
        }

        public boolean isDisposed() {
            return this.f.isDisposed();
        }

        public void onNext(T t) {
            Collection collection = this.d;
            if (collection != null) {
                collection.add(t);
                int i = this.e + 1;
                this.e = i;
                if (i >= this.b) {
                    this.a.onNext(collection);
                    this.e = 0;
                    a();
                }
            }
        }

        public void onError(Throwable th) {
            this.d = null;
            this.a.onError(th);
        }

        public void onComplete() {
            Collection collection = this.d;
            if (collection != null) {
                this.d = null;
                if (!collection.isEmpty()) {
                    this.a.onNext(collection);
                }
                this.a.onComplete();
            }
        }
    }

    public ObservableBuffer(p<T> pVar, int i, int i2, Callable<U> callable) {
        super(pVar);
        this.b = i;
        this.c = i2;
        this.d = callable;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super U> rVar) {
        if (this.c == this.b) {
            a aVar = new a(rVar, this.b, this.d);
            if (aVar.a()) {
                this.a.subscribe(aVar);
                return;
            }
            return;
        }
        this.a.subscribe(new BufferSkipObserver(rVar, this.b, this.c, this.d));
    }
}
