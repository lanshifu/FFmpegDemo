package net.vidageek.mirror.invoke;

import net.vidageek.mirror.bean.Bean;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.invoke.dsl.SetterMethodHandler;
import net.vidageek.mirror.provider.ReflectionProvider;

public final class DefaultSetterMethodHandler implements SetterMethodHandler {
    private final String fieldName;
    private final ReflectionProvider provider;
    private final Object target;

    public DefaultSetterMethodHandler(ReflectionProvider reflectionProvider, Object obj, String str) {
        this.provider = reflectionProvider;
        this.target = obj;
        this.fieldName = str;
    }

    public void withValue(Object obj) {
        new Mirror(this.provider).on(this.target).invoke().method(new Bean().setter(this.fieldName)).withArgs(obj);
    }
}
