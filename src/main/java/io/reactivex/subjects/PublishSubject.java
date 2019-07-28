package io.reactivex.subjects;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.functions.a;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class PublishSubject<T> extends c<T> {
    static final PublishDisposable[] a = new PublishDisposable[0];
    static final PublishDisposable[] b = new PublishDisposable[0];
    final AtomicReference<PublishDisposable<T>[]> c = new AtomicReference(b);
    Throwable d;

    static final class PublishDisposable<T> extends AtomicBoolean implements b {
        private static final long serialVersionUID = 3562861878281475070L;
        final r<? super T> downstream;
        final PublishSubject<T> parent;

        PublishDisposable(r<? super T> rVar, PublishSubject<T> publishSubject) {
            this.downstream = rVar;
            this.parent = publishSubject;
        }

        public void onNext(T t) {
            if (!get()) {
                this.downstream.onNext(t);
            }
        }

        public void onError(Throwable th) {
            if (get()) {
                xk.a(th);
            } else {
                this.downstream.onError(th);
            }
        }

        public void onComplete() {
            if (!get()) {
                this.downstream.onComplete();
            }
        }

        public void dispose() {
            if (compareAndSet(false, true)) {
                this.parent.b(this);
            }
        }

        public boolean isDisposed() {
            return get();
        }
    }

    public static <T> PublishSubject<T> a() {
        return new PublishSubject();
    }

    PublishSubject() {
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        PublishDisposable publishDisposable = new PublishDisposable(rVar, this);
        rVar.onSubscribe(publishDisposable);
        if (!a(publishDisposable)) {
            Throwable th = this.d;
            if (th != null) {
                rVar.onError(th);
            } else {
                rVar.onComplete();
            }
        } else if (publishDisposable.isDisposed()) {
            b(publishDisposable);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a(PublishDisposable<T> publishDisposable) {
        PublishDisposable[] publishDisposableArr;
        PublishDisposable[] publishDisposableArr2;
        do {
            publishDisposableArr = (PublishDisposable[]) this.c.get();
            if (publishDisposableArr == a) {
                return false;
            }
            int length = publishDisposableArr.length;
            publishDisposableArr2 = new PublishDisposable[(length + 1)];
            System.arraycopy(publishDisposableArr, 0, publishDisposableArr2, 0, length);
            publishDisposableArr2[length] = publishDisposable;
        } while (!this.c.compareAndSet(publishDisposableArr, publishDisposableArr2));
        return true;
    }

    /* Access modifiers changed, original: 0000 */
    public void b(PublishDisposable<T> publishDisposable) {
        PublishDisposable[] publishDisposableArr;
        Object obj;
        do {
            publishDisposableArr = (PublishDisposable[]) this.c.get();
            if (publishDisposableArr != a && publishDisposableArr != b) {
                int length = publishDisposableArr.length;
                int i = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    if (publishDisposableArr[i2] == publishDisposable) {
                        i = i2;
                        break;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        obj = b;
                    } else {
                        Object obj2 = new PublishDisposable[(length - 1)];
                        System.arraycopy(publishDisposableArr, 0, obj2, 0, i);
                        System.arraycopy(publishDisposableArr, i + 1, obj2, i, (length - i) - 1);
                        obj = obj2;
                    }
                } else {
                    return;
                }
            }
            return;
        } while (!this.c.compareAndSet(publishDisposableArr, obj));
    }

    public void onSubscribe(b bVar) {
        if (this.c.get() == a) {
            bVar.dispose();
        }
    }

    public void onNext(T t) {
        a.a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        for (PublishDisposable onNext : (PublishDisposable[]) this.c.get()) {
            onNext.onNext(t);
        }
    }

    public void onError(Throwable th) {
        a.a((Object) th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.c.get() == a) {
            xk.a(th);
            return;
        }
        this.d = th;
        for (PublishDisposable onError : (PublishDisposable[]) this.c.getAndSet(a)) {
            onError.onError(th);
        }
    }

    public void onComplete() {
        if (this.c.get() != a) {
            for (PublishDisposable onComplete : (PublishDisposable[]) this.c.getAndSet(a)) {
                onComplete.onComplete();
            }
        }
    }
}
