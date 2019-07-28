package kotlin.collections;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.f;

/* compiled from: Collections.kt */
class l extends k {
    public static final <T> List<T> a() {
        return EmptyList.INSTANCE;
    }

    public static final <T> List<T> a(T... tArr) {
        f.b(tArr, "elements");
        return tArr.length == 0 ? new ArrayList() : new ArrayList(new c(tArr, true));
    }

    public static final <T> ArrayList<T> b(T... tArr) {
        f.b(tArr, "elements");
        return tArr.length == 0 ? new ArrayList() : new ArrayList(new c(tArr, true));
    }

    public static final <T> int a(List<? extends T> list) {
        f.b(list, "$this$lastIndex");
        return list.size() - 1;
    }

    public static final <T> List<T> b(List<? extends T> list) {
        f.b(list, "$this$optimizeReadOnlyList");
        switch (list.size()) {
            case 0:
                return a();
            case 1:
                return k.a(list.get(0));
            default:
                return list;
        }
    }

    public static final void b() {
        throw new ArithmeticException("Index overflow has happened.");
    }
}
