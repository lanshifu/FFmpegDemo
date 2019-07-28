package defpackage;

import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import kotlin.collections.v;

/* compiled from: Progressions.kt */
/* renamed from: yd */
public class yd implements Iterable<Integer> {
    public static final a a = new a();
    private final int b;
    private final int c;
    private final int d;

    /* compiled from: Progressions.kt */
    /* renamed from: yd$a */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(d dVar) {
            this();
        }

        public final yd a(int i, int i2, int i3) {
            return new yd(i, i2, i3);
        }
    }

    public yd(int i, int i2, int i3) {
        if (i3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        } else if (i3 != CheckView.UNCHECKED) {
            this.b = i;
            this.c = xs.a(i, i2, i3);
            this.d = i3;
        } else {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
    }

    public final int a() {
        return this.b;
    }

    public final int b() {
        return this.c;
    }

    public final int c() {
        return this.d;
    }

    /* renamed from: d */
    public v iterator() {
        return new ye(this.b, this.c, this.d);
    }

    public boolean e() {
        if (this.d > 0) {
            if (this.b <= this.c) {
                return false;
            }
        } else if (this.b >= this.c) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Missing block: B:11:0x0025, code skipped:
            if (r2.d == r3.d) goto L_0x0027;
     */
    public boolean equals(java.lang.Object r3) {
        /*
        r2 = this;
        r0 = r3 instanceof defpackage.yd;
        if (r0 == 0) goto L_0x0029;
    L_0x0004:
        r0 = r2.e();
        if (r0 == 0) goto L_0x0013;
    L_0x000a:
        r0 = r3;
        r0 = (defpackage.yd) r0;
        r0 = r0.e();
        if (r0 != 0) goto L_0x0027;
    L_0x0013:
        r0 = r2.b;
        r3 = (defpackage.yd) r3;
        r1 = r3.b;
        if (r0 != r1) goto L_0x0029;
    L_0x001b:
        r0 = r2.c;
        r1 = r3.c;
        if (r0 != r1) goto L_0x0029;
    L_0x0021:
        r0 = r2.d;
        r3 = r3.d;
        if (r0 != r3) goto L_0x0029;
    L_0x0027:
        r3 = 1;
        goto L_0x002a;
    L_0x0029:
        r3 = 0;
    L_0x002a:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yd.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return e() ? -1 : (((this.b * 31) + this.c) * 31) + this.d;
    }

    public String toString() {
        StringBuilder stringBuilder;
        int i;
        if (this.d > 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.b);
            stringBuilder.append("..");
            stringBuilder.append(this.c);
            stringBuilder.append(" step ");
            i = this.d;
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.b);
            stringBuilder.append(" downTo ");
            stringBuilder.append(this.c);
            stringBuilder.append(" step ");
            i = -this.d;
        }
        stringBuilder.append(i);
        return stringBuilder.toString();
    }
}
