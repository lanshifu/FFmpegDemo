package io.fabric.sdk.android.services.concurrency;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* compiled from: PriorityFutureTask */
public class e<V> extends FutureTask<V> implements a<i>, f, i {
    final Object b;

    public e(Callable<V> callable) {
        super(callable);
        this.b = a((Object) callable);
    }

    public e(Runnable runnable, V v) {
        super(runnable, v);
        this.b = a((Object) runnable);
    }

    public int compareTo(Object obj) {
        return ((f) a()).compareTo(obj);
    }

    /* renamed from: a */
    public void c(i iVar) {
        ((a) ((f) a())).c(iVar);
    }

    public Collection<i> c() {
        return ((a) ((f) a())).c();
    }

    public boolean d() {
        return ((a) ((f) a())).d();
    }

    public Priority b() {
        return ((f) a()).b();
    }

    public void b(boolean z) {
        ((i) ((f) a())).b(z);
    }

    public boolean f() {
        return ((i) ((f) a())).f();
    }

    public void a(Throwable th) {
        ((i) ((f) a())).a(th);
    }

    public <T extends a<i> & f & i> T a() {
        return (a) this.b;
    }

    /* Access modifiers changed, original: protected */
    public <T extends a<i> & f & i> T a(Object obj) {
        if (g.a(obj)) {
            return (a) obj;
        }
        return new g();
    }
}
