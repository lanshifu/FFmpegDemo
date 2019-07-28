package defpackage;

import defpackage.zn.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.e;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: DanmakuFilters */
/* renamed from: yu */
public class yu {
    public final Exception a = new Exception("not suuport this filter tag");
    e<?>[] b = new e[0];
    e<?>[] c = new e[0];
    private final Map<String, e<?>> d = Collections.synchronizedSortedMap(new TreeMap());
    private final Map<String, e<?>> e = Collections.synchronizedSortedMap(new TreeMap());

    /* compiled from: DanmakuFilters */
    /* renamed from: yu$e */
    public interface e<T> {
        void a();

        void a(T t);

        boolean a(zf zfVar, int i, int i2, zh zhVar, boolean z, DanmakuContext danmakuContext);
    }

    /* compiled from: DanmakuFilters */
    /* renamed from: yu$a */
    public static abstract class a<T> implements e<T> {
        public void a() {
        }
    }

    /* compiled from: DanmakuFilters */
    /* renamed from: yu$b */
    public static class b extends a<Void> {
        protected final zn a = new e(4);
        protected final LinkedHashMap<String, zf> b = new LinkedHashMap();
        private final zn c = new e(4);

        public void a(Void voidR) {
        }

        private final void a(zn znVar, final long j) {
            znVar.a(new c<zf>() {
                long a = aaf.a();

                public int a(zf zfVar) {
                    try {
                        if (aaf.a() - this.a <= j && zfVar.f()) {
                            return 2;
                        }
                        return 1;
                    } catch (Exception unused) {
                        return 1;
                    }
                }
            });
        }

        private void a(LinkedHashMap<String, zf> linkedHashMap, int i) {
            Iterator it = linkedHashMap.entrySet().iterator();
            long a = aaf.a();
            while (it.hasNext()) {
                try {
                    if (((zf) ((Entry) it.next()).getValue()).f()) {
                        it.remove();
                        if (aaf.a() - a > ((long) i)) {
                            return;
                        }
                    }
                    return;
                } catch (Exception unused) {
                    return;
                }
            }
        }

        public synchronized boolean a(zf zfVar, int i, int i2, zh zhVar, boolean z) {
            a(this.a, 2);
            a(this.c, 2);
            a(this.b, 3);
            if (this.a.c(zfVar) && !zfVar.g()) {
                return true;
            }
            if (this.c.c(zfVar)) {
                return false;
            }
            if (this.b.containsKey(zfVar.b)) {
                this.b.put(String.valueOf(zfVar.b), zfVar);
                this.a.b(zfVar);
                this.a.a(zfVar);
                return true;
            }
            this.b.put(String.valueOf(zfVar.b), zfVar);
            this.c.a(zfVar);
            return false;
        }

        public boolean a(zf zfVar, int i, int i2, zh zhVar, boolean z, DanmakuContext danmakuContext) {
            boolean a = a(zfVar, i, i2, zhVar, z);
            if (a) {
                zfVar.D |= 128;
            }
            return a;
        }

        public synchronized void b() {
            this.c.b();
            this.a.b();
            this.b.clear();
        }

        public void a() {
            b();
        }
    }

    /* compiled from: DanmakuFilters */
    /* renamed from: yu$c */
    public static class c extends a<Object> {
        long a = 20;

        /* JADX WARNING: Missing block: B:19:0x0022, code skipped:
            return false;
     */
        private synchronized boolean a(defpackage.zf r3, int r4, int r5, defpackage.zh r6, boolean r7) {
            /*
            r2 = this;
            monitor-enter(r2);
            r4 = 0;
            if (r6 == 0) goto L_0x0021;
        L_0x0004:
            r3 = r3.g();	 Catch:{ all -> 0x001e }
            if (r3 != 0) goto L_0x000b;
        L_0x000a:
            goto L_0x0021;
        L_0x000b:
            r0 = aaf.a();	 Catch:{ all -> 0x001e }
            r5 = r6.a;	 Catch:{ all -> 0x001e }
            r3 = 0;
            r0 = r0 - r5;
            r5 = r2.a;	 Catch:{ all -> 0x001e }
            r3 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1));
            if (r3 < 0) goto L_0x001c;
        L_0x0019:
            r3 = 1;
            monitor-exit(r2);
            return r3;
        L_0x001c:
            monitor-exit(r2);
            return r4;
        L_0x001e:
            r3 = move-exception;
            monitor-exit(r2);
            throw r3;
        L_0x0021:
            monitor-exit(r2);
            return r4;
            */
            throw new UnsupportedOperationException("Method not decompiled: defpackage.yu$c.a(zf, int, int, zh, boolean):boolean");
        }

        public boolean a(zf zfVar, int i, int i2, zh zhVar, boolean z, DanmakuContext danmakuContext) {
            boolean a = a(zfVar, i, i2, zhVar, z);
            if (a) {
                zfVar.D |= 4;
            }
            return a;
        }

        public void a(Object obj) {
            b();
        }

        public synchronized void b() {
        }

        public void a() {
            b();
        }
    }

    /* compiled from: DanmakuFilters */
    /* renamed from: yu$d */
    public static class d extends a<Boolean> {
        private Boolean a = Boolean.valueOf(false);

        public boolean a(zf zfVar, int i, int i2, zh zhVar, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = this.a.booleanValue() && zfVar.A;
            if (z2) {
                zfVar.D |= 64;
            }
            return z2;
        }

        public void a(Boolean bool) {
            this.a = bool;
        }
    }

    /* compiled from: DanmakuFilters */
    /* renamed from: yu$f */
    public static class f extends a<Map<Integer, Integer>> {
        private Map<Integer, Integer> a;

        public boolean a(zf zfVar, int i, int i2, zh zhVar, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = false;
            if (this.a != null) {
                Integer num = (Integer) this.a.get(Integer.valueOf(zfVar.o()));
                if (num != null && i >= num.intValue()) {
                    z2 = true;
                }
                if (z2) {
                    zfVar.D |= 256;
                }
            }
            return z2;
        }

        public void a(Map<Integer, Integer> map) {
            this.a = map;
        }
    }

    /* compiled from: DanmakuFilters */
    /* renamed from: yu$g */
    public static class g extends a<Map<Integer, Boolean>> {
        private Map<Integer, Boolean> a;

        public boolean a(zf zfVar, int i, int i2, zh zhVar, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = false;
            if (this.a != null) {
                Boolean bool = (Boolean) this.a.get(Integer.valueOf(zfVar.o()));
                if (bool != null && bool.booleanValue() && z) {
                    z2 = true;
                }
                if (z2) {
                    zfVar.D |= IjkMediaMeta.FF_PROFILE_H264_CONSTRAINED;
                }
            }
            return z2;
        }

        public void a(Map<Integer, Boolean> map) {
            this.a = map;
        }
    }

    /* compiled from: DanmakuFilters */
    /* renamed from: yu$h */
    public static class h extends a<Integer> {
        protected int a = -1;
        protected zf b = null;
        private float c = 1.0f;

        private boolean b(zf zfVar, int i, int i2, zh zhVar, boolean z, DanmakuContext danmakuContext) {
            if (this.a <= 0 || zfVar.o() != 1) {
                return false;
            }
            if (this.b == null || this.b.f()) {
                this.b = zfVar;
                return false;
            }
            long s = zfVar.s() - this.b.s();
            zi ziVar = danmakuContext.t.e;
            if ((s >= 0 && ziVar != null && ((float) s) < ((float) ziVar.a) * this.c) || i > this.a) {
                return true;
            }
            this.b = zfVar;
            return false;
        }

        public synchronized boolean a(zf zfVar, int i, int i2, zh zhVar, boolean z, DanmakuContext danmakuContext) {
            boolean b;
            b = b(zfVar, i, i2, zhVar, z, danmakuContext);
            if (b) {
                zfVar.D |= 2;
            }
            return b;
        }

        public void a(Integer num) {
            b();
            if (!(num == null || num.intValue() == this.a)) {
                this.a = num.intValue() + (num.intValue() / 5);
                this.c = 1.0f / ((float) this.a);
            }
        }

        public synchronized void b() {
            this.b = null;
        }

        public void a() {
            b();
        }
    }

    /* compiled from: DanmakuFilters */
    /* renamed from: yu$i */
    public static class i extends a<List<Integer>> {
        public List<Integer> a = new ArrayList();

        private void a(Integer num) {
            if (!this.a.contains(num)) {
                this.a.add(num);
            }
        }

        public boolean a(zf zfVar, int i, int i2, zh zhVar, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = (zfVar == null || this.a.contains(Integer.valueOf(zfVar.e))) ? false : true;
            if (z2) {
                zfVar.D |= 8;
            }
            return z2;
        }

        public void a(List<Integer> list) {
            b();
            if (list != null) {
                for (Integer a : list) {
                    a(a);
                }
            }
        }

        public void b() {
            this.a.clear();
        }
    }

    /* compiled from: DanmakuFilters */
    /* renamed from: yu$j */
    public static class j extends a<List<Integer>> {
        final List<Integer> a = Collections.synchronizedList(new ArrayList());

        public void a(Integer num) {
            if (!this.a.contains(num)) {
                this.a.add(num);
            }
        }

        public boolean a(zf zfVar, int i, int i2, zh zhVar, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = zfVar != null && this.a.contains(Integer.valueOf(zfVar.o()));
            if (z2) {
                zfVar.D = 1 | zfVar.D;
            }
            return z2;
        }

        public void a(List<Integer> list) {
            b();
            if (list != null) {
                for (Integer a : list) {
                    a(a);
                }
            }
        }

        public void b() {
            this.a.clear();
        }
    }

    /* compiled from: DanmakuFilters */
    /* renamed from: yu$k */
    public static abstract class k<T> extends a<List<T>> {
        public List<T> a = new ArrayList();

        private void b(T t) {
            if (!this.a.contains(t)) {
                this.a.add(t);
            }
        }

        public void a(List<T> list) {
            b();
            if (list != null) {
                for (T b : list) {
                    b(b);
                }
            }
        }

        public void b() {
            this.a.clear();
        }
    }

    /* compiled from: DanmakuFilters */
    /* renamed from: yu$l */
    public static class l extends k<String> {
        public boolean a(zf zfVar, int i, int i2, zh zhVar, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = zfVar != null && this.a.contains(zfVar.z);
            if (z2) {
                zfVar.D |= 32;
            }
            return z2;
        }
    }

    /* compiled from: DanmakuFilters */
    /* renamed from: yu$m */
    public static class m extends k<Integer> {
        public boolean a(zf zfVar, int i, int i2, zh zhVar, boolean z, DanmakuContext danmakuContext) {
            boolean z2 = zfVar != null && this.a.contains(Integer.valueOf(zfVar.y));
            if (z2) {
                zfVar.D |= 16;
            }
            return z2;
        }
    }

    public void a(zf zfVar, int i, int i2, zh zhVar, boolean z, DanmakuContext danmakuContext) {
        for (e eVar : this.b) {
            if (eVar != null) {
                boolean a = eVar.a(zfVar, i, i2, zhVar, z, danmakuContext);
                zfVar.E = danmakuContext.r.c;
                if (a) {
                    return;
                }
            } else {
                zf zfVar2 = zfVar;
                DanmakuContext danmakuContext2 = danmakuContext;
            }
        }
    }

    public boolean b(zf zfVar, int i, int i2, zh zhVar, boolean z, DanmakuContext danmakuContext) {
        for (e eVar : this.c) {
            if (eVar != null) {
                boolean a = eVar.a(zfVar, i, i2, zhVar, z, danmakuContext);
                zfVar.E = danmakuContext.r.c;
                if (a) {
                    return true;
                }
            } else {
                zf zfVar2 = zfVar;
                DanmakuContext danmakuContext2 = danmakuContext;
            }
        }
        return false;
    }

    public e<?> a(String str, boolean z) {
        e<?> eVar = (e) (z ? this.d : this.e).get(str);
        return eVar == null ? b(str, z) : eVar;
    }

    public e<?> a(String str) {
        return b(str, true);
    }

    public e<?> b(String str, boolean z) {
        if (str == null) {
            b();
            return null;
        }
        e eVar = (e) this.d.get(str);
        if (eVar == null) {
            if ("1010_Filter".equals(str)) {
                eVar = new j();
            } else if ("1011_Filter".equals(str)) {
                eVar = new h();
            } else if ("1012_Filter".equals(str)) {
                eVar = new c();
            } else if ("1013_Filter".equals(str)) {
                eVar = new i();
            } else if ("1014_Filter".equals(str)) {
                eVar = new m();
            } else if ("1015_Filter".equals(str)) {
                eVar = new l();
            } else if ("1016_Filter".equals(str)) {
                eVar = new d();
            } else if ("1017_Filter".equals(str)) {
                eVar = new b();
            } else if ("1018_Filter".equals(str)) {
                eVar = new f();
            } else if ("1019_Filter".equals(str)) {
                eVar = new g();
            }
        }
        if (eVar == null) {
            b();
            return null;
        }
        eVar.a(null);
        if (z) {
            this.d.put(str, eVar);
            this.b = (e[]) this.d.values().toArray(this.b);
        } else {
            this.e.put(str, eVar);
            this.c = (e[]) this.e.values().toArray(this.c);
        }
        return eVar;
    }

    public void b(String str) {
        c(str, true);
    }

    public void c(String str, boolean z) {
        e eVar = (e) (z ? this.d : this.e).remove(str);
        if (eVar != null) {
            eVar.a();
            if (z) {
                this.b = (e[]) this.d.values().toArray(this.b);
            } else {
                this.c = (e[]) this.e.values().toArray(this.c);
            }
        }
    }

    public void a() {
        for (e eVar : this.b) {
            if (eVar != null) {
                eVar.a();
            }
        }
        for (e eVar2 : this.c) {
            if (eVar2 != null) {
                eVar2.a();
            }
        }
    }

    private void b() {
        try {
            throw this.a;
        } catch (Exception unused) {
        }
    }
}
