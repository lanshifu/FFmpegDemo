package defpackage;

/* compiled from: Range */
/* renamed from: ew */
public final class ew<T extends Comparable<? super T>> {
    private T a;
    private T b;

    public ew(T t, T t2) {
        if (t.compareTo(t2) <= 0) {
            this.a = t;
            this.b = t2;
            return;
        }
        throw new IllegalArgumentException("lower must be less than or equal to upper");
    }

    public boolean a(T t) {
        Object obj = t.compareTo(this.a) >= 0 ? 1 : null;
        Object obj2 = t.compareTo(this.b) <= 0 ? 1 : null;
        if (obj == null || obj2 == null) {
            return false;
        }
        return true;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ew)) {
            return false;
        }
        ew ewVar = (ew) obj;
        if (this.a.equals(ewVar.a) && this.b.equals(ewVar.b)) {
            z = true;
        }
        return z;
    }

    public String toString() {
        return String.format("[%s, %s]", new Object[]{this.a, this.b});
    }
}
