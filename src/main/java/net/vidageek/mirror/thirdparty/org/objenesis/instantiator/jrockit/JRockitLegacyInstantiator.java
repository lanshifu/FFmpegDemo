package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.jrockit;

import java.lang.reflect.Method;
import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator;

public class JRockitLegacyInstantiator implements ObjectInstantiator {
    static /* synthetic */ Class class$java$lang$Class;
    private static Method safeAllocObjectMethod;
    private final Class type;

    private static void initialize() {
        if (safeAllocObjectMethod == null) {
            try {
                Class class$;
                Class cls = Class.forName("jrockit.vm.MemSystem");
                String str = "safeAllocObject";
                Class[] clsArr = new Class[1];
                if (class$java$lang$Class == null) {
                    class$ = class$("java.lang.Class");
                    class$java$lang$Class = class$;
                } else {
                    class$ = class$java$lang$Class;
                }
                clsArr[0] = class$;
                safeAllocObjectMethod = cls.getDeclaredMethod(str, clsArr);
                safeAllocObjectMethod.setAccessible(true);
            } catch (RuntimeException e) {
                throw new ObjenesisException(e);
            } catch (ClassNotFoundException e2) {
                throw new ObjenesisException(e2);
            } catch (NoSuchMethodException e22) {
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

    public JRockitLegacyInstantiator(Class cls) {
        initialize();
        this.type = cls;
    }

    public Object newInstance() {
        try {
            return safeAllocObjectMethod.invoke(null, new Object[]{this.type});
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }
}
