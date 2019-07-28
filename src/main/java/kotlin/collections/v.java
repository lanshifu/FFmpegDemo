package kotlin.collections;

import java.util.Iterator;

/* compiled from: Iterators.kt */
public abstract class v implements Iterator<Integer> {
    public abstract int b();

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: a */
    public final Integer next() {
        return Integer.valueOf(b());
    }
}
