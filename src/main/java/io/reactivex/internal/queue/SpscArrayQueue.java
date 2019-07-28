package io.reactivex.internal.queue;

import defpackage.xc;
import io.reactivex.internal.util.j;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

public final class SpscArrayQueue<E> extends AtomicReferenceArray<E> implements xc<E> {
    private static final Integer a = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    private static final long serialVersionUID = -1296597691183856449L;
    final AtomicLong consumerIndex = new AtomicLong();
    final int lookAheadStep;
    final int mask = (length() - 1);
    final AtomicLong producerIndex = new AtomicLong();
    long producerLookAhead;

    /* Access modifiers changed, original: 0000 */
    public int calcElementOffset(long j, int i) {
        return ((int) j) & i;
    }

    public SpscArrayQueue(int i) {
        super(j.a(i));
        this.lookAheadStep = Math.min(i / 4, a.intValue());
    }

    public boolean offer(E e) {
        if (e != null) {
            int i = this.mask;
            long j = this.producerIndex.get();
            int calcElementOffset = calcElementOffset(j, i);
            if (j >= this.producerLookAhead) {
                long j2 = ((long) this.lookAheadStep) + j;
                if (lvElement(calcElementOffset(j2, i)) == null) {
                    this.producerLookAhead = j2;
                } else if (lvElement(calcElementOffset) != null) {
                    return false;
                }
            }
            soElement(calcElementOffset, e);
            soProducerIndex(j + 1);
            return true;
        }
        throw new NullPointerException("Null is not a valid element");
    }

    public boolean offer(E e, E e2) {
        return offer(e) && offer(e2);
    }

    public E poll() {
        long j = this.consumerIndex.get();
        int calcElementOffset = calcElementOffset(j);
        Object lvElement = lvElement(calcElementOffset);
        if (lvElement == null) {
            return null;
        }
        soConsumerIndex(j + 1);
        soElement(calcElementOffset, null);
        return lvElement;
    }

    public boolean isEmpty() {
        return this.producerIndex.get() == this.consumerIndex.get();
    }

    /* Access modifiers changed, original: 0000 */
    public void soProducerIndex(long j) {
        this.producerIndex.lazySet(j);
    }

    /* Access modifiers changed, original: 0000 */
    public void soConsumerIndex(long j) {
        this.consumerIndex.lazySet(j);
    }

    public void clear() {
        while (true) {
            if (poll() == null) {
                if (isEmpty()) {
                    return;
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int calcElementOffset(long j) {
        return ((int) j) & this.mask;
    }

    /* Access modifiers changed, original: 0000 */
    public void soElement(int i, E e) {
        lazySet(i, e);
    }

    /* Access modifiers changed, original: 0000 */
    public E lvElement(int i) {
        return get(i);
    }
}
