package defpackage;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.e;
import com.google.android.exoplayer2.upstream.g;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.z;
import java.io.IOException;

/* compiled from: InitializationChunk */
/* renamed from: js */
public final class js extends jk {
    private final jl a;
    private volatile int j;
    private volatile boolean k;

    public js(e eVar, g gVar, Format format, int i, @Nullable Object obj, jl jlVar) {
        super(eVar, gVar, 2, format, i, obj, -9223372036854775807L, -9223372036854775807L);
        this.a = jlVar;
    }

    public long e() {
        return (long) this.j;
    }

    public void a() {
        this.k = true;
    }

    public void b() throws IOException, InterruptedException {
        g a = this.b.a((long) this.j);
        ha gwVar;
        try {
            gwVar = new gw(this.i, a.c, this.i.a(a));
            if (this.j == 0) {
                this.a.a(null, -9223372036854775807L);
            }
            gz gzVar = this.a.a;
            int i = 0;
            while (i == 0 && !this.k) {
                i = gzVar.a(gwVar, null);
            }
            boolean z = true;
            if (i == 1) {
                z = false;
            }
            a.b(z);
            this.j = (int) (gwVar.c() - this.b.c);
            z.a(this.i);
        } catch (Throwable th) {
            z.a(this.i);
        }
    }
}
