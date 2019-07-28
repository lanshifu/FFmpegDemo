package defpackage;

import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;

/* compiled from: Buffer */
/* renamed from: go */
public abstract class go {
    private int a;

    public void a() {
        this.a = 0;
    }

    public final boolean e_() {
        return d(CheckView.UNCHECKED);
    }

    public final boolean c() {
        return d(4);
    }

    public final boolean d() {
        return d(1);
    }

    public final void b_(int i) {
        this.a = i;
    }

    public final void b(int i) {
        this.a = i | this.a;
    }

    public final void c(int i) {
        this.a = (i ^ -1) & this.a;
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean d(int i) {
        return (this.a & i) == i;
    }
}
