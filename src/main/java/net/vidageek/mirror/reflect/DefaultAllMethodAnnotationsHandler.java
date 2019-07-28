package net.vidageek.mirror.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.AllMethodAnnotationsHandler;

public final class DefaultAllMethodAnnotationsHandler implements AllMethodAnnotationsHandler {
    private final Class<?> clazz;
    private final String methodName;
    private final ReflectionProvider provider;

    public DefaultAllMethodAnnotationsHandler(ReflectionProvider reflectionProvider, Class<?> cls, String str) {
        if (cls == null) {
            throw new IllegalArgumentException("Argument clazz cannot be null.");
        } else if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("Argument methodName cannot be null or blank.");
        } else {
            this.provider = reflectionProvider;
            this.clazz = cls;
            this.methodName = str.trim();
        }
    }

    public List<Annotation> withoutArgs() {
        return withArgs(new Class[0]);
    }

    public List<Annotation> withArgs(Class<?>... clsArr) {
        Method withArgs = new Mirror(this.provider).on(this.clazz).reflect().method(this.methodName).withArgs(clsArr);
        if (withArgs != null) {
            return this.provider.getAnnotatedElementReflectionProvider(withArgs).getAnnotations();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("could not find method that matched ");
        stringBuilder.append(Arrays.asList(clsArr));
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
