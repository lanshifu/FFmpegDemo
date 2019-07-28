package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.sun;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;

public class Sun13Instantiator extends Sun13InstantiatorBase {
    static /* synthetic */ Class class$java$lang$Object;

    public Sun13Instantiator(Class cls) {
        super(cls);
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
            Class class$;
            Method method = allocateNewObjectMethod;
            Object[] objArr = new Object[2];
            objArr[0] = this.type;
            if (class$java$lang$Object == null) {
                class$ = class$("java.lang.Object");
                class$java$lang$Object = class$;
            } else {
                class$ = class$java$lang$Object;
            }
            objArr[1] = class$;
            return method.invoke(null, objArr);
        } catch (RuntimeException e) {
            throw new ObjenesisException(e);
        } catch (IllegalAccessException e2) {
            throw new ObjenesisException(e2);
        } catch (InvocationTargetException e22) {
            throw new ObjenesisException(e22);
        }
    }
}
