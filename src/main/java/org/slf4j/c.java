package org.slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.helpers.d;
import org.slf4j.helpers.e;
import org.slf4j.helpers.f;
import org.slf4j.impl.b;

/* compiled from: LoggerFactory */
public final class c {
    static volatile int a;
    static e b = new e();
    static org.slf4j.helpers.c c = new org.slf4j.helpers.c();
    static boolean d = f.b("slf4j.detectLoggerNameMismatch");
    private static final String[] e = new String[]{"1.6", "1.7"};
    private static String f = "org/slf4j/impl/StaticLoggerBinder.class";

    private c() {
    }

    private static final void c() {
        d();
        if (a == 3) {
            h();
        }
    }

    private static boolean b(String str) {
        if (str == null) {
            return false;
        }
        return str.contains("org/slf4j/impl/StaticLoggerBinder") || str.contains("org.slf4j.impl.StaticLoggerBinder");
    }

    private static final void d() {
        Set set = null;
        try {
            if (!i()) {
                set = a();
                b(set);
            }
            b.a();
            a = 3;
            c(set);
            e();
            f();
            b.d();
        } catch (NoClassDefFoundError e) {
            if (b(e.getMessage())) {
                a = 4;
                f.c("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
                f.c("Defaulting to no-operation (NOP) logger implementation");
                f.c("See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.");
                return;
            }
            a(e);
            throw e;
        } catch (NoSuchMethodError e2) {
            String message = e2.getMessage();
            if (message != null && message.contains("org.slf4j.impl.StaticLoggerBinder.getSingleton()")) {
                a = 2;
                f.c("slf4j-api 1.6.x (or later) is incompatible with this binding.");
                f.c("Your binding is version 1.5.5 or earlier.");
                f.c("Upgrade your binding to version 1.6.x.");
            }
            throw e2;
        } catch (Exception e3) {
            a(e3);
            throw new IllegalStateException("Unexpected initialization failure", e3);
        }
    }

    private static void e() {
        synchronized (b) {
            b.c();
            for (d dVar : b.a()) {
                dVar.a(a(dVar.a()));
            }
        }
    }

    static void a(Throwable th) {
        a = 2;
        f.a("Failed to instantiate SLF4J LoggerFactory", th);
    }

    private static void f() {
        LinkedBlockingQueue b = b.b();
        int size = b.size();
        ArrayList<org.slf4j.event.c> arrayList = new ArrayList(128);
        int i = 0;
        while (b.drainTo(arrayList, 128) != 0) {
            for (org.slf4j.event.c cVar : arrayList) {
                a(cVar);
                int i2 = i + 1;
                if (i == 0) {
                    a(cVar, size);
                }
                i = i2;
            }
            arrayList.clear();
        }
    }

    private static void a(org.slf4j.event.c cVar, int i) {
        if (cVar.a().c()) {
            a(i);
        } else if (!cVar.a().e()) {
            g();
        }
    }

    private static void a(org.slf4j.event.c cVar) {
        if (cVar != null) {
            d a = cVar.a();
            String a2 = a.a();
            if (a.d()) {
                throw new IllegalStateException("Delegate logger cannot be null at this state.");
            }
            if (!a.e()) {
                if (a.c()) {
                    a.a((org.slf4j.event.b) cVar);
                } else {
                    f.c(a2);
                }
            }
        }
    }

    private static void g() {
        f.c("The following set of substitute loggers may have been accessed");
        f.c("during the initialization phase. Logging calls during this");
        f.c("phase were not honored. However, subsequent logging calls to these");
        f.c("loggers will work as normally expected.");
        f.c("See also http://www.slf4j.org/codes.html#substituteLogger");
    }

    private static void a(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("A number (");
        stringBuilder.append(i);
        stringBuilder.append(") of logging calls during the initialization phase have been intercepted and are");
        f.c(stringBuilder.toString());
        f.c("now being replayed. These are subject to the filtering rules of the underlying logging system.");
        f.c("See also http://www.slf4j.org/codes.html#replay");
    }

    private static final void h() {
        try {
            String str = b.a;
            Object obj = null;
            for (String startsWith : e) {
                if (str.startsWith(startsWith)) {
                    obj = 1;
                }
            }
            if (obj == null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("The requested version ");
                stringBuilder.append(str);
                stringBuilder.append(" by your slf4j binding is not compatible with ");
                stringBuilder.append(Arrays.asList(e).toString());
                f.c(stringBuilder.toString());
                f.c("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
            }
        } catch (NoSuchFieldError unused) {
        } catch (Throwable th) {
            f.a("Unexpected problem occured during version sanity check", th);
        }
    }

    static Set<URL> a() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        try {
            Enumeration systemResources;
            ClassLoader classLoader = c.class.getClassLoader();
            if (classLoader == null) {
                systemResources = ClassLoader.getSystemResources(f);
            } else {
                systemResources = classLoader.getResources(f);
            }
            while (systemResources.hasMoreElements()) {
                linkedHashSet.add((URL) systemResources.nextElement());
            }
        } catch (IOException e) {
            f.a("Error getting resources from path", e);
        }
        return linkedHashSet;
    }

    private static boolean a(Set<URL> set) {
        return set.size() > 1;
    }

    private static void b(Set<URL> set) {
        if (a((Set) set)) {
            f.c("Class path contains multiple SLF4J bindings.");
            for (URL url : set) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Found binding in [");
                stringBuilder.append(url);
                stringBuilder.append("]");
                f.c(stringBuilder.toString());
            }
            f.c("See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
        }
    }

    private static boolean i() {
        String a = f.a("java.vendor.url");
        if (a == null) {
            return false;
        }
        return a.toLowerCase().contains("android");
    }

    private static void c(Set<URL> set) {
        if (set != null && a((Set) set)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Actual binding is of type [");
            stringBuilder.append(b.a().c());
            stringBuilder.append("]");
            f.c(stringBuilder.toString());
        }
    }

    public static b a(String str) {
        return b().a(str);
    }

    public static a b() {
        if (a == 0) {
            synchronized (c.class) {
                if (a == 0) {
                    a = 1;
                    c();
                }
            }
        }
        switch (a) {
            case 1:
                return b;
            case 2:
                throw new IllegalStateException("org.slf4j.LoggerFactory could not be successfully initialized. See also http://www.slf4j.org/codes.html#unsuccessfulInit");
            case 3:
                return b.a().b();
            case 4:
                return c;
            default:
                throw new IllegalStateException("Unreachable code");
        }
    }
}
