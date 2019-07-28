package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.sun;

import java.lang.reflect.Constructor;
import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator;
import sun.reflect.ReflectionFactory;

public class SunReflectionFactoryInstantiator implements ObjectInstantiator {
    static /* synthetic */ Class class$java$lang$Object;
    private final Constructor mungedConstructor;

    public SunReflectionFactoryInstantiator(Class cls) {
        ReflectionFactory reflectionFactory = ReflectionFactory.getReflectionFactory();
        try {
            Class class$;
            if (class$java$lang$Object == null) {
                class$ = class$("java.lang.Object");
                class$java$lang$Object = class$;
            } else {
                class$ = class$java$lang$Object;
            }
            this.mungedConstructor = reflectionFactory.newConstructorForSerialization(cls, class$.getConstructor((Class[]) null));
            this.mungedConstructor.setAccessible(true);
        } catch (NoSuchMethodException unused) {
            throw new Error("Cannot find constructor for java.lang.Object!");
        }
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
            return this.mungedConstructor.newInstance((Object[]) null);
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }
}
