package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.basic;

public class AccessibleInstantiator extends ConstructorInstantiator {
    public AccessibleInstantiator(Class cls) {
        super(cls);
        if (this.constructor != null) {
            this.constructor.setAccessible(true);
        }
    }
}
