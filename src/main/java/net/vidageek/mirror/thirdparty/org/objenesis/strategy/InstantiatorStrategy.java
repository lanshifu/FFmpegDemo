package net.vidageek.mirror.thirdparty.org.objenesis.strategy;

import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator;

public interface InstantiatorStrategy {
    ObjectInstantiator newInstantiatorOf(Class cls);
}
