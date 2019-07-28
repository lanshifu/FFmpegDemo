package net.vidageek.mirror.thirdparty.org.objenesis.instantiator;

public class SerializationInstantiatorHelper {
    static /* synthetic */ Class class$java$io$Serializable;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static Class getNonSerializableSuperClass(Class cls) {
        while (true) {
            Class class$;
            if (class$java$io$Serializable == null) {
                class$ = class$("java.io.Serializable");
                class$java$io$Serializable = class$;
            } else {
                class$ = class$java$io$Serializable;
            }
            if (!class$.isAssignableFrom(cls)) {
                return cls;
            }
            cls = cls.getSuperclass();
            if (cls == null) {
                throw new Error("Bad class hierarchy: No non-serializable parents");
            }
        }
    }
}
