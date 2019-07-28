package defpackage;

import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.text.e;
import com.google.android.exoplayer2.util.a;
import java.util.Collections;
import java.util.List;

/* compiled from: CeaSubtitle */
/* renamed from: kr */
final class kr implements e {
    private final List<b> a;

    public int a(long j) {
        return j < 0 ? 0 : -1;
    }

    public int b() {
        return 1;
    }

    public kr(List<b> list) {
        this.a = list;
    }

    public long a(int i) {
        a.a(i == 0);
        return 0;
    }

    public List<b> b(long j) {
        return j >= 0 ? this.a : Collections.emptyList();
    }
}
