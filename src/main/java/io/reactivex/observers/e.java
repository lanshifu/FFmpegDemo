package io.reactivex.observers;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.a;
import io.reactivex.r;

/* compiled from: SerializedObserver */
public final class e<T> implements b, r<T> {
    final r<? super T> a;
    final boolean b;
    b c;
    boolean d;
    a<Object> e;
    volatile boolean f;

    public e(r<? super T> rVar) {
        this(rVar, false);
    }

    public e(r<? super T> rVar, boolean z) {
        this.a = rVar;
        this.b = z;
    }

    public void onSubscribe(b bVar) {
        if (DisposableHelper.validate(this.c, bVar)) {
            this.c = bVar;
            this.a.onSubscribe(this);
        }
    }

    public void dispose() {
        this.c.dispose();
    }

    public boolean isDisposed() {
        return this.c.isDisposed();
    }

    public void onNext(T t) {
        if (!this.f) {
            if (t == null) {
                this.c.dispose();
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                return;
            }
            synchronized (this) {
                if (this.f) {
                } else if (this.d) {
                    a aVar = this.e;
                    if (aVar == null) {
                        aVar = new a(4);
                        this.e = aVar;
                    }
                    aVar.a(NotificationLite.next(t));
                } else {
                    this.d = true;
                    this.a.onNext(t);
                    a();
                }
            }
        }
    }

    /* JADX WARNING: Missing block: B:19:0x0031, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:22:0x0038, code skipped:
            if (r1 == false) goto L_0x003e;
     */
    /* JADX WARNING: Missing block: B:23:0x003a, code skipped:
            defpackage.xk.a(r3);
     */
    /* JADX WARNING: Missing block: B:24:0x003d, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:25:0x003e, code skipped:
            r2.a.onError(r3);
     */
    /* JADX WARNING: Missing block: B:26:0x0043, code skipped:
            return;
     */
    public void onError(java.lang.Throwable r3) {
        /*
        r2 = this;
        r0 = r2.f;
        if (r0 == 0) goto L_0x0008;
    L_0x0004:
        defpackage.xk.a(r3);
        return;
    L_0x0008:
        monitor-enter(r2);
        r0 = r2.f;	 Catch:{ all -> 0x0044 }
        r1 = 1;
        if (r0 == 0) goto L_0x000f;
    L_0x000e:
        goto L_0x0037;
    L_0x000f:
        r0 = r2.d;	 Catch:{ all -> 0x0044 }
        if (r0 == 0) goto L_0x0032;
    L_0x0013:
        r2.f = r1;	 Catch:{ all -> 0x0044 }
        r0 = r2.e;	 Catch:{ all -> 0x0044 }
        if (r0 != 0) goto L_0x0021;
    L_0x0019:
        r0 = new io.reactivex.internal.util.a;	 Catch:{ all -> 0x0044 }
        r1 = 4;
        r0.<init>(r1);	 Catch:{ all -> 0x0044 }
        r2.e = r0;	 Catch:{ all -> 0x0044 }
    L_0x0021:
        r3 = io.reactivex.internal.util.NotificationLite.error(r3);	 Catch:{ all -> 0x0044 }
        r1 = r2.b;	 Catch:{ all -> 0x0044 }
        if (r1 == 0) goto L_0x002d;
    L_0x0029:
        r0.a(r3);	 Catch:{ all -> 0x0044 }
        goto L_0x0030;
    L_0x002d:
        r0.b(r3);	 Catch:{ all -> 0x0044 }
    L_0x0030:
        monitor-exit(r2);	 Catch:{ all -> 0x0044 }
        return;
    L_0x0032:
        r2.f = r1;	 Catch:{ all -> 0x0044 }
        r2.d = r1;	 Catch:{ all -> 0x0044 }
        r1 = 0;
    L_0x0037:
        monitor-exit(r2);	 Catch:{ all -> 0x0044 }
        if (r1 == 0) goto L_0x003e;
    L_0x003a:
        defpackage.xk.a(r3);
        return;
    L_0x003e:
        r0 = r2.a;
        r0.onError(r3);
        return;
    L_0x0044:
        r3 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0044 }
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.observers.e.onError(java.lang.Throwable):void");
    }

    public void onComplete() {
        if (!this.f) {
            synchronized (this) {
                if (this.f) {
                } else if (this.d) {
                    a aVar = this.e;
                    if (aVar == null) {
                        aVar = new a(4);
                        this.e = aVar;
                    }
                    aVar.a(NotificationLite.complete());
                } else {
                    this.f = true;
                    this.d = true;
                    this.a.onComplete();
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a() {
        a aVar;
        do {
            synchronized (this) {
                aVar = this.e;
                if (aVar == null) {
                    this.d = false;
                    return;
                }
                this.e = null;
            }
        } while (!aVar.a(this.a));
    }
}
