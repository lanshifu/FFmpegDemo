package net.vidageek.mirror.proxy.cglib;

import java.util.List;
import net.sf.cglib.proxy.Enhancer;
import net.vidageek.mirror.provider.ProxyReflectionProvider;
import net.vidageek.mirror.proxy.dsl.MethodInterceptor;

public class CGLibProxyReflectionProvider implements ProxyReflectionProvider {
    private final Class<?> clazz;
    private final Class<?>[] interfaces;
    private final MethodInterceptor[] methodInterceptors;

    public CGLibProxyReflectionProvider(Class<?> cls, List<Class<?>> list, MethodInterceptor... methodInterceptorArr) {
        this.clazz = cls;
        this.interfaces = (Class[]) list.toArray(new Class[list.size()]);
        this.methodInterceptors = methodInterceptorArr;
    }

    public Object createProxy() {
        return Enhancer.create(this.clazz, this.interfaces, new CGLibInvocationHandler(this.methodInterceptors));
    }
}
