package net.vidageek.mirror.dsl;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.ArrayList;
import net.vidageek.mirror.DefaultAccessorsController;
import net.vidageek.mirror.DefaultClassController;
import net.vidageek.mirror.DefaultFieldController;
import net.vidageek.mirror.DefaultMemberController;
import net.vidageek.mirror.DefaultProxyHandler;
import net.vidageek.mirror.config.MirrorProviderBuilder;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.proxy.dsl.ProxyHandler;

public final class Mirror {
    private static final String MIRROR_CFG = "/mirror.properties";
    private static final ReflectionProvider cachedProvider = new MirrorProviderBuilder(Mirror.class.getResourceAsStream(MIRROR_CFG)).createProvider();
    private final ReflectionProvider provider;

    public Mirror(ReflectionProvider reflectionProvider) {
        this.provider = reflectionProvider;
    }

    public Mirror() {
        this(cachedProvider);
    }

    public Class<?> reflectClass(String str) {
        if (str != null && str.trim().length() != 0) {
            return this.provider.getClassReflectionProvider(str).reflectClass();
        }
        throw new IllegalArgumentException("className cannot be null or empty");
    }

    public <T> ClassController<T> on(Class<T> cls) {
        return new DefaultClassController(this.provider, cls);
    }

    public AccessorsController on(Object obj) {
        return new DefaultAccessorsController(this.provider, obj);
    }

    public ClassController<?> on(String str) {
        return on(reflectClass(str));
    }

    public MemberController on(AnnotatedElement annotatedElement) {
        return new DefaultMemberController(this.provider, annotatedElement);
    }

    public FieldController on(Field field) {
        return new DefaultFieldController(this.provider, field);
    }

    public <T> ProxyHandler<T> proxify(Class<T> cls) {
        return proxify(cls);
    }

    public ProxyHandler<Object> proxify(String str) {
        return proxify(str);
    }

    public ProxyHandler<Object> proxify(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String reflectClass : strArr) {
            arrayList.add(reflectClass(reflectClass));
        }
        return proxify((Class[]) arrayList.toArray(new Class[strArr.length]));
    }

    public ProxyHandler<Object> proxify(Class<?>... clsArr) {
        return new DefaultProxyHandler(this.provider, clsArr);
    }
}
