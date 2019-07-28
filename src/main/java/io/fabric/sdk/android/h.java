package io.fabric.sdk.android;

import android.content.Context;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.concurrency.b;
import io.fabric.sdk.android.services.concurrency.i;
import java.io.File;
import java.util.Collection;

/* compiled from: Kit */
public abstract class h<Result> implements Comparable<h> {
    c e;
    g<Result> f = new g(this);
    Context g;
    f<Result> h;
    IdManager i;
    final b j = ((b) getClass().getAnnotation(b.class));

    public abstract String a();

    /* Access modifiers changed, original: protected */
    public void a(Result result) {
    }

    /* Access modifiers changed, original: protected */
    public boolean a_() {
        return true;
    }

    public abstract String b();

    /* Access modifiers changed, original: protected */
    public void b(Result result) {
    }

    public abstract Result e();

    /* Access modifiers changed, original: 0000 */
    public void a(Context context, c cVar, f<Result> fVar, IdManager idManager) {
        this.e = cVar;
        this.g = new d(context, b(), t());
        this.h = fVar;
        this.i = idManager;
    }

    /* Access modifiers changed, original: final */
    public final void p() {
        this.f.a(this.e.e(), (Void) null);
    }

    /* Access modifiers changed, original: protected */
    public IdManager q() {
        return this.i;
    }

    public Context r() {
        return this.g;
    }

    public c s() {
        return this.e;
    }

    public String t() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(".Fabric");
        stringBuilder.append(File.separator);
        stringBuilder.append(b());
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public int compareTo(h hVar) {
        if (b(hVar)) {
            return 1;
        }
        if (hVar.b(this)) {
            return -1;
        }
        if (u() && !hVar.u()) {
            return 1;
        }
        if (u() || !hVar.u()) {
            return 0;
        }
        return -1;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean b(h hVar) {
        if (u()) {
            for (Class isAssignableFrom : this.j.a()) {
                if (isAssignableFrom.isAssignableFrom(hVar.getClass())) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean u() {
        return this.j != null;
    }

    /* Access modifiers changed, original: protected */
    public Collection<i> v() {
        return this.f.c();
    }
}
