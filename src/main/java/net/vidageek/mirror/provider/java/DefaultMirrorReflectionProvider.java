package net.vidageek.mirror.provider.java;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import net.vidageek.mirror.provider.AnnotatedElementReflectionProvider;
import net.vidageek.mirror.provider.ClassReflectionProvider;
import net.vidageek.mirror.provider.ConstructorBypassingReflectionProvider;
import net.vidageek.mirror.provider.ConstructorReflectionProvider;
import net.vidageek.mirror.provider.FieldReflectionProvider;
import net.vidageek.mirror.provider.GenericTypeAccessor;
import net.vidageek.mirror.provider.MethodReflectionProvider;
import net.vidageek.mirror.provider.ParameterizedElementReflectionProvider;
import net.vidageek.mirror.provider.ProxyReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.proxy.cglib.CGLibProxyReflectionProvider;
import net.vidageek.mirror.proxy.dsl.MethodInterceptor;

public final class DefaultMirrorReflectionProvider implements ReflectionProvider {
    public ClassReflectionProvider<?> getClassReflectionProvider(String str) {
        return new PureJavaClassReflectionProvider(str);
    }

    public <T> ClassReflectionProvider<T> getClassReflectionProvider(Class<T> cls) {
        return new PureJavaClassReflectionProvider((Class) cls);
    }

    public FieldReflectionProvider getFieldReflectionProvider(Object obj, Class<?> cls, Field field) {
        return new PureJavaFieldReflectionProvider(obj, cls, field);
    }

    public <T> ConstructorReflectionProvider<T> getConstructorReflectionProvider(Class<T> cls, Constructor<T> constructor) {
        return new PureJavaConstructorReflectionProvider(cls, constructor);
    }

    public AnnotatedElementReflectionProvider getAnnotatedElementReflectionProvider(AnnotatedElement annotatedElement) {
        return new PureJavaAnnotatedElementReflectionProvider(annotatedElement);
    }

    public MethodReflectionProvider getMethodReflectionProvider(Object obj, Class<?> cls, Method method) {
        return new PureJavaMethodReflectionProvider(obj, cls, method);
    }

    public ParameterizedElementReflectionProvider getParameterizedElementProvider(GenericTypeAccessor genericTypeAccessor) {
        return new PureJavaParameterizedElementReflectionProvider(genericTypeAccessor);
    }

    public GenericTypeAccessor getClassGenericTypeAccessor(Class<?> cls) {
        return new PureJavaClassGenericTypeAccessor(cls);
    }

    public GenericTypeAccessor getFieldGenericTypeAccessor(Field field) {
        return new PureJavaFieldGenericTypeAccessor(field);
    }

    public <T> ConstructorBypassingReflectionProvider<T> getConstructorBypassingReflectionProvider(Class<T> cls) {
        return new ObjenesisConstructorBypassingReflectionProvider(cls);
    }

    public ProxyReflectionProvider getProxyReflectionProvider(Class<?> cls, List<Class<?>> list, MethodInterceptor... methodInterceptorArr) {
        return new CGLibProxyReflectionProvider(cls, list, methodInterceptorArr);
    }
}
