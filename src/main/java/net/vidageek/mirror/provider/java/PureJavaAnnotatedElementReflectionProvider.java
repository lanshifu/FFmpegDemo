package net.vidageek.mirror.provider.java;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.List;
import net.vidageek.mirror.provider.AnnotatedElementReflectionProvider;

public final class PureJavaAnnotatedElementReflectionProvider implements AnnotatedElementReflectionProvider {
    private final AnnotatedElement element;

    public PureJavaAnnotatedElementReflectionProvider(AnnotatedElement annotatedElement) {
        this.element = annotatedElement;
    }

    public List<Annotation> getAnnotations() {
        return Arrays.asList(this.element.getAnnotations());
    }

    public <T extends Annotation> T getAnnotation(Class<T> cls) {
        return this.element.getAnnotation(cls);
    }
}
