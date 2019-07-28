package net.vidageek.mirror.set;

import java.lang.reflect.Field;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.set.dsl.FieldSetter;

public final class FieldSetterByName implements FieldSetter {
    private final Class<?> clazz;
    private final String fieldName;
    private final ReflectionProvider provider;
    private final Object target;

    public FieldSetterByName(ReflectionProvider reflectionProvider, String str, Object obj, Class<?> cls) {
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("fieldName cannot be null or blank");
        } else if (cls != null) {
            this.provider = reflectionProvider;
            this.fieldName = str;
            this.target = obj;
            this.clazz = cls;
        } else {
            throw new IllegalArgumentException("clazz cannot be null");
        }
    }

    public void withValue(Object obj) {
        Field field = new Mirror(this.provider).on(this.clazz).reflect().field(this.fieldName);
        if (field != null) {
            new FieldSetterByField(this.provider, this.target, this.clazz, field).withValue(obj);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("could not find field ");
        stringBuilder.append(this.fieldName);
        stringBuilder.append(" on class ");
        stringBuilder.append(this.clazz.getName());
        throw new MirrorException(stringBuilder.toString());
    }
}
