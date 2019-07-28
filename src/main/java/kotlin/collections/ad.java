package kotlin.collections;

import java.util.Set;
import kotlin.jvm.internal.f;

/* compiled from: Sets.kt */
class ad extends ac {
    public static final <T> Set<T> a() {
        return EmptySet.INSTANCE;
    }

    public static final <T> Set<T> a(T... tArr) {
        f.b(tArr, "elements");
        return tArr.length > 0 ? h.b((Object[]) tArr) : a();
    }
}
