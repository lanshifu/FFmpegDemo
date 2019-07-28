package net.vidageek.mirror.thirdparty.org.objenesis.instantiator;

public class NullInstantiator implements ObjectInstantiator {
    public Object newInstance() {
        return null;
    }
}
