package org.greenrobot.eventbus;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;

/* compiled from: EventBus */
public class c {
    public static String a = "EventBus";
    static volatile c b;
    private static final d c = new d();
    private static final Map<Class<?>, List<Class<?>>> d = new HashMap();
    private final Map<Class<?>, CopyOnWriteArrayList<p>> e;
    private final Map<Object, List<Class<?>>> f;
    private final Map<Class<?>, Object> g;
    private final ThreadLocal<a> h;
    private final g i;
    private final k j;
    private final b k;
    private final a l;
    private final o m;
    private final ExecutorService n;
    private final boolean o;
    private final boolean p;
    private final boolean q;
    private final boolean r;
    private final boolean s;
    private final boolean t;
    private final int u;
    private final f v;

    /* compiled from: EventBus */
    /* renamed from: org.greenrobot.eventbus.c$2 */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[ThreadMode.values().length];

        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Missing block: B:13:?, code skipped:
            return;
     */
        static {
            /*
            r0 = org.greenrobot.eventbus.ThreadMode.values();
            r0 = r0.length;
            r0 = new int[r0];
            a = r0;
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = org.greenrobot.eventbus.ThreadMode.POSTING;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = 1;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = org.greenrobot.eventbus.ThreadMode.MAIN;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
            r2 = 2;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x001f }
        L_0x001f:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x002a }
            r1 = org.greenrobot.eventbus.ThreadMode.MAIN_ORDERED;	 Catch:{ NoSuchFieldError -> 0x002a }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x002a }
            r2 = 3;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x002a }
        L_0x002a:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0035 }
            r1 = org.greenrobot.eventbus.ThreadMode.BACKGROUND;	 Catch:{ NoSuchFieldError -> 0x0035 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0035 }
            r2 = 4;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0035 }
        L_0x0035:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0040 }
            r1 = org.greenrobot.eventbus.ThreadMode.ASYNC;	 Catch:{ NoSuchFieldError -> 0x0040 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0040 }
            r2 = 5;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0040 }
        L_0x0040:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: org.greenrobot.eventbus.c$AnonymousClass2.<clinit>():void");
        }
    }

    /* compiled from: EventBus */
    static final class a {
        final List<Object> a = new ArrayList();
        boolean b;
        boolean c;
        p d;
        Object e;
        boolean f;

        a() {
        }
    }

    public static c a() {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
        }
        return b;
    }

    public c() {
        this(c);
    }

    c(d dVar) {
        this.h = new ThreadLocal<a>() {
            /* Access modifiers changed, original: protected */
            /* renamed from: a */
            public a initialValue() {
                return new a();
            }
        };
        this.v = dVar.a();
        this.e = new HashMap();
        this.f = new HashMap();
        this.g = new ConcurrentHashMap();
        this.i = dVar.b();
        this.j = this.i != null ? this.i.a(this) : null;
        this.k = new b(this);
        this.l = new a(this);
        this.u = dVar.j != null ? dVar.j.size() : 0;
        this.m = new o(dVar.j, dVar.h, dVar.g);
        this.p = dVar.a;
        this.q = dVar.b;
        this.r = dVar.c;
        this.s = dVar.d;
        this.o = dVar.e;
        this.t = dVar.f;
        this.n = dVar.i;
    }

    public void a(Object obj) {
        List<n> a = this.m.a(obj.getClass());
        synchronized (this) {
            for (n a2 : a) {
                a(obj, a2);
            }
        }
    }

    private void a(Object obj, n nVar) {
        Class cls = nVar.c;
        p pVar = new p(obj, nVar);
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.e.get(cls);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList();
            this.e.put(cls, copyOnWriteArrayList);
        } else if (copyOnWriteArrayList.contains(pVar)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Subscriber ");
            stringBuilder.append(obj.getClass());
            stringBuilder.append(" already registered to event ");
            stringBuilder.append(cls);
            throw new EventBusException(stringBuilder.toString());
        }
        int size = copyOnWriteArrayList.size();
        int i = 0;
        while (i <= size) {
            if (i == size || nVar.d > ((p) copyOnWriteArrayList.get(i)).b.d) {
                copyOnWriteArrayList.add(i, pVar);
                break;
            }
            i++;
        }
        List list = (List) this.f.get(obj);
        if (list == null) {
            list = new ArrayList();
            this.f.put(obj, list);
        }
        list.add(cls);
        if (!nVar.e) {
            return;
        }
        if (this.t) {
            for (Entry entry : this.g.entrySet()) {
                if (cls.isAssignableFrom((Class) entry.getKey())) {
                    b(pVar, entry.getValue());
                }
            }
            return;
        }
        b(pVar, this.g.get(cls));
    }

    private void b(p pVar, Object obj) {
        if (obj != null) {
            a(pVar, obj, d());
        }
    }

    private boolean d() {
        return this.i != null ? this.i.a() : true;
    }

    private void a(Object obj, Class<?> cls) {
        List list = (List) this.e.get(cls);
        if (list != null) {
            int size = list.size();
            int i = 0;
            while (i < size) {
                p pVar = (p) list.get(i);
                if (pVar.a == obj) {
                    pVar.c = false;
                    list.remove(i);
                    i--;
                    size--;
                }
                i++;
            }
        }
    }

    public synchronized void b(Object obj) {
        List<Class> list = (List) this.f.get(obj);
        if (list != null) {
            for (Class a : list) {
                a(obj, a);
            }
            this.f.remove(obj);
        } else {
            f fVar = this.v;
            Level level = Level.WARNING;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Subscriber to unregister was not registered before: ");
            stringBuilder.append(obj.getClass());
            fVar.a(level, stringBuilder.toString());
        }
    }

    public void c(Object obj) {
        a aVar = (a) this.h.get();
        List list = aVar.a;
        list.add(obj);
        if (!aVar.b) {
            aVar.c = d();
            aVar.b = true;
            if (aVar.f) {
                throw new EventBusException("Internal error. Abort state was not reset");
            }
            while (!list.isEmpty()) {
                try {
                    a(list.remove(0), aVar);
                } finally {
                    aVar.b = false;
                    aVar.c = false;
                }
            }
        }
    }

    public void d(Object obj) {
        synchronized (this.g) {
            this.g.put(obj.getClass(), obj);
        }
        c(obj);
    }

    private void a(Object obj, a aVar) throws Error {
        int i;
        Class cls = obj.getClass();
        if (this.t) {
            List a = a(cls);
            i = 0;
            for (int i2 = 0; i2 < a.size(); i2++) {
                i |= a(obj, aVar, (Class) a.get(i2));
            }
        } else {
            i = a(obj, aVar, cls);
        }
        if (i == 0) {
            if (this.q) {
                f fVar = this.v;
                Level level = Level.FINE;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("No subscribers registered for event ");
                stringBuilder.append(cls);
                fVar.a(level, stringBuilder.toString());
            }
            if (this.s && cls != h.class && cls != m.class) {
                c(new h(this, obj));
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0040 A:{SYNTHETIC, EDGE_INSN: B:24:0x0040->B:17:0x0040 ?: BREAK  } */
    private boolean a(java.lang.Object r5, org.greenrobot.eventbus.c.a r6, java.lang.Class<?> r7) {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = r4.e;	 Catch:{ all -> 0x0043 }
        r7 = r0.get(r7);	 Catch:{ all -> 0x0043 }
        r7 = (java.util.concurrent.CopyOnWriteArrayList) r7;	 Catch:{ all -> 0x0043 }
        monitor-exit(r4);	 Catch:{ all -> 0x0043 }
        r0 = 0;
        if (r7 == 0) goto L_0x0042;
    L_0x000d:
        r1 = r7.isEmpty();
        if (r1 != 0) goto L_0x0042;
    L_0x0013:
        r7 = r7.iterator();
    L_0x0017:
        r1 = r7.hasNext();
        if (r1 == 0) goto L_0x0040;
    L_0x001d:
        r1 = r7.next();
        r1 = (org.greenrobot.eventbus.p) r1;
        r6.e = r5;
        r6.d = r1;
        r2 = 0;
        r3 = r6.c;	 Catch:{ all -> 0x0038 }
        r4.a(r1, r5, r3);	 Catch:{ all -> 0x0038 }
        r1 = r6.f;	 Catch:{ all -> 0x0038 }
        r6.e = r2;
        r6.d = r2;
        r6.f = r0;
        if (r1 == 0) goto L_0x0017;
    L_0x0037:
        goto L_0x0040;
    L_0x0038:
        r5 = move-exception;
        r6.e = r2;
        r6.d = r2;
        r6.f = r0;
        throw r5;
    L_0x0040:
        r5 = 1;
        return r5;
    L_0x0042:
        return r0;
    L_0x0043:
        r5 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0043 }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.greenrobot.eventbus.c.a(java.lang.Object, org.greenrobot.eventbus.c$a, java.lang.Class):boolean");
    }

    private void a(p pVar, Object obj, boolean z) {
        switch (AnonymousClass2.a[pVar.b.b.ordinal()]) {
            case 1:
                a(pVar, obj);
                return;
            case 2:
                if (z) {
                    a(pVar, obj);
                    return;
                } else {
                    this.j.a(pVar, obj);
                    return;
                }
            case 3:
                if (this.j != null) {
                    this.j.a(pVar, obj);
                    return;
                } else {
                    a(pVar, obj);
                    return;
                }
            case 4:
                if (z) {
                    this.k.a(pVar, obj);
                    return;
                } else {
                    a(pVar, obj);
                    return;
                }
            case 5:
                this.l.a(pVar, obj);
                return;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unknown thread mode: ");
                stringBuilder.append(pVar.b.b);
                throw new IllegalStateException(stringBuilder.toString());
        }
    }

    private static List<Class<?>> a(Class<?> cls) {
        List<Class<?>> list;
        synchronized (d) {
            list = (List) d.get(cls);
            if (list == null) {
                list = new ArrayList();
                for (Object obj = cls; obj != null; obj = obj.getSuperclass()) {
                    list.add(obj);
                    a((List) list, obj.getInterfaces());
                }
                d.put(cls, list);
            }
        }
        return list;
    }

    static void a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Object obj : clsArr) {
            if (!list.contains(obj)) {
                list.add(obj);
                a((List) list, obj.getInterfaces());
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(i iVar) {
        Object obj = iVar.a;
        p pVar = iVar.b;
        i.a(iVar);
        if (pVar.c) {
            a(pVar, obj);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(p pVar, Object obj) {
        try {
            pVar.b.a.invoke(pVar.a, new Object[]{obj});
        } catch (InvocationTargetException e) {
            a(pVar, obj, e.getCause());
        } catch (IllegalAccessException e2) {
            throw new IllegalStateException("Unexpected exception", e2);
        }
    }

    private void a(p pVar, Object obj, Throwable th) {
        f fVar;
        Level level;
        StringBuilder stringBuilder;
        if (obj instanceof m) {
            if (this.p) {
                fVar = this.v;
                level = Level.SEVERE;
                stringBuilder = new StringBuilder();
                stringBuilder.append("SubscriberExceptionEvent subscriber ");
                stringBuilder.append(pVar.a.getClass());
                stringBuilder.append(" threw an exception");
                fVar.a(level, stringBuilder.toString(), th);
                m mVar = (m) obj;
                f fVar2 = this.v;
                Level level2 = Level.SEVERE;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Initial event ");
                stringBuilder2.append(mVar.c);
                stringBuilder2.append(" caused exception in ");
                stringBuilder2.append(mVar.d);
                fVar2.a(level2, stringBuilder2.toString(), mVar.b);
            }
        } else if (this.o) {
            throw new EventBusException("Invoking subscriber failed", th);
        } else {
            if (this.p) {
                fVar = this.v;
                level = Level.SEVERE;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Could not dispatch event: ");
                stringBuilder.append(obj.getClass());
                stringBuilder.append(" to subscribing class ");
                stringBuilder.append(pVar.a.getClass());
                fVar.a(level, stringBuilder.toString(), th);
            }
            if (this.r) {
                c(new m(this, th, obj, pVar.a));
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public ExecutorService b() {
        return this.n;
    }

    public f c() {
        return this.v;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("EventBus[indexCount=");
        stringBuilder.append(this.u);
        stringBuilder.append(", eventInheritance=");
        stringBuilder.append(this.t);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
