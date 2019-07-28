package io.fabric.sdk.android.services.concurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PriorityTask */
public class g implements a<i>, f, i {
    private final List<i> a = new ArrayList();
    private final AtomicBoolean b = new AtomicBoolean(false);
    private final AtomicReference<Throwable> c = new AtomicReference(null);

    public synchronized Collection<i> c() {
        return Collections.unmodifiableCollection(this.a);
    }

    /* renamed from: a */
    public synchronized void c(i iVar) {
        this.a.add(iVar);
    }

    public boolean d() {
        for (i f : c()) {
            if (!f.f()) {
                return false;
            }
        }
        return true;
    }

    public synchronized void b(boolean z) {
        this.b.set(z);
    }

    public boolean f() {
        return this.b.get();
    }

    public Priority b() {
        return Priority.NORMAL;
    }

    public void a(Throwable th) {
        this.c.set(th);
    }

    public int compareTo(Object obj) {
        return Priority.compareTo(this, obj);
    }

    public static boolean a(Object obj) {
        boolean z = false;
        try {
            a aVar = (a) obj;
            i iVar = (i) obj;
            f fVar = (f) obj;
            if (!(aVar == null || iVar == null || fVar == null)) {
                z = true;
            }
            return z;
        } catch (ClassCastException unused) {
            return false;
        }
    }
}
