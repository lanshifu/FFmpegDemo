package io.reactivex.internal.operators.observable;

import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.observers.b;
import io.reactivex.p;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: BlockingObservableMostRecent */
public final class c<T> implements Iterable<T> {
    final p<T> a;
    final T b;

    /* compiled from: BlockingObservableMostRecent */
    static final class a<T> extends b<T> {
        volatile Object a;

        /* compiled from: BlockingObservableMostRecent */
        final class a implements Iterator<T> {
            private Object b;

            a() {
            }

            public boolean hasNext() {
                this.b = a.this.a;
                return NotificationLite.isComplete(this.b) ^ 1;
            }

            public T next() {
                try {
                    Object obj;
                    if (this.b == null) {
                        obj = a.this.a;
                    }
                    if (NotificationLite.isComplete(this.b)) {
                        throw new NoSuchElementException();
                    } else if (NotificationLite.isError(this.b)) {
                        throw ExceptionHelper.a(NotificationLite.getError(this.b));
                    } else {
                        obj = NotificationLite.getValue(this.b);
                        this.b = null;
                        return obj;
                    }
                } finally {
                    this.b = null;
                }
            }

            public void remove() {
                throw new UnsupportedOperationException("Read only iterator");
            }
        }

        a(T t) {
            this.a = NotificationLite.next(t);
        }

        public void onComplete() {
            this.a = NotificationLite.complete();
        }

        public void onError(Throwable th) {
            this.a = NotificationLite.error(th);
        }

        public void onNext(T t) {
            this.a = NotificationLite.next(t);
        }

        public a a() {
            return new a();
        }
    }

    public c(p<T> pVar, T t) {
        this.a = pVar;
        this.b = t;
    }

    public Iterator<T> iterator() {
        a aVar = new a(this.b);
        this.a.subscribe(aVar);
        return aVar.a();
    }
}
