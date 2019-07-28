package defpackage;

/* compiled from: AnimatorCompat */
/* renamed from: fw */
public abstract class fw {

    /* compiled from: AnimatorCompat */
    /* renamed from: fw$a */
    public interface a {
        void a(float f);
    }

    /* compiled from: AnimatorCompat */
    /* renamed from: fw$b */
    private static class b extends fw {
        private final a a;
        private final float b;

        public void a() {
        }

        public void a(int i) {
        }

        public boolean b() {
            return false;
        }

        public b(float f, float f2, a aVar) {
            this.a = aVar;
            this.b = f2;
        }

        public void c() {
            this.a.a(this.b);
        }
    }

    public abstract void a();

    public abstract void a(int i);

    public abstract boolean b();

    public abstract void c();

    fw() {
    }

    public static final fw a(float f, float f2, a aVar) {
        return new b(f, f2, aVar);
    }
}
