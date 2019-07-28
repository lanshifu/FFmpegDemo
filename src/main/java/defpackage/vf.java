package defpackage;

import android.content.Context;

/* compiled from: AbstractValueCache */
/* renamed from: vf */
public abstract class vf<T> implements vh<T> {
    private final vh<T> a;

    public abstract T a(Context context);

    public abstract void a(Context context, T t);

    public vf(vh<T> vhVar) {
        this.a = vhVar;
    }

    public final synchronized T a(Context context, vi<T> viVar) throws Exception {
        T a;
        a = a(context);
        if (a == null) {
            a = this.a != null ? this.a.a(context, viVar) : viVar.a(context);
            b(context, a);
        }
        return a;
    }

    private void b(Context context, T t) {
        if (t != null) {
            a(context, (Object) t);
            return;
        }
        throw new NullPointerException();
    }
}
