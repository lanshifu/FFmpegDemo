package defpackage;

/* compiled from: Cubic */
/* renamed from: xn */
public class xn implements xo {
    public double a(double d, double d2, double d3, double d4) {
        d = (d / d4) - 1.0d;
        return (d3 * (((d * d) * d) + 1.0d)) + d2;
    }

    public double b(double d, double d2, double d3, double d4) {
        d /= d4 / 2.0d;
        if (d < 1.0d) {
            return ((((d3 / 2.0d) * d) * d) * d) + d2;
        }
        d -= 2.0d;
        return ((d3 / 2.0d) * (((d * d) * d) + 2.0d)) + d2;
    }
}
