package net.vidageek.mirror.provider;

public interface MethodReflectionProvider extends ReflectionElementReflectionProvider {
    Class<?>[] getParameters();

    Object invoke(Object[] objArr);
}
