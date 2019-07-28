package defpackage;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.util.y;

/* compiled from: RangedUri */
/* renamed from: ka */
public final class ka {
    public final long a;
    public final long b;
    private final String c;
    private int d;

    public ka(@Nullable String str, long j, long j2) {
        if (str == null) {
            str = "";
        }
        this.c = str;
        this.a = j;
        this.b = j2;
    }

    public Uri a(String str) {
        return y.a(str, this.c);
    }

    public String b(String str) {
        return y.b(str, this.c);
    }

    @Nullable
    public ka a(@Nullable ka kaVar, String str) {
        String b = b(str);
        if (kaVar == null || !b.equals(kaVar.b(str))) {
            return null;
        }
        long j = -1;
        long j2;
        if (this.b != -1 && this.a + this.b == kaVar.a) {
            j2 = this.a;
            if (kaVar.b != -1) {
                j = this.b + kaVar.b;
            }
            return new ka(b, j2, j);
        } else if (kaVar.b == -1 || kaVar.a + kaVar.b != this.a) {
            return null;
        } else {
            j2 = kaVar.a;
            if (this.b != -1) {
                j = kaVar.b + this.b;
            }
            return new ka(b, j2, j);
        }
    }

    public int hashCode() {
        if (this.d == 0) {
            this.d = ((((527 + ((int) this.a)) * 31) + ((int) this.b)) * 31) + this.c.hashCode();
        }
        return this.d;
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ka kaVar = (ka) obj;
        if (!(this.a == kaVar.a && this.b == kaVar.b && this.c.equals(kaVar.c))) {
            z = false;
        }
        return z;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RangedUri(referenceUri=");
        stringBuilder.append(this.c);
        stringBuilder.append(", start=");
        stringBuilder.append(this.a);
        stringBuilder.append(", length=");
        stringBuilder.append(this.b);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
