package defpackage;

import java.io.PrintStream;

/* compiled from: FinitePool */
/* renamed from: zt */
class zt<T extends zv<T>> implements zu<T> {
    private final zw<T> a;
    private final int b;
    private final boolean c;
    private T d;
    private int e;

    zt(zw<T> zwVar, int i) {
        if (i > 0) {
            this.a = zwVar;
            this.b = i;
            this.c = false;
            return;
        }
        throw new IllegalArgumentException("The pool limit must be > 0");
    }

    public T a() {
        zv zvVar;
        if (this.d != null) {
            zvVar = this.d;
            this.d = (zv) zvVar.l();
            this.e--;
        } else {
            zvVar = this.a.b();
        }
        if (zvVar != null) {
            zvVar.a(null);
            zvVar.a(false);
            this.a.b(zvVar);
        }
        return zvVar;
    }

    public void a(T t) {
        if (t.j()) {
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[FinitePool] Element is already in pool: ");
            stringBuilder.append(t);
            printStream.print(stringBuilder.toString());
            return;
        }
        if (this.c || this.e < this.b) {
            this.e++;
            t.a(this.d);
            t.a(true);
            this.d = t;
        }
        this.a.a(t);
    }
}
