package kotlin.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import kotlin.jvm.internal.c;
import kotlin.jvm.internal.f;

/* compiled from: Collections.kt */
public final class EmptyList implements Serializable, List, RandomAccess {
    public static final EmptyList INSTANCE = new EmptyList();
    private static final long serialVersionUID = -7390468764508069838L;

    public void add(int i, Void voidR) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean add(Void voidR) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(int i, Collection collection) {
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
        return 1;
    }

    public int indexOf(Void voidR) {
        f.b(voidR, "element");
        return -1;
    }

    public boolean isEmpty() {
        return true;
    }

    public int lastIndexOf(Void voidR) {
        f.b(voidR, "element");
        return -1;
    }

    public Void remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
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

    public Void set(int i, Void voidR) {
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

    private EmptyList() {
    }

    public final /* bridge */ boolean contains(Object obj) {
        return obj instanceof Void ? contains((Void) obj) : false;
    }

    public final /* bridge */ int indexOf(Object obj) {
        return obj instanceof Void ? indexOf((Void) obj) : -1;
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        return obj instanceof Void ? lastIndexOf((Void) obj) : -1;
    }

    public final int size() {
        return getSize();
    }

    public boolean equals(Object obj) {
        return (obj instanceof List) && ((List) obj).isEmpty();
    }

    public boolean containsAll(Collection collection) {
        f.b(collection, "elements");
        return collection.isEmpty();
    }

    public Void get(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Empty list doesn't contain element at index ");
        stringBuilder.append(i);
        stringBuilder.append('.');
        throw new IndexOutOfBoundsException(stringBuilder.toString());
    }

    public Iterator iterator() {
        return u.a;
    }

    public ListIterator listIterator() {
        return u.a;
    }

    public ListIterator listIterator(int i) {
        if (i == 0) {
            return u.a;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Index: ");
        stringBuilder.append(i);
        throw new IndexOutOfBoundsException(stringBuilder.toString());
    }

    public List subList(int i, int i2) {
        if (i == 0 && i2 == 0) {
            return this;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("fromIndex: ");
        stringBuilder.append(i);
        stringBuilder.append(", toIndex: ");
        stringBuilder.append(i2);
        throw new IndexOutOfBoundsException(stringBuilder.toString());
    }

    private final Object readResolve() {
        return INSTANCE;
    }
}
