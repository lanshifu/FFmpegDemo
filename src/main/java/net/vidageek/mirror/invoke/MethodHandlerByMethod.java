package net.vidageek.mirror.invoke;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import net.vidageek.mirror.invoke.dsl.MethodHandler;
import net.vidageek.mirror.provider.MethodReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProvider;

public final class MethodHandlerByMethod implements MethodHandler {
    private final Class<?> clazz;
    private final Method method;
    private final ReflectionProvider provider;
    private final Object target;

    public MethodHandlerByMethod(ReflectionProvider reflectionProvider, Object obj, Class<?> cls, Method method) {
        if (cls == null) {
            throw new IllegalArgumentException("clazz cannot be null");
        } else if (method == null) {
            throw new IllegalArgumentException("method cannot be null");
        } else if (method.getDeclaringClass().isAssignableFrom(cls)) {
            this.provider = reflectionProvider;
            this.target = obj;
            this.clazz = cls;
            this.method = method;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("method ");
            stringBuilder.append(method);
            stringBuilder.append(" cannot be invoked on clazz ");
            stringBuilder.append(cls.getName());
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public Object withArgs(Object... objArr) {
        if (this.target != null || Modifier.isStatic(this.method.getModifiers())) {
            MethodReflectionProvider methodReflectionProvider = this.provider.getMethodReflectionProvider(this.target, this.clazz, this.method);
            methodReflectionProvider.setAccessible();
            return methodReflectionProvider.invoke(objArr);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("attempt to call instance method ");
        stringBuilder.append(this.method.getName());
        stringBuilder.append(" on class ");
        stringBuilder.append(this.clazz.getName());
        throw new IllegalStateException(stringBuilder.toString());
    }

    public Object withoutArgs() {
        return withArgs(new Object[0]);
    }
}
