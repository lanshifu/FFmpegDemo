package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.perc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator;

public class PercSerializationInstantiator implements ObjectInstantiator {
    static /* synthetic */ Class class$java$io$ObjectInputStream;
    static /* synthetic */ Class class$java$io$Serializable;
    static /* synthetic */ Class class$java$lang$Class;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$lang$String;
    private final Method newInstanceMethod;
    private Object[] typeArgs;

    public PercSerializationInstantiator(Class cls) {
        Class class$;
        Class class$2;
        Class class$3;
        Class class$4;
        Class class$5;
        Class cls2 = cls;
        while (true) {
            if (class$java$io$Serializable == null) {
                class$ = class$("java.io.Serializable");
                class$java$io$Serializable = class$;
            } else {
                class$ = class$java$io$Serializable;
            }
            if (class$.isAssignableFrom(cls2)) {
                cls2 = cls2.getSuperclass();
            } else {
                try {
                    break;
                } catch (ClassNotFoundException e) {
                    throw new ObjenesisException(e);
                } catch (NoSuchMethodException e2) {
                    throw new ObjenesisException(e2);
                } catch (InvocationTargetException e22) {
                    throw new ObjenesisException(e22);
                } catch (IllegalAccessException e222) {
                    throw new ObjenesisException(e222);
                }
            }
        }
        class$ = Class.forName("COM.newmonics.PercClassLoader.Method");
        if (class$java$io$ObjectInputStream == null) {
            class$2 = class$("java.io.ObjectInputStream");
            class$java$io$ObjectInputStream = class$2;
        } else {
            class$2 = class$java$io$ObjectInputStream;
        }
        String str = "noArgConstruct";
        Class[] clsArr = new Class[3];
        if (class$java$lang$Class == null) {
            class$3 = class$("java.lang.Class");
            class$java$lang$Class = class$3;
        } else {
            class$3 = class$java$lang$Class;
        }
        clsArr[0] = class$3;
        if (class$java$lang$Object == null) {
            class$3 = class$("java.lang.Object");
            class$java$lang$Object = class$3;
        } else {
            class$3 = class$java$lang$Object;
        }
        clsArr[1] = class$3;
        clsArr[2] = class$;
        this.newInstanceMethod = class$2.getDeclaredMethod(str, clsArr);
        this.newInstanceMethod.setAccessible(true);
        class$ = Class.forName("COM.newmonics.PercClassLoader.PercClass");
        String str2 = "getPercClass";
        Class[] clsArr2 = new Class[1];
        if (class$java$lang$Class == null) {
            class$4 = class$("java.lang.Class");
            class$java$lang$Class = class$4;
        } else {
            class$4 = class$java$lang$Class;
        }
        clsArr2[0] = class$4;
        Object invoke = class$.getDeclaredMethod(str2, clsArr2).invoke(null, new Object[]{cls2});
        class$2 = invoke.getClass();
        str = "findMethod";
        clsArr = new Class[1];
        if (class$java$lang$String == null) {
            class$5 = class$("java.lang.String");
            class$java$lang$String = class$5;
        } else {
            class$5 = class$java$lang$String;
        }
        clsArr[0] = class$5;
        invoke = class$2.getDeclaredMethod(str, clsArr).invoke(invoke, new Object[]{"<init>()V"});
        this.typeArgs = new Object[]{cls2, cls, invoke};
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public Object newInstance() {
        try {
            return this.newInstanceMethod.invoke(null, this.typeArgs);
        } catch (IllegalAccessException e) {
            throw new ObjenesisException(e);
        } catch (InvocationTargetException e2) {
            throw new ObjenesisException(e2);
        }
    }
}
