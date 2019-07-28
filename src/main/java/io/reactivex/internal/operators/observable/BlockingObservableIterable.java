package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.c;
import io.reactivex.p;
import io.reactivex.r;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class BlockingObservableIterable<T> implements Iterable<T> {
    final p<? extends T> a;
    final int b;

    static final class BlockingObservableIterator<T> extends AtomicReference<b> implements b, r<T>, Iterator<T> {
        private static final long serialVersionUID = 6695226475494099826L;
        final Condition condition = this.lock.newCondition();
        volatile boolean done;
        Throwable error;
        final Lock lock = new ReentrantLock();
        final a<T> queue;

        BlockingObservableIterator(int i) {
            this.queue = new a(i);
        }

        public boolean hasNext() {
            while (true) {
                Throwable th;
                boolean z = this.done;
                boolean isEmpty = this.queue.isEmpty();
                if (z) {
                    th = this.error;
                    if (th != null) {
                        throw ExceptionHelper.a(th);
                    } else if (isEmpty) {
                        return false;
                    }
                }
                if (!isEmpty) {
                    return true;
                }
                c.a();
                this.lock.lock();
                while (!this.done && this.queue.isEmpty()) {
                    try {
                        this.condition.await();
                    } catch (InterruptedException th2) {
                        DisposableHelper.dispose(this);
                        signalConsumer();
                        throw ExceptionHelper.a(th2);
                    } catch (Throwable th3) {
                        this.lock.unlock();
                    }
                }
                this.lock.unlock();
            }
        }

        public T next() {
            if (hasNext()) {
                return this.queue.poll();
            }
            throw new NoSuchElementException();
        }

        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        public void onNext(T t) {
            this.queue.offer(t);
            signalConsumer();
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            signalConsumer();
        }

        public void onComplete() {
            this.done = true;
            signalConsumer();
        }

        /* Access modifiers changed, original: 0000 */
        public void signalConsumer() {
            this.lock.lock();
            try {
                this.condition.signalAll();
            } finally {
                this.lock.unlock();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException("remove");
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((b) get());
        }
    }

    public BlockingObservableIterable(p<? extends T> pVar, int i) {
        this.a = pVar;
        this.b = i;
    }

    public Iterator<T> iterator() {
        BlockingObservableIterator blockingObservableIterator = new BlockingObservableIterator(this.b);
        this.a.subscribe(blockingObservableIterator);
        return blockingObservableIterator;
    }
}
