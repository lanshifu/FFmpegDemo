package kotlin.text;

import defpackage.xw;
import kotlin.jvm.internal.f;

/* compiled from: StringBuilder.kt */
class j extends i {
    public static final <T> void a(Appendable appendable, T t, xw<? super T, ? extends CharSequence> xwVar) {
        f.b(appendable, "$this$appendElement");
        if (xwVar != null) {
            appendable.append((CharSequence) xwVar.invoke(t));
            return;
        }
        if (t != null ? t instanceof CharSequence : true) {
            appendable.append((CharSequence) t);
        } else if (t instanceof Character) {
            appendable.append(((Character) t).charValue());
        } else {
            appendable.append(String.valueOf(t));
        }
    }
}
