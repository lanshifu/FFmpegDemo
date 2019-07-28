package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.basic;

import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator;

public class NewInstanceInstantiator implements ObjectInstantiator {
    private final Class type;

    public NewInstanceInstantiator(Class cls) {
        this.type = cls;
    }

    public Object newInstance() {
        try {
            return this.type.newInstance();
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }
}
