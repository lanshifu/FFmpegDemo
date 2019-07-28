package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.perc;

import java.lang.reflect.Method;
import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator;

public class PercInstantiator implements ObjectInstantiator {
    static /* synthetic */ Class class$java$io$ObjectInputStream;
    static /* synthetic */ Class class$java$lang$Class;
    private final Method newInstanceMethod;
    private final Object[] typeArgs = new Object[]{null, Boolean.FALSE};

    public PercInstantiator(Class cls) {
        this.typeArgs[0] = cls;
        try {
            Class class$;
            if (class$java$io$ObjectInputStream == null) {
                cls = class$("java.io.ObjectInputStream");
                class$java$io$ObjectInputStream = cls;
            } else {
                cls = class$java$io$ObjectInputStream;
            }
            String str = "newInstance";
            Class[] clsArr = new Class[2];
            if (class$java$lang$Class == null) {
                class$ = class$("java.lang.Class");
                class$java$lang$Class = class$;
            } else {
                class$ = class$java$lang$Class;
            }
            clsArr[0] = class$;
            clsArr[1] = Boolean.TYPE;
            this.newInstanceMethod = cls.getDeclaredMethod(str, clsArr);
            this.newInstanceMethod.setAccessible(true);
        } catch (RuntimeException e) {
            throw new ObjenesisException(e);
        } catch (NoSuchMethodException e2) {
            throw new ObjenesisException(e2);
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
            return this.newInstanceMethod.invoke(null, this.typeArgs);
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }
}
