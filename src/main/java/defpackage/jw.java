package defpackage;

import android.net.Uri;
import com.google.android.exoplayer2.b;
import java.util.List;

/* compiled from: DashManifest */
/* renamed from: jw */
public class jw {
    public final long a;
    public final long b;
    public final boolean c;
    public final long d;
    public final long e;
    public final long f;
    public final long g;
    public final kc h;
    public final Uri i;
    private final List<jz> j;

    public final int a() {
        return this.j.size();
    }

    public final jz a(int i) {
        return (jz) this.j.get(i);
    }

    public final long b(int i) {
        if (i != this.j.size() - 1) {
            return ((jz) this.j.get(i + 1)).b - ((jz) this.j.get(i)).b;
        }
        if (this.b == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return this.b - ((jz) this.j.get(i)).b;
    }

    public final long c(int i) {
        return b.b(b(i));
    }
}
