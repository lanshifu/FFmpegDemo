package net.vidageek.mirror;

import java.lang.reflect.AnnotatedElement;
import net.vidageek.mirror.dsl.MemberController;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.DefaultAllMemberHandler;
import net.vidageek.mirror.reflect.DefaultMemberHandler;
import net.vidageek.mirror.reflect.dsl.AllMemberHandler;
import net.vidageek.mirror.reflect.dsl.MemberHandler;

public final class DefaultMemberController implements MemberController {
    private final AnnotatedElement member;
    private final ReflectionProvider provider;

    public DefaultMemberController(ReflectionProvider reflectionProvider, AnnotatedElement annotatedElement) {
        if (annotatedElement != null) {
            this.provider = reflectionProvider;
            this.member = annotatedElement;
            return;
        }
        throw new IllegalArgumentException("Argument member cannot be null");
    }

    public AllMemberHandler reflectAll() {
        return new DefaultAllMemberHandler(this.provider, this.member);
    }

    public MemberHandler reflect() {
        return new DefaultMemberHandler(this.provider, this.member);
    }
}
