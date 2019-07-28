package defpackage;

import android.util.Log;
import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.text.c;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.i;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: SsaDecoder */
/* renamed from: ky */
public final class ky extends c {
    private static final Pattern a = Pattern.compile("(?:(\\d+):)?(\\d+):(\\d+)(?::|\\.)(\\d+)");
    private final boolean b;
    private int c;
    private int d;
    private int e;
    private int f;

    public ky() {
        this(null);
    }

    public ky(List<byte[]> list) {
        super("SsaDecoder");
        if (list == null || list.isEmpty()) {
            this.b = false;
            return;
        }
        this.b = true;
        String a = z.a((byte[]) list.get(0));
        a.a(a.startsWith("Format: "));
        b(a);
        a(new n((byte[]) list.get(1)));
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public kz a(byte[] bArr, int i, boolean z) {
        List arrayList = new ArrayList();
        i iVar = new i();
        n nVar = new n(bArr, i);
        if (!this.b) {
            a(nVar);
        }
        a(nVar, arrayList, iVar);
        b[] bVarArr = new b[arrayList.size()];
        arrayList.toArray(bVarArr);
        return new kz(bVarArr, iVar.b());
    }

    private void a(n nVar) {
        String z;
        do {
            z = nVar.z();
            if (z == null) {
                return;
            }
        } while (!z.startsWith("[Events]"));
    }

    private void a(n nVar, List<b> list, i iVar) {
        while (true) {
            String z = nVar.z();
            if (z == null) {
                return;
            }
            if (!this.b && z.startsWith("Format: ")) {
                b(z);
            } else if (z.startsWith("Dialogue: ")) {
                a(z, (List) list, iVar);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0069 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0069 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0069 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0061  */
    private void b(java.lang.String r7) {
        /*
        r6 = this;
        r0 = "Format: ";
        r0 = r0.length();
        r7 = r7.substring(r0);
        r0 = ",";
        r7 = android.text.TextUtils.split(r7, r0);
        r0 = r7.length;
        r6.c = r0;
        r0 = -1;
        r6.d = r0;
        r6.e = r0;
        r6.f = r0;
        r1 = 0;
        r2 = 0;
    L_0x001c:
        r3 = r6.c;
        if (r2 >= r3) goto L_0x006c;
    L_0x0020:
        r3 = r7[r2];
        r3 = r3.trim();
        r3 = com.google.android.exoplayer2.util.z.d(r3);
        r4 = r3.hashCode();
        r5 = 100571; // 0x188db float:1.4093E-40 double:4.96887E-319;
        if (r4 == r5) goto L_0x0052;
    L_0x0033:
        r5 = 3556653; // 0x36452d float:4.983932E-39 double:1.75722E-317;
        if (r4 == r5) goto L_0x0048;
    L_0x0038:
        r5 = 109757538; // 0x68ac462 float:5.219839E-35 double:5.4227429E-316;
        if (r4 == r5) goto L_0x003e;
    L_0x003d:
        goto L_0x005c;
    L_0x003e:
        r4 = "start";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x005c;
    L_0x0046:
        r3 = 0;
        goto L_0x005d;
    L_0x0048:
        r4 = "text";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x005c;
    L_0x0050:
        r3 = 2;
        goto L_0x005d;
    L_0x0052:
        r4 = "end";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x005c;
    L_0x005a:
        r3 = 1;
        goto L_0x005d;
    L_0x005c:
        r3 = -1;
    L_0x005d:
        switch(r3) {
            case 0: goto L_0x0067;
            case 1: goto L_0x0064;
            case 2: goto L_0x0061;
            default: goto L_0x0060;
        };
    L_0x0060:
        goto L_0x0069;
    L_0x0061:
        r6.f = r2;
        goto L_0x0069;
    L_0x0064:
        r6.e = r2;
        goto L_0x0069;
    L_0x0067:
        r6.d = r2;
    L_0x0069:
        r2 = r2 + 1;
        goto L_0x001c;
    L_0x006c:
        r7 = r6.d;
        if (r7 == r0) goto L_0x0078;
    L_0x0070:
        r7 = r6.e;
        if (r7 == r0) goto L_0x0078;
    L_0x0074:
        r7 = r6.f;
        if (r7 != r0) goto L_0x007a;
    L_0x0078:
        r6.c = r1;
    L_0x007a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ky.b(java.lang.String):void");
    }

    private void a(String str, List<b> list, i iVar) {
        StringBuilder stringBuilder;
        if (this.c == 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Skipping dialogue line before complete format: ");
            stringBuilder.append(str);
            Log.w("SsaDecoder", stringBuilder.toString());
            return;
        }
        String[] split = str.substring("Dialogue: ".length()).split(",", this.c);
        if (split.length != this.c) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Skipping dialogue line with fewer columns than format: ");
            stringBuilder.append(str);
            Log.w("SsaDecoder", stringBuilder.toString());
            return;
        }
        long a = ky.a(split[this.d]);
        if (a == -9223372036854775807L) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Skipping invalid timing: ");
            stringBuilder.append(str);
            Log.w("SsaDecoder", stringBuilder.toString());
            return;
        }
        long j;
        String str2 = split[this.e];
        if (str2.trim().isEmpty()) {
            j = -9223372036854775807L;
        } else {
            j = ky.a(str2);
            if (j == -9223372036854775807L) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Skipping invalid timing: ");
                stringBuilder.append(str);
                Log.w("SsaDecoder", stringBuilder.toString());
                return;
            }
        }
        list.add(new b(split[this.f].replaceAll("\\{.*?\\}", "").replaceAll("\\\\N", "\n").replaceAll("\\\\n", "\n")));
        iVar.a(a);
        if (j != -9223372036854775807L) {
            list.add(null);
            iVar.a(j);
        }
    }

    public static long a(String str) {
        Matcher matcher = a.matcher(str);
        if (matcher.matches()) {
            return (((((Long.parseLong(matcher.group(1)) * 60) * 60) * 1000000) + ((Long.parseLong(matcher.group(2)) * 60) * 1000000)) + (Long.parseLong(matcher.group(3)) * 1000000)) + (Long.parseLong(matcher.group(4)) * 10000);
        }
        return -9223372036854775807L;
    }
}
