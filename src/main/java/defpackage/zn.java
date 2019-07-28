package defpackage;

import java.util.Comparator;

/* compiled from: IDanmakus */
/* renamed from: zn */
public interface zn {

    /* compiled from: IDanmakus */
    /* renamed from: zn$a */
    public static class a implements Comparator<zf> {
        protected boolean a;

        public a(boolean z) {
            a(z);
        }

        public void a(boolean z) {
            this.a = z;
        }

        /* renamed from: a */
        public int compare(zf zfVar, zf zfVar2) {
            if (this.a && aae.a(zfVar, zfVar2)) {
                return 0;
            }
            return aae.b(zfVar, zfVar2);
        }
    }

    /* compiled from: IDanmakus */
    /* renamed from: zn$b */
    public static abstract class b<Progress, Result> {
        public abstract int a(Progress progress);

        public Result b() {
            return null;
        }

        public void c() {
        }

        public void d() {
        }
    }

    /* compiled from: IDanmakus */
    /* renamed from: zn$c */
    public static abstract class c<Progress> extends b<Progress, Void> {
    }

    /* compiled from: IDanmakus */
    /* renamed from: zn$d */
    public static class d extends a {
        public d(boolean z) {
            super(z);
        }

        /* renamed from: a */
        public int compare(zf zfVar, zf zfVar2) {
            return super.compare(zfVar, zfVar2);
        }
    }

    /* compiled from: IDanmakus */
    /* renamed from: zn$e */
    public static class e extends a {
        public e(boolean z) {
            super(z);
        }

        /* renamed from: a */
        public int compare(zf zfVar, zf zfVar2) {
            if (this.a && aae.a(zfVar, zfVar2)) {
                return 0;
            }
            return Float.compare(zfVar.l(), zfVar2.l());
        }
    }

    /* compiled from: IDanmakus */
    /* renamed from: zn$f */
    public static class f extends a {
        public f(boolean z) {
            super(z);
        }

        /* renamed from: a */
        public int compare(zf zfVar, zf zfVar2) {
            if (this.a && aae.a(zfVar, zfVar2)) {
                return 0;
            }
            return Float.compare(zfVar2.l(), zfVar.l());
        }
    }

    int a();

    zn a(long j, long j2);

    void a(b<? super zf, ?> bVar);

    boolean a(zf zfVar);

    zn b(long j, long j2);

    void b();

    void b(b<? super zf, ?> bVar);

    boolean b(zf zfVar);

    zf c();

    boolean c(zf zfVar);

    zf d();

    boolean e();

    Object f();
}
