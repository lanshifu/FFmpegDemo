package net.vidageek.mirror.invoke;

import java.lang.reflect.Constructor;
import net.vidageek.mirror.invoke.dsl.ConstructorHandler;
import net.vidageek.mirror.provider.ConstructorReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProvider;

public final class ConstructorHandlerByConstructor<T> implements ConstructorHandler<T> {
    private final Class<T> clazz;
    private final Constructor<T> constructor;
    private final ReflectionProvider provider;

    public ConstructorHandlerByConstructor(ReflectionProvider reflectionProvider, Class<T> cls, Constructor<T> constructor) {
        if (cls == null) {
            throw new IllegalArgumentException("clazz cannot be null");
        } else if (constructor == null) {
            throw new IllegalArgumentException("constructor cannot be null");
        } else if (cls.equals(constructor.getDeclaringClass())) {
            this.provider = reflectionProvider;
            this.clazz = cls;
            this.constructor = constructor;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("constructor declaring type should be ");
            stringBuilder.append(cls.getName());
            stringBuilder.append(" but was ");
            stringBuilder.append(constructor.getDeclaringClass().getName());
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public T withArgs(Object... objArr) {
        ConstructorReflectionProvider constructorReflectionProvider = this.provider.getConstructorReflectionProvider(this.clazz, this.constructor);
        constructorReflectionProvider.setAccessible();
        return constructorReflectionProvider.instantiate(objArr);
    }

    public T withoutArgs() {
        return withArgs(new Object[0]);
    }

    public T bypasser() {
        return this.provider.getConstructorBypassingReflectionProvider(this.clazz).bypassConstructor();
    }
}
