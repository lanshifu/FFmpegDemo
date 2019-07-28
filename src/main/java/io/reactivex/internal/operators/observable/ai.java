package io.reactivex.internal.operators.observable;

import io.reactivex.internal.observers.b;
import io.reactivex.k;
import io.reactivex.r;

/* compiled from: ObservableFromArray */
public final class ai<T> extends k<T> {
    final T[] a;

    /* compiled from: ObservableFromArray */
    static final class a<T> extends b<T> {
        final r<? super T> a;
        final T[] b;
        int c;
        boolean d;
        volatile boolean e;

        a(r<? super T> rVar, T[] tArr) {
            this.a = rVar;
            this.b = tArr;
        }

        public int requestFusion(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            this.d = true;
            return 1;
        }

        public T poll() {
            int i = this.c;
            Object[] objArr = this.b;
            if (i == objArr.length) {
                return null;
            }
            this.c = i + 1;
            return io.reactivex.internal.functions.a.a(objArr[i], "The array element is null");
        }

        public boolean isEmpty() {
            return this.c == this.b.length;
        }

        public void clear() {
            this.c = this.b.length;
        }

        public void dispose() {
            this.e = true;
        }

        public boolean isDisposed() {
            return this.e;
        }

        /* Access modifiers changed, original: 0000 */
        public void a() {
            Object[] objArr = this.b;
            int length = objArr.length;
            for (int i = 0; i < length && !isDisposed(); i++) {
                Object obj = objArr[i];
                if (obj == null) {
                    r rVar = this.a;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("The ");
                    stringBuilder.append(i);
                    stringBuilder.append("th element is null");
                    rVar.onError(new NullPointerException(stringBuilder.toString()));
                    return;
                }
                this.a.onNext(obj);
            }
            if (!isDisposed()) {
                this.a.onComplete();
            }
        }
    }

    public ai(T[] tArr) {
        this.a = tArr;
    }

    public void subscribeActual(r<? super T> rVar) {
        a aVar = new a(rVar, this.a);
        rVar.onSubscribe(aVar);
        if (!aVar.d) {
            aVar.a();
        }
    }
}
