package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.e;
import com.google.android.exoplayer2.upstream.g;
import com.google.android.exoplayer2.util.z;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import java.io.IOException;

/* compiled from: SingleSampleMediaChunk */
/* renamed from: ju */
public final class ju extends ji {
    private final int k;
    private final Format l;
    private volatile int m;
    private volatile boolean n;

    public void a() {
    }

    public ju(e eVar, g gVar, Format format, int i, Object obj, long j, long j2, long j3, int i2, Format format2) {
        super(eVar, gVar, format, i, obj, j, j2, -9223372036854775807L, j3);
        this.k = i2;
        this.l = format2;
    }

    public boolean g() {
        return this.n;
    }

    public long e() {
        return (long) this.m;
    }

    public void b() throws IOException, InterruptedException {
        try {
            long a = this.i.a(this.b.a((long) this.m));
            if (a != -1) {
                a += (long) this.m;
            }
            gw gwVar = new gw(this.i, (long) this.m, a);
            jj c = c();
            c.a(0);
            int i = 0;
            hj a2 = c.a(0, this.k);
            a2.a(this.l);
            while (i != -1) {
                this.m += i;
                i = a2.a(gwVar, Filter.MAX, true);
            }
            a2.a(this.g, 1, this.m, 0, null);
            this.n = true;
        } finally {
            z.a(this.i);
        }
    }
}
