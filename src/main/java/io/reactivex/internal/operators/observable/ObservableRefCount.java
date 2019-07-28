package io.reactivex.internal.operators.observable;

import defpackage.wl;
import defpackage.xi;
import defpackage.xk;
import defpackage.xl;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.c;
import io.reactivex.k;
import io.reactivex.r;
import io.reactivex.s;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableRefCount<T> extends k<T> {
    final xi<T> a;
    final int b;
    final long c;
    final TimeUnit d;
    final s e;
    RefConnection f;

    static final class RefConnection extends AtomicReference<b> implements Runnable, wl<b> {
        private static final long serialVersionUID = -4552101107598366241L;
        boolean connected;
        final ObservableRefCount<?> parent;
        long subscriberCount;
        b timer;

        RefConnection(ObservableRefCount<?> observableRefCount) {
            this.parent = observableRefCount;
        }

        public void run() {
            this.parent.c(this);
        }

        public void accept(b bVar) throws Exception {
            DisposableHelper.replace(this, bVar);
        }
    }

    static final class RefCountObserver<T> extends AtomicBoolean implements b, r<T> {
        private static final long serialVersionUID = -7419642935409022375L;
        final RefConnection connection;
        final r<? super T> downstream;
        final ObservableRefCount<T> parent;
        b upstream;

        RefCountObserver(r<? super T> rVar, ObservableRefCount<T> observableRefCount, RefConnection refConnection) {
            this.downstream = rVar;
            this.parent = observableRefCount;
            this.connection = refConnection;
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onError(Throwable th) {
            if (compareAndSet(false, true)) {
                this.parent.b(this.connection);
                this.downstream.onError(th);
                return;
            }
            xk.a(th);
        }

        public void onComplete() {
            if (compareAndSet(false, true)) {
                this.parent.b(this.connection);
                this.downstream.onComplete();
            }
        }

        public void dispose() {
            this.upstream.dispose();
            if (compareAndSet(false, true)) {
                this.parent.a(this.connection);
            }
        }

        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }
    }

    public ObservableRefCount(xi<T> xiVar) {
        this(xiVar, 1, 0, TimeUnit.NANOSECONDS, xl.c());
    }

    public ObservableRefCount(xi<T> xiVar, int i, long j, TimeUnit timeUnit, s sVar) {
        this.a = xiVar;
        this.b = i;
        this.c = j;
        this.d = timeUnit;
        this.e = sVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        RefConnection refConnection;
        boolean z;
        synchronized (this) {
            refConnection = this.f;
            if (refConnection == null) {
                refConnection = new RefConnection(this);
                this.f = refConnection;
            }
            long j = refConnection.subscriberCount;
            if (j == 0 && refConnection.timer != null) {
                refConnection.timer.dispose();
            }
            j++;
            refConnection.subscriberCount = j;
            z = true;
            if (refConnection.connected || j != ((long) this.b)) {
                z = false;
            } else {
                refConnection.connected = true;
            }
        }
        this.a.subscribe((r) new RefCountObserver(rVar, this, refConnection));
        if (z) {
            this.a.a(refConnection);
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Missing block: B:25:0x0040, code skipped:
            return;
     */
    public void a(io.reactivex.internal.operators.observable.ObservableRefCount.RefConnection r6) {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = r5.f;	 Catch:{ all -> 0x0041 }
        if (r0 == 0) goto L_0x003f;
    L_0x0005:
        r0 = r5.f;	 Catch:{ all -> 0x0041 }
        if (r0 == r6) goto L_0x000a;
    L_0x0009:
        goto L_0x003f;
    L_0x000a:
        r0 = r6.subscriberCount;	 Catch:{ all -> 0x0041 }
        r2 = 1;
        r0 = r0 - r2;
        r6.subscriberCount = r0;	 Catch:{ all -> 0x0041 }
        r2 = 0;
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 != 0) goto L_0x003d;
    L_0x0017:
        r0 = r6.connected;	 Catch:{ all -> 0x0041 }
        if (r0 != 0) goto L_0x001c;
    L_0x001b:
        goto L_0x003d;
    L_0x001c:
        r0 = r5.c;	 Catch:{ all -> 0x0041 }
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 != 0) goto L_0x0027;
    L_0x0022:
        r5.c(r6);	 Catch:{ all -> 0x0041 }
        monitor-exit(r5);	 Catch:{ all -> 0x0041 }
        return;
    L_0x0027:
        r0 = new io.reactivex.internal.disposables.SequentialDisposable;	 Catch:{ all -> 0x0041 }
        r0.<init>();	 Catch:{ all -> 0x0041 }
        r6.timer = r0;	 Catch:{ all -> 0x0041 }
        monitor-exit(r5);	 Catch:{ all -> 0x0041 }
        r1 = r5.e;
        r2 = r5.c;
        r4 = r5.d;
        r6 = r1.a(r6, r2, r4);
        r0.replace(r6);
        return;
    L_0x003d:
        monitor-exit(r5);	 Catch:{ all -> 0x0041 }
        return;
    L_0x003f:
        monitor-exit(r5);	 Catch:{ all -> 0x0041 }
        return;
    L_0x0041:
        r6 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0041 }
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableRefCount.a(io.reactivex.internal.operators.observable.ObservableRefCount$RefConnection):void");
    }

    /* Access modifiers changed, original: 0000 */
    public void b(RefConnection refConnection) {
        synchronized (this) {
            if (this.f != null && this.f == refConnection) {
                this.f = null;
                if (refConnection.timer != null) {
                    refConnection.timer.dispose();
                }
            }
            long j = refConnection.subscriberCount - 1;
            refConnection.subscriberCount = j;
            if (j == 0) {
                if (this.a instanceof b) {
                    ((b) this.a).dispose();
                } else if (this.a instanceof c) {
                    ((c) this.a).a((b) refConnection.get());
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void c(RefConnection refConnection) {
        synchronized (this) {
            if (refConnection.subscriberCount == 0 && refConnection == this.f) {
                this.f = null;
                b bVar = (b) refConnection.get();
                DisposableHelper.dispose(refConnection);
                if (this.a instanceof b) {
                    ((b) this.a).dispose();
                } else if (this.a instanceof c) {
                    ((c) this.a).a(bVar);
                }
            }
        }
    }
}
