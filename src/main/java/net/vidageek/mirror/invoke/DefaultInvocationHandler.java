package net.vidageek.mirror.invoke;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.vidageek.mirror.bean.Bean;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.invoke.dsl.ConstructorHandler;
import net.vidageek.mirror.invoke.dsl.InvocationHandler;
import net.vidageek.mirror.invoke.dsl.MethodHandler;
import net.vidageek.mirror.invoke.dsl.SetterMethodHandler;
import net.vidageek.mirror.provider.ReflectionProvider;

public final class DefaultInvocationHandler<T> implements InvocationHandler<T> {
    private final Class<?> clazz;
    private final ReflectionProvider provider;
    private final Object target;

    public DefaultInvocationHandler(ReflectionProvider reflectionProvider, Object obj) {
        if (obj != null) {
            this.provider = reflectionProvider;
            this.target = obj;
            this.clazz = obj.getClass();
            return;
        }
        throw new IllegalArgumentException("target can't be null");
    }

    public DefaultInvocationHandler(ReflectionProvider reflectionProvider, Class<T> cls) {
        if (cls != null) {
            this.provider = reflectionProvider;
            this.clazz = cls;
            this.target = null;
            return;
        }
        throw new IllegalArgumentException("target can't be null");
    }

    public MethodHandler method(String str) {
        if (str != null) {
            return new MethodHandlerByName(this.provider, this.target, this.clazz, str);
        }
        throw new IllegalArgumentException("methodName can't be null");
    }

    public ConstructorHandler<T> constructor() {
        if (this.target == null) {
            return new ConstructorHandlerByArgs(this.provider, this.clazz);
        }
        throw new IllegalStateException("must use constructor InvocationHandler(Class<T>) instead of InvocationHandler(Object).");
    }

    public MethodHandler method(Method method) {
        return new MethodHandlerByMethod(this.provider, this.target, this.clazz, method);
    }

    public <C> ConstructorHandler<C> constructor(Constructor<C> constructor) {
        return new ConstructorHandlerByConstructor(this.provider, this.clazz, constructor);
    }

    public Object getterFor(String str) {
        Method method = null;
        for (String method2 : new Bean().getter(str)) {
            method = new Mirror(this.provider).on(this.target.getClass()).reflect().method(method2).withoutArgs();
            if (method != null) {
                break;
            }
        }
        if (method != null) {
            return new Mirror(this.provider).on(this.target).invoke().method(method).withoutArgs();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Could not find getter for field ");
        stringBuilder.append(str);
        throw new MirrorException(stringBuilder.toString());
    }

    public Object getterFor(Field field) {
        return getterFor(field.getName());
    }

    public SetterMethodHandler setterFor(String str) {
        return new DefaultSetterMethodHandler(this.provider, this.target, str);
    }

    public SetterMethodHandler setterFor(Field field) {
        return setterFor(field.getName());
    }
}
