package net.vidageek.mirror.reflect;

import java.lang.reflect.Constructor;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.list.dsl.MirrorList;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.ConstructorReflector;

public final class DefaultConstructorReflector<T> implements ConstructorReflector<T> {
    private final Class<T> clazz;
    private final ReflectionProvider provider;

    public DefaultConstructorReflector(ReflectionProvider reflectionProvider, Class<T> cls) {
        if (cls != null) {
            this.provider = reflectionProvider;
            this.clazz = cls;
            return;
        }
        throw new IllegalArgumentException("argument class cannot be null.");
    }

    public Constructor<T> withArgs(Class<?>... clsArr) {
        if (clsArr != null) {
            return this.provider.getClassReflectionProvider(this.clazz).reflectConstructor(clsArr);
        }
        throw new IllegalArgumentException("classes cannot be null");
    }

    public Constructor<T> withoutArgs() {
        return withArgs(new Class[0]);
    }

    public Constructor<T> withAnyArgs() {
        MirrorList constructors = new Mirror(this.provider).on(this.clazz).reflectAll().constructors();
        if (constructors.size() == 1) {
            return (Constructor) constructors.get(0);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("there is more than one constructor on class ");
        stringBuilder.append(this.clazz.getName());
        stringBuilder.append(". withAnyArgs must be called only on classes with a single constructor.");
        throw new MirrorException(stringBuilder.toString());
    }
}
