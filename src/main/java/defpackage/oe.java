package defpackage;

import android.content.Context;
import com.one.tomato.mvp.base.view.a;

/* compiled from: MvpBasePresenter.kt */
/* renamed from: oe */
public abstract class oe<V extends a> {
    private V a;

    public abstract void e();

    /* Access modifiers changed, original: protected|final */
    public final V a() {
        return this.a;
    }

    public final void a(V v) {
        this.a = v;
    }

    public final void b() {
        if (this.a != null) {
            this.a = (a) null;
        }
    }

    public final Context c() {
        a aVar = this.a;
        return aVar != null ? aVar.a() : null;
    }

    public final void d() {
        a aVar = this.a;
        if (aVar != null) {
            aVar.c();
        }
    }
}
