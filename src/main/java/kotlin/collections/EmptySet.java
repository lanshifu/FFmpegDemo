package kotlin.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.jvm.internal.c;
import kotlin.jvm.internal.f;

/* compiled from: Sets.kt */
public final class EmptySet implements Serializable, Set {
    public static final EmptySet INSTANCE = new EmptySet();
    private static final long serialVersionUID = 3406603774387020532L;

    public boolean add(Void voidR) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean contains(Void voidR) {
        f.b(voidR, "element");
        return false;
    }

    public int getSize() {
        return 0;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Object[] toArray() {
        return c.a(this);
    }

    public <T> T[] toArray(T[] tArr) {
        return c.a(this, tArr);
    }

    public String toString() {
        return "[]";
    }

    private EmptySet() {
    }

    public final /* bridge */ boolean contains(Object obj) {
        return obj instanceof Void ? contains((Void) obj) : false;
    }

    public final int size() {
        return getSize();
    }

    public boolean equals(Object obj) {
        return (obj instanceof Set) && ((Set) obj).isEmpty();
    }

    public boolean containsAll(Collection collection) {
        f.b(collection, "elements");
        return collection.isEmpty();
    }

    public Iterator iterator() {
        return u.a;
    }

    private final Object readResolve() {
        return INSTANCE;
    }
}
