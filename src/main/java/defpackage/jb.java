package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.w;
import defpackage.jd.d;

/* compiled from: SpliceInfoSectionReader */
/* renamed from: jb */
public final class jb implements iy {
    private w a;
    private hj b;
    private boolean c;

    public void a(w wVar, hb hbVar, d dVar) {
        this.a = wVar;
        dVar.a();
        this.b = hbVar.a(dVar.b(), 4);
        this.b.a(Format.a(dVar.c(), "application/x-scte35", null, -1, null));
    }

    public void a(n nVar) {
        if (!this.c) {
            if (this.a.c() != -9223372036854775807L) {
                this.b.a(Format.a(null, "application/x-scte35", this.a.c()));
                this.c = true;
            } else {
                return;
            }
        }
        int b = nVar.b();
        this.b.a(nVar, b);
        this.b.a(this.a.b(), 1, b, 0, null);
    }
}
