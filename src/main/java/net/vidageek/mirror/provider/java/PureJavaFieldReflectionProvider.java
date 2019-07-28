package net.vidageek.mirror.provider.java;

import java.lang.reflect.Field;
import net.vidageek.mirror.exception.ReflectionProviderException;
import net.vidageek.mirror.provider.FieldReflectionProvider;

public final class PureJavaFieldReflectionProvider implements FieldReflectionProvider {
    private final Class<?> clazz;
    private final Field field;
    private final Object target;

    public PureJavaFieldReflectionProvider(Object obj, Class<?> cls, Field field) {
        this.target = obj;
        this.clazz = cls;
        this.field = field;
    }

    public void setValue(Object obj) {
        try {
            setAccessible();
            this.field.set(this.target, obj);
        } catch (IllegalAccessException unused) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("could not set value ");
            stringBuilder.append(obj);
            stringBuilder.append(" on field ");
            stringBuilder.append(this.field.getName());
            stringBuilder.append(" of class ");
            stringBuilder.append(this.clazz.getName());
            throw new ReflectionProviderException(stringBuilder.toString());
        }
    }

    public Object getValue() {
        try {
            setAccessible();
            return this.field.get(this.target);
        } catch (IllegalAccessException unused) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("could not get value for field ");
            stringBuilder.append(this.field.getName());
            stringBuilder.append(" of class ");
            stringBuilder.append(this.clazz.getName());
            throw new ReflectionProviderException(stringBuilder.toString());
        }
    }

    public void setAccessible() {
        this.field.setAccessible(true);
    }
}
