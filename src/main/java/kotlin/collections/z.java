package kotlin.collections;

import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;
import kotlin.jvm.internal.f;

/* compiled from: Maps.kt */
class z extends y {
    public static final <K, V> Map<K, V> a(Pair<? extends K, ? extends V>... pairArr) {
        f.b(pairArr, "pairs");
        Map linkedHashMap = new LinkedHashMap(a(pairArr.length));
        a(linkedHashMap, pairArr);
        return linkedHashMap;
    }

    public static final int a(int i) {
        if (i < 3) {
            return i + 1;
        }
        return i < 1073741824 ? i + (i / 3) : Filter.MAX;
    }

    public static final <K, V> void a(Map<? super K, ? super V> map, Pair<? extends K, ? extends V>[] pairArr) {
        f.b(map, "$this$putAll");
        f.b(pairArr, "pairs");
        for (Pair pair : pairArr) {
            map.put(pair.component1(), pair.component2());
        }
    }
}
