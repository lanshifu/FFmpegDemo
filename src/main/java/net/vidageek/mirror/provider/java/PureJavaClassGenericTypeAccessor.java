package net.vidageek.mirror.provider.java;

import java.lang.reflect.Type;
import net.vidageek.mirror.provider.GenericTypeAccessor;

public class PureJavaClassGenericTypeAccessor implements GenericTypeAccessor {
    private final Class<?> clazz;

    public PureJavaClassGenericTypeAccessor(Class<?> cls) {
        this.clazz = cls;
    }

    public Type getGenericTypes() {
        return this.clazz.getGenericSuperclass();
    }
}
