package org.slf4j.event;

import java.util.Queue;
import org.slf4j.Marker;
import org.slf4j.b;
import org.slf4j.helpers.d;

/* compiled from: EventRecodingLogger */
public class a implements b {
    String a;
    d b;
    Queue<c> c;

    public boolean isDebugEnabled() {
        return true;
    }

    public boolean isErrorEnabled() {
        return true;
    }

    public boolean isInfoEnabled() {
        return true;
    }

    public boolean isTraceEnabled() {
        return true;
    }

    public boolean isWarnEnabled() {
        return true;
    }

    public a(d dVar, Queue<c> queue) {
        this.b = dVar;
        this.a = dVar.a();
        this.c = queue;
    }

    private void a(Level level, String str, Object[] objArr, Throwable th) {
        a(level, null, str, objArr, th);
    }

    private void a(Level level, Marker marker, String str, Object[] objArr, Throwable th) {
        c cVar = new c();
        cVar.a(System.currentTimeMillis());
        cVar.a(level);
        cVar.a(this.b);
        cVar.a(this.a);
        cVar.b(str);
        cVar.a(objArr);
        cVar.a(th);
        cVar.c(Thread.currentThread().getName());
        this.c.add(cVar);
    }

    public void trace(String str) {
        a(Level.TRACE, str, null, null);
    }

    public void trace(String str, Object obj) {
        a(Level.TRACE, str, new Object[]{obj}, null);
    }

    public void trace(String str, Object obj, Object obj2) {
        a(Level.TRACE, str, new Object[]{obj, obj2}, null);
    }

    public void trace(String str, Object... objArr) {
        a(Level.TRACE, str, objArr, null);
    }

    public void trace(String str, Throwable th) {
        a(Level.TRACE, str, null, th);
    }

    public void debug(String str) {
        a(Level.TRACE, str, null, null);
    }

    public void debug(String str, Object obj) {
        a(Level.DEBUG, str, new Object[]{obj}, null);
    }

    public void debug(String str, Object obj, Object obj2) {
        a(Level.DEBUG, str, new Object[]{obj, obj2}, null);
    }

    public void debug(String str, Object... objArr) {
        a(Level.DEBUG, str, objArr, null);
    }

    public void debug(String str, Throwable th) {
        a(Level.DEBUG, str, null, th);
    }

    public void info(String str) {
        a(Level.INFO, str, null, null);
    }

    public void info(String str, Object obj) {
        a(Level.INFO, str, new Object[]{obj}, null);
    }

    public void info(String str, Object obj, Object obj2) {
        a(Level.INFO, str, new Object[]{obj, obj2}, null);
    }

    public void info(String str, Object... objArr) {
        a(Level.INFO, str, objArr, null);
    }

    public void info(String str, Throwable th) {
        a(Level.INFO, str, null, th);
    }

    public void warn(String str) {
        a(Level.WARN, str, null, null);
    }

    public void warn(String str, Object obj) {
        a(Level.WARN, str, new Object[]{obj}, null);
    }

    public void warn(String str, Object obj, Object obj2) {
        a(Level.WARN, str, new Object[]{obj, obj2}, null);
    }

    public void warn(String str, Object... objArr) {
        a(Level.WARN, str, objArr, null);
    }

    public void warn(String str, Throwable th) {
        a(Level.WARN, str, null, th);
    }

    public void error(String str) {
        a(Level.ERROR, str, null, null);
    }

    public void error(String str, Object obj) {
        a(Level.ERROR, str, new Object[]{obj}, null);
    }

    public void error(String str, Object obj, Object obj2) {
        a(Level.ERROR, str, new Object[]{obj, obj2}, null);
    }

    public void error(String str, Object... objArr) {
        a(Level.ERROR, str, objArr, null);
    }

    public void error(String str, Throwable th) {
        a(Level.ERROR, str, null, th);
    }
}
