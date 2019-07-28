package net.vidageek.mirror.provider.java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.vidageek.mirror.exception.ReflectionProviderException;
import net.vidageek.mirror.provider.MethodReflectionProvider;

public final class PureJavaMethodReflectionProvider implements MethodReflectionProvider {
    private final Method method;
    private final Object target;

    public PureJavaMethodReflectionProvider(Object obj, Class<?> cls, Method method) {
        this.target = obj;
        this.method = method;
    }

    public Class<?>[] getParameters() {
        return this.method.getParameterTypes();
    }

    public void setAccessible() {
        this.method.setAccessible(true);
    }

    public Object invoke(Object[] objArr) {
        StringBuilder stringBuilder;
        try {
            setAccessible();
            return this.method.invoke(this.target, objArr);
        } catch (IllegalArgumentException e) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Could not invoke method ");
            stringBuilder.append(this.method.getName());
            throw new ReflectionProviderException(stringBuilder.toString(), e);
        } catch (IllegalAccessException e2) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Could not invoke method ");
            stringBuilder.append(this.method.getName());
            throw new ReflectionProviderException(stringBuilder.toString(), e2);
        } catch (InvocationTargetException e3) {
            Throwable e4 = e3;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Could not invoke method ");
            stringBuilder.append(this.method.getName());
            String stringBuilder2 = stringBuilder.toString();
            if (e4.getCause() != null) {
                e4 = e4.getCause();
            }
            throw new ReflectionProviderException(stringBuilder2, e4);
        } catch (NullPointerException e5) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Attempt to call an instance method ( ");
            stringBuilder.append(this.method.getName());
            stringBuilder.append(") on a null object.");
            throw new ReflectionProviderException(stringBuilder.toString(), e5);
        }
    }
}
