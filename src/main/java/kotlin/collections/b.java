package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.jvm.internal.f;

/* compiled from: AbstractList.kt */
public abstract class b<E> extends a<E> implements List<E> {
    public static final a a = new a();

    /* compiled from: AbstractList.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(d dVar) {
            this();
        }

        public final void a(int i, int i2) {
            if (i < 0 || i >= i2) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("index: ");
                stringBuilder.append(i);
                stringBuilder.append(", size: ");
                stringBuilder.append(i2);
                throw new IndexOutOfBoundsException(stringBuilder.toString());
            }
        }

        public final void b(int i, int i2) {
            if (i < 0 || i > i2) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("index: ");
                stringBuilder.append(i);
                stringBuilder.append(", size: ");
                stringBuilder.append(i2);
                throw new IndexOutOfBoundsException(stringBuilder.toString());
            }
        }

        public final void a(int i, int i2, int i3) {
            if (i < 0 || i2 > i3) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("fromIndex: ");
                stringBuilder.append(i);
                stringBuilder.append(", toIndex: ");
                stringBuilder.append(i2);
                stringBuilder.append(", size: ");
                stringBuilder.append(i3);
                throw new IndexOutOfBoundsException(stringBuilder.toString());
            } else if (i > i2) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("fromIndex: ");
                stringBuilder2.append(i);
                stringBuilder2.append(" > toIndex: ");
                stringBuilder2.append(i2);
                throw new IllegalArgumentException(stringBuilder2.toString());
            }
        }

        public final int a(Collection<?> collection) {
            f.b(collection, "c");
            int i = 1;
            for (Object next : collection) {
                i = (i * 31) + (next != null ? next.hashCode() : 0);
            }
            return i;
        }

        public final boolean a(Collection<?> collection, Collection<?> collection2) {
            f.b(collection, "c");
            f.b(collection2, "other");
            if (collection.size() != collection2.size()) {
                return false;
            }
            Iterator it = collection2.iterator();
            for (Object a : collection) {
                if ((f.a(a, it.next()) ^ 1) != 0) {
                    return false;
                }
            }
            return true;
        }
    }

    /* compiled from: AbstractList.kt */
    private class b implements Iterator<E> {
        private int b;

        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        /* Access modifiers changed, original: protected|final */
        public final int a() {
            return this.b;
        }

        /* Access modifiers changed, original: protected|final */
        public final void a(int i) {
            this.b = i;
        }

        public boolean hasNext() {
            return this.b < b.this.size();
        }

        public E next() {
            if (hasNext()) {
                b bVar = b.this;
                int i = this.b;
                this.b = i + 1;
                return bVar.get(i);
            }
            throw new NoSuchElementException();
        }
    }

    /* compiled from: AbstractList.kt */
    private class c extends b implements ListIterator<E> {
        public void add(E e) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public void set(E e) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public c(int i) {
            super();
            b.a.b(i, b.this.size());
            a(i);
        }

        public boolean hasPrevious() {
            return a() > 0;
        }

        public int nextIndex() {
            return a();
        }

        public E previous() {
            if (hasPrevious()) {
                b bVar = b.this;
                a(a() - 1);
                return bVar.get(a());
            }
            throw new NoSuchElementException();
        }

        public int previousIndex() {
            return a() - 1;
        }
    }

    /* compiled from: AbstractList.kt */
    private static final class d<E> extends b<E> implements RandomAccess {
        private int b;
        private final b<E> c;
        private final int d;

        public d(b<? extends E> bVar, int i, int i2) {
            f.b(bVar, "list");
            this.c = bVar;
            this.d = i;
            b.a.a(this.d, i2, this.c.size());
            this.b = i2 - this.d;
        }

        public E get(int i) {
            b.a.a(i, this.b);
            return this.c.get(this.d + i);
        }

        public int a() {
            return this.b;
        }
    }

    public void add(int i, E e) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public abstract E get(int i);

    public E remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public E set(int i, E e) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    protected b() {
    }

    public Iterator<E> iterator() {
        return new b();
    }

    public ListIterator<E> listIterator() {
        return new c(0);
    }

    public ListIterator<E> listIterator(int i) {
        return new c(i);
    }

    public List<E> subList(int i, int i2) {
        return new d(this, i, i2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            return a.a((Collection) this, (Collection) obj);
        }
        return false;
    }

    public int hashCode() {
        return a.a(this);
    }

    public int indexOf(Object obj) {
        int i = 0;
        for (Object a : this) {
            if (f.a(a, obj)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int lastIndexOf(Object obj) {
        ListIterator listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (f.a(listIterator.previous(), obj)) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }
}
