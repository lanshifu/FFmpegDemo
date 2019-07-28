package net.vidageek.mirror.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.AllAnnotationsHandler;
import net.vidageek.mirror.reflect.dsl.AllMethodAnnotationsHandler;

public final class DefaultAllAnnotationsHandler implements AllAnnotationsHandler {
    private final Class<?> clazz;
    private final ReflectionProvider provider;

    public DefaultAllAnnotationsHandler(ReflectionProvider reflectionProvider, Class<?> cls) {
        if (cls != null) {
            this.provider = reflectionProvider;
            this.clazz = cls;
            return;
        }
        throw new IllegalArgumentException("Argument clazz cannot be null.");
    }

    public List<Annotation> atClass() {
        return this.provider.getAnnotatedElementReflectionProvider(this.clazz).getAnnotations();
    }

    public List<Annotation> atField(String str) {
        Field field = new Mirror(this.provider).on(this.clazz).reflect().field(str);
        if (field != null) {
            return this.provider.getAnnotatedElementReflectionProvider(field).getAnnotations();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("could not find field ");
        stringBuilder.append(str);
        stringBuilder.append(" at class ");
        stringBuilder.append(this.clazz);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public AllMethodAnnotationsHandler atMethod(String str) {
        return new DefaultAllMethodAnnotationsHandler(this.provider, this.clazz, str);
    }
}
