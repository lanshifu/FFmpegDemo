package net.vidageek.mirror.proxy.dsl;

public interface ProxyHandler<T> {
    T interceptingWith(MethodInterceptor... methodInterceptorArr);
}
