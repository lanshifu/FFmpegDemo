package defpackage;

import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.text.e;
import com.google.android.exoplayer2.util.a;
import java.util.Collections;
import java.util.List;

/* compiled from: Tx3gSubtitle */
/* renamed from: lj */
final class lj implements e {
    public static final lj a = new lj();
    private final List<b> b;

    public int a(long j) {
        return j < 0 ? 0 : -1;
    }

    public int b() {
        return 1;
    }

    public lj(b bVar) {
        this.b = Collections.singletonList(bVar);
    }

    private lj() {
        this.b = Collections.emptyList();
    }

    public long a(int i) {
        a.a(i == 0);
        return 0;
    }

    public List<b> b(long j) {
        return j >= 0 ? this.b : Collections.emptyList();
    }
}
