package net.vidageek.mirror.reflect;

import java.lang.reflect.Field;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.FieldReflector;

public final class DefaultFieldReflector implements FieldReflector {
    private final String fieldName;
    private final ReflectionProvider provider;

    public DefaultFieldReflector(ReflectionProvider reflectionProvider, String str) {
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("fieldName cannot be null or blank");
        }
        this.provider = reflectionProvider;
        this.fieldName = str;
    }

    public Field onClass(Class cls) {
        if (cls != null) {
            return this.provider.getClassReflectionProvider(cls).reflectField(this.fieldName);
        }
        throw new IllegalArgumentException("argument clazz cannot be null.");
    }
}
