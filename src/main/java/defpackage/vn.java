package defpackage;

/* compiled from: RetryState */
/* renamed from: vn */
public class vn {
    private final int a;
    private final vj b;
    private final vm c;

    public vn(vj vjVar, vm vmVar) {
        this(0, vjVar, vmVar);
    }

    public vn(int i, vj vjVar, vm vmVar) {
        this.a = i;
        this.b = vjVar;
        this.c = vmVar;
    }

    public long a() {
        return this.b.a(this.a);
    }

    public vn b() {
        return new vn(this.a + 1, this.b, this.c);
    }

    public vn c() {
        return new vn(this.b, this.c);
    }
}
