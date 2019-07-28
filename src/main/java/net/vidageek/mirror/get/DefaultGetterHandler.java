package net.vidageek.mirror.get;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.get.dsl.GetterHandler;
import net.vidageek.mirror.provider.FieldReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProvider;

public final class DefaultGetterHandler implements GetterHandler {
    private final Class<?> clazz;
    private final ReflectionProvider provider;
    private final Object target;

    public DefaultGetterHandler(ReflectionProvider reflectionProvider, Object obj) {
        if (obj != null) {
            this.provider = reflectionProvider;
            this.clazz = obj.getClass();
            this.target = obj;
            return;
        }
        throw new IllegalArgumentException("target cannot be null");
    }

    public DefaultGetterHandler(ReflectionProvider reflectionProvider, Class<?> cls) {
        if (cls != null) {
            this.provider = reflectionProvider;
            this.clazz = cls;
            this.target = null;
            return;
        }
        throw new IllegalArgumentException("clazz cannot be null");
    }

    public Object field(String str) {
        if (str != null && str.trim().length() != 0) {
            return field(getField(str));
        }
        throw new IllegalArgumentException("fieldName cannot be null or empty.");
    }

    public Object field(Field field) {
        StringBuilder stringBuilder;
        if (field == null) {
            throw new IllegalArgumentException("field cannot be null");
        } else if (!field.getDeclaringClass().isAssignableFrom(this.clazz)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("field declaring class (");
            stringBuilder.append(field.getDeclaringClass().getName());
            stringBuilder.append(") doesn't match clazz ");
            stringBuilder.append(this.clazz.getName());
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (this.target != null || Modifier.isStatic(field.getModifiers())) {
            FieldReflectionProvider fieldReflectionProvider = this.provider.getFieldReflectionProvider(this.target, this.clazz, field);
            fieldReflectionProvider.setAccessible();
            return fieldReflectionProvider.getValue();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("attempt to get instance field ");
            stringBuilder.append(field.getName());
            stringBuilder.append(" on class ");
            stringBuilder.append(this.clazz.getName());
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    private Field getField(String str) {
        Field field = new Mirror(this.provider).on(this.clazz).reflect().field(str);
        if (field != null) {
            return field;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("could not find field ");
        stringBuilder.append(str);
        stringBuilder.append(" for class ");
        stringBuilder.append(this.clazz.getName());
        throw new MirrorException(stringBuilder.toString());
    }
}
