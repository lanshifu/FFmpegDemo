package org.slf4j.impl;

import android.util.Log;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.a;
import org.slf4j.helpers.b;

class AndroidLoggerAdapter extends MarkerIgnoringBase {
    private static final long serialVersionUID = -1227274521521287937L;

    AndroidLoggerAdapter(String str) {
        this.name = str;
    }

    public boolean isTraceEnabled() {
        return a(2);
    }

    public void trace(String str) {
        a(2, str, null);
    }

    public void trace(String str, Object obj) {
        a(2, str, obj);
    }

    public void trace(String str, Object obj, Object obj2) {
        a(2, str, obj, obj2);
    }

    public void trace(String str, Object... objArr) {
        a(2, str, objArr);
    }

    public void trace(String str, Throwable th) {
        a(2, str, th);
    }

    public boolean isDebugEnabled() {
        return a(3);
    }

    public void debug(String str) {
        a(3, str, null);
    }

    public void debug(String str, Object obj) {
        a(3, str, obj);
    }

    public void debug(String str, Object obj, Object obj2) {
        a(3, str, obj, obj2);
    }

    public void debug(String str, Object... objArr) {
        a(3, str, objArr);
    }

    public void debug(String str, Throwable th) {
        a(2, str, th);
    }

    public boolean isInfoEnabled() {
        return a(4);
    }

    public void info(String str) {
        a(4, str, null);
    }

    public void info(String str, Object obj) {
        a(4, str, obj);
    }

    public void info(String str, Object obj, Object obj2) {
        a(4, str, obj, obj2);
    }

    public void info(String str, Object... objArr) {
        a(4, str, objArr);
    }

    public void info(String str, Throwable th) {
        a(4, str, th);
    }

    public boolean isWarnEnabled() {
        return a(5);
    }

    public void warn(String str) {
        a(5, str, null);
    }

    public void warn(String str, Object obj) {
        a(5, str, obj);
    }

    public void warn(String str, Object obj, Object obj2) {
        a(5, str, obj, obj2);
    }

    public void warn(String str, Object... objArr) {
        a(5, str, objArr);
    }

    public void warn(String str, Throwable th) {
        a(5, str, th);
    }

    public boolean isErrorEnabled() {
        return a(6);
    }

    public void error(String str) {
        a(6, str, null);
    }

    public void error(String str, Object obj) {
        a(6, str, obj);
    }

    public void error(String str, Object obj, Object obj2) {
        a(6, str, obj, obj2);
    }

    public void error(String str, Object... objArr) {
        a(6, str, objArr);
    }

    public void error(String str, Throwable th) {
        a(6, str, th);
    }

    private void a(int i, String str, Object... objArr) {
        if (a(i)) {
            a a = b.a(str, objArr);
            b(i, a.a(), a.b());
        }
    }

    private void a(int i, String str, Throwable th) {
        if (a(i)) {
            b(i, str, th);
        }
    }

    private boolean a(int i) {
        return Log.isLoggable(this.name, i);
    }

    private void b(int i, String str, Throwable th) {
        if (th != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(10);
            stringBuilder.append(Log.getStackTraceString(th));
            str = stringBuilder.toString();
        }
        Log.println(i, this.name, str);
    }
}
