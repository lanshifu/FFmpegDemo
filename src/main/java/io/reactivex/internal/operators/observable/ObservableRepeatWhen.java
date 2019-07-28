package io.reactivex.internal.operators.observable;

import defpackage.wm;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.f;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.c;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableRepeatWhen<T> extends a<T, T> {
    final wm<? super k<Object>, ? extends p<?>> b;

    static final class RepeatWhenObserver<T> extends AtomicInteger implements b, r<T> {
        private static final long serialVersionUID = 802743776666017014L;
        volatile boolean active;
        final r<? super T> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        final InnerRepeatObserver inner = new InnerRepeatObserver();
        final c<Object> signaller;
        final p<T> source;
        final AtomicReference<b> upstream = new AtomicReference();
        final AtomicInteger wip = new AtomicInteger();

        final class InnerRepeatObserver extends AtomicReference<b> implements r<Object> {
            private static final long serialVersionUID = 3254781284376480842L;

            InnerRepeatObserver() {
            }

            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            public void onNext(Object obj) {
                RepeatWhenObserver.this.innerNext();
            }

            public void onError(Throwable th) {
                RepeatWhenObserver.this.innerError(th);
            }

            public void onComplete() {
                RepeatWhenObserver.this.innerComplete();
            }
        }

        RepeatWhenObserver(r<? super T> rVar, c<Object> cVar, p<T> pVar) {
            this.downstream = rVar;
            this.signaller = cVar;
            this.source = pVar;
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.replace(this.upstream, bVar);
        }

        public void onNext(T t) {
            f.a(this.downstream, (Object) t, (AtomicInteger) this, this.error);
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this.inner);
            f.a(this.downstream, th, (AtomicInteger) this, this.error);
        }

        public void onComplete() {
            this.active = false;
            this.signaller.onNext(Integer.valueOf(0));
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) this.upstream.get());
        }

        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this.inner);
        }

        /* Access modifiers changed, original: 0000 */
        public void innerNext() {
            subscribeNext();
        }

        /* Access modifiers changed, original: 0000 */
        public void innerError(Throwable th) {
            DisposableHelper.dispose(this.upstream);
            f.a(this.downstream, th, (AtomicInteger) this, this.error);
        }

        /* Access modifiers changed, original: 0000 */
        public void innerComplete() {
            DisposableHelper.dispose(this.upstream);
            f.a(this.downstream, (AtomicInteger) this, this.error);
        }

        /* Access modifiers changed, original: 0000 */
        public void subscribeNext() {
            if (this.wip.getAndIncrement() == 0) {
                while (!isDisposed()) {
                    if (!this.active) {
                        this.active = true;
                        this.source.subscribe(this);
                    }
                    if (this.wip.decrementAndGet() == 0) {
                    }
                }
            }
        }
    }

    public ObservableRepeatWhen(p<T> pVar, wm<? super k<Object>, ? extends p<?>> wmVar) {
        super(pVar);
        this.b = wmVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        c b = PublishSubject.a().b();
        try {
            p pVar = (p) a.a(this.b.apply(b), "The handler returned a null ObservableSource");
            RepeatWhenObserver repeatWhenObserver = new RepeatWhenObserver(rVar, b, this.a);
            rVar.onSubscribe(repeatWhenObserver);
            pVar.subscribe(repeatWhenObserver.inner);
            repeatWhenObserver.subscribeNext();
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, (r) rVar);
        }
    }
}
