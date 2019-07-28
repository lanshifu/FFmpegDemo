package net.vidageek.mirror.invoke;

import java.lang.reflect.Method;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.invoke.dsl.MethodHandler;
import net.vidageek.mirror.provider.ReflectionProvider;

public final class MethodHandlerByName implements MethodHandler {
    private final Class<?> clazz;
    private final String methodName;
    private final ReflectionProvider provider;
    private final Object target;

    public MethodHandlerByName(ReflectionProvider reflectionProvider, Object obj, Class<?> cls, String str) {
        if (cls == null) {
            throw new IllegalArgumentException("clazz can't be null");
        } else if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("methodName can't be null");
        } else {
            this.provider = reflectionProvider;
            this.target = obj;
            this.clazz = cls;
            this.methodName = str;
        }
    }

    public Object withoutArgs() {
        return withArgs(new Object[0]);
    }

    public Object withArgs(Object... objArr) {
        return new MethodHandlerByMethod(this.provider, this.target, this.clazz, getMethod(objArr)).withArgs(objArr);
    }

    private Method getMethod(Object[] objArr) {
        int i = 0;
        int length = objArr == null ? 0 : objArr.length;
        Class[] clsArr = new Class[length];
        while (i < length) {
            if (objArr[i] != null) {
                clsArr[i] = objArr[i].getClass();
                i++;
            } else {
                throw new IllegalArgumentException("Cannot invoke a method by name if one of it's arguments is null. First reflect the method.");
            }
        }
        Method withArgs = new Mirror(this.provider).on(this.clazz).reflect().method(this.methodName).withArgs(clsArr);
        if (withArgs != null) {
            return withArgs;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Could not find method ");
        stringBuilder.append(this.methodName);
        stringBuilder.append(" on class ");
        stringBuilder.append(this.clazz.getName());
        throw new MirrorException(stringBuilder.toString());
    }
}
