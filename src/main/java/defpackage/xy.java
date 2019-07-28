package defpackage;

import java.util.Random;

/* compiled from: PlatformRandom.kt */
/* renamed from: xy */
public abstract class xy extends yb {
    public abstract Random a();

    public int a(int i) {
        return yc.a(a().nextInt(), i);
    }

    public int b() {
        return a().nextInt();
    }
}
