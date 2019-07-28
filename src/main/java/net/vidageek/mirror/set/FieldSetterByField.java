package net.vidageek.mirror.set;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.matcher.ClassArrayMatcher;
import net.vidageek.mirror.matcher.MatchType;
import net.vidageek.mirror.provider.FieldReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.set.dsl.FieldSetter;

public final class FieldSetterByField implements FieldSetter {
    private final Class<?> clazz;
    private final Field field;
    private final ReflectionProvider provider;
    private final Object target;

    public FieldSetterByField(ReflectionProvider reflectionProvider, Object obj, Class<?> cls, Field field) {
        if (cls == null) {
            throw new IllegalArgumentException("clazz cannot be null");
        } else if (field == null) {
            throw new IllegalArgumentException("field cannot be null");
        } else if (field.getDeclaringClass().isAssignableFrom(cls)) {
            this.provider = reflectionProvider;
            this.target = obj;
            this.clazz = cls;
            this.field = field;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("field declaring class (");
            stringBuilder.append(field.getDeclaringClass().getName());
            stringBuilder.append(") doesn't match clazz ");
            stringBuilder.append(cls.getName());
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public void withValue(Object obj) {
        if (this.target == null && !Modifier.isStatic(this.field.getModifiers())) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("attempt to set instance field ");
            stringBuilder.append(this.field.getName());
            stringBuilder.append(" on class ");
            stringBuilder.append(this.clazz.getName());
            throw new MirrorException(stringBuilder.toString());
        } else if (obj == null && this.field.getType().isPrimitive()) {
            throw new IllegalArgumentException("cannot set null value on primitive field");
        } else {
            if (obj != null) {
                if (MatchType.DONT_MATCH.equals(new ClassArrayMatcher(obj.getClass()).match(this.field.getType()))) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Value of type ");
                    stringBuilder2.append(obj.getClass());
                    stringBuilder2.append(" cannot be set on field ");
                    stringBuilder2.append(this.field.getName());
                    stringBuilder2.append(" of type ");
                    stringBuilder2.append(this.field.getType());
                    stringBuilder2.append(" from class ");
                    stringBuilder2.append(this.clazz.getName());
                    stringBuilder2.append(". Incompatible types");
                    throw new IllegalArgumentException(stringBuilder2.toString());
                }
            }
            FieldReflectionProvider fieldReflectionProvider = this.provider.getFieldReflectionProvider(this.target, this.clazz, this.field);
            fieldReflectionProvider.setAccessible();
            fieldReflectionProvider.setValue(obj);
        }
    }
}
