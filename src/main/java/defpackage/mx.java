package defpackage;

/* compiled from: DoubleUtils */
/* renamed from: mx */
public class mx {
    private static long a;

    public static boolean a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - a < 800) {
            return true;
        }
        a = currentTimeMillis;
        return false;
    }
}
