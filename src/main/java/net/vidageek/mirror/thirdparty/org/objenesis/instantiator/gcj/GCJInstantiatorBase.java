package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.gcj;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator;

public abstract class GCJInstantiatorBase implements ObjectInstantiator {
    static /* synthetic */ Class class$java$io$ObjectInputStream;
    static /* synthetic */ Class class$java$lang$Class;
    protected static ObjectInputStream dummyStream;
    protected static Method newObjectMethod;
    protected final Class type;

    private static class DummyStream extends ObjectInputStream {
    }

    public abstract Object newInstance();

    private static void initialize() {
        if (newObjectMethod == null) {
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
                String str = "newObject";
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
                newObjectMethod = class$.getDeclaredMethod(str, clsArr);
                newObjectMethod.setAccessible(true);
                dummyStream = new DummyStream();
            } catch (RuntimeException e) {
                throw new ObjenesisException(e);
            } catch (NoSuchMethodException e2) {
                throw new ObjenesisException(e2);
            } catch (IOException e22) {
                throw new ObjenesisException(e22);
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

    public GCJInstantiatorBase(Class cls) {
        this.type = cls;
        initialize();
    }
}
