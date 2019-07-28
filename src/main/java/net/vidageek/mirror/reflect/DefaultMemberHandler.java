package net.vidageek.mirror.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.MemberHandler;

public final class DefaultMemberHandler implements MemberHandler {
    private final AnnotatedElement member;
    private final ReflectionProvider provider;

    public DefaultMemberHandler(ReflectionProvider reflectionProvider, AnnotatedElement annotatedElement) {
        if (annotatedElement != null) {
            this.provider = reflectionProvider;
            this.member = annotatedElement;
            return;
        }
        throw new IllegalArgumentException("Argument member cannot be null");
    }

    public <T extends Annotation> T annotation(Class<T> cls) {
        return this.provider.getAnnotatedElementReflectionProvider(this.member).getAnnotation(cls);
    }
}
