package defpackage;

import android.text.TextUtils;
import com.google.android.exoplayer2.util.d;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: CssParser */
/* renamed from: lk */
final class lk {
    private static final Pattern a = Pattern.compile("\\[voice=\"([^\"]*)\"\\]");
    private final n b = new n();
    private final StringBuilder c = new StringBuilder();

    public ln a(n nVar) {
        this.c.setLength(0);
        int d = nVar.d();
        lk.c(nVar);
        this.b.a(nVar.a, nVar.d());
        this.b.c(d);
        String b = lk.b(this.b, this.c);
        ln lnVar = null;
        if (b == null || !"{".equals(lk.a(this.b, this.c))) {
            return null;
        }
        ln lnVar2 = new ln();
        a(lnVar2, b);
        Object obj = null;
        Object obj2 = null;
        while (obj2 == null) {
            int d2 = this.b.d();
            obj = lk.a(this.b, this.c);
            Object obj3 = (obj == null || "}".equals(obj)) ? 1 : null;
            if (obj3 == null) {
                this.b.c(d2);
                lk.a(this.b, lnVar2, this.c);
            }
            obj2 = obj3;
        }
        if ("}".equals(obj)) {
            lnVar = lnVar2;
        }
        return lnVar;
    }

    private static String b(n nVar, StringBuilder stringBuilder) {
        lk.b(nVar);
        if (nVar.b() < 5) {
            return null;
        }
        if (!"::cue".equals(nVar.e(5))) {
            return null;
        }
        int d = nVar.d();
        String a = lk.a(nVar, stringBuilder);
        if (a == null) {
            return null;
        }
        if ("{".equals(a)) {
            nVar.c(d);
            return "";
        }
        String d2 = "(".equals(a) ? lk.d(nVar) : null;
        String a2 = lk.a(nVar, stringBuilder);
        if (!")".equals(a2) || a2 == null) {
            return null;
        }
        return d2;
    }

    private static String d(n nVar) {
        int d = nVar.d();
        int c = nVar.c();
        Object obj = null;
        while (d < c && obj == null) {
            int i = d + 1;
            obj = ((char) nVar.a[d]) == ')' ? 1 : null;
            d = i;
        }
        return nVar.e((d - 1) - nVar.d()).trim();
    }

    private static void a(n nVar, ln lnVar, StringBuilder stringBuilder) {
        lk.b(nVar);
        String d = lk.d(nVar, stringBuilder);
        if (!"".equals(d) && ":".equals(lk.a(nVar, stringBuilder))) {
            lk.b(nVar);
            String c = lk.c(nVar, stringBuilder);
            if (c != null && !"".equals(c)) {
                int d2 = nVar.d();
                String a = lk.a(nVar, stringBuilder);
                if (!";".equals(a)) {
                    if ("}".equals(a)) {
                        nVar.c(d2);
                    } else {
                        return;
                    }
                }
                if ("color".equals(d)) {
                    lnVar.a(d.b(c));
                } else if ("background-color".equals(d)) {
                    lnVar.b(d.b(c));
                } else if ("text-decoration".equals(d)) {
                    if ("underline".equals(c)) {
                        lnVar.a(true);
                    }
                } else if ("font-family".equals(d)) {
                    lnVar.d(c);
                } else if ("font-weight".equals(d)) {
                    if ("bold".equals(c)) {
                        lnVar.b(true);
                    }
                } else if ("font-style".equals(d) && "italic".equals(c)) {
                    lnVar.c(true);
                }
            }
        }
    }

    static void b(n nVar) {
        while (true) {
            Object obj = 1;
            while (nVar.b() > 0 && obj != null) {
                if (!lk.e(nVar)) {
                    if (!lk.f(nVar)) {
                        obj = null;
                    }
                }
            }
            return;
        }
    }

    static String a(n nVar, StringBuilder stringBuilder) {
        lk.b(nVar);
        if (nVar.b() == 0) {
            return null;
        }
        String d = lk.d(nVar, stringBuilder);
        if (!"".equals(d)) {
            return d;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append((char) nVar.g());
        return stringBuilder.toString();
    }

    private static boolean e(n nVar) {
        switch (lk.a(nVar, nVar.d())) {
            case 9:
            case 10:
            case 12:
            case 13:
            case ' ':
                nVar.d(1);
                return true;
            default:
                return false;
        }
    }

    static void c(n nVar) {
        do {
        } while (!TextUtils.isEmpty(nVar.z()));
    }

    private static char a(n nVar, int i) {
        return (char) nVar.a[i];
    }

    private static String c(n nVar, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder();
        Object obj = null;
        while (obj == null) {
            int d = nVar.d();
            String a = lk.a(nVar, stringBuilder);
            if (a == null) {
                return null;
            }
            if ("}".equals(a) || ";".equals(a)) {
                nVar.c(d);
                obj = 1;
            } else {
                stringBuilder2.append(a);
            }
        }
        return stringBuilder2.toString();
    }

    private static boolean f(n nVar) {
        int d = nVar.d();
        int c = nVar.c();
        byte[] bArr = nVar.a;
        if (d + 2 <= c) {
            int i = d + 1;
            if (bArr[d] == (byte) 47) {
                d = i + 1;
                if (bArr[i] == (byte) 42) {
                    while (true) {
                        i = d + 1;
                        if (i >= c) {
                            nVar.d(c - nVar.d());
                            return true;
                        } else if (((char) bArr[d]) == '*' && ((char) bArr[i]) == '/') {
                            d = i + 1;
                            c = d;
                        } else {
                            d = i;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static String d(n nVar, StringBuilder stringBuilder) {
        int i = 0;
        stringBuilder.setLength(0);
        int d = nVar.d();
        int c = nVar.c();
        while (d < c && i == 0) {
            char c2 = (char) nVar.a[d];
            if ((c2 < 'A' || c2 > 'Z') && ((c2 < 'a' || c2 > 'z') && !((c2 >= '0' && c2 <= '9') || c2 == '#' || c2 == '-' || c2 == '.' || c2 == '_'))) {
                i = 1;
            } else {
                d++;
                stringBuilder.append(c2);
            }
        }
        nVar.d(d - nVar.d());
        return stringBuilder.toString();
    }

    private void a(ln lnVar, String str) {
        if (!"".equals(str)) {
            int indexOf = str.indexOf(91);
            if (indexOf != -1) {
                Matcher matcher = a.matcher(str.substring(indexOf));
                if (matcher.matches()) {
                    lnVar.c(matcher.group(1));
                }
                str = str.substring(0, indexOf);
            }
            String[] a = z.a(str, "\\.");
            String str2 = a[0];
            int indexOf2 = str2.indexOf(35);
            if (indexOf2 != -1) {
                lnVar.b(str2.substring(0, indexOf2));
                lnVar.a(str2.substring(indexOf2 + 1));
            } else {
                lnVar.b(str2);
            }
            if (a.length > 1) {
                lnVar.a((String[]) Arrays.copyOfRange(a, 1, a.length));
            }
        }
    }
}
