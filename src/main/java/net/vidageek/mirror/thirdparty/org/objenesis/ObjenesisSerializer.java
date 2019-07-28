package net.vidageek.mirror.thirdparty.org.objenesis;

import net.vidageek.mirror.thirdparty.org.objenesis.strategy.SerializingInstantiatorStrategy;

public class ObjenesisSerializer extends ObjenesisBase {
    public ObjenesisSerializer() {
        super(new SerializingInstantiatorStrategy());
    }

    public ObjenesisSerializer(boolean z) {
        super(new SerializingInstantiatorStrategy(), z);
    }
}
