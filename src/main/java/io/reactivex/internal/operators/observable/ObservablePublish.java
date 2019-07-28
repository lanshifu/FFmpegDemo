package io.reactivex.internal.operators.observable;

import defpackage.wl;
import defpackage.xi;
import defpackage.xk;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservablePublish<T> extends xi<T> {
    final p<T> a;
    final AtomicReference<a<T>> b;
    final p<T> c;

    static final class InnerDisposable<T> extends AtomicReference<Object> implements io.reactivex.disposables.b {
        private static final long serialVersionUID = -1100270633763673112L;
        final r<? super T> child;

        InnerDisposable(r<? super T> rVar) {
            this.child = rVar;
        }

        public boolean isDisposed() {
            return get() == this;
        }

        public void dispose() {
            InnerDisposable andSet = getAndSet(this);
            if (andSet != null && andSet != this) {
                ((a) andSet).b(this);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void setParent(a<T> aVar) {
            if (!compareAndSet(null, aVar)) {
                aVar.b(this);
            }
        }
    }

    static final class a<T> implements io.reactivex.disposables.b, r<T> {
        static final InnerDisposable[] b = new InnerDisposable[0];
        static final InnerDisposable[] c = new InnerDisposable[0];
        final AtomicReference<a<T>> a;
        final AtomicReference<InnerDisposable<T>[]> d = new AtomicReference(b);
        final AtomicBoolean e;
        final AtomicReference<io.reactivex.disposables.b> f = new AtomicReference();

        a(AtomicReference<a<T>> atomicReference) {
            this.a = atomicReference;
            this.e = new AtomicBoolean();
        }

        public void dispose() {
            if (((InnerDisposable[]) this.d.getAndSet(c)) != c) {
                this.a.compareAndSet(this, null);
                DisposableHelper.dispose(this.f);
            }
        }

        public boolean isDisposed() {
            return this.d.get() == c;
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            DisposableHelper.setOnce(this.f, bVar);
        }

        public void onNext(T t) {
            for (InnerDisposable innerDisposable : (InnerDisposable[]) this.d.get()) {
                innerDisposable.child.onNext(t);
            }
        }

        public void onError(Throwable th) {
            this.a.compareAndSet(this, null);
            InnerDisposable[] innerDisposableArr = (InnerDisposable[]) this.d.getAndSet(c);
            if (innerDisposableArr.length != 0) {
                for (InnerDisposable innerDisposable : innerDisposableArr) {
                    innerDisposable.child.onError(th);
                }
                return;
            }
            xk.a(th);
        }

        public void onComplete() {
            this.a.compareAndSet(this, null);
            for (InnerDisposable innerDisposable : (InnerDisposable[]) this.d.getAndSet(c)) {
                innerDisposable.child.onComplete();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public boolean a(InnerDisposable<T> innerDisposable) {
            InnerDisposable[] innerDisposableArr;
            InnerDisposable[] innerDisposableArr2;
            do {
                innerDisposableArr = (InnerDisposable[]) this.d.get();
                if (innerDisposableArr == c) {
                    return false;
                }
                int length = innerDisposableArr.length;
                innerDisposableArr2 = new InnerDisposable[(length + 1)];
                System.arraycopy(innerDisposableArr, 0, innerDisposableArr2, 0, length);
                innerDisposableArr2[length] = innerDisposable;
            } while (!this.d.compareAndSet(innerDisposableArr, innerDisposableArr2));
            return true;
        }

        /* Access modifiers changed, original: 0000 */
        public void b(InnerDisposable<T> innerDisposable) {
            InnerDisposable[] innerDisposableArr;
            Object obj;
            do {
                innerDisposableArr = (InnerDisposable[]) this.d.get();
                int length = innerDisposableArr.length;
                if (length != 0) {
                    int i = -1;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (innerDisposableArr[i2].equals(innerDisposable)) {
                            i = i2;
                            break;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            obj = b;
                        } else {
                            Object obj2 = new InnerDisposable[(length - 1)];
                            System.arraycopy(innerDisposableArr, 0, obj2, 0, i);
                            System.arraycopy(innerDisposableArr, i + 1, obj2, i, (length - i) - 1);
                            obj = obj2;
                        }
                    } else {
                        return;
                    }
                }
                return;
            } while (!this.d.compareAndSet(innerDisposableArr, obj));
        }
    }

    static final class b<T> implements p<T> {
        private final AtomicReference<a<T>> a;

        b(AtomicReference<a<T>> atomicReference) {
            this.a = atomicReference;
        }

        public void subscribe(r<? super T> rVar) {
            InnerDisposable innerDisposable = new InnerDisposable(rVar);
            rVar.onSubscribe(innerDisposable);
            while (true) {
                a aVar = (a) this.a.get();
                if (aVar == null || aVar.isDisposed()) {
                    a aVar2 = new a(this.a);
                    if (this.a.compareAndSet(aVar, aVar2)) {
                        aVar = aVar2;
                    }
                }
                if (aVar.a(innerDisposable)) {
                    innerDisposable.setParent(aVar);
                    return;
                }
            }
        }
    }

    public static <T> xi<T> a(p<T> pVar) {
        AtomicReference atomicReference = new AtomicReference();
        return xk.a(new ObservablePublish(new b(atomicReference), pVar, atomicReference));
    }

    private ObservablePublish(p<T> pVar, p<T> pVar2, AtomicReference<a<T>> atomicReference) {
        this.c = pVar;
        this.a = pVar2;
        this.b = atomicReference;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.c.subscribe(rVar);
    }

    public void a(wl<? super io.reactivex.disposables.b> wlVar) {
        a aVar;
        while (true) {
            aVar = (a) this.b.get();
            if (aVar != null && !aVar.isDisposed()) {
                break;
            }
            a aVar2 = new a(this.b);
            if (this.b.compareAndSet(aVar, aVar2)) {
                aVar = aVar2;
                break;
            }
        }
        boolean z = true;
        if (aVar.e.get() || !aVar.e.compareAndSet(false, true)) {
            z = false;
        }
        try {
            wlVar.accept(aVar);
            if (z) {
                this.a.subscribe(aVar);
            }
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            RuntimeException a = ExceptionHelper.a(th);
        }
    }
}
