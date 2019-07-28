package kotlin.jvm.internal;

import defpackage.yk;

/* compiled from: Reflection */
public class g {
    private static final h a;
    private static final yk[] b = new yk[0];

    static {
        h hVar = null;
        try {
            hVar = (h) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (hVar == null) {
            hVar = new h();
        }
        a = hVar;
    }

    public static String a(Lambda lambda) {
        return a.a(lambda);
    }
}
