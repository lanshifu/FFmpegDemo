package kotlin.collections;

import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.f;

/* compiled from: _CollectionsJvm.kt */
class s extends r {
    public static final <T> void c(List<T> list) {
        f.b(list, "$this$reverse");
        Collections.reverse(list);
    }
}
