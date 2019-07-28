package net.vidageek.mirror.invoke;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.invoke.dsl.ConstructorHandler;
import net.vidageek.mirror.provider.ReflectionProvider;

public final class ConstructorHandlerByArgs<T> implements ConstructorHandler<T> {
    private final Class<T> clazz;
    private final ReflectionProvider provider;

    public ConstructorHandlerByArgs(ReflectionProvider reflectionProvider, Class<T> cls) {
        if (cls != null) {
            this.provider = reflectionProvider;
            this.clazz = cls;
            return;
        }
        throw new IllegalArgumentException("Argument class cannot be null");
    }

    public T withoutArgs() {
        return withArgs(new Object[0]);
    }

    public T withArgs(Object... objArr) {
        return new ConstructorHandlerByConstructor(this.provider, this.clazz, getConstructor(objArr)).withArgs(objArr);
    }

    public T bypasser() {
        return this.provider.getConstructorBypassingReflectionProvider(this.clazz).bypassConstructor();
    }

    private Constructor<T> getConstructor(Object... objArr) {
        int i = 0;
        int length = objArr == null ? 0 : objArr.length;
        Class[] clsArr = new Class[length];
        while (i < length) {
            if (objArr[i] != null) {
                clsArr[i] = objArr[i].getClass();
                i++;
            } else {
                throw new IllegalArgumentException("Cannot invoke a constructor by args if one of it's arguments is null. First reflect the constructor.");
            }
        }
        Constructor withArgs = new Mirror(this.provider).on(this.clazz).reflect().constructor().withArgs(clsArr);
        if (withArgs != null) {
            return withArgs;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Could not find constructor with args ");
        stringBuilder.append(Arrays.asList(clsArr));
        stringBuilder.append(" on class ");
        stringBuilder.append(this.clazz.getName());
        throw new MirrorException(stringBuilder.toString());
    }
}
