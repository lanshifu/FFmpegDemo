package defpackage;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.y;
import com.google.android.exoplayer2.util.z;
import java.util.List;

/* compiled from: SsManifest */
/* renamed from: km */
public class km {
    public final boolean a;
    public final a b;
    public final b[] c;
    public final long d;
    public final long e;

    /* compiled from: SsManifest */
    /* renamed from: km$a */
    public static class a {
        public final byte[] a;
    }

    /* compiled from: SsManifest */
    /* renamed from: km$b */
    public static class b {
        public final int a;
        public final long b;
        public final Format[] c;
        public final int d;
        private final String e;
        private final String f;
        private final List<Long> g;
        private final long[] h;
        private final long i;

        public int a(long j) {
            return z.a(this.h, j, true, true);
        }

        public long a(int i) {
            return this.h[i];
        }

        public long b(int i) {
            return i == this.d + -1 ? this.i : this.h[i + 1] - this.h[i];
        }

        public Uri a(int i, int i2) {
            boolean z = false;
            a.b(this.c != null);
            a.b(this.g != null);
            if (i2 < this.g.size()) {
                z = true;
            }
            a.b(z);
            String num = Integer.toString(this.c[i].b);
            String l = ((Long) this.g.get(i2)).toString();
            return y.a(this.e, this.f.replace("{bitrate}", num).replace("{Bitrate}", num).replace("{start time}", l).replace("{start_time}", l));
        }
    }
}
