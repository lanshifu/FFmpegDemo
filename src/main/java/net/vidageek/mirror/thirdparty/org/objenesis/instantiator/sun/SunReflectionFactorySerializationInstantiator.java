package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.sun;

import java.io.NotSerializableException;
import java.lang.reflect.Constructor;
import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.SerializationInstantiatorHelper;
import sun.reflect.ReflectionFactory;

public class SunReflectionFactorySerializationInstantiator implements ObjectInstantiator {
    private final Constructor mungedConstructor;

    public SunReflectionFactorySerializationInstantiator(Class cls) {
        try {
            this.mungedConstructor = ReflectionFactory.getReflectionFactory().newConstructorForSerialization(cls, SerializationInstantiatorHelper.getNonSerializableSuperClass(cls).getConstructor((Class[]) null));
            this.mungedConstructor.setAccessible(true);
        } catch (NoSuchMethodException unused) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(cls);
            stringBuffer.append(" has no suitable superclass constructor");
            throw new ObjenesisException(new NotSerializableException(stringBuffer.toString()));
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
