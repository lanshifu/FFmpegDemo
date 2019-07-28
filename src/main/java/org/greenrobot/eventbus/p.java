package org.greenrobot.eventbus;

/* compiled from: Subscription */
final class p {
    final Object a;
    final n b;
    volatile boolean c = true;

    p(Object obj, n nVar) {
        this.a = obj;
        this.b = nVar;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        if (this.a == pVar.a && this.b.equals(pVar.b)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return this.a.hashCode() + this.b.f.hashCode();
    }
}
