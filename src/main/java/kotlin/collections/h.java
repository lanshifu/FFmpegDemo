package kotlin.collections;

import defpackage.yf;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.jvm.internal.f;

/* compiled from: _Arrays.kt */
class h extends g {
    public static final <T> boolean a(T[] tArr, T t) {
        f.b(tArr, "$this$contains");
        return b((Object[]) tArr, (Object) t) >= 0;
    }

    public static final boolean a(byte[] bArr, byte b) {
        f.b(bArr, "$this$contains");
        return b(bArr, b) >= 0;
    }

    public static final <T> int b(T[] tArr, T t) {
        f.b(tArr, "$this$indexOf");
        int i = 0;
        if (t == null) {
            int length = tArr.length;
            while (i < length) {
                if (tArr[i] == null) {
                    return i;
                }
                i++;
            }
        } else {
            int length2 = tArr.length;
            while (i < length2) {
                if (f.a((Object) t, tArr[i])) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    public static final int b(byte[] bArr, byte b) {
        f.b(bArr, "$this$indexOf");
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            if (b == bArr[i]) {
                return i;
            }
        }
        return -1;
    }

    public static final int c(byte[] bArr, byte b) {
        f.b(bArr, "$this$lastIndexOf");
        for (Number intValue : t.b(b(bArr))) {
            int intValue2 = intValue.intValue();
            if (b == bArr[intValue2]) {
                return intValue2;
            }
        }
        return -1;
    }

    public static final char a(char[] cArr) {
        f.b(cArr, "$this$single");
        switch (cArr.length) {
            case 0:
                throw new NoSuchElementException("Array is empty.");
            case 1:
                return cArr[0];
            default:
                throw new IllegalArgumentException("Array has more than one element.");
        }
    }

    public static final List<Byte> a(byte[] bArr, yf yfVar) {
        f.b(bArr, "$this$slice");
        f.b(yfVar, "indices");
        if (yfVar.e()) {
            return l.a();
        }
        return g.a(g.a(bArr, yfVar.f().intValue(), yfVar.g().intValue() + 1));
    }

    public static final yf b(byte[] bArr) {
        f.b(bArr, "$this$indices");
        return new yf(0, c(bArr));
    }

    public static final int c(byte[] bArr) {
        f.b(bArr, "$this$lastIndex");
        return bArr.length - 1;
    }

    public static final <T, C extends Collection<? super T>> C a(T[] tArr, C c) {
        f.b(tArr, "$this$toCollection");
        f.b(c, "destination");
        for (Object add : tArr) {
            c.add(add);
        }
        return c;
    }

    public static final <T> Set<T> b(T[] tArr) {
        f.b(tArr, "$this$toSet");
        switch (tArr.length) {
            case 0:
                return ad.a();
            case 1:
                return ac.a(tArr[0]);
            default:
                return (Set) a((Object[]) tArr, (Collection) new LinkedHashSet(z.a(tArr.length)));
        }
    }
}
