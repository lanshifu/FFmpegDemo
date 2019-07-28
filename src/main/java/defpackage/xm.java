package defpackage;

import io.reactivex.internal.functions.a;
import java.util.concurrent.TimeUnit;

/* compiled from: Timed */
/* renamed from: xm */
public final class xm<T> {
    final T a;
    final long b;
    final TimeUnit c;

    public xm(T t, long j, TimeUnit timeUnit) {
        this.a = t;
        this.b = j;
        this.c = (TimeUnit) a.a((Object) timeUnit, "unit is null");
    }

    public T a() {
        return this.a;
    }

    public long b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof xm)) {
            return false;
        }
        xm xmVar = (xm) obj;
        if (a.a(this.a, xmVar.a) && this.b == xmVar.b && a.a(this.c, xmVar.c)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return ((((this.a != null ? this.a.hashCode() : 0) * 31) + ((int) ((this.b >>> 31) ^ this.b))) * 31) + this.c.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Timed[time=");
        stringBuilder.append(this.b);
        stringBuilder.append(", unit=");
        stringBuilder.append(this.c);
        stringBuilder.append(", value=");
        stringBuilder.append(this.a);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
