package defpackage;

import android.content.Context;

/* compiled from: MemoryValueCache */
/* renamed from: vg */
public class vg<T> extends vf<T> {
    private T a;

    public vg() {
        this(null);
    }

    public vg(vh<T> vhVar) {
        super(vhVar);
    }

    /* Access modifiers changed, original: protected */
    public T a(Context context) {
        return this.a;
    }

    /* Access modifiers changed, original: protected */
    public void a(Context context, T t) {
        this.a = t;
    }
}
