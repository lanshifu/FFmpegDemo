package net.vidageek.mirror.provider.java;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import net.vidageek.mirror.provider.GenericTypeAccessor;

public class PureJavaFieldGenericTypeAccessor implements GenericTypeAccessor {
    private final Field field;

    public PureJavaFieldGenericTypeAccessor(Field field) {
        if (field != null) {
            this.field = field;
            return;
        }
        throw new IllegalArgumentException("Argument field cannot be null.");
    }

    public Type getGenericTypes() {
        return this.field.getGenericType();
    }
}
