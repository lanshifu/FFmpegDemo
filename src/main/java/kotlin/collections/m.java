package kotlin.collections;

import java.util.Collection;
import kotlin.jvm.internal.f;

/* compiled from: Iterables.kt */
class m extends l {
    public static final <T> int a(Iterable<? extends T> iterable, int i) {
        f.b(iterable, "$this$collectionSizeOrDefault");
        return iterable instanceof Collection ? ((Collection) iterable).size() : i;
    }
}
