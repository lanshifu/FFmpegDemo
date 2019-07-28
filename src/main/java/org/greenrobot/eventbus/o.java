package org.greenrobot.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: SubscriberMethodFinder */
class o {
    private static final Map<Class<?>, List<n>> a = new ConcurrentHashMap();
    private static final a[] e = new a[4];
    private List<aap> b;
    private final boolean c;
    private final boolean d;

    /* compiled from: SubscriberMethodFinder */
    static class a {
        final List<n> a = new ArrayList();
        final Map<Class, Object> b = new HashMap();
        final Map<String, Class> c = new HashMap();
        final StringBuilder d = new StringBuilder(128);
        Class<?> e;
        Class<?> f;
        boolean g;
        aao h;

        a() {
        }

        /* Access modifiers changed, original: 0000 */
        public void a(Class<?> cls) {
            this.f = cls;
            this.e = cls;
            this.g = false;
            this.h = null;
        }

        /* Access modifiers changed, original: 0000 */
        public void a() {
            this.a.clear();
            this.b.clear();
            this.c.clear();
            this.d.setLength(0);
            this.e = null;
            this.f = null;
            this.g = false;
            this.h = null;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean a(Method method, Class<?> cls) {
            Object put = this.b.put(cls, method);
            if (put == null) {
                return true;
            }
            if (put instanceof Method) {
                if (b((Method) put, cls)) {
                    this.b.put(cls, this);
                } else {
                    throw new IllegalStateException();
                }
            }
            return b(method, cls);
        }

        private boolean b(Method method, Class<?> cls) {
            this.d.setLength(0);
            this.d.append(method.getName());
            StringBuilder stringBuilder = this.d;
            stringBuilder.append('>');
            stringBuilder.append(cls.getName());
            String stringBuilder2 = this.d.toString();
            Class declaringClass = method.getDeclaringClass();
            Class cls2 = (Class) this.c.put(stringBuilder2, declaringClass);
            if (cls2 == null || cls2.isAssignableFrom(declaringClass)) {
                return true;
            }
            this.c.put(stringBuilder2, cls2);
            return false;
        }

        /* Access modifiers changed, original: 0000 */
        public void b() {
            if (this.g) {
                this.f = null;
                return;
            }
            this.f = this.f.getSuperclass();
            String name = this.f.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                this.f = null;
            }
        }
    }

    o(List<aap> list, boolean z, boolean z2) {
        this.b = list;
        this.c = z;
        this.d = z2;
    }

    /* Access modifiers changed, original: 0000 */
    public List<n> a(Class<?> cls) {
        List list = (List) a.get(cls);
        if (list != null) {
            return list;
        }
        if (this.d) {
            list = c((Class) cls);
        } else {
            list = b((Class) cls);
        }
        if (list.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Subscriber ");
            stringBuilder.append(cls);
            stringBuilder.append(" and its super classes have no public methods with the @Subscribe annotation");
            throw new EventBusException(stringBuilder.toString());
        }
        a.put(cls, list);
        return list;
    }

    private List<n> b(Class<?> cls) {
        a a = a();
        a.a(cls);
        while (a.f != null) {
            a.h = b(a);
            if (a.h != null) {
                for (n nVar : a.h.b()) {
                    if (a.a(nVar.a, nVar.c)) {
                        a.a.add(nVar);
                    }
                }
            } else {
                c(a);
            }
            a.b();
        }
        return a(a);
    }

    private List<n> a(a aVar) {
        ArrayList arrayList = new ArrayList(aVar.a);
        aVar.a();
        synchronized (e) {
            int i = 0;
            while (i < 4) {
                try {
                    if (e[i] == null) {
                        e[i] = aVar;
                        break;
                    }
                    i++;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return arrayList;
    }

    private a a() {
        synchronized (e) {
            int i = 0;
            while (i < 4) {
                try {
                    a aVar = e[i];
                    if (aVar != null) {
                        e[i] = null;
                        return aVar;
                    }
                    i++;
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            return new a();
        }
    }

    private aao b(a aVar) {
        if (!(aVar.h == null || aVar.h.c() == null)) {
            aao c = aVar.h.c();
            if (aVar.f == c.a()) {
                return c;
            }
        }
        if (this.b != null) {
            for (aap a : this.b) {
                aao a2 = a.a(aVar.f);
                if (a2 != null) {
                    return a2;
                }
            }
        }
        return null;
    }

    private List<n> c(Class<?> cls) {
        a a = a();
        a.a(cls);
        while (a.f != null) {
            c(a);
            a.b();
        }
        return a(a);
    }

    private void c(a aVar) {
        Method[] declaredMethods;
        try {
            declaredMethods = aVar.f.getDeclaredMethods();
        } catch (Throwable unused) {
            declaredMethods = aVar.f.getMethods();
            aVar.g = true;
        }
        for (Method method : declaredMethods) {
            int modifiers = method.getModifiers();
            StringBuilder stringBuilder;
            String stringBuilder2;
            StringBuilder stringBuilder3;
            if ((modifiers & 1) != 0 && (modifiers & 5192) == 0) {
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1) {
                    l lVar = (l) method.getAnnotation(l.class);
                    if (lVar != null) {
                        Class cls = parameterTypes[0];
                        if (aVar.a(method, cls)) {
                            aVar.a.add(new n(method, cls, lVar.a(), lVar.c(), lVar.b()));
                        }
                    }
                } else if (this.c && method.isAnnotationPresent(l.class)) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(method.getDeclaringClass().getName());
                    stringBuilder.append(".");
                    stringBuilder.append(method.getName());
                    stringBuilder2 = stringBuilder.toString();
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("@Subscribe method ");
                    stringBuilder3.append(stringBuilder2);
                    stringBuilder3.append("must have exactly 1 parameter but has ");
                    stringBuilder3.append(parameterTypes.length);
                    throw new EventBusException(stringBuilder3.toString());
                }
            } else if (this.c && method.isAnnotationPresent(l.class)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(method.getDeclaringClass().getName());
                stringBuilder.append(".");
                stringBuilder.append(method.getName());
                stringBuilder2 = stringBuilder.toString();
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(stringBuilder2);
                stringBuilder3.append(" is a illegal @Subscribe method: must be public, non-static, and non-abstract");
                throw new EventBusException(stringBuilder3.toString());
            }
        }
    }
}
