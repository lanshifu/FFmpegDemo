package net.vidageek.mirror.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

public final class MirrorProviderBuilder {
    private final InputStream configurationFile;

    public MirrorProviderBuilder(InputStream inputStream) {
        this.configurationFile = inputStream;
    }

    public ReflectionProvider createProvider() {
        if (this.configurationFile == null) {
            return new DefaultMirrorReflectionProvider();
        }
        return (ReflectionProvider) new Mirror(new DefaultMirrorReflectionProvider()).on((String) processProperties(this.configurationFile).get(Item.REFLECTION_PROVIDER)).invoke().constructor().withoutArgs();
    }

    private Map<Item, String> processProperties(InputStream inputStream) {
        HashMap hashMap = new HashMap();
        hashMap.put(Item.REFLECTION_PROVIDER, DefaultMirrorReflectionProvider.class.getName());
        try {
            Properties properties = new Properties();
            properties.load(inputStream);
            for (Item item : Item.values()) {
                if (properties.containsKey(item.getPropertyKey())) {
                    hashMap.put(item, properties.getProperty(item.getPropertyKey()).trim());
                }
            }
            return hashMap;
        } catch (IOException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("could not ready file ");
            stringBuilder.append(inputStream);
            throw new MirrorException(stringBuilder.toString(), e);
        }
    }
}
