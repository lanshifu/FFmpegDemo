package net.vidageek.mirror.thirdparty.org.objenesis.strategy;

import java.io.NotSerializableException;
import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.basic.ObjectStreamClassInstantiator;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.gcj.GCJSerializationInstantiator;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.perc.PercSerializationInstantiator;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.sun.Sun13SerializationInstantiator;

public class SerializingInstantiatorStrategy extends BaseInstantiatorStrategy {
    static /* synthetic */ Class class$java$io$Serializable;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public ObjectInstantiator newInstantiatorOf(Class cls) {
        Class class$;
        if (class$java$io$Serializable == null) {
            class$ = class$("java.io.Serializable");
            class$java$io$Serializable = class$;
        } else {
            class$ = class$java$io$Serializable;
        }
        if (class$.isAssignableFrom(cls)) {
            if (JVM_NAME.startsWith("Java HotSpot")) {
                if (VM_VERSION.startsWith("1.3")) {
                    return new Sun13SerializationInstantiator(cls);
                }
            } else if (JVM_NAME.startsWith("GNU libgcj")) {
                return new GCJSerializationInstantiator(cls);
            } else {
                if (JVM_NAME.startsWith("PERC")) {
                    return new PercSerializationInstantiator(cls);
                }
            }
            return new ObjectStreamClassInstantiator(cls);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(cls);
        stringBuffer.append(" not serializable");
        throw new ObjenesisException(new NotSerializableException(stringBuffer.toString()));
    }
}
