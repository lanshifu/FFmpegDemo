package defpackage;

import android.view.animation.Interpolator;

/* compiled from: Keyframe */
/* renamed from: nl */
public abstract class nl implements Cloneable {
    float a;
    Class b;
    boolean c = false;
    private Interpolator d = null;

    /* compiled from: Keyframe */
    /* renamed from: nl$a */
    static class a extends nl {
        float d;

        a(float f, float f2) {
            this.a = f;
            this.d = f2;
            this.b = Float.TYPE;
            this.c = true;
        }

        a(float f) {
            this.a = f;
            this.b = Float.TYPE;
        }

        public float e() {
            return this.d;
        }

        public Object a() {
            return Float.valueOf(this.d);
        }

        /* renamed from: f */
        public a d() {
            a aVar = new a(b(), this.d);
            aVar.a(c());
            return aVar;
        }
    }

    public abstract Object a();

    /* renamed from: d */
    public abstract nl clone();

    public static nl a(float f, float f2) {
        return new a(f, f2);
    }

    public static nl a(float f) {
        return new a(f);
    }

    public float b() {
        return this.a;
    }

    public Interpolator c() {
        return this.d;
    }

    public void a(Interpolator interpolator) {
        this.d = interpolator;
    }
}
