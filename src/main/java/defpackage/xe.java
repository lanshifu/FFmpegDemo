package defpackage;

import io.reactivex.a;
import io.reactivex.b;
import io.reactivex.c;
import io.reactivex.internal.disposables.EmptyDisposable;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: CompletableAmb */
/* renamed from: xe */
public final class xe extends a {
    private final c[] a;
    private final Iterable<? extends c> b;

    /* compiled from: CompletableAmb */
    /* renamed from: xe$a */
    static final class a implements b {
        private final AtomicBoolean a;
        private final io.reactivex.disposables.a b;
        private final b c;

        a(AtomicBoolean atomicBoolean, io.reactivex.disposables.a aVar, b bVar) {
            this.a = atomicBoolean;
            this.b = aVar;
            this.c = bVar;
        }

        public void onComplete() {
            if (this.a.compareAndSet(false, true)) {
                this.b.dispose();
                this.c.onComplete();
            }
        }

        public void onError(Throwable th) {
            if (this.a.compareAndSet(false, true)) {
                this.b.dispose();
                this.c.onError(th);
                return;
            }
            xk.a(th);
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            this.b.a(bVar);
        }
    }

    public xe(c[] cVarArr, Iterable<? extends c> iterable) {
        this.a = cVarArr;
        this.b = iterable;
    }

    public void b(b bVar) {
        int i;
        Throwable th;
        Object obj = this.a;
        if (obj == null) {
            obj = new c[8];
            try {
                i = 0;
                for (c cVar : this.b) {
                    if (cVar == null) {
                        EmptyDisposable.error(new NullPointerException("One of the sources is null"), bVar);
                        return;
                    }
                    if (i == obj.length) {
                        Object obj2 = new c[((i >> 2) + i)];
                        System.arraycopy(obj, 0, obj2, 0, i);
                        obj = obj2;
                    }
                    int i2 = i + 1;
                    obj[i] = cVar;
                    i = i2;
                }
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                EmptyDisposable.error(th2, bVar);
                return;
            }
        }
        i = obj.length;
        io.reactivex.disposables.a aVar = new io.reactivex.disposables.a();
        bVar.onSubscribe(aVar);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        a aVar2 = new a(atomicBoolean, aVar, bVar);
        int i3 = 0;
        while (i3 < i) {
            c cVar2 = obj[i3];
            if (!aVar.isDisposed()) {
                if (cVar2 == null) {
                    th2 = new NullPointerException("One of the sources is null");
                    if (atomicBoolean.compareAndSet(false, true)) {
                        aVar.dispose();
                        bVar.onError(th2);
                    } else {
                        xk.a(th2);
                    }
                    return;
                }
                cVar2.a(aVar2);
                i3++;
            } else {
                return;
            }
        }
        if (i == 0) {
            bVar.onComplete();
        }
    }
}
