package defpackage;

import java.util.Iterator;
import kotlin.jvm.internal.f;

/* compiled from: _Sequences.kt */
/* renamed from: yr */
class yr extends yq {
    public static final <T, R> yl<R> a(yl<? extends T> ylVar, xw<? super T, ? extends R> xwVar) {
        f.b(ylVar, "$this$map");
        f.b(xwVar, "transform");
        return new ys(ylVar, xwVar);
    }

    public static final <T, A extends Appendable> A a(yl<? extends T> ylVar, A a, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, xw<? super T, ? extends CharSequence> xwVar) {
        f.b(ylVar, "$this$joinTo");
        f.b(a, "buffer");
        f.b(charSequence, "separator");
        f.b(charSequence2, "prefix");
        f.b(charSequence3, "postfix");
        f.b(charSequence4, "truncated");
        a.append(charSequence2);
        Iterator a2 = ylVar.a();
        int i2 = 0;
        while (a2.hasNext()) {
            Object next = a2.next();
            i2++;
            if (i2 > 1) {
                a.append(charSequence);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            j.a(a, next, xwVar);
        }
        if (i >= 0 && r5 > i) {
            a.append(charSequence4);
        }
        a.append(charSequence3);
        return a;
    }

    public static /* synthetic */ String a(yl ylVar, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, xw xwVar, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charSequence = ", ";
        }
        if ((i2 & 2) != 0) {
            charSequence2 = "";
        }
        CharSequence charSequence5 = charSequence2;
        if ((i2 & 4) != 0) {
            charSequence3 = "";
        }
        CharSequence charSequence6 = charSequence3;
        int i3 = (i2 & 8) != 0 ? -1 : i;
        if ((i2 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence7 = charSequence4;
        if ((i2 & 32) != 0) {
            xwVar = (xw) null;
        }
        return yr.a(ylVar, charSequence, charSequence5, charSequence6, i3, charSequence7, xwVar);
    }

    public static final <T> String a(yl<? extends T> ylVar, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, xw<? super T, ? extends CharSequence> xwVar) {
        f.b(ylVar, "$this$joinToString");
        f.b(charSequence, "separator");
        f.b(charSequence2, "prefix");
        f.b(charSequence3, "postfix");
        f.b(charSequence4, "truncated");
        Object stringBuilder = ((StringBuilder) yr.a(ylVar, new StringBuilder(), charSequence, charSequence2, charSequence3, i, charSequence4, xwVar)).toString();
        f.a(stringBuilder, "joinTo(StringBuilder(), …ed, transform).toString()");
        return stringBuilder;
    }
}
