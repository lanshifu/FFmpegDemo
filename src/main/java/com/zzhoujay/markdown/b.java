package com.zzhoujay.markdown;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import com.zzhoujay.markdown.style.c;
import defpackage.td;
import defpackage.te;
import defpackage.tf.a;
import defpackage.tg;
import defpackage.tj;
import defpackage.tk;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: MarkDownParser */
class b {
    private BufferedReader a;
    private tj b;

    b(BufferedReader bufferedReader, tg tgVar) {
        this.a = bufferedReader;
        this.b = new tk(tgVar);
    }

    b(String str, tg tgVar) {
        if (str == null) {
            str = "";
        }
        this(new BufferedReader(new StringReader(str)), tgVar);
    }

    public Spannable a() throws IOException {
        return a(b());
    }

    private te b() throws IOException {
        te teVar = null;
        te teVar2 = null;
        while (true) {
            String readLine = this.a.readLine();
            if (readLine == null) {
                return teVar;
            }
            if (!(this.b.b(readLine) || this.b.a(readLine))) {
                td tdVar = new td(readLine);
                if (teVar2 == null) {
                    teVar = new te(tdVar);
                    teVar2 = tdVar;
                } else {
                    teVar.a(tdVar);
                }
            }
        }
    }

    private Spannable a(final te teVar) {
        if (teVar == null) {
            return null;
        }
        this.b.a(new a() {
            public te a() {
                return teVar;
            }
        });
        d(teVar);
        if (teVar.j()) {
            return null;
        }
        do {
            Object obj = (teVar.b() == null || !((teVar.b().c() == 3 || teVar.b().c() == 2) && (this.b.a(9, teVar.c()) || this.b.a(10, teVar.c())))) ? null : 1;
            if (!(obj == null && (this.b.g(teVar.c()) || this.b.h(teVar.c())))) {
                obj = (this.b.a(26, teVar.c()) || this.b.a(27, teVar.c()) || this.b.a(23, teVar.c())) ? 1 : null;
                if (obj != null) {
                    if (teVar.a() != null) {
                        a(teVar, true);
                    }
                    c(teVar);
                } else {
                    while (teVar.a() != null && !c(teVar) && !this.b.a(1, teVar.a()) && !this.b.a(2, teVar.a()) && !this.b.a(27, teVar.a()) && !this.b.a(9, teVar.a()) && !this.b.a(10, teVar.a()) && !this.b.a(23, teVar.a())) {
                        if (a(teVar, false)) {
                            break;
                        }
                    }
                    c(teVar);
                }
                if (!(this.b.e(teVar.c()) || this.b.b(teVar.c()) || this.b.d(teVar.c()) || this.b.c(teVar.c()) || this.b.a(teVar.c()))) {
                    teVar.c().a(SpannableStringBuilder.valueOf(teVar.c().a()));
                    this.b.f(teVar.c());
                }
            }
        } while (teVar.d());
        return b(teVar);
    }

    private boolean a(te teVar, boolean z) {
        int a = this.b.a(8, teVar.a(), 1);
        int a2 = this.b.a(8, teVar.c(), 1);
        if (a > 0 && a > a2) {
            return true;
        }
        String a3 = teVar.a().a();
        if (a > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("^\\s{0,3}(>\\s+){");
            stringBuilder.append(a);
            stringBuilder.append("}");
            a3 = a3.replaceFirst(stringBuilder.toString(), "");
        }
        if (a2 == a && (b(teVar, a2, a3) || a(teVar, a2, a3))) {
            return true;
        }
        if (z) {
            return false;
        }
        if (this.b.a(9, a3) || this.b.a(10, a3) || this.b.a(23, a3)) {
            return true;
        }
        td c = teVar.c();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(teVar.c().a());
        stringBuilder2.append(' ');
        stringBuilder2.append(a3);
        c.a(stringBuilder2.toString());
        teVar.f();
        return false;
    }

    private boolean a(te teVar, int i, String str) {
        if (!this.b.a(29, str)) {
            return false;
        }
        String stringBuilder;
        str = teVar.c().a();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("^\\s{0,3}(>\\s+?){");
        stringBuilder2.append(i);
        stringBuilder2.append("}(.*)");
        Matcher matcher = Pattern.compile(stringBuilder2.toString()).matcher(teVar.c().a());
        if (matcher.find()) {
            int start = matcher.start(2);
            i = matcher.end(2);
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str.substring(0, start));
            stringBuilder2.append("## ");
            stringBuilder2.append(str.subSequence(start, i));
            stringBuilder = stringBuilder2.toString();
        } else {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("## ");
            stringBuilder3.append(str);
            stringBuilder = stringBuilder3.toString();
        }
        teVar.c().a(stringBuilder);
        teVar.f();
        return true;
    }

    private boolean b(te teVar, int i, String str) {
        if (!this.b.a(28, str)) {
            return false;
        }
        String stringBuilder;
        str = teVar.c().a();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("^\\s{0,3}(>\\s+?){");
        stringBuilder2.append(i);
        stringBuilder2.append("}(.*)");
        Matcher matcher = Pattern.compile(stringBuilder2.toString()).matcher(str);
        if (matcher.find()) {
            int start = matcher.start(2);
            i = matcher.end(2);
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str.substring(0, start));
            stringBuilder2.append("# ");
            stringBuilder2.append(str.subSequence(start, i));
            stringBuilder = stringBuilder2.toString();
        } else {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("# ");
            stringBuilder3.append(str);
            stringBuilder = stringBuilder3.toString();
        }
        teVar.c().a(stringBuilder);
        teVar.f();
        return true;
    }

    private Spannable b(te teVar) {
        teVar.i();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        do {
            td c = teVar.c();
            td a = teVar.a();
            spannableStringBuilder.append(c.b());
            if (a != null) {
                spannableStringBuilder.append(10);
                switch (c.c()) {
                    case 1:
                        if (a.c() != 1) {
                            spannableStringBuilder.append(10);
                            break;
                        }
                        break;
                    case 2:
                        if (a.c() == 2) {
                            spannableStringBuilder.append(c());
                        }
                        spannableStringBuilder.append(10);
                        break;
                    case 3:
                        if (a.c() == 3) {
                            spannableStringBuilder.append(c());
                        }
                        spannableStringBuilder.append(10);
                        break;
                    default:
                        spannableStringBuilder.append(10);
                        break;
                }
            }
            return spannableStringBuilder;
        } while (teVar.d());
        return spannableStringBuilder;
    }

    private boolean c(te teVar) {
        boolean z = false;
        while (teVar.a() != null && this.b.a(25, teVar.a())) {
            teVar.f();
            z = true;
        }
        return z;
    }

    private boolean d(te teVar) {
        boolean z = false;
        while (teVar.c() != null && this.b.a(25, teVar.c())) {
            teVar.e();
            z = true;
        }
        return z;
    }

    private SpannableString c() {
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(new c(0.4f), 0, spannableString.length(), 33);
        return spannableString;
    }
}
