package io.reactivex.internal.operators.observable;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.e;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.s;
import io.reactivex.s.c;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableDebounceTimed<T> extends a<T, T> {
    final long b;
    final TimeUnit c;
    final s d;

    static final class DebounceEmitter<T> extends AtomicReference<b> implements b, Runnable {
        private static final long serialVersionUID = 6812032969491025141L;
        final long idx;
        final AtomicBoolean once = new AtomicBoolean();
        final a<T> parent;
        final T value;

        DebounceEmitter(T t, long j, a<T> aVar) {
            this.value = t;
            this.idx = j;
            this.parent = aVar;
        }

        public void run() {
            if (this.once.compareAndSet(false, true)) {
                this.parent.a(this.idx, this.value, this);
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        public void setResource(b bVar) {
            DisposableHelper.replace(this, bVar);
        }
    }

    static final class a<T> implements b, r<T> {
        final r<? super T> a;
        final long b;
        final TimeUnit c;
        final c d;
        b e;
        b f;
        volatile long g;
        boolean h;

        a(r<? super T> rVar, long j, TimeUnit timeUnit, c cVar) {
            this.a = rVar;
            this.b = j;
            this.c = timeUnit;
            this.d = cVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.e, bVar)) {
                this.e = bVar;
                this.a.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.h) {
                long j = this.g + 1;
                this.g = j;
                b bVar = this.f;
                if (bVar != null) {
                    bVar.dispose();
                }
                DebounceEmitter debounceEmitter = new DebounceEmitter(t, j, this);
                this.f = debounceEmitter;
                debounceEmitter.setResource(this.d.a(debounceEmitter, this.b, this.c));
            }
        }

        public void onError(Throwable th) {
            if (this.h) {
                xk.a(th);
                return;
            }
            b bVar = this.f;
            if (bVar != null) {
                bVar.dispose();
            }
            this.h = true;
            this.a.onError(th);
            this.d.dispose();
        }

        public void onComplete() {
            if (!this.h) {
                this.h = true;
                b bVar = this.f;
                if (bVar != null) {
                    bVar.dispose();
                }
                DebounceEmitter debounceEmitter = (DebounceEmitter) bVar;
                if (debounceEmitter != null) {
                    debounceEmitter.run();
                }
                this.a.onComplete();
                this.d.dispose();
            }
        }

        public void dispose() {
            this.e.dispose();
            this.d.dispose();
        }

        public boolean isDisposed() {
            return this.d.isDisposed();
        }

        /* Access modifiers changed, original: 0000 */
        public void a(long j, T t, DebounceEmitter<T> debounceEmitter) {
            if (j == this.g) {
                this.a.onNext(t);
                debounceEmitter.dispose();
            }
        }
    }

    public ObservableDebounceTimed(p<T> pVar, long j, TimeUnit timeUnit, s sVar) {
        super(pVar);
        this.b = j;
        this.c = timeUnit;
        this.d = sVar;
    }

    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new a(new e(rVar), this.b, this.c, this.d.a()));
    }
}
