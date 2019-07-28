package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.gcj;

import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;

public class GCJInstantiator extends GCJInstantiatorBase {
    static /* synthetic */ Class class$java$lang$Object;

    public GCJInstantiator(Class cls) {
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
            Method method = newObjectMethod;
            ObjectInputStream objectInputStream = dummyStream;
            Object[] objArr = new Object[2];
            objArr[0] = this.type;
            if (class$java$lang$Object == null) {
                class$ = class$("java.lang.Object");
                class$java$lang$Object = class$;
            } else {
                class$ = class$java$lang$Object;
            }
            objArr[1] = class$;
            return method.invoke(objectInputStream, objArr);
        } catch (RuntimeException e) {
            throw new ObjenesisException(e);
        } catch (IllegalAccessException e2) {
            throw new ObjenesisException(e2);
        } catch (InvocationTargetException e22) {
            throw new ObjenesisException(e22);
        }
    }
}
