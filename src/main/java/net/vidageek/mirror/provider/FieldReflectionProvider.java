package net.vidageek.mirror.provider;

public interface FieldReflectionProvider extends ReflectionElementReflectionProvider {
    Object getValue();

    void setValue(Object obj);
}
