package net.vidageek.mirror.provider.experimental.sun15;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.ReflectionProviderException;
import net.vidageek.mirror.provider.FieldReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;
import sun.misc.Unsafe;
import sun.reflect.FieldAccessor;
import sun.reflect.MethodAccessor;
import sun.reflect.ReflectionFactory;

public final class Sun15FieldReflectionProvider implements FieldReflectionProvider {
    private static final MethodAccessor fieldAccessorAcquirer;
    private static final long fieldAccessorFieldOffset;
    private static final Object[] objectArray = new Object[]{Boolean.valueOf(true)};
    private static final Unsafe unsafe;
    private final FieldAccessor accessor;
    private final Class<?> clazz;
    private final Field field;
    private final Object target;

    static {
        Mirror mirror = new Mirror(new DefaultMirrorReflectionProvider());
        Field field = mirror.on(Field.class).reflect().field("overrideFieldAccessor");
        Method withArgs = mirror.on(Field.class).reflect().method("acquireFieldAccessor").withArgs(Boolean.TYPE);
        unsafe = (Unsafe) mirror.on(Unsafe.class).get().field("theUnsafe");
        fieldAccessorFieldOffset = unsafe.objectFieldOffset(field);
        fieldAccessorAcquirer = ReflectionFactory.getReflectionFactory().newMethodAccessor(withArgs);
    }

    public Sun15FieldReflectionProvider(Object obj, Class<?> cls, Field field) {
        this.target = obj;
        this.clazz = cls;
        this.field = field;
        FieldAccessor fieldAccessor = (FieldAccessor) unsafe.getObject(field, fieldAccessorFieldOffset);
        if (fieldAccessor == null) {
            try {
                fieldAccessorAcquirer.invoke(field, objectArray);
                fieldAccessor = (FieldAccessor) unsafe.getObject(field, fieldAccessorFieldOffset);
            } catch (IllegalArgumentException e) {
                throw new ReflectionProviderException("Could not acquire FieldAccessor.", e);
            } catch (InvocationTargetException e2) {
                throw new ReflectionProviderException("Could not acquire FieldAccessor.", e2);
            }
        }
        this.accessor = fieldAccessor;
    }

    public Object getValue() {
        return this.accessor.get(this.target);
    }

    public void setValue(Object obj) {
        StringBuilder stringBuilder;
        try {
            this.accessor.set(this.target, obj);
        } catch (IllegalArgumentException unused) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("could not set value ");
            stringBuilder.append(obj);
            stringBuilder.append(" on field ");
            stringBuilder.append(this.field.getName());
            stringBuilder.append(" of class ");
            stringBuilder.append(this.clazz.getName());
            throw new ReflectionProviderException(stringBuilder.toString());
        } catch (IllegalAccessException unused2) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("could not set value ");
            stringBuilder.append(obj);
            stringBuilder.append(" on field ");
            stringBuilder.append(this.field.getName());
            stringBuilder.append(" of class ");
            stringBuilder.append(this.clazz.getName());
            throw new ReflectionProviderException(stringBuilder.toString());
        }
    }

    public void setAccessible() {
        this.field.setAccessible(true);
    }
}
