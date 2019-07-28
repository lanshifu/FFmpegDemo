package kotlin.text;

import defpackage.xx;
import defpackage.yf;
import defpackage.yj;
import defpackage.yl;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.f;

/* compiled from: Strings.kt */
final class d implements yl<yf> {
    private final CharSequence a;
    private final int b;
    private final int c;
    private final xx<CharSequence, Integer, Pair<Integer, Integer>> d;

    /* compiled from: Strings.kt */
    public static final class a implements Iterator<yf> {
        final /* synthetic */ d a;
        private int b = -1;
        private int c;
        private int d;
        private yf e;
        private int f;

        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        a(d dVar) {
            this.a = dVar;
            this.c = yj.a(dVar.b, 0, dVar.a.length());
            this.d = this.c;
        }

        /* JADX WARNING: Missing block: B:6:0x0025, code skipped:
            if (r6.f < kotlin.text.d.a(r6.a)) goto L_0x0027;
     */
        private final void b() {
            /*
            r6 = this;
            r0 = r6.d;
            r1 = 0;
            if (r0 >= 0) goto L_0x000e;
        L_0x0005:
            r6.b = r1;
            r0 = 0;
            r0 = (defpackage.yf) r0;
            r6.e = r0;
            goto L_0x00a4;
        L_0x000e:
            r0 = r6.a;
            r0 = r0.c;
            r2 = -1;
            r3 = 1;
            if (r0 <= 0) goto L_0x0027;
        L_0x0018:
            r0 = r6.f;
            r0 = r0 + r3;
            r6.f = r0;
            r0 = r6.f;
            r4 = r6.a;
            r4 = r4.c;
            if (r0 >= r4) goto L_0x0035;
        L_0x0027:
            r0 = r6.d;
            r4 = r6.a;
            r4 = r4.a;
            r4 = r4.length();
            if (r0 <= r4) goto L_0x004b;
        L_0x0035:
            r0 = r6.c;
            r1 = new yf;
            r4 = r6.a;
            r4 = r4.a;
            r4 = kotlin.text.n.b(r4);
            r1.<init>(r0, r4);
            r6.e = r1;
            r6.d = r2;
            goto L_0x00a2;
        L_0x004b:
            r0 = r6.a;
            r0 = r0.d;
            r4 = r6.a;
            r4 = r4.a;
            r5 = r6.d;
            r5 = java.lang.Integer.valueOf(r5);
            r0 = r0.invoke(r4, r5);
            r0 = (kotlin.Pair) r0;
            if (r0 != 0) goto L_0x007b;
        L_0x0065:
            r0 = r6.c;
            r1 = new yf;
            r4 = r6.a;
            r4 = r4.a;
            r4 = kotlin.text.n.b(r4);
            r1.<init>(r0, r4);
            r6.e = r1;
            r6.d = r2;
            goto L_0x00a2;
        L_0x007b:
            r2 = r0.component1();
            r2 = (java.lang.Number) r2;
            r2 = r2.intValue();
            r0 = r0.component2();
            r0 = (java.lang.Number) r0;
            r0 = r0.intValue();
            r4 = r6.c;
            r4 = defpackage.yj.b(r4, r2);
            r6.e = r4;
            r2 = r2 + r0;
            r6.c = r2;
            r2 = r6.c;
            if (r0 != 0) goto L_0x009f;
        L_0x009e:
            r1 = 1;
        L_0x009f:
            r2 = r2 + r1;
            r6.d = r2;
        L_0x00a2:
            r6.b = r3;
        L_0x00a4:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.text.d$a.b():void");
        }

        /* renamed from: a */
        public yf next() {
            if (this.b == -1) {
                b();
            }
            if (this.b != 0) {
                yf yfVar = this.e;
                if (yfVar != null) {
                    this.e = (yf) null;
                    this.b = -1;
                    return yfVar;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.ranges.IntRange");
            }
            throw new NoSuchElementException();
        }

        public boolean hasNext() {
            if (this.b == -1) {
                b();
            }
            return this.b == 1;
        }
    }

    public d(CharSequence charSequence, int i, int i2, xx<? super CharSequence, ? super Integer, Pair<Integer, Integer>> xxVar) {
        f.b(charSequence, "input");
        f.b(xxVar, "getNextMatch");
        this.a = charSequence;
        this.b = i;
        this.c = i2;
        this.d = xxVar;
    }

    public Iterator<yf> a() {
        return new a(this);
    }
}
