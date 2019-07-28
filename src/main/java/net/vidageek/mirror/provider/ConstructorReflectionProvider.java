package net.vidageek.mirror.provider;

public interface ConstructorReflectionProvider<T> extends ReflectionElementReflectionProvider {
    Class<?>[] getParameters();

    T instantiate(Object... objArr);
}
