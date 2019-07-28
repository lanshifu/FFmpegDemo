package defpackage;

import android.support.annotation.Nullable;

/* compiled from: SeekPoint */
/* renamed from: hi */
public final class hi {
    public static final hi a = new hi(0, 0);
    public final long b;
    public final long c;

    public hi(long j, long j2) {
        this.b = j;
        this.c = j2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[timeUs=");
        stringBuilder.append(this.b);
        stringBuilder.append(", position=");
        stringBuilder.append(this.c);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        hi hiVar = (hi) obj;
        if (!(this.b == hiVar.b && this.c == hiVar.c)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (((int) this.b) * 31) + ((int) this.c);
    }
}
