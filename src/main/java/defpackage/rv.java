package defpackage;

/* compiled from: CubicEasing */
/* renamed from: rv */
public final class rv {
    public static float a(float f, float f2, float f3, float f4) {
        f = (f / f4) - 1.0f;
        return (f3 * (((f * f) * f) + 1.0f)) + f2;
    }

    public static float b(float f, float f2, float f3, float f4) {
        f /= f4 / 2.0f;
        if (f < 1.0f) {
            f3 = (((f3 / 2.0f) * f) * f) * f;
        } else {
            f -= 2.0f;
            f3 = (f3 / 2.0f) * (((f * f) * f) + 2.0f);
        }
        return f3 + f2;
    }
}
