package io.reactivex.internal.operators.observable;

import defpackage.wf;
import defpackage.wy;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.p;
import io.reactivex.r;

public final class ObservableDoFinally<T> extends a<T, T> {
    final wf b;

    static final class DoFinallyObserver<T> extends BasicIntQueueDisposable<T> implements r<T> {
        private static final long serialVersionUID = 4109457741734051389L;
        final r<? super T> downstream;
        final wf onFinally;
        wy<T> qd;
        boolean syncFused;
        b upstream;

        DoFinallyObserver(r<? super T> rVar, wf wfVar) {
            this.downstream = rVar;
            this.onFinally = wfVar;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                if (bVar instanceof wy) {
                    this.qd = (wy) bVar;
                }
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
            runFinally();
        }

        public void onComplete() {
            this.downstream.onComplete();
            runFinally();
        }

        public void dispose() {
            this.upstream.dispose();
            runFinally();
        }

        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        public int requestFusion(int i) {
            wy wyVar = this.qd;
            if (wyVar == null || (i & 4) != 0) {
                return 0;
            }
            i = wyVar.requestFusion(i);
            if (i != 0) {
                boolean z = true;
                if (i != 1) {
                    z = false;
                }
                this.syncFused = z;
            }
            return i;
        }

        public void clear() {
            this.qd.clear();
        }

        public boolean isEmpty() {
            return this.qd.isEmpty();
        }

        public T poll() throws Exception {
            Object poll = this.qd.poll();
            if (poll == null && this.syncFused) {
                runFinally();
            }
            return poll;
        }

        /* Access modifiers changed, original: 0000 */
        public void runFinally() {
            if (compareAndSet(0, 1)) {
                try {
                    this.onFinally.a();
                } catch (Throwable th) {
                    a.b(th);
                    xk.a(th);
                }
            }
        }
    }

    public ObservableDoFinally(p<T> pVar, wf wfVar) {
        super(pVar);
        this.b = wfVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe(new DoFinallyObserver(rVar, this.b));
    }
}
