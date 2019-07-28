package io.reactivex.internal.queue;

import defpackage.xc;
import java.util.concurrent.atomic.AtomicReference;

public final class MpscLinkedQueue<T> implements xc<T> {
    private final AtomicReference<LinkedQueueNode<T>> a = new AtomicReference();
    private final AtomicReference<LinkedQueueNode<T>> b = new AtomicReference();

    static final class LinkedQueueNode<E> extends AtomicReference<LinkedQueueNode<E>> {
        private static final long serialVersionUID = 2404266111789071508L;
        private E value;

        LinkedQueueNode() {
        }

        LinkedQueueNode(E e) {
            spValue(e);
        }

        public E getAndNullValue() {
            Object lpValue = lpValue();
            spValue(null);
            return lpValue;
        }

        public E lpValue() {
            return this.value;
        }

        public void spValue(E e) {
            this.value = e;
        }

        public void soNext(LinkedQueueNode<E> linkedQueueNode) {
            lazySet(linkedQueueNode);
        }

        public LinkedQueueNode<E> lvNext() {
            return (LinkedQueueNode) get();
        }
    }

    public MpscLinkedQueue() {
        LinkedQueueNode linkedQueueNode = new LinkedQueueNode();
        b(linkedQueueNode);
        a(linkedQueueNode);
    }

    public boolean offer(T t) {
        if (t != null) {
            LinkedQueueNode linkedQueueNode = new LinkedQueueNode(t);
            a(linkedQueueNode).soNext(linkedQueueNode);
            return true;
        }
        throw new NullPointerException("Null is not a valid element");
    }

    public T poll() {
        LinkedQueueNode c = c();
        LinkedQueueNode lvNext = c.lvNext();
        Object andNullValue;
        if (lvNext != null) {
            andNullValue = lvNext.getAndNullValue();
            b(lvNext);
            return andNullValue;
        } else if (c == a()) {
            return null;
        } else {
            while (true) {
                lvNext = c.lvNext();
                if (lvNext != null) {
                    andNullValue = lvNext.getAndNullValue();
                    b(lvNext);
                    return andNullValue;
                }
            }
        }
    }

    public void clear() {
        while (poll() != null && !isEmpty()) {
        }
    }

    /* Access modifiers changed, original: 0000 */
    public LinkedQueueNode<T> a() {
        return (LinkedQueueNode) this.a.get();
    }

    /* Access modifiers changed, original: 0000 */
    public LinkedQueueNode<T> a(LinkedQueueNode<T> linkedQueueNode) {
        return (LinkedQueueNode) this.a.getAndSet(linkedQueueNode);
    }

    /* Access modifiers changed, original: 0000 */
    public LinkedQueueNode<T> b() {
        return (LinkedQueueNode) this.b.get();
    }

    /* Access modifiers changed, original: 0000 */
    public LinkedQueueNode<T> c() {
        return (LinkedQueueNode) this.b.get();
    }

    /* Access modifiers changed, original: 0000 */
    public void b(LinkedQueueNode<T> linkedQueueNode) {
        this.b.lazySet(linkedQueueNode);
    }

    public boolean isEmpty() {
        return b() == a();
    }
}
