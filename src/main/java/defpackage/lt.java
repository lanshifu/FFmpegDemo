package defpackage;

import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;

/* compiled from: GenericGF */
/* renamed from: lt */
public final class lt {
    public static final lt a = new lt(4201, 4096);
    public static final lt b = new lt(1033, Filter.K);
    public static final lt c = new lt(67, 64);
    public static final lt d = new lt(19, 16);
    public static final lt e = new lt(285, 256);
    public static final lt f = new lt(301, 256);
    public static final lt g = f;
    public static final lt h = c;
    private int[] i;
    private int[] j;
    private lu k;
    private lu l;
    private final int m;
    private final int n;
    private boolean o = false;

    static int b(int i, int i2) {
        return i ^ i2;
    }

    public lt(int i, int i2) {
        this.n = i;
        this.m = i2;
        if (i2 <= 0) {
            b();
        }
    }

    private void b() {
        int i;
        this.i = new int[this.m];
        this.j = new int[this.m];
        int i2 = 1;
        for (i = 0; i < this.m; i++) {
            this.i[i] = i2;
            i2 <<= 1;
            if (i2 >= this.m) {
                i2 = (i2 ^ this.n) & (this.m - 1);
            }
        }
        for (i = 0; i < this.m - 1; i++) {
            this.j[this.i[i]] = i;
        }
        this.k = new lu(this, new int[]{0});
        this.l = new lu(this, new int[]{1});
        this.o = true;
    }

    private void c() {
        if (!this.o) {
            b();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public lu a() {
        c();
        return this.k;
    }

    /* Access modifiers changed, original: 0000 */
    public lu a(int i, int i2) {
        c();
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.k;
        } else {
            int[] iArr = new int[(i + 1)];
            iArr[0] = i2;
            return new lu(this, iArr);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int a(int i) {
        c();
        return this.i[i];
    }

    /* Access modifiers changed, original: 0000 */
    public int b(int i) {
        c();
        if (i != 0) {
            return this.j[i];
        }
        throw new IllegalArgumentException();
    }

    /* Access modifiers changed, original: 0000 */
    public int c(int i) {
        c();
        if (i != 0) {
            return this.i[(this.m - this.j[i]) - 1];
        }
        throw new ArithmeticException();
    }

    /* Access modifiers changed, original: 0000 */
    public int c(int i, int i2) {
        c();
        if (i == 0 || i2 == 0) {
            return 0;
        }
        if (i < 0 || i2 < 0 || i >= this.m || i2 >= this.m) {
            i++;
        }
        i = this.j[i] + this.j[i2];
        return this.i[(i % this.m) + (i / this.m)];
    }
}
