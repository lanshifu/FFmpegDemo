package defpackage;

import android.graphics.Color;
import android.text.Editable;
import android.text.Html.TagHandler;
import android.widget.TextView;
import java.lang.ref.SoftReference;
import java.util.Stack;
import org.xml.sax.XMLReader;

/* compiled from: HtmlTagHandler */
/* renamed from: uh */
public class uh implements TagHandler {
    private static final int a = Color.parseColor("#F0F0F0");
    private static final int b = Color.parseColor("#333333");
    private Stack<Integer> c = new Stack();
    private Stack<Boolean> d = new Stack();
    private int e = 0;
    private SoftReference<TextView> f;

    public uh(TextView textView) {
        this.f = new SoftReference(textView);
    }

    public void handleTag(boolean z, String str, Editable editable, XMLReader xMLReader) {
        if (z) {
            a(str, editable, xMLReader);
            this.c.push(Integer.valueOf(editable.length()));
            return;
        }
        int i;
        if (this.c.isEmpty()) {
            i = 0;
        } else {
            i = ((Integer) this.c.pop()).intValue();
        }
        a(i, editable.length(), str.toLowerCase(), editable, xMLReader);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003d  */
    private void a(java.lang.String r4, android.text.Editable r5, org.xml.sax.XMLReader r6) {
        /*
        r3 = this;
        r4 = r4.toLowerCase();
        r6 = r4.hashCode();
        r0 = 3549; // 0xddd float:4.973E-42 double:1.7534E-320;
        r1 = 0;
        r2 = 1;
        if (r6 == r0) goto L_0x002c;
    L_0x000e:
        r0 = 3735; // 0xe97 float:5.234E-42 double:1.8453E-320;
        if (r6 == r0) goto L_0x0022;
    L_0x0012:
        r0 = 111267; // 0x1b2a3 float:1.55918E-40 double:5.4973E-319;
        if (r6 == r0) goto L_0x0018;
    L_0x0017:
        goto L_0x0036;
    L_0x0018:
        r6 = "pre";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x0036;
    L_0x0020:
        r4 = 2;
        goto L_0x0037;
    L_0x0022:
        r6 = "ul";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x0036;
    L_0x002a:
        r4 = 0;
        goto L_0x0037;
    L_0x002c:
        r6 = "ol";
        r4 = r4.equals(r6);
        if (r4 == 0) goto L_0x0036;
    L_0x0034:
        r4 = 1;
        goto L_0x0037;
    L_0x0036:
        r4 = -1;
    L_0x0037:
        r6 = 10;
        switch(r4) {
            case 0: goto L_0x004a;
            case 1: goto L_0x003d;
            default: goto L_0x003c;
        };
    L_0x003c:
        goto L_0x0056;
    L_0x003d:
        r4 = r3.d;
        r0 = java.lang.Boolean.valueOf(r1);
        r4.push(r0);
        r5.append(r6);
        goto L_0x0056;
    L_0x004a:
        r4 = r3.d;
        r0 = java.lang.Boolean.valueOf(r2);
        r4.push(r0);
        r5.append(r6);
    L_0x0056:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uh.a(java.lang.String, android.text.Editable, org.xml.sax.XMLReader):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004e  */
    private void a(int r5, int r6, java.lang.String r7, android.text.Editable r8, org.xml.sax.XMLReader r9) {
        /*
        r4 = this;
        r7 = r7.toLowerCase();
        r9 = r7.hashCode();
        r0 = 3453; // 0xd7d float:4.839E-42 double:1.706E-320;
        r1 = 0;
        r2 = -1;
        r3 = 1;
        if (r9 == r0) goto L_0x003b;
    L_0x000f:
        r0 = 3549; // 0xddd float:4.973E-42 double:1.7534E-320;
        if (r9 == r0) goto L_0x0031;
    L_0x0013:
        r0 = 3735; // 0xe97 float:5.234E-42 double:1.8453E-320;
        if (r9 == r0) goto L_0x0027;
    L_0x0017:
        r0 = 3059181; // 0x2eaded float:4.286826E-39 double:1.511436E-317;
        if (r9 == r0) goto L_0x001d;
    L_0x001c:
        goto L_0x0045;
    L_0x001d:
        r9 = "code";
        r7 = r7.equals(r9);
        if (r7 == 0) goto L_0x0045;
    L_0x0025:
        r7 = 0;
        goto L_0x0046;
    L_0x0027:
        r9 = "ul";
        r7 = r7.equals(r9);
        if (r7 == 0) goto L_0x0045;
    L_0x002f:
        r7 = 2;
        goto L_0x0046;
    L_0x0031:
        r9 = "ol";
        r7 = r7.equals(r9);
        if (r7 == 0) goto L_0x0045;
    L_0x0039:
        r7 = 1;
        goto L_0x0046;
    L_0x003b:
        r9 = "li";
        r7 = r7.equals(r9);
        if (r7 == 0) goto L_0x0045;
    L_0x0043:
        r7 = 3;
        goto L_0x0046;
    L_0x0045:
        r7 = -1;
    L_0x0046:
        r9 = 10;
        r0 = 33;
        switch(r7) {
            case 0: goto L_0x009a;
            case 1: goto L_0x0089;
            case 2: goto L_0x0089;
            case 3: goto L_0x004e;
            default: goto L_0x004d;
        };
    L_0x004d:
        goto L_0x00a4;
    L_0x004e:
        r6 = r4.d;
        r6 = r6.peek();
        r6 = (java.lang.Boolean) r6;
        r6 = r6.booleanValue();
        if (r6 == 0) goto L_0x005f;
    L_0x005c:
        r4.e = r1;
        goto L_0x0065;
    L_0x005f:
        r6 = r4.e;
        r2 = r6 + 1;
        r4.e = r2;
    L_0x0065:
        r8.append(r9);
        r6 = r4.f;
        r6 = r6.get();
        r6 = (android.widget.TextView) r6;
        if (r6 != 0) goto L_0x0073;
    L_0x0072:
        return;
    L_0x0073:
        r7 = new com.zzhoujay.markdown.style.MarkDownBulletSpan;
        r9 = r4.d;
        r9 = r9.size();
        r9 = r9 - r3;
        r1 = b;
        r7.<init>(r9, r1, r2, r6);
        r6 = r8.length();
        r8.setSpan(r7, r5, r6, r0);
        goto L_0x00a4;
    L_0x0089:
        r8.append(r9);
        r5 = r4.d;
        r5 = r5.isEmpty();
        if (r5 != 0) goto L_0x00a4;
    L_0x0094:
        r5 = r4.d;
        r5.pop();
        goto L_0x00a4;
    L_0x009a:
        r7 = new com.zzhoujay.markdown.style.b;
        r9 = a;
        r7.<init>(r9);
        r8.setSpan(r7, r5, r6, r0);
    L_0x00a4:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uh.a(int, int, java.lang.String, android.text.Editable, org.xml.sax.XMLReader):void");
    }
}
