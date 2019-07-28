package kotlin.collections;

import defpackage.xw;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.f;

/* compiled from: _Collections.kt */
class t extends s {
    public static final <T> T d(List<? extends T> list) {
        f.b(list, "$this$first");
        if (!list.isEmpty()) {
            return list.get(0);
        }
        throw new NoSuchElementException("List is empty.");
    }

    public static final <T> T e(List<? extends T> list) {
        f.b(list, "$this$last");
        if (!list.isEmpty()) {
            return list.get(l.a((List) list));
        }
        throw new NoSuchElementException("List is empty.");
    }

    public static final <T> T a(Iterable<? extends T> iterable) {
        f.b(iterable, "$this$single");
        if (iterable instanceof List) {
            return f((List) iterable);
        }
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            if (!it.hasNext()) {
                return next;
            }
            throw new IllegalArgumentException("Collection has more than one element.");
        }
        throw new NoSuchElementException("Collection is empty.");
    }

    public static final <T> T f(List<? extends T> list) {
        f.b(list, "$this$single");
        switch (list.size()) {
            case 0:
                throw new NoSuchElementException("List is empty.");
            case 1:
                return list.get(0);
            default:
                throw new IllegalArgumentException("List has more than one element.");
        }
    }

    public static final <T> List<T> b(Iterable<? extends T> iterable) {
        f.b(iterable, "$this$reversed");
        if ((iterable instanceof Collection) && ((Collection) iterable).size() <= 1) {
            return c(iterable);
        }
        List d = d((Iterable) iterable);
        s.c(d);
        return d;
    }

    public static final <T, C extends Collection<? super T>> C a(Iterable<? extends T> iterable, C c) {
        f.b(iterable, "$this$toCollection");
        f.b(c, "destination");
        for (Object add : iterable) {
            c.add(add);
        }
        return c;
    }

    public static final <T> List<T> c(Iterable<? extends T> iterable) {
        f.b(iterable, "$this$toList");
        if (!(iterable instanceof Collection)) {
            return l.b(d((Iterable) iterable));
        }
        List<T> a;
        Collection collection = (Collection) iterable;
        switch (collection.size()) {
            case 0:
                a = l.a();
                break;
            case 1:
                a = k.a(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
                break;
            default:
                a = a(collection);
                break;
        }
        return a;
    }

    public static final <T> List<T> d(Iterable<? extends T> iterable) {
        f.b(iterable, "$this$toMutableList");
        if (iterable instanceof Collection) {
            return a((Collection) iterable);
        }
        return (List) a(iterable, new ArrayList());
    }

    public static final <T> List<T> a(Collection<? extends T> collection) {
        f.b(collection, "$this$toMutableList");
        return new ArrayList(collection);
    }

    public static final Float e(Iterable<Float> iterable) {
        f.b(iterable, "$this$max");
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        float floatValue = ((Number) it.next()).floatValue();
        if (Float.isNaN(floatValue)) {
            return Float.valueOf(floatValue);
        }
        while (it.hasNext()) {
            float floatValue2 = ((Number) it.next()).floatValue();
            if (Float.isNaN(floatValue2)) {
                return Float.valueOf(floatValue2);
            }
            if (floatValue < floatValue2) {
                floatValue = floatValue2;
            }
        }
        return Float.valueOf(floatValue);
    }

    public static final <T, A extends Appendable> A a(Iterable<? extends T> iterable, A a, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, xw<? super T, ? extends CharSequence> xwVar) {
        f.b(iterable, "$this$joinTo");
        f.b(a, "buffer");
        f.b(charSequence, "separator");
        f.b(charSequence2, "prefix");
        f.b(charSequence3, "postfix");
        f.b(charSequence4, "truncated");
        a.append(charSequence2);
        int i2 = 0;
        for (Object next : iterable) {
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

    public static /* synthetic */ String a(Iterable iterable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, xw xwVar, int i2, Object obj) {
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
        return a(iterable, charSequence, charSequence5, charSequence6, i3, charSequence7, xwVar);
    }

    public static final <T> String a(Iterable<? extends T> iterable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, xw<? super T, ? extends CharSequence> xwVar) {
        f.b(iterable, "$this$joinToString");
        f.b(charSequence, "separator");
        f.b(charSequence2, "prefix");
        f.b(charSequence3, "postfix");
        f.b(charSequence4, "truncated");
        Object stringBuilder = ((StringBuilder) a(iterable, new StringBuilder(), charSequence, charSequence2, charSequence3, i, charSequence4, xwVar)).toString();
        f.a(stringBuilder, "joinTo(StringBuilder(), …ed, transform).toString()");
        return stringBuilder;
    }
}
