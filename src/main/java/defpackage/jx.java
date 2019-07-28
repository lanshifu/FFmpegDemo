package defpackage;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.util.z;

/* compiled from: Descriptor */
/* renamed from: jx */
public final class jx {
    @NonNull
    public final String a;
    @Nullable
    public final String b;
    @Nullable
    public final String c;

    public boolean equals(@Nullable Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        jx jxVar = (jx) obj;
        if (!(z.a(this.a, jxVar.a) && z.a(this.b, jxVar.b) && z.a(this.c, jxVar.c))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((this.a != null ? this.a.hashCode() : 0) * 31) + (this.b != null ? this.b.hashCode() : 0)) * 31;
        if (this.c != null) {
            i = this.c.hashCode();
        }
        return hashCode + i;
    }
}
