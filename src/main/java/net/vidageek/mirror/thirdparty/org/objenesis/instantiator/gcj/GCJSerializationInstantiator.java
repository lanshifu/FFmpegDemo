package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.gcj;

import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.SerializationInstantiatorHelper;

public class GCJSerializationInstantiator extends GCJInstantiatorBase {
    private Class superType;

    public GCJSerializationInstantiator(Class cls) {
        super(cls);
        this.superType = SerializationInstantiatorHelper.getNonSerializableSuperClass(cls);
    }

    public Object newInstance() {
        try {
            return newObjectMethod.invoke(dummyStream, new Object[]{this.type, this.superType});
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }
}
