package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.jrockit;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator;

public class JRockit131Instantiator implements ObjectInstantiator {
    static /* synthetic */ Class class$java$lang$Class;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$lang$reflect$Constructor;
    private static Method newConstructorForSerializationMethod;
    private Constructor mungedConstructor;

    private static void initialize() {
        if (newConstructorForSerializationMethod == null) {
            try {
                Class class$;
                Class class$2;
                Class cls = Class.forName("COM.jrockit.reflect.MemberAccess");
                String str = "newConstructorForSerialization";
                Class[] clsArr = new Class[2];
                if (class$java$lang$reflect$Constructor == null) {
                    class$ = class$("java.lang.reflect.Constructor");
                    class$java$lang$reflect$Constructor = class$;
                } else {
                    class$ = class$java$lang$reflect$Constructor;
                }
                clsArr[0] = class$;
                if (class$java$lang$Class == null) {
                    class$2 = class$("java.lang.Class");
                    class$java$lang$Class = class$2;
                } else {
                    class$2 = class$java$lang$Class;
                }
                clsArr[1] = class$2;
                newConstructorForSerializationMethod = cls.getDeclaredMethod(str, clsArr);
                newConstructorForSerializationMethod.setAccessible(true);
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

    public JRockit131Instantiator(Class cls) {
        initialize();
        if (newConstructorForSerializationMethod != null) {
            try {
                Class class$;
                if (class$java$lang$Object == null) {
                    class$ = class$("java.lang.Object");
                    class$java$lang$Object = class$;
                } else {
                    class$ = class$java$lang$Object;
                }
                Constructor constructor = class$.getConstructor((Class[]) null);
                try {
                    this.mungedConstructor = (Constructor) newConstructorForSerializationMethod.invoke(null, new Object[]{constructor, cls});
                } catch (Exception e) {
                    throw new ObjenesisException(e);
                }
            } catch (NoSuchMethodException unused) {
                throw new Error("Cannot find constructor for java.lang.Object!");
            }
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
