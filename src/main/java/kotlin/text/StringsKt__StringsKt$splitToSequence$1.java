package kotlin.text;

import defpackage.xw;
import defpackage.yf;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.f;

/* compiled from: Strings.kt */
final class StringsKt__StringsKt$splitToSequence$1 extends Lambda implements xw<yf, String> {
    final /* synthetic */ CharSequence $this_splitToSequence;

    StringsKt__StringsKt$splitToSequence$1(CharSequence charSequence) {
        this.$this_splitToSequence = charSequence;
        super(1);
    }

    public final String invoke(yf yfVar) {
        f.b(yfVar, "it");
        return n.a(this.$this_splitToSequence, yfVar);
    }
}
