package net.vidageek.mirror.invoke.dsl;

public interface ConstructorHandler<T> {
    T bypasser();

    T withArgs(Object... objArr);

    T withoutArgs();
}
