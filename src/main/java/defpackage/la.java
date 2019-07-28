package defpackage;

import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.text.c;
import com.google.android.exoplayer2.util.i;
import com.google.android.exoplayer2.util.n;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: SubripDecoder */
/* renamed from: la */
public final class la extends c {
    private static final Pattern a = Pattern.compile("\\s*((?:(\\d+):)?(\\d+):(\\d+),(\\d+))\\s*-->\\s*((?:(\\d+):)?(\\d+):(\\d+),(\\d+))?\\s*");
    private final StringBuilder b = new StringBuilder();

    public la() {
        super("SubripDecoder");
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public lb a(byte[] bArr, int i, boolean z) {
        ArrayList arrayList = new ArrayList();
        i iVar = new i();
        n nVar = new n(bArr, i);
        while (true) {
            String z2 = nVar.z();
            if (z2 == null) {
                break;
            } else if (z2.length() != 0) {
                StringBuilder stringBuilder;
                try {
                    Integer.parseInt(z2);
                    z2 = nVar.z();
                    if (z2 == null) {
                        Log.w("SubripDecoder", "Unexpected end");
                        break;
                    }
                    Matcher matcher = a.matcher(z2);
                    if (matcher.matches()) {
                        int i2 = 1;
                        iVar.a(la.a(matcher, 1));
                        if (TextUtils.isEmpty(matcher.group(6))) {
                            i2 = 0;
                        } else {
                            iVar.a(la.a(matcher, 6));
                        }
                        this.b.setLength(0);
                        while (true) {
                            String z3 = nVar.z();
                            if (TextUtils.isEmpty(z3)) {
                                break;
                            }
                            if (this.b.length() > 0) {
                                this.b.append("<br>");
                            }
                            this.b.append(z3.trim());
                        }
                        arrayList.add(new b(Html.fromHtml(this.b.toString())));
                        if (i2 != 0) {
                            arrayList.add(null);
                        }
                    } else {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Skipping invalid timing: ");
                        stringBuilder.append(z2);
                        Log.w("SubripDecoder", stringBuilder.toString());
                    }
                } catch (NumberFormatException unused) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Skipping invalid index: ");
                    stringBuilder.append(z2);
                    Log.w("SubripDecoder", stringBuilder.toString());
                }
            }
        }
        b[] bVarArr = new b[arrayList.size()];
        arrayList.toArray(bVarArr);
        return new lb(bVarArr, iVar.b());
    }

    private static long a(Matcher matcher, int i) {
        return ((((((Long.parseLong(matcher.group(i + 1)) * 60) * 60) * 1000) + ((Long.parseLong(matcher.group(i + 2)) * 60) * 1000)) + (Long.parseLong(matcher.group(i + 3)) * 1000)) + Long.parseLong(matcher.group(i + 4))) * 1000;
    }
}
