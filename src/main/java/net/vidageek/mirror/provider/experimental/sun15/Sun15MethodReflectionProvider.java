package net.vidageek.mirror.provider.experimental.sun15;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.ReflectionProviderException;
import net.vidageek.mirror.provider.MethodReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaMethodReflectionProvider;
import sun.misc.Unsafe;
import sun.reflect.MethodAccessor;
import sun.reflect.ReflectionFactory;

public final class Sun15MethodReflectionProvider implements MethodReflectionProvider {
    private static final Object[] emptyObjectArray = new Object[0];
    private static final MethodAccessor methodAccessorAcquirer;
    private static final long methodAccessorFieldOffset;
    private static final Unsafe unsafe;
    private final MethodAccessor accessor;
    private final Class<?> clazz;
    private final Method method;
    private final Object target;

    static {
        Mirror mirror = new Mirror(new DefaultMirrorReflectionProvider());
        Field field = mirror.on(Method.class).reflect().field("methodAccessor");
        Method withoutArgs = mirror.on(Method.class).reflect().method("acquireMethodAccessor").withoutArgs();
        unsafe = (Unsafe) mirror.on(Unsafe.class).get().field("theUnsafe");
        methodAccessorFieldOffset = unsafe.objectFieldOffset(field);
        methodAccessorAcquirer = ReflectionFactory.getReflectionFactory().newMethodAccessor(withoutArgs);
    }

    public Sun15MethodReflectionProvider(Object obj, Class<?> cls, Method method) {
        this.target = obj;
        this.clazz = cls;
        this.method = method;
        MethodAccessor methodAccessor = (MethodAccessor) unsafe.getObject(method, methodAccessorFieldOffset);
        if (methodAccessor == null) {
            try {
                methodAccessorAcquirer.invoke(method, emptyObjectArray);
                methodAccessor = (MethodAccessor) unsafe.getObject(method, methodAccessorFieldOffset);
            } catch (IllegalArgumentException e) {
                throw new ReflectionProviderException("Could not acquire MethodAccessor.", e);
            } catch (InvocationTargetException e2) {
                throw new ReflectionProviderException("Could not acquire MethodAccessor.", e2);
            }
        }
        this.accessor = methodAccessor;
    }

    public Class<?>[] getParameters() {
        return new PureJavaMethodReflectionProvider(this.target, this.clazz, this.method).getParameters();
    }

    public Object invoke(Object[] objArr) {
        StringBuilder stringBuilder;
        try {
            return this.accessor.invoke(this.target, objArr);
        } catch (IllegalArgumentException e) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Could not invoke method ");
            stringBuilder.append(this.method.getName());
            throw new ReflectionProviderException(stringBuilder.toString(), e);
        } catch (InvocationTargetException e2) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Could not invoke method ");
            stringBuilder.append(this.method.getName());
            throw new ReflectionProviderException(stringBuilder.toString(), e2);
        } catch (NullPointerException e3) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Attempt to call an instance method ( ");
            stringBuilder.append(this.method.getName());
            stringBuilder.append(") on a null object.");
            throw new ReflectionProviderException(stringBuilder.toString(), e3);
        }
    }

    public void setAccessible() {
        this.method.setAccessible(true);
    }
}
