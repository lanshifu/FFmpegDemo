package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.e;
import com.google.android.exoplayer2.upstream.g;

/* compiled from: BaseMediaChunk */
/* renamed from: ji */
public abstract class ji extends jt {
    public final long a;
    private jj k;
    private int[] l;

    public ji(e eVar, g gVar, Format format, int i, Object obj, long j, long j2, long j3, long j4) {
        super(eVar, gVar, format, i, obj, j, j2, j4);
        this.a = j3;
    }

    public void a(jj jjVar) {
        this.k = jjVar;
        this.l = jjVar.a();
    }

    public final int a(int i) {
        return this.l[i];
    }

    /* Access modifiers changed, original: protected|final */
    public final jj c() {
        return this.k;
    }
}
