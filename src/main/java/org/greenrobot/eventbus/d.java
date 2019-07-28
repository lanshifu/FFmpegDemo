package org.greenrobot.eventbus;

import android.os.Looper;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.greenrobot.eventbus.f.a;
import org.greenrobot.eventbus.f.b;

/* compiled from: EventBusBuilder */
public class d {
    private static final ExecutorService m = Executors.newCachedThreadPool();
    boolean a = true;
    boolean b = true;
    boolean c = true;
    boolean d = true;
    boolean e;
    boolean f = true;
    boolean g;
    boolean h;
    ExecutorService i = m;
    List<aap> j;
    f k;
    g l;

    d() {
    }

    /* Access modifiers changed, original: 0000 */
    public f a() {
        if (this.k != null) {
            return this.k;
        }
        f bVar = (!a.a() || c() == null) ? new b() : new a("EventBus");
        return bVar;
    }

    /* Access modifiers changed, original: 0000 */
    public g b() {
        if (this.l != null) {
            return this.l;
        }
        g gVar = null;
        if (!a.a()) {
            return null;
        }
        Object c = c();
        if (c != null) {
            gVar = new g.a((Looper) c);
        }
        return gVar;
    }

    /* Access modifiers changed, original: 0000 */
    public Object c() {
        try {
            return Looper.getMainLooper();
        } catch (RuntimeException unused) {
            return null;
        }
    }
}
