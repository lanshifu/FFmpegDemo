package net.vidageek.mirror.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import net.vidageek.mirror.proxy.dsl.MethodInterceptor;

public class DynamicInvocationHadler implements InvocationHandler {
    private final MethodInterceptor[] interceptors;

    public DynamicInvocationHadler(MethodInterceptor... methodInterceptorArr) {
        this.interceptors = methodInterceptorArr;
    }

    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        for (MethodInterceptor methodInterceptor : this.interceptors) {
            if (methodInterceptor.accepts(method)) {
                return methodInterceptor.intercepts(obj, method, objArr);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(method.getName());
        stringBuilder.append(" was not intercepted");
        throw new Exception(stringBuilder.toString());
    }
}
