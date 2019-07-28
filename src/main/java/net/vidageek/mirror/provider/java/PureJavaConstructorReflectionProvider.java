package net.vidageek.mirror.provider.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import net.vidageek.mirror.exception.ReflectionProviderException;
import net.vidageek.mirror.provider.ConstructorReflectionProvider;

public final class PureJavaConstructorReflectionProvider<T> implements ConstructorReflectionProvider<T> {
    private final Class<T> clazz;
    private final Constructor<T> constructor;

    public PureJavaConstructorReflectionProvider(Class<T> cls, Constructor<T> constructor) {
        this.clazz = cls;
        this.constructor = constructor;
    }

    public void setAccessible() {
        this.constructor.setAccessible(true);
    }

    public T instantiate(Object... objArr) {
        StringBuilder stringBuilder;
        try {
            setAccessible();
            return this.constructor.newInstance(objArr);
        } catch (IllegalArgumentException e) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("could not invoke constructor ");
            stringBuilder.append(this.constructor.toGenericString());
            stringBuilder.append(" on class ");
            stringBuilder.append(this.clazz.getName());
            throw new ReflectionProviderException(stringBuilder.toString(), e);
        } catch (InstantiationException e2) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("could not invoke constructor ");
            stringBuilder.append(this.constructor.toGenericString());
            stringBuilder.append(" on class ");
            stringBuilder.append(this.clazz.getName());
            throw new ReflectionProviderException(stringBuilder.toString(), e2);
        } catch (IllegalAccessException e3) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("could not invoke constructor ");
            stringBuilder.append(this.constructor.toGenericString());
            stringBuilder.append(" on class ");
            stringBuilder.append(this.clazz.getName());
            throw new ReflectionProviderException(stringBuilder.toString(), e3);
        } catch (InvocationTargetException e4) {
            Throwable e5 = e4;
            stringBuilder = new StringBuilder();
            stringBuilder.append("could not invoke constructor ");
            stringBuilder.append(this.constructor.toGenericString());
            stringBuilder.append(" on class ");
            stringBuilder.append(this.clazz.getName());
            String stringBuilder2 = stringBuilder.toString();
            if (e5.getCause() != null) {
                e5 = e5.getCause();
            }
            throw new ReflectionProviderException(stringBuilder2, e5);
        }
    }

    public Class<?>[] getParameters() {
        return this.constructor.getParameterTypes();
    }
}
