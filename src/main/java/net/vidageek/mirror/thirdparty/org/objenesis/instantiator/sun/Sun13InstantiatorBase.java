package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.sun;

import java.lang.reflect.Method;
import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator;

public abstract class Sun13InstantiatorBase implements ObjectInstantiator {
    protected static Method allocateNewObjectMethod;
    static /* synthetic */ Class class$java$io$ObjectInputStream;
    static /* synthetic */ Class class$java$lang$Class;
    protected final Class type;

    public abstract Object newInstance();

    private static void initialize() {
        if (allocateNewObjectMethod == null) {
            try {
                Class class$;
                Class class$2;
                Class class$3;
                if (class$java$io$ObjectInputStream == null) {
                    class$ = class$("java.io.ObjectInputStream");
                    class$java$io$ObjectInputStream = class$;
                } else {
                    class$ = class$java$io$ObjectInputStream;
                }
                String str = "allocateNewObject";
                Class[] clsArr = new Class[2];
                if (class$java$lang$Class == null) {
                    class$2 = class$("java.lang.Class");
                    class$java$lang$Class = class$2;
                } else {
                    class$2 = class$java$lang$Class;
                }
                clsArr[0] = class$2;
                if (class$java$lang$Class == null) {
                    class$3 = class$("java.lang.Class");
                    class$java$lang$Class = class$3;
                } else {
                    class$3 = class$java$lang$Class;
                }
                clsArr[1] = class$3;
                allocateNewObjectMethod = class$.getDeclaredMethod(str, clsArr);
                allocateNewObjectMethod.setAccessible(true);
            } catch (RuntimeException e) {
                throw new ObjenesisException(e);
            } catch (NoSuchMethodException e2) {
                throw new ObjenesisException(e2);
            }
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public Sun13InstantiatorBase(Class cls) {
        this.type = cls;
        initialize();
    }
}
