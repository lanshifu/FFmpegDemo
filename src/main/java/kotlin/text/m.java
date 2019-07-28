package kotlin.text;

import defpackage.yr;
import kotlin.jvm.internal.f;

/* compiled from: StringsJVM.kt */
class m extends l {
    public static final boolean a(String str, String str2, boolean z) {
        if (str == null) {
            return str2 == null;
        }
        boolean equalsIgnoreCase;
        if (z) {
            equalsIgnoreCase = str.equalsIgnoreCase(str2);
        } else {
            equalsIgnoreCase = str.equals(str2);
        }
        return equalsIgnoreCase;
    }

    public static final String a(String str, String str2, String str3, boolean z) {
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        f.b(str4, "$this$replace");
        f.b(str5, "oldValue");
        f.b(str6, "newValue");
        return yr.a(n.a((CharSequence) str4, new String[]{str5}, z, 0, 4, null), str6, null, null, 0, null, null, 62, null);
    }

    public static /* synthetic */ boolean a(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return b(str, str2, z);
    }

    public static final boolean b(String str, String str2, boolean z) {
        f.b(str, "$this$startsWith");
        f.b(str2, "prefix");
        if (!z) {
            return str.startsWith(str2);
        }
        return a(str, 0, str2, 0, str2.length(), z);
    }

    public static /* synthetic */ boolean b(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return c(str, str2, z);
    }

    public static final boolean c(String str, String str2, boolean z) {
        f.b(str, "$this$endsWith");
        f.b(str2, "suffix");
        if (!z) {
            return str.endsWith(str2);
        }
        return a(str, str.length() - str2.length(), str2, 0, str2.length(), true);
    }

    public static final boolean a(String str, int i, String str2, int i2, int i3, boolean z) {
        f.b(str, "$this$regionMatches");
        f.b(str2, "other");
        if (z) {
            return str.regionMatches(z, i, str2, i2, i3);
        }
        return str.regionMatches(i, str2, i2, i3);
    }
}
