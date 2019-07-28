package net.vidageek.mirror.reflect.dsl;

import java.lang.annotation.Annotation;

public interface MemberHandler {
    <T extends Annotation> T annotation(Class<T> cls);
}
