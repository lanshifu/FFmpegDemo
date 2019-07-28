package net.vidageek.mirror.reflect;

import java.lang.annotation.Annotation;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.AnnotationHandler;
import net.vidageek.mirror.reflect.dsl.MethodAnnotationHandler;

public final class DefaultAnnotationHandler<T extends Annotation> implements AnnotationHandler<T> {
    private final Class<T> annotation;
    private final Class<?> clazz;
    private final ReflectionProvider provider;

    public DefaultAnnotationHandler(ReflectionProvider reflectionProvider, Class<?> cls, Class<T> cls2) {
        if (cls == null) {
            throw new IllegalArgumentException("Argument clazz cannot be null.");
        } else if (cls2 != null) {
            this.provider = reflectionProvider;
            this.clazz = cls;
            this.annotation = cls2;
        } else {
            throw new IllegalArgumentException("Argument annotation cannot be null.");
        }
    }

    public T atField(String str) {
        if (str != null && str.trim().length() != 0) {
            return this.provider.getAnnotatedElementReflectionProvider(new Mirror(this.provider).on(this.clazz).reflect().field(str)).getAnnotation(this.annotation);
        }
        throw new IllegalArgumentException("fieldName cannot be null or empty.");
    }

    public MethodAnnotationHandler<T> atMethod(String str) {
        if (str != null && str.trim().length() != 0) {
            return new DefaultMethodAnnotationHandler(this.provider, this.clazz, str, this.annotation);
        }
        throw new IllegalArgumentException("methodName cannot be null or empty");
    }

    public T atClass() {
        return this.provider.getAnnotatedElementReflectionProvider(this.clazz).getAnnotation(this.annotation);
    }
}
