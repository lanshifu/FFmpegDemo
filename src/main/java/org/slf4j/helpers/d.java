package org.slf4j.helpers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Queue;
import org.slf4j.b;
import org.slf4j.event.a;
import org.slf4j.event.c;

/* compiled from: SubstituteLogger */
public class d implements b {
    private final String a;
    private volatile b b;
    private Boolean c;
    private Method d;
    private a e;
    private Queue<c> f;
    private final boolean g;

    public d(String str, Queue<c> queue, boolean z) {
        this.a = str;
        this.f = queue;
        this.g = z;
    }

    public String a() {
        return this.a;
    }

    public boolean isTraceEnabled() {
        return b().isTraceEnabled();
    }

    public void trace(String str) {
        b().trace(str);
    }

    public void trace(String str, Object obj) {
        b().trace(str, obj);
    }

    public void trace(String str, Object obj, Object obj2) {
        b().trace(str, obj, obj2);
    }

    public void trace(String str, Object... objArr) {
        b().trace(str, objArr);
    }

    public void trace(String str, Throwable th) {
        b().trace(str, th);
    }

    public boolean isDebugEnabled() {
        return b().isDebugEnabled();
    }

    public void debug(String str) {
        b().debug(str);
    }

    public void debug(String str, Object obj) {
        b().debug(str, obj);
    }

    public void debug(String str, Object obj, Object obj2) {
        b().debug(str, obj, obj2);
    }

    public void debug(String str, Object... objArr) {
        b().debug(str, objArr);
    }

    public void debug(String str, Throwable th) {
        b().debug(str, th);
    }

    public boolean isInfoEnabled() {
        return b().isInfoEnabled();
    }

    public void info(String str) {
        b().info(str);
    }

    public void info(String str, Object obj) {
        b().info(str, obj);
    }

    public void info(String str, Object obj, Object obj2) {
        b().info(str, obj, obj2);
    }

    public void info(String str, Object... objArr) {
        b().info(str, objArr);
    }

    public void info(String str, Throwable th) {
        b().info(str, th);
    }

    public boolean isWarnEnabled() {
        return b().isWarnEnabled();
    }

    public void warn(String str) {
        b().warn(str);
    }

    public void warn(String str, Object obj) {
        b().warn(str, obj);
    }

    public void warn(String str, Object obj, Object obj2) {
        b().warn(str, obj, obj2);
    }

    public void warn(String str, Object... objArr) {
        b().warn(str, objArr);
    }

    public void warn(String str, Throwable th) {
        b().warn(str, th);
    }

    public boolean isErrorEnabled() {
        return b().isErrorEnabled();
    }

    public void error(String str) {
        b().error(str);
    }

    public void error(String str, Object obj) {
        b().error(str, obj);
    }

    public void error(String str, Object obj, Object obj2) {
        b().error(str, obj, obj2);
    }

    public void error(String str, Object... objArr) {
        b().error(str, objArr);
    }

    public void error(String str, Throwable th) {
        b().error(str, th);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((d) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    /* Access modifiers changed, original: 0000 */
    public b b() {
        if (this.b != null) {
            return this.b;
        }
        if (this.g) {
            return NOPLogger.NOP_LOGGER;
        }
        return f();
    }

    private b f() {
        if (this.e == null) {
            this.e = new a(this, this.f);
        }
        return this.e;
    }

    public void a(b bVar) {
        this.b = bVar;
    }

    public boolean c() {
        if (this.c != null) {
            return this.c.booleanValue();
        }
        try {
            this.d = this.b.getClass().getMethod("log", new Class[]{org.slf4j.event.b.class});
            this.c = Boolean.TRUE;
        } catch (NoSuchMethodException unused) {
            this.c = Boolean.FALSE;
        }
        return this.c.booleanValue();
    }

    public void a(org.slf4j.event.b bVar) {
        if (c()) {
            try {
                this.d.invoke(this.b, new Object[]{bVar});
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
            }
        }
    }

    public boolean d() {
        return this.b == null;
    }

    public boolean e() {
        return this.b instanceof NOPLogger;
    }
}
