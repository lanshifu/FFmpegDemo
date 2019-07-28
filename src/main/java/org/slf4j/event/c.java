package org.slf4j.event;

import org.slf4j.helpers.d;

/* compiled from: SubstituteLoggingEvent */
public class c implements b {
    Level a;
    String b;
    d c;
    String d;
    String e;
    Object[] f;
    long g;
    Throwable h;

    public void a(Level level) {
        this.a = level;
    }

    public void a(String str) {
        this.b = str;
    }

    public d a() {
        return this.c;
    }

    public void a(d dVar) {
        this.c = dVar;
    }

    public void b(String str) {
        this.e = str;
    }

    public void a(Object[] objArr) {
        this.f = objArr;
    }

    public void a(long j) {
        this.g = j;
    }

    public void c(String str) {
        this.d = str;
    }

    public void a(Throwable th) {
        this.h = th;
    }
}
