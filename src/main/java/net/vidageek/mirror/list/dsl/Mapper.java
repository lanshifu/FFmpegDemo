package net.vidageek.mirror.list.dsl;

public interface Mapper<F, T> {
    T map(F f);
}
