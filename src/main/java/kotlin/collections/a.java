package kotlin.collections;

import java.util.Collection;
import kotlin.TypeCastException;
import kotlin.jvm.internal.c;
import kotlin.jvm.internal.f;

/* compiled from: AbstractCollection.kt */
public abstract class a<E> implements Collection<E> {
    public abstract int a();

    public boolean add(E e) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(Collection<? extends E> collection) {
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

    protected a() {
    }

    public final int size() {
        return a();
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

    public boolean isEmpty() {
        return size() == 0;
    }

    public String toString() {
        return t.a(this, ", ", "[", "]", 0, null, new AbstractCollection$toString$1(this), 24, null);
    }

    public Object[] toArray() {
        return c.a(this);
    }

    public <T> T[] toArray(T[] tArr) {
        f.b(tArr, "array");
        Object[] a = c.a(this, tArr);
        if (a != null) {
            return a;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public boolean contains(Object obj) {
        if ((this instanceof Collection) && isEmpty()) {
            return false;
        }
        for (Object a : this) {
            if (f.a(a, obj)) {
                return true;
            }
        }
        return false;
    }
}
