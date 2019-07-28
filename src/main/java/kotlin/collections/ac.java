package kotlin.collections;

import java.util.Collections;
import java.util.Set;
import kotlin.jvm.internal.f;

/* compiled from: SetsJVM.kt */
class ac {
    public static final <T> Set<T> a(T t) {
        Object singleton = Collections.singleton(t);
        f.a(singleton, "java.util.Collections.singleton(element)");
        return singleton;
    }
}
