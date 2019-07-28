package defpackage;

import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;

/* compiled from: Random.kt */
/* renamed from: yb */
public abstract class yb {
    public static final a a = a.c;
    public static final b b = new b();
    private static final yb c = xr.a.a();

    /* compiled from: Random.kt */
    /* renamed from: yb$a */
    public static final class a extends yb {
        public static final a c = new a();

        private a() {
        }

        public int a(int i) {
            return yb.b.a(i);
        }
    }

    /* compiled from: Random.kt */
    /* renamed from: yb$b */
    public static final class b extends yb {
        private b() {
        }

        public /* synthetic */ b(d dVar) {
            this();
        }

        public int a(int i) {
            return yb.c.a(i);
        }

        public int b() {
            return yb.c.b();
        }

        public int a(int i, int i2) {
            return yb.c.a(i, i2);
        }
    }

    public abstract int a(int i);

    public int b() {
        return a(32);
    }

    public int a(int i, int i2) {
        yc.b(i, i2);
        int i3 = i2 - i;
        if (i3 > 0 || i3 == CheckView.UNCHECKED) {
            if (((-i3) & i3) == i3) {
                i2 = a(ya.a(i3));
            } else {
                int i4;
                do {
                    i2 = b() >>> 1;
                    i4 = i2 % i3;
                } while ((i2 - i4) + (i3 - 1) < 0);
                i2 = i4;
            }
            return i + i2;
        }
        while (true) {
            i3 = b();
            if (i <= i3) {
                if (i2 > i3) {
                    return i3;
                }
            }
        }
    }
}
