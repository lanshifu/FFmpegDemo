package io.reactivex.subjects;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: BehaviorSubject */
public final class a<T> extends c<T> {
    static final a[] c = new a[0];
    static final a[] d = new a[0];
    private static final Object[] j = new Object[0];
    final AtomicReference<Object> a = new AtomicReference();
    final AtomicReference<a<T>[]> b = new AtomicReference(c);
    final ReadWriteLock e = new ReentrantReadWriteLock();
    final Lock f = this.e.readLock();
    final Lock g = this.e.writeLock();
    final AtomicReference<Throwable> h = new AtomicReference();
    long i;

    /* compiled from: BehaviorSubject */
    static final class a<T> implements b, io.reactivex.internal.util.a.a<Object> {
        final r<? super T> a;
        final a<T> b;
        boolean c;
        boolean d;
        io.reactivex.internal.util.a<Object> e;
        boolean f;
        volatile boolean g;
        long h;

        a(r<? super T> rVar, a<T> aVar) {
            this.a = rVar;
            this.b = aVar;
        }

        public void dispose() {
            if (!this.g) {
                this.g = true;
                this.b.b(this);
            }
        }

        public boolean isDisposed() {
            return this.g;
        }

        /* Access modifiers changed, original: 0000 */
        /* JADX WARNING: Missing block: B:19:0x0031, code skipped:
            if (r0 == null) goto L_0x003d;
     */
        /* JADX WARNING: Missing block: B:21:0x0037, code skipped:
            if (test(r0) == false) goto L_0x003a;
     */
        /* JADX WARNING: Missing block: B:22:0x0039, code skipped:
            return;
     */
        /* JADX WARNING: Missing block: B:23:0x003a, code skipped:
            b();
     */
        /* JADX WARNING: Missing block: B:24:0x003d, code skipped:
            return;
     */
        public void a() {
            /*
            r4 = this;
            r0 = r4.g;
            if (r0 == 0) goto L_0x0005;
        L_0x0004:
            return;
        L_0x0005:
            monitor-enter(r4);
            r0 = r4.g;	 Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x000c;
        L_0x000a:
            monitor-exit(r4);	 Catch:{ all -> 0x003e }
            return;
        L_0x000c:
            r0 = r4.c;	 Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x0012;
        L_0x0010:
            monitor-exit(r4);	 Catch:{ all -> 0x003e }
            return;
        L_0x0012:
            r0 = r4.b;	 Catch:{ all -> 0x003e }
            r1 = r0.f;	 Catch:{ all -> 0x003e }
            r1.lock();	 Catch:{ all -> 0x003e }
            r2 = r0.i;	 Catch:{ all -> 0x003e }
            r4.h = r2;	 Catch:{ all -> 0x003e }
            r0 = r0.a;	 Catch:{ all -> 0x003e }
            r0 = r0.get();	 Catch:{ all -> 0x003e }
            r1.unlock();	 Catch:{ all -> 0x003e }
            r1 = 1;
            if (r0 == 0) goto L_0x002b;
        L_0x0029:
            r2 = 1;
            goto L_0x002c;
        L_0x002b:
            r2 = 0;
        L_0x002c:
            r4.d = r2;	 Catch:{ all -> 0x003e }
            r4.c = r1;	 Catch:{ all -> 0x003e }
            monitor-exit(r4);	 Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x003d;
        L_0x0033:
            r0 = r4.test(r0);
            if (r0 == 0) goto L_0x003a;
        L_0x0039:
            return;
        L_0x003a:
            r4.b();
        L_0x003d:
            return;
        L_0x003e:
            r0 = move-exception;
            monitor-exit(r4);	 Catch:{ all -> 0x003e }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.subjects.a$a.a():void");
        }

        /* Access modifiers changed, original: 0000 */
        /* JADX WARNING: Missing block: B:25:0x0031, code skipped:
            r3.f = true;
     */
        public void a(java.lang.Object r4, long r5) {
            /*
            r3 = this;
            r0 = r3.g;
            if (r0 == 0) goto L_0x0005;
        L_0x0004:
            return;
        L_0x0005:
            r0 = r3.f;
            if (r0 != 0) goto L_0x0037;
        L_0x0009:
            monitor-enter(r3);
            r0 = r3.g;	 Catch:{ all -> 0x0034 }
            if (r0 == 0) goto L_0x0010;
        L_0x000e:
            monitor-exit(r3);	 Catch:{ all -> 0x0034 }
            return;
        L_0x0010:
            r0 = r3.h;	 Catch:{ all -> 0x0034 }
            r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1));
            if (r2 != 0) goto L_0x0018;
        L_0x0016:
            monitor-exit(r3);	 Catch:{ all -> 0x0034 }
            return;
        L_0x0018:
            r5 = r3.d;	 Catch:{ all -> 0x0034 }
            if (r5 == 0) goto L_0x002d;
        L_0x001c:
            r5 = r3.e;	 Catch:{ all -> 0x0034 }
            if (r5 != 0) goto L_0x0028;
        L_0x0020:
            r5 = new io.reactivex.internal.util.a;	 Catch:{ all -> 0x0034 }
            r6 = 4;
            r5.<init>(r6);	 Catch:{ all -> 0x0034 }
            r3.e = r5;	 Catch:{ all -> 0x0034 }
        L_0x0028:
            r5.a(r4);	 Catch:{ all -> 0x0034 }
            monitor-exit(r3);	 Catch:{ all -> 0x0034 }
            return;
        L_0x002d:
            r5 = 1;
            r3.c = r5;	 Catch:{ all -> 0x0034 }
            monitor-exit(r3);	 Catch:{ all -> 0x0034 }
            r3.f = r5;
            goto L_0x0037;
        L_0x0034:
            r4 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0034 }
            throw r4;
        L_0x0037:
            r3.test(r4);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.subjects.a$a.a(java.lang.Object, long):void");
        }

        public boolean test(Object obj) {
            return this.g || NotificationLite.accept(obj, this.a);
        }

        /* Access modifiers changed, original: 0000 */
        /* JADX WARNING: Missing block: B:12:0x0013, code skipped:
            r0.a((io.reactivex.internal.util.a.a) r2);
     */
        public void b() {
            /*
            r2 = this;
        L_0x0000:
            r0 = r2.g;
            if (r0 == 0) goto L_0x0005;
        L_0x0004:
            return;
        L_0x0005:
            monitor-enter(r2);
            r0 = r2.e;	 Catch:{ all -> 0x0017 }
            if (r0 != 0) goto L_0x000f;
        L_0x000a:
            r0 = 0;
            r2.d = r0;	 Catch:{ all -> 0x0017 }
            monitor-exit(r2);	 Catch:{ all -> 0x0017 }
            return;
        L_0x000f:
            r1 = 0;
            r2.e = r1;	 Catch:{ all -> 0x0017 }
            monitor-exit(r2);	 Catch:{ all -> 0x0017 }
            r0.a(r2);
            goto L_0x0000;
        L_0x0017:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x0017 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.subjects.a$a.b():void");
        }
    }

    public static <T> a<T> a() {
        return new a();
    }

    a() {
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        a aVar = new a(rVar, this);
        rVar.onSubscribe(aVar);
        if (!a(aVar)) {
            Throwable th = (Throwable) this.h.get();
            if (th == ExceptionHelper.a) {
                rVar.onComplete();
            } else {
                rVar.onError(th);
            }
        } else if (aVar.g) {
            b(aVar);
        } else {
            aVar.a();
        }
    }

    public void onSubscribe(b bVar) {
        if (this.h.get() != null) {
            bVar.dispose();
        }
    }

    public void onNext(T t) {
        io.reactivex.internal.functions.a.a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.h.get() == null) {
            Object next = NotificationLite.next(t);
            b(next);
            for (a a : (a[]) this.b.get()) {
                a.a(next, this.i);
            }
        }
    }

    public void onError(Throwable th) {
        io.reactivex.internal.functions.a.a((Object) th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.h.compareAndSet(null, th)) {
            Object error = NotificationLite.error(th);
            for (a a : a(error)) {
                a.a(error, this.i);
            }
            return;
        }
        xk.a(th);
    }

    public void onComplete() {
        if (this.h.compareAndSet(null, ExceptionHelper.a)) {
            Object complete = NotificationLite.complete();
            for (a a : a(complete)) {
                a.a(complete, this.i);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a(a<T> aVar) {
        a[] aVarArr;
        a[] aVarArr2;
        do {
            aVarArr = (a[]) this.b.get();
            if (aVarArr == d) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new a[(length + 1)];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.b.compareAndSet(aVarArr, aVarArr2));
        return true;
    }

    /* Access modifiers changed, original: 0000 */
    public void b(a<T> aVar) {
        a[] aVarArr;
        Object obj;
        do {
            aVarArr = (a[]) this.b.get();
            int length = aVarArr.length;
            if (length != 0) {
                int i = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    if (aVarArr[i2] == aVar) {
                        i = i2;
                        break;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        obj = c;
                    } else {
                        Object obj2 = new a[(length - 1)];
                        System.arraycopy(aVarArr, 0, obj2, 0, i);
                        System.arraycopy(aVarArr, i + 1, obj2, i, (length - i) - 1);
                        obj = obj2;
                    }
                } else {
                    return;
                }
            }
            return;
        } while (!this.b.compareAndSet(aVarArr, obj));
    }

    /* Access modifiers changed, original: 0000 */
    public a<T>[] a(Object obj) {
        a[] aVarArr = (a[]) this.b.getAndSet(d);
        if (aVarArr != d) {
            b(obj);
        }
        return aVarArr;
    }

    /* Access modifiers changed, original: 0000 */
    public void b(Object obj) {
        this.g.lock();
        this.i++;
        this.a.lazySet(obj);
        this.g.unlock();
    }
}
