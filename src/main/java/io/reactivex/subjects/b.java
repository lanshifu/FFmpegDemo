package io.reactivex.subjects;

import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.a.a;
import io.reactivex.r;

/* compiled from: SerializedSubject */
final class b<T> extends c<T> implements a<Object> {
    final c<T> a;
    boolean b;
    io.reactivex.internal.util.a<Object> c;
    volatile boolean d;

    b(c<T> cVar) {
        this.a = cVar;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.a.subscribe((r) rVar);
    }

    public void onSubscribe(io.reactivex.disposables.b bVar) {
        boolean z = true;
        if (!this.d) {
            synchronized (this) {
                if (!this.d) {
                    if (this.b) {
                        io.reactivex.internal.util.a aVar = this.c;
                        if (aVar == null) {
                            aVar = new io.reactivex.internal.util.a(4);
                            this.c = aVar;
                        }
                        aVar.a(NotificationLite.disposable(bVar));
                        return;
                    }
                    this.b = true;
                    z = false;
                }
            }
        }
        if (z) {
            bVar.dispose();
        } else {
            this.a.onSubscribe(bVar);
            a();
        }
    }

    public void onNext(T t) {
        if (!this.d) {
            synchronized (this) {
                if (this.d) {
                } else if (this.b) {
                    io.reactivex.internal.util.a aVar = this.c;
                    if (aVar == null) {
                        aVar = new io.reactivex.internal.util.a(4);
                        this.c = aVar;
                    }
                    aVar.a(NotificationLite.next(t));
                } else {
                    this.b = true;
                    this.a.onNext(t);
                    a();
                }
            }
        }
    }

    /* JADX WARNING: Missing block: B:19:0x002f, code skipped:
            if (r0 == null) goto L_0x0035;
     */
    /* JADX WARNING: Missing block: B:20:0x0031, code skipped:
            defpackage.xk.a(r3);
     */
    /* JADX WARNING: Missing block: B:21:0x0034, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:22:0x0035, code skipped:
            r2.a.onError(r3);
     */
    /* JADX WARNING: Missing block: B:23:0x003a, code skipped:
            return;
     */
    public void onError(java.lang.Throwable r3) {
        /*
        r2 = this;
        r0 = r2.d;
        if (r0 == 0) goto L_0x0008;
    L_0x0004:
        defpackage.xk.a(r3);
        return;
    L_0x0008:
        monitor-enter(r2);
        r0 = r2.d;	 Catch:{ all -> 0x003b }
        r1 = 1;
        if (r0 == 0) goto L_0x0010;
    L_0x000e:
        r0 = 1;
        goto L_0x002e;
    L_0x0010:
        r2.d = r1;	 Catch:{ all -> 0x003b }
        r0 = r2.b;	 Catch:{ all -> 0x003b }
        if (r0 == 0) goto L_0x002b;
    L_0x0016:
        r0 = r2.c;	 Catch:{ all -> 0x003b }
        if (r0 != 0) goto L_0x0022;
    L_0x001a:
        r0 = new io.reactivex.internal.util.a;	 Catch:{ all -> 0x003b }
        r1 = 4;
        r0.<init>(r1);	 Catch:{ all -> 0x003b }
        r2.c = r0;	 Catch:{ all -> 0x003b }
    L_0x0022:
        r3 = io.reactivex.internal.util.NotificationLite.error(r3);	 Catch:{ all -> 0x003b }
        r0.b(r3);	 Catch:{ all -> 0x003b }
        monitor-exit(r2);	 Catch:{ all -> 0x003b }
        return;
    L_0x002b:
        r0 = 0;
        r2.b = r1;	 Catch:{ all -> 0x003b }
    L_0x002e:
        monitor-exit(r2);	 Catch:{ all -> 0x003b }
        if (r0 == 0) goto L_0x0035;
    L_0x0031:
        defpackage.xk.a(r3);
        return;
    L_0x0035:
        r0 = r2.a;
        r0.onError(r3);
        return;
    L_0x003b:
        r3 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x003b }
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.subjects.b.onError(java.lang.Throwable):void");
    }

    public void onComplete() {
        if (!this.d) {
            synchronized (this) {
                if (this.d) {
                    return;
                }
                this.d = true;
                if (this.b) {
                    io.reactivex.internal.util.a aVar = this.c;
                    if (aVar == null) {
                        aVar = new io.reactivex.internal.util.a(4);
                        this.c = aVar;
                    }
                    aVar.a(NotificationLite.complete());
                    return;
                }
                this.b = true;
                this.a.onComplete();
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a() {
        while (true) {
            io.reactivex.internal.util.a aVar;
            synchronized (this) {
                aVar = this.c;
                if (aVar == null) {
                    this.b = false;
                    return;
                }
                this.c = null;
            }
            aVar.a((a) this);
        }
        while (true) {
        }
    }

    public boolean test(Object obj) {
        return NotificationLite.acceptFull(obj, this.a);
    }
}
