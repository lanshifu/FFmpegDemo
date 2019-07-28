package net.vidageek.mirror.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaFieldGenericTypeAccessor;
import net.vidageek.mirror.reflect.dsl.FieldHandler;
import net.vidageek.mirror.reflect.dsl.MemberHandler;
import net.vidageek.mirror.reflect.dsl.ParameterizedElementHandler;

public class DefaultFieldHandler implements FieldHandler {
    private final Field field;
    private MemberHandler memberHandler;
    private final ReflectionProvider provider;

    public DefaultFieldHandler(ReflectionProvider reflectionProvider, Field field) {
        if (field != null) {
            this.provider = reflectionProvider;
            this.field = field;
            this.memberHandler = new DefaultMemberHandler(reflectionProvider, field);
            return;
        }
        throw new IllegalArgumentException("Argument field cannot be null.");
    }

    public <T extends Annotation> T annotation(Class<T> cls) {
        return this.memberHandler.annotation(cls);
    }

    public ParameterizedElementHandler genericType() {
        return new DefaultParameterizedElementHandler(this.provider, new PureJavaFieldGenericTypeAccessor(this.field));
    }
}
