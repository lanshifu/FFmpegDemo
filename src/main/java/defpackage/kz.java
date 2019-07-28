package defpackage;

import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.text.e;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.z;
import java.util.Collections;
import java.util.List;

/* compiled from: SsaSubtitle */
/* renamed from: kz */
final class kz implements e {
    private final b[] a;
    private final long[] b;

    public kz(b[] bVarArr, long[] jArr) {
        this.a = bVarArr;
        this.b = jArr;
    }

    public int a(long j) {
        int b = z.b(this.b, j, false, false);
        return b < this.b.length ? b : -1;
    }

    public int b() {
        return this.b.length;
    }

    public long a(int i) {
        boolean z = false;
        a.a(i >= 0);
        if (i < this.b.length) {
            z = true;
        }
        a.a(z);
        return this.b[i];
    }

    public List<b> b(long j) {
        int a = z.a(this.b, j, true, false);
        if (a == -1 || this.a[a] == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(this.a[a]);
    }
}
