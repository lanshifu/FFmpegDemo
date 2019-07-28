package defpackage;

import android.util.SparseArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.n;
import java.io.IOException;

/* compiled from: ChunkExtractorWrapper */
/* renamed from: jl */
public final class jl implements hb {
    public final gz a;
    private final int b;
    private final Format c;
    private final SparseArray<a> d = new SparseArray();
    private boolean e;
    private b f;
    private hh g;
    private Format[] h;

    /* compiled from: ChunkExtractorWrapper */
    /* renamed from: jl$b */
    public interface b {
        hj a(int i, int i2);
    }

    /* compiled from: ChunkExtractorWrapper */
    /* renamed from: jl$a */
    private static final class a implements hj {
        public Format a;
        private final int b;
        private final int c;
        private final Format d;
        private hj e;

        public a(int i, int i2, Format format) {
            this.b = i;
            this.c = i2;
            this.d = format;
        }

        public void a(b bVar) {
            if (bVar == null) {
                this.e = new gy();
                return;
            }
            this.e = bVar.a(this.b, this.c);
            if (this.a != null) {
                this.e.a(this.a);
            }
        }

        public void a(Format format) {
            if (this.d != null) {
                format = format.a(this.d);
            }
            this.a = format;
            this.e.a(this.a);
        }

        public int a(ha haVar, int i, boolean z) throws IOException, InterruptedException {
            return this.e.a(haVar, i, z);
        }

        public void a(n nVar, int i) {
            this.e.a(nVar, i);
        }

        public void a(long j, int i, int i2, int i3, defpackage.hj.a aVar) {
            this.e.a(j, i, i2, i3, aVar);
        }
    }

    public jl(gz gzVar, int i, Format format) {
        this.a = gzVar;
        this.b = i;
        this.c = format;
    }

    public hh b() {
        return this.g;
    }

    public Format[] c() {
        return this.h;
    }

    public void a(b bVar, long j) {
        this.f = bVar;
        if (this.e) {
            gz gzVar = this.a;
            if (j == -9223372036854775807L) {
                j = 0;
            }
            gzVar.a(0, j);
            for (int i = 0; i < this.d.size(); i++) {
                ((a) this.d.valueAt(i)).a(bVar);
            }
            return;
        }
        this.a.a((hb) this);
        if (j != -9223372036854775807L) {
            this.a.a(0, j);
        }
        this.e = true;
    }

    public hj a(int i, int i2) {
        hj hjVar = (a) this.d.get(i);
        if (hjVar == null) {
            a.b(this.h == null);
            hjVar = new a(i, i2, i2 == this.b ? this.c : null);
            hjVar.a(this.f);
            this.d.put(i, hjVar);
        }
        return hjVar;
    }

    public void a() {
        Format[] formatArr = new Format[this.d.size()];
        for (int i = 0; i < this.d.size(); i++) {
            formatArr[i] = ((a) this.d.valueAt(i)).a;
        }
        this.h = formatArr;
    }

    public void a(hh hhVar) {
        this.g = hhVar;
    }
}
