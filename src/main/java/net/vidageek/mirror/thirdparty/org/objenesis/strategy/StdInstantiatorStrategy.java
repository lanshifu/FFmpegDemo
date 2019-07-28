package net.vidageek.mirror.thirdparty.org.objenesis.strategy;

import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.gcj.GCJInstantiator;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.jrockit.JRockit131Instantiator;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.jrockit.JRockitLegacyInstantiator;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.perc.PercInstantiator;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.sun.Sun13Instantiator;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator;

public class StdInstantiatorStrategy extends BaseInstantiatorStrategy {
    public ObjectInstantiator newInstantiatorOf(Class cls) {
        if (JVM_NAME.startsWith("Java HotSpot")) {
            if (VM_VERSION.startsWith("1.3")) {
                return new Sun13Instantiator(cls);
            }
        } else if (JVM_NAME.startsWith("BEA")) {
            if (VM_VERSION.startsWith("1.3")) {
                return new JRockit131Instantiator(cls);
            }
            if (!(!VM_VERSION.startsWith("1.4") || VENDOR_VERSION.startsWith("R") || (VM_INFO != null && VM_INFO.startsWith("R25.1") && VM_INFO.startsWith("R25.2")))) {
                return new JRockitLegacyInstantiator(cls);
            }
        } else if (JVM_NAME.startsWith("GNU libgcj")) {
            return new GCJInstantiator(cls);
        } else {
            if (JVM_NAME.startsWith("PERC")) {
                return new PercInstantiator(cls);
            }
        }
        return new SunReflectionFactoryInstantiator(cls);
    }
}
