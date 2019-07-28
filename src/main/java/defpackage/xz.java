package defpackage;

import java.util.Random;
import kotlin.jvm.internal.f;

/* compiled from: PlatformRandom.kt */
/* renamed from: xz */
public final class xz extends xy {
    private final a c = new a();

    /* compiled from: PlatformRandom.kt */
    /* renamed from: xz$a */
    public static final class a extends ThreadLocal<Random> {
        a() {
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public Random initialValue() {
            return new Random();
        }
    }

    public Random a() {
        Object obj = this.c.get();
        f.a(obj, "implStorage.get()");
        return (Random) obj;
    }
}
