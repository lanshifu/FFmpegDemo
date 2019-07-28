package net.vidageek.mirror.list.dsl;

public interface Matcher<T> {
    boolean accepts(T t);
}
