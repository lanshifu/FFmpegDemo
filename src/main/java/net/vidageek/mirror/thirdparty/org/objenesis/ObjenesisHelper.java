package net.vidageek.mirror.thirdparty.org.objenesis;

import java.io.Serializable;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator;

public final class ObjenesisHelper {
    private static final Objenesis OBJENESIS_SERIALIZER = new ObjenesisSerializer();
    private static final Objenesis OBJENESIS_STD = new ObjenesisStd();

    private ObjenesisHelper() {
    }

    public static Object newInstance(Class cls) {
        return OBJENESIS_STD.newInstance(cls);
    }

    public static Serializable newSerializableInstance(Class cls) {
        return (Serializable) OBJENESIS_SERIALIZER.newInstance(cls);
    }

    public static ObjectInstantiator getInstantiatorOf(Class cls) {
        return OBJENESIS_STD.getInstantiatorOf(cls);
    }

    public static ObjectInstantiator getSerializableObjectInstantiatorOf(Class cls) {
        return OBJENESIS_SERIALIZER.getInstantiatorOf(cls);
    }
}
