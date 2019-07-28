package net.vidageek.mirror;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.proxy.dsl.MethodInterceptor;
import net.vidageek.mirror.proxy.dsl.ProxyHandler;

public class DefaultProxyHandler<T> implements ProxyHandler<T> {
    private Class<?> baseClass = Object.class;
    private final List<Class<?>> interfaces = new ArrayList();
    private final ReflectionProvider provider;

    public DefaultProxyHandler(ReflectionProvider reflectionProvider, Class<?>[] clsArr) {
        this.provider = reflectionProvider;
        extractBaseClassAndInterfaces(clsArr);
    }

    private void extractBaseClassAndInterfaces(Class<?>[] clsArr) {
        Object obj = null;
        for (Class cls : clsArr) {
            if (cls.isInterface()) {
                this.interfaces.add(cls);
            } else if (obj != null) {
                throw new IllegalArgumentException("Cannot proxify more than one concrete/abstract class");
            } else if (Modifier.isFinal(cls.getModifiers())) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Cannot proxify final class ");
                stringBuilder.append(cls.getName());
                throw new IllegalArgumentException(stringBuilder.toString());
            } else {
                this.baseClass = cls;
                obj = 1;
            }
        }
    }

    public T interceptingWith(MethodInterceptor... methodInterceptorArr) {
        if (methodInterceptorArr != null && methodInterceptorArr.length != 0) {
            return this.provider.getProxyReflectionProvider(this.baseClass, this.interfaces, methodInterceptorArr).createProxy();
        }
        throw new IllegalArgumentException("interceptors cannot be null or empty");
    }
}
