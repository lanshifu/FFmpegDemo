package net.vidageek.mirror.reflect;

import net.vidageek.mirror.provider.GenericTypeAccessor;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.ParameterizedElementHandler;

public class DefaultParameterizedElementHandler implements ParameterizedElementHandler {
    private final GenericTypeAccessor accessor;
    private final ReflectionProvider provider;

    public DefaultParameterizedElementHandler(ReflectionProvider reflectionProvider, GenericTypeAccessor genericTypeAccessor) {
        if (genericTypeAccessor != null) {
            this.provider = reflectionProvider;
            this.accessor = genericTypeAccessor;
            return;
        }
        throw new IllegalArgumentException("Argument accessor cannot be null");
    }

    public Class<?> atPosition(int i) {
        return this.provider.getParameterizedElementProvider(this.accessor).getTypeAtPosition(i);
    }
}
