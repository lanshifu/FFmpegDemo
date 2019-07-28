package defpackage;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.source.j.a;

/* compiled from: AnalyticsCollector */
/* renamed from: gm$c */
final class gm$c {
    public final int a;
    public final a b;

    public gm$c(int i, a aVar) {
        this.a = i;
        this.b = aVar;
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        gm$c gm_c = (gm$c) obj;
        if (!(this.a == gm_c.a && this.b.equals(gm_c.b))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (this.a * 31) + this.b.hashCode();
    }
}
