package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.sun;

import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.SerializationInstantiatorHelper;

public class Sun13SerializationInstantiator extends Sun13InstantiatorBase {
    private final Class superType;

    public Sun13SerializationInstantiator(Class cls) {
        super(cls);
        this.superType = SerializationInstantiatorHelper.getNonSerializableSuperClass(cls);
    }

    public Object newInstance() {
        try {
            return allocateNewObjectMethod.invoke(null, new Object[]{this.type, this.superType});
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }
}
