package io.fabric.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import io.fabric.sdk.android.a.b;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.common.k;
import io.fabric.sdk.android.services.concurrency.UnmetDependencyException;
import io.fabric.sdk.android.services.concurrency.h;
import io.fabric.sdk.android.services.concurrency.i;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: Fabric */
public class c {
    static volatile c a;
    static final k b = new b();
    final k c;
    final boolean d;
    private final Context e;
    private final Map<Class<? extends h>, h> f;
    private final ExecutorService g;
    private final Handler h;
    private final f<c> i;
    private final f<?> j;
    private final IdManager k;
    private a l;
    private WeakReference<Activity> m;
    private AtomicBoolean n = new AtomicBoolean(false);

    /* compiled from: Fabric */
    public static class a {
        private final Context a;
        private h[] b;
        private h c;
        private Handler d;
        private k e;
        private boolean f;
        private String g;
        private String h;
        private f<c> i;

        public a(Context context) {
            if (context != null) {
                this.a = context;
                return;
            }
            throw new IllegalArgumentException("Context must not be null.");
        }

        public a a(h... hVarArr) {
            if (this.b == null) {
                if (!k.a(this.a).a()) {
                    ArrayList arrayList = new ArrayList();
                    Object obj = null;
                    for (h hVar : hVarArr) {
                        String b = hVar.b();
                        Object obj2 = -1;
                        int hashCode = b.hashCode();
                        if (hashCode != 607220212) {
                            if (hashCode == 1830452504 && b.equals("com.crashlytics.sdk.android:crashlytics")) {
                                obj2 = null;
                            }
                        } else if (b.equals("com.crashlytics.sdk.android:answers")) {
                            obj2 = 1;
                        }
                        switch (obj2) {
                            case null:
                            case 1:
                                arrayList.add(hVar);
                                break;
                            default:
                                if (obj != null) {
                                    break;
                                }
                                c.g().d("Fabric", "Fabric will not initialize any kits when Firebase automatic data collection is disabled; to use Third-party kits with automatic data collection disabled, initialize these kits via non-Fabric means.");
                                obj = 1;
                                break;
                        }
                    }
                    hVarArr = (h[]) arrayList.toArray(new h[0]);
                }
                this.b = hVarArr;
                return this;
            }
            throw new IllegalStateException("Kits already set.");
        }

        public c a() {
            HashMap hashMap;
            if (this.c == null) {
                this.c = h.a();
            }
            if (this.d == null) {
                this.d = new Handler(Looper.getMainLooper());
            }
            if (this.e == null) {
                if (this.f) {
                    this.e = new b(3);
                } else {
                    this.e = new b();
                }
            }
            if (this.h == null) {
                this.h = this.a.getPackageName();
            }
            if (this.i == null) {
                this.i = f.d;
            }
            if (this.b == null) {
                hashMap = new HashMap();
            } else {
                hashMap = c.b(Arrays.asList(this.b));
            }
            HashMap hashMap2 = hashMap;
            Context applicationContext = this.a.getApplicationContext();
            return new c(applicationContext, hashMap2, this.c, this.d, this.e, this.f, this.i, new IdManager(applicationContext, this.h, this.g, hashMap2.values()), c.d(this.a));
        }
    }

    public String c() {
        return "1.4.8.32";
    }

    public String d() {
        return "io.fabric.sdk.android:fabric";
    }

    static c a() {
        if (a != null) {
            return a;
        }
        throw new IllegalStateException("Must Initialize Fabric before using singleton()");
    }

    c(Context context, Map<Class<? extends h>, h> map, h hVar, Handler handler, k kVar, boolean z, f fVar, IdManager idManager, Activity activity) {
        this.e = context;
        this.f = map;
        this.g = hVar;
        this.h = handler;
        this.c = kVar;
        this.d = z;
        this.i = fVar;
        this.j = a(map.size());
        this.k = idManager;
        a(activity);
    }

    public static c a(Context context, h... hVarArr) {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    c(new a(context).a(hVarArr).a());
                }
            }
        }
        return a;
    }

    private static void c(c cVar) {
        a = cVar;
        cVar.i();
    }

    public c a(Activity activity) {
        this.m = new WeakReference(activity);
        return this;
    }

    public Activity b() {
        return this.m != null ? (Activity) this.m.get() : null;
    }

    private void i() {
        this.l = new a(this.e);
        this.l.a(new b() {
            public void a(Activity activity, Bundle bundle) {
                c.this.a(activity);
            }

            public void a(Activity activity) {
                c.this.a(activity);
            }

            public void b(Activity activity) {
                c.this.a(activity);
            }
        });
        a(this.e);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(Context context) {
        StringBuilder stringBuilder;
        Future b = b(context);
        Collection f = f();
        l lVar = new l(b, f);
        ArrayList<h> arrayList = new ArrayList(f);
        Collections.sort(arrayList);
        lVar.a(context, this, f.d, this.k);
        for (h a : arrayList) {
            a.a(context, this, this.j, this.k);
        }
        lVar.p();
        if (g().a("Fabric", 3)) {
            stringBuilder = new StringBuilder("Initializing ");
            stringBuilder.append(d());
            stringBuilder.append(" [Version: ");
            stringBuilder.append(c());
            stringBuilder.append("], with the following kits:\n");
        } else {
            stringBuilder = null;
        }
        for (h hVar : arrayList) {
            hVar.f.a((i) lVar.f);
            a(this.f, hVar);
            hVar.p();
            if (stringBuilder != null) {
                stringBuilder.append(hVar.b());
                stringBuilder.append(" [Version: ");
                stringBuilder.append(hVar.a());
                stringBuilder.append("]\n");
            }
        }
        if (stringBuilder != null) {
            g().a("Fabric", stringBuilder.toString());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(Map<Class<? extends h>, h> map, h hVar) {
        io.fabric.sdk.android.services.concurrency.b bVar = hVar.j;
        if (bVar != null) {
            for (Class cls : bVar.a()) {
                if (cls.isInterface()) {
                    for (h hVar2 : map.values()) {
                        if (cls.isAssignableFrom(hVar2.getClass())) {
                            hVar.f.a((i) hVar2.f);
                        }
                    }
                } else if (((h) map.get(cls)) != null) {
                    hVar.f.a((i) ((h) map.get(cls)).f);
                } else {
                    throw new UnmetDependencyException("Referenced Kit was null, does the kit exist?");
                }
            }
        }
    }

    private static Activity d(Context context) {
        return context instanceof Activity ? (Activity) context : null;
    }

    public ExecutorService e() {
        return this.g;
    }

    public Collection<h> f() {
        return this.f.values();
    }

    public static <T extends h> T a(Class<T> cls) {
        return (h) a().f.get(cls);
    }

    public static k g() {
        if (a == null) {
            return b;
        }
        return a.c;
    }

    public static boolean h() {
        if (a == null) {
            return false;
        }
        return a.d;
    }

    private static Map<Class<? extends h>, h> b(Collection<? extends h> collection) {
        Map hashMap = new HashMap(collection.size());
        a(hashMap, (Collection) collection);
        return hashMap;
    }

    private static void a(Map<Class<? extends h>, h> map, Collection<? extends h> collection) {
        for (h hVar : collection) {
            map.put(hVar.getClass(), hVar);
            if (hVar instanceof i) {
                a((Map) map, ((i) hVar).c());
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public f<?> a(final int i) {
        return new f() {
            final CountDownLatch a = new CountDownLatch(i);

            public void a(Object obj) {
                this.a.countDown();
                if (this.a.getCount() == 0) {
                    c.this.n.set(true);
                    c.this.i.a(c.this);
                }
            }

            public void a(Exception exception) {
                c.this.i.a(exception);
            }
        };
    }

    /* Access modifiers changed, original: 0000 */
    public Future<Map<String, j>> b(Context context) {
        return e().submit(new e(context.getPackageCodePath()));
    }
}
