package kotlin.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.f;

/* compiled from: CollectionsJVM.kt */
class k {
    public static final <T> List<T> a(T t) {
        Object singletonList = Collections.singletonList(t);
        f.a(singletonList, "java.util.Collections.singletonList(element)");
        return singletonList;
    }

    public static final <T> Object[] a(T[] tArr, boolean z) {
        f.b(tArr, "$this$copyToArrayOfAny");
        if (z && f.a(tArr.getClass(), (Object) Object[].class)) {
            return tArr;
        }
        Object copyOf = Arrays.copyOf(tArr, tArr.length, Object[].class);
        f.a(copyOf, "java.util.Arrays.copyOf(… Array<Any?>::class.java)");
        return copyOf;
    }
}
