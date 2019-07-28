package net.vidageek.mirror.set;

import java.lang.reflect.Field;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.set.dsl.FieldSetter;
import net.vidageek.mirror.set.dsl.SetterHandler;

public final class DefaultSetterHandler implements SetterHandler {
    private final Class<?> clazz;
    private final ReflectionProvider provider;
    private final Object target;

    public DefaultSetterHandler(ReflectionProvider reflectionProvider, Object obj) {
        if (obj != null) {
            this.provider = reflectionProvider;
            this.target = obj;
            this.clazz = obj.getClass();
            return;
        }
        throw new IllegalArgumentException("target cannot be null");
    }

    public DefaultSetterHandler(ReflectionProvider reflectionProvider, Class<?> cls) {
        if (cls != null) {
            this.provider = reflectionProvider;
            this.clazz = cls;
            this.target = null;
            return;
        }
        throw new IllegalArgumentException("clazz cannot be null");
    }

    public FieldSetter field(String str) {
        if (str != null && str.trim().length() != 0) {
            return new FieldSetterByName(this.provider, str, this.target, this.clazz);
        }
        throw new IllegalArgumentException("fieldName cannot be null or empty.");
    }

    public FieldSetter field(Field field) {
        if (field != null) {
            return new FieldSetterByField(this.provider, this.target, this.clazz, field);
        }
        throw new IllegalArgumentException("parameter field cannot be null.");
    }
}
