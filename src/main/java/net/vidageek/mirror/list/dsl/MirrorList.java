package net.vidageek.mirror.list.dsl;

import java.util.List;

public interface MirrorList<T> extends List<T> {
    <E> MirrorList<E> mappingTo(Mapper<T, E> mapper);

    MirrorList<T> matching(Matcher<T> matcher);
}
