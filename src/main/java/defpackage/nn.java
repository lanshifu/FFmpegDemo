package defpackage;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: PropertyValuesHolder */
/* renamed from: nn */
public class nn implements Cloneable {
    private static final no i = new nk();
    private static final no j = new ni();
    private static Class[] k = new Class[]{Float.TYPE, Float.class, Double.TYPE, Integer.TYPE, Double.class, Integer.class};
    private static Class[] l = new Class[]{Integer.TYPE, Integer.class, Float.TYPE, Double.TYPE, Float.class, Double.class};
    private static Class[] m = new Class[]{Double.TYPE, Double.class, Float.TYPE, Integer.TYPE, Float.class, Integer.class};
    private static final HashMap<Class, HashMap<String, Method>> n = new HashMap();
    private static final HashMap<Class, HashMap<String, Method>> o = new HashMap();
    String a;
    protected nq b;
    Method c;
    Class d;
    nm e;
    final ReentrantReadWriteLock f;
    final Object[] g;
    private Method h;
    private no p;
    private Object q;

    /* compiled from: PropertyValuesHolder */
    /* renamed from: nn$a */
    static class a extends nn {
        nj h;
        float i;

        public a(String str, float... fArr) {
            super(str);
            a(fArr);
        }

        public void a(float... fArr) {
            super.a(fArr);
            this.h = (nj) this.e;
        }

        /* Access modifiers changed, original: 0000 */
        public void a(float f) {
            this.i = this.h.b(f);
        }

        /* renamed from: d */
        public a clone() {
            a aVar = (a) super.clone();
            aVar.h = (nj) aVar.e;
            return aVar;
        }
    }

    private nn(String str) {
        this.c = null;
        this.h = null;
        this.e = null;
        this.f = new ReentrantReadWriteLock();
        this.g = new Object[1];
        this.a = str;
    }

    public static nn a(String str, float... fArr) {
        return new a(str, fArr);
    }

    public void a(float... fArr) {
        this.d = Float.TYPE;
        this.e = nm.a(fArr);
    }

    /* renamed from: a */
    public nn clone() {
        try {
            nn nnVar = (nn) super.clone();
            nnVar.a = this.a;
            nnVar.b = this.b;
            nnVar.e = this.e.clone();
            nnVar.p = this.p;
            return nnVar;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        if (this.p == null) {
            no noVar = this.d == Integer.class ? i : this.d == Float.class ? j : null;
            this.p = noVar;
        }
        if (this.p != null) {
            this.e.a(this.p);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(float f) {
        this.q = this.e.a(f);
    }

    public String c() {
        return this.a;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        stringBuilder.append(": ");
        stringBuilder.append(this.e.toString());
        return stringBuilder.toString();
    }
}
