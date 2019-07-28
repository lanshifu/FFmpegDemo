package net.vidageek.mirror.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.List;
import net.vidageek.mirror.list.BackedMirrorList;
import net.vidageek.mirror.list.dsl.Matcher;
import net.vidageek.mirror.list.dsl.MirrorList;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.AllMemberHandler;

public final class DefaultAllMemberHandler implements AllMemberHandler {
    private final AnnotatedElement member;
    private final ReflectionProvider provider;

    public DefaultAllMemberHandler(ReflectionProvider reflectionProvider, AnnotatedElement annotatedElement) {
        if (annotatedElement != null) {
            this.provider = reflectionProvider;
            this.member = annotatedElement;
            return;
        }
        throw new IllegalArgumentException("Argument member cannot be null");
    }

    public MirrorList<Annotation> annotations() {
        return new BackedMirrorList(this.provider.getAnnotatedElementReflectionProvider(this.member).getAnnotations());
    }

    public List<Annotation> annotationsMatching(Matcher<Annotation> matcher) {
        return annotations().matching(matcher);
    }
}
