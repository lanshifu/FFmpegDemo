package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.b;
import kotlin.jvm.internal.f;

/* compiled from: Collections.kt */
final class c<T> implements Collection<T> {
    private final T[] a;
    private final boolean b;

    public boolean add(T t) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public <T> T[] toArray(T[] tArr) {
        return kotlin.jvm.internal.c.a(this, tArr);
    }

    public c(T[] tArr, boolean z) {
        f.b(tArr, "values");
        this.a = tArr;
        this.b = z;
    }

    public final int size() {
        return a();
    }

    public int a() {
        return this.a.length;
    }

    public boolean isEmpty() {
        return this.a.length == 0;
    }

    public boolean contains(Object obj) {
        return h.a(this.a, obj);
    }

    public boolean containsAll(Collection<? extends Object> collection) {
        f.b(collection, "elements");
        Iterable<Object> iterable = collection;
        if (((Collection) iterable).isEmpty()) {
            return true;
        }
        for (Object contains : iterable) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public Iterator<T> iterator() {
        return b.a(this.a);
    }

    public final Object[] toArray() {
        return k.a(this.a, this.b);
    }
}
