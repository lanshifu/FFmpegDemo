package net.vidageek.mirror.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import net.vidageek.mirror.list.dsl.Mapper;
import net.vidageek.mirror.list.dsl.Matcher;
import net.vidageek.mirror.list.dsl.MirrorList;

public final class BackedMirrorList<T> implements MirrorList<T> {
    private final List<T> list;

    public BackedMirrorList(List<T> list) {
        this.list = Collections.unmodifiableList(list);
    }

    public MirrorList<T> matching(Matcher<T> matcher) {
        ArrayList arrayList = new ArrayList();
        for (Object next : this.list) {
            if (matcher.accepts(next)) {
                arrayList.add(next);
            }
        }
        return new BackedMirrorList(arrayList);
    }

    public <E> MirrorList<E> mappingTo(Mapper<T, E> mapper) {
        ArrayList arrayList = new ArrayList();
        for (Object map : this.list) {
            arrayList.add(mapper.map(map));
        }
        return new BackedMirrorList(arrayList);
    }

    public void add(int i, T t) {
        this.list.add(i, t);
    }

    public boolean add(T t) {
        return this.list.add(t);
    }

    public boolean addAll(Collection<? extends T> collection) {
        return this.list.addAll(collection);
    }

    public boolean addAll(int i, Collection<? extends T> collection) {
        return this.list.addAll(i, collection);
    }

    public void clear() {
        this.list.clear();
    }

    public boolean contains(Object obj) {
        return this.list.contains(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        return this.list.containsAll(collection);
    }

    public boolean equals(Object obj) {
        return this.list.equals(obj);
    }

    public T get(int i) {
        return this.list.get(i);
    }

    public int hashCode() {
        return this.list.hashCode();
    }

    public int indexOf(Object obj) {
        return this.list.indexOf(obj);
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public Iterator<T> iterator() {
        return this.list.iterator();
    }

    public int lastIndexOf(Object obj) {
        return this.list.lastIndexOf(obj);
    }

    public ListIterator<T> listIterator() {
        return this.list.listIterator();
    }

    public ListIterator<T> listIterator(int i) {
        return this.list.listIterator(i);
    }

    public T remove(int i) {
        return this.list.remove(i);
    }

    public boolean remove(Object obj) {
        return this.list.remove(obj);
    }

    public boolean removeAll(Collection<?> collection) {
        return this.list.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        return this.list.retainAll(collection);
    }

    public T set(int i, T t) {
        return this.list.set(i, t);
    }

    public int size() {
        return this.list.size();
    }

    public List<T> subList(int i, int i2) {
        return this.list.subList(i, i2);
    }

    public Object[] toArray() {
        return this.list.toArray();
    }

    public <E> E[] toArray(E[] eArr) {
        return this.list.toArray(eArr);
    }

    public String toString() {
        return this.list.toString();
    }
}
