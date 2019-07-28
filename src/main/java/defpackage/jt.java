package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.e;
import com.google.android.exoplayer2.upstream.g;
import com.google.android.exoplayer2.util.a;

/* compiled from: MediaChunk */
/* renamed from: jt */
public abstract class jt extends jk {
    public final long j;

    public abstract boolean g();

    public jt(e eVar, g gVar, Format format, int i, Object obj, long j, long j2, long j3) {
        super(eVar, gVar, 1, format, i, obj, j, j2);
        a.a(format);
        this.j = j3;
    }

    public long f() {
        return this.j != -1 ? 1 + this.j : -1;
    }
}
