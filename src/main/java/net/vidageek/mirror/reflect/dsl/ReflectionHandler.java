package net.vidageek.mirror.reflect.dsl;

import java.lang.reflect.Field;

public interface ReflectionHandler<T> {
    <A> AnnotationHandler<? extends A> annotation(Class<A> cls);

    ConstructorReflector<T> constructor();

    Field field(String str);

    MethodReflector method(String str);

    ParameterizedElementHandler parentGenericType();
}
