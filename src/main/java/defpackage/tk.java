package defpackage;

import android.text.SpannableStringBuilder;
import android.util.Pair;
import android.util.SparseArray;
import com.zzhoujay.markdown.style.b;
import defpackage.tf.a;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: TagHandlerImpl */
/* renamed from: tk */
public class tk implements tj {
    private static final Matcher A = Pattern.compile("\\S*(\\s+)\\S+").matcher("");
    private static final Matcher B = Pattern.compile("^( {4}|\\t)(.*)").matcher("");
    private static final Matcher C = Pattern.compile("^\\s*```").matcher("");
    private static final Matcher D = Pattern.compile("^\\s*$").matcher("");
    private static final Matcher E = Pattern.compile("^\\s*([-*]\\s*){3,}$").matcher("");
    private static final SparseArray<Matcher> F = new SparseArray();
    private static final Matcher a = Pattern.compile("^\\s*=+\\s*$").matcher("");
    private static final Matcher b = Pattern.compile("^\\s*-+\\s*$").matcher("");
    private static final Matcher c = Pattern.compile("^\\s*#{1,6}\\s*([^#]*)(\\s*#)?").matcher("");
    private static final Matcher d = Pattern.compile("^\\s*#\\s*([^#]*)(\\s*#)?").matcher("");
    private static final Matcher e = Pattern.compile("^\\s*#{2}\\s*([^#]*)(\\s*#)?").matcher("");
    private static final Matcher f = Pattern.compile("^\\s*#{3}\\s*([^#]*)(\\s*#)?").matcher("");
    private static final Matcher g = Pattern.compile("^\\s*#{4}\\s*([^#]*)(\\s*#)?").matcher("");
    private static final Matcher h = Pattern.compile("^\\s*#{5}\\s*([^#]*)(\\s*#)?").matcher("");
    private static final Matcher i = Pattern.compile("^\\s*#{6}\\s*([^#]*)(\\s*#)?").matcher("");
    private static final Matcher j = Pattern.compile("^\\s{0,3}>\\s(.*)").matcher("");
    private static final Matcher k = Pattern.compile("^\\s*[*+-]\\s+(.*)").matcher("");
    private static final Matcher l = Pattern.compile("^\\s*\\d+\\.\\s+(.*)").matcher("");
    private static final Matcher m = Pattern.compile("[^*_]*(([*_])([^*_].*?)\\2)").matcher("");
    private static final Matcher n = Pattern.compile("[^*_]*(([*_])\\2([^*_].*?)\\2\\2)").matcher("");
    private static final Matcher o = Pattern.compile("[^*_]*(([*_])\\2\\2([^*_].*?)\\2\\2\\2)").matcher("");
    private static final Matcher p = Pattern.compile("[^~]*((~{2,4})([^~].*?)\\2)").matcher("");
    private static final Matcher q = Pattern.compile("[^`]*((`+)([^`].*?)\\2)").matcher("");
    private static final Matcher r = Pattern.compile(".*?(\\[\\s*(.*?)\\s*]\\(\\s*(\\S*?)(\\s+(['\"])(.*?)\\5)?\\s*?\\))").matcher("");
    private static final Matcher s = Pattern.compile(".*?(!\\[\\s*(.*?)\\s*]\\(\\s*(\\S*?)(\\s+(['\"])(.*?)\\5)?\\s*?\\))").matcher("");
    private static final Matcher t = Pattern.compile(".*?(\\[\\s*(.*?)\\s*]\\s*\\[\\s*(.*?)\\s*])").matcher("");
    private static final Matcher u = Pattern.compile("^\\s*\\[\\s*(.*?)\\s*]:\\s*(\\S+?)(\\s+(['\"])(.*?)\\4)?\\s*$").matcher("");
    private static final Matcher v = Pattern.compile(".*?(!\\[\\s*(.*?)\\s*]\\s*\\[\\s*(.*?)\\s*])").matcher("");
    private static final Matcher w = Pattern.compile("^\\s*!\\[\\s*(.*?)\\s*]:\\s*(\\S+?)(\\s+(['\"])(.*?)\\4)?\\s*$").matcher("");
    private static final Matcher x = Pattern.compile(".*?(<(\\S+@\\S+\\.\\S+)>).*?").matcher("");
    private static final Matcher y = Pattern.compile("((https|http|ftp|rtsp|mms)?://)[^\\s]+").matcher("");
    private static final Matcher z = Pattern.compile("(.*?) {2} *$").matcher("");
    private tg G;
    private a H;
    private HashMap<String, Pair<String, String>> I = new HashMap();
    private HashMap<String, Pair<String, String>> J = new HashMap();

    static {
        F.put(1, B);
        F.put(2, C);
        F.put(3, d);
        F.put(4, e);
        F.put(24, f);
        F.put(5, g);
        F.put(6, h);
        F.put(7, i);
        F.put(23, c);
        F.put(8, j);
        F.put(9, k);
        F.put(10, l);
        F.put(11, n);
        F.put(12, m);
        F.put(13, o);
        F.put(14, x);
        F.put(15, y);
        F.put(16, p);
        F.put(17, r);
        F.put(18, t);
        F.put(19, u);
        F.put(20, s);
        F.put(21, v);
        F.put(22, w);
        F.put(25, D);
        F.put(26, z);
        F.put(27, E);
        F.put(28, a);
        F.put(29, b);
        F.put(30, q);
    }

    public tk(tg tgVar) {
        this.G = tgVar;
    }

    public boolean a(td tdVar) {
        return n(tdVar) || m(tdVar) || l(tdVar) || k(tdVar) || j(tdVar) || i(tdVar);
    }

    public boolean i(td tdVar) {
        Matcher a = a(3, tdVar.a());
        if (a == null || !a.find()) {
            return false;
        }
        tdVar.a(4);
        tdVar.a(SpannableStringBuilder.valueOf(a.group(1)));
        f(tdVar);
        tdVar.a(this.G.g(tdVar.b()));
        return true;
    }

    public boolean j(td tdVar) {
        Matcher a = a(4, tdVar.a());
        if (!a.find()) {
            return false;
        }
        tdVar.a(5);
        tdVar.a(SpannableStringBuilder.valueOf(a.group(1)));
        f(tdVar);
        tdVar.a(this.G.h(tdVar.b()));
        return true;
    }

    public boolean k(td tdVar) {
        Matcher a = a(24, tdVar.a());
        if (!a.find()) {
            return false;
        }
        tdVar.a(6);
        tdVar.a(SpannableStringBuilder.valueOf(a.group(1)));
        f(tdVar);
        tdVar.a(this.G.i(tdVar.b()));
        return true;
    }

    public boolean l(td tdVar) {
        Matcher a = a(5, tdVar.a());
        if (!a.find()) {
            return false;
        }
        tdVar.a(7);
        tdVar.a(SpannableStringBuilder.valueOf(a.group(1)));
        f(tdVar);
        tdVar.a(this.G.j(tdVar.b()));
        return true;
    }

    public boolean m(td tdVar) {
        Matcher a = a(6, tdVar.a());
        if (!a.find()) {
            return false;
        }
        tdVar.a(8);
        tdVar.a(SpannableStringBuilder.valueOf(a.group(1)));
        f(tdVar);
        tdVar.a(this.G.k(tdVar.b()));
        return true;
    }

    public boolean n(td tdVar) {
        Matcher a = a(7, tdVar.a());
        if (!a.find()) {
            return false;
        }
        tdVar.a(9);
        tdVar.a(SpannableStringBuilder.valueOf(a.group(1)));
        f(tdVar);
        tdVar.a(this.G.l(tdVar.b()));
        return true;
    }

    public boolean b(td tdVar) {
        te a = this.H.a();
        tdVar = tdVar.h();
        Matcher a2 = a(8, tdVar.a());
        if (!a2.find()) {
            return false;
        }
        tdVar.a(1);
        td b = tdVar.b(a2.group(1));
        tdVar.p();
        tdVar.q();
        td b2 = a.b();
        if (tdVar.l() == null && b2 != null && b2.c() == 1) {
            CharSequence spannableStringBuilder = new SpannableStringBuilder(" ");
            this.G.m(spannableStringBuilder);
            while (b2.k() != null && b2.k().c() == 1) {
                b2 = b2.k();
                this.G.m(spannableStringBuilder);
            }
            b2.s();
            a.b().a(spannableStringBuilder);
        }
        if (!b(b) && !c(b) && !d(b) && !a(b)) {
            b.a(SpannableStringBuilder.valueOf(b.a()));
            f(b);
        } else if (b.e() == 1) {
            if (tdVar.l() != null) {
                tdVar.d(b.f());
                tdVar.a(b.b());
                tdVar.e(b.g());
                tdVar.b(b.d());
                tdVar.c(1);
            } else if (b.f() == 2) {
                tdVar.a(this.G.b(b.b(), a(8, tdVar, 1) - 1, b.g()));
            } else {
                tdVar.a(this.G.a(b.b(), a(8, tdVar, 1) - 1, b.g(), b.d()));
            }
            return true;
        }
        tdVar.a(this.G.m(b.b()));
        return true;
    }

    public boolean c(td tdVar) {
        return a(tdVar, 0);
    }

    private boolean a(td tdVar, int i) {
        Matcher a = a(9, tdVar.a());
        int i2 = 0;
        if (!a.find()) {
            return false;
        }
        tdVar.a(2);
        td b = tdVar.b(a.group(1));
        tdVar.e(0);
        td l = tdVar.l();
        te a2 = this.H.a();
        td j = tdVar.j();
        if (a2.c().c() == 1) {
            i2 = 1;
        }
        if (i2 != 0) {
            tdVar.c(1);
            tdVar.d(2);
        }
        if (j != null && (j.c() == 3 || j.c() == 2)) {
            if (i > 0) {
                tdVar.e(i);
            } else {
                String replaceAll = tdVar.a().substring(a.start(), a.start(1) - 2).replaceAll("\\t", "    ");
                if (replaceAll.length() > (j.g() * 2) + 1) {
                    tdVar.e(j.g() + 1);
                } else {
                    tdVar.e(replaceAll.length() / 2);
                }
            }
        }
        if (i2 != 0) {
            tdVar.a((CharSequence) " ");
        } else {
            tdVar.a(this.G.a(" ", tdVar.g()));
        }
        if (a(9, b)) {
            i = tdVar.g() + 1;
            b.r();
            if (l != null) {
                tdVar = l.s();
                tdVar.c(b);
                a2.d();
                a(b, i);
                if (i2 != 0) {
                    while (tdVar.l() != null) {
                        tdVar = tdVar.l();
                    }
                    tdVar.a(this.G.b(b.b(), a(8, tdVar, 1) - 1, b.g()));
                } else {
                    while (tdVar != null && tdVar.c() == 1) {
                        tdVar.a(this.G.m(b.b()));
                        tdVar = tdVar.l();
                    }
                }
            } else {
                tdVar.a(b);
                a2.d();
                a(a2.c(), i);
            }
            return true;
        } else if (a(10, b)) {
            i = tdVar.g() + 1;
            b.r();
            if (l != null) {
                tdVar = l.s();
                tdVar.c(b);
                a2.d();
                b(b, i);
                if (i2 != 0) {
                    while (tdVar.l() != null) {
                        tdVar = tdVar.l();
                    }
                    tdVar.a(this.G.a(b.b(), a(8, tdVar, 1) - 1, b.g(), b.d()));
                } else {
                    while (tdVar != null && tdVar.c() == 1) {
                        tdVar.a(this.G.m(b.b()));
                        tdVar = tdVar.l();
                    }
                }
            } else {
                tdVar.a(b);
                a2.d();
                b(a2.c(), i);
            }
            return true;
        } else {
            CharSequence b2;
            if (a(b)) {
                b2 = b.b();
            } else {
                b2 = b.a();
            }
            if (b2 instanceof SpannableStringBuilder) {
                b2 = (SpannableStringBuilder) b2;
            } else {
                b2 = new SpannableStringBuilder(b2);
            }
            tdVar.a(b2);
            f(tdVar);
            if (i2 == 0) {
                tdVar.a(this.G.a(tdVar.b(), tdVar.g()));
            }
            return true;
        }
    }

    public boolean d(td tdVar) {
        return b(tdVar, 0);
    }

    private boolean b(td tdVar, int i) {
        Matcher a = a(10, tdVar.a());
        int i2 = 0;
        if (!a.find()) {
            return false;
        }
        tdVar.a(3);
        td tdVar2 = new td(a.group(1));
        tdVar.e(0);
        td l = tdVar.l();
        te a2 = this.H.a();
        td j = tdVar.j();
        if (a2.c().c() == 1) {
            i2 = 1;
        }
        if (i2 != 0) {
            tdVar.c(1);
            tdVar.d(3);
        }
        if (j != null && (j.c() == 3 || j.c() == 2)) {
            if (i > 0) {
                tdVar.e(i);
            } else {
                String replaceAll = tdVar.a().substring(a.start(), a.start(1) - 2).replaceAll("\\t", "    ");
                if (replaceAll.length() > (j.g() * 2) + 1) {
                    tdVar.e(j.g() + 1);
                } else {
                    tdVar.e(replaceAll.length() / 2);
                }
            }
        }
        if (j != null && j.c() == 3 && j.g() == tdVar.g()) {
            tdVar.b(j.d() + 1);
        } else {
            tdVar.b(1);
        }
        if (i2 != 0) {
            tdVar.a((CharSequence) " ");
        } else {
            tdVar.a(this.G.a((CharSequence) " ", tdVar.g(), tdVar.d()));
        }
        if (a(9, tdVar2)) {
            i = tdVar.g() + 1;
            tdVar2.r();
            if (l != null) {
                tdVar = l.s();
                tdVar.c(tdVar2);
                a2.d();
                a(tdVar2, i);
                if (i2 != 0) {
                    while (tdVar.l() != null) {
                        tdVar = tdVar.l();
                    }
                    tdVar.a(this.G.b(tdVar2.b(), a(8, tdVar, 1) - 1, tdVar2.g()));
                } else {
                    while (tdVar != null && tdVar.c() == 1) {
                        tdVar.a(this.G.m(tdVar2.b()));
                        tdVar = tdVar.l();
                    }
                }
            } else {
                tdVar.a(tdVar2);
                a2.d();
                a(a2.c(), i);
            }
            return true;
        } else if (a(10, tdVar2)) {
            i = tdVar.g() + 1;
            tdVar2.r();
            if (l != null) {
                tdVar = l.s();
                tdVar.c(tdVar2);
                a2.d();
                b(tdVar2, i);
                if (i2 != 0) {
                    while (tdVar.l() != null) {
                        tdVar = tdVar.l();
                    }
                    tdVar.a(this.G.a(tdVar2.b(), a(8, tdVar, 1) - 1, tdVar2.g(), tdVar2.d()));
                } else {
                    while (tdVar != null && tdVar.c() == 1) {
                        tdVar.a(this.G.m(tdVar2.b()));
                        tdVar = tdVar.l();
                    }
                }
            } else {
                tdVar.a(tdVar2);
                a2.d();
                b(a2.c(), i);
            }
            return true;
        } else {
            CharSequence b;
            if (a(tdVar2)) {
                b = tdVar2.b();
            } else {
                b = tdVar2.a();
            }
            if (b instanceof SpannableStringBuilder) {
                b = (SpannableStringBuilder) b;
            } else {
                b = new SpannableStringBuilder(b);
            }
            tdVar.a(b);
            f(tdVar);
            if (i2 == 0) {
                tdVar.a(this.G.a(tdVar.b(), tdVar.g(), tdVar.d()));
            }
            return true;
        }
    }

    public boolean e(td tdVar) {
        tdVar = tdVar.h();
        if (!a(27, tdVar.a()).matches()) {
            return false;
        }
        tdVar.a(12);
        tdVar.a(this.G.a());
        return true;
    }

    public boolean o(td tdVar) {
        tdVar = tdVar.h();
        SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) tdVar.b();
        Matcher a = a(11, (CharSequence) spannableStringBuilder);
        while (a.find()) {
            if (!a(spannableStringBuilder, a.start(1), a.end(1))) {
                CharSequence charSequence = (SpannableStringBuilder) spannableStringBuilder.subSequence(a.start(3), a.end(3));
                spannableStringBuilder.delete(a.start(1), a.end(1));
                spannableStringBuilder.insert(a.start(1), this.G.a(charSequence));
                o(tdVar);
                return true;
            }
        }
        return false;
    }

    public boolean p(td tdVar) {
        tdVar = tdVar.h();
        SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) tdVar.b();
        Matcher a = a(12, (CharSequence) spannableStringBuilder);
        while (a.find()) {
            if (!a(spannableStringBuilder, a.start(1), a.end(1))) {
                SpannableStringBuilder spannableStringBuilder2 = (SpannableStringBuilder) spannableStringBuilder.subSequence(a.start(3), a.end(3));
                spannableStringBuilder.delete(a.start(1), a.end(1));
                spannableStringBuilder.insert(a.start(1), this.G.b(spannableStringBuilder2));
                p(tdVar);
                return true;
            }
        }
        return false;
    }

    public boolean q(td tdVar) {
        tdVar = tdVar.h();
        SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) tdVar.b();
        Matcher a = a(13, (CharSequence) spannableStringBuilder);
        while (a.find()) {
            if (!a(spannableStringBuilder, a.start(1), a.end(1))) {
                SpannableStringBuilder spannableStringBuilder2 = (SpannableStringBuilder) spannableStringBuilder.subSequence(a.start(3), a.end(3));
                spannableStringBuilder.delete(a.start(1), a.end(1));
                spannableStringBuilder.insert(a.start(1), this.G.c(spannableStringBuilder2));
                q(tdVar);
                return true;
            }
        }
        return false;
    }

    public boolean r(td tdVar) {
        tdVar = tdVar.h();
        CharSequence charSequence = (SpannableStringBuilder) tdVar.b();
        Matcher a = a(30, charSequence);
        if (!a.find()) {
            return false;
        }
        String group = a.group(3);
        charSequence.delete(a.start(1), a.end(1));
        charSequence.insert(a.start(1), this.G.f(group));
        r(tdVar);
        return true;
    }

    public boolean s(td tdVar) {
        tdVar = tdVar.h();
        CharSequence charSequence = (SpannableStringBuilder) tdVar.b();
        Matcher a = a(14, charSequence);
        if (!a.find()) {
            return false;
        }
        SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) charSequence.subSequence(a.start(2), a.end(2));
        charSequence.delete(a.start(1), a.end(1));
        charSequence.insert(a.start(1), this.G.e(spannableStringBuilder));
        s(tdVar);
        return true;
    }

    public boolean t(td tdVar) {
        tdVar = tdVar.h();
        SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) tdVar.b();
        Matcher a = a(16, (CharSequence) spannableStringBuilder);
        while (a.find()) {
            if (!a(spannableStringBuilder, a.start(1), a.end(1))) {
                SpannableStringBuilder spannableStringBuilder2 = (SpannableStringBuilder) spannableStringBuilder.subSequence(a.start(3), a.end(3));
                spannableStringBuilder.delete(a.start(1), a.end(1));
                spannableStringBuilder.insert(a.start(1), this.G.d(spannableStringBuilder2));
                t(tdVar);
                return true;
            }
        }
        return false;
    }

    public boolean u(td tdVar) {
        CharSequence charSequence = (SpannableStringBuilder) tdVar.h().b();
        Matcher a = a(15, charSequence);
        boolean z = false;
        while (a.find()) {
            CharSequence group = a.group();
            charSequence.delete(a.start(), a.end());
            charSequence.insert(a.start(), this.G.a(group, (String) group, ""));
            z = true;
        }
        return z;
    }

    public boolean v(td tdVar) {
        tdVar = tdVar.h();
        CharSequence charSequence = (SpannableStringBuilder) tdVar.b();
        Matcher a = a(17, charSequence);
        if (!a.find()) {
            return false;
        }
        CharSequence group = a.group(2);
        String group2 = a.group(3);
        String group3 = a.group(6);
        charSequence.delete(a.start(1), a.end(1));
        charSequence.insert(a.start(1), this.G.a(group, group2, group3));
        v(tdVar);
        return true;
    }

    public boolean w(td tdVar) {
        tdVar = tdVar.h();
        CharSequence charSequence = (SpannableStringBuilder) tdVar.b();
        Matcher a = a(18, charSequence);
        if (!a.find()) {
            return false;
        }
        CharSequence group = a.group(2);
        Pair pair = (Pair) this.I.get(a.group(3));
        if (pair == null) {
            return false;
        }
        charSequence.delete(a.start(1), a.end(1));
        charSequence.insert(a.start(1), this.G.a(group, (String) pair.first, (String) pair.second));
        w(tdVar);
        return true;
    }

    public boolean a(String str) {
        Matcher a = a(19, (CharSequence) str);
        if (!a.find()) {
            return false;
        }
        this.I.put(a.group(1), new Pair(a.group(2), a.group(5)));
        return true;
    }

    public boolean x(td tdVar) {
        tdVar = tdVar.h();
        CharSequence charSequence = (SpannableStringBuilder) tdVar.b();
        Matcher a = a(20, charSequence);
        if (!a.find()) {
            return false;
        }
        CharSequence group = a.group(2);
        String group2 = a.group(3);
        String group3 = a.group(6);
        charSequence.delete(a.start(1), a.end(1));
        charSequence.insert(a.start(1), this.G.b(group, group2, group3));
        x(tdVar);
        return true;
    }

    public boolean y(td tdVar) {
        tdVar = tdVar.h();
        CharSequence charSequence = (SpannableStringBuilder) tdVar.b();
        Matcher a = a(21, charSequence);
        if (!a.find()) {
            return false;
        }
        CharSequence group = a.group(2);
        Pair pair = (Pair) this.J.get(a.group(3));
        if (pair == null) {
            return false;
        }
        charSequence.delete(a.start(1), a.end(1));
        charSequence.insert(a.start(1), this.G.b(group, (String) pair.first, (String) pair.second));
        y(tdVar);
        return true;
    }

    public boolean b(String str) {
        Matcher a = a(22, (CharSequence) str);
        if (!a.find()) {
            return false;
        }
        this.J.put(a.group(1), new Pair(a.group(2), a.group(5)));
        return true;
    }

    public boolean g(td tdVar) {
        Matcher a = a(1, tdVar.a());
        if (!a.find()) {
            return false;
        }
        String group = a.group(2);
        te a2 = this.H.a();
        StringBuilder stringBuilder = new StringBuilder(group);
        StringBuilder stringBuilder2 = new StringBuilder();
        for (td a3 = a2.a(); a3 != null; a3 = a2.a()) {
            CharSequence b = b(1, a3, 2);
            if (b == null) {
                if (!a(25, a3)) {
                    break;
                }
                stringBuilder2.append(' ');
                stringBuilder2.append(10);
            } else if (stringBuilder2.length() != 0) {
                stringBuilder.append(stringBuilder2);
                stringBuilder.append(b);
                stringBuilder2.delete(0, stringBuilder.length());
            } else {
                stringBuilder.append(10);
                stringBuilder.append(b);
            }
            a2.f();
        }
        tdVar.a(11);
        tdVar.a(this.G.a(stringBuilder.toString()));
        return true;
    }

    public boolean h(td tdVar) {
        if (!a(2, tdVar)) {
            return false;
        }
        Object obj;
        te a = this.H.a();
        te h = a.h();
        while (h.a() != null) {
            if (a(2, h.a())) {
                h.d();
                b(h);
                a(a);
                obj = 1;
                break;
            }
            h.d();
        }
        obj = null;
        if (obj == null) {
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder();
        a.d();
        a.g();
        while (a.c() != h.c()) {
            stringBuilder.append(a.c().a());
            stringBuilder.append(10);
            a.d();
            a.g();
        }
        a(h);
        h.c().a(10);
        h.c().a(this.G.a(stringBuilder.toString()));
        return true;
    }

    public boolean f(td tdVar) {
        Object obj = (q(tdVar) || r(tdVar)) ? 1 : null;
        obj = (o(tdVar) || obj != null) ? 1 : null;
        obj = (p(tdVar) || obj != null) ? 1 : null;
        obj = (t(tdVar) || obj != null) ? 1 : null;
        obj = (s(tdVar) || obj != null) ? 1 : null;
        obj = (x(tdVar) || obj != null) ? 1 : null;
        obj = (y(tdVar) || obj != null) ? 1 : null;
        obj = (v(tdVar) || obj != null) ? 1 : null;
        obj = (w(tdVar) || obj != null) ? 1 : null;
        if (u(tdVar) || obj != null) {
            return true;
        }
        return false;
    }

    public boolean a(int i, td tdVar) {
        return tdVar != null && a(i, tdVar.a());
    }

    public boolean a(int i, String str) {
        boolean z = false;
        if (str == null) {
            return false;
        }
        Matcher a = a(i, (CharSequence) str);
        if (a != null && a.find()) {
            z = true;
        }
        return z;
    }

    public int a(int i, td tdVar, int i2) {
        return tdVar == null ? 0 : a(i, tdVar.a(), i2);
    }

    public int a(int i, String str, int i2) {
        if (str == null) {
            return 0;
        }
        Matcher a = a(i, (CharSequence) str);
        if (a != null && a.find()) {
            return a(i, a.group(i2), i2) + 1;
        }
        return 0;
    }

    private boolean a(SpannableStringBuilder spannableStringBuilder, int i, int i2) {
        for (Object obj : (b[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), b.class)) {
            int spanStart = spannableStringBuilder.getSpanStart(obj);
            int spanEnd = spannableStringBuilder.getSpanEnd(obj);
            if (spanStart < i2 && spanEnd > i) {
                return true;
            }
        }
        return false;
    }

    private Matcher a(int i, CharSequence charSequence) {
        Matcher matcher = (Matcher) F.get(i, null);
        if (matcher != null) {
            matcher.reset(charSequence);
        }
        return matcher;
    }

    public void a(a aVar) {
        this.H = aVar;
    }

    public CharSequence b(int i, td tdVar, int i2) {
        return a(i, tdVar.a(), i2);
    }

    public CharSequence a(int i, CharSequence charSequence, int i2) {
        Matcher a = a(i, charSequence);
        return a.find() ? a.group(i2) : null;
    }

    private void a(te teVar) {
        while (teVar.a() != null && a(25, teVar.a())) {
            teVar.f();
        }
    }

    private void b(te teVar) {
        while (teVar.b() != null && a(25, teVar.b())) {
            teVar.g();
        }
    }
}
