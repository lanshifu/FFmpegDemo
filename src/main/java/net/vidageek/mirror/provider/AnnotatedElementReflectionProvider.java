package net.vidageek.mirror.provider;

import java.lang.annotation.Annotation;
import java.util.List;

public interface AnnotatedElementReflectionProvider {
    <T extends Annotation> T getAnnotation(Class<T> cls);

    List<Annotation> getAnnotations();
}
