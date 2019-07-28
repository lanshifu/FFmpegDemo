package net.vidageek.mirror.proxy.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.vidageek.mirror.exception.MethodNonInterceptedException;

public class CGLibInvocationHandler implements MethodInterceptor {
    private final net.vidageek.mirror.proxy.dsl.MethodInterceptor[] interceptors;

    public CGLibInvocationHandler(net.vidageek.mirror.proxy.dsl.MethodInterceptor... methodInterceptorArr) {
        this.interceptors = methodInterceptorArr;
    }

    public Object intercept(Object obj, Method method, Object[] objArr, MethodProxy methodProxy) throws Throwable {
        for (net.vidageek.mirror.proxy.dsl.MethodInterceptor methodInterceptor : this.interceptors) {
            if (methodInterceptor.accepts(method)) {
                return methodInterceptor.intercepts(obj, method, objArr);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(method.getName());
        stringBuilder.append(" was not intercepted");
        throw new MethodNonInterceptedException(stringBuilder.toString());
    }
}
