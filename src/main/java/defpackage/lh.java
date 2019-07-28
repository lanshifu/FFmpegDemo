package defpackage;

import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.text.e;
import com.google.android.exoplayer2.util.z;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: TtmlSubtitle */
/* renamed from: lh */
final class lh implements e {
    private final ld a;
    private final long[] b;
    private final Map<String, lg> c;
    private final Map<String, le> d;

    public lh(ld ldVar, Map<String, lg> map, Map<String, le> map2) {
        this.a = ldVar;
        this.d = map2;
        this.c = map != null ? Collections.unmodifiableMap(map) : Collections.emptyMap();
        this.b = ldVar.b();
    }

    public int a(long j) {
        int b = z.b(this.b, j, false, false);
        return b < this.b.length ? b : -1;
    }

    public int b() {
        return this.b.length;
    }

    public long a(int i) {
        return this.b[i];
    }

    public List<b> b(long j) {
        return this.a.a(j, this.c, this.d);
    }
}
