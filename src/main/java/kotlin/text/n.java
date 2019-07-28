package kotlin.text;

import defpackage.yd;
import defpackage.yf;
import defpackage.yj;
import defpackage.yl;
import defpackage.yr;
import java.util.Collection;
import kotlin.Pair;
import kotlin.c;
import kotlin.jvm.internal.f;

/* compiled from: Strings.kt */
class n extends m {
    public static final int b(CharSequence charSequence) {
        f.b(charSequence, "$this$lastIndex");
        return charSequence.length() - 1;
    }

    public static final String a(CharSequence charSequence, yf yfVar) {
        f.b(charSequence, "$this$substring");
        f.b(yfVar, "range");
        return charSequence.subSequence(yfVar.f().intValue(), yfVar.g().intValue() + 1).toString();
    }

    public static final boolean a(CharSequence charSequence, int i, CharSequence charSequence2, int i2, int i3, boolean z) {
        f.b(charSequence, "$this$regionMatchesImpl");
        f.b(charSequence2, "other");
        if (i2 < 0 || i < 0 || i > charSequence.length() - i3 || i2 > charSequence2.length() - i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (!c.a(charSequence.charAt(i + i4), charSequence2.charAt(i2 + i4), z)) {
                return false;
            }
        }
        return true;
    }

    public static final int a(CharSequence charSequence, char[] cArr, int i, boolean z) {
        f.b(charSequence, "$this$indexOfAny");
        f.b(cArr, "chars");
        if (!z && cArr.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(h.a(cArr), i);
        }
        i = yj.c(i, 0);
        int b = b(charSequence);
        if (i <= b) {
            while (true) {
                Object obj;
                char charAt = charSequence.charAt(i);
                for (char a : cArr) {
                    if (c.a(a, charAt, z)) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    if (i == b) {
                        break;
                    }
                    i++;
                } else {
                    return i;
                }
            }
        }
        return -1;
    }

    static /* synthetic */ int a(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2, int i3, Object obj) {
        return a(charSequence, charSequence2, i, i2, z, (i3 & 16) != 0 ? false : z2);
    }

    private static final int a(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2) {
        yd a;
        if (z2) {
            a = yj.a(yj.d(i, b(charSequence)), yj.c(i2, 0));
        } else {
            a = new yf(yj.c(i, 0), yj.d(i2, charSequence.length()));
        }
        int c;
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            i = a.a();
            i2 = a.b();
            c = a.c();
            if (c < 0 ? i < i2 : i > i2) {
                while (true) {
                    if (!m.a((String) charSequence2, 0, (String) charSequence, i, charSequence2.length(), z)) {
                        if (i == i2) {
                            break;
                        }
                        i += c;
                    } else {
                        return i;
                    }
                }
            }
        } else {
            i = a.a();
            i2 = a.b();
            c = a.c();
            if (c < 0 ? i < i2 : i > i2) {
                while (true) {
                    if (!a(charSequence2, 0, charSequence, i, charSequence2.length(), z)) {
                        if (i == i2) {
                            break;
                        }
                        i += c;
                    } else {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    private static final Pair<Integer, String> b(CharSequence charSequence, Collection<String> collection, int i, boolean z, boolean z2) {
        Pair<Integer, String> pair = null;
        if (z || collection.size() != 1) {
            yd yfVar = !z2 ? new yf(yj.c(i, 0), charSequence.length()) : yj.a(yj.d(i, b(charSequence)), 0);
            int b;
            int c;
            String str;
            Object obj;
            String str2;
            if (charSequence instanceof String) {
                i = yfVar.a();
                b = yfVar.b();
                c = yfVar.c();
                if (c < 0 ? i < b : i > b) {
                    while (true) {
                        for (Object obj2 : collection) {
                            str = (String) obj2;
                            if (m.a(str, 0, (String) charSequence, i, str.length(), z)) {
                                break;
                            }
                        }
                        obj2 = null;
                        str2 = (String) obj2;
                        if (str2 == null) {
                            if (i == b) {
                                break;
                            }
                            i += c;
                        } else {
                            return c.a(Integer.valueOf(i), str2);
                        }
                    }
                }
            } else {
                i = yfVar.a();
                b = yfVar.b();
                c = yfVar.c();
                if (c < 0 ? i < b : i > b) {
                    while (true) {
                        for (Object obj22 : collection) {
                            str = (String) obj22;
                            if (a((CharSequence) str, 0, charSequence, i, str.length(), z)) {
                                break;
                            }
                        }
                        obj22 = null;
                        str2 = (String) obj22;
                        if (str2 == null) {
                            if (i == b) {
                                break;
                            }
                            i += c;
                        } else {
                            return c.a(Integer.valueOf(i), str2);
                        }
                    }
                }
            }
            return null;
        }
        String str3 = (String) t.a((Iterable) collection);
        int a = !z2 ? a(charSequence, str3, i, false, 4, null) : b(charSequence, str3, i, false, 4, null);
        if (a >= 0) {
            pair = c.a(Integer.valueOf(a), str3);
        }
        return pair;
    }

    public static final int a(CharSequence charSequence, char c, int i, boolean z) {
        f.b(charSequence, "$this$indexOf");
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(c, i);
        }
        return a(charSequence, new char[]{c}, i, z);
    }

    public static final int a(CharSequence charSequence, String str, int i, boolean z) {
        f.b(charSequence, "$this$indexOf");
        f.b(str, "string");
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(str, i);
        }
        return a(charSequence, str, i, charSequence.length(), z, false, 16, null);
    }

    public static /* synthetic */ int b(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = b(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return b(charSequence, str, i, z);
    }

    public static final int b(CharSequence charSequence, String str, int i, boolean z) {
        f.b(charSequence, "$this$lastIndexOf");
        f.b(str, "string");
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).lastIndexOf(str, i);
        }
        return a(charSequence, (CharSequence) str, i, 0, z, true);
    }

    public static final boolean a(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        f.b(charSequence, "$this$contains");
        f.b(charSequence2, "other");
        if (charSequence2 instanceof String) {
            if (a(charSequence, (String) charSequence2, 0, z, 2, null) < 0) {
                return false;
            }
        }
        if (a(charSequence, charSequence2, 0, charSequence.length(), z, false, 16, null) < 0) {
            return false;
        }
        return true;
    }

    static /* synthetic */ yl a(CharSequence charSequence, String[] strArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return a(charSequence, strArr, i, z, i2);
    }

    private static final yl<yf> a(CharSequence charSequence, String[] strArr, int i, boolean z, int i2) {
        if ((i2 >= 0 ? 1 : null) != null) {
            return new d(charSequence, i, i2, new StringsKt__StringsKt$rangesDelimitedBy$4(g.a((Object[]) strArr), z));
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Limit must be non-negative, but was ");
        stringBuilder.append(i2);
        stringBuilder.append('.');
        throw new IllegalArgumentException(stringBuilder.toString().toString());
    }

    public static final yl<String> a(CharSequence charSequence, String[] strArr, boolean z, int i) {
        f.b(charSequence, "$this$splitToSequence");
        f.b(strArr, "delimiters");
        return yr.a(a(charSequence, strArr, 0, z, i, 2, null), new StringsKt__StringsKt$splitToSequence$1(charSequence));
    }

    public static final CharSequence a(CharSequence charSequence) {
        f.b(charSequence, "$this$trim");
        int length = charSequence.length() - 1;
        int i = 0;
        Object obj = null;
        while (i <= length) {
            boolean a = b.a(charSequence.charAt(obj == null ? i : length));
            if (obj == null) {
                if (a) {
                    i++;
                } else {
                    obj = 1;
                }
            } else if (!a) {
                break;
            } else {
                length--;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }
}
