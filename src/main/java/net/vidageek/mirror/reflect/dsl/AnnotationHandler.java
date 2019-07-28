package net.vidageek.mirror.reflect.dsl;

import java.lang.annotation.Annotation;

public interface AnnotationHandler<T extends Annotation> {
    T atClass();

    T atField(String str);

    MethodAnnotationHandler<T> atMethod(String str);
}
