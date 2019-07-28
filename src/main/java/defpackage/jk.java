package defpackage;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.Loader.c;
import com.google.android.exoplayer2.upstream.e;
import com.google.android.exoplayer2.upstream.g;
import com.google.android.exoplayer2.util.a;

/* compiled from: Chunk */
/* renamed from: jk */
public abstract class jk implements c {
    public final g b;
    public final int c;
    public final Format d;
    public final int e;
    @Nullable
    public final Object f;
    public final long g;
    public final long h;
    protected final e i;

    public abstract long e();

    public jk(e eVar, g gVar, int i, Format format, int i2, @Nullable Object obj, long j, long j2) {
        this.i = (e) a.a(eVar);
        this.b = (g) a.a(gVar);
        this.c = i;
        this.d = format;
        this.e = i2;
        this.f = obj;
        this.g = j;
        this.h = j2;
    }

    public final long d() {
        return this.h - this.g;
    }
}
