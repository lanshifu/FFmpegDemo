package defpackage;

import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan.Standard;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import defpackage.lo.a;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: WebvttCueParser */
/* renamed from: lp */
public final class lp {
    public static final Pattern a = Pattern.compile("^(\\S+)\\s+-->\\s+(\\S+)(.*)?$");
    private static final Pattern b = Pattern.compile("(\\S+?):(\\S+)");
    private final StringBuilder c = new StringBuilder();

    /* compiled from: WebvttCueParser */
    /* renamed from: lp$a */
    private static final class a {
        private static final String[] e = new String[0];
        public final String a;
        public final int b;
        public final String c;
        public final String[] d;

        private a(String str, int i, String str2, String[] strArr) {
            this.b = i;
            this.a = str;
            this.c = str2;
            this.d = strArr;
        }

        public static a a(String str, int i) {
            str = str.trim();
            if (str.isEmpty()) {
                return null;
            }
            String str2;
            String trim;
            int indexOf = str.indexOf(" ");
            if (indexOf == -1) {
                str2 = "";
            } else {
                trim = str.substring(indexOf).trim();
                str = str.substring(0, indexOf);
                str2 = trim;
            }
            String[] a = z.a(str, "\\.");
            trim = a[0];
            if (a.length > 1) {
                a = (String[]) Arrays.copyOfRange(a, 1, a.length);
            } else {
                a = e;
            }
            return new a(trim, i, str2, a);
        }

        public static a a() {
            return new a("", 0, "", new String[0]);
        }
    }

    /* compiled from: WebvttCueParser */
    /* renamed from: lp$b */
    private static final class b implements Comparable<b> {
        public final int a;
        public final ln b;

        public b(int i, ln lnVar) {
            this.a = i;
            this.b = lnVar;
        }

        /* renamed from: a */
        public int compareTo(@NonNull b bVar) {
            return this.a - bVar.a;
        }
    }

    public boolean a(n nVar, a aVar, List<ln> list) {
        String z = nVar.z();
        if (z == null) {
            return false;
        }
        Matcher matcher = a.matcher(z);
        if (matcher.matches()) {
            return lp.a(null, matcher, nVar, aVar, this.c, list);
        }
        String z2 = nVar.z();
        if (z2 == null) {
            return false;
        }
        Matcher matcher2 = a.matcher(z2);
        if (!matcher2.matches()) {
            return false;
        }
        return lp.a(z.trim(), matcher2, nVar, aVar, this.c, list);
    }

    static void a(String str, a aVar) {
        Matcher matcher = b.matcher(str);
        while (matcher.find()) {
            String group = matcher.group(1);
            String group2 = matcher.group(2);
            try {
                if ("line".equals(group)) {
                    lp.b(group2, aVar);
                } else if ("align".equals(group)) {
                    aVar.a(lp.b(group2));
                } else if ("position".equals(group)) {
                    lp.c(group2, aVar);
                } else if ("size".equals(group)) {
                    aVar.c(lr.b(group2));
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Unknown cue setting ");
                    stringBuilder.append(group);
                    stringBuilder.append(":");
                    stringBuilder.append(group2);
                    Log.w("WebvttCueParser", stringBuilder.toString());
                }
            } catch (NumberFormatException unused) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Skipping bad cue setting: ");
                stringBuilder2.append(matcher.group());
                Log.w("WebvttCueParser", stringBuilder2.toString());
            }
        }
    }

    static void a(String str, String str2, a aVar, List<ln> list) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayDeque arrayDeque = new ArrayDeque();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < str2.length()) {
            char charAt = str2.charAt(i);
            if (charAt == '&') {
                i++;
                int indexOf = str2.indexOf(59, i);
                int indexOf2 = str2.indexOf(32, i);
                if (indexOf == -1) {
                    indexOf = indexOf2;
                } else if (indexOf2 != -1) {
                    indexOf = Math.min(indexOf, indexOf2);
                }
                if (indexOf != -1) {
                    lp.a(str2.substring(i, indexOf), spannableStringBuilder);
                    if (indexOf == indexOf2) {
                        spannableStringBuilder.append(" ");
                    }
                    i = indexOf + 1;
                } else {
                    spannableStringBuilder.append(charAt);
                }
            } else if (charAt != '<') {
                spannableStringBuilder.append(charAt);
                i++;
            } else {
                int i2 = i + 1;
                if (i2 < str2.length()) {
                    int i3 = 1;
                    Object obj = str2.charAt(i2) == '/' ? 1 : null;
                    i2 = lp.a(str2, i2);
                    int i4 = i2 - 2;
                    Object obj2 = str2.charAt(i4) == '/' ? 1 : null;
                    if (obj != null) {
                        i3 = 2;
                    }
                    i += i3;
                    if (obj2 == null) {
                        i4 = i2 - 1;
                    }
                    String substring = str2.substring(i, i4);
                    String d = lp.d(substring);
                    if (d != null && lp.c(d)) {
                        if (obj != null) {
                            while (!arrayDeque.isEmpty()) {
                                a aVar2 = (a) arrayDeque.pop();
                                lp.a(str, aVar2, spannableStringBuilder, list, arrayList);
                                if (aVar2.a.equals(d)) {
                                    break;
                                }
                            }
                        } else if (obj2 == null) {
                            arrayDeque.push(a.a(substring, spannableStringBuilder.length()));
                        }
                    }
                }
                i = i2;
            }
        }
        while (!arrayDeque.isEmpty()) {
            lp.a(str, (a) arrayDeque.pop(), spannableStringBuilder, list, arrayList);
        }
        lp.a(str, a.a(), spannableStringBuilder, list, arrayList);
        aVar.a(spannableStringBuilder);
    }

    private static boolean a(String str, Matcher matcher, n nVar, a aVar, StringBuilder stringBuilder, List<ln> list) {
        try {
            aVar.a(lr.a(matcher.group(1))).b(lr.a(matcher.group(2)));
            lp.a(matcher.group(3), aVar);
            stringBuilder.setLength(0);
            while (true) {
                String z = nVar.z();
                if (TextUtils.isEmpty(z)) {
                    lp.a(str, stringBuilder.toString(), aVar, (List) list);
                    return true;
                }
                if (stringBuilder.length() > 0) {
                    stringBuilder.append("\n");
                }
                stringBuilder.append(z.trim());
            }
        } catch (NumberFormatException unused) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Skipping cue with bad header: ");
            stringBuilder2.append(matcher.group());
            Log.w("WebvttCueParser", stringBuilder2.toString());
            return false;
        }
    }

    private static void b(String str, a aVar) throws NumberFormatException {
        int indexOf = str.indexOf(44);
        if (indexOf != -1) {
            aVar.b(lp.a(str.substring(indexOf + 1)));
            str = str.substring(0, indexOf);
        } else {
            aVar.b((int) CheckView.UNCHECKED);
        }
        if (str.endsWith("%")) {
            aVar.a(lr.b(str)).a(0);
            return;
        }
        int parseInt = Integer.parseInt(str);
        if (parseInt < 0) {
            parseInt--;
        }
        aVar.a((float) parseInt).a(1);
    }

    private static void c(String str, a aVar) throws NumberFormatException {
        int indexOf = str.indexOf(44);
        if (indexOf != -1) {
            aVar.c(lp.a(str.substring(indexOf + 1)));
            str = str.substring(0, indexOf);
        } else {
            aVar.c((int) CheckView.UNCHECKED);
        }
        aVar.b(lr.b(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0063 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0062 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0061 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0063 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0062 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0061 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0063 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0062 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0061 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0063 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0062 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0061 A:{RETURN} */
    private static int a(java.lang.String r5) {
        /*
        r0 = r5.hashCode();
        r1 = -1364013995; // 0xffffffffaeb2cc55 float:-8.1307995E-11 double:NaN;
        r2 = 2;
        r3 = 1;
        r4 = 0;
        if (r0 == r1) goto L_0x003a;
    L_0x000c:
        r1 = -1074341483; // 0xffffffffbff6d995 float:-1.9285151 double:NaN;
        if (r0 == r1) goto L_0x0030;
    L_0x0011:
        r1 = 100571; // 0x188db float:1.4093E-40 double:4.96887E-319;
        if (r0 == r1) goto L_0x0026;
    L_0x0016:
        r1 = 109757538; // 0x68ac462 float:5.219839E-35 double:5.4227429E-316;
        if (r0 == r1) goto L_0x001c;
    L_0x001b:
        goto L_0x0044;
    L_0x001c:
        r0 = "start";
        r0 = r5.equals(r0);
        if (r0 == 0) goto L_0x0044;
    L_0x0024:
        r0 = 0;
        goto L_0x0045;
    L_0x0026:
        r0 = "end";
        r0 = r5.equals(r0);
        if (r0 == 0) goto L_0x0044;
    L_0x002e:
        r0 = 3;
        goto L_0x0045;
    L_0x0030:
        r0 = "middle";
        r0 = r5.equals(r0);
        if (r0 == 0) goto L_0x0044;
    L_0x0038:
        r0 = 2;
        goto L_0x0045;
    L_0x003a:
        r0 = "center";
        r0 = r5.equals(r0);
        if (r0 == 0) goto L_0x0044;
    L_0x0042:
        r0 = 1;
        goto L_0x0045;
    L_0x0044:
        r0 = -1;
    L_0x0045:
        switch(r0) {
            case 0: goto L_0x0063;
            case 1: goto L_0x0062;
            case 2: goto L_0x0062;
            case 3: goto L_0x0061;
            default: goto L_0x0048;
        };
    L_0x0048:
        r0 = "WebvttCueParser";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Invalid anchor value: ";
        r1.append(r2);
        r1.append(r5);
        r5 = r1.toString();
        android.util.Log.w(r0, r5);
        r5 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        return r5;
    L_0x0061:
        return r2;
    L_0x0062:
        return r3;
    L_0x0063:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lp.a(java.lang.String):int");
    }

    private static android.text.Layout.Alignment b(java.lang.String r3) {
        /*
        r0 = r3.hashCode();
        switch(r0) {
            case -1364013995: goto L_0x003a;
            case -1074341483: goto L_0x0030;
            case 100571: goto L_0x0026;
            case 3317767: goto L_0x001c;
            case 108511772: goto L_0x0012;
            case 109757538: goto L_0x0008;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x0044;
    L_0x0008:
        r0 = "start";
        r0 = r3.equals(r0);
        if (r0 == 0) goto L_0x0044;
    L_0x0010:
        r0 = 0;
        goto L_0x0045;
    L_0x0012:
        r0 = "right";
        r0 = r3.equals(r0);
        if (r0 == 0) goto L_0x0044;
    L_0x001a:
        r0 = 5;
        goto L_0x0045;
    L_0x001c:
        r0 = "left";
        r0 = r3.equals(r0);
        if (r0 == 0) goto L_0x0044;
    L_0x0024:
        r0 = 1;
        goto L_0x0045;
    L_0x0026:
        r0 = "end";
        r0 = r3.equals(r0);
        if (r0 == 0) goto L_0x0044;
    L_0x002e:
        r0 = 4;
        goto L_0x0045;
    L_0x0030:
        r0 = "middle";
        r0 = r3.equals(r0);
        if (r0 == 0) goto L_0x0044;
    L_0x0038:
        r0 = 3;
        goto L_0x0045;
    L_0x003a:
        r0 = "center";
        r0 = r3.equals(r0);
        if (r0 == 0) goto L_0x0044;
    L_0x0042:
        r0 = 2;
        goto L_0x0045;
    L_0x0044:
        r0 = -1;
    L_0x0045:
        switch(r0) {
            case 0: goto L_0x0066;
            case 1: goto L_0x0066;
            case 2: goto L_0x0063;
            case 3: goto L_0x0063;
            case 4: goto L_0x0060;
            case 5: goto L_0x0060;
            default: goto L_0x0048;
        };
    L_0x0048:
        r0 = "WebvttCueParser";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Invalid alignment value: ";
        r1.append(r2);
        r1.append(r3);
        r3 = r1.toString();
        android.util.Log.w(r0, r3);
        r3 = 0;
        return r3;
    L_0x0060:
        r3 = android.text.Layout.Alignment.ALIGN_OPPOSITE;
        return r3;
    L_0x0063:
        r3 = android.text.Layout.Alignment.ALIGN_CENTER;
        return r3;
    L_0x0066:
        r3 = android.text.Layout.Alignment.ALIGN_NORMAL;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lp.b(java.lang.String):android.text.Layout$Alignment");
    }

    private static int a(String str, int i) {
        i = str.indexOf(62, i);
        return i == -1 ? str.length() : i + 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005f  */
    private static void a(java.lang.String r2, android.text.SpannableStringBuilder r3) {
        /*
        r0 = r2.hashCode();
        r1 = 3309; // 0xced float:4.637E-42 double:1.635E-320;
        if (r0 == r1) goto L_0x0035;
    L_0x0008:
        r1 = 3464; // 0xd88 float:4.854E-42 double:1.7114E-320;
        if (r0 == r1) goto L_0x002b;
    L_0x000c:
        r1 = 96708; // 0x179c4 float:1.35517E-40 double:4.778E-319;
        if (r0 == r1) goto L_0x0021;
    L_0x0011:
        r1 = 3374865; // 0x337f11 float:4.729193E-39 double:1.667405E-317;
        if (r0 == r1) goto L_0x0017;
    L_0x0016:
        goto L_0x003f;
    L_0x0017:
        r0 = "nbsp";
        r0 = r2.equals(r0);
        if (r0 == 0) goto L_0x003f;
    L_0x001f:
        r0 = 2;
        goto L_0x0040;
    L_0x0021:
        r0 = "amp";
        r0 = r2.equals(r0);
        if (r0 == 0) goto L_0x003f;
    L_0x0029:
        r0 = 3;
        goto L_0x0040;
    L_0x002b:
        r0 = "lt";
        r0 = r2.equals(r0);
        if (r0 == 0) goto L_0x003f;
    L_0x0033:
        r0 = 0;
        goto L_0x0040;
    L_0x0035:
        r0 = "gt";
        r0 = r2.equals(r0);
        if (r0 == 0) goto L_0x003f;
    L_0x003d:
        r0 = 1;
        goto L_0x0040;
    L_0x003f:
        r0 = -1;
    L_0x0040:
        switch(r0) {
            case 0: goto L_0x0071;
            case 1: goto L_0x006b;
            case 2: goto L_0x0065;
            case 3: goto L_0x005f;
            default: goto L_0x0043;
        };
    L_0x0043:
        r3 = "WebvttCueParser";
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "ignoring unsupported entity: '&";
        r0.append(r1);
        r0.append(r2);
        r2 = ";'";
        r0.append(r2);
        r2 = r0.toString();
        android.util.Log.w(r3, r2);
        goto L_0x0076;
    L_0x005f:
        r2 = 38;
        r3.append(r2);
        goto L_0x0076;
    L_0x0065:
        r2 = 32;
        r3.append(r2);
        goto L_0x0076;
    L_0x006b:
        r2 = 62;
        r3.append(r2);
        goto L_0x0076;
    L_0x0071:
        r2 = 60;
        r3.append(r2);
    L_0x0076:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lp.a(java.lang.String, android.text.SpannableStringBuilder):void");
    }

    private static boolean c(java.lang.String r3) {
        /*
        r0 = r3.hashCode();
        r1 = 1;
        r2 = 0;
        switch(r0) {
            case 98: goto L_0x003c;
            case 99: goto L_0x0032;
            case 105: goto L_0x0028;
            case 117: goto L_0x001e;
            case 118: goto L_0x0014;
            case 3314158: goto L_0x000a;
            default: goto L_0x0009;
        };
    L_0x0009:
        goto L_0x0046;
    L_0x000a:
        r0 = "lang";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0046;
    L_0x0012:
        r3 = 3;
        goto L_0x0047;
    L_0x0014:
        r0 = "v";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0046;
    L_0x001c:
        r3 = 5;
        goto L_0x0047;
    L_0x001e:
        r0 = "u";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0046;
    L_0x0026:
        r3 = 4;
        goto L_0x0047;
    L_0x0028:
        r0 = "i";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0046;
    L_0x0030:
        r3 = 2;
        goto L_0x0047;
    L_0x0032:
        r0 = "c";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0046;
    L_0x003a:
        r3 = 1;
        goto L_0x0047;
    L_0x003c:
        r0 = "b";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0046;
    L_0x0044:
        r3 = 0;
        goto L_0x0047;
    L_0x0046:
        r3 = -1;
    L_0x0047:
        switch(r3) {
            case 0: goto L_0x004b;
            case 1: goto L_0x004b;
            case 2: goto L_0x004b;
            case 3: goto L_0x004b;
            case 4: goto L_0x004b;
            case 5: goto L_0x004b;
            default: goto L_0x004a;
        };
    L_0x004a:
        return r2;
    L_0x004b:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lp.c(java.lang.String):boolean");
    }

    private static void a(java.lang.String r7, defpackage.lp.a r8, android.text.SpannableStringBuilder r9, java.util.List<defpackage.ln> r10, java.util.List<defpackage.lp.b> r11) {
        /*
        r0 = r8.b;
        r1 = r9.length();
        r2 = r8.a;
        r3 = r2.hashCode();
        r4 = 0;
        r5 = 2;
        r6 = 1;
        switch(r3) {
            case 0: goto L_0x004f;
            case 98: goto L_0x0045;
            case 99: goto L_0x003b;
            case 105: goto L_0x0031;
            case 117: goto L_0x0027;
            case 118: goto L_0x001d;
            case 3314158: goto L_0x0013;
            default: goto L_0x0012;
        };
    L_0x0012:
        goto L_0x0059;
    L_0x0013:
        r3 = "lang";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0059;
    L_0x001b:
        r2 = 4;
        goto L_0x005a;
    L_0x001d:
        r3 = "v";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0059;
    L_0x0025:
        r2 = 5;
        goto L_0x005a;
    L_0x0027:
        r3 = "u";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0059;
    L_0x002f:
        r2 = 2;
        goto L_0x005a;
    L_0x0031:
        r3 = "i";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0059;
    L_0x0039:
        r2 = 1;
        goto L_0x005a;
    L_0x003b:
        r3 = "c";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0059;
    L_0x0043:
        r2 = 3;
        goto L_0x005a;
    L_0x0045:
        r3 = "b";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0059;
    L_0x004d:
        r2 = 0;
        goto L_0x005a;
    L_0x004f:
        r3 = "";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0059;
    L_0x0057:
        r2 = 6;
        goto L_0x005a;
    L_0x0059:
        r2 = -1;
    L_0x005a:
        r3 = 33;
        switch(r2) {
            case 0: goto L_0x0072;
            case 1: goto L_0x0069;
            case 2: goto L_0x0060;
            case 3: goto L_0x007a;
            case 4: goto L_0x007a;
            case 5: goto L_0x007a;
            case 6: goto L_0x007a;
            default: goto L_0x005f;
        };
    L_0x005f:
        return;
    L_0x0060:
        r2 = new android.text.style.UnderlineSpan;
        r2.<init>();
        r9.setSpan(r2, r0, r1, r3);
        goto L_0x007a;
    L_0x0069:
        r2 = new android.text.style.StyleSpan;
        r2.<init>(r5);
        r9.setSpan(r2, r0, r1, r3);
        goto L_0x007a;
    L_0x0072:
        r2 = new android.text.style.StyleSpan;
        r2.<init>(r6);
        r9.setSpan(r2, r0, r1, r3);
    L_0x007a:
        r11.clear();
        defpackage.lp.a(r10, r7, r8, r11);
        r7 = r11.size();
    L_0x0084:
        if (r4 >= r7) goto L_0x0094;
    L_0x0086:
        r8 = r11.get(r4);
        r8 = (defpackage.lp.b) r8;
        r8 = r8.b;
        defpackage.lp.a(r9, r8, r0, r1);
        r4 = r4 + 1;
        goto L_0x0084;
    L_0x0094:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lp.a(java.lang.String, lp$a, android.text.SpannableStringBuilder, java.util.List, java.util.List):void");
    }

    private static void a(SpannableStringBuilder spannableStringBuilder, ln lnVar, int i, int i2) {
        if (lnVar != null) {
            if (lnVar.b() != -1) {
                spannableStringBuilder.setSpan(new StyleSpan(lnVar.b()), i, i2, 33);
            }
            if (lnVar.c()) {
                spannableStringBuilder.setSpan(new StrikethroughSpan(), i, i2, 33);
            }
            if (lnVar.d()) {
                spannableStringBuilder.setSpan(new UnderlineSpan(), i, i2, 33);
            }
            if (lnVar.g()) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(lnVar.f()), i, i2, 33);
            }
            if (lnVar.i()) {
                spannableStringBuilder.setSpan(new BackgroundColorSpan(lnVar.h()), i, i2, 33);
            }
            if (lnVar.e() != null) {
                spannableStringBuilder.setSpan(new TypefaceSpan(lnVar.e()), i, i2, 33);
            }
            if (lnVar.j() != null) {
                spannableStringBuilder.setSpan(new Standard(lnVar.j()), i, i2, 33);
            }
            switch (lnVar.k()) {
                case 1:
                    spannableStringBuilder.setSpan(new AbsoluteSizeSpan((int) lnVar.l(), true), i, i2, 33);
                    break;
                case 2:
                    spannableStringBuilder.setSpan(new RelativeSizeSpan(lnVar.l()), i, i2, 33);
                    break;
                case 3:
                    spannableStringBuilder.setSpan(new RelativeSizeSpan(lnVar.l() / 100.0f), i, i2, 33);
                    break;
            }
        }
    }

    private static String d(String str) {
        str = str.trim();
        if (str.isEmpty()) {
            return null;
        }
        return z.b(str, "[ \\.]")[0];
    }

    private static void a(List<ln> list, String str, a aVar, List<b> list2) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ln lnVar = (ln) list.get(i);
            int a = lnVar.a(str, aVar.a, aVar.d, aVar.c);
            if (a > 0) {
                list2.add(new b(a, lnVar));
            }
        }
        Collections.sort(list2);
    }
}
