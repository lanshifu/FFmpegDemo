package kotlin.text;

import defpackage.xx;
import java.util.List;
import kotlin.Pair;
import kotlin.c;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.f;

/* compiled from: Strings.kt */
final class StringsKt__StringsKt$rangesDelimitedBy$4 extends Lambda implements xx<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>> {
    final /* synthetic */ List $delimitersList;
    final /* synthetic */ boolean $ignoreCase;

    StringsKt__StringsKt$rangesDelimitedBy$4(List list, boolean z) {
        this.$delimitersList = list;
        this.$ignoreCase = z;
        super(2);
    }

    public final Pair<Integer, Integer> invoke(CharSequence charSequence, int i) {
        f.b(charSequence, "$receiver");
        Pair a = n.b(charSequence, this.$delimitersList, i, this.$ignoreCase, false);
        return a != null ? c.a(a.getFirst(), Integer.valueOf(((String) a.getSecond()).length())) : null;
    }
}
