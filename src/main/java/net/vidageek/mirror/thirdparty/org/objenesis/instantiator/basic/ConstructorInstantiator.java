package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.basic;

import java.lang.reflect.Constructor;
import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator;

public class ConstructorInstantiator implements ObjectInstantiator {
    protected Constructor constructor;

    public ConstructorInstantiator(Class cls) {
        try {
            this.constructor = cls.getDeclaredConstructor((Class[]) null);
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }

    public Object newInstance() {
        try {
            return this.constructor.newInstance((Object[]) null);
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }
}
