package defpackage;

/* compiled from: progressionUtil.kt */
/* renamed from: xs */
public final class xs {
    private static final int a(int i, int i2) {
        i %= i2;
        return i >= 0 ? i : i + i2;
    }

    private static final int b(int i, int i2, int i3) {
        return xs.a(xs.a(i, i3) - xs.a(i2, i3), i3);
    }

    public static final int a(int i, int i2, int i3) {
        if (i3 > 0) {
            if (i >= i2) {
                return i2;
            }
            return i2 - xs.b(i2, i, i3);
        } else if (i3 < 0) {
            return i <= i2 ? i2 : i2 + xs.b(i, i2, -i3);
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }
}
