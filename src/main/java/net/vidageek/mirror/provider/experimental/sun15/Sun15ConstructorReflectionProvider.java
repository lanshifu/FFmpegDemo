package net.vidageek.mirror.provider.experimental.sun15;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.ReflectionProviderException;
import net.vidageek.mirror.provider.ConstructorReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaConstructorReflectionProvider;
import sun.misc.Unsafe;
import sun.reflect.ConstructorAccessor;
import sun.reflect.MethodAccessor;
import sun.reflect.ReflectionFactory;

public final class Sun15ConstructorReflectionProvider<T> implements ConstructorReflectionProvider<T> {
    private static MethodAccessor constructorAccessorAcquirer;
    private static long constructorAccessorFieldOffset;
    private static final Object[] emptyObjectArray = new Object[0];
    private static Unsafe unsafe;
    private final ConstructorAccessor accessor;
    private final Class<T> clazz;
    private final Constructor<T> constructor;

    static {
        Mirror mirror = new Mirror(new DefaultMirrorReflectionProvider());
        Field field = mirror.on(Constructor.class).reflect().field("constructorAccessor");
        Method withoutArgs = mirror.on(Constructor.class).reflect().method("acquireConstructorAccessor").withoutArgs();
        unsafe = (Unsafe) mirror.on(Unsafe.class).get().field("theUnsafe");
        constructorAccessorFieldOffset = unsafe.objectFieldOffset(field);
        constructorAccessorAcquirer = ReflectionFactory.getReflectionFactory().newMethodAccessor(withoutArgs);
    }

    public Sun15ConstructorReflectionProvider(Class<T> cls, Constructor<T> constructor) {
        this.clazz = cls;
        this.constructor = constructor;
        ConstructorAccessor constructorAccessor = (ConstructorAccessor) unsafe.getObject(constructor, constructorAccessorFieldOffset);
        if (constructorAccessor == null) {
            try {
                constructorAccessorAcquirer.invoke(constructor, emptyObjectArray);
                constructorAccessor = (ConstructorAccessor) unsafe.getObject(constructor, constructorAccessorFieldOffset);
            } catch (IllegalArgumentException e) {
                throw new ReflectionProviderException("Could not acquire ConstructorAccessor.", e);
            } catch (InvocationTargetException e2) {
                throw new ReflectionProviderException("Could not acquire ConstructorAccessor.", e2);
            }
        }
        this.accessor = constructorAccessor;
    }

    public Class<?>[] getParameters() {
        return new PureJavaConstructorReflectionProvider(this.clazz, this.constructor).getParameters();
    }

    public T instantiate(Object... objArr) {
        StringBuilder stringBuilder;
        try {
            return this.accessor.newInstance(objArr);
        } catch (IllegalArgumentException e) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("could not invoke constructor ");
            stringBuilder.append(this.constructor.toGenericString());
            stringBuilder.append(" on class ");
            stringBuilder.append(this.clazz.getName());
            throw new ReflectionProviderException(stringBuilder.toString(), e);
        } catch (InstantiationException e2) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("could not invoke constructor ");
            stringBuilder.append(this.constructor.toGenericString());
            stringBuilder.append(" on class ");
            stringBuilder.append(this.clazz.getName());
            throw new ReflectionProviderException(stringBuilder.toString(), e2);
        } catch (InvocationTargetException e3) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("could not invoke constructor ");
            stringBuilder.append(this.constructor.toGenericString());
            stringBuilder.append(" on class ");
            stringBuilder.append(this.clazz.getName());
            throw new ReflectionProviderException(stringBuilder.toString(), e3);
        }
    }

    public void setAccessible() {
        this.constructor.setAccessible(true);
    }
}
